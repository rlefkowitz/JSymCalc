package functions;

import unary_functions.Const;

public abstract class UnaryFunction implements Function {
	
	public Function f;
	
	public UnaryFunction(Function f) {
		this.f = f;
	}
	
	public UnaryFunction(double f) {
		this.f = new Const(f);
	}
	
	public abstract double output(double v);

	public abstract Function derivative();
	

}
