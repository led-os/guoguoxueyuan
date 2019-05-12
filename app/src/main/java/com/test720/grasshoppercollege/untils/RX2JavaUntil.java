package com.test720.grasshoppercollege.untils;

import org.reactivestreams.Subscription;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;

/**
 * 作者：水东流 编于 2019/3/16
 */
public class RX2JavaUntil {
    //创建观察者
    FlowableSubscriber<Integer> subscriber = new FlowableSubscriber<Integer>() {
        @Override
        public void onSubscribe(Subscription s) {
            //订阅时候的操作
            s.request(Long.MAX_VALUE);//请求多少事件，这里表示不限制
        }

        @Override
        public void onNext(Integer i) {
            //将异步回调转为同步，拿到接口请求结果后才重新执行下一任务
            doWork();
        }

        @Override
        public void onError(Throwable t) {
            //onError事件处理
        }

        @Override
        public void onComplete() {
            //onComplete事件处理
        }
    };

    //被观察者
    Flowable<Integer> flowable = Flowable.create(e -> {
        //订阅观察者时的操作
        e.onNext(0);
        e.onComplete();
    }, BackpressureStrategy.BUFFER);


    /**
     * 业务逻辑
     *
     * @return
     */
    public  RX2JavaUntil doWork() {


        return this;
    }

    /**
     * 开始
     *
     * @return
     */
    public void start() {
        new Thread(() -> flowable.subscribe(subscriber)).start();
    }

}
