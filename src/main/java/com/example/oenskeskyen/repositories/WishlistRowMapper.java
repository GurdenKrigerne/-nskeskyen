package com.example.oenskeskyen.repositories;

import com.example.oenskeskyen.models.WishList;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WishlistRowMapper implements RowMapper<WishList> {

    @Override
    public WishList mapRow(ResultSet rs, int rowNum) throws SQLException {
       WishList wishList = new WishList();

                wishList.setUserId(rs.getInt("wishlist_id")); // Unik Id for ønskelisten
                wishList.setTitle(rs.getString("name")); // Ønskelistens titel
                wishList.setDescription(rs.getString("description")); // Beskrivelse af ønskelistens indhold
                wishList.setUserId(rs.getInt("owner_Id")); // Hvilken bruger ejeren er


                return wishList;
    }
}
