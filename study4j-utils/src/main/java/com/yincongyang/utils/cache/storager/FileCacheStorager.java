package com.yincongyang.utils.cache.storager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yincongyang.utils.cache.core.CacheStorager;
import com.yincongyang.utils.cache.core.Cacheable;
import com.yincongyang.utils.cache.property.CacheProperties;
import com.yincongyang.utils.cache.utils.SerializationUtils;

/**
 * 用File保存缓存对象
 * @author yincongyang
 *
 * @param <T>
 */
@Component
public class FileCacheStorager<T extends Cacheable> implements CacheStorager<T> {

	private final static Logger LOGGER = LoggerFactory
			.getLogger(FileCacheStorager.class);

	private final File tmpdir;
	private final String SEPARATOR = File.separator;
	
	@Autowired
	public FileCacheStorager(CacheProperties cacheProperties) {
		System.out.println("FileCacheStorager Construction running!");
		this.tmpdir = new File(String.format("%s%s%s", cacheProperties.getFile_path(), SEPARATOR, ALLKEY));
		this.tmpdir.mkdirs();
	}

	public FileCacheStorager(String path) {
		this.tmpdir = new File(String.format("%s%s%s", path, SEPARATOR, ALLKEY));
		this.tmpdir.mkdirs();
	}

	@Override
	public T lookup(String key) {
		File cacheFile = new File(String.format("%s%s%s",
				tmpdir.getAbsolutePath(), SEPARATOR, key));
		try {
			if (cacheFile.exists()) {
				T cache = SerializationUtils.deserialize(new FileInputStream(
						cacheFile));
				if (cache.getCreateTime() < 0) {
					return cache;
				}
				if ((cache.getCreateTime() + cache.getExpires() - CUTMS) > System
						.currentTimeMillis()) {
					return cache;
				}
			}
			return null;
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void caching(String key, T cache) {
		if (cache == null) {
			return;
		}
		try {
			SerializationUtils.serialize(
					cache,
					new FileOutputStream(new File(String.format("%s%s%s",
							tmpdir.getAbsolutePath(), SEPARATOR, key))));
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public T evict(String key) {
		T cache = null;
		File cacheFile = new File(String.format("%s%s%s",
				tmpdir.getAbsolutePath(), SEPARATOR, key));
		try {
			if (cacheFile.exists()) {
				cache = SerializationUtils.deserialize(new FileInputStream(
						cacheFile));
				cacheFile.delete();
			}
		} catch (IOException e) {
			LOGGER.error(e.getMessage(), e);

			; // ingore
		}
		return cache;
	}

	@Override
	public void clear() {
		if (tmpdir != null) {
			for (File cache : tmpdir.listFiles()) {
				cache.delete();
			}
		}
	}
}
