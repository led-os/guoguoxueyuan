package com.test720.grasshoppercollege.module.gongLue.teacherAndConsultant;

import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.WindowManager;
import android.widget.TextView;

import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseOkDialogFragment;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.gongLue.teacherAndConsultant.bean.PeiXunTeacherXiangQingData;

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
 * 作者：水东流 编于 2018/9/21
 */
public class FuWuDialog extends BaseOkDialogFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.cancel)
    TextView cancel;
    private List<PeiXunTeacherXiangQingData.DataBean.ServiceBean> service = new ArrayList<>();

    public void setService(List<PeiXunTeacherXiangQingData.DataBean.ServiceBean> service) {
        this.service = service;
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
        return R.layout.fu_wu_dialog;
    }

    @Override
    public void start() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), 1));
        MyBaseRecyclerAdapter adapter = new MyBaseRecyclerAdapter<PeiXunTeacherXiangQingData.DataBean.ServiceBean>(service, getActivity(), R.layout.fu_wu_text) {
            @Override
            public void convert(BaseRecyclerHolder holder, PeiXunTeacherXiangQingData.DataBean.ServiceBean item, int postion) {
                holder.setText(R.id.text, item.getName());
            }
        };
        adapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                if (getId != null) getId.getId(service.get(position).getService_id());
                dismissAllowingStateLoss();
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void getSuccess(String s, int what) {

    }

    @OnClick(R.id.cancel)
    public void onViewClicked() {
        dismissAllowingStateLoss();
    }

    GetId getId;

    public void setGetId(GetId getId) {
        this.getId = getId;
    }

    public interface GetId {
        void getId(String Id);
    }
}
