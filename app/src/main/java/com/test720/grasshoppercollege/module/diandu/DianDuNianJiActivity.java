package com.test720.grasshoppercollege.module.diandu;

import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.PublickNianJiActivity;

public class DianDuNianJiActivity extends PublickNianJiActivity {


    @Override
    protected String setTitle() {
        return "课本点读";
    }

    @Override
    public MyBaseRecyclerAdapter.OnItemClickListener setItemClick() {
        MyBaseRecyclerAdapter.OnItemClickListener listener = new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                jumpToActivity(DianDuAddBookActivity.class, false);
            }
        };
        return listener;
    }
}
