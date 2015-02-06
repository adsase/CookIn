package dam2.sixapp.cookin.recipes.activitySteps;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import dam2.sixapp.cookin.R;

public class StepsActivity extends ActionBarActivity implements View.OnClickListener,OnInitListener {

    private TextView nombreReceta, pasoReceta;
    private Button backButton, pauseButton, nextButton;
    int actualStep = 0;
    String[] steps;
    int[] recipeNum;
    String con="http://cookin.hol.es/android_connect/";
    String web="pasos.php?id=";
    static int id;
    String pasos;
    static String recipeName;
    int numpaso;
    boolean changed = false;
    ArrayList<String> suggestedWords;

    //voice recognition and general variables

    //variable for checking Voice Recognition support on user device
    private static final int VR_REQUEST = 999;

    //Log tag for output information
    private final String LOG_TAG = "CookIn";//***enter your own tag here***

    //TTS variables

    //variable for checking TTS engine data on user device
    private int MY_DATA_CHECK_CODE = 0;

    //Text To Speech instance
    private TextToSpeech repeatTTS;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps_assistant);

        nombreReceta = (TextView) findViewById(R.id.textView14);
        pasoReceta = (TextView) findViewById(R.id.textView16);
        backButton = (Button) findViewById(R.id.button3);
        pauseButton = (Button) findViewById(R.id.button4);
        nextButton = (Button) findViewById(R.id.button5);
        pauseButton.setText(getResources().getString(R.string.start));

        /** Listeners **/
        backButton.setOnClickListener(this);
        pauseButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        /** Listeners **/

        Bundle b=getIntent().getExtras();
        id=b.getInt("id");
        recipeName=b.getString("recipeName");
        mostrar tarea=new mostrar();
        tarea.execute();

        nombreReceta.setText(recipeName);

        //find out whether speech recognition is supported
        PackageManager packManager = getPackageManager();
        List<ResolveInfo> intActivities = packManager.queryIntentActivities(new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
        if (intActivities.size() != 0) {
            //speech recognition is supported - detect user button clicks
            //speechBtn.setOnClickListener(this);
            //prepare the TTS to repeat chosen words
            Intent checkTTSIntent = new Intent();
            //check TTS data
            checkTTSIntent.setAction(TextToSpeech.Engine.ACTION_CHECK_TTS_DATA);
            //start the checking Intent - will retrieve result in onActivityResult
            startActivityForResult(checkTTSIntent, MY_DATA_CHECK_CODE);
        }
        else
        {
            //speech recognition not supported, disable button and output message
            //speechBtn.setEnabled(false);
            Toast.makeText(this, "Oops - Speech recognition not supported!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onInit(int initStatus) {
        Locale loc = new Locale("es", "","");
        if (initStatus == TextToSpeech.SUCCESS)
            repeatTTS.setLanguage(loc);
    }

    /** ASYNC TASK **/

    private class mostrar extends AsyncTask<String,Integer,Boolean> {

        protected Boolean doInBackground(String... params) {

            boolean resul = true;

            HttpClient httpClient = new DefaultHttpClient();
            HttpPost del = new HttpPost(con+web+id);
            del.setHeader("content-type", "application/json");

            try
            {
                HttpResponse resp = httpClient.execute(del);
                String respStr = EntityUtils.toString(resp.getEntity());
                JSONArray respJSON = new JSONArray(respStr);
                steps = new String[respJSON.length()];
                recipeNum = new int[respJSON.length()];
                for(int i=0; i<respJSON.length(); i++)
                {
                    JSONObject obj = respJSON.getJSONObject(i);
                    pasos= obj.getString("TEXTO");
                    numpaso=obj.getInt("POSICION");
                    steps[i]=pasos;
                    recipeNum[i]=numpaso;

                    //pasos2=pasos2+""+numpaso+"\n"+steps[i]+"\n";
                    //pasos2=steps[i];
                    //Log.e("pasos",pasos2);
                }
            }
            catch(Exception ex)
            {
                Log.e("ServicioRest","Error!", ex);
                resul = false;
            }
            return resul;

        }

        protected void onPostExecute(Boolean result) {

            if (result)
            {
                pasoReceta.setText(recipeNum[actualStep] + ". " + steps[actualStep]);
            }
        }
    }

    @Override
    public void onClick(View v) {
        //int size = steps.length;
        //int size2 = recipeNum.length;
        switch(v.getId()){
            case R.id.button3:
                if(actualStep>0) {
                    if(pauseButton.getText().toString().compareTo(getResources().getString(R.string.stop).toString())==0) {
                        actualStep--;
                        pasoReceta.setText(recipeNum[actualStep] + ". " + steps[actualStep]);
                        changed = true;
                        repeatTTS.speak(steps[actualStep], TextToSpeech.QUEUE_FLUSH, null);
                        if (pauseButton.getText().toString().compareTo(getResources().getString(R.string.start).toString()) == 0) {
                            pauseButton.setText(getResources().getString(R.string.stop).toString());
                        }
                    }
                }
                break;
            case R.id.button4:
                //FALTA
                //Log.e("asdsa",pauseButton.getText().toString()+" / "+getResources().getString(R.string.start).toString());
                //repeatTTS.speak(recipeNum[actualStep] + " " + steps[actualStep],TextToSpeech.QUEUE_FLUSH,null);
                if(pauseButton.getText().toString().compareTo(getResources().getString(R.string.stop).toString())==0){
                    pauseButton.setText(getResources().getString(R.string.start).toString());
                    repeatTTS.stop();
                }else if(pauseButton.getText().toString().compareTo(getResources().getString(R.string.start).toString())==0) {
                        pauseButton.setText(R.string.stop);
                        repeatTTS.speak(steps[actualStep], TextToSpeech.QUEUE_FLUSH, null);
                }

                break;
            case R.id.button5:
                if(actualStep<steps.length-1) {
                    if(pauseButton.getText().toString().compareTo(getResources().getString(R.string.stop).toString())==0) {
                        actualStep++;
                        pasoReceta.setText(recipeNum[actualStep] + ". " + steps[actualStep]);
                        changed = true;
                        repeatTTS.speak(steps[actualStep], TextToSpeech.QUEUE_FLUSH, null);
                        if (pauseButton.getText().toString().compareTo(getResources().getString(R.string.start).toString()) == 0) {
                            pauseButton.setText(R.string.stop);
                        }
                    }
                }
                break;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //check speech recognition result
        if (requestCode == VR_REQUEST && resultCode == RESULT_OK)
        {
            //store the returned word list as an ArrayList
            /** palabras recogidas por el reconocimiento **/
            suggestedWords = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            //set the retrieved list to display in the ListView using an ArrayAdapter
            //wordList.setAdapter(new ArrayAdapter<String> (this, R.layout.word, suggestedWords));

        }

        //tss code here

        //returned from TTS data check
        if (requestCode == MY_DATA_CHECK_CODE)
        {
            //we have the data - create a TTS instance
            if (resultCode == TextToSpeech.Engine.CHECK_VOICE_DATA_PASS)
                repeatTTS = new TextToSpeech(this, this);
                //data not installed, prompt the user to install it
            else
            {
                //intent will take user to TTS download page in Google Play
                Intent installTTSIntent = new Intent();
                installTTSIntent.setAction(TextToSpeech.Engine.ACTION_INSTALL_TTS_DATA);
                startActivity(installTTSIntent);
            }
        }

        //call superclass method
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void listenToSpeech() {

        //start the speech recognition intent passing required data
        Intent listenIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        //indicate package
        listenIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, getClass().getPackage().getName());
        //message to display while listening
        listenIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Di: Atras, Empezar o Adelante");
        //set speech model
        listenIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        //specify number of results to retrieve
        listenIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 10);

        //start listening
        startActivityForResult(listenIntent, VR_REQUEST);
    }
}
