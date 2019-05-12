package com.test720.grasshoppercollege.module.yuWen.chengYu.chengYuBuZi;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.yuWen.chengYu.ChengYuTiMuFragment;
import com.test720.grasshoppercollege.publicQuestion.AnswerDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 水东流 on 2018/7/5.
 */

public class ChengYuBuZiFragment extends ChengYuTiMuFragment {
    @BindView(R.id.one)
    TextView one;
    @BindView(R.id.two)
    TextView two;
    @BindView(R.id.three)
    TextView three;
    @BindView(R.id.four)
    TextView four;
    @BindView(R.id.titleLin)
    LinearLayout titleLin;
    @BindView(R.id.relativeLayout)
    RelativeLayout relativeLayout;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.cn)
    TextView cn;
    private GridLayoutManager layoutManager;
    MyBaseRecyclerAdapter adapter;
    List<TextBean> textList = new ArrayList<>();//存放顶部4个字的集合
    List<Item> itemList = new ArrayList<>();//存放底部字的集合

    @Override
    public void initData() {
        layoutManager = new GridLayoutManager(getActivity(), 6);
        //设置为垂直布局，这也是默认的
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        textList.add(new TextBean(one, -1, false));
        textList.add(new TextBean(two, -1, false));
        textList.add(new TextBean(three, -1, false));
        textList.add(new TextBean(four, -1, false));

    }

    private void initAddData() {
        if (getDataBean() == null) return;
        if (getDataBean().getQuestion_type().equals("1")) {
            cn.setText(getDataBean().getQuestion());
            relativeLayout.setVisibility(View.VISIBLE);
        } else if (getDataBean().getQuestion_type().equals("2")) {
            Glide.with(getActivity()).load(getDataBean().getQuestion()).into(img);
            relativeLayout.setVisibility(View.VISIBLE);
        } else {
            relativeLayout.setVisibility(View.INVISIBLE);
        }
        /*创建头部集合*/

        for (int i = 0; i < getDataBean().getQuestion_content().size(); i++) {
            textList.get(i).getTextview().setText(getDataBean().getQuestion_content().get(i));
            if (getDataBean().getQuestion_content().get(i).equals("")) {
                textList.get(i).setNeedBu(true);
            } else {
                textList.get(i).setNeedBu(false);
            }
        }
        /*创建底部集合*/
        itemList.clear();
        for (int i = 0; i < getDataBean().getQuestion_content1().size(); i++) {
            itemList.add(new Item(getDataBean().getQuestion_content1().get(i), true));
        }
        adapter = new MyBaseRecyclerAdapter<Item>(itemList, getActivity(), R.layout.cheng_yu_text_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, Item item, final int postion) {
                holder.setText(R.id.text, item.getText());
                if (item.isShow) {
                    holder.getView(R.id.text).setVisibility(View.VISIBLE);
                } else {
                    holder.getView(R.id.text).setVisibility(View.INVISIBLE);
                }
                holder.getView(R.id.text).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        itemClick(postion, v);
                    }
                });
            }
        };
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setListener() {

    }


    @Override
    public void onStart() {
        super.onStart();
        initAddData();
    }

    /*点击事件*/
    private void itemClick(int position, View view) {
        if (isAnswer) return;
        /**填充**/
        for (int i = 0; i < textList.size(); i++) {
            boolean b = (boolean) textList.get(i).isNeedBu();
            if (b) {
                textList.get(i).getTextview().setText(getDataBean().getQuestion_content1().get(position));
                textList.get(i).setIndex(position);//标记位置
                textList.get(i).getTextview().setBackgroundResource(R.mipmap.chengyuzikuangtwo);//跟换背景
                textList.get(i).setNeedBu(false);//标记为不需要补充
                view.setVisibility(View.INVISIBLE);//隐藏下部按钮
                itemList.get(position).setShow(false);
                break;
            }
        }
        boolean noMore = true;
        /****如果无需填充的字，则弹出结果****/
        for (int j = 0; j < textList.size(); j++) {
            if (textList.get(j).isNeedBu()) {
                noMore = false;
            }
        }
        /***答题结束***/
        if (noMore) {
            isAnswer = true;
            setAnswerIsRight(true);
            for (int t = 0; t < getDataBean().getAnswer().size(); t++) {
                //判断答案是否正确
                if (!getDataBean().getAnswer().get(t).equals(textList.get(t).getTextview().getText().toString())) {
                    setAnswerIsRight(false);
                    getBaseTiMuActivity().setFen(getBaseTiMuActivity().getFen() - 1);
                    break;
                }
            }

            AnswerDialog danCiTiMuAnswerDialog = new AnswerDialog();
            String a = "";
            for (int i = 0; i < getDataBean().getAnswer().size(); i++) {
                a = a + getDataBean().getAnswer().get(i);
            }
            danCiTiMuAnswerDialog.setAnswerTxt(a);
            danCiTiMuAnswerDialog.setDeceTxt(getDataBean().getDesc());
            danCiTiMuAnswerDialog.setRight(answerIsRight);
            danCiTiMuAnswerDialog.setAnswerInterface(new AnswerDialog.AnswerInterface() {
                @Override
                public void nextClick() {
                    if (getViewPager().getAdapter() != null && getViewPager().getCurrentItem() < getViewPager().getAdapter().getCount() - 1) {
                        getViewPager().setCurrentItem(getViewPager().getCurrentItem() + 1);
                    } else {
                        AddGuoGuoDou();
                    }
                }

                @Override
                public String nextTxt() {
                    return "下一题";
                }

                @Override
                public int nextBack() {
                    return R.mipmap.xiati;
                }

                @Override
                public int linBack() {
                    return R.mipmap.jiexit;
                }
            });
            danCiTiMuAnswerDialog.show(getChildFragmentManager(), "answer");
        }

    }

    @Override
    public int setlayoutResID() {
        return R.layout.cheng_yu_bu_cong_ti;
    }


    @OnClick({R.id.one, R.id.two, R.id.three, R.id.four})
    public void onViewClicked(View view) {
        if(isAnswer)return;
        switch (view.getId()) {
            case R.id.one:
                if (textList.get(0).getIndex() != -1) {
                    itemList.get(textList.get(0).getIndex()).setShow(true);
                    adapter.notifyDataSetChanged();
                    textList.get(0).getTextview().setText("");
                    textList.get(0).setIndex(-1);
                    textList.get(0).setNeedBu(true);
                    textList.get(0).getTextview().setBackgroundResource(R.drawable.yello_back_kuang);
                }
                break;
            case R.id.two:
                if (textList.get(1).getIndex() != -1) {
                    itemList.get(textList.get(1).getIndex()).setShow(true);
                    adapter.notifyDataSetChanged();
                    textList.get(1).getTextview().setText("");
                    textList.get(1).setIndex(-1);
                    textList.get(1).setNeedBu(true);
                    textList.get(1).getTextview().setBackgroundResource(R.drawable.yello_back_kuang);
                }
                break;
            case R.id.three:
                if (textList.get(2).getIndex() != -1) {
                    itemList.get(textList.get(2).getIndex()).setShow(true);
                    adapter.notifyDataSetChanged();
                    textList.get(2).getTextview().setText("");
                    textList.get(2).setIndex(-1);
                    textList.get(2).setNeedBu(true);
                    textList.get(2).getTextview().setBackgroundResource(R.drawable.yello_back_kuang);
                }
                break;
            case R.id.four:
                if (textList.get(3).getIndex() != -1) {
                    itemList.get(textList.get(3).getIndex()).setShow(true);
                    adapter.notifyDataSetChanged();
                    textList.get(3).getTextview().setText("");
                    textList.get(3).setIndex(-1);
                    textList.get(3).setNeedBu(true);
                    textList.get(3).getTextview().setBackgroundResource(R.drawable.yello_back_kuang);
                }
                break;
        }
    }

    class Item {
        String text;
        boolean isShow;

        public Item(String text, boolean isShow) {
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


    class TextBean {
        TextView textview;
        int index;//补充此view对应的下部列表位置
        boolean needBu;

        public TextBean(TextView textview, int index, boolean needBu) {
            this.textview = textview;
            this.index = index;
            this.needBu = needBu;
        }

        public TextView getTextview() {
            return textview;
        }

        public void setTextview(TextView textview) {
            this.textview = textview;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        public boolean isNeedBu() {
            return needBu;
        }

        public void setNeedBu(boolean needBu) {
            this.needBu = needBu;
        }
    }
}
