package repositories;

import models.WishList;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WishlistRowMapper implements RowMapper<WishList> {

    @Override
    public WishList mapRow(ResultSet rs, int rowNum) throws SQLException {
       WishList wishList = new WishList();

                wishList.setUserId(rs.getInt("id")); // Unik Id for ønskelisten
                wishList.setTitle(rs.getString("title")); // Ønskelistens titel
                wishList.setDescription(rs.getString("description")); // Beskrivelse af ønskelistens indhold
                wishList.setUserId(rs.getInt("userId")); // Hvilken bruger ejeren er
                wishList.setWishes(null);

                return wishList;
    }
}
