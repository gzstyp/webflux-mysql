package com.fwtai.entity;

import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.UUIDGenerator;

import java.io.Serializable;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class CustomIDGenerator extends UUIDGenerator{

    @Override
    public Serializable generate(SharedSessionContractImplementor session,Object object) throws MappingException{
        final ThreadLocalRandom random = ThreadLocalRandom.current();
        final String id = new UUID(random.nextInt(),random.nextInt()).toString().replaceAll("-","");
        if (id != null) {
            return id;
        }
        return super.generate(session, object);
    }
}