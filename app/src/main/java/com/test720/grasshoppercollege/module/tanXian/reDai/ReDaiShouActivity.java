package com.test720.grasshoppercollege.module.tanXian.reDai;

import com.test720.grasshoppercollege.module.tanXian.BaseTanXianAdapter;
import com.test720.grasshoppercollege.module.tanXian.TanXianGuanKaShouActivity;

public class ReDaiShouActivity extends TanXianGuanKaShouActivity {


    @Override
    public int indexType() {
        return 4;
    }

    @Override
    public BaseTanXianAdapter adapter() {
        return new ReDaiAdapter(getList(),mcontext);
    }

    @Override
    protected String setTitle() {
        return null;
    }
}