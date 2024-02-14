package business;

import core.Helper;
import dao.OtelDao;
import entity.Otel;

import java.util.ArrayList;

public class OtelManager {
    private final OtelDao otelDao;

    public OtelManager(){
        this.otelDao = new OtelDao();
    }
    public Otel getById(int id) {
        return this.otelDao.getById(id);
    }
    public ArrayList<Otel> findAll(){
        return this.otelDao.findAll();
    }
    public boolean save(Otel otel){
        if (this.otelDao.getById(otel.getId()) != null){
            Helper.showMsg("error");
            return false;
        }
        return this.otelDao.save(otel);
    }
   public ArrayList<Object[]> getForTable(int size, ArrayList<Otel> otels){
        ArrayList<Object[]> otelList = new ArrayList<>();
        for (Otel obj : otels){
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getName();
            rowObject[i++] = obj.getCity();
            rowObject[i++] = obj.getRegion();
            rowObject[i++] = obj.getAddress();
            rowObject[i++] = obj.getEmail();
            rowObject[i++] = obj.getPhone();
            rowObject[i++] = getStar(obj.getRate());
            rowObject[i++] = (obj.isOtopark() ? "VAR" : "YOK") ;
            rowObject[i++] = (obj.isWifi() ? "VAR" : "YOK");
            rowObject[i++] = (obj.isPool() ? "VAR" : "YOK");
            rowObject[i++] = (obj.isFitness() ? "VAR" : "YOK");
            rowObject[i++] = (obj.isConcierge() ? "VAR" : "YOK");
            rowObject[i++] = (obj.isSpa() ? "VAR" : "YOK");
            rowObject[i++] = (obj.isService() ? "VAR" : "YOK");
            otelList.add(rowObject);
        }
        return otelList;
    }
    public String getStar(Otel.Rate rate){
        String star = "";
        if(rate == Otel.Rate.ONE){
            star = "★";
        }
        if(rate == Otel.Rate.TWO){
            star = "★★";
        }
        if(rate == Otel.Rate.THREE){
            star = "★★★";
        }
        if(rate == Otel.Rate.FOUR){
            star = "★★★★";
        }
        if(rate == Otel.Rate.FIVE){
            star = "★★★★★";
        }
        return star;
    }
}
