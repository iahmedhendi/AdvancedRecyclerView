package com.hendiware.advancedrecyclerview;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by developerhendy on 9/8/16.
 * © 2016.
 */
public class PostsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Post> posts;
    private Context context;

    public PostsAdapter(List<Post> posts, Context ctx) {
        this.posts = posts;
        this.context = ctx;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 456) {
            return new AdsHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.ads_row, parent, false));
        } else {
            return new PostHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.post_row, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);
        if (viewType == 456) {
            AdsHolder adsHolder = (AdsHolder) holder;
            // if we wanna do some thing with ads row for example change img we do it here
        } else {
            PostHolder postHolder = (PostHolder) holder;

            if (viewType == 123) {
                postHolder.postLayout.setBackgroundColor(Color.parseColor("#23c486"));
                postHolder.postTitle.setTextColor(Color.parseColor("#ffffff"));
                postHolder.postDesc.setTextColor(Color.parseColor("#ffffff"));
            }

            final Post post = posts.get(position);
            postHolder.postTitle.setText(post.getTitle());
            postHolder.postDesc.setText(post.getBody());
        }

    }


    @Override
    public int getItemCount() {
        return posts.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 2) {
            return 123;
        } else if (position == 3) {
            return 456;
        } else {
            return super.getItemViewType(position);
        }
    }


    public class AdsHolder extends RecyclerView.ViewHolder {

        public AdsHolder(View itemView) {
            super(itemView);
        }
    }

    public class PostHolder extends RecyclerView.ViewHolder {
        private TextView postTitle;
        private TextView postDesc;
        private LinearLayout postLayout;


        public PostHolder(View itemView) {
            super(itemView);
            postTitle = (TextView) itemView.findViewById(R.id.post_title);
            postDesc = (TextView) itemView.findViewById(R.id.post_desc);
            postLayout = (LinearLayout) itemView.findViewById(R.id.row_container);

            postTitle.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Post post = posts.get(getAdapterPosition());
                    Toast.makeText(context, "You clicked on " + post.getTitle(), Toast.LENGTH_SHORT).show();
                }
            });

        }

    }
}
