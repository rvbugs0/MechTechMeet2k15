package com.mech.tech.meet.custom.list.adapters;

/**
 * Created by bugs-pc on 2/7/2015.
 */

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mech.tech.meet.R;
import com.mech.tech.meet.event.info.EventInfo;
import com.mech.tech.meet.event.info.PostCard;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;


/**
 * Created by bugs-pc on 2/1/2015.
 */
public class PostCardAdapter extends ArrayAdapter<PostCard> {

    private Context context;
    private ArrayList<PostCard> objects=new ArrayList<>();

    public PostCardAdapter(Context context, int resource, ArrayList<PostCard> objects) {
        super(context, resource, objects);
        if(objects.size()==0) {
            return;

        }    this.objects = objects;

        this.context=context;
    }




    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {


        LayoutInflater inflater =(LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
      //  ViewHolder holder = null;
try {

    View v;

    v = inflater.inflate(R.layout.post_card_layout, null);
    TextView name = (TextView) v.findViewById(R.id.name);
    TextView college = (TextView) v.findViewById(R.id.college);
    TextView message = (TextView) v.findViewById(R.id.message);

    name.setText(objects.get(position).getName());
    college.setText(objects.get(position).getCollege());
    message.setText(objects.get(position).getMessage());
    return v;

}


catch (Exception e)
{
    e.printStackTrace();
View v=inflater.inflate(R.layout.fake_view,null);
    return  v;
}


    }


}
