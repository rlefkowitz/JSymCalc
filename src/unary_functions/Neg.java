package unary_functions;

import functions.Function;
import functions.Main;
import functions.UnaryFunction;

public class Neg extends UnaryFunction {

	public Neg(Function f) {
		super(f);
	}

	public Neg(double f) {
		super(f);
	}

	public double output(double v) {
		return -v;
	}

	public Function derivative() {
		return new Neg(f.derivative());
	}
	
	public String toString() {
		if(Main.DEBUG)
			return "neg(" + f + ")";
		return "-" + f;
	}

	public boolean equals(Function other) {
		if(other instanceof Neg)
			return f.equals(((Neg) other).f);
		return false;
	}

}
