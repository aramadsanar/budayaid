package com.example.armada_nasar.budayaid;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

/**
 * Created by Quantum Higgs on 5/15/2018.
 */

public class CardviewAdapter extends RecyclerView.Adapter<CardviewAdapter.CardViewViewHolder> {

    private ArrayList<Budaya> listBudaya;
    private Context context;

    public CardviewAdapter(Context context) {
        this.context = context;
    }

    public ArrayList< Budaya> getListBudaya() {
        return listBudaya;
    }

    public void setListBudaya(ArrayList<Budaya> listPresident) {
        this.listBudaya = listPresident;
    }
    @Override
    public CardViewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.budaya_cardview, parent, false);
        CardViewViewHolder viewHolder = new CardViewViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CardViewViewHolder holder, int position) {

        Budaya p = getListBudaya().get(position);

        Glide.with(context)
                .load(p.getmImgURLBudaya())
                .override(350, 550)
                .into(holder.imgPhoto);

        holder.tvName.setText(p.getmAsalKotaBudaya());

        holder.imgPhoto.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {

            @Override
            public void onItemClicked(View view, int position) {
                Toast.makeText(context, "Favorite "+getListBudaya().get(position).getmAsalKotaBudaya(), Toast.LENGTH_SHORT).show();
            }
        }));

    }

    @Override
    public int getItemCount() {
        return getListBudaya().size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder{
        ImageButton imgPhoto;
        TextView tvName;
        public CardViewViewHolder(View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_card);
            tvName = itemView.findViewById(R.id.txt_budaya);
        }
    }
}
