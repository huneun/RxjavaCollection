package com.example.rxjavacollection.collection

import android.util.Log
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.schedulers.Schedulers
import java.util.*
import kotlin.collections.ArrayList

class ObserveOn {

    //ObserveOn의 호출 위치에 따라 결과가 어떻게 달라지는지를 안다.
    val shapes = ArrayList<MyShape>()

    fun ObserveOnTask(){
        shapes.add(MyShape("Red", "Ball"))
        shapes.add(MyShape("Green", "Ball"))
        shapes.add(MyShape("Blue", "Ball"))

        Observable.fromIterable(shapes)
            .subscribeOn(Schedulers.computation())
            .subscribeOn(Schedulers.io())
            .doOnSubscribe{data ->
                MyUtil.printData("doOnSubscribe")
            }
            .doOnNext{data ->

                MyUtil.printData("doOnNext", data)}
            .observeOn(Schedulers.newThread())
            .map { data -> data.sp = "Square" }
            .doOnNext{data ->

                MyUtil.printData("map(Square)", shapes)} //한번 발행된 값은 재사용이 안되어 다시 소비를 하려고 해도 반영이 안된다?
//            .observeOn(Schedulers.newThread())
//            .map<Any> { data -> data.shape = "Triangle" }
//            .doOnNext{data -> MyUtil.printData("map(Triangle)", data)}
            .observeOn(Schedulers.newThread())
            .subscribe{data -> MyUtil.printData("subscribe", data)}
    }



    class MyShape( var color:String,  var sp: String){

        override fun toString(): String {
            return "MyShape{ color=\' $color \', shape=\'$sp\'}"
        }

    }
    object MyUtil{

        fun printData(msg:String){
            Log.d("test-jennet", "Current Thread is :${Thread.currentThread().name} | message $msg")
        }
        fun printData(msg: String, obj :Any){
            Log.d("test-jennet", "Current Thread is :${Thread.currentThread().name} | message : $msg | Object : ${obj.toString()}")
        }
    }
}