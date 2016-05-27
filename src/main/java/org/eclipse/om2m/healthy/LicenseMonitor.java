package org.eclipse.om2m.healthy;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.om2m.commons.resource.Application;
import org.eclipse.om2m.commons.resource.Container;
import org.eclipse.om2m.commons.resource.ContentInstance;
import org.eclipse.om2m.commons.resource.StatusCode;
import org.eclipse.om2m.commons.rest.RequestIndication;
import org.eclipse.om2m.commons.rest.ResponseConfirm;
import org.eclipse.om2m.core.service.SclService;

import calgps.ObjTraining;
import calgps.Sql1;
import calgps.Train;
import Calculator.CalcResion;
import Calculator.Persion;

public class LicenseMonitor {
	static SclService core;
	static String sclId = System.getProperty("org.eclipse.om2m.sclBaseId", "");

	static String reqEntity = System.getProperty("org.eclipse.om2m.adminRequestingEntity", "");
	/** Generic create method name */
	public final static String METHOD_CREATE = "CREATE";
	/** Generic execute method name */
	public final static String METHOD_RITRIVE = "RETRIEVE";
	/** State container id */
	public final static String DATA = "DATA";
	/** Descriptor container id */
	public final static String DESC = "DESCRIPTOR";
	static String APOCPATH = "licenses";
	static String actuatorId = "MY_ACTUATOR";
	static String appID = "HEALTHY";
	static String urlData = "http://127.0.0.1:8080/om2m/nscl/applications/HEALTHY/containers/DATA/contentInstances/";
	static String urlFullData = "http://127.0.0.1:8080/om2m/nscl/applications/HEALTHY/containers/FULLDATA/contentInstances/";

	static boolean actuatorValue = false;
	static int sensorValue = 0;
	public static Log LOGGER = LogFactory.getLog(LicenseMonitor.class);
	// CalcResion calc = new CalcResion();

	public LicenseMonitor(SclService sclService) {
		core = sclService;
	}

	public void start() {
		// Create required resources for the Sensor

		createApplicationResources(appID, APOCPATH);
		addFullData();

		ResponseConfirm confirm = core.doRequest(new RequestIndication(METHOD_RITRIVE,
				sclId + "/applications/" + appID + "/containers/DATA/contentInstances", reqEntity));
		if (confirm.getStatusCode().equals(StatusCode.STATUS_OK)) {
			confirm.getRepresentation();
		}
	}

	public void createApplicationResources(String appID, String ipuID) {
		String targetId, content;

		// Create the License application
		targetId = sclId + "/applications";
		ResponseConfirm response = core
				.doRequest(new RequestIndication(METHOD_CREATE, targetId, reqEntity, new Application(appID, ipuID)));
		if (response.getStatusCode().equals(StatusCode.STATUS_CREATED)) {
			// Create the "DESCRIPTOR" container
			targetId = sclId + "/applications/" + appID + "/containers";
			core.doRequest(new RequestIndication(METHOD_CREATE, targetId, reqEntity, new Container("DESCRIPTOR")));

			// Create the "DATA" container
			core.doRequest(new RequestIndication(METHOD_CREATE, targetId, reqEntity, new Container("DATA")));

			core.doRequest(new RequestIndication(METHOD_CREATE, targetId, reqEntity, new Container("FULLDATA")));

			// Create the description contentInstance
			content = Mapper.getSensorDescriptorRep(sclId, appID, ipuID);
			targetId = sclId + "/applications/" + appID + "/containers/DESCRIPTOR/contentInstances";
			core.doRequest(
					new RequestIndication(METHOD_CREATE, targetId, reqEntity, new ContentInstance(content.getBytes())));
		} else {
			// Get List Data from contentInstances
		}
	}

	public void createActuatorResources(String ipuID) {
		String targetId, content;

		// Create the "MY_ACTUATOR" application
		targetId = sclId + "/applications";
		ResponseConfirm response = core.doRequest(
				new RequestIndication(METHOD_CREATE, targetId, reqEntity, new Application(actuatorId, ipuID)));

		if (response.getStatusCode().equals(StatusCode.STATUS_CREATED)) {
			// Create the "DESCRIPTOR" container
			targetId = sclId + "/applications/" + actuatorId + "/containers";
			core.doRequest(new RequestIndication(METHOD_CREATE, targetId, reqEntity, new Container("DESCRIPTOR")));

			// Create the "DATA" container
			core.doRequest(new RequestIndication(METHOD_CREATE, targetId, reqEntity, new Container("DATA")));

			// Create the description contentInstance
			content = Mapper.getActutaorDescriptorRep(sclId, actuatorId, ipuID);
			targetId = sclId + "/applications/" + actuatorId + "/containers/DESCRIPTOR/contentInstances";
			core.doRequest(new RequestIndication("CREATE", targetId, reqEntity, content));

			// Create the data contentInstance
			content = Mapper.getActuatorDataRep(actuatorValue);
			targetId = sclId + "/applications/" + actuatorId + "/containers/DATA/contentInstances";
			core.doRequest(new RequestIndication(METHOD_CREATE, targetId, reqEntity, content));
		}
	}

	public void addFullData() {
		System.out.println("start add full data");
		// calc.readFile("output.txt");

		// calc.setArrayResion();
		new Thread() {
			public void run() {
				while (true) {
					try {
						Thread.sleep(10000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

					String[] data = new String[4];
					ObjTraining obj=new ObjTraining();
					Train n=new Train();
				//	Sql1 s=new Sql1();
					
						try {
							obj=n.doExcute(n);
							LOGGER.info("hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh");
							LOGGER.info(obj.getDensity());
							LOGGER.info(obj.getNumber());
							LOGGER.info(obj.getAddress());
							LOGGER.info(obj.getVol());
						} catch (ClassNotFoundException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					
					

					if (obj!= null) {
						
						data[0]= obj.getAddress()+ "";
						data[1] =obj.getNumber()+ "";
						data[2] =obj.getVol() + "";
						data[3] =obj.getDensity()+"";
						

							String content = Mapper.getAppFullData(data);
							System.out.println(content);
							String targetID = sclId + "/applications/" + appID
									+ "/containers/FULLDATA/contentInstances";
							core.doRequest(new RequestIndication(METHOD_CREATE, targetID, reqEntity, content));
						}
				}
			}
		}.start();
	}

	public static void stop() {
		// TODO Auto-generated method stub
	}
//	public static void main(String[] ar) throws IOException{
//		String[] data = new String[4];
//		//ArrayList<Persion> listPersion = new CalcResion().doExcutive(urlData, urlFullData);
//		ObjTraining obj=new ObjTraining();
//		Train n=new Train();
//		try {
//			obj=new Train().doExcute(n);
//			if (obj != null) {
//				
//				data[0] =obj.getAddress()+ "";
//				data[1] = obj.getNumber() + "";
//				data[2] = obj.getVol() + "";
//				data[3] =obj.getDensity()+"";
//				System.out.println(obj.getAddress());
//				String t=Mapper.getAppFullData(data);
//				System.out.println(t);
//			}
//		} catch (ClassNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//	}
}
