package unary_functions;

import binary_functions.Exp;
import binary_functions.Product;
import functions.Function;
import functions.Main;
import functions.UnaryFunction;

public class Cot extends UnaryFunction {

	public Cot(Function f) {
		super(f);
	}

	public Cot(double f) {
		super(f);
	}

	public double output(double v) {
		return 1. / Math.tan(f.output(v));
	}

	public Function derivative() {
		return new Neg(new Product(f.derivative(), new Exp(new Csc(f), 2)));
	}
	
	public String toString() {
		if(Main.LATEX) {
			if(f instanceof Var)
				return "\\cot(x)";
			return "\\cot\\left(" + f + "\\right)";
		}
		return "cot(" + f + ")";
	}

	public boolean equals(Function other) {
		if(other instanceof Cot)
			return f.equals(((Cot) other).f);
		return false;
	}

}
