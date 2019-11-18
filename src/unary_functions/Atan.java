package unary_functions;

import binary_functions.Division;
import binary_functions.Exp;
import binary_functions.Sum;
import functions.Function;
import functions.UnaryFunction;

public class Atan extends UnaryFunction {

	public Atan(Function f) {
		super(f);
	}

	public Atan(double f) {
		super(f);
	}

	public double output(double v) {
		return Math.atan(f.output(v));
	}

	public Function derivative() {
		return new Division(f.derivative(), new Sum(1, new Exp(f, 2)));
	}
	
	public String toString() {
		return "arctan(" + f + ")";
	}

	public boolean equals(Function other) {
		if(other instanceof Atan) 
			return f.equals(((Atan) other).f);
		return false;
	}

}
