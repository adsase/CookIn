package dam2.sixapp.cookin.collaborate;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


import dam2.sixapp.cookin.R;
import dam2.sixapp.cookin.database.remote.JSONParser;
import dam2.sixapp.cookin.drawer.NavigationDrawerFragment;
import dam2.sixapp.cookin.recipes.readMode;


public class Collaborate extends ActionBarActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks, AdapterView.OnClickListener {

    EditText nom, dur, dif, ing, zon, cat, desc;
    Button enviar;
    // Dialogo de progreso
    private ProgressDialog pDialog;

    JSONParser jsonParser = new JSONParser();
    InputStream is = null;
    String result = null;
    String line = null;
    int code;

    // URL al servicio de creacion de usuario
    private static String url_create_product = "http://cookin.hol.es/android_connect/insertarrecetas.php";

    // JSON Node names
    private static final String TAG_SUCCESS = "success";

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.colabora_layout);

        nom = (EditText) findViewById(R.id.editN);
        dur = (EditText) findViewById(R.id.editDur);
        dif = (EditText) findViewById(R.id.editDif);
        ing = (EditText) findViewById(R.id.editIng);
        zon = (EditText) findViewById(R.id.editZon);
        cat = (EditText) findViewById(R.id.editCat);
        desc = (EditText) findViewById(R.id.editDesc);

        enviar = (Button) findViewById(R.id.button2);
        enviar.setOnClickListener(this);

    }

    @Override
    public void onNavigationDrawerItemSelected(int position) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

       Insertar inser=new Insertar(Collaborate.this);
        inser.execute();
    }




    public class Insertar extends AsyncTask<String,String,String> {

        private Activity context;
        readMode main=new readMode();
        int id;

        Insertar(Activity context){

            this.context=context;
        }

        @Override
        protected String doInBackground(String... params) {
            if(Insertar()){
                context.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        Toast.makeText(context, "Receta Enviada", Toast.LENGTH_SHORT).show();

                    }
                });
            }else{
                context.runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        Toast.makeText(context, "Error al Enviar",Toast.LENGTH_SHORT).show();

                    }

                });
            }

            return null;
        }

        private boolean Insertar(){
            HttpClient cliente=new DefaultHttpClient();
            //Creo un List del tamaño de los parametros que le voy a pasar
            List<NameValuePair> nvp =new ArrayList<NameValuePair>();
            //Lanzamos la url de donde esta el php para insertar
            HttpPost post=new HttpPost("http://cookin.hol.es/android_connect/insertarrecetas.php");

            nvp.add(new BasicNameValuePair("nom",nom.getText().toString()));
            nvp.add(new BasicNameValuePair("ing",ing.getText().toString()));
            nvp.add(new BasicNameValuePair("dur",dur.getText().toString()));
            nvp.add(new BasicNameValuePair("dif",dif.getText().toString()));
            nvp.add(new BasicNameValuePair("zon",zon.getText().toString()));
            nvp.add(new BasicNameValuePair("cat",cat.getText().toString()));
            nvp.add(new BasicNameValuePair("desc",desc.getText().toString()));


            try {
                post.setEntity(new UrlEncodedFormEntity(nvp));
                cliente.execute(post);
                return true;
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }

        //Toast.makeText(getApplicationContext(), nom.getText().toString(), Toast.LENGTH_LONG).show();

    }
}



