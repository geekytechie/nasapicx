package in.obvious.nasapicx.viewholder;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.greenrobot.eventbus.EventBus;

import in.obvious.nasapicx.R;
import in.obvious.nasapicx.event.OnNASAImagePosition;
import in.obvious.nasapicx.model.NASAImage;

public class NASAImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private AppCompatImageView ivItemImage;
    private NASAImage nasaImage;
    private int position;

    public NASAImageViewHolder(@NonNull View itemView) {
        super(itemView);
        ivItemImage = itemView.findViewById(R.id.ivItemImage);
        ivItemImage.setOnClickListener(this);
    }

    public void setContents(int position, NASAImage nasaImage){
        this.position = position;
        this.nasaImage = nasaImage;
        Picasso.get().load(nasaImage.getUrl()).into(ivItemImage);
    }

    @Override
    public void onClick(View view) {
        EventBus.getDefault().post(new OnNASAImagePosition(position));
    }
}
