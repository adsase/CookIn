package dam2.sixapp.cookin.customList;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by Florida on 15/02/2015.
 */
public abstract class ListaPersonalizada extends BaseAdapter{

    Context context;
    int id;
    ArrayList<?> elemento;

    public ListaPersonalizada(Context context, int id, ArrayList<?> elemento){

        this.context=context;
        this.id = id;
        this.elemento = elemento;

    }

    @Override
    public int getCount() {
        return elemento.size();
    }

    @Override
    public Object getItem(int position) {
        return elemento.get(position);

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {

            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(id, null); //ConvertView és eixe Layout però sense les dades
        }

        onEntrada (elemento.get(position), convertView);
        return convertView;
    }

    public abstract void onEntrada (Object entrada, View view);
}
