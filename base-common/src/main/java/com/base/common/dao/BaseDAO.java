package com.base.common.dao;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import com.base.common.mybatis.page.util.PageUtil;
import com.base.common.mybatis.page.vo.PageInfo;
import com.base.common.mybatis.vo.Record;
import com.base.common.mybatis.vo.RecordValue;



@SuppressWarnings("unchecked")
public class BaseDAO<T> extends SqlSessionDaoSupport {
	
	public final String PK_KEY = "pk";

	public final String COLUMN_LIST = "columnList";

	public final String TABLE_NAME = "tableName";

	public final String BASE_NAMESPACE = "com.base";
	
	public static String BASE_PREFIX = "com.base.persistence.";
	
	public final String UPDATE_PK = "UPDATE_PK";

	public Date getSysDate() {
		Date date = (Date) this.getSqlSession().selectOne(
				BASE_NAMESPACE + ".getSysDate");
		return date;
	}

	public void createSeq(String tableName) {
		this.getSqlSession().selectOne(BASE_NAMESPACE + ".createSeq",
				tableName);
	}

	public int getSeq(String seqName) {
		Map map = new HashMap();
		map.put("seqName", seqName);
		Integer date = (Integer) this.getSqlSession().selectOne(
				BASE_NAMESPACE + ".getSeq", map);
		return date;
	}
	

	/**
	 * insert方法
	 * @param record
	 * @return
	 */
	public Object insertDynamicTable(Record record) {
		List list = new ArrayList();
		Map<String, RecordValue> paramMap = record.getValues();
		Iterator it = paramMap.keySet().iterator();
		while (it.hasNext()) {
			String column = (String) it.next();
			RecordValue value = paramMap.get(column);
			Map map = new HashMap();
			map.put("column", column);
			map.put("value", value.getValue());
			map.put("sqlValue", value.getSqlValue());
			list.add(map);
		}
		Map newMap = new HashMap();
		newMap.put(TABLE_NAME, record.getTableName());
		newMap.put(COLUMN_LIST, list);
		return this.getSqlSession().insert(
				BASE_NAMESPACE + ".insertDynamicTable", newMap);
	}

	public Object insertDynamicTable(String tableName, Map paramMap) {
		List list = new ArrayList();
		Iterator it = paramMap.keySet().iterator();
		while (it.hasNext()) {
			String column = (String) it.next();
			Object value = paramMap.get(column);
			Map map = new HashMap();
			map.put("column", column);
			if (value == null) {
				value = "";
			}
			if (value instanceof String) {
				if (((String) value).startsWith("sql:")) {
					map.put("sqlValue", ((String) value).substring(4));
				}
			}
			map.put("value", value);
			list.add(map);
		}
		Map newMap = new HashMap();
		newMap.put(TABLE_NAME, tableName);
		newMap.put(COLUMN_LIST, list);
		return this.getSqlSession().insert(
				BASE_NAMESPACE + ".insertDynamicTable", newMap);
	}
	
	public int updateDynamicTable(Record record, Record pk) {
		if (record == null || record.getValues().isEmpty()) {
			throw new RuntimeException("参数不允许为�?!");
		}
		if(pk==null || pk.getValues().isEmpty()){
			throw new RuntimeException("参数不允许为�?!");
		}
		List list = new ArrayList();
		Map<String, RecordValue> paramMap = record.getValues();
		Iterator it = paramMap.keySet().iterator();
		while (it.hasNext()) {
			String column = (String) it.next();
			RecordValue value = paramMap.get(column);
			Map map = new HashMap();
			map.put("column", column);
			map.put("value", value.getValue());
			map.put("sqlValue", value.getSqlValue());
			list.add(map);
		}
		Map map = new HashMap();
		map.put(TABLE_NAME, record.getTableName());
		map.put(COLUMN_LIST, list);
		Map<String,RecordValue> pkParamMap = pk.getValues();
		it = pkParamMap.keySet().iterator();
		List keyList = new ArrayList();
		while (it.hasNext()) {
			Map pkMap = new HashMap();
			String column = (String) it.next();
			RecordValue value = pkParamMap.get(column);
			if (value == null)
				throw new RuntimeException("主键不允许为�?!");
			pkMap.put("key", column);
			pkMap.put("value", value.getValue());
			pkMap.put("sqlValue", value.getSqlValue());
			keyList.add(pkMap);
		}
		map.put(PK_KEY, keyList);
		return this.getSqlSession().update(
				BASE_NAMESPACE + ".updateDynamicTable", map);
	}

	public int updateDynamicTable(String tableName, Map paramMap, Map pk) {
		if (paramMap == null || paramMap.keySet().size() == 0) {
			throw new RuntimeException("参数不允许为�?!");
		}
		List list = new ArrayList();
		Iterator it = paramMap.keySet().iterator();
		while (it.hasNext()) {
			String column = (String) it.next();
			Object value = paramMap.get(column);
			Map map = new HashMap();
			map.put("column", column);
			map.put("value", value);
			list.add(map);
		}
		Map map = new HashMap();
		map.put(TABLE_NAME, tableName);
		map.put(COLUMN_LIST, list);
		it = pk.keySet().iterator();
		List keyList = new ArrayList();
		while (it.hasNext()) {
			Map pkMap = new HashMap();
			String column = (String) it.next();
			Object value = paramMap.get(column);
			if (value == null)
				value = pk.get(column);
			if (value == null)
				throw new RuntimeException("主键不允许为�?!");
			pkMap.put("key", column);
			pkMap.put("value", value);
			keyList.add(pkMap);
		}
		map.put(PK_KEY, keyList);
		return this.getSqlSession().update(
				BASE_NAMESPACE + ".updateDynamicTable", map);
	}

	/**
	 * 执行sql�?
	 * 
	 * @param sql
	 * @return
	 */
	public List querySql(String sql) {
		if(StringUtils.isBlank(sql)) {
			throw new RuntimeException("sql不能为空�?");
		}else if(!StringUtils.startsWithIgnoreCase(sql, "SELECT")) {
			throw new RuntimeException("只能执行select语句�?");
		}
		Map<String,Object> mp = new HashMap<String,Object>();
		mp.put("sql", sql);
		return this.getSqlSession().selectList(
				BASE_NAMESPACE + ".executeSql", mp);
	}

	public Integer calculate(String sql) {
		Map<String,Object> mp = new HashMap<String,Object>();
		mp.put("sql", sql);
		return (Integer) this.getSqlSession().selectOne(
				BASE_NAMESPACE + ".calculate", mp);
	}

	/**
	 * 执行删除sql�?
	 * 
	 * @param sql
	 * @return
	 */
	public int deleteSql(String sql) {
		if(StringUtils.isBlank(sql)) {
			throw new RuntimeException("sql不能为空�?");
		}else if(StringUtils.startsWithIgnoreCase(sql, "SELECT")) {
			throw new RuntimeException("不能执行select语句�?");
		}
		Map<String,Object> mp = new HashMap<String,Object>();
		mp.put("sql", sql);
		return this.getSqlSession().delete(BASE_NAMESPACE + ".deleteSql",
				mp);
	}

	/**
	 * 执行删除sql�?
	 * 
	 * @param sql
	 * @return
	 */
	public int updateSql(String sql) {
		if(StringUtils.isBlank(sql)) {
			throw new RuntimeException("sql不能为空�?");
		}else if(StringUtils.startsWithIgnoreCase(sql, "SELECT")) {
			throw new RuntimeException("不能执行select语句�?");
		}
		Map<String,Object> mp = new HashMap<String,Object>();
		mp.put("sql", sql);
		return this.getSqlSession().delete(BASE_NAMESPACE + ".updateSql",
				mp);
	}


	public List SelectListByProcedure(String procedureName, Map map) {
		return this.getSqlSession().selectList(procedureName, map);
	}
	
	public List SelectListByProcedure(String procedureName, Map map, String root) {
		this.getSqlSession().selectList(procedureName, map);
		return (List)map.get(root);
	}

	/**
	 * 
	 * @param tableName
	 * @param paramMapList
	 * @return
	 */
	public List insertDynamicTableBatch(String tableName,List<Map> paramMapList) {
		for (Map param : paramMapList) {
			List list = new ArrayList();
			Iterator it = param.keySet().iterator();
			while (it.hasNext()) {
				String column = (String) it.next();
				Object value = param.get(column);
				Map map = new HashMap();
				map.put("column", column);
				if (value == null) {
					value = "";
				}
				if (value instanceof String) {
					if (((String) value).startsWith("sql:")) {
						map.put("sqlValue", ((String) value).substring(4));
					}
				}
				map.put("value", value);
				list.add(map);
			}
			Map newMap = new HashMap();
			newMap.put(TABLE_NAME, tableName);
			newMap.put(COLUMN_LIST, list);
			this.getSqlSession().insert(BASE_NAMESPACE + ".insertDynamicTable",
					newMap);
		}
		return paramMapList;

	}

	/**
	 * 
	 * @param tableName
	 * @param paramMapList
	 * @return
	 */
	public List insertDynamicTableBatch(List<Record> paramMapList) {
		if(paramMapList==null || paramMapList.isEmpty()) {
			return new ArrayList();
		}
		for (Record record : paramMapList) {
			List list = new ArrayList();
			Map<String, RecordValue> param = record.getValues();
			Iterator it = param.keySet().iterator();
			while (it.hasNext()) {
				String column = (String) it.next();
				RecordValue value = param.get(column);
				Map map = new HashMap();
				map.put("column", column);
				map.put("value", value.getValue());
				map.put("sqlValue", value.getSqlValue());
				list.add(map);
			}
			Map newMap = new HashMap();
			newMap.put(TABLE_NAME, record.getTableName());
			newMap.put(COLUMN_LIST, list);
			this.getSqlSession().insert(BASE_NAMESPACE
					+ ".insertDynamicTable", newMap);
		}
		return paramMapList;

	}

	public void updateDynamicTableBatch(List<Record> paramMapList,List<Record> pkList) {
		if(paramMapList==null||pkList==null) throw new RuntimeException("参数不允许为空！");
		if(paramMapList.size()!=pkList.size()) throw new RuntimeException("主键与数据列不同�?");
				int i = 1;
				for (Record record : paramMapList) {
					List list = new ArrayList();
					Map<String,RecordValue> paramMap = record.getValues();
					Iterator it = paramMap.keySet().iterator();
					while (it.hasNext()) {
						String column = (String) it.next();
						if (StringUtils.isEmpty(column)) {
							continue;
						}
						RecordValue value = paramMap.get(column);
						Map map = new HashMap();
						map.put("column", column);
						map.put("value", value.getValue());
						map.put("sqlValue", value.getSqlValue());
						list.add(map);
					}
					Map map = new HashMap();
					map.put(TABLE_NAME, record.getTableName());
					map.put(COLUMN_LIST, list);
					Record pk = pkList.get(i-1);
					Map<String,RecordValue> pkParamMap = pk.getValues();
					it = pkParamMap.keySet().iterator();
					List keyList = new ArrayList();
					while (it.hasNext()) {
						Map pkMap = new HashMap();
						String column = (String) it.next();
						RecordValue value = pkParamMap.get(column);
						pkMap.put("key", column);
						pkMap.put("value", value.getValue());
						pkMap.put("sqlValue", value.getSqlValue());
						keyList.add(pkMap);
					}
					map.put(PK_KEY, keyList);
					this.getSqlSession().update(
							BASE_NAMESPACE + ".updateDynamicTable", map);
					i++;
				}
	}
	
	public void updateDynamicTableBatch(String tableName,
			List<Map> paramMapList) {
				for (Map paramMap : paramMapList) {
					List list = new ArrayList();
					Iterator it = paramMap.keySet().iterator();
					while (it.hasNext()) {
						String column = (String) it.next();
						if (StringUtils.isEmpty(column)
								|| column.equals(UPDATE_PK)) {
							continue;
						}
						Object value = paramMap.get(column);
						Map map = new HashMap();
						map.put("column", column);
						map.put("value", value);
						list.add(map);
					}
					Map map = new HashMap();
					map.put(TABLE_NAME, tableName);
					map.put(COLUMN_LIST, list);

					Map pk = (Map) paramMap.get(UPDATE_PK);
					it = pk.keySet().iterator();
					Map pkMap = new HashMap();
					while (it.hasNext()) {
						String column = (String) it.next();
						Object value = paramMap.get(column);
						pkMap.put("key", column);
						pkMap.put("value", value);
						map.put(PK_KEY, pkMap);
					}
					this.getSqlSession().update(
							BASE_NAMESPACE + ".updateDynamicTable", map);
				}

	}
	/**
	 * 分页统计查询
	 * @param sqlId
	 * @param mp
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public PageInfo selectPageInfo(String sqlId,Map mp,int pageNum,int pageSize){
		List<T> list = new ArrayList<T>();
		PageUtil.startPage(pageNum, pageSize);
		if(sqlId.startsWith("com.")){
			list = this.getSqlSession().selectList(sqlId,mp);
		}else{
			list = this.getSqlSession().selectList(BASE_PREFIX +sqlId,mp);
		}
		PageInfo pageInfo = new PageInfo(list);
		return pageInfo;
	}
	/**
	 * 分页查询，但是不统计总数
	 * @param sql
	 * @param mp
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	public List<T> selectList(String sqlId,Map mp,int pageNum,int pageSize){
		List<T> list = new ArrayList<T>();
		PageUtil.startPage(pageNum, pageSize,false);
		if(sqlId.startsWith("com.")){
			list = this.getSqlSession().selectList(sqlId,mp);
		}else{
			list = this.getSqlSession().selectList(BASE_PREFIX +sqlId,mp);
		}
		return list;
	}
	/**
	 * 保存方法，可以自定义前缀
	 * @param sqlId
	 * @param prefix
	 * @param obj
	 * @return
	 */
	public int save(String sqlId, String prefix, Object obj) {
		if (prefix == null || "".equals(prefix)) {
			return save(sqlId, obj);
		} else {
			if (!prefix.endsWith("\\.")) {
				prefix += ".";
			}
			return getSqlSession().insert(prefix + sqlId, obj);
		}
	}
	/**
	 * 保存方法
	 * @param sqlId
	 * @param prefix
	 * @param obj
	 * @return
	 */
	public int save(String sqlId, Object obj) {
		return getSqlSession().insert(BASE_PREFIX + sqlId, obj);
	}
	/**
	 * 更新方法,可以自定义前�?
	 * @param sqlId
	 * @param prefix
	 * @param obj
	 * @return
	 */
	public int update(String sqlId, String prefix, Object obj) {
		if (prefix == null || "".equals(prefix)) {
			return update(sqlId, obj);
		} else {
			if (!prefix.endsWith("\\.")) {
				prefix += ".";
			}
			return getSqlSession().update(prefix + sqlId, obj);
		}
	}
	/**
	 * 更新方法
	 * @param sqlId
	 * @param prefix
	 * @param obj
	 * @return
	 */
	public int update(String sqlId, Object obj) {
		return getSqlSession().update(BASE_PREFIX + sqlId, obj);
	}
	/**
	 * 删除方法
	 * @param sqlId
	 * @param obj
	 * @return
	 */
	public int delete(String sqlId, Object obj) {
		return getSqlSession().delete(BASE_PREFIX + sqlId, obj);
	}
	/**
	 * 可以自定义前�?的删除方�?
	 * @param sqlId
	 * @param prefix
	 * @param obj
	 * @return
	 */
	public int delete(String sqlId, String prefix, Object obj) {
		if (prefix == null || "".equals(prefix)) {
			return delete(sqlId, obj);
		} else {
			if (!prefix.endsWith("\\.")) {
				prefix += ".";
			}
			return getSqlSession().delete(prefix + sqlId, obj);
		}
	}
	/**
	 * 带参数的单个查询，前�?可以自己定义
	 * @param sqlId
	 * @param prefix
	 * @param obj
	 * @return
	 */
	public Object select(String sqlId, String prefix, Object obj) {
		if (prefix == null || "".equals(prefix)) {
			return select(sqlId, obj);
		} else {
			if (!prefix.endsWith("\\.")) {
				prefix += ".";
			}
			return getSqlSession().selectOne(prefix + sqlId, obj);
		}
	}
	/**
	 * 带参数的单个查询
	 * @param sqlId
	 * @param obj
	 * @return
	 */
	public Object select(String sqlId, Object obj) {
		return getSqlSession().selectOne(BASE_PREFIX + sqlId, obj);
	}
	/**
	 * 不带参数的单个查�?
	 * @param sqlId
	 * @return
	 */
	public Object select(String sqlId){
		return getSqlSession().selectOne(BASE_PREFIX + sqlId);
	}
	/**
	 * 带参数的List集合查询
	 * @param sqlId
	 * @param prefix
	 * @param obj
	 * @return
	 */
	public List<T> selectList(String sqlId, String prefix, Object obj) {
		if (prefix == null || "".equals(prefix)) {
			return selectList(sqlId, obj);
		} else {
			if (!prefix.endsWith("\\.")) {
				prefix += ".";
			}
			return getSqlSession().selectList(prefix + sqlId, obj);
		}
	}
	/**
	 * 带参数的List集合查询
	 * @param sqlId
	 * @param prefix
	 * @param obj
	 * @return
	 */
	public List<T> selectList(String sqlId, Object obj) {
		return getSqlSession().selectList(BASE_PREFIX + sqlId, obj);
	}
	/**
	 * 不带参数的List集合查询
	 * @param sqlId
	 * @return
	 */
	public List<T> selectList(String sqlId) {
		return getSqlSession().selectList(BASE_PREFIX + sqlId);
	}
}
