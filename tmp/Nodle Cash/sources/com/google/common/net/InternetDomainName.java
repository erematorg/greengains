package com.google.common.net;

import androidx.constraintlayout.core.state.b;
import com.google.common.annotations.GwtCompatible;
import com.google.common.base.Ascii;
import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.errorprone.annotations.CanIgnoreReturnValue;
import com.google.errorprone.annotations.Immutable;
import com.google.errorprone.annotations.concurrent.LazyInit;
import com.google.thirdparty.publicsuffix.PublicSuffixPatterns;
import com.google.thirdparty.publicsuffix.PublicSuffixType;
import com.reown.foundation.util.jwt.JwtUtilsKt;
import java.util.List;
import javax.annotation.CheckForNull;
import org.apache.commons.lang3.ClassUtils;

@GwtCompatible(emulated = true)
@Immutable
@ElementTypesAreNonnullByDefault
public final class InternetDomainName {
    private static final CharMatcher DASH_MATCHER;
    private static final CharMatcher DIGIT_MATCHER;
    private static final CharMatcher DOTS_MATCHER = CharMatcher.anyOf(".。．｡");
    private static final Joiner DOT_JOINER = Joiner.on((char) ClassUtils.PACKAGE_SEPARATOR_CHAR);
    private static final Splitter DOT_SPLITTER = Splitter.on((char) ClassUtils.PACKAGE_SEPARATOR_CHAR);
    private static final CharMatcher LETTER_MATCHER;
    private static final int MAX_DOMAIN_PART_LENGTH = 63;
    private static final int MAX_LENGTH = 253;
    private static final int MAX_PARTS = 127;
    private static final int NO_SUFFIX_FOUND = -1;
    private static final CharMatcher PART_CHAR_MATCHER;
    private static final int SUFFIX_NOT_INITIALIZED = -2;
    private final String name;
    private final ImmutableList<String> parts;
    @LazyInit
    private int publicSuffixIndexCache = -2;
    @LazyInit
    private int registrySuffixIndexCache = -2;

    static {
        CharMatcher anyOf = CharMatcher.anyOf("-_");
        DASH_MATCHER = anyOf;
        CharMatcher inRange = CharMatcher.inRange('0', '9');
        DIGIT_MATCHER = inRange;
        CharMatcher or = CharMatcher.inRange('a', 'z').or(CharMatcher.inRange('A', 'Z'));
        LETTER_MATCHER = or;
        PART_CHAR_MATCHER = inRange.or(or).or(anyOf);
    }

    public InternetDomainName(String str) {
        String lowerCase = Ascii.toLowerCase(DOTS_MATCHER.replaceFrom((CharSequence) str, (char) ClassUtils.PACKAGE_SEPARATOR_CHAR));
        boolean z2 = true;
        lowerCase = lowerCase.endsWith(JwtUtilsKt.JWT_DELIMITER) ? b.y(lowerCase, 1, 0) : lowerCase;
        Preconditions.checkArgument(lowerCase.length() <= MAX_LENGTH, "Domain name too long: '%s':", (Object) lowerCase);
        this.name = lowerCase;
        ImmutableList<String> copyOf = ImmutableList.copyOf(DOT_SPLITTER.split(lowerCase));
        this.parts = copyOf;
        Preconditions.checkArgument(copyOf.size() > 127 ? false : z2, "Domain has too many parts: '%s'", (Object) lowerCase);
        Preconditions.checkArgument(validateSyntax(copyOf), "Not a valid domain name: '%s'", (Object) lowerCase);
    }

    private InternetDomainName ancestor(int i3) {
        Joiner joiner = DOT_JOINER;
        ImmutableList<String> immutableList = this.parts;
        return from(joiner.join((Iterable<? extends Object>) immutableList.subList(i3, immutableList.size())));
    }

    private int findSuffixOfType(Optional<PublicSuffixType> optional) {
        int size = this.parts.size();
        for (int i3 = 0; i3 < size; i3++) {
            String join = DOT_JOINER.join((Iterable<? extends Object>) this.parts.subList(i3, size));
            if (i3 > 0 && matchesType(optional, Optional.fromNullable(PublicSuffixPatterns.UNDER.get(join)))) {
                return i3 - 1;
            }
            if (matchesType(optional, Optional.fromNullable(PublicSuffixPatterns.EXACT.get(join)))) {
                return i3;
            }
            if (PublicSuffixPatterns.EXCLUDED.containsKey(join)) {
                return i3 + 1;
            }
        }
        return -1;
    }

    @CanIgnoreReturnValue
    public static InternetDomainName from(String str) {
        return new InternetDomainName((String) Preconditions.checkNotNull(str));
    }

    public static boolean isValid(String str) {
        try {
            from(str);
            return true;
        } catch (IllegalArgumentException unused) {
            return false;
        }
    }

    private static boolean matchesType(Optional<PublicSuffixType> optional, Optional<PublicSuffixType> optional2) {
        return optional.isPresent() ? optional.equals(optional2) : optional2.isPresent();
    }

    private int publicSuffixIndex() {
        int i3 = this.publicSuffixIndexCache;
        if (i3 != -2) {
            return i3;
        }
        int findSuffixOfType = findSuffixOfType(Optional.absent());
        this.publicSuffixIndexCache = findSuffixOfType;
        return findSuffixOfType;
    }

    private int registrySuffixIndex() {
        int i3 = this.registrySuffixIndexCache;
        if (i3 != -2) {
            return i3;
        }
        int findSuffixOfType = findSuffixOfType(Optional.of(PublicSuffixType.REGISTRY));
        this.registrySuffixIndexCache = findSuffixOfType;
        return findSuffixOfType;
    }

    private static boolean validatePart(String str, boolean z2) {
        if (str.length() >= 1 && str.length() <= 63) {
            if (!PART_CHAR_MATCHER.matchesAllOf(CharMatcher.ascii().retainFrom(str))) {
                return false;
            }
            CharMatcher charMatcher = DASH_MATCHER;
            if (!charMatcher.matches(str.charAt(0)) && !charMatcher.matches(str.charAt(str.length() - 1))) {
                return !z2 || !DIGIT_MATCHER.matches(str.charAt(0));
            }
        }
        return false;
    }

    private static boolean validateSyntax(List<String> list) {
        int size = list.size() - 1;
        if (!validatePart(list.get(size), true)) {
            return false;
        }
        for (int i3 = 0; i3 < size; i3++) {
            if (!validatePart(list.get(i3), false)) {
                return false;
            }
        }
        return true;
    }

    public InternetDomainName child(String str) {
        return from(((String) Preconditions.checkNotNull(str)) + JwtUtilsKt.JWT_DELIMITER + this.name);
    }

    public boolean equals(@CheckForNull Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof InternetDomainName) {
            return this.name.equals(((InternetDomainName) obj).name);
        }
        return false;
    }

    public boolean hasParent() {
        return this.parts.size() > 1;
    }

    public boolean hasPublicSuffix() {
        return publicSuffixIndex() != -1;
    }

    public boolean hasRegistrySuffix() {
        return registrySuffixIndex() != -1;
    }

    public int hashCode() {
        return this.name.hashCode();
    }

    public boolean isPublicSuffix() {
        return publicSuffixIndex() == 0;
    }

    public boolean isRegistrySuffix() {
        return registrySuffixIndex() == 0;
    }

    public boolean isTopDomainUnderRegistrySuffix() {
        return registrySuffixIndex() == 1;
    }

    public boolean isTopPrivateDomain() {
        return publicSuffixIndex() == 1;
    }

    public boolean isUnderPublicSuffix() {
        return publicSuffixIndex() > 0;
    }

    public boolean isUnderRegistrySuffix() {
        return registrySuffixIndex() > 0;
    }

    public InternetDomainName parent() {
        Preconditions.checkState(hasParent(), "Domain '%s' has no parent", (Object) this.name);
        return ancestor(1);
    }

    public ImmutableList<String> parts() {
        return this.parts;
    }

    @CheckForNull
    public InternetDomainName publicSuffix() {
        if (hasPublicSuffix()) {
            return ancestor(publicSuffixIndex());
        }
        return null;
    }

    @CheckForNull
    public InternetDomainName registrySuffix() {
        if (hasRegistrySuffix()) {
            return ancestor(registrySuffixIndex());
        }
        return null;
    }

    public String toString() {
        return this.name;
    }

    public InternetDomainName topDomainUnderRegistrySuffix() {
        if (isTopDomainUnderRegistrySuffix()) {
            return this;
        }
        Preconditions.checkState(isUnderRegistrySuffix(), "Not under a registry suffix: %s", (Object) this.name);
        return ancestor(registrySuffixIndex() - 1);
    }

    public InternetDomainName topPrivateDomain() {
        if (isTopPrivateDomain()) {
            return this;
        }
        Preconditions.checkState(isUnderPublicSuffix(), "Not under a public suffix: %s", (Object) this.name);
        return ancestor(publicSuffixIndex() - 1);
    }
}
