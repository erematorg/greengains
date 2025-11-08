package com.google.android.material.carousel;

import A.a;
import C0.d;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.camera.camera2.internal.C0118y;
import androidx.core.graphics.ColorUtils;
import androidx.core.math.MathUtils;
import androidx.core.util.Preconditions;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.carousel.KeylineState;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class CarouselLayoutManager extends RecyclerView.LayoutManager implements Carousel, RecyclerView.SmoothScroller.ScrollVectorProvider {
    public static final int ALIGNMENT_CENTER = 1;
    public static final int ALIGNMENT_START = 0;
    public static final int HORIZONTAL = 0;
    private static final String TAG = "CarouselLayoutManager";
    public static final int VERTICAL = 1;
    private int carouselAlignment;
    @NonNull
    private CarouselStrategy carouselStrategy;
    private int currentEstimatedPosition;
    private int currentFillStartPosition;
    @Nullable
    private KeylineState currentKeylineState;
    private final DebugItemDecoration debugItemDecoration;
    private boolean isDebuggingEnabled;
    /* access modifiers changed from: private */
    @Nullable
    public KeylineStateList keylineStateList;
    @Nullable
    private Map<Integer, KeylineState> keylineStatePositionMap;
    private int lastItemCount;
    @VisibleForTesting
    int maxScroll;
    @VisibleForTesting
    int minScroll;
    private CarouselOrientationHelper orientationHelper;
    private final View.OnLayoutChangeListener recyclerViewSizeChangeListener;
    @VisibleForTesting
    int scrollOffset;

    public static final class ChildCalculations {
        final float center;
        final View child;
        final float offsetCenter;
        final KeylineRange range;

        public ChildCalculations(View view, float f2, float f3, KeylineRange keylineRange) {
            this.child = view;
            this.center = f2;
            this.offsetCenter = f3;
            this.range = keylineRange;
        }
    }

    public static class DebugItemDecoration extends RecyclerView.ItemDecoration {
        private List<KeylineState.Keyline> keylines = Collections.unmodifiableList(new ArrayList());
        private final Paint linePaint;

        public DebugItemDecoration() {
            Paint paint = new Paint();
            this.linePaint = paint;
            paint.setStrokeWidth(5.0f);
            paint.setColor(-65281);
        }

        public void onDrawOver(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
            super.onDrawOver(canvas, recyclerView, state);
            this.linePaint.setStrokeWidth(recyclerView.getResources().getDimension(R.dimen.m3_carousel_debug_keyline_width));
            for (KeylineState.Keyline next : this.keylines) {
                this.linePaint.setColor(ColorUtils.blendARGB(-65281, -16776961, next.mask));
                if (((CarouselLayoutManager) recyclerView.getLayoutManager()).isHorizontal()) {
                    canvas.drawLine(next.locOffset, (float) ((CarouselLayoutManager) recyclerView.getLayoutManager()).getParentTop(), next.locOffset, (float) ((CarouselLayoutManager) recyclerView.getLayoutManager()).getParentBottom(), this.linePaint);
                } else {
                    canvas.drawLine((float) ((CarouselLayoutManager) recyclerView.getLayoutManager()).getParentLeft(), next.locOffset, (float) ((CarouselLayoutManager) recyclerView.getLayoutManager()).getParentRight(), next.locOffset, this.linePaint);
                }
            }
        }

        public void setKeylines(List<KeylineState.Keyline> list) {
            this.keylines = Collections.unmodifiableList(list);
        }
    }

    public static class KeylineRange {
        final KeylineState.Keyline leftOrTop;
        final KeylineState.Keyline rightOrBottom;

        public KeylineRange(KeylineState.Keyline keyline, KeylineState.Keyline keyline2) {
            Preconditions.checkArgument(keyline.loc <= keyline2.loc);
            this.leftOrTop = keyline;
            this.rightOrBottom = keyline2;
        }
    }

    public static class LayoutDirection {
        private static final int INVALID_LAYOUT = Integer.MIN_VALUE;
        private static final int LAYOUT_END = 1;
        private static final int LAYOUT_START = -1;

        private LayoutDirection() {
        }
    }

    public CarouselLayoutManager() {
        this(new MultiBrowseCarouselStrategy());
    }

    private void addAndLayoutView(View view, int i3, ChildCalculations childCalculations) {
        float itemSize = this.currentKeylineState.getItemSize() / 2.0f;
        addView(view, i3);
        float f2 = childCalculations.offsetCenter;
        this.orientationHelper.layoutDecoratedWithMargins(view, (int) (f2 - itemSize), (int) (f2 + itemSize));
        updateChildMaskForLocation(view, childCalculations.center, childCalculations.range);
    }

    private float addEnd(float f2, float f3) {
        return isLayoutRtl() ? f2 - f3 : f2 + f3;
    }

    private float addStart(float f2, float f3) {
        return isLayoutRtl() ? f2 + f3 : f2 - f3;
    }

    private void addViewAtPosition(@NonNull RecyclerView.Recycler recycler, int i3, int i4) {
        if (i3 >= 0 && i3 < getItemCount()) {
            ChildCalculations makeChildCalculations = makeChildCalculations(recycler, calculateChildStartForFill(i3), i3);
            addAndLayoutView(makeChildCalculations.child, i4, makeChildCalculations);
        }
    }

    private void addViewsEnd(RecyclerView.Recycler recycler, RecyclerView.State state, int i3) {
        float calculateChildStartForFill = calculateChildStartForFill(i3);
        while (i3 < state.getItemCount()) {
            ChildCalculations makeChildCalculations = makeChildCalculations(recycler, calculateChildStartForFill, i3);
            if (!isLocOffsetOutOfFillBoundsEnd(makeChildCalculations.offsetCenter, makeChildCalculations.range)) {
                calculateChildStartForFill = addEnd(calculateChildStartForFill, this.currentKeylineState.getItemSize());
                if (!isLocOffsetOutOfFillBoundsStart(makeChildCalculations.offsetCenter, makeChildCalculations.range)) {
                    addAndLayoutView(makeChildCalculations.child, -1, makeChildCalculations);
                }
                i3++;
            } else {
                return;
            }
        }
    }

    private void addViewsStart(RecyclerView.Recycler recycler, int i3) {
        float calculateChildStartForFill = calculateChildStartForFill(i3);
        while (i3 >= 0) {
            ChildCalculations makeChildCalculations = makeChildCalculations(recycler, calculateChildStartForFill, i3);
            if (!isLocOffsetOutOfFillBoundsStart(makeChildCalculations.offsetCenter, makeChildCalculations.range)) {
                calculateChildStartForFill = addStart(calculateChildStartForFill, this.currentKeylineState.getItemSize());
                if (!isLocOffsetOutOfFillBoundsEnd(makeChildCalculations.offsetCenter, makeChildCalculations.range)) {
                    addAndLayoutView(makeChildCalculations.child, 0, makeChildCalculations);
                }
                i3--;
            } else {
                return;
            }
        }
    }

    private float calculateChildOffsetCenterForLocation(View view, float f2, KeylineRange keylineRange) {
        KeylineState.Keyline keyline = keylineRange.leftOrTop;
        float f3 = keyline.locOffset;
        KeylineState.Keyline keyline2 = keylineRange.rightOrBottom;
        float lerp = AnimationUtils.lerp(f3, keyline2.locOffset, keyline.loc, keyline2.loc, f2);
        if (keylineRange.rightOrBottom != this.currentKeylineState.getFirstKeyline() && keylineRange.leftOrTop != this.currentKeylineState.getLastKeyline()) {
            return lerp;
        }
        float maskMargins = this.orientationHelper.getMaskMargins((RecyclerView.LayoutParams) view.getLayoutParams()) / this.currentKeylineState.getItemSize();
        KeylineState.Keyline keyline3 = keylineRange.rightOrBottom;
        return lerp + (((1.0f - keyline3.mask) + maskMargins) * (f2 - keyline3.loc));
    }

    private float calculateChildStartForFill(int i3) {
        return addEnd((float) (getParentStart() - this.scrollOffset), this.currentKeylineState.getItemSize() * ((float) i3));
    }

    private int calculateEndScroll(RecyclerView.State state, KeylineStateList keylineStateList2) {
        boolean isLayoutRtl = isLayoutRtl();
        KeylineState startState = isLayoutRtl ? keylineStateList2.getStartState() : keylineStateList2.getEndState();
        KeylineState.Keyline firstFocalKeyline = isLayoutRtl ? startState.getFirstFocalKeyline() : startState.getLastFocalKeyline();
        int itemCount = (int) ((((((float) (state.getItemCount() - 1)) * startState.getItemSize()) * (isLayoutRtl ? -1.0f : 1.0f)) - (firstFocalKeyline.loc - ((float) getParentStart()))) + (((float) getParentEnd()) - firstFocalKeyline.loc) + (isLayoutRtl ? -firstFocalKeyline.leftOrTopPaddingShift : firstFocalKeyline.rightOrBottomPaddingShift));
        return isLayoutRtl ? Math.min(0, itemCount) : Math.max(0, itemCount);
    }

    private static int calculateShouldScrollBy(int i3, int i4, int i5, int i6) {
        int i7 = i4 + i3;
        return i7 < i5 ? i5 - i4 : i7 > i6 ? i6 - i4 : i3;
    }

    private int calculateStartScroll(@NonNull KeylineStateList keylineStateList2) {
        boolean isLayoutRtl = isLayoutRtl();
        KeylineState endState = isLayoutRtl ? keylineStateList2.getEndState() : keylineStateList2.getStartState();
        return (int) (((float) getParentStart()) - addStart((isLayoutRtl ? endState.getLastFocalKeyline() : endState.getFirstFocalKeyline()).loc, endState.getItemSize() / 2.0f));
    }

    private int convertFocusDirectionToLayoutDirection(int i3) {
        int orientation = getOrientation();
        if (i3 == 1) {
            return -1;
        }
        if (i3 == 2) {
            return 1;
        }
        if (i3 != 17) {
            if (i3 == 33) {
                return orientation == 1 ? -1 : Integer.MIN_VALUE;
            }
            if (i3 != 66) {
                if (i3 == 130) {
                    return orientation == 1 ? 1 : Integer.MIN_VALUE;
                }
                Log.d(TAG, "Unknown focus request:" + i3);
                return Integer.MIN_VALUE;
            } else if (orientation == 0) {
                return isLayoutRtl() ? -1 : 1;
            } else {
                return Integer.MIN_VALUE;
            }
        } else if (orientation == 0) {
            return isLayoutRtl() ? 1 : -1;
        } else {
            return Integer.MIN_VALUE;
        }
    }

    private void fill(RecyclerView.Recycler recycler, RecyclerView.State state) {
        removeAndRecycleOutOfBoundsViews(recycler);
        if (getChildCount() == 0) {
            addViewsStart(recycler, this.currentFillStartPosition - 1);
            addViewsEnd(recycler, state, this.currentFillStartPosition);
        } else {
            int position = getPosition(getChildAt(0));
            int position2 = getPosition(getChildAt(getChildCount() - 1));
            addViewsStart(recycler, position - 1);
            addViewsEnd(recycler, state, position2 + 1);
        }
        validateChildOrderIfDebugging();
    }

    private View getChildClosestToEnd() {
        return getChildAt(isLayoutRtl() ? 0 : getChildCount() - 1);
    }

    private View getChildClosestToStart() {
        return getChildAt(isLayoutRtl() ? getChildCount() - 1 : 0);
    }

    private int getContainerSize() {
        return isHorizontal() ? getContainerWidth() : getContainerHeight();
    }

    private float getDecoratedCenterWithMargins(View view) {
        Rect rect = new Rect();
        super.getDecoratedBoundsWithMargins(view, rect);
        return (float) (isHorizontal() ? rect.centerX() : rect.centerY());
    }

    private int getItemMargins() {
        int i3;
        int i4;
        if (getChildCount() <= 0) {
            return 0;
        }
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) getChildAt(0).getLayoutParams();
        if (this.orientationHelper.orientation == 0) {
            i3 = layoutParams.leftMargin;
            i4 = layoutParams.rightMargin;
        } else {
            i3 = layoutParams.topMargin;
            i4 = layoutParams.bottomMargin;
        }
        return i3 + i4;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r4 = r0.get(java.lang.Integer.valueOf(androidx.core.math.MathUtils.clamp(r4, 0, java.lang.Math.max(0, getItemCount() - 1))));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.google.android.material.carousel.KeylineState getKeylineStateForPosition(int r4) {
        /*
            r3 = this;
            java.util.Map<java.lang.Integer, com.google.android.material.carousel.KeylineState> r0 = r3.keylineStatePositionMap
            if (r0 == 0) goto L_0x0020
            int r1 = r3.getItemCount()
            int r1 = r1 + -1
            r2 = 0
            int r1 = java.lang.Math.max(r2, r1)
            int r4 = androidx.core.math.MathUtils.clamp((int) r4, (int) r2, (int) r1)
            java.lang.Integer r4 = java.lang.Integer.valueOf(r4)
            java.lang.Object r4 = r0.get(r4)
            com.google.android.material.carousel.KeylineState r4 = (com.google.android.material.carousel.KeylineState) r4
            if (r4 == 0) goto L_0x0020
            return r4
        L_0x0020:
            com.google.android.material.carousel.KeylineStateList r3 = r3.keylineStateList
            com.google.android.material.carousel.KeylineState r3 = r3.getDefaultState()
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.carousel.CarouselLayoutManager.getKeylineStateForPosition(int):com.google.android.material.carousel.KeylineState");
    }

    private int getLeftOrTopPaddingForKeylineShift() {
        if (getClipToPadding() || !this.carouselStrategy.isContained()) {
            return 0;
        }
        return getOrientation() == 1 ? getPaddingTop() : getPaddingLeft();
    }

    private float getMaskedItemSizeForLocOffset(float f2, KeylineRange keylineRange) {
        KeylineState.Keyline keyline = keylineRange.leftOrTop;
        float f3 = keyline.maskedItemSize;
        KeylineState.Keyline keyline2 = keylineRange.rightOrBottom;
        return AnimationUtils.lerp(f3, keyline2.maskedItemSize, keyline.locOffset, keyline2.locOffset, f2);
    }

    /* access modifiers changed from: private */
    public int getParentBottom() {
        return this.orientationHelper.getParentBottom();
    }

    private int getParentEnd() {
        return this.orientationHelper.getParentEnd();
    }

    /* access modifiers changed from: private */
    public int getParentLeft() {
        return this.orientationHelper.getParentLeft();
    }

    /* access modifiers changed from: private */
    public int getParentRight() {
        return this.orientationHelper.getParentRight();
    }

    private int getParentStart() {
        return this.orientationHelper.getParentStart();
    }

    /* access modifiers changed from: private */
    public int getParentTop() {
        return this.orientationHelper.getParentTop();
    }

    private int getRightOrBottomPaddingForKeylineShift() {
        if (getClipToPadding() || !this.carouselStrategy.isContained()) {
            return 0;
        }
        return getOrientation() == 1 ? getPaddingBottom() : getPaddingRight();
    }

    private int getScrollOffsetForPosition(int i3, KeylineState keylineState) {
        if (isLayoutRtl()) {
            return (int) (((((float) getContainerSize()) - keylineState.getLastFocalKeyline().loc) - (((float) i3) * keylineState.getItemSize())) - (keylineState.getItemSize() / 2.0f));
        }
        return (int) ((keylineState.getItemSize() / 2.0f) + ((((float) i3) * keylineState.getItemSize()) - keylineState.getFirstFocalKeyline().loc));
    }

    private int getSmallestScrollOffsetToFocalKeyline(int i3, @NonNull KeylineState keylineState) {
        int i4 = Integer.MAX_VALUE;
        for (KeylineState.Keyline next : keylineState.getFocalKeylines()) {
            float itemSize = (keylineState.getItemSize() / 2.0f) + (((float) i3) * keylineState.getItemSize());
            int containerSize = (isLayoutRtl() ? (int) ((((float) getContainerSize()) - next.loc) - itemSize) : (int) (itemSize - next.loc)) - this.scrollOffset;
            if (Math.abs(i4) > Math.abs(containerSize)) {
                i4 = containerSize;
            }
        }
        return i4;
    }

    private static KeylineRange getSurroundingKeylineRange(List<KeylineState.Keyline> list, float f2, boolean z2) {
        float f3 = Float.MAX_VALUE;
        int i3 = -1;
        int i4 = -1;
        int i5 = -1;
        int i6 = -1;
        float f4 = -3.4028235E38f;
        float f5 = Float.MAX_VALUE;
        float f6 = Float.MAX_VALUE;
        for (int i7 = 0; i7 < list.size(); i7++) {
            KeylineState.Keyline keyline = list.get(i7);
            float f7 = z2 ? keyline.locOffset : keyline.loc;
            float abs = Math.abs(f7 - f2);
            if (f7 <= f2 && abs <= f3) {
                i3 = i7;
                f3 = abs;
            }
            if (f7 > f2 && abs <= f5) {
                i5 = i7;
                f5 = abs;
            }
            if (f7 <= f6) {
                i4 = i7;
                f6 = f7;
            }
            if (f7 > f4) {
                i6 = i7;
                f4 = f7;
            }
        }
        if (i3 == -1) {
            i3 = i4;
        }
        if (i5 == -1) {
            i5 = i6;
        }
        return new KeylineRange(list.get(i3), list.get(i5));
    }

    private boolean isLocOffsetOutOfFillBoundsEnd(float f2, KeylineRange keylineRange) {
        float addStart = addStart(f2, getMaskedItemSizeForLocOffset(f2, keylineRange) / 2.0f);
        if (isLayoutRtl()) {
            if (addStart >= 0.0f) {
                return false;
            }
        } else if (addStart <= ((float) getContainerSize())) {
            return false;
        }
        return true;
    }

    private boolean isLocOffsetOutOfFillBoundsStart(float f2, KeylineRange keylineRange) {
        float addEnd = addEnd(f2, getMaskedItemSizeForLocOffset(f2, keylineRange) / 2.0f);
        if (isLayoutRtl()) {
            if (addEnd <= ((float) getContainerSize())) {
                return false;
            }
        } else if (addEnd >= 0.0f) {
            return false;
        }
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(View view, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10) {
        if (i3 != i7 || i4 != i8 || i5 != i9 || i6 != i10) {
            view.post(new d(this, 29));
        }
    }

    private void logChildrenIfDebugging() {
        if (this.isDebuggingEnabled && Log.isLoggable(TAG, 3)) {
            Log.d(TAG, "internal representation of views on the screen");
            for (int i3 = 0; i3 < getChildCount(); i3++) {
                View childAt = getChildAt(i3);
                float decoratedCenterWithMargins = getDecoratedCenterWithMargins(childAt);
                Log.d(TAG, "item position " + getPosition(childAt) + ", center:" + decoratedCenterWithMargins + ", child index:" + i3);
            }
            Log.d(TAG, "==============");
        }
    }

    private ChildCalculations makeChildCalculations(RecyclerView.Recycler recycler, float f2, int i3) {
        View viewForPosition = recycler.getViewForPosition(i3);
        measureChildWithMargins(viewForPosition, 0, 0);
        float addEnd = addEnd(f2, this.currentKeylineState.getItemSize() / 2.0f);
        KeylineRange surroundingKeylineRange = getSurroundingKeylineRange(this.currentKeylineState.getKeylines(), addEnd, false);
        return new ChildCalculations(viewForPosition, addEnd, calculateChildOffsetCenterForLocation(viewForPosition, addEnd, surroundingKeylineRange), surroundingKeylineRange);
    }

    private float offsetChild(View view, float f2, float f3, Rect rect) {
        float addEnd = addEnd(f2, f3);
        KeylineRange surroundingKeylineRange = getSurroundingKeylineRange(this.currentKeylineState.getKeylines(), addEnd, false);
        float calculateChildOffsetCenterForLocation = calculateChildOffsetCenterForLocation(view, addEnd, surroundingKeylineRange);
        super.getDecoratedBoundsWithMargins(view, rect);
        updateChildMaskForLocation(view, addEnd, surroundingKeylineRange);
        this.orientationHelper.offsetChild(view, rect, f3, calculateChildOffsetCenterForLocation);
        return calculateChildOffsetCenterForLocation;
    }

    private void recalculateKeylineStateList(RecyclerView.Recycler recycler) {
        View viewForPosition = recycler.getViewForPosition(0);
        measureChildWithMargins(viewForPosition, 0, 0);
        KeylineState onFirstChildMeasuredWithMargins = this.carouselStrategy.onFirstChildMeasuredWithMargins(this, viewForPosition);
        if (isLayoutRtl()) {
            onFirstChildMeasuredWithMargins = KeylineState.reverse(onFirstChildMeasuredWithMargins, (float) getContainerSize());
        }
        this.keylineStateList = KeylineStateList.from(this, onFirstChildMeasuredWithMargins, (float) getItemMargins(), (float) getLeftOrTopPaddingForKeylineShift(), (float) getRightOrBottomPaddingForKeylineShift());
    }

    /* access modifiers changed from: private */
    public void refreshKeylineState() {
        this.keylineStateList = null;
        requestLayout();
    }

    private void removeAndRecycleOutOfBoundsViews(RecyclerView.Recycler recycler) {
        while (getChildCount() > 0) {
            View childAt = getChildAt(0);
            float decoratedCenterWithMargins = getDecoratedCenterWithMargins(childAt);
            if (!isLocOffsetOutOfFillBoundsStart(decoratedCenterWithMargins, getSurroundingKeylineRange(this.currentKeylineState.getKeylines(), decoratedCenterWithMargins, true))) {
                break;
            }
            removeAndRecycleView(childAt, recycler);
        }
        while (getChildCount() - 1 >= 0) {
            View childAt2 = getChildAt(getChildCount() - 1);
            float decoratedCenterWithMargins2 = getDecoratedCenterWithMargins(childAt2);
            if (isLocOffsetOutOfFillBoundsEnd(decoratedCenterWithMargins2, getSurroundingKeylineRange(this.currentKeylineState.getKeylines(), decoratedCenterWithMargins2, true))) {
                removeAndRecycleView(childAt2, recycler);
            } else {
                return;
            }
        }
    }

    private void scrollBy(RecyclerView recyclerView, int i3) {
        if (isHorizontal()) {
            recyclerView.scrollBy(i3, 0);
        } else {
            recyclerView.scrollBy(0, i3);
        }
    }

    private void setCarouselAttributes(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.Carousel);
            setCarouselAlignment(obtainStyledAttributes.getInt(R.styleable.Carousel_carousel_alignment, 0));
            setOrientation(obtainStyledAttributes.getInt(R.styleable.RecyclerView_android_orientation, 0));
            obtainStyledAttributes.recycle();
        }
    }

    private void updateChildMaskForLocation(View view, float f2, KeylineRange keylineRange) {
        if (view instanceof Maskable) {
            KeylineState.Keyline keyline = keylineRange.leftOrTop;
            float f3 = keyline.mask;
            KeylineState.Keyline keyline2 = keylineRange.rightOrBottom;
            float lerp = AnimationUtils.lerp(f3, keyline2.mask, keyline.loc, keyline2.loc, f2);
            float height = (float) view.getHeight();
            float width = (float) view.getWidth();
            float lerp2 = AnimationUtils.lerp(0.0f, width / 2.0f, 0.0f, 1.0f, lerp);
            RectF maskRect = this.orientationHelper.getMaskRect(height, width, AnimationUtils.lerp(0.0f, height / 2.0f, 0.0f, 1.0f, lerp), lerp2);
            float calculateChildOffsetCenterForLocation = calculateChildOffsetCenterForLocation(view, f2, keylineRange);
            RectF rectF = new RectF(calculateChildOffsetCenterForLocation - (maskRect.width() / 2.0f), calculateChildOffsetCenterForLocation - (maskRect.height() / 2.0f), (maskRect.width() / 2.0f) + calculateChildOffsetCenterForLocation, (maskRect.height() / 2.0f) + calculateChildOffsetCenterForLocation);
            RectF rectF2 = new RectF((float) getParentLeft(), (float) getParentTop(), (float) getParentRight(), (float) getParentBottom());
            if (this.carouselStrategy.isContained()) {
                this.orientationHelper.containMaskWithinBounds(maskRect, rectF, rectF2);
            }
            this.orientationHelper.moveMaskOnEdgeOutsideBounds(maskRect, rectF, rectF2);
            ((Maskable) view).setMaskRectF(maskRect);
        }
    }

    private void updateCurrentKeylineStateForScrollOffset(@NonNull KeylineStateList keylineStateList2) {
        int i3 = this.maxScroll;
        int i4 = this.minScroll;
        if (i3 <= i4) {
            this.currentKeylineState = isLayoutRtl() ? keylineStateList2.getEndState() : keylineStateList2.getStartState();
        } else {
            this.currentKeylineState = keylineStateList2.getShiftedState((float) this.scrollOffset, (float) i4, (float) i3);
        }
        this.debugItemDecoration.setKeylines(this.currentKeylineState.getKeylines());
    }

    private void updateItemCount() {
        int itemCount = getItemCount();
        int i3 = this.lastItemCount;
        if (itemCount != i3 && this.keylineStateList != null) {
            if (this.carouselStrategy.shouldRefreshKeylineState(this, i3)) {
                refreshKeylineState();
            }
            this.lastItemCount = itemCount;
        }
    }

    private void validateChildOrderIfDebugging() {
        if (this.isDebuggingEnabled && getChildCount() >= 1) {
            int i3 = 0;
            while (i3 < getChildCount() - 1) {
                int position = getPosition(getChildAt(i3));
                int i4 = i3 + 1;
                int position2 = getPosition(getChildAt(i4));
                if (position <= position2) {
                    i3 = i4;
                } else {
                    logChildrenIfDebugging();
                    StringBuilder k2 = C0118y.k(i3, position, "Detected invalid child order. Child at index [", "] had adapter position [", "] and child at index [");
                    k2.append(i4);
                    k2.append("] had adapter position [");
                    k2.append(position2);
                    k2.append("].");
                    throw new IllegalStateException(k2.toString());
                }
            }
        }
    }

    public int calculateScrollDeltaToMakePositionVisible(int i3) {
        return (int) (((float) this.scrollOffset) - ((float) getScrollOffsetForPosition(i3, getKeylineStateForPosition(i3))));
    }

    public boolean canScrollHorizontally() {
        return isHorizontal();
    }

    public boolean canScrollVertically() {
        return !isHorizontal();
    }

    public int computeHorizontalScrollExtent(@NonNull RecyclerView.State state) {
        if (getChildCount() == 0 || this.keylineStateList == null || getItemCount() <= 1) {
            return 0;
        }
        return (int) (((float) getWidth()) * (this.keylineStateList.getDefaultState().getItemSize() / ((float) computeHorizontalScrollRange(state))));
    }

    public int computeHorizontalScrollOffset(@NonNull RecyclerView.State state) {
        return this.scrollOffset;
    }

    public int computeHorizontalScrollRange(@NonNull RecyclerView.State state) {
        return this.maxScroll - this.minScroll;
    }

    @Nullable
    public PointF computeScrollVectorForPosition(int i3) {
        if (this.keylineStateList == null) {
            return null;
        }
        int offsetToScrollToPosition = getOffsetToScrollToPosition(i3, getKeylineStateForPosition(i3));
        return isHorizontal() ? new PointF((float) offsetToScrollToPosition, 0.0f) : new PointF(0.0f, (float) offsetToScrollToPosition);
    }

    public int computeVerticalScrollExtent(@NonNull RecyclerView.State state) {
        if (getChildCount() == 0 || this.keylineStateList == null || getItemCount() <= 1) {
            return 0;
        }
        return (int) (((float) getHeight()) * (this.keylineStateList.getDefaultState().getItemSize() / ((float) computeVerticalScrollRange(state))));
    }

    public int computeVerticalScrollOffset(@NonNull RecyclerView.State state) {
        return this.scrollOffset;
    }

    public int computeVerticalScrollRange(@NonNull RecyclerView.State state) {
        return this.maxScroll - this.minScroll;
    }

    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(-2, -2);
    }

    public int getCarouselAlignment() {
        return this.carouselAlignment;
    }

    public int getContainerHeight() {
        return getHeight();
    }

    public int getContainerWidth() {
        return getWidth();
    }

    public void getDecoratedBoundsWithMargins(@NonNull View view, @NonNull Rect rect) {
        super.getDecoratedBoundsWithMargins(view, rect);
        float centerY = (float) rect.centerY();
        if (isHorizontal()) {
            centerY = (float) rect.centerX();
        }
        float maskedItemSizeForLocOffset = getMaskedItemSizeForLocOffset(centerY, getSurroundingKeylineRange(this.currentKeylineState.getKeylines(), centerY, true));
        float f2 = 0.0f;
        float width = isHorizontal() ? (((float) rect.width()) - maskedItemSizeForLocOffset) / 2.0f : 0.0f;
        if (!isHorizontal()) {
            f2 = (((float) rect.height()) - maskedItemSizeForLocOffset) / 2.0f;
        }
        rect.set((int) (((float) rect.left) + width), (int) (((float) rect.top) + f2), (int) (((float) rect.right) - width), (int) (((float) rect.bottom) - f2));
    }

    public int getOffsetToScrollToPosition(int i3, @NonNull KeylineState keylineState) {
        return getScrollOffsetForPosition(i3, keylineState) - this.scrollOffset;
    }

    public int getOffsetToScrollToPositionForSnap(int i3, boolean z2) {
        int offsetToScrollToPosition = getOffsetToScrollToPosition(i3, this.keylineStateList.getShiftedState((float) this.scrollOffset, (float) this.minScroll, (float) this.maxScroll, true));
        int offsetToScrollToPosition2 = this.keylineStatePositionMap != null ? getOffsetToScrollToPosition(i3, getKeylineStateForPosition(i3)) : offsetToScrollToPosition;
        return (!z2 || Math.abs(offsetToScrollToPosition2) >= Math.abs(offsetToScrollToPosition)) ? offsetToScrollToPosition : offsetToScrollToPosition2;
    }

    public int getOrientation() {
        return this.orientationHelper.orientation;
    }

    public boolean isAutoMeasureEnabled() {
        return true;
    }

    public boolean isHorizontal() {
        return this.orientationHelper.orientation == 0;
    }

    public boolean isLayoutRtl() {
        return isHorizontal() && getLayoutDirection() == 1;
    }

    public void measureChildWithMargins(@NonNull View view, int i3, int i4) {
        if (view instanceof Maskable) {
            RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) view.getLayoutParams();
            Rect rect = new Rect();
            calculateItemDecorationsForChild(view, rect);
            int i5 = rect.left + rect.right + i3;
            int i6 = rect.top + rect.bottom + i4;
            KeylineStateList keylineStateList2 = this.keylineStateList;
            float itemSize = (keylineStateList2 == null || this.orientationHelper.orientation != 0) ? (float) layoutParams.width : keylineStateList2.getDefaultState().getItemSize();
            KeylineStateList keylineStateList3 = this.keylineStateList;
            view.measure(RecyclerView.LayoutManager.getChildMeasureSpec(getWidth(), getWidthMode(), getPaddingRight() + getPaddingLeft() + layoutParams.leftMargin + layoutParams.rightMargin + i5, (int) itemSize, canScrollHorizontally()), RecyclerView.LayoutManager.getChildMeasureSpec(getHeight(), getHeightMode(), getPaddingBottom() + getPaddingTop() + layoutParams.topMargin + layoutParams.bottomMargin + i6, (int) ((keylineStateList3 == null || this.orientationHelper.orientation != 1) ? (float) layoutParams.height : keylineStateList3.getDefaultState().getItemSize()), canScrollVertically()));
            return;
        }
        throw new IllegalStateException("All children of a RecyclerView using CarouselLayoutManager must use MaskableFrameLayout as their root ViewGroup.");
    }

    public void onAttachedToWindow(RecyclerView recyclerView) {
        super.onAttachedToWindow(recyclerView);
        this.carouselStrategy.initialize(recyclerView.getContext());
        refreshKeylineState();
        recyclerView.addOnLayoutChangeListener(this.recyclerViewSizeChangeListener);
    }

    public void onDetachedFromWindow(RecyclerView recyclerView, RecyclerView.Recycler recycler) {
        super.onDetachedFromWindow(recyclerView, recycler);
        recyclerView.removeOnLayoutChangeListener(this.recyclerViewSizeChangeListener);
    }

    @Nullable
    public View onFocusSearchFailed(@NonNull View view, int i3, @NonNull RecyclerView.Recycler recycler, @NonNull RecyclerView.State state) {
        int convertFocusDirectionToLayoutDirection;
        if (getChildCount() == 0 || (convertFocusDirectionToLayoutDirection = convertFocusDirectionToLayoutDirection(i3)) == Integer.MIN_VALUE) {
            return null;
        }
        if (convertFocusDirectionToLayoutDirection == -1) {
            if (getPosition(view) == 0) {
                return null;
            }
            addViewAtPosition(recycler, getPosition(getChildAt(0)) - 1, 0);
            return getChildClosestToStart();
        } else if (getPosition(view) == getItemCount() - 1) {
            return null;
        } else {
            addViewAtPosition(recycler, getPosition(getChildAt(getChildCount() - 1)) + 1, -1);
            return getChildClosestToEnd();
        }
    }

    public void onInitializeAccessibilityEvent(@NonNull AccessibilityEvent accessibilityEvent) {
        super.onInitializeAccessibilityEvent(accessibilityEvent);
        if (getChildCount() > 0) {
            accessibilityEvent.setFromIndex(getPosition(getChildAt(0)));
            accessibilityEvent.setToIndex(getPosition(getChildAt(getChildCount() - 1)));
        }
    }

    public void onItemsAdded(@NonNull RecyclerView recyclerView, int i3, int i4) {
        super.onItemsAdded(recyclerView, i3, i4);
        updateItemCount();
    }

    public void onItemsRemoved(@NonNull RecyclerView recyclerView, int i3, int i4) {
        super.onItemsRemoved(recyclerView, i3, i4);
        updateItemCount();
    }

    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (state.getItemCount() <= 0 || ((float) getContainerSize()) <= 0.0f) {
            removeAndRecycleAllViews(recycler);
            this.currentFillStartPosition = 0;
            return;
        }
        boolean isLayoutRtl = isLayoutRtl();
        boolean z2 = this.keylineStateList == null;
        if (z2) {
            recalculateKeylineStateList(recycler);
        }
        int calculateStartScroll = calculateStartScroll(this.keylineStateList);
        int calculateEndScroll = calculateEndScroll(state, this.keylineStateList);
        this.minScroll = isLayoutRtl ? calculateEndScroll : calculateStartScroll;
        if (isLayoutRtl) {
            calculateEndScroll = calculateStartScroll;
        }
        this.maxScroll = calculateEndScroll;
        if (z2) {
            this.scrollOffset = calculateStartScroll;
            this.keylineStatePositionMap = this.keylineStateList.getKeylineStateForPositionMap(getItemCount(), this.minScroll, this.maxScroll, isLayoutRtl());
            int i3 = this.currentEstimatedPosition;
            if (i3 != -1) {
                this.scrollOffset = getScrollOffsetForPosition(i3, getKeylineStateForPosition(i3));
            }
        }
        int i4 = this.scrollOffset;
        this.scrollOffset = i4 + calculateShouldScrollBy(0, i4, this.minScroll, this.maxScroll);
        this.currentFillStartPosition = MathUtils.clamp(this.currentFillStartPosition, 0, state.getItemCount());
        updateCurrentKeylineStateForScrollOffset(this.keylineStateList);
        detachAndScrapAttachedViews(recycler);
        fill(recycler, state);
        this.lastItemCount = getItemCount();
    }

    public void onLayoutCompleted(RecyclerView.State state) {
        super.onLayoutCompleted(state);
        if (getChildCount() == 0) {
            this.currentFillStartPosition = 0;
        } else {
            this.currentFillStartPosition = getPosition(getChildAt(0));
        }
        validateChildOrderIfDebugging();
    }

    public boolean requestChildRectangleOnScreen(@NonNull RecyclerView recyclerView, @NonNull View view, @NonNull Rect rect, boolean z2, boolean z3) {
        int smallestScrollOffsetToFocalKeyline;
        if (this.keylineStateList == null || (smallestScrollOffsetToFocalKeyline = getSmallestScrollOffsetToFocalKeyline(getPosition(view), getKeylineStateForPosition(getPosition(view)))) == 0) {
            return false;
        }
        scrollBy(recyclerView, getSmallestScrollOffsetToFocalKeyline(getPosition(view), this.keylineStateList.getShiftedState((float) (this.scrollOffset + calculateShouldScrollBy(smallestScrollOffsetToFocalKeyline, this.scrollOffset, this.minScroll, this.maxScroll)), (float) this.minScroll, (float) this.maxScroll)));
        return true;
    }

    public int scrollHorizontallyBy(int i3, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (canScrollHorizontally()) {
            return scrollBy(i3, recycler, state);
        }
        return 0;
    }

    public void scrollToPosition(int i3) {
        this.currentEstimatedPosition = i3;
        if (this.keylineStateList != null) {
            this.scrollOffset = getScrollOffsetForPosition(i3, getKeylineStateForPosition(i3));
            this.currentFillStartPosition = MathUtils.clamp(i3, 0, Math.max(0, getItemCount() - 1));
            updateCurrentKeylineStateForScrollOffset(this.keylineStateList);
            requestLayout();
        }
    }

    public int scrollVerticallyBy(int i3, RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (canScrollVertically()) {
            return scrollBy(i3, recycler, state);
        }
        return 0;
    }

    public void setCarouselAlignment(int i3) {
        this.carouselAlignment = i3;
        refreshKeylineState();
    }

    public void setCarouselStrategy(@NonNull CarouselStrategy carouselStrategy2) {
        this.carouselStrategy = carouselStrategy2;
        refreshKeylineState();
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setDebuggingEnabled(@NonNull RecyclerView recyclerView, boolean z2) {
        this.isDebuggingEnabled = z2;
        recyclerView.removeItemDecoration(this.debugItemDecoration);
        if (z2) {
            recyclerView.addItemDecoration(this.debugItemDecoration);
        }
        recyclerView.invalidateItemDecorations();
    }

    public void setOrientation(int i3) {
        if (i3 == 0 || i3 == 1) {
            assertNotInLayoutOrScroll((String) null);
            CarouselOrientationHelper carouselOrientationHelper = this.orientationHelper;
            if (carouselOrientationHelper == null || i3 != carouselOrientationHelper.orientation) {
                this.orientationHelper = CarouselOrientationHelper.createOrientationHelper(this, i3);
                refreshKeylineState();
                return;
            }
            return;
        }
        throw new IllegalArgumentException(a.k("invalid orientation:", i3));
    }

    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int i3) {
        AnonymousClass1 r2 = new LinearSmoothScroller(recyclerView.getContext()) {
            public int calculateDxToMakeVisible(View view, int i3) {
                if (CarouselLayoutManager.this.keylineStateList == null || !CarouselLayoutManager.this.isHorizontal()) {
                    return 0;
                }
                CarouselLayoutManager carouselLayoutManager = CarouselLayoutManager.this;
                return carouselLayoutManager.calculateScrollDeltaToMakePositionVisible(carouselLayoutManager.getPosition(view));
            }

            public int calculateDyToMakeVisible(View view, int i3) {
                if (CarouselLayoutManager.this.keylineStateList == null || CarouselLayoutManager.this.isHorizontal()) {
                    return 0;
                }
                CarouselLayoutManager carouselLayoutManager = CarouselLayoutManager.this;
                return carouselLayoutManager.calculateScrollDeltaToMakePositionVisible(carouselLayoutManager.getPosition(view));
            }

            @Nullable
            public PointF computeScrollVectorForPosition(int i3) {
                return CarouselLayoutManager.this.computeScrollVectorForPosition(i3);
            }
        };
        r2.setTargetPosition(i3);
        startSmoothScroll(r2);
    }

    public CarouselLayoutManager(@NonNull CarouselStrategy carouselStrategy2) {
        this(carouselStrategy2, 0);
    }

    public CarouselLayoutManager(@NonNull CarouselStrategy carouselStrategy2, int i3) {
        this.isDebuggingEnabled = false;
        this.debugItemDecoration = new DebugItemDecoration();
        this.currentFillStartPosition = 0;
        this.recyclerViewSizeChangeListener = new a(this);
        this.currentEstimatedPosition = -1;
        this.carouselAlignment = 0;
        setCarouselStrategy(carouselStrategy2);
        setOrientation(i3);
    }

    private int scrollBy(int i3, RecyclerView.Recycler recycler, RecyclerView.State state) {
        float f2;
        if (getChildCount() == 0 || i3 == 0) {
            return 0;
        }
        if (this.keylineStateList == null) {
            recalculateKeylineStateList(recycler);
        }
        int calculateShouldScrollBy = calculateShouldScrollBy(i3, this.scrollOffset, this.minScroll, this.maxScroll);
        this.scrollOffset += calculateShouldScrollBy;
        updateCurrentKeylineStateForScrollOffset(this.keylineStateList);
        float itemSize = this.currentKeylineState.getItemSize() / 2.0f;
        float calculateChildStartForFill = calculateChildStartForFill(getPosition(getChildAt(0)));
        Rect rect = new Rect();
        if (isLayoutRtl()) {
            f2 = this.currentKeylineState.getLastFocalKeyline().locOffset;
        } else {
            f2 = this.currentKeylineState.getFirstFocalKeyline().locOffset;
        }
        float f3 = Float.MAX_VALUE;
        for (int i4 = 0; i4 < getChildCount(); i4++) {
            View childAt = getChildAt(i4);
            float abs = Math.abs(f2 - offsetChild(childAt, calculateChildStartForFill, itemSize, rect));
            if (childAt != null && abs < f3) {
                this.currentEstimatedPosition = getPosition(childAt);
                f3 = abs;
            }
            calculateChildStartForFill = addEnd(calculateChildStartForFill, this.currentKeylineState.getItemSize());
        }
        fill(recycler, state);
        return calculateShouldScrollBy;
    }

    @SuppressLint({"UnknownNullness"})
    public CarouselLayoutManager(Context context, AttributeSet attributeSet, int i3, int i4) {
        this.isDebuggingEnabled = false;
        this.debugItemDecoration = new DebugItemDecoration();
        this.currentFillStartPosition = 0;
        this.recyclerViewSizeChangeListener = new a(this);
        this.currentEstimatedPosition = -1;
        this.carouselAlignment = 0;
        setCarouselStrategy(new MultiBrowseCarouselStrategy());
        setCarouselAttributes(context, attributeSet);
    }
}
