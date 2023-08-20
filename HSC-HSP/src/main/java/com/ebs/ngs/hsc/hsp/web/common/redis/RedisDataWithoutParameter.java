package com.ebs.ngs.hsc.hsp.web.common.redis;

import java.util.function.Supplier;

public class RedisDataWithoutParameter<T> implements RedisData<T> {
	
	private Supplier<T> getData;
	
	public RedisDataWithoutParameter(Supplier<T> getData) {
		this.getData = getData;
	}

	@Override
	public T get() {
		return getData.get();
	}

}
