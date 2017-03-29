package io.vicp.goradical.intellirec.service.impl;

import io.vicp.goradical.intellirec.dao.*;
import io.vicp.goradical.intellirec.model.echartsvo.VideoStatisticsPieVo;
import io.vicp.goradical.intellirec.model.pmrs.Video;
import io.vicp.goradical.intellirec.service.VideoStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Kenny on 2017/3/27.
 */
@Service(value = "videoStatisticsService")
public class VideoStatisticsServiceImpl implements VideoStatisticsService {

	@Autowired
	private VideoDao videoDao;

	@Autowired
	private CollectionRecordDao collectionRd;

	@Autowired
	private CommentRecordDao commentRd;

	@Autowired
	private GlanceRecordDao glanceRd;

	@Autowired
	private MarkRecordDao markRd;

	@Autowired
	private PlayRecordDao playRd;

	@Autowired
	private PraiseRecordDao praiseRd;

	@Autowired
	private UserRecommendDao userRecommendDao;

	@Override
	public VideoStatisticsPieVo videoActionsStatics(Serializable videoId) {
		Video video = videoDao.getEntity(videoId);

		VideoStatisticsPieVo vspv = new VideoStatisticsPieVo();
		vspv.setVideoName(video.getVideoName());

		List<Object> legendData = new ArrayList<>();
		List<Map<String, Object>> seriesData = new ArrayList<>();

		String str1 = "收藏总数";
		long collectionTotal = collectionRd.countTotalCollectWithVideoId(videoId);
		legendData.add(str1);
		Map<String, Object> map1 = new HashMap();
		map1.put("value", collectionTotal);
		map1.put("name", str1);
		seriesData.add(map1);

		String str2 = "评论总数";
		long commentTotal = commentRd.countTotalCommentWithVideoId(videoId);
		legendData.add(str2);
		Map<String, Object> map2 = new HashMap();
		map2.put("value", commentTotal);
		map2.put("name", str2);
		seriesData.add(map2);

		String str3 = "浏览总数";
		long glanceTotal = glanceRd.countTotalGlanceWithVideoId(videoId);
		legendData.add(str3);
		Map<String, Object> map3 = new HashMap();
		map3.put("value", glanceTotal);
		map3.put("name", str3);
		seriesData.add(map3);

		String str4 = "评分总数";
		long markTotal = markRd.countTotalMarkWithVideoId(videoId);
		legendData.add(str4);
		Map<String, Object> map4 = new HashMap();
		map4.put("value", markTotal);
		map4.put("name", str4);
		seriesData.add(map4);

		String str5 = "播放总数";
		long playTotal = playRd.countTotalPlayWithVideoId(videoId);
		legendData.add(str5);
		Map<String, Object> map5 = new HashMap();
		map5.put("value", playTotal);
		map5.put("name", str5);
		seriesData.add(map5);

		String str6 = "点赞总数";
		long praiseTotal = praiseRd.countTotalPraiseWithVideoId(videoId);
		legendData.add(str6);
		Map<String, Object> map6 = new HashMap();
		map6.put("value", praiseTotal);
		map6.put("name", str6);
		seriesData.add(map6);

		String str7 = "用户推荐总数";
		long userRecmooendTotal = userRecommendDao.countTotalUserRecommendWithVideoId(videoId);
		legendData.add(str7);
		Map<String, Object> map7 = new HashMap();
		map7.put("value", userRecmooendTotal);
		map7.put("name", str7);
		seriesData.add(map7);

		vspv.setLegendData(legendData);
		vspv.setSeriesData(seriesData);

		return vspv;
	}
}
