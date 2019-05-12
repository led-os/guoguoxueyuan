package com.test720.grasshoppercollege.module.diandu.downDianDu;

import android.view.Gravity;
import android.view.WindowManager;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.test720.grasshoppercollege.Adapter.DianDuMuLuAdapter;
import com.test720.grasshoppercollege.BaseOkDialogFragment;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.diandu.bean.BookDataBean;

import butterknife.BindView;
import butterknife.OnClick;

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
 * 作者：水东流 编于 2018/10/11
 */
public class CatalogDialog extends BaseOkDialogFragment {

    BookDataBean bookDataBean;

    /**********当前正展示的单元页**********/
    String unit = "";
    String lesson = "";

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public void setLesson(String lesson) {
        this.lesson = lesson;
    }

    public void setBookDataBean(BookDataBean bookDataBean) {
        this.bookDataBean = bookDataBean;
    }

    @Override
    public DialogInit dialogInit() {
        return new DialogInit() {
            @Override
            public boolean outTouch() {
                return true;
            }

            @Override
            public int gravity() {
                return Gravity.BOTTOM;
            }

            @Override
            public int widthLayout() {
                return WindowManager.LayoutParams.MATCH_PARENT;
            }

            @Override
            public int heightLayout() {
                return WindowManager.LayoutParams.MATCH_PARENT;
            }
        };
    }

    @Override
    public int layoutId() {
        return R.layout.dain_du_mu_lu;
    }

    @Override
    public void start() {
        if (bookDataBean != null) {
            listView.setAdapter(new DianDuMuLuAdapter(bookDataBean, getActivity(), unit, lesson));
            String b =
                    bookDataBean.getData().getBook().getBook_version() + "-"
                            + bookDataBean.getData().getBook().getClassX()
                            + "-" + bookDataBean.getData().getBook().getStatus();
            body.setText(b);
            /*展开默认的选项*/
            for (int i = 0; i < bookDataBean.getData().getContent().size(); i++) {
                if (unit.equals(bookDataBean.getData().getContent().get(i).getUnit())) {
                    listView.expandGroup(i);
                }
            }
        }
        /*点击目录，跳转*/
        listView.setOnChildClickListener((parent, v, groupPosition, childPosition, id) -> {
            unit = bookDataBean.getData().getContent().get(groupPosition).getUnit();
            lesson = bookDataBean.getData().getContent().get(groupPosition).getLesson().get(childPosition).getLesson();
            int index = 0;
            //计算前面单元页数
            for (int i = 0; i < groupPosition; i++) {
                BookDataBean.DataBean.ContentBeanX bean = bookDataBean.getData().getContent().get(i);
                for (int j = 0; j < bean.getLesson().size(); j++) {
                    index = index + bean.getLesson().get(j).getPage().size();
                }
            }
            //计算本单元，前面章节页数

            for (int c = 0; c < childPosition; c++) {
                BookDataBean.DataBean.ContentBeanX.LessonBean lessonBean
                        = bookDataBean.getData().getContent().get(groupPosition).getLesson().get(c);
                index = index + lessonBean.getPage().size();
            }
            if (listViewClick != null) listViewClick.itemClick(index);
            dismissAllowingStateLoss();
            return true;
        });
    }

    @Override
    public void getSuccess(String s, int what) {

    }

    ListViewClick listViewClick;

    public void setListViewClick(ListViewClick listViewClick) {
        this.listViewClick = listViewClick;
    }

    public interface ListViewClick {
        void itemClick(int pageIndex);
    }


    @OnClick(R.id.shanchu)
    public void onViewClicked() {
        dismissAllowingStateLoss();
    }

    @BindView(R.id.shanchu)
    ImageView shanchu;
    @BindView(R.id.type)
    TextView type;
    @BindView(R.id.body)
    TextView body;
    @BindView(R.id.listView)
    ExpandableListView listView;
}
