package RandomSample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import Calculator.GetData;
import Calculator.Persion;
import Calculator.Resion;

public class Training {

	public static final int NUMINFO = 8;

	public static Resion[][] arrResion = new Resion[5][NUMINFO];
	public static Resion sumResion = new Resion();

	public static void main(String[] args) {
		countResion("input.txt");

		outFile();
	}

	public static void countResion(String link) {
		byte[] truong = new byte[NUMINFO];
		byte resion;

		for (byte i = 0; i < 5; i++) {
			for (byte j = 0; j < NUMINFO; j++) {
				arrResion[i][j] = new Resion();
			}
		}

		try {
			File f = new File(link);
			FileInputStream fis = new FileInputStream(f);
			Scanner scan = new Scanner(fis);
			System.out.println(f.getAbsolutePath());
			
			while (scan.hasNextLine()) { // id // scan.nextLong();

				// 8 truong tuoi, gioi tinh, vung, mien, hut thuoc, gia dinh,
				// dong vat, chan doan
				for (byte i = 0; i < NUMINFO; i++) {
					truong[i] = scan.nextByte();
				}

				// du doan
				resion = scan.nextByte();

				// dem so luong resion
				switch (resion) {
				case -1:
					sumResion.setDown(sumResion.getDown() + 1);
					break;
				case 0:
					sumResion.setNone(sumResion.getNone() + 1);
					break;
				case 1:
					sumResion.setUp(sumResion.getUp() + 1);
					break;
				default:
					break;
				}

				for (byte i = 0; i < NUMINFO; i++) {
					setArr(resion, truong[i], i);
				}
			}

			scan.close();
		} catch (

		Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void setArr(byte resion, byte truong, byte i) {
		long temp = 0;
		switch (resion) {
		case -1:
			temp = arrResion[truong][i].getDown() + 1;
			arrResion[truong][i].setDown(temp);
			break;
		case 0:
			temp = arrResion[truong][i].getNone() + 1;
			arrResion[truong][i].setNone(temp);
			break;
		case 1:
			temp = arrResion[truong][i].getUp() + 1;
			arrResion[truong][i].setUp(temp);
			break;
		default:
			break;
		}

		// family, pet khong tac dong den du doan
		for (int j = 0; j < 2; j++) {
			for (int k = 5; k < 7; k++) {
				arrResion[j][k].setDown(sumResion.getDown());
				arrResion[j][k].setNone(sumResion.getNone());
				arrResion[j][k].setUp(sumResion.getUp());
			}
		}
	}

	public static void outFile() {
		try {
			File f = new File("output.txt");
			FileOutputStream fos = new FileOutputStream(f);
			PrintWriter pw = new PrintWriter(fos);

			// System.out.println(f.getAbsolutePath());

			for (byte i = 0; i < 5; i++) {
				for (byte j = 0; j < NUMINFO; j++) {
					pw.print(arrResion[i][j].getDown() + " " + arrResion[i][j].getNone() + " " + arrResion[i][j].getUp()
							+ "  ");
				}
				pw.println();
			}
			pw.print(sumResion.getDown() + " " + sumResion.getNone() + " " + sumResion.getUp());

			pw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
