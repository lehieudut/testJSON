package com.example.lehieudut.jsontest;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.example.lehieudut.jsontest.Base.BaseActivity;
import com.example.lehieudut.jsontest.Base.ImageModel;
import com.example.lehieudut.jsontest.Base.JsonModel;
import com.example.lehieudut.jsontest.service.ApiClient;
import com.example.lehieudut.jsontest.service.ApiConfig;
import com.example.lehieudut.jsontest.service.RecyclerViewAdapter;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

    private RecyclerViewAdapter mAdapter;
    private Call<JsonModel> mRequestApi;
    private List<ImageModel> mArray = new ArrayList<>();

    @ViewById
    SwipeRefreshLayout mSwipeLayout;
    @ViewById(R.id.mRecyclerView)
    RecyclerView mRecyclerView;

    @Override
    protected void afterView() {
        startService();
        //getApi();
        setUpRecyclerView(mArray);
        mSwipeLayout.setOnRefreshListener(this);
        mSwipeLayout.post(new Runnable() {
            @Override
            public void run() {
                mArray.clear();
                getApi();
            }
        });
    }

    private void setUpRecyclerView(List<ImageModel> imageModels) {
        mAdapter = new RecyclerViewAdapter(this, imageModels);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mAdapter);
        //  mAdapter.notifyDataSetChanged();
    }

    private void startService() {
        ApiConfig apiConfig = ApiConfig.builder()
                .context(getApplicationContext())
                .baseUrl(getString(R.string.url_base))
                .build();
        ApiClient.getInstance().init(apiConfig);
    }

    private void getApi() {
        mRequestApi = ApiClient.getService().getListImage("14lbq");
        mRequestApi.enqueue(new Callback<JsonModel>() {
            @Override
            public void onResponse(Call<JsonModel> call, Response<JsonModel> response) {
                List<JsonModel.Photos> models = response.body().getPhotos();
                for (int i = 0; i < models.size(); i++) {
                    ImageModel imageModel = new ImageModel();
                    imageModel.setName(models.get(i).getName());
                    imageModel.setImage(models.get(i).getImage());
                    Log.d("hieulele", models.get(i).getName());
                    mArray.add(imageModel);
                    mAdapter.notifyDataSetChanged();
                }
                mSwipeLayout.setRefreshing(false);
                Log.d("hieulele", mArray.size() + "");
            }

            @Override
            public void onFailure(Call<JsonModel> call, Throwable t) {
                Log.d("hieulele", "failth");
            }
        });
    }

    private void updateJson(JsonModel new_json) {
        mRequestApi = ApiClient.getService().updateJSON("14lbq", new_json);
        mRequestApi.enqueue(new Callback<JsonModel>() {
            @Override
            public void onResponse(Call<JsonModel> call, Response<JsonModel> response) {
                Log.d("new_json", "success");
            }

            @Override
            public void onFailure(Call<JsonModel> call, Throwable t) {
                Log.d("new_json", "failth");
            }
        });
    }

    public void onClick(View view) {
        String arrayImageLink[] = getResources().getStringArray(R.array.image_Array);
        String arrayName[] = getResources().getStringArray(R.array.name_Array);
        JsonModel jsonModel = new JsonModel();
        List<JsonModel.Photos> photosList = new ArrayList<>();

        for (int i = 0; i < arrayImageLink.length; i++) {
            JsonModel.Photos photos = new JsonModel().new Photos();
            photos.setImage(arrayImageLink[i]);
            photos.setName(arrayName[i]);
            photosList.add(photos);
        }
        jsonModel.setPhotos(photosList);

        updateJson(jsonModel);
        mArray.clear();
        getApi();
    }

    @Override
    public void onRefresh() {
        mArray.clear();
        getApi();
    }
}
