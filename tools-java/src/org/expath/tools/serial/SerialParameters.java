/****************************************************************************/
/*  File:       SerialParameters.java                                       */
/*  Author:     F. Georges                                                  */
/*  Company:    H2O Consulting                                              */
/*  Date:       2015-01-08                                                  */
/*  Tags:                                                                   */
/*      Copyright (c) 2015 Florent Georges (see end of file.)               */
/* ------------------------------------------------------------------------ */


package org.expath.tools.serial;

import javax.xml.namespace.QName;
import org.expath.tools.ToolsException;

/**
 * A set of serialization parameters.
 * 
 * @author Florent Georges
 * @date   2015-01-08
 */
public class SerialParameters
{
    /**
     * Set an extension handler.
     */
    public void setExtension(ExtensionHandler handler)
            throws ToolsException {
        myHandler = handler;
    }

    /**
     * Get an extension output property.
     * 
     * If the property is not known by the specific implementation, it must
     * raise a technical exception.
     */
    public String getExtension(QName n)
            throws ToolsException {
        if ( myHandler == null ) {
            throw new ToolsException("No handler set for extension serialization parameters");
        }
        return myHandler.getExtension(n);
    }

    /**
     * Set an extension output property.
     * 
     * If the property is not known by the specific implementation, it must
     * raise a technical exception.
     */
    public void setExtension(QName n, String v)
            throws ToolsException {
        if ( myHandler == null ) {
            throw new ToolsException("No handler set for extension serialization parameters, name=" + n);
        }
        myHandler.setExtension(n, v);
    }

    /**
     * Get the output property {@code byte-order-mark}.
     */
    public String getByteOrderMark() {
        return myByteOrderMark;
    }

    /**
     * Set the output property {@code byte-order-mark}.
     */
    public void setByteOrderMark(String v) {
        myByteOrderMark = v;
    }

    /**
     * Get the output property {@code cdata-section-elements}.
     */
    public String getCdataSectionElements() {
        return myCdataSectionElements;
    }

    /**
     * Set the output property {@code cdata-section-elements}.
     */
    public void setCdataSectionElements(String v) {
        myCdataSectionElements = v;
    }

    /**
     * Get the output property {@code doctype-public}.
     */
    public String getDoctypePublic() {
        return myDoctypePublic;
    }

    /**
     * Set the output property {@code doctype-public}.
     */
    public void setDoctypePublic(String v) {
        myDoctypePublic = v;
    }

    /**
     * Get the output property {@code doctype-system}.
     */
    public String getDoctypeSystem() {
        return myDoctypeSystem;
    }

    /**
     * Set the output property {@code doctype-system}.
     */
    public void setDoctypeSystem(String v) {
        myDoctypeSystem = v;
    }

    /**
     * Get the output property {@code encoding}.
     */
    public String getEncoding() {
        return myEncoding;
    }

    /**
     * Set the output property {@code encoding}.
     */
    public void setEncoding(String v) {
        myEncoding = v;
    }

    /**
     * Get the output property {@code escape-uri-attributes}.
     */
    public String getEscapeUriAttributes() {
        return myEscapeUriAttributes;
    }

    /**
     * Set the output property {@code escape-uri-attributes}.
     */
    public void setEscapeUriAttributes(String v) {
        myEscapeUriAttributes = v;
    }

    /**
     * Get the output property {@code include-content-type}.
     */
    public String getIncludeContentType() {
        return myIncludeContentType;
    }

    /**
     * Set the output property {@code include-content-type}.
     */
    public void setIncludeContentType(String v) {
        myIncludeContentType = v;
    }

    /**
     * Get the output property {@code indent}.
     */
    public String getIndent() {
        return myIndent;
    }

    /**
     * Set the output property {@code indent}.
     */
    public void setIndent(String v) {
        myIndent = v;
    }

    /**
     * Get the output property {@code media-type}.
     */
    public String getMediaType() {
        return myMediaType;
    }

    /**
     * Set the output property {@code media-type}.
     */
    public void setMediaType(String v) {
        myMediaType = v;
    }

    /**
     * Get the output property {@code method}.
     */
    public String getMethod() {
        return myMethod;
    }

    /**
     * Set the output property {@code method}.
     */
    public void setMethod(String v) {
        myMethod = v;
    }

    /**
     * Get the output property {@code normalization-form}.
     */
    public String getNormalizationForm() {
        return myNormalizationForm;
    }

    /**
     * Set the output property {@code normalization-form}.
     */
    public void setNormalizationForm(String v) {
        myNormalizationForm = v;
    }

    /**
     * Get the output property {@code omit-xml-declaration}.
     */
    public String getOmitXmlDeclaration() {
        return myOmitXmlDeclaration;
    }

    /**
     * Set the output property {@code omit-xml-declaration}.
     */
    public void setOmitXmlDeclaration(String v) {
        myOmitXmlDeclaration = v;
    }

    /**
     * Get the output property {@code standalone}.
     */
    public String getStandalone() {
        return myStandalone;
    }

    /**
     * Set the output property {@code standalone}.
     */
    public void setStandalone(String v) {
        myStandalone = v;
    }

    /**
     * Get the output property {@code undeclare-prefixes}.
     */
    public String getUndeclarePrefixes() {
        return myUndeclarePrefixes;
    }

    /**
     * Set the output property {@code undeclare-prefixes}.
     */
    public void setUndeclarePrefixes(String v) {
        myUndeclarePrefixes = v;
    }

    /**
     * Get the output property {@code use-character-maps}.
     */
    public String getUseCharacterMaps() {
        return myUseCharacterMaps;
    }

    /**
     * Set the output property {@code use-character-maps}.
     */
    public void setUseCharacterMaps(String v) {
        myUseCharacterMaps = v;
    }

    /**
     * Get the output property {@code version}.
     */
    public String getVersion() {
        return myVersion;
    }

    /**
     * Set the output property {@code version}.
     */
    public void setVersion(String v) {
        myVersion = v;
    }

    private String myByteOrderMark;
    private String myCdataSectionElements;
    private String myDoctypePublic;
    private String myDoctypeSystem;
    private String myEncoding;
    private String myEscapeUriAttributes;
    private String myIncludeContentType;
    private String myIndent;
    private String myMediaType;
    private String myMethod;
    private String myNormalizationForm;
    private String myOmitXmlDeclaration;
    private String myStandalone;
    private String myUndeclarePrefixes;
    private String myUseCharacterMaps;
    private String myVersion;

    private ExtensionHandler myHandler;
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
