package com.test720.grasshoppercollege.module.userData;

import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseToolActivity;
import com.test720.grasshoppercollege.HttpBean.AdressListData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.myViews.TextDialog;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.FormBody;

public class MyAdressActivity extends BaseToolActivity {


    @BindView(R.id.addresslist)
    RecyclerView addresslist;
    @BindView(R.id.confirm)
    TextView confirm;
    @BindView(R.id.bottom)
    RelativeLayout bottom;
    @BindView(R.id.activity_my_adress)
    RelativeLayout activityMyAdress;
    MyBaseRecyclerAdapter adapter;
    List<AdressListData.DataBean> list = new ArrayList<>();
    private GridLayoutManager layoutManager;
    int mSelectedPos = -1;

    @Override
    protected String setTitle() {
        return "地址管理";
    }

    @Override
    protected int setlayoutResID() {
        return R.layout.activity_my_adress;
    }

    @Override
    protected void initData() {
        layoutManager = new GridLayoutManager(this, 1);
        //设置为垂直布局，这也是默认的
        //设置布局管理器
        addresslist.setLayoutManager(layoutManager);
        adapter = new MyBaseRecyclerAdapter<AdressListData.DataBean>(list, this, R.layout.adress_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, AdressListData.DataBean item, final int postion) {
                if (item.getStatus().equals("1")) {
                    holder.setImageResource(R.id.checkbox, R.mipmap.mrdz);
                    mSelectedPos = postion;
                } else {
                    holder.setImageResource(R.id.checkbox, R.mipmap.kongh);
                }
                holder.setText(R.id.name, item.getConsignee());
                holder.setText(R.id.phone, item.getPhone());
                holder.setText(R.id.adress, item.getProvince() + "-" + item.getCity() + "-" + item.getArea() + "-" + item.getOther());
                /*删除地址*/
                holder.getView(R.id.dele).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleAdress(postion);
                    }
                });

                /*编辑地址*/
                holder.getView(R.id.set).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent in = new Intent(MyAdressActivity.this, SetAdressActivity.class);
                        in.putExtra("name", list.get(postion).getConsignee());
                        in.putExtra("sheng", list.get(postion).getProvince());
                        in.putExtra("shi", list.get(postion).getCity());
                        in.putExtra("qu", list.get(postion).getArea());
                        in.putExtra("id", list.get(postion).getAddress_id());
                        in.putExtra("phone", list.get(postion).getPhone());
                        in.putExtra("other", list.get(postion).getOther());
                        startActivityForResult(in, 2);
                    }
                });
            }
        };
        adapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (mSelectedPos != position) {
                    //先取消上个item的勾选状态
                    if (mSelectedPos != -1) {
                        list.get(mSelectedPos).setStatus("0");
                        adapter.notifyItemChanged(mSelectedPos);
                    }
                    //设置新Item的勾选状态
                    mSelectedPos = position;
                    list.get(mSelectedPos).setStatus("1");
                    adapter.notifyItemChanged(mSelectedPos);
                }
                setAddress(list.get(position).getAddress_id());
                /*设置地址，名字等等，传回上一界面 */
                Intent intent = new Intent();
                intent.putExtra("id", list.get(position).getAddress_id());
                intent.putExtra("adress", list.get(position).getProvince() + list.get(position).getCity() + list.get(position).getArea() + list.get(position).getOther());
                intent.putExtra("name", list.get(position).getConsignee());
                intent.putExtra("phone", list.get(position).getPhone());
                setResult(1, intent);
            }
        });
        addresslist.setAdapter(adapter);

        /***获取地址列表*/
        getDataList();

    }

    private void deleAdress(final int postion) {
        TextDialog textDialog = new TextDialog();
        textDialog.setTextStr("是否删除？");
        textDialog.setOkClick(new TextDialog.OkClick() {
            @Override
            public void click() {
                deleAddress(list.get(postion).getAddress_id());
                list.remove(postion);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void no() {

            }
        });
        textDialog.show(getSupportFragmentManager(), "tag");

    }

    private void getDataList() {
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("uid", MyApplication.getmInstance().getUid());//请求参数二
        Post(HttpUntil.GetIntent().addressList(), formBuilder, 1, true);
    }

    /*设置默认地址*/
    public void setAddress(String id) {
        /***获取地址列表*/
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("uid", MyApplication.getmInstance().getUid());//请求参数二
        formBuilder.add("address_id", id);//请求参数二
        Post(HttpUntil.GetIntent().setDefaultAddress(), formBuilder, 2, false);
    }

    /*删除地址*/
    public void deleAddress(String id) {
        /***获取地址列表*/
        FormBody.Builder formBuilder = new FormBody.Builder();
        formBuilder.add("uid", MyApplication.getmInstance().getUid());//请求参数二
        formBuilder.add("address_id", id);//请求参数二
        Post(HttpUntil.GetIntent().deleteAddress(), formBuilder, 3, false);
    }

    @Override
    public void GetHttpBackStr(String str, int what) {
        switch (what) {
            case 1:
                if (codeIsOne(str, false)) {
                    AdressListData data = new Gson().fromJson(str, AdressListData.class);
                    list.clear();
                    list.addAll(data.getData());
                    adapter.notifyDataSetChanged();
                }
                break;
            case 2:
                if (codeIsOne(str)) {
                    finish();
                }

            case 3:///删除地址返回break;
                if (!codeIsOne(str)) {
                    getDataList();//删除失败的话，重新请求列表
                }
                break;
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1://添加返回
                if (resultCode == 2) getDataList();
                break;
            case 2:/*编辑返回*/
                if (resultCode == 2) getDataList();
                break;
        }

    }

    @OnClick(R.id.confirm)
    public void onClick() {
        Intent in = new Intent(this, AddAdressActivity.class);
        startActivityForResult(in, 1);
    }
}
