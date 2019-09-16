package com.example.shakil.androidtaptoprogress;

import android.view.animation.Animation;
import android.view.animation.Transformation;

public class CircleAnimation extends Animation {
    Circle circle;
    float angle;
    float endAngle;

    public CircleAnimation(Circle circle, float endAngle) {
        this.circle = circle;
        this.angle = circle.getAngle();
        this.endAngle = endAngle;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        this.circle.setAngle(this.angle + ((this.endAngle - this.angle) * interpolatedTime));
        this.circle.requestLayout();

    }
}
