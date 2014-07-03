package org.human.gulim.runcatch.bean;

public class User {
	
	private String id_room;
	private String nickname;
	private String id;
	private String phone;
	private double latitude;
	private double longitude;
	private int team;
	
	public String getId_room() {
		return id_room;
	}
	public void setId_room(String id_room) {
		this.id_room = id_room;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public int getTeam() {
		return team;
	}
	public void setTeam(int team) {
		this.team = team;
	}
}
