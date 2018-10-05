package com.example.akundu.android_mysql_nodejs;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashTestActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_test);

        Animation glow_n_zoom_in = AnimationUtils.loadAnimation(this, R.anim.glow_n_zoom_in);
        Animation drag_to_top = AnimationUtils.loadAnimation(this, R.anim.drag_to_top);
        Animation zoom_out = AnimationUtils.loadAnimation(this, R.anim.zoom_out);
        Animation zoom_out_to_bottom_right_corner = AnimationUtils.loadAnimation(this, R.anim.zoom_out_to_bottom_right_corner);

        ImageView node_icon = findViewById(R.id.node_icon);
        ImageView bg_water_mark = findViewById(R.id.bg_water_mark);
        ImageView bg_photo_frame = findViewById(R.id.bg_photo_frame);
        ImageView bg_photo_frame1 = findViewById(R.id.bg_photo_frame1);

        node_icon.startAnimation(glow_n_zoom_in);
        bg_water_mark.startAnimation(drag_to_top);
        bg_photo_frame.startAnimation(zoom_out);
        bg_photo_frame1.startAnimation(zoom_out_to_bottom_right_corner);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
