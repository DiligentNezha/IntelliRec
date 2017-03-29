package io.vicp.goradical.intellirec.model.echartsvo;

import java.util.List;
import java.util.Map;

/**
 * Created by Kenny on 2017/3/29.
 */
public class VideoStatisticsPieVo {

	private int id;
	private String videoName;
	private List<Map<String, Object>> seriesData;
	private List<Object> legendData;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public List<Map<String, Object>> getSeriesData() {
		return seriesData;
	}

	public void setSeriesData(List<Map<String, Object>> seriesData) {
		this.seriesData = seriesData;
	}

	public List<Object> getLegendData() {
		return legendData;
	}

	public void setLegendData(List<Object> legendData) {
		this.legendData = legendData;
	}
}
