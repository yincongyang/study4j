package com.yincongyang.utils.cache.storager;

import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.http.Consts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yincongyang.utils.cache.core.CacheStorager;
import com.yincongyang.utils.cache.core.Cacheable;
import com.yincongyang.utils.cache.property.RedisProperties;
import com.yincongyang.utils.cache.utils.SerializationUtils;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;


/**
 * 用Redis保存缓存对象(推荐使用)
 * @author yincongyang
 *
 * @param <T>
 */
@Component
public class RedisCacheStorager<T extends Cacheable> implements
		CacheStorager<T> {

    private JedisPool jedisPool;
	private final static boolean TEST_ON_BORROW = false;
	private final static boolean TEST_ON_RETURN = true;

	@Autowired
	public RedisCacheStorager(RedisProperties cacheProperties) {
		System.out.println("RedisCacheStorager Construct...");
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxTotal(cacheProperties.getMax_total());
		jedisPoolConfig.setMaxIdle(cacheProperties.getMax_idle());
		jedisPoolConfig.setMaxWaitMillis(cacheProperties.getMax_wail_mills());
		jedisPoolConfig.setTestOnBorrow(TEST_ON_BORROW);
		jedisPoolConfig.setTestOnReturn(TEST_ON_RETURN);
		this.jedisPool = new JedisPool(jedisPoolConfig, cacheProperties.getHost(), cacheProperties.getPort(), cacheProperties.getTimeout());
		System.out.println(cacheProperties.toString());
	}

	public RedisCacheStorager(String host, int port, int timeout,
			JedisPoolConfig jedisPoolConfig) {
		this(new JedisPool(jedisPoolConfig, host, port, timeout));
	}

	public RedisCacheStorager(JedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T lookup(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			byte[] value = jedis.get(key.getBytes(Consts.UTF_8));
			return value != null ? (T) SerializationUtils.deserialize(value)
					: null;
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	@Override
	public void caching(String key, T cache) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			byte[] cacheKey = key.getBytes(Consts.UTF_8);
			byte[] value = SerializationUtils.serialize(cache);
			if (cache.getExpires() > 0) {
				jedis.setex(cacheKey,
						(int) (cache.getExpires() - CUTMS) / 1000, value);
			} else {
				jedis.set(cacheKey, value);
			}
			jedis.sadd(ALLKEY, key);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	@Override
	public T evict(String key) {
		T cache = lookup(key);
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			jedis.del(key);
			jedis.srem(ALLKEY, key);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
		return cache;
	}

	@Override
	public void clear() {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			Set<String> cacheKeys = jedis.smembers(ALLKEY);
			if (!cacheKeys.isEmpty()) {
				cacheKeys.add(ALLKEY);
				jedis.del(cacheKeys.toArray(new String[cacheKeys.size()]));
			}
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}

	/**
	 * 自增
	 * @param key
	 * @param v
	 * @return
	 */
	public long increment(String key, Long v) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.incrBy(key, v);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}
	
	/**
	 * 设置缓存有效期
	 * @param key
	 * @param duration
	 * @param unit
	 */
	public void expire(String key,final long duration, final TimeUnit unit) {
		Jedis jedis = null;
		try {
			final long rawTimeout = unit.toMillis(duration)/1000l;
			jedis = jedisPool.getResource();
			jedis.expire(key, (int) rawTimeout);
		} finally {
			if (jedis != null) {
				jedis.close();
			}
		}
	}
	
}