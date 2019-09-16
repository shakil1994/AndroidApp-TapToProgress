package com.example.shakil.testkotlintaptoprogress

import android.view.animation.Animation
import android.view.animation.Transformation

class CircleAnimation(private val circle: Circle, private val endangle: Float) : Animation() {
    private val angle: Float

    init {
        this.angle = circle.angle
    }


    override fun applyTransformation(f: Float, transformation: Transformation) {
        this.circle.angle = this.angle + (this.endangle - this.angle) * f
        this.circle.requestLayout()
    }
}
