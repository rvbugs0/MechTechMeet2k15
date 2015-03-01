package com.mech.tech.meet.custom.list.adapters;

/**
 * Created by bugs-pc on 2/7/2015.
 */

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Rect;
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
public class PartnersListAdapter extends ArrayAdapter<EventInfo> {

    private Context context;
    private ArrayList<EventInfo> objects=new ArrayList<>();


    private class ViewHolder {
        ImageView ivMenu;
        TextView tvMenuHeader;
        TextView content;
    }


    public PartnersListAdapter(Context context, int resource, ArrayList<EventInfo> objects) {
        super(context, resource, objects);
        if(objects.size()==0) {
            return;
        }    this.objects = objects;

        this.context=context;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        int viewType = this.getItemViewType(position);

        LayoutInflater inflater =(LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        ViewHolder holder = null;

        if (convertView == null) {

            if(position!=-1) {
                convertView = inflater.inflate(R.layout.partners_page_list_layout, null);
                holder = new ViewHolder();

                holder.tvMenuHeader = (TextView) convertView.findViewById(R.id.heading);
                holder.ivMenu = (ImageView) convertView.findViewById(R.id.roundimage);
               holder.content= (TextView) convertView.findViewById(R.id.contents);
                //  holder.lineColor= (ImageView) convertView.findViewById(R.id.arrowBG1);
                convertView.setTag(holder);

            }



        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Picasso.with(context)
                .load(objects.get(position).getImageResource()).fit().centerCrop()
                .into(holder.ivMenu);

     holder.content.setText(objects.get(position).getIntroduction());
        holder.tvMenuHeader.setText(objects.get(position).getEventTitle());
        return convertView;

    }








}
