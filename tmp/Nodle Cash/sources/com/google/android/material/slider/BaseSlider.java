package com.google.android.material.slider;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.media.session.a;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityManager;
import android.widget.SeekBar;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.Dimension;
import androidx.annotation.DrawableRes;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.camera.camera2.internal.C0118y;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.math.MathUtils;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.customview.widget.ExploreByTouchHelper;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.drawable.DrawableUtils;
import com.google.android.material.internal.DescendantOffsetUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewOverlayImpl;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.motion.MotionUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.slider.BaseOnChangeListener;
import com.google.android.material.slider.BaseOnSliderTouchListener;
import com.google.android.material.slider.BaseSlider;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import com.google.android.material.tooltip.TooltipDrawable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

abstract class BaseSlider<S extends BaseSlider<S, L, T>, L extends BaseOnChangeListener<S>, T extends BaseOnSliderTouchListener<S>> extends View {
    private static final int DEFAULT_LABEL_ANIMATION_ENTER_DURATION = 83;
    private static final int DEFAULT_LABEL_ANIMATION_EXIT_DURATION = 117;
    static final int DEF_STYLE_RES = R.style.Widget_MaterialComponents_Slider;
    private static final String EXCEPTION_ILLEGAL_DISCRETE_VALUE = "Value(%s) must be equal to valueFrom(%s) plus a multiple of stepSize(%s) when using stepSize(%s)";
    private static final String EXCEPTION_ILLEGAL_MIN_SEPARATION = "minSeparation(%s) must be greater or equal to 0";
    private static final String EXCEPTION_ILLEGAL_MIN_SEPARATION_STEP_SIZE = "minSeparation(%s) must be greater or equal and a multiple of stepSize(%s) when using stepSize(%s)";
    private static final String EXCEPTION_ILLEGAL_MIN_SEPARATION_STEP_SIZE_UNIT = "minSeparation(%s) cannot be set as a dimension when using stepSize(%s)";
    private static final String EXCEPTION_ILLEGAL_STEP_SIZE = "The stepSize(%s) must be 0, or a factor of the valueFrom(%s)-valueTo(%s) range";
    private static final String EXCEPTION_ILLEGAL_VALUE = "Slider value(%s) must be greater or equal to valueFrom(%s), and lower or equal to valueTo(%s)";
    private static final String EXCEPTION_ILLEGAL_VALUE_FROM = "valueFrom(%s) must be smaller than valueTo(%s)";
    private static final String EXCEPTION_ILLEGAL_VALUE_TO = "valueTo(%s) must be greater than valueFrom(%s)";
    private static final int HALO_ALPHA = 63;
    private static final int LABEL_ANIMATION_ENTER_DURATION_ATTR = R.attr.motionDurationMedium4;
    private static final int LABEL_ANIMATION_ENTER_EASING_ATTR = R.attr.motionEasingEmphasizedInterpolator;
    private static final int LABEL_ANIMATION_EXIT_DURATION_ATTR = R.attr.motionDurationShort3;
    private static final int LABEL_ANIMATION_EXIT_EASING_ATTR = R.attr.motionEasingEmphasizedAccelerateInterpolator;
    @Dimension(unit = 0)
    private static final int MIN_TOUCH_TARGET_DP = 48;
    private static final String TAG = "BaseSlider";
    private static final double THRESHOLD = 1.0E-4d;
    private static final float THUMB_WIDTH_PRESSED_RATIO = 0.5f;
    private static final int TIMEOUT_SEND_ACCESSIBILITY_EVENT = 200;
    static final int UNIT_PX = 0;
    static final int UNIT_VALUE = 1;
    private static final String WARNING_FLOATING_POINT_ERROR = "Floating point value used for %s(%s). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the value correctly.";
    private BaseSlider<S, L, T>.AccessibilityEventSender accessibilityEventSender;
    /* access modifiers changed from: private */
    @NonNull
    public final AccessibilityHelper accessibilityHelper;
    private final AccessibilityManager accessibilityManager;
    private int activeThumbIdx;
    @NonNull
    private final Paint activeTicksPaint;
    @NonNull
    private final Paint activeTrackPaint;
    @NonNull
    private final List<L> changeListeners;
    @NonNull
    private final RectF cornerRect;
    @Nullable
    private Drawable customThumbDrawable;
    @NonNull
    private List<Drawable> customThumbDrawablesForValues;
    @NonNull
    private final MaterialShapeDrawable defaultThumbDrawable;
    private int defaultThumbRadius;
    private int defaultThumbTrackGapSize;
    private int defaultThumbWidth;
    private int defaultTickActiveRadius;
    private int defaultTickInactiveRadius;
    private int defaultTrackHeight;
    private boolean dirtyConfig;
    private int focusedThumbIdx;
    private boolean forceDrawCompatHalo;
    private LabelFormatter formatter;
    @NonNull
    private ColorStateList haloColor;
    @NonNull
    private final Paint haloPaint;
    private int haloRadius;
    @NonNull
    private final Paint inactiveTicksPaint;
    @NonNull
    private final Paint inactiveTrackPaint;
    private boolean isLongPress;
    private int labelBehavior;
    private int labelPadding;
    private int labelStyle;
    /* access modifiers changed from: private */
    @NonNull
    public final List<TooltipDrawable> labels;
    private boolean labelsAreAnimatedIn;
    private ValueAnimator labelsInAnimator;
    private ValueAnimator labelsOutAnimator;
    private MotionEvent lastEvent;
    private int minTickSpacing;
    @Px
    private int minTouchTargetSize;
    private int minTrackSidePadding;
    private int minWidgetHeight;
    @NonNull
    private final ViewTreeObserver.OnScrollChangedListener onScrollChangedListener;
    private final int scaledTouchSlop;
    private int separationUnit;
    private float stepSize;
    @NonNull
    private final Paint stopIndicatorPaint;
    private int thumbHeight;
    private boolean thumbIsPressed;
    @NonNull
    private final Paint thumbPaint;
    private int thumbTrackGapSize;
    private int thumbWidth;
    private int tickActiveRadius;
    @NonNull
    private ColorStateList tickColorActive;
    @NonNull
    private ColorStateList tickColorInactive;
    private int tickInactiveRadius;
    private boolean tickVisible;
    private float[] ticksCoordinates;
    private float touchDownX;
    @NonNull
    private final List<T> touchListeners;
    private float touchPosition;
    @NonNull
    private ColorStateList trackColorActive;
    @NonNull
    private ColorStateList trackColorInactive;
    private int trackHeight;
    private int trackInsideCornerSize;
    @NonNull
    private final Path trackPath;
    @NonNull
    private final RectF trackRect;
    private int trackSidePadding;
    private int trackStopIndicatorSize;
    private int trackWidth;
    private float valueFrom;
    private float valueTo;
    private ArrayList<Float> values;
    private int widgetHeight;

    /* renamed from: com.google.android.material.slider.BaseSlider$3  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$google$android$material$slider$BaseSlider$FullCornerDirection;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                com.google.android.material.slider.BaseSlider$FullCornerDirection[] r0 = com.google.android.material.slider.BaseSlider.FullCornerDirection.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$google$android$material$slider$BaseSlider$FullCornerDirection = r0
                com.google.android.material.slider.BaseSlider$FullCornerDirection r1 = com.google.android.material.slider.BaseSlider.FullCornerDirection.NONE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$google$android$material$slider$BaseSlider$FullCornerDirection     // Catch:{ NoSuchFieldError -> 0x001d }
                com.google.android.material.slider.BaseSlider$FullCornerDirection r1 = com.google.android.material.slider.BaseSlider.FullCornerDirection.LEFT     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$google$android$material$slider$BaseSlider$FullCornerDirection     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.google.android.material.slider.BaseSlider$FullCornerDirection r1 = com.google.android.material.slider.BaseSlider.FullCornerDirection.RIGHT     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$google$android$material$slider$BaseSlider$FullCornerDirection     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.google.android.material.slider.BaseSlider$FullCornerDirection r1 = com.google.android.material.slider.BaseSlider.FullCornerDirection.BOTH     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.slider.BaseSlider.AnonymousClass3.<clinit>():void");
        }
    }

    public class AccessibilityEventSender implements Runnable {
        int virtualViewId;

        private AccessibilityEventSender() {
            this.virtualViewId = -1;
        }

        public void run() {
            BaseSlider.this.accessibilityHelper.sendEventForVirtualView(this.virtualViewId, 4);
        }

        public void setVirtualViewId(int i3) {
            this.virtualViewId = i3;
        }
    }

    public static class AccessibilityHelper extends ExploreByTouchHelper {
        private final BaseSlider<?, ?, ?> slider;
        final Rect virtualViewBounds = new Rect();

        public AccessibilityHelper(BaseSlider<?, ?, ?> baseSlider) {
            super(baseSlider);
            this.slider = baseSlider;
        }

        @NonNull
        private String startOrEndDescription(int i3) {
            return i3 == this.slider.getValues().size() + -1 ? this.slider.getContext().getString(R.string.material_slider_range_end) : i3 == 0 ? this.slider.getContext().getString(R.string.material_slider_range_start) : "";
        }

        public int getVirtualViewAt(float f2, float f3) {
            for (int i3 = 0; i3 < this.slider.getValues().size(); i3++) {
                this.slider.updateBoundsForVirtualViewId(i3, this.virtualViewBounds);
                if (this.virtualViewBounds.contains((int) f2, (int) f3)) {
                    return i3;
                }
            }
            return -1;
        }

        public void getVisibleVirtualViews(List<Integer> list) {
            for (int i3 = 0; i3 < this.slider.getValues().size(); i3++) {
                list.add(Integer.valueOf(i3));
            }
        }

        public boolean onPerformActionForVirtualView(int i3, int i4, Bundle bundle) {
            if (!this.slider.isEnabled()) {
                return false;
            }
            if (i4 == 4096 || i4 == 8192) {
                float access$700 = this.slider.calculateStepIncrement(20);
                if (i4 == 8192) {
                    access$700 = -access$700;
                }
                if (this.slider.isRtl()) {
                    access$700 = -access$700;
                }
                if (!this.slider.snapThumbToValue(i3, MathUtils.clamp(this.slider.getValues().get(i3).floatValue() + access$700, this.slider.getValueFrom(), this.slider.getValueTo()))) {
                    return false;
                }
                this.slider.updateHaloHotspot();
                this.slider.postInvalidate();
                invalidateVirtualView(i3);
                return true;
            }
            if (i4 == 16908349 && bundle != null && bundle.containsKey(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_PROGRESS_VALUE)) {
                if (this.slider.snapThumbToValue(i3, bundle.getFloat(AccessibilityNodeInfoCompat.ACTION_ARGUMENT_PROGRESS_VALUE))) {
                    this.slider.updateHaloHotspot();
                    this.slider.postInvalidate();
                    invalidateVirtualView(i3);
                    return true;
                }
            }
            return false;
        }

        public void onPopulateNodeForVirtualView(int i3, AccessibilityNodeInfoCompat accessibilityNodeInfoCompat) {
            accessibilityNodeInfoCompat.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_SET_PROGRESS);
            List<Float> values = this.slider.getValues();
            float floatValue = values.get(i3).floatValue();
            float valueFrom = this.slider.getValueFrom();
            float valueTo = this.slider.getValueTo();
            if (this.slider.isEnabled()) {
                if (floatValue > valueFrom) {
                    accessibilityNodeInfoCompat.addAction(8192);
                }
                if (floatValue < valueTo) {
                    accessibilityNodeInfoCompat.addAction(4096);
                }
            }
            accessibilityNodeInfoCompat.setRangeInfo(AccessibilityNodeInfoCompat.RangeInfoCompat.obtain(1, valueFrom, valueTo, floatValue));
            accessibilityNodeInfoCompat.setClassName(SeekBar.class.getName());
            StringBuilder sb = new StringBuilder();
            if (this.slider.getContentDescription() != null) {
                sb.append(this.slider.getContentDescription());
                sb.append(",");
            }
            String access$400 = this.slider.formatValue(floatValue);
            String string = this.slider.getContext().getString(R.string.material_slider_value);
            if (values.size() > 1) {
                string = startOrEndDescription(i3);
            }
            Locale locale = Locale.US;
            sb.append(string + ", " + access$400);
            accessibilityNodeInfoCompat.setContentDescription(sb.toString());
            this.slider.updateBoundsForVirtualViewId(i3, this.virtualViewBounds);
            accessibilityNodeInfoCompat.setBoundsInParent(this.virtualViewBounds);
        }
    }

    public enum FullCornerDirection {
        BOTH,
        LEFT,
        RIGHT,
        NONE
    }

    public static class SliderState extends View.BaseSavedState {
        public static final Parcelable.Creator<SliderState> CREATOR = new Parcelable.Creator<SliderState>() {
            @NonNull
            public SliderState createFromParcel(@NonNull Parcel parcel) {
                return new SliderState(parcel);
            }

            @NonNull
            public SliderState[] newArray(int i3) {
                return new SliderState[i3];
            }
        };
        boolean hasFocus;
        float stepSize;
        float valueFrom;
        float valueTo;
        ArrayList<Float> values;

        public void writeToParcel(@NonNull Parcel parcel, int i3) {
            super.writeToParcel(parcel, i3);
            parcel.writeFloat(this.valueFrom);
            parcel.writeFloat(this.valueTo);
            parcel.writeList(this.values);
            parcel.writeFloat(this.stepSize);
            parcel.writeBooleanArray(new boolean[]{this.hasFocus});
        }

        public SliderState(Parcelable parcelable) {
            super(parcelable);
        }

        private SliderState(@NonNull Parcel parcel) {
            super(parcel);
            this.valueFrom = parcel.readFloat();
            this.valueTo = parcel.readFloat();
            ArrayList<Float> arrayList = new ArrayList<>();
            this.values = arrayList;
            parcel.readList(arrayList, Float.class.getClassLoader());
            this.stepSize = parcel.readFloat();
            this.hasFocus = parcel.createBooleanArray()[0];
        }
    }

    public BaseSlider(@NonNull Context context) {
        this(context, (AttributeSet) null);
    }

    private void adjustCustomThumbDrawableBounds(Drawable drawable) {
        int intrinsicWidth = drawable.getIntrinsicWidth();
        int intrinsicHeight = drawable.getIntrinsicHeight();
        if (intrinsicWidth == -1 && intrinsicHeight == -1) {
            drawable.setBounds(0, 0, this.thumbWidth, this.thumbHeight);
            return;
        }
        float max = ((float) Math.max(this.thumbWidth, this.thumbHeight)) / ((float) Math.max(intrinsicWidth, intrinsicHeight));
        drawable.setBounds(0, 0, (int) (((float) intrinsicWidth) * max), (int) (((float) intrinsicHeight) * max));
    }

    private void attachLabelToContentView(TooltipDrawable tooltipDrawable) {
        tooltipDrawable.setRelativeToView(ViewUtils.getContentView(this));
    }

    @Nullable
    private Float calculateIncrementForKey(int i3) {
        float calculateStepIncrement = this.isLongPress ? calculateStepIncrement(20) : calculateStepIncrement();
        if (i3 == 21) {
            if (!isRtl()) {
                calculateStepIncrement = -calculateStepIncrement;
            }
            return Float.valueOf(calculateStepIncrement);
        } else if (i3 == 22) {
            if (isRtl()) {
                calculateStepIncrement = -calculateStepIncrement;
            }
            return Float.valueOf(calculateStepIncrement);
        } else if (i3 == 69) {
            return Float.valueOf(-calculateStepIncrement);
        } else {
            if (i3 == 70 || i3 == 81) {
                return Float.valueOf(calculateStepIncrement);
            }
            return null;
        }
    }

    private float calculateStepIncrement() {
        float f2 = this.stepSize;
        if (f2 == 0.0f) {
            return 1.0f;
        }
        return f2;
    }

    private int calculateTrackCenter() {
        int i3 = this.widgetHeight / 2;
        int i4 = 0;
        if (this.labelBehavior == 1 || shouldAlwaysShowLabel()) {
            i4 = this.labels.get(0).getIntrinsicHeight();
        }
        return i3 + i4;
    }

    private ValueAnimator createLabelAnimator(boolean z2) {
        int i3;
        TimeInterpolator timeInterpolator;
        float f2 = 1.0f;
        float animatorCurrentValueOrDefault = getAnimatorCurrentValueOrDefault(z2 ? this.labelsOutAnimator : this.labelsInAnimator, z2 ? 0.0f : 1.0f);
        if (!z2) {
            f2 = 0.0f;
        }
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{animatorCurrentValueOrDefault, f2});
        if (z2) {
            i3 = MotionUtils.resolveThemeDuration(getContext(), LABEL_ANIMATION_ENTER_DURATION_ATTR, 83);
            timeInterpolator = MotionUtils.resolveThemeInterpolator(getContext(), LABEL_ANIMATION_ENTER_EASING_ATTR, AnimationUtils.DECELERATE_INTERPOLATOR);
        } else {
            i3 = MotionUtils.resolveThemeDuration(getContext(), LABEL_ANIMATION_EXIT_DURATION_ATTR, 117);
            timeInterpolator = MotionUtils.resolveThemeInterpolator(getContext(), LABEL_ANIMATION_EXIT_EASING_ATTR, AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR);
        }
        ofFloat.setDuration((long) i3);
        ofFloat.setInterpolator(timeInterpolator);
        ofFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
                for (TooltipDrawable revealFraction : BaseSlider.this.labels) {
                    revealFraction.setRevealFraction(floatValue);
                }
                ViewCompat.postInvalidateOnAnimation(BaseSlider.this);
            }
        });
        return ofFloat;
    }

    private void createLabelPool() {
        int i3;
        if (this.labels.size() > this.values.size()) {
            List<TooltipDrawable> subList = this.labels.subList(this.values.size(), this.labels.size());
            for (TooltipDrawable next : subList) {
                if (ViewCompat.isAttachedToWindow(this)) {
                    detachLabelFromContentView(next);
                }
            }
            subList.clear();
        }
        while (true) {
            i3 = 0;
            if (this.labels.size() >= this.values.size()) {
                break;
            }
            TooltipDrawable createFromAttributes = TooltipDrawable.createFromAttributes(getContext(), (AttributeSet) null, 0, this.labelStyle);
            this.labels.add(createFromAttributes);
            if (ViewCompat.isAttachedToWindow(this)) {
                attachLabelToContentView(createFromAttributes);
            }
        }
        if (this.labels.size() != 1) {
            i3 = 1;
        }
        for (TooltipDrawable strokeWidth : this.labels) {
            strokeWidth.setStrokeWidth((float) i3);
        }
    }

    private void detachLabelFromContentView(TooltipDrawable tooltipDrawable) {
        ViewOverlayImpl contentViewOverlay = ViewUtils.getContentViewOverlay(this);
        if (contentViewOverlay != null) {
            contentViewOverlay.remove(tooltipDrawable);
            tooltipDrawable.detachView(ViewUtils.getContentView(this));
        }
    }

    private float dimenToValue(float f2) {
        if (f2 == 0.0f) {
            return 0.0f;
        }
        float f3 = (f2 - ((float) this.trackSidePadding)) / ((float) this.trackWidth);
        float f4 = this.valueFrom;
        return a.b(f4, this.valueTo, f3, f4);
    }

    private void dispatchOnChangedFromUser(int i3) {
        for (L onValueChange : this.changeListeners) {
            onValueChange.onValueChange(this, this.values.get(i3).floatValue(), true);
        }
        AccessibilityManager accessibilityManager2 = this.accessibilityManager;
        if (accessibilityManager2 != null && accessibilityManager2.isEnabled()) {
            scheduleAccessibilityEventSender(i3);
        }
    }

    private void dispatchOnChangedProgrammatically() {
        for (L l2 : this.changeListeners) {
            Iterator<Float> it = this.values.iterator();
            while (it.hasNext()) {
                l2.onValueChange(this, it.next().floatValue(), false);
            }
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00b6 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void drawActiveTrack(@androidx.annotation.NonNull android.graphics.Canvas r12, int r13, int r14) {
        /*
            r11 = this;
            float[] r0 = r11.getActiveRange()
            int r1 = r11.trackSidePadding
            float r2 = (float) r1
            r3 = 1
            r4 = r0[r3]
            float r13 = (float) r13
            float r4 = r4 * r13
            float r8 = r4 + r2
            float r1 = (float) r1
            r2 = 0
            r0 = r0[r2]
            float r0 = r0 * r13
            float r6 = r0 + r1
            boolean r13 = r11.hasGapBetweenThumbAndTrack()
            if (r13 == 0) goto L_0x00ba
            com.google.android.material.slider.BaseSlider$FullCornerDirection r13 = com.google.android.material.slider.BaseSlider.FullCornerDirection.NONE
            java.util.ArrayList<java.lang.Float> r0 = r11.values
            int r0 = r0.size()
            if (r0 != r3) goto L_0x0030
            boolean r13 = r11.isRtl()
            if (r13 == 0) goto L_0x002e
            com.google.android.material.slider.BaseSlider$FullCornerDirection r13 = com.google.android.material.slider.BaseSlider.FullCornerDirection.RIGHT
            goto L_0x0030
        L_0x002e:
            com.google.android.material.slider.BaseSlider$FullCornerDirection r13 = com.google.android.material.slider.BaseSlider.FullCornerDirection.LEFT
        L_0x0030:
            java.util.ArrayList<java.lang.Float> r0 = r11.values
            int r0 = r0.size()
            if (r2 >= r0) goto L_0x00d0
            java.util.ArrayList<java.lang.Float> r0 = r11.values
            int r0 = r0.size()
            if (r0 <= r3) goto L_0x006e
            if (r2 <= 0) goto L_0x0054
            java.util.ArrayList<java.lang.Float> r0 = r11.values
            int r1 = r2 + -1
            java.lang.Object r0 = r0.get(r1)
            java.lang.Float r0 = (java.lang.Float) r0
            float r0 = r0.floatValue()
            float r6 = r11.valueToX(r0)
        L_0x0054:
            java.util.ArrayList<java.lang.Float> r0 = r11.values
            java.lang.Object r0 = r0.get(r2)
            java.lang.Float r0 = (java.lang.Float) r0
            float r0 = r0.floatValue()
            float r0 = r11.valueToX(r0)
            boolean r1 = r11.isRtl()
            if (r1 == 0) goto L_0x006d
            r8 = r6
            r6 = r0
            goto L_0x006e
        L_0x006d:
            r8 = r0
        L_0x006e:
            int[] r0 = com.google.android.material.slider.BaseSlider.AnonymousClass3.$SwitchMap$com$google$android$material$slider$BaseSlider$FullCornerDirection
            int r1 = r13.ordinal()
            r0 = r0[r1]
            r1 = 1073741824(0x40000000, float:2.0)
            if (r0 == r3) goto L_0x0096
            r4 = 2
            if (r0 == r4) goto L_0x008c
            r4 = 3
            if (r0 == r4) goto L_0x0081
            goto L_0x009b
        L_0x0081:
            int r0 = r11.thumbTrackGapSize
            float r0 = (float) r0
            float r6 = r6 + r0
            int r0 = r11.trackHeight
            float r0 = (float) r0
            float r0 = r0 / r1
            float r0 = r0 + r8
            r8 = r0
            goto L_0x009b
        L_0x008c:
            int r0 = r11.trackHeight
            float r0 = (float) r0
            float r0 = r0 / r1
            float r6 = r6 - r0
            int r0 = r11.thumbTrackGapSize
        L_0x0093:
            float r0 = (float) r0
            float r8 = r8 - r0
            goto L_0x009b
        L_0x0096:
            int r0 = r11.thumbTrackGapSize
            float r4 = (float) r0
            float r6 = r6 + r4
            goto L_0x0093
        L_0x009b:
            int r0 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r0 < 0) goto L_0x00a0
            goto L_0x00b6
        L_0x00a0:
            android.graphics.RectF r0 = r11.trackRect
            float r4 = (float) r14
            int r5 = r11.trackHeight
            float r7 = (float) r5
            float r7 = r7 / r1
            float r7 = r4 - r7
            float r5 = (float) r5
            float r5 = r5 / r1
            float r5 = r5 + r4
            r0.set(r6, r7, r8, r5)
            android.graphics.Paint r0 = r11.activeTrackPaint
            android.graphics.RectF r1 = r11.trackRect
            r11.updateTrack(r12, r0, r1, r13)
        L_0x00b6:
            int r2 = r2 + 1
            goto L_0x0030
        L_0x00ba:
            android.graphics.Paint r13 = r11.activeTrackPaint
            android.graphics.Paint$Style r0 = android.graphics.Paint.Style.STROKE
            r13.setStyle(r0)
            android.graphics.Paint r13 = r11.activeTrackPaint
            android.graphics.Paint$Cap r0 = android.graphics.Paint.Cap.ROUND
            r13.setStrokeCap(r0)
            float r9 = (float) r14
            android.graphics.Paint r10 = r11.activeTrackPaint
            r5 = r12
            r7 = r9
            r5.drawLine(r6, r7, r8, r9, r10)
        L_0x00d0:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.slider.BaseSlider.drawActiveTrack(android.graphics.Canvas, int, int):void");
    }

    private void drawInactiveTrack(@NonNull Canvas canvas, int i3, int i4) {
        float[] activeRange = getActiveRange();
        int i5 = this.trackSidePadding;
        float f2 = (float) i3;
        float f3 = (activeRange[1] * f2) + ((float) i5);
        if (f3 < ((float) (i5 + i3))) {
            if (hasGapBetweenThumbAndTrack()) {
                RectF rectF = this.trackRect;
                float f4 = f3 + ((float) this.thumbTrackGapSize);
                float f5 = (float) i4;
                int i6 = this.trackHeight;
                rectF.set(f4, f5 - (((float) i6) / 2.0f), (((float) i6) / 2.0f) + ((float) (this.trackSidePadding + i3)), (((float) i6) / 2.0f) + f5);
                updateTrack(canvas, this.inactiveTrackPaint, this.trackRect, FullCornerDirection.RIGHT);
            } else {
                this.inactiveTrackPaint.setStyle(Paint.Style.STROKE);
                this.inactiveTrackPaint.setStrokeCap(Paint.Cap.ROUND);
                float f6 = (float) i4;
                canvas.drawLine(f3, f6, (float) (this.trackSidePadding + i3), f6, this.inactiveTrackPaint);
            }
        }
        int i7 = this.trackSidePadding;
        float f7 = (activeRange[0] * f2) + ((float) i7);
        if (f7 <= ((float) i7)) {
            return;
        }
        if (hasGapBetweenThumbAndTrack()) {
            RectF rectF2 = this.trackRect;
            int i8 = this.trackHeight;
            float f8 = (float) i4;
            rectF2.set(((float) this.trackSidePadding) - (((float) i8) / 2.0f), f8 - (((float) i8) / 2.0f), f7 - ((float) this.thumbTrackGapSize), (((float) i8) / 2.0f) + f8);
            updateTrack(canvas, this.inactiveTrackPaint, this.trackRect, FullCornerDirection.LEFT);
            return;
        }
        this.inactiveTrackPaint.setStyle(Paint.Style.STROKE);
        this.inactiveTrackPaint.setStrokeCap(Paint.Cap.ROUND);
        float f9 = (float) i4;
        canvas.drawLine((float) this.trackSidePadding, f9, f7, f9, this.inactiveTrackPaint);
    }

    private void drawThumbDrawable(@NonNull Canvas canvas, int i3, int i4, float f2, @NonNull Drawable drawable) {
        canvas.save();
        canvas.translate(((float) (this.trackSidePadding + ((int) (normalizeValue(f2) * ((float) i3))))) - (((float) drawable.getBounds().width()) / 2.0f), ((float) i4) - (((float) drawable.getBounds().height()) / 2.0f));
        drawable.draw(canvas);
        canvas.restore();
    }

    private void drawThumbs(@NonNull Canvas canvas, int i3, int i4) {
        for (int i5 = 0; i5 < this.values.size(); i5++) {
            float floatValue = this.values.get(i5).floatValue();
            Drawable drawable = this.customThumbDrawable;
            if (drawable != null) {
                drawThumbDrawable(canvas, i3, i4, floatValue, drawable);
            } else if (i5 < this.customThumbDrawablesForValues.size()) {
                drawThumbDrawable(canvas, i3, i4, floatValue, this.customThumbDrawablesForValues.get(i5));
            } else {
                if (!isEnabled()) {
                    canvas.drawCircle((normalizeValue(floatValue) * ((float) i3)) + ((float) this.trackSidePadding), (float) i4, (float) getThumbRadius(), this.thumbPaint);
                }
                drawThumbDrawable(canvas, i3, i4, floatValue, this.defaultThumbDrawable);
            }
        }
    }

    private void ensureLabelsAdded() {
        if (!this.labelsAreAnimatedIn) {
            this.labelsAreAnimatedIn = true;
            ValueAnimator createLabelAnimator = createLabelAnimator(true);
            this.labelsInAnimator = createLabelAnimator;
            this.labelsOutAnimator = null;
            createLabelAnimator.start();
        }
        Iterator<TooltipDrawable> it = this.labels.iterator();
        for (int i3 = 0; i3 < this.values.size() && it.hasNext(); i3++) {
            if (i3 != this.focusedThumbIdx) {
                setValueForLabel(it.next(), this.values.get(i3).floatValue());
            }
        }
        if (it.hasNext()) {
            setValueForLabel(it.next(), this.values.get(this.focusedThumbIdx).floatValue());
            return;
        }
        throw new IllegalStateException(String.format("Not enough labels(%d) to display all the values(%d)", new Object[]{Integer.valueOf(this.labels.size()), Integer.valueOf(this.values.size())}));
    }

    private void ensureLabelsRemoved() {
        if (this.labelsAreAnimatedIn) {
            this.labelsAreAnimatedIn = false;
            ValueAnimator createLabelAnimator = createLabelAnimator(false);
            this.labelsOutAnimator = createLabelAnimator;
            this.labelsInAnimator = null;
            createLabelAnimator.addListener(new AnimatorListenerAdapter() {
                public void onAnimationEnd(Animator animator) {
                    super.onAnimationEnd(animator);
                    ViewOverlayImpl contentViewOverlay = ViewUtils.getContentViewOverlay(BaseSlider.this);
                    for (TooltipDrawable remove : BaseSlider.this.labels) {
                        contentViewOverlay.remove(remove);
                    }
                }
            });
            this.labelsOutAnimator.start();
        }
    }

    private void focusThumbOnFocusGained(int i3) {
        if (i3 == 1) {
            moveFocus(Integer.MAX_VALUE);
        } else if (i3 == 2) {
            moveFocus(Integer.MIN_VALUE);
        } else if (i3 == 17) {
            moveFocusInAbsoluteDirection(Integer.MAX_VALUE);
        } else if (i3 == 66) {
            moveFocusInAbsoluteDirection(Integer.MIN_VALUE);
        }
    }

    /* access modifiers changed from: private */
    public String formatValue(float f2) {
        if (hasLabelFormatter()) {
            return this.formatter.getFormattedValue(f2);
        }
        return String.format(((float) ((int) f2)) == f2 ? "%.0f" : "%.2f", new Object[]{Float.valueOf(f2)});
    }

    private float[] getActiveRange() {
        float floatValue = this.values.get(0).floatValue();
        float floatValue2 = ((Float) androidx.compose.ui.autofill.a.h(this.values, 1)).floatValue();
        if (this.values.size() == 1) {
            floatValue = this.valueFrom;
        }
        float normalizeValue = normalizeValue(floatValue);
        float normalizeValue2 = normalizeValue(floatValue2);
        if (isRtl()) {
            return new float[]{normalizeValue2, normalizeValue};
        }
        return new float[]{normalizeValue, normalizeValue2};
    }

    private static float getAnimatorCurrentValueOrDefault(ValueAnimator valueAnimator, float f2) {
        if (valueAnimator == null || !valueAnimator.isRunning()) {
            return f2;
        }
        float floatValue = ((Float) valueAnimator.getAnimatedValue()).floatValue();
        valueAnimator.cancel();
        return floatValue;
    }

    private float getClampedValue(int i3, float f2) {
        float minSeparation = getMinSeparation();
        if (this.separationUnit == 0) {
            minSeparation = dimenToValue(minSeparation);
        }
        if (isRtl()) {
            minSeparation = -minSeparation;
        }
        int i4 = i3 + 1;
        int i5 = i3 - 1;
        return MathUtils.clamp(f2, i5 < 0 ? this.valueFrom : this.values.get(i5).floatValue() + minSeparation, i4 >= this.values.size() ? this.valueTo : this.values.get(i4).floatValue() - minSeparation);
    }

    @ColorInt
    private int getColorForState(@NonNull ColorStateList colorStateList) {
        return colorStateList.getColorForState(getDrawableState(), colorStateList.getDefaultColor());
    }

    private float[] getCornerRadii(float f2, float f3) {
        return new float[]{f2, f2, f3, f3, f3, f3, f2, f2};
    }

    private float getValueOfTouchPosition() {
        double snapPosition = snapPosition(this.touchPosition);
        if (isRtl()) {
            snapPosition = 1.0d - snapPosition;
        }
        float f2 = this.valueTo;
        float f3 = this.valueFrom;
        return (float) ((snapPosition * ((double) (f2 - f3))) + ((double) f3));
    }

    private float getValueOfTouchPositionAbsolute() {
        float f2 = this.touchPosition;
        if (isRtl()) {
            f2 = 1.0f - f2;
        }
        float f3 = this.valueTo;
        float f4 = this.valueFrom;
        return a.b(f3, f4, f2, f4);
    }

    private boolean hasGapBetweenThumbAndTrack() {
        return this.thumbTrackGapSize > 0;
    }

    private Drawable initializeCustomThumbDrawable(Drawable drawable) {
        Drawable newDrawable = drawable.mutate().getConstantState().newDrawable();
        adjustCustomThumbDrawableBounds(newDrawable);
        return newDrawable;
    }

    private void invalidateTrack() {
        this.inactiveTrackPaint.setStrokeWidth((float) this.trackHeight);
        this.activeTrackPaint.setStrokeWidth((float) this.trackHeight);
    }

    private boolean isInVerticalScrollingContainer() {
        for (ViewParent parent = getParent(); parent instanceof ViewGroup; parent = parent.getParent()) {
            ViewGroup viewGroup = (ViewGroup) parent;
            if ((viewGroup.canScrollVertically(1) || viewGroup.canScrollVertically(-1)) && viewGroup.shouldDelayChildPressedState()) {
                return true;
            }
        }
        return false;
    }

    private static boolean isMouseEvent(MotionEvent motionEvent) {
        return motionEvent.getToolType(0) == 3;
    }

    private boolean isMultipleOfStepSize(double d2) {
        double doubleValue = new BigDecimal(Double.toString(d2)).divide(new BigDecimal(Float.toString(this.stepSize)), MathContext.DECIMAL64).doubleValue();
        return Math.abs(((double) Math.round(doubleValue)) - doubleValue) < THRESHOLD;
    }

    private boolean isPotentialVerticalScroll(MotionEvent motionEvent) {
        return !isMouseEvent(motionEvent) && isInVerticalScrollingContainer();
    }

    private boolean isSliderVisibleOnScreen() {
        Rect rect = new Rect();
        ViewUtils.getContentView(this).getHitRect(rect);
        return getLocalVisibleRect(rect);
    }

    private void loadResources(@NonNull Resources resources) {
        this.minWidgetHeight = resources.getDimensionPixelSize(R.dimen.mtrl_slider_widget_height);
        int dimensionPixelOffset = resources.getDimensionPixelOffset(R.dimen.mtrl_slider_track_side_padding);
        this.minTrackSidePadding = dimensionPixelOffset;
        this.trackSidePadding = dimensionPixelOffset;
        this.defaultThumbRadius = resources.getDimensionPixelSize(R.dimen.mtrl_slider_thumb_radius);
        this.defaultTrackHeight = resources.getDimensionPixelSize(R.dimen.mtrl_slider_track_height);
        int i3 = R.dimen.mtrl_slider_tick_radius;
        this.defaultTickActiveRadius = resources.getDimensionPixelSize(i3);
        this.defaultTickInactiveRadius = resources.getDimensionPixelSize(i3);
        this.minTickSpacing = resources.getDimensionPixelSize(R.dimen.mtrl_slider_tick_min_spacing);
        this.labelPadding = resources.getDimensionPixelSize(R.dimen.mtrl_slider_label_padding);
    }

    private void maybeCalculateTicksCoordinates() {
        if (this.stepSize > 0.0f) {
            validateConfigurationIfDirty();
            int min = Math.min((int) (((this.valueTo - this.valueFrom) / this.stepSize) + 1.0f), (this.trackWidth / this.minTickSpacing) + 1);
            float[] fArr = this.ticksCoordinates;
            if (fArr == null || fArr.length != min * 2) {
                this.ticksCoordinates = new float[(min * 2)];
            }
            float f2 = ((float) this.trackWidth) / ((float) (min - 1));
            for (int i3 = 0; i3 < min * 2; i3 += 2) {
                float[] fArr2 = this.ticksCoordinates;
                fArr2[i3] = ((((float) i3) / 2.0f) * f2) + ((float) this.trackSidePadding);
                fArr2[i3 + 1] = (float) calculateTrackCenter();
            }
        }
    }

    private void maybeDrawCompatHalo(@NonNull Canvas canvas, int i3, int i4) {
        if (shouldDrawCompatHalo()) {
            canvas.drawCircle((float) ((int) ((normalizeValue(this.values.get(this.focusedThumbIdx).floatValue()) * ((float) i3)) + ((float) this.trackSidePadding))), (float) i4, (float) this.haloRadius, this.haloPaint);
        }
    }

    private void maybeDrawStopIndicator(@NonNull Canvas canvas, int i3) {
        if (this.trackStopIndicatorSize > 0) {
            if (this.values.size() >= 1) {
                float floatValue = ((Float) androidx.compose.ui.autofill.a.h(this.values, 1)).floatValue();
                float f2 = this.valueTo;
                if (floatValue < f2) {
                    canvas.drawPoint(valueToX(f2), (float) i3, this.stopIndicatorPaint);
                }
            }
            if (this.values.size() > 1) {
                float floatValue2 = this.values.get(0).floatValue();
                float f3 = this.valueFrom;
                if (floatValue2 > f3) {
                    canvas.drawPoint(valueToX(f3), (float) i3, this.stopIndicatorPaint);
                }
            }
        }
    }

    private void maybeDrawTicks(@NonNull Canvas canvas) {
        if (this.tickVisible && this.stepSize > 0.0f) {
            float[] activeRange = getActiveRange();
            int ceil = (int) Math.ceil((double) (((((float) this.ticksCoordinates.length) / 2.0f) - 1.0f) * activeRange[0]));
            int floor = (int) Math.floor((double) (((((float) this.ticksCoordinates.length) / 2.0f) - 1.0f) * activeRange[1]));
            if (ceil > 0) {
                canvas.drawPoints(this.ticksCoordinates, 0, ceil * 2, this.inactiveTicksPaint);
            }
            if (ceil <= floor) {
                canvas.drawPoints(this.ticksCoordinates, ceil * 2, ((floor - ceil) + 1) * 2, this.activeTicksPaint);
            }
            int i3 = (floor + 1) * 2;
            float[] fArr = this.ticksCoordinates;
            if (i3 < fArr.length) {
                canvas.drawPoints(fArr, i3, fArr.length - i3, this.inactiveTicksPaint);
            }
        }
    }

    private boolean maybeIncreaseTrackSidePadding() {
        int max = Math.max((this.thumbWidth / 2) - this.defaultThumbRadius, 0);
        int max2 = Math.max((this.trackHeight - this.defaultTrackHeight) / 2, 0);
        int max3 = Math.max(this.tickActiveRadius - this.defaultTickActiveRadius, 0);
        int max4 = Math.max(this.tickInactiveRadius - this.defaultTickInactiveRadius, 0);
        int max5 = Math.max(Math.max(max, max2), Math.max(max3, max4)) + this.minTrackSidePadding;
        if (this.trackSidePadding == max5) {
            return false;
        }
        this.trackSidePadding = max5;
        if (!ViewCompat.isLaidOut(this)) {
            return true;
        }
        updateTrackWidth(getWidth());
        return true;
    }

    private boolean maybeIncreaseWidgetHeight() {
        int max = Math.max(this.minWidgetHeight, Math.max(this.trackHeight + getPaddingBottom() + getPaddingTop(), getPaddingBottom() + getPaddingTop() + this.thumbHeight));
        if (max == this.widgetHeight) {
            return false;
        }
        this.widgetHeight = max;
        return true;
    }

    private boolean moveFocus(int i3) {
        int i4 = this.focusedThumbIdx;
        int clamp = (int) MathUtils.clamp(((long) i4) + ((long) i3), 0, (long) (this.values.size() - 1));
        this.focusedThumbIdx = clamp;
        if (clamp == i4) {
            return false;
        }
        if (this.activeThumbIdx != -1) {
            this.activeThumbIdx = clamp;
        }
        updateHaloHotspot();
        postInvalidate();
        return true;
    }

    private boolean moveFocusInAbsoluteDirection(int i3) {
        if (isRtl()) {
            i3 = i3 == Integer.MIN_VALUE ? Integer.MAX_VALUE : -i3;
        }
        return moveFocus(i3);
    }

    private float normalizeValue(float f2) {
        float f3 = this.valueFrom;
        float f4 = (f2 - f3) / (this.valueTo - f3);
        return isRtl() ? 1.0f - f4 : f4;
    }

    @Nullable
    private Boolean onKeyDownNoActiveThumb(int i3, @NonNull KeyEvent keyEvent) {
        if (i3 == 61) {
            return keyEvent.hasNoModifiers() ? Boolean.valueOf(moveFocus(1)) : keyEvent.isShiftPressed() ? Boolean.valueOf(moveFocus(-1)) : Boolean.FALSE;
        }
        if (i3 != 66) {
            if (i3 != 81) {
                if (i3 == 69) {
                    moveFocus(-1);
                    return Boolean.TRUE;
                } else if (i3 != 70) {
                    switch (i3) {
                        case 21:
                            moveFocusInAbsoluteDirection(-1);
                            return Boolean.TRUE;
                        case 22:
                            moveFocusInAbsoluteDirection(1);
                            return Boolean.TRUE;
                        case 23:
                            break;
                        default:
                            return null;
                    }
                }
            }
            moveFocus(1);
            return Boolean.TRUE;
        }
        this.activeThumbIdx = this.focusedThumbIdx;
        postInvalidate();
        return Boolean.TRUE;
    }

    private void onStartTrackingTouch() {
        for (T onStartTrackingTouch : this.touchListeners) {
            onStartTrackingTouch.onStartTrackingTouch(this);
        }
    }

    private void onStopTrackingTouch() {
        for (T onStopTrackingTouch : this.touchListeners) {
            onStopTrackingTouch.onStopTrackingTouch(this);
        }
    }

    private void positionLabel(TooltipDrawable tooltipDrawable, float f2) {
        int normalizeValue = (this.trackSidePadding + ((int) (normalizeValue(f2) * ((float) this.trackWidth)))) - (tooltipDrawable.getIntrinsicWidth() / 2);
        int calculateTrackCenter = calculateTrackCenter() - ((this.thumbHeight / 2) + this.labelPadding);
        tooltipDrawable.setBounds(normalizeValue, calculateTrackCenter - tooltipDrawable.getIntrinsicHeight(), tooltipDrawable.getIntrinsicWidth() + normalizeValue, calculateTrackCenter);
        Rect rect = new Rect(tooltipDrawable.getBounds());
        DescendantOffsetUtils.offsetDescendantRect(ViewUtils.getContentView(this), this, rect);
        tooltipDrawable.setBounds(rect);
    }

    private void processAttributes(Context context, AttributeSet attributeSet, int i3) {
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context, attributeSet, R.styleable.Slider, i3, DEF_STYLE_RES, new int[0]);
        this.labelStyle = obtainStyledAttributes.getResourceId(R.styleable.Slider_labelStyle, R.style.Widget_MaterialComponents_Tooltip);
        this.valueFrom = obtainStyledAttributes.getFloat(R.styleable.Slider_android_valueFrom, 0.0f);
        this.valueTo = obtainStyledAttributes.getFloat(R.styleable.Slider_android_valueTo, 1.0f);
        setValues(Float.valueOf(this.valueFrom));
        this.stepSize = obtainStyledAttributes.getFloat(R.styleable.Slider_android_stepSize, 0.0f);
        this.minTouchTargetSize = (int) Math.ceil((double) obtainStyledAttributes.getDimension(R.styleable.Slider_minTouchTargetSize, (float) Math.ceil((double) ViewUtils.dpToPx(getContext(), 48))));
        int i4 = R.styleable.Slider_trackColor;
        boolean hasValue = obtainStyledAttributes.hasValue(i4);
        int i5 = hasValue ? i4 : R.styleable.Slider_trackColorInactive;
        if (!hasValue) {
            i4 = R.styleable.Slider_trackColorActive;
        }
        ColorStateList colorStateList = MaterialResources.getColorStateList(context, obtainStyledAttributes, i5);
        if (colorStateList == null) {
            colorStateList = AppCompatResources.getColorStateList(context, R.color.material_slider_inactive_track_color);
        }
        setTrackInactiveTintList(colorStateList);
        ColorStateList colorStateList2 = MaterialResources.getColorStateList(context, obtainStyledAttributes, i4);
        if (colorStateList2 == null) {
            colorStateList2 = AppCompatResources.getColorStateList(context, R.color.material_slider_active_track_color);
        }
        setTrackActiveTintList(colorStateList2);
        this.defaultThumbDrawable.setFillColor(MaterialResources.getColorStateList(context, obtainStyledAttributes, R.styleable.Slider_thumbColor));
        int i6 = R.styleable.Slider_thumbStrokeColor;
        if (obtainStyledAttributes.hasValue(i6)) {
            setThumbStrokeColor(MaterialResources.getColorStateList(context, obtainStyledAttributes, i6));
        }
        setThumbStrokeWidth(obtainStyledAttributes.getDimension(R.styleable.Slider_thumbStrokeWidth, 0.0f));
        ColorStateList colorStateList3 = MaterialResources.getColorStateList(context, obtainStyledAttributes, R.styleable.Slider_haloColor);
        if (colorStateList3 == null) {
            colorStateList3 = AppCompatResources.getColorStateList(context, R.color.material_slider_halo_color);
        }
        setHaloTintList(colorStateList3);
        this.tickVisible = obtainStyledAttributes.getBoolean(R.styleable.Slider_tickVisible, true);
        int i7 = R.styleable.Slider_tickColor;
        boolean hasValue2 = obtainStyledAttributes.hasValue(i7);
        int i8 = hasValue2 ? i7 : R.styleable.Slider_tickColorInactive;
        if (!hasValue2) {
            i7 = R.styleable.Slider_tickColorActive;
        }
        ColorStateList colorStateList4 = MaterialResources.getColorStateList(context, obtainStyledAttributes, i8);
        if (colorStateList4 == null) {
            colorStateList4 = AppCompatResources.getColorStateList(context, R.color.material_slider_inactive_tick_marks_color);
        }
        setTickInactiveTintList(colorStateList4);
        ColorStateList colorStateList5 = MaterialResources.getColorStateList(context, obtainStyledAttributes, i7);
        if (colorStateList5 == null) {
            colorStateList5 = AppCompatResources.getColorStateList(context, R.color.material_slider_active_tick_marks_color);
        }
        setTickActiveTintList(colorStateList5);
        setThumbTrackGapSize(obtainStyledAttributes.getDimensionPixelSize(R.styleable.Slider_thumbTrackGapSize, 0));
        setTrackStopIndicatorSize(obtainStyledAttributes.getDimensionPixelSize(R.styleable.Slider_trackStopIndicatorSize, 0));
        setTrackInsideCornerSize(obtainStyledAttributes.getDimensionPixelSize(R.styleable.Slider_trackInsideCornerSize, 0));
        int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.Slider_thumbRadius, 0) * 2;
        int dimensionPixelSize2 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.Slider_thumbWidth, dimensionPixelSize);
        int dimensionPixelSize3 = obtainStyledAttributes.getDimensionPixelSize(R.styleable.Slider_thumbHeight, dimensionPixelSize);
        setThumbWidth(dimensionPixelSize2);
        setThumbHeight(dimensionPixelSize3);
        setHaloRadius(obtainStyledAttributes.getDimensionPixelSize(R.styleable.Slider_haloRadius, 0));
        setThumbElevation(obtainStyledAttributes.getDimension(R.styleable.Slider_thumbElevation, 0.0f));
        setTrackHeight(obtainStyledAttributes.getDimensionPixelSize(R.styleable.Slider_trackHeight, 0));
        setTickActiveRadius(obtainStyledAttributes.getDimensionPixelSize(R.styleable.Slider_tickRadiusActive, this.trackStopIndicatorSize / 2));
        setTickInactiveRadius(obtainStyledAttributes.getDimensionPixelSize(R.styleable.Slider_tickRadiusInactive, this.trackStopIndicatorSize / 2));
        setLabelBehavior(obtainStyledAttributes.getInt(R.styleable.Slider_labelBehavior, 0));
        if (!obtainStyledAttributes.getBoolean(R.styleable.Slider_android_enabled, true)) {
            setEnabled(false);
        }
        obtainStyledAttributes.recycle();
    }

    private void scheduleAccessibilityEventSender(int i3) {
        BaseSlider<S, L, T>.AccessibilityEventSender accessibilityEventSender2 = this.accessibilityEventSender;
        if (accessibilityEventSender2 == null) {
            this.accessibilityEventSender = new AccessibilityEventSender();
        } else {
            removeCallbacks(accessibilityEventSender2);
        }
        this.accessibilityEventSender.setVirtualViewId(i3);
        postDelayed(this.accessibilityEventSender, 200);
    }

    private void setValueForLabel(TooltipDrawable tooltipDrawable, float f2) {
        tooltipDrawable.setText(formatValue(f2));
        positionLabel(tooltipDrawable, f2);
        ViewUtils.getContentViewOverlay(this).add(tooltipDrawable);
    }

    private void setValuesInternal(@NonNull ArrayList<Float> arrayList) {
        if (!arrayList.isEmpty()) {
            Collections.sort(arrayList);
            if (this.values.size() != arrayList.size() || !this.values.equals(arrayList)) {
                this.values = arrayList;
                this.dirtyConfig = true;
                this.focusedThumbIdx = 0;
                updateHaloHotspot();
                createLabelPool();
                dispatchOnChangedProgrammatically();
                postInvalidate();
                return;
            }
            return;
        }
        throw new IllegalArgumentException("At least one value must be set");
    }

    private boolean shouldAlwaysShowLabel() {
        return this.labelBehavior == 3;
    }

    private boolean shouldDrawCompatHalo() {
        return this.forceDrawCompatHalo || !(getBackground() instanceof RippleDrawable);
    }

    private boolean snapActiveThumbToValue(float f2) {
        return snapThumbToValue(this.activeThumbIdx, f2);
    }

    private double snapPosition(float f2) {
        float f3 = this.stepSize;
        if (f3 <= 0.0f) {
            return (double) f2;
        }
        int i3 = (int) ((this.valueTo - this.valueFrom) / f3);
        return ((double) Math.round(f2 * ((float) i3))) / ((double) i3);
    }

    /* access modifiers changed from: private */
    public boolean snapThumbToValue(int i3, float f2) {
        this.focusedThumbIdx = i3;
        if (((double) Math.abs(f2 - this.values.get(i3).floatValue())) < THRESHOLD) {
            return false;
        }
        this.values.set(i3, Float.valueOf(getClampedValue(i3, f2)));
        dispatchOnChangedFromUser(i3);
        return true;
    }

    private boolean snapTouchPosition() {
        return snapActiveThumbToValue(getValueOfTouchPosition());
    }

    /* access modifiers changed from: private */
    public void updateHaloHotspot() {
        if (!shouldDrawCompatHalo() && getMeasuredWidth() > 0) {
            Drawable background = getBackground();
            if (background instanceof RippleDrawable) {
                int normalizeValue = (int) ((normalizeValue(this.values.get(this.focusedThumbIdx).floatValue()) * ((float) this.trackWidth)) + ((float) this.trackSidePadding));
                int calculateTrackCenter = calculateTrackCenter();
                int i3 = this.haloRadius;
                DrawableCompat.setHotspotBounds(background, normalizeValue - i3, calculateTrackCenter - i3, normalizeValue + i3, calculateTrackCenter + i3);
            }
        }
    }

    /* access modifiers changed from: private */
    public void updateLabels() {
        int i3 = this.labelBehavior;
        if (i3 == 0 || i3 == 1) {
            if (this.activeThumbIdx == -1 || !isEnabled()) {
                ensureLabelsRemoved();
            } else {
                ensureLabelsAdded();
            }
        } else if (i3 == 2) {
            ensureLabelsRemoved();
        } else if (i3 != 3) {
            throw new IllegalArgumentException("Unexpected labelBehavior: " + this.labelBehavior);
        } else if (!isEnabled() || !isSliderVisibleOnScreen()) {
            ensureLabelsRemoved();
        } else {
            ensureLabelsAdded();
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:0x0042  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0053  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private void updateTrack(android.graphics.Canvas r9, android.graphics.Paint r10, android.graphics.RectF r11, com.google.android.material.slider.BaseSlider.FullCornerDirection r12) {
        /*
            r8 = this;
            int r0 = r8.trackHeight
            float r1 = (float) r0
            r2 = 1073741824(0x40000000, float:2.0)
            float r1 = r1 / r2
            float r0 = (float) r0
            float r0 = r0 / r2
            int[] r3 = com.google.android.material.slider.BaseSlider.AnonymousClass3.$SwitchMap$com$google$android$material$slider$BaseSlider$FullCornerDirection
            int r4 = r12.ordinal()
            r4 = r3[r4]
            r5 = 3
            r6 = 2
            r7 = 1
            if (r4 == r7) goto L_0x0022
            if (r4 == r6) goto L_0x001e
            if (r4 == r5) goto L_0x001a
            goto L_0x0026
        L_0x001a:
            int r1 = r8.trackInsideCornerSize
            float r1 = (float) r1
            goto L_0x0026
        L_0x001e:
            int r0 = r8.trackInsideCornerSize
        L_0x0020:
            float r0 = (float) r0
            goto L_0x0026
        L_0x0022:
            int r0 = r8.trackInsideCornerSize
            float r1 = (float) r0
            goto L_0x0020
        L_0x0026:
            android.graphics.Paint$Style r4 = android.graphics.Paint.Style.FILL
            r10.setStyle(r4)
            android.graphics.Paint$Cap r4 = android.graphics.Paint.Cap.BUTT
            r10.setStrokeCap(r4)
            r10.setAntiAlias(r7)
            android.graphics.Path r4 = r8.trackPath
            r4.reset()
            float r4 = r11.width()
            float r7 = r1 + r0
            int r4 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r4 < 0) goto L_0x0053
            android.graphics.Path r12 = r8.trackPath
            float[] r0 = r8.getCornerRadii(r1, r0)
            android.graphics.Path$Direction r1 = android.graphics.Path.Direction.CW
            r12.addRoundRect(r11, r0, r1)
            android.graphics.Path r8 = r8.trackPath
            r9.drawPath(r8, r10)
            goto L_0x00ac
        L_0x0053:
            float r4 = java.lang.Math.min(r1, r0)
            float r0 = java.lang.Math.max(r1, r0)
            r9.save()
            android.graphics.Path r1 = r8.trackPath
            android.graphics.Path$Direction r7 = android.graphics.Path.Direction.CW
            r1.addRoundRect(r11, r4, r4, r7)
            android.graphics.Path r1 = r8.trackPath
            r9.clipPath(r1)
            int r12 = r12.ordinal()
            r12 = r3[r12]
            if (r12 == r6) goto L_0x0097
            if (r12 == r5) goto L_0x0088
            android.graphics.RectF r12 = r8.cornerRect
            float r1 = r11.centerX()
            float r1 = r1 - r0
            float r2 = r11.top
            float r3 = r11.centerX()
            float r3 = r3 + r0
            float r11 = r11.bottom
            r12.set(r1, r2, r3, r11)
            goto L_0x00a4
        L_0x0088:
            android.graphics.RectF r12 = r8.cornerRect
            float r1 = r11.right
            float r2 = r2 * r0
            float r2 = r1 - r2
            float r3 = r11.top
            float r11 = r11.bottom
            r12.set(r2, r3, r1, r11)
            goto L_0x00a4
        L_0x0097:
            android.graphics.RectF r12 = r8.cornerRect
            float r1 = r11.left
            float r3 = r11.top
            float r2 = r2 * r0
            float r2 = r2 + r1
            float r11 = r11.bottom
            r12.set(r1, r3, r2, r11)
        L_0x00a4:
            android.graphics.RectF r8 = r8.cornerRect
            r9.drawRoundRect(r8, r0, r0, r10)
            r9.restore()
        L_0x00ac:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.slider.BaseSlider.updateTrack(android.graphics.Canvas, android.graphics.Paint, android.graphics.RectF, com.google.android.material.slider.BaseSlider$FullCornerDirection):void");
    }

    private void updateTrackWidth(int i3) {
        this.trackWidth = Math.max(i3 - (this.trackSidePadding * 2), 0);
        maybeCalculateTicksCoordinates();
    }

    private void updateWidgetLayout() {
        boolean maybeIncreaseWidgetHeight = maybeIncreaseWidgetHeight();
        boolean maybeIncreaseTrackSidePadding = maybeIncreaseTrackSidePadding();
        if (maybeIncreaseWidgetHeight) {
            requestLayout();
        } else if (maybeIncreaseTrackSidePadding) {
            postInvalidate();
        }
    }

    private void validateConfigurationIfDirty() {
        if (this.dirtyConfig) {
            validateValueFrom();
            validateValueTo();
            validateStepSize();
            validateValues();
            validateMinSeparation();
            warnAboutFloatingPointError();
            this.dirtyConfig = false;
        }
    }

    private void validateMinSeparation() {
        float minSeparation = getMinSeparation();
        if (minSeparation >= 0.0f) {
            float f2 = this.stepSize;
            if (f2 > 0.0f && minSeparation > 0.0f) {
                if (this.separationUnit != 1) {
                    float f3 = this.stepSize;
                    throw new IllegalStateException("minSeparation(" + minSeparation + ") cannot be set as a dimension when using stepSize(" + f3 + ")");
                } else if (minSeparation < f2 || !isMultipleOfStepSize((double) minSeparation)) {
                    float f4 = this.stepSize;
                    float f5 = this.stepSize;
                    StringBuilder sb = new StringBuilder("minSeparation(");
                    sb.append(minSeparation);
                    sb.append(") must be greater or equal and a multiple of stepSize(");
                    sb.append(f4);
                    sb.append(") when using stepSize(");
                    throw new IllegalStateException(C0118y.i(sb, ")", f5));
                }
            }
        } else {
            throw new IllegalStateException("minSeparation(" + minSeparation + ") must be greater or equal to 0");
        }
    }

    private void validateStepSize() {
        if (this.stepSize > 0.0f && !valueLandsOnTick(this.valueTo)) {
            float f2 = this.stepSize;
            float f3 = this.valueFrom;
            float f4 = this.valueTo;
            StringBuilder sb = new StringBuilder("The stepSize(");
            sb.append(f2);
            sb.append(") must be 0, or a factor of the valueFrom(");
            sb.append(f3);
            sb.append(")-valueTo(");
            throw new IllegalStateException(C0118y.i(sb, ") range", f4));
        }
    }

    private void validateValueFrom() {
        if (this.valueFrom >= this.valueTo) {
            float f2 = this.valueFrom;
            float f3 = this.valueTo;
            throw new IllegalStateException("valueFrom(" + f2 + ") must be smaller than valueTo(" + f3 + ")");
        }
    }

    private void validateValueTo() {
        if (this.valueTo <= this.valueFrom) {
            float f2 = this.valueTo;
            float f3 = this.valueFrom;
            throw new IllegalStateException("valueTo(" + f2 + ") must be greater than valueFrom(" + f3 + ")");
        }
    }

    private void validateValues() {
        Iterator<Float> it = this.values.iterator();
        while (it.hasNext()) {
            Float next = it.next();
            if (next.floatValue() < this.valueFrom || next.floatValue() > this.valueTo) {
                float f2 = this.valueFrom;
                float f3 = this.valueTo;
                StringBuilder sb = new StringBuilder("Slider value(");
                sb.append(next);
                sb.append(") must be greater or equal to valueFrom(");
                sb.append(f2);
                sb.append("), and lower or equal to valueTo(");
                throw new IllegalStateException(C0118y.i(sb, ")", f3));
            } else if (this.stepSize > 0.0f && !valueLandsOnTick(next.floatValue())) {
                float f4 = this.valueFrom;
                float f5 = this.stepSize;
                float f6 = this.stepSize;
                throw new IllegalStateException("Value(" + next + ") must be equal to valueFrom(" + f4 + ") plus a multiple of stepSize(" + f5 + ") when using stepSize(" + f6 + ")");
            }
        }
    }

    private boolean valueLandsOnTick(float f2) {
        return isMultipleOfStepSize(new BigDecimal(Float.toString(f2)).subtract(new BigDecimal(Float.toString(this.valueFrom)), MathContext.DECIMAL64).doubleValue());
    }

    private float valueToX(float f2) {
        return (normalizeValue(f2) * ((float) this.trackWidth)) + ((float) this.trackSidePadding);
    }

    private void warnAboutFloatingPointError() {
        float f2 = this.stepSize;
        if (f2 != 0.0f) {
            if (((float) ((int) f2)) != f2) {
                String str = TAG;
                Log.w(str, "Floating point value used for stepSize(" + f2 + "). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the value correctly.");
            }
            float f3 = this.valueFrom;
            if (((float) ((int) f3)) != f3) {
                String str2 = TAG;
                Log.w(str2, "Floating point value used for valueFrom(" + f3 + "). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the value correctly.");
            }
            float f4 = this.valueTo;
            if (((float) ((int) f4)) != f4) {
                String str3 = TAG;
                Log.w(str3, "Floating point value used for valueTo(" + f4 + "). Using floats can have rounding errors which may result in incorrect values. Instead, consider using integers with a custom LabelFormatter to display the value correctly.");
            }
        }
    }

    public void addOnChangeListener(@NonNull L l2) {
        this.changeListeners.add(l2);
    }

    public void addOnSliderTouchListener(@NonNull T t2) {
        this.touchListeners.add(t2);
    }

    public void clearOnChangeListeners() {
        this.changeListeners.clear();
    }

    public void clearOnSliderTouchListeners() {
        this.touchListeners.clear();
    }

    public boolean dispatchHoverEvent(@NonNull MotionEvent motionEvent) {
        return this.accessibilityHelper.dispatchHoverEvent(motionEvent) || super.dispatchHoverEvent(motionEvent);
    }

    public boolean dispatchKeyEvent(@NonNull KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    public void drawableStateChanged() {
        super.drawableStateChanged();
        this.inactiveTrackPaint.setColor(getColorForState(this.trackColorInactive));
        this.activeTrackPaint.setColor(getColorForState(this.trackColorActive));
        this.inactiveTicksPaint.setColor(getColorForState(this.tickColorInactive));
        this.activeTicksPaint.setColor(getColorForState(this.tickColorActive));
        this.stopIndicatorPaint.setColor(getColorForState(this.trackColorActive));
        for (TooltipDrawable next : this.labels) {
            if (next.isStateful()) {
                next.setState(getDrawableState());
            }
        }
        if (this.defaultThumbDrawable.isStateful()) {
            this.defaultThumbDrawable.setState(getDrawableState());
        }
        this.haloPaint.setColor(getColorForState(this.haloColor));
        this.haloPaint.setAlpha(63);
    }

    @VisibleForTesting
    public void forceDrawCompatHalo(boolean z2) {
        this.forceDrawCompatHalo = z2;
    }

    @NonNull
    public CharSequence getAccessibilityClassName() {
        return SeekBar.class.getName();
    }

    @VisibleForTesting
    public final int getAccessibilityFocusedVirtualViewId() {
        return this.accessibilityHelper.getAccessibilityFocusedVirtualViewId();
    }

    public int getActiveThumbIndex() {
        return this.activeThumbIdx;
    }

    public int getFocusedThumbIndex() {
        return this.focusedThumbIdx;
    }

    @Px
    public int getHaloRadius() {
        return this.haloRadius;
    }

    @NonNull
    public ColorStateList getHaloTintList() {
        return this.haloColor;
    }

    public int getLabelBehavior() {
        return this.labelBehavior;
    }

    public float getMinSeparation() {
        return 0.0f;
    }

    public float getStepSize() {
        return this.stepSize;
    }

    public float getThumbElevation() {
        return this.defaultThumbDrawable.getElevation();
    }

    @Px
    public int getThumbHeight() {
        return this.thumbHeight;
    }

    @Px
    public int getThumbRadius() {
        return this.thumbWidth / 2;
    }

    public ColorStateList getThumbStrokeColor() {
        return this.defaultThumbDrawable.getStrokeColor();
    }

    public float getThumbStrokeWidth() {
        return this.defaultThumbDrawable.getStrokeWidth();
    }

    @NonNull
    public ColorStateList getThumbTintList() {
        return this.defaultThumbDrawable.getFillColor();
    }

    public int getThumbTrackGapSize() {
        return this.thumbTrackGapSize;
    }

    @Px
    public int getThumbWidth() {
        return this.thumbWidth;
    }

    @Px
    public int getTickActiveRadius() {
        return this.tickActiveRadius;
    }

    @NonNull
    public ColorStateList getTickActiveTintList() {
        return this.tickColorActive;
    }

    @Px
    public int getTickInactiveRadius() {
        return this.tickInactiveRadius;
    }

    @NonNull
    public ColorStateList getTickInactiveTintList() {
        return this.tickColorInactive;
    }

    @NonNull
    public ColorStateList getTickTintList() {
        if (this.tickColorInactive.equals(this.tickColorActive)) {
            return this.tickColorActive;
        }
        throw new IllegalStateException("The inactive and active ticks are different colors. Use the getTickColorInactive() and getTickColorActive() methods instead.");
    }

    @NonNull
    public ColorStateList getTrackActiveTintList() {
        return this.trackColorActive;
    }

    @Px
    public int getTrackHeight() {
        return this.trackHeight;
    }

    @NonNull
    public ColorStateList getTrackInactiveTintList() {
        return this.trackColorInactive;
    }

    public int getTrackInsideCornerSize() {
        return this.trackInsideCornerSize;
    }

    @Px
    public int getTrackSidePadding() {
        return this.trackSidePadding;
    }

    public int getTrackStopIndicatorSize() {
        return this.trackStopIndicatorSize;
    }

    @NonNull
    public ColorStateList getTrackTintList() {
        if (this.trackColorInactive.equals(this.trackColorActive)) {
            return this.trackColorActive;
        }
        throw new IllegalStateException("The inactive and active parts of the track are different colors. Use the getInactiveTrackColor() and getActiveTrackColor() methods instead.");
    }

    @Px
    public int getTrackWidth() {
        return this.trackWidth;
    }

    public float getValueFrom() {
        return this.valueFrom;
    }

    public float getValueTo() {
        return this.valueTo;
    }

    @NonNull
    public List<Float> getValues() {
        return new ArrayList(this.values);
    }

    public boolean hasLabelFormatter() {
        return this.formatter != null;
    }

    public final boolean isRtl() {
        return ViewCompat.getLayoutDirection(this) == 1;
    }

    public boolean isTickVisible() {
        return this.tickVisible;
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnScrollChangedListener(this.onScrollChangedListener);
        for (TooltipDrawable attachLabelToContentView : this.labels) {
            attachLabelToContentView(attachLabelToContentView);
        }
    }

    public void onDetachedFromWindow() {
        BaseSlider<S, L, T>.AccessibilityEventSender accessibilityEventSender2 = this.accessibilityEventSender;
        if (accessibilityEventSender2 != null) {
            removeCallbacks(accessibilityEventSender2);
        }
        this.labelsAreAnimatedIn = false;
        for (TooltipDrawable detachLabelFromContentView : this.labels) {
            detachLabelFromContentView(detachLabelFromContentView);
        }
        getViewTreeObserver().removeOnScrollChangedListener(this.onScrollChangedListener);
        super.onDetachedFromWindow();
    }

    public void onDraw(@NonNull Canvas canvas) {
        if (this.dirtyConfig) {
            validateConfigurationIfDirty();
            maybeCalculateTicksCoordinates();
        }
        super.onDraw(canvas);
        int calculateTrackCenter = calculateTrackCenter();
        float floatValue = this.values.get(0).floatValue();
        float floatValue2 = ((Float) androidx.compose.ui.autofill.a.h(this.values, 1)).floatValue();
        if (floatValue2 < this.valueTo || (this.values.size() > 1 && floatValue > this.valueFrom)) {
            drawInactiveTrack(canvas, this.trackWidth, calculateTrackCenter);
        }
        if (floatValue2 > this.valueFrom) {
            drawActiveTrack(canvas, this.trackWidth, calculateTrackCenter);
        }
        maybeDrawTicks(canvas);
        maybeDrawStopIndicator(canvas, calculateTrackCenter);
        if ((this.thumbIsPressed || isFocused()) && isEnabled()) {
            maybeDrawCompatHalo(canvas, this.trackWidth, calculateTrackCenter);
        }
        updateLabels();
        drawThumbs(canvas, this.trackWidth, calculateTrackCenter);
    }

    public void onFocusChanged(boolean z2, int i3, @Nullable Rect rect) {
        super.onFocusChanged(z2, i3, rect);
        if (!z2) {
            this.activeThumbIdx = -1;
            this.accessibilityHelper.clearKeyboardFocusForVirtualView(this.focusedThumbIdx);
            return;
        }
        focusThumbOnFocusGained(i3);
        this.accessibilityHelper.requestKeyboardFocusForVirtualView(this.focusedThumbIdx);
    }

    public boolean onKeyDown(int i3, @NonNull KeyEvent keyEvent) {
        if (!isEnabled()) {
            return super.onKeyDown(i3, keyEvent);
        }
        if (this.values.size() == 1) {
            this.activeThumbIdx = 0;
        }
        if (this.activeThumbIdx == -1) {
            Boolean onKeyDownNoActiveThumb = onKeyDownNoActiveThumb(i3, keyEvent);
            return onKeyDownNoActiveThumb != null ? onKeyDownNoActiveThumb.booleanValue() : super.onKeyDown(i3, keyEvent);
        }
        this.isLongPress |= keyEvent.isLongPress();
        Float calculateIncrementForKey = calculateIncrementForKey(i3);
        if (calculateIncrementForKey != null) {
            if (snapActiveThumbToValue(calculateIncrementForKey.floatValue() + this.values.get(this.activeThumbIdx).floatValue())) {
                updateHaloHotspot();
                postInvalidate();
            }
            return true;
        }
        if (i3 != 23) {
            if (i3 != 61) {
                if (i3 != 66) {
                    return super.onKeyDown(i3, keyEvent);
                }
            } else if (keyEvent.hasNoModifiers()) {
                return moveFocus(1);
            } else {
                if (keyEvent.isShiftPressed()) {
                    return moveFocus(-1);
                }
                return false;
            }
        }
        this.activeThumbIdx = -1;
        postInvalidate();
        return true;
    }

    public boolean onKeyUp(int i3, @NonNull KeyEvent keyEvent) {
        this.isLongPress = false;
        return super.onKeyUp(i3, keyEvent);
    }

    public void onMeasure(int i3, int i4) {
        int i5 = this.widgetHeight;
        int i6 = 0;
        if (this.labelBehavior == 1 || shouldAlwaysShowLabel()) {
            i6 = this.labels.get(0).getIntrinsicHeight();
        }
        super.onMeasure(i3, View.MeasureSpec.makeMeasureSpec(i5 + i6, 1073741824));
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        SliderState sliderState = (SliderState) parcelable;
        super.onRestoreInstanceState(sliderState.getSuperState());
        this.valueFrom = sliderState.valueFrom;
        this.valueTo = sliderState.valueTo;
        setValuesInternal(sliderState.values);
        this.stepSize = sliderState.stepSize;
        if (sliderState.hasFocus) {
            requestFocus();
        }
    }

    public Parcelable onSaveInstanceState() {
        SliderState sliderState = new SliderState(super.onSaveInstanceState());
        sliderState.valueFrom = this.valueFrom;
        sliderState.valueTo = this.valueTo;
        sliderState.values = new ArrayList<>(this.values);
        sliderState.stepSize = this.stepSize;
        sliderState.hasFocus = hasFocus();
        return sliderState;
    }

    public void onSizeChanged(int i3, int i4, int i5, int i6) {
        updateTrackWidth(i3);
        updateHaloHotspot();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0033, code lost:
        if (r2 != 3) goto L_0x0125;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onTouchEvent(@androidx.annotation.NonNull android.view.MotionEvent r6) {
        /*
            r5 = this;
            boolean r0 = r5.isEnabled()
            r1 = 0
            if (r0 != 0) goto L_0x0008
            return r1
        L_0x0008:
            float r0 = r6.getX()
            int r2 = r5.trackSidePadding
            float r2 = (float) r2
            float r2 = r0 - r2
            int r3 = r5.trackWidth
            float r3 = (float) r3
            float r2 = r2 / r3
            r5.touchPosition = r2
            r3 = 0
            float r2 = java.lang.Math.max(r3, r2)
            r5.touchPosition = r2
            r3 = 1065353216(0x3f800000, float:1.0)
            float r2 = java.lang.Math.min(r3, r2)
            r5.touchPosition = r2
            int r2 = r6.getActionMasked()
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x00da
            if (r2 == r4) goto L_0x006f
            if (r2 == r3) goto L_0x0037
            r0 = 3
            if (r2 == r0) goto L_0x006f
            goto L_0x0125
        L_0x0037:
            boolean r2 = r5.thumbIsPressed
            if (r2 != 0) goto L_0x005a
            boolean r2 = r5.isPotentialVerticalScroll(r6)
            if (r2 == 0) goto L_0x0050
            float r2 = r5.touchDownX
            float r0 = r0 - r2
            float r0 = java.lang.Math.abs(r0)
            int r2 = r5.scaledTouchSlop
            float r2 = (float) r2
            int r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r0 >= 0) goto L_0x0050
            return r1
        L_0x0050:
            android.view.ViewParent r0 = r5.getParent()
            r0.requestDisallowInterceptTouchEvent(r4)
            r5.onStartTrackingTouch()
        L_0x005a:
            boolean r0 = r5.pickActiveThumb()
            if (r0 != 0) goto L_0x0062
            goto L_0x0125
        L_0x0062:
            r5.thumbIsPressed = r4
            r5.snapTouchPosition()
            r5.updateHaloHotspot()
            r5.invalidate()
            goto L_0x0125
        L_0x006f:
            r5.thumbIsPressed = r1
            android.view.MotionEvent r0 = r5.lastEvent
            if (r0 == 0) goto L_0x00b0
            int r0 = r0.getActionMasked()
            if (r0 != 0) goto L_0x00b0
            android.view.MotionEvent r0 = r5.lastEvent
            float r0 = r0.getX()
            float r1 = r6.getX()
            float r0 = r0 - r1
            float r0 = java.lang.Math.abs(r0)
            int r1 = r5.scaledTouchSlop
            float r1 = (float) r1
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 > 0) goto L_0x00b0
            android.view.MotionEvent r0 = r5.lastEvent
            float r0 = r0.getY()
            float r1 = r6.getY()
            float r0 = r0 - r1
            float r0 = java.lang.Math.abs(r0)
            int r1 = r5.scaledTouchSlop
            float r1 = (float) r1
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 > 0) goto L_0x00b0
            boolean r0 = r5.pickActiveThumb()
            if (r0 == 0) goto L_0x00b0
            r5.onStartTrackingTouch()
        L_0x00b0:
            int r0 = r5.activeThumbIdx
            r1 = -1
            if (r0 == r1) goto L_0x00d6
            r5.snapTouchPosition()
            r5.updateHaloHotspot()
            boolean r0 = r5.hasGapBetweenThumbAndTrack()
            if (r0 == 0) goto L_0x00d1
            int r0 = r5.defaultThumbWidth
            if (r0 == r1) goto L_0x00d1
            int r2 = r5.defaultThumbTrackGapSize
            if (r2 == r1) goto L_0x00d1
            r5.setThumbWidth(r0)
            int r0 = r5.defaultThumbTrackGapSize
            r5.setThumbTrackGapSize(r0)
        L_0x00d1:
            r5.activeThumbIdx = r1
            r5.onStopTrackingTouch()
        L_0x00d6:
            r5.invalidate()
            goto L_0x0125
        L_0x00da:
            r5.touchDownX = r0
            boolean r0 = r5.isPotentialVerticalScroll(r6)
            if (r0 == 0) goto L_0x00e3
            goto L_0x0125
        L_0x00e3:
            android.view.ViewParent r0 = r5.getParent()
            r0.requestDisallowInterceptTouchEvent(r4)
            boolean r0 = r5.pickActiveThumb()
            if (r0 != 0) goto L_0x00f1
            goto L_0x0125
        L_0x00f1:
            r5.requestFocus()
            r5.thumbIsPressed = r4
            r5.snapTouchPosition()
            r5.updateHaloHotspot()
            boolean r0 = r5.hasGapBetweenThumbAndTrack()
            if (r0 == 0) goto L_0x011f
            int r0 = r5.thumbWidth
            r5.defaultThumbWidth = r0
            int r1 = r5.thumbTrackGapSize
            r5.defaultThumbTrackGapSize = r1
            float r0 = (float) r0
            r1 = 1056964608(0x3f000000, float:0.5)
            float r0 = r0 * r1
            int r0 = java.lang.Math.round(r0)
            int r1 = r5.thumbWidth
            int r1 = r1 - r0
            r5.setThumbWidth(r0)
            int r0 = r5.thumbTrackGapSize
            int r1 = r1 / r3
            int r0 = r0 - r1
            r5.setThumbTrackGapSize(r0)
        L_0x011f:
            r5.invalidate()
            r5.onStartTrackingTouch()
        L_0x0125:
            boolean r0 = r5.thumbIsPressed
            r5.setPressed(r0)
            android.view.MotionEvent r6 = android.view.MotionEvent.obtain(r6)
            r5.lastEvent = r6
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.slider.BaseSlider.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void onVisibilityChanged(@NonNull View view, int i3) {
        ViewOverlayImpl contentViewOverlay;
        super.onVisibilityChanged(view, i3);
        if (i3 != 0 && (contentViewOverlay = ViewUtils.getContentViewOverlay(this)) != null) {
            for (TooltipDrawable remove : this.labels) {
                contentViewOverlay.remove(remove);
            }
        }
    }

    public boolean pickActiveThumb() {
        if (this.activeThumbIdx != -1) {
            return true;
        }
        float valueOfTouchPositionAbsolute = getValueOfTouchPositionAbsolute();
        float valueToX = valueToX(valueOfTouchPositionAbsolute);
        this.activeThumbIdx = 0;
        float abs = Math.abs(this.values.get(0).floatValue() - valueOfTouchPositionAbsolute);
        for (int i3 = 1; i3 < this.values.size(); i3++) {
            float abs2 = Math.abs(this.values.get(i3).floatValue() - valueOfTouchPositionAbsolute);
            float valueToX2 = valueToX(this.values.get(i3).floatValue());
            if (Float.compare(abs2, abs) > 0) {
                break;
            }
            boolean z2 = !isRtl() ? valueToX2 - valueToX < 0.0f : valueToX2 - valueToX > 0.0f;
            if (Float.compare(abs2, abs) < 0) {
                this.activeThumbIdx = i3;
            } else {
                if (Float.compare(abs2, abs) != 0) {
                    continue;
                } else if (Math.abs(valueToX2 - valueToX) < ((float) this.scaledTouchSlop)) {
                    this.activeThumbIdx = -1;
                    return false;
                } else if (z2) {
                    this.activeThumbIdx = i3;
                }
            }
            abs = abs2;
        }
        return this.activeThumbIdx != -1;
    }

    public void removeOnChangeListener(@NonNull L l2) {
        this.changeListeners.remove(l2);
    }

    public void removeOnSliderTouchListener(@NonNull T t2) {
        this.touchListeners.remove(t2);
    }

    public void setActiveThumbIndex(int i3) {
        this.activeThumbIdx = i3;
    }

    public void setCustomThumbDrawable(@DrawableRes int i3) {
        setCustomThumbDrawable(getResources().getDrawable(i3));
    }

    public void setCustomThumbDrawablesForValues(@NonNull @DrawableRes int... iArr) {
        Drawable[] drawableArr = new Drawable[iArr.length];
        for (int i3 = 0; i3 < iArr.length; i3++) {
            drawableArr[i3] = getResources().getDrawable(iArr[i3]);
        }
        setCustomThumbDrawablesForValues(drawableArr);
    }

    public void setEnabled(boolean z2) {
        super.setEnabled(z2);
        setLayerType(z2 ? 0 : 2, (Paint) null);
    }

    public void setFocusedThumbIndex(int i3) {
        if (i3 < 0 || i3 >= this.values.size()) {
            throw new IllegalArgumentException("index out of range");
        }
        this.focusedThumbIdx = i3;
        this.accessibilityHelper.requestKeyboardFocusForVirtualView(i3);
        postInvalidate();
    }

    public void setHaloRadius(@Px @IntRange(from = 0) int i3) {
        if (i3 != this.haloRadius) {
            this.haloRadius = i3;
            Drawable background = getBackground();
            if (shouldDrawCompatHalo() || !(background instanceof RippleDrawable)) {
                postInvalidate();
            } else {
                DrawableUtils.setRippleDrawableRadius((RippleDrawable) background, this.haloRadius);
            }
        }
    }

    public void setHaloRadiusResource(@DimenRes int i3) {
        setHaloRadius(getResources().getDimensionPixelSize(i3));
    }

    public void setHaloTintList(@NonNull ColorStateList colorStateList) {
        if (!colorStateList.equals(this.haloColor)) {
            this.haloColor = colorStateList;
            Drawable background = getBackground();
            if (shouldDrawCompatHalo() || !(background instanceof RippleDrawable)) {
                this.haloPaint.setColor(getColorForState(colorStateList));
                this.haloPaint.setAlpha(63);
                invalidate();
                return;
            }
            ((RippleDrawable) background).setColor(colorStateList);
        }
    }

    public void setLabelBehavior(int i3) {
        if (this.labelBehavior != i3) {
            this.labelBehavior = i3;
            requestLayout();
        }
    }

    public void setLabelFormatter(@Nullable LabelFormatter labelFormatter) {
        this.formatter = labelFormatter;
    }

    public void setSeparationUnit(int i3) {
        this.separationUnit = i3;
        this.dirtyConfig = true;
        postInvalidate();
    }

    public void setStepSize(float f2) {
        if (f2 < 0.0f) {
            float f3 = this.valueFrom;
            float f4 = this.valueTo;
            StringBuilder sb = new StringBuilder("The stepSize(");
            sb.append(f2);
            sb.append(") must be 0, or a factor of the valueFrom(");
            sb.append(f3);
            sb.append(")-valueTo(");
            throw new IllegalArgumentException(C0118y.i(sb, ") range", f4));
        } else if (this.stepSize != f2) {
            this.stepSize = f2;
            this.dirtyConfig = true;
            postInvalidate();
        }
    }

    public void setThumbElevation(float f2) {
        this.defaultThumbDrawable.setElevation(f2);
    }

    public void setThumbElevationResource(@DimenRes int i3) {
        setThumbElevation(getResources().getDimension(i3));
    }

    public void setThumbHeight(@Px @IntRange(from = 0) int i3) {
        if (i3 != this.thumbHeight) {
            this.thumbHeight = i3;
            this.defaultThumbDrawable.setBounds(0, 0, this.thumbWidth, i3);
            Drawable drawable = this.customThumbDrawable;
            if (drawable != null) {
                adjustCustomThumbDrawableBounds(drawable);
            }
            for (Drawable adjustCustomThumbDrawableBounds : this.customThumbDrawablesForValues) {
                adjustCustomThumbDrawableBounds(adjustCustomThumbDrawableBounds);
            }
            updateWidgetLayout();
        }
    }

    public void setThumbHeightResource(@DimenRes int i3) {
        setThumbHeight(getResources().getDimensionPixelSize(i3));
    }

    public void setThumbRadius(@Px @IntRange(from = 0) int i3) {
        int i4 = i3 * 2;
        setThumbWidth(i4);
        setThumbHeight(i4);
    }

    public void setThumbRadiusResource(@DimenRes int i3) {
        setThumbRadius(getResources().getDimensionPixelSize(i3));
    }

    public void setThumbStrokeColor(@Nullable ColorStateList colorStateList) {
        this.defaultThumbDrawable.setStrokeColor(colorStateList);
        postInvalidate();
    }

    public void setThumbStrokeColorResource(@ColorRes int i3) {
        if (i3 != 0) {
            setThumbStrokeColor(AppCompatResources.getColorStateList(getContext(), i3));
        }
    }

    public void setThumbStrokeWidth(float f2) {
        this.defaultThumbDrawable.setStrokeWidth(f2);
        postInvalidate();
    }

    public void setThumbStrokeWidthResource(@DimenRes int i3) {
        if (i3 != 0) {
            setThumbStrokeWidth(getResources().getDimension(i3));
        }
    }

    public void setThumbTintList(@NonNull ColorStateList colorStateList) {
        if (!colorStateList.equals(this.defaultThumbDrawable.getFillColor())) {
            this.defaultThumbDrawable.setFillColor(colorStateList);
            invalidate();
        }
    }

    public void setThumbTrackGapSize(@Px int i3) {
        if (this.thumbTrackGapSize != i3) {
            this.thumbTrackGapSize = i3;
            invalidate();
        }
    }

    public void setThumbWidth(@Px @IntRange(from = 0) int i3) {
        if (i3 != this.thumbWidth) {
            this.thumbWidth = i3;
            this.defaultThumbDrawable.setShapeAppearanceModel(ShapeAppearanceModel.builder().setAllCorners(0, ((float) this.thumbWidth) / 2.0f).build());
            this.defaultThumbDrawable.setBounds(0, 0, this.thumbWidth, this.thumbHeight);
            Drawable drawable = this.customThumbDrawable;
            if (drawable != null) {
                adjustCustomThumbDrawableBounds(drawable);
            }
            for (Drawable adjustCustomThumbDrawableBounds : this.customThumbDrawablesForValues) {
                adjustCustomThumbDrawableBounds(adjustCustomThumbDrawableBounds);
            }
            updateWidgetLayout();
        }
    }

    public void setThumbWidthResource(@DimenRes int i3) {
        setThumbWidth(getResources().getDimensionPixelSize(i3));
    }

    public void setTickActiveRadius(@Px @IntRange(from = 0) int i3) {
        if (this.tickActiveRadius != i3) {
            this.tickActiveRadius = i3;
            this.activeTicksPaint.setStrokeWidth((float) (i3 * 2));
            updateWidgetLayout();
        }
    }

    public void setTickActiveTintList(@NonNull ColorStateList colorStateList) {
        if (!colorStateList.equals(this.tickColorActive)) {
            this.tickColorActive = colorStateList;
            this.activeTicksPaint.setColor(getColorForState(colorStateList));
            invalidate();
        }
    }

    public void setTickInactiveRadius(@Px @IntRange(from = 0) int i3) {
        if (this.tickInactiveRadius != i3) {
            this.tickInactiveRadius = i3;
            this.inactiveTicksPaint.setStrokeWidth((float) (i3 * 2));
            updateWidgetLayout();
        }
    }

    public void setTickInactiveTintList(@NonNull ColorStateList colorStateList) {
        if (!colorStateList.equals(this.tickColorInactive)) {
            this.tickColorInactive = colorStateList;
            this.inactiveTicksPaint.setColor(getColorForState(colorStateList));
            invalidate();
        }
    }

    public void setTickTintList(@NonNull ColorStateList colorStateList) {
        setTickInactiveTintList(colorStateList);
        setTickActiveTintList(colorStateList);
    }

    public void setTickVisible(boolean z2) {
        if (this.tickVisible != z2) {
            this.tickVisible = z2;
            postInvalidate();
        }
    }

    public void setTrackActiveTintList(@NonNull ColorStateList colorStateList) {
        if (!colorStateList.equals(this.trackColorActive)) {
            this.trackColorActive = colorStateList;
            this.activeTrackPaint.setColor(getColorForState(colorStateList));
            this.stopIndicatorPaint.setColor(getColorForState(this.trackColorActive));
            invalidate();
        }
    }

    public void setTrackHeight(@Px @IntRange(from = 0) int i3) {
        if (this.trackHeight != i3) {
            this.trackHeight = i3;
            invalidateTrack();
            updateWidgetLayout();
        }
    }

    public void setTrackInactiveTintList(@NonNull ColorStateList colorStateList) {
        if (!colorStateList.equals(this.trackColorInactive)) {
            this.trackColorInactive = colorStateList;
            this.inactiveTrackPaint.setColor(getColorForState(colorStateList));
            invalidate();
        }
    }

    public void setTrackInsideCornerSize(@Px int i3) {
        if (this.trackInsideCornerSize != i3) {
            this.trackInsideCornerSize = i3;
            invalidate();
        }
    }

    public void setTrackStopIndicatorSize(@Px int i3) {
        if (this.trackStopIndicatorSize != i3) {
            this.trackStopIndicatorSize = i3;
            this.stopIndicatorPaint.setStrokeWidth((float) i3);
            invalidate();
        }
    }

    public void setTrackTintList(@NonNull ColorStateList colorStateList) {
        setTrackInactiveTintList(colorStateList);
        setTrackActiveTintList(colorStateList);
    }

    public void setValueFrom(float f2) {
        this.valueFrom = f2;
        this.dirtyConfig = true;
        postInvalidate();
    }

    public void setValueTo(float f2) {
        this.valueTo = f2;
        this.dirtyConfig = true;
        postInvalidate();
    }

    public void setValues(@NonNull Float... fArr) {
        ArrayList arrayList = new ArrayList();
        Collections.addAll(arrayList, fArr);
        setValuesInternal(arrayList);
    }

    public void updateBoundsForVirtualViewId(int i3, Rect rect) {
        int normalizeValue = this.trackSidePadding + ((int) (normalizeValue(getValues().get(i3).floatValue()) * ((float) this.trackWidth)));
        int calculateTrackCenter = calculateTrackCenter();
        int max = Math.max(this.thumbWidth / 2, this.minTouchTargetSize / 2);
        int max2 = Math.max(this.thumbHeight / 2, this.minTouchTargetSize / 2);
        rect.set(normalizeValue - max, calculateTrackCenter - max2, normalizeValue + max, calculateTrackCenter + max2);
    }

    public BaseSlider(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.sliderStyle);
    }

    /* access modifiers changed from: private */
    public float calculateStepIncrement(int i3) {
        float calculateStepIncrement = calculateStepIncrement();
        float f2 = (this.valueTo - this.valueFrom) / calculateStepIncrement;
        float f3 = (float) i3;
        if (f2 <= f3) {
            return calculateStepIncrement;
        }
        return ((float) Math.round(f2 / f3)) * calculateStepIncrement;
    }

    public void setCustomThumbDrawable(@NonNull Drawable drawable) {
        this.customThumbDrawable = initializeCustomThumbDrawable(drawable);
        this.customThumbDrawablesForValues.clear();
        postInvalidate();
    }

    public BaseSlider(@NonNull Context context, @Nullable AttributeSet attributeSet, int i3) {
        super(MaterialThemeOverlay.wrap(context, attributeSet, i3, DEF_STYLE_RES), attributeSet, i3);
        this.labels = new ArrayList();
        this.changeListeners = new ArrayList();
        this.touchListeners = new ArrayList();
        this.labelsAreAnimatedIn = false;
        this.defaultThumbWidth = -1;
        this.defaultThumbTrackGapSize = -1;
        this.thumbIsPressed = false;
        this.values = new ArrayList<>();
        this.activeThumbIdx = -1;
        this.focusedThumbIdx = -1;
        this.stepSize = 0.0f;
        this.tickVisible = true;
        this.isLongPress = false;
        this.trackPath = new Path();
        this.trackRect = new RectF();
        this.cornerRect = new RectF();
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable();
        this.defaultThumbDrawable = materialShapeDrawable;
        this.customThumbDrawablesForValues = Collections.emptyList();
        this.separationUnit = 0;
        this.onScrollChangedListener = new a(this);
        Context context2 = getContext();
        this.inactiveTrackPaint = new Paint();
        this.activeTrackPaint = new Paint();
        Paint paint = new Paint(1);
        this.thumbPaint = paint;
        Paint.Style style = Paint.Style.FILL;
        paint.setStyle(style);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        Paint paint2 = new Paint(1);
        this.haloPaint = paint2;
        paint2.setStyle(style);
        Paint paint3 = new Paint();
        this.inactiveTicksPaint = paint3;
        Paint.Style style2 = Paint.Style.STROKE;
        paint3.setStyle(style2);
        Paint.Cap cap = Paint.Cap.ROUND;
        paint3.setStrokeCap(cap);
        Paint paint4 = new Paint();
        this.activeTicksPaint = paint4;
        paint4.setStyle(style2);
        paint4.setStrokeCap(cap);
        Paint paint5 = new Paint();
        this.stopIndicatorPaint = paint5;
        paint5.setStyle(style);
        paint5.setStrokeCap(cap);
        loadResources(context2.getResources());
        processAttributes(context2, attributeSet, i3);
        setFocusable(true);
        setClickable(true);
        materialShapeDrawable.setShadowCompatibilityMode(2);
        this.scaledTouchSlop = ViewConfiguration.get(context2).getScaledTouchSlop();
        AccessibilityHelper accessibilityHelper2 = new AccessibilityHelper(this);
        this.accessibilityHelper = accessibilityHelper2;
        ViewCompat.setAccessibilityDelegate(this, accessibilityHelper2);
        this.accessibilityManager = (AccessibilityManager) getContext().getSystemService("accessibility");
    }

    public void setValues(@NonNull List<Float> list) {
        setValuesInternal(new ArrayList(list));
    }

    public void setCustomThumbDrawablesForValues(@NonNull Drawable... drawableArr) {
        this.customThumbDrawable = null;
        this.customThumbDrawablesForValues = new ArrayList();
        for (Drawable initializeCustomThumbDrawable : drawableArr) {
            this.customThumbDrawablesForValues.add(initializeCustomThumbDrawable(initializeCustomThumbDrawable));
        }
        postInvalidate();
    }
}
