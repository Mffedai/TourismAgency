package dao;

import core.Db;
import entity.Pension;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PensionDao {
    private final Connection con;
    private OtelDao otelDao;
    public PensionDao(){
        this.con = Db.getInstance();
        this.otelDao = new OtelDao();
    }
    public ArrayList<Pension> findAll(){
        return this.selectByQuery("SELECT * FROM public.pension ORDER BY pension_id ASC");
    }
    public ArrayList<Pension> selectByQuery(String query){
        ArrayList<Pension> pensions = new ArrayList<>();
        try {
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()){
                pensions.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pensions;
    }
    public Pension getById(int id){
        Pension obj = null;
        String query = "SELECT * FROM public.pension WHERE pension_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1,id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()){
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }
    private Pension match(ResultSet rs) throws SQLException{
        Pension pension = new Pension();
        pension.setId(rs.getInt("pension_id"));
        pension.setOtel_id(rs.getInt("pension_otel_id"));
        pension.setName(Pension.Name.valueOf(rs.getString("pension_name")));
        pension.setOtel(this.otelDao.getById(rs.getInt("pension_otel_id")));
        return pension;
    }
    public boolean save(Pension pension){
        String query = "INSERT INTO public.pension " +
                "(" +
                "pension_otel_id, " +
                "pension_name " +
                ")" +
                " VALUES(?,?)";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, pension.getOtel_id());
            pr.setString(2,pension.getName().toString());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean update(Pension pension){
        String query = "UPDATE public.pension SET " +
                "pension_otel_id = ? , " +
                "pension_name = ? " +
                "WHERE pension_id = ?";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1,pension.getOtel_id());
            pr.setString(2, pension.getName().toString());
            pr.setInt(3,pension.getId());
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public boolean delete(int pension_id){
        String query = "DELETE FROM public.pension WHERE pension_id = ? ";
        try {
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setInt(1, pension_id);
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }
    public ArrayList<Pension> getPensionsByOtelId(int otelId) {
        ArrayList<Pension> pensions = new ArrayList<>();
        String query = "SELECT * FROM public.pension WHERE pension_otel_id = ?";

        try (PreparedStatement pr = con.prepareStatement(query)) {
            pr.setInt(1, otelId);
            ResultSet rs = pr.executeQuery();

            while (rs.next()) {
                Pension pension = match(rs);
                pensions.add(pension);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pensions;
    }
}
