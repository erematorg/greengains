package com.reown.android.internal.common.storage.pairing;

import com.reown.android.internal.common.model.Pairing;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function12;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
public final /* synthetic */ class PairingStorageRepository$getListOfPairings$2 extends FunctionReferenceImpl implements Function12<String, Long, String, String, String, String, Boolean, String, String, String, List<? extends String>, String, Pairing> {
    public PairingStorageRepository$getListOfPairings$2(Object obj) {
        super(12, obj, PairingStorageRepository.class, "toPairing", "toPairing(Ljava/lang/String;JLjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Ljava/lang/String;)Lcom/reown/android/internal/common/model/Pairing;", 0);
    }

    public final Pairing invoke(String str, long j2, String str2, String str3, String str4, String str5, Boolean bool, String str6, String str7, String str8, List<String> list, String str9) {
        String str10 = str;
        Intrinsics.checkNotNullParameter(str10, "p0");
        String str11 = str2;
        Intrinsics.checkNotNullParameter(str11, "p2");
        String str12 = str4;
        Intrinsics.checkNotNullParameter(str12, "p4");
        String str13 = str5;
        Intrinsics.checkNotNullParameter(str13, "p5");
        return ((PairingStorageRepository) this.receiver).toPairing(str10, j2, str11, str3, str12, str13, bool, str6, str7, str8, list, str9);
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3, Object obj4, Object obj5, Object obj6, Object obj7, Object obj8, Object obj9, Object obj10, Object obj11, Object obj12) {
        return invoke((String) obj, ((Number) obj2).longValue(), (String) obj3, (String) obj4, (String) obj5, (String) obj6, (Boolean) obj7, (String) obj8, (String) obj9, (String) obj10, (List<String>) (List) obj11, (String) obj12);
    }
}
