package com.example.petagramv2.presentador;

import android.content.Context;

import com.example.petagramv2.db.ConstructorMascotas;
import com.example.petagramv2.fragment.IRecyclerViewFragment;
import com.example.petagramv2.pojo.Mascota;

import java.util.ArrayList;

public class RecyclerViewFragmentPresenter implements IRecyclerViewFragmentPresenter {

    private IRecyclerViewFragment iRecyclerViewFragment;
    private Context context;
    private ConstructorMascotas constructorMascotas;
    private ArrayList<Mascota> mascotas;

    public RecyclerViewFragmentPresenter(IRecyclerViewFragment iRecyclerViewFragment, Context context, int opc) {
        this.iRecyclerViewFragment = iRecyclerViewFragment;
        this.context = context;
        if(opc == 0) {
            obtenerMascotasBaseDatos();
        }else{
            obtenerMascotasFavoritos();
        }
    }

    @Override
    public void obtenerMascotasBaseDatos() {
        constructorMascotas = new ConstructorMascotas(context);
        mascotas = constructorMascotas.obtenerDatos();
        mostrarContactosRV();
    }


    @Override
    public void mostrarContactosRV() {
        iRecyclerViewFragment.inicializarAdaptadorRV(iRecyclerViewFragment.crearAdapatador(mascotas));
        iRecyclerViewFragment.generarLinearLayout();
    }

    @Override
    public void obtenerMascotasFavoritos() {
        constructorMascotas = new ConstructorMascotas(context);
        mascotas = constructorMascotas.obtenerFavoritos();
        mostrarContactosRV();
    }
}
