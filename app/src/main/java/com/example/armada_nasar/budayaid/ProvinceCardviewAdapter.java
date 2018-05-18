package com.example.armada_nasar.budayaid;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Quantum Higgs on 5/15/2018.
 */

public class ProvinceCardviewAdapter extends RecyclerView.Adapter<ProvinceCardviewAdapter.CardViewViewHolder> {

    private ArrayList<Province> listBudaya;
    private Context context;
    private final String IMG_URL = "http://35.194.234.226:6014/getImage/";
    public ProvinceCardviewAdapter(Context context, ArrayList<Province> listPresident) {
        this.context = context;
        this.listBudaya = listPresident;
    }

    public ProvinceCardviewAdapter(Context context) {
        this.context = context;
    }

    public ArrayList<Province> getListBudaya() {
        return listBudaya;
    }

    public void setListBudaya(ArrayList<Province> listPresident) {
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

        final Province p = getListBudaya().get(position);

        Picasso.with(context)
                .load(IMG_URL + p.getName())
                .into(holder.imgPhoto);
        Log.d("imageurl", IMG_URL + p.getName());

        holder.tvName.setText(p.getFriendly_name());


        /*holder.imgPhoto.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {

            @Override
            public void onItemClicked(View view, int position) {
                Toast.makeText(context, "Favorite "+getListBudaya().get(position).getmAsalKotaBudaya(), Toast.LENGTH_SHORT).show();
            }
        }));*/

        holder.item_container.setOnClickListener(new CustomOnItemClickListener(position, new CustomOnItemClickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent viewBudayaIntent = new Intent(context, ViewBudayaActivity.class);
                viewBudayaIntent.putExtra("provinceId", p.getId());
                viewBudayaIntent.putExtra("provinceFriendlyName", p.getFriendly_name());

                view.getContext().startActivity(viewBudayaIntent);
            }
        }));

    }

    @Override
    public int getItemCount() {
        return getListBudaya().size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder{
        ImageView imgPhoto;
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
