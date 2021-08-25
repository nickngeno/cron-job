package com.kimmy.cronusers.repository;

import com.kimmy.cronusers.entity.OldUser;
import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface OldUserRepository extends R2dbcRepository<OldUser, Integer> {
    Mono<Integer> findByUserId(Integer id);
}
