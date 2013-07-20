package net.sgoliver.android.mapasapi2;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class BaseDatosHelper extends SQLiteOpenHelper {
		 
	    //La carpeta por defecto donde Android espera encontrar la Base de Datos de tu aplicaci�n
	    @SuppressLint("SdCardPath")
		private static String DB_PATH = "/data/data/net.sgoliver.android.mapasapi2/databases/";
	    private static String DB_NAME = "BDLugars";
	    //private static String DB_NAME = "Prueba2.sqlite";
	    private SQLiteDatabase myDataBase; 
	 
	    private final Context myContext;
	 
	    /*
	     * Constructor
	     * 
	     * Guarda una referencia al contexto para acceder a la carpeta assets de la aplicaci�n y a los recursos
	     * @param contexto
	     */
	    public BaseDatosHelper(Context contexto) {
	 
	    	super(contexto, DB_NAME, null, 1);
	        this.myContext = contexto;
	    }	
	 
	  /* 
	   * Crea una base de datos vac�a en el sistema y la sobreescribe con la que hemos puesto en Assets
	   */
	    public void crearDataBase() throws IOException{
	 
	    	boolean dbExist = comprobarBaseDatos();
	 
	    	if(dbExist){
	    		//Si ya existe no hacemos nada
	    	}else{
	    		//Si no existe, creamos una nueva Base de datos en la carpeta por defecto de nuestra aplicaci�n, 
	    		//de esta forma el Sistema nos permitir� sobreescribirla con la que tenemos en la carpeta Assets
	        	this.getReadableDatabase();
	        	try {
	    			copiarBaseDatos();
	    		} catch (IOException e) {
	        		throw new Error("Error al copiar la Base de Datos");
	        	}
	    	}
	    }
	 
	    /*
	     * Comprobamos si la base de datos existe
	     * @return true si existe, false en otro caso
	     */
	    private boolean comprobarBaseDatos(){
	    	SQLiteDatabase checkDB = null;
	    	try{
	    		String myPath = DB_PATH + DB_NAME;
	    		checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
	    	}catch(SQLiteException e){
	    		//No existe
	    	}
	 
	    	if(checkDB != null){
	    		checkDB.close();
	    	}
	 
	    	return checkDB != null ? true : false;
	    }
	 
	    /*
	     * Copia la base de datos desde la carpeta Assets sobre la base de datos vac�a reci�n creada en la carpeta del sistema,
	     * desde donde es accesible
	     */
	    private void copiarBaseDatos() throws IOException{
	 
	    	//Abrimos la BBDD de la carpeta Assets como un InputStream
	    	InputStream myInput = myContext.getAssets().open(DB_NAME);
	 
	    	//Carpeta de destino (donde hemos creado la BBDD vacia)
	    	String outFileName = DB_PATH + DB_NAME;
	 
	    	//Abrimos la BBDD vacia como OutputStream
	    	OutputStream myOutput = new FileOutputStream(outFileName);
	 
	    	//Transfiere los Bytes entre el Stream de entrada y el de Salida
	    	byte[] buffer = new byte[1024];
	    	int length;
	    	while ((length = myInput.read(buffer))>0){
	    		myOutput.write(buffer, 0, length);
	    	}
	 
	    	//Cerramos los ficheros abiertos
	    	myOutput.flush();
	    	myOutput.close();
	    	myInput.close();
	    }
	 
	    /*
	     * Abre la base de datos
	     */
	    public void abrirBaseDatos() throws SQLException{
	        String myPath = DB_PATH + DB_NAME;
	    	myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
	 
	    }
	 
	    /*
	     * Cierra la base de datos
	     */
	    @Override
		public synchronized void close() {
	    	    if(myDataBase != null)
	    		    myDataBase.close();
	 
	    	    super.close();
		}
	 
	    
		@Override
		public void onCreate(SQLiteDatabase db) {
			//No usamos este m�todo
		}
	 
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			//No usamos este m�todo
		}
	 
		//Podemos a�adir m�todos p�blicos que accedan al contenido de la base de datos, 
		//para realizar consultas, u operaciones CRUD (create, read, update, delete)
		
		private final String TABLE_LUGARES = "Lugar";
		private final String TABLE_KEY_ID = "_id";
		private final String TABLE_KEY_LUGAR = "lugar";
		private final String TABLE_KEY_POSICION = "Posicion";
		
		/*
	     * Obtiene todos los libros desde la Base de Datos
	     */
	     public ArrayList<Lugar> GetLugares(){
	     	ArrayList<Lugar> listaLugares = new ArrayList<Lugar>();
	     	
	     	Cursor c = myDataBase.query(TABLE_LUGARES, 
	     			new String[] {TABLE_KEY_ID, TABLE_KEY_LUGAR, TABLE_KEY_POSICION}, 
	     			null, null, null, null, null);
	     	Log.d("error", "Si ");
	     	//Iteramos a traves de los registros del cursor
	     	c.moveToFirst();Log.d("dato", c.getString(0));
	         while (c.isAfterLast() == false) {
	         //	Lugar libro = new Lugar();
	         	
	         	Log.d("dato", c.getString(1));
	         	
	         	Log.d("dato", c.getString(2));
	         	
	        	    c.moveToNext();
	         }
	         c.close();
	         Log.d("error", "termineeeeeeee");
	         return listaLugares;
	     }
}
