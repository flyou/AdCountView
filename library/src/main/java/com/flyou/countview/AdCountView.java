package com.flyou.countview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.support.annotation.ColorInt;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Toast;

/**
 * Created by fzl on 2017/2/17.
 * VersionCode: 1
 * Desc: AdCountView
 */

public class AdCountView extends View implements CountAnimationUtil.OnInterpolatedTimeChanged {
    /**
     * the text paint of info
     */
    private Paint mTextPaint;
    /**
     * the background Paint
     */
    private Paint mCircleBackgroundPaint;
    /**
     * out Ring Paint
     */
    private Paint mOutRingBackgroundPaint;
    /**
     * sweep angeleattrs.xml
     */
    private float mSweepAngle;
    /**
     * ad show Text
     */
    private String mText;
    /**
     * text color
     */
    private int mTextColor;
    /**
     * text size
     */
    private int mTextSize;
    /**
     * background color
     */
    private int mbackgroundColor;
    /**
     * Out Ring color
     */
    private int mOutRingColor;
    /**
     * out Ring StrokeWidth
     */

    private float mOutStrokeWidth;
    /**
     * the reduis
     */
    private int r;

    private RectF mCircleRectf;
    /**
     * textBounds
     */
    private Rect textBounds;
    /**
     * the duration of anim
     */
    private long adTimeMillis;

    /**
     * adCountView anim
     */
    private CountAnimationUtil animation;


    private OnStatusChangeListener onStatusChangeListener;

    public AdCountView(Context context) {
        this(context, null);
    }

    public AdCountView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AdCountView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        final TypedArray a =
                context.obtainStyledAttributes(attrs, R.styleable.AdCountView, defStyleAttr, 0);
        init(a);
    }

    private void init(TypedArray typedArray) {
        mSweepAngle = 0;
        mText = "click skip";
        mOutStrokeWidth = 3;

        if (!TextUtils.isEmpty(typedArray.getString(R.styleable.AdCountView_text))) {
            mText = typedArray.getString(R.styleable.AdCountView_text);
        }
        mTextSize = typedArray.getInt(R.styleable.AdCountView_textSize, 10);
        mbackgroundColor = typedArray.getColor(R.styleable.AdCountView_backgroundColor, 0x80000000);
        mOutRingColor = typedArray.getColor(R.styleable.AdCountView_outRingColor, 0xFF00FF00);
        mTextColor = typedArray.getColor(R.styleable.AdCountView_textColor, 0x4D000000);
        adTimeMillis = typedArray.getInt(R.styleable.AdCountView_duration, 5000);
        typedArray.recycle();

        mTextSize = DensityUtil.dip2px(getContext(), mTextSize);
        mTextPaint = new Paint();
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.LINEAR_TEXT_FLAG);
        mTextPaint.setColor(mTextColor);
        mTextPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mTextPaint.setTextAlign(Paint.Align.LEFT);
        mTextPaint.setTextSize(mTextSize);
        textBounds = new Rect();
        mTextPaint.getTextBounds(mText, 0, mText.length(), textBounds);


        mCircleBackgroundPaint = new Paint();
        mCircleBackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mCircleBackgroundPaint.setColor(mbackgroundColor);
        mCircleBackgroundPaint.setStyle(Paint.Style.FILL);

        mOutStrokeWidth = DensityUtil.dip2px(getContext(), mOutStrokeWidth);
        mOutRingBackgroundPaint = new Paint();
        mOutRingBackgroundPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mOutRingBackgroundPaint.setColor(mOutRingColor);
        mOutRingBackgroundPaint.setStyle(Paint.Style.STROKE);
        mOutRingBackgroundPaint.setStrokeWidth(mOutStrokeWidth);

        animation = new CountAnimationUtil();
        animation.setDuration(adTimeMillis);
        animation.setInterpolatedTimeChangeListener(this);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(r, r, r, mCircleBackgroundPaint);
        mCircleRectf = new RectF(mOutStrokeWidth / 2, mOutStrokeWidth / 2, 2 * r - mOutStrokeWidth / 2, 2 * r - mOutStrokeWidth / 2);
        canvas.drawArc(mCircleRectf, -90, mSweepAngle, false, mOutRingBackgroundPaint);

        canvas.drawText(mText, r - (mTextPaint.measureText(mText) / 2), r + textBounds.height() / 2, mTextPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = getDefaultSize(getSuggestedMinimumHeight(),
                heightMeasureSpec);
        int width = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        r = Math.min(width, height) / 2;
        setMeasuredDimension(2 * r, 2 * r);

    }


    @Override
    public void onInterpolatedTimeChanged(float interpolatedTime) {
        if (interpolatedTime < 1.0f) {
            mSweepAngle = 360 * interpolatedTime;
            postInvalidate();
        } else {
            animation.cancel();
            mSweepAngle = 360;
            if (onStatusChangeListener != null) {
                onStatusChangeListener.onCountViewStop();
            }
        }
    }

    /**
     * start play
     */
    public void start() {
        this.startAnimation(animation);
        if (onStatusChangeListener != null) {
            onStatusChangeListener.onCountViewStart();
        }
    }

    /**
     * set text color
     */
    public void setTextClor(@ColorInt int textColor) {
        mTextPaint.setColor(textColor);
    }

    /**
     * set textsize
     */
    public void setTextSize(int textSize) {
        mTextPaint.setTextSize(DensityUtil.dip2px(getContext(), textSize));
    }

    /**
     * set circle background color
     */
    public void setbackgroundColor(@ColorInt int backGroundColor) {
        mCircleBackgroundPaint.setColor(backGroundColor);
    }

    /**
     * set out ring color
     */
    public void setOutRingColor(@ColorInt int outRingColor) {
        this.mOutRingColor=outRingColor;
        mOutRingBackgroundPaint.setColor(outRingColor);
    }

    /**
     * set animation time
     */
    public void setTime(long adTimeMillis) {
        this.adTimeMillis=adTimeMillis;
        animation.setDuration(adTimeMillis);
    }

    /**
     * set text
     */
    public void setText(String text) {
        this.mText = text;
    }



    /**
     * anim start or stop
     */
    public void setOnStatusChangeListener(OnStatusChangeListener listener) {
        this.onStatusChangeListener = listener;
    }


    public interface OnStatusChangeListener {
        void onCountViewStart();

        void onCountViewStop();
    }
}
