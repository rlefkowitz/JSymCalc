package unary_functions;

import functions.Function;

public class Var implements Function {

	public double output(double v) {
		return v;
	}

	public Function derivative() {
		return new Const(1);
	}
	
	public String toString() {
		return "x";
	}

	public boolean equals(Function other) {
		return (other instanceof Var);
	}

}
