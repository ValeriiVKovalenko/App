package com.example.demo.repository;

import com.example.demo.model.dto.BottleReqDto;
import com.example.demo.model.dto.BottleResDto;
import com.example.demo.model.dto.BottleSaveDto;
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
                SELECT b.id, b.name, b.volume, b.packing_type as packingType,
                       b.price, b.producer_country as producerCountry, b.code, b.is_deleted as isDeleted
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
                SELECT b.id, b.name, b.volume, b.packing_type as packingType,
                       b.price, b.producer_country as producerCountry, b.code, b.is_deleted as isDeleted
                  FROM bottles b
                 WHERE b.code = :id
                """;

        var params = new MapSqlParameterSource("code", code);

        return wrapExceptionWithOptionalResult(() -> namedJdbcTemplate
                .queryForObject(sql, params, new BeanPropertyRowMapper<>(BottleResDto.class)));
    }

    @Override
    public Optional<BottleResDto> findByName(String name) {
        String sql = """
                SELECT b.id, b.name, b.volume, b.packing_type as packingType,
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
    public BottleSaveDto createBottle(BottleReqDto bottleReqDto) {
        String sql = """
                INSERT INTO bottles (name, volume, packing_type, price, producer_country, code, is_deleted)
                VALUES (:name, :volume, :packingType, :price, :producerCountry, :code, :isDeleted);
                """;
        var param = new MapSqlParameterSource()
                .addValue("name", bottleReqDto.getName())
                .addValue("volume", bottleReqDto.getVolume())
                .addValue("packingType", bottleReqDto.getPackingType())
                .addValue("price", bottleReqDto.getPrice())
                .addValue("producerCountry", bottleReqDto.getProducerCountry())
                .addValue("code", bottleReqDto.getCode())
                .addValue("isDeleted", bottleReqDto.getIsDeleted());

        namedJdbcTemplate.update(sql, param);

        return BottleSaveDto.builder()
                .name(bottleReqDto.getName())
                .volume(bottleReqDto.getVolume())
                .packingType(bottleReqDto.getPackingType())
                .price(bottleReqDto.getPrice())
                .producerCountry(bottleReqDto.getProducerCountry())
                .code(bottleReqDto.getCode())
                .isDeleted(bottleReqDto.getIsDeleted())
                .build();
    }

    @Transactional
    @Override
    public Optional<BottleResDto> updateBottleById(Long id, BottleReqDto bottleReqDto) {
        String sql = """
                UPDATE bottles
                  SET name = ?, volume = ?, packing_type = ?, price = ?, producer_country = ?, code = ?, is_deleted = ?
                 WHERE id = ?;
                """;
        jdbcTemplate.update(sql,
                bottleReqDto.getName(),
                bottleReqDto.getVolume(),
                bottleReqDto.getPackingType(),
                bottleReqDto.getPrice(),
                bottleReqDto.getProducerCountry(),
                bottleReqDto.getCode(),
                bottleReqDto.getIsDeleted(),
                id);

        return findById(id);
    }

    @Override
    public Optional<BottleResDto> deleteBottleById(Long id) {
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
