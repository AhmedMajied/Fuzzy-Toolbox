import java.util.ArrayList;

public class Shape {
	public String name;
	public double valueOfCrisp;
	public ArrayList <Point> points;
	
	public Shape(String name,ArrayList<Point> points){
		this.name = name;
		this.points = points;
		valueOfCrisp = 0;
	}

}
