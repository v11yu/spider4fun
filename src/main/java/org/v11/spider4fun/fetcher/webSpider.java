package org.v11.spider4fun.fetcher;

import java.util.Map;

/**
 * 网页内容抓取器
 * @author v11
 *
 */
public interface webSpider {
	/**
	 * GET Method 获取html源码
	 * @param url web地址
	 * @return 网页源码
	 */
	public String getRequest(String url);
	/**
	 * POST Method 获取html源码
	 * @param url web地址
	 * @param param 请求字段
	 * @return 网页源码
	 */
	public String postRequest(String url,Map<String, String> param);
	
}
