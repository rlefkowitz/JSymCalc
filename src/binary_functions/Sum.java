package binary_functions;

import functions.BinaryFunction;
import functions.Function;
import functions.Main;

public class Sum extends BinaryFunction {

	public Sum(Function f, Function g) {
		super(f, g);
	}

	public Sum(double f, Function g) {
		super(f, g);
	}

	public Sum(Function f, double g) {
		super(f, g);
	}

	public Sum(double f, double g) {
		super(f, g);
	}

	public double output(double v) {
		return f.output(v) + g.output(v);
	}

	public Function derivative() {
		return new Sum(f.derivative(), g.derivative());
	}
	
	public String toString() {
		if(Main.DEBUG)
			return "sum(" + f + ", " + g + ")";
		return f + " + " + g;
	}

	public boolean equals(Function other) {
		if(other instanceof Sum) 
			return (f.equals(((Sum) other).f) && g.equals(((Sum) other).g));
		return false;
	}

}
