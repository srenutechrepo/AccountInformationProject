package com.tesco.offers.account.balance.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;

import com.tesco.offers.account.balance.dao.BalanceDAO;
import com.tesco.offers.account.balance.dao.beans.BalanceDAORequstBean;
import com.tesco.offers.account.balance.dao.beans.BalanceDAOResponseBean;
import com.tesco.offers.account.balance.dao.entities.BalanceInformation;
import com.tesco.offers.account.balance.dao.entities.ChannelDetails;
import com.tesco.offers.account.balance.dao.entities.ClientDetails;
import com.tesco.offers.account.balance.dao.entities.CustomerDetails;
import com.tesco.offers.account.balance.dao.exception.BalanceBusinessException;
import com.tesco.offers.account.balance.dao.exception.BalanceSystemException;
import com.tesco.offers.account.balance.dao.util.HibernateUtility;

public class BalanceDAOImpl implements BalanceDAO {

	private SessionFactory sessionFactory = null;

	@Override
	public BalanceDAOResponseBean getBalance(BalanceDAORequstBean balanceReq)
			throws BalanceBusinessException, BalanceSystemException, ClassNotFoundException, SQLException {
		sessionFactory = HibernateUtility.getSessionFactory();
		Session session = sessionFactory.openSession();

		System.out.println("Entered into balancedao layer::" + balanceReq);
		// get request from process layer
		// prepare the request for db with the help of daoReq
		// call the the db passing db req anmd get the resultset
		// prepare the dao resp with the help of resultset
		BalanceDAOResponseBean balanceRes = new BalanceDAOResponseBean();
		try {
			Criteria criteria = session.createCriteria(ClientDetails.class);
			criteria.add(Restrictions.eq("clientId", balanceReq.getClientId()));
			List<ClientDetails> clientDetailsList = criteria.list();
			if (clientDetailsList == null || clientDetailsList.size() <= 0) {
				balanceRes.setRespCode("100");
				balanceRes.setRespMsg("Invalid clientId");
				throw new BalanceBusinessException(balanceRes.getRespCode(), balanceRes.getRespMsg());
			}

			// channel deatils
			criteria = session.createCriteria(ChannelDetails.class);
			criteria.add(Restrictions.eq("channelId", balanceReq.getChannelId()));
			List<ChannelDetails> channelDetailsList = criteria.list();
			if (channelDetailsList == null || channelDetailsList.size() <= 0) {
				balanceRes.setRespCode("200");
				balanceRes.setRespMsg("Invalid ChannelId");
				throw new BalanceBusinessException(balanceRes.getRespCode(), balanceRes.getRespMsg());
			}
			// customer details
			String hql = "from CustomerDetails cd where cd.card_num=:card_num";
			Query query = session.createQuery(hql);
			query.setParameter("card_num", balanceReq.getAccountNumber());
			List<CustomerDetails> customerDetailsList = query.list();
			if (customerDetailsList == null || customerDetailsList.size() <= 0) {
				balanceRes.setRespCode("300");
				balanceRes.setRespMsg("Invalid Account Number");
				throw new BalanceBusinessException(balanceRes.getRespCode(), balanceRes.getRespMsg());
			}
			// balance information
			hql = "from BalanceInformation bal where bal.card_number=:card_number";
			query = session.createQuery(hql);
			query.setParameter("card_number", balanceReq.getAccountNumber());
			List<BalanceInformation> balanceInformationsList = query.list();
			if (balanceInformationsList == null || balanceInformationsList.size() <= 0) {
				balanceRes.setRespCode("400");
				balanceRes.setRespMsg("No Balance information is available for given Account Number "
						+ balanceReq.getAccountNumber());
				throw new BalanceSystemException(balanceRes.getRespCode(), balanceRes.getRespMsg());
			} else {
				balanceRes.setRespCode("0");
				balanceRes.setRespMsg("SUCCESS");
				for (BalanceInformation balanceInformation : balanceInformationsList) {
					balanceRes.setBalanceAmt(Double.valueOf(balanceInformation.getBalance()));
					balanceRes.setAvailablePts(Long.valueOf(balanceInformation.getAvailable_pts()));
					balanceRes.setCreditLimit(Long.valueOf(balanceInformation.getCredit_limit()));
				}
			}
		} catch (BalanceBusinessException bbe) {
				throw bbe;
		} catch (BalanceSystemException bse) {
			throw bse;
		} catch (Exception e) {
			throw new BalanceSystemException("bal45", "unknowerror from db" + e);
		}

		session.close();
		HibernateUtility.shutdown();
		// send dao resp to process layer
		System.out.println("exit from balancedao:::" + balanceRes);
		return balanceRes;
	}

	public static void main(String[] args)
			throws ClassNotFoundException, BalanceBusinessException, BalanceSystemException, SQLException {
		BalanceDAORequstBean balanceReq = new BalanceDAORequstBean();
		balanceReq.setAccountNumber("34567896756789");
		balanceReq.setClientId("web");
		balanceReq.setChannelId("online");
		BalanceDAOImpl impl = new BalanceDAOImpl();
		BalanceDAOResponseBean daoResponseBean = impl.getBalance(balanceReq);
		System.out.println("daoResponseBean is " + daoResponseBean);

	}
}
