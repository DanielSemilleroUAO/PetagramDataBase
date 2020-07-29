package com.example.petagramv2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.example.petagramv2.Adapter.AdapatadorMascota;
import com.example.petagramv2.pojo.Mascota;

import java.util.ArrayList;

public class ActivityFavoritos extends AppCompatActivity {

    private Toolbar toolbar;
    private RecyclerView listaFavoritos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favoritos);


        toolbar = (Toolbar) findViewById(R.id.toolbar_secundarias);

        listaFavoritos = (RecyclerView) findViewById(R.id.rvMascotasFavoritas);

        LinearLayoutManager llm2 = new LinearLayoutManager(this);
        llm2.setOrientation(LinearLayoutManager.VERTICAL);

        //GridLayoutManager glm = new GridLayoutManager(this,4);

        listaFavoritos.setLayoutManager(llm2);
        inicializarAdaptador();
        Toast.makeText(this,MainActivity.favoritos.get(0).getNombre(),Toast.LENGTH_SHORT).show();

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("       Favoritos         ");
        toolbar.setLogo(R.drawable.ic_principal);
        //toolbar.setNavigationIcon(R.drawable.ic_principal);

    }


    public void inicializarAdaptador(){
        AdapatadorMascota adaptador = new AdapatadorMascota(MainActivity.favoritos,this,0);
        listaFavoritos.setAdapter(adaptador);
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
