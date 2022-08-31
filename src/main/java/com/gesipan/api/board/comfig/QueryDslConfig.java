package com.gesipan.api.board.comfig;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
public class QueryDslConfig {

    @PersistenceContext
    public EntityManager em;

    public JPAQueryFactory JpaQueryFactory() {
        return new JPAQueryFactory(em);
    }
}
