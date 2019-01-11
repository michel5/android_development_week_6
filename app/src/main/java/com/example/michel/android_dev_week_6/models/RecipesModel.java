package com.example.michel.android_dev_week_6.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RecipesModel {
    @SerializedName("count")
    @Expose
    private Integer count;
    @SerializedName("recipes")
    @Expose
    public RecipeModel[] recipes = null;

    public Integer getCount() {
        System.out.println(count);
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public RecipeModel[] getRecipes() {
        return recipes;
    }

    public void setRecipes(RecipeModel[] recipes) {
        this.recipes = recipes;
    }
}
