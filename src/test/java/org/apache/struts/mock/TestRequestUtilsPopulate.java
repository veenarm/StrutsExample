package org.apache.struts.mock;

import junit.framework.Test;
import junit.framework.TestSuite;
import org.apache.struts.Globals;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.mock.MockFormBean;
import org.apache.struts.mock.MockMultipartRequestHandler;
import org.apache.struts.mock.TestMockBase;
import org.apache.struts.util.RequestUtils;

import javax.servlet.ServletException;

/**
 * Unit tests for the RequestUtil's <code>populate</code> method.
 * @version $Rev$
 */
public class TestRequestUtilsPopulate extends TestMockBase {

    /**
     * Defines the testcase name for JUnit.
     *
     * @param theName the testcase's name.
     */
    public TestRequestUtilsPopulate(String theName) {
        super(theName);
    }

    /**
     * Start the tests.
     *
     * @param theArgs the arguments. Not used
     */
    public static void main(String[] theArgs) {
        junit.awtui.TestRunner.main(new String[]{TestRequestUtilsPopulate.class.getName()});
    }

    /**
     * @return a test suite (<code>TestSuite</code>) that includes all methods
     * starting with "test"
     */
    public static Test suite() {
        // All methods starting with "test" will be executed in the test suite.
        return new TestSuite(TestRequestUtilsPopulate.class);
    }

    public void setUp() {
        super.setUp();
    }

    public void tearDown() {
        super.tearDown();
    }

    /**
     * Ensure that the getMultipartRequestHandler cannot be seen in
     * a subclass of ActionForm.
     * <p>
     * The purpose of this test is to ensure that Bug #38534 is fixed.
     */
    public void testMultipartVisibility() throws Exception {

        String mockMappingName = "mockMapping";
        String stringValue = "Test";

        MockFormBean mockForm = new MockFormBean();
        ActionMapping mapping = new ActionMapping();
        mapping.setName(mockMappingName);

        // Set up the mock HttpServletRequest
        request.setMethod("POST");
        request.setContentType("multipart/form-data");
        request.setAttribute(Globals.MULTIPART_KEY, MockMultipartRequestHandler.class.getName());
        request.setAttribute(Globals.MAPPING_KEY, mapping);

        request.addParameter("stringProperty", stringValue);
        request.addParameter("multipartRequestHandler.mapping.name", "Bad");

        // Check the Mapping/ActionForm before
        assertNull("Multipart Handler already set", mockForm.getMultipartRequestHandler());
        assertEquals("Mapping name not set correctly", mockMappingName, mapping.getName());

        // Try to populate
        try {
            RequestUtils.populate(mockForm, request);
        } catch (ServletException se) {
            // Expected BeanUtils.populate() to throw a NestedNullException
            // which gets wrapped in RequestUtils in a ServletException
            assertEquals("Unexpected type of Exception thrown", "BeanUtils.populate", se.getMessage());
        }

        // Check the Mapping/ActionForm after
        assertNotNull("Multipart Handler Missing", mockForm.getMultipartRequestHandler());
        assertEquals("Mapping name has been modified", mockMappingName, mapping.getName());

    }
}
