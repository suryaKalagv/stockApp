package com.acme.mytrader.execution;

public class ExecutionServiceImpl implements ExecutionService{
	
	private String security;
	private double price;
	private int volume ;
	
	@Override
	public void buy(String security, double price, int volume) {
		setSecurity( security);
		setPrice(price);
		setVolume(volume);
		System.out.println("Buying IBM at price " + getPrice() + " Volume " + getVolume());
		
	}

	@Override
	public void sell(String security, double price, int volume) {
		setSecurity( security);
		setPrice(price);
		setVolume(volume);
		System.out.println("Selling IBM at price " + getPrice() + " Volume " + getVolume());
		
	}

	public String getSecurity() {
		return security;
	}

	public void setSecurity(String security) {
		this.security = security;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		if(price>=0.0) 
			this.price = price;
		else 
			this.price = 0.0;
	}

	public int getVolume() {
		return volume;
	}

	public void setVolume(int volume) {
		if(volume>=0) 
			this.volume = volume;
		else 
			this.volume = 0;
	}

}
