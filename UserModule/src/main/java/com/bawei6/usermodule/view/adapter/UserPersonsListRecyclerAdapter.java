package com.bawei6.usermodule.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei6.baselibrary.common.BaseConstant;
import com.bawei6.usermodule.R;
import com.bawei6.usermodule.model.bean.UserInfoBean;
import com.bawei6.usermodule.presenter.UserPresenter;
import com.bawei6.usermodule.view.activity.ChatActivity;

import java.util.List;

/**
 * @author AZhung
 * @date 2020/1/5
 * @description
 */
public class UserPersonsListRecyclerAdapter extends RecyclerView.Adapter<UserPersonsListRecyclerAdapter.MyViewHolder> {
    private Context context;
    private List<UserInfoBean> friends;
    public UserPersonsListRecyclerAdapter(Context context, List<UserInfoBean> friends) {
        this.context = context;
        this.friends = friends;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.person_list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.username.setText(friends.get(position).getUsername());
        holder.btu_addPerson.setVisibility(View.GONE);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, ChatActivity.class).putExtra("tousername",friends.get(position).getUsername()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return friends.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        private TextView username;
        private Button btu_addPerson;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            username = itemView.findViewById(R.id.text_item_username);
            btu_addPerson = itemView.findViewById(R.id.btn_item_add_person);
        }
    }
}
