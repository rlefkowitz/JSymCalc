package parser;

import binary_functions.Diff;
import binary_functions.Division;
import binary_functions.Exp;
import binary_functions.Product;
import binary_functions.Sum;
import functions.*;
import unary_functions.Acos;
import unary_functions.Asin;
import unary_functions.Atan;
import unary_functions.Const;
import unary_functions.Cos;
import unary_functions.Cot;
import unary_functions.Csc;
import unary_functions.Ln;
import unary_functions.Sec;
import unary_functions.Sin;
import unary_functions.Tan;
import unary_functions.Var;

public class ParseMain {

	static int[] keyChars = {42, 47, 94};

	public static Function parseAll(String s) {
		return parseRec(s.replaceAll("\\s+", ""));
	}

	public static Function parseRec(String s) {

		//		System.out.println(s);
		Function AS = splitByAddSub(s);
		if(AS != null)
			return AS;
		Function MD = splitByMultDiv(s);
		if(MD != null)
			return MD;
		Function EXP = Exp(s);
		if(EXP != null)
			return EXP;
		if(s.equals("x")) 
			return new Var();
		if(s.equals("\u03c0") || s.equals("pi"))
			return Main.pi;
		if(s.equals("e"))
			return Main.e;
		try {
			double i = Double.parseDouble(s);
			return new Const(i);
		}
		catch(NumberFormatException e) { }
		Function U = unary(s);
		if(U != null) 
			return U;
//		System.out.println(s);
		return parseRec(toEndParentheses(s));
	}

	public static Function splitByAddSub(String s) {
		int len = s.length(), idx = 0, c = 0, v;
		for(; idx < len; idx++) {
			v = s.charAt(idx);
			if(v == 40) c++;
			else if(v == 41) c--;
			else if(c == 0) {
				if(v == 43) 
					return new Sum(parseRec(s.substring(0, idx)), parseRec(s.substring(idx + 1)));
				else if(v == 45) 
					return new Diff(parseRec(s.substring(0, idx)), parseRec(s.substring(idx + 1)));
			}
		}
		return null;
	}

	public static Function splitByMultDiv(String s) {
		int len = s.length(), idx = 0, c = 0, v;
		for(; idx < len; idx++) {
			v = s.charAt(idx);
			//			System.out.println(c + ", " + v + "(" + s.substring(idx, idx + 1) + ")");
			if(v == 40) c++;
			else if(v == 41) c--;
			if(c == 0) {
				if(idx + 1 < len && !isKeyChar(s.charAt(idx + 1))) {
					if(v == 41 && idx > 0) {
						//					String prec = s.substring(0, idx + 1);
						//					if(unary(s.substring(0, idx + 1)) != null) {
						if(idx + 1 != len && s.charAt(idx + 1) != 42)
							return new Product(parseRec(s.substring(0, idx + 1)), parseRec(s.substring(idx + 1)));
						//					} 
					}
					if(idx + 2 < len) {
						if(isStandalone(s.substring(0, idx + 1)) && !isStandalone(s.substring(0, idx + 2))) {
							return new Product(parseRec(s.substring(0, idx + 1)), parseRec(s.substring(idx + 1)));
						}
						if(!isStandalone(s.substring(idx)) && isStandalone(s.substring(idx + 1))) {
							return new Product(parseRec(s.substring(0, idx + 1)), parseRec(s.substring(idx + 1)));
						}
					}
				}
				if(v == 42) 
					return new Product(parseRec(s.substring(0, idx)), parseRec(s.substring(idx + 1)));
				else if(v == 47) 
					return new Division(parseRec(s.substring(0, idx)), parseRec(s.substring(idx + 1)));
			}
		}
		return null;
	}

	public static Function Exp(String s) {
//		System.out.println(s);
		int len = s.length(), idx = 0, c = 0, v;
		for(; idx < len; idx++) {
			v = s.charAt(idx);
			if(v == 40) c++;
			else if(v == 41) c--;
			else if(c == 0) {
				if(v == 94) {
					//					String base = s.substring(0, idx);
					//					String expo = s.substring(idx + 1);
					//					System.out.println(base);
					//					System.out.println(expo);
					//					Function fbase = parseRec(base);
					//					Function fexpo = parseRec(expo);
					//					boolean sbase = fbase instanceof BinaryFunction && s.charAt(idx - 1) != 41;
					//					boolean sexpo = fexpo instanceof BinaryFunction && s.charAt(idx + 1) != 40;
					//					if(!(sbase || sexpo)) {
//					System.out.println("exp of " + s);
//					System.out.println(parseRec(s.substring(0, idx)));
//					System.out.println(parseRec(s.substring(idx + 1)));
					return new Exp(parseRec(s.substring(0, idx)), parseRec(s.substring(idx + 1)));
					//					} else if(sbase){
					//						Function res = parseRec(s.substring(0, idx));
					//						if(res instanceof BinaryFunction) {
					//							Function lastFuncForExp = res;
					//							Function funcForExp = ((BinaryFunction) res).g;
					//							while(funcForExp instanceof BinaryFunction) {
					//								lastFuncForExp = funcForExp;
					//								funcForExp = ((BinaryFunction) funcForExp).g;
					//							}
					//							((BinaryFunction) lastFuncForExp).g = new Exp(funcForExp, parseRec(s.substring(idx + 1)));
					//						}
					//						return new Exp(res, fexpo);
					//					} else if(sexpo){
					//						Function res = parseRec(s.substring(idx + 1));
					//						if(res instanceof BinaryFunction) {
					//							Function lastFuncForExp = res;
					//							Function funcForExp = ((BinaryFunction) res).f;
					//							while(funcForExp instanceof BinaryFunction) {
					//								lastFuncForExp = funcForExp;
					//								funcForExp = ((BinaryFunction) funcForExp).f;
					//							}
					//							((BinaryFunction) lastFuncForExp).f = new Exp(parseRec(s.substring(idx + 1)), funcForExp);
					//						}
					//						return new Exp(fbase, res);
					//					} else {
					//						Function res0 = parseRec(s.substring(0, idx));
					//						Function res1 = parseRec(s.substring(idx + 1));
					//						Function lastFuncForExp0 = res0;
					//						Function funcForExp0 = ((BinaryFunction) res0).g;
					//						while(funcForExp0 instanceof BinaryFunction) {
					//							lastFuncForExp0 = funcForExp0;
					//							funcForExp0 = ((BinaryFunction) funcForExp0).g;
					//						}
					//						Function lastFuncForExp1 = res1;
					//						Function funcForExp1 = ((BinaryFunction) res1).f;
					//						while(funcForExp1 instanceof BinaryFunction) {
					//							lastFuncForExp1 = funcForExp1;
					//							funcForExp1 = ((BinaryFunction) funcForExp1).f;
					//						}
					//						((BinaryFunction) lastFuncForExp1).f = new Exp(funcForExp0, funcForExp1);
					//						((BinaryFunction) lastFuncForExp0).g = res1;
					//						return res0;
					//					}
				}
			}
		}
		return null;
	}

	public static Function unary(String s) {
		int iop = s.indexOf("(");
		if(iop < 2) return null;
		Function inner = parseRec(s.substring(iop));

		switch(s.substring(0, iop)) {
		case "ln":
			return new Ln(inner);
		case "sin":
			return new Sin(inner);
		case "cos":
			return new Cos(inner);
		case "tan":
			return new Tan(inner);
		case "csc":
			return new Csc(inner);
		case "sec":
			return new Sec(inner);
		case "cot":
			return new Cot(inner);
		case "asin":
			return new Asin(inner);
		case "arcsin":
			return new Asin(inner);
		case "acos":
			return new Acos(inner);
		case "arccos":
			return new Acos(inner);
		case "atan":
			return new Atan(inner);
		case "arctan":
			return new Atan(inner);
		case "sqrt":
			return new Exp(inner, 0.5);
		default:
			return null;
		}
	}

	public static boolean isStandalone(String s) {
		try {
			Double.parseDouble(s);
			return true;
		} catch(NumberFormatException e){  
			if(s.equals("\u03c0") || s.equals("pi") || s.equals("e") || s.equals("x")) {
				return true;
			}  
			return false;
		}  
	}

	public static String toEndParentheses(String s) {
		int len = s.length(), idx = 0, c = 0, v;
		for(; c >= 0 && idx < len; idx++) {
			v = s.charAt(idx);
			if(v == 40) c++;
			else if(v == 41) c--;
		}
		String wo = s.substring(1, idx - 1);
		return wo;
	}

	public static boolean incompleteParentheses(String s) {
		int idx = 0, c = 0, v;
		for(; idx < s.length(); idx++) {
			v = s.charAt(idx);
			if(v == 40) c++;
			else if(v == 41) c--;
		}
		return c == 0;
	}

	public static boolean isKeyChar(int i) {
		for(int c : keyChars)
			if(i == c)
				return true;
		return false;
	}

}
