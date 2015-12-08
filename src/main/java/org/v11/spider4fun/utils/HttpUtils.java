package org.v11.spider4fun.utils;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.util.EntityUtils;

/**
 * httpclient 请求时，常用方法工具类
 * @author v11
 *
 */
public class HttpUtils {
	/**
	 * 附带浏览器header信息的HttpGet
	 * @param url 请求地址
	 * @return
	 */
	public HttpGet generateHttpGet(String url){
		HttpGet httpGet = new HttpGet(url);
		httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; rv:16.0) Gecko/20100101 Firefox/16.0");
		httpGet.setHeader("Content-Type", "application/x-www-form-urlencoded");
		return httpGet;
	}
	/**
	 * 附带浏览器header信息的HttpPost
	 * @param url 请求地址
	 * @return
	 */
	public HttpPost generateHttpPost(String url){
		HttpPost httpPost = new HttpPost(url);
		httpPost.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; rv:16.0) Gecko/20100101 Firefox/16.0");
		httpPost.setHeader("Content-Type", "application/x-www-form-urlencoded");
		return httpPost;
	}
	/**
	 * 根据HttpResponse返回网页源码
	 * @param res 
	 * @return 网页源码
	 */
	public String getResponseBody(HttpResponse res){
		HttpEntity entity = res.getEntity();
		if(entity != null){
			String content;
			try {
				content = EntityUtils.toString(entity,"UTF-8");
				return content;
			} catch (ParseException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * 释放HttpResponse资源
	 * @param res
	 */
	public void release(HttpResponse res){
		try {
			EntityUtils.consume(res.getEntity());
		} catch (IOException e) {
			Log.error(e.toString());
		}
	}
}
