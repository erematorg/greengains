package com.google.android.material.divider;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.DimenRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.Px;
import androidx.camera.camera2.internal.C0118y;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.material.R;
import com.google.android.material.internal.ThemeEnforcement;
import com.google.android.material.internal.ViewUtils;
import com.google.android.material.resources.MaterialResources;

public class MaterialDividerItemDecoration extends RecyclerView.ItemDecoration {
    private static final int DEF_STYLE_RES = R.style.Widget_MaterialComponents_MaterialDivider;
    public static final int HORIZONTAL = 0;
    public static final int VERTICAL = 1;
    @ColorInt
    private int color;
    @NonNull
    private Drawable dividerDrawable;
    private int insetEnd;
    private int insetStart;
    private boolean lastItemDecorated;
    private int orientation;
    private final Rect tempRect;
    private int thickness;

    public MaterialDividerItemDecoration(@NonNull Context context, int i3) {
        this(context, (AttributeSet) null, i3);
    }

    private void drawForHorizontalOrientation(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView) {
        int i3;
        int i4;
        int i5;
        int i6;
        canvas.save();
        if (recyclerView.getClipToPadding()) {
            i4 = recyclerView.getPaddingTop();
            i3 = recyclerView.getHeight() - recyclerView.getPaddingBottom();
            canvas.clipRect(recyclerView.getPaddingLeft(), i4, recyclerView.getWidth() - recyclerView.getPaddingRight(), i3);
        } else {
            i3 = recyclerView.getHeight();
            i4 = 0;
        }
        int i7 = i4 + this.insetStart;
        int i8 = i3 - this.insetEnd;
        boolean isLayoutRtl = ViewUtils.isLayoutRtl(recyclerView);
        int childCount = recyclerView.getChildCount();
        for (int i9 = 0; i9 < childCount; i9++) {
            View childAt = recyclerView.getChildAt(i9);
            if (shouldDrawDivider(recyclerView, childAt)) {
                recyclerView.getLayoutManager().getDecoratedBoundsWithMargins(childAt, this.tempRect);
                int round = Math.round(childAt.getTranslationX());
                if (isLayoutRtl) {
                    i5 = this.tempRect.left + round;
                    i6 = this.thickness + i5;
                } else {
                    i6 = round + this.tempRect.right;
                    i5 = i6 - this.thickness;
                }
                this.dividerDrawable.setBounds(i5, i7, i6, i8);
                this.dividerDrawable.setAlpha(Math.round(childAt.getAlpha() * 255.0f));
                this.dividerDrawable.draw(canvas);
            }
        }
        canvas.restore();
    }

    private void drawForVerticalOrientation(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView) {
        int i3;
        int i4;
        canvas.save();
        if (recyclerView.getClipToPadding()) {
            i4 = recyclerView.getPaddingLeft();
            i3 = recyclerView.getWidth() - recyclerView.getPaddingRight();
            canvas.clipRect(i4, recyclerView.getPaddingTop(), i3, recyclerView.getHeight() - recyclerView.getPaddingBottom());
        } else {
            i3 = recyclerView.getWidth();
            i4 = 0;
        }
        boolean isLayoutRtl = ViewUtils.isLayoutRtl(recyclerView);
        int i5 = i4 + (isLayoutRtl ? this.insetEnd : this.insetStart);
        int i6 = i3 - (isLayoutRtl ? this.insetStart : this.insetEnd);
        int childCount = recyclerView.getChildCount();
        for (int i7 = 0; i7 < childCount; i7++) {
            View childAt = recyclerView.getChildAt(i7);
            if (shouldDrawDivider(recyclerView, childAt)) {
                recyclerView.getLayoutManager().getDecoratedBoundsWithMargins(childAt, this.tempRect);
                int round = Math.round(childAt.getTranslationY()) + this.tempRect.bottom;
                this.dividerDrawable.setBounds(i5, round - this.thickness, i6, round);
                this.dividerDrawable.setAlpha(Math.round(childAt.getAlpha() * 255.0f));
                this.dividerDrawable.draw(canvas);
            }
        }
        canvas.restore();
    }

    @ColorInt
    public int getDividerColor() {
        return this.color;
    }

    @Px
    public int getDividerInsetEnd() {
        return this.insetEnd;
    }

    @Px
    public int getDividerInsetStart() {
        return this.insetStart;
    }

    @Px
    public int getDividerThickness() {
        return this.thickness;
    }

    public void getItemOffsets(@NonNull Rect rect, @NonNull View view, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
        rect.set(0, 0, 0, 0);
        if (!shouldDrawDivider(recyclerView, view)) {
            return;
        }
        if (this.orientation == 1) {
            rect.bottom = this.thickness;
        } else if (ViewUtils.isLayoutRtl(recyclerView)) {
            rect.left = this.thickness;
        } else {
            rect.right = this.thickness;
        }
    }

    public int getOrientation() {
        return this.orientation;
    }

    public boolean isLastItemDecorated() {
        return this.lastItemDecorated;
    }

    public void onDraw(@NonNull Canvas canvas, @NonNull RecyclerView recyclerView, @NonNull RecyclerView.State state) {
        if (recyclerView.getLayoutManager() != null) {
            if (this.orientation == 1) {
                drawForVerticalOrientation(canvas, recyclerView);
            } else {
                drawForHorizontalOrientation(canvas, recyclerView);
            }
        }
    }

    public void setDividerColor(@ColorInt int i3) {
        this.color = i3;
        Drawable wrap = DrawableCompat.wrap(this.dividerDrawable);
        this.dividerDrawable = wrap;
        DrawableCompat.setTint(wrap, i3);
    }

    public void setDividerColorResource(@NonNull Context context, @ColorRes int i3) {
        setDividerColor(ContextCompat.getColor(context, i3));
    }

    public void setDividerInsetEnd(@Px int i3) {
        this.insetEnd = i3;
    }

    public void setDividerInsetEndResource(@NonNull Context context, @DimenRes int i3) {
        setDividerInsetEnd(context.getResources().getDimensionPixelOffset(i3));
    }

    public void setDividerInsetStart(@Px int i3) {
        this.insetStart = i3;
    }

    public void setDividerInsetStartResource(@NonNull Context context, @DimenRes int i3) {
        setDividerInsetStart(context.getResources().getDimensionPixelOffset(i3));
    }

    public void setDividerThickness(@Px int i3) {
        this.thickness = i3;
    }

    public void setDividerThicknessResource(@NonNull Context context, @DimenRes int i3) {
        setDividerThickness(context.getResources().getDimensionPixelSize(i3));
    }

    public void setLastItemDecorated(boolean z2) {
        this.lastItemDecorated = z2;
    }

    public void setOrientation(int i3) {
        if (i3 == 0 || i3 == 1) {
            this.orientation = i3;
            return;
        }
        throw new IllegalArgumentException(C0118y.c(i3, "Invalid orientation: ", ". It should be either HORIZONTAL or VERTICAL"));
    }

    public boolean shouldDrawDivider(int i3, @Nullable RecyclerView.Adapter<?> adapter) {
        return true;
    }

    public MaterialDividerItemDecoration(@NonNull Context context, @Nullable AttributeSet attributeSet, int i3) {
        this(context, attributeSet, R.attr.materialDividerStyle, i3);
    }

    private boolean shouldDrawDivider(@NonNull RecyclerView recyclerView, @NonNull View view) {
        int childAdapterPosition = recyclerView.getChildAdapterPosition(view);
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        boolean z2 = adapter != null && childAdapterPosition == adapter.getItemCount() - 1;
        if (childAdapterPosition == -1) {
            return false;
        }
        if ((!z2 || this.lastItemDecorated) && shouldDrawDivider(childAdapterPosition, (RecyclerView.Adapter<?>) adapter)) {
            return true;
        }
        return false;
    }

    public MaterialDividerItemDecoration(@NonNull Context context, @Nullable AttributeSet attributeSet, int i3, int i4) {
        this.tempRect = new Rect();
        TypedArray obtainStyledAttributes = ThemeEnforcement.obtainStyledAttributes(context, attributeSet, R.styleable.MaterialDivider, i3, DEF_STYLE_RES, new int[0]);
        this.color = MaterialResources.getColorStateList(context, obtainStyledAttributes, R.styleable.MaterialDivider_dividerColor).getDefaultColor();
        this.thickness = obtainStyledAttributes.getDimensionPixelSize(R.styleable.MaterialDivider_dividerThickness, context.getResources().getDimensionPixelSize(R.dimen.material_divider_thickness));
        this.insetStart = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.MaterialDivider_dividerInsetStart, 0);
        this.insetEnd = obtainStyledAttributes.getDimensionPixelOffset(R.styleable.MaterialDivider_dividerInsetEnd, 0);
        this.lastItemDecorated = obtainStyledAttributes.getBoolean(R.styleable.MaterialDivider_lastItemDecorated, true);
        obtainStyledAttributes.recycle();
        this.dividerDrawable = new ShapeDrawable();
        setDividerColor(this.color);
        setOrientation(i4);
    }
}
