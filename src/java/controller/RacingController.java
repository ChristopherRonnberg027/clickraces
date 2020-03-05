package controller;

import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import model.User;
import recource.ResultsDaoJDBC;

@Named(value = "racingController")
@ApplicationScoped
public class RacingController {

    String name;
    private List<User> users;
    private Long clicks;
    private boolean finished;
    ResultsDaoJDBC db;
    

    public RacingController() {
        db = new ResultsDaoJDBC();
        users = new ArrayList<>();
        clicks = 0L;
        name = "";
        finished = false;
    }

    public String click() {
        boolean contain = false;
        for (User user : users) {
            if (user.getName().equals(name)) {
                contain = true;
                user.click();
            }
        }
        if (!contain) {
            users.add(new User(name, 1L));
        }
        clicks++;
        return "index2";
    }

    public Long getUsersClicks() {
        for (User user : users) {
            if (!(users.isEmpty()) && user.getName().equals(this.name)) {
                return user.getClics();
            }
        }
        return 0L;
    }

//    public String racingTrack() {
//        String retur="";
//        for (User user : users) {
//            retur+=user;
//            retur+="\n";
//        }
//        return retur;
//    }
    public String racingTrack() {
        String retur = "";
        for (User user : users) {
            if(users.isEmpty())
                return "";
            if (!finished && (user.getClics() <= user.getRange())) {
                retur += user;
                retur += "\n";
            } else {
                if(!finished) {
                    db.createResult(user.getName());
                }    
                finished=true;
                retur = "Winner is " + user.getName() 
                        + "\n"
                        + db.getToTen();
                //return retur;
            }
        }
        return retur;
    }

    public Long getClicks() {
        return clicks;
    }

    public void setClicks(Long clicks) {
        this.clicks = clicks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = fixNameLength(name, 3);
    }

    public String fixNameLength(String start, int length) {
        if (start.length() >= length) {
            return start.substring(0, length);
        } else {
            while (start.length() < length) {
                start += "_";
            }
            return start;
        }
    }

    public void resetUsers() {
        users = new ArrayList<>();
        finished = false;
    }

    public void click(String user, Long clicks) {
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

}
