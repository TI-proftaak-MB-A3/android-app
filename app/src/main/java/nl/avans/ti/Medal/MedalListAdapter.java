package nl.avans.ti.Medal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

import nl.avans.ti.R;

public class MedalListAdapter extends RecyclerView.Adapter<MedalListAdapter.ProjectViewHolder> {
    private LinkedList<Attraction> attractions;
    private LayoutInflater inflater;

    public MedalListAdapter(Context context, LinkedList<Attraction> attractions) {
        this.attractions = attractions;
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View projectView = this.inflater.inflate(R.layout.medal_item, parent, false);
        return new ProjectViewHolder(projectView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
        Attraction current = this.attractions.get(position);
        holder.attractionImage.setImageResource(current.getAttractionResourceID());
        holder.attractionName.setText(current.getName());
        holder.checkOne.setImageResource(current.getCheckTrueResourceID());
        holder.checkTwo.setImageResource(current.getCheckTrueResourceID());
        holder.checkThree.setImageResource(current.getCheckTrueResourceID());
        holder.medalCheck.setImageResource(current.getMedalTrueResourceID());
    }

    @Override
    public int getItemCount() {
        return this.attractions.size();
    }

    class ProjectViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView attractionImage;
        public TextView attractionName;
        public ImageView checkOne;
        public ImageView checkTwo;
        public ImageView checkThree;
        public ImageView medalCheck;
        public MedalListAdapter adapter;

        public ProjectViewHolder(View itemView, MedalListAdapter adapter) {
            super(itemView);
            this.attractionImage = itemView.findViewById(R.id.attractionImage);
            this.attractionName = itemView.findViewById(R.id.attractionName);
            this.checkOne = itemView.findViewById(R.id.checkOne);
            this.checkTwo = itemView.findViewById(R.id.checkTwo);
            this.checkThree = itemView.findViewById(R.id.checkThree);
            this.medalCheck = itemView.findViewById(R.id.medalImage);
            this.adapter = adapter;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
