package com.ycy.study4j.springboot.springmvc.util.xml;

import com.ycy.study4j.springboot.springmvc.util.exception.Exceptions;
import com.ycy.study4j.springboot.springmvc.util.reflect.Reflections;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.namespace.QName;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.util.Assert;


/**
 * jaxb xml解析工具类 Created by yincongyang on 17/11/14.
 */
public class JaxbUtils {

  private static ConcurrentMap<Class, JAXBContext> jaxbContexts = new ConcurrentHashMap<>();

  /**
   * Java Object->Xml without encoding.
   */
  public static String toXml(Object root) {
    Class clazz = Reflections.getUserClass(root);
    return toXml(root, clazz, null, true);
  }

  public static String toXml(Object root, boolean isNeedFragment) {
    Class clazz = Reflections.getUserClass(root);
    return toXml(root, clazz, null, isNeedFragment);
  }

  /**
   * Java Object->Xml with encoding.
   */
  public static String toXml(Object root, String encoding) {
    Class clazz = Reflections.getUserClass(root);
    return toXml(root, clazz, encoding, true);
  }

  /**
   * Java Object->Xml with encoding.
   */
  public static String toXml(Object root, Class clazz, String encoding, boolean isNeedFragment) {
    String retXML = "";
    try {
      StringWriter writer = new StringWriter();
      createMarshaller(clazz, encoding, isNeedFragment).marshal(root, writer);
      retXML = writer.toString().replace("\n", "");
      retXML = retXML.replace("<resData/>", "<resData></resData>");
      retXML = retXML.replace("<resDatas/>", "<resDatas></resDatas>");
      return retXML;
    } catch (JAXBException e) {
      throw Exceptions.unchecked(e);
    }
  }

  /**
   * Java Collection->Xml without encoding, 特别支持Root Element是Collection的情形.
   */
  public static String toXml(List<?> root, String rootName, boolean isNeedFragment) {
    return toXml(root, rootName, null, isNeedFragment);
  }

  /**
   * Java Collection->Xml with encoding, 特别支持Root Element是Collection的情形.
   */
  public static String toXml(List<?> root, String rootName, String encoding,
      boolean isNeedFragment) {
    String retXML = "";
    if (root.isEmpty()) {
      return "<" + rootName + "></" + rootName + ">";
    }
    try {
      CollectionWrapper wrapper = new CollectionWrapper();
      wrapper.collection = root;
      Class clazz = Reflections.getUserClass(((List) root).get(0));
      JAXBElement<CollectionWrapper> wrapperElement = new JAXBElement<CollectionWrapper>(
          new QName(rootName),
          CollectionWrapper.class, wrapper);

      StringWriter writer = new StringWriter();
      createMarshaller(clazz, encoding, isNeedFragment).marshal(wrapperElement, writer);

      retXML = writer.toString().replace("\n", "");
      return retXML;
    } catch (JAXBException e) {
      throw Exceptions.unchecked(e);
    }
  }

  /**
   * Xml->Java Object.
   */
  @SuppressWarnings("unchecked")
  public static <T> T fromXml(String xml, Class<T> clazz) {
    try {
      StringReader reader = new StringReader(xml);
      return (T) createUnmarshaller(clazz).unmarshal(reader);
    } catch (JAXBException e) {
      throw Exceptions.unchecked(e);
    }
  }


  /**
   * 创建Marshaller并设定encoding(可为null). 线程不安全，需要每次创建或pooling。
   */
  public static Marshaller createMarshaller(Class clazz, String encoding, boolean isNeedFragment) {
    try {
      JAXBContext jaxbContext = getJaxbContext(clazz);

      Marshaller marshaller = jaxbContext.createMarshaller();

      marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.FALSE);

      if (!isNeedFragment) {
        marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
      }

      if (StringUtils.isNotBlank(encoding)) {
        marshaller.setProperty(Marshaller.JAXB_ENCODING, encoding);
      }

      return marshaller;
    } catch (JAXBException e) {
      throw Exceptions.unchecked(e);
    }
  }

  /**
   * 创建UnMarshaller. 线程不安全，需要每次创建或pooling。
   */
  public static Unmarshaller createUnmarshaller(Class clazz) {
    try {
      JAXBContext jaxbContext = getJaxbContext(clazz);
      return jaxbContext.createUnmarshaller();
    } catch (JAXBException e) {
      throw Exceptions.unchecked(e);
    }
  }

  protected static JAXBContext getJaxbContext(Class clazz) {
    Assert.notNull(clazz, "'clazz' must not be null");
    JAXBContext jaxbContext = jaxbContexts.get(clazz);
    if (jaxbContext == null) {
      try {
        jaxbContext = JAXBContext.newInstance(clazz, CollectionWrapper.class);
        jaxbContexts.putIfAbsent(clazz, jaxbContext);
      } catch (JAXBException ex) {
        throw new HttpMessageConversionException(
            "Could not instantiate JAXBContext for class [" + clazz
                + "]: " + ex.getMessage(), ex);
      }
    }
    return jaxbContext;
  }

  /**
   * 封装Root Element 是 Collection的情况.
   */
  public static class CollectionWrapper {

    @XmlAnyElement
    protected Collection<?> collection;
  }
}
