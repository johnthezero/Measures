package gui;

import java.util.ArrayList;
import java.util.List;

public class SexyException extends Exception {
	private String message="Exception";
	private ArrayList<String> units;
	public SexyException(String...vars) {
		super();
		units=new ArrayList<String>();
		units.add("mm");
		units.add("cm");
		units.add("m");
		if(vars.length==1) {
			this.message="The "+vars[0]+" is not valid, must be a valid number followed by a valid unit ex: "+units.toString();
		}else if(vars.length==2) {
			this.message="The "+vars[0]+" is not valid, must be a valid number followed by a valid unit ex: "+units.toString()+"\r\n"
					+"The "+vars[1]+" is not valid, must be a valid number followed by a valid unit ex: "+units.toString();
		}
		
	}
	public String getMessage() {
		return this.message;
	}
}
