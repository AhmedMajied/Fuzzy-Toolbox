import java.util.ArrayList;
import java.util.Stack;

public class Rule {
	public int premises;
	public double result;
	public Stack<String> predicts;
	public ArrayList<Pair> input;
	public String predictedSetsBlockName;
	
	public Rule(int premises,double result,Stack<String> predict,ArrayList<Pair> input,String predictedSetsBlockName){
		this.premises = premises;
		this.result = result;
		this.predicts = predict;
		this.input = input;
		this.predictedSetsBlockName = predictedSetsBlockName;
	}
}
