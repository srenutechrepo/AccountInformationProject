package com.tesco.offers.account.balance.dao;

import java.sql.SQLException;

import com.tesco.offers.account.balance.dao.beans.BalanceDAORequstBean;
import com.tesco.offers.account.balance.dao.beans.BalanceDAOResponseBean;
import com.tesco.offers.account.balance.dao.exception.BalanceBusinessException;
import com.tesco.offers.account.balance.dao.exception.BalanceSystemException;

public interface BalanceDAO {
	public BalanceDAOResponseBean getBalance(BalanceDAORequstBean balanceReq) throws BalanceBusinessException, BalanceSystemException, ClassNotFoundException, SQLException ;
}
