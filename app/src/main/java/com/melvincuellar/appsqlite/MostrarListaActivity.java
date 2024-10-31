package com.melvincuellar.appsqlite;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.melvincuellar.appsqlite.Entitites.Personas;
import com.melvincuellar.appsqlite.ViewModel.PersonasViewModel;


public class MostrarListaActivity extends AppCompatActivity {

    private PersonasViewModel personaViewModel;
    private PersonaAdapter personaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_mostrar_lista);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        personaAdapter = new PersonaAdapter();
        recyclerView.setAdapter(personaAdapter);
        personaViewModel = new ViewModelProvider(this).get(PersonasViewModel.class);
        personaViewModel.getListaDePersonas().observe(this, personas -> {
            personaAdapter.setPersonasList(personas);
        });

        //setOnItemClickListener update
        personaAdapter.setOnItemClickListener(position -> {
            // Obtener la persona seleccionada y pasar su ID a la actividad de edición
            Personas persona = personaAdapter.getPersonaAt(position);
            Intent intent = new Intent(MostrarListaActivity.this, EditarPersonaActivity.class);
            intent.putExtra("PERSONA_ID", persona.getId()); // Asegúrate de tener un método getId en la clase Personas
            startActivity(intent);
        });

        //setOnItemLongClickListener eliminar
        personaAdapter.setOnItemLongClickListener(position -> {
            // Eliminar la persona seleccionada
            Personas persona = personaAdapter.getPersonaAt(position);
            personaViewModel.eliminarPersona(persona); // Implementa este método en tu ViewModel
        });
    }
}