package org.human.gulim.runcatch.bean;

import java.util.List;
import java.util.Map;

import org.human.gulim.runcatch.factory.ListFactory;
import org.human.gulim.runcatch.factory.MapFactory;

public class Team {
	private Map<String,User> userMap;
	private int id_team;
	
	
	
	public Team(){
		userMap = MapFactory.getMap(String.class, User.class);
		this.id_team = -1;
	}
	
	public int getCount(){
		return userMap.size();
	}
	
	public void put(String key, User user){
		userMap.put(key, user);
	}
	
	public User get(String key)
	{
		return userMap.get(key);
	}
	
	public List<User> getMembers()
	{
		List<User> members = ListFactory.getList(User.class);
		members.addAll(userMap.values());
		return members;
	}
	
	public int getId_team() {
		return id_team;
	}
	public void setId_team(int id_team) {
		this.id_team = id_team;
	}


}
