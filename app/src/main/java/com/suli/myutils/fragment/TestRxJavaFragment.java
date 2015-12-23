package com.suli.myutils.fragment;

import android.os.Bundle;
import android.os.HandlerThread;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fernandocejas.frodo.annotation.RxLogObservable;
import com.fernandocejas.frodo.annotation.RxLogSubscriber;
import com.suli.myutils.R;

import java.util.concurrent.TimeUnit;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import common.log.DebugLog;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.exceptions.OnErrorThrowable;
import rx.functions.Action1;
import rx.functions.Func0;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

import static android.os.Process.THREAD_PRIORITY_BACKGROUND;

/**
 * Created by suli on 2015/10/20.
 */
public class TestRxJavaFragment extends PlaceholderFragment {

    @Bind(R.id.tv_output)
    TextView tvOutput;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_rxjava, container, false);
        ButterKnife.bind(this, rootView);
        testRxJava();
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
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
        sampleObservable()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DefaultSubscriber<String>() {
                    @Override
                    public void onCompleted() {
                        DebugLog.d("onCompleted()");
                    }

                    @Override
                    public void onError(Throwable e) {
                        DebugLog.e("onError");
                    }

                    @Override
                    public void onNext(String string) {
                        DebugLog.d("onNext(" + string + ")");
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
