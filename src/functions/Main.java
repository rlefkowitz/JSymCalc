package functions;

import java.util.Scanner;

import binary_functions.Diff;
import binary_functions.Division;
import binary_functions.Exp;
import binary_functions.Product;
import binary_functions.Sum;
import consts.DefConst;
import parser.ParseMain;
import unary_functions.Acos;
import unary_functions.Asin;
import unary_functions.Atan;
import unary_functions.Cos;
import unary_functions.Cot;
import unary_functions.Csc;
import unary_functions.Ln;
import unary_functions.Sec;
import unary_functions.Sin;
import unary_functions.Tan;
import unary_functions.Var;

public class Main {

	public static boolean DEBUG = false, LATEX = false;

	public static final DefConst e = new consts.e(), pi = new consts.pi();
	public static final Var x = new Var();

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner myinput = new Scanner(System.in);
		System.out.println("Find the derivative of: ");
		String given = myinput.nextLine();
		try {
			Function f = ParseMain.parseAll(given);//"x*ln(x^(sin(ln(x))*cos(x)))");
//			System.out.println("f(x) = " + f);
	
			f = Simplifier.simplest(f);
			Function df = f.derivative();
			Function dfs = Simplifier.simplest(df);
			while(!dfs.equals(df)) {
				df = dfs;
				dfs = Simplifier.simplest(df);
			}
			System.out.println(" f(x) = " + f);
			System.out.println("df/dx = " + df + "\n");
//			LATEX = true;
//			System.out.println("\\frac{d}{dx}\\left(" + f + "\\right) = " + df);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static Function exp(Function f, Function g) { return new Exp(f, g); }
	public static Function exp(double f, Function g) { return new Exp(f, g); }
	public static Function exp(Function f, double g) { return new Exp(f, g); }
	public static Function exp(double f, double g) { return new Exp(f, g); }
	public static Function sum(Function f, Function g) { return new Sum(f, g); }
	public static Function sum(double f, Function g) { return new Sum(f, g); }
	public static Function sum(Function f, double g) { return new Sum(f, g); }
	public static Function sum(double f, double g) { return new Sum(f, g); }
	public static Function product(Function f, Function g) { return new Product(f, g); }
	public static Function product(double f, Function g) { return new Product(f, g); }
	public static Function product(Function f, double g) { return new Product(f, g); }
	public static Function product(double f, double g) { return new Product(f, g); }
	public static Function div(Function f, Function g) { return new Division(f, g); }
	public static Function div(double f, Function g) { return new Division(f, g); }
	public static Function div(Function f, double g) { return new Division(f, g); }
	public static Function div(double f, double g) { return new Division(f, g); }
	public static Function diff(Function f, Function g) { return new Diff(f, g); }
	public static Function diff(double f, Function g) { return new Diff(f, g); }
	public static Function diff(Function f, double g) { return new Diff(f, g); }
	public static Function diff(double f, double g) { return new Diff(f, g); }
	public static Function ln(Function f) { return new Ln(f); }
	public static Function ln(double f) { return new Ln(f); }
	public static Function sin(Function f) { return new Sin(f); }
	public static Function sin(double f) { return new Sin(f); }
	public static Function cos(Function f) { return new Cos(f); }
	public static Function cos(double f) { return new Cos(f); }
	public static Function tan(Function f) { return new Tan(f); }
	public static Function tan(double f) { return new Tan(f); }
	public static Function sec(Function f) { return new Sec(f); }
	public static Function sec(double f) { return new Sec(f); }
	public static Function csc(Function f) { return new Csc(f); }
	public static Function csc(double f) { return new Csc(f); }
	public static Function cot(Function f) { return new Cot(f); }
	public static Function cot(double f) { return new Cot(f); }
	public static Function asin(Function f) { return new Asin(f); }
	public static Function asin(double f) { return new Asin(f); }
	public static Function arcsin(Function f) { return new Asin(f); }
	public static Function arcsin(double f) { return new Asin(f); }
	public static Function acos(Function f) { return new Acos(f); }
	public static Function acos(double f) { return new Acos(f); }
	public static Function arccos(Function f) { return new Acos(f); }
	public static Function arccos(double f) { return new Acos(f); }
	public static Function atan(Function f) { return new Atan(f); }
	public static Function atan(double f) { return new Atan(f); }
	public static Function arctan(Function f) { return new Atan(f); }
	public static Function arctan(double f) { return new Atan(f); }
}
