
package modelo;
/*import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;*/
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author Irann
 */
public class VentaDAO {
    Connection con;
    ConectarBD cn = new ConectarBD();
    PreparedStatement ps;
    ResultSet rs;
    int r;

    public int RegistrarVenta(Venta v){
        String sql = "INSERT INTO ventass (cliente, vendedor, total) VALUES (?,?,?)";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, v.getCliente());
            ps.setString(2, v.getVendedor());
            ps.setDouble(3, v.getTotal());
           // ps.setString(4, v.getFecha());
            ps.execute();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
        return r;
    }
    
    public int RegistrarDetalle(Detalle Dv){
           String sql = "INSERT INTO detallesventa(cod_pro,cantidad,precio,id_venta) VALUES (?,?,?,?)";
            try {
                con = cn.getConnection();
                ps = con.prepareStatement(sql);
                ps.setString(1, Dv.getCod_pro());
                ps.setInt(2, Dv.getCantidad());
                ps.setDouble(3, Dv.getPrecio());
                ps.setInt(4, Dv.getId_venta());
                ps.execute();
            } catch (SQLException e) {
                System.out.println(e.toString());
                JOptionPane.showMessageDialog(null, "Error BD");
            }finally{
                try {
                    con.close();
                } catch (SQLException e) {
                    System.out.println(e.toString());
                }
            }
            return r;
        }

    public boolean ActualizarStock(int cant, String cod){
            String sql = "UPDATE productos SET stock = ? WHERE codigo = ?";
            try {
                con = cn.getConnection();
                ps = con.prepareStatement(sql);
                ps.setInt(1,cant);
                ps.setString(2, cod);
                ps.execute();
                return true;
            } catch (SQLException e) {
                System.out.println(e.toString());
                return false;
            }
        }
    
    public List Listarventa(){
            List<Venta> ListaVenta = new ArrayList();
            String sql = "SELECT * FROM ventass";
            try {
                con = cn.getConnection();
                ps = con.prepareStatement(sql);
                rs = ps.executeQuery();
                while (rs.next()) {
                Venta vent = new Venta();
                vent.setId(rs.getInt("id"));
                vent.setCliente(rs.getString("cliente"));
                vent.setVendedor(rs.getString("vendedor"));
                vent.setTotal(rs.getDouble("total"));

                ListaVenta.add(vent);             
                }
            }catch(SQLException e){
                System.out.println(e.toString()); 
            }
            return ListaVenta;
        }
}
