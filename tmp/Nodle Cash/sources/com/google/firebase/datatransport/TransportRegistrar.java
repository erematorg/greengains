package com.google.firebase.datatransport;

import M0.a;
import android.content.Context;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.google.android.datatransport.TransportFactory;
import com.google.firebase.components.Component;
import com.google.firebase.components.ComponentRegistrar;
import com.google.firebase.components.Dependency;
import com.google.firebase.components.Qualified;
import com.google.firebase.platforminfo.LibraryVersionComponent;
import java.util.Arrays;
import java.util.List;

@Keep
public class TransportRegistrar implements ComponentRegistrar {
    private static final String LIBRARY_NAME = "fire-transport";

    @NonNull
    public List<Component<?>> getComponents() {
        Class<TransportFactory> cls = TransportFactory.class;
        Class<Context> cls2 = Context.class;
        return Arrays.asList(new Component[]{Component.builder(cls).name(LIBRARY_NAME).add(Dependency.required((Class<?>) cls2)).factory(new a(1)).build(), Component.builder(Qualified.qualified(LegacyTransportBackend.class, cls)).add(Dependency.required((Class<?>) cls2)).factory(new a(2)).build(), Component.builder(Qualified.qualified(TransportBackend.class, cls)).add(Dependency.required((Class<?>) cls2)).factory(new a(3)).build(), LibraryVersionComponent.create(LIBRARY_NAME, BuildConfig.VERSION_NAME)});
    }
}
