package com.test720.grasshoppercollege;

import java.text.DecimalFormat;

/**
 * Created by 水东流 on 2018/7/5.
 */

public abstract class BaseTiMuActivity extends BaseToolActivity {
    float fen = 100;
    int type = 19;
    String id;
    boolean isDingZheng = false;

    public boolean isDingZheng() {
        return isDingZheng;
    }

    public void setDingZheng(boolean dingZheng) {
        isDingZheng = dingZheng;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getFen() {
        DecimalFormat decimalFormat = new DecimalFormat("##0.0");//构造方法的字符格式这里如果小数不足2位,会以0补足.
        String p = decimalFormat.format(fen);//format 返回的是字符串
        return Float.parseFloat(p);
    }

    public void setFen(float fen) {
        this.fen = fen;
        if (fen > 100) {
            this.fen = 100;
        }
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }


}
