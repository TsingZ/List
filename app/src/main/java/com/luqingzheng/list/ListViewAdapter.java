package com.luqingzheng.list;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

public class ListViewAdapter extends BaseAdapter {

    private List<Contacts> list;
    private static HashMap<Integer, Boolean> isSelected= new HashMap<Integer, Boolean>();
    private Context context;
    private LayoutInflater inflater = null;
    public static Boolean isShow = false;
    private final String TAG = "ADAPTER";

    ListViewAdapter(List<Contacts> list, Context context, Boolean isShow) {
        this.context = context;
        this.list = list;
        inflater = LayoutInflater.from(context);
        this.isShow = isShow;
        //initialData();
    }


    /*private void initialData() {
        for (int i = 0; i < list.size(); i++) {
            getIsSelected().put(i, false);
        }
    }*/

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position){
        return list.get(position);
    }

    @Override
    public long getItemId(int position){
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        Contacts contacts = (Contacts) getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate( R.layout.listview_item,parent,false);
            viewHolder.image =  convertView.findViewById(R.id.listview_image);
            viewHolder.text =  convertView.findViewById(R.id.listview_text);
            viewHolder.subtext =  convertView.findViewById(R.id.subtext);
            viewHolder.checkBox =  convertView.findViewById(R.id.checkbox);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if(isShow){
            viewHolder.checkBox.setVisibility(View.VISIBLE);
        }else {
            isSelected.clear();
            viewHolder.checkBox.setVisibility(View.GONE);
        }
        viewHolder.image.setImageResource(contacts.getImageId());
        viewHolder.text.setText(contacts.getName());
        viewHolder.subtext.setText(contacts.getSubname());
        viewHolder.checkBox.setId(position);

        //viewHolder.checkBox.setChecked(getIsSelected().get(position));

        viewHolder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked)
            {
                if(isChecked){
                    isSelected.put(position,true);
                }else {
                    isSelected.remove(position);
                }
            }
        });
        if (isSelected != null && isSelected.containsKey(position)){
            viewHolder.checkBox.setChecked(true);
            convertView.setBackgroundColor(Color.parseColor("#50B0F0F6"));
        }else {
            viewHolder.checkBox.setChecked(false);
            convertView.setBackgroundColor(Color.TRANSPARENT);
        }

        return convertView;
    }

    /*private static HashMap<Integer, Boolean> getIsSelected() {
        return isSelected;
    }

    public static void setIsSelected(HashMap<Integer, Boolean> isSelected) {
        ListViewAdapter.isSelected = isSelected;
    }*/

    class ViewHolder {
        ImageView image;
        TextView text;
        TextView subtext;
        CheckBox checkBox;
    }
}
