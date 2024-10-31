package com.melvincuellar.appsqlite;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.melvincuellar.appsqlite.Entitites.Personas;
import com.melvincuellar.appsqlite.ViewModel.PersonasViewModel;


public class AgregarPersonaActivity extends AppCompatActivity {
    private EditText etNombre, etApellido, etEdad;
    private PersonasViewModel personaViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_agregar_persona);
        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etEdad = findViewById(R.id.etEdad);
        Button btnGuardar = findViewById(R.id.btnGuardar);

        personaViewModel = new ViewModelProvider(this).get(PersonasViewModel.class);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Llamar la función para validad que no haya campos vacíos
                if(validarCampos()){
                    String nombre = etNombre.getText().toString();
                    String apellido = etApellido.getText().toString();
                    int edad = Integer.parseInt(etEdad.getText().toString());

                    Personas persona = new Personas();

                    persona.nombrePersona = nombre;
                    persona.apellidoPersona = apellido;
                    persona.edadPersona = edad;

                    personaViewModel.insertarPersona(persona);

                    finish(); // Cierra la actividad después de guardar.
                }
            }
        });
    }

    //Función para validad que no haya campos vacíos
    private boolean validarCampos() {
        String nombre = etNombre.getText().toString().trim();
        String apellido = etApellido.getText().toString().trim();
        String edad = etEdad.getText().toString().trim();

        if (nombre.isEmpty()) {
            etNombre.setError("Campo obligatorio");
            return false;
        }
        if (apellido.isEmpty()) {
            etApellido.setError("Campo obligatorio");
            return false;
        }
        if (edad.isEmpty()) {
            etEdad.setError("Campo obligatorio");
            return false;
        }
        return true;
    }
}