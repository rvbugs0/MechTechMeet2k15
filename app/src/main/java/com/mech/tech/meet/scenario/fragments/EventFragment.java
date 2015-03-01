package com.mech.tech.meet.scenario.fragments;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mech.tech.meet.activities.scenario.MainActivity;
import com.mech.tech.meet.R;
import com.mech.tech.meet.custom.list.adapters.HomeListAdapter;
import com.mech.tech.meet.event.info.EventInfo;
import com.mech.tech.meet.parser.EventApi;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class EventFragment extends Fragment {




    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }




    private static final String ENDPOINT = "http://www.example.com";
    private static final String ARG_SECTION_NUMBER = "section_number";



    public static EventFragment newInstance(int sectionNumber) {
        EventFragment fragment = new EventFragment();
        Bundle args = new Bundle();

        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);


        return fragment;
    }





    public EventFragment() {
    }


    public void openFragment(int position)
    {
        try {
            android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    fragmentManager.beginTransaction();
            EventFragmentForSelection ef = EventFragmentForSelection.newInstance(position  +1+ 1);
            fragmentTransaction.replace(R.id.container, ef);

            fragmentTransaction.commit();
        } catch (Exception e) {

            e.printStackTrace();

        }

    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final ProgressBar pb= (ProgressBar) getActivity().findViewById(R.id.progressBar);
        pb.setVisibility(View.VISIBLE);


       final View rootView;
        if (isOnline()) {


            rootView = inflater.inflate(R.layout.fragment_main, container, false);




try {

    RestAdapter adapter;
    adapter = new RestAdapter.Builder().setEndpoint(ENDPOINT).build();
    EventApi api = adapter.create(EventApi.class);
    api.getEventFeeds("GetEvents.php", new Callback<List<EventInfo>>() {
        @Override
        public void success(List<EventInfo> eventInfos, Response response) {
            rootView.findViewById(R.id.fullLayout).setVisibility(View.VISIBLE);
            pb.setVisibility(View.INVISIBLE);
            //  LayoutInflater vi = (LayoutInflater) getActivity().getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);


            ViewGroup insertPoint = (ViewGroup) rootView.findViewById(R.id.insertionPoint);
            int z = 0;
            while (z < eventInfos.size()) {
                final int p = z;
                View v = inflater.inflate(R.layout.home_page_list_layout, null);
                v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openFragment(p);
                    }
                });
                insertPoint.addView(v, z, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
                TextView textView = (TextView) v.findViewById(R.id.heading);
                textView.setText(eventInfos.get(z).getEventTitle());
                ImageView iv = (ImageView) v.findViewById(R.id.roundimage);
                TextView contents = (TextView) v.findViewById(R.id.contents);
                contents.setText(eventInfos.get(z).getIntroduction());
             try {
                 Picasso.with(getActivity().getBaseContext()).load(eventInfos.get(z).getImageResource()).fit().centerCrop().into(iv);
             }catch (Exception e)
             {
                 e.printStackTrace();

             }z++;
            }
        }

        @Override
        public void failure(RetrofitError retrofitError) {
            Log.i("fail hogya", "fail hogya");
        }
    });


api.getEventFeeds("GetHome.php",new Callback<List<EventInfo>>() {
    @Override
    public void success(List<EventInfo> eventInfos, Response response) {

        TextView title= (TextView) rootView.findViewById(R.id.name);
        title.setText(eventInfos.get(0).getEventTitle());

        TextView introduction= (TextView) rootView.findViewById(R.id.introduction);
        introduction.setText(eventInfos.get(0).getIntroduction());

        ImageView iv= (ImageView) rootView.findViewById(R.id.imagef);
        Picasso.with(getActivity().getBaseContext())
                .load(eventInfos.get(0).getImageResource()).fit()
                .into(iv);

    }

    @Override
    public void failure(RetrofitError retrofitError) {
        Log.i("fail hogya", "fail hogya");
    }
});

}catch (Exception e)
{

    e.printStackTrace();
}


        } else {


            pb.setVisibility(View.INVISIBLE);
            rootView = inflater.inflate(R.layout.fragment_connection_error, container, false);
        }
        return rootView;
    }





    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(
                getArguments().getInt(ARG_SECTION_NUMBER));
    }


}



