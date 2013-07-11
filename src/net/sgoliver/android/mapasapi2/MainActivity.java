package net.sgoliver.android.mapasapi2;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.google.android.gms.maps.Projection;

import android.graphics.Point;

public class MainActivity extends android.support.v4.app.FragmentActivity {

	private GoogleMap mapa = null;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		inicio();//inicializamos mapa en UdeA
		mapa.setOnMapClickListener(new OnMapClickListener() {
			@Override
			public void onMapClick(LatLng point) {
				// TODO Auto-generated method stub
				Projection proj = mapa.getProjection();
				Point coord = proj.toScreenLocation(point);

				Toast.makeText(
						MainActivity.this, 
						"Click\n" + 
						"Lat: " + point.latitude + "\n" +
						"Lng: " + point.longitude + "\n" +
						"X: " + coord.x + " - Y: " + coord.y,
						Toast.LENGTH_SHORT).show();
				
			}
		});
		mapa.setOnMapLongClickListener(new OnMapLongClickListener() {
			public void onMapLongClick(LatLng point) {
				Projection proj = mapa.getProjection();
				Point coord = proj.toScreenLocation(point);

				Toast.makeText(
						MainActivity.this, 
						"Click Largo\n" + 
						"Lat: " + point.latitude + "\n" +
						"Lng: " + point.longitude + "\n" +
						"X: " + coord.x + " - Y: " + coord.y,
						Toast.LENGTH_SHORT).show();
			}
		});
		/*mapa.setOnCameraChangeListener(new OnCameraChangeListener() {
			public void onCameraChange(CameraPosition position) {
				Toast.makeText(
						MainActivity.this, 
						"Cambio Cámara\n" + 
						"Lat: " + position.target.latitude + "\n" +
						"Lng: " + position.target.longitude + "\n" +
						"Zoom: " + position.zoom + "\n" +
						"Orientación: " + position.bearing + "\n" +
						"Ángulo: " + position.tilt,
						Toast.LENGTH_SHORT).show();
			}
		});*/

		mapa.setOnMarkerClickListener(new OnMarkerClickListener() {
			public boolean onMarkerClick(Marker marker) {
				Toast.makeText(
						MainActivity.this, 
						"Marcador pulsado:\n" + 
						marker.getTitle(),
						Toast.LENGTH_SHORT).show();
				return false;
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem item) 
	{	
		switch(item.getItemId())
		{
			case R.id.menu_marcadores_ing:
				mostrarMarcador(6.268283367644, -75.56728340685368, "bloque 19"); //bloque 19
				mostrarMarcador(6.268370342305693, -75.56785807013512, "bloque 20"); //bloque 20
				mostrarMarcador(6.268110723634329, -75.56821882724762, "bloque 21"); //bloque 21
				mostrarMarcador(6.2676674719456305, -75.56743361055851, "bloque 18"); //bloque 18
				//mostrarMarcador(6.267221220428, -75.5690885335); //biblioteca
				break;
			case R.id.menu_lineas:
				limpiarMapa();
				break;
		}

		return super.onOptionsItemSelected(item);
	}
	private void inicio(){
		 
		mapa = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
		 mapa.setMapType(GoogleMap.MAP_TYPE_HYBRID);//vista hibrida, satelital con nombres
			LatLng udea = new LatLng(6.267221220428, -75.5690885335);
			CameraPosition camPos = new CameraPosition.Builder()
				    .target(udea)   //Centramos el mapa en UdeA
				    .zoom(17)         //Establecemos el zoom en 17
				    .build();

			CameraUpdate camUpd3 = 
					CameraUpdateFactory.newCameraPosition(camPos);

			mapa.animateCamera(camUpd3);
	}
	private void mostrarMarcador(double lat, double lng, String lugar)
	{
		
	    mapa.addMarker(new MarkerOptions()
	        .position(new LatLng(lat, lng))
	        .title("Lugar:"+lugar).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_CYAN)))
	        ;
	}
	private void limpiarMapa(){
		mapa.clear();
	}
}