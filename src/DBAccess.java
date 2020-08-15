import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBAccess {

    private final String URL = "jdbc:mysql://localhost:3306/CR7_Hosseini?useTimezone=true&serverTimezone=UTC";
    private Connection con;
    public DBAccess() throws ClassNotFoundException, SQLException {
        java.lang.Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(URL,
                new SECRET().getUsername(),
                new SECRET().getPassword());
    }

    public List<Student> getStudentsList () throws SQLException{
        List<Student> stdList = new ArrayList<>();
        String sql = "SELECT * FROM students";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String surname = rs.getString("surname");
            String email = rs.getString("email");
            stdList.add(new Student(id, name, surname, email));
        }
        ps.close();
        return stdList;
    }


    public List<Teacher> getTeachersList () throws SQLException{
        List<Teacher> teacherList = new ArrayList<>();
        String sql = "SELECT * FROM teachers";
        PreparedStatement ps = con.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String surname = rs.getString("surname");
            String email = rs.getString("email");
            teacherList.add(new Teacher(id, name, surname, email));
        }
        ps.close();
        return teacherList;
    }



    public List<Class> getClassList () throws SQLException{
        List<Class> courseList = new ArrayList<>();
        String sql = "SELECT * FROM classes";
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            courseList.add(new Class(id, name));
        }
        pst.close();
        return courseList;
    }

    public List<String> getTeacherClasses(int input_id) throws SQLException {
        List<String> classNames = new ArrayList<>();
        String sql_classes = "SELECT classes.name FROM ongoing_classes " +
                "JOIN classes ON ongoing_classes.class_id= classes.id " +
                "WHERE ongoing_classes.teacher_id = ?";

        PreparedStatement pst = con.prepareStatement(sql_classes);
        ResultSet rs = pst.executeQuery();

        while (rs.next()) {
            String name = rs.getString("name");
            classNames.add(name);
        }

        pst.close();
        return classNames;
    }

    private String getTeacherName (int input_id) throws SQLException{
        String full_name = "No Teacher";
        String sql_name = "SELECT concat(name, ' ', surname) as full_name FROM teachers WHERE id =?";
        PreparedStatement pst = con.prepareStatement(sql_name);
        pst.setInt(1, input_id);
        ResultSet rs= pst.executeQuery();
        while (rs.next()) {
            String name = rs.getString("name");
            String surname = rs.getString("surname");
            full_name = name + " " + surname;
        }
        pst.close();
        return full_name;
    }


    public void displayTeacherClasses (int input_id) {
        List<String> classNames = new ArrayList<>();
        String name = "No Teacher";
        try {
            name = getTeacherName(input_id);
            classNames = getTeacherClasses (input_id);
        } catch (SQLException e ) {
            e.printStackTrace();
        }


        System.out.printf("%nTeacher \"%s\" teaches: %n", name);
        for (String str: classNames) {
            System.out.println(str);
        }
    }


    public void closeDB () throws SQLException{
        con.close();
    }


}
