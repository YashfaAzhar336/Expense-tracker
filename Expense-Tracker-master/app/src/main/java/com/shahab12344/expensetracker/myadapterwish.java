package com.shahab12344.expensetracker;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class myadapterwish extends RecyclerView.Adapter<myadapterwish.myviewholder>
{
    ArrayList<model> dataholder;

    public myadapterwish(ArrayList<model> dataholder) {
        this.dataholder = dataholder;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position)
    {
        holder.dname.setText("Wish Type: "+dataholder.get(position).getType());
        holder.dcontact.setText("Wish Category: "+dataholder.get(position).getDate());
        holder.demail.setText("Wish Amount: "+String.valueOf((int) dataholder.get(position).getAmount()));
    }

    @Override
    public int getItemCount() {
        return dataholder.size();
    }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView dname,dcontact,demail;

        public myviewholder(@NonNull View itemView)
        {
            super(itemView);
            dname=(TextView)itemView.findViewById(R.id.displayname);
            dcontact=(TextView)itemView.findViewById(R.id.displaycontact);
            demail=(TextView)itemView.findViewById(R.id.displayemail);
        }
    }

}
