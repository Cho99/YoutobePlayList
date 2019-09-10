package com.example.youtobeplaylist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static String API_KEY = "AIzaSyAVWkAJdvF9e4JOZ38oMsu658dl5RXGtuA";
    public static String ID_PLAYLIST ="PLzrVYRai0riSRJ3M3bifVWWRq5eJMu6tv";
    String urlGetJson = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId=PLzrVYRai0riSRJ3M3bifVWWRq5eJMu6tv&key=AIzaSyAVWkAJdvF9e4JOZ38oMsu658dl5RXGtuA&maxResults=50";

    ListView lvVideo;
    ArrayList<VideoYoutube> arrayVideo;
    VideoYouTubeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvVideo = findViewById(R.id.listViewVideo);
        arrayVideo = new ArrayList<>();
        adapter = new VideoYouTubeAdapter(this, R.layout.row_video_youtube, arrayVideo);
        lvVideo.setAdapter(adapter);

        GetJsonYouTube(urlGetJson);

        lvVideo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent( MainActivity.this, PlayVideoActivity.class);
                intent.putExtra("idVideoYoutube", arrayVideo.get(i).getIdVideo());
                startActivity(intent);
            }
        });

    }

    private void GetJsonYouTube(String url) {
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonItems = response.getJSONArray("items");
                            String title = "";
                            String url = "";
                            String idVideo = "";

                            for (int i = 0; i < jsonItems.length(); i ++) {
                                JSONObject jsonItem = jsonItems.getJSONObject(i);
                                //Snippet
                                JSONObject jsonSnippet = jsonItem.getJSONObject("snippet");
                                //Title
                                title = jsonSnippet.getString("title");

                                //Thumbnails
                                JSONObject jsonThumbnail = jsonSnippet.getJSONObject("thumbnails");
                                JSONObject jsonMedium = jsonThumbnail.getJSONObject("medium");
                                url  = jsonMedium.getString("url");

                                //ResourceId
                                JSONObject jsonResourceId = jsonSnippet.getJSONObject("resourceId");
                                idVideo = jsonResourceId.getString("videoId");

                                arrayVideo.add(new VideoYoutube(title, url, idVideo));
                            }
                            adapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        requestQueue.add(jsonObjectRequest);
    }
}
