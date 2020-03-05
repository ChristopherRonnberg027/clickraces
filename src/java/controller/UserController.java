
package controller;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


@Named(value = "userController")
@SessionScoped
public class UserController implements Serializable{

    private String name;
    private Long clicks;

    
    
    public String click() {
        this.clicks++;
        return "index";
    }
    
    public UserController() {
        clicks=0L;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getClicks() {
        return clicks;
    }

    public void setClicks(Long clicks) {
        this.clicks = clicks;
    }

    
    
    @Override
    public String toString() {
        String retur = "";

        for (int i = 0; i < 100; i++) {
            if (i != clicks % 10) {
                retur += "_";
            } else {
                retur += name;
            }
        }

        return "UserController{" + "name=" + name + ", clicks=" + clicks + '}';
    }
}
