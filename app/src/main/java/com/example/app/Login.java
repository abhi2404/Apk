package com.example.app;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    EditText email;
    EditText password;
    Button button;

    @Override
    public void onBackPressed(){
        Intent a=new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=findViewById(R.id.usrname);
        password=findViewById(R.id.pass);
        button = findViewById(R.id.btn);


        ApiClient.getHttpClient().addInterceptor(new AddCookiesInterceptor(Login.this));
        ApiClient.getHttpClient().addInterceptor(new ReceivedCookiesInterceptor(Login.this));
        ApiClient.setClient();
        ApiClient.setBuilder();


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(email.getText().toString()) || TextUtils.isEmpty(password.getText().toString())){
                    Toast.makeText(Login.this, "Username/Password Required", Toast.LENGTH_SHORT).show();
                }else{
                    login();
                }
            }
        });
    }


    public void login(){
        LognRequest lognRequest = new LognRequest();
        lognRequest.setUsername(email.getText().toString());
        lognRequest.setPassword(password.getText().toString());

        String username= lognRequest.getUsername();
        String password = lognRequest.getPassword();

        LoginRequest loginRequest = ApiClient.createService(LoginRequest.class,username,password);

        Call<LoginResponse> loginResponseCall = loginRequest.requestResponse();
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call,  Response<LoginResponse> response) {
                if(response.isSuccessful()) {
                    Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_LONG).show();
                     LoginResponse loginResponse =response.body();
                    Log.i("login response",loginResponse.getMsg());
                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {
                            startActivity(new Intent(Login.this,dashboard.class));
                        }
                    },500);
                }else{
                    Toast.makeText(Login.this,"Login Failed",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(Login.this,t.getLocalizedMessage() ,Toast.LENGTH_SHORT).show();

            }
        });
    }




}