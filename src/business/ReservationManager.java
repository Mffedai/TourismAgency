package business;

import core.Helper;
import dao.ReservationDao;
import dao.RoomDao;
import entity.Reservation;
import entity.Room;

import java.security.interfaces.RSAKey;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ReservationManager {
    private final ReservationDao reservationDao;
    private final RoomDao roomDao;
    public ReservationManager(){
        this.reservationDao = new ReservationDao();
        this.roomDao = new RoomDao();
    }
    public Reservation getById(int id){
        return this.reservationDao.getById(id);
    }
    public ArrayList<Reservation> findAll(){
        return this.reservationDao.findAll();
    }
    public ArrayList<Object[]> getForTable(int size, ArrayList<Reservation> reservations){
        ArrayList<Object[]> reservationList = new ArrayList<>();
        for (Reservation obj : reservations){
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getBook_name();
            rowObject[i++] = obj.getRoom().getOtel().getName();
            rowObject[i++] = obj.getEntry_date();
            rowObject[i++] = obj.getExit_date();
            rowObject[i++] = obj.getBook_phone();
            rowObject[i++] = obj.getAdultNumber();
            rowObject[i++] = obj.getChildNumber();
            rowObject[i++] = obj.getRoom().getPension().getName();
            rowObject[i++] = obj.getRoom().getType();
            rowObject[i++] = obj.getTotal_price();
            reservationList.add(rowObject);
        }
        return reservationList;
    }
    public boolean save(Reservation reservation){
        if (this.getById(reservation.getId()) != null){
            Helper.showMsg("error");
            return false;
        }
        return this.reservationDao.save(reservation);
    }
    public boolean delete(int id){
        if (this.getById(id) == null){
            Helper.showMsg(id + " ID Kayıtlı rezervasyon bulunamadı.");
            return false;
        }
        return this.reservationDao.delete(id);
    }
    public boolean update(Reservation reservation){
        if (this.getById(reservation.getId()) == null){
            Helper.showMsg(reservation.getId() + " ID Kayıtlı araç bulunamadı.");
            return false;
        }
        return this.reservationDao.update(reservation);
    }

}

