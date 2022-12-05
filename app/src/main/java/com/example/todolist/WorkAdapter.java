package com.example.todolist;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class WorkAdapter extends RecyclerView.Adapter<WorkAdapter.ViewHolder> {


    private final ArrayList<Work> worksData;
    private OnWorkItemClickListener listener;

    public WorkAdapter(ArrayList<Work> worksData,OnWorkItemClickListener listener) {

        this.worksData = worksData;
        this.listener=listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_work,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bindView(worksData.get(position));

    }


    @Override
    public int getItemCount() {
        return worksData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvCreateDate;
        TextView tvTitle;
        TextView tvDecription;
        ImageView imvDelete;
        ImageView imgWork;

        private Work work;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            init(itemView);
            imvDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onDeleteClick(work.getId());
                }
            });

        }

        private void init(View itemView) {
            imgWork=itemView.findViewById(R.id.image_view_work);
            tvTitle=itemView.findViewById(R.id.tv_title);
            tvCreateDate=itemView.findViewById(R.id.tv_date);
            tvDecription=itemView.findViewById(R.id.tv_description);
            imvDelete=itemView.findViewById(R.id.img_delete);
        }

        private void bindView(Work work){
            this.work=work;
            imgWork.setImageBitmap(work.getImgWork());
            tvTitle.setText(work.getTitle());
            tvDecription.setText(work.getDescription());
            tvCreateDate.setText(work.getCreateDate());

        }
    }
}
