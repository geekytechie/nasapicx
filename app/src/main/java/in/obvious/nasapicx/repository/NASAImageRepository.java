package in.obvious.nasapicx.repository;

import androidx.lifecycle.MutableLiveData;

import org.greenrobot.eventbus.EventBus;

import java.util.Collections;
import java.util.List;

import in.obvious.nasapicx.event.OnNASAImagesFailed;
import in.obvious.nasapicx.model.NASAImage;
import in.obvious.nasapicx.service.RestApiService;
import in.obvious.nasapicx.service.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NASAImageRepository {

    private MutableLiveData<List<NASAImage>> nasaImagesLiveData = new MutableLiveData<List<NASAImage>>();

    public MutableLiveData<List<NASAImage>> getNASAImagesLiveData(){
        RestApiService apiService = RetrofitInstance.getApiService();
        Call<List<NASAImage>> call = apiService.getNASAImages();
        call.enqueue(new Callback<List<NASAImage>>() {
            @Override
            public void onResponse(Call<List<NASAImage>> call, Response<List<NASAImage>> response) {
                List<NASAImage> nasaImages = response.body();
                if(nasaImages!=null && nasaImages.size() > 0){
                    Collections.sort(nasaImages);
                    nasaImagesLiveData.setValue(nasaImages);
                }
            }

            @Override
            public void onFailure(Call<List<NASAImage>> call, Throwable t) {
                EventBus.getDefault().post(new OnNASAImagesFailed());
            }
        });
        return nasaImagesLiveData;
    }
}
