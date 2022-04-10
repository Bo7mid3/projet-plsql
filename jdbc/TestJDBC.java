
import java.sql.*;

public class TestJDBC {

    static Connection con;

    public static void seConnecter() throws Exception {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "system");
    }

    public static void partiesAvecDeuxSousParties() throws Exception {
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("select p.titre from partie p where id in (select inclus_dans from partie p group by inclus_dans having count(*)>1)");
        System.out.println("Parties avec 2 sous parties : ");
        while (rs.next())
            System.out.println("-" + rs.getString(1));
    }

    public static void main(String[] args) {
        try {
            seConnecter();
            System.out.println("connection établi");
            partiesAvecDeuxSousParties();
            System.out.println(LivreDAO.partiesComposes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}