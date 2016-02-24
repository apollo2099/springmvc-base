package com.base.common.mybatis.page.util;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import com.base.common.mybatis.page.vo.Page;

import java.util.List;
import java.util.Properties;

/**
 * Mybatis - 通用分页拦截�?
 *
 */
@SuppressWarnings({"rawtypes", "unchecked"})
@Intercepts(@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}))
public class PageUtil implements Interceptor {
    private static final ThreadLocal<Page> LOCAL_PAGE = new ThreadLocal<Page>();
    //sql工具�?
    private SqlUtil SQLUTIL;
    //RowBounds参数offset作为PageNum使用 - 默认不使�?
    private boolean offsetAsPageNum = false;
    //RowBounds是否进行count查询 - 默认不查�?
    private boolean rowBoundsWithCount = false;
    //当设置为true的时候，如果pagesize设置�?0（或RowBounds的limit=0），就不执行分页
    private boolean pageSizeZero = false;

    /**
     * �?始分�?
     *
     * @param pageNum  页码
     * @param pageSize 每页显示数量
     */
    public static void startPage(int pageNum, int pageSize) {
        startPage(pageNum, pageSize, true);
    }

    /**
     * �?始分�?
     *
     * @param pageNum  页码
     * @param pageSize 每页显示数量
     * @param count    是否进行count查询
     */
    public static void startPage(int pageNum, int pageSize, boolean count) {
        LOCAL_PAGE.set(new Page(pageNum, pageSize, count));
    }

    /**
     * 获取分页参数
     *
     * @param rowBounds RowBounds参数
     * @return 返回Page对象
     */
    private Page getPage(RowBounds rowBounds) {
        Page page = LOCAL_PAGE.get();
        //移除本地变量
        LOCAL_PAGE.remove();

        if (page == null) {
            if (offsetAsPageNum) {
                page = new Page(rowBounds.getOffset(), rowBounds.getLimit(), rowBoundsWithCount);
            } else {
                page = new Page(rowBounds, rowBoundsWithCount);
            }
        }
        return page;
    }

    /**
     * Mybatis拦截器方�?
     *
     * @param invocation 拦截器入�?
     * @return 返回执行结果
     * @throws Throwable 抛出异常
     */
    public Object intercept(Invocation invocation) throws Throwable {
        final Object[] args = invocation.getArgs();
        RowBounds rowBounds = (RowBounds) args[2];
        if (LOCAL_PAGE.get() == null && rowBounds == RowBounds.DEFAULT) {
            return invocation.proceed();
        } else {
            //忽略RowBounds-否则会进行Mybatis自带的内存分�?
            args[2] = RowBounds.DEFAULT;
            MappedStatement ms = (MappedStatement) args[0];
            Object parameterObject = args[1];
            //分页信息
            Page page = getPage(rowBounds);
            //pageSizeZero的判�?
            if (pageSizeZero && page.getPageSize() == 0) {
                //执行正常（不分页）查�?
                Object result = invocation.proceed();
                //得到处理结果
                page.addAll((List) result);
                //相当于查询第�?�?
                page.setPageNum(1);
                //这种情况相当于pageSize=total
                page.setPageSize(page.size());
                //仍然要设置total
                page.setTotal(page.size());
                //返回结果仍然为Page类型 - 便于后面对接收类型的统一处理
                return page;
            }
            //�?单的通过total的�?�来判断是否进行count查询
            if (page.isCount()) {
                BoundSql boundSql = null;
                //只有静�?�sql�?要获取boundSql
                if (!SQLUTIL.isDynamic(ms)) {
                    boundSql = ms.getBoundSql(parameterObject);
                }
                //将参数中的MappedStatement替换为新的qs
                args[0] = SQLUTIL.getCountMappedStatement(ms, boundSql);
                //查询总数
                Object result = invocation.proceed();
                //设置总数
                page.setTotal((Integer) ((List) result).get(0));
                if (page.getTotal() == 0) {
                    return page;
                }
            }
            //pageSize>0的时候执行分页查询，pageSize<=0的时候不执行相当于可能只返回了一个count
            if (page.getPageSize() > 0 &&
                    ((rowBounds == RowBounds.DEFAULT && page.getPageNum() > 0)
                            || rowBounds != RowBounds.DEFAULT)) {
                BoundSql boundSql = null;
                //只有静�?�sql�?要获取boundSql
                if (!SQLUTIL.isDynamic(ms)) {
                    boundSql = ms.getBoundSql(parameterObject);
                }
                //将参数中的MappedStatement替换为新的qs
                args[0] = SQLUTIL.getPageMappedStatement(ms, boundSql);
                //动�?�sql时，boundSql在这儿�?�过新的ms获取
                if (boundSql == null) {
                    boundSql = ((MappedStatement) args[0]).getBoundSql(parameterObject);
                }
                //判断parameterObject，然后赋�?
                args[1] = SQLUTIL.setPageParameter(parameterObject, boundSql, page);
                //执行分页查询
                Object result = invocation.proceed();
                //得到处理结果
                page.addAll((List) result);
            }
            //返回结果
            return page;
        }
    }

    /**
     * 只拦截Executor
     *
     * @param target
     * @return
     */
    public Object plugin(Object target) {
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    /**
     * 设置属�?��??
     *
     * @param p 属�?��??
     */
    public void setProperties(Properties p) {
        //数据库方�?
        String dialect = p.getProperty("dialect");
        SQLUTIL = new SqlUtil(dialect);
        //offset作为PageNum使用
        String offsetAsPageNum = p.getProperty("offsetAsPageNum");
        this.offsetAsPageNum = Boolean.parseBoolean(offsetAsPageNum);
        //RowBounds方式是否做count查询
        String rowBoundsWithCount = p.getProperty("rowBoundsWithCount");
        this.rowBoundsWithCount = Boolean.parseBoolean(rowBoundsWithCount);
        //当设置为true的时候，如果pagesize设置�?0（或RowBounds的limit=0），就不执行分页
        String pageSizeZero = p.getProperty("pageSizeZero");
        this.pageSizeZero = Boolean.parseBoolean(pageSizeZero);
        //分页合理化，true�?启，如果分页参数不合理会自动修正。默认false不启�?
        String reasonable = p.getProperty("reasonable");
        Page.setReasonable(reasonable);
    }
}
