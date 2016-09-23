package common.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import common.log.DebugLog;
import common.utils.DisplayUtil;


/**
 * 绘制电池的View
 */
public class BatteryView extends View {

    private Paint mPaint;

    private float mPercent;
    private float mTailWidth = 3;
    private int gap = 2;
    private int outWidth = 1;

    private int mPaddingLeft = 1;
    private int mPaddingBottom = 1;

    public BatteryView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public BatteryView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BatteryView(Context context) {
        super(context);

        init();
    }

    private void init() {
        if (isInEditMode()) {
            return;
        }

        mTailWidth = DisplayUtil.dip2px(3);
        outWidth = DisplayUtil.dip2px(1);
        gap = DisplayUtil.dip2px(1);

        mPaint = new Paint();
        mPaint.setStrokeWidth(outWidth);
    }

    public void setPercent(float percent) {
        mPercent = percent;
//        postInvalidate();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        DebugLog.d("onMeasure");
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        DebugLog.d("onLayout");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (isInEditMode()) {
            return;
        }
        DebugLog.d("onDraw");
        drawBattery(canvas);
    }

    private void drawBattery(Canvas canvas) {
        int width = getWidth();
        int height = getHeight();

        if (mPercent * 100 <= 20) {
            mPaint.setColor(Color.RED);
        } else {
            mPaint.setColor(Color.WHITE);
        }

        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(mPaddingLeft, 0, width - mTailWidth, height - mPaddingBottom, mPaint);

        mPaint.setStyle(Paint.Style.FILL);
        canvas.drawRect(width - mTailWidth, (height - mPaddingBottom) / 4.0f, width, (height - mPaddingBottom) / 4.0f * 3, mPaint);

        canvas.drawRect(gap + outWidth + mPaddingLeft, gap + outWidth, gap + outWidth + (width - mTailWidth - 2 * gap - 2 * outWidth) * mPercent, height - gap - outWidth - mPaddingBottom, mPaint);
    }

}
