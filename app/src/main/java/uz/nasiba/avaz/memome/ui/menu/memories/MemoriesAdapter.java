package uz.nasiba.avaz.memome.ui.menu.memories;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import uz.nasiba.avaz.memome.R;
import uz.nasiba.avaz.memome.db.room.entity.Memory;

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

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Memory memory = memories.get(i);
        viewHolder.title.setText(memory.getTitle());
        viewHolder.content.setText(memory.getContent());
    }

    @Override
    public int getItemCount() {
        return memories.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, content;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);
        }
    }
}
