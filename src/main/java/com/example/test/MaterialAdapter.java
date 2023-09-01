package com.example.test;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;

import org.json.JSONArray;

import java.util.List;

public class MaterialAdapter extends RecyclerView.Adapter<MaterialAdapter.MaterialViewHolder> {
    private List<MaterialItem> materialList;

    public MaterialAdapter(List<MaterialItem> materialList, Response.Listener<JSONArray> listener) {
        this.materialList = materialList;
    }

    @NonNull
    @Override
    public MaterialViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_material, parent, false);
        return new MaterialViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MaterialViewHolder holder, int position) {
        MaterialItem item = materialList.get(position);
        holder.materialKeyTextView.setText(item.getMaterialKey());
    }

    @Override
    public int getItemCount() {
        return materialList.size();
    }

    public class MaterialViewHolder extends RecyclerView.ViewHolder {
        TextView materialKeyTextView;

        public MaterialViewHolder(@NonNull View itemView) {
            super(itemView);
            materialKeyTextView = itemView.findViewById(R.id.materialKeyTextView); // Replace with the actual ID
        }
    }
}
