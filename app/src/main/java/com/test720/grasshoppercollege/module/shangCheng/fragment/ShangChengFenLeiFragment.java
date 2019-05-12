package com.test720.grasshoppercollege.module.shangCheng.fragment;

import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.FragmentViewPagerAdapter;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseFragment;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.shangCheng.bean.YiJINeiData;
import com.test720.grasshoppercollege.module.shangCheng.neiBie.NeiBieFragment;
import com.test720.grasshoppercollege.myViews.CustomViewPager;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 佛祖保佑        永无BUG
 * 佛曰:
 * 程序园里程序天，程序天里程序员；
 * 程序猿人写程序，又拿程序换肉钱。
 * 肉饱继续桌前坐，饱暖还是桌前眠；
 * 半迷半醒日复日，码上码下年复年。
 * 但愿叱咤互联世，不愿搬砖码当前；
 * 诸葛周瑜算世事，我算需求得加钱。
 * 别人笑我忒直男，我说自己是程猿；
 * 但见成都府国内，处处地地程序员。
 * 作者：水东流 编于 2018/10/23
 */
public class ShangChengFenLeiFragment extends BaseFragment {
    @BindView(R.id.edittext)
    EditText edittext;
    @BindView(R.id.xiaoxi)
    ImageView xiaoxi;
    @BindView(R.id.typeList)
    RecyclerView typeList;
    @BindView(R.id.viewPager)
    CustomViewPager viewPager;
    MyBaseRecyclerAdapter adapter;
    List<YiJINeiData.DataBean> list = new ArrayList<>();
    int index = 0;
    List<Fragment> fragmentList = new ArrayList<>();

    @Override
    public void initData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        typeList.setLayoutManager(linearLayoutManager);
        typeList.addItemDecoration(new DividerItemDecoration(mContext, 1));
        adapter = new MyBaseRecyclerAdapter<YiJINeiData.DataBean>(list, mContext, R.layout.shang_cheng_type_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, YiJINeiData.DataBean item, int postion) {
                TextView textView = holder.getView(R.id.text);
                holder.setText(R.id.text, item.getName());
                if (index == postion) {
                    textView.setBackgroundResource(R.color.buemain);
                    textView.setTextColor(getResources().getColor(R.color.white));
                } else {
                    textView.setBackgroundResource(R.color.white);
                    textView.setTextColor(getResources().getColor(R.color.gwe));
                }
            }

        };
        adapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                index = position;
                adapter.notifyDataSetChanged();
                viewPager.setCurrentItem(position);
            }
        });

        typeList.setAdapter(adapter);

        post(HttpUntil.GetIntent().MallcateList(), new HttpParams(), 1);
    }

    @Override
    public void setListener() {

    }

    @Override
    public int setlayoutResID() {
        return R.layout.activity_shang_cheng_nei_bie_shou;
    }

    @Override
    public void getSuccess(String str, int what) {
        if (codeIsOne(str, false)) {
            YiJINeiData data = new Gson().fromJson(str, YiJINeiData.class);
            list.clear();
            list.addAll(data.getData());
            adapter.notifyDataSetChanged();
            if (fragmentList.size() != 0) return;
            for (int i = 0; i < list.size(); i++) {
                NeiBieFragment bieFragment = new NeiBieFragment();
                bieFragment.setParentId(list.get(i).getParent_id());
                fragmentList.add(bieFragment);
            }
            viewPager.setAdapter(new FragmentViewPagerAdapter(getChildFragmentManager(), fragmentList));
        }
    }

    @OnClick(R.id.back)
    public void onViewClicked() {
        mContext.finish();
    }
}
