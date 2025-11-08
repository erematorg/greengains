package com.google.maps.android.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.google.maps.android.R;

public class IconGenerator {
    public static final int STYLE_BLUE = 4;
    public static final int STYLE_DEFAULT = 1;
    public static final int STYLE_GREEN = 5;
    public static final int STYLE_ORANGE = 7;
    public static final int STYLE_PURPLE = 6;
    public static final int STYLE_RED = 3;
    public static final int STYLE_WHITE = 2;
    private float mAnchorU = 0.5f;
    private float mAnchorV = 1.0f;
    private BubbleDrawable mBackground;
    private ViewGroup mContainer;
    private View mContentView;
    private final Context mContext;
    private int mRotation;
    private RotationLayout mRotationLayout;
    private TextView mTextView;

    public IconGenerator(Context context) {
        this.mContext = context;
        this.mBackground = new BubbleDrawable(context);
        ViewGroup viewGroup = (ViewGroup) LayoutInflater.from(context).inflate(R.layout.amu_text_bubble, (ViewGroup) null);
        this.mContainer = viewGroup;
        RotationLayout rotationLayout = (RotationLayout) viewGroup.getChildAt(0);
        this.mRotationLayout = rotationLayout;
        TextView textView = (TextView) rotationLayout.findViewById(R.id.amu_text);
        this.mTextView = textView;
        this.mContentView = textView;
        setStyle(1);
    }

    private static int getStyleColor(int i3) {
        if (i3 == 3) {
            return -3407872;
        }
        if (i3 == 4) {
            return -16737844;
        }
        if (i3 == 5) {
            return -10053376;
        }
        if (i3 != 6) {
            return i3 != 7 ? -1 : -30720;
        }
        return -6736948;
    }

    private static int getTextStyle(int i3) {
        return (i3 == 3 || i3 == 4 || i3 == 5 || i3 == 6 || i3 == 7) ? R.style.amu_Bubble_TextAppearance_Light : R.style.amu_Bubble_TextAppearance_Dark;
    }

    private float rotateAnchor(float f2, float f3) {
        int i3 = this.mRotation;
        if (i3 == 0) {
            return f2;
        }
        if (i3 == 1) {
            return 1.0f - f3;
        }
        if (i3 == 2) {
            return 1.0f - f2;
        }
        if (i3 == 3) {
            return f3;
        }
        throw new IllegalStateException();
    }

    public float getAnchorU() {
        return rotateAnchor(this.mAnchorU, this.mAnchorV);
    }

    public float getAnchorV() {
        return rotateAnchor(this.mAnchorV, this.mAnchorU);
    }

    public Bitmap makeIcon(CharSequence charSequence) {
        TextView textView = this.mTextView;
        if (textView != null) {
            textView.setText(charSequence);
        }
        return makeIcon();
    }

    public void setBackground(Drawable drawable) {
        this.mContainer.setBackgroundDrawable(drawable);
        if (drawable != null) {
            Rect rect = new Rect();
            drawable.getPadding(rect);
            this.mContainer.setPadding(rect.left, rect.top, rect.right, rect.bottom);
            return;
        }
        this.mContainer.setPadding(0, 0, 0, 0);
    }

    public void setColor(int i3) {
        this.mBackground.setColor(i3);
        setBackground(this.mBackground);
    }

    public void setContentPadding(int i3, int i4, int i5, int i6) {
        this.mContentView.setPadding(i3, i4, i5, i6);
    }

    public void setContentRotation(int i3) {
        this.mRotationLayout.setViewRotation(i3);
    }

    public void setContentView(View view) {
        this.mRotationLayout.removeAllViews();
        this.mRotationLayout.addView(view);
        this.mContentView = view;
        View findViewById = this.mRotationLayout.findViewById(R.id.amu_text);
        this.mTextView = findViewById instanceof TextView ? (TextView) findViewById : null;
    }

    public void setRotation(int i3) {
        this.mRotation = ((i3 + 360) % 360) / 90;
    }

    public void setStyle(int i3) {
        setColor(getStyleColor(i3));
        setTextAppearance(this.mContext, getTextStyle(i3));
    }

    public void setTextAppearance(Context context, int i3) {
        TextView textView = this.mTextView;
        if (textView != null) {
            textView.setTextAppearance(context, i3);
        }
    }

    public void setTextAppearance(int i3) {
        setTextAppearance(this.mContext, i3);
    }

    public Bitmap makeIcon() {
        int makeMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, 0);
        this.mContainer.measure(makeMeasureSpec, makeMeasureSpec);
        int measuredWidth = this.mContainer.getMeasuredWidth();
        int measuredHeight = this.mContainer.getMeasuredHeight();
        this.mContainer.layout(0, 0, measuredWidth, measuredHeight);
        int i3 = this.mRotation;
        if (i3 == 1 || i3 == 3) {
            measuredHeight = this.mContainer.getMeasuredWidth();
            measuredWidth = this.mContainer.getMeasuredHeight();
        }
        Bitmap createBitmap = Bitmap.createBitmap(measuredWidth, measuredHeight, Bitmap.Config.ARGB_8888);
        createBitmap.eraseColor(0);
        Canvas canvas = new Canvas(createBitmap);
        int i4 = this.mRotation;
        if (i4 == 1) {
            canvas.translate((float) measuredWidth, 0.0f);
            canvas.rotate(90.0f);
        } else if (i4 == 2) {
            canvas.rotate(180.0f, (float) (measuredWidth / 2), (float) (measuredHeight / 2));
        } else if (i4 == 3) {
            canvas.translate(0.0f, (float) measuredHeight);
            canvas.rotate(270.0f);
        }
        this.mContainer.draw(canvas);
        return createBitmap;
    }
}
