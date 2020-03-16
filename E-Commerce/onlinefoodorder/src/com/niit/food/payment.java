package com.niit.food;

public class payment {
	
	private boolean status ;
	private int cash;
	private String card_no;
	private String online_wallet;
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	} 
	public int getCash() {
		return cash;
	}
	public void setCash(int cash) {
		this.cash = cash;
	}
	public String getCard_no() {
		return card_no;
	}
	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}
	public String getOnline_wallet() {
		return online_wallet;
	}
	public void setOnline_wallet(String online_wallet) {
		this.online_wallet = online_wallet;
	}

}
