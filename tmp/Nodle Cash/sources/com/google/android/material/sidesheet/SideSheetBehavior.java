package com.google.android.material.sidesheet;

import A.a;
import W.c;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import androidx.activity.BackEventCompat;
import androidx.annotation.GravityInt;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.camera.camera2.internal.C0118y;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.math.MathUtils;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.core.view.accessibility.AccessibilityViewCommand;
import androidx.customview.view.AbsSavedState;
import androidx.customview.widget.ViewDragHelper;
import c2.C0251h;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.motion.MaterialSideContainerBackHelper;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.ShapeAppearanceModel;
import java.lang.ref.WeakReference;
import java.util.LinkedHashSet;
import java.util.Set;

public class SideSheetBehavior<V extends View> extends CoordinatorLayout.Behavior<V> implements Sheet<SideSheetCallback> {
    private static final int DEFAULT_ACCESSIBILITY_PANE_TITLE = R.string.side_sheet_accessibility_pane_title;
    private static final int DEF_STYLE_RES = R.style.Widget_Material3_SideSheet;
    private static final float HIDE_FRICTION = 0.1f;
    private static final float HIDE_THRESHOLD = 0.5f;
    private static final int NO_MAX_SIZE = -1;
    static final int SIGNIFICANT_VEL_THRESHOLD = 500;
    @Nullable
    private ColorStateList backgroundTint;
    @NonNull
    private final Set<SideSheetCallback> callbacks = new LinkedHashSet();
    /* access modifiers changed from: private */
    public int childWidth;
    @IdRes
    private int coplanarSiblingViewId = -1;
    @Nullable
    private WeakReference<View> coplanarSiblingViewRef;
    private final ViewDragHelper.Callback dragCallback = new ViewDragHelper.Callback() {
        public int clampViewPositionHorizontal(@NonNull View view, int i3, int i4) {
            return MathUtils.clamp(i3, SideSheetBehavior.this.sheetDelegate.getMinViewPositionHorizontal(), SideSheetBehavior.this.sheetDelegate.getMaxViewPositionHorizontal());
        }

        public int clampViewPositionVertical(@NonNull View view, int i3, int i4) {
            return view.getTop();
        }

        public int getViewHorizontalDragRange(@NonNull View view) {
            return SideSheetBehavior.this.getInnerMargin() + SideSheetBehavior.this.childWidth;
        }

        public void onViewDragStateChanged(int i3) {
            if (i3 == 1 && SideSheetBehavior.this.draggable) {
                SideSheetBehavior.this.setStateInternal(1);
            }
        }

        public void onViewPositionChanged(@NonNull View view, int i3, int i4, int i5, int i6) {
            ViewGroup.MarginLayoutParams marginLayoutParams;
            View coplanarSiblingView = SideSheetBehavior.this.getCoplanarSiblingView();
            if (!(coplanarSiblingView == null || (marginLayoutParams = (ViewGroup.MarginLayoutParams) coplanarSiblingView.getLayoutParams()) == null)) {
                SideSheetBehavior.this.sheetDelegate.updateCoplanarSiblingLayoutParams(marginLayoutParams, view.getLeft(), view.getRight());
                coplanarSiblingView.setLayoutParams(marginLayoutParams);
            }
            SideSheetBehavior.this.dispatchOnSlide(view, i3);
        }

        public void onViewReleased(@NonNull View view, float f2, float f3) {
            int access$500 = SideSheetBehavior.this.calculateTargetStateOnViewReleased(view, f2, f3);
            SideSheetBehavior sideSheetBehavior = SideSheetBehavior.this;
            sideSheetBehavior.startSettling(view, access$500, sideSheetBehavior.shouldSkipSmoothAnimation());
        }

        public boolean tryCaptureView(@NonNull View view, int i3) {
            return (SideSheetBehavior.this.state == 1 || SideSheetBehavior.this.viewRef == null || SideSheetBehavior.this.viewRef.get() != view) ? false : true;
        }
    };
    /* access modifiers changed from: private */
    public boolean draggable = true;
    private float elevation;
    private float hideFriction = 0.1f;
    private boolean ignoreEvents;
    private int initialX;
    private int innerMargin;
    private int lastStableState = 5;
    @Nullable
    private MaterialShapeDrawable materialShapeDrawable;
    private float maximumVelocity;
    private int parentInnerEdge;
    private int parentWidth;
    private ShapeAppearanceModel shapeAppearanceModel;
    /* access modifiers changed from: private */
    public SheetDelegate sheetDelegate;
    @Nullable
    private MaterialSideContainerBackHelper sideContainerBackHelper;
    /* access modifiers changed from: private */
    public int state = 5;
    private final SideSheetBehavior<V>.StateSettlingTracker stateSettlingTracker = new StateSettlingTracker();
    @Nullable
    private VelocityTracker velocityTracker;
    /* access modifiers changed from: private */
    @Nullable
    public ViewDragHelper viewDragHelper;
    /* access modifiers changed from: private */
    @Nullable
    public WeakReference<V> viewRef;

    public static class SavedState extends AbsSavedState {
        public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator<SavedState>() {
            @NonNull
            public SavedState[] newArray(int i3) {
                return new SavedState[i3];
            }

            @NonNull
            public SavedState createFromParcel(@NonNull Parcel parcel, ClassLoader classLoader) {
                return new SavedState(parcel, classLoader);
            }

            @Nullable
            public SavedState createFromParcel(@NonNull Parcel parcel) {
                return new SavedState(parcel, (ClassLoader) null);
            }
        };
        final int state;

        public SavedState(@NonNull Parcel parcel) {
            this(parcel, (ClassLoader) null);
        }

        public void writeToParcel(@NonNull Parcel parcel, int i3) {
            super.writeToParcel(parcel, i3);
            parcel.writeInt(this.state);
        }

        public SavedState(@NonNull Parcel parcel, ClassLoader classLoader) {
            super(parcel, classLoader);
            this.state = parcel.readInt();
        }

        public SavedState(Parcelable parcelable, @NonNull SideSheetBehavior<?> sideSheetBehavior) {
            super(parcelable);
            this.state = sideSheetBehavior.state;
        }
    }

    public class StateSettlingTracker {
        private final Runnable continueSettlingRunnable = new c(this);
        private boolean isContinueSettlingRunnablePosted;
        private int targetState;

        public StateSettlingTracker() {
        }

        /* access modifiers changed from: private */
        public /* synthetic */ void lambda$new$0() {
            this.isContinueSettlingRunnablePosted = false;
            if (SideSheetBehavior.this.viewDragHelper != null && SideSheetBehavior.this.viewDragHelper.continueSettling(true)) {
                continueSettlingToState(this.targetState);
            } else if (SideSheetBehavior.this.state == 2) {
                SideSheetBehavior.this.setStateInternal(this.targetState);
            }
        }

        public void continueSettlingToState(int i3) {
            if (SideSheetBehavior.this.viewRef != null && SideSheetBehavior.this.viewRef.get() != null) {
                this.targetState = i3;
                if (!this.isContinueSettlingRunnablePosted) {
                    ViewCompat.postOnAnimation((View) SideSheetBehavior.this.viewRef.get(), this.continueSettlingRunnable);
                    this.isContinueSettlingRunnablePosted = true;
                }
            }
        }
    }

    public SideSheetBehavior() {
    }

    private int calculateCurrentOffset(int i3, V v2) {
        int i4 = this.state;
        if (i4 == 1 || i4 == 2) {
            return i3 - this.sheetDelegate.getOuterEdge(v2);
        }
        if (i4 == 3) {
            return 0;
        }
        if (i4 == 5) {
            return this.sheetDelegate.getHiddenOffset();
        }
        throw new IllegalStateException("Unexpected value: " + this.state);
    }

    private float calculateDragDistance(float f2, float f3) {
        return Math.abs(f2 - f3);
    }

    /* access modifiers changed from: private */
    public int calculateTargetStateOnViewReleased(@NonNull View view, float f2, float f3) {
        if (isExpandingOutwards(f2)) {
            return 3;
        }
        if (shouldHide(view, f2)) {
            if (!this.sheetDelegate.isSwipeSignificant(f2, f3) && !this.sheetDelegate.isReleasedCloseToInnerEdge(view)) {
                return 3;
            }
        } else if (f2 == 0.0f || !SheetUtils.isSwipeMostlyHorizontal(f2, f3)) {
            int left = view.getLeft();
            if (Math.abs(left - getExpandedOffset()) < Math.abs(left - this.sheetDelegate.getHiddenOffset())) {
                return 3;
            }
        }
        return 5;
    }

    private void clearCoplanarSiblingView() {
        WeakReference<View> weakReference = this.coplanarSiblingViewRef;
        if (weakReference != null) {
            weakReference.clear();
        }
        this.coplanarSiblingViewRef = null;
    }

    private AccessibilityViewCommand createAccessibilityViewCommandForState(int i3) {
        return new C0251h(this, i3);
    }

    private void createMaterialShapeDrawableIfNeeded(@NonNull Context context) {
        if (this.shapeAppearanceModel != null) {
            MaterialShapeDrawable materialShapeDrawable2 = new MaterialShapeDrawable(this.shapeAppearanceModel);
            this.materialShapeDrawable = materialShapeDrawable2;
            materialShapeDrawable2.initializeElevationOverlay(context);
            ColorStateList colorStateList = this.backgroundTint;
            if (colorStateList != null) {
                this.materialShapeDrawable.setFillColor(colorStateList);
                return;
            }
            TypedValue typedValue = new TypedValue();
            context.getTheme().resolveAttribute(16842801, typedValue, true);
            this.materialShapeDrawable.setTint(typedValue.data);
        }
    }

    /* access modifiers changed from: private */
    public void dispatchOnSlide(@NonNull View view, int i3) {
        if (!this.callbacks.isEmpty()) {
            float calculateSlideOffset = this.sheetDelegate.calculateSlideOffset(i3);
            for (SideSheetCallback onSlide : this.callbacks) {
                onSlide.onSlide(view, calculateSlideOffset);
            }
        }
    }

    private void ensureAccessibilityPaneTitleIsSet(View view) {
        if (ViewCompat.getAccessibilityPaneTitle(view) == null) {
            ViewCompat.setAccessibilityPaneTitle(view, view.getResources().getString(DEFAULT_ACCESSIBILITY_PANE_TITLE));
        }
    }

    @NonNull
    public static <V extends View> SideSheetBehavior<V> from(@NonNull V v2) {
        ViewGroup.LayoutParams layoutParams = v2.getLayoutParams();
        if (layoutParams instanceof CoordinatorLayout.LayoutParams) {
            CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) layoutParams).getBehavior();
            if (behavior instanceof SideSheetBehavior) {
                return (SideSheetBehavior) behavior;
            }
            throw new IllegalArgumentException("The view is not associated with SideSheetBehavior");
        }
        throw new IllegalArgumentException("The view is not a child of CoordinatorLayout");
    }

    private int getChildMeasureSpec(int i3, int i4, int i5, int i6) {
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(i3, i4, i6);
        if (i5 == -1) {
            return childMeasureSpec;
        }
        int mode = View.MeasureSpec.getMode(childMeasureSpec);
        int size = View.MeasureSpec.getSize(childMeasureSpec);
        if (mode == 1073741824) {
            return View.MeasureSpec.makeMeasureSpec(Math.min(size, i5), 1073741824);
        }
        if (size != 0) {
            i5 = Math.min(size, i5);
        }
        return View.MeasureSpec.makeMeasureSpec(i5, Integer.MIN_VALUE);
    }

    @Nullable
    private ValueAnimator.AnimatorUpdateListener getCoplanarFinishAnimatorUpdateListener() {
        ViewGroup.MarginLayoutParams marginLayoutParams;
        View coplanarSiblingView = getCoplanarSiblingView();
        if (coplanarSiblingView == null || (marginLayoutParams = (ViewGroup.MarginLayoutParams) coplanarSiblingView.getLayoutParams()) == null) {
            return null;
        }
        return new b(this, marginLayoutParams, this.sheetDelegate.getCoplanarSiblingAdjacentMargin(marginLayoutParams), coplanarSiblingView);
    }

    @GravityInt
    private int getGravityFromSheetEdge() {
        SheetDelegate sheetDelegate2 = this.sheetDelegate;
        return (sheetDelegate2 == null || sheetDelegate2.getSheetEdge() == 0) ? 5 : 3;
    }

    @Nullable
    private CoordinatorLayout.LayoutParams getViewLayoutParams() {
        View view;
        WeakReference<V> weakReference = this.viewRef;
        if (weakReference == null || (view = (View) weakReference.get()) == null || !(view.getLayoutParams() instanceof CoordinatorLayout.LayoutParams)) {
            return null;
        }
        return (CoordinatorLayout.LayoutParams) view.getLayoutParams();
    }

    private boolean hasLeftMargin() {
        CoordinatorLayout.LayoutParams viewLayoutParams = getViewLayoutParams();
        return viewLayoutParams != null && viewLayoutParams.leftMargin > 0;
    }

    private boolean hasRightMargin() {
        CoordinatorLayout.LayoutParams viewLayoutParams = getViewLayoutParams();
        return viewLayoutParams != null && viewLayoutParams.rightMargin > 0;
    }

    private boolean isDraggedFarEnough(@NonNull MotionEvent motionEvent) {
        return shouldHandleDraggingWithHelper() && calculateDragDistance((float) this.initialX, motionEvent.getX()) > ((float) this.viewDragHelper.getTouchSlop());
    }

    private boolean isExpandingOutwards(float f2) {
        return this.sheetDelegate.isExpandingOutwards(f2);
    }

    private boolean isLayingOut(@NonNull V v2) {
        ViewParent parent = v2.getParent();
        return parent != null && parent.isLayoutRequested() && ViewCompat.isAttachedToWindow(v2);
    }

    private boolean isSettling(View view, int i3, boolean z2) {
        int outerEdgeOffsetForState = getOuterEdgeOffsetForState(i3);
        ViewDragHelper viewDragHelper2 = getViewDragHelper();
        return viewDragHelper2 != null && (!z2 ? viewDragHelper2.smoothSlideViewTo(view, outerEdgeOffsetForState, view.getTop()) : viewDragHelper2.settleCapturedViewAt(outerEdgeOffsetForState, view.getTop()));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ boolean lambda$createAccessibilityViewCommandForState$2(int i3, View view, AccessibilityViewCommand.CommandArguments commandArguments) {
        setState(i3);
        return true;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getCoplanarFinishAnimatorUpdateListener$1(ViewGroup.MarginLayoutParams marginLayoutParams, int i3, View view, ValueAnimator valueAnimator) {
        this.sheetDelegate.updateCoplanarSiblingAdjacentMargin(marginLayoutParams, AnimationUtils.lerp(i3, 0, valueAnimator.getAnimatedFraction()));
        view.requestLayout();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$setState$0(int i3) {
        View view = (View) this.viewRef.get();
        if (view != null) {
            startSettling(view, i3, false);
        }
    }

    private void maybeAssignCoplanarSiblingViewBasedId(@NonNull CoordinatorLayout coordinatorLayout) {
        int i3;
        View findViewById;
        if (this.coplanarSiblingViewRef == null && (i3 = this.coplanarSiblingViewId) != -1 && (findViewById = coordinatorLayout.findViewById(i3)) != null) {
            this.coplanarSiblingViewRef = new WeakReference<>(findViewById);
        }
    }

    private void replaceAccessibilityActionForState(V v2, AccessibilityNodeInfoCompat.AccessibilityActionCompat accessibilityActionCompat, int i3) {
        ViewCompat.replaceAccessibilityAction(v2, accessibilityActionCompat, (CharSequence) null, createAccessibilityViewCommandForState(i3));
    }

    private void resetVelocity() {
        VelocityTracker velocityTracker2 = this.velocityTracker;
        if (velocityTracker2 != null) {
            velocityTracker2.recycle();
            this.velocityTracker = null;
        }
    }

    private void runAfterLayout(@NonNull V v2, Runnable runnable) {
        if (isLayingOut(v2)) {
            v2.post(runnable);
        } else {
            runnable.run();
        }
    }

    private void setSheetEdge(@NonNull V v2, int i3) {
        setSheetEdge(GravityCompat.getAbsoluteGravity(((CoordinatorLayout.LayoutParams) v2.getLayoutParams()).gravity, i3) == 3 ? 1 : 0);
    }

    private boolean shouldHandleDraggingWithHelper() {
        return this.viewDragHelper != null && (this.draggable || this.state == 1);
    }

    private boolean shouldInterceptTouchEvent(@NonNull V v2) {
        return (v2.isShown() || ViewCompat.getAccessibilityPaneTitle(v2) != null) && this.draggable;
    }

    /* access modifiers changed from: private */
    public void startSettling(View view, int i3, boolean z2) {
        if (isSettling(view, i3, z2)) {
            setStateInternal(2);
            this.stateSettlingTracker.continueSettlingToState(i3);
            return;
        }
        setStateInternal(i3);
    }

    private void updateAccessibilityActions() {
        View view;
        WeakReference<V> weakReference = this.viewRef;
        if (weakReference != null && (view = (View) weakReference.get()) != null) {
            ViewCompat.removeAccessibilityAction(view, 262144);
            ViewCompat.removeAccessibilityAction(view, 1048576);
            if (this.state != 5) {
                replaceAccessibilityActionForState(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_DISMISS, 5);
            }
            if (this.state != 3) {
                replaceAccessibilityActionForState(view, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_EXPAND, 3);
            }
        }
    }

    private void updateCoplanarSiblingBackProgress() {
        ViewGroup.MarginLayoutParams marginLayoutParams;
        WeakReference<V> weakReference = this.viewRef;
        if (weakReference != null && weakReference.get() != null) {
            View view = (View) this.viewRef.get();
            View coplanarSiblingView = getCoplanarSiblingView();
            if (coplanarSiblingView != null && (marginLayoutParams = (ViewGroup.MarginLayoutParams) coplanarSiblingView.getLayoutParams()) != null) {
                float scaleX = view.getScaleX();
                this.sheetDelegate.updateCoplanarSiblingAdjacentMargin(marginLayoutParams, (int) ((scaleX * ((float) this.childWidth)) + ((float) this.innerMargin)));
                coplanarSiblingView.requestLayout();
            }
        }
    }

    private void updateMaterialShapeDrawable(@NonNull ShapeAppearanceModel shapeAppearanceModel2) {
        MaterialShapeDrawable materialShapeDrawable2 = this.materialShapeDrawable;
        if (materialShapeDrawable2 != null) {
            materialShapeDrawable2.setShapeAppearanceModel(shapeAppearanceModel2);
        }
    }

    private void updateSheetVisibility(@NonNull View view) {
        int i3 = this.state == 5 ? 4 : 0;
        if (view.getVisibility() != i3) {
            view.setVisibility(i3);
        }
    }

    public void cancelBackProgress() {
        MaterialSideContainerBackHelper materialSideContainerBackHelper = this.sideContainerBackHelper;
        if (materialSideContainerBackHelper != null) {
            materialSideContainerBackHelper.cancelBackProgress();
        }
    }

    public void expand() {
        setState(3);
    }

    @VisibleForTesting
    @Nullable
    public MaterialSideContainerBackHelper getBackHelper() {
        return this.sideContainerBackHelper;
    }

    public int getChildWidth() {
        return this.childWidth;
    }

    @Nullable
    public View getCoplanarSiblingView() {
        WeakReference<View> weakReference = this.coplanarSiblingViewRef;
        if (weakReference != null) {
            return weakReference.get();
        }
        return null;
    }

    public int getExpandedOffset() {
        return this.sheetDelegate.getExpandedOffset();
    }

    public float getHideFriction() {
        return this.hideFriction;
    }

    public float getHideThreshold() {
        return 0.5f;
    }

    public int getInnerMargin() {
        return this.innerMargin;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public int getLastStableState() {
        return this.lastStableState;
    }

    public int getOuterEdgeOffsetForState(int i3) {
        if (i3 == 3) {
            return getExpandedOffset();
        }
        if (i3 == 5) {
            return this.sheetDelegate.getHiddenOffset();
        }
        throw new IllegalArgumentException(a.k("Invalid state to get outer edge offset: ", i3));
    }

    public int getParentInnerEdge() {
        return this.parentInnerEdge;
    }

    public int getParentWidth() {
        return this.parentWidth;
    }

    public int getSignificantVelocityThreshold() {
        return 500;
    }

    public int getState() {
        return this.state;
    }

    @Nullable
    public ViewDragHelper getViewDragHelper() {
        return this.viewDragHelper;
    }

    public float getXVelocity() {
        VelocityTracker velocityTracker2 = this.velocityTracker;
        if (velocityTracker2 == null) {
            return 0.0f;
        }
        velocityTracker2.computeCurrentVelocity(1000, this.maximumVelocity);
        return this.velocityTracker.getXVelocity();
    }

    public void handleBackInvoked() {
        MaterialSideContainerBackHelper materialSideContainerBackHelper = this.sideContainerBackHelper;
        if (materialSideContainerBackHelper != null) {
            BackEventCompat onHandleBackInvoked = materialSideContainerBackHelper.onHandleBackInvoked();
            if (onHandleBackInvoked == null || Build.VERSION.SDK_INT < 34) {
                setState(5);
            } else {
                this.sideContainerBackHelper.finishBackProgress(onHandleBackInvoked, getGravityFromSheetEdge(), new AnimatorListenerAdapter() {
                    public void onAnimationEnd(Animator animator) {
                        SideSheetBehavior.this.setStateInternal(5);
                        if (SideSheetBehavior.this.viewRef != null && SideSheetBehavior.this.viewRef.get() != null) {
                            ((View) SideSheetBehavior.this.viewRef.get()).requestLayout();
                        }
                    }
                }, getCoplanarFinishAnimatorUpdateListener());
            }
        }
    }

    public void hide() {
        setState(5);
    }

    public boolean isDraggable() {
        return this.draggable;
    }

    public void onAttachedToLayoutParams(@NonNull CoordinatorLayout.LayoutParams layoutParams) {
        super.onAttachedToLayoutParams(layoutParams);
        this.viewRef = null;
        this.viewDragHelper = null;
        this.sideContainerBackHelper = null;
    }

    public void onDetachedFromLayoutParams() {
        super.onDetachedFromLayoutParams();
        this.viewRef = null;
        this.viewDragHelper = null;
        this.sideContainerBackHelper = null;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:22:0x003d, code lost:
        r2 = r2.viewDragHelper;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean onInterceptTouchEvent(@androidx.annotation.NonNull androidx.coordinatorlayout.widget.CoordinatorLayout r3, @androidx.annotation.NonNull V r4, @androidx.annotation.NonNull android.view.MotionEvent r5) {
        /*
            r2 = this;
            boolean r3 = r2.shouldInterceptTouchEvent(r4)
            r4 = 1
            r0 = 0
            if (r3 != 0) goto L_0x000b
            r2.ignoreEvents = r4
            return r0
        L_0x000b:
            int r3 = r5.getActionMasked()
            if (r3 != 0) goto L_0x0014
            r2.resetVelocity()
        L_0x0014:
            android.view.VelocityTracker r1 = r2.velocityTracker
            if (r1 != 0) goto L_0x001e
            android.view.VelocityTracker r1 = android.view.VelocityTracker.obtain()
            r2.velocityTracker = r1
        L_0x001e:
            android.view.VelocityTracker r1 = r2.velocityTracker
            r1.addMovement(r5)
            if (r3 == 0) goto L_0x0032
            if (r3 == r4) goto L_0x002b
            r1 = 3
            if (r3 == r1) goto L_0x002b
            goto L_0x0039
        L_0x002b:
            boolean r3 = r2.ignoreEvents
            if (r3 == 0) goto L_0x0039
            r2.ignoreEvents = r0
            return r0
        L_0x0032:
            float r3 = r5.getX()
            int r3 = (int) r3
            r2.initialX = r3
        L_0x0039:
            boolean r3 = r2.ignoreEvents
            if (r3 != 0) goto L_0x0048
            androidx.customview.widget.ViewDragHelper r2 = r2.viewDragHelper
            if (r2 == 0) goto L_0x0048
            boolean r2 = r2.shouldInterceptTouchEvent(r5)
            if (r2 == 0) goto L_0x0048
            goto L_0x0049
        L_0x0048:
            r4 = r0
        L_0x0049:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.sidesheet.SideSheetBehavior.onInterceptTouchEvent(androidx.coordinatorlayout.widget.CoordinatorLayout, android.view.View, android.view.MotionEvent):boolean");
    }

    public boolean onLayoutChild(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v2, int i3) {
        if (ViewCompat.getFitsSystemWindows(coordinatorLayout) && !ViewCompat.getFitsSystemWindows(v2)) {
            v2.setFitsSystemWindows(true);
        }
        if (this.viewRef == null) {
            this.viewRef = new WeakReference<>(v2);
            this.sideContainerBackHelper = new MaterialSideContainerBackHelper(v2);
            MaterialShapeDrawable materialShapeDrawable2 = this.materialShapeDrawable;
            if (materialShapeDrawable2 != null) {
                ViewCompat.setBackground(v2, materialShapeDrawable2);
                MaterialShapeDrawable materialShapeDrawable3 = this.materialShapeDrawable;
                float f2 = this.elevation;
                if (f2 == -1.0f) {
                    f2 = ViewCompat.getElevation(v2);
                }
                materialShapeDrawable3.setElevation(f2);
            } else {
                ColorStateList colorStateList = this.backgroundTint;
                if (colorStateList != null) {
                    ViewCompat.setBackgroundTintList(v2, colorStateList);
                }
            }
            updateSheetVisibility(v2);
            updateAccessibilityActions();
            if (ViewCompat.getImportantForAccessibility(v2) == 0) {
                ViewCompat.setImportantForAccessibility(v2, 1);
            }
            ensureAccessibilityPaneTitleIsSet(v2);
        }
        setSheetEdge(v2, i3);
        if (this.viewDragHelper == null) {
            this.viewDragHelper = ViewDragHelper.create(coordinatorLayout, this.dragCallback);
        }
        int outerEdge = this.sheetDelegate.getOuterEdge(v2);
        coordinatorLayout.onLayoutChild(v2, i3);
        this.parentWidth = coordinatorLayout.getWidth();
        this.parentInnerEdge = this.sheetDelegate.getParentInnerEdge(coordinatorLayout);
        this.childWidth = v2.getWidth();
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) v2.getLayoutParams();
        this.innerMargin = marginLayoutParams != null ? this.sheetDelegate.calculateInnerMargin(marginLayoutParams) : 0;
        ViewCompat.offsetLeftAndRight(v2, calculateCurrentOffset(outerEdge, v2));
        maybeAssignCoplanarSiblingViewBasedId(coordinatorLayout);
        for (SheetCallback next : this.callbacks) {
            if (next instanceof SideSheetCallback) {
                ((SideSheetCallback) next).onLayout(v2);
            }
        }
        return true;
    }

    public boolean onMeasureChild(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v2, int i3, int i4, int i5, int i6) {
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) v2.getLayoutParams();
        v2.measure(getChildMeasureSpec(i3, coordinatorLayout.getPaddingRight() + coordinatorLayout.getPaddingLeft() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin + i4, -1, marginLayoutParams.width), getChildMeasureSpec(i5, coordinatorLayout.getPaddingBottom() + coordinatorLayout.getPaddingTop() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin + i6, -1, marginLayoutParams.height));
        return true;
    }

    public void onRestoreInstanceState(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v2, @NonNull Parcelable parcelable) {
        SavedState savedState = (SavedState) parcelable;
        if (savedState.getSuperState() != null) {
            super.onRestoreInstanceState(coordinatorLayout, v2, savedState.getSuperState());
        }
        int i3 = savedState.state;
        if (i3 == 1 || i3 == 2) {
            i3 = 5;
        }
        this.state = i3;
        this.lastStableState = i3;
    }

    @NonNull
    public Parcelable onSaveInstanceState(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v2) {
        return new SavedState(super.onSaveInstanceState(coordinatorLayout, v2), (SideSheetBehavior<?>) this);
    }

    public boolean onTouchEvent(@NonNull CoordinatorLayout coordinatorLayout, @NonNull V v2, @NonNull MotionEvent motionEvent) {
        if (!v2.isShown()) {
            return false;
        }
        int actionMasked = motionEvent.getActionMasked();
        if (this.state == 1 && actionMasked == 0) {
            return true;
        }
        if (shouldHandleDraggingWithHelper()) {
            this.viewDragHelper.processTouchEvent(motionEvent);
        }
        if (actionMasked == 0) {
            resetVelocity();
        }
        if (this.velocityTracker == null) {
            this.velocityTracker = VelocityTracker.obtain();
        }
        this.velocityTracker.addMovement(motionEvent);
        if (shouldHandleDraggingWithHelper() && actionMasked == 2 && !this.ignoreEvents && isDraggedFarEnough(motionEvent)) {
            this.viewDragHelper.captureChildView(v2, motionEvent.getPointerId(motionEvent.getActionIndex()));
        }
        return !this.ignoreEvents;
    }

    public void setCoplanarSiblingView(@Nullable View view) {
        this.coplanarSiblingViewId = -1;
        if (view == null) {
            clearCoplanarSiblingView();
            return;
        }
        this.coplanarSiblingViewRef = new WeakReference<>(view);
        WeakReference<V> weakReference = this.viewRef;
        if (weakReference != null) {
            View view2 = (View) weakReference.get();
            if (ViewCompat.isLaidOut(view2)) {
                view2.requestLayout();
            }
        }
    }

    public void setCoplanarSiblingViewId(@IdRes int i3) {
        this.coplanarSiblingViewId = i3;
        clearCoplanarSiblingView();
        WeakReference<V> weakReference = this.viewRef;
        if (weakReference != null) {
            View view = (View) weakReference.get();
            if (i3 != -1 && ViewCompat.isLaidOut(view)) {
                view.requestLayout();
            }
        }
    }

    public void setDraggable(boolean z2) {
        this.draggable = z2;
    }

    public void setHideFriction(float f2) {
        this.hideFriction = f2;
    }

    public void setState(int i3) {
        if (i3 == 1 || i3 == 2) {
            throw new IllegalArgumentException(a.n(new StringBuilder("STATE_"), i3 == 1 ? "DRAGGING" : "SETTLING", " should not be set externally."));
        }
        WeakReference<V> weakReference = this.viewRef;
        if (weakReference == null || weakReference.get() == null) {
            setStateInternal(i3);
        } else {
            runAfterLayout((View) this.viewRef.get(), new c(this, i3, 3));
        }
    }

    public void setStateInternal(int i3) {
        View view;
        if (this.state != i3) {
            this.state = i3;
            if (i3 == 3 || i3 == 5) {
                this.lastStableState = i3;
            }
            WeakReference<V> weakReference = this.viewRef;
            if (weakReference != null && (view = (View) weakReference.get()) != null) {
                updateSheetVisibility(view);
                for (SideSheetCallback onStateChanged : this.callbacks) {
                    onStateChanged.onStateChanged(view, i3);
                }
                updateAccessibilityActions();
            }
        }
    }

    public boolean shouldHide(@NonNull View view, float f2) {
        return this.sheetDelegate.shouldHide(view, f2);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public boolean shouldSkipSmoothAnimation() {
        return true;
    }

    public void startBackProgress(@NonNull BackEventCompat backEventCompat) {
        MaterialSideContainerBackHelper materialSideContainerBackHelper = this.sideContainerBackHelper;
        if (materialSideContainerBackHelper != null) {
            materialSideContainerBackHelper.startBackProgress(backEventCompat);
        }
    }

    public void updateBackProgress(@NonNull BackEventCompat backEventCompat) {
        MaterialSideContainerBackHelper materialSideContainerBackHelper = this.sideContainerBackHelper;
        if (materialSideContainerBackHelper != null) {
            materialSideContainerBackHelper.updateBackProgress(backEventCompat, getGravityFromSheetEdge());
            updateCoplanarSiblingBackProgress();
        }
    }

    public void addCallback(@NonNull SideSheetCallback sideSheetCallback) {
        this.callbacks.add(sideSheetCallback);
    }

    public void removeCallback(@NonNull SideSheetCallback sideSheetCallback) {
        this.callbacks.remove(sideSheetCallback);
    }

    private void setSheetEdge(int i3) {
        SheetDelegate sheetDelegate2 = this.sheetDelegate;
        if (sheetDelegate2 != null && sheetDelegate2.getSheetEdge() == i3) {
            return;
        }
        if (i3 == 0) {
            this.sheetDelegate = new RightSheetDelegate(this);
            if (this.shapeAppearanceModel != null && !hasRightMargin()) {
                ShapeAppearanceModel.Builder builder = this.shapeAppearanceModel.toBuilder();
                builder.setTopRightCornerSize(0.0f).setBottomRightCornerSize(0.0f);
                updateMaterialShapeDrawable(builder.build());
            }
        } else if (i3 == 1) {
            this.sheetDelegate = new LeftSheetDelegate(this);
            if (this.shapeAppearanceModel != null && !hasLeftMargin()) {
                ShapeAppearanceModel.Builder builder2 = this.shapeAppearanceModel.toBuilder();
                builder2.setTopLeftCornerSize(0.0f).setBottomLeftCornerSize(0.0f);
                updateMaterialShapeDrawable(builder2.build());
            }
        } else {
            throw new IllegalArgumentException(C0118y.c(i3, "Invalid sheet edge position value: ", ". Must be 0 or 1."));
        }
    }

    public SideSheetBehavior(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.SideSheetBehavior_Layout);
        int i3 = R.styleable.SideSheetBehavior_Layout_backgroundTint;
        if (obtainStyledAttributes.hasValue(i3)) {
            this.backgroundTint = MaterialResources.getColorStateList(context, obtainStyledAttributes, i3);
        }
        if (obtainStyledAttributes.hasValue(R.styleable.SideSheetBehavior_Layout_shapeAppearance)) {
            this.shapeAppearanceModel = ShapeAppearanceModel.builder(context, attributeSet, 0, DEF_STYLE_RES).build();
        }
        int i4 = R.styleable.SideSheetBehavior_Layout_coplanarSiblingViewId;
        if (obtainStyledAttributes.hasValue(i4)) {
            setCoplanarSiblingViewId(obtainStyledAttributes.getResourceId(i4, -1));
        }
        createMaterialShapeDrawableIfNeeded(context);
        this.elevation = obtainStyledAttributes.getDimension(R.styleable.SideSheetBehavior_Layout_android_elevation, -1.0f);
        setDraggable(obtainStyledAttributes.getBoolean(R.styleable.SideSheetBehavior_Layout_behavior_draggable, true));
        obtainStyledAttributes.recycle();
        this.maximumVelocity = (float) ViewConfiguration.get(context).getScaledMaximumFlingVelocity();
    }
}
