package com.sy.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.sy.myapplication.service.HelloServer;

public class ServicesTestActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_service_act);
        initCont();
    }

    private void initCont() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    new HelloServer(8080).bind();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
