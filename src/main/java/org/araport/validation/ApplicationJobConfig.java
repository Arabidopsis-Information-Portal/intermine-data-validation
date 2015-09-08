package org.araport.validation;

import org.araport.validation.bean.ListenerBeans;
import org.araport.validation.bean.ProcessorBeans;
import org.araport.validation.bean.TaskBeans;
import org.araport.validation.domain.Person;
import org.araport.validation.domain.TAIRLocusPublication;
import org.araport.validation.listener.LogProcessListener;
import org.araport.validation.processor.PersonItemProcessor;
import org.araport.validation.reader.PersonReader;
import org.araport.validation.reader.TAIRPublicationReader;
import org.araport.validation.tasklet.PostDeployTasklet;
import org.araport.validation.tasklet.StagingSchemaInitTasklet;
import org.araport.validation.writer.PersonWriter;
import org.araport.validation.writer.TAIRPublicationWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.explore.support.SimpleJobExplorer;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.ResourceLoader;

@Configuration
@EnableBatchProcessing
@PropertySources(value = { @PropertySource("classpath:/in/data_source.properties") })
@Import({ PersonWriter.class, PersonReader.class, ListenerBeans.class,
		ProcessorBeans.class, TAIRPublicationReader.class,
		TAIRPublicationWriter.class, TaskBeans.class })
public class ApplicationJobConfig {

	private static final Logger log = LoggerFactory
			.getLogger(ApplicationJobConfig.class);

	@Autowired
	Environment environment;

	@Autowired
	private ResourceLoader resourceLoader;

	@Autowired
	private JobBuilderFactory jobBuilders;

	@Autowired
	private StepBuilderFactory stepBuilders;

	@Autowired
	StagingSchemaInitTasklet stagingSchemaInit;

	@Autowired
	PostDeployTasklet postDeployTask;

	@Autowired
	ItemWriter<Person> personWriter;

	@Autowired
	ItemReader<Person> personReader;

	@Autowired
	ItemProcessor<Person, Person> personProcessor;

	@Autowired
	ItemProcessor<TAIRLocusPublication, TAIRLocusPublication> tairPublicationProcessor;

	@Autowired
	ItemReader<TAIRLocusPublication> tairLocusPublicationReader;

	@Autowired
	ItemWriter<TAIRLocusPublication> tairPublicationWriter;

	@Autowired
	LogProcessListener logProcessListener;

	@Bean
	public ResourcelessTransactionManager transactionManager() {
		return new ResourcelessTransactionManager();
	}

	@Bean
	public MapJobRepositoryFactoryBean mapJobRepositoryFactory(
			ResourcelessTransactionManager txManager) throws Exception {
		MapJobRepositoryFactoryBean factory = new MapJobRepositoryFactoryBean(
				txManager);
		factory.afterPropertiesSet();
		return factory;
	}

	@Bean
	public JobRepository jobRepository(MapJobRepositoryFactoryBean factory)
			throws Exception {
		return factory.getObject();
	}

	@Bean
	public JobExplorer jobExplorer(MapJobRepositoryFactoryBean factory) {
		return new SimpleJobExplorer(factory.getJobInstanceDao(),
				factory.getJobExecutionDao(), factory.getStepExecutionDao(),
				factory.getExecutionContextDao());
	}

	@Bean
	public SimpleJobLauncher jobLauncher(JobRepository jobRepository) {
		SimpleJobLauncher launcher = new SimpleJobLauncher();
		launcher.setJobRepository(jobRepository);
		return launcher;
	}

	@Bean
	public Step step1() {
		return stepBuilders.get("step1").<Person, Person> chunk(10)
				.reader(personReader).processor(personProcessor)
				.writer(personWriter).listener(logProcessListener).build();
	}

	@Bean
	public Step step2() {
		return stepBuilders.get(StepConfig.TAIR_PUBLICATION_FLAT_FILE_STEP)
				.<TAIRLocusPublication, TAIRLocusPublication> chunk(10)
				.reader(tairLocusPublicationReader)
				.processor(tairPublicationProcessor)
				.writer(tairPublicationWriter).listener(logProcessListener)
				.build();
	}

	@Bean
	public Job job() {
		return jobBuilders.get("validationJob").start(stagingSchemaInitStep())
				.next(step1()).next(step2()).next(postDeployStep()).build();
	}

	@Bean
	public Step stagingSchemaInitStep() {

		StepBuilder stepBuilder = stepBuilders
				.get("STAGING_DB_INITIALIZATION_STEP");

		Step step = stepBuilder.tasklet(stagingSchemaInit).build();

		return step;

	}

	@Bean
	public Step postDeployStep() {

		StepBuilder stepBuilder = stepBuilders.get(StepConfig.POSTDEPLOY_STEP);

		Step step = stepBuilder.tasklet(postDeployTask).build();

		return step;

	}
}
