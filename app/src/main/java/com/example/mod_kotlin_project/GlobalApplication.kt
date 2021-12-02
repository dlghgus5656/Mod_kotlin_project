package com.example.mod_kotlin_project

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this, "26260caf5ab277297c52486e44878aa0")
    }
}