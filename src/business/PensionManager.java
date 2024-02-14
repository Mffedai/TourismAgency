package business;

import core.Helper;
import dao.PensionDao;
import entity.Pension;

import java.util.ArrayList;

public class PensionManager {
    private final PensionDao pensionDao;
    public PensionManager() {
        this.pensionDao = new PensionDao();
    }
    public Pension getById(int id){
        return this.pensionDao.getById(id);
    }
    public ArrayList<Pension> findAll(){
        return this.pensionDao.findAll();
    }
    public boolean save(Pension pension){
        if (this.getById(pension.getId()) != null){
            Helper.showMsg("error");
            return false;
        }
        return this.pensionDao.save(pension);
    }
    public boolean update(Pension pension){
        if (this.getById(pension.getId()) == null){
            Helper.showMsg(pension.getId() + " ID Kayıtlı pansiyon bulunamadı.");
            return false;
        }
        return this.pensionDao.update(pension);
    }
    public boolean delete(int id){
        if (this.getById(id) == null){
            Helper.showMsg(id + "ID Kayıtlı pansiyon bulunamadı");
            return false;
        }
        return this.pensionDao.delete(id);
    }
    public ArrayList<Object[]> getForTable(int size, ArrayList<Pension> pensions){
        ArrayList<Object[]> pensionList = new ArrayList<>();
        for (Pension obj : pensions){
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            //rowObject[i++] = obj.getOtel().getId();
            rowObject[i++] = obj.getOtel().getName();
            rowObject[i++] = obj.getName();
            pensionList.add(rowObject);
        }
        return pensionList;
    }
}
