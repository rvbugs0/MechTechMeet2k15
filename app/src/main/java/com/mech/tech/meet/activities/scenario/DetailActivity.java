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


public class DetailActivity extends ActionBarActivity {

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        String title=getIntent().getExtras().getString("TITLE");
        final String coordinatorNumber=getIntent().getExtras().getString("COORDINATORNUMBER");
        getSupportActionBar().setTitle(title);


      updateUI();

       Button register= (Button) findViewById(R.id.register);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:"+coordinatorNumber));
                startActivity(intent);

            }
        });


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
    {  int position = Integer.parseInt(getIntent().getExtras().getString("POSITION"));



        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
     if(position==0)
     {
         FadingActionBarHelper helper = new FadingActionBarHelper()
                 .actionBarBackground(R.drawable.p5)
                 .headerLayout(R.layout.header_light)
                 .contentLayout(R.layout.activity_scrollview)
                 .lightActionBar(true);
         setContentView(helper.createView(this));
         helper.initActionBar(this);

         if (currentapiVersion >= Build.VERSION_CODES.LOLLIPOP)
         {
             Window window = getWindow();
             window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
             window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
             window.setStatusBarColor(getResources().getColor(R.color.material_wala_red));

         }

     }

        else   if(position==1) {

            FadingActionBarHelper helper = new FadingActionBarHelper()
                    .actionBarBackground(R.drawable.p1)
                    .headerLayout(R.layout.header_light)
                    .contentLayout(R.layout.activity_scrollview)
                    .lightActionBar(true);
            setContentView(helper.createView(this));
            helper.initActionBar(this);

            if (currentapiVersion == Build.VERSION_CODES.LOLLIPOP)
            {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.setStatusBarColor(getResources().getColor(R.color.material_wala_green));

            }

        }


        else   if(position%2==0 && position%4!=0)
        {

            FadingActionBarHelper helper = new FadingActionBarHelper()
                    .actionBarBackground(R.drawable.p24)
                    .headerLayout(R.layout.header_light)
                    .contentLayout(R.layout.activity_scrollview)
                    .lightActionBar(true);
            setContentView(helper.createView(this));
            helper.initActionBar(this);

            if (currentapiVersion == Build.VERSION_CODES.LOLLIPOP)
            {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.setStatusBarColor(getResources().getColor(R.color.material_wala_orange));

            }

        }

        else   if(position%3==0)
        {

            FadingActionBarHelper helper = new FadingActionBarHelper()
                    .actionBarBackground(R.drawable.p3)
                    .headerLayout(R.layout.header_light)
                    .contentLayout(R.layout.activity_scrollview)
                    .lightActionBar(true);
            setContentView(helper.createView(this));
            helper.initActionBar(this);

            if (currentapiVersion == Build.VERSION_CODES.LOLLIPOP)
            {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.setStatusBarColor(getResources().getColor(R.color.material_wala_blue));

            }


        }


        else        if(position%4==0)
        {

            FadingActionBarHelper helper = new FadingActionBarHelper()
                    .actionBarBackground(R.drawable.p4)
                    .headerLayout(R.layout.header_light)
                    .contentLayout(R.layout.activity_scrollview)
                    .lightActionBar(true);
            setContentView(helper.createView(this));
            helper.initActionBar(this);

            if (currentapiVersion == Build.VERSION_CODES.LOLLIPOP)
            {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.setStatusBarColor(getResources().getColor(R.color.material_wala_dark_purple));
            }

        }


        else   if(position%5==0)
        {

            FadingActionBarHelper helper = new FadingActionBarHelper()
                    .actionBarBackground(R.drawable.p5)
                    .headerLayout(R.layout.header_light)
                    .contentLayout(R.layout.activity_scrollview)
                    .lightActionBar(true);
            setContentView(helper.createView(this));
            helper.initActionBar(this);

            if (currentapiVersion == Build.VERSION_CODES.LOLLIPOP)
            {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.setStatusBarColor(getResources().getColor(R.color.material_wala_red));

            }
        }
        else
        {
            FadingActionBarHelper helper = new FadingActionBarHelper()
                    .actionBarBackground(R.drawable.p5)
                    .headerLayout(R.layout.header_light)
                    .contentLayout(R.layout.activity_scrollview)
                    .lightActionBar(true);
            setContentView(helper.createView(this));
            helper.initActionBar(this);
            if (currentapiVersion == Build.VERSION_CODES.LOLLIPOP)
            {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
                window.setStatusBarColor(getResources().getColor(R.color.material_wala_red));

            }
        }
        TextView scrollTitle= (TextView) findViewById(R.id.scrollTitle);
        scrollTitle.setText(getIntent().getExtras().getString("TITLE"));


        TextView introduction= (TextView) findViewById(R.id.introductionParagraph);

        introduction.setText(getIntent().getExtras().getString("INTRODUCTION"));


        TextView problemStatement= (TextView) findViewById(R.id.problemParagraph);
        if(getIntent().getExtras().getString("PROBLEMSTATEMENT").compareTo("NULL")!=0) {
            problemStatement.setText(getIntent().getExtras().getString("PROBLEMSTATEMENT"));
        }else
        {
            TextView problemStatementHeading= (TextView) findViewById(R.id.problemStatementHeading);
           problemStatementHeading.setVisibility(View.GONE);
            problemStatement.setVisibility(View.GONE);
ImageView eye= (ImageView) findViewById(R.id.taskeye);
            eye.setVisibility(View.GONE);
        }

        TextView teamTitle = (TextView) findViewById(R.id.teamParagraph);

        if(getIntent().getExtras().getString("TEAMREQUIREMENT").compareTo("NULL")!=0) {

            teamTitle.setText(getIntent().getExtras().getString("TEAMREQUIREMENT"));
        }
        else {
            TextView teamHeading = (TextView) findViewById(R.id.TeamHeading);
            teamHeading.setVisibility(View.GONE);
teamTitle.setVisibility(View.GONE);
            ImageView teameye= (ImageView) findViewById(R.id.teameye);
            teameye.setVisibility(View.GONE);

        }

        TextView coordinatorDetails= (TextView) findViewById(R.id.cordinatordetails);
        coordinatorDetails.setText(getIntent().getExtras().getString("COORDINATORDETAILS"));

        TextView rules = (TextView) findViewById(R.id.rules);

        if(getIntent().getExtras().getString("RULES").compareTo("NULL")!=0) {

            rules.setText(getIntent().getExtras().getString("RULES"));
        }
        else {
            rules.setVisibility(View.GONE);
            TextView rulesHeading= (TextView) findViewById(R.id.rulesheading);
          rulesHeading.setVisibility(View.GONE);
            ImageView ruleseye= (ImageView) findViewById(R.id.ruleseye);
            ruleseye.setVisibility(View.GONE);

        }
        ImageView iv = (ImageView) findViewById(R.id.image_header);
        Picasso.with(getApplicationContext()).load(getIntent().getExtras().getString("IMAGEURL")).into(iv);
    }

}