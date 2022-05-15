package com.example.WarrantyPeriods;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


public class MainActivity extends AppCompatActivity {

    public Button BtnHome;
    public Button BtnSettings;
    public Button BtnSpisok;
    public Button BtnCreate;
    public Button BtnThemes;
    public Button BtnProfile;
    public TextView app_name;
    public CalendarView calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //кнопки
        BtnHome = findViewById(R.id.BtnHome);
        BtnSettings = findViewById(R.id.BtnSettings);
        BtnSpisok = findViewById(R.id.BtnSpisok);
        BtnThemes = findViewById(R.id.BtnThemes);
        BtnProfile = findViewById(R.id.BtnProfile);
        BtnCreate = findViewById(R.id.BtnCreate);
        //другое
        app_name = findViewById(R.id.app_name);
        calendar = findViewById(R.id.calend);

        BtnSpisok.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SpisokActivity.class);
            startActivity(intent);
        });
    }

    public void onClick(View view) {
        Fragment fragment = null;

        switch (view.getId()) {
            case (R.id.BtnHome):
                fragment = new HomeFragment();
                break;
            case (R.id.BtnSettings):
                fragment = new SettingsFragment();
                break;
            case (R.id.BtnThemes):
                fragment = new ThemesFragment();
                break;
            case (R.id.BtnProfile):
                fragment = new ProfileFragment();
                break;
        }
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        assert fragment != null;
        ft.replace(R.id.HomeFragment, fragment);
        ft.commit();
    }
}