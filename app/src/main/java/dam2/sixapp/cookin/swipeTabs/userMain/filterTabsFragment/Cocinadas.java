package dam2.sixapp.cookin.swipeTabs.userMain.filterTabsFragment;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import dam2.sixapp.cookin.R;
import dam2.sixapp.cookin.customList.CustomListAdapter;
import dam2.sixapp.cookin.customList.ListaPersonalizada;
import dam2.sixapp.cookin.customList.NewsItem;
import dam2.sixapp.cookin.recipes.recipeModeSelector;

/**
 * A simple {@link android.app.Fragment} subclass.
 *
 */
public class Cocinadas extends Fragment implements AdapterView.OnItemClickListener{

    ArrayList<NewsItem> item = new ArrayList<NewsItem>();
    ListView lv2;
    int id;
    private int[] arrayid;

    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_cocinadas, container, false);


        lv2 = (ListView) rootView.findViewById(R.id.custom_list_Cocinadas);
        lv2.setOnItemClickListener(this);

        mostrar m = new mostrar();
        m.execute();

        return rootView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        float x= lv2.getItemIdAtPosition(position);
        int index =Math.round(x);
        int idrec=arrayid[index];

        //Toast.makeText(getApplicationContext(),"ID: "+idrec,Toast.LENGTH_SHORT).show();

        Intent i = new Intent(getActivity().getApplicationContext(),recipeModeSelector.class);

        i.putExtra("id", idrec);

        startActivity(i);
    }

    private class mostrar extends AsyncTask<String, Integer, Boolean> {

        @Override
        protected Boolean doInBackground(String... params) {
            boolean resul = true;

            HttpClient httpClient = new DefaultHttpClient();
            HttpPost del = new HttpPost("http://cookin.hol.es/android_connect/masacabadas.php");

            del.setHeader("content-type", "application/json");

            try {
                HttpResponse resp = httpClient.execute(del);
                String respStr = EntityUtils.toString(resp.getEntity());
                JSONArray respJSON = new JSONArray(respStr);
                arrayid = new int[respJSON.length()];
                item.clear();


                for (int i = 0; i < 1; i++) {
                    JSONObject obj = respJSON.getJSONObject(i);

                    item.add(new NewsItem(obj.getString("NOMBRE"), obj.getString("DIFICULTAD"), obj.getString("IMAGEN")));
                    id = obj.getInt("IDRECETAS");
                    arrayid[i] = id;
                }
            } catch (Exception ex) {
                Log.e("ServicioRest", "Error!", ex);
                resul = false;
            }

            return resul;

        }

        public void onPostExecute(Boolean result){
        if(result){
            lv2.setAdapter(new ListaPersonalizada(getActivity().getApplicationContext(),R.layout.list_row_layout,item) {
                @Override
                public void onEntrada(Object entrada, View view) {

                    if (entrada != null) {

                        TextView titulo = (TextView) view.findViewById(R.id.title);
                        if (titulo != null) {
                            titulo.setText(((NewsItem) entrada).getTitle());
                        }


                           ImageView img = (ImageView) view.findViewById(R.id.image);

                       if(img != null){
                            Picasso.with(getActivity().getApplicationContext()).load(((NewsItem) entrada).getImage()).into(img);

                        }

                       /* TextView desc = (TextView) view.findViewById(R.id.textViewDescription);
                        if (desc != null) {
                            desc.setText(((NewsItem) entrada).getDesc());
                        }*/
                    }//Fin IF
                }
            });
        }
    }
    }
}