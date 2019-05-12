package com.test720.grasshoppercollege.untils.pinLunUntil;

import android.content.Context;
import android.text.SpannableString;
import android.view.View;
import android.widget.TextView;

import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.Adapter.PinJiaChildrenItem;
import com.test720.grasshoppercollege.HttpBean.PingLunData;
import com.test720.grasshoppercollege.ImagePagerActivity;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.myViews.CustomListView;
import com.test720.grasshoppercollege.untils.ExpressionUtil;

import java.util.List;

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
 * 作者：水东流 编于 2018/8/31
 */
public class PinLunUntil {
    private List<PingLunData> list;//评论数据
    private Context context;
    private PinLunInterface pinLunInterface;
    private MyBaseRecyclerAdapter<PingLunData> adapter;//评论适配器

    /**
     * @param pinLunList 评论数据
     * @param activity   界面
     * @param pinLunface 评论接口外调
     */
    public PinLunUntil(List<PingLunData> pinLunList, Context activity, PinLunInterface pinLunface) {
        this.list = pinLunList;
        this.context = activity.getApplicationContext();
        this.pinLunInterface = pinLunface;
        adapter = new MyBaseRecyclerAdapter<PingLunData>(list, context, R.layout.ping_jia) {
            @Override
            public void convert(final BaseRecyclerHolder holder, final PingLunData item, final int postion) {
                holder.setHeaderByUrl(R.id.iv_icon, item.getHeader());
                holder.setText(R.id.time, item.getTime());
                /*评论内容，包含表情*/
                int size = item.getContent().length();
                SpannableString spannableString = ExpressionUtil
                        .getExpressionString(context, item.getContent(), size);
                TextView textView = holder.getView(R.id.body);
                textView.setText(spannableString);

                holder.setText(R.id.zanNum, item.getThumb_up_count());
                holder.setText(R.id.pinlunNum, item.getComment_count());
                final CustomListView listView = holder.getView(R.id.listView);
                /*如果无最新回复，则隐藏回复相关布局*/
                if (item.getChild().size() == 0) {
                    holder.getView(R.id.zuixinhuifu).setVisibility(View.GONE);
                } else {
                    holder.getView(R.id.zuixinhuifu).setVisibility(View.VISIBLE);
                }
                listView.setAdapter(new PinJiaChildrenItem(item.getChild(), this.getContext()));
                /*点赞*/
                holder.getView(R.id.zan).setOnClickListener(new View.OnClickListener() {//对评论点赞
                    @Override
                    public void onClick(View v) {
                        MyApplication.getmInstance().setLoginDialogIsShow(false);
                        if (MyApplication.getmInstance().getUid().equals("")) {
                            return;
                        }
                        pinLunInterface.Zan(item.getComment_id(), postion);
                    }
                });
                if (item.getIs_thumb_up() == 1) {
                    holder.setImageResource(R.id.zanimg, R.mipmap.zan);
                } else {
                    holder.setImageResource(R.id.zanimg, R.mipmap.meizan);
                }
                /*回复*/
                holder.getView(R.id.huiFu).setOnClickListener(new View.OnClickListener() {//对评论点赞
                    @Override
                    public void onClick(View v) {
                        MyApplication.getmInstance().setLoginDialogIsShow(false);
                        if (MyApplication.getmInstance().getUid().equals("")) {
                            return;
                        }
                        pinLunInterface.Reply(item.getComment_id(), item.getNickname());
                    }
                });

                /*名字*/
                holder.setText(R.id.name, item.getNickname());

                /*是否vip*/
                if (item.getVip() == 1) {
                    holder.setImageResource(R.id.vip, R.mipmap.vip);
                } else {
                    holder.setImageResource(R.id.vip, R.mipmap.novip);
                }

                /*照片*/
                holder.getView(R.id.one).setVisibility(View.GONE);
                holder.getView(R.id.two).setVisibility(View.GONE);
                holder.getView(R.id.three).setVisibility(View.GONE);
                for (int i = 0; i < item.getImg().size(); i++) {
                    switch (i) {
                        case 0:
                            holder.getView(R.id.one).setVisibility(View.VISIBLE);
                            holder.setImageByUrl(R.id.one, item.getImg().get(0));
                            holder.getView(R.id.one).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ImagePagerActivity.startImagePagerActivity(context, item.getImg(), 0, new ImagePagerActivity
                                            .ImageSize(v.getMeasuredWidth(), v.getMeasuredHeight()));
                                }
                            });
                            break;
                        case 1:
                            holder.getView(R.id.two).setVisibility(View.VISIBLE);
                            holder.setImageByUrl(R.id.two, item.getImg().get(1));
                            holder.getView(R.id.two).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ImagePagerActivity.startImagePagerActivity(context, item.getImg(), 1, new ImagePagerActivity
                                            .ImageSize(v.getMeasuredWidth(), v.getMeasuredHeight()));
                                }
                            });
                            break;
                        case 2:
                            holder.getView(R.id.three).setVisibility(View.VISIBLE);
                            holder.setImageByUrl(R.id.three, item.getImg().get(2));
                            holder.getView(R.id.three).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    ImagePagerActivity.startImagePagerActivity(context, item.getImg(), 2, new ImagePagerActivity
                                            .ImageSize(v.getMeasuredWidth(), v.getMeasuredHeight()));
                                }
                            });
                            break;
                    }
                }
            }
        };
        adapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                MyApplication.getmInstance().setLoginDialogIsShow(false);
                if (MyApplication.getmInstance().getUid().equals("")) {
                    return;
                }
                pinLunInterface.ItemClick(list.get(position));
            }
        });
    }


    public MyBaseRecyclerAdapter<PingLunData> getAdapter() {
        if (adapter != null) {
            return adapter;
        }
        return null;
    }

    /**
     * 评论，回复，点赞，跳转详情，接口
     */
    public interface PinLunInterface {

        void Zan(String commentId, int pos);

        void Reply(String commentId, String name);

        void ItemClick(PingLunData item);

    }


}
