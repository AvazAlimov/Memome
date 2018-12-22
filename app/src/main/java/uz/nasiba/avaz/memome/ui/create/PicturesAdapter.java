package uz.nasiba.avaz.memome.ui.create;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Objects;

import uz.nasiba.avaz.memome.R;

//Avaz: Adapter for recycler view with pictures
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
        return new ViewHolder(inflater.inflate(R.layout.layout_picture, viewGroup, false), viewGroup);
    }

    //Nasiba: init all listeners and info
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
                    delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            uris.remove(viewHolder.getAdapterPosition());
                            viewModel.pictures.setValue(uris);
                            dialog.dismiss();
                        }
                    });
                    dialog.show();
                }
                Button show = dialog.findViewById(R.id.show);
                if (show != null) {
                    show.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ImageView picture = new ImageView(inflater.getContext());
                            Picasso.get().load(uris.get(viewHolder.getAdapterPosition())).into(picture);
                            Dialog dialog = new Dialog(inflater.getContext());
                            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                            Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                            dialog.setContentView(picture);
                            dialog.show();
                        }
                    });
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
        ViewGroup viewGroup;

        ViewHolder(View itemView, ViewGroup viewGroup) {
            super(itemView);
            picture = itemView.findViewById(R.id.picture);
            this.viewGroup = viewGroup;
        }
    }
}
