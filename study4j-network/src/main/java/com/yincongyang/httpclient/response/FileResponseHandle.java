package com.yincongyang.httpclient.response;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Lists;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * 单例模式
 *
 * 返回报文处理器 处理文件类返回报文
 * 
 * Created by yincongyang on 17/9/28.
 */
public class FileResponseHandle implements ResponseHandler<List<String>> {
	private final static Logger logger = LoggerFactory.getLogger(FileResponseHandle.class);

	private static FileResponseHandle instance = new FileResponseHandle();

	public static FileResponseHandle getInstance() {
		return instance;
	}

	private FileResponseHandle() {
	}

    /**
     * 读取文件流，并将文件中每行内容读成String，返回List<String>
     * @param response
     * @return
     * @throws IOException
     */
	@Override
	public List<String> handleResponse(HttpResponse response) throws IOException {
		int status = response.getStatusLine().getStatusCode();
		if (status >= 200 && status < 300) {
			HttpEntity entity = response.getEntity();
			InputStream in = entity.getContent();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));

			List<String> returnArr = Lists.newArrayList();
			String line;
			while ((line = reader.readLine()) != null) {
				returnArr.add(line);
				logger.info(line);
			}

			return returnArr;

		} else {
			logger.error("Unexpected response status: " + status);
			throw new ClientProtocolException("Unexpected response status: " + status);
		}

	}
}
