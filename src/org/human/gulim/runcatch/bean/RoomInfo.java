package org.human.gulim.runcatch.bean;

import java.util.Map;

public class RoomInfo {
	
	private String id_room;
	private long time;
	private Map<String, Team>teams;
	private int mode;//탈출지모드인지 시간 뻐기기 모드인지.

	public Map<String, Team> getTeams() {
		return teams;
	}

	public void setTeams(Map<String, Team> teams) {
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
