package com.mlzj.common.utils;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import net.sf.json.JSONSerializer;
import net.sf.json.xml.XMLSerializer;

import java.io.IOException;

/**
 * @author yhl
 * @date 2019/2/19
 */
public class XmlJsonUtils {

    private static XmlMapper xmlMapper = new XmlMapper();
    static {
        xmlMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }
    public static String json2xml(String jsonString) {

        XMLSerializer xmlSerializer = new XMLSerializer();
        xmlSerializer.setTypeHintsEnabled(false);
        String xml = xmlSerializer.write(JSONSerializer.toJSON(jsonString));
        xml = xml.replace("<o>", "").replace("</o>", "");
        xml = xml.replaceAll("\r\n", "").concat("\r\n");
        return xml;
    }

    private XmlJsonUtils() {

    }

    public static String obj2Xml(Object o) throws IOException {
        return xmlMapper.writeValueAsString(o);
    }

    public static <T> T xml2Obj(String xml, Class<T> c) throws IOException {
        return xmlMapper.readValue(xml, c);
    }
}
