package com.example.pcd.scarnesdice;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private int player_score = 0;
    private int comp_score = 0;
    private int turn = 0;
    private int temp_score = 0;

    Button roll,hold,reset;
    TextView player_text_score,comp_text_score;
    ImageView img_dice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        roll = (Button) findViewById(R.id.button);
        hold = (Button) findViewById(R.id.button3);
        reset = (Button) findViewById(R.id.button2);


        player_text_score = (TextView) findViewById(R.id.textView2);
        comp_text_score = (TextView) findViewById(R.id.textView4);
        img_dice = (ImageView) findViewById(R.id.imageView);

        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                roll(v);
            }
        });
        hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hold(v);
            }
        });
        reset.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                reset();
            }
        });
    }
    public void roll (View view){
        Random r = new Random();
        int dice = r.nextInt(6)+1;
        System.out.println(dice);
        switch (dice){
            case 1:
                img_dice.setImageResource(R.drawable.dice1);
                break;

            case 2:
                img_dice.setImageResource(R.drawable.dice2);
                break;
            case 3:
                img_dice.setImageResource(R.drawable.dice3);
                break;
            case 4:
                img_dice.setImageResource(R.drawable.dice4);
                break;
            case 5:
                img_dice.setImageResource(R.drawable.dice5);
                break;

            case 6:
                img_dice.setImageResource(R.drawable.dice6);

        }
        //temp_score+=dice;
        if(turn==0)
        {
            //user turn

            if(dice==1)
            {
                temp_score = 0;
                hold(comp_text_score);
            }
            else
            {
                temp_score+=dice;
            }
        }
        else
        {
            Random r2 = new Random();
            int random_number=r2.nextInt(10);
            if(random_number==5)
            {
                hold(player_text_score);
            }
            else if(dice==1)
            {
                temp_score=0;
                hold(comp_text_score);
            }
            else
            {
                temp_score+=dice;
                roll(player_text_score);
            }
        }
    }

    public void hold(View view)
    {
        if(turn==0)
        {
            player_score += temp_score;
            player_text_score.setText(Integer.toString(player_score));
            temp_score=0;
            turn=1;
            roll(comp_text_score);

        }

        else
        {
            comp_score += temp_score;
            comp_text_score.setText(Integer.toString(comp_score));
            temp_score=0;
            turn=0;
        }
    }
    public void reset()
    {
        player_score=0;
        comp_score=0;
        player_text_score.setText(Integer.toString(player_score));
        comp_text_score.setText(Integer.toString(comp_score));
    }
}
