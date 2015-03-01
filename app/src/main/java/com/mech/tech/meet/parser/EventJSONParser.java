package com.mech.tech.meet.parser;

import android.util.Log;

import com.mech.tech.meet.event.info.EventInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by bugs-pc on 12/15/2014.
 */
public class EventJSONParser {

        public static List<EventInfo> parseFeed(String content) {

            try {
                JSONArray ar = new JSONArray(content);
                List<EventInfo> eventList = new ArrayList<>();

                for (int i = 0; i < ar.length(); i++) {

                    JSONObject obj = ar.getJSONObject(i);
                    EventInfo event = new EventInfo();


                    event.setEventTitle(obj.getString("title"));
                    Log.i("DATA",event.getEventTitle());
                    event.setImageResource(obj.getString("imagepath"));

                    event.setImageResource(obj.getString("imagepath"));
                    Log.i("DATA",event.getImageResource());
                    eventList.add(event);
                }

                return eventList;
            } catch (JSONException e) {
                e.printStackTrace();


                return null;
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return  null;
            }

        }
    }

