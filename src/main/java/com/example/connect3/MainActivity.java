package com.example.connect3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int[] state={2,2,2,2,2,2,2,2,2}; //2 is empty
    int[][] winPositions ={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    int temp=0; //0 is yellow and 1 is red
    boolean active = true;


    public void dropping(View view){
        ImageView counter = (ImageView) view;
        int tapcounter= Integer.parseInt(counter.getTag().toString());
        if(state[tapcounter]==2 && active) {
            state[tapcounter] = temp;
            counter.setTranslationY(-1500);

            if (temp == 0) {
                counter.setImageResource(R.drawable.yellow);
                temp = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                temp = 0;
            }

            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);


            TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
            Button tryButton = (Button) findViewById(R.id.tryButton);
            for (int[] winPosition : winPositions) {


                if (state[winPosition[0]] == state[winPosition[1]] && state[winPosition[1]] == state[winPosition[2]] && state[winPosition[0]] != 2) {
                    active = false;
                    String winner = "";
                    if (temp == 1)
                        winner = "Yellow";
                    else
                        winner = "Red";

                    //Toast.makeText(this, winner + " has won", Toast.LENGTH_SHORT).show();


                    winnerTextView.setText(winner + " has won!!!");
                    tryButton.setVisibility(View.VISIBLE);
                    winnerTextView.setVisibility(View.VISIBLE);


                }

            }
        }


     }


    public void playAgain(View view){
        Button tryButton = (Button) findViewById(R.id.tryButton);
        TextView winnerTextView = (TextView) findViewById(R.id.winnerTextView);
        tryButton.setVisibility(View.INVISIBLE);
        winnerTextView.setVisibility(View.INVISIBLE);

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout2);
        for(int i=0; i<gridLayout.getChildCount(); i++){
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }

        for(int j=0;j<9;j++)
            state[j]=2;


        temp=0; //0 is yellow and 1 is red
        active = true;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}