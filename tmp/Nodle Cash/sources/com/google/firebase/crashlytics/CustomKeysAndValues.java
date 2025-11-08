package com.google.firebase.crashlytics;

import androidx.annotation.NonNull;
import java.util.HashMap;
import java.util.Map;

public class CustomKeysAndValues {
    final Map<String, String> keysAndValues;

    public static class Builder {
        /* access modifiers changed from: private */
        public Map<String, String> keysAndValues = new HashMap();

        @NonNull
        public CustomKeysAndValues build() {
            return new CustomKeysAndValues(this);
        }

        @NonNull
        public Builder putBoolean(@NonNull String str, boolean z2) {
            this.keysAndValues.put(str, Boolean.toString(z2));
            return this;
        }

        @NonNull
        public Builder putDouble(@NonNull String str, double d2) {
            this.keysAndValues.put(str, Double.toString(d2));
            return this;
        }

        @NonNull
        public Builder putFloat(@NonNull String str, float f2) {
            this.keysAndValues.put(str, Float.toString(f2));
            return this;
        }

        @NonNull
        public Builder putInt(@NonNull String str, int i3) {
            this.keysAndValues.put(str, Integer.toString(i3));
            return this;
        }

        @NonNull
        public Builder putLong(@NonNull String str, long j2) {
            this.keysAndValues.put(str, Long.toString(j2));
            return this;
        }

        @NonNull
        public Builder putString(@NonNull String str, @NonNull String str2) {
            this.keysAndValues.put(str, str2);
            return this;
        }
    }

    public CustomKeysAndValues(@NonNull Builder builder) {
        this.keysAndValues = builder.keysAndValues;
    }
}
