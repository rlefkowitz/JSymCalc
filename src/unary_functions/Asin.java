package unary_functions;

import binary_functions.Diff;
import binary_functions.Division;
import binary_functions.Exp;
import functions.Function;
import functions.UnaryFunction;

public class Asin extends UnaryFunction {

	public Asin(Function f) {
		super(f);
	}

	public Asin(double f) {
		super(f);
	}

	public double output(double v) {
		return Math.asin(f.output(v));
	}

	public Function derivative() {
		return new Division(f.derivative(), new Exp(new Diff(1, new Exp(f, 2)), 0.5));
	}
	
	public String toString() {
		return "arcsin(" + f + ")";
	}

	public boolean equals(Function other) {
		if(other instanceof Asin) 
			return f.equals(((Asin) other).f);
		return false;
	}
	
	

}
