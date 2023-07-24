package com.example.demo.repository;

import com.example.demo.exception.DatabaseOperationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.Optional;
import java.util.concurrent.Callable;

public interface JdbcUtils {
    Logger log = LoggerFactory.getLogger(JdbcUtils.class);
    String DATABASE_ERROR_MSG = "Database error";

    default <V> V wrapException(Callable<V> callable) {
        try {
            return callable.call();
        } catch (Exception e) {
            log.error(DATABASE_ERROR_MSG, e);
            throw new DatabaseOperationException(DATABASE_ERROR_MSG);
        }
    }

    default <V> Optional<V> wrapExceptionWithOptionalResult(Callable<V> callable) {
        try {
            return Optional.ofNullable(callable.call());
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        } catch (Exception e) {
            log.error(DATABASE_ERROR_MSG, e);
            throw new DatabaseOperationException(DATABASE_ERROR_MSG);
        }
    }
}
