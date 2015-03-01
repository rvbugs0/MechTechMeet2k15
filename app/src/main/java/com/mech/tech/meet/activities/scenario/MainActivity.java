package com.mech.tech.meet.activities.scenario;

import android.annotation.TargetApi;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;

import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.mech.tech.meet.NavigationDrawerFragment;
import com.mech.tech.meet.R;
import com.mech.tech.meet.running.services.MyNotificationService;
import com.mech.tech.meet.scenario.fragments.DeveloperFragment;
import com.mech.tech.meet.scenario.fragments.EventFragment;
import com.mech.tech.meet.scenario.fragments.EventFragmentForSelection;
import com.mech.tech.meet.scenario.fragments.PartnersFragment;
import com.mech.tech.meet.scenario.fragments.PostCardFragment;
import com.mech.tech.meet.scenario.fragments.LicenseActivity;

import java.util.Calendar;


public class MainActivity extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {


    // CardView cv= (CardView) findViewById(R.id.card_view);

    private boolean doubleBackToExitPressedOnce = false;
    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        int currentapiVersion = android.os.Build.VERSION.SDK_INT;
        if (currentapiVersion >= Build.VERSION_CODES.LOLLIPOP)
        {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(getResources().getColor(R.color.material_wala_deep_blue));
        }

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND, 10);

        Intent intent = new Intent(this, MyNotificationService.class);
        PendingIntent pintent = PendingIntent.getService(this, 0, intent,0);
        AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarm.setRepeating(AlarmManager.RTC_WAKEUP, cal.getTimeInMillis(),
                36000 * 1000*2, pintent);


        startService(new Intent(getBaseContext(), MyNotificationService.class));


        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(String name, @NonNull Context context, @NonNull AttributeSet attrs) {


        /*
    int currentapiVersion = android.os.Build.VERSION.SDK_INT;
    if (currentapiVersion >= Build.VERSION_CODES.LOLLIPOP)
    {
        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(getResources().getColor(R.color.material_wala_deep_blue));
    }

*/
    return super.onCreateView(name, context, attrs);

    }




    @Override


    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments

        doubleBackToExitPressedOnce=false;
if(position==7)
{
    getSupportFragmentManager().beginTransaction().replace(R.id.container,DeveloperFragment.newInstance(position+1))
            .commit();

}
 if(position==0)
        {   getSupportFragmentManager().beginTransaction().replace(R.id.container, EventFragment.newInstance(position + 1))
            .commit();
}


        if(position==5)
        {   getSupportFragmentManager().beginTransaction().replace(R.id.container, PartnersFragment.newInstance(position + 1))
                .commit();
        }



        if(position>0 && position<5)


        {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, EventFragmentForSelection.newInstance(position + 1))
                    .commit();


        }
        if(position==6)
        {   getSupportFragmentManager().beginTransaction().replace(R.id.container, PostCardFragment.newInstance(position + 1))
                .commit();
        }


    }





    public void onSectionAttached(int number) {





        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
            case 4:
                mTitle = getString(R.string.title_section4);
                break;
            case 5:
                mTitle = getString(R.string.title_section5);
                break;
            case 6:
                mTitle = getString(R.string.title_section6);
                break;

            case 7:
                mTitle = getString(R.string.title_section9);
                break;

            case 8:
                mTitle = getString(R.string.title_section8);
                break;

        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.menu_main, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_facebook) {

            String url = "http://www.facebook.com/mtmuec";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
            return true;
        }

        if (id == R.id.action_twitter) {

            String url = "http://www.twitter.com/mechtechmeet";
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            startActivity(i);
            return true;
        }


        if (id == R.id.action_licences) {

            Intent i = new Intent(this, LicenseActivity.class);
            startActivity(i);
            return true;
        }

        if(id==R.id.action_query)
        {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("message/rfc822");
            intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"mechtechmeet@gmail.com"});
            intent.putExtra(Intent.EXTRA_SUBJECT, "subject");
            intent.putExtra(Intent.EXTRA_TEXT, "message");
            Intent mailer = Intent.createChooser(intent, null);
            startActivity(mailer);


return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */



    @Override
    protected void onResume() {
        super.onResume();
        // .... other stuff in my onResume ....
        this.doubleBackToExitPressedOnce = false;
    }


    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Press twice to exit", Toast.LENGTH_SHORT).show();

    }

    }
