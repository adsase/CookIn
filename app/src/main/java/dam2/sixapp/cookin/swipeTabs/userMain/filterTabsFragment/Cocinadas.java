package dam2.sixapp.cookin.swipeTabs.userMain.filterTabsFragment;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import dam2.sixapp.cookin.R;
import dam2.sixapp.cookin.customList.CustomListAdapter;
import dam2.sixapp.cookin.customList.NewsItem;

/**
 * A simple {@link android.app.Fragment} subclass.
 *
 */
public class Cocinadas extends Fragment {


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_cocinadas, container, false);

        ArrayList image_details = getListData();
        final ListView lv2 = (ListView) rootView.findViewById(R.id.custom_list_Cocinadas);
        lv2.setAdapter(new CustomListAdapter(rootView.getContext(), image_details));
        lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = lv2.getItemAtPosition(position);
                //NewsItem newsData = (NewsItem) o;
                //Toast.makeText(MainActivity.this, "Selected :" + " " + newsData, Toast.LENGTH_LONG).show();
            }

        });

        return rootView;
    }

    private ArrayList getListData() {
        ArrayList results = new ArrayList();
        NewsItem newsData = new NewsItem();

        newsData = new NewsItem();
        newsData.setTitle("Titulo");
        newsData.setReputation(4.5);
        newsData.setTime("Tiempo");
        newsData.setNationality("Nacionalidad");
        newsData.setImage(R.drawable.ic_launcher);
        results.add(newsData);

        newsData = new NewsItem();
        newsData.setTitle("Huevos fritos");
        newsData.setReputation(0);
        newsData.setTime("1 hora");
        newsData.setNationality("Francesa");
        newsData.setImage(R.drawable.ic_launcher);
        results.add(newsData);

        return results;
    }

}