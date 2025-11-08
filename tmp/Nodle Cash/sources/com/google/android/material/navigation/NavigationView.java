package com.google.android.material.navigation;

import A1.C0219a;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Pair;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import androidx.activity.BackEventCompat;
import androidx.annotation.DimenRes;
import androidx.annotation.Dimension;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.RestrictTo;
import androidx.annotation.StyleRes;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.view.SupportMenuInflater;
import androidx.appcompat.view.menu.MenuItemImpl;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.content.ContextCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.customview.view.AbsSavedState;
import androidx.drawerlayout.widget.DrawerLayout;
import com.google.android.material.R;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.internal.NavigationMenu;
import com.google.android.material.internal.NavigationMenuPresenter;
import com.google.android.material.internal.ScrimInsetsFrameLayout;
import com.google.android.material.motion.MaterialBackHandler;
import com.google.android.material.motion.MaterialBackOrchestrator;
import com.google.android.material.motion.MaterialSideContainerBackHelper;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.shape.MaterialShapeUtils;
import com.google.android.material.shape.ShapeAppearanceModel;
import com.google.android.material.shape.ShapeableDelegate;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

public class NavigationView extends ScrimInsetsFrameLayout implements MaterialBackHandler {
    private static final int[] CHECKED_STATE_SET = {16842912};
    private static final int DEF_STYLE_RES = R.style.Widget_Design_NavigationView;
    private static final int[] DISABLED_STATE_SET = {-16842910};
    private static final int PRESENTER_NAVIGATION_VIEW_ID = 1;
    private final DrawerLayout.DrawerListener backDrawerListener;
    /* access modifiers changed from: private */
    public final MaterialBackOrchestrator backOrchestrator;
    private boolean bottomInsetScrimEnabled;
    @Px
    private int drawerLayoutCornerSize;
    private final boolean drawerLayoutCornerSizeBackAnimationEnabled;
    @Px
    private final int drawerLayoutCornerSizeBackAnimationMax;
    OnNavigationItemSelectedListener listener;
    private final int maxWidth;
    @NonNull
    private final NavigationMenu menu;
    private MenuInflater menuInflater;
    private ViewTreeObserver.OnGlobalLayoutListener onGlobalLayoutListener;
    /* access modifiers changed from: private */
    public final NavigationMenuPresenter presenter;
    private final ShapeableDelegate shapeableDelegate;
    private final MaterialSideContainerBackHelper sideContainerBackHelper;
    /* access modifiers changed from: private */
    public final int[] tmpLocation;
    private boolean topInsetScrimEnabled;

    public interface OnNavigationItemSelectedListener {
        boolean onNavigationItemSelected(@NonNull MenuItem menuItem);
    }

    public NavigationView(@NonNull Context context) {
        this(context, (AttributeSet) null);
    }

    @Nullable
    private ColorStateList createDefaultColorStateList(int i3) {
        TypedValue typedValue = new TypedValue();
        if (!getContext().getTheme().resolveAttribute(i3, typedValue, true)) {
            return null;
        }
        ColorStateList colorStateList = AppCompatResources.getColorStateList(getContext(), typedValue.resourceId);
        if (!getContext().getTheme().resolveAttribute(androidx.appcompat.R.attr.colorPrimary, typedValue, true)) {
            return null;
        }
        int i4 = typedValue.data;
        int defaultColor = colorStateList.getDefaultColor();
        int[] iArr = DISABLED_STATE_SET;
        return new ColorStateList(new int[][]{iArr, CHECKED_STATE_SET, FrameLayout.EMPTY_STATE_SET}, new int[]{colorStateList.getColorForState(iArr, defaultColor), i4, defaultColor});
    }

    @NonNull
    private Drawable createDefaultItemBackground(@NonNull TintTypedArray tintTypedArray) {
        return createDefaultItemDrawable(tintTypedArray, MaterialResources.getColorStateList(getContext(), tintTypedArray, R.styleable.NavigationView_itemShapeFillColor));
    }

    @NonNull
    private Drawable createDefaultItemDrawable(@NonNull TintTypedArray tintTypedArray, @Nullable ColorStateList colorStateList) {
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(ShapeAppearanceModel.builder(getContext(), tintTypedArray.getResourceId(R.styleable.NavigationView_itemShapeAppearance, 0), tintTypedArray.getResourceId(R.styleable.NavigationView_itemShapeAppearanceOverlay, 0)).build());
        materialShapeDrawable.setFillColor(colorStateList);
        return new InsetDrawable(materialShapeDrawable, tintTypedArray.getDimensionPixelSize(R.styleable.NavigationView_itemShapeInsetStart, 0), tintTypedArray.getDimensionPixelSize(R.styleable.NavigationView_itemShapeInsetTop, 0), tintTypedArray.getDimensionPixelSize(R.styleable.NavigationView_itemShapeInsetEnd, 0), tintTypedArray.getDimensionPixelSize(R.styleable.NavigationView_itemShapeInsetBottom, 0));
    }

    private MenuInflater getMenuInflater() {
        if (this.menuInflater == null) {
            this.menuInflater = new SupportMenuInflater(getContext());
        }
        return this.menuInflater;
    }

    private boolean hasShapeAppearance(@NonNull TintTypedArray tintTypedArray) {
        return tintTypedArray.hasValue(R.styleable.NavigationView_itemShapeAppearance) || tintTypedArray.hasValue(R.styleable.NavigationView_itemShapeAppearanceOverlay);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$dispatchDraw$0(Canvas canvas) {
        super.dispatchDraw(canvas);
    }

    /* access modifiers changed from: private */
    public void maybeClearCornerSizeAnimationForDrawerLayout() {
        if (this.drawerLayoutCornerSizeBackAnimationEnabled && this.drawerLayoutCornerSize != 0) {
            this.drawerLayoutCornerSize = 0;
            maybeUpdateCornerSizeForDrawerLayout(getWidth(), getHeight());
        }
    }

    private void maybeUpdateCornerSizeForDrawerLayout(@Px int i3, @Px int i4) {
        if ((getParent() instanceof DrawerLayout) && (getLayoutParams() instanceof DrawerLayout.LayoutParams)) {
            if ((this.drawerLayoutCornerSize > 0 || this.drawerLayoutCornerSizeBackAnimationEnabled) && (getBackground() instanceof MaterialShapeDrawable)) {
                boolean z2 = GravityCompat.getAbsoluteGravity(((DrawerLayout.LayoutParams) getLayoutParams()).gravity, ViewCompat.getLayoutDirection(this)) == 3;
                MaterialShapeDrawable materialShapeDrawable = (MaterialShapeDrawable) getBackground();
                ShapeAppearanceModel.Builder allCornerSizes = materialShapeDrawable.getShapeAppearanceModel().toBuilder().setAllCornerSizes((float) this.drawerLayoutCornerSize);
                if (z2) {
                    allCornerSizes.setTopLeftCornerSize(0.0f);
                    allCornerSizes.setBottomLeftCornerSize(0.0f);
                } else {
                    allCornerSizes.setTopRightCornerSize(0.0f);
                    allCornerSizes.setBottomRightCornerSize(0.0f);
                }
                ShapeAppearanceModel build = allCornerSizes.build();
                materialShapeDrawable.setShapeAppearanceModel(build);
                this.shapeableDelegate.onShapeAppearanceChanged(this, build);
                this.shapeableDelegate.onMaskChanged(this, new RectF(0.0f, 0.0f, (float) i3, (float) i4));
                this.shapeableDelegate.setOffsetZeroCornerEdgeBoundsEnabled(this, true);
            }
        }
    }

    @CanIgnoreReturnValue
    private Pair<DrawerLayout, DrawerLayout.LayoutParams> requireDrawerLayoutParent() {
        ViewParent parent = getParent();
        ViewGroup.LayoutParams layoutParams = getLayoutParams();
        if ((parent instanceof DrawerLayout) && (layoutParams instanceof DrawerLayout.LayoutParams)) {
            return new Pair<>((DrawerLayout) parent, (DrawerLayout.LayoutParams) layoutParams);
        }
        throw new IllegalStateException("NavigationView back progress requires the direct parent view to be a DrawerLayout.");
    }

    private void setupInsetScrimsListener() {
        this.onGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
            /* JADX WARNING: Removed duplicated region for block: B:18:0x0061  */
            /* JADX WARNING: Removed duplicated region for block: B:40:? A[RETURN, SYNTHETIC] */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public void onGlobalLayout() {
                /*
                    r6 = this;
                    com.google.android.material.navigation.NavigationView r0 = com.google.android.material.navigation.NavigationView.this
                    int[] r1 = r0.tmpLocation
                    r0.getLocationOnScreen(r1)
                    com.google.android.material.navigation.NavigationView r0 = com.google.android.material.navigation.NavigationView.this
                    int[] r0 = r0.tmpLocation
                    r1 = 1
                    r0 = r0[r1]
                    r2 = 0
                    if (r0 != 0) goto L_0x0017
                    r0 = r1
                    goto L_0x0018
                L_0x0017:
                    r0 = r2
                L_0x0018:
                    com.google.android.material.navigation.NavigationView r3 = com.google.android.material.navigation.NavigationView.this
                    com.google.android.material.internal.NavigationMenuPresenter r3 = r3.presenter
                    r3.setBehindStatusBar(r0)
                    com.google.android.material.navigation.NavigationView r3 = com.google.android.material.navigation.NavigationView.this
                    if (r0 == 0) goto L_0x002d
                    boolean r0 = r3.isTopInsetScrimEnabled()
                    if (r0 == 0) goto L_0x002d
                    r0 = r1
                    goto L_0x002e
                L_0x002d:
                    r0 = r2
                L_0x002e:
                    r3.setDrawTopInsetForeground(r0)
                    com.google.android.material.navigation.NavigationView r0 = com.google.android.material.navigation.NavigationView.this
                    int[] r0 = r0.tmpLocation
                    r0 = r0[r2]
                    if (r0 == 0) goto L_0x004f
                    com.google.android.material.navigation.NavigationView r0 = com.google.android.material.navigation.NavigationView.this
                    int[] r0 = r0.tmpLocation
                    r0 = r0[r2]
                    com.google.android.material.navigation.NavigationView r3 = com.google.android.material.navigation.NavigationView.this
                    int r3 = r3.getWidth()
                    int r3 = r3 + r0
                    if (r3 != 0) goto L_0x004d
                    goto L_0x004f
                L_0x004d:
                    r0 = r2
                    goto L_0x0050
                L_0x004f:
                    r0 = r1
                L_0x0050:
                    com.google.android.material.navigation.NavigationView r3 = com.google.android.material.navigation.NavigationView.this
                    r3.setDrawLeftInsetForeground(r0)
                    com.google.android.material.navigation.NavigationView r0 = com.google.android.material.navigation.NavigationView.this
                    android.content.Context r0 = r0.getContext()
                    android.app.Activity r0 = com.google.android.material.internal.ContextUtils.getActivity(r0)
                    if (r0 == 0) goto L_0x00ca
                    android.graphics.Rect r3 = com.google.android.material.internal.WindowUtils.getCurrentWindowBounds(r0)
                    int r4 = r3.height()
                    com.google.android.material.navigation.NavigationView r5 = com.google.android.material.navigation.NavigationView.this
                    int r5 = r5.getHeight()
                    int r4 = r4 - r5
                    com.google.android.material.navigation.NavigationView r5 = com.google.android.material.navigation.NavigationView.this
                    int[] r5 = r5.tmpLocation
                    r5 = r5[r1]
                    if (r4 != r5) goto L_0x007c
                    r4 = r1
                    goto L_0x007d
                L_0x007c:
                    r4 = r2
                L_0x007d:
                    android.view.Window r0 = r0.getWindow()
                    int r0 = r0.getNavigationBarColor()
                    int r0 = android.graphics.Color.alpha(r0)
                    if (r0 == 0) goto L_0x008d
                    r0 = r1
                    goto L_0x008e
                L_0x008d:
                    r0 = r2
                L_0x008e:
                    com.google.android.material.navigation.NavigationView r5 = com.google.android.material.navigation.NavigationView.this
                    if (r4 == 0) goto L_0x009c
                    if (r0 == 0) goto L_0x009c
                    boolean r0 = r5.isBottomInsetScrimEnabled()
                    if (r0 == 0) goto L_0x009c
                    r0 = r1
                    goto L_0x009d
                L_0x009c:
                    r0 = r2
                L_0x009d:
                    r5.setDrawBottomInsetForeground(r0)
                    int r0 = r3.width()
                    com.google.android.material.navigation.NavigationView r4 = com.google.android.material.navigation.NavigationView.this
                    int[] r4 = r4.tmpLocation
                    r4 = r4[r2]
                    if (r0 == r4) goto L_0x00c5
                    int r0 = r3.width()
                    com.google.android.material.navigation.NavigationView r3 = com.google.android.material.navigation.NavigationView.this
                    int r3 = r3.getWidth()
                    int r0 = r0 - r3
                    com.google.android.material.navigation.NavigationView r3 = com.google.android.material.navigation.NavigationView.this
                    int[] r3 = r3.tmpLocation
                    r3 = r3[r2]
                    if (r0 != r3) goto L_0x00c4
                    goto L_0x00c5
                L_0x00c4:
                    r1 = r2
                L_0x00c5:
                    com.google.android.material.navigation.NavigationView r6 = com.google.android.material.navigation.NavigationView.this
                    r6.setDrawRightInsetForeground(r1)
                L_0x00ca:
                    return
                */
                throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.navigation.NavigationView.AnonymousClass3.onGlobalLayout():void");
            }
        };
        getViewTreeObserver().addOnGlobalLayoutListener(this.onGlobalLayoutListener);
    }

    public void addHeaderView(@NonNull View view) {
        this.presenter.addHeaderView(view);
    }

    public void cancelBackProgress() {
        requireDrawerLayoutParent();
        this.sideContainerBackHelper.cancelBackProgress();
        maybeClearCornerSizeAnimationForDrawerLayout();
    }

    public void dispatchDraw(@NonNull Canvas canvas) {
        this.shapeableDelegate.maybeClip(canvas, new C0219a(this, 21));
    }

    @VisibleForTesting
    public MaterialSideContainerBackHelper getBackHelper() {
        return this.sideContainerBackHelper;
    }

    @Nullable
    public MenuItem getCheckedItem() {
        return this.presenter.getCheckedItem();
    }

    @Px
    public int getDividerInsetEnd() {
        return this.presenter.getDividerInsetEnd();
    }

    @Px
    public int getDividerInsetStart() {
        return this.presenter.getDividerInsetStart();
    }

    public int getHeaderCount() {
        return this.presenter.getHeaderCount();
    }

    public View getHeaderView(int i3) {
        return this.presenter.getHeaderView(i3);
    }

    @Nullable
    public Drawable getItemBackground() {
        return this.presenter.getItemBackground();
    }

    @Dimension
    public int getItemHorizontalPadding() {
        return this.presenter.getItemHorizontalPadding();
    }

    @Dimension
    public int getItemIconPadding() {
        return this.presenter.getItemIconPadding();
    }

    @Nullable
    public ColorStateList getItemIconTintList() {
        return this.presenter.getItemTintList();
    }

    public int getItemMaxLines() {
        return this.presenter.getItemMaxLines();
    }

    @Nullable
    public ColorStateList getItemTextColor() {
        return this.presenter.getItemTextColor();
    }

    @Px
    public int getItemVerticalPadding() {
        return this.presenter.getItemVerticalPadding();
    }

    @NonNull
    public Menu getMenu() {
        return this.menu;
    }

    @Px
    public int getSubheaderInsetEnd() {
        return this.presenter.getSubheaderInsetEnd();
    }

    @Px
    public int getSubheaderInsetStart() {
        return this.presenter.getSubheaderInsetStart();
    }

    public void handleBackInvoked() {
        Pair<DrawerLayout, DrawerLayout.LayoutParams> requireDrawerLayoutParent = requireDrawerLayoutParent();
        DrawerLayout drawerLayout = (DrawerLayout) requireDrawerLayoutParent.first;
        BackEventCompat onHandleBackInvoked = this.sideContainerBackHelper.onHandleBackInvoked();
        if (onHandleBackInvoked == null || Build.VERSION.SDK_INT < 34) {
            drawerLayout.closeDrawer((View) this);
            return;
        }
        this.sideContainerBackHelper.finishBackProgress(onHandleBackInvoked, ((DrawerLayout.LayoutParams) requireDrawerLayoutParent.second).gravity, DrawerLayoutUtils.getScrimCloseAnimatorListener(drawerLayout, this), DrawerLayoutUtils.getScrimCloseAnimatorUpdateListener(drawerLayout));
    }

    public View inflateHeaderView(@LayoutRes int i3) {
        return this.presenter.inflateHeaderView(i3);
    }

    public void inflateMenu(int i3) {
        this.presenter.setUpdateSuspended(true);
        getMenuInflater().inflate(i3, this.menu);
        this.presenter.setUpdateSuspended(false);
        this.presenter.updateMenuView(false);
    }

    public boolean isBottomInsetScrimEnabled() {
        return this.bottomInsetScrimEnabled;
    }

    public boolean isTopInsetScrimEnabled() {
        return this.topInsetScrimEnabled;
    }

    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        MaterialShapeUtils.setParentAbsoluteElevation(this);
        ViewParent parent = getParent();
        if ((parent instanceof DrawerLayout) && this.backOrchestrator.shouldListenForBackCallbacks()) {
            DrawerLayout drawerLayout = (DrawerLayout) parent;
            drawerLayout.removeDrawerListener(this.backDrawerListener);
            drawerLayout.addDrawerListener(this.backDrawerListener);
            if (drawerLayout.isDrawerOpen((View) this)) {
                this.backOrchestrator.startListeningForBackCallbacksWithPriorityOverlay();
            }
        }
    }

    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeOnGlobalLayoutListener(this.onGlobalLayoutListener);
        ViewParent parent = getParent();
        if (parent instanceof DrawerLayout) {
            ((DrawerLayout) parent).removeDrawerListener(this.backDrawerListener);
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void onInsetsChanged(@NonNull WindowInsetsCompat windowInsetsCompat) {
        this.presenter.dispatchApplyWindowInsets(windowInsetsCompat);
    }

    public void onMeasure(int i3, int i4) {
        int mode = View.MeasureSpec.getMode(i3);
        if (mode == Integer.MIN_VALUE) {
            i3 = View.MeasureSpec.makeMeasureSpec(Math.min(View.MeasureSpec.getSize(i3), this.maxWidth), 1073741824);
        } else if (mode == 0) {
            i3 = View.MeasureSpec.makeMeasureSpec(this.maxWidth, 1073741824);
        }
        super.onMeasure(i3, i4);
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (!(parcelable instanceof SavedState)) {
            super.onRestoreInstanceState(parcelable);
            return;
        }
        SavedState savedState = (SavedState) parcelable;
        super.onRestoreInstanceState(savedState.getSuperState());
        this.menu.restorePresenterStates(savedState.menuState);
    }

    public Parcelable onSaveInstanceState() {
        SavedState savedState = new SavedState(super.onSaveInstanceState());
        Bundle bundle = new Bundle();
        savedState.menuState = bundle;
        this.menu.savePresenterStates(bundle);
        return savedState;
    }

    public void onSizeChanged(int i3, int i4, int i5, int i6) {
        super.onSizeChanged(i3, i4, i5, i6);
        maybeUpdateCornerSizeForDrawerLayout(i3, i4);
    }

    public void removeHeaderView(@NonNull View view) {
        this.presenter.removeHeaderView(view);
    }

    public void setBottomInsetScrimEnabled(boolean z2) {
        this.bottomInsetScrimEnabled = z2;
    }

    public void setCheckedItem(@IdRes int i3) {
        MenuItem findItem = this.menu.findItem(i3);
        if (findItem != null) {
            this.presenter.setCheckedItem((MenuItemImpl) findItem);
        }
    }

    public void setDividerInsetEnd(@Px int i3) {
        this.presenter.setDividerInsetEnd(i3);
    }

    public void setDividerInsetStart(@Px int i3) {
        this.presenter.setDividerInsetStart(i3);
    }

    public void setElevation(float f2) {
        super.setElevation(f2);
        MaterialShapeUtils.setElevation(this, f2);
    }

    @VisibleForTesting
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    public void setForceCompatClippingEnabled(boolean z2) {
        this.shapeableDelegate.setForceCompatClippingEnabled(this, z2);
    }

    public void setItemBackground(@Nullable Drawable drawable) {
        this.presenter.setItemBackground(drawable);
    }

    public void setItemBackgroundResource(@DrawableRes int i3) {
        setItemBackground(ContextCompat.getDrawable(getContext(), i3));
    }

    public void setItemHorizontalPadding(@Dimension int i3) {
        this.presenter.setItemHorizontalPadding(i3);
    }

    public void setItemHorizontalPaddingResource(@DimenRes int i3) {
        this.presenter.setItemHorizontalPadding(getResources().getDimensionPixelSize(i3));
    }

    public void setItemIconPadding(@Dimension int i3) {
        this.presenter.setItemIconPadding(i3);
    }

    public void setItemIconPaddingResource(int i3) {
        this.presenter.setItemIconPadding(getResources().getDimensionPixelSize(i3));
    }

    public void setItemIconSize(@Dimension int i3) {
        this.presenter.setItemIconSize(i3);
    }

    public void setItemIconTintList(@Nullable ColorStateList colorStateList) {
        this.presenter.setItemIconTintList(colorStateList);
    }

    public void setItemMaxLines(int i3) {
        this.presenter.setItemMaxLines(i3);
    }

    public void setItemTextAppearance(@StyleRes int i3) {
        this.presenter.setItemTextAppearance(i3);
    }

    public void setItemTextAppearanceActiveBoldEnabled(boolean z2) {
        this.presenter.setItemTextAppearanceActiveBoldEnabled(z2);
    }

    public void setItemTextColor(@Nullable ColorStateList colorStateList) {
        this.presenter.setItemTextColor(colorStateList);
    }

    public void setItemVerticalPadding(@Px int i3) {
        this.presenter.setItemVerticalPadding(i3);
    }

    public void setItemVerticalPaddingResource(@DimenRes int i3) {
        this.presenter.setItemVerticalPadding(getResources().getDimensionPixelSize(i3));
    }

    public void setNavigationItemSelectedListener(@Nullable OnNavigationItemSelectedListener onNavigationItemSelectedListener) {
        this.listener = onNavigationItemSelectedListener;
    }

    public void setOverScrollMode(int i3) {
        super.setOverScrollMode(i3);
        NavigationMenuPresenter navigationMenuPresenter = this.presenter;
        if (navigationMenuPresenter != null) {
            navigationMenuPresenter.setOverScrollMode(i3);
        }
    }

    public void setSubheaderInsetEnd(@Px int i3) {
        this.presenter.setSubheaderInsetEnd(i3);
    }

    public void setSubheaderInsetStart(@Px int i3) {
        this.presenter.setSubheaderInsetStart(i3);
    }

    public void setTopInsetScrimEnabled(boolean z2) {
        this.topInsetScrimEnabled = z2;
    }

    public void startBackProgress(@NonNull BackEventCompat backEventCompat) {
        requireDrawerLayoutParent();
        this.sideContainerBackHelper.startBackProgress(backEventCompat);
    }

    public void updateBackProgress(@NonNull BackEventCompat backEventCompat) {
        this.sideContainerBackHelper.updateBackProgress(backEventCompat, ((DrawerLayout.LayoutParams) requireDrawerLayoutParent().second).gravity);
        if (this.drawerLayoutCornerSizeBackAnimationEnabled) {
            this.drawerLayoutCornerSize = AnimationUtils.lerp(0, this.drawerLayoutCornerSizeBackAnimationMax, this.sideContainerBackHelper.interpolateProgress(backEventCompat.getProgress()));
            maybeUpdateCornerSizeForDrawerLayout(getWidth(), getHeight());
        }
    }

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
        @Nullable
        public Bundle menuState;

        public SavedState(@NonNull Parcel parcel, @Nullable ClassLoader classLoader) {
            super(parcel, classLoader);
            this.menuState = parcel.readBundle(classLoader);
        }

        public void writeToParcel(@NonNull Parcel parcel, int i3) {
            super.writeToParcel(parcel, i3);
            parcel.writeBundle(this.menuState);
        }

        public SavedState(Parcelable parcelable) {
            super(parcelable);
        }
    }

    public NavigationView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        this(context, attributeSet, R.attr.navigationViewStyle);
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public NavigationView(@androidx.annotation.NonNull android.content.Context r17, @androidx.annotation.Nullable android.util.AttributeSet r18, int r19) {
        /*
            r16 = this;
            r0 = r16
            r7 = r18
            r8 = r19
            int r9 = DEF_STYLE_RES
            r1 = r17
            android.content.Context r1 = com.google.android.material.theme.overlay.MaterialThemeOverlay.wrap(r1, r7, r8, r9)
            r0.<init>(r1, r7, r8)
            com.google.android.material.internal.NavigationMenuPresenter r10 = new com.google.android.material.internal.NavigationMenuPresenter
            r10.<init>()
            r0.presenter = r10
            r1 = 2
            int[] r1 = new int[r1]
            r0.tmpLocation = r1
            r11 = 1
            r0.topInsetScrimEnabled = r11
            r0.bottomInsetScrimEnabled = r11
            r12 = 0
            r0.drawerLayoutCornerSize = r12
            com.google.android.material.shape.ShapeableDelegate r1 = com.google.android.material.shape.ShapeableDelegate.create(r16)
            r0.shapeableDelegate = r1
            com.google.android.material.motion.MaterialSideContainerBackHelper r1 = new com.google.android.material.motion.MaterialSideContainerBackHelper
            r1.<init>(r0)
            r0.sideContainerBackHelper = r1
            com.google.android.material.motion.MaterialBackOrchestrator r1 = new com.google.android.material.motion.MaterialBackOrchestrator
            r1.<init>(r0)
            r0.backOrchestrator = r1
            com.google.android.material.navigation.NavigationView$1 r1 = new com.google.android.material.navigation.NavigationView$1
            r1.<init>()
            r0.backDrawerListener = r1
            android.content.Context r13 = r16.getContext()
            com.google.android.material.internal.NavigationMenu r14 = new com.google.android.material.internal.NavigationMenu
            r14.<init>(r13)
            r0.menu = r14
            int[] r3 = com.google.android.material.R.styleable.NavigationView
            int[] r6 = new int[r12]
            r1 = r13
            r2 = r18
            r4 = r19
            r5 = r9
            androidx.appcompat.widget.TintTypedArray r1 = com.google.android.material.internal.ThemeEnforcement.obtainTintedStyledAttributes(r1, r2, r3, r4, r5, r6)
            int r2 = com.google.android.material.R.styleable.NavigationView_android_background
            boolean r3 = r1.hasValue(r2)
            if (r3 == 0) goto L_0x0068
            android.graphics.drawable.Drawable r2 = r1.getDrawable(r2)
            androidx.core.view.ViewCompat.setBackground(r0, r2)
        L_0x0068:
            int r2 = com.google.android.material.R.styleable.NavigationView_drawerLayoutCornerSize
            int r2 = r1.getDimensionPixelSize(r2, r12)
            r0.drawerLayoutCornerSize = r2
            if (r2 != 0) goto L_0x0074
            r2 = r11
            goto L_0x0075
        L_0x0074:
            r2 = r12
        L_0x0075:
            r0.drawerLayoutCornerSizeBackAnimationEnabled = r2
            android.content.res.Resources r2 = r16.getResources()
            int r3 = com.google.android.material.R.dimen.m3_navigation_drawer_layout_corner_size
            int r2 = r2.getDimensionPixelSize(r3)
            r0.drawerLayoutCornerSizeBackAnimationMax = r2
            android.graphics.drawable.Drawable r2 = r16.getBackground()
            android.content.res.ColorStateList r3 = com.google.android.material.drawable.DrawableUtils.getColorStateListOrNull(r2)
            if (r2 == 0) goto L_0x008f
            if (r3 == 0) goto L_0x00a7
        L_0x008f:
            com.google.android.material.shape.ShapeAppearanceModel$Builder r2 = com.google.android.material.shape.ShapeAppearanceModel.builder((android.content.Context) r13, (android.util.AttributeSet) r7, (int) r8, (int) r9)
            com.google.android.material.shape.ShapeAppearanceModel r2 = r2.build()
            com.google.android.material.shape.MaterialShapeDrawable r4 = new com.google.android.material.shape.MaterialShapeDrawable
            r4.<init>((com.google.android.material.shape.ShapeAppearanceModel) r2)
            if (r3 == 0) goto L_0x00a1
            r4.setFillColor(r3)
        L_0x00a1:
            r4.initializeElevationOverlay(r13)
            androidx.core.view.ViewCompat.setBackground(r0, r4)
        L_0x00a7:
            int r2 = com.google.android.material.R.styleable.NavigationView_elevation
            boolean r3 = r1.hasValue(r2)
            if (r3 == 0) goto L_0x00b7
            int r2 = r1.getDimensionPixelSize(r2, r12)
            float r2 = (float) r2
            r0.setElevation(r2)
        L_0x00b7:
            int r2 = com.google.android.material.R.styleable.NavigationView_android_fitsSystemWindows
            boolean r2 = r1.getBoolean(r2, r12)
            r0.setFitsSystemWindows(r2)
            int r2 = com.google.android.material.R.styleable.NavigationView_android_maxWidth
            int r2 = r1.getDimensionPixelSize(r2, r12)
            r0.maxWidth = r2
            int r2 = com.google.android.material.R.styleable.NavigationView_subheaderColor
            boolean r3 = r1.hasValue(r2)
            r4 = 0
            if (r3 == 0) goto L_0x00d6
            android.content.res.ColorStateList r2 = r1.getColorStateList(r2)
            goto L_0x00d7
        L_0x00d6:
            r2 = r4
        L_0x00d7:
            int r3 = com.google.android.material.R.styleable.NavigationView_subheaderTextAppearance
            boolean r5 = r1.hasValue(r3)
            if (r5 == 0) goto L_0x00e4
            int r3 = r1.getResourceId(r3, r12)
            goto L_0x00e5
        L_0x00e4:
            r3 = r12
        L_0x00e5:
            r5 = 16842808(0x1010038, float:2.3693715E-38)
            if (r3 != 0) goto L_0x00f0
            if (r2 != 0) goto L_0x00f0
            android.content.res.ColorStateList r2 = r0.createDefaultColorStateList(r5)
        L_0x00f0:
            int r6 = com.google.android.material.R.styleable.NavigationView_itemIconTint
            boolean r7 = r1.hasValue(r6)
            if (r7 == 0) goto L_0x00fd
            android.content.res.ColorStateList r5 = r1.getColorStateList(r6)
            goto L_0x0101
        L_0x00fd:
            android.content.res.ColorStateList r5 = r0.createDefaultColorStateList(r5)
        L_0x0101:
            int r6 = com.google.android.material.R.styleable.NavigationView_itemTextAppearance
            boolean r7 = r1.hasValue(r6)
            if (r7 == 0) goto L_0x010e
            int r6 = r1.getResourceId(r6, r12)
            goto L_0x010f
        L_0x010e:
            r6 = r12
        L_0x010f:
            int r7 = com.google.android.material.R.styleable.NavigationView_itemTextAppearanceActiveBoldEnabled
            boolean r7 = r1.getBoolean(r7, r11)
            int r8 = com.google.android.material.R.styleable.NavigationView_itemIconSize
            boolean r9 = r1.hasValue(r8)
            if (r9 == 0) goto L_0x0124
            int r8 = r1.getDimensionPixelSize(r8, r12)
            r0.setItemIconSize(r8)
        L_0x0124:
            int r8 = com.google.android.material.R.styleable.NavigationView_itemTextColor
            boolean r9 = r1.hasValue(r8)
            if (r9 == 0) goto L_0x0131
            android.content.res.ColorStateList r8 = r1.getColorStateList(r8)
            goto L_0x0132
        L_0x0131:
            r8 = r4
        L_0x0132:
            if (r6 != 0) goto L_0x013d
            if (r8 != 0) goto L_0x013d
            r8 = 16842806(0x1010036, float:2.369371E-38)
            android.content.res.ColorStateList r8 = r0.createDefaultColorStateList(r8)
        L_0x013d:
            int r9 = com.google.android.material.R.styleable.NavigationView_itemBackground
            android.graphics.drawable.Drawable r9 = r1.getDrawable(r9)
            if (r9 != 0) goto L_0x0167
            boolean r15 = r0.hasShapeAppearance(r1)
            if (r15 == 0) goto L_0x0167
            android.graphics.drawable.Drawable r9 = r0.createDefaultItemBackground(r1)
            int r15 = com.google.android.material.R.styleable.NavigationView_itemRippleColor
            android.content.res.ColorStateList r15 = com.google.android.material.resources.MaterialResources.getColorStateList((android.content.Context) r13, (androidx.appcompat.widget.TintTypedArray) r1, (int) r15)
            if (r15 == 0) goto L_0x0167
            android.graphics.drawable.Drawable r11 = r0.createDefaultItemDrawable(r1, r4)
            android.graphics.drawable.RippleDrawable r12 = new android.graphics.drawable.RippleDrawable
            android.content.res.ColorStateList r15 = com.google.android.material.ripple.RippleUtils.sanitizeRippleDrawableColor(r15)
            r12.<init>(r15, r4, r11)
            r10.setItemForeground(r12)
        L_0x0167:
            int r4 = com.google.android.material.R.styleable.NavigationView_itemHorizontalPadding
            boolean r11 = r1.hasValue(r4)
            if (r11 == 0) goto L_0x0178
            r11 = 0
            int r4 = r1.getDimensionPixelSize(r4, r11)
            r0.setItemHorizontalPadding(r4)
            goto L_0x0179
        L_0x0178:
            r11 = 0
        L_0x0179:
            int r4 = com.google.android.material.R.styleable.NavigationView_itemVerticalPadding
            boolean r12 = r1.hasValue(r4)
            if (r12 == 0) goto L_0x0188
            int r4 = r1.getDimensionPixelSize(r4, r11)
            r0.setItemVerticalPadding(r4)
        L_0x0188:
            int r4 = com.google.android.material.R.styleable.NavigationView_dividerInsetStart
            int r4 = r1.getDimensionPixelSize(r4, r11)
            r0.setDividerInsetStart(r4)
            int r4 = com.google.android.material.R.styleable.NavigationView_dividerInsetEnd
            int r4 = r1.getDimensionPixelSize(r4, r11)
            r0.setDividerInsetEnd(r4)
            int r4 = com.google.android.material.R.styleable.NavigationView_subheaderInsetStart
            int r4 = r1.getDimensionPixelSize(r4, r11)
            r0.setSubheaderInsetStart(r4)
            int r4 = com.google.android.material.R.styleable.NavigationView_subheaderInsetEnd
            int r4 = r1.getDimensionPixelSize(r4, r11)
            r0.setSubheaderInsetEnd(r4)
            int r4 = com.google.android.material.R.styleable.NavigationView_topInsetScrimEnabled
            boolean r12 = r0.topInsetScrimEnabled
            boolean r4 = r1.getBoolean(r4, r12)
            r0.setTopInsetScrimEnabled(r4)
            int r4 = com.google.android.material.R.styleable.NavigationView_bottomInsetScrimEnabled
            boolean r12 = r0.bottomInsetScrimEnabled
            boolean r4 = r1.getBoolean(r4, r12)
            r0.setBottomInsetScrimEnabled(r4)
            int r4 = com.google.android.material.R.styleable.NavigationView_itemIconPadding
            int r4 = r1.getDimensionPixelSize(r4, r11)
            int r11 = com.google.android.material.R.styleable.NavigationView_itemMaxLines
            r12 = 1
            int r11 = r1.getInt(r11, r12)
            r0.setItemMaxLines(r11)
            com.google.android.material.navigation.NavigationView$2 r11 = new com.google.android.material.navigation.NavigationView$2
            r11.<init>()
            r14.setCallback(r11)
            r10.setId(r12)
            r10.initForMenu(r13, r14)
            if (r3 == 0) goto L_0x01e5
            r10.setSubheaderTextAppearance(r3)
        L_0x01e5:
            r10.setSubheaderColor(r2)
            r10.setItemIconTintList(r5)
            int r2 = r16.getOverScrollMode()
            r10.setOverScrollMode(r2)
            if (r6 == 0) goto L_0x01f7
            r10.setItemTextAppearance(r6)
        L_0x01f7:
            r10.setItemTextAppearanceActiveBoldEnabled(r7)
            r10.setItemTextColor(r8)
            r10.setItemBackground(r9)
            r10.setItemIconPadding(r4)
            r14.addMenuPresenter(r10)
            androidx.appcompat.view.menu.MenuView r2 = r10.getMenuView(r0)
            android.view.View r2 = (android.view.View) r2
            r0.addView(r2)
            int r2 = com.google.android.material.R.styleable.NavigationView_menu
            boolean r3 = r1.hasValue(r2)
            if (r3 == 0) goto L_0x0220
            r3 = 0
            int r2 = r1.getResourceId(r2, r3)
            r0.inflateMenu(r2)
            goto L_0x0221
        L_0x0220:
            r3 = 0
        L_0x0221:
            int r2 = com.google.android.material.R.styleable.NavigationView_headerLayout
            boolean r4 = r1.hasValue(r2)
            if (r4 == 0) goto L_0x0230
            int r2 = r1.getResourceId(r2, r3)
            r0.inflateHeaderView(r2)
        L_0x0230:
            r1.recycle()
            r16.setupInsetScrimsListener()
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.material.navigation.NavigationView.<init>(android.content.Context, android.util.AttributeSet, int):void");
    }

    public void setCheckedItem(@NonNull MenuItem menuItem) {
        MenuItem findItem = this.menu.findItem(menuItem.getItemId());
        if (findItem != null) {
            this.presenter.setCheckedItem((MenuItemImpl) findItem);
            return;
        }
        throw new IllegalArgumentException("Called setCheckedItem(MenuItem) with an item that is not in the current menu.");
    }
}
