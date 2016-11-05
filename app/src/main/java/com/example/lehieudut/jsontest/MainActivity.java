package com.example.lehieudut.jsontest;

import android.util.Log;
import android.view.View;

import com.example.lehieudut.jsontest.Base.BaseActivity;
import com.example.lehieudut.jsontest.Base.JsonModel;
import com.example.lehieudut.jsontest.service.ApiClient;
import com.example.lehieudut.jsontest.service.ApiConfig;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity implements View.OnClickListener {

    private Call<JsonModel> mRequestApi;

    @Override
    protected void afterView() {
        startService();
        getApi();
    }

    private void startService() {
        ApiConfig apiConfig = ApiConfig.builder()
                .context(getApplicationContext())
                .baseUrl(getString(R.string.url_base))
                .build();
        ApiClient.getInstance().init(apiConfig);
    }

    private void getApi() {
            mRequestApi = ApiClient.getService().getListImage("4x9um");
        mRequestApi.enqueue(new Callback<JsonModel>() {
            @Override
            public void onResponse(Call<JsonModel> call, Response<JsonModel> response) {
                String name = response.body().getName();
                Log.d("hieulele", name);
            }

            @Override
            public void onFailure(Call<JsonModel> call, Throwable t) {
                Log.d("hieulele", "failth");
            }
        });
    }

    private void updateJson(JsonModel new_json) {
        mRequestApi = ApiClient.getService().updateJSON("4x9um", new_json);
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

    @Click(R.id.mUpdate)
    public void onClick(View view) {
        JsonModel mjson = new JsonModel();
        mjson.setId(12);
        mjson.setName("lele");
        mjson.setPrice("12344");
        updateJson(mjson);
    }
}
