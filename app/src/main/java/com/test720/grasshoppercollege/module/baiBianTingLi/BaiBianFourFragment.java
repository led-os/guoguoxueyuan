package com.test720.grasshoppercollege.module.baiBianTingLi;

import android.annotation.SuppressLint;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.HttpBean.BaiBianTingLiTiMuData;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.myViews.TiMuAnswerDialog;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by 水东流 on 2018/6/26.
 */

public class BaiBianFourFragment extends BaseBaiBianTiMuFragment {
    TextView ok;//底部提交答案
    MyBaseRecyclerAdapter<BaiBianTingLiTiMuData.DataBean.ListBean> adapter;
    Map<Integer, String> map = new HashMap<>();
    TiMuAnswerDialog tiMuAnswerDialog;

    @Override
    public SeekBar getSeekbar() {
        return seekBar;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (getDataBean() == null) return;
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        @SuppressLint("InflateParams") View foot = LayoutInflater.from(mContext).inflate(R.layout.bai_bian_foot_ok, null);
        ok = foot.findViewById(R.id.ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myAnswer();
            }
        });

        //初始化答案
        for (int i = 0; i < getDataBean().getList().size(); i++) {
            map.put(i, "");
        }
        adapter = new MyBaseRecyclerAdapter<BaiBianTingLiTiMuData.DataBean.ListBean>(getDataBean().getList(), mContext, R.layout.bai_bian_four_litle_fragment) {
            @Override
            public void convert(final BaseRecyclerHolder holder, final BaiBianTingLiTiMuData.DataBean.ListBean item, final int postion) {
                final TextView textViewA = holder.getView(R.id.text_a);
                final TextView textViewB = holder.getView(R.id.text_b);
                final TextView textViewC = holder.getView(R.id.text_c);
                final TextView textViewD = holder.getView(R.id.text_d);

                String a, b, c = "", d = "";
                a = "A :" + item.getSelect_a();
                b = "B :" + item.getSelect_b();
                if (!item.getSelect_c().equals("")) c = "C :" + item.getSelect_c();
                if (!item.getSelect_d().equals("")) d = "D :" + item.getSelect_d();
                holder.setText(R.id.program, item.getQuestion());
                holder.setText(R.id.text_a, a);
                holder.setText(R.id.text_b, b);
                holder.setText(R.id.text_c, c);
                holder.setText(R.id.text_d, d);

                holder.getView(R.id.text_a).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isAnswer) return;
                        textViewA.setTextColor(getResources().getColor(R.color.green));
                        textViewB.setTextColor(getResources().getColor(R.color.gwe));
                        textViewC.setTextColor(getResources().getColor(R.color.gwe));
                        textViewD.setTextColor(getResources().getColor(R.color.gwe));
                        map.put(postion, "A");

                    }
                });
                holder.getView(R.id.text_b).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isAnswer) return;
                        textViewA.setTextColor(getResources().getColor(R.color.gwe));
                        textViewB.setTextColor(getResources().getColor(R.color.green));
                        textViewC.setTextColor(getResources().getColor(R.color.gwe));
                        textViewD.setTextColor(getResources().getColor(R.color.gwe));
                        map.put(postion, "B");
                    }
                });
                holder.getView(R.id.text_c).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isAnswer) return;
                        textViewA.setTextColor(getResources().getColor(R.color.gwe));
                        textViewB.setTextColor(getResources().getColor(R.color.gwe));
                        textViewC.setTextColor(getResources().getColor(R.color.green));
                        textViewD.setTextColor(getResources().getColor(R.color.gwe));
                        map.put(postion, "C");
                    }
                });
                holder.getView(R.id.text_d).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (isAnswer) return;
                        textViewA.setTextColor(getResources().getColor(R.color.gwe));
                        textViewB.setTextColor(getResources().getColor(R.color.gwe));
                        textViewC.setTextColor(getResources().getColor(R.color.gwe));
                        textViewD.setTextColor(getResources().getColor(R.color.green));
                        map.put(postion, "D");
                    }
                });


            }
        };
        adapter.setmFoot(foot);
        recyclerView.setAdapter(adapter);
    }


    @Override
    public int setlayoutResID() {
        return R.layout.bai_bian_four_ti_xing;
    }


    @OnClick(R.id.play)
    public void onViewClicked() {
        if (getMediaPlayer() != null) {
            if (getMediaPlayer().isPlaying()) {
                play.setImageResource(R.mipmap.bofang1);
                getMediaPlayer().pause();
            } else {
                play.setImageResource(R.mipmap.zant);
                getMediaPlayer().start();
            }
        } else {
            if(startPlay()){
                play.setImageResource(R.mipmap.zant);
            }
        }
    }

    /**
     * 统计答案
     */
    public void myAnswer() {
        if (isAnswer) return;
        if (tiMuAnswerDialog != null) {
            tiMuAnswerDialog.show(getChildFragmentManager(), "");
            return;
        }

        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            if (!entry.getValue().equals(getDataBean().getList().get(entry.getKey()).getAnswer().get(0))) {
                getBaseTiMuActivity().setFen(getBaseTiMuActivity().getFen() - 1);
                setAnswerIsRight(false);
            }
        }

        String dec = "";
        String answer = "你的答案：\n";
        for (int i = 0; i < map.size(); i++) {
            if (i < map.size() - 1)
                answer = answer + (i + 1) + ":" + map.get(i) + "  ";
            else
                answer = answer + (i + 1) + ":" + map.get(i) + "。\n";
        }

        answer = answer + "正确答案：\n";
        for (int j = 0; j < getDataBean().getList().size(); j++) {
            BaiBianTingLiTiMuData.DataBean.ListBean listBean = getDataBean().getList().get(j);
            dec = dec + listBean.getDesc();
            answer = answer + (j + 1) + ":" + listBean.getAnswer().get(0) + "  ";
        }

        tiMuAnswerDialog = new TiMuAnswerDialog();
        tiMuAnswerDialog.setAnswerTxt(answer);
        tiMuAnswerDialog.setRight(answerIsRight);
        tiMuAnswerDialog.setJieXi(dec);
        tiMuAnswerDialog.setNextTiMu(new TiMuAnswerDialog.NextTiMu() {
            @Override
            public void next() {
                if (getViewPager().getCurrentItem() < getViewPager().getAdapter().getCount() - 1) {
                    getViewPager().setCurrentItem(getViewPager().getCurrentItem() + 1);
                    stop();
                } else {
                    AddGuoGuoDou();
                }
            }
        });
        tiMuAnswerDialog.show(getChildFragmentManager(), "answer");
        isAnswer = true;
    }

    @BindView(R.id.play)
    ImageView play;
    @BindView(R.id.seekBar)
    SeekBar seekBar;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

}
