package betriebsdirektor601.gcbabble;

/* INFO:
-HauptActivity
-wird gestartet durch IntentMain
-beinhaltet alle visuellen Geschichten, 
 Display-Bedienhandlungen, 
 Prüfung ob TTS installiert, 
 Speichern der Einstellungen & Daten
*/

import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.net.Uri;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.text.format.DateFormat;
import android.os.Vibrator;
import java.text.DecimalFormat;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;


public class GCBabbleActivity extends Activity implements OnClickListener, OnInitListener
{
	
	//verwendete Komponenten und Variablen initialisieren 
	Button bNaviStarten; 
	Button bMinus;
	Button bPlus;
	Button bKwMinus;
	Button bKwPlus;	
	Button bNaviAusMinus;
	Button bNaviAusPlus;	
	Button bAufKarteZeigen;
	Button bStrassenNavigation;
	Button bKoordinatenSpeichernLaden;
	Button bErweiterteAnzeige;
	Button bKartenUndNavigation;
	Button bNotizenUndSonstiges;
	Button bNordSued;
	Button bOstWest;
	
	Button bAlsZielP0;
	Button bPosMerkenP1;
	Button bZielMerkenP1;
	Button bAlsZielP1;
	Button bPosMerkenP2;
	Button bZielMerkenP2;
	Button bAlsZielP2;
	Button bPosMerkenP3;
	Button bZielMerkenP3;
	Button bAlsZielP3;
	Button bPosMerkenP4;
	Button bZielMerkenP4;
	Button bAlsZielP4;
	Button bPosMerkenP5;
	Button bZielMerkenP5;
	Button bAlsZielP5;
	
	EditText etP0Text;
	EditText etP1Text;
	EditText etP2Text;
	EditText etP3Text;
	EditText etP4Text;
	EditText etP5Text;
	EditText etP0BreiteGrad;
	EditText etP0BreiteMinDez;
	EditText etP0LaengeGrad;
	EditText etP0LaengeMinDez;
	EditText etP1BreiteGrad;
	EditText etP1BreiteMinDez;
	EditText etP1LaengeGrad;
	EditText etP1LaengeMinDez;
	EditText etP2BreiteGrad;
	EditText etP2BreiteMinDez;
	EditText etP2LaengeGrad;
	EditText etP2LaengeMinDez;
	EditText etP3BreiteGrad;
	EditText etP3BreiteMinDez;
	EditText etP3LaengeGrad;
	EditText etP3LaengeMinDez;
	EditText etP4BreiteGrad;
	EditText etP4BreiteMinDez;
	EditText etP4LaengeGrad;
	EditText etP4LaengeMinDez;
	EditText etP5BreiteGrad;
	EditText etP5BreiteMinDez;
	EditText etP5LaengeGrad;
	EditText etP5LaengeMinDez;
	EditText etNotizen;
	
	TextView tvP0NordSued;
	TextView tvP1NordSued;
	TextView tvP2NordSued;
	TextView tvP3NordSued;
	TextView tvP4NordSued;
	TextView tvP5NordSued;
	TextView tvP0OstWest;
	TextView tvP1OstWest;
	TextView tvP2OstWest;
	TextView tvP3OstWest;
	TextView tvP4OstWest;
	TextView tvP5OstWest;
	
	boolean P0NordSuedVar;
	boolean P1NordSuedVar;
	boolean P2NordSuedVar;
	boolean P3NordSuedVar;
	boolean P4NordSuedVar;
	boolean P5NordSuedVar;
	boolean P0OstWestVar;
	boolean P1OstWestVar;
	boolean P2OstWestVar;
	boolean P3OstWestVar;
	boolean P4OstWestVar;
	boolean P5OstWestVar;
	
	//Merker für Erkennung und Speicherung "Letztes Ziel"
	String etP0TextMerker;
	String ZielBreiteGradStringMerker;
	String ZielBreiteMinDezStringMerker;
	String ZielLaengeGradStringMerker;
	String ZielLaengeMinDezStringMerker;
	
	TextView tvTextAusgabe;////still gelegt

	EditText etZielBreiteGrad;
	String ZielBreiteGradString;
	float ZielBreiteGrad;
	
	EditText etZielBreiteMinDez;
	String ZielBreiteMinDezString;
	float ZielBreiteMinDez;//
	
	EditText etZielLaengeGrad;
	String ZielLaengeGradString;
	float ZielLaengeGrad;//
	
	EditText etZielLaengeMinDez;
	String ZielLaengeMinDezString;
	float ZielLaengeMinDez;//
	
	TextView tvZielBreiteDez;
	String ZielBreiteDezString;
	double ZielBreiteDez;
	
	TextView tvZielLaengeDez;
	String ZielLaengeDezString;
	double ZielLaengeDez;
	
	TextView tvPosBreiteDez;
	String PosBreiteDezString;
	double PosBreiteDez;
	double VorPosBreiteDez;
	double VorVorPosBreiteDez;
	double GehPos1BreiteDez;
	
	TextView tvPosLaengeDez;
	String PosLaengeDezString;
	double PosLaengeDez;
	double VorPosLaengeDez;
	double VorVorPosLaengeDez;
	double GehPos1LaengeDez;
		
	double ParkplatzBreiteDez; 
	double ParkplatzLaengeDez;  
	boolean ParkplatzMerken;   
	boolean PositionMerken;
	int PosMerkenP;
	TextView tvParkplatzBreiteDez;
	TextView tvParkplatzLaengeDez;
	//TextView tvParkplatzNordSued;//////////////////noch nacharbeiten
	//TextView tvParkplatzOstWest;//////////////////////noch nacharbeiten
	String ParkplatzBreiteDezString;
	String ParkplatzLaengeDezString;
	boolean ParkplatzNordSuedVar; //false = Nord
	boolean ParkplatzOstWestVar; //false = Ost 
	
	TextView tvMagKompass;
	String MagKompassSting;
	double MagKompass;
	TextView tvBewRichtungGrad;
	
	TextView tvEntfernung;
	String EntfernungString;
	double Entfernung;
	
	TextView tvRichtung;
	String RichtungString;
	double Richtung;
	double RichtungDrehGrad;
	double RichtungDEG;
	double BewRichtungDEG;
	
	TextView tvRichtungGrad;
	String RichtungGradString;
	double RichtungGrad;
	double BewRichtungGrad;
	
	TextView tvAbweichung;
	String AbweichungString;
	float Abweichung;
	
	TextView tvLetzteBewRtg;
	float LetzteBewRtg;
	String LetzteBewRtgString;
	
	String GpsBearingGradString;
	float GpsBearingGrad;
		
	String NaviSprachAusgabe; 
	String NaviSprachAusgabeZusatz = " ";   
	private static SensorManager mSensorManager; //f�r Kompass-Sensor
	private SensorManager mASensorManager; //f�r ACC-Sensor
	private TextToSpeech tts;
	private int MY_DATA_CHECK_CODE;//////= 0
	
	LocationManager locationManager;
	LocationListener locationListener;
		
	double offsetRichtung;
	TextView tvEntfernungsEinheit;
	String AnsageEntfernungsEinheit;
	
	String intentData;
	String btEinstellungen = "Einstellungen";
	TextView tvKorrekturwinkel;
	double Korrekturwinkel;
	String KorrekturwinkelString;
	
	TextView tvNaviAusDisplay;
	int NaviAusDisplay;
	String NaviAusDisplayString;
	
	TextView tvGeschwindigkeit;
	TextView tvHoehe;
	
	int NaviAnsageIntervall;//interne Variable, kann sich z.B. bei Entf. unter 10 Meter �ndern
	int NaviAnsageIntervallSoll; //vom User vorgegebener/eingestellter Sollwert
	int NaviAnsageZaehler; 
	boolean NaviAnsageEin; 
	long NaviAnsageStartZeit; //f�r Zeitsteuerung der Ansagen, wird bei Navistart und nach jeder Ansage auf aktuelle Systemzeit gesetzt
	
	int NaviAnsageAutomatischAus; 
	int aufDisplayGedreht;   
	double zAxe; //Ab Wert kleiner -9 Ger�t auf Display gedreht
	double xAxe;
	double yAxe;
	int NaviAusZaehler;  
	long NaviAusDisplayStartZeit;  //f�r TimeOut Navi beenden wenn Handy auf Display gedreht
	int ZeitAnsageZaehler; 
	long ZeitAnsageStartZeit; 
	long ZeitAnsageTimeOutStartZeit; //f�r Begrenzung der Zeitansage bei Display-Lage
	int LinksLageZaehler; 
	long LinksLageStartZeit;
	int RechtsLageZaehler;
	long RechtsLageStartZeit;
	int KopfLageZaehler;
	long KopfLageStartZeit;
	boolean GPSein;
	long BackButtonDruckZeit;
	float GeschwindigkeitKpH;
	
	private LinearLayout loNavigation;
	private LinearLayout loNaviAnzeige;
	private LinearLayout loEinstellungen;
	private LinearLayout loInfo1;
	private LinearLayout loInfo2;
	private LinearLayout loKoordinatenSpeichernLaden;
	private LinearLayout loErweiterteAnzeige;
	private LinearLayout loKartenUndNavigation;
	private LinearLayout loNotizenUndSonstiges;
	
	int layoutConfig;//0=Navigation, 11=Einstellungen (Seite1), 21=Info (Seite1), 22=Info (Seite2)...
	
	TextView tvAnsageIntervall;
	String zeitAnsage;
	double test;
	String NaviAnsageIntervallString;
	
	CheckBox cbNaviLinksRechts;
	CheckBox cbDatumZeit;
	CheckBox cbBewRichtungEin;
	CheckBox cbParkplatzAutomatischSpeichern;
	CheckBox cbKeinMagnetKompass;
	CheckBox cbGehFahrHaltErkennung;
	CheckBox cbOptimiertesAnsageIntervall;
	
	boolean cbNaviLinksRechtsVar;
	boolean cbDatumZeitVar;
	boolean cbBewRichtungEinVar;
	boolean cbParkplatzAutomatischSpeichernVar;
	boolean cbKeinMagnetKompassVar;
	boolean cbGehFahrHaltErkennungVar;
	boolean cbOptimiertesAnsageIntervallVar;
	
	boolean bNordSuedVar; //false = Nord
	boolean bOstWestVar; //false = Ost 
	boolean bNordSuedVarMerker; //false = Nord
	boolean bOstWestVarMerker; //false = Ost 
	
	TextView tvParkplatzNordSued;
	TextView tvParkplatzOstWest;
	boolean satFixAnsageErl;
	boolean BewRichtungOK;
	boolean ParkplatzManuellGespeichert;
		
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
	long onCreateZeit;
	
	//Sonstige Merker-Variablen
	boolean IntentTypOK;
	boolean DoppeltePausenlaenge;
	boolean DoppeltePausenlaengeGeradeVerstellt;
	boolean ServiceDatenanfrage = false;
	boolean neueEingabeBeiNaviEin = false;
	
	String IntentAbsender = "unbekannt";
	
	
	/** Called when the activity is first created. 
	 * @param sensorManager */
	public void onCreate(Bundle savedInstanceState)
	{
		////Toast.makeText(getApplicationContext(), "onCreate", Toast.LENGTH_LONG).show();
		//Zeit merken, wann App gestartet wurde (z.B. f�r SatFix-Ansage)
		onCreateZeit = System.currentTimeMillis();
		
		super.onCreate(savedInstanceState);
		
		//Layout-Datei / Screen anzeigen		
		setContentView(R.layout.main);
			
		// vorerst deaktiviert: Aktuelle Orientation erkennen und festlegen
		switch(this.getResources().getConfiguration().orientation){
		case Configuration.ORIENTATION_PORTRAIT:
			////Toast.makeText(getApplicationContext(),  "ORIENTATION_PORTRAIT", Toast.LENGTH_LONG).show();
			//Bildschirmdrehung festlegen
			this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		break;
		
		case Configuration.ORIENTATION_LANDSCAPE:
			////Toast.makeText(getApplicationContext(),  "ORIENTATION_LANDSCAPE", Toast.LENGTH_LONG).show();
			//Bildschirmdrehung festlegen
			this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		break;
			
		case Configuration.ORIENTATION_SQUARE:
			////Toast.makeText(getApplicationContext(),  "ORIENTATION_SQUARE", Toast.LENGTH_LONG).show();
			//Bildschirmdrehung festlegen
			this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
		break;
		
		default: 
			try {
				throw new Exception("Unbekanntes Bildschirmformat");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		break;
		}
		
		
	
		
		//UserInterface "anmelden"
		bNaviStarten = (Button)findViewById(R.id.bNaviStarten);
		bMinus = (Button)findViewById(R.id.bMinus);
		bPlus = (Button)findViewById(R.id.bPlus);
		bKwMinus = (Button)findViewById(R.id.bKwMinus);
		bKwPlus = (Button)findViewById(R.id.bKwPlus);
		bNaviAusMinus = (Button)findViewById(R.id.bNaviAusMinus);
		bNaviAusPlus = (Button)findViewById(R.id.bNaviAusPlus);
		bAufKarteZeigen = (Button)findViewById(R.id.bAufKarteZeigen);
		bStrassenNavigation = (Button)findViewById(R.id.bStrassenNavigation);
		bKoordinatenSpeichernLaden = (Button)findViewById(R.id.bKoordinatenSpeichernLaden);
		bErweiterteAnzeige = (Button)findViewById(R.id.bErweiterteAnzeige);
		bKartenUndNavigation = (Button)findViewById(R.id.bKartenUndNavigation);
		bNotizenUndSonstiges = (Button)findViewById(R.id.bNotizenUndSonstiges);
		bNordSued = (Button)findViewById(R.id.bNordSued);
		bOstWest = (Button)findViewById(R.id.bOstWest);
				
		bAlsZielP0 = (Button)findViewById(R.id.bAlsZielP0);
		bPosMerkenP1 = (Button)findViewById(R.id.bPosMerkenP1);
		bZielMerkenP1 = (Button)findViewById(R.id.bZielMerkenP1);
		bAlsZielP1 = (Button)findViewById(R.id.bAlsZielP1);
		bPosMerkenP2 = (Button)findViewById(R.id.bPosMerkenP2);
		bZielMerkenP2 = (Button)findViewById(R.id.bZielMerkenP2);
		bAlsZielP2 = (Button)findViewById(R.id.bAlsZielP2);
		bPosMerkenP3 = (Button)findViewById(R.id.bPosMerkenP3);
		bZielMerkenP3 = (Button)findViewById(R.id.bZielMerkenP3);
		bAlsZielP3 = (Button)findViewById(R.id.bAlsZielP3);
		bPosMerkenP4 = (Button)findViewById(R.id.bPosMerkenP4);
		bZielMerkenP4 = (Button)findViewById(R.id.bZielMerkenP4);
		bAlsZielP4 = (Button)findViewById(R.id.bAlsZielP4);
		bPosMerkenP5 = (Button)findViewById(R.id.bPosMerkenP5);
		bZielMerkenP5 = (Button)findViewById(R.id.bZielMerkenP5);
		bAlsZielP5 = (Button)findViewById(R.id.bAlsZielP5);
		
		cbNaviLinksRechts = (CheckBox)findViewById(R.id.cbNaviLinksRechts);
		cbDatumZeit = (CheckBox)findViewById(R.id.cbDatumZeit);
		cbBewRichtungEin = (CheckBox)findViewById(R.id.cbBewRichtungEin);
		cbParkplatzAutomatischSpeichern = (CheckBox)findViewById(R.id.cbParkplatzAutomatischSpeichern);
		cbKeinMagnetKompass = (CheckBox)findViewById(R.id.cbKeinMagnetKompass);
		cbGehFahrHaltErkennung = (CheckBox)findViewById(R.id.cbGehFahrHaltErkennung);
		cbOptimiertesAnsageIntervall = (CheckBox)findViewById(R.id.cbOptimiertesAnsageIntervall);
		
		tvZielBreiteDez = (TextView)findViewById(R.id.tvZielBreiteDez);
		tvZielLaengeDez = (TextView)findViewById(R.id.tvZielLaengeDez);
		tvPosBreiteDez = (TextView)findViewById(R.id.tvPosBreiteDez);
		tvPosLaengeDez = (TextView)findViewById(R.id.tvPosLaengeDez);
		tvTextAusgabe = (TextView)findViewById(R.id.tvTextAusgabe);    		
		tvMagKompass = (TextView)findViewById(R.id.tvMagKompass);    
		tvEntfernung = (TextView)findViewById(R.id.tvEntfernung);  
		tvAbweichung = (TextView)findViewById(R.id.tvAbweichung); 
		tvRichtung = (TextView)findViewById(R.id.tvRichtung); 
		tvRichtungGrad = (TextView)findViewById(R.id.tvRichtungGrad); 
		tvEntfernungsEinheit = (TextView)findViewById(R.id.tvEntfernungsEinheit); 
		tvGeschwindigkeit = (TextView)findViewById(R.id.tvGeschwindigkeit); 
		tvHoehe = (TextView)findViewById(R.id.tvHoehe); 
		tvLetzteBewRtg = (TextView)findViewById(R.id.tvLetzteBewRtg); 
		tvAnsageIntervall = (TextView)findViewById(R.id.tvAnsageIntervall); 
		tvKorrekturwinkel = (TextView)findViewById(R.id.tvKorrekturwinkel); 
		tvNaviAusDisplay = (TextView)findViewById(R.id.tvNaviAusDisplay); 
		tvParkplatzBreiteDez = (TextView)findViewById(R.id.tvParkplatzBreiteDez); 
		tvParkplatzLaengeDez = (TextView)findViewById(R.id.tvParkplatzLaengeDez);
		tvBewRichtungGrad = (TextView)findViewById(R.id.tvBewRichtungGrad);
		tvParkplatzNordSued = (TextView)findViewById(R.id.tvParkplatzNordSued);
		tvParkplatzOstWest = (TextView)findViewById(R.id.tvParkplatzOstWest);
		
		tvP0NordSued = (TextView)findViewById(R.id.tvP0NordSued);
		tvP1NordSued = (TextView)findViewById(R.id.tvP1NordSued);
		tvP2NordSued = (TextView)findViewById(R.id.tvP2NordSued);
		tvP3NordSued = (TextView)findViewById(R.id.tvP3NordSued);
		tvP4NordSued = (TextView)findViewById(R.id.tvP4NordSued);
		tvP5NordSued = (TextView)findViewById(R.id.tvP5NordSued);
		tvP0OstWest = (TextView)findViewById(R.id.tvP0OstWest);
		tvP1OstWest = (TextView)findViewById(R.id.tvP1OstWest);
		tvP2OstWest = (TextView)findViewById(R.id.tvP2OstWest);
		tvP3OstWest = (TextView)findViewById(R.id.tvP3OstWest);
		tvP4OstWest = (TextView)findViewById(R.id.tvP4OstWest);
		tvP5OstWest = (TextView)findViewById(R.id.tvP5OstWest);
		
		etZielBreiteGrad = (EditText)findViewById(R.id.etZielBreiteGrad); 
		etZielBreiteMinDez = (EditText)findViewById(R.id.etZielBreiteMinDez); 
		etZielLaengeGrad = (EditText)findViewById(R.id.etZielLaengeGrad); 
		etZielLaengeMinDez = (EditText)findViewById(R.id.etZielLaengeMinDez); 
		
		etP0Text = (EditText)findViewById(R.id.etP0Text); 
		etP1Text = (EditText)findViewById(R.id.etP1Text); 
		etP2Text = (EditText)findViewById(R.id.etP2Text); 
		etP3Text = (EditText)findViewById(R.id.etP3Text); 
		etP4Text = (EditText)findViewById(R.id.etP4Text); 
		etP5Text = (EditText)findViewById(R.id.etP5Text); 
		etP0BreiteGrad = (EditText)findViewById(R.id.etP0BreiteGrad); 
		etP0BreiteMinDez = (EditText)findViewById(R.id.etP0BreiteMinDez); 
		etP0LaengeGrad = (EditText)findViewById(R.id.etP0LaengeGrad); 
		etP0LaengeMinDez = (EditText)findViewById(R.id.etP0LaengeMinDez); 
		etP1BreiteGrad = (EditText)findViewById(R.id.etP1BreiteGrad); 
		etP1BreiteMinDez = (EditText)findViewById(R.id.etP1BreiteMinDez); 
		etP1LaengeGrad = (EditText)findViewById(R.id.etP1LaengeGrad); 
		etP1LaengeMinDez = (EditText)findViewById(R.id.etP1LaengeMinDez); 
		etP2BreiteGrad = (EditText)findViewById(R.id.etP2BreiteGrad); 
		etP2BreiteMinDez = (EditText)findViewById(R.id.etP2BreiteMinDez); 
		etP2LaengeGrad = (EditText)findViewById(R.id.etP2LaengeGrad); 
		etP2LaengeMinDez = (EditText)findViewById(R.id.etP2LaengeMinDez); 
		etP3BreiteGrad = (EditText)findViewById(R.id.etP3BreiteGrad); 
		etP3BreiteMinDez = (EditText)findViewById(R.id.etP3BreiteMinDez); 
		etP3LaengeGrad = (EditText)findViewById(R.id.etP3LaengeGrad); 
		etP3LaengeMinDez = (EditText)findViewById(R.id.etP3LaengeMinDez); 
		etP4BreiteGrad = (EditText)findViewById(R.id.etP4BreiteGrad); 
		etP4BreiteMinDez = (EditText)findViewById(R.id.etP4BreiteMinDez); 
		etP4LaengeGrad = (EditText)findViewById(R.id.etP4LaengeGrad); 
		etP4LaengeMinDez = (EditText)findViewById(R.id.etP4LaengeMinDez); 
		etP5BreiteGrad = (EditText)findViewById(R.id.etP5BreiteGrad); 
		etP5BreiteMinDez = (EditText)findViewById(R.id.etP5BreiteMinDez); 
		etP5LaengeGrad = (EditText)findViewById(R.id.etP5LaengeGrad); 
		etP5LaengeMinDez = (EditText)findViewById(R.id.etP5LaengeMinDez); 
		etNotizen = (EditText)findViewById(R.id.etNotizen); 
		
		loNavigation = (LinearLayout)findViewById(R.id.loNavigation);
		loNaviAnzeige = (LinearLayout)findViewById(R.id.loNaviAnzeige);
		loEinstellungen = (LinearLayout)findViewById(R.id.loEinstellungen);
		loInfo1 = (LinearLayout)findViewById(R.id.loInfo1);
		loInfo2 = (LinearLayout)findViewById(R.id.loInfo2);
		////loParkplatz = (LinearLayout)findViewById(R.id.loParkplatz);
		loKoordinatenSpeichernLaden = (LinearLayout)findViewById(R.id.loKoordinatenSpeichernLaden);
		loErweiterteAnzeige = (LinearLayout)findViewById(R.id.loErweiterteAnzeige);
		loKartenUndNavigation = (LinearLayout)findViewById(R.id.loKartenUndNavigation);
		loNotizenUndSonstiges = (LinearLayout)findViewById(R.id.loNotizenUndSonstiges);
		
	
		//TextViews initialisieren
		tvZielBreiteDez.setText(String.valueOf(ZielBreiteDez));
		tvZielLaengeDez.setText(String.valueOf(ZielLaengeDez));
		tvTextAusgabe.setText(String.valueOf(" ")); //Textausgabe
	
		
		//Layout "Einstellungen" und "Info" beim Start ausblenden
		loNavigation.setVisibility(View.VISIBLE);
		loNaviAnzeige.setVisibility(View.VISIBLE);
		loEinstellungen.setVisibility(View.GONE); //Layout aus-blenden
		loInfo1.setVisibility(View.GONE); //Layout aus-blenden
		loInfo2.setVisibility(View.GONE); //Layout aus-blenden
		loKoordinatenSpeichernLaden.setVisibility(View.GONE); //Layout aus-blenden
		loErweiterteAnzeige.setVisibility(View.GONE); //Layout aus-blenden
		layoutConfig = 0;
		
		
		//******************************************************
		//Einstellungen beim Start �bernehmen aus PREFERENCES //
		//******************************************************
		SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
		KorrekturwinkelString = einstellungspeicher.getString("KorrekturwinkelString", "0");
		tvKorrekturwinkel.setText(String.valueOf(KorrekturwinkelString)); //String zu TextView
		Korrekturwinkel = (double) Float.parseFloat(KorrekturwinkelString); //String zu double - Variable
		
		NaviAusDisplayString = einstellungspeicher.getString("NaviAusDisplayString", "30");
		tvNaviAusDisplay.setText(String.valueOf(NaviAusDisplayString)); //String zu TextView}
		NaviAusDisplay = (int) Float.parseFloat(NaviAusDisplayString); //String zu int - Variable
			
		Boolean cbNaviLinksRechtsVar = einstellungspeicher.getBoolean("cbNaviLinksRechtsVar", true); 
		cbNaviLinksRechts.setChecked(cbNaviLinksRechtsVar);
		Boolean cbDatumZeitVar = einstellungspeicher.getBoolean("cbDatumZeitVar", true); 
		cbDatumZeit.setChecked(cbDatumZeitVar);
		Boolean cbBewRichtungEinVar = einstellungspeicher.getBoolean("cbBewRichtungEinVar", true); 
		cbBewRichtungEin.setChecked(cbBewRichtungEinVar);
		Boolean cbParkplatzAutomatischSpeichernVar = einstellungspeicher.getBoolean("cbParkplatzAutomatischSpeichernVar", true); 
		cbParkplatzAutomatischSpeichern.setChecked(cbParkplatzAutomatischSpeichernVar);
		Boolean cbKeinMagnetKompassVar = einstellungspeicher.getBoolean("cbKeinMagnetKompassVar", false); 
		cbKeinMagnetKompass.setChecked(cbKeinMagnetKompassVar);
		cbGehFahrHaltErkennungVar = einstellungspeicher.getBoolean("cbGehFahrHaltErkennungVar", true); 
		cbGehFahrHaltErkennung.setChecked(cbGehFahrHaltErkennungVar);
		cbOptimiertesAnsageIntervallVar = einstellungspeicher.getBoolean("cbOptimiertesAnsageIntervallVar", true);
		cbOptimiertesAnsageIntervall.setChecked(cbOptimiertesAnsageIntervallVar);
		
		
		bNordSuedVar = einstellungspeicher.getBoolean("bNordSuedVar", false); 
		bOstWestVar = einstellungspeicher.getBoolean("bOstWestVar", false); 
		P0NordSuedVar = einstellungspeicher.getBoolean("P0NordSuedVar", false); 
		P1NordSuedVar = einstellungspeicher.getBoolean("P1NordSuedVar", false); 
		P2NordSuedVar = einstellungspeicher.getBoolean("P2NordSuedVar", false); 
		P3NordSuedVar = einstellungspeicher.getBoolean("P3NordSuedVar", false); 
		P4NordSuedVar = einstellungspeicher.getBoolean("P4NordSuedVar", false); 
		P5NordSuedVar = einstellungspeicher.getBoolean("P5NordSuedVar", false); 
		P0OstWestVar = einstellungspeicher.getBoolean("P0OstWestVar", false); 
		P1OstWestVar = einstellungspeicher.getBoolean("P1OstWestVar", false); 
		P2OstWestVar = einstellungspeicher.getBoolean("P2OstWestVar", false); 
		P3OstWestVar = einstellungspeicher.getBoolean("P3OstWestVar", false); 
		P4OstWestVar = einstellungspeicher.getBoolean("P4OstWestVar", false); 
		P5OstWestVar = einstellungspeicher.getBoolean("P5OstWestVar", false); 
		
		NaviAnsageIntervallSoll = (int) einstellungspeicher.getLong("NaviAnsageIntervallSoll", 15); //Int-Variable aus long-Preferences holen
		NaviAnsageIntervallString = "" + NaviAnsageIntervallSoll;		//TextView ausf�llen
		tvAnsageIntervall.setText(String.valueOf(NaviAnsageIntervallString)); //String zu TextView
		
		//Variablen beim Start �bernehmen aus PREFERENCES ////////////////////////////
		SharedPreferences variablenspeicher = getPreferences(Context.MODE_PRIVATE);
		ParkplatzMerken = variablenspeicher.getBoolean("ParkplatzMerken", false);
		NaviAnsageEin = variablenspeicher.getBoolean("NaviAnsageEin", false);
		
		//Parkplatzkoordinaten beim Start �bernehmen aus PREFERENCES //
		//SharedPreferences koordinatenspeicher = getPreferences(Context.MODE_WORLD_WRITEABLE); geändert, nicht genutz
		SharedPreferences koordinatenspeicher = PreferenceManager.getDefaultSharedPreferences(this);
		ParkplatzBreiteDezString = koordinatenspeicher.getString("ParkplatzBreiteDezString", "0.00000000");
		ParkplatzBreiteDezString = ParkplatzBreiteDezString.replace(",","."); //Komma durch Punkt ersetzen
		ParkplatzBreiteDez = (double) Double.parseDouble(ParkplatzBreiteDezString); //String zu double - Variable
		////Toast.makeText(getApplicationContext(), ParkplatzBreiteDezString, Toast.LENGTH_LONG).show();
		ParkplatzLaengeDezString = koordinatenspeicher.getString("ParkplatzLaengeDezString", "0.00000000");
		ParkplatzLaengeDezString = ParkplatzLaengeDezString.replace(",","."); //Komma durch Punkt ersetzen
		ParkplatzLaengeDez = (double) Double.parseDouble(ParkplatzLaengeDezString); //String zu double - Variable
		//Toast.makeText(getApplicationContext(), ParkplatzLaengeDezString, Toast.LENGTH_LONG).show();
		ParkplatzNordSuedVar = koordinatenspeicher.getBoolean("ParkplatzNordSuedVar", false);
		ParkplatzOstWestVar = koordinatenspeicher.getBoolean("ParkplatzOstWestVar", false);
		
		//Zielkoordinaten beim Start �bernehmen aus PREFERENCES //
		ZielBreiteDezString = koordinatenspeicher.getString("ZielBreiteDezString", "0.00000000");
		ZielBreiteDezString = ZielBreiteDezString.replace(",","."); //Komma durch Punkt ersetzen
		ZielBreiteDez = (double) Double.parseDouble(ZielBreiteDezString); //String zu double - Variable
		ZielLaengeDezString = koordinatenspeicher.getString("ZielLaengeDezString", "0.00000000");
		ZielLaengeDezString = ZielLaengeDezString.replace(",","."); //Komma durch Punkt ersetzen
		ZielLaengeDez = (double) Double.parseDouble(ZielLaengeDezString); //String zu double - Variable
		
		//EditText-Felder f�r Zieleingabe aus Prefs �bernehmen
		ZielBreiteGradString = koordinatenspeicher.getString("ZielBreiteGradString", "");
		ZielBreiteMinDezString = koordinatenspeicher.getString("ZielBreiteMinDezString", "");
		ZielLaengeGradString = koordinatenspeicher.getString("ZielLaengeGradString", "");
		ZielLaengeMinDezString = koordinatenspeicher.getString("ZielLaengeMinDezString", "");	
		
		//Parkplatzkoordinaten-TextView aktualisieren
		DecimalFormat df23 = new DecimalFormat("0.00000000"); //Dezimalzahl formatieren
		ParkplatzBreiteDezString = df23.format(ParkplatzBreiteDez); //Dezimalzahl formatieren
		ParkplatzBreiteDezString = ParkplatzBreiteDezString.replace(",","."); //Komma durch Punkt ersetzen
		tvParkplatzBreiteDez.setText(String.valueOf(ParkplatzBreiteDezString)); //String zu TextView
		DecimalFormat df24 = new DecimalFormat("0.00000000"); //Dezimalzahl formatieren
		ParkplatzLaengeDezString = df24.format(ParkplatzLaengeDez); //Dezimalzahl formatieren
		ParkplatzLaengeDezString = ParkplatzLaengeDezString.replace(",","."); //Komma durch Punkt ersetzen
		tvParkplatzLaengeDez.setText(String.valueOf(ParkplatzLaengeDezString)); //String zu TextView
						 
		//Zielkoordinaten-TextView aktualisieren
		////DecimalFormat df25 = new DecimalFormat("0.00000000"); //Dezimalzahl formatieren
		ZielBreiteDezString = df23.format(ZielBreiteDez); //Dezimalzahl formatieren
		ZielBreiteDezString = ZielBreiteDezString.replace(",","."); //Komma durch Punkt ersetzen
		tvZielBreiteDez.setText(String.valueOf(ZielBreiteDezString)); //String zu TextView
		////DecimalFormat df26 = new DecimalFormat("0.00000000"); //Dezimalzahl formatieren
		ZielLaengeDezString = df24.format(ZielLaengeDez); //Dezimalzahl formatieren
		ZielLaengeDezString = ZielLaengeDezString.replace(",","."); //Komma durch Punkt ersetzen
		tvZielLaengeDez.setText(String.valueOf(ZielLaengeDezString)); //String zu TextView
		
		//Buttonbeschriftung f�r N/S und O/W aktualisieren
		if (bNordSuedVar != true){
			bNordSued.setText("   N"); 
		}
		else{
			bNordSued.setText("   S");
		}
		if (bOstWestVar != true){
			bOstWest.setText("' E");
		}
		else{
			bOstWest.setText("' W");
		}
		
		//EditText-Felder f�r Zieleingabe aus Pref.-erstellten Variablen �bernehmen
		etZielBreiteGrad.setText(ZielBreiteGradString);
		etZielBreiteMinDez.setText(ZielBreiteMinDezString);
		etZielLaengeGrad.setText(ZielLaengeGradString);
		etZielLaengeMinDez.setText(ZielLaengeMinDezString);
	
		//EditText-Felder f�r Ziel-Speicher aus Prefs übernehmen	
		etP0Text.setText(koordinatenspeicher.getString("etP0Text", ""));
		etP1Text.setText(koordinatenspeicher.getString("etP1Text", ""));
		etP2Text.setText(koordinatenspeicher.getString("etP2Text", ""));
		etP3Text.setText(koordinatenspeicher.getString("etP3Text", ""));
		etP4Text.setText(koordinatenspeicher.getString("etP4Text", ""));
		etP5Text.setText(koordinatenspeicher.getString("etP5Text", ""));
	etP0BreiteGrad.setText(koordinatenspeicher.getString("etP0BreiteGrad", ""));
	etP0BreiteMinDez.setText(koordinatenspeicher.getString("etP0BreiteMinDez", ""));
	etP0LaengeGrad.setText(koordinatenspeicher.getString("etP0LaengeGrad", ""));
	etP0LaengeMinDez.setText(koordinatenspeicher.getString("etP0LaengeMinDez", ""));
	etP1BreiteGrad.setText(koordinatenspeicher.getString("etP1BreiteGrad", ""));
	etP1BreiteMinDez.setText(koordinatenspeicher.getString("etP1BreiteMinDez", ""));
	etP1LaengeGrad.setText(koordinatenspeicher.getString("etP1LaengeGrad", ""));
	etP1LaengeMinDez.setText(koordinatenspeicher.getString("etP1LaengeMinDez", ""));
	etP2BreiteGrad.setText(koordinatenspeicher.getString("etP2BreiteGrad", ""));
	etP2BreiteMinDez.setText(koordinatenspeicher.getString("etP2BreiteMinDez", ""));
	etP2LaengeGrad.setText(koordinatenspeicher.getString("etP2LaengeGrad", ""));
	etP2LaengeMinDez.setText(koordinatenspeicher.getString("etP2LaengeMinDez", ""));
	etP3BreiteGrad.setText(koordinatenspeicher.getString("etP3BreiteGrad", ""));
	etP3BreiteMinDez.setText(koordinatenspeicher.getString("etP3BreiteMinDez", ""));
	etP3LaengeGrad.setText(koordinatenspeicher.getString("etP3LaengeGrad", ""));
	etP3LaengeMinDez.setText(koordinatenspeicher.getString("etP3LaengeMinDez", ""));
	etP4BreiteGrad.setText(koordinatenspeicher.getString("etP4BreiteGrad", ""));
	etP4BreiteMinDez.setText(koordinatenspeicher.getString("etP4BreiteMinDez", ""));
	etP4LaengeGrad.setText(koordinatenspeicher.getString("etP4LaengeGrad", ""));
	etP4LaengeMinDez.setText(koordinatenspeicher.getString("etP4LaengeMinDez", ""));
	etP5BreiteGrad.setText(koordinatenspeicher.getString("etP5BreiteGrad", ""));
	etP5BreiteMinDez.setText(koordinatenspeicher.getString("etP5BreiteMinDez", ""));
	etP5LaengeGrad.setText(koordinatenspeicher.getString("etP5LaengeGrad", ""));
	etP5LaengeMinDez.setText(koordinatenspeicher.getString("etP5LaengeMinDez", ""));
	etNotizen.setText(koordinatenspeicher.getString("etNotizen", ""));
	
		//TextViewbeschriftung f�r N/S und O/W KoordSpeicher aktualisieren
		if (P0NordSuedVar != true){
			tvP0NordSued.setText("  N"); 
		}
		else{
			tvP0NordSued.setText("  S");
		}
		if (P0OstWestVar != true){
			tvP0OstWest.setText("' E");
		}
		else{
			tvP0OstWest.setText("' W");
		}
		
		if (P1NordSuedVar != true){
			tvP1NordSued.setText("  N"); 
		}
		else{
			tvP1NordSued.setText("  S");
		}
		if (P1OstWestVar != true){
			tvP1OstWest.setText("' E");
		}
		else{
			tvP1OstWest.setText("' W");
		}
	
		if (P2NordSuedVar != true){
			tvP2NordSued.setText("  N"); 
		}
		else{
			tvP2NordSued.setText("  S");
		}
		if (P2OstWestVar != true){
			tvP2OstWest.setText("' E");
		}
		else{
			tvP2OstWest.setText("' W");
		}
		
		if (P3NordSuedVar != true){
			tvP3NordSued.setText("  N"); 
		}
		else{
			tvP3NordSued.setText("  S");
		}
		if (P3OstWestVar != true){
			tvP3OstWest.setText("' E");
		}
		else{
			tvP3OstWest.setText("' W");
		}
	
		if (P4NordSuedVar != true){
			tvP4NordSued.setText("  N"); 
		}
		else{
			tvP4NordSued.setText("  S");
		}
		if (P4OstWestVar != true){
			tvP4OstWest.setText("' E");
		}
		else{
			tvP4OstWest.setText("' W");
		}
		
		if (P5NordSuedVar != true){
			tvP5NordSued.setText("  N"); 
		}
		else{
			tvP5NordSued.setText("  S");
		}
		if (P5OstWestVar != true){
			tvP5OstWest.setText("' E");
		}
		else{
			tvP5OstWest.setText("' W");
		}
		

		//Merker f�r "Letztes Ziel speichern" aus Prefs holen
		etP0TextMerker = koordinatenspeicher.getString("etP0TextMerker", "");
		ZielBreiteGradStringMerker = koordinatenspeicher.getString("ZielBreiteGradStringMerker", "");
		ZielBreiteMinDezStringMerker = koordinatenspeicher.getString("ZielBreiteMinDezStringMerker", "");
		ZielLaengeGradStringMerker = koordinatenspeicher.getString("ZielLaengeGradStringMerker", "");
		ZielLaengeMinDezStringMerker = koordinatenspeicher.getString("ZielLaengeMinDezStringMerker", "");
						
		//Button Listeners definieren
		bNaviStarten.setOnClickListener(this);
		bMinus.setOnClickListener(this);
		bPlus.setOnClickListener(this);
		bKwMinus.setOnClickListener(this);
		bKwPlus.setOnClickListener(this);
		bNaviAusMinus.setOnClickListener(this);
		bNaviAusPlus.setOnClickListener(this);
		bAufKarteZeigen.setOnClickListener(this);
		bStrassenNavigation.setOnClickListener(this);
		bKoordinatenSpeichernLaden.setOnClickListener(this);
		bErweiterteAnzeige.setOnClickListener(this);
		bKartenUndNavigation.setOnClickListener(this);
		bNotizenUndSonstiges.setOnClickListener(this);
		
		bAlsZielP0.setOnClickListener(this);
		bPosMerkenP1.setOnClickListener(this);
		bZielMerkenP1.setOnClickListener(this);
		bAlsZielP1.setOnClickListener(this);
		bPosMerkenP2.setOnClickListener(this);
		bZielMerkenP2.setOnClickListener(this);
		bAlsZielP2.setOnClickListener(this);
		bPosMerkenP3.setOnClickListener(this);
		bZielMerkenP3.setOnClickListener(this);
		bAlsZielP3.setOnClickListener(this);
		bPosMerkenP4.setOnClickListener(this);
		bZielMerkenP4.setOnClickListener(this);
		bAlsZielP4.setOnClickListener(this);
		bPosMerkenP5.setOnClickListener(this);
		bZielMerkenP5.setOnClickListener(this);
		bAlsZielP5.setOnClickListener(this);
		
		bNordSued.setOnClickListener(this);
		bOstWest.setOnClickListener(this);
		
				
		//Location-Manager vorbereiten
		
		locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
		locationListener = new locationListener();
		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
		GPSein = true;
		
		
		//Compass Sensor-Manager vorbereiten
		mSensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
		List<Sensor> mSensors = mSensorManager.getSensorList(Sensor.TYPE_ORIENTATION);
		mSensorManager.registerListener(mSensorEventListener, mSensors.get(0), SensorManager.SENSOR_DELAY_FASTEST);
		
		//ACCELEROMETER Sensor-Manager vorbereiten
		mASensorManager = (SensorManager)getSystemService(Context.SENSOR_SERVICE);
		List<Sensor> mASensors = mASensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
		mASensorManager.registerListener(mASensorEventListener, mASensors.get(0), SensorManager.SENSOR_DELAY_NORMAL);
			

		//Sprachausgabe vorbereiten
		Intent checkIntent = new Intent();
		checkIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
		startActivityForResult(checkIntent, MY_DATA_CHECK_CODE);
			
		//Medienlautst�rke �ndern mit Hardwaretasten, wenn Display ein
		setVolumeControlStream(AudioManager.STREAM_MUSIC);
		
		
		//UI (Button und TextViews) mit Text f�llen (beim Start und nach Bildschirmdrehung)
		if (NaviAnsageEin == true){ //wenn Navigation schon l�uft (nach Bildschirmdrehung)
			//tvTextAusgabe.setText(String.valueOf("Navigation l�uft. Warten auf GPS..."));
			bNaviStarten.setText("Navigation beenden");
		}
		else{    //wenn Navigation nicht l�uft z.B. beim ersten Start
			//tvTextAusgabe.setText(String.valueOf(" "));
			bNaviStarten.setText("Navigation starten");
		}
		
		
		//*********************************************
		//Parkplatz-Hintergrundservice starten/stoppen
		//*********************************************
		if (cbParkplatzAutomatischSpeichern.isChecked() == true){
			startService(new Intent(this, GCBabbleServiceParkplatz.class));
		}
		else{
			stopService(new Intent(this, GCBabbleServiceParkplatz.class));
		}
		
		
		
		
		//****************************************************************************************************
		// Intent-Start explizit (durch Intent-�bersetzer oder durch Service) oder durch IntentMain
		//****************************************************************************************************
		
		// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
		//Intent vom Eigenen Intent-�bersetzer GC-Babble Geo-Intent & Radar-Intent
		// - - - - - - - - - - - - - - - - - - - - - - - - - - - - - 
		
		//R�cksetzen
		IntentAbsender = null;
			
		Intent meinAusgehenderIntent = getIntent();
		String IntentZielBreiteDezString = meinAusgehenderIntent.getStringExtra("ZielBreiteDezString");
		String IntentZielLaengeDezString = meinAusgehenderIntent.getStringExtra("ZielLaengeDezString");
		IntentAbsender = meinAusgehenderIntent.getStringExtra("IntentAbsender");
		Boolean ErrorNumberFormatException = meinAusgehenderIntent.getBooleanExtra("ErrorNumberFormatException", true);
		
		if (ErrorNumberFormatException == true) { 
		    Toast.makeText(getApplicationContext(),  "Ein Fehler ist aufgetreten!!!", Toast.LENGTH_LONG).show();
			Toast.makeText(getApplicationContext(),  "Koordinaten-Format unbekannt!", Toast.LENGTH_LONG).show();
		    Toast.makeText(getApplicationContext(),  "Erneut versuchen oder Koordinaten von Hand eingeben!", Toast.LENGTH_LONG).show();
	    	Toast.makeText(getApplicationContext(),  "Keine Navigation möglich!", Toast.LENGTH_LONG).show();
		    //Eingabefelder mit "0" füllen
		    etZielBreiteGrad.setText("0"); //EditText Ziel Grad ausf�llen
			etZielBreiteMinDez.setText("0"); //EditText Ziel Min dezimal ausf�llen
			etZielLaengeGrad.setText("0"); //EditText Ziel Grad ausf�llen
			etZielLaengeMinDez.setText("0"); //EditText Ziel Min dezimal ausf�llen
		}//end if error
		
		
		//wenn String IntentAbsender gleich GCBabbleIntentGeo oder Radar //if mit String !!!!
		if ( ("GCBabbleIntentGeo".equals(IntentAbsender) || 
			 "GCBabbleIntentRadar".equals(IntentAbsender)) && 
			ErrorNumberFormatException == false) { 
			
			//Zur Sicherheit Hintergrundservice stoppen wenn �ber Intent neu gestartet wird
			stopService(new Intent(this, GCBabbleService.class));

			//Intent-Koordinaten String zu double umwandeln mit Punkt statt Komma
			IntentZielBreiteDezString = IntentZielBreiteDezString.replace(",","."); //Komma durch Punkt ersetzen
			ZielBreiteDez = (double) Double.parseDouble(IntentZielBreiteDezString); //String zu double - Variable
			IntentZielLaengeDezString = IntentZielLaengeDezString.replace(",","."); //Komma durch Punkt ersetzen
			ZielLaengeDez = (double) Double.parseDouble(IntentZielLaengeDezString); //String zu double - Variable
			
			
			//NEUE Koordinatenumrechnung mit Methode statt Rechnerisch
			
			//Zielkoordinaten Grad Breite dezimal in Grad und Min dezimal umrechnen und in EditText eintragen
			String ZielBreiteString = Location.convert(ZielBreiteDez, Location.FORMAT_MINUTES);
			////Toast.makeText(getApplicationContext(), ZielBreiteString, Toast.LENGTH_LONG).show();
			//Datenstring zerlegen an ":" in Grad und Min Dez
			String[] ZielBreiteStringTeil = ZielBreiteString.split(":");
			ZielBreiteGradString = ZielBreiteStringTeil[0];
			ZielBreiteMinDezString = ZielBreiteStringTeil[1].replace(",","."); //Komma durch Punkt ersetzen
			etZielBreiteGrad.setText(ZielBreiteGradString); //EditText Ziel Grad ausf�llen
			etZielBreiteMinDez.setText(ZielBreiteMinDezString); //EditText Ziel Min dezimal ausf�llen
			
			//Zielkoordinaten Grad L�nge dezimal in Grad und Min dezimal umrechnen und in EditText eintragen
			String ZielLaengeString = Location.convert(ZielLaengeDez, Location.FORMAT_MINUTES);
			////Toast.makeText(getApplicationContext(), ZielBreiteString, Toast.LENGTH_LONG).show();
			//Datenstring zerlegen an ":" in Grad und Min Dez
			String[] ZielLaengeStringTeil = ZielLaengeString.split(":");
			ZielLaengeGradString = ZielLaengeStringTeil[0];
			ZielLaengeMinDezString = ZielLaengeStringTeil[1].replace(",","."); //Komma durch Punkt ersetzen
			etZielLaengeGrad.setText(ZielLaengeGradString); //EditText Ziel Grad ausf�llen
			etZielLaengeMinDez.setText(ZielLaengeMinDezString); //EditText Ziel Min dezimal ausf�llen
		
			
			if (ZielBreiteDez < 0){
				bNordSued.setText("   S"); 
				bNordSuedVar = true;
				////SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
				Editor editor1 = einstellungspeicher.edit();
				editor1.putBoolean("bNordSuedVar", bNordSuedVar);
				editor1.commit();
				etZielBreiteGrad.setText(ZielBreiteGradString.replace("-","")); 		
			}
			else{
				bNordSued.setText("   N"); 
				bNordSuedVar = false;
				////SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
				Editor editor1 = einstellungspeicher.edit();
				editor1.putBoolean("bNordSuedVar", bNordSuedVar);
				editor1.commit();
			}
	
			if (ZielLaengeDez < 0){
				bOstWest.setText("' W");
				bOstWestVar = true;
				////SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
				Editor editor1 = einstellungspeicher.edit();
				editor1.putBoolean("bOstWestVar", bOstWestVar);
				editor1.commit();
				etZielLaengeGrad.setText(ZielLaengeGradString.replace("-","")); 			
			}
			else{
				bOstWest.setText("' E");
				bOstWestVar = false;
				////SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
				Editor editor1 = einstellungspeicher.edit();
				editor1.putBoolean("bOstWestVar", bOstWestVar);
				editor1.commit();
			}
			
		
			NaviAnsageEin = true; //Navigation sofort beginnen
			

			// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
			//Letztes Ziel speichern...
			// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
			if (ZielBreiteGradString.equals(ZielBreiteGradStringMerker) && ZielBreiteMinDezString.equals(ZielBreiteMinDezStringMerker) && 
					ZielLaengeGradString.equals(ZielLaengeGradStringMerker) && ZielLaengeMinDezString.equals(ZielLaengeMinDezStringMerker)){
				////Toast.makeText(getApplicationContext(),  "Kein neues Ziel*", Toast.LENGTH_SHORT).show();
			}//end "Kein neues Ziel*"
			else{//Wenn es ein neues Ziel gibt, dann...
				//...Merker in "Letztes Ziel" schreiben
				etP0Text.setText(etP0TextMerker);
				etP0BreiteGrad.setText(ZielBreiteGradStringMerker);
				etP0BreiteMinDez.setText(ZielBreiteMinDezStringMerker);
				etP0LaengeGrad.setText(ZielLaengeGradStringMerker);
				etP0LaengeMinDez.setText(ZielLaengeMinDezStringMerker);
				//...aktuelle Zielkoordinaten in Merker speichern
				String Zeitstempel = (String) DateFormat.format("dd.MM.yy / kk:mm", new java.util.Date());
				etP0TextMerker = "Letztes Ziel vom " + Zeitstempel;
				ZielBreiteGradStringMerker = ZielBreiteGradString;
				ZielBreiteMinDezStringMerker = ZielBreiteMinDezString;
				ZielLaengeGradStringMerker = ZielLaengeGradString;
				ZielLaengeMinDezStringMerker = ZielLaengeMinDezString;
				Toast.makeText(getApplicationContext(),  "Letztes Ziel gespeichert.", Toast.LENGTH_SHORT).show();
			}//end "Neues Ziel*" dann letztes Ziel speichern
			
			
			
			//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			// Hintergrund-Service starten
			//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		
		
			//Intent f�r Service vorbereiten
			Intent meinServiceIntent = new Intent(this, GCBabbleService.class);
											
			//Einstellungen per Intent an Service �bertragen
			meinServiceIntent.putExtra("KorrekturwinkelString", KorrekturwinkelString);
    		meinServiceIntent.putExtra("NaviAusDisplayString", NaviAusDisplayString); 
    		cbNaviLinksRechtsVar = cbNaviLinksRechts.isChecked();
    		meinServiceIntent.putExtra("cbNaviLinksRechtsVar", cbNaviLinksRechtsVar); 
    		cbDatumZeitVar = cbDatumZeit.isChecked();
    		meinServiceIntent.putExtra("cbDatumZeitVar", cbDatumZeitVar); 
    		cbBewRichtungEinVar = cbBewRichtungEin.isChecked();
    		meinServiceIntent.putExtra("cbBewRichtungEinVar", cbBewRichtungEinVar); 
    		cbParkplatzAutomatischSpeichernVar = cbParkplatzAutomatischSpeichern.isChecked();
    		meinServiceIntent.putExtra("cbParkplatzAutomatischSpeichernVar", cbParkplatzAutomatischSpeichernVar); 
    		cbKeinMagnetKompassVar = cbKeinMagnetKompass.isChecked();
    		meinServiceIntent.putExtra("cbKeinMagnetKompassVar", cbKeinMagnetKompassVar); 
			NaviAnsageIntervallString = tvAnsageIntervall.getText().toString(); //TextView zu String
			meinServiceIntent.putExtra("NaviAnsageIntervallString", NaviAnsageIntervallString);
			meinServiceIntent.putExtra("bNordSuedVar", bNordSuedVar); 
			meinServiceIntent.putExtra("bOstWestVar", bOstWestVar); 
			meinServiceIntent.putExtra("cbGehFahrHaltErkennungVar", cbGehFahrHaltErkennungVar);
			meinServiceIntent.putExtra("cbOptimiertesAnsageIntervallVar", cbOptimiertesAnsageIntervallVar);
			
		    //Button-Beschriftung und Variable anpassen
			NaviAnsageEin = true;
			bNaviStarten.setText("Navigation beenden");
			
			//Ziel-Koordinaten per Intent an Service �bertragen
			//Zielkoordinaten in Dezimal umrechnen wenn alle EditText-Felder ausgef�llt sind
			if (etZielBreiteGrad.length() > 0 && etZielLaengeGrad.length() > 0 && etZielBreiteMinDez.length() > 0 && etZielLaengeMinDez.length() > 0){
				//Eingaben aus EditText in float konvertieren
				ZielBreiteGradString = etZielBreiteGrad.getText().toString(); //EditText zu String
				ZielBreiteGrad = Float.parseFloat(ZielBreiteGradString); //String zu float
				ZielBreiteMinDezString = etZielBreiteMinDez.getText().toString(); //EditText zu String
				ZielBreiteMinDez = Float.parseFloat(ZielBreiteMinDezString); //String zu float
				ZielLaengeGradString = etZielLaengeGrad.getText().toString(); //EditText zu String
				ZielLaengeGrad = Float.parseFloat(ZielLaengeGradString); //String zu float
				ZielLaengeMinDezString = etZielLaengeMinDez.getText().toString(); //EditText zu String
				ZielLaengeMinDez = Float.parseFloat(ZielLaengeMinDezString); //String zu float
			
				//Berechnung ZielBreiteDez und ZielLaengeDez
				ZielBreiteDez = ZielBreiteGrad + (ZielBreiteMinDez / 60); //Berechnung Ziel dezimal
				ZielLaengeDez = ZielLaengeGrad + (ZielLaengeMinDez / 60); //Berechnung Ziel dezimal
				
				if (bNordSuedVar == true){
					ZielBreiteDez = ZielBreiteDez * (-1);
				}
				if (bOstWestVar == true){
					ZielLaengeDez = ZielLaengeDez * (-1);
				}
			}
			ZielBreiteDezString = "" + ZielBreiteDez;
			ZielLaengeDezString = "" + ZielLaengeDez;
			meinServiceIntent.putExtra("ZielBreiteDezString", ZielBreiteDezString);
    		meinServiceIntent.putExtra("ZielLaengeDezString", ZielLaengeDezString); 
			
    		//Aktuelle Variablen per Intent an Service �bertragen
    		meinServiceIntent.putExtra("NaviAnsageEin", NaviAnsageEin); 
			
    		//Zusammenstellung "Untertitel" Foreground-Service
			meinServiceIntent.putExtra("IventInfo", 
					"Ziel:" + bNordSued.getText() + " " + ZielBreiteGradString + " Grad " + ZielBreiteMinDezString +
					bOstWest.getText() + " " + ZielLaengeGradString + " Grad " + ZielLaengeMinDezString + "' ");
			
			
			//Service-Intent starten
			startService(meinServiceIntent);  
		  
		
		}//end if ("GCBabbleIntentGeo".equals(IntentAbsender)) 

		
		
		if ("GCBabbleIntentMain".equals(IntentAbsender)  && NaviAnsageEin == true) { 
			////Toast.makeText(getApplicationContext(), "Intent-Absender: GCBabbleIntentMain bei Navi ein*", Toast.LENGTH_LONG).show();

			//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			// Hintergrund-Service starten
			//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
		
		
			//Intent f�r Service vorbereiten
			Intent meinServiceIntent = new Intent(this, GCBabbleService.class);
											
			//Einstellungen per Intent an Service �bertragen
			meinServiceIntent.putExtra("KorrekturwinkelString", KorrekturwinkelString);
    		meinServiceIntent.putExtra("NaviAusDisplayString", NaviAusDisplayString); 
    		cbNaviLinksRechtsVar = cbNaviLinksRechts.isChecked();
    		meinServiceIntent.putExtra("cbNaviLinksRechtsVar", cbNaviLinksRechtsVar); 
    		cbDatumZeitVar = cbDatumZeit.isChecked();
    		meinServiceIntent.putExtra("cbDatumZeitVar", cbDatumZeitVar); 
    		cbBewRichtungEinVar = cbBewRichtungEin.isChecked();
    		meinServiceIntent.putExtra("cbBewRichtungEinVar", cbBewRichtungEinVar); 
    		cbParkplatzAutomatischSpeichernVar = cbParkplatzAutomatischSpeichern.isChecked();
			meinServiceIntent.putExtra("cbParkplatzAutomatischSpeichernVar", cbParkplatzAutomatischSpeichernVar); 
			cbKeinMagnetKompassVar = cbKeinMagnetKompass.isChecked();
    		meinServiceIntent.putExtra("cbKeinMagnetKompassVar", cbKeinMagnetKompassVar); 
			NaviAnsageIntervallString = tvAnsageIntervall.getText().toString(); //TextView zu String
			meinServiceIntent.putExtra("NaviAnsageIntervallString", NaviAnsageIntervallString);
			meinServiceIntent.putExtra("cbGehFahrHaltErkennungVar", cbGehFahrHaltErkennungVar);
		    meinServiceIntent.putExtra("cbOptimiertesAnsageIntervallVar", cbOptimiertesAnsageIntervallVar);
			
		    //Button-Beschriftung und Variable anpassen
			NaviAnsageEin = true;
			bNaviStarten.setText("Navigation beenden");
			
			//Ziel-Koordinaten per Intent an Service �bertragen
			//Zielkoordinaten in Dezimal umrechnen wenn alle EditText-Felder ausgef�llt sind
			if (etZielBreiteGrad.length() > 0 && etZielLaengeGrad.length() > 0 && etZielBreiteMinDez.length() > 0 && etZielLaengeMinDez.length() > 0){
				//Eingaben aus EditText in float konvertieren
				ZielBreiteGradString = etZielBreiteGrad.getText().toString(); //EditText zu String
				ZielBreiteGrad = Float.parseFloat(ZielBreiteGradString); //String zu float
				ZielBreiteMinDezString = etZielBreiteMinDez.getText().toString(); //EditText zu String
				ZielBreiteMinDez = Float.parseFloat(ZielBreiteMinDezString); //String zu float
				ZielLaengeGradString = etZielLaengeGrad.getText().toString(); //EditText zu String
				ZielLaengeGrad = Float.parseFloat(ZielLaengeGradString); //String zu float
				ZielLaengeMinDezString = etZielLaengeMinDez.getText().toString(); //EditText zu String
				ZielLaengeMinDez = Float.parseFloat(ZielLaengeMinDezString); //String zu float
			
				//Berechnung ZielBreiteDez und ZielLaengeDez
				ZielBreiteDez = ZielBreiteGrad + (ZielBreiteMinDez / 60); //Berechnung Ziel dezimal
				ZielLaengeDez = ZielLaengeGrad + (ZielLaengeMinDez / 60); //Berechnung Ziel dezimal
				
				if (bNordSuedVar == true){
					ZielBreiteDez = ZielBreiteDez * (-1);
				}
				if (bOstWestVar == true){
					ZielLaengeDez = ZielLaengeDez * (-1);
				}
			}
			ZielBreiteDezString = "" + ZielBreiteDez;
			ZielLaengeDezString = "" + ZielLaengeDez;
			meinServiceIntent.putExtra("ZielBreiteDezString", ZielBreiteDezString);
    		meinServiceIntent.putExtra("ZielLaengeDezString", ZielLaengeDezString); 
			
    		//Aktuelle Variablen per Intent an Service �bertragen
    		meinServiceIntent.putExtra("NaviAnsageEin", NaviAnsageEin); 
			
    		//Zusammenstellung "Untertitel" Foreground-Service
			meinServiceIntent.putExtra("IventInfo", 
					"Ziel:" + bNordSued.getText() + " " + ZielBreiteGradString + " Grad " + ZielBreiteMinDezString +
					bOstWest.getText() + " " + ZielLaengeGradString + " Grad " + ZielLaengeMinDezString + "' ");
			
			
			//Service-Intent starten
			startService(meinServiceIntent);  
				
		}//end if ("GCBabbleIntentMain".equals(IntentAbsender)) 
		
		
			
			
	}// ******** end public void onCreate(Bundle savedInstanceState) ************
    		
	
	
	
	
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
			
			//Sprachausgabe beim ersten Satfix nach 5 Sek. nach dem Start
			if (onCreateZeit <= (System.currentTimeMillis() - 15000) && satFixAnsageErl == false){
				tts.speak("Satt-Fix.",TextToSpeech.QUEUE_ADD, null);
				satFixAnsageErl = true;
			}
			else { //wenn Satfix vor 15 Sekunden
				satFixAnsageErl = true;
			}
				
			//Geschwindigkeit aktualisieren
			location.getSpeed();
			float GeschwindigkeitMpS = location.getSpeed();
			GeschwindigkeitKpH = (float) (GeschwindigkeitMpS * 3.6);
			DecimalFormat df6 = new DecimalFormat("0"); //Dezimalzahl formatieren
			String GeschwindigkeitKpHString = df6.format(GeschwindigkeitKpH); //Dezimalzahl formatieren
			tvGeschwindigkeit.setText(String.valueOf(GeschwindigkeitKpHString));
			
			//H�he aktualisieren
			location.getAccuracy();
			float Hoehe = (float) location.getAltitude();
			DecimalFormat df5 = new DecimalFormat("0"); //Dezimalzahl formatieren
			String HoeheString = df5.format(Hoehe); //Dezimalzahl formatieren
			tvHoehe.setText(String.valueOf(HoeheString));
			
			//Genauigkeit aktualisieren
			location.getAccuracy();
			Abweichung = location.getAccuracy();
			DecimalFormat df4 = new DecimalFormat("0"); //Dezimalzahl formatieren
			AbweichungString = df4.format(Abweichung); //Dezimalzahl formatieren
			tvAbweichung.setText(String.valueOf(AbweichungString));
			
			//Bewegungsrichtung aktualisieren
			location.getBearing();
			GpsBearingGrad = location.getBearing();
			////DecimalFormat df4 = new DecimalFormat("0"); //Dezimalzahl formatieren
			GpsBearingGradString = df4.format(GpsBearingGrad); //Dezimalzahl formatieren
			tvBewRichtungGrad.setText(String.valueOf(GpsBearingGradString));
			////tvBewRichtungGrad.setTextColor(Color.rgb(0,200,0));
		//Letzte Bewegungsrichtung merken ACHTUNG: Bei Aenderungen auch in Service aktualisieren!!!
		if ((GeschwindigkeitKpH >= 12 && Abweichung <= 60)
				|| (GeschwindigkeitKpH >= 10 && Abweichung <= 8)
				|| (GeschwindigkeitKpH >= 8 && Abweichung <= 4)){	// Letzte Bewegungsrichtung merken
				LetzteBewRtg = GpsBearingGrad;
				DecimalFormat df1 = new DecimalFormat("0"); //Dezimalzahl formatieren
				LetzteBewRtgString = df1.format(LetzteBewRtg); //Dezimalzahl formatieren
				tvLetzteBewRtg.setText(String.valueOf(LetzteBewRtgString));
			}
					
			
			//Parkplatz-Koordinaten manuell speichern (nach Druck auf "Parkplatz merken")
			if (ParkplatzMerken == true){
				location.getLatitude();
				location.getLongitude();
				ParkplatzBreiteDez = location.getLatitude();
				ParkplatzLaengeDez = location.getLongitude();
				ParkplatzMerken = false; 
				//Prefs speichern 
				SharedPreferences variablenspeicher = getPreferences(Context.MODE_PRIVATE);
				Editor editor2 = variablenspeicher.edit();
				editor2.putBoolean("ParkplatzMerken", ParkplatzMerken); 
				editor2.commit();
				cbParkplatzAutomatischSpeichern.setChecked(false); //Checkbox deaktivieren
				Toast.makeText(getApplicationContext(),  "Parkplatz gespeichert. Automatisches Speichern aus.", Toast.LENGTH_LONG).show();
				tts.speak("Parkplatz gespeichert.",TextToSpeech.QUEUE_ADD, null);
				//loParkplatz.setVisibility(View.VISIBLE);
				DecimalFormat df23 = new DecimalFormat("0.00000000"); //Dezimalzahl formatieren
				ParkplatzBreiteDezString = df23.format(ParkplatzBreiteDez); //Dezimalzahl formatieren
				ParkplatzBreiteDezString = ParkplatzBreiteDezString.replace(",","."); //Komma durch Punkt ersetzen
				tvParkplatzBreiteDez.setText(String.valueOf(ParkplatzBreiteDezString)); //String zu TextView
				DecimalFormat df24 = new DecimalFormat("0.00000000"); //Dezimalzahl formatieren
				ParkplatzLaengeDezString = df24.format(ParkplatzLaengeDez); //Dezimalzahl formatieren
				ParkplatzLaengeDezString = ParkplatzLaengeDezString.replace(",","."); //Komma durch Punkt ersetzen
				tvParkplatzLaengeDez.setText(String.valueOf(ParkplatzLaengeDezString)); //String zu TextView
			
				//Festlegung der L�ngen-/Breitengrade der Parkplatzkoordinaten 
				if (ParkplatzBreiteDez <= 0){
					tvParkplatzNordSued.setText("S"); 
					ParkplatzNordSuedVar = true;
					//Toast.makeText(getApplicationContext(),  "S Breite", Toast.LENGTH_LONG).show();
				
				}
				else{
					tvParkplatzNordSued.setText("N"); 
					ParkplatzNordSuedVar = false;
					//Toast.makeText(getApplicationContext(),  "N Breite", Toast.LENGTH_LONG).show();
				
				}
		
				if (ParkplatzLaengeDez <= 0){
					tvParkplatzOstWest.setText("W");
					ParkplatzOstWestVar = true;
					//Toast.makeText(getApplicationContext(),  "W Länge", Toast.LENGTH_LONG).show();
				
				}
				else{
					tvParkplatzOstWest.setText("E");
					ParkplatzOstWestVar = false;
					//Toast.makeText(getApplicationContext(),  "E Länge", Toast.LENGTH_LONG).show();
				
				}
				
				//Parkplatzkoordinaten per Intentstart an Activity zum Speichern �bergeben
				Intent meinParkplatzSpeichernIntent = new Intent();
				meinParkplatzSpeichernIntent.setClass(getApplicationContext(), GCBabbleParkplatzSpeichern.class);
				meinParkplatzSpeichernIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);		  
				meinParkplatzSpeichernIntent.putExtra("ParkplatzBreiteDezString", ParkplatzBreiteDezString);
				meinParkplatzSpeichernIntent.putExtra("ParkplatzLaengeDezString", ParkplatzLaengeDezString);
				meinParkplatzSpeichernIntent.putExtra("IntentAbsender", "GCBabbleServiceParkplatz");
				meinParkplatzSpeichernIntent.putExtra("ParkplatzNordSuedVar", ParkplatzNordSuedVar);
				meinParkplatzSpeichernIntent.putExtra("ParkplatzOstWestVar", ParkplatzOstWestVar);
				
				startActivity(meinParkplatzSpeichernIntent);
								
			}
			
			//Aktuelle Position speichern in Koordinaten-Speichern/Laden
			if (PositionMerken == true){
				location.getLatitude();
				location.getLongitude();
				PosBreiteDezString = "" + location.getLatitude();
				PosLaengeDezString = "" + location.getLongitude();
				PositionMerken = false; 
				Toast.makeText(getApplicationContext(),  "Aktuelle Position gespeichert.", Toast.LENGTH_LONG).show();
				tts.speak("Position gespeichert.",TextToSpeech.QUEUE_ADD, null);
				float PosBreiteDez = Float.parseFloat(PosBreiteDezString); //String zu float
				float PosLaengeDez = Float.parseFloat(PosLaengeDezString); //String zu float	
			
        //NeueKoordinatenumrechnung
			
		if (PosMerkenP == 1) { //Erkennung, welche EditText-Boxen ausgef�llt werden m�ssen / welcher Button gedr�ckt wurde	    
			//Zielkoordinaten Grad Breite dezimal in Grad und Min dezimal umrechnen und in EditText eintragen
			String PosBreiteString = Location.convert(PosBreiteDez, Location.FORMAT_MINUTES);
			//Datenstring zerlegen an ":" in Grad und Min Dez
			String[] PosBreiteStringTeil = PosBreiteString.split(":");
			String PosBreiteGradString = PosBreiteStringTeil[0];
			String PosBreiteMinDezString = PosBreiteStringTeil[1].replace(",","."); //Komma durch Punkt ersetzen
			etP1BreiteGrad.setText(PosBreiteGradString); //EditText Ziel Grad ausf�llen
			etP1BreiteMinDez.setText(PosBreiteMinDezString); //EditText Ziel Min dezimal ausf�llen
			
			//Zielkoordinaten Grad L�nge dezimal in Grad und Min dezimal umrechnen und in EditText eintragen
			String PosLaengeString = Location.convert(PosLaengeDez, Location.FORMAT_MINUTES);
			//Datenstring zerlegen an ":" in Grad und Min Dez
			String[] PosLaengeStringTeil = PosLaengeString.split(":");
			String PosLaengeGradString = PosLaengeStringTeil[0];
			String PosLaengeMinDezString = PosLaengeStringTeil[1].replace(",","."); //Komma durch Punkt ersetzen
			etP1LaengeGrad.setText(PosLaengeGradString); //EditText Ziel Grad ausf�llen
			etP1LaengeMinDez.setText(PosLaengeMinDezString); //EditText Ziel Min dezimal ausf�llen
		
			String Zeitstempel = (String) DateFormat.format("dd.MM.yy / kk:mm", new java.util.Date());
			etP1Text.setText("Position am " + Zeitstempel + " +/- " + AbweichungString +" m");
			
			if (PosBreiteDez <= 0){
				tvP1NordSued.setText("  S");
				P1NordSuedVar = true;
				SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
				Editor editor1 = einstellungspeicher.edit();
				editor1.putBoolean("P1NordSuedVar", P1NordSuedVar);
				editor1.commit();
				etP1BreiteGrad.setText(PosBreiteGradString.replace("-","")); 		
			}
			else{
				tvP1NordSued.setText("  N");
				P1NordSuedVar = false;
				SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
				Editor editor1 = einstellungspeicher.edit();
				editor1.putBoolean("P1NordSuedVar", P1NordSuedVar);
				editor1.commit();
			}
			if (PosLaengeDez <= 0){
				tvP1OstWest.setText("' W");
				P1OstWestVar = true;
				SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
				Editor editor1 = einstellungspeicher.edit();
				editor1.putBoolean("P1OstWestVar", P1OstWestVar);
				editor1.commit();
				etP1LaengeGrad.setText(PosLaengeGradString.replace("-","")); 			
			}
			else{
				tvP1OstWest.setText("' E");
				P1OstWestVar = false;
				SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
				Editor editor1 = einstellungspeicher.edit();
				editor1.putBoolean("P1OstWestVar", P1OstWestVar);
				editor1.commit();
			}	
		}//end if (PosMerkenP = ...
					
			
		if (PosMerkenP == 2) { //Erkennung, welche EditText-Boxen ausgef�llt werden m�ssen / welcher Button gedr�ckt wurde	    
			//Zielkoordinaten Grad Breite dezimal in Grad und Min dezimal umrechnen und in EditText eintragen
			String PosBreiteString = Location.convert(PosBreiteDez, Location.FORMAT_MINUTES);
			//Datenstring zerlegen an ":" in Grad und Min Dez
			String[] PosBreiteStringTeil = PosBreiteString.split(":");
			String PosBreiteGradString = PosBreiteStringTeil[0];
			String PosBreiteMinDezString = PosBreiteStringTeil[1].replace(",","."); //Komma durch Punkt ersetzen
			etP2BreiteGrad.setText(PosBreiteGradString); //EditText Ziel Grad ausf�llen
			etP2BreiteMinDez.setText(PosBreiteMinDezString); //EditText Ziel Min dezimal ausf�llen
			
			//Zielkoordinaten Grad L�nge dezimal in Grad und Min dezimal umrechnen und in EditText eintragen
			String PosLaengeString = Location.convert(PosLaengeDez, Location.FORMAT_MINUTES);
			//Datenstring zerlegen an ":" in Grad und Min Dez
			String[] PosLaengeStringTeil = PosLaengeString.split(":");
			String PosLaengeGradString = PosLaengeStringTeil[0];
			String PosLaengeMinDezString = PosLaengeStringTeil[1].replace(",","."); //Komma durch Punkt ersetzen
			etP2LaengeGrad.setText(PosLaengeGradString); //EditText Ziel Grad ausf�llen
			etP2LaengeMinDez.setText(PosLaengeMinDezString); //EditText Ziel Min dezimal ausf�llen
		
			String Zeitstempel = (String) DateFormat.format("dd.MM.yy / kk:mm", new java.util.Date());
			etP2Text.setText("Position am " + Zeitstempel + " +/- " + AbweichungString +" m");
			
			if (PosBreiteDez <= 0){
				tvP2NordSued.setText("  S");
				P2NordSuedVar = true;
				SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
				Editor editor1 = einstellungspeicher.edit();
				editor1.putBoolean("P2NordSuedVar", P2NordSuedVar);
				editor1.commit();
				etP2BreiteGrad.setText(PosBreiteGradString.replace("-","")); 		
			}
			else{
				tvP2NordSued.setText("  N");
				P2NordSuedVar = false;
				SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
				Editor editor1 = einstellungspeicher.edit();
				editor1.putBoolean("P2NordSuedVar", P2NordSuedVar);
				editor1.commit();
			}
			if (PosLaengeDez <= 0){
				tvP2OstWest.setText("' W");
				P2OstWestVar = true;
				SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
				Editor editor1 = einstellungspeicher.edit();
				editor1.putBoolean("P2OstWestVar", P2OstWestVar);
				editor1.commit();
				etP2LaengeGrad.setText(PosLaengeGradString.replace("-","")); 			
			}
			else{
				tvP2OstWest.setText("' E");
				P2OstWestVar = false;
				SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
				Editor editor1 = einstellungspeicher.edit();
				editor1.putBoolean("P2OstWestVar", P2OstWestVar);
				editor1.commit();
			}	
		}//end if (PosMerkenP = ...
				
			
		if (PosMerkenP == 3) { //Erkennung, welche EditText-Boxen ausgef�llt werden m�ssen / welcher Button gedr�ckt wurde	    
			//Zielkoordinaten Grad Breite dezimal in Grad und Min dezimal umrechnen und in EditText eintragen
			String PosBreiteString = Location.convert(PosBreiteDez, Location.FORMAT_MINUTES);
			//Datenstring zerlegen an ":" in Grad und Min Dez
			String[] PosBreiteStringTeil = PosBreiteString.split(":");
			String PosBreiteGradString = PosBreiteStringTeil[0];
			String PosBreiteMinDezString = PosBreiteStringTeil[1].replace(",","."); //Komma durch Punkt ersetzen
			etP3BreiteGrad.setText(PosBreiteGradString); //EditText Ziel Grad ausf�llen
			etP3BreiteMinDez.setText(PosBreiteMinDezString); //EditText Ziel Min dezimal ausf�llen
			
			//Zielkoordinaten Grad L�nge dezimal in Grad und Min dezimal umrechnen und in EditText eintragen
			String PosLaengeString = Location.convert(PosLaengeDez, Location.FORMAT_MINUTES);
			//Datenstring zerlegen an ":" in Grad und Min Dez
			String[] PosLaengeStringTeil = PosLaengeString.split(":");
			String PosLaengeGradString = PosLaengeStringTeil[0];
			String PosLaengeMinDezString = PosLaengeStringTeil[1].replace(",","."); //Komma durch Punkt ersetzen
			etP3LaengeGrad.setText(PosLaengeGradString); //EditText Ziel Grad ausf�llen
			etP3LaengeMinDez.setText(PosLaengeMinDezString); //EditText Ziel Min dezimal ausf�llen
		
			String Zeitstempel = (String) DateFormat.format("dd.MM.yy / kk:mm", new java.util.Date());
			etP3Text.setText("Position am " + Zeitstempel + " +/- " + AbweichungString +" m");
			
			if (PosBreiteDez <= 0){
				tvP3NordSued.setText("  S");
				P3NordSuedVar = true;
				SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
				Editor editor1 = einstellungspeicher.edit();
				editor1.putBoolean("P3NordSuedVar", P3NordSuedVar);
				editor1.commit();
				etP3BreiteGrad.setText(PosBreiteGradString.replace("-","")); 		
			}
			else{
				tvP3NordSued.setText("  N");
				P3NordSuedVar = false;
				SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
				Editor editor1 = einstellungspeicher.edit();
				editor1.putBoolean("P3NordSuedVar", P3NordSuedVar);
				editor1.commit();
			}
			if (PosLaengeDez <= 0){
				tvP3OstWest.setText("' W");
				P3OstWestVar = true;
				SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
				Editor editor1 = einstellungspeicher.edit();
				editor1.putBoolean("P3OstWestVar", P3OstWestVar);
				editor1.commit();
				etP3LaengeGrad.setText(PosLaengeGradString.replace("-","")); 			
			}
			else{
				tvP3OstWest.setText("' E");
				P3OstWestVar = false;
				SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
				Editor editor1 = einstellungspeicher.edit();
				editor1.putBoolean("P3OstWestVar", P3OstWestVar);
				editor1.commit();
			}	
		}//end if (PosMerkenP = ...
				

				if (PosMerkenP == 4) { //Erkennung, welche EditText-Boxen ausgef�llt werden m�ssen / welcher Button gedr�ckt wurde	    
					//Zielkoordinaten Grad Breite dezimal in Grad und Min dezimal umrechnen und in EditText eintragen
					String PosBreiteString = Location.convert(PosBreiteDez, Location.FORMAT_MINUTES);
					//Datenstring zerlegen an ":" in Grad und Min Dez
					String[] PosBreiteStringTeil = PosBreiteString.split(":");
					String PosBreiteGradString = PosBreiteStringTeil[0];
					String PosBreiteMinDezString = PosBreiteStringTeil[1].replace(",","."); //Komma durch Punkt ersetzen
					etP4BreiteGrad.setText(PosBreiteGradString); //EditText Ziel Grad ausf�llen
					etP4BreiteMinDez.setText(PosBreiteMinDezString); //EditText Ziel Min dezimal ausf�llen

					//Zielkoordinaten Grad L�nge dezimal in Grad und Min dezimal umrechnen und in EditText eintragen
					String PosLaengeString = Location.convert(PosLaengeDez, Location.FORMAT_MINUTES);
					//Datenstring zerlegen an ":" in Grad und Min Dez
					String[] PosLaengeStringTeil = PosLaengeString.split(":");
					String PosLaengeGradString = PosLaengeStringTeil[0];
					String PosLaengeMinDezString = PosLaengeStringTeil[1].replace(",","."); //Komma durch Punkt ersetzen
					etP4LaengeGrad.setText(PosLaengeGradString); //EditText Ziel Grad ausf�llen
					etP4LaengeMinDez.setText(PosLaengeMinDezString); //EditText Ziel Min dezimal ausf�llen

					String Zeitstempel = (String) DateFormat.format("dd.MM.yy / kk:mm", new java.util.Date());
					etP4Text.setText("Position am " + Zeitstempel + " +/- " + AbweichungString +" m");

					if (PosBreiteDez <= 0){
						tvP4NordSued.setText("  S");
						P4NordSuedVar = true;
						SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
						Editor editor1 = einstellungspeicher.edit();
						editor1.putBoolean("P4NordSuedVar", P4NordSuedVar);
						editor1.commit();
						etP4BreiteGrad.setText(PosBreiteGradString.replace("-","")); 		
					}
					else{
						tvP4NordSued.setText("  N");
						P4NordSuedVar = false;
						SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
						Editor editor1 = einstellungspeicher.edit();
						editor1.putBoolean("P4NordSuedVar", P4NordSuedVar);
						editor1.commit();
					}
					if (PosLaengeDez <= 0){
						tvP4OstWest.setText("' W");
						P4OstWestVar = true;
						SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
						Editor editor1 = einstellungspeicher.edit();
						editor1.putBoolean("P4OstWestVar", P4OstWestVar);
						editor1.commit();
						etP4LaengeGrad.setText(PosLaengeGradString.replace("-","")); 			
					}
					else{
						tvP4OstWest.setText("' E");
						P4OstWestVar = false;
						SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
						Editor editor1 = einstellungspeicher.edit();
						editor1.putBoolean("P4OstWestVar", P4OstWestVar);
						editor1.commit();
					}	
				}//end if (PosMerkenP = ...	
				

				if (PosMerkenP == 5) { //Erkennung, welche EditText-Boxen ausgef�llt werden m�ssen / welcher Button gedr�ckt wurde	    
					//Zielkoordinaten Grad Breite dezimal in Grad und Min dezimal umrechnen und in EditText eintragen
					String PosBreiteString = Location.convert(PosBreiteDez, Location.FORMAT_MINUTES);
					//Datenstring zerlegen an ":" in Grad und Min Dez
					String[] PosBreiteStringTeil = PosBreiteString.split(":");
					String PosBreiteGradString = PosBreiteStringTeil[0];
					String PosBreiteMinDezString = PosBreiteStringTeil[1].replace(",","."); //Komma durch Punkt ersetzen
					etP5BreiteGrad.setText(PosBreiteGradString); //EditText Ziel Grad ausf�llen
					etP5BreiteMinDez.setText(PosBreiteMinDezString); //EditText Ziel Min dezimal ausf�llen

					//Zielkoordinaten Grad L�nge dezimal in Grad und Min dezimal umrechnen und in EditText eintragen
					String PosLaengeString = Location.convert(PosLaengeDez, Location.FORMAT_MINUTES);
					//Datenstring zerlegen an ":" in Grad und Min Dez
					String[] PosLaengeStringTeil = PosLaengeString.split(":");
					String PosLaengeGradString = PosLaengeStringTeil[0];
					String PosLaengeMinDezString = PosLaengeStringTeil[1].replace(",","."); //Komma durch Punkt ersetzen
					etP5LaengeGrad.setText(PosLaengeGradString); //EditText Ziel Grad ausf�llen
					etP5LaengeMinDez.setText(PosLaengeMinDezString); //EditText Ziel Min dezimal ausf�llen

					String Zeitstempel = (String) DateFormat.format("dd.MM.yy / kk:mm", new java.util.Date());
					etP5Text.setText("Position am " + Zeitstempel + " +/- " + AbweichungString +" m");

					if (PosBreiteDez <= 0){
						tvP5NordSued.setText("  S");
						P5NordSuedVar = true;
						SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
						Editor editor1 = einstellungspeicher.edit();
						editor1.putBoolean("P5NordSuedVar", P5NordSuedVar);
						editor1.commit();
						etP5BreiteGrad.setText(PosBreiteGradString.replace("-","")); 		
					}
					else{
						tvP5NordSued.setText("  N");
						P5NordSuedVar = false;
						SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
						Editor editor1 = einstellungspeicher.edit();
						editor1.putBoolean("P5NordSuedVar", P5NordSuedVar);
						editor1.commit();
					}
					if (PosLaengeDez <= 0){
						tvP5OstWest.setText("' W");
						P5OstWestVar = true;
						SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
						Editor editor1 = einstellungspeicher.edit();
						editor1.putBoolean("P5OstWestVar", P5OstWestVar);
						editor1.commit();
						etP5LaengeGrad.setText(PosLaengeGradString.replace("-","")); 			
					}
					else{
						tvP5OstWest.setText("' E");
						P5OstWestVar = false;
						SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
						Editor editor1 = einstellungspeicher.edit();
						editor1.putBoolean("P5OstWestVar", P5OstWestVar);
						editor1.commit();
					}	
				}//end if (PosMerkenP = ...
				
				
				PosMerkenP = 0; //Buttonmerker zur�cksetzen
			}//end if (PositionMerken == true)
			
					
			//Variablen und TextViews aktualisieren
			location.getLatitude();
			location.getLongitude();
			PosBreiteDezString = "" + location.getLatitude();
			PosLaengeDezString = "" + location.getLongitude();
			//String PosBreiteDezString = "" + 51; /////////Testkoordinaten aktuelle Position///////////////
			//String PosLaengeDezString = "" + 11; /////////Testkoordinaten aktuelle Position///////////////
			tvPosBreiteDez.setText(String.valueOf(PosBreiteDezString));
			tvPosLaengeDez.setText(String.valueOf(PosLaengeDezString));
			float PosBreiteDez = Float.parseFloat(PosBreiteDezString); //String zu float
			float PosLaengeDez = Float.parseFloat(PosLaengeDezString); //String zu float	
												
			//EditText-Box f�r Gradeingabe Ziel automatisch eintragen wenn sie leer sind
			if (etZielBreiteGrad.length() < 1) { //L�nge des EditText-Strings �berpr�fen
				ZielBreiteGradString = df4.format((location.getLatitude()) - 0.4); //Dezimalzahl formatieren
				etZielBreiteGrad.setText(String.valueOf(ZielBreiteGradString));
			}
			
			if (etZielLaengeGrad.length() < 1){
				ZielLaengeGradString = df4.format((location.getLongitude()) - 0.4); //Dezimalzahl formatieren
				etZielLaengeGrad.setText(String.valueOf(ZielLaengeGradString));				
			}
			
			
			
			//Teil 1/2: Zielkoordinaten in Dezimal umrechnen wenn alle EditText-Felder ausgef�llt sind
			if (etZielBreiteGrad.length() > 0 && etZielLaengeGrad.length() > 0 && etZielBreiteMinDez.length() > 0 && etZielLaengeMinDez.length() > 0){
				//Eingaben aus EditText in float konvertieren
				ZielBreiteGradString = etZielBreiteGrad.getText().toString(); //EditText zu String
				ZielBreiteGrad = Float.parseFloat(ZielBreiteGradString); //String zu float
				ZielBreiteMinDezString = etZielBreiteMinDez.getText().toString(); //EditText zu String
				ZielBreiteMinDez = Float.parseFloat(ZielBreiteMinDezString); //String zu float
				ZielLaengeGradString = etZielLaengeGrad.getText().toString(); //EditText zu String
				ZielLaengeGrad = Float.parseFloat(ZielLaengeGradString); //String zu float
				ZielLaengeMinDezString = etZielLaengeMinDez.getText().toString(); //EditText zu String
				ZielLaengeMinDez = Float.parseFloat(ZielLaengeMinDezString); //String zu float
					
				//Teil 2/2: Berechnung ZielBreiteDez und ZielLaengeDez
				ZielBreiteDez = ZielBreiteGrad + (ZielBreiteMinDez / 60); //Berechnung Ziel dezimal
				ZielLaengeDez = ZielLaengeGrad + (ZielLaengeMinDez / 60); //Berechnung Ziel dezimal
				
				if (bNordSuedVar == true){
					ZielBreiteDez = ZielBreiteDez * (-1);
				}
				if (bOstWestVar == true){
					ZielLaengeDez = ZielLaengeDez * (-1);
				}
						
			}// end if (etZielBreiteGrad.length() > 0 && etZielLaengeGrad.length() >
			
						
			//ZielBreiteDez = 50; /////////////////////////Testkoordinaten Ziel/////////////////////////
			//ZielLaengeDez = 11; /////////////////////////Testkoordinaten Ziel/////////////////////////
			
			//float in String konvertieren und in TextView ausgeben
			DecimalFormat df23 = new DecimalFormat("0.00000000"); //Dezimalzahl formatieren
			ZielBreiteDezString = df23.format(ZielBreiteDez); //Dezimalzahl formatieren
			tvZielBreiteDez.setText(String.valueOf(ZielBreiteDezString)); //String zu TextView
			DecimalFormat df24 = new DecimalFormat("0.00000000"); //Dezimalzahl formatieren
			ZielLaengeDezString = df24.format(ZielLaengeDez); //Dezimalzahl formatieren
			tvZielLaengeDez.setText(String.valueOf(ZielLaengeDezString)); //String zu TextView
					
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
						
			//Berechnung Winkel / Richtung
			RichtungRAD = (Math.acos((Math.sin(ZielBreiteDezRAD) - Math.sin(PosBreiteDezRAD) * Math.cos(eWinkel))  / (Math.cos(PosBreiteDezRAD) * Math.sin(eWinkel))));
			RichtungDEG = Math.toDegrees(RichtungRAD);
				
			//Korrektur / Offset L�ngengrade
			if (PosLaengeDezRAD >= ZielLaengeDezRAD) {
				RichtungDEG = RichtungDEG - 360;
			}
			
			//Ausgabe der Ergebnisse von float �ber String zu TextView
			if (Entfernung > 1000){	
				Entfernung = Entfernung / 1000;
				tvEntfernungsEinheit.setText(String.valueOf(" km          "));
				AnsageEntfernungsEinheit = "Kilometer";
				DecimalFormat df2 = new DecimalFormat("0.0"); //Dezimalzahl formatieren
				EntfernungString = df2.format(Entfernung); //Dezimalzahl formatieren
			}
			else{	
				tvEntfernungsEinheit.setText(String.valueOf(" m          "));
				AnsageEntfernungsEinheit = "";
				DecimalFormat df2 = new DecimalFormat("0"); //Dezimalzahl formatieren
				EntfernungString = df2.format(Entfernung); //Dezimalzahl formatieren
			}
			
			tvEntfernung.setText(String.valueOf(EntfernungString)); //String zu TextView
			DecimalFormat df3 = new DecimalFormat("0"); //Dezimalzahl formatieren
			String RichtungDEGString = df3.format(Math.abs(RichtungDEG)); //Dezimalzahl formatieren
			tvRichtungGrad.setText(String.valueOf(RichtungDEGString)); //zu TextView
			
					
			//Drehrichtung �ber Bewegungs-Richtung berechnen und anzeigen (bei 2 bis 7 km/h oder wenn Magnet-Kompass deaktiviert)
			//ACHTUNG: Bei �nderungen an der Bedingung auch folgende "If's" 
			//anpassen: Letzte Bew.Rtg.(Activity oben), Magnetkompass, Service-LetzteBew.Rtg., -Tonfall und  Service Richtungsberechnung(GPS und Magnet)
			if ((cbBewRichtungEin.isChecked() == true && GeschwindigkeitKpH >= 7 && Abweichung <= 60)
				|| (cbBewRichtungEin.isChecked() == true && GeschwindigkeitKpH >= 5 && Abweichung <= 8)
				|| (cbBewRichtungEin.isChecked() == true && GeschwindigkeitKpH >= 2 && Abweichung <= 4)
				|| cbKeinMagnetKompass.isChecked() == true){
				
				////RichtungDrehGrad = Math.abs(RichtungDEG) - Math.abs(BewRichtungDEG); //Berechnung der Drehrichtung in Grad beim Fahren
				RichtungDrehGrad = Math.abs(RichtungDEG) - Math.abs(GpsBearingGrad); //Berechnung der Drehrichtung in Grad beim Fahren
				if (RichtungDrehGrad < 1) { RichtungDrehGrad = RichtungDrehGrad + 360; } //Korrektur f�r Wert unter 0
				if (RichtungDrehGrad > 360) { RichtungDrehGrad = RichtungDrehGrad - 360; } //Korrektur f�r Wert �ber 360
				Richtung = RichtungDrehGrad / 30; //Berechnung der Drehrichtung in Uhr-Wert
				DecimalFormat df1 = new DecimalFormat("0"); //Dezimalzahl formatieren
				RichtungString = df1.format(Richtung); //Dezimalzahl formatieren
				tvRichtung.setText(String.valueOf(RichtungString));
				tvBewRichtungGrad.setTextColor(Color.parseColor("#FFFFFFFF"));
				tvMagKompass.setTextColor(Color.parseColor("#99FFFFFF"));
			}
			
			
			
			
			
			//------------------------------------------------------------------------------------------------------------------------------------------
			//************************* Sprachausgabe Naviansage / Zeitansage bei Koordinaten-Update ***************************************************
			//------------------------------------------------------------------------------------------------------------------------------------------
		
		// ENTF�LLT DA IN SERVICE VERLEGT //
		
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
	


	
	//***************************************************************************
	//   <<<<<<<<<<<<<<<<   Kompass-Sensor-Daten-Update  <<<<<<<<<<<<<<<<<<
	//***************************************************************************
	
	private SensorEventListener mSensorEventListener = new SensorEventListener(){	
		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy){
		}
		
		public void onSensorChanged(SensorEvent event)
		{
			MagKompass = (event.values[0]); // Kompass-Variable in TextView anzeigen
			//MagKompass = BewRichtungDEG;
			DecimalFormat dfKompass = new DecimalFormat("000"); //Dezimalzahl formatieren
			String MagKompassString = dfKompass.format(Math.abs(MagKompass)); //Dezimalzahl formatieren
			tvMagKompass.setText(MagKompassString);
						
			
			
			if ((cbBewRichtungEin.isChecked() == true && GeschwindigkeitKpH >= 7 && Abweichung <= 60)
				|| (cbBewRichtungEin.isChecked() == true && GeschwindigkeitKpH >= 5 && Abweichung <= 8)
				|| (cbBewRichtungEin.isChecked() == true && GeschwindigkeitKpH >= 2 && Abweichung <= 4)
				|| cbKeinMagnetKompass.isChecked() == true){
				//bleibt leer, da "Gegenst�ck" in GPS LocationChange verschoben...
			}
			else{
				//Drehrichtung aus Magnetkompass berechnen und anzeigen 
				RichtungDrehGrad = Math.abs(RichtungDEG) - MagKompass + Korrekturwinkel; //Berechnung der Drehrichtung in Grad
				if (RichtungDrehGrad < 1) { RichtungDrehGrad = RichtungDrehGrad + 360; } //Korrektur f�r Wert unter 0
				if (RichtungDrehGrad > 360) { RichtungDrehGrad = RichtungDrehGrad - 360; } //Korrektur f�r Wert �ber 360
				Richtung = RichtungDrehGrad / 30; //Berechnung der Drehrichtung in Uhr-Wert
				DecimalFormat df1 = new DecimalFormat("0"); //Dezimalzahl formatieren
				RichtungString = df1.format(Richtung); //Dezimalzahl formatieren
				tvRichtung.setText(String.valueOf(RichtungString));
				tvBewRichtungGrad.setTextColor(Color.parseColor("#99FFFFFF"));
				tvMagKompass.setTextColor(Color.parseColor("#FFFFFFFF"));
				
				
			}//end else
		}//end public void onSensorChanged
	};// end private SensorEventListener mSensorEventListener = new SensorEventListener()
	
	
	



	//------------------------------------------------------------------------------------------------------------------------------------------
	//************************* Button-Bet�tigungen / Ausf�hren bei Klick: ***************************************
	//------------------------------------------------------------------------------------------------------------------------------------------
	
	@Override
	public void onClick(View src) {
		
		switch(src.getId()) {
			case R.id.bNordSued: 
			    //Vibration als Bestätigung
			    Vibrator v1 = (Vibrator) getSystemService
				(Context.VIBRATOR_SERVICE);
				v1.vibrate( 60 );
				if (bNordSuedVar != true){
					bNordSued.setText("   S"); 
					bNordSuedVar = true;
					SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
					Editor editor1 = einstellungspeicher.edit();
					editor1.putBoolean("bNordSuedVar", bNordSuedVar);
					editor1.commit();
				}
				else{
					bNordSued.setText("   N"); 
					bNordSuedVar = false;
					SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
					Editor editor1 = einstellungspeicher.edit();
					editor1.putBoolean("bNordSuedVar", bNordSuedVar);
					editor1.commit();
				}
		
			break;
		
			case R.id.bOstWest: 
			    //Vibration als Bestätigung
			    Vibrator v2 = (Vibrator) getSystemService
				(Context.VIBRATOR_SERVICE);
				v2.vibrate( 60 );
				if (bOstWestVar != true){
					bOstWest.setText("' W");
					bOstWestVar = true;
					SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
					Editor editor1 = einstellungspeicher.edit();
					editor1.putBoolean("bOstWestVar", bOstWestVar);
					editor1.commit();
				}
				else{
					bOstWest.setText("' E");
					bOstWestVar = false;
					SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
					Editor editor1 = einstellungspeicher.edit();
					editor1.putBoolean("bOstWestVar", bOstWestVar);
					editor1.commit();
				}
		
			break;
		
		
		
			case R.id.bNaviStarten: //KLICK Navigation starten
				if (NaviAnsageEin != true){
					//tvTextAusgabe.setText(String.valueOf("Navigation gestartet. Warten auf GPS..."));
					NaviAnsageEin = true;
					NaviAnsageZaehler = NaviAnsageIntervall - 4;
					NaviAnsageStartZeit = System.currentTimeMillis() - ((NaviAnsageIntervall - 3) * 1000); //NaviAnsageStartZeit auf "in 3 Sekunden" setzen
					//tts.speak("Navi gestartet. ",TextToSpeech.QUEUE_FLUSH, null);
					bNaviStarten.setText("Navigation beenden");
				
					SharedPreferences variablenspeicher = getPreferences(Context.MODE_PRIVATE);
					Editor editor2 = variablenspeicher.edit();
					editor2.putBoolean("NaviAnsageEin", NaviAnsageEin);
					editor2.commit();
					
				//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
				//     Hintergrund-Service starten bei "Navigation starten"
				//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
				
					////Toast.makeText(getApplicationContext(),  "Hintergrund-Service wird gestartet...*", Toast.LENGTH_SHORT).show();
					
					
					//Intent f�r Service vorbereiten
					Intent meinServiceIntent = new Intent(this, GCBabbleService.class);
					//Diagnose f�r Daten�bertragung per Service-Intent
					meinServiceIntent.putExtra("extradaten1", "Test1");
										
					//Einstellungen per Intent an Service �bertragen
					meinServiceIntent.putExtra("KorrekturwinkelString", KorrekturwinkelString);
		    		meinServiceIntent.putExtra("NaviAusDisplayString", NaviAusDisplayString); 
		    		boolean cbNaviLinksRechtsVar = cbNaviLinksRechts.isChecked();
		    		meinServiceIntent.putExtra("cbNaviLinksRechtsVar", cbNaviLinksRechtsVar); 
		    		boolean cbDatumZeitVar = cbDatumZeit.isChecked();
		    		meinServiceIntent.putExtra("cbDatumZeitVar", cbDatumZeitVar); 
		    		boolean cbBewRichtungEinVar = cbBewRichtungEin.isChecked();
		    		meinServiceIntent.putExtra("cbBewRichtungEinVar", cbBewRichtungEinVar); 
		    		boolean cbParkplatzAutomatischSpeichernVar = cbParkplatzAutomatischSpeichern.isChecked();
	    			meinServiceIntent.putExtra("cbParkplatzAutomatischSpeichernVar", cbParkplatzAutomatischSpeichernVar); 
	    			boolean cbKeinMagnetKompassVar = cbKeinMagnetKompass.isChecked();
	        		meinServiceIntent.putExtra("cbKeinMagnetKompassVar", cbKeinMagnetKompassVar); 
					NaviAnsageIntervallString = tvAnsageIntervall.getText().toString(); //TextView zu String
					meinServiceIntent.putExtra("NaviAnsageIntervallString", NaviAnsageIntervallString);
					meinServiceIntent.putExtra("cbGehFahrHaltErkennungVar", cbGehFahrHaltErkennungVar);
		        	meinServiceIntent.putExtra("cbOptimiertesAnsageIntervallVar", cbOptimiertesAnsageIntervallVar);
			
					
					
					//Ziel-Koordinaten per Intent an Service �bertragen
					
					//Zielkoordinaten in Dezimal umrechnen wenn alle EditText-Felder ausgef�llt sind
					if (etZielBreiteGrad.length() > 0 && etZielLaengeGrad.length() > 0 && etZielBreiteMinDez.length() > 0 && etZielLaengeMinDez.length() > 0){
						//Eingaben aus EditText in float konvertieren
						ZielBreiteGradString = etZielBreiteGrad.getText().toString(); //EditText zu String
						ZielBreiteGrad = Float.parseFloat(ZielBreiteGradString); //String zu float
						ZielBreiteMinDezString = etZielBreiteMinDez.getText().toString(); //EditText zu String
						ZielBreiteMinDez = Float.parseFloat(ZielBreiteMinDezString); //String zu float
						ZielLaengeGradString = etZielLaengeGrad.getText().toString(); //EditText zu String
						ZielLaengeGrad = Float.parseFloat(ZielLaengeGradString); //String zu float
						ZielLaengeMinDezString = etZielLaengeMinDez.getText().toString(); //EditText zu String
						ZielLaengeMinDez = Float.parseFloat(ZielLaengeMinDezString); //String zu float
					
						//Berechnung ZielBreiteDez und ZielLaengeDez
						ZielBreiteDez = ZielBreiteGrad + (ZielBreiteMinDez / 60); //Berechnung Ziel dezimal
						ZielLaengeDez = ZielLaengeGrad + (ZielLaengeMinDez / 60); //Berechnung Ziel dezimal
						
						if (bNordSuedVar == true){
							ZielBreiteDez = ZielBreiteDez * (-1);
						}
						if (bOstWestVar == true){
							ZielLaengeDez = ZielLaengeDez * (-1);
						}
					}
					ZielBreiteDezString = "" + ZielBreiteDez;
					ZielLaengeDezString = "" + ZielLaengeDez;
					meinServiceIntent.putExtra("ZielBreiteDezString", ZielBreiteDezString);
		    		meinServiceIntent.putExtra("ZielLaengeDezString", ZielLaengeDezString); 
					
		    		//Aktuelle Variablen per Intent an Service �bertragen
		    		meinServiceIntent.putExtra("NaviAnsageEin", NaviAnsageEin); 
		    		
		    		//Zusammenstellung "Untertitel" Foreground-Service
					meinServiceIntent.putExtra("IventInfo", 
							"Ziel:" + bNordSued.getText() + " " + ZielBreiteGradString + " Grad " + ZielBreiteMinDezString + 
							bOstWest.getText() + " " + ZielLaengeGradString + " Grad " + ZielLaengeMinDezString + "' ");
					
									
					//Service-Intent starten
					startService(meinServiceIntent);     
						
					
					
					// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
					//Letztes Ziel speichern......
					// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
					if (ZielBreiteGradString.equals(ZielBreiteGradStringMerker) && ZielBreiteMinDezString.equals(ZielBreiteMinDezStringMerker) && 
							ZielLaengeGradString.equals(ZielLaengeGradStringMerker) && ZielLaengeMinDezString.equals(ZielLaengeMinDezStringMerker)){
						////Toast.makeText(getApplicationContext(),  "Kein neues Ziel*", Toast.LENGTH_SHORT).show();
					}//end "Kein neues Ziel*"
					else{//Wenn es ein neues Ziel gibt, dann...
						//...Merker in "Letztes Ziel" schreiben
						etP0Text.setText(etP0TextMerker);
						etP0BreiteGrad.setText(ZielBreiteGradStringMerker);
						etP0BreiteMinDez.setText(ZielBreiteMinDezStringMerker);
						etP0LaengeGrad.setText(ZielLaengeGradStringMerker);
						etP0LaengeMinDez.setText(ZielLaengeMinDezStringMerker);
					//TV-Beschriftung N/S / E/W setzen  
					if (bNordSuedVarMerker == true){
						tvP0NordSued.setText("  S ");
						P0NordSuedVar = true;
						SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
						Editor editor1 = einstellungspeicher.edit();
						editor1.putBoolean("P0NordSuedVar", P0NordSuedVar);
						editor1.commit();		
					}
					else{
						tvP0NordSued.setText("  N ");
						P0NordSuedVar = false;
						SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
						Editor editor1 = einstellungspeicher.edit();
						editor1.putBoolean("P0NordSuedVar", P0NordSuedVar);
						editor1.commit();
					}
					if (bOstWestVarMerker == true){
						tvP0OstWest.setText("' W");
						P0OstWestVar = true;
						SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
						Editor editor1 = einstellungspeicher.edit();
						editor1.putBoolean("P0OstWestVar", P0OstWestVar);
						editor1.commit();			
					}
					else{
						tvP0OstWest.setText("' E");
						P0OstWestVar = false;
						SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
						Editor editor1 = einstellungspeicher.edit();
						editor1.putBoolean("P0OstWestVar", P0OstWestVar);
						editor1.commit();
					}					
						//...aktuelle Zielkoordinaten in Merker speichern
						String Zeitstempel = (String) DateFormat.format("dd.MM.yy / kk:mm", new java.util.Date());
						etP0TextMerker = "Letztes Ziel vom " + Zeitstempel;
						ZielBreiteGradStringMerker = ZielBreiteGradString;
						ZielBreiteMinDezStringMerker = ZielBreiteMinDezString;
						ZielLaengeGradStringMerker = ZielLaengeGradString;
						ZielLaengeMinDezStringMerker = ZielLaengeMinDezString;
						Toast.makeText(getApplicationContext(),  "Letztes Ziel gespeichert.", Toast.LENGTH_SHORT).show();
					//N/S / E/W in Merker schreiben
					bNordSuedVarMerker = bNordSuedVar;
					bOstWestVarMerker = bOstWestVar;
					
					}//end "Neues Ziel*" dann letztes Ziel speichern
					
				
				
				}
				else{    //KLICK Navigation beenden
					//tvTextAusgabe.setText(String.valueOf("Navigation durch Benutzer beendet."));
					NaviAnsageEin = false;
					//tts.speak("Beendet. ",TextToSpeech.QUEUE_FLUSH, null);
					bNaviStarten.setText("Navigation starten");
				
					
					SharedPreferences variablenspeicher = getPreferences(Context.MODE_PRIVATE);
					Editor editor2 = variablenspeicher.edit();
					editor2.putBoolean("NaviAnsageEin", NaviAnsageEin);
					editor2.commit();
					
				//----------------------------------------------------------------
					
				// Hintergrund-Service beenden --------------------------------------
					
				//----------------------------------------------------------------
					
					//Toast.makeText(getApplicationContext(),  "Hintergrund-Service wird beendet...*", Toast.LENGTH_SHORT).show();
					
					
					stopService(new Intent(this, GCBabbleService.class));    
		
				}
				break;
			
			case R.id.bMinus: //AnsageIntervall einstellen
					DoppeltePausenlaenge = false; //Falls Doppelte Ansage aktiviert, wird sie hier beim Einstellen zur�ck gesetzt
					NaviAnsageIntervallString = tvAnsageIntervall.getText().toString(); //TextView zu String
				NaviAnsageIntervall = (int) Float.parseFloat(NaviAnsageIntervallString); //String zu int
				if (NaviAnsageIntervall > 9){
					NaviAnsageIntervallString = tvAnsageIntervall.getText().toString(); //EditText zu String
					NaviAnsageIntervall = (int) Float.parseFloat(NaviAnsageIntervallString); //String zu int
						NaviAnsageIntervall = NaviAnsageIntervall - 5;
					NaviAnsageIntervallSoll = NaviAnsageIntervall;
					}	
				NaviAnsageIntervallString = "" + NaviAnsageIntervall;		
				tvAnsageIntervall.setText(String.valueOf(NaviAnsageIntervallString)); //String zu TextView
					
					// - - - - - - - - - - - - - - - - - - - - - --- --- --- --- -  - - -  - -
					//  Service mit aktuellen Daten f�ttern, falls Navi bei �nderungen l�uft
					// - - - - - - - - - - - - - - - - - - ---- ---- - --  - - - -  - - -  - -
										
							if (NaviAnsageEin == true){
								
								//Intent f�r Service vorbereiten
								Intent meinServiceIntent = new Intent(this, GCBabbleService.class);
								//Diagnose f�r Daten�bertragung per Service-Intent
								//meinServiceIntent.putExtra("extradaten1", "Test1");
													
								//Einstellungen per Intent an Service �bertragen
								meinServiceIntent.putExtra("KorrekturwinkelString", KorrekturwinkelString);
					    		meinServiceIntent.putExtra("NaviAusDisplayString", NaviAusDisplayString); 
					    		cbNaviLinksRechtsVar = cbNaviLinksRechts.isChecked();
					    		meinServiceIntent.putExtra("cbNaviLinksRechtsVar", cbNaviLinksRechtsVar); 
					    		cbDatumZeitVar = cbDatumZeit.isChecked();
					    		meinServiceIntent.putExtra("cbDatumZeitVar", cbDatumZeitVar); 
					    		cbBewRichtungEinVar = cbBewRichtungEin.isChecked();
					    		meinServiceIntent.putExtra("cbBewRichtungEinVar", cbBewRichtungEinVar); 
					    		boolean cbParkplatzAutomatischSpeichernVar = cbParkplatzAutomatischSpeichern.isChecked();
				    			meinServiceIntent.putExtra("cbParkplatzAutomatischSpeichernVar", cbParkplatzAutomatischSpeichernVar); 
				    			boolean cbKeinMagnetKompassVar = cbKeinMagnetKompass.isChecked();
				        		meinServiceIntent.putExtra("cbKeinMagnetKompassVar", cbKeinMagnetKompassVar); 
								NaviAnsageIntervallString = tvAnsageIntervall.getText().toString(); //TextView zu String
								meinServiceIntent.putExtra("NaviAnsageIntervallString", NaviAnsageIntervallString);
								meinServiceIntent.putExtra("cbGehFahrHaltErkennungVar", cbGehFahrHaltErkennungVar);
			                    meinServiceIntent.putExtra("cbOptimiertesAnsageIntervallVar", cbOptimiertesAnsageIntervallVar);
			
													
								//Ziel-Koordinaten per Intent an Service �bertragen
								//Zielkoordinaten in Dezimal umrechnen wenn alle EditText-Felder ausgef�llt sind
								if (etZielBreiteGrad.length() > 0 && etZielLaengeGrad.length() > 0 && etZielBreiteMinDez.length() > 0 && etZielLaengeMinDez.length() > 0){
									//Eingaben aus EditText in float konvertieren
									ZielBreiteGradString = etZielBreiteGrad.getText().toString(); //EditText zu String
									ZielBreiteGrad = Float.parseFloat(ZielBreiteGradString); //String zu float
									ZielBreiteMinDezString = etZielBreiteMinDez.getText().toString(); //EditText zu String
									ZielBreiteMinDez = Float.parseFloat(ZielBreiteMinDezString); //String zu float
									ZielLaengeGradString = etZielLaengeGrad.getText().toString(); //EditText zu String
									ZielLaengeGrad = Float.parseFloat(ZielLaengeGradString); //String zu float
									ZielLaengeMinDezString = etZielLaengeMinDez.getText().toString(); //EditText zu String
									ZielLaengeMinDez = Float.parseFloat(ZielLaengeMinDezString); //String zu float
								
									//Berechnung ZielBreiteDez und ZielLaengeDez
									ZielBreiteDez = ZielBreiteGrad + (ZielBreiteMinDez / 60); //Berechnung Ziel dezimal
									ZielLaengeDez = ZielLaengeGrad + (ZielLaengeMinDez / 60); //Berechnung Ziel dezimal
									
									if (bNordSuedVar == true){
										ZielBreiteDez = ZielBreiteDez * (-1);
									}
									if (bOstWestVar == true){
										ZielLaengeDez = ZielLaengeDez * (-1);
									}
								}
								ZielBreiteDezString = "" + ZielBreiteDez;
								ZielLaengeDezString = "" + ZielLaengeDez;
								meinServiceIntent.putExtra("ZielBreiteDezString", ZielBreiteDezString);
					    		meinServiceIntent.putExtra("ZielLaengeDezString", ZielLaengeDezString); 
								
					    		//Aktuelle Variablen per Intent an Service �bertragen
					    		meinServiceIntent.putExtra("NaviAnsageEin", NaviAnsageEin); 
								
					    		//Zusammenstellung "Untertitel" Foreground-Service
								meinServiceIntent.putExtra("IventInfo", 
										"Ziel:" + bNordSued.getText() + " " + ZielBreiteGradString + " Grad " + ZielBreiteMinDezString + 
										bOstWest.getText() + " " + ZielLaengeGradString + " Grad " + ZielLaengeMinDezString + "' ");
								
								
								//Service-Intent starten
								startService(meinServiceIntent);     
									
					       }//end if (NaviAnsageEin == true)
					
					break;
			
			case R.id.bPlus: //AnsageIntervall einstellen
					DoppeltePausenlaenge = false; //Falls Doppelte Ansage aktiviert, wird sie hier beim Einstellen zur�ck gesetzt
				NaviAnsageIntervallString = tvAnsageIntervall.getText().toString(); //TextView zu String
				NaviAnsageIntervall = (int) Float.parseFloat(NaviAnsageIntervallString); //String zu int
				if (NaviAnsageIntervall < 1000){
					NaviAnsageIntervallString = tvAnsageIntervall.getText().toString(); //EditText zu String
					NaviAnsageIntervall = (int) Float.parseFloat(NaviAnsageIntervallString); //String zu int
					NaviAnsageIntervall = NaviAnsageIntervall + 5;
					NaviAnsageIntervallSoll = NaviAnsageIntervall;
					}			
				NaviAnsageIntervallString = "" + NaviAnsageIntervall;		
				tvAnsageIntervall.setText(String.valueOf(NaviAnsageIntervallString)); //String zu TextView
				

			// - - - - - - - - - - - - - - - - - - - - - --- --- --- --- -  - - -  - -
			//  Service mit aktuellen Daten f�ttern, falls Navi bei �nderungen l�uft
			// - - - - - - - - - - - - - - - - - - ---- ---- - --  - - - -  - - -  - -
								
					if (NaviAnsageEin == true){
						
						//Intent f�r Service vorbereiten
						Intent meinServiceIntent = new Intent(this, GCBabbleService.class);
						//Diagnose f�r Daten�bertragung per Service-Intent
						//meinServiceIntent.putExtra("extradaten1", "Test1");
											
						//Einstellungen per Intent an Service �bertragen
						meinServiceIntent.putExtra("KorrekturwinkelString", KorrekturwinkelString);
			    		meinServiceIntent.putExtra("NaviAusDisplayString", NaviAusDisplayString); 
			    		cbNaviLinksRechtsVar = cbNaviLinksRechts.isChecked();
			    		meinServiceIntent.putExtra("cbNaviLinksRechtsVar", cbNaviLinksRechtsVar); 
			    		cbDatumZeitVar = cbDatumZeit.isChecked();
			    		meinServiceIntent.putExtra("cbDatumZeitVar", cbDatumZeitVar); 
			    		cbBewRichtungEinVar = cbBewRichtungEin.isChecked();
			    		meinServiceIntent.putExtra("cbBewRichtungEinVar", cbBewRichtungEinVar); 
			    		boolean cbParkplatzAutomatischSpeichernVar = cbParkplatzAutomatischSpeichern.isChecked();
		    			meinServiceIntent.putExtra("cbParkplatzAutomatischSpeichernVar", cbParkplatzAutomatischSpeichernVar); 
		    			boolean cbKeinMagnetKompassVar = cbKeinMagnetKompass.isChecked();
		        		meinServiceIntent.putExtra("cbKeinMagnetKompassVar", cbKeinMagnetKompassVar); 
						NaviAnsageIntervallString = tvAnsageIntervall.getText().toString(); //TextView zu String
						meinServiceIntent.putExtra("NaviAnsageIntervallString", NaviAnsageIntervallString);
						meinServiceIntent.putExtra("cbGehFahrHaltErkennungVar", cbGehFahrHaltErkennungVar);
			            meinServiceIntent.putExtra("cbOptimiertesAnsageIntervallVar", cbOptimiertesAnsageIntervallVar);
			
							
						//Ziel-Koordinaten per Intent an Service �bertragen
						//Zielkoordinaten in Dezimal umrechnen wenn alle EditText-Felder ausgef�llt sind
						if (etZielBreiteGrad.length() > 0 && etZielLaengeGrad.length() > 0 && etZielBreiteMinDez.length() > 0 && etZielLaengeMinDez.length() > 0){
							//Eingaben aus EditText in float konvertieren
							ZielBreiteGradString = etZielBreiteGrad.getText().toString(); //EditText zu String
							ZielBreiteGrad = Float.parseFloat(ZielBreiteGradString); //String zu float
							ZielBreiteMinDezString = etZielBreiteMinDez.getText().toString(); //EditText zu String
							ZielBreiteMinDez = Float.parseFloat(ZielBreiteMinDezString); //String zu float
							ZielLaengeGradString = etZielLaengeGrad.getText().toString(); //EditText zu String
							ZielLaengeGrad = Float.parseFloat(ZielLaengeGradString); //String zu float
							ZielLaengeMinDezString = etZielLaengeMinDez.getText().toString(); //EditText zu String
							ZielLaengeMinDez = Float.parseFloat(ZielLaengeMinDezString); //String zu float
						
							//Berechnung ZielBreiteDez und ZielLaengeDez
							ZielBreiteDez = ZielBreiteGrad + (ZielBreiteMinDez / 60); //Berechnung Ziel dezimal
							ZielLaengeDez = ZielLaengeGrad + (ZielLaengeMinDez / 60); //Berechnung Ziel dezimal
							
							if (bNordSuedVar == true){
								ZielBreiteDez = ZielBreiteDez * (-1);
							}
							if (bOstWestVar == true){
								ZielLaengeDez = ZielLaengeDez * (-1);
							}
						}
						ZielBreiteDezString = "" + ZielBreiteDez;
						ZielLaengeDezString = "" + ZielLaengeDez;
						meinServiceIntent.putExtra("ZielBreiteDezString", ZielBreiteDezString);
			    		meinServiceIntent.putExtra("ZielLaengeDezString", ZielLaengeDezString); 
						
			    		//Aktuelle Variablen per Intent an Service �bertragen
			    		meinServiceIntent.putExtra("NaviAnsageEin", NaviAnsageEin); 
						
			    		//Zusammenstellung "Untertitel" Foreground-Service
						meinServiceIntent.putExtra("IventInfo", 
								"Ziel:" + bNordSued.getText() + " " + ZielBreiteGradString + " Grad " + ZielBreiteMinDezString + 
								bOstWest.getText() + " " + ZielLaengeGradString + " Grad " + ZielLaengeMinDezString + "' ");
						
						
						//Service-Intent starten
						startService(meinServiceIntent);     
							
			       }//end if (NaviAnsageEin == true)
					
					
					
					break;
				
				case R.id.bKwMinus: //Korrekturwinkel einstellen
					KorrekturwinkelString = tvKorrekturwinkel.getText().toString(); 
					Korrekturwinkel = (double) Float.parseFloat(KorrekturwinkelString); //String zu double
					if (Korrekturwinkel > -180){
						KorrekturwinkelString = tvKorrekturwinkel.getText().toString(); 
						Korrekturwinkel = (double) Float.parseFloat(KorrekturwinkelString); 
						Korrekturwinkel = Korrekturwinkel - 30;
					}	
					KorrekturwinkelString = "" + Korrekturwinkel;	
					DecimalFormat df21 = new DecimalFormat("0"); //Dezimalzahl formatieren
					KorrekturwinkelString = df21.format(Korrekturwinkel); //Dezimalzahl formatieren
					tvKorrekturwinkel.setText(String.valueOf(KorrekturwinkelString)); //String zu TextView
					
					//Hilfe-Texte einblenden
					if (Korrekturwinkel == -180 || Korrekturwinkel == 180){Toast.makeText(getApplicationContext(), "Display oder untere Seite zeigt in Marschrichtung.", Toast.LENGTH_LONG).show();}
					if (Korrekturwinkel == -90){Toast.makeText(getApplicationContext(), "Rechte Seite zeigt in Marschrichtung.", Toast.LENGTH_LONG).show();}
					if (Korrekturwinkel == 0){Toast.makeText(getApplicationContext(), "Hintere oder obere Seite zeigt in Marschrichtung.", Toast.LENGTH_LONG).show();}
					if (Korrekturwinkel == 90){Toast.makeText(getApplicationContext(), "Linke Seite zeigt in Marschrichtung.", Toast.LENGTH_LONG).show();}
				break;
				
				case R.id.bKwPlus: 	//Korrekturwinkel einstellen
					KorrekturwinkelString = tvKorrekturwinkel.getText().toString(); 
					Korrekturwinkel = (double) Float.parseFloat(KorrekturwinkelString); //String zu double
					if (Korrekturwinkel < 180){
						KorrekturwinkelString = tvKorrekturwinkel.getText().toString(); 
						Korrekturwinkel = (double) Float.parseFloat(KorrekturwinkelString); 
						Korrekturwinkel = Korrekturwinkel + 30;
					}	
					KorrekturwinkelString = "" + Korrekturwinkel;		
					DecimalFormat df22 = new DecimalFormat("0"); //Dezimalzahl formatieren
					KorrekturwinkelString = df22.format(Korrekturwinkel); //Dezimalzahl formatieren
					tvKorrekturwinkel.setText(String.valueOf(KorrekturwinkelString)); //String zu TextView
					
					//Hilfe-Texte einblenden
					if (Korrekturwinkel == -180 || Korrekturwinkel == 180){Toast.makeText(getApplicationContext(), "Display oder untere Seite zeigt in Marschrichtung.", Toast.LENGTH_LONG).show();}
					if (Korrekturwinkel == -90){Toast.makeText(getApplicationContext(), "Rechte Seite zeigt in Marschrichtung.", Toast.LENGTH_LONG).show();}
					if (Korrekturwinkel == 0){Toast.makeText(getApplicationContext(), "Hintere oder obere Seite zeigt in Marschrichtung.", Toast.LENGTH_LONG).show();}
					if (Korrekturwinkel == 90){Toast.makeText(getApplicationContext(), "Linke Seite zeigt in Marschrichtung.", Toast.LENGTH_LONG).show();}
				break;
				
				case R.id.bNaviAusMinus: //Anzahl der GPS-Updates bis Navi aus durch Displaylage einstellen
					NaviAusDisplayString = tvNaviAusDisplay.getText().toString(); 
					NaviAusDisplay = (int) Float.parseFloat(NaviAusDisplayString); //String zu int
					if (NaviAusDisplay > 9){
						NaviAusDisplayString = tvNaviAusDisplay.getText().toString(); 
						NaviAusDisplay = (int) Float.parseFloat(NaviAusDisplayString); //String zu int
						NaviAusDisplay = NaviAusDisplay - 5;
					}	
					NaviAusDisplayString = "" + NaviAusDisplay;		
					tvNaviAusDisplay.setText(String.valueOf(NaviAusDisplayString)); //String zu TextView
				break;
				
				case R.id.bNaviAusPlus: //Anzahl der GPS-Updates bis Navi aus durch Displaylage einstellen
					NaviAusDisplayString = tvNaviAusDisplay.getText().toString(); 
					NaviAusDisplay = (int) Float.parseFloat(NaviAusDisplayString); //String zu int
					if (NaviAusDisplay < 1000){
						NaviAusDisplayString = tvNaviAusDisplay.getText().toString(); 
						NaviAusDisplay = (int) Float.parseFloat(NaviAusDisplayString); //String zu int
						NaviAusDisplay = NaviAusDisplay + 5;
					}	
					NaviAusDisplayString = "" + NaviAusDisplay;		
					tvNaviAusDisplay.setText(String.valueOf(NaviAusDisplayString)); //String zu TextView
				break;
				
				case R.id.bAufKarteZeigen: //Ziel auf externer Karte zeigen
					startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("geo:" + ZielBreiteDez + "," + ZielLaengeDez + "?z=14")));
				break;
				
				case R.id.bStrassenNavigation: //Ziel in Navi �bertragen
					startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q=" + ZielBreiteDez + "," + ZielLaengeDez)));
				break;
				
				case R.id.bKoordinatenSpeichernLaden: //Ziel in Navi �bertragen
					if (loKoordinatenSpeichernLaden.getVisibility() == View.VISIBLE){ //pr�fen ob Layout sichtbar ist
						loKoordinatenSpeichernLaden.setVisibility(View.GONE); //Layout aus/ein-blenden
						bKoordinatenSpeichernLaden.setText("> Koordinaten speichern / laden...");
					}//end if visible
					else { //wenn Layout nicht sichtbar
						loKoordinatenSpeichernLaden.setVisibility(View.VISIBLE); //Layout aus/ein-blenden
						bKoordinatenSpeichernLaden.setText("< Koordinaten speichern / laden:");
					}//end else
				break;
				
				case R.id.bErweiterteAnzeige: 
					if (loErweiterteAnzeige.getVisibility() == View.VISIBLE){ //pr�fen ob Layout sichtbar ist
						loErweiterteAnzeige.setVisibility(View.GONE); //Layout aus/ein-blenden
						bErweiterteAnzeige.setText("> Erweiterte Anzeige...");
					}//end if visible
					else { //wenn Layout nicht sichtbar
						loErweiterteAnzeige.setVisibility(View.VISIBLE); //Layout aus/ein-blenden
						bErweiterteAnzeige.setText("< Erweiterte Anzeige:");
					
						//Parkplatzkoordinaten beim Aufklappen �bernehmen aus PREFERENCES //
						
						//SharedPreferences koordinatenspeicher = getPreferences(Context.MODE_WORLD_WRITEABLE);
						SharedPreferences koordinatenspeicher = PreferenceManager.getDefaultSharedPreferences(this);
						ParkplatzBreiteDezString = koordinatenspeicher.getString("ParkplatzBreiteDezString", "0.00000000");
						ParkplatzBreiteDezString = ParkplatzBreiteDezString.replace(",","."); //Komma durch Punkt ersetzen
						ParkplatzBreiteDez = (double) Double.parseDouble(ParkplatzBreiteDezString); //String zu double - Variable
						//Toast.makeText(getApplicationContext(), ParkplatzBreiteDezString, Toast.LENGTH_LONG).show();
						ParkplatzLaengeDezString = koordinatenspeicher.getString("ParkplatzLaengeDezString", "0.00000000");
						ParkplatzLaengeDezString = ParkplatzLaengeDezString.replace(",","."); //Komma durch Punkt ersetzen
						ParkplatzLaengeDez = (double) Double.parseDouble(ParkplatzLaengeDezString); //String zu double - Variable
						//Toast.makeText(getApplicationContext(), ParkplatzLaengeDezString, Toast.LENGTH_LONG).show();
						//Parkplatzkoordinaten-TextView aktualisieren
						DecimalFormat df23 = new DecimalFormat("0.00000000"); //Dezimalzahl formatieren
						ParkplatzBreiteDezString = df23.format(ParkplatzBreiteDez); //Dezimalzahl formatieren
						ParkplatzBreiteDezString = ParkplatzBreiteDezString.replace(",","."); //Komma durch Punkt ersetzen
						tvParkplatzBreiteDez.setText(String.valueOf(ParkplatzBreiteDezString)); //String zu TextView
						DecimalFormat df24 = new DecimalFormat("0.00000000"); //Dezimalzahl formatieren
						ParkplatzLaengeDezString = df24.format(ParkplatzLaengeDez); //Dezimalzahl formatieren
						ParkplatzLaengeDezString = ParkplatzLaengeDezString.replace(",","."); //Komma durch Punkt ersetzen
						tvParkplatzLaengeDez.setText(String.valueOf(ParkplatzLaengeDezString)); //String zu TextView
						
						//Festlegung der L�ngen-/Breitengrade der Parkplatzkoordinaten
						if (ParkplatzBreiteDez < 0){
							tvParkplatzNordSued.setText("S"); 
							ParkplatzNordSuedVar = true;
						}
						else{
							tvParkplatzNordSued.setText("N"); 
							ParkplatzNordSuedVar = false;
						}
				
						if (ZielLaengeDez < 0){
							tvParkplatzOstWest.setText("W");
							ParkplatzOstWestVar = true;
						}
						else{
							tvParkplatzOstWest.setText("E");
							ParkplatzOstWestVar = false;
						}
						
					}//end else
				break;
				
				case R.id.bKartenUndNavigation: 
					if (loKartenUndNavigation.getVisibility() == View.VISIBLE){ //pr�fen ob Layout sichtbar ist
						loKartenUndNavigation.setVisibility(View.GONE); //Layout aus/ein-blenden
						bKartenUndNavigation.setText("> Karten und Navigation...");
					}//end if visible
					else { //wenn Layout nicht sichtbar
						loKartenUndNavigation.setVisibility(View.VISIBLE); //Layout aus/ein-blenden
						bKartenUndNavigation.setText("< Karten und Navigation:");
					}//end else
				break;

				case R.id.bNotizenUndSonstiges:
					if (loNotizenUndSonstiges.getVisibility() == View.VISIBLE){ //pr�fen ob Layout sichtbar ist
						loNotizenUndSonstiges.setVisibility(View.GONE); //Layout aus/ein-blenden
						bNotizenUndSonstiges.setText("> Notizen und Sonstiges...");
					}//end if visible
					else { //wenn Layout nicht sichtbar
						loNotizenUndSonstiges.setVisibility(View.VISIBLE); //Layout aus/ein-blenden
						bNotizenUndSonstiges.setText("< Notizen und Sonstiges:");
					}//end else
				break;
				//*************************
				//Buttons f�r Ziel-Speicher
				//*************************
				case R.id.bAlsZielP0: 
				    //Vibration als Bestätigung
			        Vibrator vZP0 = (Vibrator) getSystemService
			    	(Context.VIBRATOR_SERVICE);
				    vZP0.vibrate( 60 );
					ZielBreiteGradString = (etP0BreiteGrad.getText().toString());
					ZielBreiteMinDezString = (etP0BreiteMinDez.getText().toString());
					ZielLaengeGradString = (etP0LaengeGrad.getText().toString());
					ZielLaengeMinDezString = (etP0LaengeMinDez.getText().toString());
					etZielBreiteGrad.setText(ZielBreiteGradString);
					etZielBreiteMinDez.setText(ZielBreiteMinDezString);
					etZielLaengeGrad.setText(ZielLaengeGradString);
					etZielLaengeMinDez.setText(ZielLaengeMinDezString);	
					
					//Buttonbeschriftung für Ziel N/S / E/W setzen  
					if (P0NordSuedVar == true){
						bNordSued.setText("   S");
						bNordSuedVar = true;
						SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
						Editor editor1 = einstellungspeicher.edit();
						editor1.putBoolean("bNordSuedVar", bNordSuedVar);
						editor1.commit();		
					}
					else{
						bNordSued.setText("   N");
						bNordSuedVar = false;
						SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
						Editor editor1 = einstellungspeicher.edit();
						editor1.putBoolean("bNordSuedVar", bNordSuedVar);
						editor1.commit();
					}
					if (P0OstWestVar == true){
						bOstWest.setText("' W");
						bOstWestVar = true;
						SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
						Editor editor1 = einstellungspeicher.edit();
						editor1.putBoolean("bOstWestVar", bOstWestVar);
						editor1.commit();			
					}
					else{
						bOstWest.setText("' E");
						bOstWestVar = false;
						SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
						Editor editor1 = einstellungspeicher.edit();
						editor1.putBoolean("bOstWestVar", bOstWestVar);
						editor1.commit();
					}	
					//ggf. laufende Navigation beenden
					NaviAnsageEin = false;
					bNaviStarten.setText("Navigation starten");
					stopService(new Intent(this, GCBabbleService.class));    
					Toast.makeText(this, "Als Ziel gesetzt.", Toast.LENGTH_LONG).show();
				break;
				case R.id.bPosMerkenP1: 
				    //Vibration als Bestätigung
			        Vibrator vPMP1 = (Vibrator) getSystemService
			    	(Context.VIBRATOR_SERVICE);
				    vPMP1.vibrate( 40 );
					PosMerkenP = 1;
					PositionMerken = true; 
					Toast.makeText(this, "Bitte warten...", Toast.LENGTH_SHORT).show();
				break;
				case R.id.bZielMerkenP1: 
				    //Vibration als Bestätigung
			        Vibrator vZMP1 = (Vibrator) getSystemService
			    	(Context.VIBRATOR_SERVICE);
				    vZMP1.vibrate( 40 );
					ZielBreiteGradString = (etZielBreiteGrad.getText().toString());
					ZielBreiteMinDezString = (etZielBreiteMinDez.getText().toString());
					ZielLaengeGradString = (etZielLaengeGrad.getText().toString());
					ZielLaengeMinDezString = (etZielLaengeMinDez.getText().toString());
					etP1BreiteGrad.setText(ZielBreiteGradString);
					etP1BreiteMinDez.setText(ZielBreiteMinDezString);
					etP1LaengeGrad.setText(ZielLaengeGradString);
					etP1LaengeMinDez.setText(ZielLaengeMinDezString);	
					String Zeitstempel1 = (String) DateFormat.format("dd.MM.yy / kk:mm", new java.util.Date());
					etP1Text.setText("Ziel vom " + Zeitstempel1);

					//TV-Beschriftung N/S / E/W setzen  
					if (bNordSuedVar == true){
						tvP1NordSued.setText("  S ");
						P1NordSuedVar = true;
						SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
						Editor editor1 = einstellungspeicher.edit();
						editor1.putBoolean("P1NordSuedVar", P1NordSuedVar);
						editor1.commit();		
					}
					else{
						tvP1NordSued.setText("  N ");
						P1NordSuedVar = false;
						SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
						Editor editor1 = einstellungspeicher.edit();
						editor1.putBoolean("P1NordSuedVar", P1NordSuedVar);
						editor1.commit();
					}
					if (bOstWestVar == true){
						tvP1OstWest.setText("' W");
						P1OstWestVar = true;
						SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
						Editor editor1 = einstellungspeicher.edit();
						editor1.putBoolean("P1OstWestVar", P1OstWestVar);
						editor1.commit();			
					}
					else{
						tvP1OstWest.setText("' E");
						P1OstWestVar = false;
						SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
						Editor editor1 = einstellungspeicher.edit();
						editor1.putBoolean("P1OstWestVar", P1OstWestVar);
						editor1.commit();
					}					
					Toast.makeText(this, "Aktuelles Ziel gespeichert.", Toast.LENGTH_SHORT).show();
				break;
				case R.id.bAlsZielP1: 
				    //Vibration als Bestätigung
			        Vibrator vZP1 = (Vibrator) getSystemService
			     	(Context.VIBRATOR_SERVICE);
			    	vZP1.vibrate( 60 );
					ZielBreiteGradString = (etP1BreiteGrad.getText().toString());
					ZielBreiteMinDezString = (etP1BreiteMinDez.getText().toString());
					ZielLaengeGradString = (etP1LaengeGrad.getText().toString());
					ZielLaengeMinDezString = (etP1LaengeMinDez.getText().toString());
					etZielBreiteGrad.setText(ZielBreiteGradString);
					etZielBreiteMinDez.setText(ZielBreiteMinDezString);
					etZielLaengeGrad.setText(ZielLaengeGradString);
					etZielLaengeMinDez.setText(ZielLaengeMinDezString);	

					//Buttonbeschriftung für Ziel N/S / E/W setzen  
					if (P1NordSuedVar == true){
						bNordSued.setText("   S");
						bNordSuedVar = true;
						SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
						Editor editor1 = einstellungspeicher.edit();
						editor1.putBoolean("bNordSuedVar", bNordSuedVar);
						editor1.commit();		
					}
					else{
						bNordSued.setText("   N");
						bNordSuedVar = false;
						SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
						Editor editor1 = einstellungspeicher.edit();
						editor1.putBoolean("bNordSuedVar", bNordSuedVar);
						editor1.commit();
					}
					if (P1OstWestVar == true){
						bOstWest.setText("' W");
						bOstWestVar = true;
						SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
						Editor editor1 = einstellungspeicher.edit();
						editor1.putBoolean("bOstWestVar", bOstWestVar);
						editor1.commit();			
					}
					else{
						bOstWest.setText("' E");
						bOstWestVar = false;
						SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
						Editor editor1 = einstellungspeicher.edit();
						editor1.putBoolean("bOstWestVar", bOstWestVar);
						editor1.commit();
					}	
					//ggf. laufende Navigation beenden
					NaviAnsageEin = false;
					bNaviStarten.setText("Navigation starten");
					stopService(new Intent(this, GCBabbleService.class));  
					Toast.makeText(this, "Als Ziel gesetzt.", Toast.LENGTH_LONG).show();
				break;
				case R.id.bPosMerkenP2: 
				    //Vibration als Bestätigung
			        Vibrator vPMP2 = (Vibrator) getSystemService
			    	(Context.VIBRATOR_SERVICE);
				    vPMP2.vibrate( 40 );
					PosMerkenP = 2;
					PositionMerken = true; 
					Toast.makeText(this, "Bitte warten...", Toast.LENGTH_SHORT).show();
				break;
				case R.id.bZielMerkenP2: 
				    //Vibration als Bestätigung
			        Vibrator vZMP2 = (Vibrator) getSystemService
			    	(Context.VIBRATOR_SERVICE);
				    vZMP2.vibrate( 40 );
					ZielBreiteGradString = (etZielBreiteGrad.getText().toString());
					ZielBreiteMinDezString = (etZielBreiteMinDez.getText().toString());
					ZielLaengeGradString = (etZielLaengeGrad.getText().toString());
					ZielLaengeMinDezString = (etZielLaengeMinDez.getText().toString());
					etP2BreiteGrad.setText(ZielBreiteGradString);
					etP2BreiteMinDez.setText(ZielBreiteMinDezString);
					etP2LaengeGrad.setText(ZielLaengeGradString);
					etP2LaengeMinDez.setText(ZielLaengeMinDezString);	
					String Zeitstempel2 = (String) DateFormat.format("dd.MM.yy / kk:mm", new java.util.Date());
					etP2Text.setText("Ziel vom " + Zeitstempel2);

				//TV-Beschriftung N/S / E/W setzen  
				if (bNordSuedVar == true){
					tvP2NordSued.setText("   S");
					P2NordSuedVar = true;
					SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
					Editor editor1 = einstellungspeicher.edit();
					editor1.putBoolean("P2NordSuedVar", P2NordSuedVar);
					editor1.commit();		
				}
				else{
					tvP2NordSued.setText("   N");
					P2NordSuedVar = false;
					SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
					Editor editor1 = einstellungspeicher.edit();
					editor1.putBoolean("P2NordSuedVar", P2NordSuedVar);
					editor1.commit();
				}
				if (bOstWestVar == true){
					tvP2OstWest.setText("' W");
					P2OstWestVar = true;
					SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
					Editor editor1 = einstellungspeicher.edit();
					editor1.putBoolean("P2OstWestVar", P2OstWestVar);
					editor1.commit();			
				}
				else{
					tvP2OstWest.setText("' E");
					P2OstWestVar = false;
					SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
					Editor editor1 = einstellungspeicher.edit();
					editor1.putBoolean("P2OstWestVar", P2OstWestVar);
					editor1.commit();
				}	
					Toast.makeText(this, "Aktuelles Ziel gespeichert.", Toast.LENGTH_SHORT).show();
				break;
				case R.id.bAlsZielP2: 
					//Vibration als Bestätigung
			        Vibrator vZP2 = (Vibrator) getSystemService
			    	(Context.VIBRATOR_SERVICE);
			    	vZP2.vibrate( 60 );
					ZielBreiteGradString = (etP2BreiteGrad.getText().toString());
					ZielBreiteMinDezString = (etP2BreiteMinDez.getText().toString());
					ZielLaengeGradString = (etP2LaengeGrad.getText().toString());
					ZielLaengeMinDezString = (etP2LaengeMinDez.getText().toString());
					etZielBreiteGrad.setText(ZielBreiteGradString);
					etZielBreiteMinDez.setText(ZielBreiteMinDezString);
					etZielLaengeGrad.setText(ZielLaengeGradString);
					etZielLaengeMinDez.setText(ZielLaengeMinDezString);	
					
					//Buttonbeschriftung für Ziel N/S / E/W setzen  
					if (P2NordSuedVar == true){
						bNordSued.setText("   S");
						bNordSuedVar = true;
						SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
						Editor editor1 = einstellungspeicher.edit();
						editor1.putBoolean("bNordSuedVar", bNordSuedVar);
						editor1.commit();		
					}
					else{
						bNordSued.setText("   N");
						bNordSuedVar = false;
						SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
						Editor editor1 = einstellungspeicher.edit();
						editor1.putBoolean("bNordSuedVar", bNordSuedVar);
						editor1.commit();
					}
					if (P2OstWestVar == true){
						bOstWest.setText("' W");
						bOstWestVar = true;
						SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
						Editor editor1 = einstellungspeicher.edit();
						editor1.putBoolean("bOstWestVar", bOstWestVar);
						editor1.commit();			
					}
					else{
						bOstWest.setText("' E");
						bOstWestVar = false;
						SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
						Editor editor1 = einstellungspeicher.edit();
						editor1.putBoolean("bOstWestVar", bOstWestVar);
						editor1.commit();
					}	
				    //ggf. laufende Navigation beenden
			    	NaviAnsageEin = false;
		    		bNaviStarten.setText("Navigation starten");
		    		stopService(new Intent(this, GCBabbleService.class));  
					Toast.makeText(this, "Als Ziel gesetzt.", Toast.LENGTH_LONG).show();
				break;
				case R.id.bPosMerkenP3: 
				    //Vibration als Bestätigung
			        Vibrator vPMP3 = (Vibrator) getSystemService
			    	(Context.VIBRATOR_SERVICE);
				    vPMP3.vibrate( 40 );
					PosMerkenP = 3;
					PositionMerken = true; 
					Toast.makeText(this, "Bitte warten...", Toast.LENGTH_SHORT).show();
				break;
				case R.id.bZielMerkenP3: 
				    //Vibration als Bestätigung
			        Vibrator vZMP3 = (Vibrator) getSystemService
			    	(Context.VIBRATOR_SERVICE);
				    vZMP3.vibrate( 40 );
					ZielBreiteGradString = (etZielBreiteGrad.getText().toString());
					ZielBreiteMinDezString = (etZielBreiteMinDez.getText().toString());
					ZielLaengeGradString = (etZielLaengeGrad.getText().toString());
					ZielLaengeMinDezString = (etZielLaengeMinDez.getText().toString());
					etP3BreiteGrad.setText(ZielBreiteGradString);
					etP3BreiteMinDez.setText(ZielBreiteMinDezString);
					etP3LaengeGrad.setText(ZielLaengeGradString);
					etP3LaengeMinDez.setText(ZielLaengeMinDezString);	
					String Zeitstempel3 = (String) DateFormat.format("dd.MM.yy / kk:mm", new java.util.Date());
					etP3Text.setText("Ziel vom " + Zeitstempel3);

				//TV-Beschriftung N/S / E/W setzen  
				if (bNordSuedVar == true){
					tvP3NordSued.setText("  S");
					P3NordSuedVar = true;
					SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
					Editor editor1 = einstellungspeicher.edit();
					editor1.putBoolean("P3NordSuedVar", P3NordSuedVar);
					editor1.commit();		
				}
				else{
					tvP3NordSued.setText("  N");
					P3NordSuedVar = false;
					SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
					Editor editor1 = einstellungspeicher.edit();
					editor1.putBoolean("P3NordSuedVar", P3NordSuedVar);
					editor1.commit();
				}
				if (bOstWestVar == true){
					tvP3OstWest.setText("' W");
					P3OstWestVar = true;
					SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
					Editor editor1 = einstellungspeicher.edit();
					editor1.putBoolean("P3OstWestVar", P3OstWestVar);
					editor1.commit();			
				}
				else{
					tvP3OstWest.setText("' E");
					P3OstWestVar = false;
					SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
					Editor editor1 = einstellungspeicher.edit();
					editor1.putBoolean("P3OstWestVar", P3OstWestVar);
					editor1.commit();
				}	
					Toast.makeText(this, "Aktuelles Ziel gespeichert.", Toast.LENGTH_SHORT).show();
				break;
				case R.id.bAlsZielP3: 
				    //Vibration als Bestätigung
			        Vibrator vZP3 = (Vibrator) getSystemService
			    	(Context.VIBRATOR_SERVICE);
			    	vZP3.vibrate( 60 );
					ZielBreiteGradString = (etP3BreiteGrad.getText().toString());
					ZielBreiteMinDezString = (etP3BreiteMinDez.getText().toString());
					ZielLaengeGradString = (etP3LaengeGrad.getText().toString());
					ZielLaengeMinDezString = (etP3LaengeMinDez.getText().toString());
					etZielBreiteGrad.setText(ZielBreiteGradString);
					etZielBreiteMinDez.setText(ZielBreiteMinDezString);
					etZielLaengeGrad.setText(ZielLaengeGradString);
					etZielLaengeMinDez.setText(ZielLaengeMinDezString);	
					
				//Buttonbeschriftung für Ziel N/S / E/W setzen  
				if (P3NordSuedVar == true){
					bNordSued.setText("   S");
					bNordSuedVar = true;
					SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
					Editor editor1 = einstellungspeicher.edit();
					editor1.putBoolean("bNordSuedVar", bNordSuedVar);
					editor1.commit();		
				}
				else{
					bNordSued.setText("   N");
					bNordSuedVar = false;
					SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
					Editor editor1 = einstellungspeicher.edit();
					editor1.putBoolean("bNordSuedVar", bNordSuedVar);
					editor1.commit();
				}
				if (P3OstWestVar == true){
					bOstWest.setText("' W");
					bOstWestVar = true;
					SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
					Editor editor1 = einstellungspeicher.edit();
					editor1.putBoolean("bOstWestVar", bOstWestVar);
					editor1.commit();			
				}
				else{
					bOstWest.setText("' E");
					bOstWestVar = false;
					SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
					Editor editor1 = einstellungspeicher.edit();
					editor1.putBoolean("bOstWestVar", bOstWestVar);
					editor1.commit();
				}	
				//ggf. laufende Navigation beenden
			   	NaviAnsageEin = false;
		   		bNaviStarten.setText("Navigation starten");
		   		stopService(new Intent(this, GCBabbleService.class));  
				Toast.makeText(this, "Als Ziel gesetzt.", Toast.LENGTH_LONG).show();
				break;
				case R.id.bPosMerkenP4: 
				    //Vibration als Bestätigung
			        Vibrator vPMP4 = (Vibrator) getSystemService
			    	(Context.VIBRATOR_SERVICE);
				    vPMP4.vibrate( 40 );
					PosMerkenP = 4;
					PositionMerken = true; 
					Toast.makeText(this, "Bitte warten...", Toast.LENGTH_SHORT).show();
				break;
				case R.id.bZielMerkenP4: 
				    //Vibration als Bestätigung
			        Vibrator vZMP4 = (Vibrator) getSystemService
			    	(Context.VIBRATOR_SERVICE);
				    vZMP4.vibrate( 40 );
					ZielBreiteGradString = (etZielBreiteGrad.getText().toString());
					ZielBreiteMinDezString = (etZielBreiteMinDez.getText().toString());
					ZielLaengeGradString = (etZielLaengeGrad.getText().toString());
					ZielLaengeMinDezString = (etZielLaengeMinDez.getText().toString());
					etP4BreiteGrad.setText(ZielBreiteGradString);
					etP4BreiteMinDez.setText(ZielBreiteMinDezString);
					etP4LaengeGrad.setText(ZielLaengeGradString);
					etP4LaengeMinDez.setText(ZielLaengeMinDezString);	
					String Zeitstempel4 = (String) DateFormat.format("dd.MM.yy / kk:mm", new java.util.Date());
					etP4Text.setText("Ziel vom " + Zeitstempel4);

				//TV-Beschriftung N/S / E/W setzen  
				if (bNordSuedVar == true){
					tvP4NordSued.setText("   S");
					P4NordSuedVar = true;
					SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
					Editor editor1 = einstellungspeicher.edit();
					editor1.putBoolean("P4NordSuedVar", P4NordSuedVar);
					editor1.commit();		
				}
				else{
					tvP4NordSued.setText("   N");
					P4NordSuedVar = false;
					SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
					Editor editor1 = einstellungspeicher.edit();
					editor1.putBoolean("P4NordSuedVar", P4NordSuedVar);
					editor1.commit();
				}
				if (bOstWestVar == true){
					tvP4OstWest.setText("' W");
					P4OstWestVar = true;
					SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
					Editor editor1 = einstellungspeicher.edit();
					editor1.putBoolean("P4OstWestVar", P4OstWestVar);
					editor1.commit();			
				}
				else{
					tvP4OstWest.setText("' E");
					P4OstWestVar = false;
					SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
					Editor editor1 = einstellungspeicher.edit();
					editor1.putBoolean("P4OstWestVar", P4OstWestVar);
					editor1.commit();
				}	
					Toast.makeText(this, "Aktuelles Ziel gespeichert.", Toast.LENGTH_SHORT).show();
				break;
				case R.id.bAlsZielP4: 
				    //Vibration als Bestätigung
			        Vibrator vZP4 = (Vibrator) getSystemService
			    	(Context.VIBRATOR_SERVICE);
			    	vZP4.vibrate( 60 );
					ZielBreiteGradString = (etP4BreiteGrad.getText().toString());
					ZielBreiteMinDezString = (etP4BreiteMinDez.getText().toString());
					ZielLaengeGradString = (etP4LaengeGrad.getText().toString());
					ZielLaengeMinDezString = (etP4LaengeMinDez.getText().toString());
					etZielBreiteGrad.setText(ZielBreiteGradString);
					etZielBreiteMinDez.setText(ZielBreiteMinDezString);
					etZielLaengeGrad.setText(ZielLaengeGradString);
					etZielLaengeMinDez.setText(ZielLaengeMinDezString);	
					
					//Buttonbeschriftung für Ziel N/S / E/W setzen  
					if (P4NordSuedVar == true){
						bNordSued.setText("   S");
						bNordSuedVar = true;
						SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
						Editor editor1 = einstellungspeicher.edit();
						editor1.putBoolean("bNordSuedVar", bNordSuedVar);
						editor1.commit();		
					}
					else{
						bNordSued.setText("   N");
						bNordSuedVar = false;
						SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
						Editor editor1 = einstellungspeicher.edit();
						editor1.putBoolean("bNordSuedVar", bNordSuedVar);
						editor1.commit();
					}
					if (P4OstWestVar == true){
						bOstWest.setText("' W");
						bOstWestVar = true;
						SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
						Editor editor1 = einstellungspeicher.edit();
						editor1.putBoolean("bOstWestVar", bOstWestVar);
						editor1.commit();			
					}
					else{
						bOstWest.setText("' E");
						bOstWestVar = false;
						SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
						Editor editor1 = einstellungspeicher.edit();
						editor1.putBoolean("bOstWestVar", bOstWestVar);
						editor1.commit();
					}	
				    //ggf. laufende Navigation beenden
			     	NaviAnsageEin = false;
			    	bNaviStarten.setText("Navigation starten");
		    		stopService(new Intent(this, GCBabbleService.class));  
					Toast.makeText(this, "Als Ziel gesetzt.", Toast.LENGTH_LONG).show();
				break;
				case R.id.bPosMerkenP5: 
				    //Vibration als Bestätigung
			        Vibrator vPMP5 = (Vibrator) getSystemService
			    	(Context.VIBRATOR_SERVICE);
				    vPMP5.vibrate( 40 );
					PosMerkenP = 5;
					PositionMerken = true; 
					Toast.makeText(this, "Bitte warten...", Toast.LENGTH_SHORT).show();
				break;
				case R.id.bZielMerkenP5: 
				    //Vibration als Bestätigung
			        Vibrator vZMP5 = (Vibrator) getSystemService
			    	(Context.VIBRATOR_SERVICE);
				    vZMP5.vibrate( 40 );
					ZielBreiteGradString = (etZielBreiteGrad.getText().toString());
					ZielBreiteMinDezString = (etZielBreiteMinDez.getText().toString());
					ZielLaengeGradString = (etZielLaengeGrad.getText().toString());
					ZielLaengeMinDezString = (etZielLaengeMinDez.getText().toString());
					etP5BreiteGrad.setText(ZielBreiteGradString);
					etP5BreiteMinDez.setText(ZielBreiteMinDezString);
					etP5LaengeGrad.setText(ZielLaengeGradString);
					etP5LaengeMinDez.setText(ZielLaengeMinDezString);	
					String Zeitstempel5 = (String) DateFormat.format("dd.MM.yy / kk:mm", new java.util.Date());
					etP5Text.setText("Ziel vom " + Zeitstempel5);

					//TV-Beschriftung N/S / E/W setzen  
					if (bNordSuedVar == true){
						tvP5NordSued.setText("  S");
						P5NordSuedVar = true;
						SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
						Editor editor1 = einstellungspeicher.edit();
						editor1.putBoolean("P5NordSuedVar", P5NordSuedVar);
						editor1.commit();		
					}
					else{
						tvP5NordSued.setText("  N");
						P5NordSuedVar = false;
						SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
						Editor editor1 = einstellungspeicher.edit();
						editor1.putBoolean("P5NordSuedVar", P5NordSuedVar);
						editor1.commit();
					}
					if (bOstWestVar == true){
						tvP5OstWest.setText("' W");
						P5OstWestVar = true;
						SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
						Editor editor1 = einstellungspeicher.edit();
						editor1.putBoolean("P5OstWestVar", P5OstWestVar);
						editor1.commit();			
					}
					else{
						tvP5OstWest.setText("' E");
						P5OstWestVar = false;
						SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
						Editor editor1 = einstellungspeicher.edit();
						editor1.putBoolean("P5OstWestVar", P5OstWestVar);
						editor1.commit();
					}	
					Toast.makeText(this, "Aktuelles Ziel gespeichert.", Toast.LENGTH_SHORT).show();
				break;
				case R.id.bAlsZielP5: 
				    //Vibration als Bestätigung
			        Vibrator vZP5 = (Vibrator) getSystemService
			    	(Context.VIBRATOR_SERVICE);
			    	vZP5.vibrate( 60 );
					ZielBreiteGradString = (etP5BreiteGrad.getText().toString());
					ZielBreiteMinDezString = (etP5BreiteMinDez.getText().toString());
					ZielLaengeGradString = (etP5LaengeGrad.getText().toString());
					ZielLaengeMinDezString = (etP5LaengeMinDez.getText().toString());
					etZielBreiteGrad.setText(ZielBreiteGradString);
					etZielBreiteMinDez.setText(ZielBreiteMinDezString);
					etZielLaengeGrad.setText(ZielLaengeGradString);
					etZielLaengeMinDez.setText(ZielLaengeMinDezString);	
					
				//Buttonbeschriftung für Ziel N/S / E/W setzen  
				if (P5NordSuedVar == true){
					bNordSued.setText("   S");
					bNordSuedVar = true;
					SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
					Editor editor1 = einstellungspeicher.edit();
					editor1.putBoolean("bNordSuedVar", bNordSuedVar);
					editor1.commit();		
				}
				else{
					bNordSued.setText("   N");
					bNordSuedVar = false;
					SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
					Editor editor1 = einstellungspeicher.edit();
					editor1.putBoolean("bNordSuedVar", bNordSuedVar);
					editor1.commit();
				}
				if (P5OstWestVar == true){
					bOstWest.setText("' W");
					bOstWestVar = true;
					SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
					Editor editor1 = einstellungspeicher.edit();
					editor1.putBoolean("bOstWestVar", bOstWestVar);
					editor1.commit();			
				}
				else{
					bOstWest.setText("' E");
					bOstWestVar = false;
					SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
					Editor editor1 = einstellungspeicher.edit();
					editor1.putBoolean("bOstWestVar", bOstWestVar);
					editor1.commit();
				}	
			    	//ggf. laufende Navigation beenden
		    		NaviAnsageEin = false;
		    		bNaviStarten.setText("Navigation starten");
		    		stopService(new Intent(this, GCBabbleService.class));  
		 			Toast.makeText(this, "Als Ziel gesetzt.", Toast.LENGTH_LONG).show();
				break;
				
				
				
				
				
		}// end switch(src.getId()) 
	}// end public void onClick(View src) 

	protected void onActivityResult(int requestCode, int resultCode, Intent data) { //OPTIONAL Check, ob TextToSpeech installiert ist
		if (requestCode == MY_DATA_CHECK_CODE) 	{
			if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS) {
				// success, create the TTS instance
				tts = new TextToSpeech(this, this);
			}
			else {
				// missing data, install it
				Toast.makeText(this, "Text-To-Speech ist nicht installiert. Gibt es kostenlos im Market...", Toast.LENGTH_LONG).show();
				//Intent installIntent = new Intent();
				//installIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
				//startActivity(installIntent);
				
				
				
			}
		}
	}// end protected void onActivityResult

	@Override
	public void onInit(int arg0) { //OPTIONAL Infos �ber TextToSpeech
		if (arg0 == TextToSpeech.SUCCESS) {
			////Toast.makeText(this, "Text-To-Speech OK", Toast.LENGTH_LONG).show();
			}
		else if (arg0 == TextToSpeech.ERROR) {
			Toast.makeText(this, "Text-To-Speech hat einen Fehler verursacht oder ist nicht installiert.", Toast.LENGTH_LONG).show();
			}	
	}// end public void onInit(int arg0) 

	@Override
	protected void onPause(){
		super.onPause();{
			////Toast.makeText(getApplicationContext(), "GC-Babble pausiert", Toast.LENGTH_LONG).show();
			
		//****************************************************************************************
		//Prefs speichern !!!!!!!!!!!  BEI PAUSE UND BEENDEN-BUTTON   !!!!!!!!!!!!!!!!!!!!!
		//****************************************************************************************
			//NaviAnsageIntervallSoll "merken"
		SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
		Editor editor1 = einstellungspeicher.edit();
			editor1.putLong("NaviAnsageIntervallSoll", NaviAnsageIntervallSoll); //AnsageIntervall speichern
		editor1.commit();
		
		//Aktuellen Navi-Modus "merken"
		SharedPreferences variablenspeicher = getPreferences(Context.MODE_PRIVATE);
		Editor editor2 = variablenspeicher.edit();
		editor2.putBoolean("NaviAnsageEin", NaviAnsageEin);
		editor2.commit();
		
		//EditText-Felder der Zieleingabe als Prefs speichern, vorher Variablen aktualisieren
		//Eingaben aus EditText in String konvertieren
		ZielBreiteGradString = etZielBreiteGrad.getText().toString(); //EditText zu String
		ZielBreiteMinDezString = etZielBreiteMinDez.getText().toString(); //EditText zu String
		ZielLaengeGradString = etZielLaengeGrad.getText().toString(); //EditText zu String
		ZielLaengeMinDezString = etZielLaengeMinDez.getText().toString(); //EditText zu String
		//Speichern der String-Variablen aus den EditText-Zieleingabefeldern		
		//Koordinatenspeicher wird "global" in Prefs gespeichert, damit Parkplatzservice mit zugreifen kann
		SharedPreferences koordinatenspeicher = PreferenceManager.getDefaultSharedPreferences(this);
		Editor editor3 = koordinatenspeicher.edit();
		editor3.putString("ZielBreiteGradString", ZielBreiteGradString);
		editor3.putString("ZielBreiteMinDezString", ZielBreiteMinDezString);
		editor3.putString("ZielLaengeGradString", ZielLaengeGradString); 
		editor3.putString("ZielLaengeMinDezString", ZielLaengeMinDezString); 
		//Ziel-Speicher in Prefs speichern - EditText direkt in Prefs speichern
		editor3.putString("etP0Text", etP0Text.getText().toString());
		editor3.putString("etP1Text", etP1Text.getText().toString());
		editor3.putString("etP2Text", etP2Text.getText().toString());
		editor3.putString("etP3Text", etP3Text.getText().toString());
		editor3.putString("etP4Text", etP4Text.getText().toString());
		editor3.putString("etP5Text", etP5Text.getText().toString());
		editor3.putString("etP0BreiteGrad", etP0BreiteGrad.getText().toString());
		editor3.putString("etP0BreiteMinDez", etP0BreiteMinDez.getText().toString());
		editor3.putString("etP0LaengeGrad", etP0LaengeGrad.getText().toString());
		editor3.putString("etP0LaengeMinDez", etP0LaengeMinDez.getText().toString());
		editor3.putString("etP1BreiteGrad", etP1BreiteGrad.getText().toString());
		editor3.putString("etP1BreiteMinDez", etP1BreiteMinDez.getText().toString());
		editor3.putString("etP1LaengeGrad", etP1LaengeGrad.getText().toString());
		editor3.putString("etP1LaengeMinDez", etP1LaengeMinDez.getText().toString());
		editor3.putString("etP2BreiteGrad", etP2BreiteGrad.getText().toString());
		editor3.putString("etP2BreiteMinDez", etP2BreiteMinDez.getText().toString());
		editor3.putString("etP2LaengeGrad", etP2LaengeGrad.getText().toString());
		editor3.putString("etP2LaengeMinDez", etP2LaengeMinDez.getText().toString());
		editor3.putString("etP3BreiteGrad", etP3BreiteGrad.getText().toString());
		editor3.putString("etP3BreiteMinDez", etP3BreiteMinDez.getText().toString());
		editor3.putString("etP3LaengeGrad", etP3LaengeGrad.getText().toString());
		editor3.putString("etP3LaengeMinDez", etP3LaengeMinDez.getText().toString());
		editor3.putString("etP4BreiteGrad", etP4BreiteGrad.getText().toString());
		editor3.putString("etP4BreiteMinDez", etP4BreiteMinDez.getText().toString());
		editor3.putString("etP4LaengeGrad", etP4LaengeGrad.getText().toString());
		editor3.putString("etP4LaengeMinDez", etP4LaengeMinDez.getText().toString());
		editor3.putString("etP5BreiteGrad", etP5BreiteGrad.getText().toString());
		editor3.putString("etP5BreiteMinDez", etP5BreiteMinDez.getText().toString());
		editor3.putString("etP5LaengeGrad", etP5LaengeGrad.getText().toString());
		editor3.putString("etP5LaengeMinDez", etP5LaengeMinDez.getText().toString());
		editor3.putString("etNotizen", etNotizen.getText().toString());		
		//Merker f�r Erkennung und Speicherung "Letztes Ziel"
		editor3.putString("etP0TextMerker", etP0TextMerker);
		editor3.putString("ZielBreiteGradStringMerker", ZielBreiteGradStringMerker);
		editor3.putString("ZielBreiteMinDezStringMerker", ZielBreiteMinDezStringMerker);
		editor3.putString("ZielLaengeGradStringMerker", ZielLaengeGradStringMerker);
		editor3.putString("ZielLaengeMinDezStringMerker", ZielLaengeMinDezStringMerker);	
		editor3.commit();
		}
	}
	
	@Override
	protected void onStop(){
		super.onStop();{
			////Toast.makeText(getApplicationContext(), "GC-Babble gestoppt", Toast.LENGTH_LONG).show();
	
		//locationManager.removeUpdates(locationListener);
		//locationManager = null;
		
		finish(); //verhindert ein Weiterlaufen im Hintergrund, bringt aber Absturz beim �ffnen der Hardwaretastatur
		
		}
	}
	
	
	
	
	protected void onDestroy()	{ //Sensoren beim Beenden abschalten
		super.onDestroy();{
			////Toast.makeText(getApplicationContext(), "Destroy", Toast.LENGTH_LONG).show();
			//mSensorManager.unregisterListener(mSensorEventListener); //SensorManager beenden
			locationManager.removeUpdates(locationListener);
			locationManager = null;
			
		}
	
	
	
	}// end protected void onDestroy()

	
	protected void onResume() {
		super.onResume();{
	
			//Toast.makeText(getApplicationContext(), "onResume", Toast.LENGTH_LONG).show();
			
			
		}
	}// end protected void onResume()
	
	
	//----------------------------------------------------------------------

	//*********************************************************************
	//                 MENU - Button - Menü erstellen
	//*********************************************************************
	
	//----------------------------------------------------------------------
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		//Men�-Items erstellen
		boolean supRetVal = super.onCreateOptionsMenu(menu);
		menu.add(0, 0, 0, "Parkplatz merken"); //Hier wird ein Men�punkt eingef�gt
		menu.add(0, 1, 0, "Zum Parkplatz navigieren"); 
		menu.add(0, 2, 0, btEinstellungen); 
		menu.add(0, 3, 0, "Info"); 
		menu.add(0, 4, 0, "Beenden"); 
		//menu.add(0, 5, 0, "Reserve3"); 
		return supRetVal;
	}

	//Men�-Items ausf�hren
	public boolean onOptionsItemSelected(MenuItem item)	{
		switch (item.getItemId()){
			case 0:ParkplatzMerken();
			return true;
			case 1:zumParkplatzNavigieren();
			return true;
			case 2:einstellungen();
			return true;
			case 3:info();
			return true;
			case 4:beenden();
			return true;
			//case 5:reserve3();
			//return true;
		}
		return false;
	}

	private void ParkplatzMerken() { //bei Menu-Button-Klick
		ParkplatzMerken = true;
		//Prefs speichern 
		SharedPreferences variablenspeicher = getPreferences(Context.MODE_PRIVATE);
		Editor editor2 = variablenspeicher.edit();
		editor2.putBoolean("ParkplatzMerken", ParkplatzMerken); 
		editor2.commit();
		
		cbParkplatzAutomatischSpeichern.setChecked(false);
		//ParkplatzManuellGespeichert = true;
		//tvTextAusgabe.setText(String.valueOf("Parkplatz wird gespeichert, wenn GPS-Signal OK.")); //Textausgabe
		Toast.makeText(getApplicationContext(), "Bitte warten...", Toast.LENGTH_LONG).show();
		
		//Parkplatz-Hintergrundservice stoppen
		stopService(new Intent(this, GCBabbleServiceParkplatz.class));
		cbParkplatzAutomatischSpeichern.setChecked(false);	
		//Checkboxen in Variablen umwandeln
		cbParkplatzAutomatischSpeichernVar = cbParkplatzAutomatischSpeichern.isChecked();
		//Prefs speichern 
		SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
		Editor editor1 = einstellungspeicher.edit();
		editor1.putBoolean("cbParkplatzAutomatischSpeichernVar", cbParkplatzAutomatischSpeichernVar); 
		editor1.commit();
		
	}

	private void zumParkplatzNavigieren() {  //bei Menu-Button-Klick
		if (ParkplatzBreiteDez > 0){
			
			//Parkplatzkoordinaten vorm Navigieren �bernehmen aus PREFERENCES //
			//SharedPreferences koordinatenspeicher = getPreferences(Context.MODE_WORLD_WRITEABLE);
			SharedPreferences koordinatenspeicher = PreferenceManager.getDefaultSharedPreferences(this);
			ParkplatzBreiteDezString = koordinatenspeicher.getString("ParkplatzBreiteDezString", "0.00000000");
			ParkplatzBreiteDezString = ParkplatzBreiteDezString.replace(",","."); //Komma durch Punkt ersetzen
			ParkplatzBreiteDez = (double) Double.parseDouble(ParkplatzBreiteDezString); //String zu double - Variable
			//Toast.makeText(getApplicationContext(), ParkplatzBreiteDezString, Toast.LENGTH_LONG).show();
			ParkplatzLaengeDezString = koordinatenspeicher.getString("ParkplatzLaengeDezString", "0.00000000");
			ParkplatzLaengeDezString = ParkplatzLaengeDezString.replace(",","."); //Komma durch Punkt ersetzen
			ParkplatzLaengeDez = (double) Double.parseDouble(ParkplatzLaengeDezString); //String zu double - Variable
			//Toast.makeText(getApplicationContext(), ParkplatzLaengeDezString, Toast.LENGTH_LONG).show();
			ParkplatzNordSuedVar = koordinatenspeicher.getBoolean("ParkplatzNordSuedVar", false);
			ParkplatzOstWestVar = koordinatenspeicher.getBoolean("ParkplatzOstWestVar", false);
			
			
			//Parkplatzkoordinaten-TextView aktualisieren
			DecimalFormat df23 = new DecimalFormat("0.00000000"); //Dezimalzahl formatieren
			ParkplatzBreiteDezString = df23.format(ParkplatzBreiteDez); //Dezimalzahl formatieren
			ParkplatzBreiteDezString = ParkplatzBreiteDezString.replace(",","."); //Komma durch Punkt ersetzen
			tvParkplatzBreiteDez.setText(String.valueOf(ParkplatzBreiteDezString)); //String zu TextView
			DecimalFormat df24 = new DecimalFormat("0.00000000"); //Dezimalzahl formatieren
			ParkplatzLaengeDezString = df24.format(ParkplatzLaengeDez); //Dezimalzahl formatieren
			ParkplatzLaengeDezString = ParkplatzLaengeDezString.replace(",","."); //Komma durch Punkt ersetzen
			tvParkplatzLaengeDez.setText(String.valueOf(ParkplatzLaengeDezString)); //String zu TextView
		
			String ParkplatzBreiteDezString = "" + ParkplatzBreiteDez; //Double zu String
			etZielBreiteGrad.setText(ParkplatzBreiteDezString); //EditText ausf�llen
			String ParkplatzLaengeDezString = "" + ParkplatzLaengeDez;
			etZielLaengeGrad.setText(ParkplatzLaengeDezString); //EditText ausf�llen
			////etZielBreiteMinDez.setText("000"); //EditText ausf�llen
			////etZielLaengeMinDez.setText("000"); //EditText ausf�llen
			NaviAnsageEin = true; //Naviansage sofort nach Aufruf starten
			bNaviStarten.setText("Navigation beenden");
			NaviAnsageAutomatischAus = 0;
			NaviAnsageZaehler = NaviAnsageIntervall - 3;
			NaviAnsageStartZeit = System.currentTimeMillis() - ((NaviAnsageIntervall - 3) * 1000); //NaviAnsageStartZeit auf "in 3 Sekunden" setzen
			//tvTextAusgabe.setText(String.valueOf("Parkplatz-Koordinaten importiert. Wartet auf GPS...")); //Textausgabe
			Toast.makeText(getApplicationContext(), "Parkplatz als Ziel.", Toast.LENGTH_LONG).show();
			tts.speak("Parkplatz als Ziel.",TextToSpeech.QUEUE_ADD, null);
		
			
			
			
			//Parkplatz-/Zielkoordinaten Grad Breite dezimal in Grad und Min dezimal umrechnen und in EditText eintragen
			String ParkplatzBreiteString = Location.convert(ParkplatzBreiteDez, Location.FORMAT_MINUTES);
			////Toast.makeText(getApplicationContext(), ZielBreiteString, Toast.LENGTH_LONG).show();
			//Datenstring zerlegen an ":" in Grad und Min Dez
			String[] ZielBreiteStringTeil = ParkplatzBreiteString.split(":");
			ZielBreiteGradString = ZielBreiteStringTeil[0];
			ZielBreiteMinDezString = ZielBreiteStringTeil[1].replace(",","."); //Komma durch Punkt ersetzen
			etZielBreiteGrad.setText(ZielBreiteGradString); //EditText Ziel Grad ausf�llen
			etZielBreiteMinDez.setText(ZielBreiteMinDezString); //EditText Ziel Min dezimal ausf�llen
			
			//Zielkoordinaten Grad L�nge dezimal in Grad und Min dezimal umrechnen und in EditText eintragen
			String ParkplatzLaengeString = Location.convert(ParkplatzLaengeDez, Location.FORMAT_MINUTES);
			////Toast.makeText(getApplicationContext(), ZielBreiteString, Toast.LENGTH_LONG).show();
			//Datenstring zerlegen an ":" in Grad und Min Dez
			String[] ZielLaengeStringTeil = ParkplatzLaengeString.split(":");
			ZielLaengeGradString = ZielLaengeStringTeil[0];
			ZielLaengeMinDezString = ZielLaengeStringTeil[1].replace(",","."); //Komma durch Punkt ersetzen
			etZielLaengeGrad.setText(ZielLaengeGradString); //EditText Ziel Grad ausf�llen
			etZielLaengeMinDez.setText(ZielLaengeMinDezString); //EditText Ziel Min dezimal ausf�llen
			
			if (ParkplatzNordSuedVar == true){
				bNordSued.setText("   S");
				bNordSuedVar = true;
				SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
				Editor editor1 = einstellungspeicher.edit();
				editor1.putBoolean("bNordSuedVar", bNordSuedVar);
				editor1.commit();
				etZielBreiteGrad.setText(ZielBreiteGradString.replace("-","")); 		
			}
			else{
				bNordSued.setText("   N"); 
				bNordSuedVar = false;
				SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
				Editor editor1 = einstellungspeicher.edit();
				editor1.putBoolean("bNordSuedVar", bNordSuedVar);
				editor1.commit();
			}
	
			if (ParkplatzOstWestVar == true){
				bOstWest.setText("' W");
				bOstWestVar = true;
				SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
				Editor editor1 = einstellungspeicher.edit();
				editor1.putBoolean("bOstWestVar", bOstWestVar);
				editor1.commit();
				etZielLaengeGrad.setText(ZielLaengeGradString.replace("-","")); 			
			}
			else{
				bOstWest.setText("' E");
				bOstWestVar = false;
				SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
				Editor editor1 = einstellungspeicher.edit();
				editor1.putBoolean("bOstWestVar", bOstWestVar);
				editor1.commit();
			}
			
			
			//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			//     Hintergrund-Service starten bei Parkplatznavigation
			//++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
			
				//Toast.makeText(getApplicationContext(),  "Hintergrund-Service wird gestartet...*", Toast.LENGTH_SHORT).show();
				
				
				//Intent f�r Service vorbereiten
				Intent meinServiceIntent = new Intent(this, GCBabbleService.class);
				//Zusammenstellung "Untertitel" Foreground-Service
				meinServiceIntent.putExtra("IventInfo", 
						"Parkplatz:" + bNordSued.getText() + " " + ZielBreiteGradString + " Grad " + ZielBreiteMinDezString + 
						bOstWest.getText() + " " + ZielLaengeGradString + " Grad " + ZielLaengeMinDezString + "' ");
									
				//Einstellungen per Intent an Service �bertragen
				meinServiceIntent.putExtra("KorrekturwinkelString", KorrekturwinkelString);
	    		meinServiceIntent.putExtra("NaviAusDisplayString", NaviAusDisplayString); 
	    		boolean cbNaviLinksRechtsVar = cbNaviLinksRechts.isChecked();
	    		meinServiceIntent.putExtra("cbNaviLinksRechtsVar", cbNaviLinksRechtsVar); 
	    		boolean cbDatumZeitVar = cbDatumZeit.isChecked();
	    		meinServiceIntent.putExtra("cbDatumZeitVar", cbDatumZeitVar); 
	    		boolean cbBewRichtungEinVar = cbBewRichtungEin.isChecked();
	    		meinServiceIntent.putExtra("cbBewRichtungEinVar", cbBewRichtungEinVar); 
	    		boolean cbParkplatzAutomatischSpeichernVar = cbParkplatzAutomatischSpeichern.isChecked();
    			meinServiceIntent.putExtra("cbParkplatzAutomatischSpeichernVar", cbParkplatzAutomatischSpeichernVar); 
    			boolean cbKeinMagnetKompassVar = cbKeinMagnetKompass.isChecked();
        		meinServiceIntent.putExtra("cbKeinMagnetKompassVar", cbKeinMagnetKompassVar); 
			NaviAnsageIntervallString = tvAnsageIntervall.getText().toString(); //TextView zu String
			meinServiceIntent.putExtra("NaviAnsageIntervallString", NaviAnsageIntervallString);
			    meinServiceIntent.putExtra("cbGehFahrHaltErkennungVar", cbGehFahrHaltErkennungVar);
			meinServiceIntent.putExtra("cbOptimiertesAnsageIntervallVar", cbOptimiertesAnsageIntervallVar);
			
				
				
				//Ziel-Koordinaten per Intent an Service �bertragen
				
				
				//Ziel-Koordinaten (Parkplatz) per Intent an Service �bertragen
				meinServiceIntent.putExtra("ZielBreiteDezString", "" + ParkplatzBreiteDez);
	    		meinServiceIntent.putExtra("ZielLaengeDezString", "" + ParkplatzLaengeDez); 
				
	    		//Aktuelle Variablen per Intent an Service �bertragen
	    		meinServiceIntent.putExtra("NaviAnsageEin", NaviAnsageEin); 
								
				//Service-Intent starten
				startService(meinServiceIntent);     
					
			
					// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
					//Letztes Ziel speichern...wenn zum P navigiert wird...
					// * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
					if (ZielBreiteGradString.equals(ZielBreiteGradStringMerker) && ZielBreiteMinDezString.equals(ZielBreiteMinDezStringMerker) && 
							ZielLaengeGradString.equals(ZielLaengeGradStringMerker) && ZielLaengeMinDezString.equals(ZielLaengeMinDezStringMerker)){
						////Toast.makeText(getApplicationContext(),  "Kein neues Ziel*", Toast.LENGTH_SHORT).show();
					}//end "Kein neues Ziel*"
					else{//Wenn es ein neues Ziel gibt, dann...
						//...Merker in "Letztes Ziel" schreiben
						etP0Text.setText(etP0TextMerker);
						etP0BreiteGrad.setText(ZielBreiteGradStringMerker);
						etP0BreiteMinDez.setText(ZielBreiteMinDezStringMerker);
						etP0LaengeGrad.setText(ZielLaengeGradStringMerker);
						etP0LaengeMinDez.setText(ZielLaengeMinDezStringMerker);
					//TV-Beschriftung N/S / E/W setzen  
					if (bNordSuedVarMerker == true){
						tvP0NordSued.setText("  S");
						P0NordSuedVar = true;
						SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
						Editor editor1 = einstellungspeicher.edit();
						editor1.putBoolean("P0NordSuedVar", P0NordSuedVar);
						editor1.commit();		
					}
					else{
						tvP0NordSued.setText("  N");
						P0NordSuedVar = false;
						SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
						Editor editor1 = einstellungspeicher.edit();
						editor1.putBoolean("P0NordSuedVar", P0NordSuedVar);
						editor1.commit();
					}
					if (bOstWestVarMerker == true){
						tvP0OstWest.setText("' W");
						P0OstWestVar = true;
						SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
						Editor editor1 = einstellungspeicher.edit();
						editor1.putBoolean("P0OstWestVar", P0OstWestVar);
						editor1.commit();			
					}
					else{
						tvP0OstWest.setText("' E");
						P0OstWestVar = false;
						SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
						Editor editor1 = einstellungspeicher.edit();
						editor1.putBoolean("P0OstWestVar", P0OstWestVar);
						editor1.commit();
					}					
						//...aktuelle Zielkoordinaten in Merker speichern
						String Zeitstempel = (String) DateFormat.format("dd.MM.yy / kk:mm", new java.util.Date());
						etP0TextMerker = "Letztes Ziel vom " + Zeitstempel;
						ZielBreiteGradStringMerker = ZielBreiteGradString;
						ZielBreiteMinDezStringMerker = ZielBreiteMinDezString;
						ZielLaengeGradStringMerker = ZielLaengeGradString;
						ZielLaengeMinDezStringMerker = ZielLaengeMinDezString;
						Toast.makeText(getApplicationContext(),  "Letztes Ziel gespeichert.", Toast.LENGTH_SHORT).show();
					//N/S / E/W in Merker schreiben
					bNordSuedVarMerker = bNordSuedVar;
					bOstWestVarMerker = bOstWestVar;
					
					}//end "Neues Ziel*" dann letztes Ziel speichern
					
				
		}
		else{
			Toast.makeText(getApplicationContext(), "Keine Parkplatz-Koordinaten vorhanden.", Toast.LENGTH_LONG).show();
		}
	}
	
	private void info() {  //bei Menu-Button-Klick
		loEinstellungen.setVisibility(View.GONE);
		loNavigation.setVisibility(View.GONE);
		loNaviAnzeige.setVisibility(View.GONE);
		loInfo2.setVisibility(View.GONE);
		loInfo1.setVisibility(View.VISIBLE);
		layoutConfig = 21;
    }
	
	private void einstellungen() {  //bei Menu-Button-Klick
		if (layoutConfig != 11){
			loNavigation.setVisibility(View.GONE);
			loNaviAnzeige.setVisibility(View.GONE);
			loInfo1.setVisibility(View.GONE);
			loInfo2.setVisibility(View.GONE);
			loEinstellungen.setVisibility(View.VISIBLE); //Layout einblenden
			layoutConfig = 11;}
		else{
			if (tvAnsageIntervall.length() > 0){
				String NaviAnsageIntervallString = tvAnsageIntervall.getText().toString(); //EditText zu String
				NaviAnsageIntervall = (int) Float.parseFloat(NaviAnsageIntervallString); //String zu int
			}
			if (tvKorrekturwinkel.length() > 0){
				String KorrekturwinkelString = tvKorrekturwinkel.getText().toString(); //EditText zu Int
				Korrekturwinkel = (double) Float.parseFloat(KorrekturwinkelString); //String zu double
			}
			loEinstellungen.setVisibility(View.GONE); //Layout ausblenden
			loNavigation.setVisibility(View.VISIBLE);
			loNaviAnzeige.setVisibility(View.VISIBLE);
			layoutConfig = 0;}
	}
	
	private void beenden() {  //bei Menu-Button-Klick
		//mSensorManager.unregisterListener(mSensorEventListener);
		NaviAnsageEin = false;
		NaviAnsageAutomatischAus = 1;
		
				
		//--------------------------------------------------
		//          Hintergrund-Service beenden
		//--------------------------------------------------
		//Toast.makeText(getApplicationContext(),  "Hintergrund-Service wird jetzt beendet...*", Toast.LENGTH_LONG).show();
		stopService(new Intent(this, GCBabbleService.class));      
		
		
		//****************************************************************************************
		//Prefs speichern !!!!!!!!!!!  BEI PAUSE UND BEENDEN-BUTTON   !!!!!!!!!!!!!!!!!!!!!
		//****************************************************************************************
		//NaviAnsageIntervallSoll "merken"
		SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
		Editor editor1 = einstellungspeicher.edit();
		editor1.putLong("NaviAnsageIntervallSoll", NaviAnsageIntervallSoll); //AnsageIntervall speichern
		editor1.commit();
		
		//Aktuellen Navi-Modus "merken"
		SharedPreferences variablenspeicher = getPreferences(Context.MODE_PRIVATE);
		Editor editor2 = variablenspeicher.edit();
		editor2.putBoolean("NaviAnsageEin", NaviAnsageEin);
		editor2.commit();
		
		//EditText-Felder der Zieleingabe als Prefs speichern, vorher Variablen aktualisieren
		//Eingaben aus EditText in String konvertieren
		ZielBreiteGradString = etZielBreiteGrad.getText().toString(); //EditText zu String
		ZielBreiteMinDezString = etZielBreiteMinDez.getText().toString(); //EditText zu String
		ZielLaengeGradString = etZielLaengeGrad.getText().toString(); //EditText zu String
		ZielLaengeMinDezString = etZielLaengeMinDez.getText().toString(); //EditText zu String
		//Speichern der String-Variablen aus den EditText-Zieleingabefeldern		
		//Koordinatenspeicher wird "global" in Prefs gespeichert, damit Parkplatzservice mit zugreifen kann
		SharedPreferences koordinatenspeicher = PreferenceManager.getDefaultSharedPreferences(this);
		Editor editor3 = koordinatenspeicher.edit();
		editor3.putString("ZielBreiteGradString", ZielBreiteGradString);
		editor3.putString("ZielBreiteMinDezString", ZielBreiteMinDezString);
		editor3.putString("ZielLaengeGradString", ZielLaengeGradString); 
		editor3.putString("ZielLaengeMinDezString", ZielLaengeMinDezString); 
		//Ziel-Speicher in Prefs speichern - EditText direkt in Prefs speichern
		editor3.putString("etP0Text", etP0Text.getText().toString());
		editor3.putString("etP1Text", etP1Text.getText().toString());
		editor3.putString("etP2Text", etP2Text.getText().toString());
		editor3.putString("etP3Text", etP3Text.getText().toString());
		editor3.putString("etP4Text", etP4Text.getText().toString());
		editor3.putString("etP5Text", etP5Text.getText().toString());
		editor3.putString("etP0BreiteGrad", etP0BreiteGrad.getText().toString());
		editor3.putString("etP0BreiteMinDez", etP0BreiteMinDez.getText().toString());
		editor3.putString("etP0LaengeGrad", etP0LaengeGrad.getText().toString());
		editor3.putString("etP0LaengeMinDez", etP0LaengeMinDez.getText().toString());
		editor3.putString("etP1BreiteGrad", etP1BreiteGrad.getText().toString());
		editor3.putString("etP1BreiteMinDez", etP1BreiteMinDez.getText().toString());
		editor3.putString("etP1LaengeGrad", etP1LaengeGrad.getText().toString());
		editor3.putString("etP1LaengeMinDez", etP1LaengeMinDez.getText().toString());
		editor3.putString("etP2BreiteGrad", etP2BreiteGrad.getText().toString());
		editor3.putString("etP2BreiteMinDez", etP2BreiteMinDez.getText().toString());
		editor3.putString("etP2LaengeGrad", etP2LaengeGrad.getText().toString());
		editor3.putString("etP2LaengeMinDez", etP2LaengeMinDez.getText().toString());
		editor3.putString("etP3BreiteGrad", etP3BreiteGrad.getText().toString());
		editor3.putString("etP3BreiteMinDez", etP3BreiteMinDez.getText().toString());
		editor3.putString("etP3LaengeGrad", etP3LaengeGrad.getText().toString());
		editor3.putString("etP3LaengeMinDez", etP3LaengeMinDez.getText().toString());
		editor3.putString("etP4BreiteGrad", etP4BreiteGrad.getText().toString());
		editor3.putString("etP4BreiteMinDez", etP4BreiteMinDez.getText().toString());
		editor3.putString("etP4LaengeGrad", etP4LaengeGrad.getText().toString());
		editor3.putString("etP4LaengeMinDez", etP4LaengeMinDez.getText().toString());
		editor3.putString("etP5BreiteGrad", etP5BreiteGrad.getText().toString());
		editor3.putString("etP5BreiteMinDez", etP5BreiteMinDez.getText().toString());
		editor3.putString("etP5LaengeGrad", etP5LaengeGrad.getText().toString());
		editor3.putString("etP5LaengeMinDez", etP5LaengeMinDez.getText().toString());
		editor3.putString("etNotizen", etNotizen.getText().toString());		
		//Merker f�r Erkennung und Speicherung "Letztes Ziel"
		editor3.putString("etP0TextMerker", etP0TextMerker);
		editor3.putString("ZielBreiteGradStringMerker", ZielBreiteGradStringMerker);
		editor3.putString("ZielBreiteMinDezStringMerker", ZielBreiteMinDezStringMerker);
		editor3.putString("ZielLaengeGradStringMerker", ZielLaengeGradStringMerker);
		editor3.putString("ZielLaengeMinDezStringMerker", ZielLaengeMinDezStringMerker);	
		editor3.commit();
			
		//App komplett beenden
		finish();
		System.exit(0);
	}
	
	  //
	//----------------------------------------------------------//
  //----------------------------------------------------------//
//    Button Back neu belegen (abh�ngig von layoutConfig)   //
  //----------------------------------------------------------//
	//----------------------------------------------------------//
	  //
	 
	@Override
	    public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if ((keyCode == KeyEvent.KEYCODE_BACK)){
	    	switch(layoutConfig){
	    	case(0): //Navigation
	    		
		    		//Zeitabh�ngiges zweites Dr�cken der Back-Taste erkennen (wenn letzter Druck nicht l�nger als 5s zur�ck liegt)
		    		if (System.currentTimeMillis() <= (BackButtonDruckZeit + 4000)){
		    			finish();
		    		}//end if (System.currentTimeMillis()
		    		else {
		    		//Zeit des ersten Tastendrucks merken
		    		BackButtonDruckZeit = System.currentTimeMillis();
		    			if (NaviAnsageEin == true) {
		    				Toast.makeText(getApplicationContext(), "Erneut -BACK-, um App zu verlassen. Navi-Ansage wird fortgesetzt.", Toast.LENGTH_LONG).show();
		    			}
		    			else {
		    				Toast.makeText(getApplicationContext(), "Erneut -BACK-, um App zu beenden.", Toast.LENGTH_LONG).show();
		    			}
		    		}
	    		break;
	    	case(11): //Einstellungen
	    		//Einstellungen SPEICHERN als PREFERENCES wenn Back-Button bei "Einstellungen" gedr�ckt wird
	    		
	    		//Checkboxen in Variablen umwandeln
	    		cbNaviLinksRechtsVar = cbNaviLinksRechts.isChecked();
	    		cbDatumZeitVar = cbDatumZeit.isChecked();
	    		cbBewRichtungEinVar = cbBewRichtungEin.isChecked();
	    		cbParkplatzAutomatischSpeichernVar = cbParkplatzAutomatischSpeichern.isChecked();
	    		cbKeinMagnetKompassVar = cbKeinMagnetKompass.isChecked();
				cbGehFahrHaltErkennungVar = cbGehFahrHaltErkennung.isChecked();
	    		//boolean cbDisplayDrehungZulassenVar = cbDisplayDrehungZulassen.isChecked();
						cbOptimiertesAnsageIntervallVar = cbOptimiertesAnsageIntervall.isChecked();
				
	    		//Prefs speichern 
	    		SharedPreferences einstellungspeicher = getPreferences(Context.MODE_PRIVATE);
				Editor editor1 = einstellungspeicher.edit();
				editor1.putString("KorrekturwinkelString", KorrekturwinkelString);
				editor1.putString("NaviAusDisplayString", NaviAusDisplayString); 
				editor1.putBoolean("cbNaviLinksRechtsVar", cbNaviLinksRechtsVar); 
				editor1.putBoolean("cbDatumZeitVar", cbDatumZeitVar); 
				editor1.putBoolean("cbBewRichtungEinVar", cbBewRichtungEinVar); 
				editor1.putBoolean("cbParkplatzAutomatischSpeichernVar", cbParkplatzAutomatischSpeichernVar); 
				editor1.putBoolean("cbKeinMagnetKompassVar", cbKeinMagnetKompassVar); 
				editor1.putBoolean("cbGehFahrHaltErkennungVar", cbGehFahrHaltErkennungVar);
				//editor1.putBoolean("cbDisplayDrehungZulassenVar", cbDisplayDrehungZulassenVar); 
						editor1.putBoolean("cbOptimiertesAnsageIntervallVar", cbOptimiertesAnsageIntervallVar);
				editor1.commit();
	    		
				//*********************************************
				//Parkplatz-Hintergrundservice starten/stoppen
				//*********************************************
				if (cbParkplatzAutomatischSpeichern.isChecked() == true){
					startService(new Intent(this, GCBabbleServiceParkplatz.class));
				}
				else{
					stopService(new Intent(this, GCBabbleServiceParkplatz.class));
				}
			
				// - - - - - - - - - - - - - - - - - - - - - --- --- --- --- -  - - -  - -
				//  Service mit aktuellen Daten f�ttern, falls Navi bei �nderungen l�uft
				// - - - - - - - - - - - - - - - - - - ---- ---- - --  - - - -  - - -  - -
									
						if (NaviAnsageEin == true){
							
							//Intent f�r Service vorbereiten
							Intent meinServiceIntent = new Intent(this, GCBabbleService.class);
							//Diagnose f�r Daten�bertragung per Service-Intent
							meinServiceIntent.putExtra("extradaten1", "Test1");
												
							//Einstellungen per Intent an Service �bertragen
							meinServiceIntent.putExtra("KorrekturwinkelString", KorrekturwinkelString);
				    		meinServiceIntent.putExtra("NaviAusDisplayString", NaviAusDisplayString); 
				    		cbNaviLinksRechtsVar = cbNaviLinksRechts.isChecked();
				    		meinServiceIntent.putExtra("cbNaviLinksRechtsVar", cbNaviLinksRechtsVar); 
				    		cbDatumZeitVar = cbDatumZeit.isChecked();
				    		meinServiceIntent.putExtra("cbDatumZeitVar", cbDatumZeitVar); 
				    		cbBewRichtungEinVar = cbBewRichtungEin.isChecked();
				    		meinServiceIntent.putExtra("cbBewRichtungEinVar", cbBewRichtungEinVar); 
				    		boolean cbParkplatzAutomatischSpeichernVar = cbParkplatzAutomatischSpeichern.isChecked();
			    			meinServiceIntent.putExtra("cbParkplatzAutomatischSpeichernVar", cbParkplatzAutomatischSpeichernVar); 
			    			cbKeinMagnetKompassVar = cbKeinMagnetKompass.isChecked();
			        		meinServiceIntent.putExtra("cbKeinMagnetKompassVar", cbKeinMagnetKompassVar); 
			    			NaviAnsageIntervallString = tvAnsageIntervall.getText().toString(); //TextView zu String
							meinServiceIntent.putExtra("NaviAnsageIntervallString", NaviAnsageIntervallString);
							meinServiceIntent.putExtra("cbGehFahrHaltErkennungVar", cbGehFahrHaltErkennungVar);
							meinServiceIntent.putExtra("cbOptimiertesAnsageIntervallVar", cbOptimiertesAnsageIntervallVar);
							
							
							//Ziel-Koordinaten per Intent an Service �bertragen
							//Zielkoordinaten in Dezimal umrechnen wenn alle EditText-Felder ausgef�llt sind
							if (etZielBreiteGrad.length() > 0 && etZielLaengeGrad.length() > 0 && etZielBreiteMinDez.length() > 0 && etZielLaengeMinDez.length() > 0){
								//Eingaben aus EditText in float konvertieren
								ZielBreiteGradString = etZielBreiteGrad.getText().toString(); //EditText zu String
								ZielBreiteGrad = Float.parseFloat(ZielBreiteGradString); //String zu float
								ZielBreiteMinDezString = etZielBreiteMinDez.getText().toString(); //EditText zu String
								ZielBreiteMinDez = Float.parseFloat(ZielBreiteMinDezString); //String zu float
								ZielLaengeGradString = etZielLaengeGrad.getText().toString(); //EditText zu String
								ZielLaengeGrad = Float.parseFloat(ZielLaengeGradString); //String zu float
								ZielLaengeMinDezString = etZielLaengeMinDez.getText().toString(); //EditText zu String
								ZielLaengeMinDez = Float.parseFloat(ZielLaengeMinDezString); //String zu float
							
								//Berechnung ZielBreiteDez und ZielLaengeDez
								ZielBreiteDez = ZielBreiteGrad + (ZielBreiteMinDez / 60); //Berechnung Ziel dezimal
								ZielLaengeDez = ZielLaengeGrad + (ZielLaengeMinDez / 60); //Berechnung Ziel dezimal
								
								if (bNordSuedVar == true){
									ZielBreiteDez = ZielBreiteDez * (-1);
								}
								if (bOstWestVar == true){
									ZielLaengeDez = ZielLaengeDez * (-1);
								}
							}
							ZielBreiteDezString = "" + ZielBreiteDez;
							ZielLaengeDezString = "" + ZielLaengeDez;
							meinServiceIntent.putExtra("ZielBreiteDezString", ZielBreiteDezString);
				    		meinServiceIntent.putExtra("ZielLaengeDezString", ZielLaengeDezString); 
							
				    		//Aktuelle Variablen per Intent an Service �bertragen
				    		meinServiceIntent.putExtra("NaviAnsageEin", NaviAnsageEin); 
							
				    		//Zusammenstellung "Untertitel" Foreground-Service
							meinServiceIntent.putExtra("IventInfo", 
									"Ziel:" + bNordSued.getText() + " " + ZielBreiteGradString + " Grad " + ZielBreiteMinDezString + 
									bOstWest.getText() + " " + ZielLaengeGradString + " Grad " + ZielLaengeMinDezString + "' ");
							
							
							//Service-Intent starten
							startService(meinServiceIntent);     
								
				       }//end if (NaviAnsageEin == true)
				
				
				
	    		loEinstellungen.setVisibility(View.GONE);
    			loNavigation.setVisibility(View.VISIBLE);
    			loNaviAnzeige.setVisibility(View.VISIBLE);
    			layoutConfig = 0;
	    		break;
	    	case(21): //Info1
	    		loInfo1.setVisibility(View.GONE);
    			loInfo2.setVisibility(View.VISIBLE);
    			layoutConfig = 22;
	    		break;
	    	case(22): //Info2
	    		loInfo2.setVisibility(View.GONE);
    			loNavigation.setVisibility(View.VISIBLE);
    			loNaviAnzeige.setVisibility(View.VISIBLE);
    			layoutConfig = 0;
	    		break;
	    	//case(23): //Info3
	    		//loInfo3.setVisibility(View.GONE);
    			//loNavigation.setVisibility(View.VISIBLE);
    			//layoutConfig = 0;
	    		//break;
	    	}//end switch(layoutConfig)
	    	        
	    }//end if ((keyCode == KeyEvent.KEYCODE_BACK)) 
		return false;
	  }//end public boolean onKeyDown(int keyCode, KeyEvent event)

	
	
	  
	  
}// ********************* end public class gcbActivity ****************************

