package betriebsdirektor601.gcbabble;

/* INFO:
-IntentActivity für eingehende "geo-Intents"
-wird gestartet durch Intent-Aufruf (externe App)
-beinhaltet keine visuellen Geschichten, 
 übersetzt & prüft die Intent-Daten, 
 startet die GCBabbleActivity
*/

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;



public class GCBabbleIntentGeo extends Activity {

	//verwendete Komponenten und Variablen initialisieren 
	
	EditText etAction;
	String ActionString;
		
	EditText etData;
	String DataString;
	
	EditText etExtras;
	String ExtrasString;
	
	EditText etIntentSonstiges;
	String IntentSonstigesString;

	EditText etTeil1;
	String Teil1String;

	EditText etTeil2;
	String Teil2String;

	EditText etTeil3;
	String Teil3String;

	EditText etTeil4;
	String Teil4String;

	EditText etTeil5;
	String Teil5String;

	EditText etTeil6;
	String Teil6String;

	EditText etZielBreiteDez;
	String ZielBreiteDezString;

	EditText etZielLaengeDez;
	String ZielLaengeDezString;

	EditText etSonstiges;
	String SonstigesString;
	
	//Fehler-Test-Variablen
	double ZielBreiteDez;
	double ZielLaengeDez;
	Boolean ErrorNumberFormatException = false;
	
	
	/** Called when the activity is first created. 
	 * @param sensorManager */
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		//Layout-Datei / Screen anzeigen		
		setContentView(R.layout.intentgeo); //wird nur für Fehlesuchen angezeigt
		 	
		//UserInterface "anmelden"
		etAction = (EditText)findViewById(R.id.etAction); 
		etData = (EditText)findViewById(R.id.etData); 
		etExtras = (EditText)findViewById(R.id.etExtras); 
		etIntentSonstiges = (EditText)findViewById(R.id.etIntentSonstiges); 
		etTeil1 = (EditText)findViewById(R.id.etTeil1); 
		etTeil2 = (EditText)findViewById(R.id.etTeil2); 
		etTeil3 = (EditText)findViewById(R.id.etTeil3); 
		etTeil4 = (EditText)findViewById(R.id.etTeil4); 
		etTeil5 = (EditText)findViewById(R.id.etTeil5); 
		etTeil6 = (EditText)findViewById(R.id.etTeil6); 
		etZielBreiteDez = (EditText)findViewById(R.id.etZielBreiteDez); 
		etZielLaengeDez = (EditText)findViewById(R.id.etZielLaengeDez); 
		etSonstiges = (EditText)findViewById(R.id.etSonstiges); 
		
        //Intent-Daten bekommen
		Intent meinEingehenderIntent = getIntent();
		
        //Datenstring in Variable ablegen (Standard ohne Zoomstufe)
        ActionString = (String.valueOf(meinEingehenderIntent.getAction())); 
        DataString = (String.valueOf(meinEingehenderIntent.getData())); 
        etAction.setText(String.valueOf(ActionString));
      	etData.setText(String.valueOf(DataString));        
        //Datenstring zerlegen an ":" in Anfangsmist und Koordinaten
		String[] intentDatenAbschnitt = DataString.split(":");
		//Zweiten Teil (Koordinaten) zerlegen an "," in Breite und L�nge
		String[] intentKoordinaten = intentDatenAbschnitt[1].split(",");
	
		ZielBreiteDezString = intentKoordinaten[0];
		ZielLaengeDezString = intentKoordinaten[1];
		
		//Test, ob erste Stringzerlegung sauber gelaufen ist
		try{
        //Testweise Intent-Koordinaten String zu double umwandeln mit Punkt statt Komma
		//um Fehler bei der Umstellung zu erkennen
		ZielBreiteDezString = ZielBreiteDezString.replace(",","."); //Komma durch Punkt ersetzen
		ZielBreiteDez = (double) Double.parseDouble(ZielBreiteDezString); //String zu double - Variable
		ZielLaengeDezString = ZielLaengeDezString.replace(",","."); //Komma durch Punkt ersetzen
		ZielLaengeDez = (double) Double.parseDouble(ZielLaengeDezString); //String zu double - Variable
		}
		catch ( NumberFormatException e ){
		//Wenn Erste Stringzerlegung gescheitert, Zweite Variante (mit angehangener Zoomstufe) wird versucht
		
			//Datenstring in Variable ablegen (String mit angehangener Zoomstufe)
			ActionString = (String.valueOf(meinEingehenderIntent.getAction())); 
			DataString = (String.valueOf(meinEingehenderIntent.getData())); 
			etAction.setText(String.valueOf(ActionString));
			etData.setText(String.valueOf(DataString));        
			//Datenstring zerlegen an ":" in Anfangsmist und Koordinaten mit ggf. Zoomstufe
			String[] intentDatenAbschnittZ = DataString.split(":");
			//Im Zweiten Teil ? durch x ersetzen, weil string nicht an Sonderzeichen zerlegt werden kann
			intentDatenAbschnitt[1] = intentDatenAbschnittZ[1].replace("?","x");
			//Zweiten Teil (Koordinaten mit ggf. Zoomstufe) zerlegen an "x" in Koordinaten und Zoomstufe
			String[] intentKoordinatenMitZoomstufe = intentDatenAbschnitt[1].split("x");
			//Mittleren Teil (Koordinaten) zerlegen an "," in Breite und L�nge
			String[] intentKoordinatenZ = intentKoordinatenMitZoomstufe[0].split(",");
			//Koordinaten-Variablen aktualisieren
			ZielBreiteDezString = intentKoordinatenZ[0];
			ZielLaengeDezString = intentKoordinatenZ[1];
			//EdtText mit zusätzlicher Zoomstufe füllen
			etTeil4.setText(intentKoordinatenMitZoomstufe[1]); //Zoomstufe
		
		}//end catch
		
		
		// Endkontrolle / Abschlußtest //
		
		try{
			//Testweise Intent-Koordinaten String zu double umwandeln mit Punkt statt Komma
			//um Fehler bei der Umstellung zu erkennen
			ZielBreiteDezString = ZielBreiteDezString.replace(",","."); //Komma durch Punkt ersetzen
			ZielBreiteDez = (double) Double.parseDouble(ZielBreiteDezString); //String zu double - Variable
			ZielLaengeDezString = ZielLaengeDezString.replace(",","."); //Komma durch Punkt ersetzen
			ZielLaengeDez = (double) Double.parseDouble(ZielLaengeDezString); //String zu double - Variable
		}
		catch ( NumberFormatException e ){
			Toast.makeText(getApplicationContext(), "Schade, Koordinaten-Format unbekannt!", Toast.LENGTH_LONG).show();
			ErrorNumberFormatException = true;
		}
		
	
		//EditText-Felder ausf�llen
		etTeil1.setText(intentDatenAbschnitt[0]); //Anfangsschrott z.B. "geo"
		etTeil2.setText(ZielBreiteDezString); //Koordinaten Breite
		etTeil3.setText(ZielLaengeDezString); //Koordinaten L�nge
	
		etZielBreiteDez.setText(ZielBreiteDezString);
		etZielLaengeDez.setText(ZielLaengeDezString);
		etSonstiges.setText("GCBabbleIntentGeo");
			
		/* vorerst stillgelegt
		//Hintergrundservices beenden
		stopService(new Intent(this, GCBabbleService.class)); 
		stopService(new Intent(this, GCBabbleServiceParkplatz.class)); 
		*/
		
	
		Intent meinAusgehenderIntent = new Intent();
		meinAusgehenderIntent.setClass(this, GCBabbleActivity.class);
		//meinAusgehenderIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		
		if (ErrorNumberFormatException == false)	{
		meinAusgehenderIntent.putExtra("ZielBreiteDezString", ZielBreiteDezString);
		meinAusgehenderIntent.putExtra("ZielLaengeDezString", ZielLaengeDezString);
		meinAusgehenderIntent.putExtra("IntentAbsender", "GCBabbleIntentGeo");
		meinAusgehenderIntent.putExtra("ErrorNumberFormatException", false);
		}//end if error
		else	{ //wenn Koordinaten nicht OK, dann Ersatzdaten mit Fehlermeldung
		meinAusgehenderIntent.putExtra("ZielBreiteDezString", "0");
		meinAusgehenderIntent.putExtra("ZielLaengeDezString", "0");
		meinAusgehenderIntent.putExtra("IntentAbsender", "GCBabbleIntentGeo");
		meinAusgehenderIntent.putExtra("ErrorNumberFormatException", true);
		}//end if error
		
		startActivity(meinAusgehenderIntent); 
	
		
		//Intent-Activity-Layout schließen (für Fehlersuchen hier deaktivieren)
		finish();
    	System.exit(0); //Wichtig, um alle laufenden GC-Babble-Activitys abzuw�rgen
						//gab sonst Abst�rze wenn Activity bei Intentstart schon aktiv
    
        
    }// end public void onCreate(Bundle savedInstanceState
	
	
}
