package br.cefetmg.inf.sigap.service;

import br.cefetmg.inf.sigap.dao.GeneralDao;

public class GeneralService {
    public static void dropTable(String sql, int id) throws Exception {
        GeneralDao.dropTable(sql, id);
    }
}
