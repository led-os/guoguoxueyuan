package com.test720.grasshoppercollege.Adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.makeramen.roundedimageview.RoundedImageView;
import com.test720.grasshoppercollege.untils.glideUntil.GlideUntil;


/**
 * Created by LuoPan on 2017/5/17 12:55.
 */
public class BaseRecyclerHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> views;
    private Context context;

    private BaseRecyclerHolder(Context context, View itemView) {
        super(itemView);
        this.context = context;
        //指定一个初始为8
        views = new SparseArray<>(8);
    }

    /**
     * 取得一个RecyclerHolder对象
     *
     * @param context  上下文
     * @param itemView 子项
     * @return 返回一个RecyclerHolder对象
     */
    public static BaseRecyclerHolder getRecyclerHolder(Context context, View itemView) {
        return new BaseRecyclerHolder(context, itemView);
    }

    public SparseArray<View> getViews() {
        return this.views;
    }

    /**
     * 通过view的id获取对应的控件，如果没有则加入views中
     *
     * @param viewId 控件的id
     * @return 返回一个控件
     */
    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            views.put(viewId, view);
        }
        return (T) view;
    }

    /**
     * 设置字符串
     */
    public BaseRecyclerHolder setText(int viewId, String text) {
        TextView tv = getView(viewId);
        tv.setText(text);
        return this;
    }

    /**
     * 设置字符串
     */
    public BaseRecyclerHolder setFontText(int viewId, String text) {
        Typeface fontFace = Typeface.createFromAsset(context.getAssets(),
                "fonts/hkhbt.ttf");
        TextView tv = getView(viewId);
        tv.setTypeface(fontFace);
        tv.setText(text);
        return this;
    }

    /**
     * 设置背景
     */
    public BaseRecyclerHolder setBack(int viewId, String url) {
        final View backview = getView(viewId);
        GlideUntil.getInstance().imgBack(context, backview, url);
        return this;
    }

    /**
     * 设置图片
     */
    public BaseRecyclerHolder setImageResource(int viewId, int drawableId) {
        ImageView iv = getView(viewId);
        iv.setImageResource(drawableId);
        return this;
    }

    /**
     * 设置图片
     */
    public BaseRecyclerHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView iv = getView(viewId);
        iv.setImageBitmap(bitmap);
        return this;
    }

    /**
     * 设置图片
     */
    public BaseRecyclerHolder setImageByUrl(int viewId, String url) {
        ImageView imageView = getView(viewId);
        GlideUntil.getInstance().imgUrl(context, imageView, url);
        return this;
    }

    /**
     * 设置头像
     */
    public BaseRecyclerHolder setHeaderByUrl(int viewId, String url) {
        final RoundedImageView roundedImageView = getView(viewId);
        GlideUntil.getInstance().headByUrl(context, roundedImageView, url);
        return this;
    }

}
