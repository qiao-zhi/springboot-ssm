package cn.qlq.utils;

import java.io.Serializable;

public class JSONResultUtil<T> implements Serializable {

	private static final long serialVersionUID = 3637122497350396679L;

	private boolean success;

	private T data;

	private String msg;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public JSONResultUtil(boolean success) {
		this(success, "");
	}

	public JSONResultUtil(boolean success, String msg) {
		this(success, msg, null);
	}

	public JSONResultUtil(boolean success, String msg, T data) {
		this.success = success;
		this.msg = msg;
		this.data = data;
	}

	/**
	 * 返回正确结果不带数据
	 * 
	 * @return
	 */
	public static JSONResultUtil<Object> ok() {
		return new JSONResultUtil<Object>(true);
	}

	/**
	 * 返回错误的结果带错误信息
	 * 
	 * @param msg
	 * @return
	 */
	public static JSONResultUtil<Object> error(String msg) {
		return new JSONResultUtil<Object>(false, msg);
	}
}