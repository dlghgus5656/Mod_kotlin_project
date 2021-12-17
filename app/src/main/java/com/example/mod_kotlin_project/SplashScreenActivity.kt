package com.example.mod_kotlin_project

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.annotation.UiThread
import android.view.animation.Animation.AnimationListener
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_reservation.*
import kotlinx.android.synthetic.main.activity_splash.*

class SplashScreenActivity : AppCompatActivity() {
    // After 3000 mileSeconds / 3 seconds your next activity will display.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        loadSplashScreen()
    }
    private fun loadSplashScreen(){
        Handler().postDelayed({
            // You can declare your desire activity here to open after finishing splash screen. Like MainActivity
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }, 3000)
    }
}


/*
 @UiThread
    private fun splashAnimation() {
        val intent = Intent(this, MainActivity::class.java)
        val textAnim = AnimationUtils.loadAnimation(this, R.anim.anim_splash_text)
        splash_text.startAnimation(textAnim)
        val imageAnim = AnimationUtils.loadAnimation(this, R.anim.anim_splash_image)
        splash_img.startAnimation(imageAnim)

        imageAnim.setAnimationListener(object : AnimationListener {
            override fun onAnimationEnd(animation: Animation) {
                startActivity(intent)
                overridePendingTransition(R.anim.anim_splash_out_top, R.anim.anim_splash_in_down)
                finish()
            }

            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationRepeat(animation: Animation) {}
        })
    }
private fun loadSplashScreen(){
    Handler().postDelayed({
        // You can declare your desire activity here to open after finishing splash screen. Like MainActivity
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }, 3000)
}
*/


