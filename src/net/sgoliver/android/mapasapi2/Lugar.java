package net.sgoliver.android.mapasapi2;

public class Lugar {
	private String Sitio = "";
	private String Ubicacion = "";

	public String getSitio() {
		return Sitio;
	}

	public String getUbicacion() {
		return Ubicacion;
	}

	public void setLugar(String sitio) {
		Sitio = sitio;
	}

	public void setPosicion(String ubicacion) {
		Ubicacion = ubicacion;
	}
}
