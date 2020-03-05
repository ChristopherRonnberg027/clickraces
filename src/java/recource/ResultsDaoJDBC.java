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
import java.util.ArrayList;
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
    public String getToTen() {
            String topTen = "";
        try {
            st = conn.createStatement();
            rs = st.executeQuery("SELECT name, COUNT(name) FROM results GROUP BY name ORDER BY COUNT(name) DESC;");
                while (rs.next()) {
                    topTen += rs.getString("name");
                    topTen += "     ";
                    topTen += rs.getInt("COUNT(name)");
                    topTen += "\n";
                }
            st.close();
            return topTen;
        } catch (SQLException ex) {
        }
        return "";
    }

}
