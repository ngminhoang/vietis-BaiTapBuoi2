package com.example.vietisbaitapbuoi2.utils;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface CsvColumn {
    String[] name();
    boolean required() default false;
}
