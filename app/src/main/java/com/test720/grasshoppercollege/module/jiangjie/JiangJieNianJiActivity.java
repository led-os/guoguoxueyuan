package com.test720.grasshoppercollege.module.jiangjie;

import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.PublickNianJiActivity;

public class JiangJieNianJiActivity extends PublickNianJiActivity {



    @Override
    protected String setTitle() {
        return "课本讲解";
    }

    @Override
    public MyBaseRecyclerAdapter.OnItemClickListener setItemClick() {
        return null;
    }
}
