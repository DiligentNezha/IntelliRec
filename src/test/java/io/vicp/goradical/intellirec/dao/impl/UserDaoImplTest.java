package io.vicp.goradical.intellirec.dao.impl;

import io.vicp.goradical.intellirec.dao.UserDao;
import io.vicp.goradical.intellirec.model.pmrs.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Kenny on 2017/3/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserDaoImplTest extends AbstractTransactionalJUnit4SpringContextTests{
	private static final Logger LOG = LogManager.getLogger(UserDaoImplTest.class);

	@Autowired
	private UserDao userDao;

	@Test
	public void getEntity() throws Exception {
		User user = userDao.getEntity(1);
		LOG.info(user);
	}

}