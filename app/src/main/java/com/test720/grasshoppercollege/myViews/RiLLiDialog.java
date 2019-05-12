package com.test720.grasshoppercollege.myViews;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;

import com.test720.grasshoppercollege.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by 水东流 on 2018/5/2.
 */

public class RiLLiDialog extends DialogFragment {
    @BindView(R.id.vp_calendar)
    CalendarView vpCalendar;
    Unbinder unbinder;
    DataChange dataChange;

    public void setDataChange(DataChange dataChange) {
        this.dataChange = dataChange;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.ri_li_dialog, container, false);
        unbinder = ButterKnife.bind(this, view);
        vpCalendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                if (dataChange != null) dataChange.chang(year, month, dayOfMonth);
                dismissAllowingStateLoss();
            }
        });
        return view;
    }

    public interface DataChange {
        void chang(int year, int month, int dayOfMonth);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
