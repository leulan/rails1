package Calculator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CalcResion {
	public static final int NUMINFO = 8;

	public static Resion[][] arrResion = new Resion[5][NUMINFO];
	public static Resion sumResion = new Resion();
	public static int[][] ARR = {
			{ 3567, 4081, 1985, 3713, 4091, 2039, 1149, 2670, 1782, 3591, 3806, 1918, 3158, 7537, 3796, 30484, 33024,
					16492, 30484, 33024, 16492, 7028, 8112, 4057 },
			{ 4054, 4175, 2138, 3908, 4165, 2084, 1174, 2740, 1793, 463, 430, 210, 4463, 719, 327, 30484, 33024, 16492,
					30484, 33024, 16492, 148, 34, 12 },
			{ 0, 0, 0, 0, 0, 0, 5298, 2846, 548, 3567, 4020, 1995, 0, 0, 0, 0, 0, 0, 0, 0, 0, 140, 40, 18 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 159, 34, 18 },
			{ 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 146, 36, 18 } };

	/*
	 * public void readFile(String link) { try { File f = new File(link);
	 * FileInputStream fis = new FileInputStream(f); Scanner scan = new
	 * Scanner(fis);
	 * 
	 * for (int i = 0; i < 5; i++) { for (int j = 0; j < NUMINFO; j++) {
	 * arrResion[i][j].setDown(scan.nextLong());
	 * arrResion[i][j].setNone(scan.nextLong());
	 * arrResion[i][j].setUp(scan.nextLong()); } }
	 * 
	 * sumResion.setDown(scan.nextLong()); sumResion.setNone(scan.nextLong());
	 * sumResion.setUp(scan.nextLong());
	 * 
	 * } catch (FileNotFoundException e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } }
	 */
	public byte xacDinhNhom(Persion persion) {
		double down = 1;
		double none = 1;
		double up = 1;
		byte[] arr = new byte[NUMINFO];

		arr[0] = persion.getAge();
		arr[1] = persion.getSex();
		arr[2] = persion.getRegion();
		arr[3] = persion.getDomain();
		arr[4] = persion.getSmoking();
		arr[5] = persion.getFamily();
		arr[6] = persion.getPet();
		arr[7] = persion.getStatus();

		if (arr[0] >= 40) {
			arr[0] = 1;
		} else {
			arr[0] = 0;
		}

		for (byte i = 0; i < NUMINFO; i++) {
			down *= arrResion[arr[i]][i].getDown();
			none *= arrResion[arr[i]][i].getNone();
			up *= arrResion[arr[i]][i].getUp();

		}

		down = down / Math.pow(sumResion.getDown(), NUMINFO);
		none = none / Math.pow(sumResion.getNone(), NUMINFO);
		up = up / Math.pow(sumResion.getUp(), NUMINFO);

		System.out.println(down);
		System.out.println(none);
		System.out.println(up);
		System.out.println();

		if (up >= Math.max(down, none)) {
			return 1;
		} else {
			if (none >= down) {
				return 0;
			} else {
				return -1;
			}
		}
	}

	public ArrayList<Persion> doExcutive(String urlData, String urlFullData) {
		System.out.println("do excutive");
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < NUMINFO; j++) {
				arrResion[i][j] = new Resion();
			}
		}
		// readFile("D:\\output.txt");

		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < NUMINFO; j++) {
				arrResion[i][j].setDown(ARR[i][j * 3]);
				arrResion[i][j].setNone(ARR[i][j * 3 + 1]);
				arrResion[i][j].setUp(ARR[i][j * 3 + 2]);
			}
		}
		sumResion.setDown(30484);
		sumResion.setNone(33024);
		sumResion.setUp(16492);

		GetData getData = new GetData(urlData);
		ArrayList<Persion> arrayData = getData.getData();
		int sizeData = arrayData.size();

		GetData getFullData = new GetData(urlFullData);
		ArrayList<Persion> arrayFullData = getFullData.getData();
		int sizeFullData = arrayFullData.size();

		System.out.println(sizeFullData + " / " + sizeData);

		ArrayList<Persion> listPersion = new ArrayList<>();
		if (sizeData > sizeFullData) {
			for (int i = sizeFullData; i < sizeData; i++) {
				Persion per = arrayData.get(i);
				per.setResion(xacDinhNhom(per));

				listPersion.add(per);
			}
			return listPersion;
		}

		return null;
	}
}
