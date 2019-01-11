package com.example.michel.android_dev_week_6;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.michel.android_dev_week_6.models.RecipeModel;

import java.util.List;

public class FragmentPLaceHolder extends Fragment {
    public static final String ARG_ITEM_ID = "item_id";
    public TextView textTitle;
    public ImageView imageView;
    public RecipeModel recipe;

    public static FragmentPLaceHolder holder(RecipeModel recipe) {
        FragmentPLaceHolder fragmentPLaceHolder = new FragmentPLaceHolder();
        Bundle args = new Bundle();
        args.putParcelable(ARG_ITEM_ID, recipe);
        fragmentPLaceHolder.setArguments(args);
        return fragmentPLaceHolder;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recipe = getArguments().getParcelable(ARG_ITEM_ID);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment, container, false);
        imageView = rootView.findViewById(R.id.imageView);
        textTitle = rootView.findViewById(R.id.title_id);
        textTitle.setText(recipe.getTitle());
        return rootView;
    }
}

/**
 * Class
 */
class PagerAdapter extends FragmentPagerAdapter {
        private List<RecipeModel> recipesModelList;

    /**
     * Constructor
     * @param fm fragment maganger
     * @param recipes recipes model
     */
    public PagerAdapter(FragmentManager fm, List<RecipeModel> recipes) {
        super(fm);
        this.recipesModelList = recipes;
    }


    @Override
    public Fragment getItem(int i) {
        return FragmentPLaceHolder.holder(recipesModelList.get(i));
    }

    @Override
    public int getCount() {
        return recipesModelList.size();
    }
}
