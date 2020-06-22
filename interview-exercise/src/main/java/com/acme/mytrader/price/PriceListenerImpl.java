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
	private Set<Double> removeItems = new HashSet<>();
	
	 private double price;
	 
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
		setPrice(price);
		setRemoveItems(new HashSet<>());
		List<List<TradingStrategy>> buyItems = buyMap.entrySet().stream()
	    .filter(entry -> entry.getKey() >= price)
	    .map(Entry::getValue)
	    .collect(Collectors.toList());
		
		buyItems.stream().forEach(items->{
			items.forEach(ts->{
				ts.execute(price);
				this.removeItems.add(ts.getPrice());
			});
			});
		
		// remove buy testing strategies from buyMap
		for(Double pr : removeItems) {
			buyMap.remove(pr);
		}
		
		setRemoveItems(new HashSet<>());
		
		List<List<TradingStrategy>> sellItems = sellMap.entrySet().stream()
			    .filter(entry -> entry.getKey() <= price)
			    .map(Entry::getValue)
			    .collect(Collectors.toList());
		
		sellItems.stream().forEach(items->{
			items.forEach(ts->{
				ts.execute(price);
				this.removeItems.add(ts.getPrice());
			});
			});
		
		// remove sell testing strategies from buyMap
		for(Double pr : removeItems) {
			sellMap.remove(pr);
		}
		
	}

	@Override
	public String getSecurity() {
		return this.security;
	}

	public Set<Double> getRemoveItems() {
		return removeItems;
	}

	public void setRemoveItems(Set<Double> removeItems) {
		this.removeItems = removeItems;
	}

	public TreeMap<Double, List<TradingStrategy>> getBuyMap() {
		return buyMap;
	}

	public void setBuyMap(TreeMap<Double, List<TradingStrategy>> buyMap) {
		this.buyMap = buyMap;
	}

	public TreeMap<Double, List<TradingStrategy>> getSellMap() {
		return sellMap;
	}

	public void setSellMap(TreeMap<Double, List<TradingStrategy>> sellMap) {
		this.sellMap = sellMap;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public void setSecurity(String security) {
		this.security = security;
	}
	
	
	



}
