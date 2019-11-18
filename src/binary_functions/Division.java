package binary_functions;

import functions.BinaryFunction;
import functions.Function;
import functions.Main;
import unary_functions.Const;

public class Division extends BinaryFunction {

	public Division(Function f, Function g) {
		super(f, g);
	}

	public Division(double f, Function g) {
		super(f, g);
	}

	public Division(Function f, double g) {
		super(f, g);
	}

	public Division(double f, double g) {
		super(f, g);
	}

	public double output(double v) {
		return f.output(v) / g.output(v);
	}

	public Function derivative() {
		return new Division(new Diff(new Product(f.derivative(), g), new Product(f, g.derivative())), new Product(g, g));
	}
	
	public String toString() {
		if(Main.LATEX) 
			return "\\frac{" + f + "}{" + g + "}";
		if(Main.DEBUG)
			return "div(" + f + ", " + g + ")";
		String f0 = "" + f, f1 = "" + g;
		if(f instanceof BinaryFunction && !(f instanceof Product))
			f0 = "(" + f + ")";
		if(g instanceof BinaryFunction)
			f1 = "(" + g + ")";
		if(f instanceof Const && g instanceof Const)
			return ""  + (((Const) f).getValue() / ((Const) g).getValue());
		return f0 + " / " + f1;
	}

	public boolean equals(Function other) {
		if(other instanceof Division) 
			return (f.equals(((Division) other).f) && g.equals(((Division) other).g));
		return false;
	}

}
