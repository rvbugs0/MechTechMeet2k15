package com.mech.tech.meet.scenario.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.mech.tech.meet.R;
import com.mech.tech.meet.activities.scenario.CreatePostCardActivity;
import com.mech.tech.meet.activities.scenario.MainActivity;
import com.mech.tech.meet.custom.list.adapters.PostCardAdapter;
import com.mech.tech.meet.event.info.PostCard;
import com.mech.tech.meet.parser.EventApi;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by bugs-pc on 2/7/2015.
 */
public class PostCardFragment extends ListFragment {

    private static final String ENDPOINT = "http://www.example.com";
    private static final String ARG_SECTION_NUMBER = "section_number";


    PostCardAdapter cd;
    ArrayList<PostCard> postCardArrayList = new ArrayList<>();


    public void updateDisplay(ArrayList<PostCard> e) {
        postCardArrayList=e;
        if(e.size()==0)
        {
            return;
        }
        try {
            ProgressBar pb= (ProgressBar) getActivity().findViewById(R.id.progressBar);
            pb.setVisibility(View.INVISIBLE);

            //   cd = new CustomListAdapter(getActivity(), R.id.list_item, e);
         cd = new PostCardAdapter(getActivity(), R.id.list_item, e);
            setListAdapter(cd);
        }
        catch (Exception ee)
        {

            ee.printStackTrace();
        }
    }

    public void requestData() {

            RestAdapter adapter;
            adapter = new RestAdapter.Builder().setEndpoint(ENDPOINT).build();


            EventApi api = adapter.create(EventApi.class);
            api.getPostCardFeeds("getpostcards.php", new Callback<List<PostCard>>() {
                @Override
                public void success(List<PostCard> postCards, Response response) {

               //    getActivity().findViewById(R.id.card_view).setVisibility(View.VISIBLE);
              successCall(postCards);


                }

                @Override
                public void failure(RetrofitError retrofitError) {
failureCall();
                }
            });

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



    }

    public void successCall(List<PostCard> postCards)
    {
        ArrayList<PostCard> setList = new ArrayList<>();
        int z=0;
        while(z<postCards.size())
        {
            setList.add(postCards.get(z));
            z++;
        }
        if(setList.size()!=0) {
            updateDisplay(setList);
        }


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
        public static PostCardFragment newInstance(int sectionNumber) {
            PostCardFragment fragment = new PostCardFragment();
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
        } else {
            return false;
        }
    }


        public PostCardFragment() {
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
Log.i("DATA","online hai");
                rootView = inflater.inflate(R.layout.fragment_post_card, container, false);
                Button postcardButton= (Button) rootView.findViewById(R.id.sendPostButton);
                postcardButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent=new Intent(getActivity(),CreatePostCardActivity.class);
                        startActivity(intent);
                    }
                });
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


