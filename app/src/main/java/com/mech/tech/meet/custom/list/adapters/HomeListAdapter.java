package com.mech.tech.meet.custom.list.adapters;

/**
 * Created by bugs-pc on 2/7/2015.
 */

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Path;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mech.tech.meet.R;
import com.mech.tech.meet.activities.scenario.MainActivity;
import com.mech.tech.meet.event.info.EventInfo;
import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;


/**
 * Created by bugs-pc on 2/1/2015.
 */
public class HomeListAdapter extends ArrayAdapter<EventInfo> {

    private Context context;
    private ArrayList<EventInfo> objects=new ArrayList<>();


    private class ViewHolder {
        ImageView ivMenu;
        TextView tvMenuHeader;
        ImageView lineColor;
    }


    public HomeListAdapter(Context context, int resource, ArrayList<EventInfo> objects) {
        super(context, resource, objects);
        if(objects.size()==0) {
            return;
        }    this.objects = objects;

        this.context=context;
    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        int viewType = this.getItemViewType(position);
       Log.i("Viewtype",viewType+"");
        LayoutInflater inflater =(LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        ViewHolder holder = null;

        if (convertView == null) {

            if(position!=-1) {
                convertView = inflater.inflate(R.layout.home_page_list_layout, null);
                holder = new ViewHolder();

                holder.tvMenuHeader = (TextView) convertView.findViewById(R.id.heading);
                holder.ivMenu = (ImageView) convertView.findViewById(R.id.roundimage);
                //  holder.lineColor= (ImageView) convertView.findViewById(R.id.arrowBG1);
                convertView.setTag(holder);

            }



        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Picasso.with(context)
                .load(objects.get(position).getImageResource())
                .into(holder.ivMenu);

        holder.tvMenuHeader.setText(objects.get(position).getEventTitle());
        return convertView;

    }



    public Bitmap getRoundedShape(Bitmap scaleBitmapImage) {
        int targetWidth = 100;
        int targetHeight = 100;
        Bitmap targetBitmap = Bitmap.createBitmap(targetWidth,
                targetHeight,Bitmap.Config.ARGB_8888);

        Canvas canvas = new Canvas(targetBitmap);
        Path path = new Path();
        path.addCircle(((float) targetWidth - 1) / 2,
                ((float) targetHeight - 1) / 2,
                (Math.min(((float) targetWidth),
                        ((float) targetHeight)) / 2),
                Path.Direction.CCW);

        canvas.clipPath(path);
        Bitmap sourceBitmap = scaleBitmapImage;
        canvas.drawBitmap(sourceBitmap,
                new Rect(0, 0, sourceBitmap.getWidth(),
                        sourceBitmap.getHeight()),
                new Rect(0, 0, targetWidth, targetHeight), null);
        return targetBitmap;
    }





}
