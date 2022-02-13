package in.obvious.nasapicx.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import in.obvious.nasapicx.model.NASAImage;
import in.obvious.nasapicx.repository.NASAImageRepository;

public class NASAImageViewModel extends ViewModel {
    private NASAImageRepository nasaImageRepository;

    public NASAImageViewModel() {
        nasaImageRepository = new NASAImageRepository();
    }

    public LiveData<List<NASAImage>> getNASAImagesLiveData(){
        return nasaImageRepository.getNASAImagesLiveData();
    }
}
