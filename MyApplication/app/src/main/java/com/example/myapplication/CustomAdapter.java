package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>{

    private Context context;
    private ArrayList lesson_id, quotas, service_id;

    CustomAdapter(Context context, ArrayList service_id, ArrayList lesson_id, ArrayList quotas){
        this.context=context;
        this.service_id=service_id;
        this.lesson_id=lesson_id;
        this.quotas=quotas;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_rows,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.services_id.setText(String.valueOf(service_id.get(position)));
        holder.lessons_id.setText(String.valueOf(lesson_id.get(position)));
        holder.lessons_cuotas.setText(String.valueOf(quotas.get(position)));
    }

    @Override
    public int getItemCount() {
        return service_id.size();
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder{
        TextView services_id, lessons_id, lessons_cuotas;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            services_id = itemView.findViewById(R.id.service_id);
            lessons_id = itemView.findViewById(R.id.lesson_id);
            lessons_cuotas = itemView.findViewById(R.id.lesson_cuotas);
        }
    }
}
