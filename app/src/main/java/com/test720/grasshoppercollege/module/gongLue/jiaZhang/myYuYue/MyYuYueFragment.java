package com.test720.grasshoppercollege.module.gongLue.jiaZhang.myYuYue;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseRecyclerViewFragment;
import com.test720.grasshoppercollege.MyApplication;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.gongLue.jiaZhang.bean.YuYuelistData;
import com.test720.grasshoppercollege.module.gongLue.teacher.pinTaiZiXun.yuYue.YuYueDingDanXiangQingActivity;
import com.test720.grasshoppercollege.myViews.GetPhoneDialog;
import com.test720.grasshoppercollege.myViews.PhoneCalDialog;
import com.test720.grasshoppercollege.untils.AppUploatUtils;
import com.test720.grasshoppercollege.untils.HttpUntil;
import com.test720.grasshoppercollege.untils.callUntil.CallUntil;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.PermissionListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

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
 * 作者：水东流 编于 2018/9/20
 */
public class MyYuYueFragment extends BaseRecyclerViewFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;
    List<YuYuelistData.DataBean> list = new ArrayList<>();
    MyBaseRecyclerAdapter<YuYuelistData.DataBean> adapter;
    CallUntil callUntil;//拨号类型

    @Override
    public int setCount() {
        return 1;
    }

    @Override
    public RecyclerView initRecyclerView() {
        return recyclerView;
    }

    @Override
    public SwipeRefreshLayout initSwipeRefreshLayout() {
        return swipeRefresh;
    }

    @Override
    public String url() {
        return HttpUntil.GetIntent().StrategyyuYueOrder();
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            YuYuelistData yuYuelistData = new Gson().fromJson(str, YuYuelistData.class);
            list.clear();
            list.addAll(yuYuelistData.getData());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void getSuccess(String str, int what) {
        super.getSuccess(str, what);
        if (what == 5 && codeIsOne(str)) {
            try {
                JSONObject jsonObject = new JSONObject(str);
                String status = jsonObject.getJSONObject("data").getString("status");
                final String phone = jsonObject.getJSONObject("data").getString("phone");
                PhoneCalDialog phoneCalDialog = new PhoneCalDialog();
                phoneCalDialog.setPhone(phone);
                phoneCalDialog.setCallListener(new PhoneCalDialog.CallListener() {
                    @Override
                    public void call() {
                        AppUploatUtils.callPhoone(phone, mContext);
                    }
                });
                phoneCalDialog.show(getChildFragmentManager(), "phone");
                //融云拨号
              /*  if (status.equals("1") && callUntil != null) {
                    callUntil.startCall();
                }*/
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            YuYuelistData yuYuelistData = new Gson().fromJson(str, YuYuelistData.class);
            list.addAll(yuYuelistData.getData());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public HttpParams httpParams() {
        HttpParams httpParams = new HttpParams();
        httpParams.put("p", page);
        httpParams.put("uid", MyApplication.getmInstance().getUid());
        assert getArguments() != null;
        httpParams.put("status", getArguments().getInt("status"));
        return httpParams;
    }

    @Override
    public void init() {
        adapter = new MyBaseRecyclerAdapter<YuYuelistData.DataBean>(list, mContext, R.layout.yu_yue_ding_dan_list_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, final YuYuelistData.DataBean item, final int postion) {
                holder.setText(R.id.name, item.getNickname());
                holder.setText(R.id.title, item.getService_name());
                String s = "咨询时间" + item.getTimes() + "(共" + item.getTime_length() + "分钟)";
                holder.setText(R.id.time, s);
                holder.setHeaderByUrl(R.id.pic, item.getHeader());
                assert getArguments() != null;
                switch (getArguments().getInt("status")) {
                    case 0:
                        holder.setText(R.id.pinLun, "立即拨号");
                        holder.getView(R.id.pinLun).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                MyApplication.getmInstance().rong_token = item.getRong_token();
                                MyApplication.getmInstance().RongListener();
                                callUntil = new CallUntil(mContext, list.get(postion).getRong_tid(), item.getWay());
                                callStatus(item.getYuyue_id());
                            }
                        });
                        break;
                    case 1:
                        holder.setText(R.id.pinLun, "去评论");
                        holder.getView(R.id.pinLun).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(getActivity(), PinJiaActivity.class);
                                intent.putExtra("yuyue_id", list.get(postion).getYuyue_id());
                                jumpToActivity(intent, false);
                            }
                        });
                        break;
                    case 2:
                        holder.getView(R.id.pinLun).setVisibility(View.GONE);
                        break;
                }
            }
        };
        adapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(mContext, YuYueDingDanXiangQingActivity.class);
                intent.putExtra("status", "uid");
                if (getArguments().getInt("status") == 0) {
                    intent.putExtra("bohao", true);
                } else {
                    intent.putExtra("bohao", false);
                }
                intent.putExtra("yuyue_id", list.get(position).getYuyue_id());
                jumpToActivity(intent, false);
            }
        });
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setListener() {

    }

    @Override
    public int setlayoutResID() {
        return R.layout.base_shua_xin;
    }

    /**
     * 获取拨号状态
     *
     * @param id 预约id
     */
    public void callStatus(final String id) {
        AndPermission.with(mContext.getApplicationContext()).requestCode(100).permission(
                Manifest.permission.READ_PHONE_STATE).callback(new PermissionListener() {
            @Override
            public void onSucceed(int requestCode, @NonNull List<String> grantPermissions) {
                GetPhoneDialog getPhoneDialog = new GetPhoneDialog();
                getPhoneDialog.setListener(new GetPhoneDialog.Listener() {
                    @Override
                    public void phone(String str) {
                        HttpParams httpParams = new HttpParams();
                        httpParams.put("yuyue_id", id);
                        httpParams.put("phone", str);
                        post(HttpUntil.GetIntent().getIP() + "small.php/Strategy/callStart", httpParams, 5, true);
                    }
                });
                getPhoneDialog.show(getChildFragmentManager(), "");

            }

            @Override
            public void onFailed(int requestCode, @NonNull List<String> deniedPermissions) {

            }
        }).start();
    }
}
