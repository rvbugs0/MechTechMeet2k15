package com.mech.tech.meet.activities.scenario;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mech.tech.meet.R;


import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;


public class CreatePostCardActivity extends ActionBarActivity {

    String nameValue;
    String emailValue;
    String collegeValue;
    String messageValue;


    private static final String ENDPOINT = "http://www.example.com/mtmdata/SendPostCard.php";
    String url;
    public boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_post_card);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final EditText name= (EditText) findViewById(R.id.namePara);
        final EditText college= (EditText) findViewById(R.id.collegePara);
        final EditText email= (EditText) findViewById(R.id.emailPara);
        final EditText message= (EditText) findViewById(R.id.messagePara);
        Button sendButton= (Button) findViewById(R.id.sendButton);
     sendButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             nameValue = name.getText().toString().trim();
             emailValue = email.getText().toString().trim();
             collegeValue = college.getText().toString().trim();
             messageValue = message.getText().toString().trim();
             if(name.getText().toString().trim().length()==0 ||
                     email.getText().toString().trim().length()==0 ||
                     college.getText().toString().trim().length()==0 ||
                     message.getText().toString().trim().length()==0)
             {
                 Toast.makeText(CreatePostCardActivity.this,"Please complete the entries",Toast.LENGTH_SHORT).show();
                 return;
             }

          SummaryAsyncTask a= new SummaryAsyncTask();
         a.execute((Void) null);
             Toast.makeText(CreatePostCardActivity.this,"Thanks,you card will be posted soon",Toast.LENGTH_SHORT).show();
         }
     });

        }







    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_post_card, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (item.getItemId() == android.R.id.home) {
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    class SummaryAsyncTask extends AsyncTask<Void, Void, Boolean> {

        private  boolean postData(String name, String email, String college,
                              String message) {

            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost("http://www.example.com/mtmdata/SendPostCard.php");

            try {
                ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(4);
                nameValuePairs.add(new BasicNameValuePair("name", name));
                nameValuePairs.add(new BasicNameValuePair("email", email));
                nameValuePairs.add(new BasicNameValuePair("college", college));
                nameValuePairs.add(new BasicNameValuePair("message", message));
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse response = httpclient.execute(httppost);
                Log.e("log_tag", "Response : "+response.getStatusLine());

             CreatePostCardActivity.this.finish();
              return true;
            }
            catch(Exception e)
            {
                Log.e("log_tag", "Error:  "+e.toString());
            return false;
            }

        }

        @Override
        protected Boolean doInBackground(Void... params) {
            postData(nameValue, emailValue, collegeValue, messageValue);
            return null;
        }
    }


}

