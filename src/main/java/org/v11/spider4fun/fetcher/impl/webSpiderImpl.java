package org.v11.spider4fun.fetcher.impl;

import java.io.IOException;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.impl.client.DefaultHttpClient;
import org.v11.spider4fun.fetcher.webSpider;
import org.v11.spider4fun.utils.HttpUtils;
import org.v11.spider4fun.utils.Log;

public class webSpiderImpl implements webSpider{
	HttpClient client;
	HttpUtils hutils = new HttpUtils();
	public webSpiderImpl() {
		// TODO Auto-generated constructor stub
		this.client = new DefaultHttpClient();
	}
	/**
	 * 用于传入已有登陆信息的client
	 * @param client
	 */
	public webSpiderImpl(HttpClient client) {
		// TODO Auto-generated constructor stub
		this.client = client;
	}
	@Override
	public String getRequest(String url) {
		// TODO Auto-generated method stub
		HttpGet get = hutils.generateHttpGet(url);
		try {
			HttpResponse res = client.execute(get);
			String text = hutils.getResponseBody(res);
			Log.debug(text);
			hutils.release(res);
			return text;
		} catch (IOException e) {
			Log.error("解析出错"+e);
		}
		return null;
	}

	@Override
	public String postRequest(String url, Map<String, String> param) {
		// TODO Auto-generated method stub
		return null;
	}

}
