package consts;

import functions.Function;
import functions.Main;

public class pi extends DefConst {
	
	public pi() {
		ch = "\u03c0";
		value = Math.PI;
	}

	public boolean equals(Function other) {
		return (other instanceof pi);
	}
	
	@Override
	public String toString() {
		return Main.LATEX ? "\\pi" : ch;
	}

}
