package com.example.bbtt.hf4_stopwatch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StopwatchActivity extends AppCompatActivity {

    private int seconds=0;
    private boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
    }

    //Запустить секундомер при щелчке на кнопке Start
    public void onClickSrart(View view) {
        running = true; //Запустить секундомер
    }

    //Запустить секундомер при щелчке на кнопке Stop
    public void onClickStop(View view) {
        running = false; //Остановить секундомер
    }

    //Обнулить секундомер при щелчке на кнопке Reset
    public void onClickReset(View view) {
        running = false; //Остановить секундомер
        seconds = 0; //И присвоить seconds значение 0
    }
}
