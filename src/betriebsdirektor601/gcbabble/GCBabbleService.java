package betriebsdirektor601.gcbabble;

/* INFO:
-HintergrundService
-wird gestartet durch GCBabbleMain beim Navigieren
-beinhaltet keine visuellen Geschichten, 
 Naviansage / Sprachausgabe, 
 Intervall-Regelung, 
 läuft als permanenter Service im Hintergrund
*/

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;
import android.os.Vibrator;

import java.util.List;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.text.format.DateFormat;
import java.text.DecimalFormat;


public class GCBabbleService extends Service implements OnInitListener
{
	// TAG (Quell/Absender-Name) der LogCat-Meldungen generieren (wird angeblich immer so gemacht)
	private String TAG = GCBabbleService.class.getSimpleName();
	
	
	final static int myID = 8152010;
	String Dateiname = "servicedaten";
	String TestdatenString = "Testdaten";
	Integer TestInteger = 1;
	
	
	//verwendete Komponenten und Variablen initialisieren 

		String ZielBreiteGradString;
		float ZielBreiteGrad;
		
		String ZielBreiteMinDezString;
		float ZielBreiteMinDez;//
		
		String ZielLaengeGradString;
		float ZielLaengeGrad;//
		
		String ZielLaengeMinDezString;
		float ZielLaengeMinDez;//
			
		String ZielBreiteDezString;
		double ZielBreiteDez;
		
		String ZielLaengeDezString;
		double ZielLaengeDez;
		
		String PosBreiteDezString;
		float PosBreiteDez;
		double VorPosBreiteDez;
		double VorVorPosBreiteDez;
		double GehPos1BreiteDez;
		
		String PosLaengeDezString;
		float PosLaengeDez;
		double VorPosLaengeDez;
		double VorVorPosLaengeDez;
		double GehPos1LaengeDez;
			
		double ParkplatzBreiteDez;  
		double ParkplatzLaengeDez;   
		boolean ParkplatzMerken;   
		String ParkplatzBreiteDezString;
		String ParkplatzLaengeDezString;
		
		String MagKompassSting;
		double MagKompass;
		
		String EntfernungString;
		double Entfernung;
		double EntfernungMeter;
		
		String RichtungString;
		double Richtung;
		double RichtungDrehGrad;
		double RichtungDEG;
		double BewRichtungDEG;
		
		String RichtungGradString;
		double RichtungGrad;
		double BewRichtungGrad;
		
		String AbweichungString;
		float Abweichung;
		
		float GpsBearingGrad;
		float LetzteBewRtg;
		String LetzteBewRtgString;
		float GeschwindigkeitKpH;
			
		String NaviSprachAusgabe;   
		String NaviSprachAusgabeZusatz = " ";   
		private static SensorManager mSensorManager; //f�r Kompass-Sensor
		private SensorManager mASensorManager; //f�r ACC-Sensor
		private TextToSpeech tts;
		//private int MY_DATA_CHECK_CODE;//////= 0
		
		LocationManager locationManager;
		LocationListener locationListener;
			
		double offsetRichtung;
		String AnsageEntfernungsEinheit;
		
		String intentData;
		String btEinstellungen = "Einstellungen";
		double Korrekturwinkel;  
		String KorrekturwinkelString;
		
		int NaviAusDisplay; 
		String NaviAusDisplayString;
		
		int NaviAnsageIntervall;   //interne Variable, kann sich z.B. bei Entf. unter 10 Meter �ndern
	int NaviAnsageIntervallSoll;  //vom User vorgegebener/eingestellter Sollwert
		int NaviAnsageZaehler;   
		boolean NaviAnsageEin;   
		long NaviAnsageStartZeit;  //f�r Zeitsteuerung der Ansagen, wird bei Navistart und nach jeder Ansage auf aktuelle Systemzeit gesetzt
		
		int NaviAnsageAutomatischAus; 
		int aufDisplayGedreht;  
		double zAxe; //Ab Wert kleiner -9 Ger�t auf Display gedreht
		double xAxe;
		double yAxe;
		int NaviAusZaehler;  
		long NaviAusDisplayStartZeit;   //f�r TimeOut Navi beenden wenn Handy auf Display gedreht
		int ZeitAnsageZaehler;  
		long ZeitAnsageStartZeit;   
		long ZeitAnsageTimeOutStartZeit;   //f�r Begrenzung der Zeitansage bei Display-Lage
		int LinksLageZaehler;   
		long LinksLageStartZeit; 
		int RechtsLageZaehler;   
		long RechtsLageStartZeit;  
		int KopfLageZaehler;   
		long KopfLageStartZeit;	  
		boolean GPSein; 
		
		//Merker für Config/Modus usw.
		int layoutConfig;    //0=Navigation, 11=Einstellungen (Seite1), 21=Info (Seite1), 22=Info (Seite2)...
	    int GehFahrHaltModus = 0; //0=Geh- 1=Fahr- 2=Halt- Modus
    	long gr15kmhZeit;
	    boolean gr15kmh15s;
	    long kl7kmhNach15kmhZeit;
		double xAxeBeiHalt;
		double yAxeBeiHalt;
		double zAxeBeiHalt;
		double MagKompassBeiHalt;
		
	
		String zeitAnsage;
		double test;
	    String NaviAnsageIntervallString;
		
		//Variablen f�r Checkboxen
		boolean cbNaviLinksRechtsVar;
		boolean cbDatumZeitVar;
		boolean cbBewRichtungEinVar;
		boolean cbParkplatzAutomatischSpeichernVar;
		boolean cbKeinMagnetKompassVar;
		boolean cbGehFahrHaltErkennungVar;
    	boolean cbOptimiertesAnsageIntervallVar;
		
		boolean satFix;
		boolean BewRichtungOK;
		boolean ParkplatzManuellGespeichert;// = false; 
		boolean OnCreateModus;	
		boolean bNordSuedVar;
		boolean bOstWestVar;
		
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
		long kleiner7kmhZeit;   //ggf. public static long 
		boolean ParkplatzAutomatischSpeichern;    // = false;
		long groesser15kmhZeit;    //als PREFERENCES speichern 
		boolean groesser15kmhFuer15Sek;    // = false;
		boolean groesser15kmh;   // = false;
		
		//Sonstige Merker-Variablen
		boolean IntentTypOK;
		boolean DoppeltePausenlaenge;
		boolean DoppeltePausenlaengeGeradeVerstellt;
		boolean GehFahrHaltErkennungEinAnsageErl;
		boolean FertigFuerAnsage; //Behebung von Richtungsfalschansage beim ersten Durchlauf
        
		String IventInfo;
	
	private GCBabbleServiceBinder meinBinder = new GCBabbleServiceBinder();
	
	public class GCBabbleServiceBinder extends Binder {
			// Schnittstellenmethoden f�r den Service (wird momentan nicht verwendet)
	}
	@Override
	public IBinder onBind(Intent intent) {
		return meinBinder;
	}
		
	@Override
	public void onCreate() {
		super.onCreate();
		Log.d(TAG, "GCBabbleService: onCreate() aufgerufen");
		//Toast.makeText(getApplicationContext(), "GCBabbleService erstellt*", Toast.LENGTH_SHORT).show();
				
		//Compass Sensor-Manager vorbereiten
		mSensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
		List<Sensor> mSensors = mSensorManager.getSensorList(Sensor.TYPE_ORIENTATION);
		mSensorManager.registerListener(mSensorEventListener, mSensors.get(0), SensorManager.SENSOR_DELAY_FASTEST);
				
		//ACCELEROMETER Sensor-Manager vorbereiten
		mASensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
		List<Sensor> mASensors = mASensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
		mASensorManager.registerListener(mASensorEventListener, mASensors.get(0), SensorManager.SENSOR_DELAY_NORMAL);
			
		//Location-Manager vorbereiten
		locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		locationListener = new locationListener();
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
		GPSein = true;
						
		//Sprachausgabe vorbereiten (ohne Pr�fung auf Vorhandensein von TTS)
		tts = new TextToSpeech(this, this);	
		
	}//end public void onCreate() 
	
	
	
	@Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v("WhatTheDroidService", System.currentTimeMillis()
                + ": WhatTheDroidService gestartet.");
        
        //Toast.makeText(getApplicationContext(), "GCBabbleService gestartet*", Toast.LENGTH_SHORT).show();

        //----------------------------------------------------------------------------------- 
        // + + + + + +  StartForeground... erst ab Version 2.0 lauff�hig !!!!!! + + + + + + + 
        //-----------------------------------------------------------------------------------
       
        //vorab Extradaten f�r "Untertitel" ForegroundService holen
        IventInfo = intent.getStringExtra("IventInfo");
        
       //Zu startetender Intent beim Dr�cken auf die ge�ffnete Notification
       Intent meinForegroundIntent = new Intent(this, GCBabbleIntentMain.class);
       meinForegroundIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
       //meinForegroundIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
       
       PendingIntent meinPendIntent = PendingIntent.getActivity(this, 0, meinForegroundIntent, 0);

       //Anzeige in der "Taskleiste" bei der Aktivierung des Services
       Notification notice = new Notification(R.drawable.icon, "GC-Babble Navigation gestartet...", System.currentTimeMillis());

       //�berschrift und Untertitel in der der ge�ffneten Service-�bersicht (Notification)
       notice.setLatestEventInfo(this, "GC-Babble Navigation", IventInfo, meinPendIntent);

       notice.flags |= Notification.FLAG_NO_CLEAR;
       startForeground(myID, notice);

         
		
        //**************************************************************************************
    	//     **** ***** ********* Extra-Daten empfangen **** ********* ********* ********
    	//**************************************************************************************
    		
    		
    		
    		if (intent != null){ //Pr�fung ob Intent-Extra-Daten �bertragen werden um Absturz bei Neustart ohne aufrufende Activity zu vermeiden
    			//Toast.makeText(getApplicationContext(), "Extradaten vorhanden*", Toast.LENGTH_SHORT).show();
    			
    			////String extradaten1 = intent.getStringExtra("extradaten1");
    			
    			//Einstellungen per Intent empfangen
    			KorrekturwinkelString = intent.getStringExtra("KorrekturwinkelString");
    			Korrekturwinkel = (double) Float.parseFloat(KorrekturwinkelString);
				NaviAusDisplayString = intent.getStringExtra("NaviAusDisplayString");
    			NaviAnsageIntervallString = intent.getStringExtra("NaviAnsageIntervallString");
    			cbNaviLinksRechtsVar = intent.getBooleanExtra("cbNaviLinksRechtsVar", true); 
    			cbDatumZeitVar = intent.getBooleanExtra("cbDatumZeitVar", true); 
    			cbBewRichtungEinVar = intent.getBooleanExtra("cbBewRichtungEinVar", true); 
    			cbParkplatzAutomatischSpeichernVar = intent.getBooleanExtra("cbParkplatzAutomatischSpeichernVar", true); 
    			cbKeinMagnetKompassVar = intent.getBooleanExtra("cbKeinMagnetKompassVar", false); 
    			bNordSuedVar = intent.getBooleanExtra("bNordSuedVar", false); 
    			bOstWestVar = intent.getBooleanExtra("bOstWestVar", false); 
				cbGehFahrHaltErkennungVar = intent.getBooleanExtra("cbGehFahrHaltErkennungVar", true);
				cbOptimiertesAnsageIntervallVar = intent.getBooleanExtra("cbOptimiertesAnsageIntervallVar", true);
		
    			//Ziel-Koordinaten per Intent empfangen
    			ZielBreiteDez = 0; //Voreinstellung, falls keine Extradaten vohanden
    			ZielLaengeDez = 0; //Voreinstellung, falls keine Extradaten vohanden
    			ZielBreiteDezString = intent.getStringExtra("ZielBreiteDezString");
    			ZielLaengeDezString = intent.getStringExtra("ZielLaengeDezString");
    			ZielBreiteDez = Float.parseFloat(ZielBreiteDezString); //String zu float
    			ZielLaengeDez = Float.parseFloat(ZielLaengeDezString); //String zu float	
    		    			
    			//Aktuelle Variablen per Intent empfangen
    			NaviAnsageEin = intent.getBooleanExtra("NaviAnsageEin", false); 
    			
    			//Diagnose Anzeige Extradaten
    			//Toast.makeText(getApplicationContext(),  ZielBreiteDezString, Toast.LENGTH_SHORT).show();
    			//Toast.makeText(getApplicationContext(),  ZielLaengeDezString, Toast.LENGTH_SHORT).show();
    			
    			   			
    		}//end if (intent != null)
    		
    	
        //Kein Neustartversuch durch Android falls MainActivity gekillt wird, 
    	//da bei Intentstart gewollt per system exit alles abgew�rgt wird (gab sonst Probleme mit "Parallel-Lauf" der "gleichen" Activity
        return START_NOT_STICKY;
        
    } //end public int onStartCommand
	
	

	//***************************************************************************
	//   <<<<<<<<<<<<<<<<   Kompass-Sensor-Daten-Update  <<<<<<<<<<<<<<<<<<
	//***************************************************************************

	private SensorEventListener mSensorEventListener = new SensorEventListener(){	
		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy){
		}

		public void onSensorChanged(SensorEvent event)
		{

			MagKompass = (event.values[0]); // Kompass-Variable aktualisieren

			//Im Halt-Modus MagKompass durch LetzteBewRtg ersetzen
			if (GehFahrHaltModus == 2 &&
			    LetzteBewRtg != 0){
			    MagKompass = LetzteBewRtg;
		    }//end Kompass ersetzen

			if ((cbBewRichtungEinVar == true && GeschwindigkeitKpH >= 7 && Abweichung <= 60)
				|| (cbBewRichtungEinVar == true && GeschwindigkeitKpH >= 5 && Abweichung <= 8)
				|| (cbBewRichtungEinVar == true && GeschwindigkeitKpH >= 2 && Abweichung <= 4)
				|| cbKeinMagnetKompassVar == true
				////////////////////////////////////////////////////////
				&& GehFahrHaltModus == 1){
				////GehfahrHaltModus NEU als Bedingung eingefügt, wird in Activity nicht abgefragt
			    ////außerdem später GehFahrHaltModus-Erkennung abstufen wie bei BewRtg
					
				//bleibt frei, da Inhalt in GPS onLocationChanged verlegt wurde...
			}
			else{
				//Drehrichtung berechnen und anzeigen 
				RichtungDrehGrad = Math.abs(RichtungDEG) - MagKompass + Korrekturwinkel; //Berechnung der Drehrichtung in Grad
				if (RichtungDrehGrad < 1) { RichtungDrehGrad = RichtungDrehGrad + 360; } //Korrektur f�r Wert unter 0
				if (RichtungDrehGrad > 360) { RichtungDrehGrad = RichtungDrehGrad - 360; } //Korrektur f�r Wert �ber 360
				Richtung = RichtungDrehGrad / 30; //Berechnung der Drehrichtung in Uhr-Wert
				DecimalFormat df1 = new DecimalFormat("0"); //Dezimalzahl formatieren
				RichtungString = df1.format(Richtung); //Dezimalzahl formatieren
				//tvRichtung.setText(String.valueOf(RichtungString));
				
			}//end else

            //MagKompass-Variable erneut den aktuellen Kompass-Wert verpassen
			//ist nötig, da oben "gefriemelt" & Kompasswert im Halt-Modus durch letzte Bew.Rtg. ersetzt wird
			MagKompass = (event.values[0]); // Kompass-Variable aktualisieren
			
		

		}//end public void onSensorChanged
	};// end private SensorEventListener mSensorEventListener = new SensorEventListener()

	
	// Lage/ACCELEROMETER-Sensor-Daten-Update
	private SensorEventListener mASensorEventListener = new SensorEventListener(){	
		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy){
		}
		
		public void onSensorChanged(SensorEvent event) 
		{
			zAxe = event.values[2];//Z-Axe f�r Display-Lage
			xAxe = event.values[0];//X-Axe f�r Links/Rechts
			yAxe = event.values[1];//y-Axe f�r auf Kopf
		}
	};// end private private SensorEventListener mASensorEventListener = new SensorEventListener()
	
	
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
			GeschwindigkeitKpH = (float) (GeschwindigkeitMpS * 3.6);
						
			//Genauigkeit aktualisieren, String wird f�r Ansage ben�tigt
			location.getAccuracy();
			Abweichung = location.getAccuracy();
			DecimalFormat df4 = new DecimalFormat("0"); //Dezimalzahl formatieren
			AbweichungString = df4.format(Abweichung); //Dezimalzahl formatieren
			////tvAbweichung.setText(String.valueOf(AbweichungString));
			
			//Bewegungsrichtung aktualisieren/merken
			location.getBearing();
			GpsBearingGrad = location.getBearing();
			//Letzte Bewegungsrichtung merken
			if ((GeschwindigkeitKpH >= 12 && Abweichung <= 60)
				|| (GeschwindigkeitKpH >= 10 && Abweichung <= 8)
				|| (GeschwindigkeitKpH >= 8 && Abweichung <= 4)){	// Letzte Bewegungsrichtung merken
				LetzteBewRtg = GpsBearingGrad;
			}
				
			//Variablen und TextViews aktualisieren
			location.getLatitude();
			location.getLongitude();
			String PosBreiteDezString = "" + location.getLatitude();
			String PosLaengeDezString = "" + location.getLongitude();
			PosBreiteDez = Float.parseFloat(PosBreiteDezString); //String zu float
			PosLaengeDez = Float.parseFloat(PosLaengeDezString); //String zu float	
			
							
							
		//*********************************************
		//         GehFahrHalt-Modus setzen
		//*********************************************
		
			//wenn gr��er 15 km/h
			if (   (GeschwindigkeitKpH > 15 && Abweichung <= 20)
				|| (GeschwindigkeitKpH > 30 && Abweichung <= 70)
			    && cbGehFahrHaltErkennungVar == true){
		
				//wenn noch keine Zeit gemerkt, dann Zeit merken
				if (gr15kmhZeit <= 1) {
					gr15kmhZeit = System.currentTimeMillis();
				}
				//wenn Zeit älter als 15 Sekunden & Fahr-Modus nicht ein, dann Fahr-Modus ein
				if (gr15kmhZeit <= (System.currentTimeMillis() - 15000) &&
				    GehFahrHaltModus != 1){
					gr15kmh15s = true;
					GehFahrHaltModus = 1; //FahrModus aktiviert
					Toast.makeText(getApplicationContext(),  "Fahr-Modus aktiviert.", Toast.LENGTH_LONG).show();
					//tts.speak("Fahrmodus aktiviert.",TextToSpeech.QUEUE_ADD, null);
				    
					//Bei optomiertem Ansageintervall SOFORTIGE Ansage (nach 10 Sek.)
					if (cbOptimiertesAnsageIntervallVar == true){
					    NaviAnsageStartZeit = System.currentTimeMillis() - ((NaviAnsageIntervall - 10) * 1000); //NaviAnsageStartZeit auf "in 1 Sekunden" setzen
					} //
				}//end if 15 Sekunden 15 km/h
			}//end if größer 15 km/h
			
			else { //wenn kleiner 15 km/h
				gr15kmhZeit = 0;
			}

			//wenn kleiner 7 km/h
			if (  (GeschwindigkeitKpH < 8 && gr15kmh15s == true && Abweichung <= 25)
			    && cbGehFahrHaltErkennungVar == true){
				
				//wenn noch keine Zeit gemerkt, dann Zeit merken
				if (kl7kmhNach15kmhZeit <= 1){
					kl7kmhNach15kmhZeit = System.currentTimeMillis();
				}
				//wenn Halte-Zeit früher als vor 0,5 Sekunden & Halt-Modus aus, dann Halt-Modus ein
				if (kl7kmhNach15kmhZeit <= (System.currentTimeMillis() - 500) &&
				    GehFahrHaltModus != 2){
					GehFahrHaltModus = 2; //Halt-Modus aktiviert
					Toast.makeText(getApplicationContext(),  "Halt-Modus aktiviert.", Toast.LENGTH_LONG).show();
					//tts.speak("Haltmodus aktiviert.",TextToSpeech.QUEUE_ADD, null);
					
					//////////////////////////////
					/* VORERST DEAKTIVIERT Bei optimiertem Ansageintervall SOFORTIGE Ansage (nach 0 Sek.)
					 if (cbOptimiertesAnsageIntervallVar == true){
					 NaviAnsageStartZeit = System.currentTimeMillis() - ((NaviAnsageIntervall - 0) * 1000); //NaviAnsageStartZeit auf "in 1 Sekunden" setzen
					}
					*/
					
					gr15kmh15s = false;
					
					//Gerätelage merken
					xAxeBeiHalt = xAxe; //Links/Rechts
					yAxeBeiHalt = yAxe; //Auf Kopf
					zAxeBeiHalt = zAxe; //Displaylage
					
					//Magnetkompass merken um bei Richtungsänderung während Langsamfahrt den Gehmodus zu aktivieren
					MagKompassBeiHalt = MagKompass;
					
				}
				//Halt-Modus automatisch rücksetzen nach 5 Min unter 7 km/h
				if (kl7kmhNach15kmhZeit <= (System.currentTimeMillis() - 300000) &&
				    GehFahrHaltModus == 2){
					GehFahrHaltModus = 0; //Halt-Modus deaktiviert
					Toast.makeText(getApplicationContext(),  "Geh-Modus aktiviert.", Toast.LENGTH_LONG).show();
					tts.speak("Gehmodus aktiviert.",TextToSpeech.QUEUE_ADD, null);
				
				    //Bei optomiertem Ansageintervall SOFORTIGE Ansage (nach 6 Sek.)
					if (cbOptimiertesAnsageIntervallVar == true){
					    NaviAnsageStartZeit = System.currentTimeMillis() - ((NaviAnsageIntervall - 6) * 1000); //NaviAnsageStartZeit auf "in 1 Sekunden" setzen
					}
				}
			}
			else { //wenn gr��er 7 kmh
				kl7kmhNach15kmhZeit = 0; 
				//Fahrmodus einschalten wenn vorher Halt
				if (GehFahrHaltModus == 2 && 
					GeschwindigkeitKpH > 8 && Abweichung <= 25 ){
					//Zeitmerker manipulieren, um Fahr-Modus sofort bei 15 km/h wieder zu aktivieren (oben)
					gr15kmhZeit = System.currentTimeMillis() - 15000;
					
				}
				
			}//end größer 7 km/h
			
			
			//aus Halt-Modus den Geh-Modus aktivieren bei Lageänderung
			//Erkennung der Lageänderung, wenn Lage-Werte oben gespeichert worden
			if ( GehFahrHaltModus == 2
			  && xAxeBeiHalt != 0
		      && yAxeBeiHalt != 0
			  && zAxeBeiHalt != 0){
				  
			    if (xAxe >= (xAxeBeiHalt + 8)
				|| xAxe <= (xAxeBeiHalt - 8)
				|| yAxe >= (yAxeBeiHalt + 8)
				|| yAxe <= (yAxeBeiHalt - 8)
				|| zAxe >= (zAxeBeiHalt + 8)
				|| zAxe <= (zAxeBeiHalt - 8)){
			    //|| MagKompass >= (MagKompassBeiHalt + 45)
				//|| MagKompass <= (MagKompassBeiHalt - 45)){
					
				GehFahrHaltModus = 0; //Halt-Modus deaktiviert
			   
				Toast.makeText(getApplicationContext(),  "Geh-Modus aktiviert durch Lageänderung.", Toast.LENGTH_LONG).show();
				//tts.speak("Gehmodus aktiviert durch Lage-änderung.",TextToSpeech.QUEUE_ADD, null);
				
			    	//Bei optomiertem Ansageintervall SOFORTIGE Ansage (nach 6 Sek.)
				if (cbOptimiertesAnsageIntervallVar == true){
					NaviAnsageStartZeit = System.currentTimeMillis() - ((NaviAnsageIntervall - 6) * 1000); //NaviAnsageStartZeit auf "in 1 Sekunden" setzen
				    }
			    }
		    }
			
			//testweise getrennt zur Analyse, wird so beibehalten um eine rükcksetzung auch ohne Magnetkompass zu ermöglichen
			
			//Erkennung der Richtungsänderung bei unter 7 km/h, wenn LageWerte gespeichert wurden
			if ( GehFahrHaltModus == 2
				&& xAxeBeiHalt != 0
				&& yAxeBeiHalt != 0
				&& zAxeBeiHalt != 0){

			    if (//xAxe >= (xAxeBeiHalt + 6)
					//|| xAxe <= (xAxeBeiHalt - 6)
					//|| yAxe >= (yAxeBeiHalt + 6)
					//|| yAxe <= (yAxeBeiHalt - 6)
					//|| zAxe >= (zAxeBeiHalt + 6)
					//|| zAxe <= (zAxeBeiHalt - 6)
					   cbKeinMagnetKompassVar == false
					&& (MagKompass >= (MagKompassBeiHalt + 30)
					|| MagKompass <= (MagKompassBeiHalt - 30))
					&& (GeschwindigkeitKpH <= 7 && Abweichung <= 25) ){

					GehFahrHaltModus = 0; //Halt-Modus deaktiviert

					Toast.makeText(getApplicationContext(),  "Geh-Modus aktiviert durch Richtungsänderung.", Toast.LENGTH_LONG).show();
					//tts.speak("Gehmodus aktiviert durch Richtungs-änderung.",TextToSpeech.QUEUE_ADD, null);

					//Bei optomiertem Ansageintervall SOFORTIGE Ansage (nach 6 Sek.)
					if (cbOptimiertesAnsageIntervallVar == true){
					    NaviAnsageStartZeit = System.currentTimeMillis() - ((NaviAnsageIntervall - 6) * 1000); //NaviAnsageStartZeit auf "in 1 Sekunden" setzen
					}
			    }
		    }
		
			//***************Ende GehFahrHalt-Modus*************	
							
							
							
			//Umrechnunmg in RAD
			PosBreiteDezRAD = Math.toRadians(PosBreiteDez);
			PosLaengeDezRAD = Math.toRadians(PosLaengeDez);
			ZielBreiteDezRAD = Math.toRadians(ZielBreiteDez);
			ZielLaengeDezRAD = Math.toRadians(ZielLaengeDez);			
			tr1 = ((Math.sin(PosBreiteDezRAD)) * (Math.sin(ZielBreiteDezRAD)));
			tr2 = ((Math.cos(PosBreiteDezRAD)) * (Math.cos(ZielBreiteDezRAD)) * (Math.cos(ZielLaengeDezRAD - PosLaengeDezRAD)));
			eWinkel = Math.acos(tr1 + tr2); // in RAD
			Entfernung = (eWinkel * 6378.137 * 1000); //Entfernung in Meter und e im Bogenma� / RAD
			Entfernung = Math.round(Entfernung);
			EntfernungMeter = Entfernung; //...da Entfernung über 1000 m in km umgerechnet wird
						
			//Berechnung Winkel / Richtung
			RichtungRAD = (Math.acos((Math.sin(ZielBreiteDezRAD) - Math.sin(PosBreiteDezRAD) * Math.cos(eWinkel))  / (Math.cos(PosBreiteDezRAD) * Math.sin(eWinkel))));
			RichtungDEG = Math.toDegrees(RichtungRAD);
				
			//Korrektur / Offset L�ngengrade
			if (PosLaengeDezRAD >= ZielLaengeDezRAD) {
				RichtungDEG = RichtungDEG - 360;
			}
			
			//Ausgabe der Ergebnisse von float �ber String zu TextView
			//Bei Entfernung gr��er 1000 in km umrechnen und Beschriftung und Ansage �ndern
			if (Entfernung > 1000){	
				Entfernung = Entfernung / 1000;
				////tvEntfernungsEinheit.setText(String.valueOf(" km          "));
				AnsageEntfernungsEinheit = "Kilometer";
				DecimalFormat df2 = new DecimalFormat("0.0"); //Dezimalzahl formatieren
				EntfernungString = df2.format(Entfernung); //Dezimalzahl formatieren
			}
			else{	
				////tvEntfernungsEinheit.setText(String.valueOf(" m          "));
				AnsageEntfernungsEinheit = "";
				DecimalFormat df2 = new DecimalFormat("0"); //Dezimalzahl formatieren
				EntfernungString = df2.format(Entfernung); //Dezimalzahl formatieren
			}
			
			
			//Im Halt-Modus GPS-Kompass durch LetzteBewRtg ersetzen
			//im Zusammenspiel mit der GFH-Mod.-Bedingung (im MagKompass & GPS-Kompass) wird somit der Uebergang vom Fahren zum Halten überbrückt
			if (GehFahrHaltModus == 2 &&
			    LetzteBewRtg != 0){
				GpsBearingGrad = LetzteBewRtg;
		    }//end Kompass ersetzen
			
			
			//Drehrichtung �ber Bewegungs-Richtung berechnen
			if ((cbBewRichtungEinVar == true && GeschwindigkeitKpH >= 7 && Abweichung <= 60)
				|| (cbBewRichtungEinVar == true && GeschwindigkeitKpH >= 5 && Abweichung <= 8)
				|| (cbBewRichtungEinVar == true && GeschwindigkeitKpH >= 2 && Abweichung <= 4)
				|| cbKeinMagnetKompassVar == true
				////////////////////////////////////////////////////////
				&& GehFahrHaltModus == 1){
				////GehfahrHaltModus NEU als Bedingung eingefügt, ggf in Activity mit ändern
			    ////außerdem später GehFahrHaltModus-Erkennung abstufen wie bei BewRtg
					
					
				////RichtungDrehGrad = Math.abs(RichtungDEG) - Math.abs(BewRichtungDEG); //Berechnung der Drehrichtung in Grad beim Fahren
				RichtungDrehGrad = Math.abs(RichtungDEG) - Math.abs(GpsBearingGrad); //Berechnung der Drehrichtung in Grad beim Fahren
				if (RichtungDrehGrad < 1) { RichtungDrehGrad = RichtungDrehGrad + 360; } //Korrektur f�r Wert unter 0
				if (RichtungDrehGrad > 360) { RichtungDrehGrad = RichtungDrehGrad - 360; } //Korrektur f�r Wert �ber 360
				Richtung = RichtungDrehGrad / 30; //Berechnung der Drehrichtung in Uhr-Wert
				DecimalFormat df1 = new DecimalFormat("0"); //Dezimalzahl formatieren
				RichtungString = df1.format(Richtung); //Dezimalzahl formatieren
				////tvRichtung.setText(String.valueOf(RichtungString));
			}
			
			
			
			//---------------------------------------------------------------------------------	
			//------------------------------------------------------------------------------------------------------------------------------------------
			//************ Sprachausgabe Naviansage / Zeitansage bei Koordinaten-Update ***************************************************
			//------------------------------------------------------------------------------------------------------------------------------------------
			//---------------------------------------------------------------------------------
			
		//wenn auf rechter Seite, dann Navi aus
		if (xAxe <= -8 && NaviAnsageEin == true && cbNaviLinksRechtsVar == true){
			//RechtsLageZaehler = RechtsLageZaehler + 1;
			if (System.currentTimeMillis() >= (LinksLageStartZeit + 3000)){
				tts.speak("Stumm.",TextToSpeech.QUEUE_ADD, null);
				NaviAnsageEin = false;
				Toast.makeText(getApplicationContext(), "Rechte Seite zum Boden: Navi-Ansagen stumm", Toast.LENGTH_SHORT).show();
	    		//RechtsLageZaehler = 0;
				LinksLageStartZeit = System.currentTimeMillis(); //auf aktuelle Systemzeit setzen
				////bNaviStarten.setText("Navigation starten");
				////tvTextAusgabe.setText(String.valueOf("Navi beendet durch Rechts-Lage.")); //Textausgabe
			    //Vibration als Bestätigung
			    Vibrator v = (Vibrator) getSystemService
				(Context.VIBRATOR_SERVICE);
				v.vibrate( 100 );
			}
		}
		else{
			//RechtsLageZaehler = 0;
			LinksLageStartZeit = System.currentTimeMillis(); //auf aktuelle Systemzeit setzen
		}
		
		//wenn auf linker Seite, dann Navi ein	
		if (xAxe >= 8 && NaviAnsageEin == false && cbNaviLinksRechtsVar == true){
			//LinksLageZaehler = LinksLageZaehler +1;
			if (System.currentTimeMillis() >= (RechtsLageStartZeit + 3000)){
				tts.speak("Ansage.",TextToSpeech.QUEUE_ADD, null);
				NaviAnsageEin = true;
				Toast.makeText(getApplicationContext(), "Linke Seite zum Boden: Navi-Ansagen aktiviert", Toast.LENGTH_SHORT).show();
				//LinksLageZaehler = 0;
				RechtsLageStartZeit = System.currentTimeMillis(); //auf aktuelle Systemzeit setzen
				NaviAnsageZaehler = NaviAnsageIntervall - 3;
				NaviAnsageStartZeit = System.currentTimeMillis() - ((NaviAnsageIntervall - 3) * 1000); //NaviAnsageStartZeit auf "in 3 Sekunden" setzen
				////bNaviStarten.setText("Navigation beenden");
				////tvTextAusgabe.setText(String.valueOf("Navi gestartet durch Links-Lage.")); //Textausgabe
			    //Vibration als Bestätigung
			    Vibrator v = (Vibrator) getSystemService
				(Context.VIBRATOR_SERVICE);
				v.vibrate( 100 );
		    }
		}
		else{
			//LinksLageZaehler = 0;
			RechtsLageStartZeit = System.currentTimeMillis(); //auf aktuelle Systemzeit setzen
		}
		
		//---------------------------------------------------------------------------------------------
		//Dreifache Pausenl�nge, wenn auf Kopf gedreht
		//---------------------------------------------------------------------------------------
			
			//wenn auf Kopf, dann doppelte Pausenl�nge ein/aus
			if (yAxe <= -8 && NaviAnsageEin == true && cbNaviLinksRechtsVar == true){
				if (System.currentTimeMillis() >= (KopfLageStartZeit + 2000)){
					if (DoppeltePausenlaenge == false && DoppeltePausenlaengeGeradeVerstellt == false){
						DoppeltePausenlaenge = true;
						tts.speak("Weniger Ansagen.",TextToSpeech.QUEUE_ADD, null);
						DoppeltePausenlaengeGeradeVerstellt = true;
						Toast.makeText(getApplicationContext(), "Oberseite zum Boden: Weniger Ansagen", Toast.LENGTH_SHORT).show();
		    		    //Vibration als Bestätigung
						Vibrator v = (Vibrator) getSystemService
						(Context.VIBRATOR_SERVICE);
						v.vibrate( 100 );
					}
					else {//if (DoppeltePausenl�nge == false)
						if (DoppeltePausenlaengeGeradeVerstellt == false){//nur verstellen, wenn nicht gerade erst verstellt wurde
							DoppeltePausenlaenge = false;
							tts.speak("Mehr Ansagen.",TextToSpeech.QUEUE_ADD, null);
							Toast.makeText(getApplicationContext(), "Oberseite zum Boden: Mehr Ansagen", Toast.LENGTH_SHORT).show();
			    			NaviAnsageZaehler = NaviAnsageIntervall - 2;
							NaviAnsageStartZeit = System.currentTimeMillis() - ((NaviAnsageIntervall - 3) * 1000); //NaviAnsageStartZeit auf "in 3 Sekunden" setzen
							DoppeltePausenlaengeGeradeVerstellt = true;
						    //Vibration als Bestätigung
							Vibrator v = (Vibrator) getSystemService
							(Context.VIBRATOR_SERVICE);
							v.vibrate( 100 );
						}
					}
										
					KopfLageStartZeit = System.currentTimeMillis(); //auf aktuelle Systemzeit setzen
					////tvTextAusgabe.setText(String.valueOf("Pausenl�nge ge�ndert durch Kopf-Lage.")); //Textausgabe
				}
			}
			else{
				KopfLageStartZeit = System.currentTimeMillis(); //auf aktuelle Systemzeit setzen
				DoppeltePausenlaengeGeradeVerstellt = false;
			}
			
		
			
		//Zeitansage wenn auf Display gedreht
		if (zAxe <= -8 && cbDatumZeitVar == true) { 
			//Datum-/Zeitansage Text zusammenstellen
			NaviSprachAusgabe = (String) DateFormat.format("dd.MM. , kk , mm", new java.util.Date());
			NaviAusDisplay = (int) Float.parseFloat(NaviAusDisplayString); //String zu int
			
			
			if (NaviAnsageIntervall < 10){ //zu kurzes Intervall verl�ngern damit es nicht nervt
				NaviAnsageIntervall = NaviAnsageIntervall + 3;
			}
			
			if (System.currentTimeMillis() >= (ZeitAnsageStartZeit + (NaviAnsageIntervall * 1000)) && System.currentTimeMillis() <= (ZeitAnsageTimeOutStartZeit + 60000)){ //wenn Zeit in Display-Lage erreicht dann Zeitansage (max 60 s)
				tts.speak(NaviSprachAusgabe,TextToSpeech.QUEUE_ADD, null);
				Toast.makeText(getApplicationContext(), NaviSprachAusgabe, Toast.LENGTH_LONG).show();
				////ZeitAnsageZaehler = 0;
				ZeitAnsageStartZeit = System.currentTimeMillis(); //auf aktuelle Systemzeit setzen
			}
			if (System.currentTimeMillis() >= (NaviAusDisplayStartZeit + (NaviAusDisplay * 1000)) && NaviAnsageEin == true){ //wenn Zeit in Display-Lage erreicht dann Navi beenden NaviAusDisplay
				NaviAnsageEin = false;
				////bNaviStarten.setText("Navigation starten");
				////NaviAusDisplayZaehler = 0;
				NaviAusDisplayStartZeit = System.currentTimeMillis(); //auf aktuelle Systemzeit setzen
				tts.speak("Stumm.",TextToSpeech.QUEUE_ADD, null);
				Toast.makeText(getApplicationContext(), "Display f�r l�ngere Zeit zum Boden: Navi-Ansagen stumm", Toast.LENGTH_SHORT).show();
	    		////tvTextAusgabe.setText(String.valueOf("Navi beendet durch l�ngere Display-Lage.")); //Textausgabe
			}
		}//end if (aufDisplayGedreht != 0)	
			
		else { //Navi-Ansage wenn nicht auf Display gedreht
			//Zeit-Ansage-Z�hler zur�cksetzen wenn nicht mehr auf Display gedreht
			ZeitAnsageStartZeit = System.currentTimeMillis(); //auf aktuelle Systemzeit setzen
			NaviAusDisplayStartZeit = System.currentTimeMillis(); //auf aktuelle Systemzeit setzen
			ZeitAnsageTimeOutStartZeit = System.currentTimeMillis(); //auf aktuelle Systemzeit setzen
			
			//Zusammenstellung Text Sprachausgabe zur Navigation
			//wenn kein Magnetkompass genommen wird dann Ansage ohne Richtung im Stillstand
			if (cbKeinMagnetKompassVar == true && GeschwindigkeitKpH <= 4){
				NaviSprachAusgabe = ("Entfernung, " + EntfernungString + " " + AnsageEntfernungsEinheit + ". " + NaviSprachAusgabeZusatz);
			}//end if (cbKeinMagnetKompassVar == true
			else{ 
				if ("1".equals(RichtungString)) { //statt "einS Uhr" "EIN Uhr" sagen
					RichtungString = "Ein";
				}
				if ("0".equals(RichtungString)) { //statt "Null Uhr" "12 Uhr" sagen
					RichtungString = "12";
				}
				//wenn Magnetkompass genommen wird dann Ansage MIT Richtung im Stillstand (mit Tonfall-Entscheidung...)
				if ((cbBewRichtungEinVar == true && GeschwindigkeitKpH >= 7 && Abweichung <= 60)
					|| (cbBewRichtungEinVar == true && GeschwindigkeitKpH >= 5 && Abweichung <= 8)
					|| (cbBewRichtungEinVar == true && GeschwindigkeitKpH >= 2 && Abweichung <= 4)
						|| cbKeinMagnetKompassVar == true){
					//Bei Bewegungsrichtung PUNKT hinter der Richtungsansage
					NaviSprachAusgabe = (RichtungString + " Uhr.  " + EntfernungString + " " + AnsageEntfernungsEinheit + ". " + NaviSprachAusgabeZusatz);
				}
				else{//Bei Magnetkompassrichtung dann FRAGEZEICHEN
				NaviSprachAusgabe = (RichtungString + " Uhr?  " + EntfernungString + " " + AnsageEntfernungsEinheit + ". " + NaviSprachAusgabeZusatz);
				    //wenn im Stillstand letzte Bew.Rtg verwendet wird (Halt-Modus), dann KOMMA neutrale Betonung in Richtungsansage
					if (GehFahrHaltModus == 2){
						NaviSprachAusgabe = (RichtungString + " Uhr,  " + EntfernungString + " " + AnsageEntfernungsEinheit + ". " + NaviSprachAusgabeZusatz);
					}//end wenn Halt-Modus
				}
				
			}//end else{ //wenn Magnetkompass genommen wird
		
								
			//AnsageIntervall auf Einstellwerte setzen (z.B. bei/nach "<20m-Intervall")
			NaviAnsageIntervall = (int) Float.parseFloat(NaviAnsageIntervallString); //String zu int, String wird per Intent geliefert
		    NaviAnsageIntervallSoll = NaviAnsageIntervall;
			
			if (Entfernung <= 1 && NaviAnsageAutomatischAus == 0 && NaviAnsageEin == true){ //wenn Entfernung kleiner z.B. 1 Meter dann Naviansage �ber NaviAnsageAutomatischAus unterdr�cken nach einer letzten ge�nderten Ansage
				NaviSprachAusgabe = "Ziel im Umkreis von: " + AbweichungString + " Meter.";
				tts.speak(NaviSprachAusgabe,TextToSpeech.QUEUE_ADD, null);
				Toast.makeText(getApplicationContext(), "Im Umkreis von: " + AbweichungString + " m", Toast.LENGTH_LONG).show();
				NaviAnsageAutomatischAus = 1;
				NaviAnsageZaehler = NaviAnsageIntervall - 4; //Einstellung, um beim Entfernen vom Ziel eine Ansage zu bekommen
				
				//////////////////////////////////// ggf. noch auf ZeitIntervall umarbeiten: NaviAnsageStartZeit = System.currentTimeMillis(); //NaviAnsageStartZeit auf aktuelle Systemzeit setzen ////////////////////////////////////////
				
			}
			
			if (Entfernung > 1) {//Zur�cksetzen der "Automatisch stumm / Naviansage aus unter 1m" - Variable
				NaviAnsageAutomatischAus = 0;
			}
			
	//----------------------------------------------------------------------
	//Ansageintevall anpassen (wird oben bei jedem Durchgang zurück gesetzt)
	//----------------------------------------------------------------------
			
		

			//ab 20 m Entfernung Ansage-Intervall verk�rzen
			if (   Entfernung <= 20 && AnsageEntfernungsEinheit == "" && NaviAnsageIntervall >= 5
				&& cbOptimiertesAnsageIntervallVar == true){ 
				NaviAnsageIntervall = (NaviAnsageIntervall/2) + 2;
			}
			//ab 10 m Entfernung Ansage-Intervall nochmals verk�rzen bei geringer Abweichung
			if (   Entfernung <= 10 && AnsageEntfernungsEinheit == "" && NaviAnsageIntervall >= 5
			    && Abweichung <= 3
			    && cbOptimiertesAnsageIntervallVar == true){ 
				NaviAnsageIntervall = (NaviAnsageIntervall/2);
			}		
			//zu kurzes Intervall vermeiden (Mindestpausenlänge einstellen)
			if (NaviAnsageIntervall <= 5
				&& cbOptimiertesAnsageIntervallVar == true){ 
				NaviAnsageIntervall = 6;
	    	}		

			//Ansageintervall verlängern (unter 20 km/h)
			if (   GeschwindigkeitKpH <= 19
				&& EntfernungMeter >=  150
				&& EntfernungMeter <= 999
			    && NaviAnsageIntervallSoll >= 15
				&& cbOptimiertesAnsageIntervallVar == true) {
				NaviAnsageIntervall = NaviAnsageIntervallSoll + 7;
			}
			if (   GeschwindigkeitKpH <= 19
				&& EntfernungMeter >= 1000
				&& EntfernungMeter <= 1999
				&& cbOptimiertesAnsageIntervallVar == true) {
				NaviAnsageIntervall = NaviAnsageIntervallSoll * 2;
			}
			if (   GeschwindigkeitKpH <= 19
				&& EntfernungMeter >= 2000
			////&& EntfernungMeter <= 9999
				&& cbOptimiertesAnsageIntervallVar == true) {
				NaviAnsageIntervall = NaviAnsageIntervallSoll * 3;
			}
		
		    //Stadt Nahbereich (über 20 km/h)
			if (   GeschwindigkeitKpH >= 20
			    && GeschwindigkeitKpH <= 59
			    && EntfernungMeter >=  900
				&& EntfernungMeter <= 1999
				&& cbOptimiertesAnsageIntervallVar == true) {
				NaviAnsageIntervall = NaviAnsageIntervallSoll * 2;
			}
			//Stadt Fernbereich (über 20 km/h)
			if (   GeschwindigkeitKpH >= 20
			    && GeschwindigkeitKpH <= 59
			    && EntfernungMeter >= 2000
				////&& EntfernungMeter <= 1999
				&& cbOptimiertesAnsageIntervallVar == true) {
				NaviAnsageIntervall = NaviAnsageIntervallSoll * 3;
			}
			//Außerorts Nahbereich (über 20 km/h)
			if (   GeschwindigkeitKpH >=  60
			    && GeschwindigkeitKpH <= 300
				&& EntfernungMeter >=  900
				&& EntfernungMeter <= 1999
				&& cbOptimiertesAnsageIntervallVar == true) {
				NaviAnsageIntervall = NaviAnsageIntervallSoll * 3;
			}
			//Außerorts Fernbereich (über 20 km/h)
			if (   GeschwindigkeitKpH >=  60
			    && GeschwindigkeitKpH <= 300
				&& EntfernungMeter >= 2000
				////&& EntfernungMeter <= 1999
				&& cbOptimiertesAnsageIntervallVar == true) {
				NaviAnsageIntervall = NaviAnsageIntervallSoll * 4;
			}
			//Schnellfahrt Nahbereich (über 100 km/h)
			if (   GeschwindigkeitKpH >= 100
			    && GeschwindigkeitKpH <= 300
				&& EntfernungMeter >=   10
				&& EntfernungMeter <= 1999
				&& cbOptimiertesAnsageIntervallVar == true) {
				NaviAnsageIntervall = NaviAnsageIntervallSoll;
			}
			//Im Halt-Modus Fernbereich sehr wenig Ansagen
			if (   GehFahrHaltModus == 2
				&& EntfernungMeter >= 1500
				&& cbOptimiertesAnsageIntervallVar == true) {
				NaviAnsageIntervall = NaviAnsageIntervallSoll * 6;
			}
			
			
			//Wenn "Doppelte"(Dreifache) Pausenl�nge durch Kopf-Lage aktiviert...	
			if (DoppeltePausenlaenge == true){ 
				NaviAnsageIntervall = NaviAnsageIntervall * 3;
			}
			
			if (NaviAnsageEin == true && NaviAnsageAutomatischAus == 0) //wenn NaviAnsage eingeschaltet / aktiviert UND nicht automatisch ausgeschaltet DANN Naviansage
			{
				NaviAnsageZaehler = NaviAnsageZaehler + 1; //NaviAnsageZ�hler zur Ansage-Zeitsteuerung
											
				if (Abweichung > (Entfernung * 1.2) && AnsageEntfernungsEinheit == "") {//wenn Abweichung gr��er als Entfernung und Einheit "m" dann Abweichung als Zusatz zur Naviansage
					NaviSprachAusgabeZusatz = "plus minus: " + AbweichungString;
				}
				else{
					NaviSprachAusgabeZusatz = "";
				}
	
	//+++++++++++++++++++++++++++++ c c c c c c c c c c c c c c 
	//endgültige NAVIANSAGE tätigen
	//+++++++++++++++++++++++++++++  c c c c c c c c c c c c
				if (System.currentTimeMillis() >= (NaviAnsageStartZeit + (NaviAnsageIntervall * 1000))
					&& FertigFuerAnsage == true) {//wenn Intervall-Vorgabe erreicht dann ANSAGE!!!
					
					tts.speak(NaviSprachAusgabe,TextToSpeech.QUEUE_ADD, null);
					Toast.makeText(getApplicationContext(), RichtungString + " Uhr " + EntfernungString + " " + AnsageEntfernungsEinheit, Toast.LENGTH_LONG).show();
					NaviAnsageZaehler = 0;
					NaviAnsageStartZeit = System.currentTimeMillis(); //NaviAnsageStartZeit auf aktuelle Systemzeit setzen
				
				}
				
				//Hilfsvariable zur Unterdrückung der ersten Richtungsfalschansage
				FertigFuerAnsage = true;
				
			}//end if (NaviAnsageEin == 1 && NaviAnsageAutomatischAus == 0)
		}//end else { //Navi-Ansage wenn nicht auf Display gedreht
		
				
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
	
//Info: hier war vorher der magnetkompass

	@Override
	public void onDestroy() {
		Log.d(TAG, "GCBabbleService: onDestroy() aufgerufen");
		//Toast.makeText(getApplicationContext(), "GCBabbleService beendet", Toast.LENGTH_LONG).show();
		super.onDestroy();
	

		NaviAnsageEin = false;
		NaviAnsageAutomatischAus = 1;
		
		mSensorManager.unregisterListener(mSensorEventListener); //SensorManager beenden
		locationManager.removeUpdates(locationListener);
		locationManager = null;
		stopForeground(true); //Versuch 08.03.12
		
	}

	@Override
	public void onInit(int arg0) { //h�ngt mit ...implements OnInitListener... unf TTS zusammen, �berpr�fung entf�llt hier
		// TODO Auto-generated method stub
		
	}

	
}
