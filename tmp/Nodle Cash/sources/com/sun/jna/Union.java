package com.sun.jna;

import com.sun.jna.Structure;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public abstract class Union extends Structure {
    private Structure.StructField activeField;

    public Union() {
    }

    private Structure.StructField findField(Class<?> cls) {
        ensureAllocated();
        for (Structure.StructField next : fields().values()) {
            if (next.type.isAssignableFrom(cls)) {
                return next;
            }
        }
        return null;
    }

    public List<String> getFieldOrder() {
        List<Field> fieldList = getFieldList();
        ArrayList arrayList = new ArrayList(fieldList.size());
        for (Field name : fieldList) {
            arrayList.add(name.getName());
        }
        return arrayList;
    }

    public int getNativeAlignment(Class<?> cls, Object obj, boolean z2) {
        return super.getNativeAlignment(cls, obj, true);
    }

    public Object getTypedValue(Class<?> cls) {
        ensureAllocated();
        for (Structure.StructField next : fields().values()) {
            if (next.type == cls) {
                this.activeField = next;
                read();
                return getFieldValue(this.activeField.field);
            }
        }
        throw new IllegalArgumentException("No field of type " + cls + " in " + this);
    }

    public Object readField(String str) {
        ensureAllocated();
        setType(str);
        return super.readField(str);
    }

    public void setType(Class<?> cls) {
        ensureAllocated();
        for (Structure.StructField next : fields().values()) {
            if (next.type == cls) {
                this.activeField = next;
                return;
            }
        }
        throw new IllegalArgumentException("No field of type " + cls + " in " + this);
    }

    public Object setTypedValue(Object obj) {
        Structure.StructField findField = findField(obj.getClass());
        if (findField != null) {
            this.activeField = findField;
            setFieldValue(findField.field, obj);
            return this;
        }
        throw new IllegalArgumentException("No field of type " + obj.getClass() + " in " + this);
    }

    public void writeField(String str) {
        ensureAllocated();
        setType(str);
        super.writeField(str);
    }

    public Union(Pointer pointer) {
        super(pointer);
    }

    public Union(Pointer pointer, int i3) {
        super(pointer, i3);
    }

    public Union(TypeMapper typeMapper) {
        super(typeMapper);
    }

    public Object readField(Structure.StructField structField) {
        if (structField != this.activeField) {
            if (Structure.class.isAssignableFrom(structField.type) || String.class.isAssignableFrom(structField.type) || WString.class.isAssignableFrom(structField.type)) {
                return null;
            }
        }
        return super.readField(structField);
    }

    public void writeField(String str, Object obj) {
        ensureAllocated();
        setType(str);
        super.writeField(str, obj);
    }

    public Union(Pointer pointer, int i3, TypeMapper typeMapper) {
        super(pointer, i3, typeMapper);
    }

    public void setType(String str) {
        ensureAllocated();
        Structure.StructField structField = fields().get(str);
        if (structField != null) {
            this.activeField = structField;
            return;
        }
        throw new IllegalArgumentException("No field named " + str + " in " + this);
    }

    public void writeField(Structure.StructField structField) {
        if (structField == this.activeField) {
            super.writeField(structField);
        }
    }
}
