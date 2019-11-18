package unary_functions;

import binary_functions.Diff;
import binary_functions.Division;
import binary_functions.Exp;
import functions.Function;
import functions.UnaryFunction;

public class Acos extends UnaryFunction {

	public Acos(Function f) {
		super(f);
	}

	public Acos(double f) {
		super(f);
	}

	public double output(double v) {
		return Math.acos(f.output(v));
	}

	public Function derivative() {
		return new Neg(new Division(f.derivative(), new Exp(new Diff(1, new Exp(f, 2)), 0.5)));
	}
	
	public String toString() {
		return "arccos(" + f + ")";
	}

	public boolean equals(Function other) {
		if(other instanceof Acos) 
			return f.equals(((Acos) other).f);
		return false;
	}
	
	

}
