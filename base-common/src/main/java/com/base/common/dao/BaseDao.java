package com.base.common.dao;

import java.util.List;
import java.util.Map;

public interface BaseDao {

	public int save(String str, Object obj) throws Exception;
	
	public <T> int batchSave(String str, List<T> objs) throws Exception;
	
	public int update(String str, Object obj) throws Exception;
	
	public <T> void batchUpdate(String str, List<T> objs) throws Exception;
	
	public int delete(String str, Object obj) throws Exception;
	
	public <T> int batchDelete(String str, List<T> objs) throws Exception;

	public <T> T findForObject(String str, Object obj) throws Exception;

	public <E> List<E> findForList(String str, Object obj) throws Exception;

	public <K, V> Map<K, V> findForMap(String str, Object obj, String key, String value) throws Exception;


}
