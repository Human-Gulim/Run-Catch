package org.human.gulim.runcatch;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

public class LocationFetchService extends Service {

	LocationManager locationManager;
	LocationAct actor;
	
	private class LocationAct implements LocationListener
	{

		@Override
		public void onLocationChanged(Location arg0) {
			if ( GameActivity.me == null )
				return;
			GameActivity.me.onLocationChange(arg0);
			//Log.d("verbose", "got location update on Lat:" + arg0.getLatitude() + "\nLong: " + arg0.getLongitude());
			
		}

		@Override
		public void onProviderDisabled(String arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onProviderEnabled(String arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	@Override
	public IBinder onBind(Intent arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		locationManager.removeUpdates(actor);
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		
		locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		
		
		actor = new LocationAct();
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 500, 5, actor);
		
		return START_STICKY;
	}


}
