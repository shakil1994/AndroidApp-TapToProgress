package com.example.shakil.androidtaptoprogress;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Shader;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

public class Circle extends View {

    private final Paint paint = new Paint();
    private final RectF rectF = new RectF();
    private float angle;
    private int endAngle;

    public Circle(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context);
    }

    private void init(Context context) {
        float dimensionPixelSize1 = (float) context.getResources().getDimensionPixelSize(R.dimen.curve_width);
        float dimensionPixelSize2 = ((float) context.getResources().getDimensionPixelSize(R.dimen.curve_radius)) -
                (4.0f * dimensionPixelSize1);

        Point screenSize = getScreenSize(context);
        float f1 = dimensionPixelSize1 / 2.0f;
        float f2 = (((float) (screenSize.x / 2)) - dimensionPixelSize1) - dimensionPixelSize2;
        this.paint.setAntiAlias(true);
        this.paint.setStyle(Paint.Style.STROKE);
        this.paint.setStrokeWidth(dimensionPixelSize1);
        this.endAngle = (int) (((float) (screenSize.x / 2)) - dimensionPixelSize1);
        this.rectF.set(f2, f1, (dimensionPixelSize2 * 2.0f) + f2, (dimensionPixelSize2 * 2.0f) + f1);
        this.angle = 54.5f;
    }

    public static Point getScreenSize(@NonNull Context context) {
        Display defaultDisplay = ((WindowManager)context.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        Point point = new Point();
        defaultDisplay.getSize(point);
        return point;
    }

    public void setCurveColor(int i, int i2){
        this.paint.setShader(new LinearGradient(0.0f, 0.0f, (float) this.endAngle,
                (float) this.endAngle, i, i2, Shader.TileMode.MIRROR));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawArc(this.rectF, 175.0f, this.angle, false, this.paint);
    }

    public float getAngle() {
        return this.angle;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }
}
