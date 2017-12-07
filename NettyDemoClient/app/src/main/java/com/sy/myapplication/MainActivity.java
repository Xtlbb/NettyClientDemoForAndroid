package com.sy.myapplication;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.sy.myapplication.client.SimpleClient;
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private  String ip ;
    private Button mLogin;
    private Button mLoginOut;
    private Button mWorkPause;
    private Button mWorkOn;
    private Button mPayAble;
    private Button mPayUp;
    private Button mPayChange;
    private Button mComment;
    private Button button_connect;
    private Button button_patientinfo;
    private Button button_workpause;
    private Button button_workon;

    SimpleClient client;
    private Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        ip = "192.168.0.178";
        button_connect = (Button) findViewById(R.id.button_connect);
        button_connect.setOnClickListener(this);



        initView();
        mHandler = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                ip ="192.168.0.129";
                client=new SimpleClient();
                try {
                    client.connect(ip,8080);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
    }

    private void initView() {
        mLogin = (Button) findViewById(R.id.button_login);
        mLogin.setOnClickListener(this);

        mLoginOut = (Button) findViewById(R.id.button_outlogin);
        mLoginOut.setOnClickListener(this);

        mWorkPause = (Button) findViewById(R.id.button_workpause);
        mWorkPause.setOnClickListener(this);

        mWorkOn = (Button) findViewById(R.id.button_workon);
        mWorkOn.setOnClickListener(this);

        mPayAble = (Button) findViewById(R.id.button_payable);
        mPayAble.setOnClickListener(this);

        mPayUp = (Button) findViewById(R.id.button_payup);
        mPayUp.setOnClickListener(this);

        mPayChange = (Button) findViewById(R.id.button_paychange);
        mPayChange.setOnClickListener(this);

        mComment = (Button) findViewById(R.id.button_comment);
        mComment.setOnClickListener(this);


    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button_login:
                client.sendData("{\"cmd\":\"login\",\"data\":{\"workerid\":\"1\",\"workername\":\"aa\",\"picture\":\"http://www.....\",\"star\":\"5\"}}/r/n");

                break;
            case R.id.button_outlogin:
                client.sendData("{\"cmd\":\"loginout\",\"data\":{\"userid\":\"1\",\"username\":\"aa\"}}/r/n");

                break;
            case R.id.button_workpause:
//                client.sendData("{"cmd":"workpause","data":{"userid":"1","username":"aa"}}/r/n");

                break;
            case R.id.button_workon:
//                client.sendData();

                break;
            case  R.id.button_payable:

                client.sendData("{\"cmd\":\"payable\",\"data\":{\"fee\":\"420\" }}\r\n");

                break;
            case R.id.button_payup:

                String payup ="{\"cmd\":\"payup\",\"data\":{\"fee\":\"500\" }}\r\n";
                client.sendData(payup);

                break;
            case R.id.button_paychange:
                String paychange ="{\"cmd\":\"paychange\",\"data\":{\"fee\":\"80\" }}\r\n";
                client.sendData(paychange);

                break;
            case R.id.button_comment:
                String comment ="{\"cmd\":\"comment\",\"data\":{\"rank\":\"A\"}}\r\n";
                client.sendData(comment);

                break;
            case R.id.button_connect:
                Log.d("点击", "onClick: ");
//                mHandler.sendEmptyMessage(1);
                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        ip ="192.168.0.129";
                        client =new SimpleClient();
                        try {
                            client.connect(ip,8080);
                            Log.d("connext", "run: 8080");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                break;

        }
    }
}
