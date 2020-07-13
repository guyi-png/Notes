package com.guyi.blob;

import com.guyi.utils.JDBCUtils;

import java.io.*;
import java.sql.*;

/**
 * 使用prepareStatement操作Blob类型的数据
 */
public class BlobTest {
    public static void main(String[] args) throws Exception{
        testInsert3();
    }

    public static void testInsert() throws Exception{
        Connection connect = JDBCUtils.getConnection();
        String sql = "INSERT INTO customer(name, email, birth, photo) "+
                "VALUES(?,?,?,?)";
        PreparedStatement ps = connect.prepareStatement(sql);
        ps.setObject(1,"大河");
        ps.setObject(2, "123456@xx.com");
        ps.setObject(3,"2007-06-04");
        // 当出现  xx  too large 的问题， 考虑修改my.ini文件max_allowed_pocket=16M
        ps.setBlob(4,new FileInputStream(new File("dahe.png")));
        ps.execute();
        JDBCUtils.closeResource(ps, connect);
    }

    // insert大量数据操作
    public static void testInsert1(){
        Connection connect = null;
        PreparedStatement ps  = null;
        try {
            connect = JDBCUtils.getConnection();
            String sql = "insert into goods(`name`) values(?)";
            ps = connect.prepareStatement(sql);
            for (int i =0; i < 20000; i++){
                ps.setString(1, "goods_"+(i+1));
                ps.execute();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(ps, connect);
        }
    }

    //对insert实现批量操作, mysql默认是关闭批处理的，在url中加入
    // ?rewriteBatchStatements=true
    public static void testInsert2(){
        Connection connect = null;
        PreparedStatement ps  = null;
        try {
            connect = JDBCUtils.getConnection();
            String sql = "insert into goods(`name`) values(?)";
            ps = connect.prepareStatement(sql);
            for (int i =0; i < 20000; i++){
                ps.setString(1, "goods_"+(i+1));
                // 积累
                ps.addBatch();
                if (i % 500 == 0){
                    // 攒下的一波一起执行
                    ps.executeBatch();
                    // 清空这波
                    ps.clearBatch();
                }
                if (i == 19999){
                    ps.executeBatch();
                    ps.clearBatch();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(ps, connect);
        }
    }

    //对上面的批量insert优化
    public static void testInsert3(){
        Connection connect = null;
        PreparedStatement ps  = null;
        try {
            connect = JDBCUtils.getConnection();
            // 关闭数据库的自动提交功能
            connect.setAutoCommit(false);

            String sql = "insert into goods(`name`) values(?)";
            ps = connect.prepareStatement(sql);
            for (int i =0; i < 200000; i++){
                ps.setString(1, "goods_"+(i+1));
                // 积累
                ps.addBatch();
                if (i % 500 == 0){
                    // 攒下的一波一起执行
                    ps.executeBatch();
                    // 清空这波
                    ps.clearBatch();
                }
                if (i == 199999){
                    ps.executeBatch();
                    ps.clearBatch();
                }
            }
            // 将所有数据一次性提交
            connect.commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(ps, connect);
        }
    }

    public static void testQuery() {
        Connection connect = null;
        PreparedStatement ps = null;
        InputStream is = null;
        FileOutputStream fos = null;
        ResultSet resultSet = null;

        try {
            connect = JDBCUtils.getConnection();
            String sql = "SELECT id, name, email, birth, photo FROM customer WHERE id = ?";
            ps = connect.prepareStatement(sql);
            ps.setInt(1, 5);
            resultSet = ps.executeQuery();// 执行查询
            if (resultSet.next()){  //如果结果集有数据
                int id = resultSet.getInt(1);
                String name = resultSet.getString(2);
                String email = resultSet.getString("email");
                Date birth = resultSet.getDate("birth");
                Customer customer = new Customer(id, name, email, birth);
                System.out.println(customer);

                Blob photo = resultSet.getBlob("photo");
                is = photo.getBinaryStream();
                fos = new FileOutputStream("dahe1.png");
                byte[] buffer = new byte[1024];
                int len;
                while ( (len = is.read(buffer)) != -1){
                    fos.write(buffer, 0, len);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.closeResource(ps,connect,resultSet);
            if (is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}

class Customer{
    private int id;
    private String name;
    private String email;
    private Date birth;

    public Customer(int id, String name, String email, Date birth){
        this.id = id;
        this.name = name;
        this.email = email;
        this.birth = birth;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", birth=" + birth +
                '}';
    }
}
