package com.melvincuellar.appsqlite;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.melvincuellar.appsqlite.Entitites.Personas;
import com.melvincuellar.appsqlite.ViewModel.PersonasViewModel;

public class EditarPersonaActivity extends AppCompatActivity {
    private EditText etNombre, etApellido, etEdad;
    private PersonasViewModel personaViewModel;
    private int personaId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_editar_persona);

        etNombre = findViewById(R.id.etNombre);
        etApellido = findViewById(R.id.etApellido);
        etEdad = findViewById(R.id.etEdad);
        Button btnGuardar = findViewById(R.id.btnGuardar);

        personaViewModel = new ViewModelProvider(this).get(PersonasViewModel.class);

        // Obtener el ID de la persona de los extras del intent
        personaId = getIntent().getIntExtra("PERSONA_ID", -1);
        // Cargar los datos de la persona
        if (personaId != -1) {
            personaViewModel.obtenerPersonaPorId(personaId).observe(this, persona -> {
                if (persona != null) {
                    etNombre.setText(persona.nombrePersona);
                    etApellido.setText(persona.apellidoPersona);
                    etEdad.setText(String.valueOf(persona.edadPersona));
                }
            });
        }

        btnGuardar.setOnClickListener(v -> {
            //Llamar la función para validad que no haya campos vacíos
            if(validarCampos()){
                String nombre = etNombre.getText().toString();
                String apellido = etApellido.getText().toString();
                int edad = Integer.parseInt(etEdad.getText().toString());

                // Crear objeto persona y actualizarlo
                Personas persona = new Personas();
                persona.setId(personaId);
                persona.nombrePersona = nombre;
                persona.apellidoPersona = apellido;
                persona.edadPersona = edad;

                personaViewModel.actualizarPersona(persona);

                finish(); // Cerrar la actividad después de guardar.
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