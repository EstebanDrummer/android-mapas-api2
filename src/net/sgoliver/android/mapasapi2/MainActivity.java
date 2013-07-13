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
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.GoogleMap.OnMapClickListener;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import com.google.android.gms.maps.Projection;

import android.graphics.Color;
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
		limiteUdeA();
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
		/*Si s descomenta esta linea agregar 
		 * import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
		 * mapa.setOnCameraChangeListener(new OnCameraChangeListener() {
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
				limpiarMapa();
				
				//mostrarMarcador(6.268283367644, -75.56728340685368, "bloque 19"); //bloque 19
				//mostrarMarcador(6.268370342305693, -75.56785807013512, "bloque 20"); //bloque 20
				//mostrarMarcador(6.268110723634329, -75.56821882724762, "bloque 21"); //bloque 21
				//mostrarMarcador(6.2676674719456305, -75.56743361055851, "bloque 18"); //bloque 18
				//mostrarMarcador(6.267221220428, -75.5690885335); //biblioteca
				
				
				break;
			case R.id.menu_recreation:
				int iconSo = R.drawable.soccer2;
				mostrarMarcador(6.269207186719753, -75.56761734187603, "Cancha Sintética", iconSo);
				mostrarMarcador(6.269448141672462, -75.56949153542519, "Cancha de Fútbol", iconSo);
				int iconCT = R.drawable.tennis;
				mostrarMarcador(6.268967231469515, -75.5682734772563, "Tennis de Campo", iconCT);
				int iconPi = R.drawable.swim;
				mostrarMarcador(6.268940569768229, -75.5687190592289, "Piscina", iconPi);
				int iconScuba = R.drawable.scubadiving;
				mostrarMarcador(6.26927617383378, -75.56869693100452, "Scuba", iconScuba);
				int iconG = R.drawable.gym;
				mostrarMarcador(6.269512129690994, -75.5687566101551, "Gimnasio", iconG);
				int iconB = R.drawable.basketball;
				mostrarMarcador(6.269832736207243, -75.56735716760159, "Basketball", iconB);
				break;
				
			case R.id.menu_Cafeterias:
				
				int iconS = R.drawable.sandwich;
				mostrarMarcador(6.2682630288269126, -75.56838143616915, "Cafetria", iconS);
				mostrarMarcador(6.2683630288269126, -75.56838143616915, "Cafetria", iconS);
				mostrarMarcador(6.2684630288269126, -75.56838143616915, "Cafetria", iconS);
				mostrarMarcador(6.268602299314811, -75.56847430765629, "juguitos", iconS);
				mostrarMarcador(6.268702299314811, -75.56847430765629, "Pollos :(", iconS);
				mostrarMarcador(6.268502299314811, -75.56847430765629, "Patacones", iconS);
				mostrarMarcador(6.268399336937477, -75.56870564818382, "Deportes", iconS);
				mostrarMarcador(6.268010408811437, -75.56828454136848, "COESDUA", iconS);
				
				break;
			case R.id.menu_Salas_de_computadores:
				
				int iconC = R.drawable.computers;
				mostrarMarcador(6.267631478558866, -75.56759253144264, "LIS", iconC);
				mostrarMarcador(6.26781544473171, -75.56769981980324, "Telemática", iconC);
				mostrarMarcador(6.268334672985597,-75.56792570819855 , "Sala 1 bloque 20", iconC);
				mostrarMarcador(6.26833800755324,-75.56779616004229 , "Sala 2 bloque 20", iconC);
				mostrarMarcador(6.268250364501864,-75.56773223944664 , "Sala 3 bloque 20", iconC);
				break;
			case R.id.menu_parking:
				int iconP = R.drawable.parking;
				mostrarMarcador(6.266966933546314, -75.56779738515615, "Parking", iconP);
				mostrarMarcador(6.267457510487932, -75.57039745151997, "Parking", iconP);
				break;
			case R.id.menu_restroom:
				int iconR = R.drawable.toilets;
				mostrarMarcador(6.268442995517917, -75.56711744517088, "Baño Mujeres", iconR);
				mostrarMarcador(6.267995411577137, -75.56711744517088, "Baño Hombres", iconR);
				mostrarMarcador(6.267995411577137, -75.5675006678388, "Baño Mujeres", iconR);
				mostrarMarcador(6.268475656132965, -75.56745339185, "Baño Hombres", iconR);
				mostrarMarcador(6.267640476905802, -75.5677330121398, "Baños", iconR);
				mostrarMarcador(6.268063732307746, -75.56825671344995, "Baño Mujeres", iconR);
				mostrarMarcador(6.268453993480358, -75.56823659688234, "Baño Hombres", iconR);
				mostrarMarcador(6.268365676502711, -75.56863021105528, "Baños", iconR);
				break;
			
		}

		return super.onOptionsItemSelected(item);
	}
	private void inicio(){
		 
		mapa = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
		 mapa.setMapType(GoogleMap.MAP_TYPE_HYBRID);//vista hibrida, satelital con nombres
			//LatLng udea = new LatLng(6.267221220428, -75.5690885335);
		 LatLng udea = new LatLng(6.2674398470598565, -75.5684907361865);
				
		 CameraPosition camPos = new CameraPosition.Builder()
				    .target(udea)   //Centramos el mapa en UdeA
				    .zoom((float) 17.274595)         //Establecemos el zoom en 17
				    .bearing((float) 16.594995)
				    .build();

			CameraUpdate camUpd3 = 
					CameraUpdateFactory.newCameraPosition(camPos);

			mapa.animateCamera(camUpd3);
	}
	
	private void limiteUdeA() {
		PolygonOptions rectangulo = new PolygonOptions()
			.add(new LatLng(6.269756083939577,-75.56692332029343),
				 new LatLng(6.270170339321613, -75.56812964379787),
				 new LatLng(6.270011036003672, -75.56817758828402),
				 new LatLng(6.270444,-75.569462),
				 new LatLng(6.27033,-75.56995),
				 new LatLng(6.270055,-75.570259),
				 new LatLng(6.268272,-75.570704),
				 new LatLng(6.26684,-75.571007),
				 new LatLng(6.266581,-75.571012),
				 new LatLng(6.266213,-75.570951),
				 new LatLng(6.265933,-75.570841),
				 new LatLng(6.265643,-75.570642),
				 new LatLng(6.26524,-75.570398),
				 new LatLng(6.265019,-75.570167),
				 new LatLng(6.264952,-75.570114),
				 new LatLng(6.264902,-75.569722),
				 new LatLng(6.265059,-75.569502),
				 new LatLng(6.265056,-75.569339),
				 new LatLng(6.264806,-75.569154),
				 new LatLng(6.264619,-75.567705),
				 new LatLng(6.264736,-75.567493),
				 new LatLng(6.269327,-75.566479)
				 );

		rectangulo.strokeWidth(8);
		rectangulo.strokeColor(Color.RED);

		mapa.addPolygon(rectangulo);
	}
	private void mostrarMarcador(double lat, double lng, String lugar, int icon)
	{
		 mapa.addMarker(new MarkerOptions()
	        .position(new LatLng(lat, lng))
	        .title("Lugar:"+lugar)
	        .icon(BitmapDescriptorFactory.fromResource(icon)))
	        ;
	}
	private void limpiarMapa(){
		mapa.clear();
		limiteUdeA();
	}
}