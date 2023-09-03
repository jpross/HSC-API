package com.ebs.ngs.hsc.hsp.system.interceptor;

import java.sql.Connection;
import java.util.Properties;

import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Intercepts({@Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})})
public class DataSyncQueryLogInterceptor implements Interceptor {
	
	private static final Logger logger = LoggerFactory.getLogger(DataSyncQueryLogInterceptor.class);
	
	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		
		BoundSql boundSql = statementHandler.getBoundSql();
		Object parameter = statementHandler.getParameterHandler().getParameterObject();
		
		String sql = boundSql.getSql();
		sql = sql.replaceAll("\\?", "'" + (String) parameter + "'");
		
		logger.info("==========================================================================================");
		logger.info(sql);
		logger.info("==========================================================================================");
		
		return invocation.proceed();
		
	}
	
	@Override
    public Object plugin (Object target) {
        return Plugin.wrap (target, this);
    }

	@Override
	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub
	}

}
