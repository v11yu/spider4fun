package org.v11.spider4fun.core.rule;

import java.util.ArrayList;

import org.v11.spider4fun.core.utils.TaskConfig;

public class RuleChecker extends BasicRules{
	public ArrayList<BasicRules> rules = new ArrayList<BasicRules>();
	
	public RuleChecker(){
		/*
		 * need add the new rule here!
		 */
		this(new LowPriceRule());
	}
	public RuleChecker(BasicRules ... ru){
		for(BasicRules rule:ru) rules.add(rule);
	}
	@Override
	public boolean checkRule(String itemName, Double price) {
		//first rule, if contain this keys,return true
		for(String rule:TaskConfig.getValue("priority_keys").split(",")){
			if(itemName.contains(rule)) return true;
		}
		//then,check the other rule
		for(BasicRules rule :rules) {
			if(!rule.checkRule(itemName, price)) return false;
		}
		return true;
	}
	
}
