package org.human.gulim.runcatch.network;

import org.human.gulim.runcatch.bean.RoomInfo;
import org.human.gulim.runcatch.bean.User;
import org.human.gulim.runcatch.exception.NetworkMethodException;

/**
 * 
 * @author KTS
 *
 *	Singleton pattern.
 */
public interface NetworkMethod {
	public RoomInfo updateMyPos(User user) throws NetworkMethodException;
	public RoomInfo catchUser(User user) throws NetworkMethodException;

}
