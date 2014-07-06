package org.human.gulim.runcatch.network;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import org.human.gulim.runcatch.bean.RoomInfo;
import org.human.gulim.runcatch.bean.User;
import org.human.gulim.runcatch.constants.Constants;
import org.human.gulim.runcatch.exception.NetworkMethodException;

public class NetworkMethodJavaSocketImpl implements NetworkMethod {

	@Override
	public RoomInfo updateMyPos(User user) throws NetworkMethodException {
		Socket socket = null;
		ObjectOutputStream oOut = null;
		ObjectInputStream oIn = null;
		RoomInfo result = null;
		Object request, response;
		try {
			socket = new Socket(Constants.SERVER_IP, Constants.SERVER_PORT);
			oOut = new ObjectOutputStream(new BufferedOutputStream(
					socket.getOutputStream()));

			request = myPosToRequest(user);
			oOut.writeObject(request);
			oOut.flush();

			oIn = new ObjectInputStream(new BufferedInputStream(
					socket.getInputStream()));
			response = oIn.readObject();
			result = responseToRoomInfo(response);

		} catch (UnknownHostException e) {
			throw new NetworkMethodException(e);
		} catch (IOException e) {
			throw new NetworkMethodException(e);
		} catch (ClassNotFoundException e) {
			throw new NetworkMethodException(e);
		} finally {
			freeResources(socket, oIn, oOut);
		}
		return result;
	}

	private Object myPosToRequest(User user) {
		Object result = null;
		//TODO
		return result;
	}

	private RoomInfo responseToRoomInfo(Object response) {
		RoomInfo roomInfo = null;
		//TODO
		return roomInfo;
	}

	
	
	private void freeResources(Socket socket, InputStream in, OutputStream out) {
		if (out != null) {
			try {
				out.close();
			} catch (IOException e) {
			}
		}
		if (in != null) {
			try {
				in.close();
			} catch (IOException e) {
			}
		}
		if (socket != null) {
			try {
				socket.close();
			} catch (IOException e) {
			}
		}
	}
	
	

	@Override
	public RoomInfo catchUser(User user) throws NetworkMethodException {
		Socket socket = null;
		ObjectOutputStream oOut = null;
		ObjectInputStream oIn = null;
		RoomInfo result = null;
		Object request, response;
		try {
			socket = new Socket(Constants.SERVER_IP, Constants.SERVER_PORT);
			oOut = new ObjectOutputStream(new BufferedOutputStream(
					socket.getOutputStream()));

			request = catchInfoToRequest(user);
			oOut.writeObject(request);
			oOut.flush();

			oIn = new ObjectInputStream(new BufferedInputStream(
					socket.getInputStream()));
			response = oIn.readObject();
			
			result = responseToRoomInfo(response);

		} catch (UnknownHostException e) {
			throw new NetworkMethodException(e);
		} catch (IOException e) {
			throw new NetworkMethodException(e);
		} catch (ClassNotFoundException e) {
			throw new NetworkMethodException(e);
		} finally {
			freeResources(socket, oIn, oOut);
		}
		return result;
	}
	
	private Object catchInfoToRequest(User user){
		Object request=null;
		//TODO
		
		return request;
	}
	
}
