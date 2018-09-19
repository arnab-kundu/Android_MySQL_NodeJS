package com.example.akundu.android_mysql_nodejs.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.akundu.android_mysql_nodejs.Constant;
import com.example.akundu.android_mysql_nodejs.MyApplication;
import com.example.akundu.android_mysql_nodejs.R;
import com.example.akundu.android_mysql_nodejs.network.API;
import com.example.akundu.android_mysql_nodejs.network.ApiResponseListener;
import com.example.akundu.android_mysql_nodejs.network.NetworkTask;
import com.example.akundu.android_mysql_nodejs.network.Parser;
import com.example.akundu.android_mysql_nodejs.pojo.Response;

public class SplashActivity extends AppCompatActivity implements GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener, ApiResponseListener {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    GestureDetectorCompat gestureDetector;
    EditText ip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        this.gestureDetector = new GestureDetectorCompat(this, this);
        gestureDetector.setOnDoubleTapListener(this);

        sharedPreferences = getSharedPreferences(Constant.SHARED_PREFERENCES_NAME, MODE_PRIVATE);
        editor = sharedPreferences.edit();

        if (MyApplication.serverIP.equalsIgnoreCase("")) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    setServerIP();
                }
            }, 3000);
        } else {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                        new NetworkTask(SplashActivity.this, API.SERVER_STATUS).execute("http://" + MyApplication.serverIP + ":3000/employee?isMobile=yes");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            Thread thread = new Thread(runnable);
            thread.start();
        }



    }

    @Override
    public void onResponse(Response response) {
        if (new Parser().getServerStatus(response.response)) {
            if (ip != null) {
                editor.putString("serverIP", ip.getText().toString().trim());
                editor.apply();
                MyApplication.serverIP = ip.getText().toString().trim();
            }
            startActivity(new Intent(SplashActivity.this, DashboardActivity.class));
            finish();
        } else {
            View view = SplashActivity.this.findViewById(R.id.view);
            Snackbar.make(view, "Would you like to change Server IP?", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Yes", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            setServerIP();
                        }
                    }).show();
        }
    }

    private void setServerIP() {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(SplashActivity.this);
        final AlertDialog alertDialog1 = alertDialog.create();
        View view = LayoutInflater.from(SplashActivity.this).inflate(R.layout.layout_alert_dialog, null);
        LinearLayout ll_alert = view.findViewById(R.id.ll_alert);
        GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setGradientType(GradientDrawable.SWEEP_GRADIENT);
        gradientDrawable.setColors(new int[]{
                Color.parseColor("#cfcfcf"),
                Color.parseColor("#000000"),
                Color.parseColor("#cfcfcf"),
                Color.parseColor("#000000"),
                Color.parseColor("#cfcfcf"),
                Color.parseColor("#000000"),
                Color.parseColor("#cfcfcf")
        });
        gradientDrawable.setCornerRadius(7f);
        ll_alert.setBackground(gradientDrawable);
        alertDialog1.setView(view);
        alertDialog1.setCancelable(false);
        ip = view.findViewById(R.id.ip);
        ip.setText(sharedPreferences.getString("serverIP", ""));
        Button submit = view.findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!ip.getText().toString().trim().equalsIgnoreCase("")) {
                    editor.putString("serverIP", ip.getText().toString().trim());
                    editor.apply();
                    new NetworkTask(SplashActivity.this, API.SERVER_STATUS).execute("http://" + ip.getText().toString().trim() + ":3000/employee?isMobile=yes");
                    alertDialog1.dismiss();
                }
            }
        });

        alertDialog1.show();


    }




    /*
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *
     * */

    @Override
    public boolean onDown(MotionEvent e) {
        Log.d("msg", "onDown");
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {
        Log.d("msg", "onShowPress");
    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        Log.d("msg", "onSingleTapUp");
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        Log.d("msg", "onScroll");
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {
        Log.d("msg", "Fling");
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        Log.d("msg", "Fling");
        //editor.putString("serverIP", "");
        //editor.apply();
        //startActivity(new Intent(SplashActivity.this, SplashActivity.class));
        //finish();
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        Log.d("msg", "onSingleTapConfirmed");

        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        Log.d("msg", "onDoubleTap");
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        Log.d("msg", "onDoubleTapEvent");
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

}
