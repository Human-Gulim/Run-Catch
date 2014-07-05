package org.human.gulim.runcatch.bean;

import java.util.Map;

import org.human.gulim.runcatch.factory.MapFactory;

public class RoomInfo {
	
	private String id_room;
	private long time;
	private Map<Integer, Team>teams;
	private int mode;

	public RoomInfo(){
		this.id_room = null;
		this.time = -1;
		this.teams = MapFactory.getMap(Integer.class, Team.class);
		this.mode = -1;
	}
	
	public Map<Integer, Team> getTeams() {

		return teams;
	}

	public void setTeams(Map<Integer, Team> teams) {
		this.teams = teams;
	}

	public String getId_room() {
		return id_room;
	}

	public void setId_room(String id_room) {
		this.id_room = id_room;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public int getMode() {
		return mode;
	}

	public void setMode(int mode) {
		this.mode = mode;
	}
	

}
