import java.util.ArrayList;

public class SetsBlock {
	public String variableName;
	public double varaibleCrisp;
	public ArrayList<Shape> shapes;
	
	public SetsBlock(String VName, double VCrisp,ArrayList<Shape> shapes){
		variableName = VName;
		varaibleCrisp = VCrisp;
		this.shapes = shapes;
	}	
}
