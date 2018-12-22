package uz.nasiba.avaz.memome.ui.menu.memories;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import uz.nasiba.avaz.memome.R;
import uz.nasiba.avaz.memome.db.room.entity.Memory;
import uz.nasiba.avaz.memome.ui.create.CreateActivity;

//Avaz: Memory card manager adapter for recycler view
class MemoriesAdapter extends RecyclerView.Adapter<MemoriesAdapter.ViewHolder> {
    private ArrayList<Memory> memories;
    private LayoutInflater inflater;

    MemoriesAdapter(Context context, ArrayList<Memory> memories) {
        this.memories = memories;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(inflater.inflate(R.layout.layout_memory, viewGroup, false));
    }

    //Avaz: fill with information
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Memory memory = memories.get(i);
        viewHolder.title.setText(memory.getTitle());
        viewHolder.content.setText(memory.getContent());
        if (memory.getDate() != null && !memory.getDate().equals("")) {
            viewHolder.date.setText(memory.getDate());
        } else {
            viewHolder.date.setVisibility(View.GONE);
        }
        if (memory.getPictures().size() > 0) {
            Picasso.get()
                    .load(memory.getPictures().get(0))
                    .resize(200, 200)
                    .centerCrop()
                    .into(viewHolder.picture);
        } else {
            viewHolder.picture.setVisibility(View.GONE);
        }
        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(inflater.getContext(), CreateActivity.class);
                intent.putExtra("memory", memory);
                inflater.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return memories.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView title, content, date;
        ImageView picture;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);
            date = itemView.findViewById(R.id.date);
            picture = itemView.findViewById(R.id.picture);
            cardView = itemView.findViewById(R.id.card);
        }
    }
}
