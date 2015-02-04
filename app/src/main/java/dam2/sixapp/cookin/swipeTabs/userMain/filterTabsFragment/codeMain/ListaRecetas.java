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

    TextView textViewDura, textViewDifi, textViewZo, textViewAli;
    private ListView list;
    String duraLink, difiLink, zonaLink, aliLink, tipoAliLink, dura2, difi2, zona2, ali2, tipoAli2, url, nombre;
    private String[] recetas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lista_recetas);

        Bundle b = getIntent().getExtras();
        duraLink = b.getString("duracionLink");
        difiLink = b.getString("dificultadLink");
        zonaLink = b.getString("zonaLink");
        aliLink = b.getString("alimentoLink");
        tipoAliLink="";


        list = (ListView)findViewById(R.id.list);

        url = ("http://cookin.hol.es/android_connect/filtros_avanzados.php?"+tipoAliLink+"&"+duraLink+"&"+difiLink+"&"+zonaLink);
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


            }else{

                Toast.makeText(getApplicationContext(),"Error de la Base de Datos", Toast.LENGTH_SHORT).show();

            }
        }

    }
}