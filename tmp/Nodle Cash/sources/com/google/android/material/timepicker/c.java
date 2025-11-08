package com.google.android.material.timepicker;

import com.google.android.material.button.MaterialButtonToggleGroup;

public final /* synthetic */ class c implements MaterialButtonToggleGroup.OnButtonCheckedListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f6705a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f6706b;

    public /* synthetic */ c(Object obj, int i3) {
        this.f6705a = i3;
        this.f6706b = obj;
    }

    public final void onButtonChecked(MaterialButtonToggleGroup materialButtonToggleGroup, int i3, boolean z2) {
        int i4 = this.f6705a;
        Object obj = this.f6706b;
        switch (i4) {
            case 0:
                ((TimePickerTextInputPresenter) obj).lambda$setupPeriodToggle$0(materialButtonToggleGroup, i3, z2);
                return;
            default:
                ((TimePickerView) obj).lambda$new$0(materialButtonToggleGroup, i3, z2);
                return;
        }
    }
}
