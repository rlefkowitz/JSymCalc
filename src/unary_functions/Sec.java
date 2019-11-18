package unary_functions;

import binary_functions.Product;
import functions.Function;
import functions.Main;
import functions.UnaryFunction;

public class Sec extends UnaryFunction {

	public Sec(Function f) {
		super(f);
	}

	public Sec(double f) {
		super(f);
	}

	public double output(double v) {
		return 1. / Math.cos(f.output(v));
	}

	public Function derivative() {
		return new Product(f.derivative(), new Product(new Sec(f), new Tan(f)));
	}
	
	public String toString() {
		if(Main.LATEX) {
			if(f instanceof Var)
				return "\\sec(x)";
			return "\\sec\\left(" + f + "\\right)";
		}
		return "sec(" + f + ")";
	}

	public boolean equals(Function other) {
		if(other instanceof Sec)
			return f.equals(((Sec) other).f);
		return false;
	}
	
}
