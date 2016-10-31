/****************************************************************************/
/*  File:       ParseParametersTest.java                                    */
/*  Author:     F. Georges - H2O Consulting                                 */
/*  Date:       2015-01-09                                                  */
/*  Tags:                                                                   */
/*      Copyright (c) 2015 Florent Georges (see end of file.)               */
/* ------------------------------------------------------------------------ */


package org.expath.tools.serial;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import javax.xml.namespace.QName;
import org.expath.tools.model.Element;
import org.expath.tools.model.dom.DomElement;
import org.expath.tools.serial.SerialParameters.UseChar;
import static org.testng.Assert.*;
import org.testng.annotations.Test;

/**
 * Test parsing serialization parameters from an XML element.
 * 
 * @author Florent Georges
 * @see http://www.w3.org/TR/xslt-xquery-serialization-30/#serparams-in-xdm-instance
 */
public class ParseParametersTest
{
    @Test
    public void recExample_01()
            throws Exception
    {
        Element elem = DomElement.parseString(REC_EXAMPLE_01);
        SerialParameters params = SerialParameters.parse(elem);
        assertEquals(params.getMethod(), METHOD_XML, "the method");
        assertEquals(params.getVersion(), "1.0" , "the version");
        assertEquals(params.getIndent(), Boolean.TRUE , "indent?");
    }

    @Test
    public void recExample_02()
            throws Exception
    {
        // the expected values
        Set<QName> expected = new HashSet<QName>();
        expected.add(CDATA_HEADING);
        expected.add(CDATA_FOOTNOTE);
        // run it
        Element elem = DomElement.parseString(REC_EXAMPLE_02);
        SerialParameters params = SerialParameters.parse(elem);
        Iterable<QName> elems = params.getCdataSectionElements();
        // iterate over the result
        Set<QName> actual = new HashSet<QName>();
        Iterator<QName> it = elems.iterator();
        assertTrue(it.hasNext(), "the list must not be empty");
        actual.add(it.next());
        assertTrue(it.hasNext(), "the list must have a second item");
        actual.add(it.next());
        assertFalse(it.hasNext(), "the list must not have more than 2 items");
        // compare sets
        assertSet(actual, expected, "cdata element must have 2 items");
    }

    @Test
    public void recExample_03()
            throws Exception
    {
        Element elem = DomElement.parseString(REC_EXAMPLE_03);
        SerialParameters params = SerialParameters.parse(elem);
        assertEquals(params.getMethod(), METHOD_HTML, "the html method");
    }

    @Test
    public void recExample_04()
            throws Exception
    {
        Element elem = DomElement.parseString(REC_EXAMPLE_04);
        SerialParameters params = SerialParameters.parse(elem);
        assertEquals(params.getMethod(), METHOD_JSP, "the jsp method");
        Iterable<UseChar> elems = params.getUseCharacterMaps();
        Iterator<UseChar> it = elems.iterator();
        assertTrue(it.hasNext(), "the use char map must not be empty");
        UseChar first = it.next();
        // relative order not guaranteed
        String exp_first_char  = "«";
        String exp_first_map   = "<%";
        String exp_second_char = "»";
        String exp_second_map  = "%>";
        if ( "»".equals(first.character) ) {
            exp_first_char  = "»";
            exp_first_map   = "%>";
            exp_second_char = "«";
            exp_second_map  = "<%";
        }
        assertTrue(it.hasNext(), "the use char map must have a second item");
        assertEquals(first.character, exp_first_char, "the first use char map, character");
        assertEquals(first.stringMap, exp_first_map, "the first use char map, string");
        UseChar second = it.next();
        assertFalse(it.hasNext(), "the use char map must not have more than 2 items");
        assertEquals(second.character, exp_second_char, "the first use char map, character");
        assertEquals(second.stringMap, exp_second_map, "the first use char map, string");
    }

    private void assertSet(Set<QName> actual, Set<QName> expected, String msg)
    {
        assertEquals(actual.size(), expected.size(), "Both sets must be of same size - " + msg);
        assertTrue(actual.containsAll(expected), "Actual set must contain all expected all - " + msg);
        assertTrue(expected.containsAll(actual), "Expected set must contain all actual all - " + msg);
    }

    private static final QName METHOD_XML     = new QName("xml");
    private static final QName METHOD_HTML    = new QName("html");
    private static final QName METHOD_JSP     = new QName("http://example.org/ext", "jsp");
    private static final QName CDATA_HEADING  = new QName("http://example.org/book/chapter", "heading");
    private static final QName CDATA_FOOTNOTE = new QName("http://example.org/book", "footnote");

    private static final String REC_EXAMPLE_01 =
        "<output:serialization-parameters\n" +
        "       xmlns:output='http://www.w3.org/2010/xslt-xquery-serialization'>\n" +
        "  <output:method value='xml'/>\n" +
        "  <output:version value='1.0'/>\n" +
        "  <output:indent value='yes'/>\n" +
        "</output:serialization-parameters>";

    private static final String REC_EXAMPLE_02 =
        "<output:serialization-parameters\n" +
        "       xmlns:output='http://www.w3.org/2010/xslt-xquery-serialization'\n" +
        "       xmlns:book='http://example.org/book'\n" +
        "       xmlns='http://example.org/book/chapter'>\n" +
        "  <output:cdata-section-elements value='heading book:footnote'/>\n" +
        "</output:serialization-parameters>";

    private static final String REC_EXAMPLE_03 =
        "<output:serialization-parameters\n" +
        "       xmlns:output='http://www.w3.org/2010/xslt-xquery-serialization'\n" +
        "       xmlns='http://example.org/ext'>\n" +
        "  <output:method value='html'/>\n" +
        "</output:serialization-parameters>";

    private static final String REC_EXAMPLE_04 =
        "<output:serialization-parameters\n" +
        "       xmlns:output='http://www.w3.org/2010/xslt-xquery-serialization'\n" +
        "       xmlns:ext='http://example.org/ext'>\n" +
        "  <output:method value='ext:jsp'/>\n" +
        "  <output:use-character-maps>\n" +
        "    <output:character-map character='&#xAB;' map-string='&lt;%'/>\n" +
        "    <output:character-map character='&#xBB;' map-string='%&gt;'/>\n" +
        "  </output:use-character-maps>\n" +
        "</output:serialization-parameters>";
}


/* ------------------------------------------------------------------------ */
/*  DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS COMMENT.               */
/*                                                                          */
/*  The contents of this file are subject to the Mozilla Public License     */
/*  Version 1.0 (the "License"); you may not use this file except in        */
/*  compliance with the License. You may obtain a copy of the License at    */
/*  http://www.mozilla.org/MPL/.                                            */
/*                                                                          */
/*  Software distributed under the License is distributed on an "AS IS"     */
/*  basis, WITHOUT WARRANTY OF ANY KIND, either express or implied.  See    */
/*  the License for the specific language governing rights and limitations  */
/*  under the License.                                                      */
/*                                                                          */
/*  The Original Code is: all this file.                                    */
/*                                                                          */
/*  The Initial Developer of the Original Code is Florent Georges.          */
/*                                                                          */
/*  Contributor(s): none.                                                   */
/* ------------------------------------------------------------------------ */
