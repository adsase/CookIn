package dam2.sixapp.cookin.swipeTabs.userMain.filterTabsFragment.codeMain;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import dam2.sixapp.cookin.R;


public class ListaRecetas extends Activity {//avanzadas

    TextView textViewDura, textViewDifi, textViewZo, textViewAli, textViewTipoAli;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_lista_recetas);

        Bundle b = getIntent().getExtras();
        String mismo1 = b.getString("duracion");

        textViewDura=(TextView)findViewById(R.id.textViewDura);
        textViewDura.setText(mismo1);

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
}
