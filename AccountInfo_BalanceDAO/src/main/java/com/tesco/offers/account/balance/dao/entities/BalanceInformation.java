package com.tesco.offers.account.balance.dao.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "balance_info")
public class BalanceInformation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column
	private String card_number;
	@Column
	private long balance;
	@Column
	private long credit_limit;
	@Column
	private long available_pts;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCard_number() {
		return card_number;
	}

	public void setCard_number(String card_number) {
		this.card_number = card_number;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public long getCredit_limit() {
		return credit_limit;
	}

	public void setCredit_limit(long credit_limit) {
		this.credit_limit = credit_limit;
	}

	public long getAvailable_pts() {
		return available_pts;
	}

	public void setAvailable_pts(long available_pts) {
		this.available_pts = available_pts;
	}

}
