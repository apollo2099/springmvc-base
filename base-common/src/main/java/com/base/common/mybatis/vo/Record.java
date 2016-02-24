package com.base.common.mybatis.vo;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.base.common.mybatis.util.CUIDHexGenerator;

public class Record {
	private String tableName;
	private Map<String,RecordValue> values;
	private Map<String,Object> valueMap;
	
	public Record(String tableName){
		this.tableName = tableName;
		this.values = new HashMap<String, RecordValue>();
		this.valueMap = new HashMap<String,Object>();
	}
	
	public Object getColValue(String colName) {
		return this.valueMap.get(colName);
	}
	
	public void addColValue(String colName,Object value){
		RecordValue rec = new RecordValue(colName);
		rec.setValue(value);
		this.values.put(colName, rec);
		this.valueMap.put(colName, value);
	}
	
	public void addColValueIf(String colName,Object value) {
		if(!this.valueMap.containsKey(colName)) {
			this.addColValue(colName, value);
		}
	}
	
	public void addColSqlValue(String colName,String value){
		if(StringUtils.isNotEmpty(value)){
			RecordValue rec = new RecordValue(colName);
			rec.setSqlValue("("+value+")");
			this.values.put(colName, rec);
		}
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Map<String, RecordValue> getValues() {
		return values;
	}
	
	public Map<String,Object> getValueMap(){
		return valueMap;
	}
	
	public RecordValue remove(String colName){
		return this.values.remove(colName);
	}
	
	public String setCuid(){
		String cuid = CUIDHexGenerator.getInstance().generate(this.tableName);
		this.addColValue("CUID",cuid);
		return cuid;
	}
	
	public String getCuid(){
		return (String)this.getColValue("CUID");
	}
	
	
}
