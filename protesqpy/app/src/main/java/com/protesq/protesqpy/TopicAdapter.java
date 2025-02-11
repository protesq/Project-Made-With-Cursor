package com.protesq.protesqpy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class TopicAdapter extends RecyclerView.Adapter<TopicAdapter.TopicViewHolder> {
    private List<Topic> topics;
    private Context context;
    private SharedPreferences prefs;
    private static final int TOPIC_DETAIL_REQUEST = 1;

    public TopicAdapter(List<Topic> topics, Context context) {
        this.topics = topics;
        this.context = context;
        this.prefs = context.getSharedPreferences("TopicProgress", Context.MODE_PRIVATE);
    }

    @Override
    public TopicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.topic_item, parent, false);
        return new TopicViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TopicViewHolder holder, int position) {
        Topic topic = topics.get(position);
        holder.titleText.setText(topic.getTitle());
        holder.descriptionText.setText(topic.getDescription());
        
        boolean isUnlocked = position == 0 || 
                           prefs.getBoolean("topic_" + (position - 1) + "_completed", false) ||
                           prefs.getBoolean("topic_" + position + "_unlocked", false);
        boolean isCompleted = prefs.getBoolean("topic_" + position + "_completed", false);
        
        holder.itemView.setAlpha(isUnlocked ? 1.0f : 0.5f);
        
        if (isCompleted) {
            holder.itemView.setBackgroundColor(context.getResources().getColor(android.R.color.holo_red_light));
            holder.titleText.setTextColor(context.getResources().getColor(android.R.color.white));
            holder.descriptionText.setTextColor(context.getResources().getColor(android.R.color.white));
        } else {
            holder.itemView.setBackgroundColor(context.getResources().getColor(android.R.color.white));
            holder.titleText.setTextColor(context.getResources().getColor(android.R.color.black));
            holder.descriptionText.setTextColor(context.getResources().getColor(android.R.color.black));
        }
        
        holder.itemView.setOnClickListener(v -> {
            if (isUnlocked) {
                Intent intent = new Intent(context, TopicDetailActivity.class);
                intent.putExtra("title", topic.getTitle());
                intent.putExtra("position", position);
                ((Activity) context).startActivityForResult(intent, TOPIC_DETAIL_REQUEST);
                ((Activity) context).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
    }

    public void updateList(List<Topic> newList) {
        this.topics = newList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return topics.size();
    }

    static class TopicViewHolder extends RecyclerView.ViewHolder {
        TextView titleText;
        TextView descriptionText;

        TopicViewHolder(View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.titleText);
            descriptionText = itemView.findViewById(R.id.descriptionText);
        }
    }
} 