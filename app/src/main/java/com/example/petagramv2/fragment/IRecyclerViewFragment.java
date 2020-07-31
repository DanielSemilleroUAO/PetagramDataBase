package com.example.petagramv2.fragment;

import com.example.petagramv2.Adapter.AdapatadorMascota;
import com.example.petagramv2.pojo.Mascota;

import java.util.ArrayList;

public interface IRecyclerViewFragment {
    public void generarLinearLayout();

    public AdapatadorMascota crearAdapatador(ArrayList<Mascota> mascotas);

    public void inicializarAdaptadorRV(AdapatadorMascota adapatador);
}
