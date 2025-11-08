package com.google.common.io;

import com.google.common.io.TempFileCreator;
import java.io.IOException;
import java.nio.file.attribute.FileAttribute;

public final /* synthetic */ class b implements TempFileCreator.JavaNioCreator.PermissionSupplier {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f6931a;

    /* renamed from: b  reason: collision with root package name */
    public final /* synthetic */ Object f6932b;

    public /* synthetic */ b(Object obj, int i3) {
        this.f6931a = i3;
        this.f6932b = obj;
    }

    public final FileAttribute get() {
        int i3 = this.f6931a;
        Object obj = this.f6932b;
        switch (i3) {
            case 0:
                return TempFileCreator.JavaNioCreator.lambda$userPermissions$3((FileAttribute) obj);
            default:
                return TempFileCreator.JavaNioCreator.lambda$userPermissions$4((IOException) obj);
        }
    }
}
