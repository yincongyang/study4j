package com.yincongyang.utils.cache.property;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * redis 属性
 * 
 * @author yincongyang
 *
 */
@Component
@ConfigurationProperties(prefix = "redis")
public class RedisProperties {

	/**
	 * redis服务器地址
	 */
	private String host;

	/**
	 * Redis服务器连接端口
	 */
	private int port;

	/**
	 * 连接池最大阻塞等待时间
	 */
	private int max_total;

	/**
	 * 连接池最大阻塞等待时间
	 */
	private int max_wail_mills;

	/**
	 * 连接池中的最大空闲连接
	 */
	private int max_idle;

	/**
	 * 连接超时时间
	 */
	private int timeout;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public int getMax_total() {
		return max_total;
	}

	public void setMax_total(int max_total) {
		this.max_total = max_total;
	}

	public int getMax_wail_mills() {
		return max_wail_mills;
	}

	public void setMax_wail_mills(int max_wail_mills) {
		this.max_wail_mills = max_wail_mills;
	}

	public int getMax_idle() {
		return max_idle;
	}

	public void setMax_idle(int max_idle) {
		this.max_idle = max_idle;
	}

	public int getTimeout() {
		return timeout;
	}

	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	@Override
	public String toString() {
		return "RedisProperties [host=" + host + ", port=" + port + ", max_total=" + max_total + ", max_wail_mills="
				+ max_wail_mills + ", max_idle=" + max_idle + ", timeout=" + timeout + "]";
	}
}
