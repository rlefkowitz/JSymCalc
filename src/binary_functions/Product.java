package binary_functions;

import functions.BinaryFunction;
import functions.Function;
import functions.Main;
import unary_functions.Const;

public class Product extends BinaryFunction {

	
	/**
	 * Constructor: Creates an instance of <code>Product</code> using the super
	 * constructor.
	 * 
	 * @param f A <code>Function</code> f.
	 * @param g A <code>Function</code> g.
	 */
	public Product(Function f, Function g) {
		super(f, g);
	}


	/**
	 * Constructor: Creates an instance of <code>Product</code> using the super
	 * constructor.
	 * 
	 * @param f A <code>double</code> f.
	 * @param g A <code>Function</code> g.
	 */
	public Product(double f, Function g) {
		super(f, g);
	}

	
	/**
	 * Constructor: Creates an instance of <code>Product</code> using the super
	 * constructor.
	 * 
	 * @param f A <code>Function</code> f.
	 * @param g A <code>double</code> g.
	 */
	public Product(Function f, double g) {
		super(f, g);
	}


	/**
	 * Constructor: Creates an instance of <code>Product</code> using the super
	 * constructor.
	 * 
	 * @param f A <code>double</code> f.
	 * @param g A <code>double</code> g.
	 */
	public Product(double f, double g) {
		super(f, g);
	}

	
	/**
	 * Returns the output of h(x) = f(x)*g(x) for x = v.
	 * 
	 * @param v A given scalar value.
	 * @return The product of <code>f(v)</code> and <code>g(v)</code>.
	 */
	public double output(double v) {
		return f.output(v) * g.output(v);
	}


	/**
	 * Returns f(x)*g(x).
	 * 
	 * @param v A given scalar value.
	 * @return The product of <code>f(v)</code> and <code>g(v)</code>.
	 */
	public Function derivative() {
		return new Sum(new Product(f.derivative(), g), new Product(f, g.derivative()));
	}
	
	public String toString() {
		if(Main.DEBUG)
			return "product(" + f + ", " + g + ")";
		String f0 = "" + f, f1 = "" + g;
		if(f instanceof BinaryFunction && !(f instanceof Product)) {
			f0 = "(" + f + ")";
			if(Main.LATEX)
				f0 = "\\left(" + f + "\\right)";
		}
		if(g instanceof BinaryFunction && !(g instanceof Product)) {
			f1 = "(" + g + ")";
			if(Main.LATEX)
				f1 = "\\left(" + g + "\\right)";
		}
		if(f instanceof Const && g instanceof Const)
			return ""  + (((Const) f).getValue() * ((Const) g).getValue());
		return f0 + "" + f1;
	}

	public boolean equals(Function other) {
		if(other instanceof Product) 
			return (f.equals(((Product) other).f) && g.equals(((Product) other).g));
		return false;
	}

}
