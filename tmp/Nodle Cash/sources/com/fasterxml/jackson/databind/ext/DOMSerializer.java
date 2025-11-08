package com.fasterxml.jackson.databind.ext;

import androidx.camera.camera2.internal.C0118y;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonFormatVisitorWrapper;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.io.StringWriter;
import java.lang.reflect.Type;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.apache.commons.lang3.BooleanUtils;
import org.w3c.dom.Node;

public class DOMSerializer extends StdSerializer<Node> {
    protected final TransformerFactory transformerFactory;

    public DOMSerializer() {
        super(Node.class);
        try {
            TransformerFactory newInstance = TransformerFactory.newInstance();
            this.transformerFactory = newInstance;
            newInstance.setFeature("http://javax.xml.XMLConstants/feature/secure-processing", true);
        } catch (Exception e3) {
            throw new IllegalStateException(C0118y.d(e3, new StringBuilder("Could not instantiate `TransformerFactory`: ")), e3);
        }
    }

    public void acceptJsonFormatVisitor(JsonFormatVisitorWrapper jsonFormatVisitorWrapper, JavaType javaType) throws JsonMappingException {
        if (jsonFormatVisitorWrapper != null) {
            jsonFormatVisitorWrapper.expectAnyFormat(javaType);
        }
    }

    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
        return createSchemaNode("string", true);
    }

    public void serialize(Node node, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        try {
            Transformer newTransformer = this.transformerFactory.newTransformer();
            newTransformer.setOutputProperty("omit-xml-declaration", BooleanUtils.YES);
            newTransformer.setOutputProperty("indent", "no");
            StreamResult streamResult = new StreamResult(new StringWriter());
            newTransformer.transform(new DOMSource(node), streamResult);
            jsonGenerator.writeString(streamResult.getWriter().toString());
        } catch (TransformerConfigurationException e3) {
            throw new IllegalStateException("Could not create XML Transformer for writing DOM `Node` value: " + e3.getMessage(), e3);
        } catch (TransformerException e4) {
            serializerProvider.reportMappingProblem(e4, "DOM `Node` value serialization failed: %s", e4.getMessage());
        }
    }
}
