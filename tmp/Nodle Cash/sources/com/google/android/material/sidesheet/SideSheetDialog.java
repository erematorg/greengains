package com.google.android.material.sidesheet;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.GravityInt;
import androidx.annotation.IdRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StyleRes;
import com.google.android.material.R;

public class SideSheetDialog extends SheetDialog<SideSheetCallback> {
    private static final int SIDE_SHEET_DIALOG_DEFAULT_THEME_RES = R.style.Theme_Material3_Light_SideSheetDialog;
    private static final int SIDE_SHEET_DIALOG_THEME_ATTR = R.attr.sideSheetDialogTheme;

    public SideSheetDialog(@NonNull Context context) {
        this(context, 0);
    }

    public void addSheetCancelOnHideCallback(Sheet<SideSheetCallback> sheet) {
        sheet.addCallback(new SideSheetCallback() {
            public void onSlide(@NonNull View view, float f2) {
            }

            public void onStateChanged(@NonNull View view, int i3) {
                if (i3 == 5) {
                    SideSheetDialog.this.cancel();
                }
            }
        });
    }

    public /* bridge */ /* synthetic */ void cancel() {
        super.cancel();
    }

    @NonNull
    public Sheet<SideSheetCallback> getBehaviorFromSheet(@NonNull FrameLayout frameLayout) {
        return SideSheetBehavior.from(frameLayout);
    }

    @IdRes
    public int getDialogId() {
        return R.id.m3_side_sheet;
    }

    @LayoutRes
    public int getLayoutResId() {
        return R.layout.m3_side_sheet_dialog;
    }

    public int getStateOnStart() {
        return 3;
    }

    public /* bridge */ /* synthetic */ boolean isDismissWithSheetAnimationEnabled() {
        return super.isDismissWithSheetAnimationEnabled();
    }

    public /* bridge */ /* synthetic */ void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    public /* bridge */ /* synthetic */ void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    public /* bridge */ /* synthetic */ void setCancelable(boolean z2) {
        super.setCancelable(z2);
    }

    public /* bridge */ /* synthetic */ void setCanceledOnTouchOutside(boolean z2) {
        super.setCanceledOnTouchOutside(z2);
    }

    public /* bridge */ /* synthetic */ void setContentView(@LayoutRes int i3) {
        super.setContentView(i3);
    }

    public /* bridge */ /* synthetic */ void setDismissWithSheetAnimationEnabled(boolean z2) {
        super.setDismissWithSheetAnimationEnabled(z2);
    }

    public /* bridge */ /* synthetic */ void setSheetEdge(@GravityInt int i3) {
        super.setSheetEdge(i3);
    }

    public SideSheetDialog(@NonNull Context context, @StyleRes int i3) {
        super(context, i3, SIDE_SHEET_DIALOG_THEME_ATTR, SIDE_SHEET_DIALOG_DEFAULT_THEME_RES);
    }

    @NonNull
    public SideSheetBehavior<? extends View> getBehavior() {
        Sheet behavior = super.getBehavior();
        if (behavior instanceof SideSheetBehavior) {
            return (SideSheetBehavior) behavior;
        }
        throw new IllegalStateException("The view is not associated with SideSheetBehavior");
    }

    public /* bridge */ /* synthetic */ void setContentView(@Nullable View view) {
        super.setContentView(view);
    }

    public /* bridge */ /* synthetic */ void setContentView(@Nullable View view, @Nullable ViewGroup.LayoutParams layoutParams) {
        super.setContentView(view, layoutParams);
    }
}
