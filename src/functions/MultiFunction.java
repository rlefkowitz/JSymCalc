package functions;

import java.util.ArrayList;

public abstract class MultiFunction implements Function {
	
	public ArrayList<Function> farr;
	
	public MultiFunction(ArrayList<Function> functions) {
		this.farr = functions;
	}

}
