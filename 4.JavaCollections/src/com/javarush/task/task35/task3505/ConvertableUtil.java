package com.javarush.task.task35.task3505;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConvertableUtil {

    public static <Key, Element extends Convertable<Key>> Map<Key, Element> convert(List<Element> list) {
        Map<Key, Element> result = new HashMap();
        for (Element element : list) {
            result.put(element.getKey(), element);
        }
        return result;
    }
}
