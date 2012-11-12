package betriebsdirektor601.gcbabble;

/* INFO:
-StartActivity 
-wird gestartet bei App-Start
-beinhaltet keine visuellen Geschichten, 
 macht nix, 
 startet per Intent GCBabbleActivity
*/

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;

public class GCBabbleIntentMain extends Activity {


	//verwendete Komponenten und Variablen initialisieren 
	
	String ZielBreiteDezString;
	String ZielLaengeDezString;
	
	
	
	/** Called when the activity is first created. 
	 * @param sensorManager */
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		
		//Layout-Datei / Screen anzeigen		
		//setContentView(R.layout.intentgeo); //wird nur für Fehlersuchen angezeigt
		 	
		//UserInterface "anmelden"
		/*
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
		*/
        
		ZielBreiteDezString = "0";
		ZielLaengeDezString = "0";
		
		/* vorerst stillgelegt (Versuche mit mehreren Intents...)
		//Hintergrundservices beenden
		stopService(new Intent(this, GCBabbleService.class)); 
		stopService(new Intent(this, GCBabbleServiceParkplatz.class)); 
		*/
		
		Intent meinAusgehenderIntent = new Intent();
		meinAusgehenderIntent.setClass(this, GCBabbleActivity.class);
		//meinAusgehenderIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
	    meinAusgehenderIntent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		meinAusgehenderIntent.putExtra("ZielBreiteDezString", ZielBreiteDezString);
		meinAusgehenderIntent.putExtra("ZielLaengeDezString", ZielLaengeDezString);
		meinAusgehenderIntent.putExtra("IntentAbsender", "GCBabbleIntentMain");
		meinAusgehenderIntent.putExtra("ErrorNumberFormatException", false);
		
		startActivity(meinAusgehenderIntent); 
		
		finish();
		System.exit(0); //Wichtig, um alle laufenden GC-Babble-Activitys abzuw�rgen
						//gab sonst Abst�rze wenn Activity bei Intentstart schon aktiv
        
        
        
        
    }// end public void onCreate(Bundle savedInstanceState)



}
