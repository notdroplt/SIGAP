package br.cefetmg.inf.sigap.service;

import br.cefetmg.inf.sigap.dao.GeneralDao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GeneralService {
    public static void dropTable(String sql) throws Exception {
        GeneralDao.dropTable(sql);
    }
}
