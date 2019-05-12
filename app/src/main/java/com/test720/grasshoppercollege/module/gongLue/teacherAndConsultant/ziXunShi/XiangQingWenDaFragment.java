package com.test720.grasshoppercollege.module.gongLue.teacherAndConsultant.ziXunShi;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseRecyclerViewFragment;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.gongLue.teacher.pinTaiZiXun.wenDa.WenDaHuiFuActivity;
import com.test720.grasshoppercollege.module.gongLue.teacherAndConsultant.bean.PeiXunTeacherXiangQingData;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

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
 * 作者：水东流 编于 2018/10/7
 */
public class XiangQingWenDaFragment extends BaseRecyclerViewFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    TextView price;

    public TextView getPrice() {
        return price;
    }

    public void setPrice(TextView price) {
        this.price = price;
    }

    MyBaseRecyclerAdapter adapter;
    private List<PeiXunTeacherXiangQingData.DataBean.AnswerBean> answer = new ArrayList<>();

    public List<PeiXunTeacherXiangQingData.DataBean.AnswerBean> getAnswer() {
        return answer;
    }

    public void setAnswer(List<PeiXunTeacherXiangQingData.DataBean.AnswerBean> answer) {
        this.answer = answer;
    }

    @Override
    public void setListener() {

    }

    @Override
    public int setlayoutResID() {
        return R.layout.base_shua_xin;
    }


    @Override
    public int setCount() {
        return 1;
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
        return HttpUntil.GetIntent().StrategyteacherAnswerList();
    }

    @Override
    public void shuaXin(String str) {

    }


    @Override
    public void gengDuo(String str) {

    }

    @Override
    public HttpParams httpParams() {
        return null;
    }

    @Override
    public void init() {
        adapter = new MyBaseRecyclerAdapter<PeiXunTeacherXiangQingData.DataBean.AnswerBean>(answer, mContext, R.layout.wen_da_list_item) {
            @Override
            public void convert(final BaseRecyclerHolder holder, final PeiXunTeacherXiangQingData.DataBean.AnswerBean item, final int postion) {
                holder.setText(R.id.content, "问：" + item.getQuestion());
                holder.setText(R.id.name, item.getUser_nickname());
                String s = item.getQuestion_time();
                holder.setText(R.id.time, s);
                holder.setText(R.id.answer, " 答：" + item.getAnswer());
                holder.setHeaderByUrl(R.id.pic, item.getUser_header());
                holder.getView(R.id.down).setVisibility(View.GONE);
            }
        };
        adapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(mContext, WenDaHuiFuActivity.class);
                intent.putExtra("answer_id", answer.get(position).getAnswer_id());
                intent.putExtra("type", WenDaHuiFuActivity.TEACHER_YHF);
                jumpToActivity(intent, false);

            /*    Intent intent = new Intent(mContext, WenDaHuiFuActivity.class);
                intent.putExtra("answer_id", answer.get(position).getAnswer_id());
                intent.putExtra("type", WenDaHuiFuActivity.TEACHER_YHF);
                jumpToActivity(intent, false);*/
            }
        });
        recyclerView.setAdapter(adapter);

    }

    @Override
    public void onStart() {
        super.onStart();
        if (adapter != null) adapter.notifyDataSetChanged();
    }
}

