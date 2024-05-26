package com.br.barbershop.repository;

import com.br.barbershop.model.DTO.barber.BarberWithoutUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public class CustomBarbershopRepository {

  @Autowired
  private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

  public List<BarberWithoutUser> findBarbersFromBarbershopById(UUID barbershopId) {
    String sql = "SELECT " +
      "u.id, " +
      "u.email, " +
      "u.username, " +
      "u.\"document\", " +
      "u.date_of_birth as dateOfBirth " +
      "FROM barbershop bs " +
      "JOIN barber b ON bs.id = b.barbershop_id " +
      "JOIN \"user\" u ON b.user_id = u.id " +
      "JOIN user_role ur ON u.id = ur.user_id " +
      "JOIN role r ON ur.role_id = r.id " +
      "WHERE bs.id = :barbershopId";

    MapSqlParameterSource params = new MapSqlParameterSource();
    params.addValue("barbershopId", barbershopId);

    return namedParameterJdbcTemplate.query(
      sql,
      params,
      (rs, rowNum) -> new BarberWithoutUser(
        rs.getObject("id", UUID.class),
        rs.getString("email"),
        rs.getString("username"),
        rs.getString("document"),
        rs.getObject("dateOfBirth", LocalDate.class)
      )
    );
  }

}
