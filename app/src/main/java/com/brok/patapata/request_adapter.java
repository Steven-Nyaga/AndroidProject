package com.brok.patapata;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class request_adapter extends RecyclerView.Adapter<request_adapter.MyViewHolder> {

    Context context;
    ArrayList<POJO_requests> requests;

    public request_adapter(Context c , ArrayList<POJO_requests> reqs){
        context = c;
        requests = reqs;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.notification_rows,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.litres.setText(requests.get(position).getLitres());
        holder.id.setText(requests.get(position).getId());
    }

    @Override
    public int getItemCount() {
        return requests.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView litres;
        TextView id;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            litres= (TextView) itemView.findViewById(R.id.litres);
            id = (TextView) itemView.findViewById(R.id.id);
        }
    }

}
