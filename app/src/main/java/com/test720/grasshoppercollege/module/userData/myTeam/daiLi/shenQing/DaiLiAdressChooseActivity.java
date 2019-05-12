package com.test720.grasshoppercollege.module.userData.myTeam.daiLi.shenQing;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.XmlAdressUntil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class DaiLiAdressChooseActivity extends BaseToolActivity {


    @BindView(R.id.back)
    LinearLayout back;
    @BindView(R.id.title)
    TextView title;
    @BindView(R.id.rightImg)
    ImageView rightImg;
    @BindView(R.id.right)
    RelativeLayout right;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    XmlAdressUntil xmlAdressUntil;
    MyBaseRecyclerAdapter adapter;
    List<String> list = new ArrayList<>();

    String provice = "";
    String city = "";
    String area = "";

    @Override
    protected String setTitle() {
        return null;
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_adress;
    }

    @Override
    protected void initData() {
        xmlAdressUntil = new XmlAdressUntil(mcontext);
        recyclerView.setLayoutManager(new LinearLayoutManager(mcontext));
        adapter = new MyBaseRecyclerAdapter<String>(list, mcontext, R.layout.start_text_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, String item, int postion) {
                holder.setText(R.id.text, item);
            }
        };
        adapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (provice.equals("")) {
                    provice = list.get(position);
                    list.clear();
                    list.addAll(Arrays.asList(xmlAdressUntil.getCityList(provice)));
                    title.setText("选择市");
                    adapter.notifyDataSetChanged();
                } else if (city.equals("")) {
                    city = list.get(position);
                    list.clear();
                    list.addAll(Arrays.asList(xmlAdressUntil.getAreaList(city)));
                    adapter.notifyDataSetChanged();
                    title.setText("选择区");
                } else if (area.equals("")) {
                    area = list.get(position);
                    Intent intent = new Intent(mcontext, ShenQingDaiLiActivity.class);
                    intent.putExtra("provice", provice);
                    intent.putExtra("city", city);
                    intent.putExtra("area", area);
                    jumpToActivity(intent, true);
                }
            }
        });
        recyclerView.setAdapter(adapter);

        //初始化省
        list.clear();
        list.addAll(Arrays.asList(xmlAdressUntil.getmProvinceDatas()));
        title.setText("选择省");
        adapter.notifyDataSetChanged();
    }

    @Override
    public void GetHttpBackStr(String str, int what) {

    }


    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }


}
