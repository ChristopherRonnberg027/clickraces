/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recource;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Results;

/**
 *
 * @author ITHSivju
 */
public class ResultsDaoJDBC implements ResultDAO {

    Statement st;
    ResultSet rs;
    Connection conn;

    public ResultsDaoJDBC() {
        try {
            st = null;
            rs = null;
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/clickers", "root", "root");
        } catch (SQLException ex) {
            Logger.getLogger(ResultsDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void createResult(String userName) {
        try {
            PreparedStatement pst = conn.prepareStatement("INSERT INTO results (name) VALUES (?)");
            pst.setString(1, userName);
            pst.execute();
            pst.close();
        } catch (SQLException ex) {
            Logger.getLogger(ResultsDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public List<Results> getToTen() {
        try {
            List<Results> list = null;
            Results res = null;
            st = conn.createStatement();
            rs = st.executeQuery("SELECT name, COUNT(name) FROM results GROUP BY name ORDER BY COUNT(name) DESC;");
            if (rs != null) {
                while (rs.next()) {
                    res.setUserName(rs.getString("name"));
                    res.setScore(rs.getInt("COUNT(name)"));
                    list.add(res);
                }
            }
            st.close();

            return list;
        } catch (SQLException ex) {
            Logger.getLogger(ResultsDaoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
