package dam2.sixapp.cookin.swipeTabs.userMain.filterTabsFragment.codeMain;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import dam2.sixapp.cookin.R;
import dam2.sixapp.cookin.recipes.recipeModeSelector;


public class TipoReceta extends Activity implements AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {

    private ListView list;
    Spinner spAlimentos;
    TextView txt1;

    String nombre,imageURL;
    int id;
    private String[] recetas,recetasURL;
    private int[] arrayid;


    String filtro;
    String conex = "http://cookin.hol.es/android_connect/";
    String php;
    String consulta ="", guardaconsulta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_receta);



        Bundle b = getIntent().getExtras();
        filtro = b.getString("elemento"); /** ERROR -- Abre recipeModeSelector y al darle a la flecha no devuelve ningun Bundle**/

        txt1=(TextView)findViewById(R.id.textViewTipoRecetas);
        list=(ListView)findViewById(R.id.lista);
        spAlimentos=(Spinner)findViewById(R.id.spinnerTipoAlimentos);
        spAlimentos.setOnItemSelectedListener(this);
        list.setOnItemClickListener(this);
        txt1.setText("Filtrado por: "+filtro);


        ArrayAdapter<CharSequence> adaptadorAlimentos;
        ArrayList<String> pruebas = new ArrayList<String>();

        switch(filtro){

            case "20 mins":
                php = "filtro_menor15.php";
                break;

            case "30 mins":
                php = "filtro_menor30.php";
                break;

            case "60 mins":
                php = "filtro_menor60.php";
                break;

            case "90 mins":
                php = "filtro_mayor60.php";
                break;

            case "120 mins":
                php = "filtro90.php";
                break;


            case "Fácil":
                php = "filtro_facil.php";
                break;

            case "Media":
                php = "filtro_media.php";
                break;

            case "Difícil":
                php = "filtro_dificil.php";
                break;

            case "Europa Este":
                php = "filtro_europaeste.php";
                break;

            case "Oriental":
                php = "filtro_oriental.php";
                break;

            case "Latina":
                php = "filtro_sudamerica.php";
                break;

            case "Mediterránea":
                php = "filtro_mediterranea.php";
                break;

            case "Africana":
                php = "filtro_africa.php";
                break;

            case "Otros":
                php = "filtro_otraszonas.php";
                break;

            case "Carnes":

                txt1.setText("Elegir tipo carne:");
                adaptadorAlimentos = ArrayAdapter.createFromResource(this,R.array.carnes, android.R.layout.simple_spinner_item);
                adaptadorAlimentos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spAlimentos.setAdapter(adaptadorAlimentos);
                php = "filtro_categoria.php";
                consulta = "?id='Carne'";
                break;

            case "Pescados":

                txt1.setText("Elegir tipo pescado:");
                adaptadorAlimentos = ArrayAdapter.createFromResource(this,R.array.pescados, android.R.layout.simple_spinner_item);
                adaptadorAlimentos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spAlimentos.setAdapter(adaptadorAlimentos);
                php = "filtro_categoria.php";
                consulta = "?id='Pescado'";
                break;

            case "Verduras":

                txt1.setText("Elegir tipo verdura:");
                adaptadorAlimentos = ArrayAdapter.createFromResource(this,R.array.verduras, android.R.layout.simple_spinner_item);
                adaptadorAlimentos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spAlimentos.setAdapter(adaptadorAlimentos);
                php = "filtro_categoria.php";
                consulta = "?id='Vegetal'";
                break;

            case "Pastas":

                txt1.setText("Elegir tipo pasta:");
                adaptadorAlimentos = ArrayAdapter.createFromResource(this,R.array.pasta, android.R.layout.simple_spinner_item);
                adaptadorAlimentos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spAlimentos.setAdapter(adaptadorAlimentos);
                php = "filtro_categoria.php";
                consulta = "?id='Pasta'";
                break;

            case "Postres":

                txt1.setText("Elegir tipo postre:");
                adaptadorAlimentos = ArrayAdapter.createFromResource(this,R.array.postres, android.R.layout.simple_spinner_item);
                adaptadorAlimentos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spAlimentos.setAdapter(adaptadorAlimentos);
                php = "filtro_categoria.php";
                consulta = "?id='Postre'";
                break;

            default:

                //txt1.setText("Filtro aplicado: "+filtro);
                spAlimentos.setActivated(false);

        }

        guardaconsulta = consulta;
        mostrar tarea = new mostrar();
        tarea.execute();

        //Toast.makeText(getApplicationContext(), php+consulta, Toast.LENGTH_LONG).show();



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tipo_receta, menu);
        MenuItem searchItem= menu.getItem(0);

        return false;
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
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        if(!spAlimentos.getSelectedItem().toString().equals("Todos")){
            php = ("filtro_ingredientes.php?id='" + parent.getItemAtPosition(position).toString() + "'");
            consulta = "";
            //Toast.makeText(getApplicationContext(),"El link es "+php,Toast.LENGTH_LONG).show();
        }else{
            php = ("filtro_categoria.php");
            consulta = guardaconsulta;
        }
        mostrar tarea = new mostrar();
        tarea.execute();


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

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
            HttpPost del = new HttpPost(conex+php+consulta);

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
                        new ArrayAdapter<String>(TipoReceta.this,
                                android.R.layout.simple_list_item_1, recetas);
/** FALTA LISTA PERSONALIZADA PARA MOSTRAR IMAGEN Y NOMBRE DE RECETA **/

                list.setAdapter(adaptador);


            }else{
            Toast.makeText(getApplicationContext(), "Error en la búsqueda", Toast.LENGTH_LONG).show();
        }
        }

    }
}
