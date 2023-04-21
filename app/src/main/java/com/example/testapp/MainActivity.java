package com.example.testapp;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, View.OnLongClickListener {
    EditText name1,mobno1,email,password,age1;
    Button signup;
    FirebaseAuth auth;
    FirebaseUser user;
    DatabaseReference ref;
    FirebaseDatabase firedata;
    TextView lgntv;
    ProgressBar pb;
    Dialog d;

    @SuppressLint("MissingInflatedId")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        auth=FirebaseAuth.getInstance();
        firedata=FirebaseDatabase.getInstance();

//        if(auth.getCurrentUser()!=null)
//        {
//            finish();
//            return;
//        }
        lgntv=(TextView)findViewById(R.id.tv);
        name1=findViewById(R.id.name);
        mobno1=findViewById(R.id.mobno);
        email=findViewById(R.id.email);
        password=findViewById(R.id.pass);
        age1=findViewById(R.id.age);
        signup=findViewById(R.id.btn_signup);
        pb=findViewById(R.id.pb1);
        pb.setVisibility(View.GONE);
        d=new Dialog(MainActivity.this);
        d.setContentView(R.layout.dialog_loading);
        signup.setOnClickListener(this);
        signup.setOnLongClickListener(this);
    }
      public void logingo(View v)
      {
          Intent tlogin=new Intent(MainActivity.this,loginpage.class);
          startActivity(tlogin);
      }

    @Override
    public void onClick(View v) {
        if(d.getWindow()!=null)
        {
            d.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        d.show();
        performAuth();
    }


    private void performAuth() {
        String name = name1.getText().toString();
        String mobno = mobno1.getText().toString();
        String e_mail = email.getText().toString();
        String pass = password.getText().toString();
        String age = age1.getText().toString();

        if (name.isEmpty()) {
            name1.setError("name is required");
            name1.requestFocus();
            return;
        }
        if (mobno.isEmpty()) {
            mobno1.setError("Mobile number required");
            mobno1.requestFocus();
            return;
        }
        if(mobno.length()<10)
        {
            mobno1.setError("10 digits required");
            mobno1.requestFocus();
            return;
        }
        if (e_mail.isEmpty()) {
            email.setError("email is required");
            email.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(e_mail).matches()) {
            email.setError("enter email correctly");
        }
        if (pass.isEmpty()) {
            password.setError("password is required");
            password.requestFocus();
            return;
        }
        if (pass.length() < 6) {
            password.setError("password should be greater than 6 character");
            password.requestFocus();
            return;
        }
        if (age.isEmpty()) {
            age1.setError("enter age ");
            age1.requestFocus();
            return;
        }
//        pb.setVisibility(View.VISIBLE);

        auth.createUserWithEmailAndPassword(e_mail,pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            User user1=new User(name,mobno,e_mail,age);
                            firedata.getReference("user")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful())
                                            {
//                                                String id=auth.getCurrentUser().getUid();
//                                               firedata.getReference("users").child(id).setValue(user1);

                                                pb.setVisibility(View.GONE);
                                                Toast.makeText(MainActivity.this,"Succesfully registered",Toast.LENGTH_LONG).show();
                                                Intent ti=new Intent(MainActivity.this,loginpage.class);
                                                startActivity(ti);
                                                finish();
                                                d.dismiss();
                                            }
                                            else
                                            {
                                                Toast.makeText(MainActivity.this,"try again",Toast.LENGTH_LONG).show();
                                                pb.setVisibility(View.GONE);

                                            }
                                        }
                                    });
                        }else
                        {
                            Toast.makeText(MainActivity.this,"USER already exist ",Toast.LENGTH_LONG).show();
                            pb.setVisibility(View.GONE);
                        }
                    }
                });
    }

    @Override
    public boolean onLongClick(View view) {
        Intent ti=new Intent(MainActivity.this,homepage.class);
        startActivity(ti);
        return false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        user =auth.getCurrentUser();
        if(user!=null)
        {
            startActivity(new Intent(MainActivity.this,homepage.class));
            finish();
        }
    }
}
