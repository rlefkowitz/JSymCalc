package binary_functions;

import functions.BinaryFunction;
import functions.Function;
import functions.Main;
import unary_functions.Const;
import unary_functions.Ln;

public class Exp extends BinaryFunction {

	public Exp(Function f, Function g) {
		super(f, g);
	}

	public Exp(double f, Function g) {
		super(f, g);
	}

	public Exp(Function f, double g) {
		super(f, g);
	}

	public Exp(double f, double g) {
		super(f, g);
	}

	@Override
	public double output(double v) {
		return Math.pow(f.output(v), g.output(v));
	}

	@Override
	public Function derivative() {
		return new Product(new Exp(f, g), (new Product(g, new Ln(f))).derivative());
	}

	public String toString() {
		if(Main.LATEX) {
			if(f instanceof BinaryFunction)
				return "\\left(" + f + "\\right)^{" + g + "}";
			return f + "^{" + g + "}";
		}
		if(Main.DEBUG)
			return "exp(" + f + ", " + g + ")";
		if(g instanceof Const) {
			Const c = (Const) g;
			if(c.getValue() == 0.5) 
				return "sqrt(" + f + ")";
		}
		String base = "" + f, exponent = "" + g;
		if(f instanceof BinaryFunction)
			base = "(" + f + ")";
		if(g instanceof BinaryFunction)
			exponent = "(" + g + ")";
		return base + "^" + exponent;
	}

	public boolean equals(Function other) {
		if(other instanceof Exp) 
			return (f.equals(((Exp) other).f) && g.equals(((Exp) other).g));
		return false;
	}

}
