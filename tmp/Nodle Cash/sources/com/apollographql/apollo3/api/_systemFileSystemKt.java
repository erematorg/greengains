package com.apollographql.apollo3.api;

import kotlin.Metadata;
import okio.FileSystem;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u0014\u0010\u0000\u001a\u00020\u00018@X\u0004¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"systemFileSystem", "Lokio/FileSystem;", "getSystemFileSystem", "()Lokio/FileSystem;", "apollo-api"}, k = 2, mv = {1, 5, 1}, xi = 48)
public final class _systemFileSystemKt {
    @NotNull
    public static final FileSystem getSystemFileSystem() {
        return FileSystem.SYSTEM;
    }
}
