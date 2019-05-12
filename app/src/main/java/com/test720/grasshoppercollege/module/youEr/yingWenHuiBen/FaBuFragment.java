package com.test720.grasshoppercollege.module.youEr.yingWenHuiBen;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.BaseFragment;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.qianDao.QianDaoDOuDialog;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.musicMedia.MusicUntil;

import org.greenrobot.eventbus.EventBus;

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
 * 作者：水东流 编于 2018/11/15
 */
public class FaBuFragment extends BaseFragment {
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.send)
    Button send;

    QianDaoDOuDialog qianDaoDOuDialog;
    int status = 1;

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMyId() {
        return myId;
    }

    public void setMyId(String myId) {
        this.myId = myId;
    }

    @Override
    public void initData() {
        if (status == 1) {
            send.setBackgroundResource(R.mipmap.tianjiaicon);
            send.setText("完成");
        }
    }

    @Override
    public void setListener() {

    }

    @Override
    public int setlayoutResID() {
        return R.layout.fragment_hui_ben_fa_bu;
    }

    @Override
    public void getSuccess(String s, int what) {
        if (codeIsOne(s)) {
            Data data = new Gson().fromJson(s, Data.class);
            if (qianDaoDOuDialog == null) {
                qianDaoDOuDialog = new QianDaoDOuDialog();
                qianDaoDOuDialog.setOkListener(new QianDaoDOuDialog.OkListener() {
                    @Override
                    public void ok() {
                        getActivity().finish();
                    }
                });
                qianDaoDOuDialog.setPin(data.getData().getPoints());
                qianDaoDOuDialog.show(getChildFragmentManager(), "point");
                EventBus.getDefault().post(new CurrencyEvent("发布成功", CurrencyEvent.FABUCHENGGONG));
            } else {
                qianDaoDOuDialog.show(getChildFragmentManager(), "point");
            }
        }
    }


    @Override
    public void onEvent(CurrencyEvent currencyEvent) {
        switch (currencyEvent.getWhat()) {
            case CurrencyEvent.HUIBENMYID:
                myId = (String) currencyEvent.getMsg();
                break;
        }
    }

    String myId, bookId;

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    MusicUntil musicUntil = new MusicUntil();

    @Override
    public void onDestroy() {
        super.onDestroy();
        musicUntil.canlce();
    }

    @OnClick({R.id.send, R.id.back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.send:
                faBu();
                break;
            case R.id.back:
                getActivity().finish();
                break;
        }
    }

    private synchronized void faBu() {
        if (status == 1) {
            getActivity().finish();
            return;
        }
        if (myId != null && bookId != null) {
            musicUntil.playRaw(getContext(), R.raw.huibenfabu);
            HttpParams httpParams = new HttpParams();
            httpParams.put("uid", MyApplication.getmInstance().getUid());
            httpParams.put("my_id", myId);
            httpParams.put("book_id", bookId);
            post(HttpUntil.GetIntent().getIP() + "small.php/ChildBook/fabu", httpParams, 1);
        }
    }

    public class Data {

        /**
         * code : 1
         * msg : 发布成功
         * data : {"points":"2"}
         */

        private int code;
        private String msg;
        private DataBean data;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public DataBean getData() {
            return data;
        }

        public void setData(DataBean data) {
            this.data = data;
        }

        public class DataBean {
            /**
             * points : 2
             */

            private String points;

            public String getPoints() {
                return points;
            }

            public void setPoints(String points) {
                this.points = points;
            }
        }
    }
}
