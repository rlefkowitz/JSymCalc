package binary_functions;

import functions.BinaryFunction;
import functions.Function;
import functions.Main;

public class Diff extends BinaryFunction {

	public Diff(Function f, Function g) {
		super(f, g);
	}

	public Diff(double f, Function g) {
		super(f, g);
	}

	public Diff(Function f, double g) {
		super(f, g);
	}

	public Diff(double f, double g) {
		super(f, g);
	}

	public double output(double v) {
		return f.output(v) - g.output(v);
	}

	public Function derivative() {
		return new Diff(f.derivative(), g.derivative());
	}
	
	public String toString() {
		if(Main.DEBUG)
			return "diff(" + f + ", " + g + ")";
		return f + " - " + g;
	}

	public boolean equals(Function other) {
		if(other instanceof Diff) 
			return (f.equals(((Diff) other).f) && g.equals(((Diff) other).g));
		return false;
	}

}
