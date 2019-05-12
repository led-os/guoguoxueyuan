package com.test720.grasshoppercollege.myViews;

import android.widget.ListView;

/**
 * Description：自定义GridView,解决嵌套时显示不全问题
 * <p>
 * Created by Mjj on 2016/11/17.
 */

public class CustomListView extends ListView {

    public CustomListView(android.content.Context context,
                          android.util.AttributeSet attrs) {
        super(context, attrs);
    }

    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);

    }

}
