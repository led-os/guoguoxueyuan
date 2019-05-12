package com.test720.grasshoppercollege.myViews;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.test720.grasshoppercollege.R;


/**
 * _oo0oo_
 * o8888888o
 * 88" . "88
 * (| -_- |)
 * 0\  =  /0
 * ___/`---'\___
 * .' \\|     |// '.
 * / \\|||  :  |||// \
 * / _||||| -卍-|||||- \
 * |   | \\\  -  /// |   |
 * | \_|  ''\---/''  |_/ |
 * \  .-\__  '-'  ___/-. /
 * ___'. .'  /--.--\  `. .'___
 * ."" '<  `.___\_<|>_/___.' >' "".
 * | | :  `- \`.;`\ _ /`;.`/ - ` : | |
 * \  \ `_.   \_ __\ /__ _/   .-` /  /
 * =====`-.____`.___ \_____/___.-`___.-'=====
 * `=---='
 * 佛祖保佑        永无BUG
 * 佛曰:
 * 程序园里程序天，程序天里程序员；
 * 程序猿人写程序，又拿程序换肉钱。
 * 肉饱继续桌前坐，饱暖还是桌前眠；
 * 半迷半醒日复日，码上码下年复年。
 * 但愿叱咤互联世，不愿搬砖码当前；
 * 诸葛周瑜算世事，我来算出得加钱。
 * 别人笑我忒直男，我笑自己太像猿；
 * 但见成都府国内，处处地地程序员。
 * Created by 水东流 on 2018/3/6 0006.
 */

public class ViewAmount extends LinearLayout implements View.OnClickListener, TextWatcher {
    private int amount = 1; //购买数量
    private int goods_storage = 10; //商品库存
    private int minNum = 0;//最小数量，默认为0
    private String tiShiYu = "库存不足";//提示语

    public void setTiShiYu(String tiShiYu) {
        this.tiShiYu = tiShiYu;
    }

    private OnAmountChangeListener mListener;

    private EditText etAmount;

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
        String am = amount + "";
        etAmount.setText(am);
    }

    public void setMinNum(int minNum) {
        this.minNum = minNum;
    }

    public ViewAmount(Context context) {
        this(context, null);
    }

    public ViewAmount(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater.from(context).inflate(R.layout.view_amount, this);
        etAmount = findViewById(R.id.etAmount);
        Button btnDecrease = findViewById(R.id.btnDecrease);
        Button btnIncrease = findViewById(R.id.btnIncrease);
        btnDecrease.setOnClickListener(this);
        btnIncrease.setOnClickListener(this);
        etAmount.addTextChangedListener(this);
        etAmount.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    //隐藏输入框
                    InputMethodManager imm = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
                }
                return true;
            }
        });
        String am = amount + "";
        etAmount.setText(am);
    }

    public void setOnAmountChangeListener(OnAmountChangeListener onAmountChangeListener) {
        this.mListener = onAmountChangeListener;
    }

    public void setGoods_storage(int goods_storage) {
        this.goods_storage = goods_storage;
        /*如果库存为o*/
        if (goods_storage == 0) {
            amount = 0;
            minNum = 0;
            etAmount.setText(String.valueOf(0));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnDecrease:
                if (amount > minNum) {
                    amount--;
                    String am = amount + "";
                    etAmount.setText(am);
                }
                break;
            case R.id.btnIncrease:
                if (goods_storage == 0) {
                    Toast.makeText(getContext(), tiShiYu, Toast.LENGTH_SHORT).show();
                }
                if (amount < goods_storage) {
                    amount++;
                    etAmount.setText(String.valueOf(amount));
                } else {
                    Toast.makeText(getContext(), tiShiYu, Toast.LENGTH_SHORT).show();
                }
                break;
        }
        etAmount.clearFocus();
        if (mListener != null) {
            mListener.onAmountChange(this, amount);
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        if (s.toString().isEmpty())
            return;
        amount = Integer.valueOf(s.toString());
        if (amount > goods_storage) {
            amount = goods_storage;
            etAmount.setText(String.valueOf(goods_storage));
            return;
        }
        if (amount < minNum) {
            amount = minNum;
            etAmount.setText(String.valueOf(minNum));
        }
        if (mListener != null) {
            mListener.onAmountChange(this, amount);
        }
    }


    public interface OnAmountChangeListener {
        void onAmountChange(View view, int amount);
    }
}
