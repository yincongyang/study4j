package com.yincongyang.utils.cache.core;

/**
 * 缓存存储器
 * @author yincongyang
 *
 * @param <T>
 */
public interface CacheStorager<T extends Cacheable> {
	/**
	 * 考虑到临界情况,实际缓存的有效时间减去该毫秒数(60秒)
	 */
	long CUTMS = 60 * 1000L;

	/**
	 * 该key对应的value是全部的缓存key：便于清理缓存
	 */
	String ALLKEY = "all_cache_keys";

	/**
	 * 查找缓存中的对象
	 *
	 * @param key
	 *            缓存key
	 * @return 缓存对象
	 */
	T lookup(String key);

	/**
	 * 缓存新的对象
	 *
	 * @param key
	 *            缓存key
	 *
	 * @param cache
	 *            将要缓存的对象
	 */
	void caching(String key, T cache);

	/**
	 * 移除缓存对象
	 *
	 * @param key
	 *            缓存key
	 * @return 移除的对象
	 */
	T evict(String key);

	/**
	 * 清除所有缓存对象(<font color="red">请慎重</font>)
	 *
	 */
	void clear();
}
