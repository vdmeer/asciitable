package de.vandermeer.asciitable;

import org.apache.commons.lang3.StringUtils;

/**
 * Provides non domain utility methods that deal with method arguments of type {@link String}.
 */
final class StringArgumentHelper {

    private StringArgumentHelper() {}

    /**
     * Check if the provided {@code arg} is {@code null} or empty.
     *
     * @param rawArg - May be {@code null}.
     * @return {@code true} if {@code arg} is not {@code null} and contains any character other than
     *         spaces. {@code false} otherwise.
     */
    static boolean isNotNullAndDoesNotContainSpacesOnly(String rawArg) {
        String arg = rawArg == null ? "" : rawArg;
        return StringUtils.countMatches(arg, " ") != arg.length();
    }
}
