import java.util.ArrayList;

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
	}
}








