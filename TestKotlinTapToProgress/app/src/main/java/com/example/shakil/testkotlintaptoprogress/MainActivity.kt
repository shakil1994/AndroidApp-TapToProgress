package com.example.shakil.testkotlintaptoprogress

import android.os.Bundle
import android.util.Log
import android.view.animation.Animation
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity(), Animation.AnimationListener {

    lateinit var curve: Circle
    lateinit var taplayout: LinearLayout
    internal var cancel = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        curve = findViewById(R.id.curve)
        taplayout = findViewById(R.id.layouttap)
        initanimation()
    }

    private fun initanimation() {
        val circleAnimation = CircleAnimation(curve, 135.0f)
        this.curve.setCurveColor(
            ContextCompat.getColor(this, R.color.tap_light),
            ContextCompat.getColor(this, R.color.tap_dark)
        )
        circleAnimation.duration = 3000
        circleAnimation.setAnimationListener(this)
        this.taplayout.setOnTouchListener { view, motionEvent ->
            when (motionEvent.action) {
                0 -> {
                    curve.startAnimation(circleAnimation)
                    cancel = false
                    true
                }
                //case 1:
                1 -> {
                    circleAnimation.cancel()
                    cancel = true
                    true
                }
                else -> false
            }
        }
    }

    override fun onAnimationStart(animation: Animation) {
        Log.d("anim", "Animation started")

    }

    override fun onAnimationEnd(animation: Animation) {
        if (cancel) {
            Log.d("anim", "Incomplete")

        } else {

            Log.d("anim", "Complete")

        }

    }

    override fun onAnimationRepeat(animation: Animation) {

    }
}
