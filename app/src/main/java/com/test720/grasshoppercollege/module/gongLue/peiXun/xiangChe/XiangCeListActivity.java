package com.test720.grasshoppercollege.module.gongLue.peiXun.xiangChe;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseTwoRecyclerViewActivity;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.gongLue.GongLueData;
import com.test720.grasshoppercollege.module.gongLue.peiXun.bean.XiangCeListData;
import com.test720.grasshoppercollege.untils.HttpUntil;

import butterknife.BindView;
import okhttp3.FormBody;

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
 * 作者：水东流 编于 2018/8/22
 */
public class XiangCeListActivity extends BaseTwoRecyclerViewActivity<XiangCeListData.DataBean> {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;


    @Override
    protected int setlayoutResID() {
        return R.layout.base_shua_xin;
    }


    @Override
    public ReflshInit reflshInit() {
        return new ReflshInit() {
            @Override
            public String reTitle() {
                if (getIntent().getStringExtra("title") != null)
                    return getIntent().getStringExtra("title");
                return "相册列表";
            }

            @Override
            public FormBody.Builder reFormBuilder() {
                FormBody.Builder builder = new FormBody.Builder();
                builder.add("school_id", GongLueData.getInstance().getSchoolId());
                builder.add("photo_type", GongLueData.getInstance().getPhotoType());
                builder.add("p", page + "");
                return builder;
            }

            @Override
            public String reUrl() {
                return HttpUntil.GetIntent().StrategyTrainschoolPhoto();
            }

            @Override
            public int reCount() {
                return 2;
            }

            @Override
            public RecyclerView reRecyclerView() {
                return recyclerView;
            }

            @Override
            public SwipeRefreshLayout reSwipeRefreshLayout() {
                return swipeRefresh;
            }

        };
    }

    @Override
    protected void initData() {
        setAdapter(new MyBaseRecyclerAdapter<XiangCeListData.DataBean>(getList(), mcontext, R.layout.xiang_ce_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, XiangCeListData.DataBean item, int postion) {
                holder.setText(R.id.text, item.getName());
                holder.setText(R.id.num, item.getCount());
                holder.setImageByUrl(R.id.img, item.getPic());
            }
        });
        getAdapter().setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(mcontext, XiangCeXiangQingActivity.class);
                intent.putExtra("title", getList().get(position).getName());
                intent.putExtra("photo_id", getList().get(position).getPhoto_id());
                jumpToActivity(intent, false);
            }
        });
        recyclerView.setAdapter(getAdapter());
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            XiangCeListData data = new Gson().fromJson(str, XiangCeListData.class);
            getList().clear();
            getList().addAll(data.getData());
            getAdapter().notifyDataSetChanged();

        }

    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            XiangCeListData data = new Gson().fromJson(str, XiangCeListData.class);
            getList().addAll(data.getData());
            getAdapter().notifyDataSetChanged();
        }
    }

}
