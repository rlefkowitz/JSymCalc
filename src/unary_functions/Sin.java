package unary_functions;

import binary_functions.Product;
import functions.Function;
import functions.Main;
import functions.UnaryFunction;

public class Sin extends UnaryFunction {

	public Sin(Function f) {
		super(f);
	}
	
	public Sin(double f) {
		super(f);
	}

	public double output(double v) {
		return Math.sin(f.output(v));
	}

	public Function derivative() {
		return new Product(f.derivative(), new Cos(f));
	}
	
	public String toString() {
		if(Main.LATEX) {
			if(f instanceof Var)
				return "\\sin(x)";
			return "\\sin\\left(" + f + "\\right)";
		}
		return "sin("+f+")";
	}
	
	public boolean equals(Function other) {
		if(other instanceof Sin) 
			return f.equals(((Sin) other).f);
		return false;
	}

}
