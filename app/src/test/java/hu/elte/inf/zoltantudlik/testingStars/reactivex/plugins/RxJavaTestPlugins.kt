package com.wanari.mlp.egisru.io.reactivex.plugins

import io.reactivex.plugins.RxJavaPlugins


class RxJavaTestPlugins {

    fun resetJavaTestPlugins(){
        RxJavaPlugins.reset()
    }

    fun initJavaTestPlugins(){
        RxJavaPlugins.reset()
//        RxJavaPlugins.createComputationScheduler {
//            Thread.currentThread()
//        }
//        RxJavaPlugins.createIoScheduler {
//
//        }
//        RxJavaPlugins.createNewThreadScheduler {
//
//        }
//        RxJavaPlugins.createSingleScheduler {
//
//        }
    }
}