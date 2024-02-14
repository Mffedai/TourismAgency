package dao;

import business.ReservationManager;
import business.RoomManager;
import core.Db;
import entity.Reservation;

import java.sql.*;
import java.util.ArrayList;

public class ReservationDao {
    private Connection con;
    private final RoomDao roomDao;
    public ReservationDao(){
        this.con = Db.getInstance();
        this.roomDao = new RoomDao();
    }
    public ArrayList<Reservation> findAll() {
        return this.selectByQuery("SELECT * FROM public.reservation ORDER BY reservation_id ASC");
    }
    public ArrayList<Reservation> selectByQuery(String query){
        ArrayList<Reservation> reservations = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()){
                reservations.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }
    public Reservation getById(int id){
        Reservation obj = null;
        String query = "SELECT * FROM public.reservation WHERE reservation_id = ? ";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }
    public Reservation match(ResultSet rs) throws SQLException {
        Reservation reservation = new Reservation();
        reservation.setId(rs.getInt("reservation_id"));
        reservation.setRoom(this.roomDao.getById(rs.getInt("room_id")));
        reservation.setRoom_id(rs.getInt("room_id"));
        reservation.setBook_name(rs.getString("book_name"));
        reservation.setBook_email(rs.getString("book_email"));
        reservation.setBook_phone(rs.getString("book_phone"));
        reservation.setGuest_name(rs.getString("guest_name"));
        reservation.setGuest_tc(rs.getString("guest_tc"));
        reservation.setTotal_price(rs.getInt("total_price"));
        reservation.setEntry_date(rs.getDate("entry_date").toLocalDate());
        reservation.setExit_date(rs.getDate("exit_date").toLocalDate());
        reservation.setAdultNumber(rs.getInt("adult_number"));
        reservation.setChildNumber(rs.getInt("child_number"));
        return reservation;
    }
    public boolean save(Reservation reservation){
        String query = "INSERT INTO public.reservation " +
                "(" +
                "room_id, " +
                "book_name, " +
                "book_email, " +
                "book_phone, " +
                "guest_name, " +
                "guest_tc, " +
                "total_price, " +
                "entry_date, " +
                "exit_date, " +
                "adult_number, " +
                "child_number " +
                ")" +
                " VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1,reservation.getRoom_id());
            pr.setString(2,reservation.getBook_name());
            pr.setString(3, reservation.getBook_email());
            pr.setString(4, reservation.getBook_phone());
            pr.setString(5, reservation.getGuest_name());
            pr.setString(6, reservation.getGuest_tc());
            pr.setInt(7, reservation.getTotal_price());
            pr.setDate(8, Date.valueOf(reservation.getEntry_date().toString()));
            pr.setDate(9, Date.valueOf(reservation.getExit_date().toString()));
            pr.setInt(10, reservation.getAdultNumber());
            pr.setInt(11, reservation.getChildNumber());
            return  pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean update(Reservation reservation){
        String query = "UPDATE public.reservation SET " +
                "room_id = ? ," +
                "book_name = ? , " +
                "book_email = ? , " +
                "book_phone = ? , " +
                "guest_name = ? , " +
                "guest_tc = ? , " +
                "total_price = ? , " +
                "entry_date = ? , " +
                "exit_date = ? , " +
                "adult_number = ? , " +
                "child_number = ?  " +
                "WHERE reservation_id = ? ";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1,reservation.getRoom_id());
            pr.setString(2,reservation.getBook_name());
            pr.setString(3, reservation.getBook_email());
            pr.setString(4, reservation.getBook_phone());
            pr.setString(5, reservation.getGuest_name());
            pr.setString(6, reservation.getGuest_tc());
            pr.setInt(7, reservation.getTotal_price());
            pr.setDate(8, Date.valueOf(reservation.getEntry_date().toString()));
            pr.setDate(9, Date.valueOf(reservation.getExit_date().toString()));
            pr.setInt(10, reservation.getAdultNumber());
            pr.setInt(11, reservation.getChildNumber());
            pr.setInt(12, reservation.getId());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean delete(int id){
        String query = "DELETE FROM public.reservation WHERE reservation_id = ? ";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
}
