package in.obvious.nasapicx.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import in.obvious.nasapicx.R;
import in.obvious.nasapicx.model.NASAImage;
import in.obvious.nasapicx.viewholder.NASAImageDetailHolder;

public class NASAImageDetailAdapter extends RecyclerView.Adapter<NASAImageDetailHolder> {

    private List<NASAImage> nasaImages;

    public NASAImageDetailAdapter(List<NASAImage> nasaImages) {
        this.nasaImages = nasaImages;
    }

    @NonNull
    @Override
    public NASAImageDetailHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_image_details, parent, false);
        NASAImageDetailHolder holder = new NASAImageDetailHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NASAImageDetailHolder holder, int position) {
        NASAImage nasaImage = nasaImages.get(position);
        holder.setContents(nasaImage);
    }

    @Override
    public int getItemCount() {
        if(nasaImages != null && nasaImages.size() > 0){
            return nasaImages.size();
        }else{
            return 0;
        }
    }
}
