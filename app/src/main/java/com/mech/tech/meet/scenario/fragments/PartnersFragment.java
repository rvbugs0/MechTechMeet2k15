package com.mech.tech.meet.scenario.fragments;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.mech.tech.meet.R;
import com.mech.tech.meet.activities.scenario.DetailActivity;
import com.mech.tech.meet.activities.scenario.MainActivity;
import com.mech.tech.meet.custom.list.adapters.EventListAdapter;
import com.mech.tech.meet.custom.list.adapters.PartnersListAdapter;
import com.mech.tech.meet.event.info.EventInfo;
import com.mech.tech.meet.parser.EventApi;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class PartnersFragment extends ListFragment {

        EventListAdapter cd;
        ArrayList<EventInfo> eventInfoArrayList = new ArrayList<>();


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

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PartnersFragment newInstance(int sectionNumber) {
            PartnersFragment fragment = new PartnersFragment();
            Bundle args = new Bundle();

            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);


            return fragment;
        }


        @Override
        public void onListItemClick(ListView l, View v, int position, long id) {

return;
  }

        public PartnersFragment() {
        }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            ProgressBar pb= (ProgressBar) getActivity().findViewById(R.id.progressBar);
            pb.setVisibility(View.VISIBLE);


            View rootView;
            if (isOnline()) {
                rootView = inflater.inflate(R.layout.event_fragment_for_selection, container, false);
            } else {


                pb.setVisibility(View.INVISIBLE);
                rootView = inflater.inflate(R.layout.fragment_connection_error, container, false);
            }
            return rootView;
        }



        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
            super.onActivityCreated(savedInstanceState);
            requestData();


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


        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        public void requestData() {
            boolean isConnectionAvailable = isOnline();
            if (isConnectionAvailable) {


                int a = (int) getArguments().get("section_number");
                Log.i("DATA", "DATA IS " + a);
                RestAdapter adapter;


                adapter = new RestAdapter.Builder().setEndpoint(ENDPOINT).build();


                EventApi api = adapter.create(EventApi.class);




                if (a == 6) {
                    api.getEventFeeds("GetPartners.php", new Callback<List<EventInfo>>()


                    {
                        @Override
                        public void success(List<EventInfo> eventInfos, Response response) {
                        //successCall(eventInfos);

                            ArrayList<EventInfo> setList = new ArrayList<>();
                            int z=0;
                            while(z<eventInfos.size())
                            {
                                setList.add(eventInfos.get(z));
                                z++;
                            }
                            if(setList.size()!=0) {


                            try {
                                ProgressBar pb= (ProgressBar) getActivity().findViewById(R.id.progressBar);
                                pb.setVisibility(View.INVISIBLE);

                                //   cd = new CustomListAdapter(getActivity(), R.id.list_item, e);
                            PartnersListAdapter    ef = new PartnersListAdapter(getActivity(), R.id.list_item, setList);
                                setListAdapter(ef);
                            }
                            catch (Exception ee)
                            {

                                ee.printStackTrace();
                            }}
                        }

                        @Override
                        public void failure(RetrofitError retrofitError) {


                            failureCall();
                        }
                    });
                }

            }
        }


    }




