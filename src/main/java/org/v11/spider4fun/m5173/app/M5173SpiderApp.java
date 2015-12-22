package org.v11.spider4fun.m5173.app;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.v11.spider4fun.core.utils.Log;
import org.v11.spider4fun.core.utils.TaskConfig;
import org.v11.spider4fun.m5173.task.M5173Task;

public class M5173SpiderApp {
	M5173Task task = new M5173Task("m5173","m5173_emails");
	static String url = "http://sy.5173.com/bizoffer/GoodsList?GameId=9ebdb1e338d64a45ac2a25d35324200d&GamePlatformId=40c9692aede64b379aa4da7577e62b3a&GameCateId=a87961b64a7d49ada686e29f180f36a8&GameAreaId=d47cfbcb11d6420aafdaf3b583d442b3&GameServerId=&Keyword=%u7F8A";
	static int Num = 300;
	public void jobDetail() throws InterruptedException{
		Log.info("初始化properties文件....");
		TaskConfig.init();
		Log.info("开始获取需要待抓取URL....");
		List<String> urls = new ArrayList<String>();
		for(int i=1;i<=Num;i++){
			String baseUrl = url + "&pageIndex="+i;
			urls.add(baseUrl);
		}
		task.work(urls);
	}
	public static void main(String[] args) throws InterruptedException {
		M5173SpiderApp app = new M5173SpiderApp();
		while(true){
			app.jobDetail();
			int TIME = TaskConfig.getNum("m5173_time_interval");
			Thread.sleep(1000*60*TIME);
		}
	}
}
