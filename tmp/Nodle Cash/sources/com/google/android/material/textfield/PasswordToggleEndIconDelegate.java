package com.google.android.material.textfield;

import android.text.method.PasswordTransformationMethod;
import android.text.method.TransformationMethod;
import android.view.View;
import android.widget.EditText;
import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import com.google.android.material.R;

class PasswordToggleEndIconDelegate extends EndIconDelegate {
    @Nullable
    private EditText editText;
    private int iconResId = R.drawable.design_password_eye;
    private final View.OnClickListener onIconClickListener = new a(this, 2);

    public PasswordToggleEndIconDelegate(@NonNull EndCompoundLayout endCompoundLayout, @DrawableRes int i3) {
        super(endCompoundLayout);
        if (i3 != 0) {
            this.iconResId = i3;
        }
    }

    private boolean hasPasswordTransformation() {
        EditText editText2 = this.editText;
        return editText2 != null && (editText2.getTransformationMethod() instanceof PasswordTransformationMethod);
    }

    private static boolean isInputTypePassword(EditText editText2) {
        return editText2 != null && (editText2.getInputType() == 16 || editText2.getInputType() == 128 || editText2.getInputType() == 144 || editText2.getInputType() == 224);
    }

    /* access modifiers changed from: private */
    public /* synthetic */ void lambda$new$0(View view) {
        EditText editText2 = this.editText;
        if (editText2 != null) {
            int selectionEnd = editText2.getSelectionEnd();
            if (hasPasswordTransformation()) {
                this.editText.setTransformationMethod((TransformationMethod) null);
            } else {
                this.editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
            }
            if (selectionEnd >= 0) {
                this.editText.setSelection(selectionEnd);
            }
            refreshIconState();
        }
    }

    public void beforeEditTextChanged(CharSequence charSequence, int i3, int i4, int i5) {
        refreshIconState();
    }

    @StringRes
    public int getIconContentDescriptionResId() {
        return R.string.password_toggle_content_description;
    }

    @DrawableRes
    public int getIconDrawableResId() {
        return this.iconResId;
    }

    public View.OnClickListener getOnIconClickListener() {
        return this.onIconClickListener;
    }

    public boolean isIconCheckable() {
        return true;
    }

    public boolean isIconChecked() {
        return !hasPasswordTransformation();
    }

    public void onEditTextAttached(@Nullable EditText editText2) {
        this.editText = editText2;
        refreshIconState();
    }

    public void setUp() {
        if (isInputTypePassword(this.editText)) {
            this.editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }

    public void tearDown() {
        EditText editText2 = this.editText;
        if (editText2 != null) {
            editText2.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
    }
}
