package com.example.testapp;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ismaeldivita.chipnavigation.ChipNavigationBar;

public class homepage extends AppCompatActivity implements ChipNavigationBar.OnItemSelectedListener {
//     ActivityMainBinding binding;
      BottomNavigationView nav;
      Menu mnu;
      home_frgmnt home=new home_frgmnt();
      add  add=new add();
      chat chats=new chat();
      p_details pdetails=new p_details();
      ChipNavigationBar cnb;
      delete ty=new delete();
      tempfrg tmp=new tempfrg();
      profile_frgmnt profile=new profile_frgmnt();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(R.layout.activity_homepage);
        cnb=findViewById(R.id.btmnav);
        cnb.setOnItemSelectedListener(this);
        cnb.setItemSelected(R.id.home,true);
        getSupportFragmentManager().beginTransaction().replace(R.id.frm_layout,home).commit();
    }
    private void loadFragment(Fragment fragment) {

        if(fragment!=null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.frm_layout,fragment).commit();
        }
        else
        {
            Toast.makeText(homepage.this,"Fragment couldnt load",Toast.LENGTH_LONG).show();
        }
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.tab_manu,menu);
        return true;
    }
    @Override
    public void onItemSelected(int i) {
        Fragment frgment;
        switch (i)
        {
            case R.id.home:
                getSupportFragmentManager().beginTransaction().replace(R.id.frm_layout,home).commit();
                break;

            case R.id.add:
                getSupportFragmentManager().beginTransaction().replace(R.id.frm_layout,add).commit();
                break;
            case R.id.chat:
                getSupportFragmentManager().beginTransaction().replace(R.id.frm_layout,tmp).commit();
                break;
            case R.id.profile:
                getSupportFragmentManager().beginTransaction().replace(R.id.frm_layout,profile).commit();
                break;

        }
    }
}