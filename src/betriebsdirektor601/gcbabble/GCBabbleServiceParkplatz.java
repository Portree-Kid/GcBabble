package betriebsdirektor601.gcbabble;

/* INFO:
-Hintergrundservice
-wird gestartet durch GCBabble wenn Parkplatz automatisch speichern aktiv
-beinhaltet keine visuellen Geschichten, 
 Läuft als permanenter Service im Hintergrund
 erkennt Parkplatz
 ruft zum Speichern ParkplatzSpeichernActivity auf
*/

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import java.text.DecimalFormat;



public class GCBabbleServiceParkplatz extends Service implements OnInitListener
{
	// TAG (Quell/Absender-Name) der LogCat-Meldungen generieren (wird angeblich immer so gemacht)
	private String TAG = GCBabbleServiceParkplatz.class.getSimpleName();
	
	final static int myID = 8152011;
	String Dateiname = "servicedaten";
	String TestdatenString = "Testdaten";
	Integer TestInteger = 1;
	
	long gr15kmhZeit;
	boolean gr15kmh15s;
	long kl7kmhNach15kmhZeit;

		//EditText etZielBreiteGrad;
		String ZielBreiteGradString;
		float ZielBreiteGrad;
		
		//EditText etZielBreiteMinDez;
		String ZielBreiteMinDezString;
		float ZielBreiteMinDez;//
		
		//EditText etZielLaengeGrad;
		String ZielLaengeGradString;
		float ZielLaengeGrad;//
		
		//EditText etZielLaengeMinDez;
		String ZielLaengeMinDezString;
		float ZielLaengeMinDez;//
			
		//TextView tvZielBreiteDez;
		String ZielBreiteDezString;
		double ZielBreiteDez;
		
		//TextView tvZielLaengeDez;
		String ZielLaengeDezString;
		double ZielLaengeDez;
		
		//TextView tvPosBreiteDez;
		String PosBreiteDezString;
		float PosBreiteDez;
		double VorPosBreiteDez;
		double VorVorPosBreiteDez;
		double GehPos1BreiteDez;
		
		//TextView tvPosLaengeDez;
		String PosLaengeDezString;
		float PosLaengeDez;
		double VorPosLaengeDez;
		double VorVorPosLaengeDez;
		double GehPos1LaengeDez;
			
		double ParkplatzBreiteDez;   
		double ParkplatzLaengeDez; 
		boolean ParkplatzMerken;  
		//TextView tvParkplatzBreiteDez;
		//TextView tvParkplatzLaengeDez;
		String ParkplatzBreiteDezString;
		String ParkplatzLaengeDezString;
	    boolean ParkplatzNordSuedVar;
		boolean ParkplatzOstWestVar;
		
		
		//TextView tvMagKompass;
		String MagKompassSting;
		double MagKompass;
		//TextView tvBewRichtungGrad;
		
		//TextView tvEntfernung;
		String EntfernungString;
		double Entfernung;
		
		//TextView tvRichtung;
		String RichtungString;
		double Richtung;
		double RichtungDrehGrad;
		double RichtungDEG;
		double BewRichtungDEG;
		
		//TextView tvRichtungGrad;
		String RichtungGradString;
		double RichtungGrad;
		double BewRichtungGrad;
		
		//TextView tvAbweichung;
		String AbweichungString;
		float Abweichung;
			
		String NaviSprachAusgabe; 
		String NaviSprachAusgabeZusatz = " ";   
		
		private TextToSpeech tts;
		
		LocationManager locationManager;
		LocationListener locationListener;
			
		double offsetRichtung;
		//TextView tvEntfernungsEinheit;
		String AnsageEntfernungsEinheit;
		
		String intentData;
		String btEinstellungen = "Einstellungen";
		//TextView tvKorrekturwinkel;
		double Korrekturwinkel;  
		String KorrekturwinkelString;
	
		int NaviAusDisplay;
		String NaviAusDisplayString;
		
		int NaviAnsageInterval;   //interne Variable, kann sich z.B. bei Entf. unter 10 Meter �ndern
		int NaviAnsageIntervalSoll;  //vom User vorgegebener/eingestellter Sollwert
		int NaviAnsageZaehler;    
		boolean NaviAnsageEin;   
		long NaviAnsageStartZeit; //f�r Zeitsteuerung der Ansagen, wird bei Navistart und nach jeder Ansage auf aktuelle Systemzeit gesetzt
		
		int NaviAnsageAutomatischAus;   
		int aufDisplayGedreht;   
		double zAxe; //Ab Wert kleiner -9 Ger�t auf Display gedreht
		double xAxe;
		double yAxe;
		int NaviAusZaehler;    
		long NaviAusDisplayStartZeit;   //f�r TimeOut Navi beenden wenn Handy auf Display gedreht
		int ZeitAnsageZaehler;   
		long ZeitAnsageStartZeit;   
		long ZeitAnsageTimeOutStartZeit;  //f�r Begrenzung der Zeitansage bei Display-Lage
		int LinksLageZaehler;  
		long LinksLageStartZeit;   
		int RechtsLageZaehler;   
		long RechtsLageStartZeit;    
		int KopfLageZaehler;    
		long KopfLageStartZeit;	   
		boolean GPSein;   
		
		int layoutConfig;  //0=Navigation, 11=Einstellungen (Seite1), 21=Info (Seite1), 22=Info (Seite2)...
		
		//TextView tvAnsageInterval;
		String zeitAnsage;
		double test;
		String NaviAnsageIntervalString;
		
		//Variablen f�r Checkboxen
		boolean cbNaviLinksRechtsVar;
		boolean cbDatumZeitVar;
		boolean cbBewRichtungEinVar;
		boolean cbParkplatzAutomatischSpeichernVar;
		boolean satFix;
		boolean BewRichtungOK;
		boolean ParkplatzManuellGespeichert; 
		boolean OnCreateModus;	
		
		//Variablen f�r Teilrechnungen
		double PosBreiteDezRAD;
		double PosLaengeDezRAD;
		double ZielBreiteDezRAD;
		double ZielLaengeDezRAD;			
		double tr1;
		double tr2;
		double eWinkel; // in RAD
		double RichtungRAD;
		double VorPosBreiteDezRAD;
		double VorPosLaengeDezRAD;
		double tr21;
		double tr22;
		double eBewWinkel; // in RAD
		double BewRichtungRAD;
		
		//Zeit-Variablen f�r Timeouts
		long kleiner7kmhZeit;   //public static long 
		boolean ParkplatzAutomatischSpeichern;   
		long groesser15kmhZeit;
		boolean groesser15kmhFuer15Sek; 
		boolean groesser15kmh;   
		
		//Sonstige Merker-Variablen
		boolean IntentTypOK;
		boolean DoppeltePausenlaenge;
		boolean DoppeltePausenlaengeGeradeVerstellt;

		String IventInfo;
	
	private GCBabbleServicePBinder meinBinderP = new GCBabbleServicePBinder();
	
	public class GCBabbleServicePBinder extends Binder {
			// Schnittstellenmethoden f�r den Service (wird momentan nicht verwendet)
	}
	@Override
	public IBinder onBind(Intent intent) {
		return meinBinderP;
	}

	
	@Override
	public void onCreate() {
		super.onCreate();
		Log.d(TAG, "GCBabbleServiceP: onCreate() aufgerufen");
		//Toast.makeText(getApplicationContext(), "GCBabbleServiceP erstellt*", Toast.LENGTH_SHORT).show();
				
		
		//Location-Manager vorbereiten
		locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		locationListener = new locationListener();
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
		GPSein = true;
					
		//Sprachausgabe vorbereiten (ohne Pr�fung auf Vorhandensein von TTS)
		tts = new TextToSpeech(this, this);
		
		
	
		
	}//end public void onCreate() ***************************
	
	
	
	@Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v("WhatTheDroidService", System.currentTimeMillis()
                + ": WhatTheDroidService gestartet.");
        
        //Toast.makeText(getApplicationContext(), "GCBabbleService gestartet*", Toast.LENGTH_SHORT).show();

        //----------------------------------------------------------------------------------- 
        // + + + + + +  StartForeground... erst ab Version 2.0 lauff�hig !!!!!! + + + + + + + 
        //-----------------------------------------------------------------------------------
       
        //vorab Extradaten f�r "Untertitel" ForegroundService holen
        //IventInfo = intent.getStringExtra("IventInfo");
        
      //Zu startetender Intent beim Dr�cken auf die ge�ffnete Notification
       Intent meinForegroundIntent = new Intent(this, GCBabbleIntentMain.class);
       meinForegroundIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
       //meinForegroundIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
       
       PendingIntent meinPendIntent = PendingIntent.getActivity(this, 0, meinForegroundIntent, 0);

       //Anzeige in der "Taskleiste" bei der Aktivierung des Services
       Notification notice = new Notification(R.drawable.parkplatz, "Automatische Parkplatz-Erkennung aktiv...", System.currentTimeMillis());

       //�berschrift und Untertitel in der der ge�ffneten Service-�bersicht (Notification)
       notice.setLatestEventInfo(this, "Parkplatz-Erkennung aktiv", "Aus: [Menu] > [Beenden] oder [Einstellungen]", meinPendIntent);

       notice.flags |= Notification.FLAG_NO_CLEAR;
       startForeground(myID, notice);

        
       
        //Kein Neustartversuch durch Android falls MainActivity gekillt wird, 
    	//da bei Intentstart gewollt per system exit alles abgew�rgt wird (gab sonst Probleme mit "Parallel-Lauf" der "gleichen" Activity
        return START_NOT_STICKY;
        
    } //end public int onStartCommand
	
	
	
	// GPS / Location-Listener aktivieren
	public class locationListener implements LocationListener {
		@Override
		//***************************************************************************************************
		//          GPS LOCATION CHANGED  ++++  KOORDINATEN-UPDATE ++++++  BERECHNUNGEN STARTEN
		//***************************************************************************************************
		public void onLocationChanged(Location location) {

			//Geschwindigkeit aktualisieren
			location.getSpeed();
			float GeschwindigkeitMpS = location.getSpeed();
			float GeschwindigkeitKpH = (float) (GeschwindigkeitMpS * 3.6);
			////DecimalFormat df6 = new DecimalFormat("0"); //Dezimalzahl formatieren
			////String GeschwindigkeitKpHString = df6.format(GeschwindigkeitKpH); //Dezimalzahl formatieren
		
			//Genauigkeit aktualisieren
			location.getAccuracy();
			Abweichung = location.getAccuracy();
			
			
		
			//wenn gr��er 15 km/h
			if (GeschwindigkeitKpH > 15 && Abweichung <= 25){
				if (gr15kmhZeit <= 1) {
					gr15kmhZeit = System.currentTimeMillis();
				}
				if (gr15kmhZeit <= (System.currentTimeMillis() - 15000)){
					gr15kmh15s = true;
				}
			}
			else { //wenn kleiner 15 km/h
				gr15kmhZeit = 0;
			}
		
			//wenn kleiner 7 km/h
			if (GeschwindigkeitKpH < 8 && gr15kmh15s == true && Abweichung <= 25){
				if (kl7kmhNach15kmhZeit <= 1){
					kl7kmhNach15kmhZeit = System.currentTimeMillis();
				}
				if (kl7kmhNach15kmhZeit <= (System.currentTimeMillis() - 28000)){
					//Parkplatz automatisch speichern
					location.getLatitude();
					location.getLongitude();
					ParkplatzBreiteDez = location.getLatitude();
					ParkplatzLaengeDez = location.getLongitude();
					
					DecimalFormat df33 = new DecimalFormat("0.00000000"); //Dezimalzahl formatieren
					ParkplatzBreiteDezString = df33.format(ParkplatzBreiteDez); //Dezimalzahl formatieren
					ParkplatzBreiteDezString = ParkplatzBreiteDezString.replace(",","."); //Komma durch Punkt ersetzen
					DecimalFormat df34 = new DecimalFormat("0.00000000"); //Dezimalzahl formatieren
					ParkplatzLaengeDezString = df34.format(ParkplatzLaengeDez); //Dezimalzahl formatieren
					ParkplatzLaengeDezString = ParkplatzLaengeDezString.replace(",","."); //Komma durch Punkt ersetzen
					
					//Festlegung der L�ngen-/Breitengrade der Parkplatzkoordinaten 
					if (ParkplatzBreiteDez <= 0){
						//tvParkplatzNordSued.setText("S"); 
						ParkplatzNordSuedVar = true;
						//Toast.makeText(getApplicationContext(),  "S Breite", Toast.LENGTH_LONG).show();

					}
					else{
						//tvParkplatzNordSued.setText("N"); 
						ParkplatzNordSuedVar = false;
						//Toast.makeText(getApplicationContext(),  "N Breite", Toast.LENGTH_LONG).show();

					}

					if (ParkplatzLaengeDez <= 0){
						//tvParkplatzOstWest.setText("W");
					    ParkplatzOstWestVar = true;
						//Toast.makeText(getApplicationContext(),  "W Länge", Toast.LENGTH_LONG).show();

					}
					else{
						//tvParkplatzOstWest.setText("E");
						ParkplatzOstWestVar = false;
						//Toast.makeText(getApplicationContext(),  "E Länge", Toast.LENGTH_LONG).show();

					}

					
					
					//**************************************************************************
					//Parkplatzkoordinaten per Intentstart an Activity zum Speichern �bergeben
					//**************************************************************************
					
					Intent meinParkplatzSpeichernIntent = new Intent();
					meinParkplatzSpeichernIntent.setClass(getApplicationContext(), GCBabbleParkplatzSpeichern.class);
					meinParkplatzSpeichernIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
							  
					meinParkplatzSpeichernIntent.putExtra("ParkplatzBreiteDezString", ParkplatzBreiteDezString);
					meinParkplatzSpeichernIntent.putExtra("ParkplatzLaengeDezString", ParkplatzLaengeDezString);
					meinParkplatzSpeichernIntent.putExtra("IntentAbsender", "GCBabbleServiceParkplatz");
					meinParkplatzSpeichernIntent.putExtra("ParkplatzNordSuedVar", ParkplatzNordSuedVar);
					meinParkplatzSpeichernIntent.putExtra("ParkplatzOstWestVar", ParkplatzOstWestVar);
				
				
					startActivity(meinParkplatzSpeichernIntent);
				
					Toast.makeText(getApplicationContext(),  "Parkplatz automatisch gespeichert.", Toast.LENGTH_LONG).show();
					tts.speak("Parkplatz gespeichert.",TextToSpeech.QUEUE_ADD, null);
			
					gr15kmh15s = false;
				}
			}
			else { //wenn gr��er 7 kmh
				kl7kmhNach15kmhZeit = 0;
			}
			
		}//end public void onLocationChanged(Location location)
		
		
		
		
		@Override
		public void onProviderDisabled(String provider) {
		}

		@Override
		public void onProviderEnabled(String provider) {
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
		}		

	}// end public class MyLocationListener
	

	@Override
	public void onDestroy() {
		Log.d(TAG, "GCBabbleService: onDestroy() aufgerufen");
		//Toast.makeText(getApplicationContext(), "GCBabbleService beendet", Toast.LENGTH_LONG).show();
		super.onDestroy();
	
		NaviAnsageEin = false;
		NaviAnsageAutomatischAus = 1;
		
		////mSensorManager.unregisterListener(mSensorEventListener); //SensorManager beenden
		locationManager.removeUpdates(locationListener);
		locationManager = null;
		stopForeground(true); //Versuch 08.03.12
		
	}

	@Override
	public void onInit(int arg0) { //h�ngt mit ...implements OnInitListener... unf TTS zusammen, �berpr�fung entf�llt hier
		// TODO Auto-generated method stub
		
	}

}
