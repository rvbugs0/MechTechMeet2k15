package com.mech.tech.meet.scenario.fragments;

import android.app.Activity;
import android.content.Context;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.mech.tech.meet.activities.scenario.MainActivity;
import com.mech.tech.meet.R;
import com.mech.tech.meet.event.info.EventInfo;
import com.mech.tech.meet.parser.EventApi;
import com.squareup.picasso.Picasso;


import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class DeveloperFragment extends Fragment {

    private static final String ENDPOINT = "http://www.example.com";
    private static final String ARG_SECTION_NUMBER = "section_number";





    public void requestData() {

            RestAdapter adapter;
            adapter = new RestAdapter.Builder().setEndpoint(ENDPOINT).build();


            EventApi api = adapter.create(EventApi.class);
            api.getEventFeeds("GetDeveloper.php", new Callback<List<EventInfo>>() {

                @Override
                public void success(List<EventInfo> eventInfos, Response response) {
               try {
                   getActivity().findViewById(R.id.developer_card_container).setVisibility(View.VISIBLE);
                   TextView tv= (TextView) getActivity().findViewById(R.id.name);
                   tv.setText(eventInfos.get(0).getEventTitle());
                   ImageView iv= (ImageView) getActivity().findViewById(R.id.image);
                   Picasso.with(getActivity().getApplicationContext()).load(eventInfos.get(0).getImageResource()).into(iv);
                   ProgressBar pb= (ProgressBar) getActivity().findViewById(R.id.progressBar);
                   pb.setVisibility(View.INVISIBLE);
               }
                catch (Exception e)
                {
                e.printStackTrace();
                }
                }
                @Override
                public void failure(RetrofitError retrofitError) {
failureCall();
                }
            });

    }




    public void failureCall()
    {
        try {
            ProgressBar pb= (ProgressBar) getActivity().findViewById(R.id.progressBar);
            pb.setVisibility(View.INVISIBLE);

            android.support.v4.app.FragmentManager fragmentManager = getFragmentManager();
            android.support.v4.app.FragmentTransaction fragmentTransaction =
                    fragmentManager.beginTransaction();

            fragmentTransaction.replace(R.id.container, ConnectionErrorFragment.newInstance(getArguments().getInt("section_number")-1 + 1));

            fragmentTransaction.commit();

        }
        catch (Exception e)
        {

            e.printStackTrace();
        }

    }
        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static DeveloperFragment newInstance(int sectionNumber) {
            DeveloperFragment fragment = new DeveloperFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }


    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        }else {
            return false;
        }
    }


        public DeveloperFragment() {
        }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    requestData();
    }

    @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            ProgressBar pb= (ProgressBar) getActivity().findViewById(R.id.progressBar);
            pb.setVisibility(View.VISIBLE);



            View rootView;
            if (isOnline()) {

                rootView = inflater.inflate(R.layout.developer_card_layout, container, false);
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


/*
    public Bitmap getRoundedShape(Bitmap scaleBitmapImage) {
        int targetWidth = 320;
        int targetHeight = 240;
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



    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
*/


    }


