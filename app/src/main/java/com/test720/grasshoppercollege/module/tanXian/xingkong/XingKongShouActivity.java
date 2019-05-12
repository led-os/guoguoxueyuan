package com.test720.grasshoppercollege.module.tanXian.xingkong;

import com.test720.grasshoppercollege.module.tanXian.BaseTanXianAdapter;
import com.test720.grasshoppercollege.module.tanXian.TanXianGuanKaShouActivity;

public class XingKongShouActivity extends TanXianGuanKaShouActivity {


    @Override
    public int indexType() {
        return 1;
    }

    @Override
    public BaseTanXianAdapter adapter() {
        return new XingKongAdapter(getList(), mcontext);
    }

    @Override
    protected String setTitle() {
        return null;
    }
}
