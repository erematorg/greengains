package com.tinder.scarlet.internal.servicemethod;

import com.tinder.scarlet.StreamAdapter;
import io.reactivex.exceptions.CompositeException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import org.apache.commons.lang3.ClassUtils;
import org.jetbrains.annotations.NotNull;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0013\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0002\u0010\u0005J\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00010\u00072\u0006\u0010\b\u001a\u00020\tR\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lcom/tinder/scarlet/internal/servicemethod/StreamAdapterResolver;", "", "streamAdapterFactories", "", "Lcom/tinder/scarlet/StreamAdapter$Factory;", "(Ljava/util/List;)V", "resolve", "Lcom/tinder/scarlet/StreamAdapter;", "type", "Ljava/lang/reflect/Type;", "scarlet"}, k = 1, mv = {1, 8, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nStreamAdapterResolver.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StreamAdapterResolver.kt\ncom/tinder/scarlet/internal/servicemethod/StreamAdapterResolver\n+ 2 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,29:1\n37#2,2:30\n*S KotlinDebug\n*F\n+ 1 StreamAdapterResolver.kt\ncom/tinder/scarlet/internal/servicemethod/StreamAdapterResolver\n*L\n25#1:30,2\n*E\n"})
public final class StreamAdapterResolver {
    @NotNull
    private final List<StreamAdapter.Factory> streamAdapterFactories;

    public StreamAdapterResolver(@NotNull List<? extends StreamAdapter.Factory> list) {
        Intrinsics.checkNotNullParameter(list, "streamAdapterFactories");
        this.streamAdapterFactories = list;
    }

    @NotNull
    public final StreamAdapter<Object, Object> resolve(@NotNull Type type) {
        Intrinsics.checkNotNullParameter(type, "type");
        ArrayList arrayList = new ArrayList();
        for (StreamAdapter.Factory create : this.streamAdapterFactories) {
            try {
                return create.create(type);
            } catch (Throwable th) {
                arrayList.add(th);
            }
        }
        Throwable[] thArr = (Throwable[]) arrayList.toArray(new Throwable[0]);
        CompositeException compositeException = new CompositeException((Throwable[]) Arrays.copyOf(thArr, thArr.length));
        throw new IllegalStateException("Cannot resolve stream adapter for type " + type + ClassUtils.PACKAGE_SEPARATOR_CHAR, compositeException);
    }
}
