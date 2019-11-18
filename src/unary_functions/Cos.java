package unary_functions;

import binary_functions.Product;
import functions.Function;
import functions.Main;
import functions.UnaryFunction;

public class Cos extends UnaryFunction {
	
	public Cos(Function f) {
		super(f);
	}

	public Cos(double f) {
		super(f);
	}

	public double output(double v) {
		return Math.cos(f.output(v));
	}

	public Function derivative() {
		return new Neg(new Product(f.derivative(), new Sin(f)));
	}
	
	public String toString() {
		if(Main.LATEX) {
			if(f instanceof Var)
				return "\\cos(x)";
			return "\\cos\\left(" + f + "\\right)";
		}
		return "cos("+f+")";
	}

	public boolean equals(Function other) {
		if(other instanceof Cos) 
			return f.equals(((Cos) other).f);
		return false;
	}

}
