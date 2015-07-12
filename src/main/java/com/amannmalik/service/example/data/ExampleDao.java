package com.amannmalik.service.example.data;

import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.enterprise.context.Dependent;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by amann.malik on 6/26/2015.
 */

@Dependent
public class ExampleDao {

    @Resource(lookup = "jboss/datasources/myDataSource")
    private DataSource ds;

    public String read() throws Exception {
        try (Connection conn = ds.getConnection()) {
            try (PreparedStatement ps = conn.prepareStatement("SELECT top(1) 'Hello World' FROM information_schema.tables")) {
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return rs.getString(1);
                    } else {
                        LoggerFactory.getLogger(ExampleDao.class).error("MSSQL information_schema.tables has no rows");
                        throw new Exception("MSSQL information_schema.tables has no rows");
                    }
                }
            }
        } catch (SQLException ex) {
            LoggerFactory.getLogger(ExampleDao.class).error("exception thrown from persistence layer", ex);
            throw ex;
        }
    }

}
