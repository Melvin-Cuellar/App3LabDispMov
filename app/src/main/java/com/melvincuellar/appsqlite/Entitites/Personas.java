package com.melvincuellar.appsqlite.Entitites;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Personas {
    @PrimaryKey(autoGenerate = true)
    public int idPersona;
    public String nombrePersona;
    public String apellidoPersona;
    public int edadPersona;

        // Métodos getter y setter para el atributo id
    public int getId() {
        return idPersona;
    }

    public void setId(int id) {
        this.idPersona = id;
    }

    @NonNull
    @Override
    public String toString() {
        return idPersona + nombrePersona + " " + apellidoPersona + " " + edadPersona;
    }
}
