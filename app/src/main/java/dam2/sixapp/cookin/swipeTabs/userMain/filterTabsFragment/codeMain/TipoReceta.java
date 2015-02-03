package dam2.sixapp.cookin.swipeTabs.userMain.filterTabsFragment.codeMain;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import dam2.sixapp.cookin.R;


public class TipoReceta extends Activity {//categorias

    TextView txt1;
    String alimento;
    Spinner spAlimentos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_receta);

        Bundle b = getIntent().getExtras();
        alimento = b.getString("elemento");

        txt1=(TextView)findViewById(R.id.textViewTipoRecetas);

        spAlimentos=(Spinner)findViewById(R.id.spinnerTipoAlimentos);

        ArrayAdapter<CharSequence> adaptadorAlimentos;

//-------------------Expandable ListView - OnClickListener---------------------------------------------------------------------------

        switch(alimento){

            case "Carnes":

                txt1.setText("Elegir tipo carne:");
                adaptadorAlimentos = ArrayAdapter.createFromResource(this,R.array.carnes, android.R.layout.simple_spinner_item);
                adaptadorAlimentos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spAlimentos.setAdapter(adaptadorAlimentos);
                break;

            case "Pescados":

                txt1.setText("Elegir tipo pescado:");
                adaptadorAlimentos = ArrayAdapter.createFromResource(this,R.array.pescados, android.R.layout.simple_spinner_item);
                adaptadorAlimentos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spAlimentos.setAdapter(adaptadorAlimentos);
                break;

            case "Verduras":

                txt1.setText("Elegir tipo verdura:");
                adaptadorAlimentos = ArrayAdapter.createFromResource(this,R.array.verduras, android.R.layout.simple_spinner_item);
                adaptadorAlimentos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spAlimentos.setAdapter(adaptadorAlimentos);
                break;

            case "Pasta":

                txt1.setText("Elegir tipo pasta:");
                adaptadorAlimentos = ArrayAdapter.createFromResource(this,R.array.pasta, android.R.layout.simple_spinner_item);
                adaptadorAlimentos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spAlimentos.setAdapter(adaptadorAlimentos);
                break;

            case "Ensaladas":

                txt1.setText("Elegir tipo ensalda:");
                adaptadorAlimentos = ArrayAdapter.createFromResource(this,R.array.ensaladas, android.R.layout.simple_spinner_item);
                adaptadorAlimentos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spAlimentos.setAdapter(adaptadorAlimentos);
                break;

            case "Postres":

                txt1.setText("Elegir tipo postre:");
                adaptadorAlimentos = ArrayAdapter.createFromResource(this,R.array.postres, android.R.layout.simple_spinner_item);
                adaptadorAlimentos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spAlimentos.setAdapter(adaptadorAlimentos);
                break;

            default:

                txt1.setText("Filtro aplicado: "+alimento);
                spAlimentos.setEnabled(false);

        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tipo_receta, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        /*if (id == R.id.) {
            return true;
        }*/

        return false;
    }
}
