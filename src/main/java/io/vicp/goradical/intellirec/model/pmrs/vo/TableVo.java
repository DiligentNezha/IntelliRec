package io.vicp.goradical.intellirec.model.pmrs.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kenny on 2017/3/15.
 */
public class TableVo<T> {
	private long recordsTotal = 0L;

	private List<T> rows = new ArrayList<>();

	public long getRecordsTotal() {
		return recordsTotal;
	}

	public void setRecordsTotal(long recordsTotal) {
		this.recordsTotal = recordsTotal;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}
}
