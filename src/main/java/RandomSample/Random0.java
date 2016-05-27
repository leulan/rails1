package RandomSample;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import Calculator.Persion;

public class Random0 {
	public static final int MAX = 20000;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		byte x;
		ArrayList<Persion> arrPersion = new ArrayList<Persion>();
		Persion persion;
		int countOne = 0, count_One = 0;
		for (int i = 0; i < MAX; i++) {
			x = randomX();
			persion = new Persion();
			persion.setId((long) i);
			persion.setAge(randomAge(x));
			persion.setSex(randomSex(x));
			persion.setRegion(randomRegion(x));
			persion.setDomain(randomDomain(x));
			persion.setSmoking(randomSmoking(x));
			persion.setPet(randomPet());
			persion.setFamily(randomFamily(x));
			persion.setStatus(x);
			persion.setResion(randomResion(persion.getAge(), persion.getSex(), persion.getRegion(), persion.getDomain(),
					persion.getSmoking()));

			if (persion.getResion() == 1) {
				countOne++;
			}
			if (persion.getResion() == -1) {
				count_One++;
			}
			arrPersion.add(persion);
		}

		System.out.println(count_One + "\t" + countOne);

		try {
			File file = new File("D:\\input.txt");
			file.createNewFile();
			FileWriter fw = new FileWriter(file);
			BufferedWriter bw = new BufferedWriter(fw);

			// bw.write("ID\tAGE\tSEX\tREGION\tDOMAIN\tSMOKING\tSTATUS\tRESION\n");

			if (!arrPersion.isEmpty()) {
				for (int i = 0; i < arrPersion.size() - 1; i++) {
					persion = arrPersion.get(i);
					bw.write(persion.getAge() + "\t" + persion.getSex() + "\t" + persion.getRegion() + "\t"
							+ persion.getDomain() + "\t" + persion.getSmoking() + "\t" + persion.getFamily() + "\t"
							+ persion.getPet() + "\t" + persion.getStatus() + "\t" + persion.getResion() + "\n");
				}
			}

			persion = arrPersion.get(arrPersion.size() - 1);
			bw.write(persion.getAge() + "\t" + persion.getSex() + "\t" + persion.getRegion() + "\t"
					+ persion.getDomain() + "\t" + persion.getSmoking() + "\t" + persion.getFamily() + "\t"
					+ persion.getPet() + "\t" + persion.getStatus() + "\t" + persion.getResion());
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static int randomOne(int n) {
		Random r = new Random();
		return (r.nextInt(n) + 1);
	}

	public static byte randomX() {
		byte y = (byte) randomOne(100);
		if (y <= 4) {
			return ((byte) randomOne(4));
		} else {
			return 0;
		}
	}

	public static byte randomAge(byte x) {
		byte y = (byte) randomOne(100);
		if ((x != 0 && y <= 91) || (x == 0 && y <= 50)) {
			return 1;// 1== >=40 tuoi
		} else {
			return 0;
		}
	}

	public static byte randomSex(byte x) {
		byte y = (byte) randomOne(100);
		if ((x != 0 && y <= 72) || (x == 0 && y <= 50)) {
			return 1;// male
		} else {
			return 0;// female
		}
	}

	public static byte randomRegion(byte x) {
		byte y = (byte) randomOne(100);
		if ((x != 0 && y <= 25) || (x == 0 && y <= 28)) {
			return 0;// thanh thi
		} else {
			if ((x != 0 && y <= 47 && y > 25) || (x == 0 && y <= 57 && y > 28)) {
				return 1;// mien nui
			} else {
				return 2;// nong thon
			}
		}
	}

	public static byte randomDomain(byte x) {
		byte y = (byte) randomOne(100);
		if ((x != 0 && y <= 66) || (x == 0 && y <= 46)) {
			return 0;// mien bac
		} else {
			if ((x != 0 && y <= 82 && y > 66) || (x == 0 && y <= 51 && y > 46)) {
				return 1;// mien trung
			} else {
				return 2;// mien nam
			}
		}
	}

	public static byte randomSmoking(byte x) {
		byte y = (byte) randomOne(100);
		if ((x != 0 && y <= 87) || (x == 0 && y <= 25)) {
			return 1;
		} else {
			return 0;
		}
	}

	public static byte randomResion(byte age, byte sex, byte region, byte domain, byte smoking) {
		byte y = (byte) randomOne(100);

		if (smoking == 1 && region == 2) {
			if (y <= 89) 
				return -1;
			if (y<=98)
				return 0;
			return 1;
			
		} else {
			if (smoking == 1 && region != 2) {
				if (y <= 75)
					return -1;
				if (y<= 92)
					return 0;
				return 1;
			}

			if (smoking != 1 && region == 2) {
				if (y <= 50)
					return -1;
				if (y<= 92)
					return 0;
				return 1;
			}
		}

		if (y <= 40)
			return 1;
		else
			return 0;
	}

	// gia su neu nguoi nha bi benh thi ti le mac benh se la 50%
	// neu nguoi nha ko bi benh thi ti le mac benh la 5%
	public static byte randomFamily(byte x) {
		byte y = (byte) randomOne(100);
		if ((x != 0 && y <= 50) || (x == 0 && y <= 5)) {
			return 1;
		} else {
			return 0;
		}
	}

	// gia su ti le nuoi dong vat la 60%
	public static byte randomPet() {
		byte y = (byte) randomOne(100);
		if (y <= 60) {
			return 1;
		} else {
			return 0;
		}
	}
}
