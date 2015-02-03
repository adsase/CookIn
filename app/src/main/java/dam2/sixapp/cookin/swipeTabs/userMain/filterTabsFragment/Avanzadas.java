package dam2.sixapp.cookin.swipeTabs.userMain.filterTabsFragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import dam2.sixapp.cookin.swipeTabs.userMain.filterTabsFragment.codeMain.ListaRecetas;
import dam2.sixapp.cookin.R;

public class Avanzadas extends android.support.v4.app.Fragment implements AdapterView.OnItemSelectedListener, View.OnClickListener {

    Spinner spDuracionAvanzadas,spDificultadAvanzadas,spZonaAvanzadas,spAlimentoAvanzadas,spTipoAlimentoAvanzadas;
    ArrayAdapter<CharSequence> adaptadorAlimentosAvanzada;
    Button bntAvanzadas;
    String uno,dos,tres,cuatro,cinco;


    public Avanzadas(){}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState){

        View rootView = inflater.inflate(R.layout.fragment_avanzadas, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        bntAvanzadas=(Button)getView().findViewById(R.id.bntAvanzadas);
        bntAvanzadas.setOnClickListener(this);

        //----------------------------Spinners Categorias Avanzadas-------------------------------------------------------------------------

        //Por Duracion
        spDuracionAvanzadas=(Spinner)getView().findViewById(R.id.spDuracionAvanzadas);
        adaptadorAlimentosAvanzada = ArrayAdapter.createFromResource(getActivity(),R.array.duracion, android.R.layout.simple_spinner_item);
        adaptadorAlimentosAvanzada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDuracionAvanzadas.setAdapter(adaptadorAlimentosAvanzada);
        spDuracionAvanzadas.setOnItemSelectedListener(this);

        //Por Dificultad
        spDificultadAvanzadas=(Spinner)getView().findViewById(R.id.spDificultadAvanzadas);
        adaptadorAlimentosAvanzada = ArrayAdapter.createFromResource(getActivity(),R.array.dificultad, android.R.layout.simple_spinner_item);
        adaptadorAlimentosAvanzada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDificultadAvanzadas.setAdapter(adaptadorAlimentosAvanzada);
        spDificultadAvanzadas.setOnItemSelectedListener(this);

        //Por Zona Geografica
        spZonaAvanzadas=(Spinner)getView().findViewById(R.id.spZonaAvanzadas);
        adaptadorAlimentosAvanzada = ArrayAdapter.createFromResource(getActivity(),R.array.zona, android.R.layout.simple_spinner_item);
        adaptadorAlimentosAvanzada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spZonaAvanzadas.setAdapter(adaptadorAlimentosAvanzada);
        spZonaAvanzadas.setOnItemSelectedListener(this);

        //Por Alimento
        spAlimentoAvanzadas=(Spinner)getView().findViewById(R.id.spAlimentoAvanzadas);
        adaptadorAlimentosAvanzada = ArrayAdapter.createFromResource(getActivity(),R.array.alimento, android.R.layout.simple_spinner_item);
        adaptadorAlimentosAvanzada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spAlimentoAvanzadas.setAdapter(adaptadorAlimentosAvanzada);
        spAlimentoAvanzadas.setOnItemSelectedListener(this);

        spTipoAlimentoAvanzadas=(Spinner)getView().findViewById(R.id.spTipoAlimentoAvanzadas);

    }//Fin metodo onActivityCreated

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        uno = spDuracionAvanzadas.getItemAtPosition(position).toString();


        //---------------------------------Switch de los tipos de alimentos------------------------------------------------------------------------
        switch (spAlimentoAvanzadas.getItemAtPosition(position).toString()) {

            case ".Sin filtro":

                spTipoAlimentoAvanzadas.setEnabled(false);
                //Toast.makeText(getActivity(), "Sale", Toast.LENGTH_SHORT).show();
                break;

            case "Carnes":

                adaptadorAlimentosAvanzada = ArrayAdapter.createFromResource(getActivity(),R.array.carnes, android.R.layout.simple_spinner_item);
                adaptadorAlimentosAvanzada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spTipoAlimentoAvanzadas.setAdapter(adaptadorAlimentosAvanzada);
                spTipoAlimentoAvanzadas.setEnabled(true);
                //Toast.makeText(getActivity(),"Seleccione un tipo de carne",Toast.LENGTH_SHORT).show();

                break;

            case "Pescados":

                adaptadorAlimentosAvanzada = ArrayAdapter.createFromResource(getActivity(),R.array.pescados, android.R.layout.simple_spinner_item);
                adaptadorAlimentosAvanzada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spTipoAlimentoAvanzadas.setAdapter(adaptadorAlimentosAvanzada);
                spTipoAlimentoAvanzadas.setEnabled(true);
                //Toast.makeText(getActivity(),"Seleccione un tipo de pescado",Toast.LENGTH_SHORT).show();

                break;

            case "Verduras":

                adaptadorAlimentosAvanzada = ArrayAdapter.createFromResource(getActivity(),R.array.verduras, android.R.layout.simple_spinner_item);
                adaptadorAlimentosAvanzada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spTipoAlimentoAvanzadas.setAdapter(adaptadorAlimentosAvanzada);
                spTipoAlimentoAvanzadas.setEnabled(true);
                //Toast.makeText(getActivity(),"Seleccione una verdura",Toast.LENGTH_SHORT).show();

                break;

            case "Pasta":

                adaptadorAlimentosAvanzada = ArrayAdapter.createFromResource(getActivity(),R.array.pasta, android.R.layout.simple_spinner_item);
                adaptadorAlimentosAvanzada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spTipoAlimentoAvanzadas.setAdapter(adaptadorAlimentosAvanzada);
                spTipoAlimentoAvanzadas.setEnabled(true);
                //Toast.makeText(getActivity(),"Seleccione un tipo de pasta",Toast.LENGTH_SHORT).show();

                break;

            case "Ensaladas":

                adaptadorAlimentosAvanzada = ArrayAdapter.createFromResource(getActivity(),R.array.ensaladas, android.R.layout.simple_spinner_item);
                adaptadorAlimentosAvanzada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spTipoAlimentoAvanzadas.setAdapter(adaptadorAlimentosAvanzada);
                spTipoAlimentoAvanzadas.setEnabled(true);
                //Toast.makeText(getActivity(),"Seleccione un tipo de ensalada",Toast.LENGTH_SHORT).show();

                break;

            case "Postres":

                adaptadorAlimentosAvanzada = ArrayAdapter.createFromResource(getActivity(),R.array.postres, android.R.layout.simple_spinner_item);
                adaptadorAlimentosAvanzada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spTipoAlimentoAvanzadas.setAdapter(adaptadorAlimentosAvanzada);
                spTipoAlimentoAvanzadas.setEnabled(true);
                //Toast.makeText(getActivity(),"Seleccione un postre",Toast.LENGTH_SHORT).show();

                break;
        }//Fin switch
    }//Fin metodo onItemSelected


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.bntAvanzadas){

            //Toast.makeText(getActivity(),"Pasara a la activity de la lista de recetas",Toast.LENGTH_SHORT).show();

            Intent i = new Intent(getActivity(), ListaRecetas.class);
            i.putExtra("duracion",uno.toString());
            startActivity(i);


        }

    }//Fin metodo onClick
}//Fin Clase