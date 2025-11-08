package com.google.android.material.chip;

import android.widget.CompoundButton;
import io.nodle.cash.feature.settings.privacy.PrivacyFragment;
import io.nodle.cash.ui.feature.settings.app.AppSettingsFragment;
import io.nodle.common.feature.logs.DebugModeActivity;

public final /* synthetic */ class a implements CompoundButton.OnCheckedChangeListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f6626a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f6627b;

    public /* synthetic */ a(Object obj, int i3) {
        this.f6626a = i3;
        this.f6627b = obj;
    }

    public final void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
        int i3 = this.f6626a;
        Object obj = this.f6627b;
        switch (i3) {
            case 0:
                ((Chip) obj).lambda$new$0(compoundButton, z2);
                return;
            case 1:
                PrivacyFragment.setupViews$lambda$0((PrivacyFragment) obj, compoundButton, z2);
                return;
            case 2:
                AppSettingsFragment.setupViews$lambda$3((AppSettingsFragment) obj, compoundButton, z2);
                return;
            default:
                DebugModeActivity.setUpView$lambda$2$lambda$1((DebugModeActivity) obj, compoundButton, z2);
                return;
        }
    }
}
