package org.human.gulim.runcatch;

import java.util.List;

import org.human.gulim.runcatch.bean.Team;
import org.human.gulim.runcatch.bean.User;

import net.daum.mf.map.api.MapPOIItem;
import net.daum.mf.map.api.MapPoint;
import net.daum.mf.map.api.MapReverseGeoCoder;
import net.daum.mf.map.api.MapView;
import android.app.Activity;
import android.app.AlertDialog;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MapActivity extends Activity implements MapView.OpenAPIKeyAuthenticationResultListener, MapView.MapViewEventListener, MapView.CurrentLocationEventListener, MapView.POIItemEventListener, MapReverseGeoCoder.ReverseGeoCodingResultListener{

	private static final int MENU_MAP_TYPE = Menu.FIRST + 1;
	private static final int MENU_MAP_MOVE = Menu.FIRST + 2;
	private static final int MENU_LOCATION_TRACKING = Menu.FIRST + 3;
	private static final int MENU_MAP_OVERLAY = Menu.FIRST + 4;

	private static final String LOG_TAG = "DaumMapLibrarySample";

	private MapView mapView;
	private MapPOIItem poiItem;

	private MapReverseGeoCoder reverseGeoCoder = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.activity_map);

		LinearLayout linearLayout = new LinearLayout(this);
		linearLayout.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		linearLayout.setOrientation(LinearLayout.VERTICAL);

		/*
		TextView textView = new TextView(this);
		textView.setText("Press MENU button!");
		textView.setTextSize(18.0f);
		textView.setGravity(Gravity.CENTER);
		textView.setBackgroundColor(Color.DKGRAY);
		textView.setTextColor(Color.WHITE);

		linearLayout.addView(textView);
		 */

		MapView.setMapTilePersistentCacheEnabled(true);

		mapView = new MapView(this);

		mapView.setDaumMapApiKey("886ab49519f14531bf5ac64f58600049288ee838");
		mapView.setOpenAPIKeyAuthenticationResultListener(this);
		mapView.setMapViewEventListener(this);
		mapView.setCurrentLocationEventListener(this);
		mapView.setPOIItemEventListener(this);

		//mapView.setMapType(MapView.MapType.Hybrid);
		mapView.setMapType(MapView.MapType.Standard);

		linearLayout.addView(mapView);

		setContentView(linearLayout);

		Intent intent = getIntent();
		String mode = intent.getStringExtra("mode");

		if ( mode.equals("map_item"))
		{
			Team t1 = MakeRoomActivity.roomInfo.getTeam(0); // °æÂû
			Team t2 = MakeRoomActivity.roomInfo.getTeam(1); // µµµÏ

			BluetoothAdapter BTAdapter = BluetoothAdapter.getDefaultAdapter();
			final String mac = BTAdapter.getAddress();

			List<User> t1List = t1.getMembers();
			List<User> t2List = t2.getMembers();

			for ( int i=0; i<t1.getCount(); i++)
			{	
				poiItem = new MapPOIItem();
				poiItem.setItemName(t1List.get(i).getNickname());
				poiItem.setMapPoint(MapPoint.mapPointWithGeoCoord(t1List.get(i).getLatitude(),t1List.get(i).getLongitude()));
				poiItem.setMarkerType(MapPOIItem.MarkerType.BluePin);
				poiItem.setShowAnimationType(MapPOIItem.ShowAnimationType.DropFromHeaven);
				
				if ( t1List.get(i).getId().equals(mac))
					poiItem.setMarkerType(MapPOIItem.MarkerType.YellowPin);
				
				mapView.addPOIItem(poiItem);
			}
			
			for ( int i=0; i<t2.getCount(); i++)
			{				
				poiItem = new MapPOIItem();
				poiItem.setItemName(t2List.get(i).getNickname());
				poiItem.setMapPoint(MapPoint.mapPointWithGeoCoord(t2List.get(i).getLatitude(),t2List.get(i).getLongitude()));
				poiItem.setMarkerType(MapPOIItem.MarkerType.RedPin);
				poiItem.setShowAnimationType(MapPOIItem.ShowAnimationType.DropFromHeaven);
				
				if ( t2List.get(i).getId().equals(mac))
					poiItem.setMarkerType(MapPOIItem.MarkerType.YellowPin);
				
				mapView.addPOIItem(poiItem);
			}
			mapView.setCurrentLocationEventListener(this);
			mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading); 
		}


	}

	/////////////////////////////////////////////////////////////////////////////////////////////////
	// net.daum.mf.map.api.MapView.OpenAPIKeyAuthenticationResultListener

	@Override
	public void onDaumMapOpenAPIKeyAuthenticationResult(MapView mapView, int resultCode, String resultMessage) {
		Log.i(LOG_TAG,	String.format("Open API Key Authentication Result : code=%d, message=%s", resultCode, resultMessage));	
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////
	// net.daum.mf.map.api.MapView.MapViewEventListener

	public void onMapViewInitialized(MapView mapView) { 
		Log.i(LOG_TAG, "MapView had loaded. Now, MapView APIs could be called safely"); 
		//mapView.setCurrentLocationTrackingMode(MapView.CurrentLocationTrackingMode.TrackingModeOnWithoutHeading);
		
		mapView.setMapCenterPointAndZoomLevel(MapPoint.mapPointWithGeoCoord(37.537229,127.005515), 2, true);
	} 

	@Override
	public void onMapViewCenterPointMoved(MapView mapView, MapPoint mapCenterPoint) {
		MapPoint.GeoCoordinate mapPointGeo = mapCenterPoint.getMapPointGeoCoord();
		Log.i(LOG_TAG, String.format("MapView onMapViewCenterPointMoved (%f,%f)", mapPointGeo.latitude, mapPointGeo.longitude));
	}

	@Override
	public void onMapViewDoubleTapped(MapView mapView, MapPoint mapPoint) {
		/*
		MapPoint.GeoCoordinate mapPointGeo = mapPoint.getMapPointGeoCoord();

		AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
		alertDialog.setTitle("DaumMapLibrarySample");
		alertDialog.setMessage(String.format("Double-Tap on (%f,%f)", mapPointGeo.latitude, mapPointGeo.longitude));
		alertDialog.setPositiveButton("OK", null);
		alertDialog.show();
		 */
	}

	@Override
	public void onMapViewLongPressed(MapView mapView, MapPoint mapPoint) {
		/*
		MapPoint.GeoCoordinate mapPointGeo = mapPoint.getMapPointGeoCoord();

		AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
		alertDialog.setTitle("DaumMapLibrarySample");
		alertDialog.setMessage(String.format("Long-Press on (%f,%f)", mapPointGeo.latitude, mapPointGeo.longitude));
		alertDialog.setPositiveButton("OK", null);
		alertDialog.show();
		 */
	}

	@Override
	public void onMapViewSingleTapped(MapView mapView, MapPoint mapPoint) {
		/*
		MapPoint.GeoCoordinate mapPointGeo = mapPoint.getMapPointGeoCoord();
		Log.i(LOG_TAG, String.format("MapView onMapViewSingleTapped (%f,%f)", mapPointGeo.latitude, mapPointGeo.longitude));
		 */
	}

	@Override
	public void onMapViewZoomLevelChanged(MapView mapView, int zoomLevel) {
		Log.i(LOG_TAG, String.format("MapView onMapViewZoomLevelChanged (%d)", zoomLevel));
	}


	/////////////////////////////////////////////////////////////////////////////////////////////////
	// net.daum.mf.map.api.MapView.CurrentLocationEventListener

	@Override
	public void onCurrentLocationUpdate(MapView mapView, MapPoint currentLocation, float accuracyInMeters) {
		MapPoint.GeoCoordinate mapPointGeo = currentLocation.getMapPointGeoCoord();
		Log.i(LOG_TAG, String.format("MapView onCurrentLocationUpdate (%f,%f) accuracy (%f)", mapPointGeo.latitude, mapPointGeo.longitude, accuracyInMeters));
	}

	@Override
	public void onCurrentLocationDeviceHeadingUpdate(MapView mapView, float headingAngle) {
		Log.i(LOG_TAG, String.format("MapView onCurrentLocationDeviceHeadingUpdate: device heading = %f degrees", headingAngle));
	}

	@Override
	public void onCurrentLocationUpdateFailed(MapView mapView) {

		AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
		alertDialog.setTitle("DaumMapLibrarySample");
		alertDialog.setMessage("Current Location Update Failed!");
		alertDialog.setPositiveButton("OK", null);
		alertDialog.show();
	}

	@Override
	public void onCurrentLocationUpdateCancelled(MapView mapView) {
		Log.i(LOG_TAG, "MapView onCurrentLocationUpdateCancelled");
	}


	/////////////////////////////////////////////////////////////////////////////////////////////////
	// net.daum.mf.map.api.MapView.POIItemEventListener

	@Override
	public void onPOIItemSelected(MapView mapView, MapPOIItem poiItem) {
		Log.i(LOG_TAG, String.format("MapPOIItem(%s) is selected", poiItem.getItemName()));
	}

	@Override
	public void onCalloutBalloonOfPOIItemTouched(MapView mapView, MapPOIItem poiItem) {

		String alertMessage = null;

		if (poiItem == this.poiItem) {

			alertMessage = "Touched the callout-balloon of item1";

		} else if (poiItem.getTag() == 153) {

			String addressForThisItem = MapReverseGeoCoder.findAddressForMapPoint("DAUM_LOCAL_DEMO_APIKEY", poiItem.getMapPoint());
			alertMessage = String.format("Touched the callout-balloon of item2 (address : %s)", addressForThisItem);

		} else if ((poiItem.getUserObject() instanceof String) &&  poiItem.getUserObject().equals("item3")) {

			Intent intent = new Intent(this, MapPOIDetailActivity.class);
			intent.putExtra("POIName", poiItem.getItemName());
			startActivity(intent);
			return;

		} else if (poiItem.getTag() == 276) {

			alertMessage = "Touched the callout-balloon of item4";
		}

		AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
		alertDialog.setTitle("DaumMapLibrarySample");
		alertDialog.setMessage(alertMessage);
		alertDialog.setPositiveButton("OK", null);
		alertDialog.show();
	}

	@Override
	public void onDraggablePOIItemMoved(MapView mapView, MapPOIItem poiItem, MapPoint newMapPoint) {

		MapPoint.GeoCoordinate newMapPointGeo = newMapPoint.getMapPointGeoCoord();
		String alertMessage = String.format("Draggable MapPOIItem(%s) has moved to new point (%f,%f)", poiItem.getItemName(), newMapPointGeo.latitude, newMapPointGeo.longitude);
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
		alertDialog.setTitle("DaumMapLibrarySample");
		alertDialog.setMessage(alertMessage);
		alertDialog.setPositiveButton("OK", null);
		alertDialog.show();
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////
	// net.daum.mf.map.api.MapReverseGeoCoder.ReverseGeoCodingResultListener

	@Override
	public void onReverseGeoCoderFoundAddress(MapReverseGeoCoder rGeoCoder, String addressString) {

		String alertMessage = String.format("Center Point Address = [%s]", addressString);
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
		alertDialog.setTitle("DaumMapLibrarySample");
		alertDialog.setMessage(alertMessage);
		alertDialog.setPositiveButton("OK", null);
		alertDialog.show();

		reverseGeoCoder = null;
	}

	@Override
	public void onReverseGeoCoderFailedToFindAddress(MapReverseGeoCoder rGeoCoder) {
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
		alertDialog.setTitle("DaumMapLibrarySample");
		alertDialog.setMessage("Reverse Geo-Coding Failed");
		alertDialog.setPositiveButton("OK", null);
		alertDialog.show();

		reverseGeoCoder = null;
	}

}
