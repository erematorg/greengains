package com.phrase.android.sdk.inject;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;
import androidx.appcompat.widget.ActionBarContextView;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.textfield.TextInputLayout;
import com.phrase.android.sdk.R;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv = {}, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\b\u0010\tJ\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¨\u0006\n"}, d2 = {"Lcom/phrase/android/sdk/inject/ViewTransformer;", "", "Landroid/view/View;", "view", "Landroid/util/AttributeSet;", "attrSet", "", "transformView", "<init>", "()V", "sdk_release"}, k = 1, mv = {1, 7, 1})
public final class ViewTransformer {
    public static CharSequence[] a(Context context, AttributeSet attributeSet, int[] iArr) {
        int length = iArr.length;
        CharSequence[] charSequenceArr = new CharSequence[length];
        for (int i3 = 0; i3 < length; i3++) {
            charSequenceArr[i3] = null;
        }
        TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, iArr);
        Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "context.obtainStyledAttributes(attrSet, styleable)");
        int length2 = iArr.length;
        for (int i4 = 0; i4 < length2; i4++) {
            int resourceId = obtainStyledAttributes.getResourceId(i4, -1);
            if (resourceId != -1) {
                charSequenceArr[i4] = context.getResources().getText(resourceId);
            }
        }
        Unit unit = Unit.INSTANCE;
        obtainStyledAttributes.recycle();
        return charSequenceArr;
    }

    @SuppressLint({"RestrictedApi"})
    public final void transformView(@NotNull View view, @NotNull AttributeSet attributeSet) {
        Intrinsics.checkNotNullParameter(view, "view");
        Intrinsics.checkNotNullParameter(attributeSet, "attrSet");
        int i3 = 0;
        if (view instanceof TextView) {
            TextView textView = (TextView) view;
            Context context = textView.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "view.context");
            int[] iArr = R.styleable.PhraseTextView;
            Intrinsics.checkNotNullExpressionValue(iArr, "PhraseTextView");
            CharSequence[] a2 = a(context, attributeSet, iArr);
            int length = a2.length;
            int i4 = 0;
            int i5 = 0;
            while (i4 < length) {
                CharSequence charSequence = a2[i4];
                int i6 = i5 + 1;
                if (charSequence != null) {
                    if (i5 == R.styleable.PhraseTextView_android_text) {
                        textView.setText(charSequence);
                    } else if (i5 == R.styleable.PhraseTextView_android_hint) {
                        textView.setHint(charSequence);
                    } else if (i5 == R.styleable.PhraseTextView_android_textOn) {
                        if (view instanceof Switch) {
                            ((Switch) view).setTextOn(charSequence);
                        } else if (view instanceof SwitchCompat) {
                            ((SwitchCompat) view).setTextOn(charSequence);
                        } else if (view instanceof ToggleButton) {
                            ((ToggleButton) view).setTextOn(charSequence);
                        }
                    } else if (i5 == R.styleable.PhraseTextView_android_textOff) {
                        if (view instanceof Switch) {
                            ((Switch) view).setTextOff(charSequence);
                        } else if (view instanceof SwitchCompat) {
                            ((SwitchCompat) view).setTextOff(charSequence);
                        } else if (view instanceof ToggleButton) {
                            ((ToggleButton) view).setTextOff(charSequence);
                        }
                    }
                }
                i4++;
                i5 = i6;
            }
            if (view instanceof ToggleButton) {
                ToggleButton toggleButton = (ToggleButton) view;
                toggleButton.setChecked(toggleButton.isChecked());
            }
        } else if (view instanceof TextInputLayout) {
            TextInputLayout textInputLayout = (TextInputLayout) view;
            Context context2 = textInputLayout.getContext();
            Intrinsics.checkNotNullExpressionValue(context2, "view.context");
            int[] iArr2 = R.styleable.PhraseTextInputLayout;
            Intrinsics.checkNotNullExpressionValue(iArr2, "PhraseTextInputLayout");
            CharSequence[] a3 = a(context2, attributeSet, iArr2);
            int length2 = a3.length;
            int i7 = 0;
            int i8 = 0;
            while (i7 < length2) {
                CharSequence charSequence2 = a3[i7];
                int i9 = i8 + 1;
                if (charSequence2 != null && i8 == R.styleable.PhraseTextInputLayout_android_hint) {
                    textInputLayout.setHint(charSequence2);
                }
                i7++;
                i8 = i9;
            }
        } else if ((view instanceof Toolbar) || (view instanceof ActionBarContextView) || (view instanceof android.widget.Toolbar)) {
            Context context3 = view.getContext();
            Intrinsics.checkNotNullExpressionValue(context3, "view.context");
            int[] iArr3 = R.styleable.PhraseToolbar;
            Intrinsics.checkNotNullExpressionValue(iArr3, "PhraseToolbar");
            CharSequence[] a4 = a(context3, attributeSet, iArr3);
            int length3 = a4.length;
            int i10 = 0;
            int i11 = 0;
            while (i10 < length3) {
                CharSequence charSequence3 = a4[i10];
                int i12 = i11 + 1;
                if (charSequence3 != null) {
                    if (i11 == R.styleable.PhraseToolbar_android_title || i11 == R.styleable.PhraseToolbar_title) {
                        if (view instanceof Toolbar) {
                            ((Toolbar) view).setTitle(charSequence3);
                        } else if (view instanceof ActionBarContextView) {
                            ((ActionBarContextView) view).setTitle(charSequence3);
                        } else if (view instanceof android.widget.Toolbar) {
                            ((android.widget.Toolbar) view).setTitle(charSequence3);
                        }
                    } else if (i11 == R.styleable.PhraseToolbar_android_subtitle || i11 == R.styleable.PhraseToolbar_subtitle) {
                        if (view instanceof Toolbar) {
                            ((Toolbar) view).setSubtitle(charSequence3);
                        } else if (view instanceof ActionBarContextView) {
                            ((ActionBarContextView) view).setSubtitle(charSequence3);
                        } else if (view instanceof android.widget.Toolbar) {
                            ((android.widget.Toolbar) view).setSubtitle(charSequence3);
                        }
                    }
                }
                i10++;
                i11 = i12;
            }
        } else if (view instanceof NavigationView) {
            a((NavigationView) view, attributeSet);
        } else if (view instanceof NavigationBarView) {
            a((NavigationBarView) view, attributeSet);
        }
        if (view.getContentDescription() != null) {
            Context context4 = view.getContext();
            Intrinsics.checkNotNullExpressionValue(context4, "view.context");
            int[] iArr4 = R.styleable.PhraseView;
            Intrinsics.checkNotNullExpressionValue(iArr4, "PhraseView");
            CharSequence[] a5 = a(context4, attributeSet, iArr4);
            int length4 = a5.length;
            int i13 = 0;
            while (i3 < length4) {
                CharSequence charSequence4 = a5[i3];
                int i14 = i13 + 1;
                if (charSequence4 != null && i13 == R.styleable.PhraseView_android_contentDescription) {
                    view.setContentDescription(charSequence4);
                }
                i3++;
                i13 = i14;
            }
        }
    }

    public static void a(NavigationView navigationView, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = navigationView.getContext().obtainStyledAttributes(attributeSet, com.google.android.material.R.styleable.NavigationView);
        Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "view.context.obtainStyle…styleable.NavigationView)");
        int resourceId = obtainStyledAttributes.getResourceId(com.google.android.material.R.styleable.NavigationView_menu, -1);
        if (resourceId != -1) {
            Context context = navigationView.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "view.context");
            PhraseMenuParser phraseMenuParser = new PhraseMenuParser(context);
            Menu menu = navigationView.getMenu();
            Intrinsics.checkNotNullExpressionValue(menu, "view.menu");
            phraseMenuParser.parseAndApply(resourceId, menu);
        }
        Unit unit = Unit.INSTANCE;
        obtainStyledAttributes.recycle();
    }

    public static void a(NavigationBarView navigationBarView, AttributeSet attributeSet) {
        TypedArray obtainStyledAttributes = navigationBarView.getContext().obtainStyledAttributes(attributeSet, com.google.android.material.R.styleable.NavigationBarView);
        Intrinsics.checkNotNullExpressionValue(obtainStyledAttributes, "view.context.obtainStyle…leable.NavigationBarView)");
        int resourceId = obtainStyledAttributes.getResourceId(com.google.android.material.R.styleable.NavigationBarView_menu, -1);
        if (resourceId != -1) {
            Context context = navigationBarView.getContext();
            Intrinsics.checkNotNullExpressionValue(context, "view.context");
            PhraseMenuParser phraseMenuParser = new PhraseMenuParser(context);
            Menu menu = navigationBarView.getMenu();
            Intrinsics.checkNotNullExpressionValue(menu, "view.menu");
            phraseMenuParser.parseAndApply(resourceId, menu);
        }
        Unit unit = Unit.INSTANCE;
        obtainStyledAttributes.recycle();
    }
}
