package com.mech.tech.meet.activities.scenario;


import android.annotation.TargetApi;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.manuelpeinado.fadingactionbar.extras.actionbarcompat.FadingActionBarHelper;
import com.mech.tech.meet.R;
import com.squareup.picasso.Picasso;


public class NotificationDetailActivity extends ActionBarActivity {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String title=getIntent().getExtras().getString("TITLE");
       // final String coordinatorNumber=getIntent().getExtras().getString("COORDINATORNUMBER");
        getSupportActionBar().setTitle(title);

setContentView(R.layout.notification_scrollview);
      updateUI();



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }





    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
        //return super.onOptionsItemSelected(item);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public  void updateUI()
    {  //int position = Integer.parseInt(getIntent().getExtras().getString("POSITION"));



     /*

        FadingActionBarHelper helper = new FadingActionBarHelper()
                .actionBarBackground(R.drawable.p5)
                .headerLayout(R.layout.header_light)
                .contentLayout(R.layout.notification_scrollview)
                .lightActionBar(true);
        setContentView(helper.createView(this));
        helper.initActionBar(this);

*/

        TextView description= (TextView) findViewById(R.id.description);
       description.setText(getIntent().getExtras().getString("DESCRIPTION"));

        TextView conclusion= (TextView) findViewById(R.id.conclusion);
        conclusion.setText(getIntent().getExtras().getString("CONCLUSION"));


       // ImageView iv = (ImageView) findViewById(R.id.image_header);
      //  Picasso.with(getApplicationContext()).load(getIntent().getExtras().getString("HEADERIMAGERESOURCE")).into(iv);

        ImageView iv1 = (ImageView) findViewById(R.id.teamImage);
        Picasso.with(getApplicationContext()).load(getIntent().getExtras().getString("IMAGERESOURCE")).fit().centerCrop().into(iv1);

    }

}