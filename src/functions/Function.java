package functions;

public interface Function {
	
	double output(double v);
	Function derivative();
	String toString();
	boolean equals(Function other);

}
