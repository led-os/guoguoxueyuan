package com.test720.grasshoppercollege.module.tanXian.haiDi;

import com.test720.grasshoppercollege.module.tanXian.BaseTanXianAdapter;
import com.test720.grasshoppercollege.module.tanXian.TanXianGuanKaShouActivity;

public class HaiDiShouActivity extends TanXianGuanKaShouActivity {


    @Override
    public int indexType() {
        return 8;
    }

    @Override
    public BaseTanXianAdapter adapter() {
        return new HaiDiAdapter(getList(),mcontext);
    }

    @Override
    protected String setTitle() {
        return null;
    }
}