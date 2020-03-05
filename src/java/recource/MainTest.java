/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package recource;

/**
 *
 * @author ITHSivju
 */
public class MainTest {
    public static void main(String[] args) {
        ResultsDaoJDBC db = new ResultsDaoJDBC();
        
        System.out.println(db.getToTen());
        
    }
}
