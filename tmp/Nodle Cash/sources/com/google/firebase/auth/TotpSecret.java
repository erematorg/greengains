package com.google.firebase.auth;

import android.app.Activity;
import androidx.annotation.NonNull;

public interface TotpSecret {
    @NonNull
    String generateQrCodeUrl();

    @NonNull
    String generateQrCodeUrl(@NonNull String str, @NonNull String str2);

    int getCodeIntervalSeconds();

    int getCodeLength();

    long getEnrollmentCompletionDeadline();

    @NonNull
    String getHashAlgorithm();

    @NonNull
    String getSessionInfo();

    @NonNull
    String getSharedSecretKey();

    void openInOtpApp(@NonNull String str);

    void openInOtpApp(@NonNull String str, @NonNull String str2, @NonNull Activity activity);
}
