package dam2.sixapp.cookin.swipeTabs.userMain.filterTabsFragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

import dam2.sixapp.cookin.R;
import dam2.sixapp.cookin.swipeTabs.userMain.filterTabsFragment.codeMain.TipoReceta;


/**
 * A simple {@link android.app.Fragment} subclass.
 *
 */
public class Categorias extends android.support.v4.app.Fragment{

	ExpandableListView exList;

    //--------------------------------Arrays-Expandable-ListView-----------------------------------------------------------------------
    String[] primerNivel = {"Por Duración", "Por Dificultad", "Por Zona Geográfica","Por Alimento"};//Array para el primer nivel.

    static String[][]segundoNivel = {//Array para el segundo nivel de la lista expandible

            {"20 mins", "30 mins", "60 mins", "90 mins", "120 mins"},//Por duracion
            {"Fácil", "Media", "Difícil"},//Por dificultad
            {"Oriental", "Latina", "Mediterránea", "Africana", "Otros"},//Por zona
            {"Carnes", "Pescados", "Verduras", "Pastas", "Postres"}, //Por alimento
    };

	public Categorias(){}//Constructor

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_categorias, container, false);

        return rootView;
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);

        //Lista Expandible con su Adaptador
		exList=(ExpandableListView)getView().findViewById(R.id.exListCategorias);
		exList.setAdapter(new MiAdaptadorEx(getActivity().getApplicationContext()));//Adaptador de la lista Expandible.

        exList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            //-------------------Expandable ListView - OnClickListener---------------------------------------------------------------------------
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {

                //Intent para enviar el elemento selccionado del segundo nivel de la lista expandible.
                Intent intent = new Intent(getActivity(), TipoReceta.class);
                intent.putExtra("elemento",segundoNivel[groupPosition][childPosition].toString());
                startActivity(intent);
                return false;
            }
        });
	}

    //---------------------------------------------ADAPTADOR 1 - BASE.EXPANDABLELIST.ADAPTER-----------------------------------------------------------------

    public class MiAdaptadorEx extends BaseExpandableListAdapter {

        private Context context;

        public MiAdaptadorEx(Context context){this.context=context;}

        @Override //Primer nivel de la lista expandible.
        public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {

            TextView tV = new TextView(context);
            tV.setText(primerNivel[groupPosition]);
            tV.setPadding(70,20,0,20);
            tV.setTextSize(1,25);
            tV.setTextColor(Color.parseColor("#000000"));

            return tV;
        }

        @Override //Segundo nivel de la lista expandible.
        public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
           // CustomExpListView SegundoNivelExLv = new CustomExpListView(context);

            TextView tV = new TextView(context);
            tV.setText(segundoNivel[groupPosition][childPosition]);
            tV.setPadding(70,20,0,20);
            tV.setTextSize(1,20);
            tV.setTextColor(Color.parseColor("#000000"));

            return tV;
        }

        @Override
        public int getGroupCount(){return primerNivel.length;}//Recoge la longitud del array de primer nivel

        @Override
        public int getChildrenCount(int groupPosition){return segundoNivel[groupPosition].length;}////Recoge la longitud del array de segundo nivel

        @Override
        public Object getGroup(int groupPosition){return groupPosition;}

        @Override
        public Object getChild(int groupPosition, int childPosition){return null;}

        @Override
        public long getGroupId(int groupPosition){return groupPosition;}

        @Override
        public long getChildId(int groupPosition, int childPosition){return 0;}

        @Override
        public boolean hasStableIds(){return false;}

        @Override
        public boolean isChildSelectable(int groupPosition, int childPosition){return true;}

    }
}//Fin de la clase principal
