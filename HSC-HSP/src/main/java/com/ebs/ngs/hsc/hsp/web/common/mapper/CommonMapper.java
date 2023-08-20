package com.ebs.ngs.hsc.hsp.web.common.mapper;

import java.util.List;
import java.util.regex.Pattern;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ebs.ngs.hsc.hsp.web.common.mapper.DataBaseFactory.DataSource;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class CommonMapper {
	
	@Autowired
	private DataBaseFactory dbFactory;
	
	private final static Pattern SELECT_SQL_PATTERN = Pattern.compile("^(find|retrieve|select|get)\\w+");
	
	private final static Pattern LIST_SQL_PATTERN = Pattern.compile("\\w+List$");
	
	private final static Pattern INSERT_SQL_PATTERN = Pattern.compile("^(insert|save|add)\\w+");
	
	private final static Pattern UPDATE_SQL_PATTERN = Pattern.compile("^(update|modify|set)\\w+");
	
	private final static Pattern DELETE_SQL_PATTERN = Pattern.compile("^(delete|remove)\\w+");
	
	private SqlSession getSqlSession(DataSource dataSource) {
		return dbFactory.getDataBase(dataSource);
	}
	
	public <T> T selectOne(String queryId) {
		return selectOne(queryId, null);
	}
	
	public <T> T selectOne(String queryId, Object parameter) {
		return selectOne(queryId, parameter, DataSource.HSC_SLAVE);
	}
	
	public <T> T selectOne(String queryId, Object parameter, DataSource dataSource) {
		/*
		if (!dataSource.isAbleRead()) {
			throw new RuntimeException(String.format("%s DB에서는 SELECT 작업을 할 수 없습니다.", dataSource.name()));
		}
		*/
		return getSqlSession(dataSource).selectOne(queryId, parameter);
	}
	
	public <E> List<E> selectList(String queryId) {
		return selectList(queryId, null);
	}
	
	public <E> List<E> selectList(String queryId, Object parameter) {
		return selectList(queryId, parameter, DataSource.HSC_SLAVE);
	}
	

	
	public <T> T selectOneMaster(String queryId) {
		return selectOneMaster(queryId, null);
	}
	
	public <T> T selectOneMaster(String queryId, Object parameter) {
		return selectOne(queryId, parameter, DataSource.HSC_MASTER);
	}
	
	public <E> List<E> selectListMaster(String queryId) {
		return selectListMaster(queryId, null);
	}
	
	public <E> List<E> selectListMaster(String queryId, Object parameter) {
		return selectList(queryId, parameter, DataSource.HSC_MASTER);
	}
	
	
	public <E> List<E> selectList(String queryId, Object parameter, DataSource dataSource) {
		if (!dataSource.isAbleRead()) {
			throw new RuntimeException(String.format("%s DB에서는 SELECT 작업을 할 수 없습니다.", dataSource.name()));
		}
		return getSqlSession(dataSource).selectList(queryId, parameter);
	}
	
	public Object execute(String queryId, Object parameter) {
		String[] tempSplit = queryId.split("\\.");
		String tempQueryId = tempSplit[tempSplit.length - 1];
		
		DataSource dataSource = (LIST_SQL_PATTERN.matcher(tempQueryId).find() || SELECT_SQL_PATTERN.matcher(tempQueryId).find()) ? DataSource.HSC_SLAVE : DataSource.HSC_MASTER;
		
		return execute(queryId, parameter, dataSource);
	}
	
	public Object execute(String queryId, Object parameter, DataSource dataSource) {
		if (queryId == null) {
			return null;
		}
		
		String[] tempSplit = queryId.split("\\.");
		String tempQueryId = tempSplit[tempSplit.length - 1];
		
		if (INSERT_SQL_PATTERN.matcher(tempQueryId).find()) {
			return insert(queryId, parameter, dataSource);
		}
		else if (UPDATE_SQL_PATTERN.matcher(tempQueryId).find()) {
			return update(queryId, parameter, dataSource);
		}
		else if (DELETE_SQL_PATTERN.matcher(tempQueryId).find()) {
			return delete(queryId, parameter, dataSource);
		}
		else if (LIST_SQL_PATTERN.matcher(tempQueryId).find()) {
			return selectList(queryId, parameter, dataSource);
		}
		else if (SELECT_SQL_PATTERN.matcher(tempQueryId).find()) {
			return selectOne(queryId, parameter, dataSource);
		}
		return null;
	}
	
	public <E> List<E> selectListToGeneric(String queryId) {
		return selectListToGeneric(queryId, null);
	}
	
	public <E> List<E> selectListToGeneric(String queryId, Object parameter) {
		return selectListToGeneric(queryId, parameter, DataSource.HSC_SLAVE);
	}
	
	public <E> List<E> selectListToGeneric(String queryId, Object parameter, DataSource dataSource) {
		if (!dataSource.isAbleRead()) {
			throw new RuntimeException(String.format("%s DB에서는 SELECT 작업을 할 수 없습니다.", dataSource.name()));
		}
		return getSqlSession(dataSource).selectList(queryId, parameter);
	}
	
	public <T> T selectOneToGeneric(String queryId) {
		return selectOneToGeneric(queryId, null);
	}
	
	public <T> T selectOneToGeneric(String queryId, Object parameter) {
		return selectOneToGeneric(queryId, parameter, DataSource.HSC_SLAVE);
	}
	
	public <T> T selectOneToGeneric(String queryId, Object parameter, DataSource dataSource) {
		if (!dataSource.isAbleRead()) {
			throw new RuntimeException(String.format("%s DB에서는 SELECT 작업을 할 수 없습니다.", dataSource.name()));
		}
		return getSqlSession(dataSource).selectOne(queryId, parameter);
	}
	
	public int selectCount(String queryId) {
		return selectCount(queryId, null);
	}
	
	public int selectCount(String queryId, Object parameter) {
		return selectCount(queryId, parameter, DataSource.HSC_SLAVE);
	}
	
	public int selectCountMaster(String queryId) {
		return selectCountMaster(queryId, null);
	}
	
	public int selectCountMaster(String queryId, Object parameter) {
		return selectCount(queryId, parameter, DataSource.HSC_MASTER);
	}
	
	public int selectCount(String queryId, Object parameter, DataSource dataSource) {
		if (!dataSource.isAbleRead()) {
			throw new RuntimeException(String.format("%s DB에서는 SELECT 작업을 할 수 없습니다.", dataSource.name()));
		}
		return getSqlSession(dataSource).selectOne(queryId, parameter);
	}
	
	public int insert(String queryId) {
		return insert(queryId, null);
	}
	
	public int insert(String queryId, Object parameter) {
		return insert(queryId, parameter, DataSource.HSC_MASTER);
	}
	
	public int insert(String queryId, Object parameter, DataSource dataSource) {
		if (!dataSource.isAbleCreate()) {
			throw new RuntimeException(String.format("%s DB에서는 INSERT 작업을 할 수 없습니다.", dataSource.name()));
		}
		return getSqlSession(dataSource).insert(queryId, parameter);
	}
	
	public int update(String queryId) throws Exception{
		return update(queryId, null);
	}
	
	public int update(String queryId, Object parameter) {
		return update(queryId, parameter, DataSource.HSC_MASTER);
	}
	
	public int update(String queryId, Object parameter, DataSource dataSource) {
		if (!dataSource.isAbleUpdate()) {
			throw new RuntimeException(String.format("%s DB에서는 UPDATE 작업을 할 수 없습니다.", dataSource.name()));
		}
		return getSqlSession(dataSource).update(queryId, parameter);
	}
	
	public int delete(String queryId) {
		return delete(queryId, null);
	}
	
	public int delete(String queryId, Object parameter) {
		return delete(queryId, parameter, DataSource.HSC_MASTER);
	}
	
	public int delete(String queryId, Object parameter, DataSource dataSource) {
		if (!dataSource.isAbleDelete()) {
			throw new RuntimeException(String.format("%s DB에서는 DELETE 작업을 할 수 없습니다.", dataSource.name()));
		}
		return getSqlSession(dataSource).delete(queryId, parameter);
	}
	
	public <T> T getSequence(String queryId) throws Exception{
		return selectOne(queryId, null, DataSource.HSC_MASTER);
	}
	
	public <T> T getSequenceItp(String queryId) throws Exception{
		return selectOne(queryId, null, DataSource.ITP_MASTER);
	}
	
	public int insertItp(String queryId, Object parameterObject) throws Exception{
		return insert(queryId, parameterObject, DataSource.ITP_MASTER);
	}
	
	public int updateItp(String queryId, Object parameterObject) throws Exception{
		return update(queryId, parameterObject, DataSource.ITP_MASTER);
	}
	
	public int deleteItp(String queryId, Object parameterObject) throws Exception{
		return delete(queryId, parameterObject, DataSource.ITP_MASTER);
	}	
	
	public <T> T getSequenceLms(String queryId) throws Exception{
		return selectOne(queryId, null, DataSource.LMS_MASTER);
	}
	
	public int insertLms(String queryId, Object parameterObject) throws Exception{
		return insert(queryId, parameterObject, DataSource.LMS_MASTER);
	}
	
	public int updateLms(String queryId, Object parameterObject) throws Exception{
		return update(queryId, parameterObject, DataSource.LMS_MASTER);
	}
	
	public int deleteLms(String queryId, Object parameterObject) throws Exception{
		return delete(queryId, parameterObject, DataSource.LMS_MASTER);
	}
	
	public <T> T getSequenceLog(String queryId) throws Exception{
		return selectOne(queryId, null, DataSource.HSC_LOG);
	}
	
	public int insertLog(String queryId, Object parameterObject) throws Exception{
		return insert(queryId, parameterObject, DataSource.HSC_LOG);
	}
	
	public int updateLog(String queryId, Object parameterObject) throws Exception{
		return update(queryId, parameterObject, DataSource.HSC_LOG);
	}
	
	public int deleteLog(String queryId, Object parameterObject) throws Exception{
		return delete(queryId, parameterObject, DataSource.HSC_LOG);
	}

}