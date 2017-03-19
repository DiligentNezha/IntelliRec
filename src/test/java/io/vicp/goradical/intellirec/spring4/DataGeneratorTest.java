package io.vicp.goradical.intellirec.spring4;

import io.vicp.goradical.intellirec.dao.CommentRecordDao;
import io.vicp.goradical.intellirec.dao.VideoDao;
import io.vicp.goradical.intellirec.model.pmrs.Video;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.sql.Timestamp;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * Created by Kenny on 2017/3/10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DataGeneratorTest extends AbstractTransactionalJUnit4SpringContextTests{
	private static final Logger LOG = LogManager.getLogger(DataGeneratorTest.class);
	@Autowired
	private CommentRecordDao crd;

	@Autowired
	private VideoDao vd;

	private OutputStreamWriter oswPlay;
	private OutputStreamWriter oswPraise;
	private OutputStreamWriter oswMark;
	private OutputStreamWriter oswRecommend;
	private OutputStreamWriter oswCollect;

	@Ignore
	@Before
	public void setUp() throws Exception {
		oswPlay = new OutputStreamWriter(new FileOutputStream("E:/sql/play_record.sql"));
		oswPlay.write("SET FOREIGN_KEY_CHECKS=0;\n");
		oswPraise = new OutputStreamWriter(new FileOutputStream("E:/sql/praise_record.sql"));
		oswPraise.write("SET FOREIGN_KEY_CHECKS=0;\n");
		oswMark = new OutputStreamWriter(new FileOutputStream("E:/sql/mark_record.sql"));
		oswMark.write("SET FOREIGN_KEY_CHECKS=0;\n");
		oswRecommend = new OutputStreamWriter(new FileOutputStream("E:/sql/recommend_record.sql"));
		oswRecommend.write("SET FOREIGN_KEY_CHECKS=0;\n");
		oswCollect = new OutputStreamWriter(new FileOutputStream("E:/sql/collect_record.sql"));
		oswCollect.write("SET FOREIGN_KEY_CHECKS=0;\n");
	}

	@Ignore
	@After
	public void tearDown() throws Exception {
		oswPlay.write("SET FOREIGN_KEY_CHECKS=1;");
		oswPlay.flush();
		oswPlay.close();

		oswPraise.write("SET FOREIGN_KEY_CHECKS=1;");
		oswPraise.flush();
		oswPraise.close();

		oswMark.write("SET FOREIGN_KEY_CHECKS=1;");
		oswMark.flush();
		oswMark.close();

		oswRecommend.write("SET FOREIGN_KEY_CHECKS=1;");
		oswRecommend.flush();
		oswRecommend.close();

		oswCollect.write("SET FOREIGN_KEY_CHECKS=1;");
		oswCollect.flush();
		oswCollect.close();
	}

	@Ignore
	@Test
	public void dataGenerator() throws Exception {
		int end = 3275;
		for (int i = 1; i <= end; i++) {
			Set<Integer> userIds = crd.getUserIdsWithVideoId(i);
			Video video = vd.getEntity(i);
			List crl = crd.getCommentRecordList(video.getPublished());
			//播放记录
			playRecordGenerator(i, userIds, crl);
			//收藏记录
			collectRecordGenerator(i, userIds, crl);
			//评分记录
			markRecordGenerator(i, userIds, crl);
			//点赞记录
			praiseRecordGenerator(i, userIds, crl);
			//用户推荐
			userRecommendRecordGenerator(i, userIds, crl);
		}
	}

	public void userRecommendRecordGenerator(int videoId, Set<Integer> userIds, List crl) throws Exception {
		int targetDataAmount = (int) (userIds.size() * new Random().nextDouble() * 0.4) + 1;
		int step = crl.size() / targetDataAmount;
		Random rd = new Random();
		for (int j = 0; j < crl.size(); j = j + step) {
			Object[] record = (Object[]) crl.get(j);
			StringBuilder sb = new StringBuilder("insert into t_user_recommend_record values(null, ");
			sb.append(rd.nextInt(2) + ", null, ");
			for (int k = record.length - 1; k >= 0; k--) {
				if (record[k] instanceof Timestamp) {
					sb.append("'");
					sb.append(record[k]);
					sb.append("', ");
				} else {
					sb.append(record[k] + ", ");
				}
			}
			sb.delete(sb.length() - 2, sb.length());
			sb.append(");\n");
			oswRecommend.write(sb.toString());
//			LOG.info(sb);
		}
		LOG.info("videoId : " + videoId + ", userIds.size : " + userIds.size() + ", targetUserRecommendDataAmount : " + targetDataAmount);
	}

	public void praiseRecordGenerator(int videoId, Set<Integer> userIds, List crl) throws Exception {
		int targetDataAmount = (int) (userIds.size() * new Random().nextDouble() * 0.65) + 1;
		int step = crl.size() / targetDataAmount;
		Random rd = new Random();
		for (int j = 0; j < crl.size(); j = j + step) {
			Object[] record = (Object[]) crl.get(j);
			StringBuilder sb = new StringBuilder("insert into t_praise_record values(null, ");
			sb.append(rd.nextInt(2) + ", ");
			for (int k = record.length - 1; k >= 0; k--) {
				if (record[k] instanceof Timestamp) {
					sb.append("'");
					sb.append(record[k]);
					sb.append("', ");
				} else {
					sb.append(record[k] + ", ");
				}
			}
			sb.delete(sb.length() - 2, sb.length());
			sb.append(");\n");
			oswPraise.write(sb.toString());
//			LOG.info(sb);
		}
		LOG.info("videoId : " + videoId + ", userIds.size : " + userIds.size() + ", targetPraiseDataAmount : " + targetDataAmount);
	}

	public void markRecordGenerator(int videoId, Set<Integer> userIds, List crl) throws Exception {
		int targetDataAmount = (int) (userIds.size() * new Random().nextDouble() * 0.5) + 1;
		int step = crl.size() / targetDataAmount;
		Random rd = new Random();
		for (int j = 0; j < crl.size(); j = j + step) {
			Object[] record = (Object[]) crl.get(j);
			StringBuilder sb = new StringBuilder("insert into t_mark_record values(null, ");
			sb.append(rd.nextInt(5) + 1 + ", ");
			for (int k = record.length - 1; k >= 0; k--) {
				if (record[k] instanceof Timestamp) {
					sb.append("'");
					sb.append(record[k]);
					sb.append("', ");
				} else {
					sb.append(record[k] + ", ");
				}
			}
			sb.delete(sb.length() - 2, sb.length());
			sb.append(");\n");
			oswMark.write(sb.toString());
//			LOG.info(sb);
		}
		LOG.info("videoId : " + videoId + ", userIds.size : " + userIds.size() + ", targetMarkDataAmount : " + targetDataAmount);
	}

	public void collectRecordGenerator(int videoId, Set<Integer> userIds, List crl) throws Exception {
		int targetDataAmount = (int) (userIds.size() * new Random().nextDouble() * 0.3) + 1;
		int step = crl.size() / targetDataAmount;
		Random rd = new Random();
		for (int j = 0; j < crl.size(); j = j + step) {
			Object[] record = (Object[]) crl.get(j);
			StringBuilder sb = new StringBuilder("insert into t_collect_record values(null, ");
			for (int k = record.length - 1; k >= 0; k--) {
				if (record[k] instanceof Timestamp) {
					sb.append("'");
					sb.append(record[k]);
					sb.append("', ");
				} else {
					sb.append(record[k] + ", ");
				}
			}
			sb.delete(sb.length() - 2, sb.length());
			sb.append(");\n");
			oswCollect.write(sb.toString());
//			LOG.info(sb);
		}
		LOG.info("videoId : " + videoId + ", userIds.size : " + userIds.size() + ", targetCollectDataAmount : " + targetDataAmount);
	}

	public void playRecordGenerator(int videoId, Set<Integer> userIds, List crl) throws Exception {
		//模拟计算需要产生的数据总数
		int targetDataAmount = playRecordDataAmountGenerator(userIds.size());
		int step = crl.size() / targetDataAmount;
		for (int j = 0; j < crl.size(); j = j + step) {
			Object[] record = (Object[]) crl.get(j);
			StringBuilder sb = new StringBuilder("insert into t_play_record values(null, ");
			for (int k = record.length - 1; k >= 0; k--) {
				if (record[k] instanceof Timestamp) {
					sb.append("'");
					sb.append(record[k]);
					sb.append("', ");
				} else {
					sb.append(record[k] + ", ");
				}
			}
			sb.delete(sb.length() - 2, sb.length());
			sb.append(");\n");
			oswPlay.write(sb.toString());
//				LOG.info(sb);
		}
		LOG.info("videoId : " + videoId + ", userIds.size : " + userIds.size() + ", targetPlayDataAmount : " + targetDataAmount);
	}

	public void glanceRecordGenerator(int videoId, Set<Integer> userIds, List crl) throws Exception {
		int targetDataAmount = glanceRecordDataAmountGenerator(userIds.size());
		int step = crl.size() / targetDataAmount;
		for (int j = 0; j < crl.size(); j = j + step) {
			Object[] record = (Object[]) crl.get(j);
			StringBuilder sb = new StringBuilder("insert into t_glance_record values(null, ");
			for (int k = record.length - 1; k >= 0; k--) {
				if (record[k] instanceof Timestamp) {
					sb.append("'");
					sb.append(record[k]);
					sb.append("', ");
				} else {
					sb.append(record[k] + ", ");
				}
			}
			sb.delete(sb.length() - 2, sb.length());
			sb.append(");\n");
//			osw.write(sb.toString());
//			LOG.info(sb);
		}
		LOG.info(videoId + " userIds.size : " + userIds.size() + ", targetDataAmount : " + targetDataAmount);
	}

	private int glanceRecordDataAmountGenerator(int size) {
		size++;
		Random rd = new Random();
		int targetDataAmount;
		if (size < 5) {
			targetDataAmount = size * 10 + rd.nextInt(200);
		} else if (size < 20) {
			targetDataAmount = size * 5 + rd.nextInt(180);
		} else if (size < 50) {
			targetDataAmount = size * 10 / 9 + rd.nextInt(170);
		} else if (size < 200) {
			targetDataAmount = size * 11 / 10 + rd.nextInt(170);
		} else if (size < 500) {
			targetDataAmount = size * 12 / 11 + rd.nextInt(160);
		} else if (size < 1000) {
			targetDataAmount = size * 13 / 12 + rd.nextInt(150);
		} else if (size < 3000) {
			targetDataAmount = size * 14 / 13 + rd.nextInt(140);
		} else if (size < 4000) {
			targetDataAmount = size * 15 / 14 + rd.nextInt(130);
		} else if (size < 5000) {
			targetDataAmount = size * 16 / 15 + rd.nextInt(120);
		} else if (size < 10000) {
			targetDataAmount = size * 17 / 16 + rd.nextInt(300);
		} else if (size < 20000) {
			targetDataAmount = size * 18 / 17 + rd.nextInt(400);
		} else if (size < 30000) {
			targetDataAmount = size * 19 / 18 + rd.nextInt(500);
		} else {
			targetDataAmount = size * 20 / 19 + rd.nextInt(600);
		}
		return targetDataAmount;
	}
	private int playRecordDataAmountGenerator(int size) {
		size++;
		Random rd = new Random();
		int targetDataAmount;
		if (size < 5) {
			targetDataAmount = size * 10 + rd.nextInt(200);
		} else if (size < 20) {
			targetDataAmount = size * 5 + rd.nextInt(180);
		} else if (size < 50) {
			targetDataAmount = size * 12 / 11 + rd.nextInt(170);
		} else if (size < 200) {
			targetDataAmount = size * 13 / 12 + rd.nextInt(170);
		} else if (size < 500) {
			targetDataAmount = size * 14 / 13 + rd.nextInt(160);
		} else if (size < 1000) {
			targetDataAmount = size * 15 / 14 + rd.nextInt(150);
		} else if (size < 3000) {
			targetDataAmount = size * 16 / 15 + rd.nextInt(140);
		} else if (size < 4000) {
			targetDataAmount = size * 17 / 16 + rd.nextInt(130);
		} else if (size < 5000) {
			targetDataAmount = size * 18 / 17 + rd.nextInt(120);
		} else if (size < 10000) {
			targetDataAmount = size * 19 / 18 + rd.nextInt(300);
		} else if (size < 20000) {
			targetDataAmount = size * 20 / 19 + rd.nextInt(400);
		} else if (size < 30000) {
			targetDataAmount = size * 21 / 20 + rd.nextInt(500);
		} else {
			targetDataAmount = size * 22 / 21 + rd.nextInt(600);
		}
		return targetDataAmount;
	}
}
