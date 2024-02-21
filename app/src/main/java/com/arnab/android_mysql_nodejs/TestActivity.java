package com.arnab.android_mysql_nodejs;

import android.animation.ValueAnimator;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.arnab.android_mysql_nodejs.view.DigitalView;

import java.util.Random;

public class TestActivity extends AppCompatActivity {

    DigitalView digitalView0, digitalView1, digitalView2;
    int count = 0;
    int count1 = 0;
    int count2 = 0;
    int range;
    Thread thread0;
    boolean IsThreadRunning;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        digitalView0 = findViewById(R.id.digit0);
        digitalView1 = findViewById(R.id.digit1);
        digitalView2 = findViewById(R.id.digit2);
textView = findViewById(R.id.textview);

       startCountAnimation();

       /* Runnable runnable = new Runnable() {
            @Override
            public void run() {
                while (true)
                    try {
                        Thread.sleep(100);
                        digitalView0.draw(count++);
                        if (count == 10)
                            count = 0;
                        Log.d("msg", "" + count);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Log.e("msg", "" + e);
                    }
            }
        };

        Thread thread = new Thread(runnable);
        thread.start();

        Runnable runnable1 = new Runnable() {
            @Override
            public void run() {
                while (true)
                    try {
                        Thread.sleep(1000);
                        digitalView1.draw(count1++);
                        if (count1 == 10) {
                            count1 = 0;
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Log.e("msg", "" + e);
                    }
            }
        };

        Thread thread1 = new Thread(runnable1);
        thread1.start();

        Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                while (true)
                    try {
                        Thread.sleep(10000);
                        digitalView2.draw(count2++);
                        if (count2 == 10)
                            count2 = 0;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Log.e("msg", "" + e);
                    }
            }
        };

        Thread thread2 = new Thread(runnable2);
        thread2.start();*/
    }


    @Override
    protected void onResume() {
        super.onResume();

        IsThreadRunning = true;
        digitalView0.draw(0);
        digitalView1.draw(0);
        digitalView2.draw(0);
        count = count1 = count2 = 0;
        Random random = new Random();
        range = random.nextInt(999);
        Log.d("msg", "range:" + range);
        Runnable runnable0 = new Runnable() {
            @Override
            public void run() {

                Log.d("msg", Thread.currentThread().getName());
                Log.d("msg", IsThreadRunning + "");

                for (int i = 0; i <= range; i++) {
                    try {
                        Thread.sleep(500);
                        if (!IsThreadRunning) {
                            break;
                        }
                        digitalView0.draw(++count);
                        if (count == 10) {
                            count = 0;
                            digitalView1.draw(++count1);
                        }
                        if (count1 == 10) {
                            count1 = 0;
                            digitalView2.draw(++count2);
                        }
                        if (count2 == 10) {
                            count2 = 0;
                        }
                        //Log.d("msg", "" + count);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Log.e("msg", "" + e);
                    }
                }
            }
        };
        thread0 = new Thread(runnable0);
        thread0.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("msg", "onPause");
        IsThreadRunning = false;
    }


    private void startCountAnimation() {
        ValueAnimator animator = ValueAnimator.ofInt(0, 600);
        animator.setDuration(5000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator animation) {
                textView.setText(animation.getAnimatedValue().toString());
            }
        });
        animator.start();
    }
}
