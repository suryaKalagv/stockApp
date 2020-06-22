package com.acme.mytrader.price;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class PriceSourceImpl implements PriceSource {
 
  
  Map<String, PriceListener> listnerMap = new ConcurrentHashMap<String, PriceListener>();

	@Override
	public void addPriceListener(PriceListener listener) {
		
		this.listnerMap.put(listener.getSecurity(), listener);

	}

	@Override
	public void removePriceListener(PriceListener listener) {
		
		
		this.listnerMap.remove(listener.getSecurity());

	}
	
	public void priceUpdateFromStocks(double price, String security) {
		System.out.println("\n  Price updated for security " + security + " as " + price) ;
		if(listnerMap.containsKey(security)) {
			listnerMap.get(security).priceUpdate(security, price);
		}
	}
	
	
	public PriceListener getPriceListener(String security) {
		if (listnerMap.containsKey(security)) {
			return listnerMap.get(security);
		}
		PriceListener pl = new PriceListenerImpl(security);
		listnerMap.put(security, pl);
		return pl;
	}

	public Map<String, PriceListener> getListnerMap() {
		return listnerMap;
	}

	public void setListnerMap(Map<String, PriceListener> listnerMap) {
		this.listnerMap = listnerMap;
	}


}
