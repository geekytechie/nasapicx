package in.obvious.nasapicx.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import in.obvious.nasapicx.model.NASAImage;
import in.obvious.nasapicx.repository.NASAImageRepository;
import in.obvious.nasapicx.service.RestApiService;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(JUnit4.class)
public class NASAImageViewModelTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();
    @Mock
    NASAImageRepository nasaImageRepository;
    private NASAImageViewModel nasaImageViewModel;
    @Mock
    Observer<List<NASAImage>> observer;
    @Mock
    LifecycleOwner lifecycleOwner;
    Lifecycle lifecycle;

    @Before
    public void setup() throws Exception{
        MockitoAnnotations.initMocks(this);
        lifecycle = new LifecycleRegistry(lifecycleOwner);
        nasaImageViewModel = new NASAImageViewModel();
        nasaImageViewModel.getNASAImagesLiveData().observeForever(observer);
    }

    @Test
    public void testNull(){
        when(nasaImageRepository.getNASAImagesLiveData()).thenReturn(null);
        assertNotNull(nasaImageViewModel.getNASAImagesLiveData());
        assertTrue(nasaImageViewModel.getNASAImagesLiveData().hasObservers());
    }

    @Test
    public void testRepositorySuccess(){
        when(nasaImageRepository.getNASAImagesLiveData()).thenReturn(new MutableLiveData<List<NASAImage>>());
        nasaImageViewModel.getNASAImagesLiveData();
    }

    @After
    public void wrapUp() throws Exception{
        nasaImageRepository = null;
        nasaImageViewModel = null;
    }
}
