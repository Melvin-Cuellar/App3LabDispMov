package com.melvincuellar.appsqlite.ViewModel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.melvincuellar.appsqlite.DAO.PersonaDAO;
import com.melvincuellar.appsqlite.Database.PersonasDatabase;
import com.melvincuellar.appsqlite.Entitites.Personas;

import java.util.List;

public class PersonasViewModel extends AndroidViewModel {

    private PersonaDAO personaDAO;
    private LiveData<List<Personas>> listaDePersonas;
        public PersonasViewModel(Application application) {
        super(application);

        PersonasDatabase database = PersonasDatabase.getInstance(application);


        personaDAO = database.personaDAO();

        listaDePersonas = personaDAO.obtenerTodasLasPersonas();
    }

    // Método para obtener la lista observable de todas las personas
    public LiveData<List<Personas>> getListaDePersonas() {
        return listaDePersonas;
    }

    // Eliminar persona de la base de datos, ejecutado en un hilo secundario
    public void eliminarPersona(Personas persona) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                personaDAO.eliminarPersona(persona);
            }
        }).start();
    }

    // Editar (actualizar) persona en la base de datos, ejecutado en un hilo secundario
    public void actualizarPersona(Personas persona) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                personaDAO.actualizarPersona(persona);
            }
        }).start();
    }

    // Método para insertar una persona en la base de datos, ejecutado en un hilo secundario
    public void insertarPersona(Personas persona) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                personaDAO.insertarPersona(persona);
            }
        }).start();
    }

    // Obtener persona por ID
    public LiveData<Personas> obtenerPersonaPorId(int personaId) {
        return personaDAO.obtenerPersonaPorId(personaId);
    }
}
