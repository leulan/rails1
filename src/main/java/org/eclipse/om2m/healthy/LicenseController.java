package org.eclipse.om2m.healthy;

import org.eclipse.om2m.commons.resource.StatusCode;
import org.eclipse.om2m.commons.rest.RequestIndication;
import org.eclipse.om2m.commons.rest.ResponseConfirm;
import org.eclipse.om2m.ipu.service.IpuService;

public class LicenseController implements IpuService {

	public ResponseConfirm doExecute(RequestIndication requestIndication) {
		String[] parts = requestIndication.getTargetID().split("/");
		String appId = parts[2];
		String value = parts[4];

		if (appId.equals(LicenseMonitor.actuatorId)) {
			LicenseMonitor.actuatorValue = Boolean.parseBoolean(value);
			return new ResponseConfirm(StatusCode.STATUS_OK);
		} else {
			return new ResponseConfirm(StatusCode.STATUS_NOT_FOUND, appId + " not found");
		}
	}

	public ResponseConfirm doRetrieve(RequestIndication requestIndication) {
		String[] parts = requestIndication.getTargetID().split("/");
		String appId = parts[2];
		String content;

		if (appId.equals(LicenseMonitor.appID)) {
			content = Mapper.getSensorDataRep(LicenseMonitor.sensorValue);
			return new ResponseConfirm(StatusCode.STATUS_OK, content);
		} else if (appId.equals(LicenseMonitor.actuatorId)) {
			content = Mapper.getActuatorDataRep(LicenseMonitor.actuatorValue);
			return new ResponseConfirm(StatusCode.STATUS_OK, content);
		} else {
			return new ResponseConfirm(StatusCode.STATUS_NOT_FOUND, appId + " not found");
		}

	}

	public ResponseConfirm doUpdate(RequestIndication requestIndication) {
		return new ResponseConfirm(StatusCode.STATUS_NOT_IMPLEMENTED,
				requestIndication.getMethod() + " not Implemented");
	}

	public ResponseConfirm doDelete(RequestIndication requestIndication) {
		return new ResponseConfirm(StatusCode.STATUS_NOT_IMPLEMENTED,
				requestIndication.getMethod() + " not Implemented");
	}

	public ResponseConfirm doCreate(RequestIndication requestIndication) {
		return new ResponseConfirm(StatusCode.STATUS_NOT_IMPLEMENTED,
				requestIndication.getMethod() + " not Implemented");
	}

	public String getAPOCPath(){
		return LicenseMonitor.APOCPATH;
	}
}