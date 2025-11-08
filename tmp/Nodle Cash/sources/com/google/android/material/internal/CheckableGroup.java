package com.google.android.material.internal;

import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.annotation.UiThread;
import com.google.android.material.internal.MaterialCheckable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@UiThread
@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public class CheckableGroup<T extends MaterialCheckable<T>> {
    private final Map<Integer, T> checkables = new HashMap();
    private final Set<Integer> checkedIds = new HashSet();
    private OnCheckedStateChangeListener onCheckedStateChangeListener;
    /* access modifiers changed from: private */
    public boolean selectionRequired;
    private boolean singleSelection;

    public interface OnCheckedStateChangeListener {
        void onCheckedStateChanged(@NonNull Set<Integer> set);
    }

    /* access modifiers changed from: private */
    public boolean checkInternal(@NonNull MaterialCheckable<T> materialCheckable) {
        int id = materialCheckable.getId();
        if (this.checkedIds.contains(Integer.valueOf(id))) {
            return false;
        }
        MaterialCheckable materialCheckable2 = (MaterialCheckable) this.checkables.get(Integer.valueOf(getSingleCheckedId()));
        if (materialCheckable2 != null) {
            uncheckInternal(materialCheckable2, false);
        }
        boolean add = this.checkedIds.add(Integer.valueOf(id));
        if (!materialCheckable.isChecked()) {
            materialCheckable.setChecked(true);
        }
        return add;
    }

    /* access modifiers changed from: private */
    public void onCheckedStateChanged() {
        OnCheckedStateChangeListener onCheckedStateChangeListener2 = this.onCheckedStateChangeListener;
        if (onCheckedStateChangeListener2 != null) {
            onCheckedStateChangeListener2.onCheckedStateChanged(getCheckedIds());
        }
    }

    /* access modifiers changed from: private */
    public boolean uncheckInternal(@NonNull MaterialCheckable<T> materialCheckable, boolean z2) {
        int id = materialCheckable.getId();
        if (!this.checkedIds.contains(Integer.valueOf(id))) {
            return false;
        }
        if (!z2 || this.checkedIds.size() != 1 || !this.checkedIds.contains(Integer.valueOf(id))) {
            boolean remove = this.checkedIds.remove(Integer.valueOf(id));
            if (materialCheckable.isChecked()) {
                materialCheckable.setChecked(false);
            }
            return remove;
        }
        materialCheckable.setChecked(true);
        return false;
    }

    public void addCheckable(T t2) {
        this.checkables.put(Integer.valueOf(t2.getId()), t2);
        if (t2.isChecked()) {
            checkInternal(t2);
        }
        t2.setInternalOnCheckedChangeListener(new MaterialCheckable.OnCheckedChangeListener<T>() {
            public void onCheckedChanged(T t2, boolean z2) {
                if (!z2) {
                    CheckableGroup checkableGroup = CheckableGroup.this;
                    if (!checkableGroup.uncheckInternal(t2, checkableGroup.selectionRequired)) {
                        return;
                    }
                } else if (!CheckableGroup.this.checkInternal(t2)) {
                    return;
                }
                CheckableGroup.this.onCheckedStateChanged();
            }
        });
    }

    public void check(@IdRes int i3) {
        MaterialCheckable materialCheckable = (MaterialCheckable) this.checkables.get(Integer.valueOf(i3));
        if (materialCheckable != null && checkInternal(materialCheckable)) {
            onCheckedStateChanged();
        }
    }

    public void clearCheck() {
        boolean isEmpty = this.checkedIds.isEmpty();
        for (T uncheckInternal : this.checkables.values()) {
            uncheckInternal(uncheckInternal, false);
        }
        if (!isEmpty) {
            onCheckedStateChanged();
        }
    }

    @NonNull
    public Set<Integer> getCheckedIds() {
        return new HashSet(this.checkedIds);
    }

    @NonNull
    public List<Integer> getCheckedIdsSortedByChildOrder(@NonNull ViewGroup viewGroup) {
        Set<Integer> checkedIds2 = getCheckedIds();
        ArrayList arrayList = new ArrayList();
        for (int i3 = 0; i3 < viewGroup.getChildCount(); i3++) {
            View childAt = viewGroup.getChildAt(i3);
            if ((childAt instanceof MaterialCheckable) && checkedIds2.contains(Integer.valueOf(childAt.getId()))) {
                arrayList.add(Integer.valueOf(childAt.getId()));
            }
        }
        return arrayList;
    }

    @IdRes
    public int getSingleCheckedId() {
        if (!this.singleSelection || this.checkedIds.isEmpty()) {
            return -1;
        }
        return this.checkedIds.iterator().next().intValue();
    }

    public boolean isSelectionRequired() {
        return this.selectionRequired;
    }

    public boolean isSingleSelection() {
        return this.singleSelection;
    }

    public void removeCheckable(T t2) {
        t2.setInternalOnCheckedChangeListener((MaterialCheckable.OnCheckedChangeListener) null);
        this.checkables.remove(Integer.valueOf(t2.getId()));
        this.checkedIds.remove(Integer.valueOf(t2.getId()));
    }

    public void setOnCheckedStateChangeListener(@Nullable OnCheckedStateChangeListener onCheckedStateChangeListener2) {
        this.onCheckedStateChangeListener = onCheckedStateChangeListener2;
    }

    public void setSelectionRequired(boolean z2) {
        this.selectionRequired = z2;
    }

    public void setSingleSelection(boolean z2) {
        if (this.singleSelection != z2) {
            this.singleSelection = z2;
            clearCheck();
        }
    }

    public void uncheck(@IdRes int i3) {
        MaterialCheckable materialCheckable = (MaterialCheckable) this.checkables.get(Integer.valueOf(i3));
        if (materialCheckable != null && uncheckInternal(materialCheckable, this.selectionRequired)) {
            onCheckedStateChanged();
        }
    }
}
