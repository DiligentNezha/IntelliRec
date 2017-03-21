package io.vicp.goradical.intellirec.service.impl;

import io.vicp.goradical.intellirec.model.pmrs.User;
import io.vicp.goradical.intellirec.model.pmrs.vo.TableVo;
import io.vicp.goradical.intellirec.model.pmrs.vo.UserVo;
import io.vicp.goradical.intellirec.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Kenny on 2017/3/13.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class UserServiceImplTest {
	private static final Logger LOG = LogManager.getLogger(UserServiceImplTest.class);

	@Autowired
	private UserService userService;

	@Test
	public void login() throws Exception {
		String email = "880000001@qq.com";
		String password = "root";
		UserVo userVo = userService.login(email, password);
		LOG.info(userVo);
	}

	@Test
	public void tableData() throws Exception {
		UserVo userVo = new UserVo();
		userVo.setUserName("优酷用户");
		userVo.setEmail("88000000");
		TableVo<UserVo> userVoTableVo = userService.tableData(userVo);
		for (UserVo vo : userVoTableVo.getRows()) {
			LOG.info(vo);
		}
	}

	@Test
	public void getEntity() throws Exception {
		User user = userService.getEntity(1);
		LOG.info(user);
	}

	@Test
	public void updateUser() throws Exception {
		UserVo userVo = new UserVo();
		userVo.setId(1);
		userVo.setUserName("优酷用户1468567979014259update");
		userService.updateUser(userVo);
	}

}