/****************************************************************************/
/*  File:       DomElementParseTest.java                                    */
/*  Author:     F. Georges - H2O Consulting                                 */
/*  Date:       2015-01-09                                                  */
/*  Tags:                                                                   */
/*      Copyright (c) 2015 Florent Georges (see end of file.)               */
/* ------------------------------------------------------------------------ */


package org.expath.tools.model.dom;

import java.util.Iterator;
import org.expath.tools.model.Attribute;
import org.expath.tools.model.Element;
import org.expath.tools.model.Sequence;
import static org.testng.Assert.*;
import org.testng.annotations.Test;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;

/**
 * Test parsing into a {@link DomElement}.
 *
 * @author Florent Georges
 * @date   2015-01-08
 */
public class DomElementParseTest
{
    @Test
    public void parseString()
            throws Exception
    {
        String xml = "<root><hello type='global'>World!</hello></root>";
        Element elem = DomElement.parseString(xml);
        // /root name
        assertEquals(elem.getLocalName(), "root", "the root element name");
        assertEquals(elem.getNamespaceUri(), "", "the root element namespace");
        // /root/*
        Iterable<Element> children = elem.children();
        Iterator<Element> it_c = children.iterator();
        assertTrue(it_c.hasNext(), "/root/* must not be empty");
        // /root/*[1]
        Element hello = it_c.next();
        assertFalse(it_c.hasNext(), "/root/* must have only one element");
        assertEquals(hello.getLocalName(), "hello", "the hello element name");
        assertEquals(hello.getNamespaceUri(), "", "the hello element namespace");
        // /root/*[1]/@type
        assertEquals(hello.getAttribute("type"), "global", "the hello/@type value");
        // /root/*[1]/@*
        Iterable<Attribute> attrs = hello.attributes();
        Iterator<Attribute> it_a = attrs.iterator();
        assertTrue(it_a.hasNext(), "/root/*[1]/@* must not be empty");
        // /root/*[1]/@*[1]
        Attribute attr = it_a.next();
        assertFalse(it_a.hasNext(), "/root/*[1]/@* must have only one attribute");
        assertEquals(attr.getLocalName(), "type", "the type attribute name");
        assertEquals(attr.getNamespaceUri(), "", "the type attribute namespace");
        assertEquals(attr.getValue(), "global", "the type attribute value");
        // /root/*[1]/node()
        Sequence content = hello.getContent();
        assertFalse(content.isEmpty(), "/root/*[1]/node() must not be empty");
        NodeList list = ((DomSequence) content).getUnderlyingNodeList();
        assertNotNull(list, "/root/*[1]/node() underlying list must not be null");
        content.next();
        assertTrue(content.isEmpty(), "/root/*[1]/node() must have only one node");
        // /root/*[1]/node()[1]
        assertEquals(list.getLength(), 1, "length of /root/*[1]/node() must be 1");
        Node text = list.item(0);
        assertEquals(text.getNodeType(), Node.TEXT_NODE, "the text node");
        Text t = (Text) text;
        assertEquals(t.getWholeText(), "World!", "the text");
    }
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
