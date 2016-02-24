package com.base.common.mybatis.vo;

public class RecordValue {
	private String colName;
	private Object value;
	private String sqlValue;
	public RecordValue(String colName){
		this.colName = colName;
	}
	public String getColName() {
		return colName;
	}
	public void setColName(String colName) {
		this.colName = colName;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public String getSqlValue() {
		return sqlValue;
	}
	public void setSqlValue(String sqlValue) {
		this.sqlValue = sqlValue;
	}
}
