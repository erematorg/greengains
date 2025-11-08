package com.google.common.io;

import com.google.common.io.TempFileCreator;
import java.nio.file.attribute.FileAttribute;
import java.nio.file.attribute.PosixFilePermissions;

public final /* synthetic */ class a implements TempFileCreator.JavaNioCreator.PermissionSupplier {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f6930a;

    public /* synthetic */ a(int i3) {
        this.f6930a = i3;
    }

    public final FileAttribute get() {
        switch (this.f6930a) {
            case 0:
                return PosixFilePermissions.asFileAttribute(PosixFilePermissions.fromString("rw-------"));
            case 1:
                return PosixFilePermissions.asFileAttribute(PosixFilePermissions.fromString("rwx------"));
            default:
                return TempFileCreator.JavaNioCreator.lambda$static$2();
        }
    }
}
