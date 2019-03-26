package com.example.abhinav.languagedemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder>
{
    ArrayList<LanguageBean> arrayList;
    Context context;

    public CustomAdapter(ArrayList<LanguageBean> arrayList,Context context)
    {
        this.arrayList = arrayList;
        this.context = context;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_custom_adapter, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }
    @Override
    public void onBindViewHolder( MyViewHolder holder, int position)
    {
        /*holder.txt_textview1.setText(arrayList.get(position).getDate());
        holder.txt_textview2.setText(arrayList.get(position).getPaidamt());
        holder.txt_textview3.setText(arrayList.get(position).getBillno());*/
    }
    @Override
    public int getItemCount()
    {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView txt_textview1, txt_textview2, txt_textview3;

        public MyViewHolder(View itemView)
        {
            super(itemView);
            txt_textview1 = (TextView) itemView.findViewById(R.id.txt_textview1);
            txt_textview2 = (TextView) itemView.findViewById(R.id.txt_textview2);
            txt_textview3 = (TextView) itemView.findViewById(R.id.txt_textview3);
        }
    }
}


