package com.example.battiger;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button b_tiger, b_chicken, b_bug, b_stick;
    TextView tv_score;
    ImageView iv_ComputerChoice, iv_HumanChoice;

    int HumanScore, ComputerScore = 0;

    private SoundPlayer sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sound = new SoundPlayer( this );

        b_tiger = (Button) findViewById(R.id.b_tiger);
        b_chicken = (Button) findViewById(R.id.b_chicken);
        b_bug = (Button) findViewById(R.id.b_bug);
        b_stick = (Button) findViewById(R.id.b_stick);

        iv_ComputerChoice = (ImageView) findViewById(R.id.iv_ComputerChoice);
        iv_HumanChoice = (ImageView) findViewById(R.id.iv_HumanChoice);

        tv_score = (TextView) findViewById(R.id.tv_score);

        b_tiger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_HumanChoice.setImageResource(R.drawable.tiger);
                String message = play_turn("tiger");
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                tv_score.setText( "Score Human: " + Integer.toString( HumanScore) + " Computer: " + Integer.toString( ComputerScore ) );
            }
        });

        b_chicken.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_HumanChoice.setImageResource(R.drawable.chicken);
                String message = play_turn("chicken");
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                tv_score.setText( "Score Human: " + Integer.toString( HumanScore)+ " Computer: "+Integer.toString(ComputerScore));
            }
        });

        b_bug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_HumanChoice.setImageResource(R.drawable.bug);
                String message = play_turn("bug");
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                tv_score.setText("Score Human: "+ Integer.toString( HumanScore)+ " Computer: "+Integer.toString(ComputerScore));
            }
        });

        b_stick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                iv_HumanChoice.setImageResource(R.drawable.stick);
                String message = play_turn("stick");
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                tv_score.setText("Score Human: "+Integer.toString(HumanScore)+" Computer: "+Integer.toString(ComputerScore));
            }
        });



    }

    public String play_turn( String player_choice ){
        String computer_choice = "";
        Random r = new Random();
        int computer_choice_number = r.nextInt(4) + 1;

        if( computer_choice_number == 1 ){
            computer_choice = "tiger";
        }else if( computer_choice_number == 2 ){
            computer_choice = "chicken";
        }else if( computer_choice_number == 3){
            computer_choice = "bug";
        }else if( computer_choice_number == 4){
            computer_choice = "stick";
        }

        if ( computer_choice == "tiger"){
            iv_ComputerChoice.setImageResource(R.drawable.tiger);
        }else if( computer_choice == "chicken" ){
            iv_ComputerChoice.setImageResource( R.drawable.chicken );
        }else if( computer_choice == "bug" ){
            iv_ComputerChoice.setImageResource( R.drawable.bug );
        }else if( computer_choice == "stick" ){
            iv_ComputerChoice.setImageResource( R.drawable.stick );
        }

        if( computer_choice == player_choice
                || player_choice == "tiger" && computer_choice == "bug"
                || player_choice == "bug" && computer_choice == "tiger"
                || player_choice == "chicken" && computer_choice == "stick"
                || player_choice == "stick" && computer_choice == "chicken"){
            return "Draw";
        }else if( player_choice == "tiger" && computer_choice == "chicken"
                || player_choice == "chicken" && computer_choice == "bug"
                || player_choice == "bug" && computer_choice == "stick"
                || player_choice == "stick" && computer_choice == "tiger" ){
            sound.playWinSound();
            HumanScore++;
            return "You Win";
        }else if( player_choice == "tiger" && computer_choice == "stick"
                || player_choice == "stick" && computer_choice == "bug"
                || player_choice == "bug" && computer_choice == "chicken"
                || player_choice == "chicken" && computer_choice == "tiger" ){
            sound.playFailSound();
            ComputerScore++;
            return "Sorry, try again";
        }else{
            return "blah";
        }
    }
}