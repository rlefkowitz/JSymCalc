package consts;

import functions.Function;
import unary_functions.Const;

public abstract class DefConst implements Function {
	
	public double value;
	public String ch;

	public double output(double v) {
		return value;
	}

	@Override
	public Function derivative() {
		return new Const(0);
	}
	
	public String toString() {
		return ch;
	}

}
