package com.example.myframework.javabean;

import java.util.Date;

public class Stay {

	private int ret;
	private Data data;
	private String msg;

	public void setRet(int ret) {
		this.ret = ret;
	}

	public int getRet() {
		return ret;
	}

	public void setData(Data data) {
		this.data = data;
	}

	public Data getData() {
		return data;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public class Data {

		private String title;
		private String content;
		private String version;
		private int time;

		public void setTitle(String title) {
			this.title = title;
		}

		public String getTitle() {
			return title;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getContent() {
			return content;
		}

		public void setVersion(String version) {
			this.version = version;
		}

		public String getVersion() {
			return version;
		}

		public void setTime(int time) {
			this.time = time;
		}

		public int getTime() {
			return time;
		}

		@Override
		public String toString() {
			return "Data [title=" + title + ", content=" + content
					+ ", version=" + version + ", time=" + time + "]";
		}

	}
}
