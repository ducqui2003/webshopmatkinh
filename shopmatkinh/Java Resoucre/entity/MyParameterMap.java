package entity;

import java.util.Map;

public class MyParameterMap {
	private Map<String, String[]> parameterMap;
	
	public MyParameterMap() {
		this.parameterMap = null;
	}
	public MyParameterMap(Map<String,String[]> parameterMap) {
		this.parameterMap = parameterMap;
	}
	public Map<String, String[]> getParameterMap() {
		return parameterMap;
	}
	public void setParameterMap(Map<String, String[]> parameterMap) {
		this.parameterMap = parameterMap;
	}
	public String toString() {
		String s = "";
		for(String name : parameterMap.keySet()) {
			s += "["+name + ":"+parameterMap.get(name)[0] +"]";
		}
		return s;
	}
}
