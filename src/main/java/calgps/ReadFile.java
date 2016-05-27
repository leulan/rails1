package calgps;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ReadFile {
		static int cout;
		double ENumberU=.3f, EVolU=.3f;
		double ENumberCr, EVolCr;
		double ENumberCo, EVolCo;
		double varNumberU=.3f, varVolU=.3f;
		double varNumberCr, varVolCr;
		double varNumberCo, varVolCo;
		int coutU = 0, coutCr = 0, coutCo = 0;
	
		public void readFile(String t) {
			FileInputStream fis = null;
			try {
				fis = new FileInputStream(t);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Scanner scanner = new Scanner(fis);
			int cout1 = 0;
	
			while (scanner.hasNextLine()) {
	
				String data = scanner.nextLine();
				
	
				String[] value = data.split(",");
				if (value[3].equalsIgnoreCase("uncrowded")) {
					ENumberU += Double.parseDouble(value[1]);
					EVolU += Double.parseDouble(value[2]);
					varNumberU += Double.parseDouble(value[1])
							* Double.parseDouble(value[1]);
					varVolU += Double.parseDouble(value[2])
							* Double.parseDouble(value[2]);
	//				System.out.print("UNCROWDED");
	//				System.out.println(ENumberU);
	//				System.out.println(varNumberU);
					coutU++;
				}
	//			if (value[3].equalsIgnoreCase("crowded")) {
	//				ENumberCr += Double.parseDouble(value[1]);
	//				EVolCr += Double.parseDouble(value[2]);
	//				varNumberU += Double.parseDouble(value[1])
	//						* Double.parseDouble(value[1]);
	//				varVolU += Double.parseDouble(value[2])
	//						* Double.parseDouble(value[2]);
	//				coutCr++;
	//			}
	//			if (value[3].equalsIgnoreCase("congested")) {
	//				ENumberCo += Double.parseDouble(value[1]);
	//				EVolCo += Double.parseDouble(value[2]);
	//				varNumberU += Double.parseDouble(value[1])
	//						* Double.parseDouble(value[1]);
	//				varVolU += Double.parseDouble(value[2])
	//						* Double.parseDouble(value[2]);
	//				coutCo++;
	//			}
	
				cout1++;
	
			}	
	
			scanner.close();
			Gauss gass = new Gauss();
			ENumberU = gass.Mean((float)ENumberU, coutU);// tinh van toc trung binh
			varNumberU = (float) gass.Varian((float)ENumberU, (float)varNumberU, coutU);
	//		ENumberCr = (float) gass.Mean((float)ENumberCr, coutCr);// tinh van toc trung binh
	//		varNumberCr = (float) gass.Varian((float)ENumberCr, (float)varNumberCr, coutCr);
	//		ENumberCo = (float) gass.Mean((float)ENumberCo, coutCo);// tinh van toc trung binh
	//		varNumberCo = (float) gass.Varian((float)ENumberCo, (float)varNumberCo, coutCo);
	//		
			EVolU = gass.Mean((float)EVolU, coutU);// tinh van toc trung binh
			varVolU = (float) gass.Varian((float)EVolU, (float)varVolU, coutU);
	//		EVolCr = (float) gass.Mean((float)EVolCr, coutCr);// tinh van toc trung binh
	//		varVolCr = (float) gass.Varian((float)EVolCr, (float)varVolCr, coutCr);
	//		EVolCo = (float) gass.Mean((float)EVolCo, coutCo);// tinh van toc trung binh
	//		varVolCo = (float) gass.Varian((float)EVolCo, (float)varVolCo, coutCo);
			
	//		System.out.println("number-class uncrowded:");
	//		System.out.println("E(X)="+ENumberU);
	//		System.out.println("V(X)="+varNumberU);
	//		System.out.println("vol-class uncrowded:");
	//		System.out.println("E(X)="+EVolU);
	//		System.out.println("V(X)="+varVolU);
	//		System.out.println(coutU);
	//		System.out.println("number-class crowded:");
	//		System.out.println("E(X)="+ENumberCr);
	//		System.out.println("V(X)="+varNumberU);
	//		System.out.println("vol-class crowded:");
	//		System.out.println("E(X)="+EVolCr);
	//		System.out.println("V(X)="+varNumberCr);
	//		System.out.println(coutCr);
	//		System.out.println("number-class congested:");
	//		System.out.println("E(X)="+ENumberCo);
	//		System.out.println("V(X)"+varNumberCo);
	//		System.out.println("vol-class congested:");
	//		System.out.println("E(X)="+EVolCo);
	//		System.out.println("V(X)="+varVolCo);
	//		System.out.println(coutCo);
	
			cout = cout1;
	
		}
}

