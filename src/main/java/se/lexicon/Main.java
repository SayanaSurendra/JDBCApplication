package se.lexicon;

import se.lexicon.dao.CityDao;
import se.lexicon.dao.CityDaoImpl;
import se.lexicon.model.City;


public class Main {
    public static void main(String[] args) {
        City city=new City("ABC","NLD","AFDF",256232);

        CityDao cityDao=new CityDaoImpl();
        System.out.println(cityDao.findById(5));

        System.out.println( cityDao.findByCode("NLD"));

        System.out.println( cityDao.findByCodeName("EDE"));
        System.out.println(cityDao.add(city));

    }
}