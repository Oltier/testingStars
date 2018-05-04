package com.wanari.mlp.egisru.io.reactivex.android.plugins

import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers


object RxAndroidTestPlugins {

    fun resetAndroidTestPlugins() {
        RxAndroidPlugins.reset()
    }

    fun initAndroidTestPlugins() {
        RxAndroidPlugins.reset()
        RxAndroidPlugins.setInitMainThreadSchedulerHandler {
            return@setInitMainThreadSchedulerHandler Schedulers.trampoline()
        }
        RxAndroidPlugins.initMainThreadScheduler {
            return@initMainThreadScheduler Schedulers.trampoline()
        }
    }

}