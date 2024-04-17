package by.itclass.model.services;

import by.itclass.model.dao.TvDao;
import by.itclass.model.entities.Tv;

import java.util.List;
import java.util.Objects;

public class TvService {
    private static TvService service;
    private TvDao dao;

    private TvService() {
        dao = TvDao.getInstance();
    }

    public static TvService getInstance() {
        if (Objects.isNull(service)) {
            service = new TvService();
        }
        return service;
    }

    public List<Tv> getAllTv() {
        return dao.selectAllTv();
    }
}
