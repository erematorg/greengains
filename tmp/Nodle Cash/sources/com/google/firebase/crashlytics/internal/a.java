package com.google.firebase.crashlytics.internal;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.google.android.datatransport.Transformer;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.SuccessContinuation;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.crashlytics.internal.model.CrashlyticsReport;
import com.google.firebase.crashlytics.internal.send.DataTransportCrashlyticsReportSender;
import com.google.firebase.messaging.FcmBroadcastProcessor;
import com.google.firebase.messaging.reporting.MessagingClientEventExtension;
import com.google.gson.internal.ConstructorConstructor;
import com.google.gson.internal.ObjectConstructor;
import io.nodle.cash.data.remoteconfig.FetchRemoteConfigUseCase;
import io.nodle.cash.domain.chat.PushNotificationTokenManager;
import java.math.BigInteger;
import java.nio.charset.Charset;
import java.util.HashMap;
import jnr.constants.platform.Errno;
import jnr.constants.platform.windows.LastError;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty1;
import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1OctetString;
import org.bouncycastle.asn1.ASN1Sequence;
import org.bouncycastle.asn1.DERSequence;
import org.koin.core.definition.BeanDefinition;
import org.koin.core.instance.FactoryInstanceFactory;
import org.koin.core.module.Module;

public final /* synthetic */ class a implements Continuation, Transformer, SuccessContinuation, ObjectConstructor, OnCompleteListener {

    /* renamed from: a  reason: collision with root package name */
    public final /* synthetic */ int f7088a;

    public /* synthetic */ a(int i3) {
        this.f7088a = i3;
    }

    public static int a(int i3, int i4) {
        return UInt.m9074constructorimpl(UInt.m9074constructorimpl(i3) + i4);
    }

    public static int b(BigInteger bigInteger, long j2) {
        return bigInteger.mod(BigInteger.valueOf(j2)).intValue();
    }

    public static int c(UInt uInt, int i3) {
        return UInt.m9074constructorimpl(uInt.m9126unboximpl() + i3);
    }

    public static long d(long j2, long j3) {
        return ULong.m9153constructorimpl(ULong.m9153constructorimpl(j2) + j3);
    }

    public static String e(String str, StringBuilder sb) {
        return str + sb;
    }

    public static KProperty1 f(Class cls, String str, String str2, int i3) {
        return Reflection.property1(new PropertyReference1Impl(cls, str, str2, i3));
    }

    public static ASN1EncodableVector g(ASN1EncodableVector aSN1EncodableVector, ASN1EncodableVector aSN1EncodableVector2) {
        aSN1EncodableVector2.add(new DERSequence(aSN1EncodableVector));
        return new ASN1EncodableVector();
    }

    public static FactoryInstanceFactory h(BeanDefinition beanDefinition, Module module) {
        FactoryInstanceFactory factoryInstanceFactory = new FactoryInstanceFactory(beanDefinition);
        module.indexPrimaryType(factoryInstanceFactory);
        return factoryInstanceFactory;
    }

    public static void i(LastError lastError, HashMap hashMap, Errno errno) {
        hashMap.put(Integer.valueOf(lastError.value()), errno);
    }

    public static byte[] j(ASN1Sequence aSN1Sequence, int i3) {
        return ASN1OctetString.getInstance(aSN1Sequence.getObjectAt(i3)).getOctets();
    }

    public Object apply(Object obj) {
        switch (this.f7088a) {
            case 3:
                return DataTransportCrashlyticsReportSender.TRANSFORM.reportToJson((CrashlyticsReport) obj).getBytes(Charset.forName("UTF-8"));
            default:
                return ((MessagingClientEventExtension) obj).toByteArray();
        }
    }

    public Object construct() {
        switch (this.f7088a) {
            case 10:
                return ConstructorConstructor.lambda$newMapConstructor$14();
            case 11:
                return ConstructorConstructor.lambda$newMapConstructor$15();
            case 12:
                return ConstructorConstructor.lambda$newMapConstructor$16();
            case 13:
                return ConstructorConstructor.lambda$newMapConstructor$17();
            case 14:
                return ConstructorConstructor.lambda$newMapConstructor$18();
            case 15:
                return ConstructorConstructor.lambda$newCollectionConstructor$10();
            case 16:
                return ConstructorConstructor.lambda$newCollectionConstructor$11();
            case 17:
                return ConstructorConstructor.lambda$newCollectionConstructor$12();
            default:
                return ConstructorConstructor.lambda$newCollectionConstructor$13();
        }
    }

    public void onComplete(Task task) {
        switch (this.f7088a) {
            case 25:
                FetchRemoteConfigUseCase.invoke$lambda$0(task);
                return;
            default:
                PushNotificationTokenManager.ensurePushTokenIsConfigured$lambda$1(task);
                return;
        }
    }

    public Task then(Object obj) {
        switch (this.f7088a) {
            case 7:
                return Tasks.forResult(null);
            case 8:
                return Tasks.forResult(null);
            default:
                return Tasks.forResult(null);
        }
    }

    public Object then(Task task) {
        switch (this.f7088a) {
            case 0:
                return Tasks.forResult(null);
            case 1:
                return Tasks.forResult(null);
            case 4:
                return Integer.valueOf(TypedValues.CycleType.TYPE_ALPHA);
            default:
                return FcmBroadcastProcessor.lambda$bindToMessagingService$3(task);
        }
    }
}
