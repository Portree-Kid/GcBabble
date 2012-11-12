package betriebsdirektor601.gcbabble;

/* INFO:
-IntentActivity für eingehende "radar-Intents"
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


public class GCBabbleIntentRadar extends Activity {


	//verwendete Komponenten und Variablen initialisieren 
	
	String ExtraLatitudeString;
	String ExtraLongitudeString;
	
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
		setContentView(R.layout.intentgeo);//nur für Fehlersuchen
		 	
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
		
        //Datenstring in Variable ablegen
        ActionString = (String.valueOf(meinEingehenderIntent.getAction())); 
        DataString = (String.valueOf(meinEingehenderIntent.getData())); 
        etAction.setText(String.valueOf(ActionString));
      	etData.setText(String.valueOf(DataString));      
		ExtraLatitudeString = (String.valueOf(meinEingehenderIntent.getFloatExtra("latitude", 0))); 
		ExtraLongitudeString = (String.valueOf(meinEingehenderIntent.getFloatExtra("longitude", 0))); 
		etExtras.setText(String.valueOf(ExtraLatitudeString + " " + ExtraLongitudeString));
		
		ZielBreiteDezString = ExtraLatitudeString;
		ZielLaengeDezString = ExtraLongitudeString;
		
		//EditText-Felder ausf�llen
		//etTeil1.setText(intentDatenAbschnitt[0]); //Anfangsschrott z.B. "geo"
		etTeil2.setText(ZielBreiteDezString); //Koordinaten Breite
		etTeil3.setText(ZielLaengeDezString); //Koordinaten L�nge
		etZielBreiteDez.setText(ZielBreiteDezString);
		etZielLaengeDez.setText(ZielLaengeDezString);
		etSonstiges.setText("GCBabbleIntentRadar");
       	
  
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

		/*
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
			meinAusgehenderIntent.putExtra("IntentAbsender", "GCBabbleIntentRadar");
			meinAusgehenderIntent.putExtra("ErrorNumberFormatException", false);
		}//end if error
		else	{ //wenn Koordinaten nicht OK, dann Ersatzdaten mit Fehlermeldung
			meinAusgehenderIntent.putExtra("ZielBreiteDezString", "0");
			meinAusgehenderIntent.putExtra("ZielLaengeDezString", "0");
			meinAusgehenderIntent.putExtra("IntentAbsender", "GCBabbleIntentRadar");
			meinAusgehenderIntent.putExtra("ErrorNumberFormatException", true);
		}//end if error

		startActivity(meinAusgehenderIntent); 

		finish();
    	System.exit(0); //Wichtig, um alle laufenden GC-Babble-Activitys abzuw�rgen
		//gab sonst Abst�rze wenn Activity bei Intentstart schon aktiv



        
		
		
		
		
        
    }// end public void onCreate(Bundle savedInstanceState)



}
