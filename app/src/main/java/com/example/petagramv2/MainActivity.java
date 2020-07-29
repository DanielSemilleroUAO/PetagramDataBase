package com.example.petagramv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.petagramv2.Adapter.PageAdapter;
import com.example.petagramv2.fragment.PerfilFragment;
import com.example.petagramv2.fragment.ReciclerViewFragment;
import com.example.petagramv2.pojo.Mascota;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //--------------------Objetos Layout
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    public static Button btnFavoritos;

    //-------------------Arreglos
    public static ArrayList<Mascota> favoritos;
    public static int cantidad_favoritos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        favoritos = new ArrayList<>();

        toolbar = (Toolbar) findViewById(R.id.toolbar_principal);
        if(toolbar != null){
            setSupportActionBar(toolbar);
            toolbar.setNavigationIcon(R.drawable.ic_principal); //Colocar icono en barra
        }

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        viewPager = (ViewPager) findViewById(R.id.viewPager);

        setUpViewPager();

        btnFavoritos = (Button) findViewById(R.id.btnFavoritos);

        btnFavoritos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ActivityFavoritos.class);
                startActivity(i);
            }
        });

    }

    //-----------------------------Fragments---------------------------------
    private ArrayList<Fragment> agregarFragments(){
        ArrayList<Fragment> fragments = new ArrayList<>();

        //Añadir fragments
        fragments.add(new ReciclerViewFragment());
        fragments.add(new PerfilFragment());

        return fragments;
    }
    //-----------------------------View Pager--------------------------------
    public void setUpViewPager(){
        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(),agregarFragments()));
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_home);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_pet);
    }
    //-----------------------------Menu de Opciones--------------------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_opciones,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mContacto:
                Intent i2 = new Intent(this, ActivityContacto.class);
                startActivity(i2);
                break;
            case R.id.mAcercaDe:
                Intent i3 = new Intent(this, ActivityBio.class);
                startActivity(i3);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
