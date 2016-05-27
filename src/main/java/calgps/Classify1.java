package calgps;

public class Classify1 {
	float P1, P2, P3;
	float P11, P22, P33;
	float UC, Cr, Co;
	Gauss g1=new Gauss();
	Posteror p=new Posteror();
	public String Calculate(Obj o1,Posteror p,Gauss g1 ) {
		
		P1 = (float) g1.Gaus(o1.getNumber(),(float) p.E11, (float)p.V11);
		P2 = (float) g1.Gaus(o1.getNumber(),(float) p.E22,(float) p.V22);
		P3 = (float) g1.Gaus(o1.getNumber(),(float) p.E33, (float)p.V33);
		P11 = (float) g1.Gaus(o1.getVol(),(float) p.E1,(float) p.V1);
		P22 = (float) g1.Gaus(o1.getVol(),(float) p.E2, (float)p.V2);
		P33 = (float) g1.Gaus(o1.getVol(),(float) p.E3,(float) p.V3);
		UC = (float) (P1 * P11 * (0.33));
		Cr = (float) (P2 * P22 * (0.33));
		Co = (float) (P3 * P33 * (0.33));
		
//		System.out.println("P(uncrowded|number)=" + P1);
//		System.out.println("P(crowded|number)=" + P2);
//		System.out.println("P(congested|number)=" + P3);
//		System.out.println("P(uncrowded|vol)=" + P11);
//		System.out.println("P(ucrowded|vol)=" + P22);
//		System.out.println("P(congested|vol)=" + P33);
//		System.out.println("P(uncrowded|X)=" + UC);
//		System.out.println("P(crowded|X)=" + Cr);
//		System.out.println("P(congested|X)=" + Co);

		if (Cr > UC && Cr > Co) {
			o1.setState("crowed");
		}
		if (UC > Co && UC > Cr)

		{
			o1.setState("uncrowded");
		}
		if (Co > UC && Co > Cr) {
			o1.setState("congested");
		}

		return "class:"+o1.getState();
	}
public String Calculate(Obj o1) {
		
		P1 = (float) g1.Gaus(o1.getNumber(),(float) p.E11, (float)p.V11);
		P2 = (float) g1.Gaus(o1.getNumber(),(float) p.E22,(float) p.V22);
		P3 = (float) g1.Gaus(o1.getNumber(),(float) p.E33, (float)p.V33);
		P11 = (float) g1.Gaus(o1.getVol(),(float) p.E1,(float) p.V1);
		P22 = (float) g1.Gaus(o1.getVol(),(float) p.E2, (float)p.V2);
		P33 = (float) g1.Gaus(o1.getVol(),(float) p.E3,(float) p.V3);
		UC = (float) (P1 * P11 * (0.33));
		Cr = (float) (P2 * P22 * (0.33));
		Co = (float) (P3 * P33 * (0.33));
//		System.out.println("P(uncrowded|number)=" + P1);
//		System.out.println("P(crowded|number)=" + P2);
//		System.out.println("P(congested|number)=" + P3);
//		System.out.println("P(uncrowded|vol)=" + P11);
//		System.out.println("P(ucrowded|vol)=" + P22);
//		System.out.println("P(congested|vol)=" + P33);
//		System.out.println("P(uncrowded|X)=" + UC);
//		System.out.println("P(crowded|X)=" + Cr);
//		System.out.println("P(congested|X)=" + Co);

		if (Cr > UC && Cr > Co) {
			o1.setState("crowed");
		}
		if (UC > Co && UC > Cr)

		{
			o1.setState("uncrowded");
		}
		if (Co > UC && Co > Cr) {
			o1.setState("congested");
		}

		return "class:"+o1.getState();
}
}
