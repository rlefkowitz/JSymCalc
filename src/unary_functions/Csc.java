package unary_functions;

import binary_functions.Product;
import functions.Function;
import functions.Main;
import functions.UnaryFunction;

public class Csc extends UnaryFunction {

	public Csc(Function f) {
		super(f);
	}

	public Csc(double f) {
		super(f);
	}

	public double output(double v) {
		return 1. / Math.sin(f.output(v));
	}

	public Function derivative() {
		return new Neg(new Product(f.derivative(), new Product(new Csc(f), new Cot(f))));
	}
	
	public String toString() {
		if(Main.LATEX) {
			if(f instanceof Var)
				return "\\csc(x)";
			return "\\csc\\left(" + f + "\\right)";
		}
		return "csc(" + f + ")";
	}

	public boolean equals(Function other) {
		if(other instanceof Csc)
			return f.equals(((Csc) other).f);
		return false;
	}

}
