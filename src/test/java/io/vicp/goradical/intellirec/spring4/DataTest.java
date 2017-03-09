package io.vicp.goradical.intellirec.spring4;

import io.vicp.goradical.intellirec.model.pmrs.CommentRecord;
import io.vicp.goradical.intellirec.service.CommentRecordService;
import io.vicp.goradical.intellirec.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Kenny on 2017/3/7.
 */
public class DataTest {
	private static final Logger LOG = LogManager.getLogger(DataTest.class);
	private ApplicationContext ac;
	private CommentRecordService crs;
	private UserService userService;

	@Before
	public void setUp() throws Exception {
		ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		crs = (CommentRecordService) ac.getBean("commentRecordService");
		userService = (UserService) ac.getBean("userService");
	}

	@Test
	public void commentRecordGet() throws Exception {
		CommentRecord entity = crs.getEntity(156);
		LOG.info(entity);
	}

}
