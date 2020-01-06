package com.bawei6.usermodule.view.activity;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;

import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.bawei6.baselibrary.base.BaseActivity;
import com.bawei6.baselibrary.common.BaseConstant;
import com.bawei6.usermodule.R;
import com.bawei6.usermodule.view.adapter.MyContractRecyclerViewAdapter;
import com.bawei6.usermodule.model.bean.ContractBean;
import com.gjiazhe.wavesidebar.WaveSideBar;

import java.util.ArrayList;
import java.util.List;

/**
 * @author AZhung
 * @date 2019/12/27
 * @description 手机联系人
 */

public class PhoneContactActivity extends BaseActivity {

    private List<ContractBean> datas = new ArrayList<>();
    private String[] sideBars = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z","#"};
    private RecyclerView contract_recyclerView;
    private WaveSideBar wave_sideBar;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_contact);
        initView();
        initData();

    }

    private void initData() {
        datas.clear();
        ContentResolver contentResolver = getContentResolver();
        Cursor cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, "phonebook_label");
        String str = "";
        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String number = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            String phonebook_label = cursor.getString(cursor.getColumnIndex("phonebook_label"));
            ContractBean contractBean = new ContractBean(name, number, phonebook_label, 1);
            if (str.equals(phonebook_label)){
                datas.add(contractBean);
            }else {
                datas.add(new ContractBean(name, number, phonebook_label, 0));
                datas.add(contractBean);
                str = phonebook_label;
            }
        }

        contract_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        contract_recyclerView.setAdapter(new MyContractRecyclerViewAdapter(datas,this));
    }

    private void initView() {
        contract_recyclerView = (RecyclerView) findViewById(R.id.contract_recyclerView);
        wave_sideBar = (WaveSideBar) findViewById(R.id.wave_sideBar);

        wave_sideBar.setIndexItems(sideBars);
        wave_sideBar.setOnSelectIndexItemListener(new WaveSideBar.OnSelectIndexItemListener() {
            @Override
            public void onSelectIndexItem(String index) {
                for (int i=0; i < datas.size()-1;i++){
                    if (datas.get(i).getLabel() == index){
                        contract_recyclerView.smoothScrollToPosition(i);
                    }
                }
            }
        });
    }
}
