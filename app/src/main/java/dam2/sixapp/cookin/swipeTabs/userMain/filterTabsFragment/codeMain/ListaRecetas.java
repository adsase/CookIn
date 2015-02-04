package dam2.sixapp.cookin.swipeTabs.userMain.filterTabsFragment.codeMain;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import dam2.sixapp.cookin.R;


public class ListaRecetas extends Activity {//avanzadas

    //TextView textViewDura, textViewDifi, textViewZo, textViewAli;
    private ListView list;
    String dura, difi, zona, ali, tipoAli, url, nombre;
    private String[] recetas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lista_recetas);

        Bundle b = getIntent().getExtras();
        dura = b.getString("duracion");
        difi = b.getString("dificultad");
        zona = b.getString("zona");
        ali = b.getString("alimento");
        tipoAli = b.getString("tipoAlimento");

        /*textViewDura=(TextView)findViewById(R.id.textViewDura);
        textViewDura.setText("~ "+dura+" mins");

        textViewDifi=(TextView)findViewById(R.id.textViewDifi);
        textViewDifi.setText(difi);

        textViewZo=(TextView)findViewById(R.id.textViewZo);
        textViewZo.setText(zona);

        textViewAli=(TextView)findViewById(R.id.textViewAli);
        textViewAli.setText(ali);*/

        list = (ListView)findViewById(R.id.list);


        /*textViewTipoAli=(TextView)findViewById(R.id.textViewTipoAli);
        textViewTipoAli.setText(tipoAli);*/

        url = ("http://cookin.hol.es/android_connect/filtros_avanzados.php?"+ali+"&"+dura+"&"+difi+"&"+zona);
        //Toast.makeText(getApplicationContext(), url, Toast.LENGTH_LONG).show();

        mostrar m = new mostrar();
        m.execute();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_lista_recetas, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

       //int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return false;
    }

    private class mostrar extends AsyncTask<String, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            boolean resul = true;

            HttpClient httpClient = new DefaultHttpClient();
            HttpPost del = new HttpPost(url);

            del.setHeader("content-type", "application/json");

            try {
                HttpResponse resp = httpClient.execute(del);
                String respStr = EntityUtils.toString(resp.getEntity());
                JSONArray respJSON = new JSONArray(respStr);
                recetas = new String[respJSON.length()];

                for (int i = 0; i < respJSON.length(); i++) {
                    JSONObject obj = respJSON.getJSONObject(i);


                    nombre = obj.getString("NOMBRE");

                    recetas[i] = "" + nombre;
                }
            } catch (Exception ex) {
                Log.e("ServicioRest", "Error!", ex);
                resul = false;
            }

            return resul;

        }


        protected void onPostExecute(Boolean result) {

            if (result) {
                //Rellenamos la lista con los nombres de las recetas
                //Rellenamos la lista con los resultados
                ArrayAdapter<String> adaptador =
                        new ArrayAdapter<String>(ListaRecetas.this,
                                android.R.layout.simple_list_item_1, recetas);


                list.setAdapter(adaptador);


            }
        }

    }
}


