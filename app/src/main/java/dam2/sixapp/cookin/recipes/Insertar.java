package dam2.sixapp.cookin.recipes;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import android.app.Activity;
import android.os.AsyncTask;
import android.widget.Toast;

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
                    Toast.makeText(context, "Valoracion Enviada",Toast.LENGTH_SHORT).show();

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
        List<NameValuePair> nvp =new ArrayList<NameValuePair>(2);
        //Lanzamos la url de donde esta el php para insertar
        HttpPost post=new HttpPost("http://cookin.hol.es/android_connect/valorar.php");

        nvp.add(new BasicNameValuePair("val",""+readMode.getVotos()));
        nvp.add(new BasicNameValuePair("id",""+readMode.getId()));

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
}
