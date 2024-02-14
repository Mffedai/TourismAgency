package business;

import core.Helper;
import dao.SeasonDao;
import entity.Season;

import java.util.ArrayList;

public class SeasonManager {
    private final SeasonDao seasonDao;
    public SeasonManager(){
        this.seasonDao = new SeasonDao();
    }
    public Season getById(int id) {
        return this.seasonDao.getById(id);
    }
    public ArrayList<Season> findAll(){
        return this.seasonDao.findAll();
    }
    public ArrayList<Object[]> getForTable(int size, ArrayList<Season> seasons){
        ArrayList<Object[]> seasonList = new ArrayList<>();
        for (Season obj : seasons){
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            //rowObject[i++] = obj.getOtel_id();
            rowObject[i++] = obj.getOtel().getName();
            rowObject[i++] = obj.getStrt_date().toString();
            rowObject[i++] = obj.getFnsh_date().toString();
            seasonList.add(rowObject);
        }
        return seasonList;
    }
    public boolean delete(int id){
        if (this.getById(id) == null){
            Helper.showMsg(id + " ID Kayıtlı dönem bulunamadı.");
            return false;
        }
        return this.seasonDao.delete(id);
    }
    public boolean save(Season season){
        return this.seasonDao.save(season);
    }
    public ArrayList<Season> getSeasonsByOtelId(int id){
        return this.seasonDao.getSeasonsByOtelId(id);
    }
}
