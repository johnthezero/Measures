package measurement;

import java.util.ArrayList;
import java.util.Arrays;

public enum MeasurementUnit {
	METERS ("m"),
	CENTIMETERS("cm"),
	MILLIMETERS("mm");
	
	public final String unit;
	MeasurementUnit(String unit){
		this.unit=unit;
	}
	
	public static String getValidStringNumber(String numberAndUnit)throws NumberFormatException,NullPointerException { 

		boolean isValid=false;
		if(numberAndUnit.charAt(0)=='+' || numberAndUnit.charAt(0)=='-') {
			numberAndUnit=numberAndUnit.substring(1);
		}
		String cleanParam=numberAndUnit.trim();
		boolean firstChar=true;
		int indexOfFirstComma=-1;
		ArrayList<Character>list=new ArrayList<Character>();
		for(char s : cleanParam.toCharArray()) {
			if(firstChar && (s=='.' || s==',')) {
				list.add('0');
				list.add('.');
			}else {
				if(s!=' ' && s!=',') {
					list.add(s);
					if(s==',') {
						list.add('.');
					}
				}
			}
			firstChar=false;
			
		}
		if(list.contains(',') || list.contains('.')) {
			int comma=-1,dot=-1;
			if(list.contains(',')) {
				comma=list.indexOf(',');
			}
			if(list.contains('.')) {
				dot=list.indexOf('.');
			}
			if(comma<=dot) {
				indexOfFirstComma=comma;
			}else{
				indexOfFirstComma=dot;
			}
		}

		if(indexOfFirstComma!=-1) {
			for(int i=indexOfFirstComma+1;i<list.size();i++) {
				if(list.get(i)=='.' || list.get(i)==',') {
					list.remove(i);
					--i;
				}
			}
		}
			boolean flag=false;//is there a dot or a comma in it ?
			boolean first=true; //is this the first character ?
			cleanParam="";
			for(char c : list) {
					if(c!='.') {
						cleanParam=cleanParam.concat(String.valueOf(c));
						first=false;
					}else {
						if(!flag) {
							if(first) {
								cleanParam=cleanParam.concat("0");
								cleanParam=cleanParam.concat(String.valueOf(c));
							}else {
								cleanParam=cleanParam.concat(String.valueOf(c));
							}
							first=false;
							flag=true;
						}
					}
			}
		
			
		String nums= "0123456789.,";
		String number="";
		String unit="";
		for(int i=0;i<cleanParam.length();i++) {
			for(int j=0;j<nums.length();j++) {
				if(cleanParam.charAt(i)==nums.charAt(j)) {
					number=number.concat(String.valueOf(cleanParam.charAt(i)));
				}
			}
		}
		
		Double decimal=null;
		unit=cleanParam.substring(number.length());
		try {
			decimal=Double.parseDouble(number);
		}catch(NullPointerException e) {
			e.printStackTrace();
		}catch(NumberFormatException e) {
			e.printStackTrace();
		}
		
		
		//A ce stade number est initialisé
		switch(unit) {
			case "m" : isValid=(decimal!=null);
					   break;
			case "cm" : isValid=(decimal!=null);
			   			break;
			case "mm" : isValid=(decimal!=null);
			   			break;   			
		}
		if(isValid) {
			return number+unit;
		}else {
			return null;
		}
	}
	public static Double getDecimalPart(String aValidStringNumber) {
		Double res=null;
		boolean flag=false;
		String s="";
		if(aValidStringNumber!=null) {
			for(int i=0;i<aValidStringNumber.length();i++) {
				if(aValidStringNumber.charAt(i)=='m' || aValidStringNumber.charAt(i)=='c') {
					break;
				}else {
					s=s.concat(String.valueOf(aValidStringNumber.charAt(i)));
				}	
			}
			res=Double.parseDouble(s);
		}
		return res;
	}
	
	public static MeasurementUnit getUnitPart(String aValidStringNumber) {
		String s ="";
		MeasurementUnit m=null;
		for(char c : aValidStringNumber.toCharArray()) {
			if(c=='c' || c=='m') {
				s=s.concat(String.valueOf(c));
			}
		}
		switch(s) {
			case "mm" : m=MeasurementUnit.MILLIMETERS;
						break;
			case "m" :  m=MeasurementUnit.METERS;
						break;
			case "cm" : m=MeasurementUnit.CENTIMETERS;
						break;
			default : s=null;
		}
		return m;
	}
	
	
}
