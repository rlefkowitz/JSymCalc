package consts;

import functions.Function;

public class e extends DefConst {
	
	public e() {
		ch = "e";
		value = Math.E;
	}

	public boolean equals(Function other) {
		return (other instanceof e);
	}

}
