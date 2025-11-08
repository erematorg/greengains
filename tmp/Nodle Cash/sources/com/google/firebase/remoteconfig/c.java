package com.google.firebase.remoteconfig;

import com.google.android.gms.common.util.BiConsumer;
import com.google.firebase.remoteconfig.internal.ConfigContainer;
import com.google.firebase.remoteconfig.internal.Personalization;

public final /* synthetic */ class c implements BiConsumer {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ Personalization f7163a;

    public /* synthetic */ c(Personalization personalization) {
        this.f7163a = personalization;
    }

    public final void accept(Object obj, Object obj2) {
        this.f7163a.logArmActive((String) obj, (ConfigContainer) obj2);
    }
}
