package io.vicp.goradical.intellirec.service;

import io.vicp.goradical.intellirec.model.echartsvo.VideoStatisticsPieVo;

import java.io.Serializable;

/**
 * Created by Kenny on 2017/3/27.
 */
public interface VideoStatisticsService {

	/**
	 * 视频的行为统计
	 * @param videoId 视频id
	 * @return 视频行为统计饼图模型
	 */
	VideoStatisticsPieVo videoActionsStatics(Serializable videoId);
}
