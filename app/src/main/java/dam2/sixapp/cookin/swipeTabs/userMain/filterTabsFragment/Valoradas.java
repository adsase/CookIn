package dam2.sixapp.cookin.swipeTabs.userMain.filterTabsFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dam2.sixapp.cookin.R;


public class Valoradas extends android.support.v4.app.Fragment {


	public Valoradas() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_valoradas, container, false);
		return rootView;
	}

}