package org.araport.validation.domain;

public class ReaderLine {
	
	private String line;
	
	public ReaderLine(){
		
	}

	public ReaderLine(String line){
		this.line = line;
	}
	
	public String getLine() {
		return line;
	}

	public void setLine(String line) {
		this.line = line;
	}

	@Override
	public String toString() {
		return "ReaderLine [line=" + line + "]";
	}
	
	

}
