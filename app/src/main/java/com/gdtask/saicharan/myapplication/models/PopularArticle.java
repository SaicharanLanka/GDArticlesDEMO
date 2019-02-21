package com.gdtask.saicharan.myapplication.models;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class PopularArticle {
    public String webUrl;

    public String imageUrl;

    public String title;
    public String tvabstract,tvdate;

    public static List<PopularArticle> fromJson(JSONArray movieJsonResults) {
        ArrayList<PopularArticle> result = new ArrayList<>();
        for(int i = 0; i < movieJsonResults.length(); i++ ) {
            PopularArticle p = new PopularArticle();
            try {
                p.title = movieJsonResults.getJSONObject(i).getString("title");
                p.tvabstract = movieJsonResults.getJSONObject(i).getString("abstract");
                p.tvdate = movieJsonResults.getJSONObject(i).getString("published_date");
                p.webUrl = movieJsonResults.getJSONObject(i).getString("url");
                JSONArray j = movieJsonResults.getJSONObject(i).getJSONArray("media");
               /* if(j !=null && j.length() > 0) {
                    JSONArray j1 = movieJsonResults.getJSONObject(0).getJSONArray("media-metadata");
                    if(j1!=null && j1.length() > 0) {
                        p.imageUrl = j1.getJSONObject(0).getString("url");
                    }
                }*/
                result.add(p);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
