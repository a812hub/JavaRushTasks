package com.javarush.task.task33.task3308;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;

/* 
Создание класса по строке xml
*/
public class Solution {
    public static void main(String[] args) throws JAXBException {

        Shop shop = new Shop();
        shop.count = 12;
        shop.profit = 1.8;
        Shop.Goods good1 =  new Shop.Goods();
        good1.names = new ArrayList<>();
        good1.names.add("1");
        good1.names.add("2");
        shop.goods = good1;


        StringWriter writer = new StringWriter();
        JAXBContext cont = JAXBContext.newInstance(Shop.class);
        Marshaller marshaller = cont.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(shop, writer);

        System.out.println(writer.toString());


        String xmlData =
                "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n" +
                        "<shop>\n" +
                        "    <goods>\n" +
                        "        <names>S1</names>\n" +
                        "        <names>S2</names>\n" +
                        "    </goods>\n" +
                        "    <count>12</count>\n" +
                        "    <profit>123.4</profit>\n" +
                        "    <secretData>String1</secretData>\n" +
                        "    <secretData>String2</secretData>\n" +
                        "    <secretData>String3</secretData>\n" +
                        "    <secretData>String4</secretData>\n" +
                        "    <secretData>String5</secretData>\n" +
                        "</shop>";

        StringReader reader = new StringReader(xmlData);

        JAXBContext context = JAXBContext.newInstance(getClassName());
        Unmarshaller unmarshaller = context.createUnmarshaller();

        Object o = unmarshaller.unmarshal(reader);

        System.out.println(o.toString());
    }

    public static Class getClassName() {
        return Shop.class;  //your class name
    }
}
