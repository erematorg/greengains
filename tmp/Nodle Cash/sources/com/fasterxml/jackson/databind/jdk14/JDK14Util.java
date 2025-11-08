package com.fasterxml.jackson.databind.jdk14;

import androidx.camera.camera2.internal.C0118y;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.introspect.AnnotatedConstructor;
import com.fasterxml.jackson.databind.util.ClassUtil;
import com.fasterxml.jackson.databind.util.NativeImageUtil;
import io.zksync.wrappers.IZkSync;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class JDK14Util {

    public static class CreatorLocator {
        protected final BeanDescription _beanDesc;
        protected final DeserializationConfig _config;
        protected final List<AnnotatedConstructor> _constructors;
        protected final AnnotationIntrospector _intr;
        protected final AnnotatedConstructor _primaryConstructor;
        protected final RawTypeName[] _recordFields;

        public CreatorLocator(DeserializationContext deserializationContext, BeanDescription beanDescription) {
            this._beanDesc = beanDescription;
            this._intr = deserializationContext.getAnnotationIntrospector();
            this._config = deserializationContext.getConfig();
            RawTypeName[] recordFields = RecordAccessor.instance().getRecordFields(beanDescription.getBeanClass());
            this._recordFields = recordFields;
            AnnotatedConstructor annotatedConstructor = null;
            if (recordFields == null) {
                this._constructors = beanDescription.getConstructors();
                this._primaryConstructor = null;
                return;
            }
            int length = recordFields.length;
            if (length != 0) {
                List<AnnotatedConstructor> constructors = beanDescription.getConstructors();
                this._constructors = constructors;
                Iterator<AnnotatedConstructor> it = constructors.iterator();
                loop0:
                while (true) {
                    if (!it.hasNext()) {
                        break;
                    }
                    AnnotatedConstructor next = it.next();
                    if (next.getParameterCount() == length) {
                        int i3 = 0;
                        while (i3 < length) {
                            if (next.getRawParameterType(i3).equals(this._recordFields[i3].rawType)) {
                                i3++;
                            }
                        }
                        annotatedConstructor = next;
                        break loop0;
                    }
                }
            } else {
                annotatedConstructor = beanDescription.findDefaultConstructor();
                this._constructors = Collections.singletonList(annotatedConstructor);
            }
            if (annotatedConstructor != null) {
                this._primaryConstructor = annotatedConstructor;
                return;
            }
            throw new IllegalArgumentException("Failed to find the canonical Record constructor of type " + ClassUtil.getTypeDescription(this._beanDesc.getType()));
        }

        public AnnotatedConstructor locate(List<String> list) {
            for (AnnotatedConstructor next : this._constructors) {
                JsonCreator.Mode findCreatorAnnotation = this._intr.findCreatorAnnotation(this._config, next);
                if (!(findCreatorAnnotation == null || JsonCreator.Mode.DISABLED == findCreatorAnnotation)) {
                    if (JsonCreator.Mode.DELEGATING == findCreatorAnnotation || next != this._primaryConstructor) {
                        return null;
                    }
                }
            }
            RawTypeName[] rawTypeNameArr = this._recordFields;
            if (rawTypeNameArr == null) {
                return null;
            }
            for (RawTypeName rawTypeName : rawTypeNameArr) {
                list.add(rawTypeName.name);
            }
            return this._primaryConstructor;
        }
    }

    public static class RawTypeName {
        public final String name;
        public final Class<?> rawType;

        public RawTypeName(Class<?> cls, String str) {
            this.rawType = cls;
            this.name = str;
        }
    }

    public static class RecordAccessor {
        private static final RecordAccessor INSTANCE;
        private static final RuntimeException PROBLEM;
        private final Method RECORD_COMPONENT_GET_NAME;
        private final Method RECORD_COMPONENT_GET_TYPE;
        private final Method RECORD_GET_RECORD_COMPONENTS;

        static {
            RecordAccessor recordAccessor = null;
            try {
                e = null;
                recordAccessor = new RecordAccessor();
            } catch (RuntimeException e3) {
                e = e3;
            }
            INSTANCE = recordAccessor;
            PROBLEM = e;
        }

        private RecordAccessor() throws RuntimeException {
            try {
                this.RECORD_GET_RECORD_COMPONENTS = Class.class.getMethod("getRecordComponents", (Class[]) null);
                Class<?> cls = Class.forName("java.lang.reflect.RecordComponent");
                this.RECORD_COMPONENT_GET_NAME = cls.getMethod(IZkSync.FUNC_GETNAME, (Class[]) null);
                this.RECORD_COMPONENT_GET_TYPE = cls.getMethod("getType", (Class[]) null);
            } catch (Exception e3) {
                throw new RuntimeException(C0118y.f("Failed to access Methods needed to support `java.lang.Record`: (", e3.getClass().getName(), ") ", e3.getMessage()), e3);
            }
        }

        public static RecordAccessor instance() {
            RuntimeException runtimeException = PROBLEM;
            if (runtimeException == null) {
                return INSTANCE;
            }
            throw runtimeException;
        }

        public String[] getRecordFieldNames(Class<?> cls) throws IllegalArgumentException {
            Object[] recordComponents = recordComponents(cls);
            if (recordComponents == null) {
                return null;
            }
            String[] strArr = new String[recordComponents.length];
            int i3 = 0;
            while (i3 < recordComponents.length) {
                try {
                    strArr[i3] = (String) this.RECORD_COMPONENT_GET_NAME.invoke(recordComponents[i3], (Object[]) null);
                    i3++;
                } catch (Exception e3) {
                    throw new IllegalArgumentException(String.format("Failed to access name of field #%d (of %d) of Record type %s", new Object[]{Integer.valueOf(i3), Integer.valueOf(recordComponents.length), ClassUtil.nameOf(cls)}), e3);
                }
            }
            return strArr;
        }

        public RawTypeName[] getRecordFields(Class<?> cls) throws IllegalArgumentException {
            Object[] recordComponents = recordComponents(cls);
            if (recordComponents == null) {
                return null;
            }
            RawTypeName[] rawTypeNameArr = new RawTypeName[recordComponents.length];
            int i3 = 0;
            while (i3 < recordComponents.length) {
                try {
                    try {
                        rawTypeNameArr[i3] = new RawTypeName((Class) this.RECORD_COMPONENT_GET_TYPE.invoke(recordComponents[i3], (Object[]) null), (String) this.RECORD_COMPONENT_GET_NAME.invoke(recordComponents[i3], (Object[]) null));
                        i3++;
                    } catch (Exception e3) {
                        throw new IllegalArgumentException(String.format("Failed to access type of field #%d (of %d) of Record type %s", new Object[]{Integer.valueOf(i3), Integer.valueOf(recordComponents.length), ClassUtil.nameOf(cls)}), e3);
                    }
                } catch (Exception e4) {
                    throw new IllegalArgumentException(String.format("Failed to access name of field #%d (of %d) of Record type %s", new Object[]{Integer.valueOf(i3), Integer.valueOf(recordComponents.length), ClassUtil.nameOf(cls)}), e4);
                }
            }
            return rawTypeNameArr;
        }

        public Object[] recordComponents(Class<?> cls) throws IllegalArgumentException {
            try {
                return (Object[]) this.RECORD_GET_RECORD_COMPONENTS.invoke(cls, (Object[]) null);
            } catch (Exception e3) {
                if (NativeImageUtil.isUnsupportedFeatureError(e3)) {
                    return null;
                }
                throw new IllegalArgumentException("Failed to access RecordComponents of type " + ClassUtil.nameOf(cls));
            }
        }
    }

    public static AnnotatedConstructor findRecordConstructor(DeserializationContext deserializationContext, BeanDescription beanDescription, List<String> list) {
        return new CreatorLocator(deserializationContext, beanDescription).locate(list);
    }

    public static String[] getRecordFieldNames(Class<?> cls) {
        return RecordAccessor.instance().getRecordFieldNames(cls);
    }
}
