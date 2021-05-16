package com.fwtai.entity;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.UUIDGenerator;

import java.io.Serializable;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public final class CustomUUIDGenerator extends UUIDGenerator{

    @Override
    public Serializable generate(final SharedSessionContractImplementor s,Object object){
        final ThreadLocalRandom random = ThreadLocalRandom.current();
        return new UUID(random.nextInt(),random.nextInt()).toString().replaceAll("-","");
    }
}