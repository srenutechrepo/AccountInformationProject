package com.tesco.offers.account.balance.dao.beans;

public class BalanceDAORequstBean {
	
	private String clientId;
	private String channelId;
	private String accountNumber;
	
	public String getClientId() {
		return clientId;
	}
	public void setClientId(String clientId) {
		this.clientId = clientId;
	}
	public String getChannelId() {
		return channelId;
	}
	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BalanceDAORequstBean [clientId=");
		builder.append(clientId);
		builder.append(", channelId=");
		builder.append(channelId);
		builder.append(", accountNumber=");
		builder.append(accountNumber);
		builder.append("]");
		return builder.toString();
	}
	

}
