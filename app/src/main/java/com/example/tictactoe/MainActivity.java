package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

import static android.view.View.*;

public class MainActivity extends AppCompatActivity {
    int player=0;
    boolean game=true;
    int state[]={2,2,2,2,2,2,2,2,2};
    int winnings[][]={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    public void onClick(View view) {
        ImageView counter = (ImageView) view;
        int tappedCounter = Integer.parseInt(counter.getTag().toString());
        if (state[tappedCounter] == 2 && game)
        {
            state[tappedCounter]=player;
            counter.setTranslationY(-1000f);
            if (player == 0) {
                counter.setImageResource(R.drawable.cross);
                player = 1;
            }
            else {
                counter.setImageResource(R.drawable.circle);
                player = 0;
            }
            counter.animate().translationYBy(1000f).setDuration(30);
            for(int [] winning:winnings)
            {
                if(state[winning[0]]==state[winning[1]] &&
                        state[winning[1]]==state[winning[2]] &&
                        state[winning[0]]!=2)
                {
                    game=false;
                    String w="Circle";
                    if(state[winning[0]]==0)
                    {
                        w="Cross";
                    }
                    TextView winner = (TextView) findViewById(R.id.winner);
                    winner.setText(w+" has WON!");
                    LinearLayout layout= findViewById(R.id.layout);
                    layout.setVisibility(VISIBLE);
                }
                else{
                    boolean over=true;
                    for(int counterState : state)
                    {
                        if(counterState==2)
                        {
                            over=false;
                        }

                    }
                    if(over)
                    {
                        TextView winner = (TextView) findViewById(R.id.winner);
                        winner.setText("Draw!");
                        LinearLayout layout= findViewById(R.id.layout);
                        layout.setVisibility(VISIBLE);
                    }
                }
            }
        }

    }
    public void playAgain(View view) {
        game = true;
        LinearLayout layout = findViewById(R.id.layout);
        layout.setVisibility(INVISIBLE);
        player = 0;
        for(int i=0;i<state.length;i++)
        {
            state[i]=2;
        }
        GridLayout grid = findViewById(R.id.grid);
        for (int i=0; i < grid.getChildCount(); i++)
        {
            ((ImageView) grid.getChildAt(i)).setImageResource(0);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


}
