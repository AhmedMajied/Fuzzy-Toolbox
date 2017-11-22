
public class Rule {
	public double premises;//from input
	public double result;
	public String predict;//from input ("AND" || "OR")
	public String position;//from input ("Left","LeftCenter",...)
	public String angel;//from input ("RBelow","RUpper",...)
	public String firePosition;//from input ("NegBig","NegSm",...)
	
	public Rule(double premises,double result,String predict,String position,String angel,String firePosition){
		this.premises = premises;
		this.result = result;
		this.predict = predict;
		this.position = position;
		this.angel = angel;
		this.firePosition = firePosition;
	}
}
