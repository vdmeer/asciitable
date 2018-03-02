package de.vandermeer.asciitable;

import org.junit.Assert;
import org.junit.Test;

public class StringArgumentHelperTest {

    @Test
    public void returnsFalseIfArgIsNull() {
        Assert.assertFalse(StringArgumentHelper.isNotNullAndDoesNotContainSpacesOnly(null));
    }

    @Test
    public void returnsFalseIfArgIsEmpty() {
        Assert.assertFalse(StringArgumentHelper.isNotNullAndDoesNotContainSpacesOnly(""));
    }

    @Test
    public void returnsFalseIfArgIsContainsMultipleSpaces() {
        Assert.assertFalse(StringArgumentHelper.isNotNullAndDoesNotContainSpacesOnly("   "));
    }

    @Test
    public void returnsTrueIfArgIsNotNullAndNotEmpty() {
        Assert.assertTrue(StringArgumentHelper.isNotNullAndDoesNotContainSpacesOnly("Arg"));
    }

    @Test
    public void returnsTrueIfArgIsLineFeed() {
        Assert.assertTrue(StringArgumentHelper.isNotNullAndDoesNotContainSpacesOnly("\n"));
    }
}