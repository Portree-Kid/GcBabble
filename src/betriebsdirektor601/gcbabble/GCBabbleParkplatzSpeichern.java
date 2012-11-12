package betriebsdirektor601.gcbabble;

/* INFO:
-Parkplatz-Speicherer
-wird gestartet durch ServiceParkplatz(automatisch speichern)
 oder GCBabbleActivity (manuell speichern)
-beinhaltet keine visuellen Geschichten, 
 empfängt & speichert Parkplatzkoordinaten
*/

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class GCBabbleParkplatzSpeichern extends Activity {

	//verwendete Komponenten und Variablen initialisieren 
	
	/** Called when the activity is first created. 
	 * @param sensorManager */
	public void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		//Layout-Datei / Screen anzeigen stillgelegt	
		//setContentView(R.layout.intentgeo);
		 		
		//Intent mit Extradaten empfangen
		Intent meinParkplatzSpeichernIntent = getIntent();
		String IntentParkplatzBreiteDezString = meinParkplatzSpeichernIntent.getStringExtra("ParkplatzBreiteDezString");
		String IntentParkplatzLaengeDezString = meinParkplatzSpeichernIntent.getStringExtra("ParkplatzLaengeDezString");
		////String IntentAbsender = meinParkplatzSpeichernIntent.getStringExtra("IntentAbsender");
		boolean ParkplatzNordSuedVar = meinParkplatzSpeichernIntent.getBooleanExtra("ParkplatzNordSuedVar", false);
		boolean ParkplatzOstWestVar = meinParkplatzSpeichernIntent.getBooleanExtra("ParkplatzOstWestVar", false);
		////meinParkplatzSpeichernIntent.putExtra("ParkplatzNordSuedVar", ParkplatzNordSuedVar);
		////meinParkplatzSpeichernIntent.putExtra("ParkplatzOstWestVar", ParkplatzOstWestVar);
		
		//Toast.makeText(getApplicationContext(), "GCBabbleParkplatzSpeichern gestartet* mit " 
		//+ IntentParkplatzBreiteDezString + " " + IntentParkplatzLaengeDezString, Toast.LENGTH_SHORT).show();
		
		//Prefs speichern
		
				//SharedPreferences koordinatenspeicher = getPreferences(Context.MODE_WORLD_WRITEABLE);
				SharedPreferences koordinatenspeicher = PreferenceManager.getDefaultSharedPreferences(this);
				Editor editor3 = koordinatenspeicher.edit();
				editor3.putString("ParkplatzBreiteDezString", IntentParkplatzBreiteDezString);
				editor3.putString("ParkplatzLaengeDezString", IntentParkplatzLaengeDezString); 
				editor3.putBoolean("ParkplatzNordSuedVar", ParkplatzNordSuedVar);
				editor3.putBoolean("ParkplatzOstWestVar", ParkplatzOstWestVar); 
				
				editor3.commit();	
	
		
		finish(); //schon einmal probeweise still gelegt, da manchmal alles geschlossen wird. Hat nix gebracht (vielleicht Speichermangel?)
		
    }// end public void onCreate(Bundle savedInstanceState)



}
