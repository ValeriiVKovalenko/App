package com.example.demo.repository.order;

import com.example.demo.exception.DatabaseOperationException;
import com.example.demo.model.dto.order.OrderInfoDto;
import com.example.demo.model.dto.order.OrderReqDto;
import com.example.demo.model.entity.Bottle;
import com.example.demo.model.entity.OrderBottle;
import com.example.demo.repository.JdbcUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Slf4j
@Repository
@RequiredArgsConstructor
public class OrderRepositoryImpl implements OrderRepository, JdbcUtils {
    private final NamedParameterJdbcTemplate namedJdbcTemplate;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Long save(OrderReqDto orderReqDto) {
        String sqlInsertOrder = """
                INSERT INTO orders (creation_date_time)
                  VALUES (:creationDateTime)
                 RETURNING id;
                """;
        var paramOrder = new MapSqlParameterSource().addValue("creationDateTime", orderReqDto.getCreationDateTime());
        Long orderId = namedJdbcTemplate.queryForObject(sqlInsertOrder, paramOrder, Long.class);

        String sqlInsertOrderBottle = """
                INSERT INTO order_bottles (order_id, bottle_id, quantity)
                 VALUES (:orderId, :bottleId, :quantity);
                """;

        for (OrderBottle orderBottle : orderReqDto.getBottles()) {
            var paramOrderBottle = new MapSqlParameterSource()
                    .addValue("orderId", orderId)
                    .addValue("bottleId", orderBottle.getBottle().getId())
                    .addValue("quantity", orderBottle.getCount());
            namedJdbcTemplate.update(sqlInsertOrderBottle, paramOrderBottle);
        }

        return orderId;
    }

    public Optional<OrderInfoDto> findById(Long id) {
        String sql = """
                SELECT o.id AS order_id, o.creation_date_time, ob.quantity,
                       b.id AS bottle_id, b.name, b.volume, b.product_type, b.price,
                       b.producer_country, b.code, b.is_deleted
                  FROM orders o
                  JOIN order_bottles ob ON o.id = ob.order_id
                  JOIN bottles b ON ob.bottle_id = b.id
                 WHERE o.id = :orderId;
                """;

        var param = new MapSqlParameterSource().addValue("orderId", id);

        final OrderInfoDto[] orderInfoDtoHolder = new OrderInfoDto[1];

        try {
            wrapExceptionWithOptionalResult(() -> {
                List<OrderBottle> orderBottles = new ArrayList<>();

                namedJdbcTemplate.query(sql, param, rs -> {
                    if (orderInfoDtoHolder[0] == null) {
                        orderInfoDtoHolder[0] = new OrderInfoDto();
                        orderInfoDtoHolder[0].setCreationDateTime(rs.getDate("creation_date_time").toLocalDate());
                    }

                    OrderBottle orderBottle = OrderBottle.builder()
                            .count(rs.getInt("quantity"))
                            .bottle(Bottle.builder()
                                    .id(rs.getLong("bottle_id"))
                                    .name(rs.getString("name"))
                                    .volume(rs.getDouble("volume"))
                                    .productType(rs.getString("product_type"))
                                    .price(rs.getBigDecimal("price"))
                                    .producerCountry(rs.getString("producer_country"))
                                    .code(rs.getString("code"))
                                    .isDeleted(rs.getBoolean("is_deleted"))
                                    .build())
                            .build();

                    orderBottles.add(orderBottle);
                });

                if (orderInfoDtoHolder[0] != null) {
                    orderInfoDtoHolder[0].setBottles(orderBottles);
                }

                return null; // The actual return value is not used in this method
            });

            return Optional.ofNullable(orderInfoDtoHolder[0]);
        } catch (DatabaseOperationException ex) {
            // Handle the exception if necessary
        }

        return Optional.empty();
    }
}
