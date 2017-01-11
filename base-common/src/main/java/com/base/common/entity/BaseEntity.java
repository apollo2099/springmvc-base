package com.base.common.entity;

import com.base.common.utils.PageInfo;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/4.
 */
public abstract class BaseEntity<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 实体编号（唯一标识）
     */
    protected String id;


    /**
     * 当前实体分页对象
     */
    protected PageInfo<T> pageInfo;

    /**
     * 自定义SQL（SQL标识，SQL内容）
     */
    protected Map<String, String> sqlMap;

    /**
     * 是否是新记录（默认：false），调用setIsNewRecord()设置新记录，使用自定义ID。
     * 设置为true后强制执行插入语句，ID不会自动生成，需从手动传入。
     */
    protected boolean isNewRecord = false;

    public BaseEntity() {

    }

    public BaseEntity(String id) {
        this();
        this.id = id;
    }


    public void setId(String id) {
        this.id = id;
    }


    public PageInfo<T> getPageInfo() {
        if (pageInfo == null){
            pageInfo = new PageInfo<T>();
        }
        return pageInfo;
    }

    public PageInfo<T> setPageInfo(PageInfo<T> pageInfo) {
        this.pageInfo = pageInfo;
        return pageInfo;
    }
}