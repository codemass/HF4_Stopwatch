package com.example.bbtt.hf4_stopwatch;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;



public class StopwatchActivity extends AppCompatActivity {

    private int seconds=0; //Количество секунд на секундомере
    private boolean running; //Секундомер работает?
    private boolean wasRunning; //Хранит состояние работал ли таймер перед выхова onStop()

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        if (savedInstanceState != null) {
            seconds = savedInstanceState.getInt("seconds");
            running = savedInstanceState.getBoolean("running");
            wasRunning = savedInstanceState.getBoolean("wasRunning");//Восстановить состояние переменной wasRunning, если активность создается заново
        }
        runTimer(); //Для обновления секундомера
    }

    @Override
    protected void onPause() {
        super.onPause();
        wasRunning = running;
        running = false;
    }

    @Override
    protected void onResume () {
        super.onResume();
        if (wasRunning) {
            running = true;
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState); //После того, как добавил тут super, все заработало.
        savedInstanceState.putInt("seconds", seconds);
        savedInstanceState.putBoolean("running", running);
        savedInstanceState.putBoolean("wasRunning", wasRunning);
    }


    //Запустить секундомер при щелчке на кнопке Start
    public void onClickStart(View view) {
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

    private void runTimer () {
        final TextView timeViev = (TextView) findViewById(R.id.time_view);
        final Handler handler = new Handler();
        handler.post(new Runnable () { //Использовать Handler для передачи кода на выполнение
            @Override
            public void run () {
                int hours = seconds/3600;
                int minutes = (seconds%3600)/60;
                int secs = seconds%60;
                String time = String.format("%d:%02d:%02d", hours, minutes, secs); //Отформатировать seconds в часы, минуты и секунды.
                timeViev.setText(time); //Задать текст надписи
                if (running) {
                    seconds++;  //Если значение running инстинно, то увеличить переменную seconds.
                }
                handler.postDelayed(this, 1000); //Запланировать повторное выполнение кода с задержкой в 1 секунду.
            }
        });
    }
}
