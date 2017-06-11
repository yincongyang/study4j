package com.yincongyang.utils.cache.core;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yincongyang.utils.cache.property.CacheProperties;
import com.yincongyang.utils.cache.storager.FileCacheStorager;
import com.yincongyang.utils.cache.storager.RedisCacheStorager;

/**
 * 缓存管理器
 * 根据配置项确定使用存储器类型
 * @author yincongyang
 *
 * @param <T>
 */
@Component
public class CacheManager<T extends Cacheable> {
	protected final CacheStorager<T> cacheStorager;
	private final ReentrantLock lock = new ReentrantLock();
	
	/**
	 * 存储器类型
	 */
	private String cacheType;
	
	/**
	 * 缓存前缀
	 */
	private String cachePrefix;
	

	@Autowired
	public CacheManager(CacheProperties cacheProperties,FileCacheStorager<T> fileCacheStorager,RedisCacheStorager<T> redisCacheStorager){
		System.out.println("CacheManager Construction running!");
		this.cachePrefix = cacheProperties.getPrefix();
		this.cacheType = cacheProperties.getType();
		if("redis".equalsIgnoreCase(cacheType)){
			this.cacheStorager = redisCacheStorager;
			return;
		}else if("file".equalsIgnoreCase(cacheType)){
			this.cacheStorager = fileCacheStorager;
			return;
		}
		this.cacheStorager = redisCacheStorager;
	}
	
	public CacheManager(CacheStorager<T> cacheStorager) {
		this.cacheStorager = cacheStorager;
	}

	/**
	 * 获取缓存对象
	 *
	 * @return 缓存对象
	 * @throws WeixinException
	 */
	public T getCache(String cacheKey) {
		T cache = cacheStorager.lookup(this.cachePrefix+cacheKey);
		return cache;
	}

	public void setCache(String cacheKey, T cache) throws Exception {
		try {
			if (cache != null && lock.tryLock(1, TimeUnit.SECONDS)) {
				try {
					cacheStorager.caching(this.cachePrefix+cacheKey, cache);
				} finally {
					lock.unlock();
				}
			}
		} catch (InterruptedException e) {
			throw new Exception("set cache error on lock", e);
		}
	}

	/**
	 * 移除缓存
	 *
	 * @return 被移除的缓存对象
	 */
	public T evictCache(String cacheKey) {
		return cacheStorager.evict(this.cachePrefix+cacheKey);
	}

	/**
	 * 清除所有的缓存(<font color="red">请慎重</font>)
	 */
	public void clearCache() {
		cacheStorager.clear();
	}
}
