package com.google.devtools.ksp.symbol;

import android.support.v4.media.session.a;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/google/devtools/ksp/symbol/FileLocation;", "Lcom/google/devtools/ksp/symbol/Location;", "filePath", "", "lineNumber", "", "(Ljava/lang/String;I)V", "getFilePath", "()Ljava/lang/String;", "getLineNumber", "()I", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "api"}, k = 1, mv = {1, 9, 0}, xi = 48)
public final class FileLocation extends Location {
    @NotNull
    private final String filePath;
    private final int lineNumber;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public FileLocation(@NotNull String str, int i3) {
        super((DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(str, "filePath");
        this.filePath = str;
        this.lineNumber = i3;
    }

    public static /* synthetic */ FileLocation copy$default(FileLocation fileLocation, String str, int i3, int i4, Object obj) {
        if ((i4 & 1) != 0) {
            str = fileLocation.filePath;
        }
        if ((i4 & 2) != 0) {
            i3 = fileLocation.lineNumber;
        }
        return fileLocation.copy(str, i3);
    }

    @NotNull
    public final String component1() {
        return this.filePath;
    }

    public final int component2() {
        return this.lineNumber;
    }

    @NotNull
    public final FileLocation copy(@NotNull String str, int i3) {
        Intrinsics.checkNotNullParameter(str, "filePath");
        return new FileLocation(str, i3);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof FileLocation)) {
            return false;
        }
        FileLocation fileLocation = (FileLocation) obj;
        return Intrinsics.areEqual((Object) this.filePath, (Object) fileLocation.filePath) && this.lineNumber == fileLocation.lineNumber;
    }

    @NotNull
    public final String getFilePath() {
        return this.filePath;
    }

    public final int getLineNumber() {
        return this.lineNumber;
    }

    public int hashCode() {
        return Integer.hashCode(this.lineNumber) + (this.filePath.hashCode() * 31);
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder("FileLocation(filePath=");
        sb.append(this.filePath);
        sb.append(", lineNumber=");
        return a.p(sb, this.lineNumber, ')');
    }
}
