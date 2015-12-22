package org.v11.spider4fun.core.rule;

import java.util.ArrayList;

public class RuleChecker extends BasicRules{
	public ArrayList<BasicRules> rules = new ArrayList<BasicRules>();
	
	public RuleChecker(){
		this(new LowPriceRule());
	}
	public RuleChecker(BasicRules ... ru){
		for(BasicRules rule:ru) rules.add(rule);
	}
	@Override
	public boolean checkRule(String itemName, Double price) {
		for(BasicRules rule :rules) {
			if(!rule.checkRule(itemName, price)) return false;
		}
		return true;
	}
	
}
