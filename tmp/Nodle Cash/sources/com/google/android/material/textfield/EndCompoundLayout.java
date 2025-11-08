package com.google.android.material.textfield;

import A.a;
import android.annotation.SuppressLint;
import android.content.res.ColorStateList;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.DrawableRes;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.appcompat.widget.TintTypedArray;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.GravityCompat;
import androidx.core.view.MarginLayoutParamsCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityManagerCompat;
import androidx.core.widget.TextViewCompat;
import com.google.android.material.R;
import com.google.android.material.internal.CheckableImageButton;
import com.google.android.material.internal.TextWatcherAdapter;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.textfield.TextInputLayout;
import java.util.Iterator;
import java.util.LinkedHashSet;

@SuppressLint({"ViewConstructor"})
class EndCompoundLayout extends LinearLayout {
    @Nullable
    private final AccessibilityManager accessibilityManager;
    /* access modifiers changed from: private */
    public EditText editText;
    /* access modifiers changed from: private */
    public final TextWatcher editTextWatcher = new TextWatcherAdapter() {
        public void afterTextChanged(Editable editable) {
            EndCompoundLayout.this.getEndIconDelegate().afterEditTextChanged(editable);
        }

        public void beforeTextChanged(CharSequence charSequence, int i3, int i4, int i5) {
            EndCompoundLayout.this.getEndIconDelegate().beforeEditTextChanged(charSequence, i3, i4, i5);
        }
    };
    private final LinkedHashSet<TextInputLayout.OnEndIconChangedListener> endIconChangedListeners = new LinkedHashSet<>();
    private final EndIconDelegates endIconDelegates;
    @NonNull
    private final FrameLayout endIconFrame;
    private int endIconMinSize;
    private int endIconMode = 0;
    private View.OnLongClickListener endIconOnLongClickListener;
    @NonNull
    private ImageView.ScaleType endIconScaleType;
    private ColorStateList endIconTintList;
    private PorterDuff.Mode endIconTintMode;
    @NonNull
    private final CheckableImageButton endIconView;
    private View.OnLongClickListener errorIconOnLongClickListener;
    private ColorStateList errorIconTintList;
    private PorterDuff.Mode errorIconTintMode;
    @NonNull
    private final CheckableImageButton errorIconView;
    private boolean hintExpanded;
    private final TextInputLayout.OnEditTextAttachedListener onEditTextAttachedListener;
    @Nullable
    private CharSequence suffixText;
    @NonNull
    private final TextView suffixTextView;
    final TextInputLayout textInputLayout;
    @Nullable
    private AccessibilityManagerCompat.TouchExplorationStateChangeListener touchExplorationStateChangeListener;

    public static class EndIconDelegates {
        /* access modifiers changed from: private */
        public final int customEndIconDrawableId;
        private final SparseArray<EndIconDelegate> delegates = new SparseArray<>();
        private final EndCompoundLayout endLayout;
        private final int passwordIconDrawableId;

        public EndIconDelegates(EndCompoundLayout endCompoundLayout, TintTypedArray tintTypedArray) {
            this.endLayout = endCompoundLayout;
            this.customEndIconDrawableId = tintTypedArray.getResourceId(R.styleable.TextInputLayout_endIconDrawable, 0);
            this.passwordIconDrawableId = tintTypedArray.getResourceId(R.styleable.TextInputLayout_passwordToggleDrawable, 0);
        }

        private EndIconDelegate create(int i3) {
            if (i3 == -1) {
                return new CustomEndIconDelegate(this.endLayout);
            }
            if (i3 == 0) {
                return new NoEndIconDelegate(this.endLayout);
            }
            if (i3 == 1) {
                return new PasswordToggleEndIconDelegate(this.endLayout, this.passwordIconDrawableId);
            }
            if (i3 == 2) {
                return new ClearTextEndIconDelegate(this.endLayout);
            }
            if (i3 == 3) {
                return new DropdownMenuEndIconDelegate(this.endLayout);
            }
            throw new IllegalArgumentException(a.k("Invalid end icon mode: ", i3));
        }

        public EndIconDelegate get(int i3) {
            EndIconDelegate endIconDelegate = this.delegates.get(i3);
            if (endIconDelegate != null) {
                return endIconDelegate;
            }
            EndIconDelegate create = create(i3);
            this.delegates.append(i3, create);
            return create;
        }
    }

    public EndCompoundLayout(TextInputLayout textInputLayout2, TintTypedArray tintTypedArray) {
        super(textInputLayout2.getContext());
        AnonymousClass2 r12 = new TextInputLayout.OnEditTextAttachedListener() {
            public void onEditTextAttached(@NonNull TextInputLayout textInputLayout) {
                if (EndCompoundLayout.this.editText != textInputLayout.getEditText()) {
                    if (EndCompoundLayout.this.editText != null) {
                        EndCompoundLayout.this.editText.removeTextChangedListener(EndCompoundLayout.this.editTextWatcher);
                        if (EndCompoundLayout.this.editText.getOnFocusChangeListener() == EndCompoundLayout.this.getEndIconDelegate().getOnEditTextFocusChangeListener()) {
                            EndCompoundLayout.this.editText.setOnFocusChangeListener((View.OnFocusChangeListener) null);
                        }
                    }
                    EditText unused = EndCompoundLayout.this.editText = textInputLayout.getEditText();
                    if (EndCompoundLayout.this.editText != null) {
                        EndCompoundLayout.this.editText.addTextChangedListener(EndCompoundLayout.this.editTextWatcher);
                    }
                    EndCompoundLayout.this.getEndIconDelegate().onEditTextAttached(EndCompoundLayout.this.editText);
                    EndCompoundLayout endCompoundLayout = EndCompoundLayout.this;
                    endCompoundLayout.setOnFocusChangeListenersIfNeeded(endCompoundLayout.getEndIconDelegate());
                }
            }
        };
        this.onEditTextAttachedListener = r12;
        this.accessibilityManager = (AccessibilityManager) getContext().getSystemService("accessibility");
        this.textInputLayout = textInputLayout2;
        setVisibility(8);
        setOrientation(0);
        setLayoutParams(new FrameLayout.LayoutParams(-2, -1, GravityCompat.END));
        FrameLayout frameLayout = new FrameLayout(getContext());
        this.endIconFrame = frameLayout;
        frameLayout.setVisibility(8);
        frameLayout.setLayoutParams(new LinearLayout.LayoutParams(-2, -1));
        LayoutInflater from = LayoutInflater.from(getContext());
        CheckableImageButton createIconView = createIconView(this, from, R.id.text_input_error_icon);
        this.errorIconView = createIconView;
        CheckableImageButton createIconView2 = createIconView(frameLayout, from, R.id.text_input_end_icon);
        this.endIconView = createIconView2;
        this.endIconDelegates = new EndIconDelegates(this, tintTypedArray);
        AppCompatTextView appCompatTextView = new AppCompatTextView(getContext());
        this.suffixTextView = appCompatTextView;
        initErrorIconView(tintTypedArray);
        initEndIconView(tintTypedArray);
        initSuffixTextView(tintTypedArray);
        frameLayout.addView(createIconView2);
        addView(appCompatTextView);
        addView(frameLayout);
        addView(createIconView);
        textInputLayout2.addOnEditTextAttachedListener(r12);
        addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            public void onViewAttachedToWindow(View view) {
                EndCompoundLayout.this.addTouchExplorationStateChangeListenerIfNeeded();
            }

            public void onViewDetachedFromWindow(View view) {
                EndCompoundLayout.this.removeTouchExplorationStateChangeListenerIfNeeded();
            }
        });
    }

    /* access modifiers changed from: private */
    public void addTouchExplorationStateChangeListenerIfNeeded() {
        if (this.touchExplorationStateChangeListener != null && this.accessibilityManager != null && ViewCompat.isAttachedToWindow(this)) {
            AccessibilityManagerCompat.addTouchExplorationStateChangeListener(this.accessibilityManager, this.touchExplorationStateChangeListener);
        }
    }

    private CheckableImageButton createIconView(ViewGroup viewGroup, LayoutInflater layoutInflater, @IdRes int i3) {
        CheckableImageButton checkableImageButton = (CheckableImageButton) layoutInflater.inflate(R.layout.design_text_input_end_icon, viewGroup, false);
        checkableImageButton.setId(i3);
        IconHelper.setCompatRippleBackgroundIfNeeded(checkableImageButton);
        if (MaterialResources.isFontScaleAtLeast1_3(getContext())) {
            MarginLayoutParamsCompat.setMarginStart((ViewGroup.MarginLayoutParams) checkableImageButton.getLayoutParams(), 0);
        }
        return checkableImageButton;
    }

    private void dispatchOnEndIconChanged(int i3) {
        Iterator<TextInputLayout.OnEndIconChangedListener> it = this.endIconChangedListeners.iterator();
        while (it.hasNext()) {
            it.next().onEndIconChanged(this.textInputLayout, i3);
        }
    }

    private int getIconResId(EndIconDelegate endIconDelegate) {
        int access$500 = this.endIconDelegates.customEndIconDrawableId;
        return access$500 == 0 ? endIconDelegate.getIconDrawableResId() : access$500;
    }

    private void initEndIconView(TintTypedArray tintTypedArray) {
        int i3 = R.styleable.TextInputLayout_passwordToggleEnabled;
        if (!tintTypedArray.hasValue(i3)) {
            int i4 = R.styleable.TextInputLayout_endIconTint;
            if (tintTypedArray.hasValue(i4)) {
                this.endIconTintList = MaterialResources.getColorStateList(getContext(), tintTypedArray, i4);
            }
            int i5 = R.styleable.TextInputLayout_endIconTintMode;
            if (tintTypedArray.hasValue(i5)) {
                this.endIconTintMode = ViewUtils.parseTintMode(tintTypedArray.getInt(i5, -1), (PorterDuff.Mode) null);
            }
        }
        int i6 = R.styleable.TextInputLayout_endIconMode;
        if (tintTypedArray.hasValue(i6)) {
            setEndIconMode(tintTypedArray.getInt(i6, 0));
            int i7 = R.styleable.TextInputLayout_endIconContentDescription;
            if (tintTypedArray.hasValue(i7)) {
                setEndIconContentDescription(tintTypedArray.getText(i7));
            }
            setEndIconCheckable(tintTypedArray.getBoolean(R.styleable.TextInputLayout_endIconCheckable, true));
        } else if (tintTypedArray.hasValue(i3)) {
            int i8 = R.styleable.TextInputLayout_passwordToggleTint;
            if (tintTypedArray.hasValue(i8)) {
                this.endIconTintList = MaterialResources.getColorStateList(getContext(), tintTypedArray, i8);
            }
            int i9 = R.styleable.TextInputLayout_passwordToggleTintMode;
            if (tintTypedArray.hasValue(i9)) {
                this.endIconTintMode = ViewUtils.parseTintMode(tintTypedArray.getInt(i9, -1), (PorterDuff.Mode) null);
            }
            setEndIconMode(tintTypedArray.getBoolean(i3, false) ? 1 : 0);
            setEndIconContentDescription(tintTypedArray.getText(R.styleable.TextInputLayout_passwordToggleContentDescription));
        }
        setEndIconMinSize(tintTypedArray.getDimensionPixelSize(R.styleable.TextInputLayout_endIconMinSize, getResources().getDimensionPixelSize(R.dimen.mtrl_min_touch_target_size)));
        int i10 = R.styleable.TextInputLayout_endIconScaleType;
        if (tintTypedArray.hasValue(i10)) {
            setEndIconScaleType(IconHelper.convertScaleType(tintTypedArray.getInt(i10, -1)));
        }
    }

    private void initErrorIconView(TintTypedArray tintTypedArray) {
        int i3 = R.styleable.TextInputLayout_errorIconTint;
        if (tintTypedArray.hasValue(i3)) {
            this.errorIconTintList = MaterialResources.getColorStateList(getContext(), tintTypedArray, i3);
        }
        int i4 = R.styleable.TextInputLayout_errorIconTintMode;
        if (tintTypedArray.hasValue(i4)) {
            this.errorIconTintMode = ViewUtils.parseTintMode(tintTypedArray.getInt(i4, -1), (PorterDuff.Mode) null);
        }
        int i5 = R.styleable.TextInputLayout_errorIconDrawable;
        if (tintTypedArray.hasValue(i5)) {
            setErrorIconDrawable(tintTypedArray.getDrawable(i5));
        }
        this.errorIconView.setContentDescription(getResources().getText(R.string.error_icon_content_description));
        ViewCompat.setImportantForAccessibility(this.errorIconView, 2);
        this.errorIconView.setClickable(false);
        this.errorIconView.setPressable(false);
        this.errorIconView.setFocusable(false);
    }

    private void initSuffixTextView(TintTypedArray tintTypedArray) {
        this.suffixTextView.setVisibility(8);
        this.suffixTextView.setId(R.id.textinput_suffix_text);
        this.suffixTextView.setLayoutParams(new LinearLayout.LayoutParams(-2, -2, 80.0f));
        ViewCompat.setAccessibilityLiveRegion(this.suffixTextView, 1);
        setSuffixTextAppearance(tintTypedArray.getResourceId(R.styleable.TextInputLayout_suffixTextAppearance, 0));
        int i3 = R.styleable.TextInputLayout_suffixTextColor;
        if (tintTypedArray.hasValue(i3)) {
            setSuffixTextColor(tintTypedArray.getColorStateList(i3));
        }
        setSuffixText(tintTypedArray.getText(R.styleable.TextInputLayout_suffixText));
    }

    /* access modifiers changed from: private */
    public void removeTouchExplorationStateChangeListenerIfNeeded() {
        AccessibilityManager accessibilityManager2;
        AccessibilityManagerCompat.TouchExplorationStateChangeListener touchExplorationStateChangeListener2 = this.touchExplorationStateChangeListener;
        if (touchExplorationStateChangeListener2 != null && (accessibilityManager2 = this.accessibilityManager) != null) {
            AccessibilityManagerCompat.removeTouchExplorationStateChangeListener(accessibilityManager2, touchExplorationStateChangeListener2);
        }
    }

    /* access modifiers changed from: private */
    public void setOnFocusChangeListenersIfNeeded(EndIconDelegate endIconDelegate) {
        if (this.editText != null) {
            if (endIconDelegate.getOnEditTextFocusChangeListener() != null) {
                this.editText.setOnFocusChangeListener(endIconDelegate.getOnEditTextFocusChangeListener());
            }
            if (endIconDelegate.getOnIconViewFocusChangeListener() != null) {
                this.endIconView.setOnFocusChangeListener(endIconDelegate.getOnIconViewFocusChangeListener());
            }
        }
    }

    private void setUpDelegate(@NonNull EndIconDelegate endIconDelegate) {
        endIconDelegate.setUp();
        this.touchExplorationStateChangeListener = endIconDelegate.getTouchExplorationStateChangeListener();
        addTouchExplorationStateChangeListenerIfNeeded();
    }

    private void tearDownDelegate(@NonNull EndIconDelegate endIconDelegate) {
        removeTouchExplorationStateChangeListenerIfNeeded();
        this.touchExplorationStateChangeListener = null;
        endIconDelegate.tearDown();
    }

    private void tintEndIconOnError(boolean z2) {
        if (!z2 || getEndIconDrawable() == null) {
            IconHelper.applyIconTint(this.textInputLayout, this.endIconView, this.endIconTintList, this.endIconTintMode);
            return;
        }
        Drawable mutate = DrawableCompat.wrap(getEndIconDrawable()).mutate();
        DrawableCompat.setTint(mutate, this.textInputLayout.getErrorCurrentTextColors());
        this.endIconView.setImageDrawable(mutate);
    }

    private void updateEndLayoutVisibility() {
        int i3 = 8;
        this.endIconFrame.setVisibility((this.endIconView.getVisibility() != 0 || isErrorIconVisible()) ? 8 : 0);
        boolean z2 = (this.suffixText == null || this.hintExpanded) ? true : false;
        if (isEndIconVisible() || isErrorIconVisible() || !z2) {
            i3 = 0;
        }
        setVisibility(i3);
    }

    private void updateErrorIconVisibility() {
        int i3 = 0;
        boolean z2 = getErrorIconDrawable() != null && this.textInputLayout.isErrorEnabled() && this.textInputLayout.shouldShowError();
        CheckableImageButton checkableImageButton = this.errorIconView;
        if (!z2) {
            i3 = 8;
        }
        checkableImageButton.setVisibility(i3);
        updateEndLayoutVisibility();
        updateSuffixTextViewPadding();
        if (!hasEndIcon()) {
            this.textInputLayout.updateDummyDrawables();
        }
    }

    private void updateSuffixTextVisibility() {
        int visibility = this.suffixTextView.getVisibility();
        boolean z2 = false;
        int i3 = (this.suffixText == null || this.hintExpanded) ? 8 : 0;
        if (visibility != i3) {
            EndIconDelegate endIconDelegate = getEndIconDelegate();
            if (i3 == 0) {
                z2 = true;
            }
            endIconDelegate.onSuffixVisibilityChanged(z2);
        }
        updateEndLayoutVisibility();
        this.suffixTextView.setVisibility(i3);
        this.textInputLayout.updateDummyDrawables();
    }

    public void addOnEndIconChangedListener(@NonNull TextInputLayout.OnEndIconChangedListener onEndIconChangedListener) {
        this.endIconChangedListeners.add(onEndIconChangedListener);
    }

    public void checkEndIcon() {
        this.endIconView.performClick();
        this.endIconView.jumpDrawablesToCurrentState();
    }

    public void clearOnEndIconChangedListeners() {
        this.endIconChangedListeners.clear();
    }

    @Nullable
    public CheckableImageButton getCurrentEndIconView() {
        if (isErrorIconVisible()) {
            return this.errorIconView;
        }
        if (!hasEndIcon() || !isEndIconVisible()) {
            return null;
        }
        return this.endIconView;
    }

    @Nullable
    public CharSequence getEndIconContentDescription() {
        return this.endIconView.getContentDescription();
    }

    public EndIconDelegate getEndIconDelegate() {
        return this.endIconDelegates.get(this.endIconMode);
    }

    @Nullable
    public Drawable getEndIconDrawable() {
        return this.endIconView.getDrawable();
    }

    public int getEndIconMinSize() {
        return this.endIconMinSize;
    }

    public int getEndIconMode() {
        return this.endIconMode;
    }

    @NonNull
    public ImageView.ScaleType getEndIconScaleType() {
        return this.endIconScaleType;
    }

    public CheckableImageButton getEndIconView() {
        return this.endIconView;
    }

    public Drawable getErrorIconDrawable() {
        return this.errorIconView.getDrawable();
    }

    @Nullable
    public CharSequence getPasswordVisibilityToggleContentDescription() {
        return this.endIconView.getContentDescription();
    }

    @Nullable
    public Drawable getPasswordVisibilityToggleDrawable() {
        return this.endIconView.getDrawable();
    }

    @Nullable
    public CharSequence getSuffixText() {
        return this.suffixText;
    }

    @Nullable
    public ColorStateList getSuffixTextColor() {
        return this.suffixTextView.getTextColors();
    }

    public int getSuffixTextEndOffset() {
        return ViewCompat.getPaddingEnd(this.suffixTextView) + ViewCompat.getPaddingEnd(this) + ((isEndIconVisible() || isErrorIconVisible()) ? this.endIconView.getMeasuredWidth() + MarginLayoutParamsCompat.getMarginStart((ViewGroup.MarginLayoutParams) this.endIconView.getLayoutParams()) : 0);
    }

    public TextView getSuffixTextView() {
        return this.suffixTextView;
    }

    public boolean hasEndIcon() {
        return this.endIconMode != 0;
    }

    public boolean isEndIconCheckable() {
        return this.endIconView.isCheckable();
    }

    public boolean isEndIconChecked() {
        return hasEndIcon() && this.endIconView.isChecked();
    }

    public boolean isEndIconVisible() {
        return this.endIconFrame.getVisibility() == 0 && this.endIconView.getVisibility() == 0;
    }

    public boolean isErrorIconVisible() {
        return this.errorIconView.getVisibility() == 0;
    }

    public boolean isPasswordVisibilityToggleEnabled() {
        return this.endIconMode == 1;
    }

    public void onHintStateChanged(boolean z2) {
        this.hintExpanded = z2;
        updateSuffixTextVisibility();
    }

    public void onTextInputBoxStateUpdated() {
        updateErrorIconVisibility();
        refreshErrorIconDrawableState();
        refreshEndIconDrawableState();
        if (getEndIconDelegate().shouldTintIconOnError()) {
            tintEndIconOnError(this.textInputLayout.shouldShowError());
        }
    }

    public void refreshEndIconDrawableState() {
        IconHelper.refreshIconDrawableState(this.textInputLayout, this.endIconView, this.endIconTintList);
    }

    public void refreshErrorIconDrawableState() {
        IconHelper.refreshIconDrawableState(this.textInputLayout, this.errorIconView, this.errorIconTintList);
    }

    public void refreshIconState(boolean z2) {
        boolean z3;
        boolean isActivated;
        boolean isChecked;
        EndIconDelegate endIconDelegate = getEndIconDelegate();
        boolean z4 = true;
        if (!endIconDelegate.isIconCheckable() || (isChecked = this.endIconView.isChecked()) == endIconDelegate.isIconChecked()) {
            z3 = false;
        } else {
            this.endIconView.setChecked(!isChecked);
            z3 = true;
        }
        if (!endIconDelegate.isIconActivable() || (isActivated = this.endIconView.isActivated()) == endIconDelegate.isIconActivated()) {
            z4 = z3;
        } else {
            setEndIconActivated(!isActivated);
        }
        if (z2 || z4) {
            refreshEndIconDrawableState();
        }
    }

    public void removeOnEndIconChangedListener(@NonNull TextInputLayout.OnEndIconChangedListener onEndIconChangedListener) {
        this.endIconChangedListeners.remove(onEndIconChangedListener);
    }

    public void setEndIconActivated(boolean z2) {
        this.endIconView.setActivated(z2);
    }

    public void setEndIconCheckable(boolean z2) {
        this.endIconView.setCheckable(z2);
    }

    public void setEndIconContentDescription(@StringRes int i3) {
        setEndIconContentDescription(i3 != 0 ? getResources().getText(i3) : null);
    }

    public void setEndIconDrawable(@DrawableRes int i3) {
        setEndIconDrawable(i3 != 0 ? AppCompatResources.getDrawable(getContext(), i3) : null);
    }

    public void setEndIconMinSize(@Px int i3) {
        if (i3 < 0) {
            throw new IllegalArgumentException("endIconSize cannot be less than 0");
        } else if (i3 != this.endIconMinSize) {
            this.endIconMinSize = i3;
            IconHelper.setIconMinSize(this.endIconView, i3);
            IconHelper.setIconMinSize(this.errorIconView, i3);
        }
    }

    public void setEndIconMode(int i3) {
        if (this.endIconMode != i3) {
            tearDownDelegate(getEndIconDelegate());
            int i4 = this.endIconMode;
            this.endIconMode = i3;
            dispatchOnEndIconChanged(i4);
            setEndIconVisible(i3 != 0);
            EndIconDelegate endIconDelegate = getEndIconDelegate();
            setEndIconDrawable(getIconResId(endIconDelegate));
            setEndIconContentDescription(endIconDelegate.getIconContentDescriptionResId());
            setEndIconCheckable(endIconDelegate.isIconCheckable());
            if (endIconDelegate.isBoxBackgroundModeSupported(this.textInputLayout.getBoxBackgroundMode())) {
                setUpDelegate(endIconDelegate);
                setEndIconOnClickListener(endIconDelegate.getOnIconClickListener());
                EditText editText2 = this.editText;
                if (editText2 != null) {
                    endIconDelegate.onEditTextAttached(editText2);
                    setOnFocusChangeListenersIfNeeded(endIconDelegate);
                }
                IconHelper.applyIconTint(this.textInputLayout, this.endIconView, this.endIconTintList, this.endIconTintMode);
                refreshIconState(true);
                return;
            }
            throw new IllegalStateException("The current box background mode " + this.textInputLayout.getBoxBackgroundMode() + " is not supported by the end icon mode " + i3);
        }
    }

    public void setEndIconOnClickListener(@Nullable View.OnClickListener onClickListener) {
        IconHelper.setIconOnClickListener(this.endIconView, onClickListener, this.endIconOnLongClickListener);
    }

    public void setEndIconOnLongClickListener(@Nullable View.OnLongClickListener onLongClickListener) {
        this.endIconOnLongClickListener = onLongClickListener;
        IconHelper.setIconOnLongClickListener(this.endIconView, onLongClickListener);
    }

    public void setEndIconScaleType(@NonNull ImageView.ScaleType scaleType) {
        this.endIconScaleType = scaleType;
        IconHelper.setIconScaleType(this.endIconView, scaleType);
        IconHelper.setIconScaleType(this.errorIconView, scaleType);
    }

    public void setEndIconTintList(@Nullable ColorStateList colorStateList) {
        if (this.endIconTintList != colorStateList) {
            this.endIconTintList = colorStateList;
            IconHelper.applyIconTint(this.textInputLayout, this.endIconView, colorStateList, this.endIconTintMode);
        }
    }

    public void setEndIconTintMode(@Nullable PorterDuff.Mode mode) {
        if (this.endIconTintMode != mode) {
            this.endIconTintMode = mode;
            IconHelper.applyIconTint(this.textInputLayout, this.endIconView, this.endIconTintList, mode);
        }
    }

    public void setEndIconVisible(boolean z2) {
        if (isEndIconVisible() != z2) {
            this.endIconView.setVisibility(z2 ? 0 : 8);
            updateEndLayoutVisibility();
            updateSuffixTextViewPadding();
            this.textInputLayout.updateDummyDrawables();
        }
    }

    public void setErrorIconDrawable(@DrawableRes int i3) {
        setErrorIconDrawable(i3 != 0 ? AppCompatResources.getDrawable(getContext(), i3) : null);
        refreshErrorIconDrawableState();
    }

    public void setErrorIconOnClickListener(@Nullable View.OnClickListener onClickListener) {
        IconHelper.setIconOnClickListener(this.errorIconView, onClickListener, this.errorIconOnLongClickListener);
    }

    public void setErrorIconOnLongClickListener(@Nullable View.OnLongClickListener onLongClickListener) {
        this.errorIconOnLongClickListener = onLongClickListener;
        IconHelper.setIconOnLongClickListener(this.errorIconView, onLongClickListener);
    }

    public void setErrorIconTintList(@Nullable ColorStateList colorStateList) {
        if (this.errorIconTintList != colorStateList) {
            this.errorIconTintList = colorStateList;
            IconHelper.applyIconTint(this.textInputLayout, this.errorIconView, colorStateList, this.errorIconTintMode);
        }
    }

    public void setErrorIconTintMode(@Nullable PorterDuff.Mode mode) {
        if (this.errorIconTintMode != mode) {
            this.errorIconTintMode = mode;
            IconHelper.applyIconTint(this.textInputLayout, this.errorIconView, this.errorIconTintList, mode);
        }
    }

    public void setPasswordVisibilityToggleContentDescription(@StringRes int i3) {
        setPasswordVisibilityToggleContentDescription(i3 != 0 ? getResources().getText(i3) : null);
    }

    public void setPasswordVisibilityToggleDrawable(@DrawableRes int i3) {
        setPasswordVisibilityToggleDrawable(i3 != 0 ? AppCompatResources.getDrawable(getContext(), i3) : null);
    }

    public void setPasswordVisibilityToggleEnabled(boolean z2) {
        if (z2 && this.endIconMode != 1) {
            setEndIconMode(1);
        } else if (!z2) {
            setEndIconMode(0);
        }
    }

    public void setPasswordVisibilityToggleTintList(@Nullable ColorStateList colorStateList) {
        this.endIconTintList = colorStateList;
        IconHelper.applyIconTint(this.textInputLayout, this.endIconView, colorStateList, this.endIconTintMode);
    }

    public void setPasswordVisibilityToggleTintMode(@Nullable PorterDuff.Mode mode) {
        this.endIconTintMode = mode;
        IconHelper.applyIconTint(this.textInputLayout, this.endIconView, this.endIconTintList, mode);
    }

    public void setSuffixText(@Nullable CharSequence charSequence) {
        this.suffixText = TextUtils.isEmpty(charSequence) ? null : charSequence;
        this.suffixTextView.setText(charSequence);
        updateSuffixTextVisibility();
    }

    public void setSuffixTextAppearance(@StyleRes int i3) {
        TextViewCompat.setTextAppearance(this.suffixTextView, i3);
    }

    public void setSuffixTextColor(@NonNull ColorStateList colorStateList) {
        this.suffixTextView.setTextColor(colorStateList);
    }

    public void togglePasswordVisibilityToggle(boolean z2) {
        if (this.endIconMode == 1) {
            this.endIconView.performClick();
            if (z2) {
                this.endIconView.jumpDrawablesToCurrentState();
            }
        }
    }

    public void updateSuffixTextViewPadding() {
        if (this.textInputLayout.editText != null) {
            ViewCompat.setPaddingRelative(this.suffixTextView, getContext().getResources().getDimensionPixelSize(R.dimen.material_input_text_to_prefix_suffix_padding), this.textInputLayout.editText.getPaddingTop(), (isEndIconVisible() || isErrorIconVisible()) ? 0 : ViewCompat.getPaddingEnd(this.textInputLayout.editText), this.textInputLayout.editText.getPaddingBottom());
        }
    }

    public void setEndIconContentDescription(@Nullable CharSequence charSequence) {
        if (getEndIconContentDescription() != charSequence) {
            this.endIconView.setContentDescription(charSequence);
        }
    }

    public void setEndIconDrawable(@Nullable Drawable drawable) {
        this.endIconView.setImageDrawable(drawable);
        if (drawable != null) {
            IconHelper.applyIconTint(this.textInputLayout, this.endIconView, this.endIconTintList, this.endIconTintMode);
            refreshEndIconDrawableState();
        }
    }

    public void setErrorIconDrawable(@Nullable Drawable drawable) {
        this.errorIconView.setImageDrawable(drawable);
        updateErrorIconVisibility();
        IconHelper.applyIconTint(this.textInputLayout, this.errorIconView, this.errorIconTintList, this.errorIconTintMode);
    }

    public void setPasswordVisibilityToggleContentDescription(@Nullable CharSequence charSequence) {
        this.endIconView.setContentDescription(charSequence);
    }

    public void setPasswordVisibilityToggleDrawable(@Nullable Drawable drawable) {
        this.endIconView.setImageDrawable(drawable);
    }
}
