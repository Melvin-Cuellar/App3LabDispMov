package com.melvincuellar.appsqlite;


import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.melvincuellar.appsqlite.Entitites.Personas;

import java.util.List;

public class PersonaAdapter extends RecyclerView.Adapter<PersonaAdapter.PersonaViewHolder> {

    private List<Personas> listaPersonas;
    private OnItemClickListener clickListener;
    private OnItemLongClickListener longClickListener;

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    public interface OnItemLongClickListener {
        void onItemLongClick(int position);
    }

    public void setPersonasList(List<Personas> personas) {
        this.listaPersonas = personas;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.clickListener = listener;
    }

    public void setOnItemLongClickListener(OnItemLongClickListener listener) {
        this.longClickListener = listener;
    }

    public Personas getPersonaAt(int position) {
        return listaPersonas.get(position);
    }
    @NonNull
    @Override
    public PersonaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_persona, parent, false);
        return new PersonaViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(@NonNull PersonaViewHolder holder, int position) {
        Personas persona = listaPersonas.get(position);
        holder.tvNombre.setText("Nombre: " + persona.nombrePersona);
        holder.tvApellido.setText("Apellido: " + persona.apellidoPersona);
        holder.tvEdad.setText("Edad: " + String.valueOf(persona.edadPersona));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (clickListener != null) {
                    clickListener.onItemClick(position);
                }
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (clickListener != null && position != RecyclerView.NO_POSITION) {
                    clickListener.onItemClick(position);
                }
            }
        });

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int position = holder.getAdapterPosition();
                if (longClickListener != null && position != RecyclerView.NO_POSITION) {
                    // Crear el diálogo para Eliminar
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setTitle("Eliminar Tarea");
                    builder.setMessage("¿Seguro que quieres eliminar esta tarea?");
                    builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            longClickListener.onItemLongClick(position); // Eliminar la tarea
                            Toast.makeText(v.getContext(), "Tarea eliminada", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setNegativeButton("No", null);
                    builder.show();
                    return true;
                }
                return false;
            }
        });


        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (longClickListener != null) {
                    // Crear el diálogo para Eliminar
                    AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                    builder.setTitle("Eliminar Tarea");
                    builder.setMessage("¿Seguro que quieres eliminar esta tarea?");
                    builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            longClickListener.onItemLongClick(position); // Eliminar la tarea
                            Toast.makeText(v.getContext(), "Tarea eliminada", Toast.LENGTH_SHORT).show();
                        }
                    });
                    builder.setNegativeButton("No", null);
                    builder.show();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return listaPersonas != null ? listaPersonas.size() : 0;
    }

    static class PersonaViewHolder extends RecyclerView.ViewHolder {
        TextView tvNombre;
        TextView tvApellido;
        TextView tvEdad;

        PersonaViewHolder(View itemView) {
            super(itemView);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvApellido = itemView.findViewById(R.id.tvApellido);
            tvEdad = itemView.findViewById(R.id.tvEdad);
        }
    }
}
