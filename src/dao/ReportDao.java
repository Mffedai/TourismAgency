package dao;

import core.Db;

import java.sql.*;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class ReportDao {
    private Connection con;
    private ReservationDao reservationDao;

    public ReportDao(){
        this.con = Db.getInstance();
        this.reservationDao = new ReservationDao();
    }
    public Map<String, Integer> getCityCount(){
        String query = "SELECT otel_city, COUNT(*) AS city_count FROM public.report GROUP BY otel_city ";
        Map<String, Integer> cityCountMap = new HashMap<>();
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            ResultSet rs = pr.executeQuery();
            while (rs.next()){
                String city = rs.getString("otel_city");
                int cityCount = rs.getInt("city_count");
                cityCountMap.put(city, cityCount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cityCountMap;
    }

    public Map<String, Integer> pieGraphForFilters(
            String cityFilter,
            String otelFilter,
            String startDateFilter,
            String endDateFilter) {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT otel_city, COUNT(*) AS city_count FROM public.reservation AS t " +
                "JOIN public.room AS r ON t.room_id = r.room_id " +
                "JOIN public.otel AS o ON r.room_otel_id = o.otel_id WHERE 1=1 ");

        fieldIsEmpty(cityFilter, otelFilter, startDateFilter, endDateFilter, queryBuilder);

        queryBuilder.append(" GROUP BY otel_city");

        Map<String, Integer> cityCountMap = new HashMap<>();
        try {
            PreparedStatement pr = this.con.prepareStatement(queryBuilder.toString());

            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                String city = rs.getString("otel_city");
                int cityCount = rs.getInt("city_count");
                cityCountMap.put(city, cityCount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cityCountMap;
    }
    public Map<YearMonth, Integer> candleGraphForFilters(
            String cityFilter,
            String otelFilter,
            String startDateFilter,
            String endDateFilter) {
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append("SELECT EXTRACT(YEAR FROM t.exit_date) AS year, EXTRACT(MONTH FROM t.exit_date) AS month, COUNT(*) AS guest_count " +
                "FROM public.reservation AS t " +
                "JOIN public.room AS r ON t.room_id = r.room_id " +
                "JOIN public.otel AS o ON r.room_otel_id = o.otel_id WHERE 1=1 ");

        fieldIsEmpty(cityFilter, otelFilter, startDateFilter, endDateFilter, queryBuilder);

        queryBuilder.append(" GROUP BY EXTRACT(YEAR FROM t.exit_date), EXTRACT(MONTH FROM t.exit_date)");

        Map<YearMonth, Integer> guestCountMap = new HashMap<>();
        try {
            PreparedStatement pr = this.con.prepareStatement(queryBuilder.toString());

            ResultSet rs = pr.executeQuery();
            while (rs.next()) {
                int year = rs.getInt("year");
                int month = rs.getInt("month");
                YearMonth yearMonth = YearMonth.of(year, month);
                int guestCount = rs.getInt("guest_count");
                guestCountMap.put(yearMonth, guestCount);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return guestCountMap;
    }

    private void fieldIsEmpty(String cityFilter, String otelFilter, String startDateFilter, String endDateFilter, StringBuilder queryBuilder) {
        if (!cityFilter.isEmpty()) {
            queryBuilder.append(" AND o.otel_city ILIKE '%" + cityFilter +"%' ");
        }

        if (!otelFilter.isEmpty()) {
            queryBuilder.append(" AND o.otel_name ILIKE '%" + otelFilter + "%' ");
        }

        if (!startDateFilter.isEmpty()) {
            queryBuilder.append(" AND t.exit_date >= '" + LocalDate.parse(startDateFilter, DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "' ");
        }

        if (!endDateFilter.isEmpty()) {
            queryBuilder.append(" AND t.exit_date <= '" + LocalDate.parse(endDateFilter, DateTimeFormatter.ofPattern("dd/MM/yyyy")) + "' ");
        }
    }
}

/*      candleGraphFilter
SELECT EXTRACT(YEAR FROM t.exit_date) AS year,
EXTRACT(MONTH FROM t.exit_date) AS month, COUNT(*) AS guest_count
FROM public.reservation AS t
JOIN public.room AS r ON t.room_id = r.room_id
JOIN public.otel AS o ON r.room_otel_id = o.otel_id WHERE 1=1
 GROUP BY EXTRACT(YEAR FROM t.exit_date), EXTRACT(MONTH FROM t.exit_date)*/
