package mahlabs.androidstudiol6a;

import android.content.Context;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView tvColor, tvColor2;
    private Button btnStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        instantiateComponents();
        addListener();

    }

    private void addListener() {
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Colorize().start();

            }
        });
    }

    private void instantiateComponents() {
        tvColor = (TextView) findViewById(R.id.tvColor);
        tvColor2 = (TextView) findViewById(R.id.tvColor2);
        btnStart= (Button) findViewById(R.id.btnStart);

    }

    private void changeBackground(int values) {
        tvColor.setBackgroundColor(values);
        tvColor2.setBackgroundColor(values);
    }

    private class Colorize extends Thread{

        public void run(){
            Random rng = new Random();
            int color = Color.argb(255, rng.nextInt(256),rng.nextInt(256),rng.nextInt(256));
            runOnUiThread(new SetColor(color));
            try {
                Thread.sleep(3000);
                runOnUiThread(new SetColor(0));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }


    }

    private class SetColor implements Runnable{

        private int color;

        public SetColor(int color) {
            this.color=color;
        }

        @Override
        public void run() {
            changeBackground(color);
        }
    }




}
