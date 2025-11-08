package com.google.android.recaptcha.internal;

import android.content.Context;
import java.io.File;
import java.io.IOException;
import java.security.GeneralSecurityException;
import kotlin.io.FilesKt;
import org.jetbrains.annotations.NotNull;

public final class zzad {
    @NotNull
    private final Context zza;

    public zzad(@NotNull Context context) {
        this.zza = context;
    }

    @NotNull
    public static final byte[] zza(@NotNull File file) throws IOException, GeneralSecurityException {
        return FilesKt.readBytes(file);
    }

    public static final void zzb(@NotNull File file, @NotNull byte[] bArr) throws IOException, GeneralSecurityException {
        if (!file.exists() || file.delete()) {
            FilesKt.writeBytes(file, bArr);
            return;
        }
        throw new IOException("Unable to delete existing encrypted file");
    }
}
