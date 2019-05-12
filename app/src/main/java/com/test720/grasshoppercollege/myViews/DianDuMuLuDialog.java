package com.test720.grasshoppercollege.myViews;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.diandu.bean.BookDataBean;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by 水东流 on 2018/4/20.
 */

public class DianDuMuLuDialog extends DialogFragment {
    @BindView(R.id.type)
    TextView type;
    @BindView(R.id.body)
    TextView body;
    @BindView(R.id.listView)
    ExpandableListView listView;
    @BindView(R.id.shanchu)
    ImageView shanchu;

    BookDataBean data;
    String classStaue;
    ID getid;


    /**********当前正展示的单元页**********/
    String unit;
    String lesson;

    /**********当前正展示的单元页**********/
    public void setUnit(String unit, String lesson) {
        this.unit = unit;
        this.lesson = lesson;
    }

    public void setData(BookDataBean data, String classStaue, ID id) {
        this.data = data;
        this.getid = id;
        this.classStaue = classStaue;
    }


    public interface ID {
        void ID(String id, int pageIndex);
    }

    /**
     * 系统调用这个来获取DialogFragment的布局，不管是否显示为对话框或嵌入的片段.
     */
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        Dialog dialog = new Dialog(getActivity(), R.style.NoTitleFullscreen);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        dialog.setContentView(R.layout.dain_du_mu_lu);
        dialog.setCanceledOnTouchOutside(true); // 外部点击取消
        // 设置宽度为屏宽, 靠近屏幕底部。
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.LEFT; // 紧贴底部
        lp.width = WindowManager.LayoutParams.WRAP_CONTENT; // 宽度持平
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);
        ButterKnife.bind(this, dialog); // Dialog即View
          /*点击目录，跳转*/
        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
//                getid.ID(data.getData().getContent().get(groupPosition).getLesson().get(childPosition).getPage().get(0).getPage_id(),
//                        data.getData().getContent().get(groupPosition).getLesson().get(childPosition).getPage().get(0).getPageIndex());
                dismissAllowingStateLoss();
                return true;
            }
        });
        shanchu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismissAllowingStateLoss();
            }
        });

        return dialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.dimAmount = 0.0f;
        window.setAttributes(windowParams);
        if (data != null) {
//            listView.setAdapter(new DianDuMuLuAdapter(data, getActivity(), unit, lesson));
            if (classStaue != null) body.setText(classStaue);
            /*展开默认的选项*/
            for (int i = 0; i < data.getData().getContent().size(); i++) {
                if (unit.equals(data.getData().getContent().get(i).getUnit())) {
                    listView.expandGroup(i);
                }
            }
        }
    }
}
