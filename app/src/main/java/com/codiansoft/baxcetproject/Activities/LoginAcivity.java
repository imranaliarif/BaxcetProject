package com.codiansoft.baxcetproject.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.codiansoft.baxcetproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginAcivity extends AppCompatActivity {

    @BindView(R.id.btn_create_account)
    Button btn_create_account;
    @BindView(R.id.btn_login)
    Button btn_login;
    Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_acivity);

        activity=this;
        ButterKnife.bind(activity);



    }
    @OnClick({R.id.btn_create_account , R.id.btn_login})
    void onClick(View view)
    {
        switch (view.getId())
        {
            case R.id.btn_create_account:
                Intent intent=new Intent(activity , RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_login:
                Intent intent1=new Intent(activity , MainActivity.class);
                startActivity(intent1);
                break;
        }
    }
}
