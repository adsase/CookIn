package dam2.sixapp.cookin.recipes.activitySteps;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import dam2.sixapp.cookin.R;

public class StepsActivity extends ActionBarActivity implements View.OnClickListener {

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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps_assistant);

        nombreReceta = (TextView) findViewById(R.id.textView14);
        pasoReceta = (TextView) findViewById(R.id.textView16);
        backButton = (Button) findViewById(R.id.button3);
        pauseButton = (Button) findViewById(R.id.button4);
        nextButton = (Button) findViewById(R.id.button5);

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
    }

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
                pasoReceta.setText(recipeNum[actualStep]+". "+steps[actualStep]);
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
                    actualStep--;
                    pasoReceta.setText(recipeNum[actualStep] + ". " + steps[actualStep]);
                }
                break;
            case R.id.button4:
                //FALTA
                break;
            case R.id.button5:
                if(actualStep<steps.length-1) {
                    actualStep++;
                    pasoReceta.setText(recipeNum[actualStep] + ". " + steps[actualStep]);
                }
                break;
        }
    }
}
