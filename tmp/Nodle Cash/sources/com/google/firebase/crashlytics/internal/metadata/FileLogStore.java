package com.google.firebase.crashlytics.internal.metadata;

import androidx.annotation.Nullable;

interface FileLogStore {
    void closeLogFile();

    void deleteLogFile();

    @Nullable
    byte[] getLogAsBytes();

    @Nullable
    String getLogAsString();

    void writeToLog(long j2, String str);
}
