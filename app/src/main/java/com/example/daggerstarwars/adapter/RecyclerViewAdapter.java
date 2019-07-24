package com.example.daggerstarwars.adapter;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.daggerstarwars.R;
import com.example.daggerstarwars.pojo.StarWars;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class RecyclerViewAdapter
        extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private List<StarWars.People> data;
    private RecyclerViewAdapter.ClickListener clickListener;

    @Inject
    public RecyclerViewAdapter (RecyclerViewAdapter.ClickListener clickListener){
        this.clickListener = clickListener;
        data = new ArrayList<>();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(LayoutInflater.from(viewGroup.getContext())
        .inflate(R.layout.recycler_view_list_row, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.textName.setText(data.get(i).name);
        viewHolder.textBirthYear.setText(data.get(i).birthYear);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.textName);
            textBirthYear = itemView.findViewById(R.id.textBirthYear);
            constraintLayoutContainer = itemView.findViewById(R.id.constraintLayout);
            constraintLayoutContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.launchIntent(
                            data.get(getAdapterPosition())
                            .films.get(0)
                    );
                }
            });
        }
        private TextView textName;
        private TextView textBirthYear;
        private ConstraintLayout constraintLayoutContainer;
    }

    public interface ClickListener{
        void launchIntent(String filmName);
    }

    public void setData(List<StarWars.People> data){
        this.data = data;
        notifyDataSetChanged();
    }
}
