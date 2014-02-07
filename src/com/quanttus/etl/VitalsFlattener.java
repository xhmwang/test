package com.quanttus.etl;

import java.net.URL;

import com.fasterxml.jackson.databind.ObjectMapper;

public class VitalsFlattener {

	public String[] flatten(String json) throws Exception {
		String[] flatjsonArray = {"",""};
		ObjectMapper mapper = new ObjectMapper();

		Vitals value = (Vitals) mapper.readValue(json, Vitals.class);
		
		//String jsonString = mapper.writeValueAsString(value);
		for (int i = 0; i < value.vitals.length; i++) {
			
			FlattenedVitals fv = new FlattenedVitals();
			
			fv.userId = value.userId;
			fv.deviceId = value.deviceId;
			fv.timestamp = value.vitals[i].timestamp;
			fv.hr = value.vitals[i].hr;
			fv.sys = value.vitals[i].sys;
					
			String flatjson = mapper.writeValueAsString(fv); System.out.println(flatjson);
			flatjsonArray[i] = flatjson;
		}
		
		return flatjsonArray;
	}

	public static void main(String[] args) throws Exception {
		URL input = new URL("file:///tmp/ingest");
		String json = "{  \"userId\": \"049e1a03-31cc-4858-9f53-65ad7cc32208\",  \"deviceId\": \"1e22a9ff-018e-4a79-8a48-ca1e455d8f9e\",  \"payloadVersion\": 0,  \"firmwareVersion\": 1,  \"vitals\": [     {\"timestamp\":\"2014-01-29T12:30:00.000-05:00\",\"hr\":28.2,\"hrConf\":0.85,\"sys\":32.0,\"dia\":12.7, \"bpConf\":0.99},     {\"timestamp\":\"2014-01-29T12:30:01.000-05:00\",\"hr\":38.2,\"hrConf\":0.75,\"sys\":35.0,\"dia\":14.1, \"bpConf\":0.92}  ] } ";
		
		System.out.println(json);
		String[] res = null;
		res = new VitalsFlattener().flatten(json);
		System.out.println(res);
	}

}
