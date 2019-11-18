package functions;

import unary_functions.Const;

public abstract class BinaryFunction implements Function {
	
	public Function f, g;
	
	public BinaryFunction(Function f, Function g) {
		this.f = f;
		this.g = g;
	}
	
	public BinaryFunction(double f, Function g) {
		this.f = new Const(f);
		this.g = g;
	}
	
	public BinaryFunction(Function f, double g) {
		this.f = f;
		this.g = new Const(g);
	}
	
	public BinaryFunction(double f, double g) {
		this.f = new Const(f);
		this.g = new Const(g);
	}

}
