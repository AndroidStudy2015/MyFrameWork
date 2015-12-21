package com.example.myframework.javabean;

public class StayPost {

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

		private String id;
		private String account;
		private String email;
		private String username;
		private String password;
		private String avatar;
		private String token;

		public void setId(String id) {
			this.id = id;
		}

		public String getId() {
			return id;
		}

		public void setAccount(String account) {
			this.account = account;
		}

		public String getAccount() {
			return account;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getEmail() {
			return email;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getUsername() {
			return username;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getPassword() {
			return password;
		}

		public void setAvatar(String avatar) {
			this.avatar = avatar;
		}

		public String getAvatar() {
			return avatar;
		}

		public void setToken(String token) {
			this.token = token;
		}

		public String getToken() {
			return token;
		}

		@Override
		public String toString() {
			return "Data [id=" + id + ", account=" + account + ", email="
					+ email + ", username=" + username + ", password="
					+ password + ", avatar=" + avatar + ", token=" + token
					+ "]";
		}

	}
}
