package com.example.kursach;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class spisokAdapter extends FirebaseRecyclerAdapter<spisok, spisokAdapter.spisoksViewholder> {

    public spisokAdapter(@NonNull FirebaseRecyclerOptions<spisok> options)
    {
        super(options);
    }

    @Override
    public void
    onBindViewHolder(@NonNull spisoksViewholder holder,int position, @NonNull spisok model)
    {

        holder.WarrantyName.setText(model.getWarrantyName());
        holder.CompanyName.setText(model.getCompanyName());
        holder.year1.setText(model.getYear1());
    }

    @NonNull
    @Override
    public spisoksViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.spisok, parent, false);
        return new spisokAdapter.spisoksViewholder(view);
    }

    class spisoksViewholder extends RecyclerView.ViewHolder {
        TextView WarrantyName, CompanyName, year1;
        public spisoksViewholder(@NonNull View itemView)
        {
            super(itemView);
            WarrantyName = itemView.findViewById(R.id.WarrantyName);
            CompanyName = itemView.findViewById(R.id.CompanyName);
            year1 = itemView.findViewById(R.id.year1);
        }
    }
}