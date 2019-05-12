package com.test720.grasshoppercollege.module.userData.geRenZiLiao;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.BaseRecyclerViewFragment;
import com.test720.grasshoppercollege.HttpBean.HeaderData;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 水东流 on 2018/4/9.
 */
/*头像共类*/
public abstract class BaseHeaderFragment extends BaseRecyclerViewFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    HeaderAdapter adapter;
    List<HeaderData.DataBean.NoObtainBean> list = new ArrayList<>();
    List<HeaderData.DataBean.ObtainBean> list1 = new ArrayList<>();
    HeaderAdapter.ImgClick imgclick;

    public void setImgclick(HeaderAdapter.ImgClick imgclick) {
        this.imgclick = imgclick;
    }

    @Override
    public int setCount() {
        return 3;
    }

    @Override
    public RecyclerView initRecyclerView() {
        return recyclerView;
    }

    @Override
    public SwipeRefreshLayout initSwipeRefreshLayout() {
        return swipeRefresh;
    }

    @Override
    public String url() {
        return HttpUntil.GetIntent().clickHeader();
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            HeaderData data = new Gson().fromJson(str, HeaderData.class);
            list.clear();
            list.addAll(data.getData().getNo_obtain());
            list1.clear();
            list1.addAll(data.getData().getObtain());
            adapter.notifyDataSetChanged();
        }
    }

    //放出刷新方法
    public void upData() {
        ShuaXin();
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            HeaderData data = new Gson().fromJson(str, HeaderData.class);
            list.addAll(data.getData().getNo_obtain());
            adapter.notifyDataSetChanged();
        }
    }

    public abstract int type();

    @Override
    public HttpParams httpParams() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        httpParams.put("p", page);
        httpParams.put("type", type());
        return httpParams;
    }

    @Override
    public void init() {
        adapter = new HeaderAdapter(list, list1, getActivity());
        if (imgclick != null) adapter.setImgClick(imgclick);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public void setListener() {

    }

    @Override
    public int setlayoutResID() {
        return R.layout.base_header_list;
    }


}
