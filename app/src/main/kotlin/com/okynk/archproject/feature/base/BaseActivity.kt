package com.okynk.archproject.feature.base

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.okynk.archproject.core.di.component.ActivityComponent

class BaseActivity : AppCompatActivity(){
    lateinit var activityComponent : ActivityComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }
}