package com.giftm.google1.rxtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    private CompositeDisposable disposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        Data data = new Data();

        LogThread.Log("메인 스레등");
//
//        disposable.add(
//                data.getArrayFromCallable()
//                        .subscribeOn(Schedulers.computation())
//                        .doOnSubscribe(disposable1 -> LogThread.Log("doOnSubScribe"))
//                        .flatMap(Observable::fromIterable)
//                        .sorted(((o1, o2) -> o2.compareTo(o1)))
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(val -> LogThread.Log(val + "")));

//        disposable.add(Observable.just(0)
//                .map(integer -> {
//                    throw new RuntimeException();
//                })
//                .subscribe(o -> {}
//                , throwable -> Log.e("throw", throwable.getMessage())));

//        disposable.add(Observable.just(0)
//                .map(integer -> {
//                    throw new RuntimeException();
//                })
//                .onErrorReturn(throwable -> 10)
//                .subscribe(o -> Log.d("성공", o+"")
//                        , throwable -> Log.e("throw", throwable.getMessage())));

//        disposable.add(Observable.just(0)
//                .map(integer -> {
//                    throw new RuntimeException();
//                })
//                .onErrorResumeNext(throwable -> {
//                    return Observable.just(11);
//                })
//                .map(o->Integer.parseInt(String.valueOf(0)))
//                .map(o->o+11)
//                .subscribe(o -> Log.d("성공", o+"")
//                        , throwable -> Log.e("throw", throwable.getMessage())));

        disposable.add(Observable.just("100" , "$100" , " 10000")
                    .map(Integer::parseInt)
                    .onErrorResumeNext(observer -> {
                        return Observable.just(-1);
                    })
                    .subscribe(testdata->{
                        if(testdata < 0){
                            LogThread.Log("테스트 : " +  testdata+"");
                        }
                    }, throwable -> LogThread.Log("오류값 : " + throwable.getMessage())));


//        disposable.add(
//                data.getUser()
//                        .doOnSubscribe(disposable1 -> LogThread.Log("doOnSubScribe"))
//                        .subscribeOn(Schedulers.computation())
//                        .flatMap(Observable::fromIterable)
//                        .map(user -> Integer.parseInt(user.getType()))
//                        .sorted((o1, o2) -> {
//                            LogThread.Log("sortThread");
//                            return o2.compareTo(o1);
//                        })
//                        .last(1)
//                        .observeOn(AndroidSchedulers.mainThread())
//                        .subscribe(val -> LogThread.Log(val + "")));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (!disposable.isDisposed()) disposable.dispose();
    }
}

class LogThread {

    public static void Log(String msg) {
        String currentThread = Thread.currentThread().getName();
        Log.d("로그 : ", "현재 thread : [ " + currentThread + " ]" + " 로그 메세지 : " + msg);
    }
}
