package com.google.android.material.badge;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import androidx.annotation.AttrRes;
import androidx.annotation.ColorInt;
import androidx.annotation.Dimension;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.PluralsRes;
import androidx.annotation.Px;
import androidx.annotation.RestrictTo;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;
import androidx.annotation.StyleableRes;
import androidx.annotation.XmlRes;
import com.google.android.material.R;
import com.google.android.material.drawable.DrawableUtils;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.resources.MaterialResources;
import com.google.android.material.resources.TextAppearance;
import java.util.Locale;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class BadgeState {
    private static final String BADGE_RESOURCE_TAG = "badge";
    final float badgeHeight;
    final float badgeRadius;
    final float badgeWidth;
    final float badgeWithTextHeight;
    final float badgeWithTextRadius;
    final float badgeWithTextWidth;
    private final State currentState;
    final int horizontalInset;
    final int horizontalInsetWithText;
    int offsetAlignmentMode;
    private final State overridingState;

    public BadgeState(Context context, @XmlRes int i3, @AttrRes int i4, @StyleRes int i5, @Nullable State state) {
        State state2 = new State();
        this.currentState = state2;
        state = state == null ? new State() : state;
        if (i3 != 0) {
            int unused = state.badgeResId = i3;
        }
        TypedArray generateTypedArray = generateTypedArray(context, state.badgeResId, i4, i5);
        Resources resources = context.getResources();
        this.badgeRadius = (float) generateTypedArray.getDimensionPixelSize(R.styleable.Badge_badgeRadius, -1);
        this.horizontalInset = context.getResources().getDimensionPixelSize(R.dimen.mtrl_badge_horizontal_edge_offset);
        this.horizontalInsetWithText = context.getResources().getDimensionPixelSize(R.dimen.mtrl_badge_text_horizontal_edge_offset);
        this.badgeWithTextRadius = (float) generateTypedArray.getDimensionPixelSize(R.styleable.Badge_badgeWithTextRadius, -1);
        int i6 = R.styleable.Badge_badgeWidth;
        int i7 = R.dimen.m3_badge_size;
        this.badgeWidth = generateTypedArray.getDimension(i6, resources.getDimension(i7));
        int i8 = R.styleable.Badge_badgeWithTextWidth;
        int i9 = R.dimen.m3_badge_with_text_size;
        this.badgeWithTextWidth = generateTypedArray.getDimension(i8, resources.getDimension(i9));
        this.badgeHeight = generateTypedArray.getDimension(R.styleable.Badge_badgeHeight, resources.getDimension(i7));
        this.badgeWithTextHeight = generateTypedArray.getDimension(R.styleable.Badge_badgeWithTextHeight, resources.getDimension(i9));
        boolean z2 = true;
        this.offsetAlignmentMode = generateTypedArray.getInt(R.styleable.Badge_offsetAlignmentMode, 1);
        int unused2 = state2.alpha = state.alpha == -2 ? 255 : state.alpha;
        if (state.number != -2) {
            int unused3 = state2.number = state.number;
        } else {
            int i10 = R.styleable.Badge_number;
            if (generateTypedArray.hasValue(i10)) {
                int unused4 = state2.number = generateTypedArray.getInt(i10, 0);
            } else {
                int unused5 = state2.number = -1;
            }
        }
        if (state.text != null) {
            String unused6 = state2.text = state.text;
        } else {
            int i11 = R.styleable.Badge_badgeText;
            if (generateTypedArray.hasValue(i11)) {
                String unused7 = state2.text = generateTypedArray.getString(i11);
            }
        }
        CharSequence unused8 = state2.contentDescriptionForText = state.contentDescriptionForText;
        CharSequence unused9 = state2.contentDescriptionNumberless = state.contentDescriptionNumberless == null ? context.getString(R.string.mtrl_badge_numberless_content_description) : state.contentDescriptionNumberless;
        int unused10 = state2.contentDescriptionQuantityStrings = state.contentDescriptionQuantityStrings == 0 ? R.plurals.mtrl_badge_content_description : state.contentDescriptionQuantityStrings;
        int unused11 = state2.contentDescriptionExceedsMaxBadgeNumberRes = state.contentDescriptionExceedsMaxBadgeNumberRes == 0 ? R.string.mtrl_exceed_max_badge_number_content_description : state.contentDescriptionExceedsMaxBadgeNumberRes;
        if (state.isVisible != null && !state.isVisible.booleanValue()) {
            z2 = false;
        }
        Boolean unused12 = state2.isVisible = Boolean.valueOf(z2);
        int unused13 = state2.maxCharacterCount = state.maxCharacterCount == -2 ? generateTypedArray.getInt(R.styleable.Badge_maxCharacterCount, -2) : state.maxCharacterCount;
        int unused14 = state2.maxNumber = state.maxNumber == -2 ? generateTypedArray.getInt(R.styleable.Badge_maxNumber, -2) : state.maxNumber;
        Integer unused15 = state2.badgeShapeAppearanceResId = Integer.valueOf(state.badgeShapeAppearanceResId == null ? generateTypedArray.getResourceId(R.styleable.Badge_badgeShapeAppearance, R.style.ShapeAppearance_M3_Sys_Shape_Corner_Full) : state.badgeShapeAppearanceResId.intValue());
        Integer unused16 = state2.badgeShapeAppearanceOverlayResId = Integer.valueOf(state.badgeShapeAppearanceOverlayResId == null ? generateTypedArray.getResourceId(R.styleable.Badge_badgeShapeAppearanceOverlay, 0) : state.badgeShapeAppearanceOverlayResId.intValue());
        Integer unused17 = state2.badgeWithTextShapeAppearanceResId = Integer.valueOf(state.badgeWithTextShapeAppearanceResId == null ? generateTypedArray.getResourceId(R.styleable.Badge_badgeWithTextShapeAppearance, R.style.ShapeAppearance_M3_Sys_Shape_Corner_Full) : state.badgeWithTextShapeAppearanceResId.intValue());
        Integer unused18 = state2.badgeWithTextShapeAppearanceOverlayResId = Integer.valueOf(state.badgeWithTextShapeAppearanceOverlayResId == null ? generateTypedArray.getResourceId(R.styleable.Badge_badgeWithTextShapeAppearanceOverlay, 0) : state.badgeWithTextShapeAppearanceOverlayResId.intValue());
        Integer unused19 = state2.backgroundColor = Integer.valueOf(state.backgroundColor == null ? readColorFromAttributes(context, generateTypedArray, R.styleable.Badge_backgroundColor) : state.backgroundColor.intValue());
        Integer unused20 = state2.badgeTextAppearanceResId = Integer.valueOf(state.badgeTextAppearanceResId == null ? generateTypedArray.getResourceId(R.styleable.Badge_badgeTextAppearance, R.style.TextAppearance_MaterialComponents_Badge) : state.badgeTextAppearanceResId.intValue());
        if (state.badgeTextColor != null) {
            Integer unused21 = state2.badgeTextColor = state.badgeTextColor;
        } else {
            int i12 = R.styleable.Badge_badgeTextColor;
            if (generateTypedArray.hasValue(i12)) {
                Integer unused22 = state2.badgeTextColor = Integer.valueOf(readColorFromAttributes(context, generateTypedArray, i12));
            } else {
                Integer unused23 = state2.badgeTextColor = Integer.valueOf(new TextAppearance(context, state2.badgeTextAppearanceResId.intValue()).getTextColor().getDefaultColor());
            }
        }
        Integer unused24 = state2.badgeGravity = Integer.valueOf(state.badgeGravity == null ? generateTypedArray.getInt(R.styleable.Badge_badgeGravity, 8388661) : state.badgeGravity.intValue());
        Integer unused25 = state2.badgeHorizontalPadding = Integer.valueOf(state.badgeHorizontalPadding == null ? generateTypedArray.getDimensionPixelSize(R.styleable.Badge_badgeWidePadding, resources.getDimensionPixelSize(R.dimen.mtrl_badge_long_text_horizontal_padding)) : state.badgeHorizontalPadding.intValue());
        Integer unused26 = state2.badgeVerticalPadding = Integer.valueOf(state.badgeVerticalPadding == null ? generateTypedArray.getDimensionPixelSize(R.styleable.Badge_badgeVerticalPadding, resources.getDimensionPixelSize(R.dimen.m3_badge_with_text_vertical_padding)) : state.badgeVerticalPadding.intValue());
        Integer unused27 = state2.horizontalOffsetWithoutText = Integer.valueOf(state.horizontalOffsetWithoutText == null ? generateTypedArray.getDimensionPixelOffset(R.styleable.Badge_horizontalOffset, 0) : state.horizontalOffsetWithoutText.intValue());
        Integer unused28 = state2.verticalOffsetWithoutText = Integer.valueOf(state.verticalOffsetWithoutText == null ? generateTypedArray.getDimensionPixelOffset(R.styleable.Badge_verticalOffset, 0) : state.verticalOffsetWithoutText.intValue());
        Integer unused29 = state2.horizontalOffsetWithText = Integer.valueOf(state.horizontalOffsetWithText == null ? generateTypedArray.getDimensionPixelOffset(R.styleable.Badge_horizontalOffsetWithText, state2.horizontalOffsetWithoutText.intValue()) : state.horizontalOffsetWithText.intValue());
        Integer unused30 = state2.verticalOffsetWithText = Integer.valueOf(state.verticalOffsetWithText == null ? generateTypedArray.getDimensionPixelOffset(R.styleable.Badge_verticalOffsetWithText, state2.verticalOffsetWithoutText.intValue()) : state.verticalOffsetWithText.intValue());
        Integer unused31 = state2.largeFontVerticalOffsetAdjustment = Integer.valueOf(state.largeFontVerticalOffsetAdjustment == null ? generateTypedArray.getDimensionPixelOffset(R.styleable.Badge_largeFontVerticalOffsetAdjustment, 0) : state.largeFontVerticalOffsetAdjustment.intValue());
        Integer unused32 = state2.additionalHorizontalOffset = Integer.valueOf(state.additionalHorizontalOffset == null ? 0 : state.additionalHorizontalOffset.intValue());
        Integer unused33 = state2.additionalVerticalOffset = Integer.valueOf(state.additionalVerticalOffset == null ? 0 : state.additionalVerticalOffset.intValue());
        Boolean unused34 = state2.autoAdjustToWithinGrandparentBounds = Boolean.valueOf(state.autoAdjustToWithinGrandparentBounds == null ? generateTypedArray.getBoolean(R.styleable.Badge_autoAdjustToWithinGrandparentBounds, false) : state.autoAdjustToWithinGrandparentBounds.booleanValue());
        generateTypedArray.recycle();
        if (state.numberLocale == null) {
            Locale unused35 = state2.numberLocale = Locale.getDefault(Locale.Category.FORMAT);
        } else {
            Locale unused36 = state2.numberLocale = state.numberLocale;
        }
        this.overridingState = state;
    }

    private TypedArray generateTypedArray(Context context, @XmlRes int i3, @AttrRes int i4, @StyleRes int i5) {
        AttributeSet attributeSet;
        int i6;
        if (i3 != 0) {
            attributeSet = DrawableUtils.parseDrawableXml(context, i3, BADGE_RESOURCE_TAG);
            i6 = attributeSet.getStyleAttribute();
        } else {
            attributeSet = null;
            i6 = 0;
        }
        return ThemeEnforcement.obtainStyledAttributes(context, attributeSet, R.styleable.Badge, i4, i6 == 0 ? i5 : i6, new int[0]);
    }

    private static int readColorFromAttributes(Context context, @NonNull TypedArray typedArray, @StyleableRes int i3) {
        return MaterialResources.getColorStateList(context, typedArray, i3).getDefaultColor();
    }

    public void clearNumber() {
        setNumber(-1);
    }

    public void clearText() {
        setText((String) null);
    }

    @Dimension(unit = 1)
    public int getAdditionalHorizontalOffset() {
        return this.currentState.additionalHorizontalOffset.intValue();
    }

    @Dimension(unit = 1)
    public int getAdditionalVerticalOffset() {
        return this.currentState.additionalVerticalOffset.intValue();
    }

    public int getAlpha() {
        return this.currentState.alpha;
    }

    @ColorInt
    public int getBackgroundColor() {
        return this.currentState.backgroundColor.intValue();
    }

    public int getBadgeGravity() {
        return this.currentState.badgeGravity.intValue();
    }

    @Px
    public int getBadgeHorizontalPadding() {
        return this.currentState.badgeHorizontalPadding.intValue();
    }

    public int getBadgeShapeAppearanceOverlayResId() {
        return this.currentState.badgeShapeAppearanceOverlayResId.intValue();
    }

    public int getBadgeShapeAppearanceResId() {
        return this.currentState.badgeShapeAppearanceResId.intValue();
    }

    @ColorInt
    public int getBadgeTextColor() {
        return this.currentState.badgeTextColor.intValue();
    }

    @Px
    public int getBadgeVerticalPadding() {
        return this.currentState.badgeVerticalPadding.intValue();
    }

    public int getBadgeWithTextShapeAppearanceOverlayResId() {
        return this.currentState.badgeWithTextShapeAppearanceOverlayResId.intValue();
    }

    public int getBadgeWithTextShapeAppearanceResId() {
        return this.currentState.badgeWithTextShapeAppearanceResId.intValue();
    }

    @StringRes
    public int getContentDescriptionExceedsMaxBadgeNumberStringResource() {
        return this.currentState.contentDescriptionExceedsMaxBadgeNumberRes;
    }

    public CharSequence getContentDescriptionForText() {
        return this.currentState.contentDescriptionForText;
    }

    public CharSequence getContentDescriptionNumberless() {
        return this.currentState.contentDescriptionNumberless;
    }

    @PluralsRes
    public int getContentDescriptionQuantityStrings() {
        return this.currentState.contentDescriptionQuantityStrings;
    }

    @Dimension(unit = 1)
    public int getHorizontalOffsetWithText() {
        return this.currentState.horizontalOffsetWithText.intValue();
    }

    @Dimension(unit = 1)
    public int getHorizontalOffsetWithoutText() {
        return this.currentState.horizontalOffsetWithoutText.intValue();
    }

    @Dimension(unit = 1)
    public int getLargeFontVerticalOffsetAdjustment() {
        return this.currentState.largeFontVerticalOffsetAdjustment.intValue();
    }

    public int getMaxCharacterCount() {
        return this.currentState.maxCharacterCount;
    }

    public int getMaxNumber() {
        return this.currentState.maxNumber;
    }

    public int getNumber() {
        return this.currentState.number;
    }

    public Locale getNumberLocale() {
        return this.currentState.numberLocale;
    }

    public State getOverridingState() {
        return this.overridingState;
    }

    public String getText() {
        return this.currentState.text;
    }

    @StyleRes
    public int getTextAppearanceResId() {
        return this.currentState.badgeTextAppearanceResId.intValue();
    }

    @Dimension(unit = 1)
    public int getVerticalOffsetWithText() {
        return this.currentState.verticalOffsetWithText.intValue();
    }

    @Dimension(unit = 1)
    public int getVerticalOffsetWithoutText() {
        return this.currentState.verticalOffsetWithoutText.intValue();
    }

    public boolean hasNumber() {
        return this.currentState.number != -1;
    }

    public boolean hasText() {
        return this.currentState.text != null;
    }

    public boolean isAutoAdjustedToGrandparentBounds() {
        return this.currentState.autoAdjustToWithinGrandparentBounds.booleanValue();
    }

    public boolean isVisible() {
        return this.currentState.isVisible.booleanValue();
    }

    public void setAdditionalHorizontalOffset(@Dimension(unit = 1) int i3) {
        Integer unused = this.overridingState.additionalHorizontalOffset = Integer.valueOf(i3);
        Integer unused2 = this.currentState.additionalHorizontalOffset = Integer.valueOf(i3);
    }

    public void setAdditionalVerticalOffset(@Dimension(unit = 1) int i3) {
        Integer unused = this.overridingState.additionalVerticalOffset = Integer.valueOf(i3);
        Integer unused2 = this.currentState.additionalVerticalOffset = Integer.valueOf(i3);
    }

    public void setAlpha(int i3) {
        int unused = this.overridingState.alpha = i3;
        int unused2 = this.currentState.alpha = i3;
    }

    public void setAutoAdjustToGrandparentBounds(boolean z2) {
        Boolean unused = this.overridingState.autoAdjustToWithinGrandparentBounds = Boolean.valueOf(z2);
        Boolean unused2 = this.currentState.autoAdjustToWithinGrandparentBounds = Boolean.valueOf(z2);
    }

    public void setBackgroundColor(@ColorInt int i3) {
        Integer unused = this.overridingState.backgroundColor = Integer.valueOf(i3);
        Integer unused2 = this.currentState.backgroundColor = Integer.valueOf(i3);
    }

    public void setBadgeGravity(int i3) {
        Integer unused = this.overridingState.badgeGravity = Integer.valueOf(i3);
        Integer unused2 = this.currentState.badgeGravity = Integer.valueOf(i3);
    }

    public void setBadgeHorizontalPadding(@Px int i3) {
        Integer unused = this.overridingState.badgeHorizontalPadding = Integer.valueOf(i3);
        Integer unused2 = this.currentState.badgeHorizontalPadding = Integer.valueOf(i3);
    }

    public void setBadgeShapeAppearanceOverlayResId(int i3) {
        Integer unused = this.overridingState.badgeShapeAppearanceOverlayResId = Integer.valueOf(i3);
        Integer unused2 = this.currentState.badgeShapeAppearanceOverlayResId = Integer.valueOf(i3);
    }

    public void setBadgeShapeAppearanceResId(int i3) {
        Integer unused = this.overridingState.badgeShapeAppearanceResId = Integer.valueOf(i3);
        Integer unused2 = this.currentState.badgeShapeAppearanceResId = Integer.valueOf(i3);
    }

    public void setBadgeTextColor(@ColorInt int i3) {
        Integer unused = this.overridingState.badgeTextColor = Integer.valueOf(i3);
        Integer unused2 = this.currentState.badgeTextColor = Integer.valueOf(i3);
    }

    public void setBadgeVerticalPadding(@Px int i3) {
        Integer unused = this.overridingState.badgeVerticalPadding = Integer.valueOf(i3);
        Integer unused2 = this.currentState.badgeVerticalPadding = Integer.valueOf(i3);
    }

    public void setBadgeWithTextShapeAppearanceOverlayResId(int i3) {
        Integer unused = this.overridingState.badgeWithTextShapeAppearanceOverlayResId = Integer.valueOf(i3);
        Integer unused2 = this.currentState.badgeWithTextShapeAppearanceOverlayResId = Integer.valueOf(i3);
    }

    public void setBadgeWithTextShapeAppearanceResId(int i3) {
        Integer unused = this.overridingState.badgeWithTextShapeAppearanceResId = Integer.valueOf(i3);
        Integer unused2 = this.currentState.badgeWithTextShapeAppearanceResId = Integer.valueOf(i3);
    }

    public void setContentDescriptionExceedsMaxBadgeNumberStringResource(@StringRes int i3) {
        int unused = this.overridingState.contentDescriptionExceedsMaxBadgeNumberRes = i3;
        int unused2 = this.currentState.contentDescriptionExceedsMaxBadgeNumberRes = i3;
    }

    public void setContentDescriptionForText(CharSequence charSequence) {
        CharSequence unused = this.overridingState.contentDescriptionForText = charSequence;
        CharSequence unused2 = this.currentState.contentDescriptionForText = charSequence;
    }

    public void setContentDescriptionNumberless(CharSequence charSequence) {
        CharSequence unused = this.overridingState.contentDescriptionNumberless = charSequence;
        CharSequence unused2 = this.currentState.contentDescriptionNumberless = charSequence;
    }

    public void setContentDescriptionQuantityStringsResource(@PluralsRes int i3) {
        int unused = this.overridingState.contentDescriptionQuantityStrings = i3;
        int unused2 = this.currentState.contentDescriptionQuantityStrings = i3;
    }

    public void setHorizontalOffsetWithText(@Dimension(unit = 1) int i3) {
        Integer unused = this.overridingState.horizontalOffsetWithText = Integer.valueOf(i3);
        Integer unused2 = this.currentState.horizontalOffsetWithText = Integer.valueOf(i3);
    }

    public void setHorizontalOffsetWithoutText(@Dimension(unit = 1) int i3) {
        Integer unused = this.overridingState.horizontalOffsetWithoutText = Integer.valueOf(i3);
        Integer unused2 = this.currentState.horizontalOffsetWithoutText = Integer.valueOf(i3);
    }

    public void setLargeFontVerticalOffsetAdjustment(@Dimension(unit = 1) int i3) {
        Integer unused = this.overridingState.largeFontVerticalOffsetAdjustment = Integer.valueOf(i3);
        Integer unused2 = this.currentState.largeFontVerticalOffsetAdjustment = Integer.valueOf(i3);
    }

    public void setMaxCharacterCount(int i3) {
        int unused = this.overridingState.maxCharacterCount = i3;
        int unused2 = this.currentState.maxCharacterCount = i3;
    }

    public void setMaxNumber(int i3) {
        int unused = this.overridingState.maxNumber = i3;
        int unused2 = this.currentState.maxNumber = i3;
    }

    public void setNumber(int i3) {
        int unused = this.overridingState.number = i3;
        int unused2 = this.currentState.number = i3;
    }

    public void setNumberLocale(Locale locale) {
        Locale unused = this.overridingState.numberLocale = locale;
        Locale unused2 = this.currentState.numberLocale = locale;
    }

    public void setText(String str) {
        String unused = this.overridingState.text = str;
        String unused2 = this.currentState.text = str;
    }

    public void setTextAppearanceResId(@StyleRes int i3) {
        Integer unused = this.overridingState.badgeTextAppearanceResId = Integer.valueOf(i3);
        Integer unused2 = this.currentState.badgeTextAppearanceResId = Integer.valueOf(i3);
    }

    public void setVerticalOffsetWithText(@Dimension(unit = 1) int i3) {
        Integer unused = this.overridingState.verticalOffsetWithText = Integer.valueOf(i3);
        Integer unused2 = this.currentState.verticalOffsetWithText = Integer.valueOf(i3);
    }

    public void setVerticalOffsetWithoutText(@Dimension(unit = 1) int i3) {
        Integer unused = this.overridingState.verticalOffsetWithoutText = Integer.valueOf(i3);
        Integer unused2 = this.currentState.verticalOffsetWithoutText = Integer.valueOf(i3);
    }

    public void setVisible(boolean z2) {
        Boolean unused = this.overridingState.isVisible = Boolean.valueOf(z2);
        Boolean unused2 = this.currentState.isVisible = Boolean.valueOf(z2);
    }

    public static final class State implements Parcelable {
        private static final int BADGE_NUMBER_NONE = -1;
        public static final Parcelable.Creator<State> CREATOR = new Parcelable.Creator<State>() {
            @NonNull
            public State createFromParcel(@NonNull Parcel parcel) {
                return new State(parcel);
            }

            @NonNull
            public State[] newArray(int i3) {
                return new State[i3];
            }
        };
        private static final int NOT_SET = -2;
        /* access modifiers changed from: private */
        @Dimension(unit = 1)
        public Integer additionalHorizontalOffset;
        /* access modifiers changed from: private */
        @Dimension(unit = 1)
        public Integer additionalVerticalOffset;
        /* access modifiers changed from: private */
        public int alpha = 255;
        /* access modifiers changed from: private */
        public Boolean autoAdjustToWithinGrandparentBounds;
        /* access modifiers changed from: private */
        @ColorInt
        public Integer backgroundColor;
        /* access modifiers changed from: private */
        public Integer badgeGravity;
        /* access modifiers changed from: private */
        @Px
        public Integer badgeHorizontalPadding;
        /* access modifiers changed from: private */
        @XmlRes
        public int badgeResId;
        /* access modifiers changed from: private */
        @StyleRes
        public Integer badgeShapeAppearanceOverlayResId;
        /* access modifiers changed from: private */
        @StyleRes
        public Integer badgeShapeAppearanceResId;
        /* access modifiers changed from: private */
        @StyleRes
        public Integer badgeTextAppearanceResId;
        /* access modifiers changed from: private */
        @ColorInt
        public Integer badgeTextColor;
        /* access modifiers changed from: private */
        @Px
        public Integer badgeVerticalPadding;
        /* access modifiers changed from: private */
        @StyleRes
        public Integer badgeWithTextShapeAppearanceOverlayResId;
        /* access modifiers changed from: private */
        @StyleRes
        public Integer badgeWithTextShapeAppearanceResId;
        /* access modifiers changed from: private */
        @StringRes
        public int contentDescriptionExceedsMaxBadgeNumberRes;
        /* access modifiers changed from: private */
        @Nullable
        public CharSequence contentDescriptionForText;
        /* access modifiers changed from: private */
        @Nullable
        public CharSequence contentDescriptionNumberless;
        /* access modifiers changed from: private */
        @PluralsRes
        public int contentDescriptionQuantityStrings;
        /* access modifiers changed from: private */
        @Dimension(unit = 1)
        public Integer horizontalOffsetWithText;
        /* access modifiers changed from: private */
        @Dimension(unit = 1)
        public Integer horizontalOffsetWithoutText;
        /* access modifiers changed from: private */
        public Boolean isVisible = Boolean.TRUE;
        /* access modifiers changed from: private */
        @Dimension(unit = 1)
        public Integer largeFontVerticalOffsetAdjustment;
        /* access modifiers changed from: private */
        public int maxCharacterCount = -2;
        /* access modifiers changed from: private */
        public int maxNumber = -2;
        /* access modifiers changed from: private */
        public int number = -2;
        /* access modifiers changed from: private */
        public Locale numberLocale;
        /* access modifiers changed from: private */
        @Nullable
        public String text;
        /* access modifiers changed from: private */
        @Dimension(unit = 1)
        public Integer verticalOffsetWithText;
        /* access modifiers changed from: private */
        @Dimension(unit = 1)
        public Integer verticalOffsetWithoutText;

        public State() {
        }

        public int describeContents() {
            return 0;
        }

        public void writeToParcel(@NonNull Parcel parcel, int i3) {
            parcel.writeInt(this.badgeResId);
            parcel.writeSerializable(this.backgroundColor);
            parcel.writeSerializable(this.badgeTextColor);
            parcel.writeSerializable(this.badgeTextAppearanceResId);
            parcel.writeSerializable(this.badgeShapeAppearanceResId);
            parcel.writeSerializable(this.badgeShapeAppearanceOverlayResId);
            parcel.writeSerializable(this.badgeWithTextShapeAppearanceResId);
            parcel.writeSerializable(this.badgeWithTextShapeAppearanceOverlayResId);
            parcel.writeInt(this.alpha);
            parcel.writeString(this.text);
            parcel.writeInt(this.number);
            parcel.writeInt(this.maxCharacterCount);
            parcel.writeInt(this.maxNumber);
            CharSequence charSequence = this.contentDescriptionForText;
            String str = null;
            parcel.writeString(charSequence != null ? charSequence.toString() : null);
            CharSequence charSequence2 = this.contentDescriptionNumberless;
            if (charSequence2 != null) {
                str = charSequence2.toString();
            }
            parcel.writeString(str);
            parcel.writeInt(this.contentDescriptionQuantityStrings);
            parcel.writeSerializable(this.badgeGravity);
            parcel.writeSerializable(this.badgeHorizontalPadding);
            parcel.writeSerializable(this.badgeVerticalPadding);
            parcel.writeSerializable(this.horizontalOffsetWithoutText);
            parcel.writeSerializable(this.verticalOffsetWithoutText);
            parcel.writeSerializable(this.horizontalOffsetWithText);
            parcel.writeSerializable(this.verticalOffsetWithText);
            parcel.writeSerializable(this.largeFontVerticalOffsetAdjustment);
            parcel.writeSerializable(this.additionalHorizontalOffset);
            parcel.writeSerializable(this.additionalVerticalOffset);
            parcel.writeSerializable(this.isVisible);
            parcel.writeSerializable(this.numberLocale);
            parcel.writeSerializable(this.autoAdjustToWithinGrandparentBounds);
        }

        public State(@NonNull Parcel parcel) {
            this.badgeResId = parcel.readInt();
            this.backgroundColor = (Integer) parcel.readSerializable();
            this.badgeTextColor = (Integer) parcel.readSerializable();
            this.badgeTextAppearanceResId = (Integer) parcel.readSerializable();
            this.badgeShapeAppearanceResId = (Integer) parcel.readSerializable();
            this.badgeShapeAppearanceOverlayResId = (Integer) parcel.readSerializable();
            this.badgeWithTextShapeAppearanceResId = (Integer) parcel.readSerializable();
            this.badgeWithTextShapeAppearanceOverlayResId = (Integer) parcel.readSerializable();
            this.alpha = parcel.readInt();
            this.text = parcel.readString();
            this.number = parcel.readInt();
            this.maxCharacterCount = parcel.readInt();
            this.maxNumber = parcel.readInt();
            this.contentDescriptionForText = parcel.readString();
            this.contentDescriptionNumberless = parcel.readString();
            this.contentDescriptionQuantityStrings = parcel.readInt();
            this.badgeGravity = (Integer) parcel.readSerializable();
            this.badgeHorizontalPadding = (Integer) parcel.readSerializable();
            this.badgeVerticalPadding = (Integer) parcel.readSerializable();
            this.horizontalOffsetWithoutText = (Integer) parcel.readSerializable();
            this.verticalOffsetWithoutText = (Integer) parcel.readSerializable();
            this.horizontalOffsetWithText = (Integer) parcel.readSerializable();
            this.verticalOffsetWithText = (Integer) parcel.readSerializable();
            this.largeFontVerticalOffsetAdjustment = (Integer) parcel.readSerializable();
            this.additionalHorizontalOffset = (Integer) parcel.readSerializable();
            this.additionalVerticalOffset = (Integer) parcel.readSerializable();
            this.isVisible = (Boolean) parcel.readSerializable();
            this.numberLocale = (Locale) parcel.readSerializable();
            this.autoAdjustToWithinGrandparentBounds = (Boolean) parcel.readSerializable();
        }
    }
}
