package com.test720.grasshoppercollege.module.userData.myTeam.daiLi;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.WindowManager;

import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseOkDialogFragment;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.userData.myTeam.bean.DaiLiShouData;

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
 * 作者：水东流 编于 2018/10/8
 */
public class DaiLiTitleDialog extends BaseOkDialogFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    private List<DaiLiShouData.DataBean.AreaListBean> area_list;
    MyBaseRecyclerAdapter adapter;

    public void setArea_list(List<DaiLiShouData.DataBean.AreaListBean> area_list) {
        this.area_list = area_list;
    }

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
        return R.layout.dai_li_title_dialog;
    }

    @Override
    public void start() {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        adapter = new MyBaseRecyclerAdapter<DaiLiShouData.DataBean.AreaListBean>(area_list, mContext, R.layout.text_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, DaiLiShouData.DataBean.AreaListBean item, int postion) {
                String s = item.getCity() + item.getArea() + "代理收益";
                holder.setText(R.id.text, s);
            }
        };
        adapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (daiLiId != null) daiLiId.getAngentId(area_list.get(position).getAgent_id());
                dismissAllowingStateLoss();
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void getSuccess(String s, int what) {

    }


    DaiLiId daiLiId;

    public void setDaiLiId(DaiLiId daiLiId) {
        this.daiLiId = daiLiId;
    }


    @OnClick(R.id.root)
    public void onViewClicked() {
        dismissAllowingStateLoss();
    }

    public interface DaiLiId {
        void getAngentId(String Id);
    }
}
