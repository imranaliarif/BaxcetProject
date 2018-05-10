package com.codiansoft.baxcetproject.Activities;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RelativeLayout;

import com.codiansoft.baxcetproject.R;

import static android.view.View.GONE;

public class LoginActivityNew extends AppCompatActivity {

    RelativeLayout layoutLogin;
    RelativeLayout layoutSignup;
    Activity activity;
    RadioButton radioBtnLogin , radioBtnSignup;
    Button btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_new);
        activity=this;
        init();
        radioBtnLogin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    layoutLogin.setVisibility(View.VISIBLE);
                    layoutSignup.setVisibility(GONE);

                }
            }
        });
        radioBtnSignup.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
               if(b) {
                   layoutSignup.setVisibility(View.VISIBLE);
                   layoutLogin.setVisibility(GONE);
               }
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1=new Intent(activity , MainActivity.class);
                startActivity(intent1);
            }
        });
    }
    void init()
    {
        layoutLogin=(RelativeLayout)findViewById(R.id.layout_login);
        layoutSignup=(RelativeLayout)findViewById(R.id.layout_signup);
        radioBtnLogin=(RadioButton)findViewById(R.id.radiobtn_login);
        radioBtnLogin.setChecked(true);
        radioBtnSignup=(RadioButton)findViewById(R.id.radiobtn_signup);
        btn_login=(Button)findViewById(R.id.btn_login);

    }

}
