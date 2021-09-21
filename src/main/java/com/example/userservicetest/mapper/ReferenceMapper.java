//package com.example.userservicetest.mapper;
//
//import org.mapstruct.ObjectFactory;
//import org.mapstruct.TargetType;
//import org.springframework.lang.NonNull;
//import org.springframework.stereotype.Component;
//
//import javax.persistence.EntityManager;
//import javax.persistence.PersistenceContext;
//
//@Component
//public class ReferenceMapper {
//
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    @ObjectFactory
//    public <T> T map(@NonNull final String uuid, @TargetType Class<T> type) {
//        return entityManager.getReference(type, uuid);
//    }
//}