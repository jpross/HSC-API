package com.ebs.ngs.hsc.hsp.web.common.redis;

import java.util.function.Function;

public class RedisDataWithParameter<T, PT> implements RedisData<T> {
	
	private PT params;
	
	private Function<PT, T> getData;
	
	public RedisDataWithParameter(PT params, Function<PT, T> getData) {
		this.params = params;
		this.getData = getData;
	}

	@Override
	public T get() {
		return getData.apply(params);
	}

}