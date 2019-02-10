package com.example.raymond.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.util.Log;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Timer;

public class MainActivity extends AppCompatActivity {

    public TextView timerTextView;
    public SeekBar eggTimerSeekBar;
    public Button controllerButton;
    CountDownTimer eggCountDownTimer;

    public void resetTimer(){
        controllerButton.setText("Start");
        eggTimerSeekBar.setEnabled(true);
        timerTextView.setText("0:30");
        eggTimerSeekBar.setProgress(30);
        eggCountDownTimer.cancel();

    }

    public void updateTimer(int secondsLeft) {
        int minutes = (int) secondsLeft / 60;
        int seconds = secondsLeft - minutes * 60;

        timerTextView.setText(Integer.toString(minutes)+ ":" + String.format("%02d", seconds));
    }

    public void clickControllerButton(View view) {

//        check if the state is "Start" or "Stop"
        String textTimerButton = controllerButton.getText().toString();
        if("Start".equals(textTimerButton)) {
            controllerButton.setText("Stop");
            eggTimerSeekBar.setEnabled(false);
            eggCountDownTimer = new CountDownTimer( eggTimerSeekBar.getProgress() * 1000,1000){

                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);
                }

                @Override
                public void onFinish() {
                    MediaPlayer mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    mPlayer.start();
                    resetTimer();
                }
            }.start();

        } else if ("Stop".equals(textTimerButton)) {
            resetTimer();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        eggTimerSeekBar = (SeekBar) findViewById(R.id.eggTimerSeekBar);
        timerTextView = (TextView) findViewById(R.id.timerTextView);
        controllerButton = (Button) findViewById(R.id.controllerButton);

//        Set up the seekbar - start
        eggTimerSeekBar.setMax(600);
        eggTimerSeekBar.setProgress(30);

        eggTimerSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                updateTimer(progress);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
//        Set up the seekbar - end

    }
}
