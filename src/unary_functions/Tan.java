package unary_functions;

import binary_functions.Exp;
import binary_functions.Product;
import functions.Function;
import functions.Main;
import functions.UnaryFunction;

public class Tan extends UnaryFunction {

	public Tan(Function f) {
		super(f);
	}

	public Tan(double f) {
		super(f);
	}

	public double output(double v) {
		return Math.tan(f.output(v));
	}

	public Function derivative() {
		return new Product(f.derivative(), new Exp(new Sec(f), 2));
	}
	
	public String toString() {
		if(Main.LATEX) {
			if(f instanceof Var)
				return "\\tan(x)";
			return "\\tan\\left(" + f + "\\right)";
		}
		return "tan(" + f + ")";
	}

	public boolean equals(Function other) {
		if(other instanceof Tan)
			return f.equals(((Tan) other).f);
		return false;
	}

}
