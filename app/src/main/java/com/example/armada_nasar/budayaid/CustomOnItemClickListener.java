package com.example.armada_nasar.budayaid;

import android.view.View;

/**
 * Created by Quantum Higgs on 5/15/2018.
 */

public class CustomOnItemClickListener implements View.OnClickListener {

    private int position;
    private OnItemClickCallback onItemClickCallback;
    public CustomOnItemClickListener(int position, OnItemClickCallback onItemClickCallback) {
        this.position = position;
        this.onItemClickCallback = onItemClickCallback;
    }

    @Override
    public void onClick(View view) {
        onItemClickCallback.onItemClicked(view, position);

    }
    public interface OnItemClickCallback {
        void onItemClicked(View view, int position);
    }
}
