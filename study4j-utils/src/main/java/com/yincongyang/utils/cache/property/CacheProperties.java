package com.yincongyang.utils.cache.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * cache 属性
 * 
 * @author yincongyang
 *
 */
@Component
@ConfigurationProperties(prefix = "cache")
public class CacheProperties {

	/**
	 * cache类型:redis,file,memory
	 */
	private String type;

	/**
	 * cache前缀
	 */
	private String prefix;

	/**
	 * 缓存文件地址
	 */
	private String file_path;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getFile_path() {
		return file_path;
	}

	public void setFile_path(String file_path) {
		this.file_path = file_path;
	}

	@Override
	public String toString() {
		return "CacheProperties [type=" + type + ", prefix=" + prefix + ", file_path=" + file_path + "]";
	}

}
