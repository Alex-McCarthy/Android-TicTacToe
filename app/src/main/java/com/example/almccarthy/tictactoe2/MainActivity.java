package com.example.almccarthy.tictactoe2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private char playerTurn;
    private char[][] board = {{' ', ' ', ' '},
                              {' ', ' ', ' '},
                              {' ', ' ', ' '}};
    private boolean gameOver = false;
    private String winner = "Tie";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        playerTurn = 'X';
    }

    public void setMark(View v){
        //String buttonID = ((Button)v).getText().toString();
        String tag = (String) v.getTag();
        int boardOne = Character.getNumericValue(tag.charAt(0));
        int boardTwo = Character.getNumericValue(tag.charAt(1));
        if(board[boardOne][boardTwo] == ' ') {
            board[boardOne][boardTwo] = playerTurn;
            if (playerTurn == 'O') {
                ImageButton btn = findViewById(v.getId());
                btn.setImageResource(R.drawable.o);
                board[boardOne][boardTwo] = 'O';
            } else {
                ImageButton btn = findViewById(v.getId());
                btn.setImageResource(R.drawable.x);
                board[boardOne][boardTwo] = 'X';
            }
            setTurn();
        }

        char winner = checkWin();

        if(gameOver == true){
            if(winner == 't') {
                TextView newText = (TextView) findViewById(R.id.whosturn);
                newText.setText("Game over. Tie game. Tap new game to play again.");
            }else{
                TextView newText = (TextView) findViewById(R.id.whosturn);
                newText.setText("Game over. " + winner + "'s win. Tap new game to play again.");
            }
        }
    }

    public void setTurn(){
        if(playerTurn == 'X'){
            playerTurn = 'O';
            TextView newText = (TextView)findViewById(R.id.whosturn);
            newText.setText("O's turn");
        }else{
            playerTurn = 'X';
            TextView newText = (TextView)findViewById(R.id.whosturn);
            newText.setText("X's turn");
        }


    }

    public void newGame(View v){

        for(int i=0; i<=2; i++){
            for(int j = 0; j<=2; j++){
                board[i][j] = ' ';
            }
        }

        ImageButton btn = findViewById(R.id.blank00);
        btn.setImageResource(R.drawable.blank);
        ImageButton btn1 = findViewById(R.id.blank01);
        btn1.setImageResource(R.drawable.blank);
        ImageButton btn2 = findViewById(R.id.blank02);
        btn2.setImageResource(R.drawable.blank);
        ImageButton btn3 = findViewById(R.id.blank10);
        btn3.setImageResource(R.drawable.blank);
        ImageButton btn4 = findViewById(R.id.blank11);
        btn4.setImageResource(R.drawable.blank);
        ImageButton btn5 = findViewById(R.id.blank12);
        btn5.setImageResource(R.drawable.blank);
        ImageButton btn6 = findViewById(R.id.blank20);
        btn6.setImageResource(R.drawable.blank);
        ImageButton btn7 = findViewById(R.id.blank21);
        btn7.setImageResource(R.drawable.blank);
        ImageButton btn8 = findViewById(R.id.blank22);
        btn8.setImageResource(R.drawable.blank);

        TextView newText = (TextView)findViewById(R.id.whosturn);
        newText.setText("X's turn");

        gameOver = false;
        playerTurn = 'X';
    }

    public char checkWin(){
        char cC = 'X'; //starts as X for the first check and switches to O for the second
        char winner = 'n'; //starts as user and switches to computer
        for(int i = 0; i<=1; i++){
            if(i == 1){ //switches the cC and winner variables to check if the computer has won
                cC = 'O';
            }
            for(int z=0; z<=2; z++){
                //x axis check
                if(cC == board[z][0] && cC == board[z][1] && cC == board[z][2]){
                    gameOver = true;
                    winner = cC;
                }
                //y axis check
                if(cC == board[0][z] && cC == board[1][z] && cC == board[2][z]){
                    gameOver = true;
                    winner = cC;
                }
            }
            //diagonal check
            if(cC == board[0][0] && cC == board[1][1] && cC == board[2][2]){
                gameOver = true;
                winner = cC;
            }
            if(cC == board[0][2] && cC == board[1][1] && cC == board[2][0]){
                gameOver = true;
                winner = cC;
            }
        }

        int blankCount = 9;

        if(gameOver == false) {
            for (int i = 0; i <= 2; i++){
                for(int j = 0; j<=2; j++){
                    if(board[i][j] != ' '){
                        blankCount--;
                    }
                }
            }
        }

        if(blankCount == 0){
            gameOver = true;
            winner = 't';
        }

        return winner;
    }
}
