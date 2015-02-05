package dam2.sixapp.cookin.recipes.activitySteps;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import dam2.sixapp.cookin.R;

public class StepsActivity extends ActionBarActivity implements View.OnClickListener {

    private TextView nombreReceta, pasoReceta;
    private Button backButton, pauseButton, nextButton;
    int stepsCount;
    String[] steps;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps_assistant);

        nombreReceta = (TextView) findViewById(R.id.textView14);
        pasoReceta = (TextView) findViewById(R.id.textView16);
        backButton = (Button) findViewById(R.id.button3);
        pauseButton = (Button) findViewById(R.id.button4);
        nextButton = (Button) findViewById(R.id.button5);

        /** Listeners **/
        backButton.setOnClickListener(this);
        pauseButton.setOnClickListener(this);
        nextButton.setOnClickListener(this);
        /** Listeners **/

        stepsCount = 4; /** cantidad de pasos en la receta **/



        nombreReceta.setText("Nombre Receta");
        pasoReceta.setText("Paso Receta");
    }

    public void updateActivity(int count){
        pasoReceta.setText(steps[count]);
    }

    @Override
    public void onClick(View v) {
        int actualStep;
        switch(v.getId()){
            case R.id.button3:
                for(int count=0;count<stepsCount;count--){
                    updateActivity(count);
                    actualStep = count;
                }
                break;
            case R.id.button4:

                break;
            case R.id.button5:
                for(int count=1;count<stepsCount;count++){
                    updateActivity(count);
                    actualStep = count;
                }
                break;
        }
    }
}
