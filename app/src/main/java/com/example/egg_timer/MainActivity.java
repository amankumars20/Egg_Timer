package com.example.egg_timer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaParser;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

     SeekBar timerseekbar;
    TextView timertextview;
    Boolean counterIsActive = false;
    Button goButton;
    CountDownTimer countDownTimer;
    public void resetTimer() {
        timertextview.setText("0:00");
        timerseekbar.setProgress(30);
        timerseekbar.setEnabled(true);
        countDownTimer.cancel();
        goButton.setText("GO!");
        counterIsActive = false;
    }

    public void buttonClicked(View view) {

        if (counterIsActive) {

            resetTimer();

        } else {

            counterIsActive = true;
            timerseekbar.setEnabled(false);
            goButton.setText("STOP!");

         countDownTimer = new CountDownTimer(timerseekbar.getProgress() * 1000 + 100, 1000) {

                @Override
                public void onTick(long l) {
                    updatetimer((int) l / 1000);
                }

                @Override
                public void onFinish() {

                    MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mediaPlayer.start();
                    resetTimer();

                }
            }.start();
        }
    }
    public void updatetimer(int secondsleft)
    {
        int mins=secondsleft/60;
        int secs= secondsleft -(mins*60);

        String secondstring = Integer.toString(secs);

        if(secs<=9)
        {
            secondstring="0"+secondstring;
        }

        timertextview.setText(Integer.toString(mins)+":"+secondstring);



    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerseekbar=findViewById(R.id.seekbar);
        timertextview=findViewById(R.id.timertextView);
        goButton = findViewById(R.id.button);

        timerseekbar.setMax(600);
        timerseekbar.setProgress(30);

        timerseekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                updatetimer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }
}