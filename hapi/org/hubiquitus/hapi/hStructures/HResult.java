package org.hubiquitus.hapi.hStructures;

import org.hubiquitus.hapi.structures.HJSONSerializable;
import org.json.JSONException;
import org.json.JSONObject;

public class HResult implements HJSONSerializable {
	
	private String cmd = null;
	private String reqid = null;
	private ResultStatus status = null;
	private Object result = null;
	
	
	public HResult() {	}
	
	public HResult(String reqid, String cmd, Object result) {
		this.reqid = reqid;
		this.cmd = cmd;
		this.result = result;
	}
	
	public HResult(JSONObject jsonObj) {
		try {
			this.fromJSON(jsonObj);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("erreur HCommand fromJSON");
		}
	}
	
	public JSONObject toJSON() {
		JSONObject jsonObj = new JSONObject();
		
		try {
			jsonObj.put("cmd", this.cmd);
			jsonObj.put("requid",this.reqid);
			jsonObj.put("status",this.status.value());
			jsonObj.put("result",this.result);
		} catch (JSONException e) {
			e.printStackTrace();
			jsonObj = null;
		}
		
		return jsonObj;
	}
	
	public void fromJSON(JSONObject jsonObj) throws Exception {
		try {
			if (jsonObj.has("cmd") && jsonObj.has("reqid") && jsonObj.has("status")) {
				this.cmd = jsonObj.getString("cmd");
				this.reqid = jsonObj.getString("reqid");
				this.result = jsonObj.get("result");
				if(jsonObj.has("status")) {
					this.status = ResultStatus.constant(jsonObj.getInt("status"));
				}
			} else {
				throw new Exception(this.getClass().toString() + " JSon object mal formated");
			}
		} catch (Exception e) {
			throw new Exception(this.getClass().toString() + " JSon object mal formated : " + e.getMessage());
		}	
	}
}