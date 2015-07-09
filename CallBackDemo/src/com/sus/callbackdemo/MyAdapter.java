package com.sus.callbackdemo;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {
    
    private ArrayList<String> items = new ArrayList<String>();
    private String item;
    private LayoutInflater mInflater;

    public MyAdapter(Activity activity,ArrayList<String> items) {
        super();
        mInflater = LayoutInflater.from(activity);
        this.items = items;
    }
    
    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        item = items.get(position);
        View itemView = null;
        MyViewHolder myViewHolder = null;
        if(convertView == null){
            myViewHolder = new MyViewHolder();
            itemView = mInflater.inflate(
                    R.layout.list_item, null);
            myViewHolder.textView = (TextView) itemView.findViewById(R.id.item);
            itemView.setTag(myViewHolder);
            convertView =itemView;
        }else{
            myViewHolder = (MyViewHolder) convertView.getTag();
        }
        myViewHolder.textView.setText(item);
        
        return convertView;
    }
    
    public void setItems(ArrayList<String> items){
        this.items = items;
        notifyDataSetChanged();
    }
    
    public static class MyViewHolder{
        TextView textView;
    }

}
