package com.bawei6.usermodule.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei6.usermodule.R;

import java.util.List;

/**
 * @author AZhung
 * @date 2020/1/8
 * @description
 */
public class ChatFaceRecyclerAdapter extends RecyclerView.Adapter<ChatFaceRecyclerAdapter.MyViewHolder> {
    private List<String> slist;

    private OnItemClickLisenter onItemClickLisenter;

    public ChatFaceRecyclerAdapter(List<String> slist) {
        this.slist = slist;
    }

    public interface  OnItemClickLisenter{
        void onItemClick(int postion);
    }

    public void setOnItemClickListener(OnItemClickLisenter onItemClickListener){
        this.onItemClickLisenter = onItemClickListener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_face_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.textView.setText(slist.get(position));
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickLisenter != null){
                    onItemClickLisenter.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return slist.size();
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.chat_face_text);
        }
    }
}
