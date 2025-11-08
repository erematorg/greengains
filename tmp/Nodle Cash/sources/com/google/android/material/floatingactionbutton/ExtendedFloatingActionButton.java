package com.google.android.material.floatingactionbutton;

import A.a;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Property;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.AnimatorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;
import com.google.android.material.animation.MotionSpec;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.internal.DescendantOffsetUtils;
import java.util.List;

public class ExtendedFloatingActionButton extends MaterialButton implements CoordinatorLayout.AttachedBehavior {
    private static final int ANIM_STATE_HIDING = 1;
    private static final int ANIM_STATE_NONE = 0;
    private static final int ANIM_STATE_SHOWING = 2;
    private static final int DEF_STYLE_RES = R.style.Widget_MaterialComponents_ExtendedFloatingActionButton_Icon;
    private static final int EXTEND = 3;
    private static final int EXTEND_STRATEGY_AUTO = 0;
    private static final int EXTEND_STRATEGY_MATCH_PARENT = 2;
    private static final int EXTEND_STRATEGY_WRAP_CONTENT = 1;
    static final Property<View, Float> HEIGHT;
    private static final int HIDE = 1;
    static final Property<View, Float> PADDING_END;
    static final Property<View, Float> PADDING_START;
    private static final int SHOW = 0;
    private static final int SHRINK = 2;
    static final Property<View, Float> WIDTH;
    /* access modifiers changed from: private */
    public int animState;
    private boolean animateShowBeforeLayout;
    @NonNull
    private final CoordinatorLayout.Behavior<ExtendedFloatingActionButton> behavior;
    private final AnimatorTracker changeVisibilityTracker;
    private final int collapsedSize;
    @NonNull
    private final MotionStrategy extendStrategy;
    private final int extendStrategyType;
    /* access modifiers changed from: private */
    public int extendedPaddingEnd;
    /* access modifiers changed from: private */
    public int extendedPaddingStart;
    private final MotionStrategy hideStrategy;
    /* access modifiers changed from: private */
    public boolean isExtended;
    /* access modifiers changed from: private */
    public boolean isTransforming;
    /* access modifiers changed from: private */
    public int originalHeight;
    @NonNull
    protected ColorStateList originalTextCsl;
    /* access modifiers changed from: private */
    public int originalWidth;
    private final MotionStrategy showStrategy;
    @NonNull
    private final MotionStrategy shrinkStrategy;

    public class ChangeSizeStrategy extends BaseMotionStrategy {
        private final boolean extending;
        private final Size size;

        public ChangeSizeStrategy(AnimatorTracker animatorTracker, Size size2, boolean z2) {
            super(ExtendedFloatingActionButton.this, animatorTracker);
            this.size = size2;
            this.extending = z2;
        }

        @NonNull
        public AnimatorSet createAnimator() {
            MotionSpec currentMotionSpec = getCurrentMotionSpec();
            if (currentMotionSpec.hasPropertyValues("width")) {
                PropertyValuesHolder[] propertyValues = currentMotionSpec.getPropertyValues("width");
                propertyValues[0].setFloatValues(new float[]{(float) ExtendedFloatingActionButton.this.getWidth(), (float) this.size.getWidth()});
                currentMotionSpec.setPropertyValues("width", propertyValues);
            }
            if (currentMotionSpec.hasPropertyValues("height")) {
                PropertyValuesHolder[] propertyValues2 = currentMotionSpec.getPropertyValues("height");
                propertyValues2[0].setFloatValues(new float[]{(float) ExtendedFloatingActionButton.this.getHeight(), (float) this.size.getHeight()});
                currentMotionSpec.setPropertyValues("height", propertyValues2);
            }
            if (currentMotionSpec.hasPropertyValues("paddingStart")) {
                PropertyValuesHolder[] propertyValues3 = currentMotionSpec.getPropertyValues("paddingStart");
                propertyValues3[0].setFloatValues(new float[]{(float) ViewCompat.getPaddingStart(ExtendedFloatingActionButton.this), (float) this.size.getPaddingStart()});
                currentMotionSpec.setPropertyValues("paddingStart", propertyValues3);
            }
            if (currentMotionSpec.hasPropertyValues("paddingEnd")) {
                PropertyValuesHolder[] propertyValues4 = currentMotionSpec.getPropertyValues("paddingEnd");
                propertyValues4[0].setFloatValues(new float[]{(float) ViewCompat.getPaddingEnd(ExtendedFloatingActionButton.this), (float) this.size.getPaddingEnd()});
                currentMotionSpec.setPropertyValues("paddingEnd", propertyValues4);
            }
            if (currentMotionSpec.hasPropertyValues("labelOpacity")) {
                PropertyValuesHolder[] propertyValues5 = currentMotionSpec.getPropertyValues("labelOpacity");
                boolean z2 = this.extending;
                float f2 = 1.0f;
                float f3 = z2 ? 0.0f : 1.0f;
                if (!z2) {
                    f2 = 0.0f;
                }
                propertyValues5[0].setFloatValues(new float[]{f3, f2});
                currentMotionSpec.setPropertyValues("labelOpacity", propertyValues5);
            }
            return super.createAnimator(currentMotionSpec);
        }

        public int getDefaultMotionSpecResource() {
            return this.extending ? R.animator.mtrl_extended_fab_change_size_expand_motion_spec : R.animator.mtrl_extended_fab_change_size_collapse_motion_spec;
        }

        public void onAnimationEnd() {
            super.onAnimationEnd();
            boolean unused = ExtendedFloatingActionButton.this.isTransforming = false;
            ExtendedFloatingActionButton.this.setHorizontallyScrolling(false);
            ViewGroup.LayoutParams layoutParams = ExtendedFloatingActionButton.this.getLayoutParams();
            if (layoutParams != null) {
                layoutParams.width = this.size.getLayoutParams().width;
                layoutParams.height = this.size.getLayoutParams().height;
            }
        }

        public void onAnimationStart(Animator animator) {
            super.onAnimationStart(animator);
            boolean unused = ExtendedFloatingActionButton.this.isExtended = this.extending;
            boolean unused2 = ExtendedFloatingActionButton.this.isTransforming = true;
            ExtendedFloatingActionButton.this.setHorizontallyScrolling(true);
        }

        public void onChange(@Nullable OnChangedCallback onChangedCallback) {
            if (onChangedCallback != null) {
                if (this.extending) {
                    onChangedCallback.onExtended(ExtendedFloatingActionButton.this);
                } else {
                    onChangedCallback.onShrunken(ExtendedFloatingActionButton.this);
                }
            }
        }

        public void performNow() {
            boolean unused = ExtendedFloatingActionButton.this.isExtended = this.extending;
            ViewGroup.LayoutParams layoutParams = ExtendedFloatingActionButton.this.getLayoutParams();
            if (layoutParams != null) {
                if (!this.extending) {
                    int unused2 = ExtendedFloatingActionButton.this.originalWidth = layoutParams.width;
                    int unused3 = ExtendedFloatingActionButton.this.originalHeight = layoutParams.height;
                }
                layoutParams.width = this.size.getLayoutParams().width;
                layoutParams.height = this.size.getLayoutParams().height;
                ViewCompat.setPaddingRelative(ExtendedFloatingActionButton.this, this.size.getPaddingStart(), ExtendedFloatingActionButton.this.getPaddingTop(), this.size.getPaddingEnd(), ExtendedFloatingActionButton.this.getPaddingBottom());
                ExtendedFloatingActionButton.this.requestLayout();
            }
        }

        public boolean shouldCancel() {
            return this.extending == ExtendedFloatingActionButton.this.isExtended || ExtendedFloatingActionButton.this.getIcon() == null || TextUtils.isEmpty(ExtendedFloatingActionButton.this.getText());
        }
    }

    public static class ExtendedFloatingActionButtonBehavior<T extends ExtendedFloatingActionButton> extends CoordinatorLayout.Behavior<T> {
        private static final boolean AUTO_HIDE_DEFAULT = false;
        private static final boolean AUTO_SHRINK_DEFAULT = true;
        private boolean autoHideEnabled;
        private boolean autoShrinkEnabled;
        @Nullable
        private OnChangedCallback internalAutoHideCallback;
        @Nullable
        private OnChangedCallback internalAutoShrinkCallback;
        private Rect tmpRect;

        public ExtendedFloatingActionButtonBehavior() {
            this.autoHideEnabled = false;
            this.autoShrinkEnabled = true;
        }

        private static boolean isBottomSheet(@NonNull View view) {
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            if (layoutParams instanceof CoordinatorLayout.LayoutParams) {
                return ((CoordinatorLayout.LayoutParams) layoutParams).getBehavior() instanceof BottomSheetBehavior;
            }
            return false;
        }

        private boolean shouldUpdateVisibility(@NonNull View view, @NonNull ExtendedFloatingActionButton extendedFloatingActionButton) {
            return (this.autoHideEnabled || this.autoShrinkEnabled) && ((CoordinatorLayout.LayoutParams) extendedFloatingActionButton.getLayoutParams()).getAnchorId() == view.getId();
        }

        private boolean updateFabVisibilityForAppBarLayout(CoordinatorLayout coordinatorLayout, @NonNull AppBarLayout appBarLayout, @NonNull ExtendedFloatingActionButton extendedFloatingActionButton) {
            if (!shouldUpdateVisibility(appBarLayout, extendedFloatingActionButton)) {
                return false;
            }
            if (this.tmpRect == null) {
                this.tmpRect = new Rect();
            }
            Rect rect = this.tmpRect;
            DescendantOffsetUtils.getDescendantRect(coordinatorLayout, appBarLayout, rect);
            if (rect.bottom <= appBarLayout.getMinimumHeightForVisibleOverlappingContent()) {
                shrinkOrHide(extendedFloatingActionButton);
                return true;
            }
            extendOrShow(extendedFloatingActionButton);
            return true;
        }

        private boolean updateFabVisibilityForBottomSheet(@NonNull View view, @NonNull ExtendedFloatingActionButton extendedFloatingActionButton) {
            if (!shouldUpdateVisibility(view, extendedFloatingActionButton)) {
                return false;
            }
            if (view.getTop() < (extendedFloatingActionButton.getHeight() / 2) + ((CoordinatorLayout.LayoutParams) extendedFloatingActionButton.getLayoutParams()).topMargin) {
                shrinkOrHide(extendedFloatingActionButton);
                return true;
            }
            extendOrShow(extendedFloatingActionButton);
            return true;
        }

        public void extendOrShow(@NonNull ExtendedFloatingActionButton extendedFloatingActionButton) {
            boolean z2 = this.autoShrinkEnabled;
            extendedFloatingActionButton.performMotion(z2 ? 3 : 0, z2 ? this.internalAutoShrinkCallback : this.internalAutoHideCallback);
        }

        public boolean isAutoHideEnabled() {
            return this.autoHideEnabled;
        }

        public boolean isAutoShrinkEnabled() {
            return this.autoShrinkEnabled;
        }

        public void onAttachedToLayoutParams(@NonNull CoordinatorLayout.LayoutParams layoutParams) {
            if (layoutParams.dodgeInsetEdges == 0) {
                layoutParams.dodgeInsetEdges = 80;
            }
        }

        public void setAutoHideEnabled(boolean z2) {
            this.autoHideEnabled = z2;
        }

        public void setAutoShrinkEnabled(boolean z2) {
            this.autoShrinkEnabled = z2;
        }

        @VisibleForTesting
        public void setInternalAutoHideCallback(@Nullable OnChangedCallback onChangedCallback) {
            this.internalAutoHideCallback = onChangedCallback;
        }

        @VisibleForTesting
        public void setInternalAutoShrinkCallback(@Nullable OnChangedCallback onChangedCallback) {
            this.internalAutoShrinkCallback = onChangedCallback;
        }

        public void shrinkOrHide(@NonNull ExtendedFloatingActionButton extendedFloatingActionButton) {
            boolean z2 = this.autoShrinkEnabled;
            extendedFloatingActionButton.performMotion(z2 ? 2 : 1, z2 ? this.internalAutoShrinkCallback : this.internalAutoHideCallback);
        }

        public boolean getInsetDodgeRect(@NonNull CoordinatorLayout coordinatorLayout, @NonNull ExtendedFloatingActionButton extendedFloatingActionButton, @NonNull Rect rect) {
            return super.getInsetDodgeRect(coordinatorLayout, extendedFloatingActionButton, rect);
        }

        public boolean onDependentViewChanged(CoordinatorLayout coordinatorLayout, @NonNull ExtendedFloatingActionButton extendedFloatingActionButton, View view) {
            if (view instanceof AppBarLayout) {
                updateFabVisibilityForAppBarLayout(coordinatorLayout, (AppBarLayout) view, extendedFloatingActionButton);
                return false;
            } else if (!isBottomSheet(view)) {
                return false;
            } else {
                updateFabVisibilityForBottomSheet(view, extendedFloatingActionButton);
                return false;
            }
        }

        public boolean onLayoutChild(@NonNull CoordinatorLayout coordinatorLayout, @NonNull ExtendedFloatingActionButton extendedFloatingActionButton, int i3) {
            List<View> dependencies = coordinatorLayout.getDependencies(extendedFloatingActionButton);
            int size = dependencies.size();
            for (int i4 = 0; i4 < size; i4++) {
                View view = dependencies.get(i4);
                if (!(view instanceof AppBarLayout)) {
                    if (isBottomSheet(view) && updateFabVisibilityForBottomSheet(view, extendedFloatingActionButton)) {
                        break;
                    }
                } else if (updateFabVisibilityForAppBarLayout(coordinatorLayout, (AppBarLayout) view, extendedFloatingActionButton)) {
                    break;
                }
            }
            coordinatorLayout.onLayoutChild(extendedFloatingActionButton, i3);
            return true;
        }

        public ExtendedFloatingActionButtonBehavior(@NonNull Context context, @Nullable AttributeSet attributeSet) {
            super(context, attributeSet);
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ExtendedFloatingActionButton_Behavior_Layout);
            this.autoHideEnabled = obtainStyledAttributes.getBoolean(R.styleable.ExtendedFloatingActionButton_Behavior_Layout_behavior_autoHide, false);
            this.autoShrinkEnabled = obtainStyledAttributes.getBoolean(R.styleable.ExtendedFloatingActionButton_Behavior_Layout_behavior_autoShrink, true);
            obtainStyledAttributes.recycle();
        }
    }

    public class HideStrategy extends BaseMotionStrategy {
        private boolean isCancelled;

        public HideStrategy(AnimatorTracker animatorTracker) {
            super(ExtendedFloatingActionButton.this, animatorTracker);
        }

        public int getDefaultMotionSpecResource() {
            return R.animator.mtrl_extended_fab_hide_motion_spec;
        }

        public void onAnimationCancel() {
            super.onAnimationCancel();
            this.isCancelled = true;
        }

        public void onAnimationEnd() {
            super.onAnimationEnd();
            int unused = ExtendedFloatingActionButton.this.animState = 0;
            if (!this.isCancelled) {
                ExtendedFloatingActionButton.this.setVisibility(8);
            }
        }

        public void onAnimationStart(Animator animator) {
            super.onAnimationStart(animator);
            this.isCancelled = false;
            ExtendedFloatingActionButton.this.setVisibility(0);
            int unused = ExtendedFloatingActionButton.this.animState = 1;
        }

        public void onChange(@Nullable OnChangedCallback onChangedCallback) {
            if (onChangedCallback != null) {
                onChangedCallback.onHidden(ExtendedFloatingActionButton.this);
            }
        }

        public void performNow() {
            ExtendedFloatingActionButton.this.setVisibility(8);
        }

        public boolean shouldCancel() {
            return ExtendedFloatingActionButton.this.isOrWillBeHidden();
        }
    }

    public static abstract class OnChangedCallback {
        public void onExtended(ExtendedFloatingActionButton extendedFloatingActionButton) {
        }

        public void onHidden(ExtendedFloatingActionButton extendedFloatingActionButton) {
        }

        public void onShown(ExtendedFloatingActionButton extendedFloatingActionButton) {
        }

        public void onShrunken(ExtendedFloatingActionButton extendedFloatingActionButton) {
        }
    }

    public class ShowStrategy extends BaseMotionStrategy {
        public ShowStrategy(AnimatorTracker animatorTracker) {
            super(ExtendedFloatingActionButton.this, animatorTracker);
        }

        public int getDefaultMotionSpecResource() {
            return R.animator.mtrl_extended_fab_show_motion_spec;
        }

        public void onAnimationEnd() {
            super.onAnimationEnd();
            int unused = ExtendedFloatingActionButton.this.animState = 0;
        }

        public void onAnimationStart(Animator animator) {
            super.onAnimationStart(animator);
            ExtendedFloatingActionButton.this.setVisibility(0);
            int unused = ExtendedFloatingActionButton.this.animState = 2;
        }

        public void onChange(@Nullable OnChangedCallback onChangedCallback) {
            if (onChangedCallback != null) {
                onChangedCallback.onShown(ExtendedFloatingActionButton.this);
            }
        }

        public void performNow() {
            ExtendedFloatingActionButton.this.setVisibility(0);
            ExtendedFloatingActionButton.this.setAlpha(1.0f);
            ExtendedFloatingActionButton.this.setScaleY(1.0f);
            ExtendedFloatingActionButton.this.setScaleX(1.0f);
        }

        public boolean shouldCancel() {
            return ExtendedFloatingActionButton.this.isOrWillBeShown();
        }
    }

    public interface Size {
        int getHeight();

        ViewGroup.LayoutParams getLayoutParams();

        int getPaddingEnd();

        int getPaddingStart();

        int getWidth();
    }

    static {
        Class<Float> cls = Float.class;
        WIDTH = new Property<View, Float>(cls, "width") {
            @NonNull
            public Float get(@NonNull View view) {
                return Float.valueOf((float) view.getLayoutParams().width);
            }

            public void set(@NonNull View view, @NonNull Float f2) {
                view.getLayoutParams().width = f2.intValue();
                view.requestLayout();
            }
        };
        HEIGHT = new Property<View, Float>(cls, "height") {
            @NonNull
            public Float get(@NonNull View view) {
                return Float.valueOf((float) view.getLayoutParams().height);
            }

            public void set(@NonNull View view, @NonNull Float f2) {
                view.getLayoutParams().height = f2.intValue();
                view.requestLayout();
            }
        };
        PADDING_START = new Property<View, Float>(cls, "paddingStart") {
            @NonNull
            public Float get(@NonNull View view) {
                return Float.valueOf((float) ViewCompat.getPaddingStart(view));
            }

            public void set(@NonNull View view, @NonNull Float f2) {
                ViewCompat.setPaddingRelative(view, f2.intValue(), view.getPaddingTop(), ViewCompat.getPaddingEnd(view), view.getPaddingBottom());
            }
        };
        PADDING_END = new Property<View, Float>(cls, "paddingEnd") {
            @NonNull
            public Float get(@NonNull View view) {
                return Float.valueOf((float) ViewCompat.getPaddingEnd(view));
            }

            public void set(@NonNull View view, @NonNull Float f2) {
                ViewCompat.setPaddingRelative(view, ViewCompat.getPaddingStart(view), view.getPaddingTop(), f2.intValue(), view.getPaddingBottom());
            }
        };
    }

    public ExtendedFloatingActionButton(@NonNull Context context) {
        this(context, (AttributeSet) null);
    }

    private Size getSizeFromExtendStrategyType(int i3) {
        final AnonymousClass2 r02 = new Size() {
            public int getHeight() {
                return ExtendedFloatingActionButton.this.getMeasuredHeight();
            }

            public ViewGroup.LayoutParams getLayoutParams() {
                return new ViewGroup.LayoutParams(-2, -2);
            }

            public int getPaddingEnd() {
                return ExtendedFloatingActionButton.this.extendedPaddingEnd;
            }

            public int getPaddingStart() {
                return ExtendedFloatingActionButton.this.extendedPaddingStart;
            }

            public int getWidth() {
                int measuredWidth = ExtendedFloatingActionButton.this.getMeasuredWidth() - (ExtendedFloatingActionButton.this.getCollapsedPadding() * 2);
                return ExtendedFloatingActionButton.this.extendedPaddingEnd + ExtendedFloatingActionButton.this.extendedPaddingStart + measuredWidth;
            }
        };
        final AnonymousClass3 r12 = new Size() {
            /* JADX WARNING: Code restructure failed: missing block: B:14:0x0047, code lost:
                r3 = (android.view.ViewGroup.MarginLayoutParams) r3.this$0.getLayoutParams();
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public int getHeight() {
                /*
                    r3 = this;
                    com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton r0 = com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.this
                    int r0 = r0.originalHeight
                    r1 = -1
                    r2 = -2
                    if (r0 != r1) goto L_0x005f
                    com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton r0 = com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.this
                    android.view.ViewParent r0 = r0.getParent()
                    boolean r0 = r0 instanceof android.view.View
                    if (r0 != 0) goto L_0x001b
                    com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$Size r3 = r0
                    int r3 = r3.getHeight()
                    return r3
                L_0x001b:
                    com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton r0 = com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.this
                    android.view.ViewParent r0 = r0.getParent()
                    android.view.View r0 = (android.view.View) r0
                    android.view.ViewGroup$LayoutParams r1 = r0.getLayoutParams()
                    if (r1 == 0) goto L_0x0034
                    int r1 = r1.height
                    if (r1 != r2) goto L_0x0034
                    com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$Size r3 = r0
                    int r3 = r3.getHeight()
                    return r3
                L_0x0034:
                    int r1 = r0.getPaddingTop()
                    int r2 = r0.getPaddingBottom()
                    int r2 = r2 + r1
                    com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton r1 = com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.this
                    android.view.ViewGroup$LayoutParams r1 = r1.getLayoutParams()
                    boolean r1 = r1 instanceof android.view.ViewGroup.MarginLayoutParams
                    if (r1 == 0) goto L_0x0057
                    com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton r3 = com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.this
                    android.view.ViewGroup$LayoutParams r3 = r3.getLayoutParams()
                    android.view.ViewGroup$MarginLayoutParams r3 = (android.view.ViewGroup.MarginLayoutParams) r3
                    if (r3 == 0) goto L_0x0057
                    int r1 = r3.topMargin
                    int r3 = r3.bottomMargin
                    int r1 = r1 + r3
                    goto L_0x0058
                L_0x0057:
                    r1 = 0
                L_0x0058:
                    int r3 = r0.getHeight()
                    int r3 = r3 - r1
                    int r3 = r3 - r2
                    return r3
                L_0x005f:
                    com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton r0 = com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.this
                    int r0 = r0.originalHeight
                    if (r0 == 0) goto L_0x0077
                    com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton r0 = com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.this
                    int r0 = r0.originalHeight
                    if (r0 != r2) goto L_0x0070
                    goto L_0x0077
                L_0x0070:
                    com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton r3 = com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.this
                    int r3 = r3.originalHeight
                    return r3
                L_0x0077:
                    com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$Size r3 = r0
                    int r3 = r3.getHeight()
                    return r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.AnonymousClass3.getHeight():int");
            }

            public ViewGroup.LayoutParams getLayoutParams() {
                return new ViewGroup.LayoutParams(-1, ExtendedFloatingActionButton.this.originalHeight == 0 ? -2 : ExtendedFloatingActionButton.this.originalHeight);
            }

            public int getPaddingEnd() {
                return ExtendedFloatingActionButton.this.extendedPaddingEnd;
            }

            public int getPaddingStart() {
                return ExtendedFloatingActionButton.this.extendedPaddingStart;
            }

            /* JADX WARNING: Code restructure failed: missing block: B:12:0x003e, code lost:
                r3 = (android.view.ViewGroup.MarginLayoutParams) r3.this$0.getLayoutParams();
             */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public int getWidth() {
                /*
                    r3 = this;
                    com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton r0 = com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.this
                    android.view.ViewParent r0 = r0.getParent()
                    boolean r0 = r0 instanceof android.view.View
                    if (r0 != 0) goto L_0x0011
                    com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$Size r3 = r0
                    int r3 = r3.getWidth()
                    return r3
                L_0x0011:
                    com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton r0 = com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.this
                    android.view.ViewParent r0 = r0.getParent()
                    android.view.View r0 = (android.view.View) r0
                    android.view.ViewGroup$LayoutParams r1 = r0.getLayoutParams()
                    if (r1 == 0) goto L_0x002b
                    int r1 = r1.width
                    r2 = -2
                    if (r1 != r2) goto L_0x002b
                    com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$Size r3 = r0
                    int r3 = r3.getWidth()
                    return r3
                L_0x002b:
                    int r1 = r0.getPaddingLeft()
                    int r2 = r0.getPaddingRight()
                    int r2 = r2 + r1
                    com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton r1 = com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.this
                    android.view.ViewGroup$LayoutParams r1 = r1.getLayoutParams()
                    boolean r1 = r1 instanceof android.view.ViewGroup.MarginLayoutParams
                    if (r1 == 0) goto L_0x004e
                    com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton r3 = com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.this
                    android.view.ViewGroup$LayoutParams r3 = r3.getLayoutParams()
                    android.view.ViewGroup$MarginLayoutParams r3 = (android.view.ViewGroup.MarginLayoutParams) r3
                    if (r3 == 0) goto L_0x004e
                    int r1 = r3.leftMargin
                    int r3 = r3.rightMargin
                    int r1 = r1 + r3
                    goto L_0x004f
                L_0x004e:
                    r1 = 0
                L_0x004f:
                    int r3 = r0.getWidth()
                    int r3 = r3 - r1
                    int r3 = r3 - r2
                    return r3
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.AnonymousClass3.getWidth():int");
            }
        };
        return i3 != 1 ? i3 != 2 ? new Size() {
            public int getHeight() {
                return ExtendedFloatingActionButton.this.originalHeight == -1 ? r12.getHeight() : (ExtendedFloatingActionButton.this.originalHeight == 0 || ExtendedFloatingActionButton.this.originalHeight == -2) ? r02.getHeight() : ExtendedFloatingActionButton.this.originalHeight;
            }

            public ViewGroup.LayoutParams getLayoutParams() {
                int i3 = -2;
                int access$300 = ExtendedFloatingActionButton.this.originalWidth == 0 ? -2 : ExtendedFloatingActionButton.this.originalWidth;
                if (ExtendedFloatingActionButton.this.originalHeight != 0) {
                    i3 = ExtendedFloatingActionButton.this.originalHeight;
                }
                return new ViewGroup.LayoutParams(access$300, i3);
            }

            public int getPaddingEnd() {
                return ExtendedFloatingActionButton.this.extendedPaddingEnd;
            }

            public int getPaddingStart() {
                return ExtendedFloatingActionButton.this.extendedPaddingStart;
            }

            public int getWidth() {
                return ExtendedFloatingActionButton.this.originalWidth == -1 ? r12.getWidth() : (ExtendedFloatingActionButton.this.originalWidth == 0 || ExtendedFloatingActionButton.this.originalWidth == -2) ? r02.getWidth() : ExtendedFloatingActionButton.this.originalWidth;
            }
        } : r12 : r02;
    }

    /* access modifiers changed from: private */
    public boolean isOrWillBeHidden() {
        return getVisibility() == 0 ? this.animState == 1 : this.animState != 2;
    }

    /* access modifiers changed from: private */
    public boolean isOrWillBeShown() {
        return getVisibility() != 0 ? this.animState == 2 : this.animState != 1;
    }

    /* access modifiers changed from: private */
    public void performMotion(int i3, @Nullable final OnChangedCallback onChangedCallback) {
        final MotionStrategy motionStrategy;
        if (i3 == 0) {
            motionStrategy = this.showStrategy;
        } else if (i3 == 1) {
            motionStrategy = this.hideStrategy;
        } else if (i3 == 2) {
            motionStrategy = this.shrinkStrategy;
        } else if (i3 == 3) {
            motionStrategy = this.extendStrategy;
        } else {
            throw new IllegalStateException(a.k("Unknown strategy type: ", i3));
        }
        if (!motionStrategy.shouldCancel()) {
            if (!shouldAnimateVisibilityChange()) {
                motionStrategy.performNow();
                motionStrategy.onChange(onChangedCallback);
                return;
            }
            if (i3 == 2) {
                ViewGroup.LayoutParams layoutParams = getLayoutParams();
                if (layoutParams != null) {
                    this.originalWidth = layoutParams.width;
                    this.originalHeight = layoutParams.height;
                } else {
                    this.originalWidth = getWidth();
                    this.originalHeight = getHeight();
                }
            }
            measure(0, 0);
            AnimatorSet createAnimator = motionStrategy.createAnimator();
            createAnimator.addListener(new AnimatorListenerAdapter() {
                private boolean cancelled;

                public void onAnimationCancel(Animator animator) {
                    this.cancelled = true;
                    motionStrategy.onAnimationCancel();
                }

                public void onAnimationEnd(Animator animator) {
                    motionStrategy.onAnimationEnd();
                    if (!this.cancelled) {
                        motionStrategy.onChange(onChangedCallback);
                    }
                }

                public void onAnimationStart(Animator animator) {
                    motionStrategy.onAnimationStart(animator);
                    this.cancelled = false;
                }
            });
            for (Animator.AnimatorListener addListener : motionStrategy.getListeners()) {
                createAnimator.addListener(addListener);
            }
            createAnimator.start();
        }
    }

    private void saveOriginalTextCsl() {
        this.originalTextCsl = getTextColors();
    }

    private boolean shouldAnimateVisibilityChange() {
        return (ViewCompat.isLaidOut(this) || (!isOrWillBeShown() && this.animateShowBeforeLayout)) && !isInEditMode();
    }

    public void addOnExtendAnimationListener(@NonNull Animator.AnimatorListener animatorListener) {
        this.extendStrategy.addAnimationListener(animatorListener);
    }

    public void addOnHideAnimationListener(@NonNull Animator.AnimatorListener animatorListener) {
        this.hideStrategy.addAnimationListener(animatorListener);
    }

    public void addOnShowAnimationListener(@NonNull Animator.AnimatorListener animatorListener) {
        this.showStrategy.addAnimationListener(animatorListener);
    }

    public void addOnShrinkAnimationListener(@NonNull Animator.AnimatorListener animatorListener) {
        this.shrinkStrategy.addAnimationListener(animatorListener);
    }

    public void extend() {
        performMotion(3, (OnChangedCallback) null);
    }

    @NonNull
    public CoordinatorLayout.Behavior<ExtendedFloatingActionButton> getBehavior() {
        return this.behavior;
    }

    public int getCollapsedPadding() {
        return (getCollapsedSize() - getIconSize()) / 2;
    }

    @VisibleForTesting
    public int getCollapsedSize() {
        int i3 = this.collapsedSize;
        return i3 < 0 ? (Math.min(ViewCompat.getPaddingStart(this), ViewCompat.getPaddingEnd(this)) * 2) + getIconSize() : i3;
    }

    @Nullable
    public MotionSpec getExtendMotionSpec() {
        return this.extendStrategy.getMotionSpec();
    }

    @Nullable
    public MotionSpec getHideMotionSpec() {
        return this.hideStrategy.getMotionSpec();
    }

    @Nullable
    public MotionSpec getShowMotionSpec() {
        return this.showStrategy.getMotionSpec();
    }

    @Nullable
    public MotionSpec getShrinkMotionSpec() {
        return this.shrinkStrategy.getMotionSpec();
    }

    public void hide() {
        performMotion(1, (OnChangedCallback) null);
    }

    public final boolean isExtended() {
        return this.isExtended;
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.isExtended && TextUtils.isEmpty(getText()) && getIcon() != null) {
            this.isExtended = false;
            this.shrinkStrategy.performNow();
        }
    }

    public void removeOnExtendAnimationListener(@NonNull Animator.AnimatorListener animatorListener) {
        this.extendStrategy.removeAnimationListener(animatorListener);
    }

    public void removeOnHideAnimationListener(@NonNull Animator.AnimatorListener animatorListener) {
        this.hideStrategy.removeAnimationListener(animatorListener);
    }

    public void removeOnShowAnimationListener(@NonNull Animator.AnimatorListener animatorListener) {
        this.showStrategy.removeAnimationListener(animatorListener);
    }

    public void removeOnShrinkAnimationListener(@NonNull Animator.AnimatorListener animatorListener) {
        this.shrinkStrategy.removeAnimationListener(animatorListener);
    }

    public void setAnimateShowBeforeLayout(boolean z2) {
        this.animateShowBeforeLayout = z2;
    }

    public void setExtendMotionSpec(@Nullable MotionSpec motionSpec) {
        this.extendStrategy.setMotionSpec(motionSpec);
    }

    public void setExtendMotionSpecResource(@AnimatorRes int i3) {
        setExtendMotionSpec(MotionSpec.createFromResource(getContext(), i3));
    }

    public void setExtended(boolean z2) {
        if (this.isExtended != z2) {
            MotionStrategy motionStrategy = z2 ? this.extendStrategy : this.shrinkStrategy;
            if (!motionStrategy.shouldCancel()) {
                motionStrategy.performNow();
            }
        }
    }

    public void setHideMotionSpec(@Nullable MotionSpec motionSpec) {
        this.hideStrategy.setMotionSpec(motionSpec);
    }

    public void setHideMotionSpecResource(@AnimatorRes int i3) {
        setHideMotionSpec(MotionSpec.createFromResource(getContext(), i3));
    }

    public void setPadding(int i3, int i4, int i5, int i6) {
        super.setPadding(i3, i4, i5, i6);
        if (this.isExtended && !this.isTransforming) {
            this.extendedPaddingStart = ViewCompat.getPaddingStart(this);
            this.extendedPaddingEnd = ViewCompat.getPaddingEnd(this);
        }
    }

    public void setPaddingRelative(int i3, int i4, int i5, int i6) {
        super.setPaddingRelative(i3, i4, i5, i6);
        if (this.isExtended && !this.isTransforming) {
            this.extendedPaddingStart = i3;
            this.extendedPaddingEnd = i5;
        }
    }

    public void setShowMotionSpec(@Nullable MotionSpec motionSpec) {
        this.showStrategy.setMotionSpec(motionSpec);
    }

    public void setShowMotionSpecResource(@AnimatorRes int i3) {
        setShowMotionSpec(MotionSpec.createFromResource(getContext(), i3));
    }

    public void setShrinkMotionSpec(@Nullable MotionSpec motionSpec) {
        this.shrinkStrategy.setMotionSpec(motionSpec);
    }

    public void setShrinkMotionSpecResource(@AnimatorRes int i3) {
        setShrinkMotionSpec(MotionSpec.createFromResource(getContext(), i3));
    }

    public void setTextColor(int i3) {
        super.setTextColor(i3);
        saveOriginalTextCsl();
    }

    public void show() {
        performMotion(0, (OnChangedCallback) null);
    }

    public void shrink() {
        performMotion(2, (OnChangedCallback) null);
    }

    public void silentlyUpdateTextColor(@NonNull ColorStateList colorStateList) {
        super.setTextColor(colorStateList);
    }

    public ExtendedFloatingActionButton(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.extendedFloatingActionButtonStyle);
    }

    public void extend(@NonNull OnChangedCallback onChangedCallback) {
        performMotion(3, onChangedCallback);
    }

    public void hide(@NonNull OnChangedCallback onChangedCallback) {
        performMotion(1, onChangedCallback);
    }

    public void show(@NonNull OnChangedCallback onChangedCallback) {
        performMotion(0, onChangedCallback);
    }

    public void shrink(@NonNull OnChangedCallback onChangedCallback) {
        performMotion(2, onChangedCallback);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ExtendedFloatingActionButton(@androidx.annotation.NonNull android.content.Context r17, @androidx.annotation.Nullable android.util.AttributeSet r18, int r19) {
        /*
            r16 = this;
            r0 = r16
            r7 = r18
            r8 = r19
            int r9 = DEF_STYLE_RES
            r1 = r17
            android.content.Context r1 = com.google.android.material.theme.overlay.MaterialThemeOverlay.wrap(r1, r7, r8, r9)
            r0.<init>(r1, r7, r8)
            r10 = 0
            r0.animState = r10
            com.google.android.material.floatingactionbutton.AnimatorTracker r1 = new com.google.android.material.floatingactionbutton.AnimatorTracker
            r1.<init>()
            r0.changeVisibilityTracker = r1
            com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$ShowStrategy r11 = new com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$ShowStrategy
            r11.<init>(r1)
            r0.showStrategy = r11
            com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$HideStrategy r12 = new com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$HideStrategy
            r12.<init>(r1)
            r0.hideStrategy = r12
            r13 = 1
            r0.isExtended = r13
            r0.isTransforming = r10
            r0.animateShowBeforeLayout = r10
            android.content.Context r14 = r16.getContext()
            com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$ExtendedFloatingActionButtonBehavior r1 = new com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$ExtendedFloatingActionButtonBehavior
            r1.<init>(r14, r7)
            r0.behavior = r1
            int[] r3 = com.google.android.material.R.styleable.ExtendedFloatingActionButton
            int[] r6 = new int[r10]
            r1 = r14
            r2 = r18
            r4 = r19
            r5 = r9
            android.content.res.TypedArray r1 = com.google.android.material.internal.ThemeEnforcement.obtainStyledAttributes(r1, r2, r3, r4, r5, r6)
            int r2 = com.google.android.material.R.styleable.ExtendedFloatingActionButton_showMotionSpec
            com.google.android.material.animation.MotionSpec r2 = com.google.android.material.animation.MotionSpec.createFromAttribute(r14, r1, r2)
            int r3 = com.google.android.material.R.styleable.ExtendedFloatingActionButton_hideMotionSpec
            com.google.android.material.animation.MotionSpec r3 = com.google.android.material.animation.MotionSpec.createFromAttribute(r14, r1, r3)
            int r4 = com.google.android.material.R.styleable.ExtendedFloatingActionButton_extendMotionSpec
            com.google.android.material.animation.MotionSpec r4 = com.google.android.material.animation.MotionSpec.createFromAttribute(r14, r1, r4)
            int r5 = com.google.android.material.R.styleable.ExtendedFloatingActionButton_shrinkMotionSpec
            com.google.android.material.animation.MotionSpec r5 = com.google.android.material.animation.MotionSpec.createFromAttribute(r14, r1, r5)
            int r6 = com.google.android.material.R.styleable.ExtendedFloatingActionButton_collapsedSize
            r15 = -1
            int r6 = r1.getDimensionPixelSize(r6, r15)
            r0.collapsedSize = r6
            int r6 = com.google.android.material.R.styleable.ExtendedFloatingActionButton_extendStrategy
            int r6 = r1.getInt(r6, r13)
            r0.extendStrategyType = r6
            int r15 = androidx.core.view.ViewCompat.getPaddingStart(r16)
            r0.extendedPaddingStart = r15
            int r15 = androidx.core.view.ViewCompat.getPaddingEnd(r16)
            r0.extendedPaddingEnd = r15
            com.google.android.material.floatingactionbutton.AnimatorTracker r15 = new com.google.android.material.floatingactionbutton.AnimatorTracker
            r15.<init>()
            com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$ChangeSizeStrategy r10 = new com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$ChangeSizeStrategy
            com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$Size r6 = r0.getSizeFromExtendStrategyType(r6)
            r10.<init>(r15, r6, r13)
            r0.extendStrategy = r10
            com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$ChangeSizeStrategy r6 = new com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$ChangeSizeStrategy
            com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$1 r13 = new com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton$1
            r13.<init>()
            r7 = 0
            r6.<init>(r15, r13, r7)
            r0.shrinkStrategy = r6
            r11.setMotionSpec(r2)
            r12.setMotionSpec(r3)
            r10.setMotionSpec(r4)
            r6.setMotionSpec(r5)
            r1.recycle()
            com.google.android.material.shape.CornerSize r1 = com.google.android.material.shape.ShapeAppearanceModel.PILL
            r2 = r18
            com.google.android.material.shape.ShapeAppearanceModel$Builder r1 = com.google.android.material.shape.ShapeAppearanceModel.builder((android.content.Context) r14, (android.util.AttributeSet) r2, (int) r8, (int) r9, (com.google.android.material.shape.CornerSize) r1)
            com.google.android.material.shape.ShapeAppearanceModel r1 = r1.build()
            r0.setShapeAppearanceModel(r1)
            r16.saveOriginalTextCsl()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public void setTextColor(@NonNull ColorStateList colorStateList) {
        super.setTextColor(colorStateList);
        saveOriginalTextCsl();
    }
}
