package org.human.gulim.runcatch.factory;

import org.human.gulim.runcatch.network.NetworkMethod;
import org.human.gulim.runcatch.network.NetworkMethodJavaSocketImpl;

public class NetworkMethodFactory {
	
	private static NetworkMethod instance = new NetworkMethodJavaSocketImpl();
	public static NetworkMethod getInstance(){
		return instance;
	}

}
