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


        //---------------------------------Switch de los tipos de alimentos------------------------------------------------------------------------
        switch (spAlimentoAvanzadas.getSelectedItem().toString()) {

            case "Sin filtro":

                spTipoAlimentoAvanzadas.setEnabled(false);
                spTipoAlimentoAvanzadas.setVisibility(View.GONE);
                cinco = spAlimentoAvanzadas.getSelectedItem().toString();

                break;

            case "Carne":

                adaptadorAlimentosAvanzada = ArrayAdapter.createFromResource(getActivity(),R.array.carnes, android.R.layout.simple_spinner_item);
                adaptadorAlimentosAvanzada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spTipoAlimentoAvanzadas.setAdapter(adaptadorAlimentosAvanzada);
                spTipoAlimentoAvanzadas.setVisibility(View.VISIBLE);
                spTipoAlimentoAvanzadas.setEnabled(true);
                cinco = spAlimentoAvanzadas.getSelectedItem().toString();

                break;

            case "Pescado":

                adaptadorAlimentosAvanzada = ArrayAdapter.createFromResource(getActivity(),R.array.pescados, android.R.layout.simple_spinner_item);
                adaptadorAlimentosAvanzada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spTipoAlimentoAvanzadas.setAdapter(adaptadorAlimentosAvanzada);
                spTipoAlimentoAvanzadas.setVisibility(View.VISIBLE);
                spTipoAlimentoAvanzadas.setEnabled(true);
                cinco = spAlimentoAvanzadas.getSelectedItem().toString();

                break;

            case "Vegetal":

                adaptadorAlimentosAvanzada = ArrayAdapter.createFromResource(getActivity(),R.array.verduras, android.R.layout.simple_spinner_item);
                adaptadorAlimentosAvanzada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spTipoAlimentoAvanzadas.setAdapter(adaptadorAlimentosAvanzada);
                spTipoAlimentoAvanzadas.setVisibility(View.VISIBLE);
                spTipoAlimentoAvanzadas.setEnabled(true);
                cinco = spAlimentoAvanzadas.getSelectedItem().toString();

                break;

            case "Pasta":

                adaptadorAlimentosAvanzada = ArrayAdapter.createFromResource(getActivity(),R.array.pasta, android.R.layout.simple_spinner_item);
                adaptadorAlimentosAvanzada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spTipoAlimentoAvanzadas.setAdapter(adaptadorAlimentosAvanzada);
                spTipoAlimentoAvanzadas.setVisibility(View.VISIBLE);
                spTipoAlimentoAvanzadas.setEnabled(true);
                cinco = spAlimentoAvanzadas.getSelectedItem().toString();

                break;


            case "Postres":

                adaptadorAlimentosAvanzada = ArrayAdapter.createFromResource(getActivity(),R.array.postres, android.R.layout.simple_spinner_item);
                adaptadorAlimentosAvanzada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spTipoAlimentoAvanzadas.setAdapter(adaptadorAlimentosAvanzada);
                spTipoAlimentoAvanzadas.setVisibility(View.VISIBLE);
                spTipoAlimentoAvanzadas.setEnabled(true);
                cinco = spAlimentoAvanzadas.getSelectedItem().toString();

                break;
        }//Fin switch

        uno = spDuracionAvanzadas.getSelectedItem().toString();
        dos = spDificultadAvanzadas.getSelectedItem().toString();
        tres = spZonaAvanzadas.getSelectedItem().toString();
        cuatro = spAlimentoAvanzadas.getSelectedItem().toString();


    }//Fin metodo onItemSelected


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.bntAvanzadas) {

            //Toast.makeText(getActivity(),"Pasara a la activity de la lista de recetas",Toast.LENGTH_SHORT).show();


            Intent i = new Intent(getActivity(), ListaRecetas.class);

            if (!uno.toString().equals("Sin filtro")) {
                i.putExtra("duracionLink", "dur="+uno.toString());
                //i.putExtra("duracionString", uno.toString());
            } else {
                i.putExtra("duracionLink", "");
            }

            if (!dos.toString().equals("Sin filtro")){
                i.putExtra("dificultadLink", "dif='"+dos.toString()+"'");
                //i.putExtra("dificultadString", dos.toString());
            }else {
                i.putExtra("dificultadLink", "");
            }

            if(!tres.toString().equals("Sin filtro")) {
                i.putExtra("zonaLink", "zon='"+tres.toString()+"'");
                //i.putExtra("zonaString", tres.toString());
            }else{
                i.putExtra("zonaLink", "");
            }

            if(!cuatro.toString().equals("Sin filtro")) {
                i.putExtra("alimentoLink", "ing='"+cuatro.toString()+"'");
                //i.putExtra("alimentoString", cuatro.toString());
            }else{
                i.putExtra("alimentoLink","");
            }

            //i.putExtra("tipoAlimento",cinco.toString());

            /*if(cuatro.toString() != ".Sin filtro"){
                i.putExtra("tipoAlimento",cinco.toString());
            }else{
                i.putExtra("tipoAlimento","Ningun alimento seleccionado");
            }*/

            startActivity(i);
        }//Fin IF

    }//Fin metodo onClick

}//Fin Clase