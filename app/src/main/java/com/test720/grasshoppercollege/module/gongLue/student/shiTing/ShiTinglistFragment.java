package com.test720.grasshoppercollege.module.gongLue.student.shiTing;

import android.content.Intent;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.lzy.okgo.model.HttpParams;
import com.test720.grasshoppercollege.Adapter.BaseRecyclerHolder;
import com.test720.grasshoppercollege.Adapter.MyBaseRecyclerAdapter;
import com.test720.grasshoppercollege.BaseRecyclerViewFragment;
import com.test720.grasshoppercollege.CurrencyEvent;
import com.test720.grasshoppercollege.R;
import com.test720.grasshoppercollege.module.gongLue.BaseKeMuShouYeActivity;
import com.test720.grasshoppercollege.module.gongLue.GongLueData;
import com.test720.grasshoppercollege.untils.HttpUntil;

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
 * 作者：水东流 编于 2018/9/13
 */
public class ShiTinglistFragment extends BaseRecyclerViewFragment {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    MyBaseRecyclerAdapter adapter;
    BaseKeMuShouYeActivity baseKeMuShouYeActivity;
    List<ShiTingData.DataBean> list = new ArrayList<>();

    public void setBaseKeMuShouYeActivity(BaseKeMuShouYeActivity baseKeMuShouYeActivity) {
        this.baseKeMuShouYeActivity = baseKeMuShouYeActivity;
    }

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
        return HttpUntil.GetIntent().StrategytryListeningList();
    }

    @Override
    public void onEvent(CurrencyEvent event) {
        super.onEvent(event);
        if(event.getWhat()==CurrencyEvent.NIANJICHANGE){
            ShuaXin();
        }
    }

    @Override
    public void shuaXin(String str) {
        if (codeIsOne(str, false)) {
            ShiTingData data = new Gson().fromJson(str, ShiTingData.class);
            list.clear();
            list.addAll(data.getData());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void gengDuo(String str) {
        if (codeIsOne(str, false)) {
            ShiTingData data = new Gson().fromJson(str, ShiTingData.class);
            list.addAll(data.getData());
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public HttpParams httpParams() {
        HttpParams httpParams = new HttpParams();
        assert getArguments() != null;
        httpParams.put("book_type", getArguments().getString("book_type"));
        httpParams.put("class", baseKeMuShouYeActivity.getNianJi());
        httpParams.put("type", GongLueData.getInstance().getModularType());
        httpParams.put("p", page);
        return httpParams;
    }

    @Override
    public void init() {

        adapter = new MyBaseRecyclerAdapter<ShiTingData.DataBean>(list, getActivity(), R.layout.gong_lue_online_item) {
            @Override
            public void convert(BaseRecyclerHolder holder, ShiTingData.DataBean item, int postion) {
             /*   holder.setText(R.id.title, item.getName());
                holder.setText(R.id.content, item.getAims());
                holder.setText(R.id.number, item.getSubscribe_count() + "人订阅");
                RatingBar ratingBar = holder.getView(R.id.ratingBar);
                ratingBar.setRating(Float.parseFloat(item.getLevel()));
                holder.setImageByUrl(R.id.book, item.getPic());*/
                holder.setText(R.id.title, item.getName());
                holder.setText(R.id.content, item.getDesc());
                holder.setText(R.id.price, "￥" + item.getPrice());
                holder.setText(R.id.priceBeiZhu, "");

                holder.setText(R.id.vipPrice, "￥" + item.getVip_price());
                holder.setText(R.id.number, item.getSubscribe_count() + "人报名");
                holder.setImageByUrl(R.id.book, item.getPic());
            }
        };
        adapter.setOnItemClickListener(new MyBaseRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(getActivity(), ShiTingXiangQingActivity.class);
                intent.putExtra("micro_class_id", list.get(position).getMicro_class_id());
                intent.putExtra("name", list.get(position).getName());
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

    class ShiTingData {

        /**
         * code : 1
         * msg : 成功
         * data : [{"micro_class_id":"347","pic":"http://cdn1.guoguoxueyuan.com/Uploads/strategy/micro/import/yingyukouyushijian/ggxykouyushijian1.jpg","name":"牛津少儿英语1","state":"1","price":"0.20","vip_price":"0.10","subscribe_count":"1","desc":"牛津少儿英语简介1","level":"1"},{"micro_class_id":"348","pic":"http://cdn1.guoguoxueyuan.com/Uploads/strategy/micro/import/yingyukouyushijian/ggxykouyushijian2.jpg","name":"牛津少儿英语2","state":"2","price":"0.00","vip_price":"0.00","subscribe_count":"1","desc":"牛津少儿英语简介2","level":"1"},{"micro_class_id":"349","pic":"http://cdn1.guoguoxueyuan.com/Uploads/strategy/micro/import/yingyukouyushijian/ggxykouyushijian3.jpg","name":"牛津少儿英语3","state":"1","price":"0.20","vip_price":"0.10","subscribe_count":"0","desc":"牛津少儿英语简介3","level":"1"}]
         */

        private int code;
        private String msg;
        private List<DataBean> data;

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

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public class DataBean {
            /**
             * micro_class_id : 347
             * pic : http://cdn1.guoguoxueyuan.com/Uploads/strategy/micro/import/yingyukouyushijian/ggxykouyushijian1.jpg
             * name : 牛津少儿英语1
             * state : 1
             * price : 0.20
             * vip_price : 0.10
             * subscribe_count : 1
             * desc : 牛津少儿英语简介1
             * level : 1
             */

            private String micro_class_id;
            private String pic;
            private String name;
            private String state;
            private String price;
            private String vip_price;
            private String subscribe_count;
            private String desc;
            private String level;

            public String getMicro_class_id() {
                return micro_class_id;
            }

            public void setMicro_class_id(String micro_class_id) {
                this.micro_class_id = micro_class_id;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getVip_price() {
                return vip_price;
            }

            public void setVip_price(String vip_price) {
                this.vip_price = vip_price;
            }

            public String getSubscribe_count() {
                return subscribe_count;
            }

            public void setSubscribe_count(String subscribe_count) {
                this.subscribe_count = subscribe_count;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public String getLevel() {
                return level;
            }

            public void setLevel(String level) {
                this.level = level;
            }
        }
    }
}
