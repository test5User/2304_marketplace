package by.itclass.model.services;

import by.itclass.model.dao.TvDao;
import by.itclass.model.entities.Tv;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import static by.itclass.constants.JspConst.*;

public class TvService implements Service {
    private final TvDao dao;

    public TvService() {
        dao = new TvDao();
    }


    public List<Tv> getTvs(Map<String, String[]> params) {
        if (params.isEmpty()) {
            return dao.selectAllTv();
        }
        var vendors = params.get(VENDOR_PARAM);
        var screens = params.get(SCREEN_SIZE_PARAM);
        var from = params.get(PRICE_FROM_PARAM)[0];
        var to = params.get(PRICE_TO_PARAM)[0];
        return dao.selectFilteredTv(vendors, screens, from, to);
    }
}
