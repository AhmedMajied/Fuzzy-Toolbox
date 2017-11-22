import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class FuzzyToolbox {
	
	public static void performFuzzyLogic(ArrayList<SetsBlock> setsBlocks,ArrayList<Rule> rules,SetsBlock output){
		
		// fuzzification step
		for(int blockIndex = 0;blockIndex < setsBlocks.size();blockIndex++){
			fuzzyfying(setsBlocks.get(blockIndex));
		}
		
		// inference step
		for(int NumofRules = 0;NumofRules < rules.size();NumofRules++){
			inference(rules.get(NumofRules),setsBlocks);
		}
		
		// defuzzification step
		
	}
	
	private static void fuzzyfying(SetsBlock setsBlock){
		double slope;
		
		for(int shapeIndex = 0;shapeIndex < setsBlock.shapes.size();shapeIndex++){
			Shape shape = setsBlock.shapes.get(shapeIndex);
			
			// calculate value of y which is intersection between crisp and shape
			for(int pointIndex = 1;pointIndex < shape.points.size();pointIndex++){
				if(shape.points.get(pointIndex).x > setsBlock.varaibleCrisp){
					// horizontal line (in trapezoidal)
					if(shape.points.get(pointIndex).y == shape.points.get(pointIndex-1).y){
						shape.valueOfCrisp = shape.points.get(pointIndex).y;
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
<<<<<<< HEAD
	
	private static void inference(Rule rule,ArrayList<SetsBlock> setsBlocks){
		double position=0, angel=0;
		for(int blockIndex = 0;blockIndex < setsBlocks.size();blockIndex++){
			if(setsBlocks.get(blockIndex).variableName.equals("position")){
				for(int shapeIndex = 0;shapeIndex < setsBlocks.get(blockIndex).shapes.size();shapeIndex++){
					String operation=setsBlocks.get(blockIndex).shapes.get(shapeIndex).name;
					if(operation.equals(rule.position)){
						position=setsBlocks.get(blockIndex).shapes.get(shapeIndex).valueOfCrisp;
					}
				}
			}else if(setsBlocks.get(blockIndex).variableName.equals("angel")){
				for(int shapeIndex = 0;shapeIndex < setsBlocks.get(blockIndex).shapes.size();shapeIndex++){
					String operation=setsBlocks.get(blockIndex).shapes.get(shapeIndex).name;
					if(operation.equals(rule.angel)){
						angel=setsBlocks.get(blockIndex).shapes.get(shapeIndex).valueOfCrisp;
					}
				}
			}
		}
		for(int premis=1 ; premis<rule.premises ; premis++){
			if(rule.predict.equals("AND")){
				if(position<angel)
					rule.result=position;
				else
					rule.result=angel;
			}else if(rule.predict.equals("OR")){
				if(position>angel)
					rule.result=position;
				else
					rule.result=angel;
			}
		}
=======
	public static double defuzzify(ArrayList<Pair>inferenceResult,SetsBlock output) {
		Map<String,Double> centroid=new HashMap<String,Double>();
		for(int i=0;i<output.shapes.size();++i) {
			centroid.put(output.shapes.get(i).name,getCentroid(output.shapes.get(i).points));
		}
		double sum1=0;
		double sum2=0;
		for(Pair i : inferenceResult) {
			sum1+=i.value*centroid.get(i.name);
			sum2+=i.value;
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
>>>>>>> 1c0ac8e06adbd53fee177a72b36f11b5440ff0bc
	}
}








