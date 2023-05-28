package com.example.testapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class loginpage extends AppCompatActivity implements View.OnClickListener {
    EditText uname,pass;
    Button btnlogin;
    FirebaseAuth lauth;
    ProgressBar lgnpb;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginpage);

        uname=findViewById(R.id.txtuname);
        pass=findViewById(R.id.txtpass);
        lgnpb=findViewById(R.id.pblgn);
        btnlogin=findViewById(R.id.btnlogin);
        lgnpb.setVisibility(View.GONE);
        lauth=FirebaseAuth.getInstance();
        btnlogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        userLogin();
    }

    private void userLogin() {
        String uname1=uname.getText().toString().trim();
        String pass1=pass.getText().toString().trim();

        if(uname1.isEmpty()) {
            uname.setError("user name required");
            uname.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(uname1).matches())
        {
            uname.setError("Enter correct email");
            uname.requestFocus();
            return;
        }
        if(pass1.length()<6)
        {
            pass.setError("pass Should be greater than 6 character");
        }
        lgnpb.setVisibility(View.VISIBLE);

        lauth.signInWithEmailAndPassword(uname1,pass1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
//                    Toast.makeText(loginpage.this,"Loged in", Toast.LENGTH_LONG).show();
//                    lgnpb.setVisibility(View.GONE);
                    FirebaseUser user=FirebaseAuth.getInstance().getCurrentUser();
                    if(user.isEmailVerified())
                    {
                        Intent tl=new Intent(loginpage.this,homepage.class);
                        startActivity(tl);
                        finish();
                        Toast.makeText(loginpage.this,"Logedin", Toast.LENGTH_LONG).show();
                        lgnpb.setVisibility(View.GONE);
                    }else
                    {
                        user.sendEmailVerification();
                        Toast.makeText(loginpage.this,"check your Email", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(loginpage.this,"Faild to login", Toast.LENGTH_LONG).show();
                    lgnpb.setVisibility(View.GONE);
                }
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser user=lauth.getCurrentUser();
        if(user!= null)
        {
            startActivity(new Intent(loginpage.this,homepage.class));
            finish();
        }
        else
        {

        }
    }
}