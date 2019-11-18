package functions;

import binary_functions.Diff;
import binary_functions.Division;
import binary_functions.Exp;
import binary_functions.Product;
import binary_functions.Sum;
import consts.DefConst;
import consts.e;
import unary_functions.Acos;
import unary_functions.Asin;
import unary_functions.Atan;
import unary_functions.Const;
import unary_functions.Cos;
import unary_functions.Cot;
import unary_functions.Csc;
import unary_functions.Ln;
import unary_functions.Neg;
import unary_functions.Sec;
import unary_functions.Sin;
import unary_functions.Tan;

public class Simplifier {

	public static Function simplify(Function f) {
		//		try {
		//			Thread.sleep(50);
		//		} catch (InterruptedException e1) {
		//			e1.printStackTrace();
		//		}
		//		System.out.println(f);
		if(f instanceof Product) {
			Product p = (Product) f;
			Function pf = simplest(p.f), pg = simplest(p.g);
			if(pf instanceof Const) {
				Const c = (Const) pf;
				if(c.getValue() == 0) 
					return new Const(0);
				if(c.getValue() == 1) 
					return pg;
				if(pg instanceof Const) 
					return new Const(((Const) pf).getValue() * ((Const) pg).getValue());
				if(pg instanceof Product) {
					Product pgp = (Product) pg;
					if(pgp.f instanceof Const) {
						double pfValue = ((Const) pf).getValue();
						double pgpf = ((Const) pgp.f).getValue();
						double product = pfValue * pgpf;
						return simplest(new Product(new Const(product), pgp.g));
					}
				}
			}
			if(pg instanceof Const) {
				Const c = (Const) pg;
				if(c.getValue() == 0)
					return new Const(0);
				if(c.getValue() == 1) 
					return pf;
				return simplest(new Product(pg, pf));
			}
			if(pf instanceof Product) {
				Product pfp = (Product) pf;
				if(pfp.f instanceof Const)
					return new Product(pfp.f, simplest(new Product(pg, pfp.g)));
			}
			if(pg instanceof Product) {
				Product pgp = (Product) pg;
				if(pgp.f instanceof Const)
					return new Product(pgp.f, simplest(new Product(pf, pgp.g)));
			}
			if(pf instanceof Division) {
				Division d = (Division) pf;
				return simplest(new Division(new Product(pg, d.f), d.g));
			}
			if(pg instanceof Division) {
				Division d = (Division) pg;
				return simplest(new Division(new Product(pf, d.f), d.g));
			}
			boolean pfneg = pf instanceof Neg, pgneg = pg instanceof Neg;
			if(pfneg && pgneg) {
				Neg nf = (Neg) pf;
				Neg ng = (Neg) pg;
				return simplest(new Product(nf.f, ng.f));
			}
			if(pfneg) {
				Neg nf = (Neg) pf;
				return simplest(new Neg(new Product(nf.f, pg)));
			}
			if(pgneg) {
				Neg ng = (Neg) pg;
				return simplest(new Neg(new Product(pf, ng.f)));
			}
			return new Product(pf, pg);
		}
		if(f instanceof Sum) {
			Sum s = (Sum) f;
			Function sf = simplest(s.f), sg = simplest(s.g);
			if(sf instanceof Const) {
				Const c = (Const) sf;
				if(c.getValue() == 0)
					return sg;
			}
			if(sg instanceof Const) {
				Const c = (Const) sg;
				if(c.getValue() == 0)
					return sf;
			}
			if(sg instanceof Neg) {
				Neg sgn = (Neg) sg;
				return simplest(new Diff(sf, sgn.f));
			}
			return new Sum(sf, sg);
		}
		if(f instanceof Diff) {
			Diff d = (Diff) f;
			Function df = simplest(d.f), dg = simplest(d.g);
			if(df instanceof Const) {
				Const c = (Const) df;
				if(c.getValue() == 0)
					return new Neg(dg);
			}
			if(dg instanceof Const) {
				Const c = (Const) dg;
				if(c.getValue() == 0)
					return df;
			}
			if(df instanceof Neg) {
				Neg nf = (Neg) df;
				return simplest(new Neg(new Sum(nf.f, dg)));
			}
			if(dg instanceof Neg) {
				Neg ng = (Neg) dg;
				return simplest(new Sum(df, ng.f));
			}
			if(dg instanceof Diff) {
				Diff dgi = (Diff) dg;
				return simplest(new Sum(df, new Diff(dgi.g, dgi.f)));
			}
			return new Diff(df, dg);
		}
		if(f instanceof Ln) {
			Ln l = (Ln) f;
			Function lf = simplest(l.f);
			if(lf instanceof Const) {
				Const c = (Const) lf;
				if(c.getValue() == 1)
					return new Const(0);
			}
			if(lf instanceof DefConst) {
				DefConst c = (DefConst) lf;
				if(c instanceof e)
					return new Const(1);
			}
			if(lf instanceof Exp) {
				Exp e = (Exp) lf;
				return new Product(simplest(e.g), simplest(new Ln(e.f)));
			}
			if(lf instanceof Product) {
				Product p = (Product) lf;
				return new Sum(simplest(new Ln(p.f)), simplest(new Ln(p.g)));
			}
			if(lf instanceof Division) {
				Division p = (Division) lf;
				return new Diff(simplest(new Ln(p.f)), simplest(new Ln(p.g)));
			}
			return new Ln(lf);
		}
		if(f instanceof Exp) {
			Exp e = (Exp) f;
			return new Exp(simplest(e.f), simplest(e.g));
		}
		if(f instanceof Division) {
			Division d = (Division) f;
			Function df = simplest(d.f), dg = simplest(d.g);
			if(df.equals(dg)) 
				return new Const(1);
			if(df instanceof Const) {
				Const c = (Const) df;
				if(c.getValue() == 0)
					return new Const(0);
			}
			if(dg instanceof Const) {
				Const c = (Const) dg;
				if(c.getValue() == 1) 
					return df;
			}
			if(df instanceof Division) {
				Division d1 = (Division) df;
				return simplest(new Division(d1.f, new Product(d1.g, dg)));
			}
			if(dg instanceof Division) {
				Division d1 = (Division) dg;
				return simplest(new Division(new Product(d1.g, df), d1.f));
			}
			if(df instanceof Sin && dg instanceof Cos)
				if((((Sin) df).f).equals(((Cos) dg).f))
					return simplest(new Tan(((Sin) df).f));
			if(df instanceof Cos && dg instanceof Sin)
				if((((Cos) df).f).equals(((Sin) dg).f))
					return simplest(new Cot(((Cos) df).f));
			if(dg instanceof Cos)
				return simplest(new Product(new Sec(((Cos) dg).f), df));
			if(dg instanceof Sin)
				return simplest(new Product(new Csc(((Sin) dg).f), df));
			if(dg instanceof Tan)
				return simplest(new Product(new Cot(((Tan) dg).f), df));
			if(dg instanceof Cot)
				return simplest(new Product(new Tan(((Cot) dg).f), df));
			return new Division(df, dg);
		}
		if(f instanceof Sin) {
			Sin s = (Sin) f;
			return new Sin(simplest(s.f));
		}
		else if(f instanceof Cos) {
			Cos s = (Cos) f;
			return new Cos(simplest(s.f));
		}
		else if(f instanceof Tan) {
			Tan s = (Tan) f;
			return new Tan(simplest(s.f));
		}
		else if(f instanceof Sec) {
			Sec s = (Sec) f;
			return new Sec(simplest(s.f));
		}
		else if(f instanceof Csc) {
			Csc s = (Csc) f;
			return new Csc(simplest(s.f));
		}
		else if(f instanceof Cot) {
			Cot s = (Cot) f;
			return new Cot(simplest(s.f));
		}
		else if(f instanceof Asin) {
			Asin s = (Asin) f;
			return new Asin(simplest(s.f));
		}
		else if(f instanceof Acos) {
			Acos s = (Acos) f;
			return new Acos(simplest(s.f));
		}
		else if(f instanceof Atan) {
			Atan s = (Atan) f;
			return new Atan(simplest(s.f));
		}
		if(f instanceof Neg) {
			Neg n = (Neg) f;
			Function nf = simplest(n.f);
			n.f = nf;
			if(nf instanceof Neg) {
				return simplest(((Neg) nf).f);
			}
			if(nf instanceof Diff) {
				Diff df = (Diff) nf;
				return simplest(new Diff(df.g, df.f));
			}
			return new Neg(nf);
		}
		return f;
	}

	public static Function simplest(Function f) {
		Function sto = f, lsto = null;
		while(!(sto.equals(lsto))) {
			Function temp = sto;
			lsto = temp;
			sto = simplify(sto);
		}
		return sto;
	}

	//	public static double getConstTotal(Function f) {
	//		double total = 1;
	//		BinaryFunction p = (BinaryFunction) f;
	//		Function pf = simplest(p.f), pg = simplest(p.g);
	//		if(pf instanceof Const) {
	//			Const c = (Const) pf;
	//			total *= c.value;
	//			p.f = new Const(1);
	//		}
	//		if(pg instanceof Const) {
	//			Const c = (Const) pg;
	//			total *= c.value;
	//			p.g = new Const(1);
	//		}
	//		if(pf instanceof Product) {
	//			total *= getConstTotal(f);
	//		}
	//		else if(pf instanceof Division) {
	//			total *= getConstTotal(f);
	//		}
	//		if(pg instanceof Product) {
	//			total *= getConstTotal(pg);
	//		}
	//		else if(pg instanceof Division) {
	//			total /= getConstTotal(pg);
	//		}
	//		return total;
	//	}

}
