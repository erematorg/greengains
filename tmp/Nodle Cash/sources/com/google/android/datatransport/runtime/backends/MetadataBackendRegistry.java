package com.google.android.datatransport.runtime.backends;

import android.content.ComponentName;
import android.content.Context;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.util.Log;
import androidx.annotation.Nullable;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
class MetadataBackendRegistry implements BackendRegistry {
    private static final String BACKEND_KEY_PREFIX = "backend:";
    private static final String TAG = "BackendRegistry";
    private final BackendFactoryProvider backendFactoryProvider;
    private final Map<String, TransportBackend> backends;
    private final CreationContextFactory creationContextFactory;

    public static class BackendFactoryProvider {
        private final Context applicationContext;
        private Map<String, String> backendProviders = null;

        public BackendFactoryProvider(Context context) {
            this.applicationContext = context;
        }

        private Map<String, String> discover(Context context) {
            Bundle metadata = getMetadata(context);
            if (metadata == null) {
                Log.w(MetadataBackendRegistry.TAG, "Could not retrieve metadata, returning empty list of transport backends.");
                return Collections.emptyMap();
            }
            HashMap hashMap = new HashMap();
            for (String next : metadata.keySet()) {
                Object obj = metadata.get(next);
                if ((obj instanceof String) && next.startsWith(MetadataBackendRegistry.BACKEND_KEY_PREFIX)) {
                    for (String trim : ((String) obj).split(",", -1)) {
                        String trim2 = trim.trim();
                        if (!trim2.isEmpty()) {
                            hashMap.put(trim2, next.substring(8));
                        }
                    }
                }
            }
            return hashMap;
        }

        private Map<String, String> getBackendProviders() {
            if (this.backendProviders == null) {
                this.backendProviders = discover(this.applicationContext);
            }
            return this.backendProviders;
        }

        private static Bundle getMetadata(Context context) {
            try {
                PackageManager packageManager = context.getPackageManager();
                if (packageManager == null) {
                    Log.w(MetadataBackendRegistry.TAG, "Context has no PackageManager.");
                    return null;
                }
                ServiceInfo serviceInfo = packageManager.getServiceInfo(new ComponentName(context, TransportBackendDiscovery.class), 128);
                if (serviceInfo != null) {
                    return serviceInfo.metaData;
                }
                Log.w(MetadataBackendRegistry.TAG, "TransportBackendDiscovery has no service info.");
                return null;
            } catch (PackageManager.NameNotFoundException unused) {
                Log.w(MetadataBackendRegistry.TAG, "Application info not found.");
                return null;
            }
        }

        @Nullable
        public BackendFactory get(String str) {
            String str2 = getBackendProviders().get(str);
            if (str2 == null) {
                return null;
            }
            try {
                return (BackendFactory) Class.forName(str2).asSubclass(BackendFactory.class).getDeclaredConstructor((Class[]) null).newInstance((Object[]) null);
            } catch (ClassNotFoundException e3) {
                Log.w(MetadataBackendRegistry.TAG, "Class " + str2 + " is not found.", e3);
                return null;
            } catch (IllegalAccessException e4) {
                Log.w(MetadataBackendRegistry.TAG, "Could not instantiate " + str2 + JwtUtilsKt.JWT_DELIMITER, e4);
                return null;
            } catch (InstantiationException e5) {
                Log.w(MetadataBackendRegistry.TAG, "Could not instantiate " + str2 + JwtUtilsKt.JWT_DELIMITER, e5);
                return null;
            } catch (NoSuchMethodException e6) {
                Log.w(MetadataBackendRegistry.TAG, "Could not instantiate ".concat(str2), e6);
                return null;
            } catch (InvocationTargetException e7) {
                Log.w(MetadataBackendRegistry.TAG, "Could not instantiate ".concat(str2), e7);
                return null;
            }
        }
    }

    @Inject
    public MetadataBackendRegistry(Context context, CreationContextFactory creationContextFactory2) {
        this(new BackendFactoryProvider(context), creationContextFactory2);
    }

    @Nullable
    public synchronized TransportBackend get(String str) {
        if (this.backends.containsKey(str)) {
            return this.backends.get(str);
        }
        BackendFactory backendFactory = this.backendFactoryProvider.get(str);
        if (backendFactory == null) {
            return null;
        }
        TransportBackend create = backendFactory.create(this.creationContextFactory.create(str));
        this.backends.put(str, create);
        return create;
    }

    public MetadataBackendRegistry(BackendFactoryProvider backendFactoryProvider2, CreationContextFactory creationContextFactory2) {
        this.backends = new HashMap();
        this.backendFactoryProvider = backendFactoryProvider2;
        this.creationContextFactory = creationContextFactory2;
    }
}
