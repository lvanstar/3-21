package com.qiansong.msparis.app.find.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.qiansong.msparis.R;


/**
 * Created by lizhaozhao on 2017/2/9.
 */

public class AddClothesAdapter extends RecyclerView.Adapter<AddClothesAdapter.ViewHolder>{

    private Context context;

    public AddClothesAdapter(Context context){
        this.context=context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=View.inflate(context, R.layout.item_add_clothes,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 21;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
