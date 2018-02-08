package com.semantalytics.stardog.kibble.strings.string;

import com.complexible.common.protocols.server.Server;
import com.complexible.common.protocols.server.ServerException;
import com.complexible.stardog.Stardog;
import com.complexible.stardog.api.Connection;
import com.complexible.stardog.api.admin.AdminConnection;
import com.complexible.stardog.api.admin.AdminConnectionConfiguration;
import com.google.common.io.Files;
import junit.framework.TestCase;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;

@RunWith(Suite.class)
@SuiteClasses({
  	TestAbbreviate.java
	TestAbbreviateMiddle.java
	TestAbbreviateWithMarker.java
	TestAppendIfMissing.java
	TestAppendIfMissingIgnoreCase.java
	TestCapitalize.java
	TestCaseFormat.java
	TestCenter.java
	TestChomp.java
	TestChop.java
	TestCommonPrefix.java
	TestCommonSuffix.java
	TestCompare.java
	TestCompareIgnoreCase.java
	TestContains.java
	TestContainsAny.java
	TestContainsIgnoreCase.java
	TestContainsNone.java
	TestContainsOnly.java
	TestContainsWhitespace.java
	TestCountMatches.java
	TestDefaultIfBlank.java
	TestDefaultIfEmpty.java
	TestDeleteWhitespace.java
	TestDifference.java
	TestDigits.java
	TestEndsWith.java
	TestEndsWithIgnoreCase.java
	TestEquals.java
	TestEqualsAny.java
	TestEqualsIgnoreCase.java
	TestIndexOf.java
	TestIndexOfAnyChar.java
	TestIndexOfAnyCharBut.java
	TestIndexOfAnyString.java
	TestIndexOfAnyStringBut.java
	TestIndexOfDifference.java
	TestIndexOfIgnoreCase.java
	TestInitials.java 	
	TestIsAllBlank.java 	
	TestIsAllLowerCase.java 
	TestIsAllUpperCase.java 
	TestIsAlpha.java 
	TestIsAlphaSpace.java 
	TestIsAlphanumeric.java 	
	TestIsAlphanumericSpace.java 
	TestIsAnyBlank.java 	
	TestIsAnyEmpty.java 	
	TestIsAsciiPrintable.java 
	TestIsBlank.java 	
	TestIsEmpty.java 	
	TestIsMixedCase.java 
	TestIsNoneBlank.java 	
	TestIsNoneEmpty.java 	
	TestIsNotBlank.java 
	TestIsNotEmpty.java 
	TestIsNumeric.java 	
	TestIsNumericSpace.java 	
	TestIsWhitespace.java 
	TestJoin.java 
	TestJoinWith.java 	
	TestLastIndexOf.java 
	TestLastIndexOfAny.java 
	TestLastIndexOfIgnoreCase.java 
	TestLastOrdinalIndexOf 
	TestLeft.java
	TestLeftPad.java 
	TestLength.java 
	TestLowerCase.java 	
	TestLowerCaseFully.java 
	TestMid.java 	
	TestNormalizeSpace.java 
	TestOrdinalIndexOf.java 
	TestOverlay.java 
	TestPadEnd.java 
	TestPadStart.java 
	TestPrependIfMissing.java 
	TestPrependIfMissingIgnoreCase.java
	TestRandom.java
	TestRemove.java 
	TestRemoveAll.java 
	TestRemoveEnd.java 
	TestRemoveEndIgnoreCase.java 
	TestRemoveFirst.java 
	TestRemoveIgnoreCase.java 	
	TestRemovePattern.java 
	TestRemoveStart.java 	
	TestRemoveStartIgnoreCase.java 
	TestRepeat.java 	
	TestReplace.java 	
	TestReplaceAll.java 
	TestReplaceChars.java 
	TestReplaceEach.java 
	TestReplaceEachRepeatedly.java
	TestReplaceFirst.java 	
	TestReplaceIgnoreCase.java 
	TestReplaceOnce.java 
	TestReplaceOnceIgnoreCase.java 
	TestReplacePattern.java 
	TestReverse.java 	
	TestReverseDelimited.java 	
	TestRight.java 	
	TestRightPad.java 
	TestRotate.java 
	TestSplit.java 
	TestSplitByCharacterType.java
	TestStartsWith.java 
	TestStartsWithAny.java
	TestStartsWithIgnoreCase.java 
	TestStrip.java 
	TestStripAccents.java 	
	TestStripAll.java 
	TestStripEnd.java 	
	TestStripStart.java 
	TestSubsringAfterLast.java 
	TestSubstring.java
	TestSubstringAfter.java 
	TestSubstringBefore.java 
	TestSubstringBeforeLast.java 
	TestSubstringBetween.java 
	TestSwapCase.java 
	TestTrim.java 
	TestTruncate.java 
	TestUncapitalize.java 
	TestUnwrap.java 	
	TestUpperCase.java 	
	TestUpperCaseFully.java 
	TestWrap.java 
	TestWrapIfMissing.java
})

public class StringTestSuite extends TestCase {

	private static Stardog STARDOG;
	private static Server SERVER;
	public static final String DB = "test";
	public static final int TEST_PORT = 5888;
	private static final String STARDOG_HOME = System.getenv("STARDOG_HOME");
	protected Connection connection;
	private static final String STARDOG_LICENCE_KEY_FILE_NAME = "stardog-license-key.bin";

    @BeforeClass
    public static void beforeClass() throws IOException, ServerException {

        final File TEST_HOME;

        TEST_HOME = Files.createTempDir();
        TEST_HOME.deleteOnExit();

        Files.copy(new File(STARDOG_HOME + "/" + STARDOG_LICENCE_KEY_FILE_NAME),
                new File(TEST_HOME, STARDOG_LICENCE_KEY_FILE_NAME));

        STARDOG = Stardog.builder().home(TEST_HOME).create();

        SERVER = STARDOG.newServer()
                //.set(ServerOptions.SECURITY_DISABLED, true)
                .bind(new InetSocketAddress("localhost", TEST_PORT))
                .start();

        final AdminConnection adminConnection = AdminConnectionConfiguration.toEmbeddedServer()
                .credentials("admin", "admin")
                .connect();

        if (adminConnection.list().contains(DB)) {
            adminConnection.drop(DB);
        }

        adminConnection.newDatabase(DB).create();
    }

    @AfterClass
    public static void afterClass() {
        if (SERVER != null) {
            SERVER.stop();
        }
        STARDOG.shutdown();
    }
}
