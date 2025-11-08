package com.fasterxml.jackson.annotation;

import java.io.Serializable;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;

@Target({ElementType.ANNOTATION_TYPE, ElementType.TYPE})
@JacksonAnnotation
@Retention(RetentionPolicy.RUNTIME)
public @interface JsonAutoDetect {

    /* renamed from: com.fasterxml.jackson.annotation.JsonAutoDetect$1  reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$annotation$JsonAutoDetect$Visibility;
        static final /* synthetic */ int[] $SwitchMap$com$fasterxml$jackson$annotation$PropertyAccessor;

        /* JADX WARNING: Can't wrap try/catch for region: R(26:0|(2:1|2)|3|(2:5|6)|7|(2:9|10)|11|(2:13|14)|15|17|18|19|20|(2:21|22)|23|25|26|27|28|29|30|31|32|33|34|36) */
        /* JADX WARNING: Can't wrap try/catch for region: R(27:0|(2:1|2)|3|(2:5|6)|7|9|10|11|(2:13|14)|15|17|18|19|20|(2:21|22)|23|25|26|27|28|29|30|31|32|33|34|36) */
        /* JADX WARNING: Can't wrap try/catch for region: R(29:0|1|2|3|(2:5|6)|7|9|10|11|13|14|15|17|18|19|20|(2:21|22)|23|25|26|27|28|29|30|31|32|33|34|36) */
        /* JADX WARNING: Can't wrap try/catch for region: R(30:0|1|2|3|(2:5|6)|7|9|10|11|13|14|15|17|18|19|20|21|22|23|25|26|27|28|29|30|31|32|33|34|36) */
        /* JADX WARNING: Code restructure failed: missing block: B:37:?, code lost:
            return;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:19:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:21:0x0049 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:27:0x0065 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:29:0x006f */
        /* JADX WARNING: Missing exception handler attribute for start block: B:31:0x0079 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:33:0x0083 */
        static {
            /*
                com.fasterxml.jackson.annotation.PropertyAccessor[] r0 = com.fasterxml.jackson.annotation.PropertyAccessor.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$fasterxml$jackson$annotation$PropertyAccessor = r0
                r1 = 1
                com.fasterxml.jackson.annotation.PropertyAccessor r2 = com.fasterxml.jackson.annotation.PropertyAccessor.CREATOR     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r2 = r2.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r0[r2] = r1     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                r0 = 2
                int[] r2 = $SwitchMap$com$fasterxml$jackson$annotation$PropertyAccessor     // Catch:{ NoSuchFieldError -> 0x001d }
                com.fasterxml.jackson.annotation.PropertyAccessor r3 = com.fasterxml.jackson.annotation.PropertyAccessor.FIELD     // Catch:{ NoSuchFieldError -> 0x001d }
                int r3 = r3.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2[r3] = r0     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                r2 = 3
                int[] r3 = $SwitchMap$com$fasterxml$jackson$annotation$PropertyAccessor     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.fasterxml.jackson.annotation.PropertyAccessor r4 = com.fasterxml.jackson.annotation.PropertyAccessor.GETTER     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r4 = r4.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r3[r4] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                r3 = 4
                int[] r4 = $SwitchMap$com$fasterxml$jackson$annotation$PropertyAccessor     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.fasterxml.jackson.annotation.PropertyAccessor r5 = com.fasterxml.jackson.annotation.PropertyAccessor.IS_GETTER     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r4[r5] = r3     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                r4 = 5
                int[] r5 = $SwitchMap$com$fasterxml$jackson$annotation$PropertyAccessor     // Catch:{ NoSuchFieldError -> 0x003e }
                com.fasterxml.jackson.annotation.PropertyAccessor r6 = com.fasterxml.jackson.annotation.PropertyAccessor.NONE     // Catch:{ NoSuchFieldError -> 0x003e }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r5[r6] = r4     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r5 = $SwitchMap$com$fasterxml$jackson$annotation$PropertyAccessor     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.fasterxml.jackson.annotation.PropertyAccessor r6 = com.fasterxml.jackson.annotation.PropertyAccessor.SETTER     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r7 = 6
                r5[r6] = r7     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                int[] r5 = $SwitchMap$com$fasterxml$jackson$annotation$PropertyAccessor     // Catch:{ NoSuchFieldError -> 0x0054 }
                com.fasterxml.jackson.annotation.PropertyAccessor r6 = com.fasterxml.jackson.annotation.PropertyAccessor.ALL     // Catch:{ NoSuchFieldError -> 0x0054 }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x0054 }
                r7 = 7
                r5[r6] = r7     // Catch:{ NoSuchFieldError -> 0x0054 }
            L_0x0054:
                com.fasterxml.jackson.annotation.JsonAutoDetect$Visibility[] r5 = com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.values()
                int r5 = r5.length
                int[] r5 = new int[r5]
                $SwitchMap$com$fasterxml$jackson$annotation$JsonAutoDetect$Visibility = r5
                com.fasterxml.jackson.annotation.JsonAutoDetect$Visibility r6 = com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.ANY     // Catch:{ NoSuchFieldError -> 0x0065 }
                int r6 = r6.ordinal()     // Catch:{ NoSuchFieldError -> 0x0065 }
                r5[r6] = r1     // Catch:{ NoSuchFieldError -> 0x0065 }
            L_0x0065:
                int[] r1 = $SwitchMap$com$fasterxml$jackson$annotation$JsonAutoDetect$Visibility     // Catch:{ NoSuchFieldError -> 0x006f }
                com.fasterxml.jackson.annotation.JsonAutoDetect$Visibility r5 = com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NONE     // Catch:{ NoSuchFieldError -> 0x006f }
                int r5 = r5.ordinal()     // Catch:{ NoSuchFieldError -> 0x006f }
                r1[r5] = r0     // Catch:{ NoSuchFieldError -> 0x006f }
            L_0x006f:
                int[] r0 = $SwitchMap$com$fasterxml$jackson$annotation$JsonAutoDetect$Visibility     // Catch:{ NoSuchFieldError -> 0x0079 }
                com.fasterxml.jackson.annotation.JsonAutoDetect$Visibility r1 = com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.NON_PRIVATE     // Catch:{ NoSuchFieldError -> 0x0079 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0079 }
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0079 }
            L_0x0079:
                int[] r0 = $SwitchMap$com$fasterxml$jackson$annotation$JsonAutoDetect$Visibility     // Catch:{ NoSuchFieldError -> 0x0083 }
                com.fasterxml.jackson.annotation.JsonAutoDetect$Visibility r1 = com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.PROTECTED_AND_PUBLIC     // Catch:{ NoSuchFieldError -> 0x0083 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0083 }
                r0[r1] = r3     // Catch:{ NoSuchFieldError -> 0x0083 }
            L_0x0083:
                int[] r0 = $SwitchMap$com$fasterxml$jackson$annotation$JsonAutoDetect$Visibility     // Catch:{ NoSuchFieldError -> 0x008d }
                com.fasterxml.jackson.annotation.JsonAutoDetect$Visibility r1 = com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.PUBLIC_ONLY     // Catch:{ NoSuchFieldError -> 0x008d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x008d }
                r0[r1] = r4     // Catch:{ NoSuchFieldError -> 0x008d }
            L_0x008d:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.fasterxml.jackson.annotation.JsonAutoDetect.AnonymousClass1.<clinit>():void");
        }
    }

    public enum Visibility {
        ANY,
        NON_PRIVATE,
        PROTECTED_AND_PUBLIC,
        PUBLIC_ONLY,
        NONE,
        DEFAULT;

        public boolean isVisible(Member member) {
            int i3 = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$annotation$JsonAutoDetect$Visibility[ordinal()];
            if (i3 == 1) {
                return true;
            }
            if (i3 == 3) {
                return !Modifier.isPrivate(member.getModifiers());
            }
            if (i3 != 4) {
                if (i3 != 5) {
                    return false;
                }
            } else if (Modifier.isProtected(member.getModifiers())) {
                return true;
            }
            return Modifier.isPublic(member.getModifiers());
        }
    }

    Visibility creatorVisibility() default Visibility.DEFAULT;

    Visibility fieldVisibility() default Visibility.DEFAULT;

    Visibility getterVisibility() default Visibility.DEFAULT;

    Visibility isGetterVisibility() default Visibility.DEFAULT;

    Visibility setterVisibility() default Visibility.DEFAULT;

    public static class Value implements JacksonAnnotationValue<JsonAutoDetect>, Serializable {
        protected static final Value DEFAULT;
        private static final Visibility DEFAULT_FIELD_VISIBILITY;
        protected static final Value NO_OVERRIDES;
        private static final long serialVersionUID = 1;
        protected final Visibility _creatorVisibility;
        protected final Visibility _fieldVisibility;
        protected final Visibility _getterVisibility;
        protected final Visibility _isGetterVisibility;
        protected final Visibility _setterVisibility;

        static {
            Visibility visibility = Visibility.PUBLIC_ONLY;
            DEFAULT_FIELD_VISIBILITY = visibility;
            DEFAULT = new Value(visibility, visibility, visibility, Visibility.ANY, visibility);
            Visibility visibility2 = Visibility.DEFAULT;
            NO_OVERRIDES = new Value(visibility2, visibility2, visibility2, visibility2, visibility2);
        }

        private Value(Visibility visibility, Visibility visibility2, Visibility visibility3, Visibility visibility4, Visibility visibility5) {
            this._fieldVisibility = visibility;
            this._getterVisibility = visibility2;
            this._isGetterVisibility = visibility3;
            this._setterVisibility = visibility4;
            this._creatorVisibility = visibility5;
        }

        private static boolean _equals(Value value, Value value2) {
            return value._fieldVisibility == value2._fieldVisibility && value._getterVisibility == value2._getterVisibility && value._isGetterVisibility == value2._isGetterVisibility && value._setterVisibility == value2._setterVisibility && value._creatorVisibility == value2._creatorVisibility;
        }

        private static Value _predefined(Visibility visibility, Visibility visibility2, Visibility visibility3, Visibility visibility4, Visibility visibility5) {
            if (visibility == DEFAULT_FIELD_VISIBILITY) {
                Value value = DEFAULT;
                if (visibility2 == value._getterVisibility && visibility3 == value._isGetterVisibility && visibility4 == value._setterVisibility && visibility5 == value._creatorVisibility) {
                    return value;
                }
                return null;
            }
            Visibility visibility6 = Visibility.DEFAULT;
            if (visibility == visibility6 && visibility2 == visibility6 && visibility3 == visibility6 && visibility4 == visibility6 && visibility5 == visibility6) {
                return NO_OVERRIDES;
            }
            return null;
        }

        public static Value construct(PropertyAccessor propertyAccessor, Visibility visibility) {
            Visibility visibility2;
            Visibility visibility3;
            Visibility visibility4;
            Visibility visibility5 = Visibility.DEFAULT;
            int i3 = AnonymousClass1.$SwitchMap$com$fasterxml$jackson$annotation$PropertyAccessor[propertyAccessor.ordinal()];
            if (i3 != 1) {
                if (i3 == 2) {
                    visibility2 = visibility5;
                    visibility4 = visibility2;
                } else if (i3 != 3) {
                    if (i3 == 4) {
                        visibility2 = visibility;
                        visibility = visibility5;
                    } else if (i3 == 6) {
                        visibility4 = visibility;
                        visibility2 = visibility5;
                        visibility = visibility2;
                        visibility3 = visibility;
                    } else if (i3 != 7) {
                        visibility2 = visibility5;
                        visibility = visibility2;
                    } else {
                        visibility2 = visibility;
                        visibility5 = visibility2;
                        visibility4 = visibility5;
                    }
                    visibility4 = visibility;
                } else {
                    visibility2 = visibility5;
                    visibility4 = visibility2;
                    visibility3 = visibility4;
                    visibility5 = visibility;
                    visibility = visibility3;
                }
                visibility3 = visibility4;
            } else {
                visibility3 = visibility;
                visibility2 = visibility5;
                visibility = visibility2;
                visibility4 = visibility;
            }
            return construct(visibility, visibility5, visibility2, visibility4, visibility3);
        }

        public static Value defaultVisibility() {
            return DEFAULT;
        }

        public static Value from(JsonAutoDetect jsonAutoDetect) {
            return construct(jsonAutoDetect.fieldVisibility(), jsonAutoDetect.getterVisibility(), jsonAutoDetect.isGetterVisibility(), jsonAutoDetect.setterVisibility(), jsonAutoDetect.creatorVisibility());
        }

        public static Value merge(Value value, Value value2) {
            return value == null ? value2 : value.withOverrides(value2);
        }

        public static Value noOverrides() {
            return NO_OVERRIDES;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj == null) {
                return false;
            }
            return obj.getClass() == getClass() && _equals(this, (Value) obj);
        }

        public Visibility getCreatorVisibility() {
            return this._creatorVisibility;
        }

        public Visibility getFieldVisibility() {
            return this._fieldVisibility;
        }

        public Visibility getGetterVisibility() {
            return this._getterVisibility;
        }

        public Visibility getIsGetterVisibility() {
            return this._isGetterVisibility;
        }

        public Visibility getSetterVisibility() {
            return this._setterVisibility;
        }

        public int hashCode() {
            int ordinal = (this._getterVisibility.ordinal() * 3) - (this._isGetterVisibility.ordinal() * 7);
            return (this._creatorVisibility.ordinal() * 13) ^ ((this._fieldVisibility.ordinal() + 1) ^ ((this._setterVisibility.ordinal() * 11) + ordinal));
        }

        public Object readResolve() {
            Value _predefined = _predefined(this._fieldVisibility, this._getterVisibility, this._isGetterVisibility, this._setterVisibility, this._creatorVisibility);
            return _predefined == null ? this : _predefined;
        }

        public String toString() {
            Visibility visibility = this._fieldVisibility;
            Visibility visibility2 = this._getterVisibility;
            Visibility visibility3 = this._isGetterVisibility;
            Visibility visibility4 = this._setterVisibility;
            Visibility visibility5 = this._creatorVisibility;
            return "JsonAutoDetect.Value(fields=" + visibility + ",getters=" + visibility2 + ",isGetters=" + visibility3 + ",setters=" + visibility4 + ",creators=" + visibility5 + ")";
        }

        public Class<JsonAutoDetect> valueFor() {
            return JsonAutoDetect.class;
        }

        public Value withCreatorVisibility(Visibility visibility) {
            return construct(this._fieldVisibility, this._getterVisibility, this._isGetterVisibility, this._setterVisibility, visibility);
        }

        public Value withFieldVisibility(Visibility visibility) {
            return construct(visibility, this._getterVisibility, this._isGetterVisibility, this._setterVisibility, this._creatorVisibility);
        }

        public Value withGetterVisibility(Visibility visibility) {
            return construct(this._fieldVisibility, visibility, this._isGetterVisibility, this._setterVisibility, this._creatorVisibility);
        }

        public Value withIsGetterVisibility(Visibility visibility) {
            return construct(this._fieldVisibility, this._getterVisibility, visibility, this._setterVisibility, this._creatorVisibility);
        }

        public Value withOverrides(Value value) {
            if (value == null || value == NO_OVERRIDES || value == this || _equals(this, value)) {
                return this;
            }
            Visibility visibility = value._fieldVisibility;
            Visibility visibility2 = Visibility.DEFAULT;
            if (visibility == visibility2) {
                visibility = this._fieldVisibility;
            }
            Visibility visibility3 = value._getterVisibility;
            if (visibility3 == visibility2) {
                visibility3 = this._getterVisibility;
            }
            Visibility visibility4 = value._isGetterVisibility;
            if (visibility4 == visibility2) {
                visibility4 = this._isGetterVisibility;
            }
            Visibility visibility5 = value._setterVisibility;
            if (visibility5 == visibility2) {
                visibility5 = this._setterVisibility;
            }
            Visibility visibility6 = value._creatorVisibility;
            if (visibility6 == visibility2) {
                visibility6 = this._creatorVisibility;
            }
            return construct(visibility, visibility3, visibility4, visibility5, visibility6);
        }

        public Value withSetterVisibility(Visibility visibility) {
            return construct(this._fieldVisibility, this._getterVisibility, this._isGetterVisibility, visibility, this._creatorVisibility);
        }

        public static Value construct(Visibility visibility, Visibility visibility2, Visibility visibility3, Visibility visibility4, Visibility visibility5) {
            Value _predefined = _predefined(visibility, visibility2, visibility3, visibility4, visibility5);
            return _predefined == null ? new Value(visibility, visibility2, visibility3, visibility4, visibility5) : _predefined;
        }
    }
}
