package com.epam.kharkiv.cdp.oleshchuk.cinema.dao.jdbc.util;

import org.apache.commons.lang3.StringUtils;

public class QueryBuilderUtil {

    public static final String createQueryWithInCause(String sqlQuery, int countOfInParams) {
        StringBuilder stringBuilder = new StringBuilder();
        int index = sqlQuery.indexOf("IN (?") + "IN (?".length();
        String createNormalCount = StringUtils.repeat(",?", countOfInParams - 1);
        stringBuilder.
                append(sqlQuery.substring(0, index)).
                append(createNormalCount).
                append(sqlQuery.substring(index, sqlQuery.length()));
        return stringBuilder.toString();
    }

}
