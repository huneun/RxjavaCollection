package com.example.rxjavacollection

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rxjavacollection.collection.Flowable
import com.example.rxjavacollection.collection.ObserveOn

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //목표 : 배압의 개념을 이해하고 적절하게 배압 전략을 구성할수 있다.
//        Flowable().observableTask()
//        Flowable().flowableTaskLatest()
//        Flowable().flowableTaskDrop()
//        Flowable().flowableTaskBuffer()

        ObserveOn().ObserveOnTask()


    }
}