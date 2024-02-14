package dao;

import core.Db;
import entity.Room;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RoomDao {
    private Connection con;
    private final OtelDao otelDao;
    private final PensionDao pensionDao;
    private final SeasonDao seasonDao;
    public RoomDao(){
        this.con = Db.getInstance();
        this.otelDao = new OtelDao();
        this.pensionDao = new PensionDao();
        this.seasonDao = new SeasonDao();
    }
    public ArrayList<Room> findAll() {
        return this.selectByQuery("SELECT * FROM public.room ORDER BY room_id ASC ");
    }
    public ArrayList<Room> selectByQuery(String query){
        ArrayList<Room> rooms = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()){
                rooms.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }
    public Room getById(int id){
        Room obj = null;
        String query = "SELECT * FROM public.room WHERE room_id = ? ";
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
    public Room match(ResultSet rs) throws SQLException{
        Room room = new Room();
        room.setId(rs.getInt("room_id"));
        room.setOtel_id(rs.getInt("room_otel_id"));
        room.setPension_id(rs.getInt("room_pension_id"));
        room.setSeason_id(rs.getByte("room_season_id"));
        room.setOtel(this.otelDao.getById(rs.getInt("room_otel_id")));
        room.setPension(this.pensionDao.getById(rs.getInt("room_pension_id")));
        room.setSeason(this.seasonDao.getById(rs.getInt("room_season_id")));
        room.setType(Room.Type.valueOf(rs.getString("room_type")));
        room.setStok(rs.getInt("room_stok"));
        room.setBed(rs.getInt("room_bed"));
        room.setMtrsqr(rs.getInt("room_mtrsqr"));
        room.setPrc_adult(rs.getInt("room_prc_adult"));
        room.setPrc_chld(rs.getInt("room_prc_child"));
        room.setAircndtn(rs.getBoolean("room_aircndtn"));
        room.setMinibar(rs.getBoolean("room_minibar"));
        room.setTv(rs.getBoolean("room_tv"));
        room.setSafe(rs.getBoolean("room_safe"));
        room.setFridge(rs.getBoolean("room_fridge"));
        return room;
    }
    public boolean save(Room room){
        String query = "INSERT INTO public.room " +
                "(" +
                "room_otel_id, " +
                "room_pension_id, " +
                "room_season_id, " +
                "room_type, " +
                "room_stok, " +
                "room_bed, " +
                "room_mtrsqr, " +
                "room_prc_adult, " +
                "room_prc_child, " +
                "room_aircndtn, " +
                "room_minibar, " +
                "room_tv, " +
                "room_safe, " +
                "room_fridge " +
                ")" +
                " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1,room.getOtel_id());
            pr.setInt(2, room.getPension_id());
            pr.setInt(3, room.getSeason_id());
            pr.setString(4, room.getType().toString());
            pr.setInt(5, room.getStok());
            pr.setInt(6, room.getBed());
            pr.setInt(7, room.getMtrsqr());
            pr.setInt(8, room.getPrc_adult());
            pr.setInt(9, room.getPrc_chld());
            pr.setBoolean(10, room.isAircndtn());
            pr.setBoolean(11, room.isMinibar());
            pr.setBoolean(12, room.isTv());
            pr.setBoolean(13, room.isSafe());
            pr.setBoolean(14, room.isFridge());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean updateStok(Room room){
        String query = "UPDATE public.room SET " +
                "room_stok = ? " +
                "WHERE room_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, room.getStok());
            pr.setInt(2, room.getId());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean delete(int id){
        String query = "DELETE FROM public.room WHERE room_id = ? ";
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
