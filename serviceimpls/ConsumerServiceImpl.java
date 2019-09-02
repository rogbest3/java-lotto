package com.lotto.web.serviceimpls;

import com.lotto.web.daos.ConsumerDAO;
import com.lotto.web.domains.ConsumerBean;
import com.lotto.web.services.ConsumerService;
import com.lotto.web.daoimpls.ConsumerDAOImpl;
public class ConsumerServiceImpl implements ConsumerService{

	ConsumerDAO dao;
	
	public ConsumerServiceImpl() {
		dao = new ConsumerDAOImpl();
	}
	
	@Override
	public void registerConsumer(ConsumerBean param) {

		dao.insertConsumer(param);
	}
}
