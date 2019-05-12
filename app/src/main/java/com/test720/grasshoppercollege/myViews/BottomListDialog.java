package com.test720.grasshoppercollege.myViews;

import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseOkDialogFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 佛祖保佑        永无BUG
 * 佛曰:
 * 程序园里程序天，程序天里程序员；
 * 程序猿人写程序，又拿程序换肉钱。
 * 肉饱继续桌前坐，饱暖还是桌前眠；
 * 半迷半醒日复日，码上码下年复年。
 * 但愿叱咤互联世，不愿搬砖码当前；
 * 诸葛周瑜算世事，我算需求得加钱。
 * 别人笑我忒直男，我说自己是程猿；
 * 但见成都府国内，处处地地程序员。
 * 作者：水东流 编于 2018/8/24
 */
public abstract class BottomListDialog<T> extends BaseOkDialogFragment {

    MyBaseRecyclerAdapter<T> adapter;
    List<T> list = new ArrayList<>();


    public MyBaseRecyclerAdapter<T> getAdapter() {
        return adapter;
    }

    public void setAdapter(MyBaseRecyclerAdapter<T> adapter) {
        this.adapter = adapter;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public List<T> getList() {
        return list;
    }


}
