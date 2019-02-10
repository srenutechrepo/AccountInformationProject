package com.tesco.offers.account.balance.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.tesco.offers.account.balance.dao.BalanceDAO;
import com.tesco.offers.account.balance.dao.beans.BalanceDAORequstBean;
import com.tesco.offers.account.balance.dao.beans.BalanceDAOResponseBean;
import com.tesco.offers.account.balance.dao.exception.BalanceBusinessException;
import com.tesco.offers.account.balance.dao.exception.BalanceSystemException;

public class BalanceDAOImpl_JDBC implements BalanceDAO{

	
	@Override
	public BalanceDAOResponseBean getBalance(BalanceDAORequstBean balanceReq) throws BalanceBusinessException, BalanceSystemException, ClassNotFoundException, SQLException {
	    System.out.println("Entered into balancedao layer::"+balanceReq);
	    //get request from process layer
	    //prepare the request for db with the help of daoReq
	    //call the the db passing db req anmd get the resultset
	    //prepare the dao resp with the help of resultset
	    BalanceDAOResponseBean balanceRes=new BalanceDAOResponseBean();
	    
	    try {
	    	Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/rtp4203", "root", "root");
		    String sql = "{call GET_BALANCES(?,?,?,?,?)}";
		    
		    CallableStatement cs = connection.prepareCall(sql);

			cs.registerOutParameter(4, Types.VARCHAR);
			cs.registerOutParameter(5, Types.VARCHAR);

			cs.setString(1, balanceReq.getClientId());
			cs.setString(2, balanceReq.getChannelId());
			cs.setString(3, balanceReq.getAccountNumber());
							
			cs.execute();
			ResultSet rs = cs.executeQuery();
			String dbRespCode = cs.getString(4);
			String dbRespMsg = cs.getString(5);
			if ("0".equals(dbRespCode)) {
				System.out.println("entered into if");
				balanceRes.setRespCode(dbRespCode);
				balanceRes.setRespMsg(dbRespMsg);
				while( rs.next()){
			
				balanceRes.setBalanceAmt(Double.valueOf(rs.getString(3)));
				balanceRes.setAvailablePts(Long.valueOf(rs.getString(4)));
				balanceRes.setCreditLimit(Double.valueOf(rs.getString(5)));
				}
				
			} else if ("100".equals(dbRespCode) || "101".equals(dbRespCode) || "102".equals(dbRespCode)) {
				throw new BalanceBusinessException(dbRespCode, dbRespMsg);
			} else {
				throw new BalanceSystemException(dbRespCode, dbRespMsg);
			}
		 }
	    catch (BalanceBusinessException bbe) {
		 throw bbe;
		}
	    catch (BalanceSystemException bse) {
			throw bse;
			}
	    catch(Exception e){
	    	throw new BalanceSystemException("bal45","unknowerror from db"+e);
	    }
	   
	    //send dao resp to process layer
	    System.out.println("exit from balancedao:::"+balanceRes);
		return balanceRes;
	}
public static void main(String[] args) throws ClassNotFoundException, BalanceBusinessException, BalanceSystemException, SQLException {
		BalanceDAORequstBean balanceReq = new BalanceDAORequstBean();
		balanceReq.setAccountNumber("34567896756789");
		balanceReq.setClientId("web");
		balanceReq.setChannelId("online");
		BalanceDAOImpl_JDBC impl = new BalanceDAOImpl_JDBC();
		impl.getBalance(balanceReq);

	}
}
