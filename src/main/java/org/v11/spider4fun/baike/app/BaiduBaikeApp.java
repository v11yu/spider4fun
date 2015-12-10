package org.v11.spider4fun.baike.app;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.v11.spider4fun.baike.task.BaiduBaiKeSpiderTask;
import org.v11.spider4fun.baike.utils.BaikeConfig;
import org.v11.spider4fun.core.utils.Log;
import org.v11.spider4fun.core.utils.TaskConfig;
import org.v11.spider4fun.jiaoyimao.app.JiaoYiMaoApp;
import org.v11.spider4fun.jiaoyimao.utils.JiaoYiMaoConfig;

public class BaiduBaikeApp {
	BaiduBaiKeSpiderTask task = new BaiduBaiKeSpiderTask();
	public void jobDetail() throws InterruptedException{
		Log.info("初始化properties文件....");
		BaikeConfig.init();
		Log.info("开始获取需要待抓取URL....");
		List<String> urls = new ArrayList<String>();
		String keywords = BaikeConfig.getValue("keywords");
		for(String word : keywords.split(",")){
			urls.add(word);
		}
		System.out.println(urls);
		task.work(urls);
	}
	public static void main(String[] args) throws InterruptedException {
		BaiduBaikeApp app = new BaiduBaikeApp();
		app.jobDetail();
		
	}
}
