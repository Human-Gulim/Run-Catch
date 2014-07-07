package org.human.gulim.runcatch.utils;

import java.util.HashSet;

import org.human.gulim.runcatch.bean.RoomInfo;
import org.human.gulim.runcatch.bean.Team;
import org.human.gulim.runcatch.bean.User;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class MixareConverter {
	
	@SuppressWarnings("unchecked")
	public static String getPageContentWithSameTeam(RoomInfo roomInfo, User user){
		JSONObject j_result = new JSONObject();
		JSONArray j_array = new JSONArray();
		JSONObject j_user;
		int id;
		Team team = roomInfo.getTeam(user.getId_team());
		j_result.put("status", "OK");
		j_result.put("num_results", team.getCount());
		j_result.put("results", j_array);
		
		HashSet<Integer> set= new HashSet<Integer>();
		
		for(User tmpUser : team.getMembers())
		{
			while(true)
			{
				id =(int) Math.round(Math.random()*3000);
				if(set.contains(id)==false)
				{
					set.add(id);
					break;
				}
			}
			
			j_user = new JSONObject();
			j_user.put("id",id+"");
			j_user.put("lat", tmpUser.getLatitude()+"");
			j_user.put("lng", tmpUser.getLongitude()+"");
			j_user.put("title", tmpUser.getNickname());
			j_user.put("elevation", "0");
			j_user.put("distance", "5");
			j_user.put("has_detail_page", "0");
			j_user.put("webpage", "");
			j_array.add(j_user);
			
		}
		return j_result.toJSONString();
	}

}
