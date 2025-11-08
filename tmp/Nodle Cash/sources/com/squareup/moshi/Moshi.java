package com.squareup.moshi;

import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.internal.Util;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.CheckReturnValue;
import javax.annotation.Nullable;

public final class Moshi {
    static final List<JsonAdapter.Factory> BUILT_IN_FACTORIES;
    /* access modifiers changed from: private */
    public final Map<Object, JsonAdapter<?>> adapterCache = new LinkedHashMap();
    private final List<JsonAdapter.Factory> factories;
    private final int lastOffset;
    /* access modifiers changed from: private */
    public final ThreadLocal<LookupChain> lookupChainThreadLocal = new ThreadLocal<>();

    public static final class Builder {
        final List<JsonAdapter.Factory> factories = new ArrayList();
        int lastOffset = 0;

        public <T> Builder add(Type type, JsonAdapter<T> jsonAdapter) {
            return add(Moshi.newAdapterFactory(type, jsonAdapter));
        }

        public <T> Builder addLast(Type type, JsonAdapter<T> jsonAdapter) {
            return addLast(Moshi.newAdapterFactory(type, jsonAdapter));
        }

        @CheckReturnValue
        public Moshi build() {
            return new Moshi(this);
        }

        public <T> Builder add(Type type, Class<? extends Annotation> cls, JsonAdapter<T> jsonAdapter) {
            return add(Moshi.newAdapterFactory(type, cls, jsonAdapter));
        }

        public <T> Builder addLast(Type type, Class<? extends Annotation> cls, JsonAdapter<T> jsonAdapter) {
            return addLast(Moshi.newAdapterFactory(type, cls, jsonAdapter));
        }

        public Builder add(JsonAdapter.Factory factory) {
            if (factory != null) {
                List<JsonAdapter.Factory> list = this.factories;
                int i3 = this.lastOffset;
                this.lastOffset = i3 + 1;
                list.add(i3, factory);
                return this;
            }
            throw new IllegalArgumentException("factory == null");
        }

        public Builder addLast(JsonAdapter.Factory factory) {
            if (factory != null) {
                this.factories.add(factory);
                return this;
            }
            throw new IllegalArgumentException("factory == null");
        }

        public Builder add(Object obj) {
            if (obj != null) {
                return add((JsonAdapter.Factory) AdapterMethodsFactory.get(obj));
            }
            throw new IllegalArgumentException("adapter == null");
        }

        public Builder addLast(Object obj) {
            if (obj != null) {
                return addLast((JsonAdapter.Factory) AdapterMethodsFactory.get(obj));
            }
            throw new IllegalArgumentException("adapter == null");
        }
    }

    public static final class Lookup<T> extends JsonAdapter<T> {
        @Nullable
        JsonAdapter<T> adapter;
        final Object cacheKey;
        @Nullable
        final String fieldName;
        final Type type;

        public Lookup(Type type2, @Nullable String str, Object obj) {
            this.type = type2;
            this.fieldName = str;
            this.cacheKey = obj;
        }

        public T fromJson(JsonReader jsonReader) throws IOException {
            JsonAdapter<T> jsonAdapter = this.adapter;
            if (jsonAdapter != null) {
                return jsonAdapter.fromJson(jsonReader);
            }
            throw new IllegalStateException("JsonAdapter isn't ready");
        }

        public void toJson(JsonWriter jsonWriter, T t2) throws IOException {
            JsonAdapter<T> jsonAdapter = this.adapter;
            if (jsonAdapter != null) {
                jsonAdapter.toJson(jsonWriter, t2);
                return;
            }
            throw new IllegalStateException("JsonAdapter isn't ready");
        }

        public String toString() {
            JsonAdapter<T> jsonAdapter = this.adapter;
            return jsonAdapter != null ? jsonAdapter.toString() : super.toString();
        }
    }

    public final class LookupChain {
        final List<Lookup<?>> callLookups = new ArrayList();
        boolean exceptionAnnotated;
        final Deque<Lookup<?>> stack = new ArrayDeque();

        public LookupChain() {
        }

        public <T> void adapterFound(JsonAdapter<T> jsonAdapter) {
            this.stack.getLast().adapter = jsonAdapter;
        }

        public IllegalArgumentException exceptionWithLookupStack(IllegalArgumentException illegalArgumentException) {
            if (this.exceptionAnnotated) {
                return illegalArgumentException;
            }
            this.exceptionAnnotated = true;
            if (this.stack.size() == 1 && this.stack.getFirst().fieldName == null) {
                return illegalArgumentException;
            }
            StringBuilder sb = new StringBuilder(illegalArgumentException.getMessage());
            Iterator<Lookup<?>> descendingIterator = this.stack.descendingIterator();
            while (descendingIterator.hasNext()) {
                Lookup next = descendingIterator.next();
                sb.append("\nfor ");
                sb.append(next.type);
                if (next.fieldName != null) {
                    sb.append(' ');
                    sb.append(next.fieldName);
                }
            }
            return new IllegalArgumentException(sb.toString(), illegalArgumentException);
        }

        public void pop(boolean z2) {
            this.stack.removeLast();
            if (this.stack.isEmpty()) {
                Moshi.this.lookupChainThreadLocal.remove();
                if (z2) {
                    synchronized (Moshi.this.adapterCache) {
                        try {
                            int size = this.callLookups.size();
                            for (int i3 = 0; i3 < size; i3++) {
                                Lookup lookup = this.callLookups.get(i3);
                                JsonAdapter<T> jsonAdapter = (JsonAdapter) Moshi.this.adapterCache.put(lookup.cacheKey, lookup.adapter);
                                if (jsonAdapter != null) {
                                    lookup.adapter = jsonAdapter;
                                    Moshi.this.adapterCache.put(lookup.cacheKey, jsonAdapter);
                                }
                            }
                        } finally {
                        }
                    }
                }
            }
        }

        public <T> JsonAdapter<T> push(Type type, @Nullable String str, Object obj) {
            int size = this.callLookups.size();
            for (int i3 = 0; i3 < size; i3++) {
                Lookup lookup = this.callLookups.get(i3);
                if (lookup.cacheKey.equals(obj)) {
                    this.stack.add(lookup);
                    JsonAdapter<T> jsonAdapter = lookup.adapter;
                    return jsonAdapter != null ? jsonAdapter : lookup;
                }
            }
            Lookup lookup2 = new Lookup(type, str, obj);
            this.callLookups.add(lookup2);
            this.stack.add(lookup2);
            return null;
        }
    }

    static {
        ArrayList arrayList = new ArrayList(5);
        BUILT_IN_FACTORIES = arrayList;
        arrayList.add(StandardJsonAdapters.FACTORY);
        arrayList.add(CollectionJsonAdapter.FACTORY);
        arrayList.add(MapJsonAdapter.FACTORY);
        arrayList.add(ArrayJsonAdapter.FACTORY);
        arrayList.add(RecordJsonAdapter.FACTORY);
        arrayList.add(ClassJsonAdapter.FACTORY);
    }

    public Moshi(Builder builder) {
        int size = builder.factories.size();
        List<JsonAdapter.Factory> list = BUILT_IN_FACTORIES;
        ArrayList arrayList = new ArrayList(list.size() + size);
        arrayList.addAll(builder.factories);
        arrayList.addAll(list);
        this.factories = Collections.unmodifiableList(arrayList);
        this.lastOffset = builder.lastOffset;
    }

    private Object cacheKey(Type type, Set<? extends Annotation> set) {
        return set.isEmpty() ? type : Arrays.asList(new Object[]{type, set});
    }

    public static <T> JsonAdapter.Factory newAdapterFactory(final Type type, final JsonAdapter<T> jsonAdapter) {
        if (type == null) {
            throw new IllegalArgumentException("type == null");
        } else if (jsonAdapter != null) {
            return new JsonAdapter.Factory() {
                @Nullable
                public JsonAdapter<?> create(Type type, Set<? extends Annotation> set, Moshi moshi) {
                    if (!set.isEmpty() || !Util.typesMatch(type, type)) {
                        return null;
                    }
                    return jsonAdapter;
                }
            };
        } else {
            throw new IllegalArgumentException("jsonAdapter == null");
        }
    }

    @CheckReturnValue
    public <T> JsonAdapter<T> adapter(Type type) {
        return adapter(type, (Set<? extends Annotation>) Util.NO_ANNOTATIONS);
    }

    @CheckReturnValue
    public Builder newBuilder() {
        Builder builder = new Builder();
        int i3 = this.lastOffset;
        for (int i4 = 0; i4 < i3; i4++) {
            builder.add(this.factories.get(i4));
        }
        int size = this.factories.size() - BUILT_IN_FACTORIES.size();
        for (int i5 = this.lastOffset; i5 < size; i5++) {
            builder.addLast(this.factories.get(i5));
        }
        return builder;
    }

    @CheckReturnValue
    public <T> JsonAdapter<T> nextAdapter(JsonAdapter.Factory factory, Type type, Set<? extends Annotation> set) {
        if (set != null) {
            Type removeSubtypeWildcard = Util.removeSubtypeWildcard(Util.canonicalize(type));
            int indexOf = this.factories.indexOf(factory);
            if (indexOf != -1) {
                int size = this.factories.size();
                for (int i3 = indexOf + 1; i3 < size; i3++) {
                    JsonAdapter<?> create = this.factories.get(i3).create(removeSubtypeWildcard, set, this);
                    if (create != null) {
                        return create;
                    }
                }
                throw new IllegalArgumentException("No next JsonAdapter for " + Util.typeAnnotatedWithAnnotations(removeSubtypeWildcard, set));
            }
            throw new IllegalArgumentException("Unable to skip past unknown factory " + factory);
        }
        throw new NullPointerException("annotations == null");
    }

    @CheckReturnValue
    public <T> JsonAdapter<T> adapter(Class<T> cls) {
        return adapter((Type) cls, (Set<? extends Annotation>) Util.NO_ANNOTATIONS);
    }

    @CheckReturnValue
    public <T> JsonAdapter<T> adapter(Type type, Class<? extends Annotation> cls) {
        if (cls != null) {
            return adapter(type, (Set<? extends Annotation>) Collections.singleton(Types.createJsonQualifierImplementation(cls)));
        }
        throw new NullPointerException("annotationType == null");
    }

    public static <T> JsonAdapter.Factory newAdapterFactory(final Type type, final Class<? extends Annotation> cls, final JsonAdapter<T> jsonAdapter) {
        if (type == null) {
            throw new IllegalArgumentException("type == null");
        } else if (cls == null) {
            throw new IllegalArgumentException("annotation == null");
        } else if (jsonAdapter == null) {
            throw new IllegalArgumentException("jsonAdapter == null");
        } else if (!cls.isAnnotationPresent(JsonQualifier.class)) {
            throw new IllegalArgumentException(cls + " does not have @JsonQualifier");
        } else if (cls.getDeclaredMethods().length <= 0) {
            return new JsonAdapter.Factory() {
                @Nullable
                public JsonAdapter<?> create(Type type, Set<? extends Annotation> set, Moshi moshi) {
                    if (!Util.typesMatch(type, type) || set.size() != 1 || !Util.isAnnotationPresent(set, cls)) {
                        return null;
                    }
                    return jsonAdapter;
                }
            };
        } else {
            throw new IllegalArgumentException("Use JsonAdapter.Factory for annotations with elements");
        }
    }

    @CheckReturnValue
    public <T> JsonAdapter<T> adapter(Type type, Class<? extends Annotation>... clsArr) {
        if (clsArr.length == 1) {
            return adapter(type, clsArr[0]);
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet(clsArr.length);
        for (Class<? extends Annotation> createJsonQualifierImplementation : clsArr) {
            linkedHashSet.add(Types.createJsonQualifierImplementation(createJsonQualifierImplementation));
        }
        return adapter(type, (Set<? extends Annotation>) Collections.unmodifiableSet(linkedHashSet));
    }

    @CheckReturnValue
    public <T> JsonAdapter<T> adapter(Type type, Set<? extends Annotation> set) {
        return adapter(type, set, (String) null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0022, code lost:
        r1 = r4.lookupChainThreadLocal.get();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002a, code lost:
        if (r1 != null) goto L_0x0036;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x002c, code lost:
        r1 = new com.squareup.moshi.Moshi.LookupChain(r4);
        r4.lookupChainThreadLocal.set(r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0036, code lost:
        r7 = r1.push(r5, r7, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003b, code lost:
        if (r7 == null) goto L_0x0041;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x003d, code lost:
        r1.pop(false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0040, code lost:
        return r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:?, code lost:
        r7 = r4.factories.size();
        r2 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0048, code lost:
        if (r2 >= r7) goto L_0x0067;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x004a, code lost:
        r3 = r4.factories.get(r2).create(r5, r6, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0056, code lost:
        if (r3 != null) goto L_0x005b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0058, code lost:
        r2 = r2 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x005b, code lost:
        r1.adapterFound(r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x005e, code lost:
        r1.pop(true);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:27:0x0062, code lost:
        return r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x0063, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0065, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0081, code lost:
        throw new java.lang.IllegalArgumentException("No JsonAdapter for " + com.squareup.moshi.internal.Util.typeAnnotatedWithAnnotations(r5, r6));
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0086, code lost:
        throw r1.exceptionWithLookupStack(r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0087, code lost:
        r1.pop(false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x008a, code lost:
        throw r4;
     */
    @javax.annotation.CheckReturnValue
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public <T> com.squareup.moshi.JsonAdapter<T> adapter(java.lang.reflect.Type r5, java.util.Set<? extends java.lang.annotation.Annotation> r6, @javax.annotation.Nullable java.lang.String r7) {
        /*
            r4 = this;
            if (r5 == 0) goto L_0x0095
            if (r6 == 0) goto L_0x008d
            java.lang.reflect.Type r5 = com.squareup.moshi.internal.Util.canonicalize(r5)
            java.lang.reflect.Type r5 = com.squareup.moshi.internal.Util.removeSubtypeWildcard(r5)
            java.lang.Object r0 = r4.cacheKey(r5, r6)
            java.util.Map<java.lang.Object, com.squareup.moshi.JsonAdapter<?>> r1 = r4.adapterCache
            monitor-enter(r1)
            java.util.Map<java.lang.Object, com.squareup.moshi.JsonAdapter<?>> r2 = r4.adapterCache     // Catch:{ all -> 0x001f }
            java.lang.Object r2 = r2.get(r0)     // Catch:{ all -> 0x001f }
            com.squareup.moshi.JsonAdapter r2 = (com.squareup.moshi.JsonAdapter) r2     // Catch:{ all -> 0x001f }
            if (r2 == 0) goto L_0x0021
            monitor-exit(r1)     // Catch:{ all -> 0x001f }
            return r2
        L_0x001f:
            r4 = move-exception
            goto L_0x008b
        L_0x0021:
            monitor-exit(r1)     // Catch:{ all -> 0x001f }
            java.lang.ThreadLocal<com.squareup.moshi.Moshi$LookupChain> r1 = r4.lookupChainThreadLocal
            java.lang.Object r1 = r1.get()
            com.squareup.moshi.Moshi$LookupChain r1 = (com.squareup.moshi.Moshi.LookupChain) r1
            if (r1 != 0) goto L_0x0036
            com.squareup.moshi.Moshi$LookupChain r1 = new com.squareup.moshi.Moshi$LookupChain
            r1.<init>()
            java.lang.ThreadLocal<com.squareup.moshi.Moshi$LookupChain> r2 = r4.lookupChainThreadLocal
            r2.set(r1)
        L_0x0036:
            com.squareup.moshi.JsonAdapter r7 = r1.push(r5, r7, r0)
            r0 = 0
            if (r7 == 0) goto L_0x0041
            r1.pop(r0)
            return r7
        L_0x0041:
            java.util.List<com.squareup.moshi.JsonAdapter$Factory> r7 = r4.factories     // Catch:{ IllegalArgumentException -> 0x0065 }
            int r7 = r7.size()     // Catch:{ IllegalArgumentException -> 0x0065 }
            r2 = r0
        L_0x0048:
            if (r2 >= r7) goto L_0x0067
            java.util.List<com.squareup.moshi.JsonAdapter$Factory> r3 = r4.factories     // Catch:{ IllegalArgumentException -> 0x0065 }
            java.lang.Object r3 = r3.get(r2)     // Catch:{ IllegalArgumentException -> 0x0065 }
            com.squareup.moshi.JsonAdapter$Factory r3 = (com.squareup.moshi.JsonAdapter.Factory) r3     // Catch:{ IllegalArgumentException -> 0x0065 }
            com.squareup.moshi.JsonAdapter r3 = r3.create(r5, r6, r4)     // Catch:{ IllegalArgumentException -> 0x0065 }
            if (r3 != 0) goto L_0x005b
            int r2 = r2 + 1
            goto L_0x0048
        L_0x005b:
            r1.adapterFound(r3)     // Catch:{ IllegalArgumentException -> 0x0065 }
            r4 = 1
            r1.pop(r4)
            return r3
        L_0x0063:
            r4 = move-exception
            goto L_0x0087
        L_0x0065:
            r4 = move-exception
            goto L_0x0082
        L_0x0067:
            java.lang.IllegalArgumentException r4 = new java.lang.IllegalArgumentException     // Catch:{ IllegalArgumentException -> 0x0065 }
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch:{ IllegalArgumentException -> 0x0065 }
            r7.<init>()     // Catch:{ IllegalArgumentException -> 0x0065 }
            java.lang.String r2 = "No JsonAdapter for "
            r7.append(r2)     // Catch:{ IllegalArgumentException -> 0x0065 }
            java.lang.String r5 = com.squareup.moshi.internal.Util.typeAnnotatedWithAnnotations(r5, r6)     // Catch:{ IllegalArgumentException -> 0x0065 }
            r7.append(r5)     // Catch:{ IllegalArgumentException -> 0x0065 }
            java.lang.String r5 = r7.toString()     // Catch:{ IllegalArgumentException -> 0x0065 }
            r4.<init>(r5)     // Catch:{ IllegalArgumentException -> 0x0065 }
            throw r4     // Catch:{ IllegalArgumentException -> 0x0065 }
        L_0x0082:
            java.lang.IllegalArgumentException r4 = r1.exceptionWithLookupStack(r4)     // Catch:{ all -> 0x0063 }
            throw r4     // Catch:{ all -> 0x0063 }
        L_0x0087:
            r1.pop(r0)
            throw r4
        L_0x008b:
            monitor-exit(r1)     // Catch:{ all -> 0x001f }
            throw r4
        L_0x008d:
            java.lang.NullPointerException r4 = new java.lang.NullPointerException
            java.lang.String r5 = "annotations == null"
            r4.<init>(r5)
            throw r4
        L_0x0095:
            java.lang.NullPointerException r4 = new java.lang.NullPointerException
            java.lang.String r5 = "type == null"
            r4.<init>(r5)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.moshi.Moshi.adapter(java.lang.reflect.Type, java.util.Set, java.lang.String):com.squareup.moshi.JsonAdapter");
    }
}
