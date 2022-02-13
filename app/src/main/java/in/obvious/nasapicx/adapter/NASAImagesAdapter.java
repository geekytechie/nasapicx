package in.obvious.nasapicx.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import in.obvious.nasapicx.R;
import in.obvious.nasapicx.model.NASAImage;
import in.obvious.nasapicx.viewholder.NASAImageViewHolder;

public class NASAImagesAdapter extends RecyclerView.Adapter<NASAImageViewHolder> {

    private List<NASAImage> nasaImages;

    public NASAImagesAdapter(List<NASAImage> nasaImages) {
        this.nasaImages = nasaImages;
    }

    @NonNull
    @Override
    public NASAImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_image, parent, false);
        NASAImageViewHolder holder = new NASAImageViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull NASAImageViewHolder holder, int position) {
        NASAImage nasaImage = nasaImages.get(position);
        holder.setContents(position, nasaImage);
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
