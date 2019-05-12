package com.test720.grasshoppercollege.module.tanXian.diDi;

import com.test720.grasshoppercollege.module.tanXian.BaseTanXianAdapter;
import com.test720.grasshoppercollege.module.tanXian.TanXianGuanKaShouActivity;

public class DiDiShouActivity extends TanXianGuanKaShouActivity {


    @Override
    public int indexType() {
        return 2;
    }

    @Override
    public BaseTanXianAdapter adapter() {
        return new DiDiAdapter(getList(), mcontext);
    }

    @Override
    protected String setTitle() {
        return null;
    }
}