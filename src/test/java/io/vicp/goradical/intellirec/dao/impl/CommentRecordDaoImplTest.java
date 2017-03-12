package io.vicp.goradical.intellirec.dao.impl;

import io.vicp.goradical.intellirec.dao.CommentRecordDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;

/**
 * Created by Kenny on 2017/3/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CommentRecordDaoImplTest extends AbstractTransactionalJUnit4SpringContextTests {
	private static final Logger LOG = LogManager.getLogger(CommentRecordDaoImplTest.class);

	@Autowired
	private CommentRecordDao crd;

	@Test
	public void countTotalPlay() throws Exception {
		for (int i = 1; i < 3000 ; i++) {
			long count = crd.countTotalCommentWithVideoId(i);
			LOG.info(count);
		}
	}

	@Test
	public void getUserIdsWithVideoId() throws Exception {
		Set userIdsWithVideoId = crd.getUserIdsWithVideoId(1);
		LOG.info(userIdsWithVideoId.size());
	}

	@Test
	public void getCommentRecordList() throws Exception {
		List crl = crd.getCommentRecordList(1);
		for (int i = 0; i < crl.size(); i++) {
			Object[] record = (Object[]) crl.get(i);
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < record.length; j++) {
				sb.append(record[j] + " ");
			}
			LOG.info(sb);
		}
	}

	@Test
	public void getCommentRecordList1() throws Exception {
		List crl = crd.getCommentRecordList(new SimpleDateFormat("yyyy-MM-dd").parse("2015-06-22"));
		for (int i = 0; i < crl.size(); i++) {
			Object[] record = (Object[]) crl.get(i);
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < record.length; j++) {
				sb.append(record[j] + " ");
			}
			LOG.info(sb);
		}
	}
}