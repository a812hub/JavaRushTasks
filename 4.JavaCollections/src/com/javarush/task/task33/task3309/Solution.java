package com.javarush.task.task33.task3309;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.Attributes;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.*;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/*
Комментарий внутри xml
*/
public class Solution {
    public static String toXmlWithComment2(Object obj, String tagName, String comment) throws JAXBException, XMLStreamException, IOException, ParserConfigurationException, SAXException, TransformerException {
        StringWriter writer = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = context.createMarshaller();

        Pattern XML_CHARS = Pattern.compile("[<>&]");

        XMLInputFactory xif = XMLInputFactory.newFactory();
        XMLStreamReader xsr = xif.createXMLStreamReader(new StringReader(""));

        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        XMLStreamWriter streamWriter = factory.createXMLStreamWriter(writer);


//        marshaller.marshal(obj, new XMLWriter(writer, "UTF-8") {
//            @Override
//            public void characters(char[] ch, int start, int length) throws SAXException {
//                boolean useCData = XML_CHARS.matcher(new String(ch, start, length)).find();
//                if (useCData) {
//                    super.startCDATA();
//                }
//                super.characters(ch, start, length);
//                if (useCData) {
//                    super.endCDATA();
//                }
//            }
//        });


//        System.out.println(writer.toString());

//        String test = new String("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
//                "<first>\n" +
//                "<second>some string</second>\n" +
//                "<second>some string</second>\n" +
//                "<second><![CDATA[need CDATA because of <second/> and >]]></second>\n" +
//                "<second/>\n" +
//                "</first>");

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = dbf.newDocumentBuilder();

//        Document document = builder.parse(new InputSource(new StringReader(test)));
        Document document = builder.parse(new InputSource(new StringReader(writer.toString())));
        document.setXmlStandalone(false);

        NodeList tagElements = document.getElementsByTagName(tagName);
        for (int i = 0; i < tagElements.getLength(); i++) {
            Node node = tagElements.item(i);
            node.getParentNode().insertBefore(document.createComment(comment), node);
            System.out.println(node.getNodeType());
        }

        writer = new StringWriter();
        DOMSource domSource = new DOMSource(document);
        StreamResult result = new StreamResult(writer);
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
        transformer.transform(domSource, result);
        return writer.toString();
    }

    public static String toXmlWithComment(Object obj, String tagName, String comment) throws JAXBException, XMLStreamException, IOException, ParserConfigurationException, SAXException, TransformerException {
        StringWriter writer = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(obj.getClass());
        Marshaller marshaller = context.createMarshaller();

        XMLOutputFactory factory = XMLOutputFactory.newInstance();
        XMLStreamWriter streamWriter = factory.createXMLStreamWriter(writer);

        marshaller.marshal(obj, new DefaultHandler() {
            @Override
            public void startDocument() throws SAXException {
                try {
                    streamWriter.writeDTD("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>");
                } catch (XMLStreamException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                try {
                    if (localName.equals(tagName)) {
                        streamWriter.writeComment(comment);
                    }
                    streamWriter.writeStartElement("", localName, uri);
                    for (int i = 0; i < attributes.getLength(); i++) {
                        streamWriter.writeAttribute(attributes.getLocalName(i), attributes.getValue(i));
                    }
                } catch (XMLStreamException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void endElement(String uri, String localName, String qName) throws SAXException {
                try {
                    streamWriter.writeEndElement();
                } catch (XMLStreamException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void characters(char[] ch, int start, int length) throws SAXException {
                String text = new String(ch, start, length);
                try {
                    if (text.matches(".*[<>&'\"].*")) {
                        streamWriter.writeCData(text);
                    } else {
                        streamWriter.writeCharacters(text);
                    }
                } catch (XMLStreamException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void endDocument() throws SAXException {
                try {
                    streamWriter.writeEndDocument();
                } catch (XMLStreamException e) {
                    e.printStackTrace();
                }
            }
        });

        return writer.toString();
    }


    public static void main(String[] args) throws JAXBException, XMLStreamException, IOException, TransformerException, SAXException, ParserConfigurationException {
        First first = new First();
        first.second = new ArrayList<>();
        first.second.add("some string");
        first.second.add("need CDATA because of <second/> < and >");
        first.second.add("");


        StringWriter writer = new StringWriter();
        JAXBContext context = JAXBContext.newInstance(First.class);

        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(first, writer);
//        System.out.println(writer.toString());

        System.out.println(toXmlWithComment(first, "second", "It's a comment"));

    }

    @XmlRootElement
    static class First {
        public List<String> second;
    }

}

