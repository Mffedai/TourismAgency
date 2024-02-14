package dao;

import core.Db;
import entity.Otel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class OtelDao {
    private final Connection con;
    public OtelDao(){
        this.con = Db.getInstance();
    }
    public ArrayList<Otel> findAll(){
        return this.selectByQuery("SELECT * FROM public.otel ORDER BY otel_id ASC");
    }
    public ArrayList<Otel> selectByQuery(String query) {
        ArrayList<Otel> otels = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()){
                otels.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return otels;
    }
    public Otel getById(int id){
        Otel obj = null;
        String query = "SELECT * FROM public.otel WHERE otel_id = ? ";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs =pr.executeQuery();
            if (rs.next()){
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }
    private Otel match(ResultSet rs) throws SQLException {
        Otel otel = new Otel();
        otel.setId(rs.getInt("otel_id"));
        otel.setName(rs.getString("otel_name"));
        otel.setCity(rs.getString("otel_city"));
        otel.setRegion(rs.getString("otel_region"));
        otel.setAddress(rs.getString("otel_address"));
        otel.setEmail(rs.getString("otel_email"));
        otel.setPhone(rs.getString("otel_phone"));
        otel.setRate(Otel.Rate.valueOf(rs.getString("otel_rate")));
        otel.setOtopark(rs.getBoolean("otel_otopark"));
        otel.setWifi(rs.getBoolean("otel_wifi"));
        otel.setPool(rs.getBoolean("otel_pool"));
        otel.setFitness(rs.getBoolean("otel_fitness"));
        otel.setConcierge(rs.getBoolean("otel_concierge"));
        otel.setSpa(rs.getBoolean("otel_spa"));
        otel.setService(rs.getBoolean("otel_service"));
        return otel;
    }

    public boolean save(Otel otel){
        String query = "INSERT INTO public.otel " +
                "(" +
                "otel_name, " +
                "otel_city, " +
                "otel_region, " +
                "otel_address, " +
                "otel_email, " +
                "otel_phone, " +
                "otel_rate, " +
                "otel_otopark, " +
                "otel_wifi, " +
                "otel_pool, " +
                "otel_fitness, " +
                "otel_concierge, " +
                "otel_spa, " +
                "otel_service " +
                ")" +
                " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1,otel.getName());
            pr.setString(2,otel.getCity());
            pr.setString(3,otel.getRegion());
            pr.setString(4,otel.getAddress());
            pr.setString(5,otel.getEmail());
            pr.setString(6,otel.getPhone());
            pr.setString(7, otel.getRate().toString());
            pr.setBoolean(8,otel.isOtopark());
            pr.setBoolean(9,otel.isWifi());
            pr.setBoolean(10,otel.isPool());
            pr.setBoolean(11,otel.isFitness());
            pr.setBoolean(12,otel.isConcierge());
            pr.setBoolean(13,otel.isSpa());
            pr.setBoolean(14,otel.isService());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean delete(int otel_id){
        String query = "DELETE FROM public.otel WHERE otel_id = ? ";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, otel_id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

}
