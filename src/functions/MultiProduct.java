package functions;

import java.util.ArrayList;

public class MultiProduct extends MultiFunction {

	public MultiProduct(ArrayList<Function> functions) {
		super(functions);
	}

	public double output(double v) {
		double ret = 1;
		for(Function f : farr)
			ret *= f.output(v);
		return ret;
	}

	public Function derivative() {
		return null;
	}

	public boolean equals(Function other) {
		if(other instanceof MultiProduct) {
			MultiProduct m = (MultiProduct) other;
			@SuppressWarnings("unchecked")
			ArrayList<Function> ofa = (ArrayList<Function>) m.farr.clone();
			if(m.farr.size() != farr.size())
				return false;
			for(Function f : farr) {
				for(int i = 0; i < ofa.size(); i++)
					if(ofa.get(i).equals(f)) {
						ofa.remove(i);
						break;
					}
			}
			return ofa.size() == 0;
		}
		return false;
	}

}
