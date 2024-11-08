package com.example.dicegame;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView[] diceViews = new TextView[5];
    private TextView currentRollScore, gameScore, rollCount;
    private Button rollDiceButton, resetButton;
    private int totalScore = 0;
    private int rolls = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        diceViews[0] = findViewById(R.id.dice1);
        diceViews[1] = findViewById(R.id.dice2);
        diceViews[2] = findViewById(R.id.dice3);
        diceViews[3] = findViewById(R.id.dice4);
        diceViews[4] = findViewById(R.id.dice5);

        currentRollScore = findViewById(R.id.currentRollScore);
        gameScore = findViewById(R.id.gameScore);
        rollCount = findViewById(R.id.rollCount);

        rollDiceButton = findViewById(R.id.rollDiceButton);
        resetButton = findViewById(R.id.resetButton);

        rollDiceButton.setOnClickListener(v -> rollDice());
        resetButton.setOnClickListener(v -> resetGame());
    }

    private void rollDice() {
        Random random = new Random();
        int[] diceResults = new int[5];
        int[] counts = new int[6];


        for (int i = 0; i < 5; i++) {
            diceResults[i] = random.nextInt(6) + 1;
            diceViews[i].setText(String.valueOf(diceResults[i]));
            counts[diceResults[i] - 1]++;
        }


        int rollScore = 0;
        for (int i = 0; i < 6; i++) {
            if (counts[i] > 1) {
                rollScore += (i + 1) * counts[i];
            }
        }

        updateScore(rollScore);
        currentRollScore.setText("Wynik tego losowania: " + rollScore);
        updateRollCount();
    }

    private void resetGame() {
        for (TextView diceView : diceViews) {
            diceView.setText("?");
        }
        totalScore = 0;
        rolls = 0;
        currentRollScore.setText("Wynik tego losowania: 0");
        gameScore.setText("Wynik gry: 0");
        rollCount.setText("Liczba rzutów: 0");
    }

    private void updateScore(int newScore) {
        totalScore += newScore;
        gameScore.setText("Wynik gry: " + totalScore);
    }

    private void updateRollCount() {
        rolls++;
        rollCount.setText("Liczba rzutów: " + rolls);
    }
}
