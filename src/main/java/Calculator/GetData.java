package Calculator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;

import javax.swing.SwingWorker;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

//get dada in dada contaner
public class GetData {
	static String url;
//	static int size;
	String result;

	public GetData(String url) {
		this.url = url;
	}

	public String getResponse() {
		StringBuffer response = new StringBuffer();
		URL obj;
		try {
			obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// optional default is GET
			con.setRequestMethod("GET");

			// add request header
			con.setRequestProperty("Authorization", "Basic YWRtaW46YWRtaW4=");

			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response.toString();
	}

	// lay danh sach tat ca cac nguoi dung
	public ArrayList<Persion> getData() {
		ArrayList<Persion> arrayList = new ArrayList<>();
		Persion persion;
		String str;

		try {

			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(new InputSource(new StringReader(getResponse())));

			doc.getDocumentElement().normalize();
			NodeList nList = doc.getElementsByTagName("om2m:contentInstance");

//			size = nList.getLength();
			for (int i = 0; i < nList.getLength(); i++) {
				Node nNode = nList.item(i);

				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					Element eElement = (Element) nNode;

					// System.out.println("creationTime: "
					// +
					// eElement.getElementsByTagName("om2m:creationTime").item(0).getTextContent());
					// System.out.println("lastModifiedTime: "
					// +
					// eElement.getElementsByTagName("om2m:lastModifiedTime").item(0).getTextContent());

					// decode du lieu ma hoa base64
					str = eElement.getElementsByTagName("om2m:content").item(0).getTextContent();
					str = new String(Base64.getDecoder().decode(str));

					// System.out.println("Decoded value is " +str);

					// boc tach du lieu ma hoa base64
					Document document = dBuilder.parse(new InputSource(new StringReader(str)));
					NodeList nodeList = document.getElementsByTagName("str");

					String[] data = new String[nodeList.getLength()];
					for (int j = 0; j < nodeList.getLength(); j++) {
						Node node = nodeList.item(j);
						Element element = (Element) node;

						data[j] = element.getAttribute("val");
					}

					persion = new Persion();

					persion.setId(Long.parseLong(data[0]));
					persion.setAge(Byte.parseByte(data[1]));
					persion.setSex(Byte.parseByte(data[2]));
					persion.setRegion(Byte.parseByte(data[3]));
					persion.setDomain(Byte.parseByte(data[4]));
					persion.setSmoking(Byte.parseByte(data[5]));
					persion.setFamily(Byte.parseByte(data[6]));
					persion.setPet(Byte.parseByte(data[7]));
					persion.setFev1_fvc(Double.parseDouble(data[8]));
					persion.setFev1_fev1Pre(Double.parseDouble(data[9]));
					persion.setStatus(getStatus(Double.parseDouble(data[8]), Double.parseDouble(data[9])));

					persion.setResion((byte) 2);
					arrayList.add(persion);
				}
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return arrayList;
	}

	/*
	 * // lay danh sach 1 nguoi dung theo ID public ArrayList<Persion>
	 * getListPersion() { ArrayList<Persion> arrayList = getData();
	 * ArrayList<Persion> listPersion = new ArrayList<>();
	 * 
	 * if (!arrayList.isEmpty()) { for (int i = arrayList.size() - 1; i >= 0;
	 * i--) { if (arrayList.get(i).getId() == Long.parseLong(id)) {
	 * listPersion.add(arrayList.get(i)); // list persion, ket qua moi nhat o vi
	 * tri 0 } } } if (!listPersion.isEmpty()) { return listPersion; }
	 * 
	 * return null; }
	 */
	public static byte getStatus(double fev1_fvc, double fev1_fev1Pre) {
		if (fev1_fvc >= 0.7) {
			return 0;
		} else {
			if (fev1_fev1Pre >= 0.8) {
				return 1;
			}
			if (fev1_fev1Pre >= 0.5) {
				return 2;
			}
			if (fev1_fev1Pre >= 0.3) {
				return 3;
			} else {
				return 4;
			}
		}
	}
}
