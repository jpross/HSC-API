package com.ebs.ngs.hsc.hsp.web.common.mapper;

import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class DataBaseFactory {
	
	public enum DataSource {
		HSC_MASTER(true, true, true, true), 
		HSC_SLAVE(true, false, false, false),
		ITP_MASTER(false, true, true, true),
		LMS_MASTER(false, true, true, true),
		HSC_LOG(true, true, true, true),
		PRI_SLAVE(true, false, false, false),
		JHS_SLAVE(true, false, false, false),
		ANL_SLAVE(true, false, false, false);

		private boolean _isAbleRead;
		
		private boolean _isAbleCreate;
		
		private boolean _isAbleUpdate;
		
		private boolean _isAbleDelete;
		
		DataSource(boolean isAbleRead, boolean isAbleCreate, boolean isAbleUpdate, boolean isAbleDelete) {
			this._isAbleRead = isAbleRead;
			this._isAbleCreate = isAbleCreate;
			this._isAbleUpdate = isAbleUpdate;
			this._isAbleDelete = isAbleDelete;
		}
		
		public boolean isAbleRead() {
			return _isAbleRead;
		}
		
		public boolean isAbleCreate() {
			return _isAbleCreate;
		}
		
		public boolean isAbleUpdate() {
			return _isAbleUpdate;
		}
		
		public boolean isAbleDelete() {
			return _isAbleDelete;
		}
	};
	
	public final Map<DataSource, SqlSession> DATABASE_CONTAINER = new HashMap<>();
	
	@Autowired @Qualifier("hscMasterSqlSession")
	private final SqlSession hscMasterSqlSession;
	
	@Autowired @Qualifier("hscSlaveSqlSession")
	private final SqlSession hscSlaveSqlSession;
	
	@Autowired @Qualifier("itpMasterSqlSession")
	private final SqlSession itpMasterSqlSession;
	
	@Autowired @Qualifier("lmsMasterSqlSession")
	private final SqlSession lmsMasterSqlSession;
	
	@Autowired @Qualifier("hscLogSqlSession")
	private final SqlSession hscLogSqlSession;
	
	@Autowired @Qualifier("priSlaveSqlSession")
	private final SqlSession priSlaveSqlSession;
	
	@Autowired @Qualifier("jhsSlaveSqlSession")
	private final SqlSession jhsSlaveSqlSession;
	
	@Autowired @Qualifier("anlSlaveSqlSession")
	private final SqlSession anlSlaveSqlSession;
	
	private SqlSession getSqlSession(DataSource dataSource) {
		if (DataSource.HSC_SLAVE.equals(dataSource)) { return hscSlaveSqlSession; }
		else if (DataSource.ITP_MASTER.equals(dataSource)) { return itpMasterSqlSession; }
		else if (DataSource.LMS_MASTER.equals(dataSource)) { return lmsMasterSqlSession; }
		else if (DataSource.HSC_LOG.equals(dataSource)) { return hscLogSqlSession; }
		else if (DataSource.PRI_SLAVE.equals(dataSource)) { return priSlaveSqlSession; }
		else if (DataSource.JHS_SLAVE.equals(dataSource)) { return jhsSlaveSqlSession; }
		else if (DataSource.ANL_SLAVE.equals(dataSource)) { return anlSlaveSqlSession; }
		return hscMasterSqlSession;
	}
	
	public SqlSession getDataBase(DataSource dataSource) {
		SqlSession sqlSession = null;
		if (DATABASE_CONTAINER.containsKey(dataSource)) {
			sqlSession = DATABASE_CONTAINER.get(dataSource);
		}
		else {
			sqlSession = getSqlSession(dataSource);
			DATABASE_CONTAINER.put(dataSource, sqlSession);
		}
		return sqlSession;
	}

}
