package com.google.android.material.motion;

import android.os.Build;
import android.view.View;
import android.window.BackEvent;
import android.window.OnBackAnimationCallback;
import android.window.OnBackInvokedCallback;
import android.window.OnBackInvokedDispatcher;
import androidx.activity.BackEventCompat;
import androidx.annotation.DoNotInline;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import java.util.Objects;

@RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
public final class MaterialBackOrchestrator {
    @Nullable
    private final BackCallbackDelegate backCallbackDelegate;
    @NonNull
    private final MaterialBackHandler backHandler;
    @NonNull
    private final View view;

    @RequiresApi(33)
    public static class Api33BackCallbackDelegate implements BackCallbackDelegate {
        @Nullable
        private OnBackInvokedCallback onBackInvokedCallback;

        private Api33BackCallbackDelegate() {
        }

        public OnBackInvokedCallback createOnBackInvokedCallback(@NonNull MaterialBackHandler materialBackHandler) {
            Objects.requireNonNull(materialBackHandler);
            return new a(materialBackHandler);
        }

        public boolean isListeningForBackCallbacks() {
            return this.onBackInvokedCallback != null;
        }

        @DoNotInline
        public void startListeningForBackCallbacks(@NonNull MaterialBackHandler materialBackHandler, @NonNull View view, boolean z2) {
            OnBackInvokedDispatcher g2;
            if (this.onBackInvokedCallback == null && (g2 = view.findOnBackInvokedDispatcher()) != null) {
                OnBackInvokedCallback createOnBackInvokedCallback = createOnBackInvokedCallback(materialBackHandler);
                this.onBackInvokedCallback = createOnBackInvokedCallback;
                g2.registerOnBackInvokedCallback(z2 ? 1000000 : 0, createOnBackInvokedCallback);
            }
        }

        @DoNotInline
        public void stopListeningForBackCallbacks(@NonNull View view) {
            OnBackInvokedDispatcher g2 = view.findOnBackInvokedDispatcher();
            if (g2 != null) {
                g2.unregisterOnBackInvokedCallback(this.onBackInvokedCallback);
                this.onBackInvokedCallback = null;
            }
        }
    }

    @RequiresApi(34)
    public static class Api34BackCallbackDelegate extends Api33BackCallbackDelegate {
        private Api34BackCallbackDelegate() {
            super();
        }

        public OnBackInvokedCallback createOnBackInvokedCallback(@NonNull final MaterialBackHandler materialBackHandler) {
            return new OnBackAnimationCallback() {
                public void onBackCancelled() {
                    if (Api34BackCallbackDelegate.this.isListeningForBackCallbacks()) {
                        materialBackHandler.cancelBackProgress();
                    }
                }

                public void onBackInvoked() {
                    materialBackHandler.handleBackInvoked();
                }

                public void onBackProgressed(@NonNull BackEvent backEvent) {
                    if (Api34BackCallbackDelegate.this.isListeningForBackCallbacks()) {
                        materialBackHandler.updateBackProgress(new BackEventCompat(backEvent));
                    }
                }

                public void onBackStarted(@NonNull BackEvent backEvent) {
                    if (Api34BackCallbackDelegate.this.isListeningForBackCallbacks()) {
                        materialBackHandler.startBackProgress(new BackEventCompat(backEvent));
                    }
                }
            };
        }
    }

    public interface BackCallbackDelegate {
        void startListeningForBackCallbacks(@NonNull MaterialBackHandler materialBackHandler, @NonNull View view, boolean z2);

        void stopListeningForBackCallbacks(@NonNull View view);
    }

    public <T extends View & MaterialBackHandler> MaterialBackOrchestrator(@NonNull T t2) {
        this((MaterialBackHandler) t2, t2);
    }

    @Nullable
    private static BackCallbackDelegate createBackCallbackDelegate() {
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 34) {
            return new Api34BackCallbackDelegate();
        }
        if (i3 >= 33) {
            return new Api33BackCallbackDelegate();
        }
        return null;
    }

    public boolean shouldListenForBackCallbacks() {
        return this.backCallbackDelegate != null;
    }

    public void startListeningForBackCallbacks() {
        startListeningForBackCallbacks(false);
    }

    public void startListeningForBackCallbacksWithPriorityOverlay() {
        startListeningForBackCallbacks(true);
    }

    public void stopListeningForBackCallbacks() {
        BackCallbackDelegate backCallbackDelegate2 = this.backCallbackDelegate;
        if (backCallbackDelegate2 != null) {
            backCallbackDelegate2.stopListeningForBackCallbacks(this.view);
        }
    }

    public MaterialBackOrchestrator(@NonNull MaterialBackHandler materialBackHandler, @NonNull View view2) {
        this.backCallbackDelegate = createBackCallbackDelegate();
        this.backHandler = materialBackHandler;
        this.view = view2;
    }

    private void startListeningForBackCallbacks(boolean z2) {
        BackCallbackDelegate backCallbackDelegate2 = this.backCallbackDelegate;
        if (backCallbackDelegate2 != null) {
            backCallbackDelegate2.startListeningForBackCallbacks(this.backHandler, this.view, z2);
        }
    }
}
