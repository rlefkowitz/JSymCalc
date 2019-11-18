package unary_functions;

import functions.Function;

public class Const implements Function {
	
	private double value;

	/**
	 * Constructor: Creates an instance of <code>Const</code> for which 
	 * <code>value</code> is set to <code>v</code>.
	 * 
	 * @param v the desired value of the new instance.
	 */
	public Const(double v) {
		this.value = v;
	}
	
	
	/**
	 * Given a function f(x) = a, where a is a constant, and a scalar value v, 
	 * f(v) = a.
	 * 
	 * @param v A given scalar value.
	 * @return f(v).
	 */
	public double output(double v) {
		return value;
	}
	
	
	/**
	 * Getter function for <code>value</code>.
	 * 
	 * @return the value of the constant
	 */
	public double getValue() {
		return value;
	}
	
	
	/**
	 * Setter function for <code>value</code>.
	 * 
	 * @param v the new value for <code>value</code>.
	 */
	public void setValue(double v) {
		this.value = v;
	}

	
	/**
	 * For any function f(x) = a, where a is constant, df/dx = 0.
	 * @return df/dx
	 */
	public Function derivative() {
		return new Const(0);
	}
	
	
	/**
	 * If value is an integer, return <code>String</code> representation for 
	 * value cast to an integer. Otherwise, return String representation for 
	 * value.
	 * 
	 * @return A <code>String</code> representation of the given 
	 * <code>Const</code>.
	 */
	public String toString() {
		if(value == ((int) value))
			return Integer.toString((int) value);
		return Double.toString(value);
	}

	
	/**
	 * Determines whether <code>other</code> is of type <code>Const</code> and 
	 * is equivalent in value to the current function.
	 * 
	 * @param other A <code>Function</code> to be compared to the current 
	 * <code>Function</code>.
	 * @return A <code>boolean</code> for this equality.
	 */
	public boolean equals(Function other) {
		if(other instanceof Const) 
			return value == ((Const) other).value;
		return false;
	}

}
