package in.obvious.nasapicx.viewholder;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import in.obvious.nasapicx.R;
import in.obvious.nasapicx.model.NASAImage;

public class NASAImageDetailHolder extends RecyclerView.ViewHolder {

    private NASAImage nasaImage;
    private AppCompatImageView ivHDImage;
    private AppCompatTextView tvTitle, tvDescription, tvCopyright;
    private Context context;

    public NASAImageDetailHolder(@NonNull View itemView) {
        super(itemView);
        context = itemView.getContext();
        ivHDImage = itemView.findViewById(R.id.ivHDImage);
        tvTitle = itemView.findViewById(R.id.tvTitle);
        tvDescription = itemView.findViewById(R.id.tvDescription);
        tvCopyright = itemView.findViewById(R.id.tvCopyright);
    }

    public void setContents(NASAImage nasaImage){
        this.nasaImage = nasaImage;
        Picasso.get().load(nasaImage.getHdurl()).into(ivHDImage);
        tvTitle.setText(nasaImage.getTitle());
        tvDescription.setText(nasaImage.getExplanation());
        if(nasaImage.getCopyright() != null && nasaImage.getCopyright().length() > 0){
            tvCopyright.setText(String.format("%s %s", context.getString(R.string.copyright), nasaImage.getCopyright()));
        }
    }
}
