//package com.yincongyang.spring.util.xml;
//
//import com.fasterxml.jackson.dataformat.xml.XmlMapper;
//
///**
// * jackson xml<-->bean解析
// * Created by yincongyang on 17/11/15.
// */
//public class JacksonUtils {
//    private static final XmlMapper xmlMapper = new XmlMapper();
//
//
//    /**
//     * 将对象转换成xml字符串
//     * @param object
//     * @return
//     * @throws Exception
//     */
//    public static String toXml(Object object) throws Exception {
//        return xmlMapper.writeValueAsString(object);
//    }
//
//    /**
//     * xml字符串转换成对象
//     * @param xml
//     * @param valueType
//     * @return
//     * @throws Exception
//     */
//    public static <T> T toObject(String xml, Class<T> valueType) throws Exception {
//        return xmlMapper.readValue(xml, valueType);
//    }
//
//    /**
//     * xml转对象(处理复杂类型对象)
//     * List<bean> : json, ArrayList.class, List.class, Bean.class
//     * Map<bean1, bean2> : json, HashMap.class, Map.class, Bean1.class, Bean2.class
//     * @param xml
//     * @param parametrized 要转换的真实类型
//     * @param parametersFor 要转换类型的类或接口
//     * @param parameterClasses 类型中的泛型类型
//     * @return
//     * @throws Exception
//     */
//    public static <T> T toObject(String xml, Class<?> parametrized, Class<?> parametersFor, Class<?>... parameterClasses) throws Exception {
//        return xmlMapper.readValue(xml, xmlMapper.getTypeFactory().constructParametrizedType(parametrized, parametersFor, parameterClasses));
//    }
//}
