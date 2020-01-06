package com.bawei6.usermodule.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei6.baselibrary.common.BaseConstant;
import com.bawei6.usermodule.R;
import com.bawei6.usermodule.contract.UserContract;
import com.bawei6.usermodule.model.bean.UserInfoBean;
import com.bawei6.usermodule.presenter.UserPresenter;

import java.util.List;

/**
 * @author AZhung
 * @date 2020/1/5
 * @description
 */
public class AddPersonListRecyclerAdapter extends RecyclerView.Adapter<AddPersonListRecyclerAdapter.MyViewHolder> {
    private Context context;
    private List<UserInfoBean> friends;
    private UserPresenter userPresenter;
    public AddPersonListRecyclerAdapter(Context context, List<UserInfoBean> friends, UserPresenter userPresenter) {
        this.context = context;
        this.friends = friends;
        this.userPresenter = userPresenter;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.person_list_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.username.setText(friends.get(position).getUsername());
        holder.btu_addPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userPresenter.addFriend(BaseConstant.userCode,friends.get(position).getUsercode(),friends.get(position).getUsername());
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
