package io.vicp.goradical.intellirec.spring4;

import io.vicp.goradical.intellirec.service.CommentRecordService;
import io.vicp.goradical.intellirec.tools.JDBCTools;
import io.vicp.goradical.intellirec.tools.ListTool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kenny on 2017/3/7.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DataCopyTest {
	private static final Logger LOG = LogManager.getLogger(DataCopyTest.class);
	private static Connection connection;

	private ApplicationContext ac;
	private CommentRecordService crs;

	private static List<String> fileIds = new ArrayList<>();
	private static List<List<Integer>> fileIdsList = new ArrayList<>();

	@Ignore
	@Before
	public void setUp() throws Exception {
		ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		crs = (CommentRecordService) ac.getBean("commentRecordService");

	}

	/**
	 * 引用 videoId 列字段值更新
	 * @throws Exception
	 */
	@Test
	public void refVideoIdUpdate() throws Exception {
		String sql = "select id, old_id from t_video";
		ResultSet rs = dataQuery(sql);
		while (rs.next()) {
			int id = rs.getInt(1);
			int oldId = rs.getInt(2);
			System.out.println("update t_comment_record set video_comment_record_id = " + id + " where video_comment_record_id = " + oldId + ";");
		}
	}

	/**
	 * 数据查询
	 * @param sql
	 * @param objects
	 * @return
	 */
	private ResultSet dataQuery(String sql, Object... objects) {
		connection = JDBCTools.getConnection();
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = connection.prepareStatement(sql);
			if (objects != null) {
				for (int i = 0; i < objects.length; i++) {
					pstmt.setObject(i + 1, objects[i]);
				}
			}
			rs = pstmt.executeQuery();
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	/**
	 * 数据删除
	 * @param sql
	 * @param objects
	 * @return
	 */
	private void dataDelete(String sql, Object... objects) {
		connection = JDBCTools.getConnection();
		PreparedStatement pstmt = null;
		try {
			pstmt = connection.prepareStatement(sql);
			if (objects != null) {
				for (int i = 0; i < objects.length; i++) {
					pstmt.setObject(i + 1, objects[i]);
				}
			}
			int count = pstmt.executeUpdate();
			LOG.info(sql + " : " + count);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTools.closeConnection(connection);
		}
	}

	/**
	 * 视频数据 videoId 唯一性检查
	 * @throws Exception
	 */
	@Test
	public void a1dataCheck() throws Exception {
		String sql = "select file_info_id, file_id, file_name, count(*) count from pmrs.t_file_info group by file_id having count > 1 order by file_info_id";
		ResultSet rs = dataQuery(sql);
		while (rs.next()) {
			int fileInfoId = rs.getInt(1);
			String fileId = rs.getString(2);
			String fileName = rs.getString(3);
//			LOG.info(fileInfoId + " : " + fileId + " : " + fileName);
			fileIds.add(fileId);
		}
//		LOG.info(fileIds);
	}

	@Test
	public void a2findDuplicate() throws Exception {
		String sql = "select file_info_id, file_id, file_name from t_file_info where file_id = ?";
		for (int i = 0; i < fileIds.size(); i++) {
			ResultSet rs = dataQuery(sql, fileIds.get(i));
			List<Integer> list = new ArrayList<>();
			rs.next();
			while (rs.next()) {
				int fileInfoId = rs.getInt(1);
				String fileId = rs.getString(2);
				String fileName = rs.getString(3);
				list.add(fileInfoId);
//				LOG.info(fileInfoId + " : " + fileId + " : " + fileName);
			}
			fileIdsList.add(list);
//			LOG.error("------------------------------------------------------------------------------");
			JDBCTools.closeConnection(connection);
		}
	}

	/**
	 * 删除重复评论
	 * @throws Exception
	 */
	@Test
	public void a3deleteComment() throws Exception {
		for (int i = 0; i < fileIdsList.size(); i++) {
			String sql = "delete from t_comment_record where file_id in " + ListTool.convertListToString(fileIdsList.get(i));
			dataDelete(sql);
		}
	}

	/**
	 * 删除重复演员
	 * @throws Exception
	 */
	@Test
	public void a4deleteActor() throws Exception {
		for (int i = 0; i < fileIdsList.size(); i++) {
			String sql = "delete from t_file_actor where file_id in " + ListTool.convertListToString(fileIdsList.get(i));
			dataDelete(sql);
		}
	}

	/**
	 * 删除重复导演
	 * @throws Exception
	 */
	@Test
	public void a5deleteDirector() throws Exception {
		for (int i = 0; i < fileIdsList.size(); i++) {
			String sql = "delete from t_file_director where file_id in " + ListTool.convertListToString(fileIdsList.get(i));
			dataDelete(sql);
		}
	}

	/**
	 * 删除重复地区
	 * @throws Exception
	 */
	@Test
	public void a6deleteNation() throws Exception {
		for (int i = 0; i < fileIdsList.size(); i++) {
			String sql = "delete from t_file_nation where file_id in " + ListTool.convertListToString(fileIdsList.get(i));
			dataDelete(sql);
		}
	}

	/**
	 * 删除重复类型
	 * @throws Exception
	 */
	@Test
	public void a7deleteType() throws Exception {
		for (int i = 0; i < fileIdsList.size(); i++) {
			String sql = "delete from t_file_type where file_id in " + ListTool.convertListToString(fileIdsList.get(i));
			dataDelete(sql);
		}
	}

	/**
	 * 删除重复影片
	 * @throws Exception
	 */
	@Test
	public void a8deleteFile() throws Exception {
		for (int i = 0; i < fileIdsList.size(); i++) {
			String sql = "delete from t_file_info where file_info_id in " + ListTool.convertListToString(fileIdsList.get(i));
			dataDelete(sql);
		}
	}
}
