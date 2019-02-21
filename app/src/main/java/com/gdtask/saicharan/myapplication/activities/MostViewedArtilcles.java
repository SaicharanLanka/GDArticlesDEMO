package com.gdtask.saicharan.myapplication.activities;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.gdtask.saicharan.myapplication.R;
import com.gdtask.saicharan.myapplication.adapters.HorizontalAdapter;
import com.gdtask.saicharan.myapplication.adapters.ItemClickSupport;
import com.gdtask.saicharan.myapplication.databinding.ActivityPopularBinding;
import com.gdtask.saicharan.myapplication.models.PopularArticle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class MostViewedArtilcles extends AppCompatActivity {

    private HorizontalAdapter horizontalPoliticsAdapter;

    LinearLayout linearLayout;

    List<PopularArticle> politicsList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPopularBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_popular);

        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
      //  getSupportActionBar().setLogo(R.mipmap.navg);
        getSupportActionBar().setDisplayUseLogoEnabled(true);

        linearLayout = binding.popularLinearLayout;

        if (!isNetworkAvailable(this)) {
            Snackbar.make(linearLayout, R.string.not_connected, Snackbar.LENGTH_INDEFINITE).setAction("Retry",
                    v -> {
                        this.recreate();
                    }).show();
        }

        horizontalPoliticsAdapter=new HorizontalAdapter(this, politicsList);

        LinearLayoutManager horizontalLayoutManager1
                = new LinearLayoutManager(MostViewedArtilcles.this, LinearLayoutManager.VERTICAL, false);

        binding.horizontalRecyclerViewPolitics.setLayoutManager(horizontalLayoutManager1);
        binding.horizontalRecyclerViewPolitics.setAdapter(horizontalPoliticsAdapter);

        ItemClickSupport.addTo(binding.horizontalRecyclerViewPolitics).setOnItemClickListener(
                (RecyclerView recyclerView, int position, View v) -> {
                   // intentUtil(position, politicsList);
                    Toast.makeText(getApplicationContext(), "Item Clicked", Toast.LENGTH_SHORT).show();
                });

        OkHttpClient client = new OkHttpClient();

        String url = "http://api.nytimes.com/svc/mostpopular/v2/mostviewed/all-sections/7.json?api-key=Mk3yhRJG4MFCwILHgjs4K6ZG0psgzi1T";

        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(new okhttp3.Callback() {
            @Override
            public void onResponse(okhttp3.Call call, final okhttp3.Response response) throws IOException {
                final String responseData = response.body().string();
                runOnUiThread( () -> {
                        try {
                            JSONObject responseJSON = new JSONObject(responseData);
                            JSONArray jsonResults = responseJSON.getJSONArray("results");
                            politicsList.addAll(PopularArticle.fromJson(jsonResults));
                            horizontalPoliticsAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                });
            }

            @Override
            public void onFailure(okhttp3.Call call, IOException e) {

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_popular, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {


            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public boolean isNetworkAvailable(final Context context) {
        final ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

}
