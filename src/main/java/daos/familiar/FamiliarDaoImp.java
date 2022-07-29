package daos.familiar;

import conectar.Conexion;
import daos.paciente.PacienteDaoImp;
import dto.Tablas.FamiliarDto;
import dto.Tablas.PacienteDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FamiliarDaoImp extends FamiliarDao<FamiliarDto>{

    ArrayList<FamiliarDto> familiar;
    private final static Logger logger = LogManager.getLogger(PacienteDaoImp.class);
    @Override
    public void registrarFamiliar(String ci, String parentesco) {
        Connection conn = Conexion.getConexion();
        String query =  "insert into familiarresponsable (ci, parentesco) values " + "('" + ci + "'" + "," + "'" + parentesco + "'" +   " );";
        ResultSet res = null;
        System.out.println(query);

        try {
            java.sql.Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
            conn.close();
        } catch (SQLException e) {

            System.exit(0);
        }
    }

    /*
    @Override
    public ArrayList<FamiliarDto> getAll() {
        Connection conn = Conexion.getConexion();
        familiar = new ArrayList<>();

        // String query = "SELECT * FROM mascotas";
        String query = " select ci,parentesco from familiarresponsable;";
        ResultSet res = null;
        try {
            java.sql.Statement stmt = conn.createStatement();
            res = stmt.executeQuery(query);
        } catch (SQLException e) {
            logger.error("No puede ejecutar la consulta SQL", e);
            System.exit(0);
        }
        try {
            while (res.next()) {
                String ci = res.getString("ci");
                String parentesco= res.getString("parentesco");



                //  logger.info(ci + " "+nombre + " "+apellidoPaterno + " "+apellidoMaterno, telefono,correo);
                familiar.add(new FamiliarDto(ci ,parentesco));

            }

            conn.close();
        } catch (SQLException e) {
            logger.error("Error en el motor SQL", e);

        }

        return familiar;
    }
     */
    @Override
    public ArrayList<FamiliarDto> getAll() {
        Connection conn = Conexion.getConexion();
        familiar = new ArrayList<>();


        String query = "select r.ci ,parentesco, p.nombre || ' ' || p.appaterno  || ' ' || p.apmaterno as nombrefamiliar  from familiarresponsable r\n" +
                "join persona p on p.ci = r.ci ;";
        ResultSet res = null;
        try {
            java.sql.Statement stmt = conn.createStatement();
            res = stmt.executeQuery(query);
        } catch (SQLException e) {
            logger.error("No puede ejecutar la consulta SQL", e);
            System.exit(0);
        }
        try {
            while (res.next()) {
                String ciFamiliar= res.getString("ci");
                String parentesco= res.getString("parentesco");
                String nombreFamiliarResponsable= res.getString("nombrefamiliar");



                //  logger.info(ci + " "+nombre + " "+apellidoPaterno + " "+apellidoMaterno, telefono,correo);
                familiar.add(new FamiliarDto(ciFamiliar,nombreFamiliarResponsable,parentesco));

            }

            conn.close();
        } catch (SQLException e) {
            logger.error("Error en el motor SQL", e);

        }

        return familiar;
    }

    @Override
    public void borrarFamiliar(String registro) {
        Connection conn = Conexion.getConexion();
        String query = "Delete from familiarresponsable where ci = " + "'" + registro + "'";
        System.out.println(query);

        logger.info(query);

        try {
            java.sql.Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
            conn.close();
        } catch (SQLException e) {
            logger.error("No puede ejecutar la consulta SQL", e);
            System.exit(0);
        }
    }

    @Override
    public void actualizar(String ci, String parentesco) {
        Connection conn = Conexion.getConexion();
        String query = "UPDATE familiarresponsable set ci = "+ "'"  +ci +"'" + "," + "parentesco="  +"'"  +parentesco +"'"
                +"where ci=" + "'"+ ci + "';";

        System.out.println(query);

        logger.info(query);

        try {
            java.sql.Statement stmt = conn.createStatement();
            stmt.executeUpdate(query);
            conn.close();
        } catch (SQLException e) {
            logger.error("No puede ejecutar la consulta SQL", e);
            System.exit(0);
        }
    }



}
