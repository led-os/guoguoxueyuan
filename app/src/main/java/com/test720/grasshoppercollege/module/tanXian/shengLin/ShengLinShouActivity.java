package com.test720.grasshoppercollege.module.tanXian.shengLin;

import com.test720.grasshoppercollege.module.tanXian.BaseTanXianAdapter;
import com.test720.grasshoppercollege.module.tanXian.TanXianGuanKaShouActivity;

public class ShengLinShouActivity extends TanXianGuanKaShouActivity {


    @Override
    public int indexType() {
        return 7;
    }

    @Override
    public BaseTanXianAdapter adapter() {
        return new ShengLinAdapter(getList(),mcontext);
    }

    @Override
    protected String setTitle() {
        return null;
    }
}