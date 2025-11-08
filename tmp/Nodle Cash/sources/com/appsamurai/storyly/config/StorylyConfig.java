package com.appsamurai.storyly.config;

import androidx.annotation.Keep;
import androidx.camera.core.impl.i;
import androidx.constraintlayout.core.state.b;
import com.appsamurai.storyly.StorylyLayoutDirection;
import com.appsamurai.storyly.config.StorylyProductConfig;
import com.appsamurai.storyly.config.StorylyShareConfig;
import com.appsamurai.storyly.config.styling.bar.StorylyBarStyling;
import com.appsamurai.storyly.config.styling.group.StorylyStoryGroupStyling;
import com.appsamurai.storyly.config.styling.moments.StorylyMomentsStyling;
import com.appsamurai.storyly.config.styling.story.StorylyStoryStyling;
import com.appsamurai.storyly.data.m0;
import com.appsamurai.storyly.data.managers.processing.e;
import com.google.firebase.analytics.FirebaseAnalytics;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b)\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0018\b\b\u0018\u00002\u00020\u0001:\u0002\u0001B\u0001\b\u0000\u0012\u0006\u0010+\u001a\u00020\b\u0012\u0006\u0010,\u001a\u00020\f\u0012\u0006\u0010-\u001a\u00020\u0010\u0012\u0006\u0010.\u001a\u00020\u0014\u0012\u0006\u0010/\u001a\u00020\u0018\u0012\u0006\u00100\u001a\u00020\u001c\u0012\u0006\u00101\u001a\u00020 \u0012\b\u00102\u001a\u0004\u0018\u00010\u0003\u0012\u000e\u00103\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002\u0012\u0012\u00104\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0005\u0012\b\u00105\u001a\u0004\u0018\u00010\u0003\u0012\u0006\u00106\u001a\u00020'¢\u0006\u0006\b\u0001\u0010\u0001J\u0011\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002HÂ\u0003J\u0015\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0005HÂ\u0003J\u000b\u0010\u0007\u001a\u0004\u0018\u00010\u0003HÂ\u0003J\u0010\u0010\u000b\u001a\u00020\bHÀ\u0003¢\u0006\u0004\b\t\u0010\nJ\u0010\u0010\u000f\u001a\u00020\fHÀ\u0003¢\u0006\u0004\b\r\u0010\u000eJ\u0010\u0010\u0013\u001a\u00020\u0010HÀ\u0003¢\u0006\u0004\b\u0011\u0010\u0012J\u0010\u0010\u0017\u001a\u00020\u0014HÀ\u0003¢\u0006\u0004\b\u0015\u0010\u0016J\u0010\u0010\u001b\u001a\u00020\u0018HÀ\u0003¢\u0006\u0004\b\u0019\u0010\u001aJ\u0010\u0010\u001f\u001a\u00020\u001cHÀ\u0003¢\u0006\u0004\b\u001d\u0010\u001eJ\u0010\u0010#\u001a\u00020 HÀ\u0003¢\u0006\u0004\b!\u0010\"J\u0012\u0010&\u001a\u0004\u0018\u00010\u0003HÀ\u0003¢\u0006\u0004\b$\u0010%J\u0010\u0010*\u001a\u00020'HÀ\u0003¢\u0006\u0004\b(\u0010)J\u0001\u00107\u001a\u00020\u00002\b\b\u0002\u0010+\u001a\u00020\b2\b\b\u0002\u0010,\u001a\u00020\f2\b\b\u0002\u0010-\u001a\u00020\u00102\b\b\u0002\u0010.\u001a\u00020\u00142\b\b\u0002\u0010/\u001a\u00020\u00182\b\b\u0002\u00100\u001a\u00020\u001c2\b\b\u0002\u00101\u001a\u00020 2\n\b\u0002\u00102\u001a\u0004\u0018\u00010\u00032\u0010\b\u0002\u00103\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00022\u0014\b\u0002\u00104\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00052\n\b\u0002\u00105\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u00106\u001a\u00020'HÆ\u0001J\t\u00108\u001a\u00020\u0003HÖ\u0001J\t\u0010:\u001a\u000209HÖ\u0001J\u0013\u0010<\u001a\u00020'2\b\u0010;\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\"\u0010+\u001a\u00020\b8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b+\u0010=\u001a\u0004\b>\u0010\n\"\u0004\b?\u0010@R\"\u0010,\u001a\u00020\f8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b,\u0010A\u001a\u0004\bB\u0010\u000e\"\u0004\bC\u0010DR\"\u0010-\u001a\u00020\u00108\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b-\u0010E\u001a\u0004\bF\u0010\u0012\"\u0004\bG\u0010HR\"\u0010.\u001a\u00020\u00148\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b.\u0010I\u001a\u0004\bJ\u0010\u0016\"\u0004\bK\u0010LR\"\u0010/\u001a\u00020\u00188\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b/\u0010M\u001a\u0004\bN\u0010\u001a\"\u0004\bO\u0010PR\"\u00100\u001a\u00020\u001c8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b0\u0010Q\u001a\u0004\bR\u0010\u001e\"\u0004\bS\u0010TR\"\u00101\u001a\u00020 8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b1\u0010U\u001a\u0004\bV\u0010\"\"\u0004\bW\u0010XR$\u00102\u001a\u0004\u0018\u00010\u00038\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b2\u0010Y\u001a\u0004\bZ\u0010%\"\u0004\b[\u0010\\R\u001e\u00103\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00028\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b3\u0010]R\"\u00104\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00058\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b4\u0010^R\u0018\u00105\u001a\u0004\u0018\u00010\u00038\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b5\u0010YR\"\u00106\u001a\u00020'8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b6\u0010_\u001a\u0004\b`\u0010)\"\u0004\ba\u0010bR$\u0010d\u001a\u0004\u0018\u00010c8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\bd\u0010e\u001a\u0004\bf\u0010g\"\u0004\bh\u0010iR0\u0010m\u001a\u0010\u0012\u0004\u0012\u00020k\u0012\u0004\u0012\u00020l\u0018\u00010j8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\bm\u0010n\u001a\u0004\bo\u0010p\"\u0004\bq\u0010rR4\u0010x\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00022\u000e\u0010s\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u00028F@FX\u000e¢\u0006\f\u001a\u0004\bt\u0010u\"\u0004\bv\u0010wR<\u0010}\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00052\u0012\u0010s\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u00058F@FX\u000e¢\u0006\f\u001a\u0004\by\u0010z\"\u0004\b{\u0010|R)\u0010\u0001\u001a\u0004\u0018\u00010\u00032\b\u0010s\u001a\u0004\u0018\u00010\u00038F@FX\u000e¢\u0006\f\u001a\u0004\b~\u0010%\"\u0004\b\u0010\\¨\u0006\u0001"}, d2 = {"Lcom/appsamurai/storyly/config/StorylyConfig;", "", "", "", "component9", "", "component10", "component11", "Lcom/appsamurai/storyly/config/styling/bar/StorylyBarStyling;", "component1$storyly_release", "()Lcom/appsamurai/storyly/config/styling/bar/StorylyBarStyling;", "component1", "Lcom/appsamurai/storyly/config/styling/group/StorylyStoryGroupStyling;", "component2$storyly_release", "()Lcom/appsamurai/storyly/config/styling/group/StorylyStoryGroupStyling;", "component2", "Lcom/appsamurai/storyly/config/styling/story/StorylyStoryStyling;", "component3$storyly_release", "()Lcom/appsamurai/storyly/config/styling/story/StorylyStoryStyling;", "component3", "Lcom/appsamurai/storyly/config/styling/moments/StorylyMomentsStyling;", "component4$storyly_release", "()Lcom/appsamurai/storyly/config/styling/moments/StorylyMomentsStyling;", "component4", "Lcom/appsamurai/storyly/config/StorylyProductConfig;", "component5$storyly_release", "()Lcom/appsamurai/storyly/config/StorylyProductConfig;", "component5", "Lcom/appsamurai/storyly/config/StorylyShareConfig;", "component6$storyly_release", "()Lcom/appsamurai/storyly/config/StorylyShareConfig;", "component6", "Lcom/appsamurai/storyly/StorylyLayoutDirection;", "component7$storyly_release", "()Lcom/appsamurai/storyly/StorylyLayoutDirection;", "component7", "component8$storyly_release", "()Ljava/lang/String;", "component8", "", "component12$storyly_release", "()Z", "component12", "bar", "group", "story", "moments", "product", "share", "layoutDirection", "customParameter", "_labels", "_userData", "_storylyPayload", "isTestMode", "copy", "toString", "", "hashCode", "other", "equals", "Lcom/appsamurai/storyly/config/styling/bar/StorylyBarStyling;", "getBar$storyly_release", "setBar$storyly_release", "(Lcom/appsamurai/storyly/config/styling/bar/StorylyBarStyling;)V", "Lcom/appsamurai/storyly/config/styling/group/StorylyStoryGroupStyling;", "getGroup$storyly_release", "setGroup$storyly_release", "(Lcom/appsamurai/storyly/config/styling/group/StorylyStoryGroupStyling;)V", "Lcom/appsamurai/storyly/config/styling/story/StorylyStoryStyling;", "getStory$storyly_release", "setStory$storyly_release", "(Lcom/appsamurai/storyly/config/styling/story/StorylyStoryStyling;)V", "Lcom/appsamurai/storyly/config/styling/moments/StorylyMomentsStyling;", "getMoments$storyly_release", "setMoments$storyly_release", "(Lcom/appsamurai/storyly/config/styling/moments/StorylyMomentsStyling;)V", "Lcom/appsamurai/storyly/config/StorylyProductConfig;", "getProduct$storyly_release", "setProduct$storyly_release", "(Lcom/appsamurai/storyly/config/StorylyProductConfig;)V", "Lcom/appsamurai/storyly/config/StorylyShareConfig;", "getShare$storyly_release", "setShare$storyly_release", "(Lcom/appsamurai/storyly/config/StorylyShareConfig;)V", "Lcom/appsamurai/storyly/StorylyLayoutDirection;", "getLayoutDirection$storyly_release", "setLayoutDirection$storyly_release", "(Lcom/appsamurai/storyly/StorylyLayoutDirection;)V", "Ljava/lang/String;", "getCustomParameter$storyly_release", "setCustomParameter$storyly_release", "(Ljava/lang/String;)V", "Ljava/util/Set;", "Ljava/util/Map;", "Z", "isTestMode$storyly_release", "setTestMode$storyly_release", "(Z)V", "Lcom/appsamurai/storyly/data/m0;", "storylyStyle", "Lcom/appsamurai/storyly/data/m0;", "getStorylyStyle$storyly_release", "()Lcom/appsamurai/storyly/data/m0;", "setStorylyStyle$storyly_release", "(Lcom/appsamurai/storyly/data/m0;)V", "Lkotlin/Function1;", "Lcom/appsamurai/storyly/data/managers/processing/e;", "", "onDataUpdate", "Lkotlin/jvm/functions/Function1;", "getOnDataUpdate$storyly_release", "()Lkotlin/jvm/functions/Function1;", "setOnDataUpdate$storyly_release", "(Lkotlin/jvm/functions/Function1;)V", "value", "getLabels", "()Ljava/util/Set;", "setLabels", "(Ljava/util/Set;)V", "labels", "getUserData", "()Ljava/util/Map;", "setUserData", "(Ljava/util/Map;)V", "userData", "getStorylyPayload", "setStorylyPayload", "storylyPayload", "<init>", "(Lcom/appsamurai/storyly/config/styling/bar/StorylyBarStyling;Lcom/appsamurai/storyly/config/styling/group/StorylyStoryGroupStyling;Lcom/appsamurai/storyly/config/styling/story/StorylyStoryStyling;Lcom/appsamurai/storyly/config/styling/moments/StorylyMomentsStyling;Lcom/appsamurai/storyly/config/StorylyProductConfig;Lcom/appsamurai/storyly/config/StorylyShareConfig;Lcom/appsamurai/storyly/StorylyLayoutDirection;Ljava/lang/String;Ljava/util/Set;Ljava/util/Map;Ljava/lang/String;Z)V", "Builder", "storyly_release"}, k = 1, mv = {1, 5, 1})
@Keep
public final class StorylyConfig {
    @Nullable
    private Set<String> _labels;
    @Nullable
    private String _storylyPayload;
    @NotNull
    private Map<String, String> _userData;
    @NotNull
    private StorylyBarStyling bar;
    @Nullable
    private String customParameter;
    @NotNull
    private StorylyStoryGroupStyling group;
    private boolean isTestMode;
    @NotNull
    private StorylyLayoutDirection layoutDirection;
    @NotNull
    private StorylyMomentsStyling moments;
    @Nullable
    private Function1<? super e, Unit> onDataUpdate;
    @NotNull
    private StorylyProductConfig product;
    @NotNull
    private StorylyShareConfig share;
    @NotNull
    private StorylyStoryStyling story;
    @Nullable
    private m0 storylyStyle;

    @Keep
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u001a\b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b9\u0010:J\u000e\u0010\u0004\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u0002J\u0010\u0010\u0007\u001a\u00020\u00002\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005J\u0016\u0010\n\u001a\u00020\u00002\u000e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\bJ\u001a\u0010\r\u001a\u00020\u00002\u0012\u0010\f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u000bJ\u0010\u0010\u000f\u001a\u00020\u00002\b\u0010\u000e\u001a\u0004\u0018\u00010\u0005J\u000e\u0010\u0012\u001a\u00020\u00002\u0006\u0010\u0011\u001a\u00020\u0010J\u000e\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u0013J\u000e\u0010\u0018\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0016J\u000e\u0010\u001a\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u0019J\u000e\u0010\u001c\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u001bJ\u000e\u0010\u001e\u001a\u00020\u00002\u0006\u0010\u0017\u001a\u00020\u001dJ\u000e\u0010 \u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u001fJ\u0006\u0010\"\u001a\u00020!R\u0016\u0010#\u001a\u00020\u00168\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b#\u0010$R\u0016\u0010%\u001a\u00020\u001d8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b%\u0010&R\u0016\u0010'\u001a\u00020\u00198\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b'\u0010(R\u0016\u0010)\u001a\u00020\u001b8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b)\u0010*R\u0016\u0010+\u001a\u00020\u00028\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b+\u0010,R\u0018\u0010-\u001a\u0004\u0018\u00010\u00058\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b-\u0010.R\u0016\u0010/\u001a\u00020\u00108\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b/\u00100R\u001e\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\b8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\t\u00101R\"\u00102\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00050\u000b8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b2\u00103R\u0018\u00104\u001a\u0004\u0018\u00010\u00058\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b4\u0010.R\u0016\u00105\u001a\u00020\u00138\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b5\u00106R\u0016\u00107\u001a\u00020\u001f8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b7\u00108¨\u0006;"}, d2 = {"Lcom/appsamurai/storyly/config/StorylyConfig$Builder;", "", "Lcom/appsamurai/storyly/StorylyLayoutDirection;", "direction", "setLayoutDirection", "", "parameter", "setCustomParameter", "", "labels", "setLabels", "", "data", "setUserData", "payload", "setStorylyPayload", "", "isTest", "setTestMode", "Lcom/appsamurai/storyly/config/StorylyProductConfig;", "config", "setProductConfig", "Lcom/appsamurai/storyly/config/styling/bar/StorylyBarStyling;", "styling", "setBarStyling", "Lcom/appsamurai/storyly/config/styling/story/StorylyStoryStyling;", "setStoryStyling", "Lcom/appsamurai/storyly/config/styling/moments/StorylyMomentsStyling;", "setMomentsStyling", "Lcom/appsamurai/storyly/config/styling/group/StorylyStoryGroupStyling;", "setStoryGroupStyling", "Lcom/appsamurai/storyly/config/StorylyShareConfig;", "setShareConfig", "Lcom/appsamurai/storyly/config/StorylyConfig;", "build", "barStyling", "Lcom/appsamurai/storyly/config/styling/bar/StorylyBarStyling;", "storyGroupStyling", "Lcom/appsamurai/storyly/config/styling/group/StorylyStoryGroupStyling;", "storyStyling", "Lcom/appsamurai/storyly/config/styling/story/StorylyStoryStyling;", "momentsStyling", "Lcom/appsamurai/storyly/config/styling/moments/StorylyMomentsStyling;", "layoutDirection", "Lcom/appsamurai/storyly/StorylyLayoutDirection;", "customParameter", "Ljava/lang/String;", "isTestMode", "Z", "Ljava/util/Set;", "userData", "Ljava/util/Map;", "storylyPayload", "productConfig", "Lcom/appsamurai/storyly/config/StorylyProductConfig;", "shareConfig", "Lcom/appsamurai/storyly/config/StorylyShareConfig;", "<init>", "()V", "storyly_release"}, k = 1, mv = {1, 5, 1})
    public static final class Builder {
        @NotNull
        private StorylyBarStyling barStyling = new StorylyBarStyling.Builder().build();
        @Nullable
        private String customParameter;
        private boolean isTestMode;
        @Nullable
        private Set<String> labels;
        @NotNull
        private StorylyLayoutDirection layoutDirection = StorylyLayoutDirection.LTR;
        @NotNull
        private StorylyMomentsStyling momentsStyling = new StorylyMomentsStyling.Builder().build();
        @NotNull
        private StorylyProductConfig productConfig = new StorylyProductConfig.Builder().build();
        @NotNull
        private StorylyShareConfig shareConfig = new StorylyShareConfig.Builder().build();
        @NotNull
        private StorylyStoryGroupStyling storyGroupStyling = new StorylyStoryGroupStyling.Builder().build();
        @NotNull
        private StorylyStoryStyling storyStyling = new StorylyStoryStyling.Builder().build();
        @Nullable
        private String storylyPayload;
        @NotNull
        private Map<String, String> userData = MapsKt.emptyMap();

        @NotNull
        public final StorylyConfig build() {
            return new StorylyConfig(this.barStyling, this.storyGroupStyling, this.storyStyling, this.momentsStyling, this.productConfig, this.shareConfig, this.layoutDirection, this.customParameter, this.labels, this.userData, this.storylyPayload, this.isTestMode);
        }

        @NotNull
        public final Builder setBarStyling(@NotNull StorylyBarStyling storylyBarStyling) {
            Intrinsics.checkNotNullParameter(storylyBarStyling, TtmlNode.TAG_STYLING);
            this.barStyling = storylyBarStyling;
            return this;
        }

        @NotNull
        public final Builder setCustomParameter(@Nullable String str) {
            if (str == null || str.length() > 200) {
                str = null;
            }
            this.customParameter = str;
            return this;
        }

        @NotNull
        public final Builder setLabels(@Nullable Set<String> set) {
            this.labels = set;
            return this;
        }

        @NotNull
        public final Builder setLayoutDirection(@NotNull StorylyLayoutDirection storylyLayoutDirection) {
            Intrinsics.checkNotNullParameter(storylyLayoutDirection, "direction");
            this.layoutDirection = storylyLayoutDirection;
            return this;
        }

        @NotNull
        public final Builder setMomentsStyling(@NotNull StorylyMomentsStyling storylyMomentsStyling) {
            Intrinsics.checkNotNullParameter(storylyMomentsStyling, TtmlNode.TAG_STYLING);
            this.momentsStyling = storylyMomentsStyling;
            return this;
        }

        @NotNull
        public final Builder setProductConfig(@NotNull StorylyProductConfig storylyProductConfig) {
            Intrinsics.checkNotNullParameter(storylyProductConfig, "config");
            this.productConfig = storylyProductConfig;
            return this;
        }

        @NotNull
        public final Builder setShareConfig(@NotNull StorylyShareConfig storylyShareConfig) {
            Intrinsics.checkNotNullParameter(storylyShareConfig, "config");
            this.shareConfig = storylyShareConfig;
            return this;
        }

        @NotNull
        public final Builder setStoryGroupStyling(@NotNull StorylyStoryGroupStyling storylyStoryGroupStyling) {
            Intrinsics.checkNotNullParameter(storylyStoryGroupStyling, TtmlNode.TAG_STYLING);
            this.storyGroupStyling = storylyStoryGroupStyling;
            return this;
        }

        @NotNull
        public final Builder setStoryStyling(@NotNull StorylyStoryStyling storylyStoryStyling) {
            Intrinsics.checkNotNullParameter(storylyStoryStyling, TtmlNode.TAG_STYLING);
            this.storyStyling = storylyStoryStyling;
            return this;
        }

        @NotNull
        public final Builder setStorylyPayload(@Nullable String str) {
            this.storylyPayload = str;
            return this;
        }

        @NotNull
        public final Builder setTestMode(boolean z2) {
            this.isTestMode = z2;
            return this;
        }

        @NotNull
        public final Builder setUserData(@NotNull Map<String, String> map) {
            Intrinsics.checkNotNullParameter(map, "data");
            this.userData = map;
            return this;
        }
    }

    public StorylyConfig(@NotNull StorylyBarStyling storylyBarStyling, @NotNull StorylyStoryGroupStyling storylyStoryGroupStyling, @NotNull StorylyStoryStyling storylyStoryStyling, @NotNull StorylyMomentsStyling storylyMomentsStyling, @NotNull StorylyProductConfig storylyProductConfig, @NotNull StorylyShareConfig storylyShareConfig, @NotNull StorylyLayoutDirection storylyLayoutDirection, @Nullable String str, @Nullable Set<String> set, @NotNull Map<String, String> map, @Nullable String str2, boolean z2) {
        Intrinsics.checkNotNullParameter(storylyBarStyling, "bar");
        Intrinsics.checkNotNullParameter(storylyStoryGroupStyling, "group");
        Intrinsics.checkNotNullParameter(storylyStoryStyling, "story");
        Intrinsics.checkNotNullParameter(storylyMomentsStyling, "moments");
        Intrinsics.checkNotNullParameter(storylyProductConfig, "product");
        Intrinsics.checkNotNullParameter(storylyShareConfig, FirebaseAnalytics.Event.SHARE);
        Intrinsics.checkNotNullParameter(storylyLayoutDirection, "layoutDirection");
        Intrinsics.checkNotNullParameter(map, "_userData");
        this.bar = storylyBarStyling;
        this.group = storylyStoryGroupStyling;
        this.story = storylyStoryStyling;
        this.moments = storylyMomentsStyling;
        this.product = storylyProductConfig;
        this.share = storylyShareConfig;
        this.layoutDirection = storylyLayoutDirection;
        this.customParameter = str;
        this._labels = set;
        this._userData = map;
        this._storylyPayload = str2;
        this.isTestMode = z2;
    }

    private final Map<String, String> component10() {
        return this._userData;
    }

    private final String component11() {
        return this._storylyPayload;
    }

    private final Set<String> component9() {
        return this._labels;
    }

    public static /* synthetic */ StorylyConfig copy$default(StorylyConfig storylyConfig, StorylyBarStyling storylyBarStyling, StorylyStoryGroupStyling storylyStoryGroupStyling, StorylyStoryStyling storylyStoryStyling, StorylyMomentsStyling storylyMomentsStyling, StorylyProductConfig storylyProductConfig, StorylyShareConfig storylyShareConfig, StorylyLayoutDirection storylyLayoutDirection, String str, Set set, Map map, String str2, boolean z2, int i3, Object obj) {
        StorylyConfig storylyConfig2 = storylyConfig;
        int i4 = i3;
        return storylyConfig.copy((i4 & 1) != 0 ? storylyConfig2.bar : storylyBarStyling, (i4 & 2) != 0 ? storylyConfig2.group : storylyStoryGroupStyling, (i4 & 4) != 0 ? storylyConfig2.story : storylyStoryStyling, (i4 & 8) != 0 ? storylyConfig2.moments : storylyMomentsStyling, (i4 & 16) != 0 ? storylyConfig2.product : storylyProductConfig, (i4 & 32) != 0 ? storylyConfig2.share : storylyShareConfig, (i4 & 64) != 0 ? storylyConfig2.layoutDirection : storylyLayoutDirection, (i4 & 128) != 0 ? storylyConfig2.customParameter : str, (i4 & 256) != 0 ? storylyConfig2._labels : set, (i4 & 512) != 0 ? storylyConfig2._userData : map, (i4 & 1024) != 0 ? storylyConfig2._storylyPayload : str2, (i4 & 2048) != 0 ? storylyConfig2.isTestMode : z2);
    }

    @NotNull
    public final StorylyBarStyling component1$storyly_release() {
        return this.bar;
    }

    public final boolean component12$storyly_release() {
        return this.isTestMode;
    }

    @NotNull
    public final StorylyStoryGroupStyling component2$storyly_release() {
        return this.group;
    }

    @NotNull
    public final StorylyStoryStyling component3$storyly_release() {
        return this.story;
    }

    @NotNull
    public final StorylyMomentsStyling component4$storyly_release() {
        return this.moments;
    }

    @NotNull
    public final StorylyProductConfig component5$storyly_release() {
        return this.product;
    }

    @NotNull
    public final StorylyShareConfig component6$storyly_release() {
        return this.share;
    }

    @NotNull
    public final StorylyLayoutDirection component7$storyly_release() {
        return this.layoutDirection;
    }

    @Nullable
    public final String component8$storyly_release() {
        return this.customParameter;
    }

    @NotNull
    public final StorylyConfig copy(@NotNull StorylyBarStyling storylyBarStyling, @NotNull StorylyStoryGroupStyling storylyStoryGroupStyling, @NotNull StorylyStoryStyling storylyStoryStyling, @NotNull StorylyMomentsStyling storylyMomentsStyling, @NotNull StorylyProductConfig storylyProductConfig, @NotNull StorylyShareConfig storylyShareConfig, @NotNull StorylyLayoutDirection storylyLayoutDirection, @Nullable String str, @Nullable Set<String> set, @NotNull Map<String, String> map, @Nullable String str2, boolean z2) {
        Intrinsics.checkNotNullParameter(storylyBarStyling, "bar");
        StorylyStoryGroupStyling storylyStoryGroupStyling2 = storylyStoryGroupStyling;
        Intrinsics.checkNotNullParameter(storylyStoryGroupStyling2, "group");
        StorylyStoryStyling storylyStoryStyling2 = storylyStoryStyling;
        Intrinsics.checkNotNullParameter(storylyStoryStyling2, "story");
        StorylyMomentsStyling storylyMomentsStyling2 = storylyMomentsStyling;
        Intrinsics.checkNotNullParameter(storylyMomentsStyling2, "moments");
        StorylyProductConfig storylyProductConfig2 = storylyProductConfig;
        Intrinsics.checkNotNullParameter(storylyProductConfig2, "product");
        StorylyShareConfig storylyShareConfig2 = storylyShareConfig;
        Intrinsics.checkNotNullParameter(storylyShareConfig2, FirebaseAnalytics.Event.SHARE);
        StorylyLayoutDirection storylyLayoutDirection2 = storylyLayoutDirection;
        Intrinsics.checkNotNullParameter(storylyLayoutDirection2, "layoutDirection");
        Map<String, String> map2 = map;
        Intrinsics.checkNotNullParameter(map2, "_userData");
        return new StorylyConfig(storylyBarStyling, storylyStoryGroupStyling2, storylyStoryStyling2, storylyMomentsStyling2, storylyProductConfig2, storylyShareConfig2, storylyLayoutDirection2, str, set, map2, str2, z2);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StorylyConfig)) {
            return false;
        }
        StorylyConfig storylyConfig = (StorylyConfig) obj;
        return Intrinsics.areEqual((Object) this.bar, (Object) storylyConfig.bar) && Intrinsics.areEqual((Object) this.group, (Object) storylyConfig.group) && Intrinsics.areEqual((Object) this.story, (Object) storylyConfig.story) && Intrinsics.areEqual((Object) this.moments, (Object) storylyConfig.moments) && Intrinsics.areEqual((Object) this.product, (Object) storylyConfig.product) && Intrinsics.areEqual((Object) this.share, (Object) storylyConfig.share) && this.layoutDirection == storylyConfig.layoutDirection && Intrinsics.areEqual((Object) this.customParameter, (Object) storylyConfig.customParameter) && Intrinsics.areEqual((Object) this._labels, (Object) storylyConfig._labels) && Intrinsics.areEqual((Object) this._userData, (Object) storylyConfig._userData) && Intrinsics.areEqual((Object) this._storylyPayload, (Object) storylyConfig._storylyPayload) && this.isTestMode == storylyConfig.isTestMode;
    }

    @NotNull
    public final StorylyBarStyling getBar$storyly_release() {
        return this.bar;
    }

    @Nullable
    public final String getCustomParameter$storyly_release() {
        return this.customParameter;
    }

    @NotNull
    public final StorylyStoryGroupStyling getGroup$storyly_release() {
        return this.group;
    }

    @Nullable
    public final Set<String> getLabels() {
        Set<String> set = this._labels;
        if (set == null) {
            return null;
        }
        ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(set, 10));
        for (String str : set) {
            Locale locale = Locale.getDefault();
            Intrinsics.checkNotNullExpressionValue(locale, "getDefault()");
            if (str != null) {
                String lowerCase = str.toLowerCase(locale);
                Intrinsics.checkNotNullExpressionValue(lowerCase, "(this as java.lang.String).toLowerCase(locale)");
                if (lowerCase != null) {
                    arrayList.add(StringsKt__StringsKt.trim((CharSequence) lowerCase).toString());
                } else {
                    throw new NullPointerException("null cannot be cast to non-null type kotlin.CharSequence");
                }
            } else {
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
        }
        return CollectionsKt.toSet(arrayList);
    }

    @NotNull
    public final StorylyLayoutDirection getLayoutDirection$storyly_release() {
        return this.layoutDirection;
    }

    @NotNull
    public final StorylyMomentsStyling getMoments$storyly_release() {
        return this.moments;
    }

    @Nullable
    public final Function1<e, Unit> getOnDataUpdate$storyly_release() {
        return this.onDataUpdate;
    }

    @NotNull
    public final StorylyProductConfig getProduct$storyly_release() {
        return this.product;
    }

    @NotNull
    public final StorylyShareConfig getShare$storyly_release() {
        return this.share;
    }

    @NotNull
    public final StorylyStoryStyling getStory$storyly_release() {
        return this.story;
    }

    @Nullable
    public final String getStorylyPayload() {
        return this._storylyPayload;
    }

    @Nullable
    public final m0 getStorylyStyle$storyly_release() {
        return this.storylyStyle;
    }

    @NotNull
    public final Map<String, String> getUserData() {
        return this._userData;
    }

    public int hashCode() {
        int hashCode = this.group.hashCode();
        int hashCode2 = this.story.hashCode();
        int hashCode3 = this.moments.hashCode();
        int hashCode4 = (this.layoutDirection.hashCode() + ((this.share.hashCode() + ((this.product.hashCode() + ((hashCode3 + ((hashCode2 + ((hashCode + (this.bar.hashCode() * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
        String str = this.customParameter;
        int i3 = 0;
        int hashCode5 = (hashCode4 + (str == null ? 0 : str.hashCode())) * 31;
        Set<String> set = this._labels;
        int d2 = b.d(this._userData, (hashCode5 + (set == null ? 0 : set.hashCode())) * 31, 31);
        String str2 = this._storylyPayload;
        if (str2 != null) {
            i3 = str2.hashCode();
        }
        int i4 = (d2 + i3) * 31;
        boolean z2 = this.isTestMode;
        if (z2) {
            z2 = true;
        }
        return i4 + (z2 ? 1 : 0);
    }

    public final boolean isTestMode$storyly_release() {
        return this.isTestMode;
    }

    public final void setBar$storyly_release(@NotNull StorylyBarStyling storylyBarStyling) {
        Intrinsics.checkNotNullParameter(storylyBarStyling, "<set-?>");
        this.bar = storylyBarStyling;
    }

    public final void setCustomParameter$storyly_release(@Nullable String str) {
        this.customParameter = str;
    }

    public final void setGroup$storyly_release(@NotNull StorylyStoryGroupStyling storylyStoryGroupStyling) {
        Intrinsics.checkNotNullParameter(storylyStoryGroupStyling, "<set-?>");
        this.group = storylyStoryGroupStyling;
    }

    public final void setLabels(@Nullable Set<String> set) {
        this._labels = set;
        Function1<? super e, Unit> function1 = this.onDataUpdate;
        if (function1 != null) {
            function1.invoke(e.StorylyData);
        }
    }

    public final void setLayoutDirection$storyly_release(@NotNull StorylyLayoutDirection storylyLayoutDirection) {
        Intrinsics.checkNotNullParameter(storylyLayoutDirection, "<set-?>");
        this.layoutDirection = storylyLayoutDirection;
    }

    public final void setMoments$storyly_release(@NotNull StorylyMomentsStyling storylyMomentsStyling) {
        Intrinsics.checkNotNullParameter(storylyMomentsStyling, "<set-?>");
        this.moments = storylyMomentsStyling;
    }

    public final void setOnDataUpdate$storyly_release(@Nullable Function1<? super e, Unit> function1) {
        this.onDataUpdate = function1;
    }

    public final void setProduct$storyly_release(@NotNull StorylyProductConfig storylyProductConfig) {
        Intrinsics.checkNotNullParameter(storylyProductConfig, "<set-?>");
        this.product = storylyProductConfig;
    }

    public final void setShare$storyly_release(@NotNull StorylyShareConfig storylyShareConfig) {
        Intrinsics.checkNotNullParameter(storylyShareConfig, "<set-?>");
        this.share = storylyShareConfig;
    }

    public final void setStory$storyly_release(@NotNull StorylyStoryStyling storylyStoryStyling) {
        Intrinsics.checkNotNullParameter(storylyStoryStyling, "<set-?>");
        this.story = storylyStoryStyling;
    }

    public final void setStorylyPayload(@Nullable String str) {
        this._storylyPayload = str;
        Function1<? super e, Unit> function1 = this.onDataUpdate;
        if (function1 != null) {
            function1.invoke(e.MomentsIDsData);
        }
    }

    public final void setStorylyStyle$storyly_release(@Nullable m0 m0Var) {
        this.storylyStyle = m0Var;
    }

    public final void setTestMode$storyly_release(boolean z2) {
        this.isTestMode = z2;
    }

    public final void setUserData(@NotNull Map<String, String> map) {
        Intrinsics.checkNotNullParameter(map, "value");
        this._userData = map;
        Function1<? super e, Unit> function1 = this.onDataUpdate;
        if (function1 != null) {
            function1.invoke(e.UserDataUpdate);
        }
    }

    @NotNull
    public String toString() {
        StringBuilder sb = new StringBuilder("StorylyConfig(bar=");
        sb.append(this.bar);
        sb.append(", group=");
        sb.append(this.group);
        sb.append(", story=");
        sb.append(this.story);
        sb.append(", moments=");
        sb.append(this.moments);
        sb.append(", product=");
        sb.append(this.product);
        sb.append(", share=");
        sb.append(this.share);
        sb.append(", layoutDirection=");
        sb.append(this.layoutDirection);
        sb.append(", customParameter=");
        sb.append(this.customParameter);
        sb.append(", _labels=");
        sb.append(this._labels);
        sb.append(", _userData=");
        sb.append(this._userData);
        sb.append(", _storylyPayload=");
        sb.append(this._storylyPayload);
        sb.append(", isTestMode=");
        return i.c(sb, this.isTestMode, ')');
    }
}
