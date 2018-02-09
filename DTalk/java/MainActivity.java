package com.example.tpwls.maketalk;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.tpwls.d_talk.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView noticeListView;
    private NoticeListAdapter Adapter;
    private List<Notice> noticeList;

    private ListView eventListView;
    private EventListAdapter Adapter1; // adpater1 은 학과행사 어댑터
    private List<Event> eventList;



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_main:

                    return true;
                case R.id.navigation_date:

                    return true;
                case R.id.navigation_class:

                    return true;
                case R.id.navigation_board:

                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noticeListView = (ListView) findViewById(R.id.noticeListView);
        noticeList = new ArrayList<Notice>();
        Adapter = new NoticeListAdapter(getApplicationContext(),noticeList);
        noticeListView.setAdapter(Adapter);

        eventListView = (ListView) findViewById(R.id.eventListView);
        eventList = new ArrayList<Event>();
        Adapter1 = new EventListAdapter(getApplicationContext(),eventList);
        eventListView.setAdapter(Adapter1);

        new BackgroundTask().execute();
        new BackgroundTask_event().execute();



        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    class BackgroundTask_event extends  AsyncTask<Void,Void,String>{
        String target;

        @Override
        protected void onPreExecute() {
            target = "http://tpwls8122.cafe24.com/EventList.php";
        }

        @Override
        protected String doInBackground(Void... voids) {
            try{

                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while( ( temp = bufferedReader.readLine() ) != null ){
                    stringBuilder.append(temp + "\n");
                }

                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate();
        }

        @Override
        protected void onPostExecute(String result) {
            try{
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count = 0;
                String event_title,event_content,event_name,event_date;
                while(count < jsonArray.length()){
                    JSONObject object = jsonArray.getJSONObject(count);
                    event_title = object.getString("event_title");
                    event_content = object.getString("event_content");
                    event_name = object.getString("event_name");
                    event_date = object.getString("event_date");
                    Event event  = new Event(event_title,event_content,event_name,event_date);
                    eventList.add(event);
                    count++;
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    class BackgroundTask extends AsyncTask<Void, Void, String>{
        String target;

        @Override
        protected void onPreExecute() {
            target = "http://tpwls8122.cafe24.com/NoticeList.php";
        }

        @Override
        protected String doInBackground(Void... voids) {
            try{
                URL url = new URL(target);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                String temp;
                StringBuilder stringBuilder = new StringBuilder();
                while( (temp = bufferedReader.readLine()) != null ){
                    stringBuilder.append(temp + "\n");
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                return stringBuilder.toString().trim();

            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate();
        }

        @Override
        protected void onPostExecute(String result) {
            try{
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("response");
                int count = 0;
                String notice_title,notice_content,notice_name,notice_date;
                while(count < jsonArray.length()){
                    JSONObject object = jsonArray.getJSONObject(count);
                    notice_title = object.getString("notice_title");
                    notice_content = object.getString("notice_content");
                    notice_name = object.getString("notice_name");
                    notice_date = object.getString("notice_date");
                    Notice notice  = new Notice(notice_title,notice_content,notice_name,notice_date);
                    noticeList.add(notice);
                    count++;
                }

            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

}
