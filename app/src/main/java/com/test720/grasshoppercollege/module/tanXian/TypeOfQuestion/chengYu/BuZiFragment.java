package com.test720.grasshoppercollege.module.tanXian.TypeOfQuestion.chengYu;

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
import com.test720.grasshoppercollege.module.tanXian.TypeOfQuestion.BaseTanXianTiMuFragment;
import com.test720.grasshoppercollege.publicQuestion.AnswerDialog;

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
 * 作者：水东流 编于 2018/11/12
 */
public class BuZiFragment extends BaseTanXianTiMuFragment {
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
    public void startShow() {
        layoutManager = new GridLayoutManager(getActivity(), 6);
        //设置为垂直布局，这也是默认的
        //设置布局管理器
        recyclerView.setLayoutManager(layoutManager);
        textList.add(new TextBean(one, -1, false));
        textList.add(new TextBean(two, -1, false));
        textList.add(new TextBean(three, -1, false));
        textList.add(new TextBean(four, -1, false));


        if (getBean().getType().equals("31")) {//成语接龙，成语补字
            relativeLayout.setVisibility(View.GONE);
        } else if (getBean().getType().equals("32")) {//成语接龙，看图猜成语
            relativeLayout.setVisibility(View.VISIBLE);
            Glide.with(getActivity()).load(getBean().getQuestion()).into(img);
        } else if (getBean().getType().equals("33")) {//成语接龙，看中午猜成语
            relativeLayout.setVisibility(View.VISIBLE);
            cn.setText(getBean().getQuestion());
        }
        /*创建头部集合*/
        for (int i = 0; i < getBean().getTo_fill().size(); i++) {
            textList.get(i).getTextview().setText(getBean().getTo_fill().get(i));
            if (getBean().getTo_fill().get(i).equals("")) {
                textList.get(i).setNeedBu(true);
            } else {
                textList.get(i).setNeedBu(false);
            }
        }
        /*创建底部集合*/
        for (int i = 0; i < getBean().getQuestion_content_select().size(); i++) {
            itemList.add(new Item(getBean().getQuestion_content_select().get(i), true));
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

    /*点击事件*/
    private void itemClick(int position, View view) {
        if (isAnswer()) return;
        /**填充**/
        for (int i = 0; i < textList.size(); i++) {
            boolean b = (boolean) textList.get(i).isNeedBu();
            if (b) {
                textList.get(i).getTextview().setText(getBean().getQuestion_content_select().get(position));
                textList.get(i).setIndex(position);//标记位置
                textList.get(i).getTextview().setBackgroundResource(R.drawable.bule_back_kuang);//跟换背景
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
            setAnswer(true);
            setAnswerIsRight(true);
            for (int t = 0; t < getBean().getAnswer().size(); t++) {
                //判断答案是否正确
                if (!getBean().getAnswer().get(t).equals(textList.get(t).getTextview().getText().toString())) {
                    setAnswerIsRight(false);
                    getBaseTiMuActivity().setFen(getBaseTiMuActivity().getFen() - 1);
                    break;
                }
            }
            AnswerDialog danCiTiMuAnswerDialog = new AnswerDialog();
            String a = "";
            for (int i = 0; i < getBean().getAnswer().size(); i++) {
                a = a + getBean().getAnswer().get(i);
            }
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
            assert getFragmentManager() != null;
            danCiTiMuAnswerDialog.show(getFragmentManager(), "answer");
        }

    }


    @OnClick({R.id.one, R.id.two, R.id.three, R.id.four})
    public void onViewClicked(View view) {
        if (isAnswer()) return;
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

    @Override
    public int setlayoutResID() {
        return R.layout.cheng_yu_bu_cong_ti;
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
