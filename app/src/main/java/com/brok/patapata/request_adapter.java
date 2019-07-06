package com.brok.patapata;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;



public class request_adapter extends RecyclerView.Adapter<request_adapter.MyViewHolder> {
    private static final String TAG = "request_adapter";
    private Button yes;
    private Button no;
    LinearLayout parentLayout;
    Context context;
    ArrayList<POJO_requests> requests;

    public request_adapter(Context c, ArrayList<POJO_requests> reqs) {
        context = c;
        requests = reqs;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.notification_rows, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.litres.setText(requests.get(position).getLitres());
        holder.Userid.setText(requests.get(position).getUserid());
        holder.parentLayout.setOnClickListener(new DoubleClickListener() {
            @Override
            public void onDoubleClick() {
                //Toast.makeText(context, "Yes", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, driverMaps.class);
                intent.putExtra("User ID", requests.get(position).getUserid());
            }
        });
        holder.parentLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(context, "No", Toast.LENGTH_SHORT).show();
                return true;
            }
        });






    }


    @Override
    public int getItemCount() {
        return requests.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView litres;
        TextView Userid;
        LinearLayout parentLayout;



        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            litres = (TextView) itemView.findViewById(R.id.litres);
            Userid = (TextView) itemView.findViewById(R.id.id);
            parentLayout = itemView.findViewById(R.id.parentLayout);
//yes = (Button) itemView.findViewById(R.id.yes);
//            no= (Button) itemView.findViewById(R.id.no);
        }









}
}
