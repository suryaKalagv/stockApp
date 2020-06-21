package com.acme.mytrader.execution;

public class ExecutionServiceImpl implements ExecutionService{

	@Override
	public void buy(String security, double price, int volume) {
		System.out.println("Buying IBM at price " + price + " Volume " + volume);
		
	}

	@Override
	public void sell(String security, double price, int volume) {
		System.out.println("Selling IBM at price " + price + " Volume " + volume);
		
	}

}
