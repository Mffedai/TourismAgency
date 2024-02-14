package business;

import core.Helper;
import dao.OtelDao;
import dao.RoomDao;
import dao.SeasonDao;
import entity.Otel;
import entity.Room;
import entity.Season;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class RoomManager {
    private final RoomDao roomDao;
    private final OtelDao otelDao;
    private final SeasonDao seasonDao;
    public RoomManager(){
        this.roomDao = new RoomDao();
        this.otelDao = new OtelDao();
        this.seasonDao = new SeasonDao();
    }
    public Room getById(int id){
        return this.roomDao.getById(id);
    }
    public ArrayList<Room> findAll(){
        return this.roomDao.findAll();
    }
    public ArrayList<Object[]> getForTable(int size, ArrayList<Room> rooms){
        ArrayList<Object[]> roomList = new ArrayList<>();
        for (Room room : rooms){
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = room.getId();
            rowObject[i++] = room.getOtel().getName();
            rowObject[i++] = room.getPension().getName();
            rowObject[i++] = room.getSeason().getStrt_date().toString();
            rowObject[i++] = room.getSeason().getFnsh_date().toString();
            rowObject[i++] = room.getType();
            rowObject[i++] = room.getStok();
            rowObject[i++] = room.getBed();
            rowObject[i++] = room.getMtrsqr();
            rowObject[i++] = room.getPrc_adult();
            rowObject[i++] = room.getPrc_chld();
            rowObject[i++] = (room.isAircndtn() ? "VAR" : "YOK");
            rowObject[i++] = (room.isMinibar() ? "VAR" : "YOK");
            rowObject[i++] = (room.isTv() ? "VAR" : "YOK");
            rowObject[i++] = (room.isSafe() ? "VAR" : "YOK");
            rowObject[i++] = (room.isFridge() ? "VAR" : "YOK");
            roomList.add(rowObject);
        }
        return roomList;
    }
    public boolean save(Room room){
        if (this.getById(room.getId()) != null){
            Helper.showMsg("error");
            return false;
        }
        return this.roomDao.save(room);
    }
    public boolean delete(int id){
        if (this.getById(id) == null){
            Helper.showMsg(id + " ID Kayıtlı oda bulunamadı.");
            return false;
        }
        return this.roomDao.delete(id);
    }
    public void updateStok(Room room){
        if (this.getById(room.getId()) == null){
            Helper.showMsg(room.getStok() + " ID Kayıtlı oda bulunamadı.");
            return;
        }
        this.roomDao.updateStok(room);
    }
    public ArrayList<Room> searchForRoom(
            String otel_city,
            String otel_name,
            String strt_date,
            String fnsh_date,
            int adultNumber,
            int childNumber){

        String query = "SELECT * FROM public.room AS r " +
                "JOIN public.otel AS o ON r.room_otel_id = o.otel_id " +
                "LEFT JOIN season s ON r.room_season_id = s.season_id WHERE ";

        ArrayList<String> roomQuery = new ArrayList<>();

        strt_date = LocalDate.parse(strt_date, DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();
        fnsh_date = LocalDate.parse(fnsh_date, DateTimeFormatter.ofPattern("dd/MM/yyyy")).toString();

        roomQuery.add(" r.room_stok > " + 0);
        roomQuery.add(" AND r.room_bed >= " + (adultNumber + childNumber));

        roomQuery.add(" AND s.strt_date <= '" + strt_date + "'");
        roomQuery.add(" AND s.fnsh_date >= '" + fnsh_date + "'");

        if (otel_city != null) roomQuery.add(" AND LOWER(o.otel_city) LIKE LOWER('%" + otel_city + "%')");
        if (otel_name != null) roomQuery.add(" AND LOWER(o.otel_name) LIKE LOWER('%" + otel_name + "%')");

        query += String.join("", roomQuery);
        ArrayList<Room> resultQuery = this.roomDao.selectByQuery(query);
        return resultQuery;
    }
    public ArrayList<Room> searchForRoomOtel(String name, String otel_name){
        String query = "SELECT * FROM public.room AS r " +
                "JOIN public.otel AS o ON r.room_otel_id = o.otel_id ";

        ArrayList<String> roomQuery = new ArrayList<>();

        roomQuery.add(" WHERE r.room_stok > 0");

        if (name != null) roomQuery.add(" AND LOWER(o." + name + ") LIKE LOWER('%" + otel_name.toString() + "%')");

        query += String.join(" ", roomQuery);
        ArrayList<Room> resultQuery = this.roomDao.selectByQuery(query);
        return resultQuery;
    }
}
/*
SELECT *
FROM room r
JOIN otel o ON r.room_otel_id = o.otel_id
LEFT JOIN season s ON r.room_season_id = s.season_id
WHERE
  s.strt_date <= '02/02/2024'
  AND s.fnsh_date >= '05/04/2024'
  AND r.room_bed >= 1 + 0
   r.room_stok > 0
  AND LOWER(o.otel_city) LIKE LOWER('%ıs%')
  AND LOWER(o.otel_name) LIKE LOWER('%h%')
 */
