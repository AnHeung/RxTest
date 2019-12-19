package com.giftm.google1.rxtest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

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

        APIInterface apiInterface = APIClient.getClient().create(APIInterface.class);

//        Data data = new Data();

//        String[] str1 = new String[]{"4", "2", "1", "4"};
//        String[] str2 = new String[]{"가", "하", "바", "나"};
//
//        List<String> array1 = new ArrayList<>();
//        array1.add("1");
//        array1.add("4");
//        array1.add("3");
//        array1.add("2");
//
//        List<String> array2 = new ArrayList<>();
//        array2.add("가");
//        array2.add("하");
//        array2.add("바");
//        array2.add("나");
//
////        Arrays.sort(str1);
////        Arrays.sort(str2);
//
//        Collections.sort(array1, (o1, o2) -> o1.compareTo(o2));
//        Collections.sort(array2, (o1, o2) -> o1.compareTo(o2));
//
//        LogThread.Log(array1.toString());
//        LogThread.Log(array2.toString());
//
//
//        LogThread.Log("메인 스레등");
//
//        Observable observable1 = Observable
//                .just(5, 3, 4, 2, 1)
//                .sorted()
//                .delay(2, TimeUnit.SECONDS);
//        Observable observable2 = Observable
//                .just(7, 8, 9, 11, 32)
//                .sorted()
//                .delay(2, TimeUnit.SECONDS);
//
//
//        disposable.add(Observable.zip(observable1, observable2, (o, o2) -> {
//
//            LogThread.Log("Observable1 : " + String.valueOf(o) + "    Observable2 : " + String.valueOf(o2));
//            return o;
//        })
//                .subscribe(System.out::println));

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

//        flatMapTest();
//        concatMapTest();



//        disposable.add(Observable.fromIterable(Arrays.asList(1, 2, 3, 4, 5))
//                .subscribeOn(Schedulers.io())
//                .flatMap(item -> {
//                    LogThread.Log("나온 아이템 : " + item);
//                    return apiInterface.getData();
//                })
//                .map(item -> {
//                    LogThread.Log(item.getTest());
//                    return item.getTest() + item;
//                })
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(successItem ->
//                                LogThread.Log(successItem)
//
//                        , throwable -> {
//                            LogThread.Log("망");
//                        }));

//        disposable.add(Observable.just("100", "100", "$10000")
//                .map(item->{
//                    LogThread.Log(item);
//                    return Integer.parseInt(item);
//                })
//                .onErrorResumeNext(item->{
//                    return Observable.just(-1);
//                })
//                .subscribe(testdata -> {
//                    if (testdata < 0) {
//                        LogThread.Log("테스트 : " + testdata + "");
//                    }else{
//                        LogThread.Log("테스트 : 100");
//                    }
//
//                }, throwable -> {
//                    LogThread.Log("오류값 : " + throwable.getMessage());
//                }));


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

    private void flatMapTest() {
        final List<String> items = Arrays.asList("a", "b", "c", "d", "e", "f");
        LogThread.Log("start - " + System.currentTimeMillis());
        disposable.add(Observable.fromIterable(items).flatMap(s -> {
            final int delay = 1;
            return Observable.just(s + "x")
                    .delay(delay, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                    .doOnNext(resp -> LogThread.Log("doOnNext"  + String.valueOf(AndroidSchedulers.mainThread().now(TimeUnit.SECONDS))));
        })
                .toList()
                .doOnSuccess(ret -> LogThread.Log("end of stream : " + AndroidSchedulers.mainThread().now(TimeUnit.SECONDS)))
                .subscribe(resp -> LogThread.Log(String.valueOf(resp)), thr -> LogThread.Log(thr.getMessage())));
        LogThread.Log("end - " + System.currentTimeMillis());
    }


    private void concatMapTest() {
        final List<String> items = Arrays.asList("a", "b", "c", "d", "e", "f");
        LogThread.Log("start - " + System.currentTimeMillis());
        disposable.add(Observable.fromIterable(items).concatMap(s -> {
            final int delay = 1;
            return Observable.just(s + "x")
                    .delay(delay, TimeUnit.SECONDS, AndroidSchedulers.mainThread())
                    .doOnNext(resp -> LogThread.Log(String.valueOf(AndroidSchedulers.mainThread().now(TimeUnit.SECONDS))));
        })
                .toList()
                .doOnSuccess(ret -> LogThread.Log("end of stream : " + AndroidSchedulers.mainThread().now(TimeUnit.SECONDS)))
                .subscribe(resp -> LogThread.Log(String.valueOf(resp)), thr -> LogThread.Log(thr.getMessage())));
        LogThread.Log("end - " + System.currentTimeMillis());
    }

    private void doOnEvent(){

        Integer[] divider={10,5,0};

        Observable.fromArray(divider)
                .map(div->1000/div)
                .doOnNext(data->LogThread.Log(("onNext()  : " + data)))
                .doOnComplete(()->LogThread.Log("onComplete()"))
                .doOnError(e->LogThread.Log("onError :" + e.getMessage()))
                .subscribe(data->LogThread.Log("subscribe : " + data) ,throwable -> LogThread.Log("Throwable : "+ throwable.getMessage()));
    }
}


class LogThread {

    public static void Log(String msg) {
        String currentThread = Thread.currentThread().getName();
        Log.d("로그 : ", "현재 thread : [ " + currentThread + " ]" + " thread Id : [ " + Thread.currentThread().getId() + " ]" + " 로그 메세지 : " + msg);
    }
}
