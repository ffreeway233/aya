package com.entity.util;

import java.util.List;

public class ReturnError {
	private List<Object> rows;
	private Object object;
	private Long results;
	private boolean hasError;
	private String error;
	private String errType;
	private String errInfo;

	public ReturnError(boolean hasError, String error, String errType, String errInfo, Long results, List<Object> rows,
			Object object) {
		super();
		this.rows = rows;
		this.object = object;
		this.results = results;
		this.hasError = hasError;
		this.error = error;
		this.errType = errType;
		this.errInfo = errInfo;
	}

	public ReturnError() {
		super();
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public String getErrType() {
		return errType;
	}

	public void setErrType(String errType) {
		this.errType = errType;
	}

	public String getErrInfo() {
		return errInfo;
	}

	public void setErrInfo(String errInfo) {
		this.errInfo = errInfo;
	}

	public List<Object> getRows() {
		return rows;
	}

	public void setRows(List<Object> rows) {
		this.rows = rows;
	}

	public Long getResults() {
		return results;
	}

	public void setResults(Long results) {
		this.results = results;
	}

	public boolean isHasError() {
		return hasError;
	}

	public void setHasError(boolean hasError) {
		this.hasError = hasError;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}
}
