package dam2.sixapp.cookin.swipeTabs.userMain.filterTabsFragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
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

    Spinner spDuracionAvanzadas, spDificultadAvanzadas, spZonaAvanzadas, spAlimentoAvanzadas, spTipoAlimentoAvanzadas;
    ArrayAdapter<CharSequence> adaptadorAlimentosAvanzada;
    Button bntAvanzadas;
    String uno, dos, tres, cuatro, cinco;


    public Avanzadas() {
    }//Constructor

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_avanzadas, container, false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //Boton enviar categorias avanzadas
        bntAvanzadas = (Button) getView().findViewById(R.id.bntAvanzadas);
        bntAvanzadas.setOnClickListener(this);

        //----------------------------Spinners Categorias Avanzadas-------------------------------------------------------------------------

        //Por Duracion
        spDuracionAvanzadas = (Spinner) getView().findViewById(R.id.spDuracionAvanzadas);
        adaptadorAlimentosAvanzada = ArrayAdapter.createFromResource(getActivity(), R.array.duracion, android.R.layout.simple_spinner_item);
        adaptadorAlimentosAvanzada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDuracionAvanzadas.setAdapter(adaptadorAlimentosAvanzada);
        spDuracionAvanzadas.setOnItemSelectedListener(this);

        //Por Dificultad
        spDificultadAvanzadas = (Spinner) getView().findViewById(R.id.spDificultadAvanzadas);
        adaptadorAlimentosAvanzada = ArrayAdapter.createFromResource(getActivity(), R.array.dificultad, android.R.layout.simple_spinner_item);
        adaptadorAlimentosAvanzada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spDificultadAvanzadas.setAdapter(adaptadorAlimentosAvanzada);
        spDificultadAvanzadas.setOnItemSelectedListener(this);

        //Por Zona Geografica
        spZonaAvanzadas = (Spinner) getView().findViewById(R.id.spZonaAvanzadas);
        adaptadorAlimentosAvanzada = ArrayAdapter.createFromResource(getActivity(), R.array.zona, android.R.layout.simple_spinner_item);
        adaptadorAlimentosAvanzada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spZonaAvanzadas.setAdapter(adaptadorAlimentosAvanzada);
        spZonaAvanzadas.setOnItemSelectedListener(this);

        //Por Alimento
        spAlimentoAvanzadas = (Spinner) getView().findViewById(R.id.spAlimentoAvanzadas);
        adaptadorAlimentosAvanzada = ArrayAdapter.createFromResource(getActivity(), R.array.alimento, android.R.layout.simple_spinner_item);
        adaptadorAlimentosAvanzada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spAlimentoAvanzadas.setAdapter(adaptadorAlimentosAvanzada);
        spAlimentoAvanzadas.setOnItemSelectedListener(this);

        spTipoAlimentoAvanzadas = (Spinner) getView().findViewById(R.id.spTipoAlimentoAvanzadas);


    }//Fin metodo onActivityCreated

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


        if(spAlimentoAvanzadas.getSelectedItem().toString().equals("Sin filtro")){

            spTipoAlimentoAvanzadas.setEnabled(false);
            spTipoAlimentoAvanzadas.setVisibility(View.GONE);
            cinco = "";
            //Toast.makeText(getActivity(),"Esto "+cinco,Toast.LENGTH_SHORT).show();



        }

        if(spAlimentoAvanzadas.getSelectedItem().toString().equals("Carne")){
            adaptadorAlimentosAvanzada = ArrayAdapter.createFromResource(getActivity(),R.array.carnes, android.R.layout.simple_spinner_item);
            adaptadorAlimentosAvanzada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spTipoAlimentoAvanzadas.setAdapter(adaptadorAlimentosAvanzada);
            spTipoAlimentoAvanzadas.setVisibility(View.VISIBLE);
            spTipoAlimentoAvanzadas.setEnabled(true);
            spTipoAlimentoAvanzadas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    cinco = spTipoAlimentoAvanzadas.getSelectedItem().toString();
                    //Toast.makeText(getActivity(),"Esto "+cinco,Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent){}
            });
        }

        if(spAlimentoAvanzadas.getSelectedItem().toString().equals("Pescado")){

            adaptadorAlimentosAvanzada = ArrayAdapter.createFromResource(getActivity(),R.array.pescados, android.R.layout.simple_spinner_item);
            adaptadorAlimentosAvanzada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spTipoAlimentoAvanzadas.setAdapter(adaptadorAlimentosAvanzada);
            spTipoAlimentoAvanzadas.setVisibility(View.VISIBLE);
            spTipoAlimentoAvanzadas.setEnabled(true);
            spTipoAlimentoAvanzadas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    cinco = spTipoAlimentoAvanzadas.getSelectedItem().toString();
                    //Toast.makeText(getActivity(),"Esto "+cinco,Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent){}
            });

        }

        if(spAlimentoAvanzadas.getSelectedItem().toString().equals("Vegetal")){

            adaptadorAlimentosAvanzada = ArrayAdapter.createFromResource(getActivity(),R.array.verduras, android.R.layout.simple_spinner_item);
            adaptadorAlimentosAvanzada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spTipoAlimentoAvanzadas.setAdapter(adaptadorAlimentosAvanzada);
            spTipoAlimentoAvanzadas.setVisibility(View.VISIBLE);
            spTipoAlimentoAvanzadas.setEnabled(true);
            spTipoAlimentoAvanzadas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    cinco = spTipoAlimentoAvanzadas.getSelectedItem().toString();
                    //Toast.makeText(getActivity(),"Esto "+cinco,Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent){}
            });

        }

        if(spAlimentoAvanzadas.getSelectedItem().toString().equals("Pasta")){

            adaptadorAlimentosAvanzada = ArrayAdapter.createFromResource(getActivity(),R.array.pasta, android.R.layout.simple_spinner_item);
            adaptadorAlimentosAvanzada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spTipoAlimentoAvanzadas.setAdapter(adaptadorAlimentosAvanzada);
            spTipoAlimentoAvanzadas.setVisibility(View.VISIBLE);
            spTipoAlimentoAvanzadas.setEnabled(true);
            spTipoAlimentoAvanzadas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    cinco = spTipoAlimentoAvanzadas.getSelectedItem().toString();
                    //Toast.makeText(getActivity(),"Esto "+cinco,Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent){}
            });
        }

        if(spAlimentoAvanzadas.getSelectedItem().toString().equals("Postres")){

            adaptadorAlimentosAvanzada = ArrayAdapter.createFromResource(getActivity(),R.array.postres, android.R.layout.simple_spinner_item);
            adaptadorAlimentosAvanzada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spTipoAlimentoAvanzadas.setAdapter(adaptadorAlimentosAvanzada);
            spTipoAlimentoAvanzadas.setVisibility(View.VISIBLE);
            spTipoAlimentoAvanzadas.setEnabled(true);
            spTipoAlimentoAvanzadas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    cinco = spTipoAlimentoAvanzadas.getSelectedItem().toString();
                    //Toast.makeText(getActivity(),"Esto "+cinco,Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent){}
            });

        }

        uno = spDuracionAvanzadas.getSelectedItem().toString();
        dos = spDificultadAvanzadas.getSelectedItem().toString();
        tres = spZonaAvanzadas.getSelectedItem().toString();
        cuatro = spAlimentoAvanzadas.getSelectedItem().toString();


        //---------------------------------Switch de los tipos de alimentos------------------------------------------------------------------------
        /*switch (spAlimentoAvanzadas.getSelectedItem().toString()) {

            case "Sin filtro":

                spTipoAlimentoAvanzadas.setEnabled(false);
                spTipoAlimentoAvanzadas.setVisibility(View.GONE);

                break;

            case "Carne":

                adaptadorAlimentosAvanzada = ArrayAdapter.createFromResource(getActivity(),R.array.carnes, android.R.layout.simple_spinner_item);
                adaptadorAlimentosAvanzada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spTipoAlimentoAvanzadas.setAdapter(adaptadorAlimentosAvanzada);
                spTipoAlimentoAvanzadas.setVisibility(View.VISIBLE);
                spTipoAlimentoAvanzadas.setEnabled(true);
                spTipoAlimentoAvanzadas.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                        cinco = spTipoAlimentoAvanzadas.getSelectedView().toString();
                        Toast.makeText(getActivity(),"Esto "+cinco,Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });
                break;

            case "Pescado":

                adaptadorAlimentosAvanzada = ArrayAdapter.createFromResource(getActivity(),R.array.pescados, android.R.layout.simple_spinner_item);
                adaptadorAlimentosAvanzada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spTipoAlimentoAvanzadas.setAdapter(adaptadorAlimentosAvanzada);
                spTipoAlimentoAvanzadas.setVisibility(View.VISIBLE);
                spTipoAlimentoAvanzadas.setEnabled(true);
                break;

            case "Vegetal":

                adaptadorAlimentosAvanzada = ArrayAdapter.createFromResource(getActivity(),R.array.verduras, android.R.layout.simple_spinner_item);
                adaptadorAlimentosAvanzada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spTipoAlimentoAvanzadas.setAdapter(adaptadorAlimentosAvanzada);
                spTipoAlimentoAvanzadas.setVisibility(View.VISIBLE);
                spTipoAlimentoAvanzadas.setEnabled(true);
                break;

            case "Pasta":

                adaptadorAlimentosAvanzada = ArrayAdapter.createFromResource(getActivity(),R.array.pasta, android.R.layout.simple_spinner_item);
                adaptadorAlimentosAvanzada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spTipoAlimentoAvanzadas.setAdapter(adaptadorAlimentosAvanzada);
                spTipoAlimentoAvanzadas.setVisibility(View.VISIBLE);
                spTipoAlimentoAvanzadas.setEnabled(true);
                break;


            case "Postres":

                adaptadorAlimentosAvanzada = ArrayAdapter.createFromResource(getActivity(),R.array.postres, android.R.layout.simple_spinner_item);
                adaptadorAlimentosAvanzada.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spTipoAlimentoAvanzadas.setAdapter(adaptadorAlimentosAvanzada);
                spTipoAlimentoAvanzadas.setVisibility(View.VISIBLE);
                spTipoAlimentoAvanzadas.setEnabled(true);
                break;
        }//Fin switch*/



    }//Fin metodo onItemSelected


    @Override
    public void onNothingSelected(AdapterView<?> parent){}

    @Override
    public void onClick(View v) {

        if(v.getId()==R.id.bntAvanzadas) {

            Intent i = new Intent(getActivity(), ListaRecetas.class);

            if (!uno.toString().equals("Sin filtro")) {
                i.putExtra("duracionLink", "dur="+uno.toString());
            } else {
                i.putExtra("duracionLink", "");
            }

            if (!dos.toString().equals("Sin filtro")){
                i.putExtra("dificultadLink", "dif='"+dos.toString()+"'");
            }else {
                i.putExtra("dificultadLink", "");
            }

            if(!tres.toString().equals("Sin filtro")) {
                i.putExtra("zonaLink", "zon='"+tres.toString()+"'");
            }else{
                i.putExtra("zonaLink", "");
            }

            if(!cuatro.toString().equals("Sin filtro")) {
                i.putExtra("alimentoLink", "cat='"+cuatro.toString()+"'");
            }else{
                i.putExtra("alimentoLink","");
            }
            if(!cinco.toString().equals("Todos") && !cinco.toString().equals("")) {
                i.putExtra("tipoalimentoLink", "ing='"+cinco.toString()+"'");
            }else{
                i.putExtra("tipoalimentoLink","");
            }


            startActivity(i);
        }//Fin If

    }//Fin metodo onClick

}//Fin Clase