package com.mech.tech.meet.running.services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.widget.Toast;

import com.mech.tech.meet.R;
import com.mech.tech.meet.activities.scenario.NotificationDetailActivity;
import com.mech.tech.meet.event.info.NotificationStructure;
import com.mech.tech.meet.parser.EventApi;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public  class MyNotificationService extends Service
{



    private static final String ENDPOINT = "http://www.example.com";

@Override
public IBinder onBind(Intent arg0) {
        return null;
        }

@Override
public int onStartCommand(Intent intent, int flags, int startId) {
       // Toast.makeText(this, "Service Started", Toast.LENGTH_LONG).show();

    if(isOnline()) {


        RestAdapter adapter;


        adapter = new RestAdapter.Builder().setEndpoint(ENDPOINT).build();


        EventApi api = adapter.create(EventApi.class);


        try {
            api.getNotifications("GetNotifications.php", new Callback<List<NotificationStructure>>() {
                @Override
                public void success(List<NotificationStructure> notifications, Response response) {
                    int p = 0;
                    while (p < notifications.size()) {

                        NotificationCompat.Builder mBuilder =
                                new NotificationCompat.Builder(MyNotificationService.this)
                                        .setSmallIcon(R.drawable.smallnotificationicon)
                                        .setContentTitle("Mech Tech Meet 2k15")
                                        .setContentText(notifications.get(p).getTitle()).setContentInfo(notifications.get(p).getIntroduction());
// Creates an explicit intent for an Activity in your app
                        Intent resultIntent = new Intent(getBaseContext(), NotificationDetailActivity.class);

                        resultIntent.putExtra("IMAGERESOURCE", notifications.get(p).getImageResource());
                        resultIntent.putExtra("DESCRIPTION", notifications.get(p).getDescription());
                        resultIntent.putExtra("HEADERIMAGERESOURCE", notifications.get(p).getHeaderImageResource());
                        resultIntent.putExtra("TITLE", notifications.get(p).getTitle());
                        resultIntent.putExtra("CONCLUSION", notifications.get(p).getConclusion());
// The stack builder object will contain an artificial back stack for the
// started Activity.
// This ensures that navigating backward from the Activity leads out of
// your application to the Home screen.
                        TaskStackBuilder stackBuilder = TaskStackBuilder.create(MyNotificationService.this);
// Adds the back stack for the Intent (but not the Intent itself)
                        stackBuilder.addParentStack(NotificationDetailActivity.class);
// Adds the Intent that starts the Activity to the top of the stack
                        stackBuilder.addNextIntent(resultIntent);
                        PendingIntent resultPendingIntent =
                                stackBuilder.getPendingIntent(
                                        0,
                                        PendingIntent.FLAG_UPDATE_CURRENT
                                );

                        mBuilder.setContentIntent(resultPendingIntent);
                        NotificationManager mNotificationManager =
                                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
// mId allows you to update the notification later on.
                        mNotificationManager.notify(p, mBuilder.build());

    /*
        NotificationManager nm = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        Notification notif = new Notification(R.drawable.ic_launcher, "Mech Tech Meet 2k15", System.currentTimeMillis());
        Context context = getBaseContext();
        Intent intent1 = new Intent(context, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(context,eventInfos.get(0).getNotificationCode() , intent1, 0);
        notif.setLatestEventInfo(context,eventInfos.get(0).getEventTitle(), eventInfos.get(0).getIntroduction(), pi);
        notif.sound = Uri.parse("android.resource://com.mech.tech.meet/" + R.raw.beep);

        nm.notify(0, notif);
*/
                        p++;
                    }

                }

                @Override
                public void failure(RetrofitError retrofitError) {

                }
            });


        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    return START_STICKY;

        }

@Override
public void onDestroy() {

        super.onDestroy();
        Toast.makeText(this, "Service Stopped", Toast.LENGTH_LONG).show();

        }

@Override
public void onCreate() {
        super.onCreate();
        }


    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }


}