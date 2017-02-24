package com.shopkeep.yodaspeech.models;

import android.content.Context;

import com.shopkeep.yodaspeech.R;
import com.shopkeep.yodaspeech.interfaces.RequestModelInterface;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public class ServiceModel implements RequestModelInterface {
    private String TAG = "YodaServiceModel";
    private final Context context;

    public ServiceModel(Context context) {
        this.context = context;
    }

    @Override
    public void requestText(String text, final RequestCallback callback) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://yoda.p.mashape.com/")
                .build();
        MashapeServiceInterface service = retrofit.create(MashapeServiceInterface.class);
        Call<ResponseBody> result = service.yodaspeak(context.getString(R.string.api_key), text);

        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Response<ResponseBody> response) {
                try {
                    if(response.code()!= 101) {
                        callback.onRequestCompleted(response.body().string(), null);
                    }
                } catch (IOException e) {
                    callback.onRequestCompleted(null, e);
                }
            }

            @Override
            public void onFailure(Throwable t) {
                callback.onRequestCompleted(null, t);
            }
        });
    }


    public interface MashapeServiceInterface {
        @Headers("Accept: text/plain")
        @GET("/yoda")
        Call<ResponseBody> yodaspeak(@Header("X-Mashape-Key") String key, @Query("sentence") String sentence);
    }
}

