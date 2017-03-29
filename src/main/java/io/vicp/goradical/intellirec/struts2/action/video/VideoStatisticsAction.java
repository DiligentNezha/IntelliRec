package io.vicp.goradical.intellirec.struts2.action.video;

import com.alibaba.fastjson.JSON;
import io.vicp.goradical.intellirec.model.echartsvo.VideoStatisticsPieVo;
import io.vicp.goradical.intellirec.service.VideoStatisticsService;
import io.vicp.goradical.intellirec.struts2.action.BaseAction;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Kenny on 2017/3/13.
 */
@Namespace("/videoStatistics")
@Action(value = "videoStatisticsAction")
public class VideoStatisticsAction extends BaseAction<VideoStatisticsPieVo> {
	private static final Logger LOG = LogManager.getLogger(VideoStatisticsAction.class);

	@Autowired
	private VideoStatisticsService vss;

	public void getStatisticsPie() {
		VideoStatisticsPieVo vspv = vss.videoActionsStatics(model.getId());
		String str = JSON.toJSONString(vspv);
		System.out.println(str);
		super.writeJson(vspv);
	}

}
