package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.databind.introspect.AnnotatedAndMetadata;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Predicate;
import org.web3j.abi.EventValues;
import org.web3j.abi.TypeDecoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.Utils;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.StaticStruct;
import org.web3j.abi.datatypes.Type;
import org.web3j.protocol.core.methods.response.AbiDefinition;
import org.web3j.tx.Contract;

public final /* synthetic */ class a implements Predicate {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f6563a;

    public /* synthetic */ a(int i3) {
        this.f6563a = i3;
    }

    public final boolean test(Object obj) {
        switch (this.f6563a) {
            case 0:
                return StdKeyDeserializers.lambda$findStringBasedKeyDeserializer$0((AnnotatedAndMetadata) obj);
            case 1:
                return Objects.nonNull((EventValues) obj);
            case 2:
                return TypeDecoder.isDynamic((Class) obj);
            case 3:
                return Modifier.isPublic(((Field) obj).getModifiers());
            case 4:
                return Type.class.isAssignableFrom((Class) obj);
            case 5:
                return Arrays.stream(((Constructor) obj).getParameterTypes()).allMatch(new a(4));
            case 6:
                return Utils.lambda$staticStructsNestedFieldsFlatList$4((Field) obj);
            case 7:
                return StaticStruct.class.isAssignableFrom(((Field) obj).getType());
            case 8:
                return ((TypeReference) obj).isIndexed();
            case 9:
                return Event.lambda$getNonIndexedParameters$0((TypeReference) obj);
            case 10:
                return Objects.nonNull((Contract.EventValuesWithLog) obj);
            default:
                return ((AbiDefinition.NamedType) obj).isDynamic();
        }
    }
}
