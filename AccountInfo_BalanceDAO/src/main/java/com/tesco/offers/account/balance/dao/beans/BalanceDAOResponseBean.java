package com.tesco.offers.account.balance.dao.beans;

public class BalanceDAOResponseBean {
	
	private long availablePts;
	private double creditLimit;
	private double balanceAmt;
	private String respCode;
	private String respMsg;
	public long getAvailablePts() {
		return availablePts;
	}
	public void setAvailablePts(long availablePts) {
		this.availablePts = availablePts;
	}
	public double getCreditLimit() {
		return creditLimit;
	}
	public void setCreditLimit(double creditLimit) {
		this.creditLimit = creditLimit;
	}
	public double getBalanceAmt() {
		return balanceAmt;
	}
	public void setBalanceAmt(double balanceAmt) {
		this.balanceAmt = balanceAmt;
	}
	public String getRespCode() {
		return respCode;
	}
	public void setRespCode(String respCode) {
		this.respCode = respCode;
	}
	public String getRespMsg() {
		return respMsg;
	}
	public void setRespMsg(String respMsg) {
		this.respMsg = respMsg;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BalanceDAOResponseBean [availablePts=");
		builder.append(availablePts);
		builder.append(", creditLimit=");
		builder.append(creditLimit);
		builder.append(", balanceAmt=");
		builder.append(balanceAmt);
		builder.append(", respCode=");
		builder.append(respCode);
		builder.append(", respMsg=");
		builder.append(respMsg);
		builder.append("]");
		return builder.toString();
	}
	
	

}
