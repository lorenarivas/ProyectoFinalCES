
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class LoginDAO {
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    ConectarBD cn = new ConectarBD();
    public Login log(String correo, String pass){
        Login lo = new Login();
        String sql = "SELECT * FROM usuarios WHERE correo =? AND pass =?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, correo);
            ps.setString(2, pass);
            rs= ps.executeQuery();
            if (rs.next()) {
                lo.setId(rs.getInt("id"));
                lo.setNombre(rs.getString("nombre"));
                lo.setCorreo(rs.getString("correo"));
                lo.setPass(rs.getString("pass"));
            JOptionPane.showMessageDialog(null, "¡Bienvenido al sistema!");   
                
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
            JOptionPane.showMessageDialog(null, "ERROR con la base de datos");
        }
        return lo;
    }
    
}
