package com.acme.mytrader.price;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import com.acme.mytrader.strategy.TradingStrategy;
import com.acme.mytrader.strategy.TradingType;


public class PriceListenerImpl implements PriceListener {
	
	private String security;
	private TreeMap<Double,  List<TradingStrategy>> buyMap = new TreeMap<>();
	private TreeMap<Double,  List<TradingStrategy>> sellMap = new TreeMap<>();
	
	
	
	public PriceListenerImpl(String security) {
		this.security = security;
	}
	
	public synchronized void addStrategy(TradingStrategy strategy) {
		List<TradingStrategy> stratsList = null;
		if (strategy.getType() == TradingType.BUY) {
			if (buyMap.containsKey(strategy.getPrice())) {
				stratsList = buyMap.get(strategy.getPrice());
			} else {
				stratsList = new LinkedList<TradingStrategy>();
				buyMap.put(strategy.getPrice(), stratsList);
			}
		} else {
			if (sellMap.containsKey(strategy.getPrice())) {
				stratsList = sellMap.get(strategy.getPrice());
			} else {
				stratsList = new LinkedList<TradingStrategy>();
				sellMap.put(strategy.getPrice(), stratsList);
			}
		}
		stratsList.add(strategy);
		
	}

	@Override
	public synchronized void   priceUpdate(String security, double price) {
		
		
		
		List<List<TradingStrategy>> buyItems = buyMap.entrySet().stream()
	    .filter(entry -> entry.getKey() >= price)
	    .map(Entry::getValue)
	    .collect(Collectors.toList());
		Set<Double> removeItems = new HashSet<>();
		for (List<TradingStrategy> items : buyItems) {
			for (TradingStrategy ts : items) {
				ts.execute(price);
				removeItems.add(ts.getPrice());
			}
		}
		for(Double pr : removeItems) {
			buyMap.remove(pr);
		}
		
		removeItems =  new HashSet<>(); 
		
		List<List<TradingStrategy>> sellItems = sellMap.entrySet().stream()
			    .filter(entry -> entry.getKey() <= price)
			    .map(Entry::getValue)
			    .collect(Collectors.toList());
		for (List<TradingStrategy> items : sellItems) {
			for (TradingStrategy ts : items) {
				ts.execute(price);
				removeItems.add(ts.getPrice());
			}
		}
		for(Double pr : removeItems) {
			sellMap.remove(pr);
		}
		
	}

	@Override
	public String getSecurity() {
		return this.security;
	}
	
	
	



}
