package io.vicp.goradical.intellirec.dao.impl;

import io.vicp.goradical.intellirec.dao.VideoDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Kenny on 2017/3/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class VideoDaoImplTest extends AbstractTransactionalJUnit4SpringContextTests{
	private static final Logger LOG = LogManager.getLogger(VideoDaoImplTest.class);

	@Autowired
	private VideoDao videoDao;

}