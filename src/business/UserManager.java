package business;

import core.Helper;
import dao.UserDao;
import entity.User;

import java.util.ArrayList;

public class UserManager {
    private final UserDao userDao;
    public UserManager(){
    this.userDao = new UserDao();
    }
    public User getById(int id){
        return userDao.getById(id);
    }
    public ArrayList<User> searchForTable(User.Role role){
        String select = "SELECT * FROM public.user ";
        ArrayList<String> whereList = new ArrayList<>();

        if (role != null){
            whereList.add("user_role = '" + role.toString() + "'");
        }

        String whereClause = String.join(" AND ", whereList);
        String query = select + (whereList.isEmpty() ? "" : "WHERE " + whereClause);

        return this.userDao.selectByQuery(query);
    }
    public ArrayList<Object[]>getForTable(int size, ArrayList<User> users){
        ArrayList<Object[]> userList = new ArrayList<>();
        for (User user : users){
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = user.getId();
            rowObject[i++] = user.getUsername();
            rowObject[i++] = user.getRole();
            userList.add(rowObject);
        }
        return userList;
    }
    public User findByLogin(String username, String password){
        return this.userDao.findByLogin(username, password);
    }
    public ArrayList<User> findAll(){
        return this.userDao.findAll();
    }
    public boolean delete(int id){
        if (this.getById(id) == null){
            Helper.showMsg(id + " ID Kayıtlı kullanıcı bulunamadı.");
            return false;
        }
        return this.userDao.delete(id);
    }
    public boolean update(User user){
        if (this.getById(user.getId()) == null){
            Helper.showMsg(user.getId() + " ID Kayıtlı kullanıcı bulunamadı.");
            return false;
        }
        return this.userDao.update(user);
    }
    public boolean save(User user){
        if (this.getById(user.getId()) != null){
            Helper.showMsg("error");
            return false;
        }
        return this.userDao.save(user);
    }
}
