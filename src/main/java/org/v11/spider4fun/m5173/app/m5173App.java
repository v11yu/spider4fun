package org.v11.spider4fun.m5173.app;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.v11.spider4fun.core.utils.Log;
import org.v11.spider4fun.core.utils.TaskConfig;
import org.v11.spider4fun.jiaoyimao.app.JiaoYiMaoApp;
import org.v11.spider4fun.jiaoyimao.task.JiaoYiMaoSpiderTask;
import org.v11.spider4fun.jiaoyimao.utils.JiaoYiMaoConfig;
import org.v11.spider4fun.m5173.task.m5173SpiderTask;

public class m5173App {
	m5173SpiderTask task = new m5173SpiderTask();
	public void jobDetail() throws InterruptedException{
		Log.info("初始化properties文件....");
		TaskConfig.init();
		Log.info("开始获取需要待抓取URL....");
		task.work();
	}
	public static void main(String[] args) throws InterruptedException {
		m5173App app = new m5173App();
		while(true){
			app.jobDetail();
			int TIME = TaskConfig.getNum("m5173_time_interval");
			Thread.sleep(1000*60*TIME);
		}
	}
}
