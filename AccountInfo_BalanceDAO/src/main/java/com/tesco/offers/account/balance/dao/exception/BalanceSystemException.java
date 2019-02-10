package com.tesco.offers.account.balance.dao.exception;

public class BalanceSystemException extends Exception {
	private String respCode;
	private String respMsg;

	
	public String getRespCode() {
		return respCode;
	}

	public String getRespMsg() {
		return respMsg;
	}

		
	public BalanceSystemException(String respCode, String respMsg) {
		this.respCode = respCode;
		this.respMsg = respMsg;
	}

}
