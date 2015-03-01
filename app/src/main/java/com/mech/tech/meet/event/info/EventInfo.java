package com.mech.tech.meet.event.info;


import android.content.res.Resources;
import android.graphics.Bitmap;

import com.mech.tech.meet.R;

import java.util.ArrayList;

/**
 * Created by bugs-pc on 2/2/2015.
 */
public class EventInfo {
private EventInfo e;

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public String getCoordinatorNumber() {
        return coordinatorNumber;
    }

    public void setCoordinatorNumber(String coordinatorNumber) {
        this.coordinatorNumber = coordinatorNumber;
    }

    private String coordinatorNumber;
    private String rules;
    private String eventTitle;
    private String imageResource;
    private String introduction;
    private String teamRequirement;
    private int notificationCode;
    private String problemStatement;

    private int registrationFee;

    public String getCoordinatorDetails() {
        return coordinatorDetails;
    }

    public void setCoordinatorDetails(String coordinatorDetails) {
        this.coordinatorDetails = coordinatorDetails;
    }

    private String coordinatorDetails;
    public int getRegistrationFee() {
        return registrationFee;
    }

    public void setRegistrationFee(int registrationFee) {
        this.registrationFee = registrationFee;
    }


    public String getTeamRequirement() {
        return teamRequirement;
    }

    public void setTeamRequirement(String teamRequirement) {
        this.teamRequirement = teamRequirement;
    }



    public int getNotificationCode() {
        return notificationCode;
    }

    public void setNotificationCode(int notificationCode) {
        this.notificationCode = notificationCode;
    }





    public String getProblemStatement() {
        return problemStatement;
    }

    public void setProblemStatement(String problemStatement) {
        this.problemStatement = problemStatement;
    }




    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }




private ArrayList<EventInfo> events=new ArrayList<>();

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    private Bitmap bitmap;

public EventInfo()

{
}


    public void setEventTitle(String eventTitle)
    {
        this.eventTitle=eventTitle;

    }

    public void setImageResource(String imageResource)
    {

        this.imageResource=imageResource;
    }

    public ArrayList<EventInfo> getAll()
    {
        for(int i=1;i<=6;i++) {
            e = new EventInfo();


            if(i==1) {
                e.setEventTitle("Competition");
              //  e.setImageResource(R.drawable.background1);
            }
            else if (i==2)
            {
                e.setEventTitle("Fun and Games");
                //e.setImageResource(R.drawable.background2);
            }

           else if(i==3) {
                e.setEventTitle("Workshop");
                //e.setImageResource(R.drawable.background3);
            }
            else if (i==4)
            {
                e.setEventTitle("Ideate");
                //e.setImageResource(R.drawable.background4);
            }

          else  if(i==5) {
                e.setEventTitle("Initiative");
                //e.setImageResource(R.drawable.background5);
            }
            else
            {
                e.setEventTitle("Special Event");
                //e.setImageResource(R.drawable.background6);
            }
            events.add(e);
        }
        return events;
    }


    public String getEventTitle()
    {
        return this.eventTitle;
    }

    public String  getImageResource()
    {
        return this.imageResource;
    }

}
