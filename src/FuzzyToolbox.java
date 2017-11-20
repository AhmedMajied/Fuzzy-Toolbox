import java.util.ArrayList;

public class FuzzyToolbox {
	
	public static void performFuzzyLogic(ArrayList<SetsBlock> setsBlocks,SetsBlock output){
		
		// fuzzification step
		for(int blockIndex = 0;blockIndex < setsBlocks.size();blockIndex++){
			fuzzyfying(setsBlocks.get(blockIndex));
		}
		
		// inference step
		
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
}
