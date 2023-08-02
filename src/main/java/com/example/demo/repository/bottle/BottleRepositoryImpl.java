package com.example.demo.repository.bottle;

import com.example.demo.model.dto.bottle.BottleReqDto;
import com.example.demo.model.dto.bottle.BottleResDto;
import com.example.demo.repository.JdbcUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class BottleRepositoryImpl implements BottleRepository, JdbcUtils {

    private final NamedParameterJdbcTemplate namedJdbcTemplate;
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Optional<BottleResDto> findById(Long id) {
        String sql = """
                SELECT b.id, b.name, b.volume, b.product_type AS productType,
                       b.price, b.producer_country AS producerCountry, b.code, b.is_deleted AS isDeleted
                  FROM bottles b
                 WHERE b.id = :id
                """;

        var params = new MapSqlParameterSource("id", id);

        return wrapExceptionWithOptionalResult(() -> namedJdbcTemplate
                .queryForObject(sql, params, new BeanPropertyRowMapper<>(BottleResDto.class)));
    }

    @Override
    public Optional<BottleResDto> findByCode(String code) {

        String sql = """
                SELECT b.id, b.name, b.volume, b.product_type as productType,
                       b.price, b.producer_country as producerCountry, b.code, b.is_deleted as isDeleted
                  FROM bottles b
                 WHERE b.code = :code
                """;

        var params = new MapSqlParameterSource("code", code);

        return wrapExceptionWithOptionalResult(() -> namedJdbcTemplate
                .queryForObject(sql, params, new BeanPropertyRowMapper<>(BottleResDto.class)));
    }

    @Override
    public Optional<BottleResDto> findByName(String name) {
        String sql = """
                SELECT b.id, b.name, b.volume, b.product_type as productType,
                       b.price, b.producer_country as producerCountry, b.code, b.is_deleted as isDeleted
                  FROM bottles b
                 WHERE b.name = :name
                """;

        var params = new MapSqlParameterSource("name", name);

        return wrapExceptionWithOptionalResult(() -> namedJdbcTemplate
                .queryForObject(sql, params, new BeanPropertyRowMapper<>(BottleResDto.class)));
    }

    //TODO:Проверить на работоспособность + output в sql postgres
    @Override
    public BottleResDto save(BottleReqDto bottleReqDto) {
        String sql = """
                INSERT INTO bottles (name, volume, product_type, price, producer_country, code, is_deleted)
                  VALUES (:name, :volume, :productType, :price, :producerCountry, :code, :isDeleted)
                 RETURNING id;
                """;

        var param = new MapSqlParameterSource()
                .addValue("name", bottleReqDto.getName())
                .addValue("volume", bottleReqDto.getVolume())
                .addValue("productType", bottleReqDto.getProductType())
                .addValue("price", bottleReqDto.getPrice())
                .addValue("producerCountry", bottleReqDto.getProducerCountry())
                .addValue("code", bottleReqDto.getCode())
                .addValue("isDeleted", bottleReqDto.getIsDeleted());
        
        Long bottleResDtoId = namedJdbcTemplate.queryForObject(sql, param, Long.class);

        return BottleResDto.builder()
                .id(bottleResDtoId)
                .name(bottleReqDto.getName())
                .volume(bottleReqDto.getVolume())
                .productType(bottleReqDto.getProductType())
                .price(bottleReqDto.getPrice())
                .producerCountry(bottleReqDto.getProducerCountry())
                .code(bottleReqDto.getCode())
                .isDeleted(bottleReqDto.getIsDeleted())
                .build();
    }

    @Transactional
    @Override
    public Optional<BottleResDto> updateById(Long id, BottleReqDto bottleReqDto) {
        String sql = """
                UPDATE bottles
                  SET name = ?, volume = ?, product_type = ?, price = ?, producer_country = ?, code = ?, is_deleted = ?
                 WHERE id = ?;
                """;
        jdbcTemplate.update(sql,
                bottleReqDto.getName(),
                bottleReqDto.getVolume(),
                bottleReqDto.getProductType(),
                bottleReqDto.getPrice(),
                bottleReqDto.getProducerCountry(),
                bottleReqDto.getCode(),
                bottleReqDto.getIsDeleted(),
                id);

        return findById(id);
    }

    @Override
    public Optional<BottleResDto> deleteById(Long id) {
        String sql = """
                Update bottles
                  SET is_deleted = true
                 WHERE id = :id;
                """;
        var params = new MapSqlParameterSource("id", id);
        namedJdbcTemplate.update(sql, params);

        return findById(id);
    }
}
