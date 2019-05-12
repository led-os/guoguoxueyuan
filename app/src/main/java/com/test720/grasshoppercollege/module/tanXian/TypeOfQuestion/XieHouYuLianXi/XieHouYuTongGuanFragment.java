package com.test720.grasshoppercollege.module.tanXian.TypeOfQuestion.XieHouYuLianXi;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.tanXian.TypeOfQuestion.BaseTanXianTiMuFragment;
import com.test720.grasshoppercollege.publicQuestion.AnswerDialog;

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
 * 作者：水东流 编于 2018/11/12
 */
public class XieHouYuTongGuanFragment extends BaseTanXianTiMuFragment {
    @BindView(R.id.cn)
    TextView cn;
    @BindView(R.id.title)
    RecyclerView title;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;


    List<DownItem> downList = new ArrayList<>();//存放底部字的集合
    List<TopItem> topList = new ArrayList<>();//存放底部字的集合
    MyBaseRecyclerAdapter downAdapter;
    MyBaseRecyclerAdapter topAdapter;
    AnswerDialog danCiTiMuAnswerDialog;

    @Override
    public void startShow() {
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 6);
        //设置为垂直布局，这也是默认的
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);

        GridLayoutManager layoutManager1 = new GridLayoutManager(getActivity(), 5);
        //设置为垂直布局，这也是默认的
        //设置布局管理器
        title.setLayoutManager(layoutManager1);


        if (getBean() == null) return;
        cn.setText(getBean().getQuestion());
        downList.clear();
        /*创建底部集合*/
        for (int i = 0; i < getBean().getQuestion_content_select().size(); i++) {
            downList.add(new DownItem(getBean().getQuestion_content_select().get(i), true));
        }
        /*创建顶部集合*/
        if (topList.size() == 0) {
            for (int i = 0; i < getBean().getAnswer().size(); i++) {
                topList.add(new TopItem(-1, true));
            }
        }

        downAdapter = new MyBaseRecyclerAdapter<DownItem>(downList, getActivity(), R.layout.cheng_yu_text_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, DownItem item, final int postion) {
                holder.setText(R.id.text, item.getText());
                if (item.isShow) {
                    holder.getView(R.id.text).setVisibility(View.VISIBLE);
                } else {
                    holder.getView(R.id.text).setVisibility(View.INVISIBLE);
                }
                holder.getView(R.id.text).setBackgroundResource(R.drawable.yello_back_kuang);
                holder.getView(R.id.text).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        itemClick(postion, v);
                    }
                });
            }
        };
        recyclerView.setAdapter(downAdapter);
        topAdapter = new MyBaseRecyclerAdapter<TopItem>(topList, getActivity(), R.layout.cheng_yu_text_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, TopItem item, final int postion) {
                if (item.getIndex() != -1)
                    holder.setText(R.id.text, getBean().getQuestion_content_select().get(item.getIndex()));
                holder.getView(R.id.text).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isAnswer()) return;
                        if (topList.get(postion).getIndex() == -1) return;//未填空的item点击无效
                        topList.get(postion).setNeedBu(true);
                        downList.get(topList.get(postion).getIndex()).setShow(true);
                        topList.get(postion).setIndex(-1);
                        downAdapter.notifyDataSetChanged();
                        topAdapter.notifyDataSetChanged();
                    }
                });
            }
        };
        title.setAdapter(topAdapter);

    }

    /*点击事件*/
    private void itemClick(int position, View view) {
        if (isAnswer()) return;
        //**填充**/
        for (int i = 0; i < topList.size(); i++) {
            boolean b = topList.get(i).isNeedBu();
            if (b) {
                topList.get(i).setIndex(position);//填充位置
                topList.get(i).setNeedBu(false);//标记为不需要补充
                view.setVisibility(View.INVISIBLE);//隐藏下部按钮
                downList.get(position).setShow(false);
                topAdapter.notifyDataSetChanged();//更新顶部ui
                break;
            }
        }
        boolean noMore = true;
        //****如果无需填充的字，则弹出结果****/
        for (int j = 0; j < topList.size(); j++) {
            if (topList.get(j).isNeedBu()) {
                noMore = false;
            }
        }
        //***答题结束***/
        if (noMore) {
            setAnswer(true);
            setAnswerIsRight(true);
            for (int t = 0; t < getBean().getAnswer().size(); t++) {
                //判断答案是否正确
                if (!getBean().getAnswer().get(t).equals(getBean().getQuestion_content_select().get(topList.get(t).getIndex()))) {
                    setAnswerIsRight(false);
                    getBaseTiMuActivity().setFen(getBaseTiMuActivity().getFen() - 1);
                    break;
                }
            }
            String a = "";
            for (int i = 0; i < getBean().getAnswer().size(); i++) {
                a = a + getBean().getAnswer().get(i) + "  ";
            }
            danCiTiMuAnswerDialog = new AnswerDialog();
            danCiTiMuAnswerDialog.setAnswerTxt(a);
            danCiTiMuAnswerDialog.setDeceTxt(getBean().getDesc());
            danCiTiMuAnswerDialog.setRight(isAnswerIsRight());
            danCiTiMuAnswerDialog.setAnswerInterface(new AnswerDialog.AnswerInterface() {
                @Override
                public void nextClick() {
                    nextItem();
                }

                @Override
                public String nextTxt() {
                    return null;
                }

                @Override
                public int nextBack() {
                    return -1;
                }

                @Override
                public int linBack() {
                    return R.mipmap.jiexikuang;
                }
            });
            assert getFragmentManager() != null;
            danCiTiMuAnswerDialog.show(getFragmentManager(), "answer");
        }

    }

    @Override
    public int setlayoutResID() {
        return R.layout.xie_hou_yu_tong_guan_ti;
    }

    /*底部item*/
    class DownItem {
        String text;
        boolean isShow;

        DownItem(String text, boolean isShow) {
            this.text = text;
            this.isShow = isShow;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public boolean isShow() {
            return isShow;
        }

        public void setShow(boolean show) {
            isShow = show;
        }
    }


    /*顶部item*/
    class TopItem {
        int index;//补充此view对应的下部列表位置
        boolean needBu;

        TopItem(int index, boolean needBu) {
            this.index = index;
            this.needBu = needBu;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        boolean isNeedBu() {
            return needBu;
        }

        public void setNeedBu(boolean needBu) {
            this.needBu = needBu;
        }
    }

}
