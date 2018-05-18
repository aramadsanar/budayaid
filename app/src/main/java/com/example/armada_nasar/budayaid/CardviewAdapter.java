package com.example.armada_nasar.budayaid;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Quantum Higgs on 5/15/2018.
 */

public class CardviewAdapter extends RecyclerView.Adapter<CardviewAdapter.CardViewViewHolder> {

    private ArrayList<Budaya> listBudaya;
    private Context context;
    private final String IMG_URL = "http://35.194.234.226:6014/getImage/";
    public CardviewAdapter(Context context, ArrayList<Budaya> listPresident) {
        this.context = context;
        this.listBudaya = listPresident;
    }

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


        Picasso.with(context)
                .load(IMG_URL + p.getmImgURLBudaya())
                .into(holder.imgPhoto);
        Log.d("imageurl", IMG_URL + p.getmImgURLBudaya());

        holder.tvName.setText(p.getmNamaBudaya());


        holder.imgPhoto.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {

            @Override
            public void onItemClicked(View view, int position) {
                Toast.makeText(context, "Favorite "+getListBudaya().get(position).getmAsalKotaBudaya(), Toast.LENGTH_SHORT).show();
            }
        }));

        holder.item_container.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                String url = "http://google.com/search?q=";
                url += getListBudaya().get(position).getmGoogleSearchTerm();

                Intent googleSearchIntent = new Intent(Intent.ACTION_VIEW);
                googleSearchIntent.setData(Uri.parse(url));

                if (googleSearchIntent.resolveActivity(view.getContext().getPackageManager()) != null) {
                    view.getContext().startActivity(googleSearchIntent);
                }
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
        RelativeLayout item_container;
        public CardViewViewHolder(View itemView) {
            super(itemView);
            item_container = itemView.findViewById(R.id.item_container);
            imgPhoto = itemView.findViewById(R.id.img_card);
            tvName = itemView.findViewById(R.id.txt_budaya);
        }
    }


}
