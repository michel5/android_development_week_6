package com.example.michel.android_dev_week_6;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import com.example.michel.android_dev_week_6.models.RecipesModel;


public interface RecipeService {
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(MainActivity.BASIS_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    @GET("/api/search?key="+MainActivity.API_KEY+"&sort=r")
    Call<RecipesModel> getRecipes();



}
