package dam2.sixapp.cookin.recipes;

import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import dam2.sixapp.cookin.R;

public class recipeModeSelector extends ActionBarActivity{

    private TextView recipeName,difficulty,duration,votes,zone,description;
    private Button readButton,assistantButton;
    private ImageView recipeImage;
    private int idReceta;
    private String recipeNameS,difficultyS,durationS,votesS,zoneS,descriptioS,imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_mode_selector);
        final android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        recipeName = (TextView) findViewById(R.id.recipeName);
        recipeImage = (ImageView) findViewById(R.id.recipeImage);
        difficulty = (TextView) findViewById(R.id.difficulty);
        duration = (TextView) findViewById(R.id.duration);
        votes = (TextView) findViewById(R.id.votes);
        zone = (TextView) findViewById(R.id.zone);
        description = (TextView) findViewById(R.id.description);
        readButton = (Button) findViewById(R.id.readButton);
        assistantButton = (Button) findViewById(R.id.assistantButton);
        Bundle b = getIntent().getExtras();
        idReceta=b.getInt("id");

        recipeNameS = "";
        imageUrl = "/** AQUI URL DE IMAGEN **/";
        difficultyS = "";
        durationS = "";
        votesS = "";
        zoneS = "";
        descriptioS = "";


        recipeName.setText(recipeNameS);
        if(imageUrl == null) {
            recipeImage.setImageDrawable(getResources().getDrawable(R.drawable.app_logo));
        }else{
            Picasso.with(getApplicationContext()).load(imageUrl).into(recipeImage);
        }
        difficulty.setText(getResources().getString(R.string.difficulty)+" "+difficultyS);
        duration.setText(getResources().getString(R.string.duration)+" "+durationS);
        votes.setText(getResources().getString(R.string.votes)+" "+votesS);
        zone.setText(getResources().getString(R.string.zone)+" "+zoneS);
        description.setText(descriptioS);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_recipe_mode_selector, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
