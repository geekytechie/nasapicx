package in.obvious.nasapicx.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import in.obvious.nasapicx.R;
import in.obvious.nasapicx.adapter.NASAImagesAdapter;
import in.obvious.nasapicx.event.OnNASAImagePosition;
import in.obvious.nasapicx.event.OnNASAImagesFailed;
import in.obvious.nasapicx.model.NASAImage;
import in.obvious.nasapicx.viewmodel.NASAImageViewModel;

public class HomeActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private NASAImageViewModel nasaImageViewModel;
    private SwipeRefreshLayout swipeRefresh;
    private RecyclerView rvImages;
    private NASAImagesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
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
        swipeRefresh = findViewById(R.id.swipeRefresh);
        rvImages = findViewById(R.id.rvImages);
        swipeRefresh.setOnRefreshListener(this);
        getNASAImages();
    }

    private void getNASAImages(){
        swipeRefresh.setRefreshing(true);
        nasaImageViewModel.getNASAImagesLiveData().observe(this, new Observer<List<NASAImage>>() {
            @Override
            public void onChanged(List<NASAImage> nasaImages) {
                swipeRefresh.setRefreshing(false);
                setupRecyclerView(nasaImages);
            }
        });
    }

    private void setupRecyclerView(List<NASAImage> nasaImages) {
        adapter = new NASAImagesAdapter(nasaImages);
        rvImages.setLayoutManager(new GridLayoutManager(this, 3));
        rvImages.setItemAnimator(new DefaultItemAnimator());
        rvImages.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onNASAImagePosition(OnNASAImagePosition event){
        int position = event.getPosition();
        Intent intent = new Intent(HomeActivity.this, ImageDetailActivity.class);
        intent.putExtra("position", position);
        startActivity(intent);
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

    @Override
    public void onRefresh() {
        getNASAImages();
    }
}