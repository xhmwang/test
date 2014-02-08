package com.quanttus.etl;

import com.fasterxml.jackson.databind.ObjectMapper;

public class VitalsFlattener {

	public String flatten(String json) throws Exception {
		
		ObjectMapper mapper = new ObjectMapper();

		Vitals value = (Vitals) mapper.readValue(json, Vitals.class);
		int len = value.vitals.length;
		if (0 == len || null == value.vitals) {
			return "{}"; 
		}

		String res = null;
		StringBuffer sb = new StringBuffer();
		
		for (int i = 0; i < len; i++) {
			
			FlattenedVitals fv = new FlattenedVitals();
			
			fv.userId = value.userId;
			fv.deviceId = value.deviceId;
			fv.payloadVersion = value.payloadVersion;
			fv.firmwareVersion = value.firmwareVersion;
			fv.timestamp = value.vitals[i].timestamp;
			fv.hr = value.vitals[i].hr;
			fv.hrConf = value.vitals[i].hrConf;
			fv.sys = value.vitals[i].sys;
			fv.dia = value.vitals[i].dia;
			fv.bpConf = value.vitals[i].bpConf;
					
			String flatjson = mapper.writeValueAsString(fv); //System.out.println(flatjson);
			sb.append(flatjson);
			
		}
		
		res = "{" + sb.toString() + "}";
		return res;
	}

	public static void main(String[] args) throws Exception {
		String json = "{\"userId\":\"763b1e66-5c4a-48f3-9b4a-b7724c180e4d\",\"deviceId\":\"a5920579-1d08-472d-adc6-cb0ce56c447d\",\"payloadVersion\":0,\"firmwareVersion\":1,\"vitals\":[{\"timestamp\":\"2014-02-03T20:04:01.000+05:00\",\"hr\":-305,\"hrConf\":95,\"sys\":-255,\"dia\":300,\"bpConf\":95},{\"timestamp\":\"2014-02-03T20:04:02.000+05:00\",\"hr\":-305,\"hrConf\":95,\"sys\":-255,\"dia\":300,\"bpConf\":95},{\"timestamp\":\"2014-02-03T20:04:03.000+05:00\",\"hr\":-305,\"hrConf\":95,\"sys\":-255,\"dia\":300,\"bpConf\":95},{\"timestamp\":\"2014-02-03T20:04:04.000+05:00\",\"hr\":-305,\"hrConf\":95,\"sys\":-255,\"dia\":300,\"bpConf\":95},{\"timestamp\":\"2014-02-03T20:04:05.000+05:00\",\"hr\":-305,\"hrConf\":95,\"sys\":-255,\"dia\":300,\"bpConf\":95},{\"timestamp\":\"2014-02-03T20:04:06.000+05:00\",\"hr\":-305,\"hrConf\":95,\"sys\":-255,\"dia\":300,\"bpConf\":95},{\"timestamp\":\"2014-02-03T20:04:07.000+05:00\",\"hr\":-305,\"hrConf\":95,\"sys\":-255,\"dia\":300,\"bpConf\":95},{\"timestamp\":\"2014-02-03T20:04:08.000+05:00\",\"hr\":-305,\"hrConf\":95,\"sys\":-255,\"dia\":300,\"bpConf\":95},{\"timestamp\":\"2014-02-03T20:04:09.000+05:00\",\"hr\":-305,\"hrConf\":95,\"sys\":-255,\"dia\":300,\"bpConf\":95},{\"timestamp\":\"2014-02-03T20:04:10.000+05:00\",\"hr\":-305,\"hrConf\":95,\"sys\":-255,\"dia\":300,\"bpConf\":95},{\"timestamp\":\"2014-02-03T20:04:11.000+05:00\",\"hr\":-305,\"hrConf\":95,\"sys\":-255,\"dia\":300,\"bpConf\":95}]}";
		//String json = "{}";
		//System.out.println(json);

		String res = new VitalsFlattener().flatten(json);
		
		//for (int i = 0; i < res.length; i++)
			System.out.println(res);
	}

}
