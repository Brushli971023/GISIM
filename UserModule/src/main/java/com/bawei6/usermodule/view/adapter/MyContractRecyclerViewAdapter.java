package com.bawei6.usermodule.view.adapter;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bawei6.usermodule.R;
import com.bawei6.usermodule.model.bean.ContractBean;

import java.util.List;

/**
 * @author AZhung
 * @date 2020/1/2
 * @description
 */
public class MyContractRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<ContractBean> arrayList;
    Activity context;

    public MyContractRecyclerViewAdapter(List<ContractBean> arrayList, Activity context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return arrayList.get(position).getFlag();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == 0){
            View view = LayoutInflater.from(context).inflate(R.layout.item_title,viewGroup,false);
            return new TitleViewHolder(view);
        }else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_phone,viewGroup,false);
            return new PhoneViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, final int i) {
        if (arrayList.get(i).getFlag() == 0){
            TitleViewHolder titleViewHolder = (TitleViewHolder) viewHolder;
            titleViewHolder.textView.setText(arrayList.get(i).getLabel());
        }else {
            PhoneViewHolder phoneViewHolder = (PhoneViewHolder) viewHolder;
            phoneViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent();
                    intent.putExtra("contactName",arrayList.get(i).getName());
                    intent.putExtra("contactNumber",arrayList.get(i).getNumber());
                    context.setResult(Activity.RESULT_OK,intent);
                    context.finish();
                }
            });
            phoneViewHolder.textView.setText(arrayList.get(i).getName()+"\t\t"+arrayList.get(i).getNumber());
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class TitleViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        public TitleViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_title);
        }
    }
    class PhoneViewHolder extends RecyclerView.ViewHolder{

        TextView textView;
        public PhoneViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.item_phone);
        }
    }
}
