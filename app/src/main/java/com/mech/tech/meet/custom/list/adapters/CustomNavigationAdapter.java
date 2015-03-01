package com.mech.tech.meet.custom.list.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mech.tech.meet.R;


/**
 * Created by bugs-pc on 2/1/2015.
 */
public class CustomNavigationAdapter extends ArrayAdapter<String> {

    private String[] menuValues;
    public CustomNavigationAdapter(Context context, int resource, int textViewResourceId, String[] menu)
    {
        super(context, resource, textViewResourceId, menu);
    menuValues=menu;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {

        LayoutInflater layoutInflater= (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
View row= layoutInflater.inflate(R.layout.custom_navigation_drawer_layout,parent,false);
        TextView tv= (TextView) row.findViewById(R.id.listtext);


        ImageView iv= (ImageView) row.findViewById(R.id.listimage);
        tv.setText(menuValues[position]);
        //return super.getView(position, convertView, parent);
        if (menuValues[position].equals("Home")) {

            iv.setImageResource(R.drawable.homee);

        }

        if (menuValues[position].equals("Competitions")) {
            iv.setImageResource(R.drawable.competition);
        }

        if (menuValues[position].equals("Workshops")) {
            iv.setImageResource(R.drawable.workshopssss);
        }
        if (menuValues[position].equals("Fun and Games")) {
            iv.setImageResource(R.drawable.funevents);
        }
        if (menuValues[position].equals("Initiative")) {
            iv.setImageResource(R.drawable.initiativeeee);
        }
        if (menuValues[position].equals("Ideate")) {
            iv.setImageResource(R.drawable.ideate);
        }
        if (menuValues[position].equals("Partners")) {
            iv.setImageResource(R.drawable.specialevents);
        }
        if (menuValues[position].equals("Developer")) {
            iv.setImageResource(R.drawable.developer);
        }
        if (menuValues[position].equals("Gallery")) {
            iv.setImageResource(R.drawable.gallery);
        }

        if (menuValues[position].equals("Send us a post card")) {
            iv.setImageResource(R.drawable.gallery);
        }


        return row;
    }
}
