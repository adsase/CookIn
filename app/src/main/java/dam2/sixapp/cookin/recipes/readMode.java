package dam2.sixapp.cookin.recipes;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import dam2.sixapp.cookin.R;


public class readMode extends ActionBarActivity implements View.OnClickListener{

    ActionBar ab;
    TextView t1;
    Button val;
    static int id;
    RatingBar valorar;
    String pasos;
    String pasos2="";
    static float votos;
    String con="http://cookin.hol.es/android_connect/";
    String web="pasos.php?id=";
    String[] totpasos;
    int numpaso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.read_mode);

        val=(Button)findViewById(R.id.valor);
        val.setBackgroundColor(Color.parseColor("#FFBB33"));
        val.setOnClickListener(this);
        ab=getSupportActionBar();
        ab.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#FF8000")));
        t1=(TextView)findViewById(R.id.textView1);
        valorar=(RatingBar)findViewById(R.id.ratingBar1);
        Bundle b=getIntent().getExtras();
        id=b.getInt("id");
        mostrar tarea=new mostrar();
        tarea.execute();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.read_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if(id==R.id.item1){
            String message = "Aqui usando Cookin "+"\n"+"Descargala aqui";
            Intent share = new Intent(Intent.ACTION_SEND);
            share.setType("text/plain");
            share.putExtra(Intent.EXTRA_TEXT, message);
            startActivity(Intent.createChooser(share, "Compartir Con..."));
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.valor){
            votos=valorar.getRating();
            Insertar inser=new Insertar(readMode.this);
            inser.execute();
        }

    }

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        readMode.id = id;
    }

    public static float getVotos() {
        return votos;
    }

    public void setVotos(float votos) {
        readMode.votos = votos;

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
                totpasos = new String[respJSON.length()];
                for(int i=0; i<respJSON.length(); i++)
                {
                    JSONObject obj = respJSON.getJSONObject(i);
                     pasos= obj.getString("TEXTO");
                    numpaso=obj.getInt("POSICION");
                    totpasos[i]=pasos;
                    pasos2=pasos2+""+numpaso+"\n"+totpasos[i]+"\n";
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
                t1.setText(pasos2);
            }
        }
    }


}
