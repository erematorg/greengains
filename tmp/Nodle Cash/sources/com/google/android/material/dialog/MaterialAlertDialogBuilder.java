package com.google.android.material.dialog;

import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.database.Cursor;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import androidx.annotation.ArrayRes;
import androidx.annotation.AttrRes;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.annotation.StringRes;
import androidx.annotation.StyleRes;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.core.view.ViewCompat;
import com.google.android.material.R;
import com.google.android.material.color.MaterialColors;
import com.google.android.material.resources.MaterialAttributes;
import com.google.android.material.shape.MaterialShapeDrawable;
import com.google.android.material.theme.overlay.MaterialThemeOverlay;
import com.google.errorprone.annotations.CanIgnoreReturnValue;

public class MaterialAlertDialogBuilder extends AlertDialog.Builder {
    @AttrRes
    private static final int DEF_STYLE_ATTR = R.attr.alertDialogStyle;
    @StyleRes
    private static final int DEF_STYLE_RES = R.style.MaterialAlertDialog_MaterialComponents;
    @AttrRes
    private static final int MATERIAL_ALERT_DIALOG_THEME_OVERLAY = R.attr.materialAlertDialogTheme;
    @Nullable
    private Drawable background;
    @NonNull
    private final Rect backgroundInsets;

    public MaterialAlertDialogBuilder(@NonNull Context context) {
        this(context, 0);
    }

    private static Context createMaterialAlertDialogThemedContext(@NonNull Context context) {
        int materialAlertDialogThemeOverlay = getMaterialAlertDialogThemeOverlay(context);
        Context wrap = MaterialThemeOverlay.wrap(context, (AttributeSet) null, DEF_STYLE_ATTR, DEF_STYLE_RES);
        return materialAlertDialogThemeOverlay == 0 ? wrap : new ContextThemeWrapper(wrap, materialAlertDialogThemeOverlay);
    }

    private static int getMaterialAlertDialogThemeOverlay(@NonNull Context context) {
        TypedValue resolve = MaterialAttributes.resolve(context, MATERIAL_ALERT_DIALOG_THEME_OVERLAY);
        if (resolve == null) {
            return 0;
        }
        return resolve.data;
    }

    private static int getOverridingThemeResId(@NonNull Context context, int i3) {
        return i3 == 0 ? getMaterialAlertDialogThemeOverlay(context) : i3;
    }

    @NonNull
    public AlertDialog create() {
        AlertDialog create = super.create();
        Window window = create.getWindow();
        View decorView = window.getDecorView();
        Drawable drawable = this.background;
        if (drawable instanceof MaterialShapeDrawable) {
            ((MaterialShapeDrawable) drawable).setElevation(ViewCompat.getElevation(decorView));
        }
        window.setBackgroundDrawable(MaterialDialogs.insetDrawable(this.background, this.backgroundInsets));
        decorView.setOnTouchListener(new InsetDialogOnTouchListener(create, this.backgroundInsets));
        return create;
    }

    @Nullable
    public Drawable getBackground() {
        return this.background;
    }

    @NonNull
    @CanIgnoreReturnValue
    public MaterialAlertDialogBuilder setBackground(@Nullable Drawable drawable) {
        this.background = drawable;
        return this;
    }

    @NonNull
    @CanIgnoreReturnValue
    public MaterialAlertDialogBuilder setBackgroundInsetBottom(@Px int i3) {
        this.backgroundInsets.bottom = i3;
        return this;
    }

    @NonNull
    @CanIgnoreReturnValue
    public MaterialAlertDialogBuilder setBackgroundInsetEnd(@Px int i3) {
        if (getContext().getResources().getConfiguration().getLayoutDirection() == 1) {
            this.backgroundInsets.left = i3;
        } else {
            this.backgroundInsets.right = i3;
        }
        return this;
    }

    @NonNull
    @CanIgnoreReturnValue
    public MaterialAlertDialogBuilder setBackgroundInsetStart(@Px int i3) {
        if (getContext().getResources().getConfiguration().getLayoutDirection() == 1) {
            this.backgroundInsets.right = i3;
        } else {
            this.backgroundInsets.left = i3;
        }
        return this;
    }

    @NonNull
    @CanIgnoreReturnValue
    public MaterialAlertDialogBuilder setBackgroundInsetTop(@Px int i3) {
        this.backgroundInsets.top = i3;
        return this;
    }

    public MaterialAlertDialogBuilder(@NonNull Context context, int i3) {
        super(createMaterialAlertDialogThemedContext(context), getOverridingThemeResId(context, i3));
        Context context2 = getContext();
        Resources.Theme theme = context2.getTheme();
        int i4 = DEF_STYLE_ATTR;
        int i5 = DEF_STYLE_RES;
        this.backgroundInsets = MaterialDialogs.getDialogBackgroundInsets(context2, i4, i5);
        int color = MaterialColors.getColor(context2, R.attr.colorSurface, getClass().getCanonicalName());
        TypedArray obtainStyledAttributes = context2.obtainStyledAttributes((AttributeSet) null, R.styleable.MaterialAlertDialog, i4, i5);
        int color2 = obtainStyledAttributes.getColor(R.styleable.MaterialAlertDialog_backgroundTint, color);
        obtainStyledAttributes.recycle();
        MaterialShapeDrawable materialShapeDrawable = new MaterialShapeDrawable(context2, (AttributeSet) null, i4, i5);
        materialShapeDrawable.initializeElevationOverlay(context2);
        materialShapeDrawable.setFillColor(ColorStateList.valueOf(color2));
        TypedValue typedValue = new TypedValue();
        theme.resolveAttribute(16844145, typedValue, true);
        float dimension = typedValue.getDimension(getContext().getResources().getDisplayMetrics());
        if (typedValue.type == 5 && dimension >= 0.0f) {
            materialShapeDrawable.setCornerSize(dimension);
        }
        this.background = materialShapeDrawable;
    }

    @NonNull
    @CanIgnoreReturnValue
    public MaterialAlertDialogBuilder setAdapter(@Nullable ListAdapter listAdapter, @Nullable DialogInterface.OnClickListener onClickListener) {
        return (MaterialAlertDialogBuilder) super.setAdapter(listAdapter, onClickListener);
    }

    @NonNull
    @CanIgnoreReturnValue
    public MaterialAlertDialogBuilder setCancelable(boolean z2) {
        return (MaterialAlertDialogBuilder) super.setCancelable(z2);
    }

    @NonNull
    @CanIgnoreReturnValue
    public MaterialAlertDialogBuilder setCursor(@Nullable Cursor cursor, @Nullable DialogInterface.OnClickListener onClickListener, @NonNull String str) {
        return (MaterialAlertDialogBuilder) super.setCursor(cursor, onClickListener, str);
    }

    @NonNull
    @CanIgnoreReturnValue
    public MaterialAlertDialogBuilder setCustomTitle(@Nullable View view) {
        return (MaterialAlertDialogBuilder) super.setCustomTitle(view);
    }

    @NonNull
    @CanIgnoreReturnValue
    public MaterialAlertDialogBuilder setIconAttribute(@AttrRes int i3) {
        return (MaterialAlertDialogBuilder) super.setIconAttribute(i3);
    }

    @NonNull
    @CanIgnoreReturnValue
    public MaterialAlertDialogBuilder setNegativeButtonIcon(@Nullable Drawable drawable) {
        return (MaterialAlertDialogBuilder) super.setNegativeButtonIcon(drawable);
    }

    @NonNull
    @CanIgnoreReturnValue
    public MaterialAlertDialogBuilder setNeutralButtonIcon(@Nullable Drawable drawable) {
        return (MaterialAlertDialogBuilder) super.setNeutralButtonIcon(drawable);
    }

    @NonNull
    @CanIgnoreReturnValue
    public MaterialAlertDialogBuilder setOnCancelListener(@Nullable DialogInterface.OnCancelListener onCancelListener) {
        return (MaterialAlertDialogBuilder) super.setOnCancelListener(onCancelListener);
    }

    @NonNull
    @CanIgnoreReturnValue
    public MaterialAlertDialogBuilder setOnDismissListener(@Nullable DialogInterface.OnDismissListener onDismissListener) {
        return (MaterialAlertDialogBuilder) super.setOnDismissListener(onDismissListener);
    }

    @NonNull
    @CanIgnoreReturnValue
    public MaterialAlertDialogBuilder setOnItemSelectedListener(@Nullable AdapterView.OnItemSelectedListener onItemSelectedListener) {
        return (MaterialAlertDialogBuilder) super.setOnItemSelectedListener(onItemSelectedListener);
    }

    @NonNull
    @CanIgnoreReturnValue
    public MaterialAlertDialogBuilder setOnKeyListener(@Nullable DialogInterface.OnKeyListener onKeyListener) {
        return (MaterialAlertDialogBuilder) super.setOnKeyListener(onKeyListener);
    }

    @NonNull
    @CanIgnoreReturnValue
    public MaterialAlertDialogBuilder setPositiveButtonIcon(@Nullable Drawable drawable) {
        return (MaterialAlertDialogBuilder) super.setPositiveButtonIcon(drawable);
    }

    @NonNull
    @CanIgnoreReturnValue
    public MaterialAlertDialogBuilder setIcon(@DrawableRes int i3) {
        return (MaterialAlertDialogBuilder) super.setIcon(i3);
    }

    @NonNull
    @CanIgnoreReturnValue
    public MaterialAlertDialogBuilder setItems(@ArrayRes int i3, @Nullable DialogInterface.OnClickListener onClickListener) {
        return (MaterialAlertDialogBuilder) super.setItems(i3, onClickListener);
    }

    @NonNull
    @CanIgnoreReturnValue
    public MaterialAlertDialogBuilder setMessage(@StringRes int i3) {
        return (MaterialAlertDialogBuilder) super.setMessage(i3);
    }

    @NonNull
    @CanIgnoreReturnValue
    public MaterialAlertDialogBuilder setNegativeButton(@StringRes int i3, @Nullable DialogInterface.OnClickListener onClickListener) {
        return (MaterialAlertDialogBuilder) super.setNegativeButton(i3, onClickListener);
    }

    @NonNull
    @CanIgnoreReturnValue
    public MaterialAlertDialogBuilder setNeutralButton(@StringRes int i3, @Nullable DialogInterface.OnClickListener onClickListener) {
        return (MaterialAlertDialogBuilder) super.setNeutralButton(i3, onClickListener);
    }

    @NonNull
    @CanIgnoreReturnValue
    public MaterialAlertDialogBuilder setPositiveButton(@StringRes int i3, @Nullable DialogInterface.OnClickListener onClickListener) {
        return (MaterialAlertDialogBuilder) super.setPositiveButton(i3, onClickListener);
    }

    @NonNull
    @CanIgnoreReturnValue
    public MaterialAlertDialogBuilder setTitle(@StringRes int i3) {
        return (MaterialAlertDialogBuilder) super.setTitle(i3);
    }

    @NonNull
    @CanIgnoreReturnValue
    public MaterialAlertDialogBuilder setView(int i3) {
        return (MaterialAlertDialogBuilder) super.setView(i3);
    }

    @NonNull
    @CanIgnoreReturnValue
    public MaterialAlertDialogBuilder setIcon(@Nullable Drawable drawable) {
        return (MaterialAlertDialogBuilder) super.setIcon(drawable);
    }

    @NonNull
    @CanIgnoreReturnValue
    public MaterialAlertDialogBuilder setItems(@Nullable CharSequence[] charSequenceArr, @Nullable DialogInterface.OnClickListener onClickListener) {
        return (MaterialAlertDialogBuilder) super.setItems(charSequenceArr, onClickListener);
    }

    @NonNull
    @CanIgnoreReturnValue
    public MaterialAlertDialogBuilder setMessage(@Nullable CharSequence charSequence) {
        return (MaterialAlertDialogBuilder) super.setMessage(charSequence);
    }

    @NonNull
    @CanIgnoreReturnValue
    public MaterialAlertDialogBuilder setMultiChoiceItems(@ArrayRes int i3, @Nullable boolean[] zArr, @Nullable DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener) {
        return (MaterialAlertDialogBuilder) super.setMultiChoiceItems(i3, zArr, onMultiChoiceClickListener);
    }

    @NonNull
    @CanIgnoreReturnValue
    public MaterialAlertDialogBuilder setNegativeButton(@Nullable CharSequence charSequence, @Nullable DialogInterface.OnClickListener onClickListener) {
        return (MaterialAlertDialogBuilder) super.setNegativeButton(charSequence, onClickListener);
    }

    @NonNull
    @CanIgnoreReturnValue
    public MaterialAlertDialogBuilder setNeutralButton(@Nullable CharSequence charSequence, @Nullable DialogInterface.OnClickListener onClickListener) {
        return (MaterialAlertDialogBuilder) super.setNeutralButton(charSequence, onClickListener);
    }

    @NonNull
    @CanIgnoreReturnValue
    public MaterialAlertDialogBuilder setPositiveButton(@Nullable CharSequence charSequence, @Nullable DialogInterface.OnClickListener onClickListener) {
        return (MaterialAlertDialogBuilder) super.setPositiveButton(charSequence, onClickListener);
    }

    @NonNull
    @CanIgnoreReturnValue
    public MaterialAlertDialogBuilder setTitle(@Nullable CharSequence charSequence) {
        return (MaterialAlertDialogBuilder) super.setTitle(charSequence);
    }

    @NonNull
    @CanIgnoreReturnValue
    public MaterialAlertDialogBuilder setView(@Nullable View view) {
        return (MaterialAlertDialogBuilder) super.setView(view);
    }

    @NonNull
    @CanIgnoreReturnValue
    public MaterialAlertDialogBuilder setMultiChoiceItems(@Nullable CharSequence[] charSequenceArr, @Nullable boolean[] zArr, @Nullable DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener) {
        return (MaterialAlertDialogBuilder) super.setMultiChoiceItems(charSequenceArr, zArr, onMultiChoiceClickListener);
    }

    @NonNull
    @CanIgnoreReturnValue
    public MaterialAlertDialogBuilder setSingleChoiceItems(@ArrayRes int i3, int i4, @Nullable DialogInterface.OnClickListener onClickListener) {
        return (MaterialAlertDialogBuilder) super.setSingleChoiceItems(i3, i4, onClickListener);
    }

    @NonNull
    @CanIgnoreReturnValue
    public MaterialAlertDialogBuilder setMultiChoiceItems(@Nullable Cursor cursor, @NonNull String str, @NonNull String str2, @Nullable DialogInterface.OnMultiChoiceClickListener onMultiChoiceClickListener) {
        return (MaterialAlertDialogBuilder) super.setMultiChoiceItems(cursor, str, str2, onMultiChoiceClickListener);
    }

    @NonNull
    @CanIgnoreReturnValue
    public MaterialAlertDialogBuilder setSingleChoiceItems(@Nullable Cursor cursor, int i3, @NonNull String str, @Nullable DialogInterface.OnClickListener onClickListener) {
        return (MaterialAlertDialogBuilder) super.setSingleChoiceItems(cursor, i3, str, onClickListener);
    }

    @NonNull
    @CanIgnoreReturnValue
    public MaterialAlertDialogBuilder setSingleChoiceItems(@Nullable CharSequence[] charSequenceArr, int i3, @Nullable DialogInterface.OnClickListener onClickListener) {
        return (MaterialAlertDialogBuilder) super.setSingleChoiceItems(charSequenceArr, i3, onClickListener);
    }

    @NonNull
    @CanIgnoreReturnValue
    public MaterialAlertDialogBuilder setSingleChoiceItems(@Nullable ListAdapter listAdapter, int i3, @Nullable DialogInterface.OnClickListener onClickListener) {
        return (MaterialAlertDialogBuilder) super.setSingleChoiceItems(listAdapter, i3, onClickListener);
    }
}
