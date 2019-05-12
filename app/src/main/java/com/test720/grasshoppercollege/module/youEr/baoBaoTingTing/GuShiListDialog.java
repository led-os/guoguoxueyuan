package com.test720.grasshoppercollege.module.youEr.baoBaoTingTing;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;

import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.youEr.baoBaoTingTing.bean.GuShiNeiRongData;
import com.test720.grasshoppercollege.myViews.BottomListDialog;
import com.test720.grasshoppercollege.untils.musicMedia.MusicServiceData;

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
 * 作者：水东流 编于 2018/9/5
 */
public class GuShiListDialog extends BottomListDialog<GuShiNeiRongData.DataBean.ListBean> {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.re1)
    RelativeLayout re1;

    @Override
    public DialogInit dialogInit() {
        return new DialogInit() {
            @Override
            public boolean outTouch() {
                return true;
            }

            @Override
            public int gravity() {
                return Gravity.BOTTOM;
            }

            @Override
            public int widthLayout() {
                return WindowManager.LayoutParams.MATCH_PARENT;
            }

            @Override
            public int heightLayout() {
                return WindowManager.LayoutParams.MATCH_PARENT;
            }
        };
    }

    @Override
    public int layoutId() {
        return R.layout.rel_list;
    }

    @Override
    public void start() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        View foot = LayoutInflater.from(getContext()).inflate(R.layout.time_foot, null);
        foot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissAllowingStateLoss();
            }
        });
        setAdapter(new MyBaseRecyclerAdapter<GuShiNeiRongData.DataBean.ListBean>(MusicServiceData.getInstance().getList()
                , getActivity(), R.layout.music_zhuan_ji_list_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, GuShiNeiRongData.DataBean.ListBean item, final int postion) {
                holder.setImageByUrl(R.id.img, item.getCover());
                holder.setText(R.id.title, item.getTitle_t());
                if (item.getTitle_t().equals(MusicServiceData.getInstance().getTitle())) {
                    holder.getView(R.id.dele).setVisibility(View.GONE);
                } else {
                    holder.getView(R.id.dele).setVisibility(View.VISIBLE);
                }
                holder.getView(R.id.dele).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MusicServiceData.getInstance().getList().remove(postion);
                        if (postion < MusicServiceData.getInstance().getIndex()) {//如果删除的是正在播放之前的元素，则指针前移
                            MusicServiceData.getInstance().setIndex(MusicServiceData.getInstance().getIndex() - 1);
                        }
                        getAdapter().notifyDataSetChanged();
                    }
                });
            }
        });
        getAdapter().setmFoot(foot);
        getAdapter().setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                MusicServiceData.getInstance().setIndex(position);
                if (itemClick != null) itemClick.itemClick();
                dismissAllowingStateLoss();
            }
        });
        recyclerView.setAdapter(getAdapter());
    }

    @Override
    public void getSuccess(String s, int what) {

    }

    public interface ItemClick {
        void itemClick();
    }

    ItemClick itemClick;

    public void setItemClick(ItemClick itemClick) {
        this.itemClick = itemClick;
    }

    @OnClick(R.id.re1)
    public void onViewClicked() {
        dismissAllowingStateLoss();
    }
}
