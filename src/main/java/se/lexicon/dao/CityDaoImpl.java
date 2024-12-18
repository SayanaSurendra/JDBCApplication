package se.lexicon.dao;

import se.lexicon.model.City;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static se.lexicon.db.MYSQLConnection.getConnection;

public class CityDaoImpl implements CityDao{

    @Override
    public City findById(int id) {
        String sql="select * from city where id=?";
        try(Connection connection=getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
        ){
           preparedStatement.setInt(1,id);
           try (ResultSet resultSet =preparedStatement.executeQuery();){
               if(resultSet.next()){
                   int id1=resultSet.getInt("id");
                   String name=resultSet.getString("name");
                   String countryCode=resultSet.getString("countryCode");
                   String district=resultSet.getString("district");
                   int population=resultSet.getInt("population");
                   return  new City(id1,name,countryCode,district,population);
               }
           }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public List<City> findByCode(String code) {
        String sql="select * from city where countryCode=?";
        List<City> cities=new ArrayList<>();
        try(Connection connection=getConnection();
            PreparedStatement preparedStatement=connection.prepareStatement(sql);
        ) {
            preparedStatement.setString(1,code);
            try (ResultSet resultSet =preparedStatement.executeQuery();){
                while(resultSet.next()){
                    int id1=resultSet.getInt("id");
                    String name=resultSet.getString("name");
                    String countryCode=resultSet.getString("countryCode");
                    String district=resultSet.getString("district");
                    int population=resultSet.getInt("population");
                    cities.add(new City(id1,name,countryCode,district,population));

                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cities;
    }

    @Override
    public List<City> findByCodeName(String name) {
        {
            String sql = "select * from city where name=?";
            List<City> cities = new ArrayList<>();
            try (Connection connection = getConnection();
                 PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ) {
                preparedStatement.setString(1, name);
                try (ResultSet resultSet = preparedStatement.executeQuery();) {
                    while (resultSet.next()) {
                        int id1 = resultSet.getInt("id");
                        String cityName = resultSet.getString("name");
                        String countryCode = resultSet.getString("countryCode");
                        String district = resultSet.getString("district");
                        int population = resultSet.getInt("population");
                        cities.add(new City(id1, name, countryCode, district, population));

                    }

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
            return cities;

        }
    }

    @Override
    public City add(City city) {
        String sql="insert into city(name,countrycode,district,population) values(?,?,?,?)";

        try(Connection connection=getConnection();
          PreparedStatement preparedStatement= connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
        ){
            preparedStatement.setString(1,city.getName());
            preparedStatement.setString(2, city.getCountryCode());
            preparedStatement.setString(3, city.getDistrict());
            preparedStatement.setInt(4,city.getPopulation());

             int affectedRows=preparedStatement.executeUpdate();
             if(affectedRows > 0){
                 try(ResultSet generatedKeys= preparedStatement.getGeneratedKeys();){
                     if(generatedKeys.next()){
                         System.out.println("generated key::::"+generatedKeys.getInt(1));
                        city.setId(generatedKeys.getInt(1));
                     }

                 }
             }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return city;
    }

    @Override
    public City update(City city) {
        return null;
    }

    @Override
    public int delete(City city) {
        return 0;
    }
}
