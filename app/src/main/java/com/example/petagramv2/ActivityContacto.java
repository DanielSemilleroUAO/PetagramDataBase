package com.example.petagramv2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class ActivityContacto extends AppCompatActivity implements View.OnClickListener {

    private Toolbar toolbar;
    private Button btnEnviar;
    private TextInputEditText nombre;
    private TextInputEditText correo;
    private TextInputEditText mensaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacto);


        nombre = (TextInputEditText) findViewById(R.id.nombre_ingresado);
        correo = (TextInputEditText) findViewById(R.id.email_ingresado);
        mensaje = (TextInputEditText) findViewById(R.id.descripcion_ingresado);




        btnEnviar = (Button) findViewById(R.id.siguiente);

        toolbar = (Toolbar) findViewById(R.id.toolbar_secundarias);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("       Contacto         ");
        toolbar.setLogo(R.drawable.ic_principal);


        btnEnviar.setOnClickListener(this);

    }

    private void  sendEmail(){
        String email = correo.getText().toString().trim();
        String subject = nombre.getText().toString().trim();
        String message = mensaje.getText().toString().trim();

        //Creating SendMail object
        SendMail sm = new SendMail(this, email, subject, message);

        //Executing sendmail to send email
        sm.execute();
    }
    @Override
    public void onClick(View v) {
        sendEmail();
    }
}
