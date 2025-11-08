package com.adjust.sdk;

import android.content.Context;
import java.util.Map;

public class AdjustSigner {
    private static volatile Object signerInstance;

    private AdjustSigner() {
    }

    public static void disableSigning(ILogger iLogger) {
        getSignerInstance();
        if (signerInstance != null) {
            try {
                Reflection.invokeInstanceMethod(signerInstance, "disableSigning", (Class[]) null, new Object[0]);
            } catch (Exception e3) {
                iLogger.warn("Invoking Signer disableSigning() received an error [%s]", e3.getMessage());
            }
        }
    }

    public static void enableSigning(ILogger iLogger) {
        getSignerInstance();
        if (signerInstance != null) {
            try {
                Reflection.invokeInstanceMethod(signerInstance, "enableSigning", (Class[]) null, new Object[0]);
            } catch (Exception e3) {
                iLogger.warn("Invoking Signer enableSigning() received an error [%s]", e3.getMessage());
            }
        }
    }

    private static void getSignerInstance() {
        if (signerInstance == null) {
            synchronized (AdjustSigner.class) {
                try {
                    if (signerInstance == null) {
                        signerInstance = Reflection.createDefaultInstance("com.adjust.sdk.sig.Signer");
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    public static void onResume(ILogger iLogger) {
        getSignerInstance();
        if (signerInstance != null) {
            try {
                Reflection.invokeInstanceMethod(signerInstance, "onResume", (Class[]) null, new Object[0]);
            } catch (Exception e3) {
                iLogger.warn("Invoking Signer onResume() received an error [%s]", e3.getMessage());
            }
        }
    }

    public static void sign(Map<String, String> map, String str, String str2, Context context, ILogger iLogger) {
        Class<String> cls = String.class;
        getSignerInstance();
        if (signerInstance != null) {
            try {
                iLogger.debug("Signing all the parameters", new Object[0]);
                Reflection.invokeInstanceMethod(signerInstance, "sign", new Class[]{Context.class, Map.class, cls, cls}, context, map, str, str2);
            } catch (Exception e3) {
                iLogger.warn("Invoking Signer sign() for %s received an error [%s]", str, e3.getMessage());
            }
        }
    }
}
