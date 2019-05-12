package com.test720.grasshoppercollege.module.tanXian.bingChuan;

import com.test720.grasshoppercollege.module.tanXian.BaseTanXianAdapter;
import com.test720.grasshoppercollege.module.tanXian.TanXianGuanKaShouActivity;

public class BingChuanShouActivity extends TanXianGuanKaShouActivity {


    @Override
    public int indexType() {
        return 6;
    }

    @Override
    public BaseTanXianAdapter adapter() {
        return new BinChuanAdapter(getList(),mcontext);
    }

    @Override
    protected String setTitle() {
        return null;
    }
}