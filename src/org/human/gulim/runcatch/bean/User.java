package org.human.gulim.runcatch.bean;

import org.json.simple.JSONObject;



public class User {
	
	private String id_room;
	private String nickname;
	private String id;
	private String phone;
	private double latitude;
	private double longitude;
	private int team;
	
	public User(){
		this.id_room = null;
		this.nickname =null;
		this.id = null;
		this.phone = null;
		this.latitude = -1;
		this.longitude = -1;
		this.team = -1;
	}
	
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
	public static User getUserFromJson(JSONObject obj){
		if(obj == null)
			return null;
		
		User user = new User();
		
		Object value = obj.get("id");
		if(value != null)
		{
			user.setId(value.toString());
		}
		
		
		value = obj.get("id_room");	
		if(value!=null)
		{
			user.setId_room(value.toString());
		}
		
		value = obj.get("nickname");
		if(value!=null)
		{
			user.setNickname(value.toString());
		}
		
		value = obj.get("phone");
		if(value!=null)
		{
			user.setPhone(value.toString());
		}
		
		value = obj.get("latitude");
		if(value!=null)
		{
			user.setLatitude(Double.parseDouble(value.toString()));
		}
		
		value = obj.get("longitude");
		if(value!=null)
		{
			user.setLongitude(Double.parseDouble(value.toString()));
		}
		
		value = obj.get("team");
		if(value!=null)
		{
			user.setTeam(Integer.parseInt(value.toString()));
		}
		
		return user;
	}
	

}
