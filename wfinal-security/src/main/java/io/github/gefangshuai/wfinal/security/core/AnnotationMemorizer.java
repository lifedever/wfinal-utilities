package io.github.gefangshuai.wfinal.security.core;

import java.lang.annotation.Annotation;

/**
 * 注解存储器，用于存储注解及其对应的类和方法
 * <br/>
 * Created by gefangshuai on 2015/7/17.
 */
public class AnnotationMemorizer {
    private Class<Annotation> annotationClass;

    private AnnotationMemorizer (Class<Annotation> annotationClass){
        this.annotationClass = annotationClass;
    }

    public static AnnotationMemorizer newMemorizer(Class<Annotation> annotationClass) {
        return new AnnotationMemorizer(annotationClass);
    }
}
