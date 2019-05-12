package com.test720.grasshoppercollege.module.xieHouYu.tongguan;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseTiMuOkFragment;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.publicQuestion.AnswerDialog;
import com.test720.grasshoppercollege.untils.HttpUntil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by 水东流 on 2018/7/6.
 */

public class XieHouTongGuanFragment extends BaseTiMuOkFragment {
    TongGuanData.DataBean dataBean;
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
    public void initData() {
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 6);
        //设置为垂直布局，这也是默认的
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);

        GridLayoutManager layoutManager1 = new GridLayoutManager(getActivity(), 5);
        //设置为垂直布局，这也是默认的
        //设置布局管理器
        title.setLayoutManager(layoutManager1);
    }


    @Override
    public void onStart() {
        super.onStart();
        if (getDataBean() == null) return;
        cn.setText(getDataBean().getQuestion());
        /*创建底部集合*/
        downList.clear();
        for (int i = 0; i < getDataBean().getQuestion_content().size(); i++) {
            downList.add(new DownItem(getDataBean().getQuestion_content().get(i), true));
        }
        /*创建顶部集合*/
        if (topList.size() == 0) {
            for (int i = 0; i < getDataBean().getAnswer().size(); i++) {
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
                    holder.setText(R.id.text, getDataBean().getQuestion_content().get(item.getIndex()));
                holder.getView(R.id.text).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isAnswer) return;
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

    @Override
    public int setlayoutResID() {
        return R.layout.xie_hou_yu_tong_guan_ti;
    }

    /*点击事件*/
    private void itemClick(int position, View view) {
        if (isAnswer) return;
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
            isAnswer = true;
            setAnswerIsRight(true);
            for (int t = 0; t < getDataBean().getAnswer().size(); t++) {
                //判断答案是否正确
                if (!getDataBean().getAnswer().get(t).equals(getDataBean().getQuestion_content().get(topList.get(t).getIndex()))) {
                    setAnswerIsRight(false);
                    getBaseTiMuActivity().setFen(getBaseTiMuActivity().getFen() - 1);
                    break;
                }
            }

            danCiTiMuAnswerDialog = new AnswerDialog();
            String a = "";
            for (int i = 0; i < getDataBean().getAnswer().size(); i++) {
                a = a + getDataBean().getAnswer().get(i) + "";
            }
            danCiTiMuAnswerDialog.setAnswerTxt(a);
            danCiTiMuAnswerDialog.setDeceTxt(getDataBean().getDesc());
            danCiTiMuAnswerDialog.setRight(answerIsRight);
            danCiTiMuAnswerDialog.setAnswerInterface(new AnswerDialog.AnswerInterface() {
                @Override
                public void nextClick() {
                    if (getViewPager().getAdapter() == null) return;
                    if (getViewPager().getCurrentItem() < getViewPager().getAdapter().getCount() - 1) {
                        getViewPager().setCurrentItem(getViewPager().getCurrentItem() + 1);
                    } else {
                        AddGuoGuoDou();
                    }
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

    /***********************************************8*/

    public TongGuanData.DataBean getDataBean() {
        return dataBean;
    }

    public void setDataBean(TongGuanData.DataBean dataBean) {
        this.dataBean = dataBean;
    }

    @Override
    public void TongGuan() {
        HttpParams httpParams1 = new HttpParams();
        httpParams1.put("uid", MyApplication.getmInstance().getUid());
        httpParams1.put("fraction", getBaseTiMuActivity().getFen());
        httpParams1.put("xhy_id", getBaseTiMuActivity().getId());
        httpParams1.put("type", getBaseTiMuActivity().getType());
        post(HttpUntil.GetIntent().ChineseXieHouYuadopt(), httpParams1, 124);
    }

    @Override
    public String GXShareTitile() {
        return "歇后语通关";
    }

    @Override
    public String corrId() {
        return null;
    }

    @Override
    public String questionId() {
        return getDataBean().getQuestion_id();
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
