package com.project.uber;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Switch;

import com.parse.LogInCallback;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseException;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        if(ParseUser.getCurrentUser()==null){
            ParseAnonymousUtils.logIn(new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if(e==null){
                        Log.i("Login","Anonymous login");
                        redirectUserActivity();
                    }else {
                        Log.i("Login","Anonymous login failed");
                    }
                }
            });
        }

    }
    public void getStarted(View view){
        Switch userSwitch = (Switch) findViewById(R.id.userSwitch);
        Log.i("switch", String.valueOf(userSwitch.isChecked()));

        String userType = "Rider";
        if(userSwitch.isChecked()){
            userType = "Driver";
        }
        ParseUser.getCurrentUser().put("riderOrDriver",userType);
        Log.i("riderOrDriver", userType);
        redirectUserActivity();
    }
    public void redirectUserActivity(){
        if(ParseUser.getCurrentUser().get("riderOrDriver")=="Rider"){
            Intent intent = new Intent(this,RiderActivity.class);
            startActivity(intent);
        }else{

        }

    }
}