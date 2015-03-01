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

import com.mech.tech.meet.activities.scenario.MainActivity;
import com.mech.tech.meet.R;
import com.mech.tech.meet.custom.list.adapters.EventListAdapter;
import com.mech.tech.meet.activities.scenario.DetailActivity;
import com.mech.tech.meet.custom.list.adapters.PartnersListAdapter;
import com.mech.tech.meet.event.info.EventInfo;
import com.mech.tech.meet.parser.EventApi;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by bugs-pc on 2/8/2015.
 */
public class EventFragmentForSelection  extends ListFragment {

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
        public static EventFragmentForSelection newInstance(int sectionNumber) {
            EventFragmentForSelection fragment = new EventFragmentForSelection();
            Bundle args = new Bundle();

            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);


            return fragment;
        }

        public void updateDisplay(ArrayList<EventInfo> e) {
            eventInfoArrayList=e;
            if(e.size()==0)
            {
                return;
            }
            try {
                ProgressBar pb= (ProgressBar) getActivity().findViewById(R.id.progressBar);
                pb.setVisibility(View.INVISIBLE);

                //   cd = new CustomListAdapter(getActivity(), R.id.list_item, e);
                cd = new EventListAdapter(getActivity(), R.id.list_item, e);
                setListAdapter(cd);
            }
            catch (Exception ee)
            {

                ee.printStackTrace();
            }
        }


        @Override
        public void onListItemClick(ListView l, View v, int position, long id) {




                Intent i = new Intent(getActivity(), DetailActivity.class);
                String title = eventInfoArrayList.get(position).getEventTitle();

                i.putExtra("POSITION", position + "");
                i.putExtra("TITLE", title);

            if(eventInfoArrayList.get(position).getRules()!=null) {

                i.putExtra("RULES", eventInfoArrayList.get(position).getRules());
            }
            else {
                i.putExtra("RULES", "NULL");

            }
                i.putExtra("IMAGEURL", eventInfoArrayList.get(position).getImageResource());

            i.putExtra("INTRODUCTION", eventInfoArrayList.get(position).getIntroduction());
if(eventInfoArrayList.get(position).getProblemStatement()!=null) {
    i.putExtra("PROBLEMSTATEMENT", eventInfoArrayList.get(position).getProblemStatement());

}
            else{

    i.putExtra("PROBLEMSTATEMENT", "NULL");
}
                //  Log.i("TEam",eventInfoArrayList.get(position).getTeamRequirement());


if(eventInfoArrayList.get(position).getTeamRequirement()!=null) {
    i.putExtra("TEAMREQUIREMENT", eventInfoArrayList.get(position).getTeamRequirement());
}
            else
{
    i.putExtra("TEAMREQUIREMENT", "NULL");
}
                i.putExtra("COORDINATORDETAILS", eventInfoArrayList.get(position).getCoordinatorDetails());
                i.putExtra("COORDINATORNUMBER", eventInfoArrayList.get(position).getCoordinatorNumber());
                // /eventInfoArrayList.get(position).
                startActivity(i);

        }

        public EventFragmentForSelection() {
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

    public void successCall(List<EventInfo> eventInfos)
    {
        ArrayList<EventInfo> setList = new ArrayList<>();
        int z=0;
        while(z<eventInfos.size())
        {
            setList.add(eventInfos.get(z));
            z++;
        }
        if(setList.size()!=0) {
            updateDisplay(setList);
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




                if (a == 2) {


                    api.getEventFeeds("GetCompetitions.php", new Callback<List<EventInfo>>()


                    {
                        @Override
                        public void success(List<EventInfo> eventInfos, Response response) {

                          successCall(eventInfos);

                        }

                        @Override
                        public void failure(RetrofitError retrofitError) {


                            failureCall();
                        }
                    });

                }


                if (a == 3) {
                    api.getEventFeeds("GetWorkshops.php", new Callback<List<EventInfo>>()


                    {
                        @Override
                        public void success(List<EventInfo> eventInfos, Response response) {
                         successCall(eventInfos);
                          }

                        @Override
                        public void failure(RetrofitError retrofitError) {


                            failureCall();
                        }
                    });

                }


                if (a == 4) {
                    api.getEventFeeds("GetFunAndGames.php", new Callback<List<EventInfo>>()


                    {
                        @Override
                        public void success(List<EventInfo> eventInfos, Response response) {
                        successCall(eventInfos);
                        }

                        @Override
                        public void failure(RetrofitError retrofitError) {


                            failureCall();
                        }
                    });

                }

                if (a == 5) {
                    api.getEventFeeds("GetInitiative.php", new Callback<List<EventInfo>>()


                    {
                        @Override
                        public void success(List<EventInfo> eventInfos, Response response) {
                     successCall(eventInfos);
                        }

                        @Override
                        public void failure(RetrofitError retrofitError) {


                            failureCall();
                        }
                    });

                }

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

                /*
                if (a == 7) {

                    api.getEventFeeds("GetSpecialEvents.php", new Callback<List<EventInfo>>()


                    {
                        @Override
                        public void success(List<EventInfo> eventInfos, Response response) {
                        successCall(eventInfos);
                        }

                        @Override
                        public void failure(RetrofitError retrofitError) {


                            failureCall();
                        }
                    });
                }


                */

                if (a == 7) {

                    api.getEventFeeds("GetSpecialEvents.php", new Callback<List<EventInfo>>()


                    {
                        @Override
                        public void success(List<EventInfo> eventInfos, Response response) {
                            successCall(eventInfos);
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




