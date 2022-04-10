import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class LivreDAO {
    public static List<Integer> partiesComposes() throws Exception {
        Statement stmt = TestJDBC.con.createStatement();
        ResultSet rs = stmt.executeQuery(
                "select p.id from partie p where id in (select inclus_dans from partie p group by inclus_dans having count(*)>1)");
        List<Integer> res = new ArrayList<Integer>();
        while (rs.next())
            res.add(rs.getInt(1));
        return res;
    }
}
