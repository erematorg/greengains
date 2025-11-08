package com.squareup.moshi.kotlin.reflect;

import com.squareup.moshi.JsonAdapter;
import kotlin.Metadata;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0010\u001b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J.\u0010\u0003\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u000e\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\f"}, d2 = {"Lcom/squareup/moshi/kotlin/reflect/KotlinJsonAdapterFactory;", "Lcom/squareup/moshi/JsonAdapter$Factory;", "()V", "create", "Lcom/squareup/moshi/JsonAdapter;", "type", "Ljava/lang/reflect/Type;", "annotations", "", "", "moshi", "Lcom/squareup/moshi/Moshi;", "moshi-kotlin"}, k = 1, mv = {1, 8, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nKotlinJsonAdapter.kt\nKotlin\n*S Kotlin\n*F\n+ 1 KotlinJsonAdapter.kt\ncom/squareup/moshi/kotlin/reflect/KotlinJsonAdapterFactory\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n+ 3 KAnnotatedElements.kt\nkotlin/reflect/full/KAnnotatedElements\n+ 4 fake.kt\nkotlin/jvm/internal/FakeKt\n+ 5 ArraysJVM.kt\nkotlin/collections/ArraysKt__ArraysJVMKt\n*L\n1#1,328:1\n1194#2,2:329\n1222#2,4:331\n288#2,2:336\n288#2,2:339\n1603#2,9:342\n1855#2:351\n1856#2:353\n1612#2:354\n1549#2:359\n1620#2,3:360\n20#3:335\n20#3:338\n1#4:341\n1#4:352\n37#5,2:355\n37#5,2:357\n37#5,2:363\n*S KotlinDebug\n*F\n+ 1 KotlinJsonAdapter.kt\ncom/squareup/moshi/kotlin/reflect/KotlinJsonAdapterFactory\n*L\n229#1:329,2\n229#1:331,4\n238#1:336,2\n244#1:339,2\n278#1:342,9\n278#1:351\n278#1:353\n278#1:354\n324#1:359\n324#1:360,3\n238#1:335\n244#1:338\n278#1:352\n278#1:355,2\n294#1:357,2\n324#1:363,2\n*E\n"})
public final class KotlinJsonAdapterFactory implements JsonAdapter.Factory {
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x01f0, code lost:
        if (r11 == null) goto L_0x01f5;
     */
    @org.jetbrains.annotations.Nullable
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.squareup.moshi.JsonAdapter<?> create(@org.jetbrains.annotations.NotNull java.lang.reflect.Type r20, @org.jetbrains.annotations.NotNull java.util.Set<? extends java.lang.annotation.Annotation> r21, @org.jetbrains.annotations.NotNull com.squareup.moshi.Moshi r22) {
        /*
            r19 = this;
            r1 = r20
            r0 = r21
            r2 = r22
            java.lang.String r3 = "type"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r3)
            java.lang.String r3 = "annotations"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r3)
            java.lang.String r3 = "moshi"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r3)
            java.util.Collection r0 = (java.util.Collection) r0
            boolean r0 = r0.isEmpty()
            r3 = 0
            if (r0 != 0) goto L_0x001f
            return r3
        L_0x001f:
            java.lang.Class r4 = com.squareup.moshi._MoshiKotlinTypesExtensionsKt.getRawType(r20)
            boolean r0 = r4.isInterface()
            if (r0 == 0) goto L_0x002a
            return r3
        L_0x002a:
            boolean r0 = r4.isEnum()
            if (r0 == 0) goto L_0x0031
            return r3
        L_0x0031:
            java.lang.Class r0 = com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterKt.KOTLIN_METADATA
            boolean r0 = r4.isAnnotationPresent(r0)
            if (r0 != 0) goto L_0x003c
            return r3
        L_0x003c:
            boolean r0 = com.squareup.moshi.internal.Util.isPlatformType(r4)
            if (r0 == 0) goto L_0x0043
            return r3
        L_0x0043:
            com.squareup.moshi.JsonAdapter r0 = com.squareup.moshi.internal.Util.generatedAdapter(r2, r1, r4)     // Catch:{ RuntimeException -> 0x004a }
            if (r0 == 0) goto L_0x0054
            return r0
        L_0x004a:
            r0 = move-exception
            r5 = r0
            java.lang.Throwable r0 = r5.getCause()
            boolean r0 = r0 instanceof java.lang.ClassNotFoundException
            if (r0 == 0) goto L_0x03f6
        L_0x0054:
            boolean r0 = r4.isLocalClass()
            if (r0 != 0) goto L_0x03e2
            kotlin.reflect.KClass r0 = kotlin.jvm.JvmClassMappingKt.getKotlinClass(r4)
            boolean r5 = r0.isAbstract()
            if (r5 != 0) goto L_0x03ce
            boolean r5 = r0.isInner()
            if (r5 != 0) goto L_0x03ba
            java.lang.Object r5 = r0.getObjectInstance()
            if (r5 != 0) goto L_0x03a6
            boolean r5 = r0.isSealed()
            if (r5 != 0) goto L_0x038f
            kotlin.reflect.KFunction r5 = kotlin.reflect.full.KClasses.getPrimaryConstructor(r0)
            if (r5 != 0) goto L_0x007d
            return r3
        L_0x007d:
            java.util.List r6 = r5.getParameters()
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            r7 = 16
            int r7 = androidx.compose.animation.core.a.h(r6, r7)
            java.util.LinkedHashMap r8 = new java.util.LinkedHashMap
            r8.<init>(r7)
            java.util.Iterator r6 = r6.iterator()
        L_0x0092:
            boolean r7 = r6.hasNext()
            if (r7 == 0) goto L_0x00a7
            java.lang.Object r7 = r6.next()
            r9 = r7
            kotlin.reflect.KParameter r9 = (kotlin.reflect.KParameter) r9
            java.lang.String r9 = r9.getName()
            r8.put(r9, r7)
            goto L_0x0092
        L_0x00a7:
            r6 = 1
            kotlin.reflect.jvm.KCallablesJvm.setAccessible(r5, r6)
            java.util.LinkedHashMap r7 = new java.util.LinkedHashMap
            r7.<init>()
            java.util.Collection r0 = kotlin.reflect.full.KClasses.getMemberProperties(r0)
            java.util.Iterator r0 = r0.iterator()
        L_0x00b8:
            boolean r9 = r0.hasNext()
            r10 = 0
            if (r9 == 0) goto L_0x02c6
            java.lang.Object r9 = r0.next()
            r14 = r9
            kotlin.reflect.KProperty1 r14 = (kotlin.reflect.KProperty1) r14
            java.lang.String r9 = r14.getName()
            java.lang.Object r9 = r8.get(r9)
            r15 = r9
            kotlin.reflect.KParameter r15 = (kotlin.reflect.KParameter) r15
            kotlin.reflect.jvm.KCallablesJvm.setAccessible(r14, r6)
            java.util.List r9 = r14.getAnnotations()
            java.lang.Iterable r9 = (java.lang.Iterable) r9
            java.util.Iterator r9 = r9.iterator()
        L_0x00de:
            boolean r11 = r9.hasNext()
            if (r11 == 0) goto L_0x00f0
            java.lang.Object r11 = r9.next()
            r12 = r11
            java.lang.annotation.Annotation r12 = (java.lang.annotation.Annotation) r12
            boolean r12 = r12 instanceof com.squareup.moshi.Json
            if (r12 == 0) goto L_0x00de
            goto L_0x00f1
        L_0x00f0:
            r11 = r3
        L_0x00f1:
            com.squareup.moshi.Json r11 = (com.squareup.moshi.Json) r11
            java.util.List r9 = r14.getAnnotations()
            java.util.Collection r9 = (java.util.Collection) r9
            java.util.List r9 = kotlin.collections.CollectionsKt.toMutableList(r9)
            if (r15 == 0) goto L_0x012d
            r12 = r9
            java.util.Collection r12 = (java.util.Collection) r12
            java.util.List r13 = r15.getAnnotations()
            java.lang.Iterable r13 = (java.lang.Iterable) r13
            kotlin.collections.CollectionsKt__MutableCollectionsKt.addAll(r12, r13)
            if (r11 != 0) goto L_0x012d
            java.util.List r11 = r15.getAnnotations()
            java.lang.Iterable r11 = (java.lang.Iterable) r11
            java.util.Iterator r11 = r11.iterator()
        L_0x0117:
            boolean r12 = r11.hasNext()
            if (r12 == 0) goto L_0x0129
            java.lang.Object r12 = r11.next()
            r13 = r12
            java.lang.annotation.Annotation r13 = (java.lang.annotation.Annotation) r13
            boolean r13 = r13 instanceof com.squareup.moshi.Json
            if (r13 == 0) goto L_0x0117
            goto L_0x012a
        L_0x0129:
            r12 = r3
        L_0x012a:
            r11 = r12
            com.squareup.moshi.Json r11 = (com.squareup.moshi.Json) r11
        L_0x012d:
            java.lang.reflect.Field r12 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaField(r14)
            if (r12 == 0) goto L_0x0138
            int r12 = r12.getModifiers()
            goto L_0x0139
        L_0x0138:
            r12 = r10
        L_0x0139:
            boolean r12 = java.lang.reflect.Modifier.isTransient(r12)
            if (r12 == 0) goto L_0x0161
            if (r15 == 0) goto L_0x00b8
            boolean r9 = r15.isOptional()
            if (r9 == 0) goto L_0x0149
            goto L_0x00b8
        L_0x0149:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "No default value for transient constructor "
            r0.<init>(r1)
            r0.append(r15)
            java.lang.String r0 = r0.toString()
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            throw r1
        L_0x0161:
            if (r11 == 0) goto L_0x018b
            boolean r12 = r11.ignore()
            if (r12 != r6) goto L_0x018b
            if (r15 == 0) goto L_0x00b8
            boolean r9 = r15.isOptional()
            if (r9 == 0) goto L_0x0173
            goto L_0x00b8
        L_0x0173:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "No default value for ignored constructor "
            r0.<init>(r1)
            r0.append(r15)
            java.lang.String r0 = r0.toString()
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            throw r1
        L_0x018b:
            if (r15 == 0) goto L_0x01d8
            kotlin.reflect.KType r12 = r15.getType()
            kotlin.reflect.KType r13 = r14.getReturnType()
            boolean r12 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r12, (java.lang.Object) r13)
            if (r12 == 0) goto L_0x019c
            goto L_0x01d8
        L_0x019c:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "'"
            r0.<init>(r1)
            java.lang.String r1 = r14.getName()
            r0.append(r1)
            java.lang.String r1 = "' has a constructor parameter of type "
            r0.append(r1)
            kotlin.jvm.internal.Intrinsics.checkNotNull(r15)
            kotlin.reflect.KType r1 = r15.getType()
            r0.append(r1)
            java.lang.String r1 = " but a property of type "
            r0.append(r1)
            kotlin.reflect.KType r1 = r14.getReturnType()
            r0.append(r1)
            r1 = 46
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            throw r1
        L_0x01d8:
            boolean r12 = r14 instanceof kotlin.reflect.KMutableProperty1
            if (r12 != 0) goto L_0x01de
            if (r15 == 0) goto L_0x00b8
        L_0x01de:
            if (r11 == 0) goto L_0x01f5
            java.lang.String r11 = r11.name()
            if (r11 == 0) goto L_0x01f5
            java.lang.String r12 = "\u0000"
            boolean r12 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r11, (java.lang.Object) r12)
            if (r12 != 0) goto L_0x01ef
            goto L_0x01f0
        L_0x01ef:
            r11 = r3
        L_0x01f0:
            if (r11 != 0) goto L_0x01f3
            goto L_0x01f5
        L_0x01f3:
            r12 = r11
            goto L_0x01fa
        L_0x01f5:
            java.lang.String r11 = r14.getName()
            goto L_0x01f3
        L_0x01fa:
            kotlin.reflect.KType r11 = r14.getReturnType()
            kotlin.reflect.KClassifier r11 = r11.getClassifier()
            boolean r13 = r11 instanceof kotlin.reflect.KClass
            if (r13 == 0) goto L_0x0272
            kotlin.reflect.KClass r11 = (kotlin.reflect.KClass) r11
            boolean r13 = r11.isValue()
            if (r13 == 0) goto L_0x0269
            java.lang.Class r11 = kotlin.jvm.JvmClassMappingKt.getJavaClass(r11)
            kotlin.reflect.KType r13 = r14.getReturnType()
            java.util.List r13 = r13.getArguments()
            boolean r13 = r13.isEmpty()
            if (r13 == 0) goto L_0x0221
            goto L_0x027e
        L_0x0221:
            kotlin.reflect.KType r13 = r14.getReturnType()
            java.util.List r13 = r13.getArguments()
            java.lang.Iterable r13 = (java.lang.Iterable) r13
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            java.util.Iterator r13 = r13.iterator()
        L_0x0234:
            boolean r16 = r13.hasNext()
            if (r16 == 0) goto L_0x0255
            java.lang.Object r16 = r13.next()
            kotlin.reflect.KTypeProjection r16 = (kotlin.reflect.KTypeProjection) r16
            kotlin.reflect.KType r16 = r16.getType()
            if (r16 == 0) goto L_0x024d
            java.lang.reflect.Type r16 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaType(r16)
            r6 = r16
            goto L_0x024e
        L_0x024d:
            r6 = 0
        L_0x024e:
            if (r6 == 0) goto L_0x0253
            r3.add(r6)
        L_0x0253:
            r6 = 1
            goto L_0x0234
        L_0x0255:
            java.lang.reflect.Type[] r6 = new java.lang.reflect.Type[r10]
            java.lang.Object[] r3 = r3.toArray(r6)
            java.lang.reflect.Type[] r3 = (java.lang.reflect.Type[]) r3
            int r6 = r3.length
            java.lang.Object[] r3 = java.util.Arrays.copyOf(r3, r6)
            java.lang.reflect.Type[] r3 = (java.lang.reflect.Type[]) r3
            java.lang.reflect.ParameterizedType r11 = com.squareup.moshi.Types.newParameterizedType(r11, r3)
            goto L_0x027e
        L_0x0269:
            kotlin.reflect.KType r3 = r14.getReturnType()
            java.lang.reflect.Type r11 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaType(r3)
            goto L_0x027e
        L_0x0272:
            boolean r3 = r11 instanceof kotlin.reflect.KTypeParameter
            if (r3 == 0) goto L_0x02be
            kotlin.reflect.KType r3 = r14.getReturnType()
            java.lang.reflect.Type r11 = kotlin.reflect.jvm.ReflectJvmMapping.getJavaType(r3)
        L_0x027e:
            java.lang.reflect.Type r3 = com.squareup.moshi.internal.Util.resolve(r1, r4, r11)
            java.util.Collection r9 = (java.util.Collection) r9
            java.lang.annotation.Annotation[] r6 = new java.lang.annotation.Annotation[r10]
            java.lang.Object[] r6 = r9.toArray(r6)
            java.lang.annotation.Annotation[] r6 = (java.lang.annotation.Annotation[]) r6
            java.util.Set r6 = com.squareup.moshi.internal.Util.jsonAnnotations((java.lang.annotation.Annotation[]) r6)
            java.lang.String r9 = r14.getName()
            com.squareup.moshi.JsonAdapter r13 = r2.adapter(r3, r6, r9)
            java.lang.String r3 = r14.getName()
            com.squareup.moshi.kotlin.reflect.KotlinJsonAdapter$Binding r6 = new com.squareup.moshi.kotlin.reflect.KotlinJsonAdapter$Binding
            java.lang.String r9 = "adapter"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r13, r9)
            java.lang.String r9 = "null cannot be cast to non-null type kotlin.reflect.KProperty1<kotlin.Any, kotlin.Any?>"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r14, r9)
            if (r15 == 0) goto L_0x02b1
            int r9 = r15.getIndex()
        L_0x02ae:
            r16 = r9
            goto L_0x02b3
        L_0x02b1:
            r9 = -1
            goto L_0x02ae
        L_0x02b3:
            r11 = r6
            r11.<init>(r12, r13, r14, r15, r16)
            r7.put(r3, r6)
            r3 = 0
            r6 = 1
            goto L_0x00b8
        L_0x02be:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "Not possible!"
            r0.<init>(r1)
            throw r0
        L_0x02c6:
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.List r1 = r5.getParameters()
            java.util.Iterator r1 = r1.iterator()
        L_0x02d3:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0312
            java.lang.Object r2 = r1.next()
            kotlin.reflect.KParameter r2 = (kotlin.reflect.KParameter) r2
            java.lang.String r3 = r2.getName()
            java.util.Map r4 = kotlin.jvm.internal.TypeIntrinsics.asMutableMap(r7)
            java.lang.Object r3 = r4.remove(r3)
            com.squareup.moshi.kotlin.reflect.KotlinJsonAdapter$Binding r3 = (com.squareup.moshi.kotlin.reflect.KotlinJsonAdapter.Binding) r3
            if (r3 != 0) goto L_0x030e
            boolean r4 = r2.isOptional()
            if (r4 == 0) goto L_0x02f6
            goto L_0x030e
        L_0x02f6:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "No property for required constructor "
            r0.<init>(r1)
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            throw r1
        L_0x030e:
            r0.add(r3)
            goto L_0x02d3
        L_0x0312:
            int r1 = r0.size()
            java.util.Set r2 = r7.entrySet()
            java.util.Iterator r2 = r2.iterator()
        L_0x031e:
            r16 = r1
            boolean r1 = r2.hasNext()
            if (r1 == 0) goto L_0x0345
            java.lang.Object r1 = r2.next()
            java.util.Map$Entry r1 = (java.util.Map.Entry) r1
            java.lang.Object r1 = r1.getValue()
            r11 = r1
            com.squareup.moshi.kotlin.reflect.KotlinJsonAdapter$Binding r11 = (com.squareup.moshi.kotlin.reflect.KotlinJsonAdapter.Binding) r11
            int r1 = r16 + 1
            r14 = 0
            r15 = 0
            r12 = 0
            r13 = 0
            r17 = 15
            r18 = 0
            com.squareup.moshi.kotlin.reflect.KotlinJsonAdapter$Binding r3 = com.squareup.moshi.kotlin.reflect.KotlinJsonAdapter.Binding.copy$default(r11, r12, r13, r14, r15, r16, r17, r18)
            r0.add(r3)
            goto L_0x031e
        L_0x0345:
            java.util.List r1 = kotlin.collections.CollectionsKt.filterNotNull(r0)
            r2 = r1
            java.lang.Iterable r2 = (java.lang.Iterable) r2
            java.util.ArrayList r3 = new java.util.ArrayList
            int r4 = kotlin.collections.CollectionsKt__IterablesKt.collectionSizeOrDefault(r2, 10)
            r3.<init>(r4)
            java.util.Iterator r2 = r2.iterator()
        L_0x0359:
            boolean r4 = r2.hasNext()
            if (r4 == 0) goto L_0x036d
            java.lang.Object r4 = r2.next()
            com.squareup.moshi.kotlin.reflect.KotlinJsonAdapter$Binding r4 = (com.squareup.moshi.kotlin.reflect.KotlinJsonAdapter.Binding) r4
            java.lang.String r4 = r4.getJsonName()
            r3.add(r4)
            goto L_0x0359
        L_0x036d:
            java.lang.String[] r2 = new java.lang.String[r10]
            java.lang.Object[] r2 = r3.toArray(r2)
            java.lang.String[] r2 = (java.lang.String[]) r2
            int r3 = r2.length
            java.lang.Object[] r2 = java.util.Arrays.copyOf(r2, r3)
            java.lang.String[] r2 = (java.lang.String[]) r2
            com.squareup.moshi.JsonReader$Options r2 = com.squareup.moshi.JsonReader.Options.of(r2)
            com.squareup.moshi.kotlin.reflect.KotlinJsonAdapter r3 = new com.squareup.moshi.kotlin.reflect.KotlinJsonAdapter
            java.lang.String r4 = "options"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r2, r4)
            r3.<init>(r5, r0, r1, r2)
            com.squareup.moshi.JsonAdapter r0 = r3.nullSafe()
            return r0
        L_0x038f:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r1 = "Cannot reflectively serialize sealed class "
            r0.<init>(r1)
            java.lang.String r1 = ". Please register an adapter."
            java.lang.String r0 = androidx.constraintlayout.core.state.b.g(r4, r0, r1)
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            throw r1
        L_0x03a6:
            java.lang.String r0 = r4.getName()
            java.lang.String r1 = "Cannot serialize object declaration "
            java.lang.String r0 = r1.concat(r0)
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            throw r1
        L_0x03ba:
            java.lang.String r0 = r4.getName()
            java.lang.String r1 = "Cannot serialize inner class "
            java.lang.String r0 = r1.concat(r0)
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            throw r1
        L_0x03ce:
            java.lang.String r0 = r4.getName()
            java.lang.String r1 = "Cannot serialize abstract class "
            java.lang.String r0 = r1.concat(r0)
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            throw r1
        L_0x03e2:
            java.lang.String r0 = r4.getName()
            java.lang.String r1 = "Cannot serialize local class or object expression "
            java.lang.String r0 = r1.concat(r0)
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            throw r1
        L_0x03f6:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory.create(java.lang.reflect.Type, java.util.Set, com.squareup.moshi.Moshi):com.squareup.moshi.JsonAdapter");
    }
}
