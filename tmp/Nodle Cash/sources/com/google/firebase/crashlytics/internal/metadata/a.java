package com.google.firebase.crashlytics.internal.metadata;

import com.google.firebase.crashlytics.internal.metadata.UserMetadata;
import java.util.concurrent.Callable;

public final /* synthetic */ class a implements Callable {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7097a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f7098b;

    public /* synthetic */ a(Object obj, int i3) {
        this.f7097a = i3;
        this.f7098b = obj;
    }

    public final Object call() {
        int i3 = this.f7097a;
        Object obj = this.f7098b;
        switch (i3) {
            case 0:
                return ((UserMetadata.SerializeableKeysMap) obj).lambda$scheduleSerializationTaskIfNeeded$0();
            default:
                return ((UserMetadata) obj).lambda$setUserId$0();
        }
    }
}
