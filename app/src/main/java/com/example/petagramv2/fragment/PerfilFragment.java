package com.example.petagramv2.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.petagramv2.Adapter.AdapatadorMascota;
import com.example.petagramv2.R;
import com.example.petagramv2.pojo.Mascota;

import java.util.ArrayList;


public class PerfilFragment extends Fragment {
    private RecyclerView listaMascotas;
    ArrayList<Mascota> mascotas;

    public PerfilFragment() {
        // Required empty public constructor
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_perfil,container,false);

        listaMascotas = (RecyclerView) v.findViewById(R.id.rvFotos);

        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        GridLayoutManager glm = new GridLayoutManager(getActivity(),3);

        listaMascotas.setLayoutManager(glm);
        inicalizarMascotas();
        inicializarAdaptador();


        return v;
    }

    public void inicializarAdaptador(){
        AdapatadorMascota adaptador = new AdapatadorMascota(mascotas,getActivity(),1);
        listaMascotas.setAdapter(adaptador);
    }

    public void inicalizarMascotas(){
        mascotas = new ArrayList<Mascota>();

        mascotas.add(new Mascota(R.drawable.perro1,"Pupy",0));
        mascotas.add(new Mascota(R.drawable.perro2,"Coco",0));
        mascotas.add(new Mascota(R.drawable.perro3,"Rambo",0));
        mascotas.add(new Mascota(R.drawable.perro4,"Lolo",0));
        mascotas.add(new Mascota(R.drawable.perro5,"Pepe",0));

    }


}
