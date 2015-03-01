package com.mech.tech.meet.custom.list.adapters;

/**
 * Created by bugs-pc on 2/7/2015.
 */

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mech.tech.meet.R;
import com.mech.tech.meet.event.info.EventInfo;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;





/**
 * Created by bugs-pc on 2/1/2015.
 */
public class EventListAdapter extends ArrayAdapter<EventInfo> {

    private Context context;
    private ArrayList<EventInfo> objects=new ArrayList<>();

    public EventListAdapter(Context context, int resource, ArrayList<EventInfo> objects) {
        super(context, resource, objects);
        if(objects.size()==0) {
            return;
        }    this.objects = objects;

        this.context=context;
    }
    private class ViewHolder {
        ImageView ivMenu;
        TextView tvMenuHeader;
         TextView price;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        LayoutInflater inflater =(LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        ViewHolder holder = null;
        ViewHolder holder1 = null;

        if (convertView == null) {

            if(position!=-1) {

                    convertView = inflater.inflate(R.layout.event_selection_list_layout, null);

                  holder = new ViewHolder();

                holder.tvMenuHeader = (TextView) convertView.findViewById(R.id.heading);
                holder.ivMenu = (ImageView) convertView.findViewById(R.id.roundimage);

                holder.price = (TextView) convertView.findViewById(R.id.price);
                convertView.setTag(holder);
            }


        } else {

                holder = (ViewHolder) convertView.getTag();

        }
        Picasso.with(context).load(objects.get(position).getImageResource()).fit().centerCrop().into(holder.ivMenu);
        holder.tvMenuHeader.setText(objects.get(position).getEventTitle());
        if(objects.get(position).getRegistrationFee()==0)
        {
            holder.price.setText("Free");
        }
        else
        {
            holder.price.setText("Rs. "+objects.get(position).getRegistrationFee());
        }

        return convertView;
    }


}
