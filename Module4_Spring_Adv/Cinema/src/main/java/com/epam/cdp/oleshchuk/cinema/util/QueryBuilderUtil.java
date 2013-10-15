package com.epam.cdp.oleshchuk.cinema.util;

import com.epam.cdp.oleshchuk.cinema.model.TicketsFilterParams;
import org.apache.commons.lang3.StringUtils;

public class QueryBuilderUtil {

    public static final String createQueryWithInCause(String sqlQuery, int countOfInParams) {
        StringBuilder stringBuilder = new StringBuilder();
        int index = sqlQuery.indexOf("IN (?") + "IN (?".length();
        String createNormalCount = StringUtils.repeat(",?", countOfInParams - 1);
        stringBuilder.append(sqlQuery.substring(0, index)).append(createNormalCount).append(sqlQuery.substring(index, sqlQuery.length()));
        stringBuilder.toString();
        return stringBuilder.toString();
    }

    public static String createQueryWithFilterParams(String sql, TicketsFilterParams ticketsFilterParams) {
        StringBuilder stringBuilder = new StringBuilder(sql);
        if (ticketsFilterParams.getTitle() != null) {
            createAndConditionForString(stringBuilder, ticketsFilterParams.getTitle(), Constant.SQL__TICKET_TABLE__RAW_TITLE);
        }
        if (ticketsFilterParams.getCategory() != null) {
            createAndConditionForString(stringBuilder, ticketsFilterParams.getCategory(), Constant.SQL__TICKET_TABLE__RAW_CATEGORY);
        }
        if (ticketsFilterParams.getDateFrom() != null) {
            createAndConditionForDateFrom(stringBuilder, ticketsFilterParams.getDateFrom(), Constant.SQL__TICKET_TABLE__RAW_DATE);
        }
        if (ticketsFilterParams.getDateTo() != null) {
            createAndConditionForDateTo(stringBuilder, ticketsFilterParams.getDateTo(), Constant.SQL__TICKET_TABLE__RAW_DATE);
        }
        return stringBuilder.toString();

    }

    private static void createAndConditionForString(StringBuilder stringBuilder, String filterParam, String rawName) {
        stringBuilder.append(" AND (").append(rawName).append(" LIKE '%").append(filterParam).append("%')");
    }

    private static void createAndConditionForDateFrom(StringBuilder stringBuilder, String filterParam, String rawName) {
        stringBuilder.append(" AND (").append(rawName).append(" >= '").append(filterParam).append("')");
    }

    private static void createAndConditionForDateTo(StringBuilder stringBuilder, String filterParam, String rawName) {
        stringBuilder.append(" AND (").append(rawName).append(" <= '").append(filterParam).append("')");
    }

}
