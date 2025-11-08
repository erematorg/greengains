package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.introspect.AnnotatedClass;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class AnnotatedCreatorCollector extends CollectorBase {
    private final boolean _collectAnnotations;
    private AnnotatedConstructor _defaultConstructor;
    private final TypeResolutionContext _typeContext;

    public AnnotatedCreatorCollector(AnnotationIntrospector annotationIntrospector, TypeResolutionContext typeResolutionContext, boolean z2) {
        super(annotationIntrospector);
        this._typeContext = typeResolutionContext;
        this._collectAnnotations = z2;
    }

    private List<AnnotatedConstructor> _findPotentialConstructors(JavaType javaType, Class<?> cls) {
        ArrayList arrayList;
        ClassUtil.Ctor ctor;
        List<AnnotatedConstructor> list;
        int i3;
        if (!javaType.isEnumType()) {
            ctor = null;
            arrayList = null;
            for (ClassUtil.Ctor ctor2 : ClassUtil.getConstructors(javaType.getRawClass())) {
                if (isIncludableConstructor(ctor2.getConstructor())) {
                    if (ctor2.getParamCount() == 0) {
                        ctor = ctor2;
                    } else {
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        arrayList.add(ctor2);
                    }
                }
            }
        } else {
            ctor = null;
            arrayList = null;
        }
        if (arrayList == null) {
            list = Collections.emptyList();
            if (ctor == null) {
                return list;
            }
            i3 = 0;
        } else {
            int size = arrayList.size();
            ArrayList arrayList2 = new ArrayList(size);
            for (int i4 = 0; i4 < size; i4++) {
                arrayList2.add((Object) null);
            }
            ArrayList arrayList3 = arrayList2;
            i3 = size;
            list = arrayList3;
        }
        if (cls != null) {
            MemberKey[] memberKeyArr = null;
            for (ClassUtil.Ctor ctor3 : ClassUtil.getConstructors(cls)) {
                if (ctor3.getParamCount() == 0) {
                    if (ctor != null) {
                        this._defaultConstructor = constructDefaultConstructor(ctor, ctor3);
                        ctor = null;
                    }
                } else if (arrayList != null) {
                    if (memberKeyArr == null) {
                        memberKeyArr = new MemberKey[i3];
                        for (int i5 = 0; i5 < i3; i5++) {
                            memberKeyArr[i5] = new MemberKey(((ClassUtil.Ctor) arrayList.get(i5)).getConstructor());
                        }
                    }
                    MemberKey memberKey = new MemberKey(ctor3.getConstructor());
                    int i6 = 0;
                    while (true) {
                        if (i6 >= i3) {
                            break;
                        } else if (memberKey.equals(memberKeyArr[i6])) {
                            list.set(i6, constructNonDefaultConstructor((ClassUtil.Ctor) arrayList.get(i6), ctor3));
                            break;
                        } else {
                            i6++;
                        }
                    }
                }
            }
        }
        if (ctor != null) {
            this._defaultConstructor = constructDefaultConstructor(ctor, (ClassUtil.Ctor) null);
        }
        for (int i7 = 0; i7 < i3; i7++) {
            if (list.get(i7) == null) {
                list.set(i7, constructNonDefaultConstructor((ClassUtil.Ctor) arrayList.get(i7), (ClassUtil.Ctor) null));
            }
        }
        return list;
    }

    private List<AnnotatedMethod> _findPotentialFactories(TypeFactory typeFactory, JavaType javaType, Class<?> cls) {
        ArrayList arrayList = null;
        for (Method method : ClassUtil.getClassMethods(javaType.getRawClass())) {
            if (_isIncludableFactoryMethod(method)) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(method);
            }
        }
        if (arrayList == null) {
            return Collections.emptyList();
        }
        TypeResolutionContext typeResolutionContext = this._typeContext;
        int size = arrayList.size();
        ArrayList arrayList2 = new ArrayList(size);
        for (int i3 = 0; i3 < size; i3++) {
            arrayList2.add((Object) null);
        }
        if (cls != null) {
            MemberKey[] memberKeyArr = null;
            for (Method method2 : cls.getDeclaredMethods()) {
                if (_isIncludableFactoryMethod(method2)) {
                    if (memberKeyArr == null) {
                        memberKeyArr = new MemberKey[size];
                        for (int i4 = 0; i4 < size; i4++) {
                            memberKeyArr[i4] = new MemberKey((Method) arrayList.get(i4));
                        }
                    }
                    MemberKey memberKey = new MemberKey(method2);
                    int i5 = 0;
                    while (true) {
                        if (i5 >= size) {
                            break;
                        } else if (memberKey.equals(memberKeyArr[i5])) {
                            arrayList2.set(i5, constructFactoryCreator((Method) arrayList.get(i5), typeResolutionContext, method2));
                            break;
                        } else {
                            i5++;
                        }
                    }
                }
            }
        }
        for (int i6 = 0; i6 < size; i6++) {
            if (((AnnotatedMethod) arrayList2.get(i6)) == null) {
                Method method3 = (Method) arrayList.get(i6);
                arrayList2.set(i6, constructFactoryCreator(method3, MethodGenericTypeResolver.narrowMethodTypeParameters(method3, javaType, typeFactory, typeResolutionContext), (Method) null));
            } else {
                TypeFactory typeFactory2 = typeFactory;
                JavaType javaType2 = javaType;
            }
        }
        return arrayList2;
    }

    private static boolean _isIncludableFactoryMethod(Method method) {
        return Modifier.isStatic(method.getModifiers()) && !method.isSynthetic();
    }

    private AnnotationMap[] collectAnnotations(Annotation[][] annotationArr, Annotation[][] annotationArr2) {
        if (!this._collectAnnotations) {
            return CollectorBase.NO_ANNOTATION_MAPS;
        }
        int length = annotationArr.length;
        AnnotationMap[] annotationMapArr = new AnnotationMap[length];
        for (int i3 = 0; i3 < length; i3++) {
            AnnotationCollector collectAnnotations = collectAnnotations(AnnotationCollector.emptyCollector(), annotationArr[i3]);
            if (annotationArr2 != null) {
                collectAnnotations = collectAnnotations(collectAnnotations, annotationArr2[i3]);
            }
            annotationMapArr[i3] = collectAnnotations.asAnnotationMap();
        }
        return annotationMapArr;
    }

    public static AnnotatedClass.Creators collectCreators(AnnotationIntrospector annotationIntrospector, TypeFactory typeFactory, TypeResolutionContext typeResolutionContext, JavaType javaType, Class<?> cls, boolean z2) {
        return new AnnotatedCreatorCollector(annotationIntrospector, typeResolutionContext, z2 | (cls != null)).collect(typeFactory, javaType, cls);
    }

    private static boolean isIncludableConstructor(Constructor<?> constructor) {
        return !constructor.isSynthetic();
    }

    public AnnotatedClass.Creators collect(TypeFactory typeFactory, JavaType javaType, Class<?> cls) {
        List<AnnotatedConstructor> _findPotentialConstructors = _findPotentialConstructors(javaType, cls);
        List<AnnotatedMethod> _findPotentialFactories = _findPotentialFactories(typeFactory, javaType, cls);
        if (this._collectAnnotations) {
            AnnotatedConstructor annotatedConstructor = this._defaultConstructor;
            if (annotatedConstructor != null && this._intr.hasIgnoreMarker(annotatedConstructor)) {
                this._defaultConstructor = null;
            }
            int size = _findPotentialConstructors.size();
            while (true) {
                size--;
                if (size < 0) {
                    break;
                } else if (this._intr.hasIgnoreMarker(_findPotentialConstructors.get(size))) {
                    _findPotentialConstructors.remove(size);
                }
            }
            int size2 = _findPotentialFactories.size();
            while (true) {
                size2--;
                if (size2 < 0) {
                    break;
                } else if (this._intr.hasIgnoreMarker(_findPotentialFactories.get(size2))) {
                    _findPotentialFactories.remove(size2);
                }
            }
        }
        return new AnnotatedClass.Creators(this._defaultConstructor, _findPotentialConstructors, _findPotentialFactories);
    }

    public AnnotatedConstructor constructDefaultConstructor(ClassUtil.Ctor ctor, ClassUtil.Ctor ctor2) {
        return new AnnotatedConstructor(this._typeContext, ctor.getConstructor(), collectAnnotations(ctor, ctor2), CollectorBase.NO_ANNOTATION_MAPS);
    }

    public AnnotatedMethod constructFactoryCreator(Method method, TypeResolutionContext typeResolutionContext, Method method2) {
        int parameterCount = method.getParameterCount();
        if (this._intr == null) {
            return new AnnotatedMethod(typeResolutionContext, method, CollectorBase._emptyAnnotationMap(), CollectorBase._emptyAnnotationMaps(parameterCount));
        }
        if (parameterCount == 0) {
            return new AnnotatedMethod(typeResolutionContext, method, collectAnnotations((AnnotatedElement) method, (AnnotatedElement) method2), CollectorBase.NO_ANNOTATION_MAPS);
        }
        return new AnnotatedMethod(typeResolutionContext, method, collectAnnotations((AnnotatedElement) method, (AnnotatedElement) method2), collectAnnotations(method.getParameterAnnotations(), method2 == null ? null : method2.getParameterAnnotations()));
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v0, resolved type: java.lang.annotation.Annotation[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v1, resolved type: com.fasterxml.jackson.databind.introspect.AnnotationMap[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: java.lang.annotation.Annotation[][]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v5, resolved type: com.fasterxml.jackson.databind.introspect.AnnotationMap[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v9, resolved type: com.fasterxml.jackson.databind.introspect.AnnotationMap[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v10, resolved type: java.lang.annotation.Annotation[][]} */
    /* JADX WARNING: type inference failed for: r3v12 */
    /* JADX WARNING: Failed to insert additional move for type inference */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0075  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.fasterxml.jackson.databind.introspect.AnnotatedConstructor constructNonDefaultConstructor(com.fasterxml.jackson.databind.util.ClassUtil.Ctor r8, com.fasterxml.jackson.databind.util.ClassUtil.Ctor r9) {
        /*
            r7 = this;
            int r0 = r8.getParamCount()
            com.fasterxml.jackson.databind.AnnotationIntrospector r1 = r7._intr
            if (r1 != 0) goto L_0x001c
            com.fasterxml.jackson.databind.introspect.AnnotatedConstructor r9 = new com.fasterxml.jackson.databind.introspect.AnnotatedConstructor
            com.fasterxml.jackson.databind.introspect.TypeResolutionContext r7 = r7._typeContext
            java.lang.reflect.Constructor r8 = r8.getConstructor()
            com.fasterxml.jackson.databind.introspect.AnnotationMap r1 = com.fasterxml.jackson.databind.introspect.CollectorBase._emptyAnnotationMap()
            com.fasterxml.jackson.databind.introspect.AnnotationMap[] r0 = com.fasterxml.jackson.databind.introspect.CollectorBase._emptyAnnotationMaps(r0)
            r9.<init>(r7, r8, r1, r0)
            return r9
        L_0x001c:
            if (r0 != 0) goto L_0x0030
            com.fasterxml.jackson.databind.introspect.AnnotatedConstructor r0 = new com.fasterxml.jackson.databind.introspect.AnnotatedConstructor
            com.fasterxml.jackson.databind.introspect.TypeResolutionContext r1 = r7._typeContext
            java.lang.reflect.Constructor r2 = r8.getConstructor()
            com.fasterxml.jackson.databind.introspect.AnnotationMap r7 = r7.collectAnnotations((com.fasterxml.jackson.databind.util.ClassUtil.Ctor) r8, (com.fasterxml.jackson.databind.util.ClassUtil.Ctor) r9)
            com.fasterxml.jackson.databind.introspect.AnnotationMap[] r8 = com.fasterxml.jackson.databind.introspect.CollectorBase.NO_ANNOTATION_MAPS
            r0.<init>(r1, r2, r7, r8)
            return r0
        L_0x0030:
            java.lang.annotation.Annotation[][] r1 = r8.getParameterAnnotations()
            int r2 = r1.length
            r3 = 0
            if (r0 == r2) goto L_0x0096
            java.lang.Class r2 = r8.getDeclaringClass()
            boolean r4 = com.fasterxml.jackson.databind.util.ClassUtil.isEnumType(r2)
            r5 = 0
            if (r4 == 0) goto L_0x0056
            int r4 = r1.length
            r6 = 2
            int r4 = r4 + r6
            if (r0 != r4) goto L_0x0056
            int r2 = r1.length
            int r2 = r2 + r6
            java.lang.annotation.Annotation[][] r2 = new java.lang.annotation.Annotation[r2][]
            int r4 = r1.length
            java.lang.System.arraycopy(r1, r5, r2, r6, r4)
            com.fasterxml.jackson.databind.introspect.AnnotationMap[] r3 = r7.collectAnnotations((java.lang.annotation.Annotation[][]) r2, (java.lang.annotation.Annotation[][]) r3)
        L_0x0054:
            r1 = r2
            goto L_0x0072
        L_0x0056:
            boolean r2 = r2.isMemberClass()
            if (r2 == 0) goto L_0x0072
            int r2 = r1.length
            r4 = 1
            int r2 = r2 + r4
            if (r0 != r2) goto L_0x0072
            int r2 = r1.length
            int r2 = r2 + r4
            java.lang.annotation.Annotation[][] r2 = new java.lang.annotation.Annotation[r2][]
            int r6 = r1.length
            java.lang.System.arraycopy(r1, r5, r2, r4, r6)
            java.lang.annotation.Annotation[] r1 = com.fasterxml.jackson.databind.introspect.CollectorBase.NO_ANNOTATIONS
            r2[r5] = r1
            com.fasterxml.jackson.databind.introspect.AnnotationMap[] r3 = r7.collectAnnotations((java.lang.annotation.Annotation[][]) r2, (java.lang.annotation.Annotation[][]) r3)
            goto L_0x0054
        L_0x0072:
            if (r3 == 0) goto L_0x0075
            goto L_0x00a1
        L_0x0075:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.Class r8 = r8.getDeclaringClass()
            java.lang.String r8 = r8.getName()
            java.lang.Integer r9 = java.lang.Integer.valueOf(r0)
            int r0 = r1.length
            java.lang.Integer r0 = java.lang.Integer.valueOf(r0)
            java.lang.Object[] r8 = new java.lang.Object[]{r8, r9, r0}
            java.lang.String r9 = "Internal error: constructor for %s has mismatch: %d parameters; %d sets of annotations"
            java.lang.String r8 = java.lang.String.format(r9, r8)
            r7.<init>(r8)
            throw r7
        L_0x0096:
            if (r9 != 0) goto L_0x0099
            goto L_0x009d
        L_0x0099:
            java.lang.annotation.Annotation[][] r3 = r9.getParameterAnnotations()
        L_0x009d:
            com.fasterxml.jackson.databind.introspect.AnnotationMap[] r3 = r7.collectAnnotations((java.lang.annotation.Annotation[][]) r1, (java.lang.annotation.Annotation[][]) r3)
        L_0x00a1:
            com.fasterxml.jackson.databind.introspect.AnnotatedConstructor r0 = new com.fasterxml.jackson.databind.introspect.AnnotatedConstructor
            com.fasterxml.jackson.databind.introspect.TypeResolutionContext r1 = r7._typeContext
            java.lang.reflect.Constructor r2 = r8.getConstructor()
            com.fasterxml.jackson.databind.introspect.AnnotationMap r7 = r7.collectAnnotations((com.fasterxml.jackson.databind.util.ClassUtil.Ctor) r8, (com.fasterxml.jackson.databind.util.ClassUtil.Ctor) r9)
            r0.<init>(r1, r2, r7, r3)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.databind.introspect.AnnotatedCreatorCollector.constructNonDefaultConstructor(com.fasterxml.jackson.databind.util.ClassUtil$Ctor, com.fasterxml.jackson.databind.util.ClassUtil$Ctor):com.fasterxml.jackson.databind.introspect.AnnotatedConstructor");
    }

    private AnnotationMap collectAnnotations(ClassUtil.Ctor ctor, ClassUtil.Ctor ctor2) {
        if (!this._collectAnnotations) {
            return CollectorBase._emptyAnnotationMap();
        }
        AnnotationCollector collectAnnotations = collectAnnotations(ctor.getDeclaredAnnotations());
        if (ctor2 != null) {
            collectAnnotations = collectAnnotations(collectAnnotations, ctor2.getDeclaredAnnotations());
        }
        return collectAnnotations.asAnnotationMap();
    }

    private final AnnotationMap collectAnnotations(AnnotatedElement annotatedElement, AnnotatedElement annotatedElement2) {
        AnnotationCollector collectAnnotations = collectAnnotations(annotatedElement.getDeclaredAnnotations());
        if (annotatedElement2 != null) {
            collectAnnotations = collectAnnotations(collectAnnotations, annotatedElement2.getDeclaredAnnotations());
        }
        return collectAnnotations.asAnnotationMap();
    }
}
