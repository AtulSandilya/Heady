package com.task.heady.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.task.heady.R;
import com.task.heady.adapters.RecyclerCategoryAdapters;
import com.task.heady.adapters.RecyclerProductAdapters;
import com.task.heady.adapters.RecyclerRankingAdapter;
import com.task.heady.api_controllers.API;
import com.task.heady.api_controllers.Controller;
import com.task.heady.models.Category;
import com.task.heady.models.HeadyModel;
import com.task.heady.models.Product;
import com.task.heady.models.Ranking;
import com.task.heady.models.RankingProduct;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HeadyActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout llCategoriesList, llSortingContainer;
    private RecyclerView recyclerCategories, recyclerProducts, recyclerRanking;
    private CardView btnCategory, btnCardSort;

    private List<Category> categories;
    private List<Product> productList;
    private List<Ranking> rankingList;
    private List<RankingProduct> rankingProductList;

    private RecyclerCategoryAdapters recyclerCategoryAdapters;
    private RecyclerProductAdapters recyclerProductAdapters;
    private RecyclerRankingAdapter recyclerRankingAdapter;

    boolean isCategory = false;
    boolean isSortingVisible = false;
    int product_position = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.heady_main);
        initComponent();
        fetchData();
    }

    private void initComponent() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        btnCategory = (CardView) findViewById(R.id.btnCategory);
        btnCategory.setOnClickListener(this);

        btnCardSort = (CardView) findViewById(R.id.btnCardSort);
        btnCardSort.setOnClickListener(this);

        llCategoriesList = (LinearLayout) findViewById(R.id.llCategoriesList);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(width / 3,
                LinearLayout.LayoutParams.MATCH_PARENT);
        llCategoriesList.setLayoutParams(lp);

        llSortingContainer = (LinearLayout) findViewById(R.id.llSortingContainer);

        categories = new ArrayList<Category>();
        recyclerCategories = (RecyclerView) findViewById(R.id.recyclerCategories);
        LinearLayoutManager layoutManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerCategories.setLayoutManager(layoutManager);
        recyclerCategories.setItemAnimator(new DefaultItemAnimator());
        recyclerCategoryAdapters = new RecyclerCategoryAdapters(this, categories);
        recyclerCategories.setAdapter(recyclerCategoryAdapters);

        productList = new ArrayList<Product>();
        recyclerProducts = (RecyclerView) findViewById(R.id.recyclerProducts);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerProducts.setLayoutManager(gridLayoutManager);
        recyclerProducts.setItemAnimator(new DefaultItemAnimator());
        recyclerProductAdapters = new RecyclerProductAdapters(this, productList);
        recyclerProducts.setAdapter(recyclerProductAdapters);

        rankingList = new ArrayList<Ranking>();
        recyclerRanking = (RecyclerView) findViewById(R.id.recyclerRanking);
        LinearLayoutManager layoutRankingManager
                = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerRanking.setLayoutManager(layoutRankingManager);
        recyclerRanking.setItemAnimator(new DefaultItemAnimator());
        recyclerRankingAdapter = new RecyclerRankingAdapter(this, rankingList);
        recyclerRanking.setAdapter(recyclerRankingAdapter);
    }

    private void fetchData() {
        Controller controller = new Controller();
        Retrofit retrofit = controller.create();
        API api = retrofit.create(API.class);
        Call<HeadyModel> call = api.fetchAllListData();
        call.enqueue(new Callback<HeadyModel>() {
            @Override
            public void onResponse(Call<HeadyModel> call, Response<HeadyModel> response) {
                if (response.isSuccessful()) {
                    HeadyModel headyModel = response.body();
                    categories.addAll(headyModel.getCategories());
                    recyclerCategoryAdapters.notifyDataSetChanged();
                    recyclerCategoryAdapters.setSelected(0);
                    if (categories.get(0).getProducts().size() > 0) {
                        productList.addAll(categories.get(0).getProducts());
                        recyclerProductAdapters.notifyDataSetChanged();
                    }

                    rankingList.addAll(headyModel.getRankings());
                    recyclerRankingAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<HeadyModel> call, Throwable t) {
                Toast.makeText(HeadyActivity.this,
                        "Something went wrong! Please try again later",
                        Toast.LENGTH_SHORT).show();

            }
        });
    }

    public void handleRecyclerClick (int position) {
        this.product_position = position;
        recyclerCategoryAdapters.setSelected(position);
        productList.clear();
        productList.addAll(categories.get(position).getProducts());
        recyclerProductAdapters.notifyDataSetChanged();
        isCategory = false;
        llCategoriesList.setVisibility(View.GONE);
        rankingProductList.clear();
        recyclerRankingAdapter.updateRankingList(false, 0);
    }

    public void handleRankingRecyclerClick (int position) {
        Ranking ranking = rankingList.get(position);
        rankingProductList = new ArrayList<RankingProduct>();
        rankingProductList = ranking.getProducts();
        recyclerRankingAdapter.updateRankingList(true, position);
        updateProductsAccordingToRanking();
    }

    private void updateProductsAccordingToRanking() {
        productList.clear();
        productList.addAll(categories.get(product_position).getProducts());
        for (int i = 0; i < rankingProductList.size(); i++) {
            for (int j = i+1; j < productList.size(); j++) {
                if (rankingProductList.get(i).getId() != productList.get(j).getId()) {
                    productList.remove(j);
                }
            }
        }
        recyclerProductAdapters.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnCategory:
                manageCategoryUI();
                break;
            case R.id.btnCardSort:
                manageSortingUI();
                break;
        }
    }

    public void manageSortingUI () {
        if (isSortingVisible) {
            isSortingVisible = false;
            llSortingContainer.setVisibility(View.GONE);
        } else {
            isSortingVisible = true;
            llSortingContainer.setVisibility(View.VISIBLE);
        }
    }

    public void manageCategoryUI () {
        if (isCategory) {
            isCategory = false;
            llCategoriesList.setVisibility(View.GONE);
        } else {
            isCategory = true;
            llCategoriesList.setVisibility(View.VISIBLE);
        }
    }

}
