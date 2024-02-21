package com.arnab.android_mysql_nodejs.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.text.format.Formatter;
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

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;

import com.arnab.android_mysql_nodejs.Constant;
import com.arnab.android_mysql_nodejs.MyApplication;
import com.arnab.android_mysql_nodejs.R;
import com.arnab.android_mysql_nodejs.network.API;
import com.arnab.android_mysql_nodejs.network.ApiResponseListener;
import com.arnab.android_mysql_nodejs.network.NetworkTask;
import com.arnab.android_mysql_nodejs.network.Parser;
import com.arnab.android_mysql_nodejs.network.RequestType;
import com.arnab.android_mysql_nodejs.pojo.Response;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;

public class SplashActivity extends AppCompatActivity implements GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener, ApiResponseListener {

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    GestureDetectorCompat gestureDetector;
    EditText ip;
    JSONObject jsonObject;

    //////////////////////////////
    // MAC Address
    //////////////////////////////
    private String getMacAddr() {
        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {
                    res1.append(String.format("%02X:", b));
                }

                if (res1.length() > 0) {
                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
        }
        return "02:00:00:00:00:00";
    }

    //////////////////////////////
    // Returns the consumer friendly device name
    //////////////////////////////
    public String getDeviceName() {
        String manufacturer = Build.MANUFACTURER;
        String model = Build.MODEL;
        if (model.startsWith(manufacturer)) {
            return capitalize(model);
        }
        return capitalize(manufacturer) + " " + model;
    }

    private String capitalize(String str) {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        char[] arr = str.toCharArray();
        boolean capitalizeNext = true;

        StringBuilder phrase = new StringBuilder();
        for (char c : arr) {
            if (capitalizeNext && Character.isLetter(c)) {
                phrase.append(Character.toUpperCase(c));
                capitalizeNext = false;
                continue;
            } else if (Character.isWhitespace(c)) {
                capitalizeNext = true;
            }
            phrase.append(c);
        }

        return phrase.toString();
    }

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


        //////////////////////////////
        // IP Address and MAC Address
        //////////////////////////////
        WifiManager manager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        String macAddress = info.getMacAddress();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
            Log.d("msg", "MAC Address: " + getMacAddr());
        else
            Log.d("msg", "MAC Address: " + macAddress);
        String ip = Formatter.formatIpAddress(info.getIpAddress());
        Log.d("msg", "IP Address: " + ip);
        Log.d("msg", "Device Model Name: " + getDeviceName());
        jsonObject = new JSONObject();
        try {
            jsonObject.accumulate("DeviceName", getDeviceName());
            jsonObject.accumulate("IP_Address", Formatter.formatIpAddress(info.getIpAddress()));
            jsonObject.accumulate("MacAddress", Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ? getMacAddr() : macAddress);
        } catch (JSONException e) {
            e.printStackTrace();
        }

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
                        new NetworkTask(SplashActivity.this, API.LOG_BOOK, RequestType.POST)
                                .execute("http://" + MyApplication.serverIP + ":3000/logbook", jsonObject.toString());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Log.e("msg InterruptedExc", "" + e);
                    }
                }
            };
            Thread thread = new Thread(runnable);
            thread.start();
        }


    }

    @Override
    public void onResponse(Response response) {
        if (response.api_name == API.SERVER_STATUS) {
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
                    new NetworkTask(SplashActivity.this, API.LOG_BOOK, RequestType.POST)
                            .execute("http://" + ip.getText().toString().trim() + ":3000/logbook", jsonObject.toString());
                    alertDialog1.dismiss();
                }
            }
        });

        alertDialog1.show();


    }

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
