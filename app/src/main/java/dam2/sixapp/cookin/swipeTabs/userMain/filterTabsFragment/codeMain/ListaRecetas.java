package dam2.sixapp.cookin.swipeTabs.userMain.filterTabsFragment.codeMain;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import dam2.sixapp.cookin.R;
import dam2.sixapp.cookin.recipes.recipeModeSelector;

//Activity que viene del fragment Avanzadas.
public class ListaRecetas extends ActionBarActivity implements AdapterView.OnItemClickListener{//avanzadas

    private ListView list;
    String duraLink, difiLink, zonaLink, aliLink, tipoAliLink, dura2, difi2, zona2, ali2, tipoAli2, url, nombre, imageURL;
    private String[] recetas, recetasURL;
    int id;
    private int[] arrayid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        //actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Búsqueda receta");

        setContentView(R.layout.activity_lista_recetas);

        Bundle b = getIntent().getExtras();
        duraLink = b.getString("duracionLink");
        difiLink = b.getString("dificultadLink");
        zonaLink = b.getString("zonaLink");
        aliLink = b.getString("alimentoLink");
        tipoAliLink=b.getString("tipoalimentoLink");


        list = (ListView)findViewById(R.id.list);
        list.setOnItemClickListener(this);

        url = ("http://cookin.hol.es/android_connect/filtros_avanzados.php?"+aliLink+"&"+duraLink+"&"+difiLink+"&"+zonaLink+"&"+tipoAliLink);//
        //Toast.makeText(getApplicationContext(), url, Toast.LENGTH_LONG).show();

        mostrar m = new mostrar();
        m.execute();


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_lista_recetas, menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        float x= list.getItemIdAtPosition(position);
        int index =Math.round(x);
        int idrec=arrayid[index];

        //Toast.makeText(getApplicationContext(),"ID: "+idrec,Toast.LENGTH_SHORT).show();

        Intent i = new Intent(getApplicationContext(),recipeModeSelector.class);

        i.putExtra("id", idrec);

        startActivity(i);

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
                recetasURL = new String[respJSON.length()];
                arrayid = new int[respJSON.length()];

                for (int i = 0; i < respJSON.length(); i++) {
                    JSONObject obj = respJSON.getJSONObject(i);


                    nombre = obj.getString("NOMBRE");
                    imageURL = obj.getString("IMAGEN");
                    id = obj.getInt("IDRECETAS");

                    recetas[i] = "" + nombre;
                    recetasURL[i] = imageURL;
                    arrayid[i] = id;
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

                Toast.makeText(getApplicationContext(),"Error en la búsqueda", Toast.LENGTH_SHORT).show();

            }
        }

    }
}