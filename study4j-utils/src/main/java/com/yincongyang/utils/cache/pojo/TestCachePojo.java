package com.yincongyang.utils.cache.pojo;

import com.yincongyang.utils.cache.core.Cacheable;

/**
 * 测试缓存对象
 * @author yincongyang
 *
 */
public class TestCachePojo implements Cacheable{
	
	private static final long serialVersionUID = 1L;
	/**
	 * 凭证有效时间，单位：毫秒
	 */
	private long expires;
	/**
	 * 创建的时间,单位：毫秒
	 */
	private long createTime;
	
	private String str;
	
	
	/**
	 * 永不过期、创建时间为当前时间戳的token对象
	 *
	 * @param accessToken
	 *            凭证字符串
	 */
	public TestCachePojo(String str) {
		this(str, -1);
	}

	/**
	 * 有过期时间、创建时间为当前时间戳的token对象
	 *
	 * @param accessToken
	 *            凭证字符串
	 * @param expires
	 *            过期时间 单位毫秒
	 */
	public TestCachePojo(String str, long expires) {
		this(str, expires, System.currentTimeMillis());
	}
	
	/**
	 * 有过期时间、创建时间为当前时间戳的token对象
	 *
	 * @param accessToken
	 *            凭证字符串
	 * @param expires
	 *            过期时间 单位毫秒
	 */
	public TestCachePojo(String str, long expires,long createTime) {
		this.str = str;
		this.expires = expires;
		this.createTime =createTime;
	}

	public void setExpires(long expires) {
		this.expires = expires;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	@Override
	public long getExpires() {
		return this.expires;
	}

	@Override
	public long getCreateTime() {
		return this.createTime;
	}

	public String getStr() {
		return str;
	}

	public void setStr(String str) {
		this.str = str;
	}

	@Override
	public String toString() {
		return "TestCachePojo [expires=" + expires + ", createTime=" + createTime + ", str=" + str + "]";
	}
}
