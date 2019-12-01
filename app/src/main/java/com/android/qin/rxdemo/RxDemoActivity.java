package com.android.qin.rxdemo;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.android.qin.R;
import com.android.qin.util.ToastUtil;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.Notification;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


//https://blog.csdn.net/bunnycoffer/article/details/80047381

/**
 * 1.
 * 观察者对象：Observer,Subscriber,Consumer
 * 被观察者对象：Observable,Flowable
 * 关系：Observer订阅Observable,Subscriber订阅Flowable
 * <p>
 * 2.
 * Observable和Observer通过subscribe()方法实现订阅关系
 */
public class RxDemoActivity extends AppCompatActivity {

    private Disposable mmm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
/*
        this.findViewById(R.id.start).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        this.findViewById(R.id.stop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });*/

    }


    /**
     * Observable<String> data = demo12();
     * data.subscribe(new Consumer<String>() {
     *
     * @return
     * @Override public void accept(String s) throws Exception {
     * Log.i("TAG",s);
     * }});
     */
    public Observable<String> demo12(View view) {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("你好");
                e.onComplete();
            }
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
    }

    /**
     * 高级使用：线程调度器（subscribeOn，subscribeOn）
     * subscribeOn() 指定的是上游发送事件的线程, observeOn() 指定的是下游接收事件的线程。
     * 多次指定上游的线程只有第一次指定的有效, 也就是说多次调用subscribeOn() 只有第一次的有效, 其余的会被忽略.
     * 多次指定下游的线程是可以的, 也就是说每调用一次observeOn() , 下游的线程就会切换一次.
     * <p>
     * doOnNext:可以拦截下游，对数据进行一次处理
     */
    public void demo11(View view) {
        Observable.just("nihao").subscribeOn(Schedulers.newThread())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.i("MALEI1", Thread.currentThread().getName() + " : " + s);
                        System.out.println("发送的数据1:" + s);
                    }
                })
                .observeOn(Schedulers.io())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        Log.i("MALEI", Thread.currentThread().getName() + " : " + s);
                        System.out.println("发送的数据:" + s);
                    }
                });

    }

    /**
     * 高级使用：Disposable
     * 在RxJava中,用它来切断Observer(观察者)与Observable(被观察者)之间的连接，当调用它的dispose()方法时,
     * 它就会将Observer(观察者)与Observable(被观察者)之间的连接切断, 从而导致Observer(观察者)收不到事件。
     * <p>
     * Disposable的作用是切断连接，确切地讲是将Observer(观察者)切断，不再接收来自被观察者的事件，而被观察者的事件却仍在继续执行。
     * <p>
     * 当Observable(被观察者)发送了一个onComplete后, Observable(被观察者)中onComplete之后的事件将会继续发送,
     * 而Observer(观察者)收到onComplete事件之后将不再继续接收事件.
     */
    public void demo10(View view) {
        Disposable dis = Observable.just("你好").subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println("发送的数据:" + s);
            }
        });

    }


    /**
     * 高级使用：Scheduler
     * 在哪个线程生产事件，就在哪个线程消费事件。如果需要切换线程，就需要用到 Scheduler（调度器）
     * Schedulers.immediate() 在当前线程运行
     * Schedulers.newThread() 启用新线程
     * Schedulers.io() I/O 操作（读写文件、读写数据库、网络信息交互等)有线程池
     * AndroidSchedulers.mainThread() 主线程
     * <p>
     * subscribeOn(): 指定Observable(被观察者)所在的线程，或者叫做事件产生的线程。
     * observeOn(): 指定 Observer(观察者)所运行在的线程，或者叫做事件消费的线程。
     */
    public void demo9(View view) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                System.out.println("所在的线程：" + Thread.currentThread().getName());
                System.out.println("发送的数据:" + 1 + "");
                e.onNext(1);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println("所在的线程：" + Thread.currentThread().getName());
                        System.out.println("接收到的数据:" + "integer:" + integer);
                    }
                });
    }


    /**
     * 中级使用：map
     * map()操作符，就是把原来的Observable对象转换成另一个Observable对象，同时将传输的数据进行一些灵活的操作，
     * 方便Observer获得想要的数据形式。
     */
    public void demo8(View view) {
        Observable.just("hello").map(new Function<String, String>() {
            @Override
            public String apply(String s) throws Exception {
                return s + " world";
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });
    }

    /**
     * 基础使用：timer
     * 创建一个Observable，它在一个给定的延迟后发射一个特殊的值，即表示延迟2秒后，调用onNext()方法。
     */
    public void demo7(View view) {
        Observable.timer(2, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long s) throws Exception {
                System.out.println(s);
            }
        });
    }

    /**
     * 基础使用：range
     * 创建一个发射特定整数序列的Observable，第一个参数为起始值，第二个为发送的个数，如果为0则不发送，负数则抛异常。
     * 上述表示发射1到20的数。即调用20次nNext()方法，依次传入1-20数字。
     */
    @SuppressLint("CheckResult")
    public void demo6(View view) {
        Observable.range(1, 20).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Exception {
                System.out.println(integer);
            }
        });
    }

    /**
     * 基础使用：interal
     * 创建一个按固定时间间隔发射整数序列的Observable，可用作定时器。即按照固定2秒一次调用onNext()方法。
     */
    @SuppressLint("CheckResult")
    public void demo5(View view) {
        Observable.interval(1, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Exception {
                System.out.println("hello");
            }
        });
    }

    /**
     * 基础使用：defer
     * 当观察者订阅时，才创建Observable，并且针对每个观察者创建都是一个新的Observable。
     */
    public void demo4(View view) {
        Observable.defer(new Callable<ObservableSource<?>>() {
            @Override
            public ObservableSource<?> call() throws Exception {
                return Observable.just("hello", "world");
            }
        }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println(o);
            }
        });
    }


    /**
     * 基础使用 fromIterable
     * 使用fromIterable()，遍历集合，发送每个item。相当于多次回调onNext()方法，每次传入一个item。
     */
    public void demo3(View view) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i + "");
        }
        Observable.fromIterable(list).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });
    }

    /**
     * 基础使用 just
     * 使用just( )，将为你创建一个Observable并自动为你调用onNext( )发射数据
     * Consumer和Observer都是观察者
     */
    public void demo2(View view) {
        Observable.just("demo2").subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                System.out.println(s);
            }
        });

    }


    /**
     * 基础使用create()
     */
    public void demo1(View view) {
        Observable.create((ObservableOnSubscribe<String>) e -> e.onNext("发送消息"))
                .subscribe(new Observer<String>() {
            @Override

            public void onSubscribe(Disposable d) {
                System.out.println("观察者接收到：onSubscribe");
            }

            @Override
            public void onNext(String s) {
                System.out.println("观察者接收到：" + s);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                System.out.println("观察者接收到：onComplete");
            }
        });
    }

    public void testRx1(View view) {
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> emitter) throws Exception {

                emitter.onNext(4);
                emitter.onNext(3);
                emitter.onNext(2);
                emitter.onNext(1);
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) throws Exception {
                        System.out.println(integer);
                    }
                });
    }

    public void testRx2(View view) {
        Observable.just(view).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Exception {
                System.out.println("viewId =" + view.getId());
            }
        });

    }

    //登陆后获取用户信息
    public void testLoginUser(View view) {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                //登录
                emitter.onNext("789");
            }
        }).map(new Function<String, Object>() {
            @Override
            public Object apply(String o) throws Exception {
                //获取用户信息
                return o + "---";
            }
        }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                        o = "###" + o;
                        System.out.println(o);
                        ToastUtil.showLongToast(o.toString());

                    }
                });


    }

    //网易菜鸟窝,登录和获取信息
    public void textRxLogin2() {
        Observable.just("").flatMap(new Function<String, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(String s) throws Exception {
                return null;
            }
        });

    }

    public void hello() {
        Observable.just("hello world just").subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {

            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        }, new Action() {
            @Override
            public void run() throws Exception {

            }
        }, new Consumer<Disposable>() {
            @Override
            public void accept(Disposable disposable) throws Exception {

            }
        });

    }

    public void hello2() {
        Observable.just("ssss").subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {
            }

            @Override

            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }

    public void dotest(View view) {
        Observable.just("do do ","canzuofu","realtttt")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(s -> System.out.println("doOnNext"))
                .doAfterNext(s -> {
                    System.out.println("doAfterNext");
                }).doOnComplete(() -> {
                    System.out.println("doOnComplete");
                })
                //订阅之后的回调方法
                .doOnSubscribe(disposable -> {
                    System.out.println("doOnSubscribe");
                }).doAfterTerminate(() -> {
                    System.out.println("doAfterTerminate");
                }).doFinally(() -> {
                    System.out.println("doFinally");
                })
                //每一次都回调
                .doOnEach(stringNotification -> {
                    System.out.println("doOnEach");
                }).doOnLifecycle(disposable -> {
                    System.out.println("disposable");
                }, () -> {
                     System.out.println("run");

                }).subscribe(s -> {
                    System.out.println("消息"+s);

                        });

    }
}
