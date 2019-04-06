package com.example.a30411;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    TextView txtbox[][], gamelevel, gamescore;
    Button start, exit;
    boolean play = false;
    final Context context = this;
    int x, y;
    int level;
    int colr, colg, colb;
    int score;
    int gab = 250;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setViews();
    }

    private void setViews(){
        txtbox = new TextView[4][];
        for (int i = 0; i < txtbox.length; i++){
            txtbox[i] = new TextView[4];
        }
        txtbox[0][0] = (TextView)findViewById(R.id.txt11);
        txtbox[0][1] = (TextView)findViewById(R.id.txt12);
        txtbox[0][2] = (TextView)findViewById(R.id.txt13);
        txtbox[0][3] = (TextView)findViewById(R.id.txt14);
        txtbox[1][0] = (TextView)findViewById(R.id.txt21);
        txtbox[1][1] = (TextView)findViewById(R.id.txt22);
        txtbox[1][2] = (TextView)findViewById(R.id.txt23);
        txtbox[1][3] = (TextView)findViewById(R.id.txt24);
        txtbox[2][0] = (TextView)findViewById(R.id.txt31);
        txtbox[2][1] = (TextView)findViewById(R.id.txt32);
        txtbox[2][2] = (TextView)findViewById(R.id.txt33);
        txtbox[2][3] = (TextView)findViewById(R.id.txt34);
        txtbox[3][0] = (TextView)findViewById(R.id.txt41);
        txtbox[3][1] = (TextView)findViewById(R.id.txt42);
        txtbox[3][2] = (TextView)findViewById(R.id.txt43);
        txtbox[3][3] = (TextView)findViewById(R.id.txt44);

        gamelevel = (TextView)findViewById(R.id.level);
        gamescore = (TextView)findViewById(R.id.score);
        start = (Button)findViewById(R.id.start);
        exit = (Button)findViewById(R.id.exit);

        for (TextView txt[] : txtbox){
            for (TextView txtView : txt){
                txtView.setOnClickListener(this);
            }
        }
        start.setOnClickListener(this);
        exit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.start:
                play = true;
                level = 1;
                score = 0;
                gamelevel.setText("");
                gamescore.setText("");
                GameStart();
                return;
            case R.id.exit:
                AlertDialog.Builder alert = new AlertDialog.Builder(context);
                alert.setTitle("EXIT");
                alert.setMessage("프로그램을 종료하시겠습니까?")
                     .setCancelable(false)
                     .setPositiveButton("끝내기", new DialogInterface.OnClickListener() {
                         @Override
                         public void onClick(DialogInterface dialog, int which) {
                             finish();
                         }
                     })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .create().show();
                break;
        }

        if(play){
            if(txtbox[x][y].getId() == v.getId()){
                level++;
                score += 100;
                gamelevel.setText("LEVLE : " + level);
                gamescore.setText("SCORE : " + score);
                DifferentColor();
                SameColor();
            } else {
                play = false;
                Toast.makeText(this, "끝", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void GameStart(){
        DifferentColor();
        SameColor();
    }

    private void DifferentColor(){
        x = (int)(Math.random() * 4);
        y = (int)(Math.random() * 4);
    }

    private void SameColor(){
        colr = (int)(Math.random() * 256) * 256 * 256;
        colg = (int)(Math.random() * 256) * 256;
        colb = (int)(Math.random() * 256);

        for (int i = 0; i < txtbox.length; i++){
            for (int j = 0; j < txtbox[i].length; j++){
                if(i == x && j == y){
                    int tempr = (colr / 256) / 256;
                    int tempg = (colg / 256);
                    int tempb = colb;
                    tempr = (tempr - gab / level <= 0 ? 0 : tempr - gab / level) * 256 * 256;
                    tempg = (tempg - gab / level <= 0 ? 0 : tempg - gab / level) * 256;
                    tempb = (tempb - gab / level <= 0 ? 0 : tempb - gab / level);
                    txtbox[i][j].setBackgroundColor(0xff000000  + tempr + tempg + tempb);
                } else {
                    txtbox[i][j].setBackgroundColor(0xff000000 + colr + colg + colb);
                }
            }
        }
    }
}
