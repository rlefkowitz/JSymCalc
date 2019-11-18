package unary_functions;

import binary_functions.Division;
import functions.Function;
import functions.Main;
import functions.UnaryFunction;

public class Ln extends UnaryFunction {

	public Ln(Function f) {
		super(f);
	}
	
	public Ln(double f) {
		super(f);
	}

	public double output(double v) {
		return Math.log(f.output(v));
	}

	public Function derivative() {
		return new Division(f.derivative(), f);
	}
	
	public String toString() {
		if(Main.LATEX) {
			if(f instanceof Var)
				return "\\ln(x)";
			return "\\ln\\left(" + f + "\\right)";
		}
		return "ln(" + f + ")";
	}

	public boolean equals(Function other) {
		if(other instanceof Ln) 
			return f.equals(((Ln) other).f);
		return false;
	}

}
