package com.riztech.assignment.view;

import android.content.Context;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.riztech.assignment.R;
import com.riztech.assignment.adapter.GroupAdpter;
import com.riztech.assignment.db.DatabaseHelper;
import com.riztech.assignment.model.Group;
import com.riztech.assignment.model.MeetupResponse;
import com.riztech.assignment.model.Results;
import com.riztech.assignment.network.ApiClient;
import com.riztech.assignment.network.ApiInterface;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {

    private static final double LAT = 51.509980;

    private static final double LON = -0.133700;

    private static final int PAGE = 20;

    private static final String KEY = "1f5718c16a7fb3a5452f45193232";

    DatabaseHelper databaseHelper;

    RecyclerView rvGroups;

    GroupAdpter groupAdpter;
    private ApiInterface apiInterface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        rvGroups = findViewById(R.id.rvGroups);

        rvGroups.setLayoutManager(new LinearLayoutManager(this));

        apiInterface = ApiClient.getClient().create(ApiInterface.class);


        if (isNetworkConnected()) {
            getDataFromServer();
        } else {
            getDataFromDatabase();
        }


    }

    private void getDataFromDatabase() {
        List<Group> groups = databaseHelper.getGroups();

        groupAdpter = new GroupAdpter(groups);
        rvGroups.setAdapter(groupAdpter);
        Toast.makeText(getApplicationContext(), "Data from database", Toast.LENGTH_LONG).show();
    }

    private void getDataFromServer() {
        Call<MeetupResponse> responseCall = apiInterface.getMeetupResponse(LAT, LON, PAGE, KEY);

        responseCall.enqueue(new Callback<MeetupResponse>() {
            @Override
            public void onResponse(Call<MeetupResponse> call, Response<MeetupResponse> response) {

                MeetupResponse meetupResponse = response.body();

                List<Results> results = Arrays.asList(meetupResponse.getResults());
                List<Group> groups = new ArrayList<>();
                for (Results result : results) {
                    Group group = new Group(result.getId(), result.getName(), result.getMembers(), result.getDescription(),
                            result.getLat(), result.getLon());
                    groups.add(group);
                }


                groupAdpter = new GroupAdpter(groups);
                rvGroups.setAdapter(groupAdpter);
                databaseHelper.insertGroups(groups);

                Toast.makeText(getApplicationContext(), "Data inserted from server", Toast.LENGTH_LONG).show();


            }

            @Override
            public void onFailure(Call<MeetupResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }
}
