package com.example.petagramv2.Adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petagramv2.MainActivity;
import com.example.petagramv2.R;
import com.example.petagramv2.db.ConstructorMascotas;
import com.example.petagramv2.pojo.Mascota;

import java.util.ArrayList;

public class AdapatadorMascota extends RecyclerView.Adapter<AdapatadorMascota.MascotaViewHolder>{

    ArrayList<Mascota> mascotas;
    Activity activity;
    int opc = 0;

    public AdapatadorMascota(ArrayList<Mascota> mascotas, Activity activity, int opc) {
        this.mascotas = mascotas;
        this.activity = activity;
        this.opc = opc;
    }

    @NonNull
    @Override
    public MascotaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        if(opc == 0) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_mascota, parent, false);
            }
        else {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_fotosperfil, parent, false);
        }
        return new MascotaViewHolder(v,opc);
    }

    @Override
    public void onBindViewHolder(@NonNull final MascotaViewHolder mascotaViewHolder, final int position) {


            if(opc == 0) {
                final Mascota mascota = mascotas.get(position);
                mascotaViewHolder.imgFoto.setImageResource(mascota.getFoto());
                mascotaViewHolder.tvNombre.setText(mascota.getNombre());
                mascotaViewHolder.tvRating.setText(String.valueOf(mascota.getRating()));

                if (mascota.getRating() > 0) {
                    mascotaViewHolder.imgLike.setImageResource(R.drawable.ic_likes);
                }

                mascotaViewHolder.imgFoto.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //Toast.makeText(activity,"Diste like a " + mascota.getNombre(),Toast.LENGTH_SHORT).show();

                        int likes = mascota.getRating();
                        likes += 1;
                        mascotas.get(position).setRating(likes);
                        mascotaViewHolder.tvRating.setText(String.valueOf(mascota.getRating()));
                        mascotaViewHolder.imgLike.setImageResource(R.drawable.ic_likes);
                        //MainActivity.btnFavoritos.setText(String.valueOf(MainActivity.cantidad_favoritos));


                        boolean favorito_esta = true;

                        for (int i = 0; i < MainActivity.favoritos.size(); i++) {
                            if (MainActivity.favoritos.get(i).getNombre() == mascota.getNombre()) {
                                MainActivity.favoritos.get(i).setRating(likes);
                                favorito_esta = false;
                            }
                        }

                        if (favorito_esta) {
                            MainActivity.cantidad_favoritos += 1;
                            MainActivity.btnFavoritos.setText(String.valueOf(MainActivity.cantidad_favoritos));
                            ConstructorMascotas constructorMascotas = new ConstructorMascotas(activity);
                            constructorMascotas.addRating(mascota);
                            constructorMascotas.addFavorito(mascota);

                            //MainActivity.favoritos.add(new Mascota(mascota.getFoto(), mascota.getNombre(), mascota.getRating()));

                            //Toast.makeText(activity,"Diste like a " + MainActivity.favoritos.get(0).getNombre(),Toast.LENGTH_SHORT).show();
                        }


                    }
                });
            }
            else{
                final Mascota mascota = mascotas.get(position);
                mascotaViewHolder.imgFoto.setImageResource(mascota.getFoto());
                mascotaViewHolder.tvRating.setText(String.valueOf(mascota.getRating()));
                if (mascota.getRating() > 0) {
                    mascotaViewHolder.imgLike.setImageResource(R.drawable.ic_likes);
                }
            }
    }

    @Override
    public int getItemCount() {
        return mascotas.size();
    }

    //------------------------------View Holder

    public static class MascotaViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgFoto;
        private TextView tvNombre;
        private TextView tvRating;
        private ImageButton btnLike;
        private ImageView imgLike;

        public MascotaViewHolder(@NonNull View itemView, int opc) {
            super(itemView);

            if(opc == 0){
                imgFoto = (ImageView) itemView.findViewById(R.id.ImgMascota);
                tvNombre = (TextView) itemView.findViewById(R.id.tvNombre);
                tvRating = (TextView) itemView.findViewById(R.id.tvRating);
                btnLike = (ImageButton) itemView.findViewById(R.id.bnLike);
                imgLike = (ImageView) itemView.findViewById(R.id.imageRating);
            }
            else{

                imgFoto = (ImageView) itemView.findViewById(R.id.ImgMascota);
                tvRating = (TextView) itemView.findViewById(R.id.tvRating);
                imgLike = (ImageView) itemView.findViewById(R.id.imageRating);

            }

        }
    }
}
