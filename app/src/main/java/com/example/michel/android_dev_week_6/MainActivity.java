package com.example.michel.android_dev_week_6;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.example.michel.android_dev_week_6.models.RecipeModel;
import com.example.michel.android_dev_week_6.models.RecipesModel;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    final static ArrayList<RecipeModel> recipes = new ArrayList<>();
    final static String API_KEY = "eb3e9a34a211ab023586a4598a5607f3";
    final static String BASIS_URL = "https://www.food2fork.com";

    private static ViewPager viewpager;
    public static PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestData();
        System.out.println(recipes.size());

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        viewpager = findViewById(R.id.frament_contrainer_pager);

        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), recipes);
        viewpager.setAdapter(pagerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

        return super.onOptionsItemSelected(item);
    }

    private void requestData(){
        RecipeService recipeService = RecipeService.retrofit.create(RecipeService.class);
        Call<RecipesModel> recipeCall = recipeService.getRecipes();
        recipeCall.enqueue(new Callback<RecipesModel>() {
            @Override
            public void onResponse(Call<RecipesModel> call, Response<RecipesModel> response) {
                // Voeg alle recepten toe aan lijst
                recipes.addAll(Arrays.asList(response.body().recipes));
                //recipeSectionsPagerAdapter.notifyDataSetChanged()
                System.out.println(recipes.size());
                MainActivity.pagerAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<RecipesModel> call, Throwable t) {
                Log.i("yroo", "onFailure: ");
                t.printStackTrace();
            }
        });
    }
}
