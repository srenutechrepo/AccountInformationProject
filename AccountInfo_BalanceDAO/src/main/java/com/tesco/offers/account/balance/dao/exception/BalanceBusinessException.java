package com.tesco.offers.account.balance.dao.exception;

public class BalanceBusinessException extends Exception {
	private String respCode;
	private String respMsg;

	
	public String getRespCode() {
		return respCode;
	}

	public String getRespMsg() {
		return respMsg;
	}

		
	public BalanceBusinessException(String respCode, String respMsg) {
		this.respCode = respCode;
		this.respMsg = respMsg;
	}

}
