package jp.developer.bbee.javamvvmdemo.presentation.artist.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import jp.developer.bbee.javamvvmdemo.R;
import jp.developer.bbee.javamvvmdemo.data.model.artist.Artist;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder> {
    private final List<Artist> artists;
    private final Map<Integer, Boolean> checkedMap = new HashMap<>();

    public ArtistAdapter(List<Artist> artists) {
        this.artists = artists;
    }

    @NonNull
    @Override
    public ArtistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item, parent, false);
        return new ArtistViewHolder(view);
    }

    private View.OnClickListener listener;
    public void setCheckBoxClickListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistViewHolder holder, int position) {
        Artist artist = artists.get(position);

        holder.itemTitle.setText(artist.name);

        String price = String.format(Locale.getDefault(), "%,d 円", artist.id);
        holder.itemPrice.setText(price);

        Boolean checked = checkedMap.get(position);
        if (checked == null) {
            checkedMap.put(position, false);
            checked = false;
        }
        holder.checkBox.setChecked(checked);

        holder.checkBox.setOnClickListener(v -> {
            boolean isChecked = ((CheckBox) v).isChecked();
            checkedMap.put(position, isChecked);
            listener.onClick(v);
        });
    }

    @Override
    public int getItemCount() {
        return artists.size();
    }

    public static class ArtistViewHolder extends RecyclerView.ViewHolder {
        TextView itemTitle;
        TextView itemPrice;
        CheckBox checkBox;

        public ArtistViewHolder(@NonNull View itemView) {
            super(itemView);

            itemTitle = itemView.findViewById(R.id.itemTitle);
            itemPrice = itemView.findViewById(R.id.itemPrice);
            checkBox = itemView.findViewById(R.id.checkbox);
        }
    }
}