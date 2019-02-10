package com.example.raymond.gameconnect3;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
//import android.widget.GridLayout;
import android.widget.ImageView;
import android.support.v7.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Array;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    //0 = yellow, 1 = red
    int activePlayer = 0;

    boolean gameIsActive = true;

    boolean allSpaceIscoupied = false;

    //2 means unplayed
    int[] gameState = {2,2,2,2,2,2,2,2,2};

    int[][] winningPositions = {{0,1,2},{3,4,5},{7,6,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};



    public void dropIn(View view){

        ImageView counter = (ImageView) view;

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        if(gameState[tappedCounter] == 2 && gameIsActive) {

            gameState[tappedCounter] = activePlayer;

            if ( activePlayer == 0 ) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.setTranslationY(-1000f);
            counter.animate().translationYBy(1000f).rotation(360).setDuration(300);

            for (int i = 0; i < 8; i++) {
                if((gameState[winningPositions[i][0]] == gameState[winningPositions[i][1]]) &&
                        (gameState[winningPositions[i][0]] == gameState[winningPositions[i][2]]) &&
                            (gameState[winningPositions[i][0]] != 2)){

                    LinearLayout playAgainLinerLayout = (LinearLayout) findViewById(R.id.playAgainLinearLayout);
                    TextView winnerMessageTextView = (TextView) findViewById(R.id.winnerMessageTextView);
                    playAgainLinerLayout.setVisibility(view.VISIBLE);

                    gameIsActive = false;

                    String winner = "red";

                    if (gameState[winningPositions[i][0]] == 0) {
                        winner = "yellow";
                    }

                    winnerMessageTextView.setText(winner + " has won");

                    return;

                }
            }
            for (int statement: gameState) {
                allSpaceIscoupied = true;
                if(statement == 2 ) {
                    allSpaceIscoupied = false;
                    break;
                }
            }
            if(allSpaceIscoupied) {
                System.out.println(Arrays.toString(gameState));
                LinearLayout playAgainLinerLayout = (LinearLayout) findViewById(R.id.playAgainLinearLayout);
                TextView winnerMessageTextView = (TextView) findViewById(R.id.winnerMessageTextView);
                playAgainLinerLayout.setVisibility(view.VISIBLE);
                gameIsActive = false;
                winnerMessageTextView.setText("no one" + " has won");

                return;
            }
        }

    }

    public void playAgain(View view) {


        LinearLayout playAgainLinearLayout = (LinearLayout)findViewById(R.id.playAgainLinearLayout);

        playAgainLinearLayout.setVisibility(View.INVISIBLE);

        gameIsActive = true;

        activePlayer = 0;

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayout);

//        int childsNumber = gridLayout.getChildCount();
        for(int i = 0; i < gridLayout.getChildCount(); i++) {
            ((ImageView) gridLayout.getChildAt(i)).setImageResource(0);
        }

        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
