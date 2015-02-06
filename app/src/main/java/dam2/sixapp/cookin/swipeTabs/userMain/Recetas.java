package dam2.sixapp.cookin.swipeTabs.userMain;

import android.support.v7.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SearchView;
import android.widget.Toast;

import dam2.sixapp.cookin.R;
import dam2.sixapp.cookin.drawer.NavigationDrawerFragment;
import dam2.sixapp.cookin.swipeTabs.userMain.filterTabsFragment.Avanzadas;
import dam2.sixapp.cookin.swipeTabs.userMain.filterTabsFragment.Categorias;
import dam2.sixapp.cookin.swipeTabs.userMain.filterTabsFragment.Cocinadas;
import dam2.sixapp.cookin.swipeTabs.userMain.filterTabsFragment.Valoradas;

public class Recetas extends ActionBarActivity implements ActionBar.TabListener,NavigationDrawerFragment.NavigationDrawerCallbacks/*SearchView.OnQueryTextListener*/ {
	

	ViewPager vP;
    SearchView mSearchView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_recetas);

        final ActionBar actionBar=getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

		vP=(ViewPager)findViewById(R.id.pager);
		vP.setAdapter(new MiAdaptador(getSupportFragmentManager()));
		vP.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0)
            {
                actionBar.setSelectedNavigationItem(arg0);
            }

		});

        String[] tabs = getResources().getStringArray(R.array.tabRecetas);

        // Añadiendo Tabs
        for (String tab_name : tabs) {
            actionBar.addTab(actionBar.newTab().setText(tab_name).setTabListener(this));
        }
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.recetas, menu);

        /*MenuItem searchItem = menu.findItem(R.id.action_search1);
        mSearchView = (SearchView) searchItem.getActionView();
        //mSearchView.setQueryHint("Buscando...");
        mSearchView.setOnQueryTextListener(this);*/

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

        /*int id = item.getItemId();
        if(id == R.id.action_search1){

            Toast.makeText(this,"Has pulsado el buscador",Toast.LENGTH_SHORT).show();

        }*/
		return false;
	}

    @Override
    public void onNavigationDrawerItemSelected(int position) {

    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {
        vP.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, android.support.v4.app.FragmentTransaction fragmentTransaction) {

    }

    /*//Boton SearchView ActionBar
    @Override
    public boolean onQueryTextSubmit(String query) {

        Toast.makeText(this, "Searching for " + query, Toast.LENGTH_LONG).show();
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        Toast.makeText(this, newText, Toast.LENGTH_SHORT).show();
        return false;
    }*/
}

class MiAdaptador extends FragmentStatePagerAdapter {


    public MiAdaptador(FragmentManager fm){super(fm);}

    @Override
    public Fragment getItem(int i) {

        Fragment fragment = null;

        if(i==0){fragment = new Categorias();}
        if(i==1){fragment = new Avanzadas();}
        if(i==2){fragment = new Cocinadas();}
        if(i==3){fragment = new Valoradas();}

        return fragment;
    }

    @Override
    public int getCount(){return 4;}
}