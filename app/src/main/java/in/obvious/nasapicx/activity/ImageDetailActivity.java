package in.obvious.nasapicx.activity;

import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.viewpager2.widget.ViewPager2;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import in.obvious.nasapicx.R;
import in.obvious.nasapicx.adapter.NASAImageDetailAdapter;
import in.obvious.nasapicx.event.OnNASAImagesFailed;
import in.obvious.nasapicx.model.NASAImage;
import in.obvious.nasapicx.viewmodel.NASAImageViewModel;

public class ImageDetailActivity extends AppCompatActivity {

    private NASAImageViewModel nasaImageViewModel;
    private ViewPager2 vpNASAImages;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    private void init(){
        nasaImageViewModel = ViewModelProviders.of(this).get(NASAImageViewModel.class);
        vpNASAImages = findViewById(R.id.vpNASAImages);
        position = getIntent().getIntExtra("position", 0);
        getNASAImages();
    }

    private void getNASAImages(){
        nasaImageViewModel.getNASAImagesLiveData().observe(this, new Observer<List<NASAImage>>() {
            @Override
            public void onChanged(List<NASAImage> nasaImages) {
                setupViewPager(nasaImages);
            }
        });
    }

    private void setupViewPager(List<NASAImage> nasaImages) {
        NASAImageDetailAdapter adapter = new NASAImageDetailAdapter(nasaImages);
        vpNASAImages.setAdapter(adapter);
        vpNASAImages.setCurrentItem(position, false);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNASAImagesFailed(OnNASAImagesFailed event){
        Toast.makeText(this, getString(R.string.error_message), Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
}