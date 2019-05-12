package com.test720.grasshoppercollege.myViews;


import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.test720.grasshoppercollege.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Creaed by 水东流 on 2018/4/11.
 */

public class EditeDialog extends DialogFragment {
    @BindView(R.id.name)
    EditText name;
    @BindView(R.id.ok)
    TextView ok;
    Unbinder unbinder;
    OKClick okClick;
    String nickName;

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public interface OKClick {
        void sendToIp(String s);
    }

    public void setOkClick(OKClick okClick) {
        this.okClick = okClick;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.edit_view_pop, container, false);
        unbinder = ButterKnife.bind(this, view);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  /*关闭软键盘*/
                View view = getDialog().getWindow().peekDecorView();
                if (view != null) {
                    InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
                okClick.sendToIp(name.getText().toString());
                dismissAllowingStateLoss();
            }
        });

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (nickName != null) {
            name.setText(nickName);
        }
    }

}
