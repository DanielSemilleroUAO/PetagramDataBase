package com.example.petagramv2.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petagramv2.Adapter.AdapatadorMascota;
import com.example.petagramv2.MainActivity;
import com.example.petagramv2.R;
import com.example.petagramv2.pojo.Mascota;
import com.example.petagramv2.presentador.IRecyclerViewFragmentPresenter;
import com.example.petagramv2.presentador.RecyclerViewFragmentPresenter;

import java.util.ArrayList;

public class ReciclerViewFragment extends Fragment implements IRecyclerViewFragment {

    private RecyclerView listaMascotas;
    ArrayList<Mascota> mascotas;
    private IRecyclerViewFragmentPresenter presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.fragment_recyclerview,container,false);

        listaMascotas = (RecyclerView) v.findViewById(R.id.rvMascotas);
        presenter = new RecyclerViewFragmentPresenter(this, getContext(),0);

        //inicalizarMascotas();
        //inicializarAdaptador();


        return v;
    }

    public void inicializarAdaptador(){
        AdapatadorMascota adaptador = new AdapatadorMascota(mascotas,getActivity(),0);
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

    @Override
    public void generarLinearLayout() {
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        listaMascotas.setLayoutManager(llm);
    }

    @Override
    public AdapatadorMascota crearAdapatador(ArrayList<Mascota> mascotas) {
        AdapatadorMascota adaptador = new AdapatadorMascota(mascotas,getActivity(),0);
        return adaptador;
    }

    @Override
    public void inicializarAdaptadorRV(AdapatadorMascota adaptador) {
        listaMascotas.setAdapter(adaptador);
    }
}
