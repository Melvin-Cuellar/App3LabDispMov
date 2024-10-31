package com.melvincuellar.appsqlite.DAO;
import androidx.lifecycle.LiveData; //agregar
import androidx.room.Dao; //agregar
import androidx.room.Delete;//agregar
import androidx.room.Insert;//agregar
import androidx.room.Query;//agregar
import androidx.room.Update;//agregar

import com.melvincuellar.appsqlite.Entitites.Personas;

import java.util.List;

@Dao
public interface PersonaDAO {
    @Insert
    void insertarPersona(Personas personaEntity);

    // Actualizar
    @Update
    void actualizarPersona(Personas personaEntity);

    // Eliminar
    @Delete
    void eliminarPersona(Personas personaEntity);

    //Mostrar
    @Query("SELECT * FROM PERSONAS")
    LiveData<List<Personas>> obtenerTodasLasPersonas();

    // MostrarPorId
    @Query("SELECT * FROM PERSONAS WHERE idPersona = :personaId")
    LiveData<Personas> obtenerPersonaPorId(int personaId);
}
