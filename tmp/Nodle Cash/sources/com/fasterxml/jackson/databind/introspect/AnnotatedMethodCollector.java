package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.introspect.ClassIntrospector;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.List;
import java.util.Map;

public class AnnotatedMethodCollector extends CollectorBase {
    private final boolean _collectAnnotations;
    private final ClassIntrospector.MixInResolver _mixInResolver;

    public static final class MethodBuilder {
        public AnnotationCollector annotations;
        public Method method;
        public TypeResolutionContext typeContext;

        public MethodBuilder(TypeResolutionContext typeResolutionContext, Method method2, AnnotationCollector annotationCollector) {
            this.typeContext = typeResolutionContext;
            this.method = method2;
            this.annotations = annotationCollector;
        }

        public AnnotatedMethod build() {
            Method method2 = this.method;
            if (method2 == null) {
                return null;
            }
            return new AnnotatedMethod(this.typeContext, method2, this.annotations.asAnnotationMap(), (AnnotationMap[]) null);
        }
    }

    public AnnotatedMethodCollector(AnnotationIntrospector annotationIntrospector, ClassIntrospector.MixInResolver mixInResolver, boolean z2) {
        super(annotationIntrospector);
        this._mixInResolver = annotationIntrospector == null ? null : mixInResolver;
        this._collectAnnotations = z2;
    }

    private void _addMemberMethods(TypeResolutionContext typeResolutionContext, Class<?> cls, Map<MemberKey, MethodBuilder> map, Class<?> cls2) {
        if (cls2 != null) {
            _addMethodMixIns(typeResolutionContext, cls, map, cls2);
        }
        if (cls != null) {
            for (Method method : ClassUtil.getClassMethods(cls)) {
                if (_isIncludableMemberMethod(method)) {
                    MemberKey memberKey = new MemberKey(method);
                    MethodBuilder methodBuilder = map.get(memberKey);
                    if (methodBuilder == null) {
                        map.put(memberKey, new MethodBuilder(typeResolutionContext, method, this._intr == null ? AnnotationCollector.emptyCollector() : collectAnnotations(method.getDeclaredAnnotations())));
                    } else {
                        if (this._collectAnnotations) {
                            methodBuilder.annotations = collectDefaultAnnotations(methodBuilder.annotations, method.getDeclaredAnnotations());
                        }
                        Method method2 = methodBuilder.method;
                        if (method2 == null) {
                            methodBuilder.method = method;
                        } else if (Modifier.isAbstract(method2.getModifiers()) && !Modifier.isAbstract(method.getModifiers())) {
                            methodBuilder.method = method;
                            methodBuilder.typeContext = typeResolutionContext;
                        }
                    }
                }
            }
        }
    }

    private static boolean _isIncludableMemberMethod(Method method) {
        return !Modifier.isStatic(method.getModifiers()) && !method.isSynthetic() && !method.isBridge() && method.getParameterCount() <= 2;
    }

    public static AnnotatedMethodMap collectMethods(AnnotationIntrospector annotationIntrospector, TypeResolutionContext typeResolutionContext, ClassIntrospector.MixInResolver mixInResolver, TypeFactory typeFactory, JavaType javaType, List<JavaType> list, Class<?> cls, boolean z2) {
        return new AnnotatedMethodCollector(annotationIntrospector, mixInResolver, z2).collect(typeFactory, typeResolutionContext, javaType, list, cls);
    }

    public void _addMethodMixIns(TypeResolutionContext typeResolutionContext, Class<?> cls, Map<MemberKey, MethodBuilder> map, Class<?> cls2) {
        if (this._intr != null) {
            for (Class<?> declaredMethods : ClassUtil.findRawSuperTypes(cls2, cls, true)) {
                for (Method method : declaredMethods.getDeclaredMethods()) {
                    if (_isIncludableMemberMethod(method)) {
                        MemberKey memberKey = new MemberKey(method);
                        MethodBuilder methodBuilder = map.get(memberKey);
                        Annotation[] declaredAnnotations = method.getDeclaredAnnotations();
                        if (methodBuilder == null) {
                            map.put(memberKey, new MethodBuilder(typeResolutionContext, (Method) null, collectAnnotations(declaredAnnotations)));
                        } else {
                            methodBuilder.annotations = collectDefaultAnnotations(methodBuilder.annotations, declaredAnnotations);
                        }
                    }
                }
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x003f, code lost:
        r8 = java.lang.Object.class;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.introspect.AnnotatedMethodMap collect(com.fasterxml.jackson.databind.type.TypeFactory r5, com.fasterxml.jackson.databind.introspect.TypeResolutionContext r6, com.fasterxml.jackson.databind.JavaType r7, java.util.List<com.fasterxml.jackson.databind.JavaType> r8, java.lang.Class<?> r9) {
        /*
            r4 = this;
            java.util.LinkedHashMap r0 = new java.util.LinkedHashMap
            r0.<init>()
            java.lang.Class r1 = r7.getRawClass()
            r4._addMemberMethods(r6, r1, r0, r9)
            java.util.Iterator r8 = r8.iterator()
        L_0x0010:
            boolean r9 = r8.hasNext()
            r1 = 0
            if (r9 == 0) goto L_0x003b
            java.lang.Object r9 = r8.next()
            com.fasterxml.jackson.databind.JavaType r9 = (com.fasterxml.jackson.databind.JavaType) r9
            com.fasterxml.jackson.databind.introspect.ClassIntrospector$MixInResolver r2 = r4._mixInResolver
            if (r2 != 0) goto L_0x0022
            goto L_0x002a
        L_0x0022:
            java.lang.Class r1 = r9.getRawClass()
            java.lang.Class r1 = r2.findMixInClassFor(r1)
        L_0x002a:
            com.fasterxml.jackson.databind.introspect.TypeResolutionContext$Basic r2 = new com.fasterxml.jackson.databind.introspect.TypeResolutionContext$Basic
            com.fasterxml.jackson.databind.type.TypeBindings r3 = r9.getBindings()
            r2.<init>(r5, r3)
            java.lang.Class r9 = r9.getRawClass()
            r4._addMemberMethods(r2, r9, r0, r1)
            goto L_0x0010
        L_0x003b:
            com.fasterxml.jackson.databind.introspect.ClassIntrospector$MixInResolver r5 = r4._mixInResolver
            if (r5 == 0) goto L_0x00a4
            java.lang.Class<java.lang.Object> r8 = java.lang.Object.class
            java.lang.Class r5 = r5.findMixInClassFor(r8)
            if (r5 == 0) goto L_0x00a4
            java.lang.Class r7 = r7.getRawClass()
            r4._addMethodMixIns(r6, r7, r0, r5)
            com.fasterxml.jackson.databind.AnnotationIntrospector r5 = r4._intr
            if (r5 == 0) goto L_0x00a4
            boolean r5 = r0.isEmpty()
            if (r5 != 0) goto L_0x00a4
            java.util.Set r5 = r0.entrySet()
            java.util.Iterator r5 = r5.iterator()
        L_0x0060:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L_0x00a4
            java.lang.Object r6 = r5.next()
            java.util.Map$Entry r6 = (java.util.Map.Entry) r6
            java.lang.Object r7 = r6.getKey()
            com.fasterxml.jackson.databind.introspect.MemberKey r7 = (com.fasterxml.jackson.databind.introspect.MemberKey) r7
            java.lang.String r9 = r7.getName()
            java.lang.String r2 = "hashCode"
            boolean r9 = r2.equals(r9)
            if (r9 == 0) goto L_0x0060
            int r9 = r7.argCount()
            if (r9 == 0) goto L_0x0085
            goto L_0x0060
        L_0x0085:
            java.lang.String r7 = r7.getName()     // Catch:{ Exception -> 0x0060 }
            java.lang.reflect.Method r7 = r8.getDeclaredMethod(r7, r1)     // Catch:{ Exception -> 0x0060 }
            if (r7 == 0) goto L_0x0060
            java.lang.Object r6 = r6.getValue()     // Catch:{ Exception -> 0x0060 }
            com.fasterxml.jackson.databind.introspect.AnnotatedMethodCollector$MethodBuilder r6 = (com.fasterxml.jackson.databind.introspect.AnnotatedMethodCollector.MethodBuilder) r6     // Catch:{ Exception -> 0x0060 }
            com.fasterxml.jackson.databind.introspect.AnnotationCollector r9 = r6.annotations     // Catch:{ Exception -> 0x0060 }
            java.lang.annotation.Annotation[] r2 = r7.getDeclaredAnnotations()     // Catch:{ Exception -> 0x0060 }
            com.fasterxml.jackson.databind.introspect.AnnotationCollector r9 = r4.collectDefaultAnnotations(r9, r2)     // Catch:{ Exception -> 0x0060 }
            r6.annotations = r9     // Catch:{ Exception -> 0x0060 }
            r6.method = r7     // Catch:{ Exception -> 0x0060 }
            goto L_0x0060
        L_0x00a4:
            boolean r4 = r0.isEmpty()
            if (r4 == 0) goto L_0x00b0
            com.fasterxml.jackson.databind.introspect.AnnotatedMethodMap r4 = new com.fasterxml.jackson.databind.introspect.AnnotatedMethodMap
            r4.<init>()
            return r4
        L_0x00b0:
            java.util.LinkedHashMap r4 = new java.util.LinkedHashMap
            int r5 = r0.size()
            r4.<init>(r5)
            java.util.Set r5 = r0.entrySet()
            java.util.Iterator r5 = r5.iterator()
        L_0x00c1:
            boolean r6 = r5.hasNext()
            if (r6 == 0) goto L_0x00e1
            java.lang.Object r6 = r5.next()
            java.util.Map$Entry r6 = (java.util.Map.Entry) r6
            java.lang.Object r7 = r6.getValue()
            com.fasterxml.jackson.databind.introspect.AnnotatedMethodCollector$MethodBuilder r7 = (com.fasterxml.jackson.databind.introspect.AnnotatedMethodCollector.MethodBuilder) r7
            com.fasterxml.jackson.databind.introspect.AnnotatedMethod r7 = r7.build()
            if (r7 == 0) goto L_0x00c1
            java.lang.Object r6 = r6.getKey()
            r4.put(r6, r7)
            goto L_0x00c1
        L_0x00e1:
            com.fasterxml.jackson.databind.introspect.AnnotatedMethodMap r5 = new com.fasterxml.jackson.databind.introspect.AnnotatedMethodMap
            r5.<init>(r4)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.introspect.AnnotatedMethodCollector.collect(com.fasterxml.jackson.databind.type.TypeFactory, com.fasterxml.jackson.databind.introspect.TypeResolutionContext, com.fasterxml.jackson.databind.JavaType, java.util.List, java.lang.Class):com.fasterxml.jackson.databind.introspect.AnnotatedMethodMap");
    }
}
