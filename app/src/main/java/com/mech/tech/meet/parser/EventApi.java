package com.mech.tech.meet.parser;

import com.mech.tech.meet.event.info.EventInfo;
import com.mech.tech.meet.event.info.NotificationStructure;
import com.mech.tech.meet.event.info.PostCard;

import java.util.List;

import retrofit.Callback;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.Path;

public interface EventApi
{
 //   @GET("/GetEvents.php")
  @GET("/mtmdata/{filename}")
    public void getEventFeeds(@Path("filename") String filename,Callback<List<EventInfo>> response);



    @GET("/mtmdata/{filename}")
    public void getPostCardFeeds(@Path("filename") String filename, Callback<List<PostCard>> response);

    @GET("/mtmdata/{filename}")
    public void getNotifications(@Path("filename") String filename, Callback<List<NotificationStructure>> response);

}