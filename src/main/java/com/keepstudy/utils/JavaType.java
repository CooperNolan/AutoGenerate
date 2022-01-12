package com.keepstudy.utils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class JavaType {

    public static String getJavaType(int columnType) {
        switch (columnType) {
            case java.sql.Types.CHAR:
            case java.sql.Types.VARCHAR:
            case java.sql.Types.LONGVARCHAR:
            case java.sql.Types.LONGNVARCHAR:
            case java.sql.Types.NCHAR:
            case java.sql.Types.NVARCHAR:
                return String.class.getSimpleName();
            case java.sql.Types.BINARY:
            case java.sql.Types.LONGVARBINARY:
            case java.sql.Types.VARBINARY:
                return "byte[]";
            case java.sql.Types.TINYINT:
                return Byte.class.getSimpleName();
            case java.sql.Types.BIT:
            case java.sql.Types.BOOLEAN:
                return Boolean.class.getSimpleName();
            case java.sql.Types.SMALLINT:
                return Short.class.getSimpleName();
            case java.sql.Types.INTEGER:
                return Integer.class.getSimpleName();
            case java.sql.Types.BIGINT:
                return Long.class.getSimpleName();
            case java.sql.Types.FLOAT:
                return Float.class.getSimpleName();
            case java.sql.Types.DOUBLE:
            case java.sql.Types.REAL:
                return Double.class.getSimpleName();
            case java.sql.Types.DECIMAL:
            case java.sql.Types.NUMERIC:
                return BigDecimal.class.getName();
            case java.sql.Types.DATE:
                return LocalDate.class.getName();
            case java.sql.Types.TIME:
            case java.sql.Types.TIMESTAMP:
                return LocalDateTime.class.getName();
            case java.sql.Types.JAVA_OBJECT:
            case java.sql.Types.OTHER:
                return Object.class.getSimpleName();
            default:
                break;
        }
        return "";
    }

}
