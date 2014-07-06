package org.human.gulim.runcatch.bean;

import java.util.Map;

import org.human.gulim.runcatch.factory.MapFactory;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
	
	public Team getTeam(Integer id_team)
	{
		return teams.get(id_team);
	}
	public void putTeam(Integer id_team, Team team)
	{
		teams.put(id_team, team);
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
	
	public static RoomInfo getRoomInfoFromJson(JSONObject obj)
	{
		if(obj==null)
			return null;
		RoomInfo roomInfo=new RoomInfo();
		Object value=null;
		JSONArray teams=null;
		JSONObject tmpObj=null;
		Team tmpTeam = null;
		
		value = obj.get("id_room");
		
		if(value!=null)
		{
			roomInfo.setId_room(value.toString());
		}
		
		value = obj.get("time");
		if(value!=null)
		{
			roomInfo.setTime(Long.parseLong(value.toString()));
		}
		
		value = obj.get("mode");
		if(value!=null)
		{
			roomInfo.setMode(Integer.parseInt(value.toString()));
		}
		
		teams =(JSONArray) obj.get("teams");
		for(int i=0;i<teams.size();i++)
		{
			tmpObj = (JSONObject)teams.get(i);
			if(tmpObj != null)
			{
				tmpTeam = Team.getTeamFromJson(tmpObj);
				if(tmpTeam!=null)
				{
					roomInfo.putTeam(tmpTeam.getId_team(), tmpTeam);
				}
			}
		}
		return roomInfo;
	}
	

}
