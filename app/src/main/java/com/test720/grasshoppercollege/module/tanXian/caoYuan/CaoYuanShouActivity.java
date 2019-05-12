package com.test720.grasshoppercollege.module.tanXian.caoYuan;

import com.test720.grasshoppercollege.module.tanXian.BaseTanXianAdapter;
import com.test720.grasshoppercollege.module.tanXian.TanXianGuanKaShouActivity;

public class CaoYuanShouActivity extends TanXianGuanKaShouActivity {

    @Override
    public int indexType() {
        return 5;
    }

    @Override
    public BaseTanXianAdapter adapter() {
        return new CaoyuanAdapter(getList(), mcontext);
    }


    @Override
    protected String setTitle() {
        return null;
    }



}