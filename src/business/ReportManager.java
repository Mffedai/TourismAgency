package business;

import dao.ReportDao;
import dao.ReservationDao;
import dao.RoomDao;
import entity.Reservation;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Map;

public class ReportManager {
    private final ReportDao reportDao;
    private final RoomDao roomDao;
    private final ReservationDao reservationDao;

    public ReportManager() {
        this.roomDao = new RoomDao();
        this.reportDao = new ReportDao();
        this.reservationDao = new ReservationDao();
    }

    public ArrayList<Object[]> getForTable(int size, ArrayList<Reservation> reservations) {
        ArrayList<Object[]> reservationList = new ArrayList<>();
        for (Reservation reservation : reservations) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = reservation.getRoom().getOtel().getName();
            rowObject[i++] = reservation.getRoom().getOtel().getCity();
            rowObject[i++] = reservation.getExit_date();
            rowObject[i++] = reservation.getRoom().getPension().getName();
            rowObject[i++] = reservation.getRoom().getType();
            rowObject[i++] = reservation.getTotal_price();
            reservationList.add(rowObject);
        }
        return reservationList;
    }

    public int total_giro(ArrayList<Reservation> reservations) {
        int sum = 0;
        for (Reservation reservation : reservations) {
            sum += reservation.getTotal_price();
        }
        return sum;
    }

    public int total_room(ArrayList<Reservation> reservations) {
        int sum = 0;
        for (Reservation reservation : reservations) {
            sum++;
        }
        return sum;
    }

    public int total_guest(ArrayList<Reservation> reservations) {
        int sum = 0;
        for (Reservation reservation : reservations) {
            sum += (reservation.getAdultNumber() + reservation.getChildNumber());
        }
        return sum;
    }

    public Map<String, Integer> getCityCount() {
        return this.reportDao.getCityCount();
    }

    public Map<String, Integer> pieGraphForFilters(
            String cityFilter,
            String otelFilter,
            String startDateFilter,
            String endDateFilter) {
        return this.reportDao.pieGraphForFilters(cityFilter, otelFilter, startDateFilter, endDateFilter);
    }
    public Map<YearMonth, Integer> candleGraphForFilters(
            String cityFilter,
            String otelFilter,
            String startDateFilter,
            String endDateFilter) {
        return this.reportDao.candleGraphForFilters(cityFilter, otelFilter, startDateFilter, endDateFilter);
    }

    public ArrayList<Reservation> searchForReservation(
            String otel_city,
            String otel_name,
            String strt_date,
            String fnsh_date) {

        String query = "SELECT * FROM public.reservation AS t " +
                "JOIN public.room AS r ON t.room_id = r.room_id " +
                "JOIN public.otel AS o ON r.room_otel_id = o.otel_id " +
                "WHERE 1=1 ";

        ArrayList<String> roomQuery = new ArrayList<>();

        if (!strt_date.isEmpty()) {
            strt_date = LocalDate.parse(strt_date, DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();
            roomQuery.add(" AND t.exit_date >= '" + strt_date + "'");
        }
        if (!fnsh_date.isEmpty()) {
            fnsh_date = LocalDate.parse(fnsh_date, DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();
            roomQuery.add(" AND t.exit_date <= '" + fnsh_date + "'");
        }

        roomQuery.add(" AND LOWER(o.otel_city) LIKE LOWER('%" + otel_city + "%')");
        roomQuery.add(" AND LOWER(o.otel_name) LIKE LOWER('%" + otel_name + "%')");
        roomQuery.add(" ORDER BY t.exit_date ASC");

        query += String.join("", roomQuery);
        ArrayList<Reservation> resultQuery = this.reservationDao.selectByQuery(query);
        return resultQuery;
    }

}
/*
 SELECT
    otel_name,
    EXTRACT(MONTH FROM reservation_date) AS month,
    SUM(total_price) AS monthly_sales
FROM
    report
WHERE
    EXTRACT(YEAR FROM reservation_date) = 2024
GROUP BY
    otel_name, EXTRACT(MONTH FROM reservation_date)
ORDER BY
    otel_name, EXTRACT(MONTH FROM reservation_date);
    */
