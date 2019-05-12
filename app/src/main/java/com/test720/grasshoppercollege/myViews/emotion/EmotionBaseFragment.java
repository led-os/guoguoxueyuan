package com.test720.grasshoppercollege.myViews.emotion;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.test720.grasshoppercollege.BaseFragment;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.bean.Emotion;
import com.test720.grasshoppercollege.untils.ExpressionUtil;
import com.test720.grasshoppercollege.untils.XmlUtil;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.Unbinder;

import static android.content.Context.INPUT_METHOD_SERVICE;

/**
 * Created by 水东流 on 2018/6/12.
 */

public abstract class EmotionBaseFragment extends BaseFragment implements AdapterView.OnItemClickListener {
    @BindView(R.id.gv_emotions)
    GridView gvEmotions;
    Unbinder unbinder;
    EditText body;
    /*表情相关*/
    private InputMethodManager inputManager = null;
    private List<Emotion> emotions = null;

  abstract int start();
  abstract int end();


    public void setBody(EditText body) {
        this.body = body;
    }

    @Override
    public void initData() {
        inputManager = (InputMethodManager) getActivity().getSystemService(INPUT_METHOD_SERVICE);
        initGrildView();
    }

    /*初始化表情弹框*/
    private void initGrildView() {
        try {
            InputStream inputStream = this.getResources().getAssets()
                    .open("emotions.xml");
            emotions = XmlUtil.getEmotions(inputStream);

            ArrayList<HashMap<String, Object>> items = new ArrayList<HashMap<String, Object>>();
            for (int i = start(); i < end(); i++) {
                Emotion emotion = emotions.get(i);
                if (emotion != null) {
                    HashMap<String, Object> map = new HashMap<String, Object>();
                    Field f = (Field) R.drawable.class.getDeclaredField(emotion
                            .getName());
                    int j = f.getInt(R.drawable.class);
                    map.put("itemImage", j);
                    items.add(map);
                }
            }
            items.remove(items.size() - 1);
            HashMap<String, Object> map = new HashMap<String, Object>();
            map.put("itemImage", R.drawable.img_delete);
            items.add(map);
            SimpleAdapter saImageItems = new SimpleAdapter(getActivity(), items,
                    R.layout.emotion_item, new String[]{"itemImage"},
                    new int[]{R.id.iv_emotion});
            gvEmotions.setSelector(new ColorDrawable(Color.TRANSPARENT));
            gvEmotions.setAdapter(saImageItems);
        } catch (Exception e) {
            e.printStackTrace();
        }
        togglekeyboard(false);
        gvEmotions.setOnItemClickListener(this);
    }

    /*表情*/
    private void togglekeyboard(boolean show) {
        if (show) {
            inputManager.showSoftInput(body, 0);
            body.setTag(1);
        } else {
            inputManager.hideSoftInputFromWindow(body.getWindowToken(), 0);
            body.setTag(null);
        }
    }

    @Override
    public void setListener() {

    }

    @Override
    public int setlayoutResID() {
        return R.layout.emotion_fragment;
    }

    @Override
    public void getSuccess(String s, int what) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == end()-start() - 1) {
            int cursorPosition = body.getSelectionStart();
            if (cursorPosition > 0) {
                String str = body.getText().subSequence(0, cursorPosition).toString();
                int lastIndex = str.lastIndexOf("f");
                if (lastIndex >= 0 && lastIndex < cursorPosition) {
                    str = str.substring(lastIndex, cursorPosition);
                    boolean match = ExpressionUtil.matchEmotion(str);
                    if (match) {
                        body.getEditableText().delete(lastIndex, cursorPosition);
                    } else {
                        body.getEditableText().delete(cursorPosition - 1, cursorPosition);
                    }
                } else {
                    body.getEditableText().delete(cursorPosition - 1, cursorPosition);
                }
            }
        } else {
            Emotion emotion = emotions.get(position+start());
            int cursor = body.getSelectionStart();
            Field f;
            try {
                f = (Field) R.drawable.class.getDeclaredField(emotion.getName());
                int j = f.getInt(R.drawable.class);
                Drawable d = getResources().getDrawable(j);
                int textSize = (int) body.getTextSize();
                d.setBounds(0, 0, textSize, textSize);

                String str = null;
                int pos = position + 1+start();
                if (pos < 10) {
                    str = "f00" + pos;
                } else if (pos < 100) {
                    str = "f0" + pos;
                } else {
                    str = "f" + pos;
                }
                SpannableString ss = new SpannableString(str);
                ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BOTTOM);
                ss.setSpan(span, 0, str.length(),
                        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                body.getText().insert(cursor, ss);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
