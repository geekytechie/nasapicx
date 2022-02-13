package in.obvious.nasapicx.service;

import java.util.List;

import in.obvious.nasapicx.model.NASAImage;
import retrofit2.Call;
import retrofit2.http.GET;

public interface RestApiService {

    @GET("1ESS")
    Call<List<NASAImage>> getNASAImages();
}
