package com.acme.mytrader.price;

import java.util.HashMap;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.acme.mytrader.execution.ExecutionServiceImpl;

public class PriceSourceTest {

	
	@Test
	public void getListenerTest() {
		
		// A Price Source instance to connect to price Source and get stock price
    	PriceSourceImpl ps = new PriceSourceImpl();
    	
    	//Price Source has a map of listeners getting listener for one security
    	PriceListener ibmlistener = ps.getPriceListener("IBM");

    	Map<String, PriceListener> myList = ps.getListnerMap();
    	 Assert.assertTrue(myList.containsKey("IBM"));
    	 Assert.assertTrue(myList.containsValue(ibmlistener));
    	
    	 PriceListener ibmlistener2 = ps.getPriceListener("IBM");
    	Assert.assertEquals(ibmlistener,ibmlistener2);
    	
 
	}
@Test
	public void removeListenerTest() {
		
		// A Price Source instance to connect to price Source and get stock price
    	PriceSourceImpl ps = new PriceSourceImpl();
    	
    	//Price Source has a map of listeners getting listener for one security
    	PriceListener ibmlistener = ps.getPriceListener("IBM");

    	ps.removePriceListener(ibmlistener);
    	Map<String, PriceListener> myList = ps.getListnerMap();
   	 Assert.assertFalse(myList.containsKey("IBM"));
   	 Assert.assertFalse(myList.containsValue(ibmlistener));
	}
	
@Test
	public void addPriceListenerTest() {
	// A Price Source instance to connect to price Source and get stock price
	PriceSourceImpl ps = new PriceSourceImpl();
	
	//Price Source has a map of listeners getting listener for one security
	PriceListener ibmlistener = new PriceListenerImpl("IBM");

	ps.addPriceListener(ibmlistener);
	Map<String, PriceListener> myList = ps.getListnerMap();
	 Assert.assertTrue(myList.containsKey("IBM"));
	 Assert.assertTrue(myList.containsValue(ibmlistener));
		
	}
@Test
public void addMultiplelistenersTest() {
// A Price Source instance to connect to price Source and get stock price
PriceSourceImpl ps = new PriceSourceImpl();

//Price Source has a map of listeners getting listener for one security
PriceListener ibmlistener = new PriceListenerImpl("IBM");
PriceListener kolistener = new PriceListenerImpl("KO");

ps.addPriceListener(kolistener);
ps.addPriceListener(ibmlistener);
Map<String, PriceListener> myList = ps.getListnerMap();
 Assert.assertTrue(myList.containsKey("IBM"));
 Assert.assertTrue(myList.containsValue(ibmlistener));
 Assert.assertTrue(myList.containsKey("KO"));
 Assert.assertTrue(myList.containsValue(kolistener));
	
}
@Test
public void setListenersMapTest() {
// A Price Source instance to connect to price Source and get stock price
PriceSourceImpl ps = new PriceSourceImpl();

//Price Source has a map of listeners getting listener for one security
PriceListener ibmlistener = new PriceListenerImpl("IBM");
PriceListener kolistener = new PriceListenerImpl("KO");
Map<String, PriceListener> myList2 = new HashMap<String,PriceListener>();
myList2.put("IBM", ibmlistener);
myList2.put("KO", kolistener);
ps.setListnerMap(myList2);
Map<String, PriceListener> myList = ps.getListnerMap();
 Assert.assertTrue(myList.containsKey("IBM"));
 Assert.assertTrue(myList.containsValue(ibmlistener));
 Assert.assertTrue(myList.containsKey("KO"));
 Assert.assertTrue(myList.containsValue(kolistener));
}



}
