package com.ebs.ngs.hsc.hsp.system.config.database;

import javax.sql.DataSource;
 
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.ebs.ngs.core.config.database.MybatisBaseConfig;

import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;

@Configuration
public class DataSourceConfig extends MybatisBaseConfig {
 
	@Value("${spring.hsc-master.datasource.jndi-name}")
	private String hscMasterJndiName;

	@Value("${spring.hsc-slave.datasource.jndi-name}")
	private String hscSlaveJndiName;

	@Value("${spring.itp-master.datasource.jndi-name}")
	private String itpMasterJndiName;

	@Value("${spring.lms-master.datasource.jndi-name}")
	private String lmsMasterJndiName;

	/* log db 접속 설정 */
	@Value("${spring.hsc-log.datasource.jndi-name}")
	private String hscLogJndiName;
	
	@Value("${spring.pri-slave.datasource.jndi-name}")
	private String priSlaveJndiName;
	
	@Value("${spring.jhs-slave.datasource.jndi-name}")
	private String jhsSlaveJndiName;
	
	@Value("${spring.anl-slave.datasource.jndi-name}")
	private String anlSlaveJndiName;
	
	@Value("${spring.mybatis.config-location:}")
	private String mybatisConfig;
	
	@Value("${spring.mybatis.mapper-location:}")
	private String mybatisMapperLocation;

	@Bean
	@Primary
	public DataSource hscMasterDataSource() {
		
		DataSource dataSource = null;
		
		if(hscMasterJndiName != null ) {
			// was에 선언한 jndi로 connection pool생성
			dataSource = (DataSource) new JndiDataSourceLookup().getDataSource(hscMasterJndiName);
		} else
			// hikari connection pool생성
			dataSource = DataSourceBuilder.create().build();		
 
		return dataSource;
	}

	@Bean
	public DataSource hscSlaveDataSource() {
		
		DataSource dataSource = null;
		
		if(hscSlaveJndiName != null ) {
			// was에 선언한 jndi로 connection pool생성
			dataSource = (DataSource) new JndiDataSourceLookup().getDataSource(hscSlaveJndiName);
		} else
			// hikari connection pool생성
			dataSource = DataSourceBuilder.create().build();		
 
		return dataSource;
	}

	@Bean
	public DataSource itpMasterDataSource() {
		
		DataSource dataSource = null;
		
		if(itpMasterJndiName != null ) {
			// was에 선언한 jndi로 connection pool생성
			dataSource = (DataSource) new JndiDataSourceLookup().getDataSource(itpMasterJndiName);
		} else
			// hikari connection pool생성
			dataSource = DataSourceBuilder.create().build();		
 
		return dataSource;
	}
	
	@Bean
	public DataSource lmsMasterDataSource() {

		DataSource dataSource = null;
		
		if(lmsMasterJndiName != null ) {
			// was에 선언한 jndi로 connection pool생성
			dataSource = (DataSource) new JndiDataSourceLookup().getDataSource(lmsMasterJndiName);
		} else
			// hikari connection pool생성
			dataSource = DataSourceBuilder.create().build();		
 
		return dataSource;
	}

	/**
	 * Log DB 접속을 위한 datasource 설정
	 * @return
	 */
	@Bean
	public DataSource hscLogDataSource() {

		DataSource dataSource = (DataSource) new JndiDataSourceLookup().getDataSource(hscLogJndiName);
		
		return dataSource;
	}
	
	@Bean
	public DataSource priSlaveDataSource() {
		DataSource dataSource = null;
		if(priSlaveJndiName != null ) {
			// was에 선언한 jndi로 connection pool생성
			dataSource = (DataSource) new JndiDataSourceLookup().getDataSource(priSlaveJndiName);
		} else {
			// hikari connection pool생성
			dataSource = DataSourceBuilder.create().build();
		}
		return dataSource;
	}
	
	@Bean
	public DataSource jhsSlaveDataSource() {
		DataSource dataSource = null;
		if(jhsSlaveJndiName != null ) {
			// was에 선언한 jndi로 connection pool생성
			dataSource = (DataSource) new JndiDataSourceLookup().getDataSource(jhsSlaveJndiName);
		} else {
			// hikari connection pool생성
			dataSource = DataSourceBuilder.create().build();
		}
		return dataSource;
	}
	
	@Bean
	public DataSource anlSlaveDataSource() {
		DataSource dataSource = null;
		if(anlSlaveJndiName != null ) {
			// was에 선언한 jndi로 connection pool생성
			dataSource = (DataSource) new JndiDataSourceLookup().getDataSource(anlSlaveJndiName);
		} else {
			// hikari connection pool생성
			dataSource = DataSourceBuilder.create().build();
		}
		return dataSource;
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory(@Autowired @Qualifier("hscMasterDataSource") DataSource dataSource) throws Exception {
		return createSqlSessionFactory(dataSource);
	}
	
	@Bean
	public SqlSession hscMasterSqlSession(@Autowired @Qualifier("sqlSessionFactory") SqlSessionFactory factory) {
		return new SqlSessionTemplate(factory);
	}


	@Bean
	public SqlSessionFactory hscSlaveSqlSessionFactory(@Autowired @Qualifier("hscSlaveDataSource") DataSource dataSource) throws Exception {
		return createSqlSessionFactory(dataSource);
	}
	
	@Bean
    public SqlSession hscSlaveSqlSession(@Autowired @Qualifier("hscSlaveSqlSessionFactory") SqlSessionFactory factory) {
        return new SqlSessionTemplate(factory);
    }

	
	@Bean
	public SqlSessionFactory itpMasterSqlSessionFactory(@Autowired @Qualifier("itpMasterDataSource") DataSource dataSource) throws Exception {
		return createSqlSessionFactory(dataSource);
	}
	
	@Bean
    public SqlSession itpMasterSqlSession(@Autowired @Qualifier("itpMasterSqlSessionFactory") SqlSessionFactory factory) {
        return new SqlSessionTemplate(factory);
    }
	
	@Bean
	public SqlSessionFactory lmsMasterSqlSessionFactory(@Autowired @Qualifier("lmsMasterDataSource") DataSource dataSource) throws Exception {
		return createSqlSessionFactory(dataSource);
	}
	
	@Bean
    public SqlSession lmsMasterSqlSession(@Autowired @Qualifier("lmsMasterSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }


	@Bean
	public SqlSessionFactory hscLogSqlSessionFactory(@Autowired @Qualifier("hscLogDataSource") DataSource dataSource) throws Exception {
		return createSqlSessionFactory(dataSource);
	}
	
	/**
	 * hscLog용 SQL Session 생성
	 * @param sqlSessionFactory
	 * @return
	 */
	@Bean
    public SqlSession hscLogSqlSession(@Autowired @Qualifier("hscLogSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
	
	@Bean
	public SqlSessionFactory priSlaveSqlSessionFactory(@Autowired @Qualifier("priSlaveDataSource") DataSource dataSource) throws Exception {
		return createSqlSessionFactory(dataSource);
	}
	
	@Bean
    public SqlSession priSlaveSqlSession(@Autowired @Qualifier("priSlaveSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
	
	@Bean
	public SqlSessionFactory jhsSlaveSqlSessionFactory(@Autowired @Qualifier("jhsSlaveDataSource") DataSource dataSource) throws Exception {
		return createSqlSessionFactory(dataSource);
	}
	
	@Bean
    public SqlSession jhsSlaveSqlSession(@Autowired @Qualifier("jhsSlaveSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
	
	@Bean
	public SqlSessionFactory anlSlaveSqlSessionFactory(@Autowired @Qualifier("anlSlaveDataSource") DataSource dataSource) throws Exception {
		return createSqlSessionFactory(dataSource);
	}
	
	@Bean
    public SqlSession anlSlaveSqlSession(@Autowired @Qualifier("anlSlaveSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
	
	private SqlSessionFactory createSqlSessionFactory(DataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(dataSource);
		
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sqlSessionFactoryBean.setMapperLocations(resolver.getResources(mybatisMapperLocation));
		sqlSessionFactoryBean.setConfigLocation(resolver.getResource(mybatisConfig));
		
		return sqlSessionFactoryBean.getObject();
	}
}

