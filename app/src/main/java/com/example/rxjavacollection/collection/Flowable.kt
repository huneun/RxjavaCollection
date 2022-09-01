package com.example.rxjavacollection.collection

import android.util.Log
import io.reactivex.rxjava3.core.BackpressureStrategy
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*

class Flowable {
    //배압의 개념 이해하기
    //생산자와 소비자의 처리속도 차이로 OutOfMemory가 발생하는 것

    fun observableTask(){
        Observable.range(1, 1000)
            .doOnNext{integer -> Log.d("test-jennet", "Emit Data : $integer")}
            .observeOn(Schedulers.io())
            .subscribe{integer ->
                Log.d("test-jennet", "Consume Data : $integer");
                Thread.sleep(100)
            }
//        Thread.sleep(100*10000)

    }

    fun flowableTaskLatest(){
        Flowable.range(1, 1000)
            .onBackpressureLatest() //구독자가 데이터를 받을 준비가 될때까지 최신 데이터만 유지하고 나머지는 버림
            .doOnNext{integer ->
                Log.d("test-jennet", "Emit Data : $integer")
            }.observeOn(Schedulers.io())
            .subscribe{ integer ->
                Log.d("test-jennet", "Consume Data : $integer")
                Thread.sleep(100)
            }
    }

    fun flowableTaskBuffer(){
        Flowable.range(1, 1000)
            .onBackpressureBuffer() //데이터를 소비할 때까지 데이터를 버퍼에 넣어 둠. 무한한 크기의 큐이지만 OutOfMemory 발생 할 수 있음.
            .doOnNext{integer ->
                Log.d("test-jennet", "Emit Data : $integer")
            }.observeOn(Schedulers.io())
            .subscribe{ integer ->
                Log.d("test-jennet", "Consume Data : $integer")
                Thread.sleep(100)
            }
    }

    fun flowableTaskDrop(){
        Flowable.range(1, 1000)
            .onBackpressureDrop() //소비 속도가 발행 속도를 따라가지 못하는 경우 발행된 데이터를 모두 버림.
            .doOnNext{integer ->
                Log.d("test-jennet", "Emit Data : $integer")
            }.observeOn(Schedulers.io())
            .subscribe{ integer ->
                Log.d("test-jennet", "Consume Data : $integer")
                Thread.sleep(100)
            }
    }
}