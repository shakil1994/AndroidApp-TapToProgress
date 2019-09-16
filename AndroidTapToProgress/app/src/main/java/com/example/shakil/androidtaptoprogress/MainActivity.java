package com.example.shakil.androidtaptoprogress;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity implements Animation.AnimationListener {

    Circle circle_curve;
    LinearLayout tap_layout;
    boolean cancel = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        circle_curve = findViewById(R.id.circle_curve);
        tap_layout = findViewById(R.id.tap_layout);

        initAnimation();
    }

    private void initAnimation() {
        final CircleAnimation circleAnimation = new CircleAnimation(circle_curve, 135.0f);
        this.circle_curve.setCurveColor(ContextCompat.getColor(this, R.color.tap_light),
                ContextCompat.getColor(this, R.color.tap_dark));

        circleAnimation.setDuration(3000);
        circleAnimation.setAnimationListener(this);
        this.tap_layout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()){
                    case 0:
                        circle_curve.startAnimation(circleAnimation);
                        cancel = false;
                        return true;

                    case 1:
                        circleAnimation.cancel();
                        cancel = true;
                        return true;

                        default:
                            return false;
                }

            }
        });
    }

    @Override
    public void onAnimationStart(Animation animation) {
        Log.d("Anim", "Animation Start");
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        if (cancel){
            Log.d("Anim", "Incomplete");
        }
        else {
            Log.d("Anim", "Complete");
        }
    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
