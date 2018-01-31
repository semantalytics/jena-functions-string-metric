import junit.framework.TestCase;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

private static Stardog STARDOG;
private static Server SERVER;
private static final String DB = "test";
private static int TEST_PORT = 5888;
private static final String STARDOG_HOME = System.getenv("STARDOG_HOME");
protected Connection connection;
private static final String STARDOG_LICENCE_KEY_FILE_NAME = "stardog-license-key.bin";

@RunWith(Suite.class)
@SuiteClasses({
 	AbbreviateMiddleTest.java,
	AbbreviateTest.java,
	AbbreviateWithMarkerTest.java,
	AppendIfMissingIgnoreCaseTest.java,
	AppendIfMissingTest.java,
	CapitalizeTest.java,
	CaseFormatTest.java,
	CenterTest.java,
	ChompTest.java,
	ChopTest.java,
	CommonPrefixTest.java,
	CommonSuffixTest.java,
	CompareIgnoreCaseTest.java,
	CompareTest.java,
	ContainsAnyTest.java,
	ContainsNoneTest.java,
	ContainsOnlyTest.java,
	ContainsTest.java,
	ContainsWhitespaceTest.java,
	CountMatchesTest.java,
	DefaultIfBlankTest.java,
	DefaultIfEmptyTest.java,
	DeleteWhitespaceTest.java,
	DifferenceTest.java,
	EndsWithIgnoreCase.java,
	EndsWithTest.java,
	EqualsAnyTest.java,
	EqualsIgnoreCaseTest.java,
	GetDigitsTest.java,
	IndexOfAnyButTest.java,
	IndexOfAnyTest.java,
	IndexOfDifferenceTest.java,
	IndexOfIgnoreCaseTest.java,
	IndexOfTest.java,
	InitialsTest.java,
	IsAllLowerCaseTest.java,
	IsAllUpperCaseTest.java,
	IsAnyBlankTest.java,
	IsAnyEmptyTest.java,
	IsBlankTest.java,
	IsEmptyTest.java,
	IsMixedCaseTest.java,
	IsWhitespaceTest.java,
	JoinTest.java,
	LowerCaseFullyTest.java,
	MidTest.java,
	OverlayTest.java,
	PadEndTest.java,
	PadStartTest.java,
	PrependIfMissingIgnoreCaseTest.java,
	PrependIfMissingTest.java,
	RandomTest.java,
	RepeatTest.java,
	ReverseDelimitedTest.java,
	ReverseTest.java,
	RotateTest.java,
	StringTestSuite.java,
	StripAccentsTest.java,
	TruncateTest.java,
	UncapitalizeTest.java,
	UnwrapTest.java,
	UpperCaseFullyTest.java,
	WrapIfMissingTest.java,
	WrapTest.java
})

public class StringTestSuite extends TestCase {
    
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
