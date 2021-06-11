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
        holder.attractionImage.setImageResource(current.getAttractionImageID());
        holder.imageViewIcon.setImageResource(current.getIconImageID());
        holder.attractionName.setText(current.getAttractionName());
        holder.imageViewCheckOne.setImageResource(current.getCheckOneResourceID());
        holder.imageViewCheckTwo.setImageResource(current.getCheckTwoResourceID());
        holder.imageViewCheckThree.setImageResource(current.getCheckThreeResourceID());
    }

    @Override
    public int getItemCount() {
        return this.attractions.size();
    }

    class ProjectViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public ImageView attractionImage;
        public TextView attractionName;
        public ImageView imageViewCheckOne;
        public ImageView imageViewCheckTwo;
        public ImageView imageViewCheckThree;
        public ImageView imageViewIcon;
        public MedalListAdapter adapter;

        public ProjectViewHolder(View itemView, MedalListAdapter adapter) {
            super(itemView);
            this.attractionImage = itemView.findViewById(R.id.attractionImage);
            this.attractionName = itemView.findViewById(R.id.attractionName);
            this.imageViewCheckOne = itemView.findViewById(R.id.checkOne);
            this.imageViewCheckTwo = itemView.findViewById(R.id.checkTwo);
            this.imageViewCheckThree = itemView.findViewById(R.id.checkThree);
            this.imageViewIcon = itemView.findViewById(R.id.medalImage);
            this.adapter = adapter;

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
