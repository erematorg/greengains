package com.appsamurai.storyly.config.styling.group;

import android.graphics.Typeface;
import androidx.annotation.Keep;
import androidx.core.view.ViewCompat;
import com.appsamurai.storyly.StoryGroupAnimation;
import com.appsamurai.storyly.StoryGroupSize;
import com.appsamurai.storyly.util.m;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\n\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\bZ\b\b\u0018\u00002\u00020\u0001:\u0002\u0001BË\u0001\b\u0000\u0012\f\u00107\u001a\b\u0012\u0004\u0012\u00020\u00020\b\u0012\f\u00108\u001a\b\u0012\u0004\u0012\u00020\u00020\b\u0012\u0006\u00109\u001a\u00020\u0002\u0012\b\u0010:\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010;\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010<\u001a\u00020\u0002\u0012\b\u0010=\u001a\u0004\u0018\u00010\u0013\u0012\u0006\u0010>\u001a\u00020\u0017\u0012\u0006\u0010?\u001a\u00020\u0002\u0012\u0006\u0010@\u001a\u00020\u0002\u0012\u0006\u0010A\u001a\u00020\u0002\u0012\u0006\u0010B\u001a\u00020!\u0012\u0014\u0010C\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0006\u0012\b\u0010D\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010E\u001a\u0004\u0018\u00010\u0002\u0012\b\u0010F\u001a\u0004\u0018\u00010\u0002\u0012\u0006\u0010G\u001a\u00020+\u0012\u0006\u0010H\u001a\u00020/\u0012\b\u0010I\u001a\u0004\u0018\u000103¢\u0006\u0006\b\u0001\u0010\u0001J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u0002HÂ\u0003¢\u0006\u0004\b\u0003\u0010\u0004J\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u0002HÂ\u0003¢\u0006\u0004\b\u0005\u0010\u0004J\u0017\u0010\u0007\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0006HÂ\u0003J\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\bHÀ\u0003¢\u0006\u0004\b\t\u0010\nJ\u0016\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00020\bHÀ\u0003¢\u0006\u0004\b\f\u0010\nJ\u0010\u0010\u0010\u001a\u00020\u0002HÀ\u0003¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0012\u001a\u00020\u0002HÀ\u0003¢\u0006\u0004\b\u0011\u0010\u000fJ\u0012\u0010\u0016\u001a\u0004\u0018\u00010\u0013HÀ\u0003¢\u0006\u0004\b\u0014\u0010\u0015J\u0010\u0010\u001a\u001a\u00020\u0017HÀ\u0003¢\u0006\u0004\b\u0018\u0010\u0019J\u0010\u0010\u001c\u001a\u00020\u0002HÀ\u0003¢\u0006\u0004\b\u001b\u0010\u000fJ\u0010\u0010\u001e\u001a\u00020\u0002HÀ\u0003¢\u0006\u0004\b\u001d\u0010\u000fJ\u0010\u0010 \u001a\u00020\u0002HÀ\u0003¢\u0006\u0004\b\u001f\u0010\u000fJ\u0010\u0010$\u001a\u00020!HÀ\u0003¢\u0006\u0004\b\"\u0010#J\u0012\u0010&\u001a\u0004\u0018\u00010\u0002HÀ\u0003¢\u0006\u0004\b%\u0010\u0004J\u0012\u0010(\u001a\u0004\u0018\u00010\u0002HÀ\u0003¢\u0006\u0004\b'\u0010\u0004J\u0012\u0010*\u001a\u0004\u0018\u00010\u0002HÀ\u0003¢\u0006\u0004\b)\u0010\u0004J\u0010\u0010.\u001a\u00020+HÀ\u0003¢\u0006\u0004\b,\u0010-J\u0010\u00102\u001a\u00020/HÀ\u0003¢\u0006\u0004\b0\u00101J\u0012\u00106\u001a\u0004\u0018\u000103HÀ\u0003¢\u0006\u0004\b4\u00105Jö\u0001\u0010J\u001a\u00020\u00002\u000e\b\u0002\u00107\u001a\b\u0012\u0004\u0012\u00020\u00020\b2\u000e\b\u0002\u00108\u001a\b\u0012\u0004\u0012\u00020\u00020\b2\b\b\u0002\u00109\u001a\u00020\u00022\n\b\u0002\u0010:\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010;\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010<\u001a\u00020\u00022\n\b\u0002\u0010=\u001a\u0004\u0018\u00010\u00132\b\b\u0002\u0010>\u001a\u00020\u00172\b\b\u0002\u0010?\u001a\u00020\u00022\b\b\u0002\u0010@\u001a\u00020\u00022\b\b\u0002\u0010A\u001a\u00020\u00022\b\b\u0002\u0010B\u001a\u00020!2\u0016\b\u0002\u0010C\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00062\n\b\u0002\u0010D\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010E\u001a\u0004\u0018\u00010\u00022\n\b\u0002\u0010F\u001a\u0004\u0018\u00010\u00022\b\b\u0002\u0010G\u001a\u00020+2\b\b\u0002\u0010H\u001a\u00020/2\n\b\u0002\u0010I\u001a\u0004\u0018\u000103HÆ\u0001¢\u0006\u0004\bJ\u0010KJ\t\u0010L\u001a\u00020\u0013HÖ\u0001J\t\u0010M\u001a\u00020\u0002HÖ\u0001J\u0013\u0010O\u001a\u00020+2\b\u0010N\u001a\u0004\u0018\u00010\u0001HÖ\u0003R(\u00107\u001a\b\u0012\u0004\u0012\u00020\u00020\b8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b7\u0010P\u001a\u0004\bQ\u0010\n\"\u0004\bR\u0010SR(\u00108\u001a\b\u0012\u0004\u0012\u00020\u00020\b8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b8\u0010P\u001a\u0004\bT\u0010\n\"\u0004\bU\u0010SR\"\u00109\u001a\u00020\u00028\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b9\u0010V\u001a\u0004\bW\u0010\u000f\"\u0004\bX\u0010YR\u0018\u0010:\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b:\u0010ZR\u0018\u0010;\u001a\u0004\u0018\u00010\u00028\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b;\u0010ZR\"\u0010<\u001a\u00020\u00028\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b<\u0010V\u001a\u0004\b[\u0010\u000f\"\u0004\b\\\u0010YR$\u0010=\u001a\u0004\u0018\u00010\u00138\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b=\u0010]\u001a\u0004\b^\u0010\u0015\"\u0004\b_\u0010`R\"\u0010>\u001a\u00020\u00178\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b>\u0010a\u001a\u0004\bb\u0010\u0019\"\u0004\bc\u0010dR\"\u0010?\u001a\u00020\u00028\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b?\u0010V\u001a\u0004\be\u0010\u000f\"\u0004\bf\u0010YR\"\u0010@\u001a\u00020\u00028\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\b@\u0010V\u001a\u0004\bg\u0010\u000f\"\u0004\bh\u0010YR\"\u0010A\u001a\u00020\u00028\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\bA\u0010V\u001a\u0004\bi\u0010\u000f\"\u0004\bj\u0010YR\"\u0010B\u001a\u00020!8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\bB\u0010k\u001a\u0004\bl\u0010#\"\u0004\bm\u0010nR$\u0010C\u001a\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u00068\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bC\u0010oR$\u0010D\u001a\u0004\u0018\u00010\u00028\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\bD\u0010Z\u001a\u0004\bp\u0010\u0004\"\u0004\bq\u0010rR$\u0010E\u001a\u0004\u0018\u00010\u00028\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\bE\u0010Z\u001a\u0004\bs\u0010\u0004\"\u0004\bt\u0010rR$\u0010F\u001a\u0004\u0018\u00010\u00028\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\bF\u0010Z\u001a\u0004\bu\u0010\u0004\"\u0004\bv\u0010rR\"\u0010G\u001a\u00020+8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\bG\u0010w\u001a\u0004\bx\u0010-\"\u0004\by\u0010zR\"\u0010H\u001a\u00020/8\u0000@\u0000X\u000e¢\u0006\u0012\n\u0004\bH\u0010{\u001a\u0004\b|\u00101\"\u0004\b}\u0010~R'\u0010I\u001a\u0004\u0018\u0001038\u0000@\u0000X\u000e¢\u0006\u0015\n\u0004\bI\u0010\u001a\u0005\b\u0001\u00105\"\u0006\b\u0001\u0010\u0001R%\u0010\u0001\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u00068@@\u0000X\u0004¢\u0006\b\u001a\u0006\b\u0001\u0010\u0001R\u0018\u0010\u0001\u001a\u00020\u00028@@\u0000X\u0004¢\u0006\u0007\u001a\u0005\b\u0001\u0010\u000fR\u0018\u0010\u0001\u001a\u00020\u00028@@\u0000X\u0004¢\u0006\u0007\u001a\u0005\b\u0001\u0010\u000f¨\u0006\u0001"}, d2 = {"Lcom/appsamurai/storyly/config/styling/group/StorylyStoryGroupStyling;", "", "", "component4", "()Ljava/lang/Integer;", "component5", "Lkotlin/Pair;", "component13", "", "component1$storyly_release", "()Ljava/util/List;", "component1", "component2$storyly_release", "component2", "component3$storyly_release", "()I", "component3", "component6$storyly_release", "component6", "", "component7$storyly_release", "()Ljava/lang/String;", "component7", "Lcom/appsamurai/storyly/StoryGroupAnimation;", "component8$storyly_release", "()Lcom/appsamurai/storyly/StoryGroupAnimation;", "component8", "component9$storyly_release", "component9", "component10$storyly_release", "component10", "component11$storyly_release", "component11", "Landroid/graphics/Typeface;", "component12$storyly_release", "()Landroid/graphics/Typeface;", "component12", "component14$storyly_release", "component14", "component15$storyly_release", "component15", "component16$storyly_release", "component16", "", "component17$storyly_release", "()Z", "component17", "Lcom/appsamurai/storyly/StoryGroupSize;", "component18$storyly_release", "()Lcom/appsamurai/storyly/StoryGroupSize;", "component18", "Lcom/appsamurai/storyly/config/styling/group/StoryGroupViewFactory;", "component19$storyly_release", "()Lcom/appsamurai/storyly/config/styling/group/StoryGroupViewFactory;", "component19", "iconBorderColorSeen", "iconBorderColorNotSeen", "iconBackgroundColor", "_iconHeight", "_iconWidth", "iconCornerRadius", "iconThematicImageLabel", "iconBorderAnimation", "pinIconColor", "titleSeenColor", "titleNotSeenColor", "titleTypeface", "_titleTextSize", "titleLineCount", "titleMinLineCount", "titleMaxLineCount", "isTitleVisible", "size", "groupViewFactory", "copy", "(Ljava/util/List;Ljava/util/List;ILjava/lang/Integer;Ljava/lang/Integer;ILjava/lang/String;Lcom/appsamurai/storyly/StoryGroupAnimation;IIILandroid/graphics/Typeface;Lkotlin/Pair;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;ZLcom/appsamurai/storyly/StoryGroupSize;Lcom/appsamurai/storyly/config/styling/group/StoryGroupViewFactory;)Lcom/appsamurai/storyly/config/styling/group/StorylyStoryGroupStyling;", "toString", "hashCode", "other", "equals", "Ljava/util/List;", "getIconBorderColorSeen$storyly_release", "setIconBorderColorSeen$storyly_release", "(Ljava/util/List;)V", "getIconBorderColorNotSeen$storyly_release", "setIconBorderColorNotSeen$storyly_release", "I", "getIconBackgroundColor$storyly_release", "setIconBackgroundColor$storyly_release", "(I)V", "Ljava/lang/Integer;", "getIconCornerRadius$storyly_release", "setIconCornerRadius$storyly_release", "Ljava/lang/String;", "getIconThematicImageLabel$storyly_release", "setIconThematicImageLabel$storyly_release", "(Ljava/lang/String;)V", "Lcom/appsamurai/storyly/StoryGroupAnimation;", "getIconBorderAnimation$storyly_release", "setIconBorderAnimation$storyly_release", "(Lcom/appsamurai/storyly/StoryGroupAnimation;)V", "getPinIconColor$storyly_release", "setPinIconColor$storyly_release", "getTitleSeenColor$storyly_release", "setTitleSeenColor$storyly_release", "getTitleNotSeenColor$storyly_release", "setTitleNotSeenColor$storyly_release", "Landroid/graphics/Typeface;", "getTitleTypeface$storyly_release", "setTitleTypeface$storyly_release", "(Landroid/graphics/Typeface;)V", "Lkotlin/Pair;", "getTitleLineCount$storyly_release", "setTitleLineCount$storyly_release", "(Ljava/lang/Integer;)V", "getTitleMinLineCount$storyly_release", "setTitleMinLineCount$storyly_release", "getTitleMaxLineCount$storyly_release", "setTitleMaxLineCount$storyly_release", "Z", "isTitleVisible$storyly_release", "setTitleVisible$storyly_release", "(Z)V", "Lcom/appsamurai/storyly/StoryGroupSize;", "getSize$storyly_release", "setSize$storyly_release", "(Lcom/appsamurai/storyly/StoryGroupSize;)V", "Lcom/appsamurai/storyly/config/styling/group/StoryGroupViewFactory;", "getGroupViewFactory$storyly_release", "setGroupViewFactory$storyly_release", "(Lcom/appsamurai/storyly/config/styling/group/StoryGroupViewFactory;)V", "getTitleTextSize$storyly_release", "()Lkotlin/Pair;", "titleTextSize", "getIconHeight$storyly_release", "iconHeight", "getIconWidth$storyly_release", "iconWidth", "<init>", "(Ljava/util/List;Ljava/util/List;ILjava/lang/Integer;Ljava/lang/Integer;ILjava/lang/String;Lcom/appsamurai/storyly/StoryGroupAnimation;IIILandroid/graphics/Typeface;Lkotlin/Pair;Ljava/lang/Integer;Ljava/lang/Integer;Ljava/lang/Integer;ZLcom/appsamurai/storyly/StoryGroupSize;Lcom/appsamurai/storyly/config/styling/group/StoryGroupViewFactory;)V", "Builder", "storyly_release"}, k = 1, mv = {1, 5, 1})
@Keep
public final class StorylyStoryGroupStyling {
    @Nullable
    private Integer _iconHeight;
    @Nullable
    private Integer _iconWidth;
    @NotNull
    private Pair<Integer, Integer> _titleTextSize;
    @Nullable
    private StoryGroupViewFactory groupViewFactory;
    private int iconBackgroundColor;
    @NotNull
    private StoryGroupAnimation iconBorderAnimation;
    @NotNull
    private List<Integer> iconBorderColorNotSeen;
    @NotNull
    private List<Integer> iconBorderColorSeen;
    private int iconCornerRadius;
    @Nullable
    private String iconThematicImageLabel;
    private boolean isTitleVisible;
    private int pinIconColor;
    @NotNull
    private StoryGroupSize size;
    @Nullable
    private Integer titleLineCount;
    @Nullable
    private Integer titleMaxLineCount;
    @Nullable
    private Integer titleMinLineCount;
    private int titleNotSeenColor;
    private int titleSeenColor;
    @NotNull
    private Typeface titleTypeface;

    @Keep
    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b \b\u0007\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\bJ\u0010KJ\u0014\u0010\u0005\u001a\u00020\u00002\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002J\u0014\u0010\u0006\u001a\u00020\u00002\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002J\u000e\u0010\b\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u0003J\u000e\u0010\t\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u0003J\u000e\u0010\u000b\u001a\u00020\u00002\u0006\u0010\n\u001a\u00020\u0003J\u000e\u0010\r\u001a\u00020\u00002\u0006\u0010\f\u001a\u00020\u0003J\u000e\u0010\u000f\u001a\u00020\u00002\u0006\u0010\u000e\u001a\u00020\u0003J\u0010\u0010\u0012\u001a\u00020\u00002\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010J\u000e\u0010\u0015\u001a\u00020\u00002\u0006\u0010\u0014\u001a\u00020\u0013J\u000e\u0010\u0016\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u0003J\u000e\u0010\u0017\u001a\u00020\u00002\u0006\u0010\u0007\u001a\u00020\u0003J\u001a\u0010\u001a\u001a\u00020\u00002\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00030\u0018J\u0017\u0010\u001c\u001a\u00020\u00002\b\u0010\u001b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u001c\u0010\u001dJ\u0017\u0010\u001e\u001a\u00020\u00002\b\u0010\u001b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u001e\u0010\u001dJ\u0017\u0010\u001f\u001a\u00020\u00002\b\u0010\u001b\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u001f\u0010\u001dJ\u000e\u0010\"\u001a\u00020\u00002\u0006\u0010!\u001a\u00020 J\u000e\u0010%\u001a\u00020\u00002\u0006\u0010$\u001a\u00020#J\u000e\u0010(\u001a\u00020\u00002\u0006\u0010'\u001a\u00020&J\u0010\u0010+\u001a\u00020\u00002\b\u0010*\u001a\u0004\u0018\u00010)J\u0006\u0010-\u001a\u00020,R\u001c\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b.\u0010/R\u001c\u00100\u001a\b\u0012\u0004\u0012\u00020\u00030\u00028\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b0\u0010/R\u0016\u00101\u001a\u00020\u00038\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b1\u00102R\u0018\u00103\u001a\u0004\u0018\u00010\u00038\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b3\u00104R\u0018\u00105\u001a\u0004\u0018\u00010\u00038\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b5\u00104R\u0016\u00106\u001a\u00020\u00038\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b6\u00102R\u0018\u00107\u001a\u0004\u0018\u00010\u00108\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b7\u00108R\u0016\u00109\u001a\u00020\u00138\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b9\u0010:R\u0016\u0010;\u001a\u00020\u00038\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b;\u00102R\u0016\u0010<\u001a\u00020\u00038\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b<\u00102R\u0016\u0010=\u001a\u00020\u00038\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b=\u00102R\u0016\u0010>\u001a\u00020 8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b>\u0010?R$\u0010@\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u00188\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b@\u0010AR\u0018\u0010B\u001a\u0004\u0018\u00010\u00038\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bB\u00104R\u0018\u0010C\u001a\u0004\u0018\u00010\u00038\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bC\u00104R\u0018\u0010D\u001a\u0004\u0018\u00010\u00038\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bD\u00104R\u0016\u0010E\u001a\u00020#8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bE\u0010FR\u0018\u0010G\u001a\u0004\u0018\u00010)8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\bG\u0010HR\u0016\u0010'\u001a\u00020&8\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b'\u0010I¨\u0006L"}, d2 = {"Lcom/appsamurai/storyly/config/styling/group/StorylyStoryGroupStyling$Builder;", "", "", "", "colors", "setIconBorderColorSeen", "setIconBorderColorNotSeen", "color", "setIconBackgroundColor", "setPinIconColor", "height", "setIconHeight", "width", "setIconWidth", "radius", "setIconCornerRadius", "", "label", "setIconThematicImageLabel", "Lcom/appsamurai/storyly/StoryGroupAnimation;", "animation", "setIconBorderAnimation", "setTitleSeenColor", "setTitleNotSeenColor", "Lkotlin/Pair;", "typeSizePair", "setTitleTextSize", "count", "setTitleLineCount", "(Ljava/lang/Integer;)Lcom/appsamurai/storyly/config/styling/group/StorylyStoryGroupStyling$Builder;", "setTitleMinLineCount", "setTitleMaxLineCount", "Landroid/graphics/Typeface;", "typeface", "setTitleTypeface", "", "isVisible", "setTitleVisibility", "Lcom/appsamurai/storyly/StoryGroupSize;", "size", "setSize", "Lcom/appsamurai/storyly/config/styling/group/StoryGroupViewFactory;", "factory", "setCustomGroupViewFactory", "Lcom/appsamurai/storyly/config/styling/group/StorylyStoryGroupStyling;", "build", "iconBorderColorSeen", "Ljava/util/List;", "iconBorderColorNotSeen", "iconBackgroundColor", "I", "iconHeight", "Ljava/lang/Integer;", "iconWidth", "iconCornerRadius", "iconThematicImageLabel", "Ljava/lang/String;", "iconBorderAnimation", "Lcom/appsamurai/storyly/StoryGroupAnimation;", "pinIconColor", "titleSeenColor", "titleNotSeenColor", "titleTypeface", "Landroid/graphics/Typeface;", "titleTextSize", "Lkotlin/Pair;", "titleLineCount", "titleMinLineCount", "titleMaxLineCount", "isTitleVisible", "Z", "customGroupViewFactory", "Lcom/appsamurai/storyly/config/styling/group/StoryGroupViewFactory;", "Lcom/appsamurai/storyly/StoryGroupSize;", "<init>", "()V", "storyly_release"}, k = 1, mv = {1, 5, 1})
    public static final class Builder {
        @Nullable
        private StoryGroupViewFactory customGroupViewFactory;
        private int iconBackgroundColor;
        @NotNull
        private StoryGroupAnimation iconBorderAnimation;
        @NotNull
        private List<Integer> iconBorderColorNotSeen;
        @NotNull
        private List<Integer> iconBorderColorSeen;
        private int iconCornerRadius;
        @Nullable
        private Integer iconHeight;
        @Nullable
        private String iconThematicImageLabel;
        @Nullable
        private Integer iconWidth;
        private boolean isTitleVisible;
        private int pinIconColor;
        @NotNull
        private StoryGroupSize size;
        @Nullable
        private Integer titleLineCount;
        @Nullable
        private Integer titleMaxLineCount;
        @Nullable
        private Integer titleMinLineCount;
        private int titleNotSeenColor;
        private int titleSeenColor;
        @NotNull
        private Pair<Integer, Integer> titleTextSize;
        @NotNull
        private Typeface titleTypeface;

        public Builder() {
            com.appsamurai.storyly.config.styling.a aVar = com.appsamurai.storyly.config.styling.a.COLOR_DBDBDB;
            List<com.appsamurai.storyly.config.styling.a> listOf = CollectionsKt.listOf(aVar, aVar);
            ArrayList arrayList = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listOf, 10));
            for (com.appsamurai.storyly.config.styling.a a2 : listOf) {
                arrayList.add(Integer.valueOf(a2.a()));
            }
            this.iconBorderColorSeen = arrayList;
            com.appsamurai.storyly.config.styling.a aVar2 = com.appsamurai.storyly.config.styling.a.COLOR_FED169;
            List<com.appsamurai.storyly.config.styling.a> listOf2 = CollectionsKt.listOf(aVar2, com.appsamurai.storyly.config.styling.a.COLOR_FA7C20, com.appsamurai.storyly.config.styling.a.COLOR_C9287B, com.appsamurai.storyly.config.styling.a.COLOR_962EC2, aVar2);
            ArrayList arrayList2 = new ArrayList(CollectionsKt__IterablesKt.collectionSizeOrDefault(listOf2, 10));
            for (com.appsamurai.storyly.config.styling.a a3 : listOf2) {
                arrayList2.add(Integer.valueOf(a3.a()));
            }
            this.iconBorderColorNotSeen = arrayList2;
            this.iconBackgroundColor = com.appsamurai.storyly.config.styling.a.COLOR_F1F1F1.a();
            this.iconCornerRadius = m.a((Number) 40);
            this.iconBorderAnimation = StoryGroupAnimation.BorderRotation;
            this.pinIconColor = com.appsamurai.storyly.config.styling.a.COLOR_F87825.a();
            this.titleSeenColor = ViewCompat.MEASURED_STATE_MASK;
            this.titleNotSeenColor = ViewCompat.MEASURED_STATE_MASK;
            Typeface typeface = Typeface.DEFAULT;
            Intrinsics.checkNotNullExpressionValue(typeface, "DEFAULT");
            this.titleTypeface = typeface;
            this.titleTextSize = new Pair<>(0, null);
            this.isTitleVisible = true;
            this.size = StoryGroupSize.Large;
        }

        @NotNull
        public final StorylyStoryGroupStyling build() {
            return new StorylyStoryGroupStyling(this.iconBorderColorSeen, this.iconBorderColorNotSeen, this.iconBackgroundColor, this.iconHeight, this.iconWidth, this.iconCornerRadius, this.iconThematicImageLabel, this.iconBorderAnimation, this.pinIconColor, this.titleSeenColor, this.titleNotSeenColor, this.titleTypeface, this.titleTextSize, this.titleLineCount, this.titleMinLineCount, this.titleMaxLineCount, this.isTitleVisible, this.size, this.customGroupViewFactory);
        }

        @NotNull
        public final Builder setCustomGroupViewFactory(@Nullable StoryGroupViewFactory storyGroupViewFactory) {
            this.customGroupViewFactory = storyGroupViewFactory;
            return this;
        }

        @NotNull
        public final Builder setIconBackgroundColor(int i3) {
            this.iconBackgroundColor = i3;
            return this;
        }

        @NotNull
        public final Builder setIconBorderAnimation(@NotNull StoryGroupAnimation storyGroupAnimation) {
            Intrinsics.checkNotNullParameter(storyGroupAnimation, "animation");
            this.iconBorderAnimation = storyGroupAnimation;
            return this;
        }

        @NotNull
        public final Builder setIconBorderColorNotSeen(@NotNull List<Integer> list) {
            Intrinsics.checkNotNullParameter(list, "colors");
            this.iconBorderColorNotSeen = list;
            return this;
        }

        @NotNull
        public final Builder setIconBorderColorSeen(@NotNull List<Integer> list) {
            Intrinsics.checkNotNullParameter(list, "colors");
            this.iconBorderColorSeen = list;
            return this;
        }

        @NotNull
        public final Builder setIconCornerRadius(int i3) {
            this.iconCornerRadius = i3;
            return this;
        }

        @NotNull
        public final Builder setIconHeight(int i3) {
            this.iconHeight = Integer.valueOf(i3);
            return this;
        }

        @NotNull
        public final Builder setIconThematicImageLabel(@Nullable String str) {
            this.iconThematicImageLabel = str;
            return this;
        }

        @NotNull
        public final Builder setIconWidth(int i3) {
            this.iconWidth = Integer.valueOf(i3);
            return this;
        }

        @NotNull
        public final Builder setPinIconColor(int i3) {
            this.pinIconColor = i3;
            return this;
        }

        @NotNull
        public final Builder setSize(@NotNull StoryGroupSize storyGroupSize) {
            Intrinsics.checkNotNullParameter(storyGroupSize, "size");
            this.size = storyGroupSize;
            return this;
        }

        @NotNull
        public final Builder setTitleLineCount(@Nullable Integer num) {
            this.titleLineCount = num;
            return this;
        }

        @NotNull
        public final Builder setTitleMaxLineCount(@Nullable Integer num) {
            this.titleMaxLineCount = num;
            return this;
        }

        @NotNull
        public final Builder setTitleMinLineCount(@Nullable Integer num) {
            this.titleMinLineCount = num;
            return this;
        }

        @NotNull
        public final Builder setTitleNotSeenColor(int i3) {
            this.titleNotSeenColor = i3;
            return this;
        }

        @NotNull
        public final Builder setTitleSeenColor(int i3) {
            this.titleSeenColor = i3;
            return this;
        }

        @NotNull
        public final Builder setTitleTextSize(@NotNull Pair<Integer, Integer> pair) {
            Intrinsics.checkNotNullParameter(pair, "typeSizePair");
            this.titleTextSize = pair;
            return this;
        }

        @NotNull
        public final Builder setTitleTypeface(@NotNull Typeface typeface) {
            Intrinsics.checkNotNullParameter(typeface, "typeface");
            this.titleTypeface = typeface;
            return this;
        }

        @NotNull
        public final Builder setTitleVisibility(boolean z2) {
            this.isTitleVisible = z2;
            return this;
        }
    }

    public /* synthetic */ class a {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[StoryGroupSize.values().length];
            iArr[StoryGroupSize.Small.ordinal()] = 1;
            iArr[StoryGroupSize.Custom.ordinal()] = 2;
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public StorylyStoryGroupStyling(@NotNull List<Integer> list, @NotNull List<Integer> list2, int i3, @Nullable Integer num, @Nullable Integer num2, int i4, @Nullable String str, @NotNull StoryGroupAnimation storyGroupAnimation, int i5, int i6, int i7, @NotNull Typeface typeface, @NotNull Pair<Integer, Integer> pair, @Nullable Integer num3, @Nullable Integer num4, @Nullable Integer num5, boolean z2, @NotNull StoryGroupSize storyGroupSize, @Nullable StoryGroupViewFactory storyGroupViewFactory) {
        StoryGroupAnimation storyGroupAnimation2 = storyGroupAnimation;
        Typeface typeface2 = typeface;
        Pair<Integer, Integer> pair2 = pair;
        StoryGroupSize storyGroupSize2 = storyGroupSize;
        Intrinsics.checkNotNullParameter(list, "iconBorderColorSeen");
        Intrinsics.checkNotNullParameter(list2, "iconBorderColorNotSeen");
        Intrinsics.checkNotNullParameter(storyGroupAnimation2, "iconBorderAnimation");
        Intrinsics.checkNotNullParameter(typeface2, "titleTypeface");
        Intrinsics.checkNotNullParameter(pair2, "_titleTextSize");
        Intrinsics.checkNotNullParameter(storyGroupSize2, "size");
        this.iconBorderColorSeen = list;
        this.iconBorderColorNotSeen = list2;
        this.iconBackgroundColor = i3;
        this._iconHeight = num;
        this._iconWidth = num2;
        this.iconCornerRadius = i4;
        this.iconThematicImageLabel = str;
        this.iconBorderAnimation = storyGroupAnimation2;
        this.pinIconColor = i5;
        this.titleSeenColor = i6;
        this.titleNotSeenColor = i7;
        this.titleTypeface = typeface2;
        this._titleTextSize = pair2;
        this.titleLineCount = num3;
        this.titleMinLineCount = num4;
        this.titleMaxLineCount = num5;
        this.isTitleVisible = z2;
        this.size = storyGroupSize2;
        this.groupViewFactory = storyGroupViewFactory;
    }

    private final Pair<Integer, Integer> component13() {
        return this._titleTextSize;
    }

    private final Integer component4() {
        return this._iconHeight;
    }

    private final Integer component5() {
        return this._iconWidth;
    }

    public static /* synthetic */ StorylyStoryGroupStyling copy$default(StorylyStoryGroupStyling storylyStoryGroupStyling, List list, List list2, int i3, Integer num, Integer num2, int i4, String str, StoryGroupAnimation storyGroupAnimation, int i5, int i6, int i7, Typeface typeface, Pair pair, Integer num3, Integer num4, Integer num5, boolean z2, StoryGroupSize storyGroupSize, StoryGroupViewFactory storyGroupViewFactory, int i8, Object obj) {
        StorylyStoryGroupStyling storylyStoryGroupStyling2 = storylyStoryGroupStyling;
        int i9 = i8;
        return storylyStoryGroupStyling.copy((i9 & 1) != 0 ? storylyStoryGroupStyling2.iconBorderColorSeen : list, (i9 & 2) != 0 ? storylyStoryGroupStyling2.iconBorderColorNotSeen : list2, (i9 & 4) != 0 ? storylyStoryGroupStyling2.iconBackgroundColor : i3, (i9 & 8) != 0 ? storylyStoryGroupStyling2._iconHeight : num, (i9 & 16) != 0 ? storylyStoryGroupStyling2._iconWidth : num2, (i9 & 32) != 0 ? storylyStoryGroupStyling2.iconCornerRadius : i4, (i9 & 64) != 0 ? storylyStoryGroupStyling2.iconThematicImageLabel : str, (i9 & 128) != 0 ? storylyStoryGroupStyling2.iconBorderAnimation : storyGroupAnimation, (i9 & 256) != 0 ? storylyStoryGroupStyling2.pinIconColor : i5, (i9 & 512) != 0 ? storylyStoryGroupStyling2.titleSeenColor : i6, (i9 & 1024) != 0 ? storylyStoryGroupStyling2.titleNotSeenColor : i7, (i9 & 2048) != 0 ? storylyStoryGroupStyling2.titleTypeface : typeface, (i9 & 4096) != 0 ? storylyStoryGroupStyling2._titleTextSize : pair, (i9 & 8192) != 0 ? storylyStoryGroupStyling2.titleLineCount : num3, (i9 & 16384) != 0 ? storylyStoryGroupStyling2.titleMinLineCount : num4, (i9 & 32768) != 0 ? storylyStoryGroupStyling2.titleMaxLineCount : num5, (i9 & 65536) != 0 ? storylyStoryGroupStyling2.isTitleVisible : z2, (i9 & 131072) != 0 ? storylyStoryGroupStyling2.size : storyGroupSize, (i9 & 262144) != 0 ? storylyStoryGroupStyling2.groupViewFactory : storyGroupViewFactory);
    }

    @NotNull
    public final List<Integer> component1$storyly_release() {
        return this.iconBorderColorSeen;
    }

    public final int component10$storyly_release() {
        return this.titleSeenColor;
    }

    public final int component11$storyly_release() {
        return this.titleNotSeenColor;
    }

    @NotNull
    public final Typeface component12$storyly_release() {
        return this.titleTypeface;
    }

    @Nullable
    public final Integer component14$storyly_release() {
        return this.titleLineCount;
    }

    @Nullable
    public final Integer component15$storyly_release() {
        return this.titleMinLineCount;
    }

    @Nullable
    public final Integer component16$storyly_release() {
        return this.titleMaxLineCount;
    }

    public final boolean component17$storyly_release() {
        return this.isTitleVisible;
    }

    @NotNull
    public final StoryGroupSize component18$storyly_release() {
        return this.size;
    }

    @Nullable
    public final StoryGroupViewFactory component19$storyly_release() {
        return this.groupViewFactory;
    }

    @NotNull
    public final List<Integer> component2$storyly_release() {
        return this.iconBorderColorNotSeen;
    }

    public final int component3$storyly_release() {
        return this.iconBackgroundColor;
    }

    public final int component6$storyly_release() {
        return this.iconCornerRadius;
    }

    @Nullable
    public final String component7$storyly_release() {
        return this.iconThematicImageLabel;
    }

    @NotNull
    public final StoryGroupAnimation component8$storyly_release() {
        return this.iconBorderAnimation;
    }

    public final int component9$storyly_release() {
        return this.pinIconColor;
    }

    @NotNull
    public final StorylyStoryGroupStyling copy(@NotNull List<Integer> list, @NotNull List<Integer> list2, int i3, @Nullable Integer num, @Nullable Integer num2, int i4, @Nullable String str, @NotNull StoryGroupAnimation storyGroupAnimation, int i5, int i6, int i7, @NotNull Typeface typeface, @NotNull Pair<Integer, Integer> pair, @Nullable Integer num3, @Nullable Integer num4, @Nullable Integer num5, boolean z2, @NotNull StoryGroupSize storyGroupSize, @Nullable StoryGroupViewFactory storyGroupViewFactory) {
        List<Integer> list3 = list;
        Intrinsics.checkNotNullParameter(list3, "iconBorderColorSeen");
        Intrinsics.checkNotNullParameter(list2, "iconBorderColorNotSeen");
        Intrinsics.checkNotNullParameter(storyGroupAnimation, "iconBorderAnimation");
        Intrinsics.checkNotNullParameter(typeface, "titleTypeface");
        Intrinsics.checkNotNullParameter(pair, "_titleTextSize");
        Intrinsics.checkNotNullParameter(storyGroupSize, "size");
        return new StorylyStoryGroupStyling(list3, list2, i3, num, num2, i4, str, storyGroupAnimation, i5, i6, i7, typeface, pair, num3, num4, num5, z2, storyGroupSize, storyGroupViewFactory);
    }

    public boolean equals(@Nullable Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof StorylyStoryGroupStyling)) {
            return false;
        }
        StorylyStoryGroupStyling storylyStoryGroupStyling = (StorylyStoryGroupStyling) obj;
        return Intrinsics.areEqual((Object) this.iconBorderColorSeen, (Object) storylyStoryGroupStyling.iconBorderColorSeen) && Intrinsics.areEqual((Object) this.iconBorderColorNotSeen, (Object) storylyStoryGroupStyling.iconBorderColorNotSeen) && this.iconBackgroundColor == storylyStoryGroupStyling.iconBackgroundColor && Intrinsics.areEqual((Object) this._iconHeight, (Object) storylyStoryGroupStyling._iconHeight) && Intrinsics.areEqual((Object) this._iconWidth, (Object) storylyStoryGroupStyling._iconWidth) && this.iconCornerRadius == storylyStoryGroupStyling.iconCornerRadius && Intrinsics.areEqual((Object) this.iconThematicImageLabel, (Object) storylyStoryGroupStyling.iconThematicImageLabel) && this.iconBorderAnimation == storylyStoryGroupStyling.iconBorderAnimation && this.pinIconColor == storylyStoryGroupStyling.pinIconColor && this.titleSeenColor == storylyStoryGroupStyling.titleSeenColor && this.titleNotSeenColor == storylyStoryGroupStyling.titleNotSeenColor && Intrinsics.areEqual((Object) this.titleTypeface, (Object) storylyStoryGroupStyling.titleTypeface) && Intrinsics.areEqual((Object) this._titleTextSize, (Object) storylyStoryGroupStyling._titleTextSize) && Intrinsics.areEqual((Object) this.titleLineCount, (Object) storylyStoryGroupStyling.titleLineCount) && Intrinsics.areEqual((Object) this.titleMinLineCount, (Object) storylyStoryGroupStyling.titleMinLineCount) && Intrinsics.areEqual((Object) this.titleMaxLineCount, (Object) storylyStoryGroupStyling.titleMaxLineCount) && this.isTitleVisible == storylyStoryGroupStyling.isTitleVisible && this.size == storylyStoryGroupStyling.size && Intrinsics.areEqual((Object) this.groupViewFactory, (Object) storylyStoryGroupStyling.groupViewFactory);
    }

    @Nullable
    public final StoryGroupViewFactory getGroupViewFactory$storyly_release() {
        return this.groupViewFactory;
    }

    public final int getIconBackgroundColor$storyly_release() {
        return this.iconBackgroundColor;
    }

    @NotNull
    public final StoryGroupAnimation getIconBorderAnimation$storyly_release() {
        return this.iconBorderAnimation;
    }

    @NotNull
    public final List<Integer> getIconBorderColorNotSeen$storyly_release() {
        return this.iconBorderColorNotSeen;
    }

    @NotNull
    public final List<Integer> getIconBorderColorSeen$storyly_release() {
        return this.iconBorderColorSeen;
    }

    public final int getIconCornerRadius$storyly_release() {
        return this.iconCornerRadius;
    }

    public final int getIconHeight$storyly_release() {
        int i3 = a.$EnumSwitchMapping$0[this.size.ordinal()];
        if (i3 == 1) {
            return m.a((Number) 60);
        }
        if (i3 != 2) {
            return m.a((Number) 80);
        }
        Integer num = this._iconHeight;
        return num == null ? m.a((Number) 80) : num.intValue();
    }

    @Nullable
    public final String getIconThematicImageLabel$storyly_release() {
        return this.iconThematicImageLabel;
    }

    public final int getIconWidth$storyly_release() {
        int i3 = a.$EnumSwitchMapping$0[this.size.ordinal()];
        if (i3 == 1) {
            return m.a((Number) 60);
        }
        if (i3 != 2) {
            return m.a((Number) 80);
        }
        Integer num = this._iconWidth;
        return num == null ? m.a((Number) 80) : num.intValue();
    }

    public final int getPinIconColor$storyly_release() {
        return this.pinIconColor;
    }

    @NotNull
    public final StoryGroupSize getSize$storyly_release() {
        return this.size;
    }

    @Nullable
    public final Integer getTitleLineCount$storyly_release() {
        return this.titleLineCount;
    }

    @Nullable
    public final Integer getTitleMaxLineCount$storyly_release() {
        return this.titleMaxLineCount;
    }

    @Nullable
    public final Integer getTitleMinLineCount$storyly_release() {
        return this.titleMinLineCount;
    }

    public final int getTitleNotSeenColor$storyly_release() {
        return this.titleNotSeenColor;
    }

    public final int getTitleSeenColor$storyly_release() {
        return this.titleSeenColor;
    }

    @NotNull
    public final Pair<Integer, Integer> getTitleTextSize$storyly_release() {
        Integer second = this._titleTextSize.getSecond();
        if (second == null) {
            return a.$EnumSwitchMapping$0[getSize$storyly_release().ordinal()] == 1 ? new Pair<>(0, Integer.valueOf(m.a((Number) 11))) : new Pair<>(0, Integer.valueOf(m.a((Number) 14)));
        }
        return new Pair<>(this._titleTextSize.getFirst(), Integer.valueOf(second.intValue()));
    }

    @NotNull
    public final Typeface getTitleTypeface$storyly_release() {
        return this.titleTypeface;
    }

    public int hashCode() {
        int c3 = androidx.compose.animation.core.a.c(this.iconBackgroundColor, androidx.compose.animation.core.a.j(this.iconBorderColorNotSeen, this.iconBorderColorSeen.hashCode() * 31, 31), 31);
        Integer num = this._iconHeight;
        int i3 = 0;
        int hashCode = (c3 + (num == null ? 0 : num.hashCode())) * 31;
        Integer num2 = this._iconWidth;
        int c4 = androidx.compose.animation.core.a.c(this.iconCornerRadius, (hashCode + (num2 == null ? 0 : num2.hashCode())) * 31, 31);
        String str = this.iconThematicImageLabel;
        int hashCode2 = (this._titleTextSize.hashCode() + ((this.titleTypeface.hashCode() + androidx.compose.animation.core.a.c(this.titleNotSeenColor, androidx.compose.animation.core.a.c(this.titleSeenColor, androidx.compose.animation.core.a.c(this.pinIconColor, (this.iconBorderAnimation.hashCode() + ((c4 + (str == null ? 0 : str.hashCode())) * 31)) * 31, 31), 31), 31)) * 31)) * 31;
        Integer num3 = this.titleLineCount;
        int hashCode3 = (hashCode2 + (num3 == null ? 0 : num3.hashCode())) * 31;
        Integer num4 = this.titleMinLineCount;
        int hashCode4 = (hashCode3 + (num4 == null ? 0 : num4.hashCode())) * 31;
        Integer num5 = this.titleMaxLineCount;
        int hashCode5 = (hashCode4 + (num5 == null ? 0 : num5.hashCode())) * 31;
        boolean z2 = this.isTitleVisible;
        if (z2) {
            z2 = true;
        }
        int hashCode6 = (this.size.hashCode() + ((hashCode5 + (z2 ? 1 : 0)) * 31)) * 31;
        StoryGroupViewFactory storyGroupViewFactory = this.groupViewFactory;
        if (storyGroupViewFactory != null) {
            i3 = storyGroupViewFactory.hashCode();
        }
        return hashCode6 + i3;
    }

    public final boolean isTitleVisible$storyly_release() {
        return this.isTitleVisible;
    }

    public final void setGroupViewFactory$storyly_release(@Nullable StoryGroupViewFactory storyGroupViewFactory) {
        this.groupViewFactory = storyGroupViewFactory;
    }

    public final void setIconBackgroundColor$storyly_release(int i3) {
        this.iconBackgroundColor = i3;
    }

    public final void setIconBorderAnimation$storyly_release(@NotNull StoryGroupAnimation storyGroupAnimation) {
        Intrinsics.checkNotNullParameter(storyGroupAnimation, "<set-?>");
        this.iconBorderAnimation = storyGroupAnimation;
    }

    public final void setIconBorderColorNotSeen$storyly_release(@NotNull List<Integer> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.iconBorderColorNotSeen = list;
    }

    public final void setIconBorderColorSeen$storyly_release(@NotNull List<Integer> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.iconBorderColorSeen = list;
    }

    public final void setIconCornerRadius$storyly_release(int i3) {
        this.iconCornerRadius = i3;
    }

    public final void setIconThematicImageLabel$storyly_release(@Nullable String str) {
        this.iconThematicImageLabel = str;
    }

    public final void setPinIconColor$storyly_release(int i3) {
        this.pinIconColor = i3;
    }

    public final void setSize$storyly_release(@NotNull StoryGroupSize storyGroupSize) {
        Intrinsics.checkNotNullParameter(storyGroupSize, "<set-?>");
        this.size = storyGroupSize;
    }

    public final void setTitleLineCount$storyly_release(@Nullable Integer num) {
        this.titleLineCount = num;
    }

    public final void setTitleMaxLineCount$storyly_release(@Nullable Integer num) {
        this.titleMaxLineCount = num;
    }

    public final void setTitleMinLineCount$storyly_release(@Nullable Integer num) {
        this.titleMinLineCount = num;
    }

    public final void setTitleNotSeenColor$storyly_release(int i3) {
        this.titleNotSeenColor = i3;
    }

    public final void setTitleSeenColor$storyly_release(int i3) {
        this.titleSeenColor = i3;
    }

    public final void setTitleTypeface$storyly_release(@NotNull Typeface typeface) {
        Intrinsics.checkNotNullParameter(typeface, "<set-?>");
        this.titleTypeface = typeface;
    }

    public final void setTitleVisible$storyly_release(boolean z2) {
        this.isTitleVisible = z2;
    }

    @NotNull
    public String toString() {
        return "StorylyStoryGroupStyling(iconBorderColorSeen=" + this.iconBorderColorSeen + ", iconBorderColorNotSeen=" + this.iconBorderColorNotSeen + ", iconBackgroundColor=" + this.iconBackgroundColor + ", _iconHeight=" + this._iconHeight + ", _iconWidth=" + this._iconWidth + ", iconCornerRadius=" + this.iconCornerRadius + ", iconThematicImageLabel=" + this.iconThematicImageLabel + ", iconBorderAnimation=" + this.iconBorderAnimation + ", pinIconColor=" + this.pinIconColor + ", titleSeenColor=" + this.titleSeenColor + ", titleNotSeenColor=" + this.titleNotSeenColor + ", titleTypeface=" + this.titleTypeface + ", _titleTextSize=" + this._titleTextSize + ", titleLineCount=" + this.titleLineCount + ", titleMinLineCount=" + this.titleMinLineCount + ", titleMaxLineCount=" + this.titleMaxLineCount + ", isTitleVisible=" + this.isTitleVisible + ", size=" + this.size + ", groupViewFactory=" + this.groupViewFactory + ')';
    }
}
