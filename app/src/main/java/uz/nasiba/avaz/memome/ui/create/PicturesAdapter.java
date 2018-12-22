package uz.nasiba.avaz.memome.ui.create;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import uz.nasiba.avaz.memome.R;

public class PicturesAdapter extends RecyclerView.Adapter<PicturesAdapter.ViewHolder> {
    private ArrayList<Uri> uris;
    private LayoutInflater inflater;
    private CreateViewModel viewModel;

    PicturesAdapter(Context context, @NonNull ArrayList<Uri> uris, CreateViewModel viewModel) {
        this.uris = uris;
        this.inflater = LayoutInflater.from(context);
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(inflater.inflate(R.layout.layout_picture, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        Picasso.get()
                .load(uris.get(i))
                .resize(100, 100)
                .centerCrop()
                .into(viewHolder.picture);
        viewHolder.picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final BottomSheetDialog dialog = new BottomSheetDialog(inflater.getContext());
                dialog.setContentView(R.layout.sheet_picture);
                Button delete = dialog.findViewById(R.id.delete);
                if (delete != null) {
                    delete.setTag(viewHolder.getAdapterPosition());
                    delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            uris.remove((int) view.getTag());
                            viewModel.pictures.setValue(uris);
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return uris.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        ImageView picture;

        ViewHolder(View itemView) {
            super(itemView);
            picture = itemView.findViewById(R.id.picture);
        }
    }
}
