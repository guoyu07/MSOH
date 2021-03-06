

import businesslogic.login_bl.Login;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import util.ResultMessage;

import static junit.framework.TestCase.assertEquals;

/**
 * Login Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>十二月 24, 2016</pre>
 */
public class LoginTest {
    private Login login;

    @Before
    public void before() throws Exception {

        TestDriver testDriver = new TestDriver(IP.ip);
        testDriver.linkServer();
        login = new Login();
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: login(String ID, String password)
     */
    @Test
    public void testLogin() throws Exception {
        ResultMessage resultMessage1 = login.login("320581196912223590", "123456");
        assertEquals(ResultMessage.Login_ClerkSuccess, resultMessage1);
    }

} 
