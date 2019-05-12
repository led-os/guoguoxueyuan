package com.test720.grasshoppercollege.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.bean.BookListItemData;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

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
 * Created by 水东流 on 2018/1/16 0016.
 */

public class BookItemAdapter extends BaseAdapter {
    List<BookListItemData> list;
    Context context;
    ImgClick imgClick;

    public void setImgClick(ImgClick imgClick) {
        this.imgClick = imgClick;
    }

    public BookItemAdapter(List<BookListItemData> list, Context context) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.add_book_grideview_item, null);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Glide.with(context).load(list.get(position).getPic()).into(viewHolder.bookImage);//背景
        viewHolder.bookName.setText(list.get(position).getClassX() + "-" + list.get(position).getStatus());//名称
        if (list.get(position).getIs_add().equals("1")) {
            viewHolder.addImg.setImageResource(R.mipmap.ytj11);
        } else {
            viewHolder.addImg.setImageResource(R.mipmap.tjkb11);
            viewHolder.addImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (imgClick != null) {

                        imgClick.click(list.get(position).getBook_id());//添加课本事件
                        imgClick.click(list.get(position));
                        viewHolder.addImg.setImageResource(R.mipmap.ytj11);
                    }
                }
            });

        }
        return convertView;
    }

    /*点击添加事件*/
    public interface ImgClick {
        void click(String bookId);

        void click(BookListItemData bean);
    }

    static class ViewHolder {
        @BindView(R.id.book_image)
        ImageView bookImage;
        @BindView(R.id.book_name)
        TextView bookName;
        @BindView(R.id.add_img)
        ImageView addImg;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }


}