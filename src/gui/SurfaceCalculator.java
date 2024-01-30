package gui;

import java.awt.Dimension;

import measurement.MeasurementUnit;
/**
 * @author JonTheZero
 * */
public class SurfaceCalculator {
	
	private String length,width;
	private boolean isReady;
	public SurfaceCalculator(String length,String width) throws SexyException {
		String validLength=MeasurementUnit.getValidStringNumber(length),
			   validWidth=MeasurementUnit.getValidStringNumber(width);
		if(validLength!=null && validWidth!=null) {
			isReady=true;
		}else if(validLength==null && validWidth==null){
			throw new SexyException("length","width") ;
		}else if(validLength==null) {
			throw new SexyException("length") ;
		}else if(validWidth==null) {
			throw new SexyException("width");
		}
		this.length=length;
		this.width=width;
	}
	public static void main(String[] args) {
		XFrame xf=new XFrame();
	}
	//TO FIX
	//something if correct, null if invalid
	
	public Double convertToMeters(String distance) {
		Double numberPart,result=null;
		Double coeff=1.0;
		String validDistance=MeasurementUnit.getValidStringNumber(distance);
		MeasurementUnit unitPart;
		if(validDistance!=null) {
			numberPart=MeasurementUnit.getDecimalPart(validDistance);
			unitPart=MeasurementUnit.getUnitPart(validDistance);
			if(unitPart!=null && numberPart!=null) {
				System.out.println(unitPart.toString());
				switch(unitPart.toString()) {
					case "METERS" : break;
					case "CENTIMETERS" : coeff=0.01;
										 break;
					case "MILLIMETERS" : coeff=0.001;
										break;
					default :   result=null;
								break;
				}
			}
			result=coeff*numberPart;
		}
		return result;
	}
	public Double getSurface() {
		Double l=convertToMeters(this.length);
		Double w=convertToMeters(this.width);
		System.out.println(l*w);
		return l*w;
		
	}
	public boolean getIsReady() {
		return this.isReady;
	}
	
	
}
