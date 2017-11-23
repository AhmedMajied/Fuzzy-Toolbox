import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class FuzzyToolbox {
	
	public static double performFuzzyLogic(ArrayList<SetsBlock> setsBlocks,ArrayList<Rule> rules,SetsBlock output){
		
		// fuzzification step
		for(int blockIndex = 0;blockIndex < setsBlocks.size();blockIndex++){
			fuzzyfying(setsBlocks.get(blockIndex));
		}
		// inference step
		for(int NumofRules = 0;NumofRules < rules.size();NumofRules++){
			inference(rules.get(NumofRules),setsBlocks);
		}
		
		return defuzzify(rules,output);
		
	}
	
	private static void fuzzyfying(SetsBlock setsBlock){
		double slope;
		
		for(int shapeIndex = 0;shapeIndex < setsBlock.shapes.size();shapeIndex++){
			Shape shape = setsBlock.shapes.get(shapeIndex);
			
			// calculate value of y which is intersection between crisp and shape
			for(int pointIndex = 1;pointIndex < shape.points.size();pointIndex++){
				if(shape.points.get(pointIndex).x >= setsBlock.varaibleCrisp && shape.points.get(pointIndex-1).x <= setsBlock.varaibleCrisp){
					// horizontal line (in trapezoidal)
					if(shape.points.get(pointIndex).y == shape.points.get(pointIndex-1).y){
						shape.valueOfCrisp = shape.points.get(pointIndex).y;
					}
					else if (shape.points.get(pointIndex).x == setsBlock.varaibleCrisp) {
						shape.valueOfCrisp = shape.points.get(pointIndex).y;
					}
					else if (shape.points.get(pointIndex-1).x == setsBlock.varaibleCrisp) {
						shape.valueOfCrisp = shape.points.get(pointIndex-1).y;
					}
					else if(shape.points.get(pointIndex).x != shape.points.get(pointIndex-1).x){
						slope = (shape.points.get(pointIndex).y - shape.points.get(pointIndex-1).y) /
								(shape.points.get(pointIndex).x - shape.points.get(pointIndex-1).x);
						
						shape.valueOfCrisp = slope * setsBlock.varaibleCrisp;
					}
					break;
				}
			}
		}
	}
	
	private static void inference(Rule rule,ArrayList<SetsBlock> setsBlocks){
		Pair temb;
		String operation;
		Stack<Double> ValueOfCrisp = new Stack<Double>();
		for(int i=rule.input.size()-1;i>=0;i--){
			temb=rule.input.get(i);
			for(int blockIndex = 0;blockIndex < setsBlocks.size();blockIndex++){
				if(setsBlocks.get(blockIndex).variableName.equals(temb.name)){
					for(int shapeIndex = 0;shapeIndex < setsBlocks.get(blockIndex).shapes.size();shapeIndex++){
						operation=setsBlocks.get(blockIndex).shapes.get(shapeIndex).name;
						
						if(operation.equals(temb.value)){
							ValueOfCrisp.push(setsBlocks.get(blockIndex).shapes.get(shapeIndex).valueOfCrisp);
							break;
						}
					}
				}
			}
		}
		
		Stack<Double> resultOfRules = new Stack<>();
		while(!rule.predicts.empty() && !ValueOfCrisp.empty()){
			resultOfRules.push(applyPredict(ValueOfCrisp.pop(),ValueOfCrisp.pop(),rule.predicts.pop()));
		}
		rule.result=resultOfRules.pop();
	}
	
	public static double applyPredict(double first,double second,String op){
		double temb=-1;
		
		if(op.equals("OR")){
			if(first<second)
				temb=second;
			else
				temb=first;
		}else if(op.equals("AND")){
			if(first>second)
				temb=second;
			else
				temb=first;
		}
		
		return temb;
	}
	
	public static double defuzzify(ArrayList<Rule>inferenceResult,SetsBlock output) {
		Map<String,Double> centroid=new HashMap<String,Double>();
		for(int i=0;i<output.shapes.size();++i) {
			centroid.put(output.shapes.get(i).name,getCentroid(output.shapes.get(i).points));
		}
		double sum1=0;
		double sum2=0;
		for(int i=0;i<inferenceResult.size();++i) {
			sum1+=inferenceResult.get(i).result*centroid.get(inferenceResult.get(i).predictedSetsBlockName);
			sum2+=inferenceResult.get(i).result;
		}
		
		return sum1/sum2;
	}
	
	public static double A(ArrayList<Point>points) {
		double sum=0;
		for(int i=0;i<points.size()-1;++i) {
			sum+=points.get(i+1).y*points.get(i).x-points.get(i+1).x*points.get(i).y;
		}
		
		return sum/2;
	}
	
	public static double getCentroid(ArrayList<Point>points) {
		double a = 6*A(points);
		double sum=0;
		for(int i=0;i<points.size()-1;++i) {
			sum+=(points.get(i+1).x+points.get(i).x)*(points.get(i+1).y*points.get(i).x-points.get(i+1).x*points.get(i).y);
		}
		
		return sum/a;
	}
}








