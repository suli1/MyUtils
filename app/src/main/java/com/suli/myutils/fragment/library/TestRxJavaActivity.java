package com.suli.myutils.fragment.library;

import android.app.Activity;
import android.os.Bundle;
import android.os.HandlerThread;
import android.widget.TextView;

import com.fernandocejas.frodo.annotation.RxLogSubscriber;
import com.suli.myutils.R;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import common.log.DebugLog;
import rx.Observable;
import rx.Subscriber;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;

import static android.os.Process.THREAD_PRIORITY_BACKGROUND;

/**
 * Created by suli on 2015/10/20.
 */
public class TestRxJavaActivity extends Activity {

    @Bind(R.id.tv_output)
    TextView tvOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.library_rxjava);
        ButterKnife.bind(this);
        testRxJava();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }

    private void testRxJava() {
        String[] urls = {"http://baidu.com", "http://www.zime.cc", "http://google.com.hk", "url3", "http://www.sina.com.cn"};
        Observable.from(urls)
                .filter(new Func1<String, Boolean>() {
                    @Override
                    public Boolean call(String s) {
                        return s != null && s.contains(".com");
                    }
                })
                .take(2)
                .subscribe(new Action1<String>() {
                    @Override
                    public void call(String s) {
                        tvOutput.append(s + "\n");
                    }
                });

        Observable.just("Hello world!")
                .map(new Func1<String, Integer>() {
                    @Override
                    public Integer call(String s) {
                        return s.hashCode();
                    }
                })
                .subscribe(new Action1<Integer>() {

                    @Override
                    public void call(Integer integer) {
                        tvOutput.append(integer + "\n");
                    }
                });

    }


    @OnClick(R.id.btn_rxlog)
    public void onRunSchedulerExampleButtonClicked() {
//        sampleObservable()
//                .subscribeOn(Schedulers.newThread())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new DefaultSubscriber<String>() {
//                    @Override
//                    public void onCompleted() {
//                        DebugLog.d("onCompleted()");
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                        DebugLog.e("onError");
//                    }
//
//                    @Override
//                    public void onNext(String string) {
//                        DebugLog.d("onNext(" + string + ")");
//                    }
//                });

        start()
                .repeatWhen(new Func1<Observable<? extends Void>, Observable<?>>() {
                    @Override
                    public Observable<?> call(Observable<? extends Void> observable) {
                        DebugLog.d("repeatWhen");
                        return observable.delay(2, TimeUnit.SECONDS);
                    }
                })
                .retryWhen(new Func1<Observable<? extends Throwable>, Observable<?>>() {
                    @Override
                    public Observable<?> call(Observable<? extends Throwable> observable) {
                        DebugLog.d("retryWhen");
                        return observable.delay(2, TimeUnit.SECONDS);
                    }
                })
                .subscribe(new DefaultSubscriber<Boolean>() {
                    @Override
                    public void onError(Throwable e) {
                        super.onError(e);
                        DebugLog.d("onError");
                        count++;
                    }

                    @Override
                    public void onCompleted() {
                        super.onCompleted();
                        DebugLog.d("onCompleted");
                    }

                    @Override
                    public void onNext(Boolean aBoolean) {
                        super.onNext(aBoolean);
                        DebugLog.d("onNext:" + aBoolean);
                        count++;
                    }
                });


    }

    private int count = 1;

    private Observable<Boolean> start() {
        return Observable.create(new Observable.OnSubscribe<Boolean>() {
            @Override
            public void call(Subscriber<? super Boolean> subscriber) {
                DebugLog.d("subscriber->" + count);
                if (count % 5 == 0) {
                    subscriber.onError(new Throwable("test error"));
                } else {
                    subscriber.onNext(true);
                    subscriber.onCompleted();
                }
            }
        });
    }


    //    @RxLogObservable
    static Observable<String> sampleObservable() {
        return Observable.defer(new Func0<Observable<String>>() {
            @Override
            public Observable<String> call() {
                try {
                    // Do some long running operation
                    Thread.sleep(TimeUnit.SECONDS.toMillis(5));
                } catch (InterruptedException e) {
                    throw OnErrorThrowable.from(e);
                }
                return Observable.just("one", "two", "three", "four", "five");
            }
        });
    }


    @RxLogSubscriber
    static class DefaultSubscriber<T> extends Subscriber<T> {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(T t) {

        }
    }


    static class BackgroundThread extends HandlerThread {
        BackgroundThread() {
            super("SchedulerSample-BackgroundThread", THREAD_PRIORITY_BACKGROUND);
        }
    }
}
