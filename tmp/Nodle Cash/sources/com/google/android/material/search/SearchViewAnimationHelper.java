package com.google.android.material.search;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.TimeInterpolator;
import android.animation.ValueAnimator;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.activity.BackEventCompat;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable;
import androidx.appcompat.widget.ActionMenuView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.view.ViewCompat;
import com.google.android.material.animation.AnimationUtils;
import com.google.android.material.internal.ClippableRoundedCornerLayout;
import com.google.android.material.internal.FadeThroughDrawable;
import com.google.android.material.internal.FadeThroughUpdateListener;
import com.google.android.material.internal.MultiViewUpdateListener;
import com.google.android.material.internal.RectEvaluator;
import com.google.android.material.internal.ReversableAnimatedValueInterpolator;
import com.google.android.material.internal.ToolbarUtils;
import com.google.android.material.internal.TouchObserverFrameLayout;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.motion.MaterialMainContainerBackHelper;
import com.google.android.material.search.SearchView;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import java.util.Objects;

class SearchViewAnimationHelper {
    private static final float CONTENT_FROM_SCALE = 0.95f;
    private static final long HIDE_CLEAR_BUTTON_ALPHA_DURATION_MS = 42;
    private static final long HIDE_CLEAR_BUTTON_ALPHA_START_DELAY_MS = 0;
    private static final long HIDE_CONTENT_ALPHA_DURATION_MS = 83;
    private static final long HIDE_CONTENT_ALPHA_START_DELAY_MS = 0;
    private static final long HIDE_CONTENT_SCALE_DURATION_MS = 250;
    private static final long HIDE_DURATION_MS = 250;
    private static final long HIDE_TRANSLATE_DURATION_MS = 300;
    private static final long SHOW_CLEAR_BUTTON_ALPHA_DURATION_MS = 50;
    private static final long SHOW_CLEAR_BUTTON_ALPHA_START_DELAY_MS = 250;
    private static final long SHOW_CONTENT_ALPHA_DURATION_MS = 150;
    private static final long SHOW_CONTENT_ALPHA_START_DELAY_MS = 75;
    private static final long SHOW_CONTENT_SCALE_DURATION_MS = 300;
    private static final long SHOW_DURATION_MS = 300;
    private static final long SHOW_TRANSLATE_DURATION_MS = 350;
    private static final long SHOW_TRANSLATE_KEYBOARD_START_DELAY_MS = 150;
    private final MaterialMainContainerBackHelper backHelper;
    @Nullable
    private AnimatorSet backProgressAnimatorSet;
    private final ImageButton clearButton;
    private final TouchObserverFrameLayout contentContainer;
    private final View divider;
    private final Toolbar dummyToolbar;
    private final EditText editText;
    private final FrameLayout headerContainer;
    /* access modifiers changed from: private */
    public final ClippableRoundedCornerLayout rootView;
    private final View scrim;
    /* access modifiers changed from: private */
    public SearchBar searchBar;
    private final TextView searchPrefix;
    /* access modifiers changed from: private */
    public final SearchView searchView;
    private final Toolbar toolbar;
    private final FrameLayout toolbarContainer;

    public SearchViewAnimationHelper(SearchView searchView2) {
        this.searchView = searchView2;
        this.scrim = searchView2.scrim;
        ClippableRoundedCornerLayout clippableRoundedCornerLayout = searchView2.rootView;
        this.rootView = clippableRoundedCornerLayout;
        this.headerContainer = searchView2.headerContainer;
        this.toolbarContainer = searchView2.toolbarContainer;
        this.toolbar = searchView2.toolbar;
        this.dummyToolbar = searchView2.dummyToolbar;
        this.searchPrefix = searchView2.searchPrefix;
        this.editText = searchView2.editText;
        this.clearButton = searchView2.clearButton;
        this.divider = searchView2.divider;
        this.contentContainer = searchView2.contentContainer;
        this.backHelper = new MaterialMainContainerBackHelper(clippableRoundedCornerLayout);
    }

    private void addActionMenuViewAnimatorIfNeeded(AnimatorSet animatorSet) {
        ActionMenuView actionMenuView = ToolbarUtils.getActionMenuView(this.toolbar);
        if (actionMenuView != null) {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{(float) getFromTranslationXEnd(actionMenuView), 0.0f});
            ofFloat.addUpdateListener(MultiViewUpdateListener.translationXListener(actionMenuView));
            ValueAnimator ofFloat2 = ValueAnimator.ofFloat(new float[]{(float) getFromTranslationY(), 0.0f});
            ofFloat2.addUpdateListener(MultiViewUpdateListener.translationYListener(actionMenuView));
            animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
        }
    }

    private void addBackButtonProgressAnimatorIfNeeded(AnimatorSet animatorSet) {
        ImageButton navigationIconButton = ToolbarUtils.getNavigationIconButton(this.toolbar);
        if (navigationIconButton != null) {
            Drawable unwrap = DrawableCompat.unwrap(navigationIconButton.getDrawable());
            if (this.searchView.isAnimatedNavigationIcon()) {
                addDrawerArrowDrawableAnimatorIfNeeded(animatorSet, unwrap);
                addFadeThroughDrawableAnimatorIfNeeded(animatorSet, unwrap);
                return;
            }
            setFullDrawableProgressIfNeeded(unwrap);
        }
    }

    private void addBackButtonTranslationAnimatorIfNeeded(AnimatorSet animatorSet) {
        ImageButton navigationIconButton = ToolbarUtils.getNavigationIconButton(this.toolbar);
        if (navigationIconButton != null) {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{(float) getFromTranslationXStart(navigationIconButton), 0.0f});
            ofFloat.addUpdateListener(MultiViewUpdateListener.translationXListener(navigationIconButton));
            ValueAnimator ofFloat2 = ValueAnimator.ofFloat(new float[]{(float) getFromTranslationY(), 0.0f});
            ofFloat2.addUpdateListener(MultiViewUpdateListener.translationYListener(navigationIconButton));
            animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
        }
    }

    private void addDrawerArrowDrawableAnimatorIfNeeded(AnimatorSet animatorSet, Drawable drawable) {
        if (drawable instanceof DrawerArrowDrawable) {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            ofFloat.addUpdateListener(new c((DrawerArrowDrawable) drawable, 1));
            animatorSet.playTogether(new Animator[]{ofFloat});
        }
    }

    private void addFadeThroughDrawableAnimatorIfNeeded(AnimatorSet animatorSet, Drawable drawable) {
        if (drawable instanceof FadeThroughDrawable) {
            ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            ofFloat.addUpdateListener(new c((FadeThroughDrawable) drawable, 2));
            animatorSet.playTogether(new Animator[]{ofFloat});
        }
    }

    private Animator getActionMenuViewsAlphaAnimator(boolean z2) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        ofFloat.setDuration(z2 ? 300 : 250);
        ofFloat.setInterpolator(ReversableAnimatedValueInterpolator.of(z2, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
        if (this.searchView.isMenuItemsAnimated()) {
            ofFloat.addUpdateListener(new FadeThroughUpdateListener(ToolbarUtils.getActionMenuView(this.dummyToolbar), ToolbarUtils.getActionMenuView(this.toolbar)));
        }
        return ofFloat;
    }

    private AnimatorSet getButtonsProgressAnimator(boolean z2) {
        AnimatorSet animatorSet = new AnimatorSet();
        addBackButtonProgressAnimatorIfNeeded(animatorSet);
        animatorSet.setDuration(z2 ? 300 : 250);
        animatorSet.setInterpolator(ReversableAnimatedValueInterpolator.of(z2, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
        return animatorSet;
    }

    private AnimatorSet getButtonsTranslationAnimator(boolean z2) {
        AnimatorSet animatorSet = new AnimatorSet();
        addBackButtonTranslationAnimatorIfNeeded(animatorSet);
        addActionMenuViewAnimatorIfNeeded(animatorSet);
        animatorSet.setDuration(z2 ? 300 : 250);
        animatorSet.setInterpolator(ReversableAnimatedValueInterpolator.of(z2, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
        return animatorSet;
    }

    private Animator getClearButtonAnimator(boolean z2) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        ofFloat.setDuration(z2 ? SHOW_CLEAR_BUTTON_ALPHA_DURATION_MS : 42);
        ofFloat.setStartDelay(z2 ? 250 : 0);
        ofFloat.setInterpolator(ReversableAnimatedValueInterpolator.of(z2, AnimationUtils.LINEAR_INTERPOLATOR));
        ofFloat.addUpdateListener(MultiViewUpdateListener.alphaListener(this.clearButton));
        return ofFloat;
    }

    private Animator getContentAlphaAnimator(boolean z2) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        ofFloat.setDuration(z2 ? 150 : HIDE_CONTENT_ALPHA_DURATION_MS);
        ofFloat.setStartDelay(z2 ? 75 : 0);
        ofFloat.setInterpolator(ReversableAnimatedValueInterpolator.of(z2, AnimationUtils.LINEAR_INTERPOLATOR));
        ofFloat.addUpdateListener(MultiViewUpdateListener.alphaListener(this.divider, this.contentContainer));
        return ofFloat;
    }

    private Animator getContentAnimator(boolean z2) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{getContentAlphaAnimator(z2), getDividerAnimator(z2), getContentScaleAnimator(z2)});
        return animatorSet;
    }

    private Animator getContentScaleAnimator(boolean z2) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{CONTENT_FROM_SCALE, 1.0f});
        ofFloat.setDuration(z2 ? 300 : 250);
        ofFloat.setInterpolator(ReversableAnimatedValueInterpolator.of(z2, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
        ofFloat.addUpdateListener(MultiViewUpdateListener.scaleListener(this.contentContainer));
        return ofFloat;
    }

    private Animator getDividerAnimator(boolean z2) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{(((float) this.contentContainer.getHeight()) * 0.050000012f) / 2.0f, 0.0f});
        ofFloat.setDuration(z2 ? 300 : 250);
        ofFloat.setInterpolator(ReversableAnimatedValueInterpolator.of(z2, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
        ofFloat.addUpdateListener(MultiViewUpdateListener.translationYListener(this.divider));
        return ofFloat;
    }

    private Animator getDummyToolbarAnimator(boolean z2) {
        return getTranslationAnimator(z2, false, this.dummyToolbar);
    }

    private Animator getEditTextAnimator(boolean z2) {
        return getTranslationAnimator(z2, true, this.editText);
    }

    private AnimatorSet getExpandCollapseAnimatorSet(final boolean z2) {
        AnimatorSet animatorSet = new AnimatorSet();
        if (this.backProgressAnimatorSet == null) {
            animatorSet.playTogether(new Animator[]{getButtonsProgressAnimator(z2), getButtonsTranslationAnimator(z2)});
        }
        animatorSet.playTogether(new Animator[]{getScrimAlphaAnimator(z2), getRootViewAnimator(z2), getClearButtonAnimator(z2), getContentAnimator(z2), getHeaderContainerAnimator(z2), getDummyToolbarAnimator(z2), getActionMenuViewsAlphaAnimator(z2), getEditTextAnimator(z2), getSearchPrefixAnimator(z2)});
        animatorSet.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                SearchViewAnimationHelper.this.setContentViewsAlpha(z2 ? 1.0f : 0.0f);
                SearchViewAnimationHelper.this.rootView.resetClipBoundsAndCornerRadius();
            }

            public void onAnimationStart(Animator animator) {
                SearchViewAnimationHelper.this.setContentViewsAlpha(z2 ? 0.0f : 1.0f);
            }
        });
        return animatorSet;
    }

    private int getFromTranslationXEnd(View view) {
        int marginEnd = MarginLayoutParamsCompat.getMarginEnd((ViewGroup.MarginLayoutParams) view.getLayoutParams());
        return ViewUtils.isLayoutRtl(this.searchBar) ? this.searchBar.getLeft() - marginEnd : (this.searchBar.getRight() - this.searchView.getWidth()) + marginEnd;
    }

    private int getFromTranslationXStart(View view) {
        int marginStart = MarginLayoutParamsCompat.getMarginStart((ViewGroup.MarginLayoutParams) view.getLayoutParams());
        int paddingStart = ViewCompat.getPaddingStart(this.searchBar);
        return ViewUtils.isLayoutRtl(this.searchBar) ? ((this.searchBar.getWidth() - this.searchBar.getRight()) + marginStart) - paddingStart : (this.searchBar.getLeft() - marginStart) + paddingStart;
    }

    private int getFromTranslationY() {
        int top = this.toolbarContainer.getTop();
        return ((this.searchBar.getBottom() + this.searchBar.getTop()) / 2) - ((this.toolbarContainer.getBottom() + top) / 2);
    }

    private Animator getHeaderContainerAnimator(boolean z2) {
        return getTranslationAnimator(z2, false, this.headerContainer);
    }

    private Animator getRootViewAnimator(boolean z2) {
        Rect initialHideToClipBounds = this.backHelper.getInitialHideToClipBounds();
        Rect initialHideFromClipBounds = this.backHelper.getInitialHideFromClipBounds();
        if (initialHideToClipBounds == null) {
            initialHideToClipBounds = ViewUtils.calculateRectFromBounds(this.searchView);
        }
        if (initialHideFromClipBounds == null) {
            initialHideFromClipBounds = ViewUtils.calculateOffsetRectFromBounds(this.rootView, this.searchBar);
        }
        Rect rect = new Rect(initialHideFromClipBounds);
        float cornerSize = this.searchBar.getCornerSize();
        float max = Math.max(this.rootView.getCornerRadius(), (float) this.backHelper.getExpandedCornerSize());
        ValueAnimator ofObject = ValueAnimator.ofObject(new RectEvaluator(rect), new Object[]{initialHideFromClipBounds, initialHideToClipBounds});
        ofObject.addUpdateListener(new l(this, cornerSize, max, rect));
        ofObject.setDuration(z2 ? 300 : 250);
        ofObject.setInterpolator(ReversableAnimatedValueInterpolator.of(z2, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
        return ofObject;
    }

    private Animator getScrimAlphaAnimator(boolean z2) {
        TimeInterpolator timeInterpolator = z2 ? AnimationUtils.LINEAR_INTERPOLATOR : AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR;
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        ofFloat.setDuration(z2 ? 300 : 250);
        ofFloat.setInterpolator(ReversableAnimatedValueInterpolator.of(z2, timeInterpolator));
        ofFloat.addUpdateListener(MultiViewUpdateListener.alphaListener(this.scrim));
        return ofFloat;
    }

    private Animator getSearchPrefixAnimator(boolean z2) {
        return getTranslationAnimator(z2, true, this.searchPrefix);
    }

    private AnimatorSet getTranslateAnimatorSet(boolean z2) {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{getTranslationYAnimator()});
        addBackButtonProgressAnimatorIfNeeded(animatorSet);
        animatorSet.setInterpolator(ReversableAnimatedValueInterpolator.of(z2, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
        animatorSet.setDuration(z2 ? SHOW_TRANSLATE_DURATION_MS : 300);
        return animatorSet;
    }

    private Animator getTranslationAnimator(boolean z2, boolean z3, View view) {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{(float) (z3 ? getFromTranslationXStart(view) : getFromTranslationXEnd(view)), 0.0f});
        ofFloat.addUpdateListener(MultiViewUpdateListener.translationXListener(view));
        ValueAnimator ofFloat2 = ValueAnimator.ofFloat(new float[]{(float) getFromTranslationY(), 0.0f});
        ofFloat2.addUpdateListener(MultiViewUpdateListener.translationYListener(view));
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(new Animator[]{ofFloat, ofFloat2});
        animatorSet.setDuration(z2 ? 300 : 250);
        animatorSet.setInterpolator(ReversableAnimatedValueInterpolator.of(z2, AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR));
        return animatorSet;
    }

    private Animator getTranslationYAnimator() {
        ValueAnimator ofFloat = ValueAnimator.ofFloat(new float[]{(float) this.rootView.getHeight(), 0.0f});
        ofFloat.addUpdateListener(MultiViewUpdateListener.translationYListener(this.rootView));
        return ofFloat;
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$getRootViewAnimator$2(float f2, float f3, Rect rect, ValueAnimator valueAnimator) {
        this.rootView.updateClipBoundsAndCornerRadius(rect, AnimationUtils.lerp(f2, f3, valueAnimator.getAnimatedFraction()));
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startShowAnimationExpand$0() {
        AnimatorSet expandCollapseAnimatorSet = getExpandCollapseAnimatorSet(true);
        expandCollapseAnimatorSet.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                if (!SearchViewAnimationHelper.this.searchView.isAdjustNothingSoftInputMode()) {
                    SearchViewAnimationHelper.this.searchView.requestFocusAndShowKeyboardIfNeeded();
                }
                SearchViewAnimationHelper.this.searchView.setTransitionState(SearchView.TransitionState.SHOWN);
            }

            public void onAnimationStart(Animator animator) {
                SearchViewAnimationHelper.this.rootView.setVisibility(0);
                SearchViewAnimationHelper.this.searchBar.stopOnLoadAnimation();
            }
        });
        expandCollapseAnimatorSet.start();
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$startShowAnimationTranslate$1() {
        ClippableRoundedCornerLayout clippableRoundedCornerLayout = this.rootView;
        clippableRoundedCornerLayout.setTranslationY((float) clippableRoundedCornerLayout.getHeight());
        AnimatorSet translateAnimatorSet = getTranslateAnimatorSet(true);
        translateAnimatorSet.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                if (!SearchViewAnimationHelper.this.searchView.isAdjustNothingSoftInputMode()) {
                    SearchViewAnimationHelper.this.searchView.requestFocusAndShowKeyboardIfNeeded();
                }
                SearchViewAnimationHelper.this.searchView.setTransitionState(SearchView.TransitionState.SHOWN);
            }

            public void onAnimationStart(Animator animator) {
                SearchViewAnimationHelper.this.rootView.setVisibility(0);
                SearchViewAnimationHelper.this.searchView.setTransitionState(SearchView.TransitionState.SHOWING);
            }
        });
        translateAnimatorSet.start();
    }

    private void setActionMenuViewAlphaIfNeeded(float f2) {
        ActionMenuView actionMenuView;
        if (this.searchView.isMenuItemsAnimated() && (actionMenuView = ToolbarUtils.getActionMenuView(this.toolbar)) != null) {
            actionMenuView.setAlpha(f2);
        }
    }

    /* access modifiers changed from: private */
    public void setContentViewsAlpha(float f2) {
        this.clearButton.setAlpha(f2);
        this.divider.setAlpha(f2);
        this.contentContainer.setAlpha(f2);
        setActionMenuViewAlphaIfNeeded(f2);
    }

    private void setFullDrawableProgressIfNeeded(Drawable drawable) {
        if (drawable instanceof DrawerArrowDrawable) {
            ((DrawerArrowDrawable) drawable).setProgress(1.0f);
        }
        if (drawable instanceof FadeThroughDrawable) {
            ((FadeThroughDrawable) drawable).setProgress(1.0f);
        }
    }

    private void setMenuItemsNotClickable(Toolbar toolbar2) {
        ActionMenuView actionMenuView = ToolbarUtils.getActionMenuView(toolbar2);
        if (actionMenuView != null) {
            for (int i3 = 0; i3 < actionMenuView.getChildCount(); i3++) {
                View childAt = actionMenuView.getChildAt(i3);
                childAt.setClickable(false);
                childAt.setFocusable(false);
                childAt.setFocusableInTouchMode(false);
            }
        }
    }

    private void setUpDummyToolbarIfNeeded() {
        Menu menu = this.dummyToolbar.getMenu();
        if (menu != null) {
            menu.clear();
        }
        if (this.searchBar.getMenuResId() == -1 || !this.searchView.isMenuItemsAnimated()) {
            this.dummyToolbar.setVisibility(8);
            return;
        }
        this.dummyToolbar.inflateMenu(this.searchBar.getMenuResId());
        setMenuItemsNotClickable(this.dummyToolbar);
        this.dummyToolbar.setVisibility(0);
    }

    private AnimatorSet startHideAnimationCollapse() {
        if (this.searchView.isAdjustNothingSoftInputMode()) {
            this.searchView.clearFocusAndHideKeyboard();
        }
        AnimatorSet expandCollapseAnimatorSet = getExpandCollapseAnimatorSet(false);
        expandCollapseAnimatorSet.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                SearchViewAnimationHelper.this.rootView.setVisibility(8);
                if (!SearchViewAnimationHelper.this.searchView.isAdjustNothingSoftInputMode()) {
                    SearchViewAnimationHelper.this.searchView.clearFocusAndHideKeyboard();
                }
                SearchViewAnimationHelper.this.searchView.setTransitionState(SearchView.TransitionState.HIDDEN);
            }

            public void onAnimationStart(Animator animator) {
                SearchViewAnimationHelper.this.searchView.setTransitionState(SearchView.TransitionState.HIDING);
            }
        });
        expandCollapseAnimatorSet.start();
        return expandCollapseAnimatorSet;
    }

    private AnimatorSet startHideAnimationTranslate() {
        if (this.searchView.isAdjustNothingSoftInputMode()) {
            this.searchView.clearFocusAndHideKeyboard();
        }
        AnimatorSet translateAnimatorSet = getTranslateAnimatorSet(false);
        translateAnimatorSet.addListener(new AnimatorListenerAdapter() {
            public void onAnimationEnd(Animator animator) {
                SearchViewAnimationHelper.this.rootView.setVisibility(8);
                if (!SearchViewAnimationHelper.this.searchView.isAdjustNothingSoftInputMode()) {
                    SearchViewAnimationHelper.this.searchView.clearFocusAndHideKeyboard();
                }
                SearchViewAnimationHelper.this.searchView.setTransitionState(SearchView.TransitionState.HIDDEN);
            }

            public void onAnimationStart(Animator animator) {
                SearchViewAnimationHelper.this.searchView.setTransitionState(SearchView.TransitionState.HIDING);
            }
        });
        translateAnimatorSet.start();
        return translateAnimatorSet;
    }

    private void startShowAnimationExpand() {
        if (this.searchView.isAdjustNothingSoftInputMode()) {
            this.searchView.requestFocusAndShowKeyboardIfNeeded();
        }
        this.searchView.setTransitionState(SearchView.TransitionState.SHOWING);
        setUpDummyToolbarIfNeeded();
        this.editText.setText(this.searchBar.getText());
        EditText editText2 = this.editText;
        editText2.setSelection(editText2.getText().length());
        this.rootView.setVisibility(4);
        this.rootView.post(new m(this, 0));
    }

    private void startShowAnimationTranslate() {
        if (this.searchView.isAdjustNothingSoftInputMode()) {
            SearchView searchView2 = this.searchView;
            Objects.requireNonNull(searchView2);
            searchView2.postDelayed(new h(searchView2, 3), 150);
        }
        this.rootView.setVisibility(4);
        this.rootView.post(new m(this, 1));
    }

    @RequiresApi(34)
    public void cancelBackProgress() {
        this.backHelper.cancelBackProgress(this.searchBar);
        AnimatorSet animatorSet = this.backProgressAnimatorSet;
        if (animatorSet != null) {
            animatorSet.reverse();
        }
        this.backProgressAnimatorSet = null;
    }

    @RequiresApi(34)
    public void finishBackProgress() {
        this.backHelper.finishBackProgress(hide().getTotalDuration(), this.searchBar);
        if (this.backProgressAnimatorSet != null) {
            getButtonsTranslationAnimator(false).start();
            this.backProgressAnimatorSet.resume();
        }
        this.backProgressAnimatorSet = null;
    }

    public MaterialMainContainerBackHelper getBackHelper() {
        return this.backHelper;
    }

    @CanIgnoreReturnValue
    public AnimatorSet hide() {
        return this.searchBar != null ? startHideAnimationCollapse() : startHideAnimationTranslate();
    }

    @Nullable
    public BackEventCompat onHandleBackInvoked() {
        return this.backHelper.onHandleBackInvoked();
    }

    public void setSearchBar(SearchBar searchBar2) {
        this.searchBar = searchBar2;
    }

    public void show() {
        if (this.searchBar != null) {
            startShowAnimationExpand();
        } else {
            startShowAnimationTranslate();
        }
    }

    public void startBackProgress(@NonNull BackEventCompat backEventCompat) {
        this.backHelper.startBackProgress(backEventCompat, (View) this.searchBar);
    }

    @RequiresApi(34)
    public void updateBackProgress(@NonNull BackEventCompat backEventCompat) {
        if (backEventCompat.getProgress() > 0.0f) {
            MaterialMainContainerBackHelper materialMainContainerBackHelper = this.backHelper;
            SearchBar searchBar2 = this.searchBar;
            materialMainContainerBackHelper.updateBackProgress(backEventCompat, searchBar2, searchBar2.getCornerSize());
            AnimatorSet animatorSet = this.backProgressAnimatorSet;
            if (animatorSet == null) {
                if (this.searchView.isAdjustNothingSoftInputMode()) {
                    this.searchView.clearFocusAndHideKeyboard();
                }
                if (this.searchView.isAnimatedNavigationIcon()) {
                    AnimatorSet buttonsProgressAnimator = getButtonsProgressAnimator(false);
                    this.backProgressAnimatorSet = buttonsProgressAnimator;
                    buttonsProgressAnimator.start();
                    this.backProgressAnimatorSet.pause();
                    return;
                }
                return;
            }
            animatorSet.setCurrentPlayTime((long) (backEventCompat.getProgress() * ((float) this.backProgressAnimatorSet.getDuration())));
        }
    }
}
