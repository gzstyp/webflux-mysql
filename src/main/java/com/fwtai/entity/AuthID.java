package com.fwtai.entity;

import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentityGenerator;

import java.io.Serializable;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class AuthID extends IdentityGenerator{

    @Override
    public Serializable generate(final SharedSessionContractImplementor s,Object object){
        final ThreadLocalRandom random = ThreadLocalRandom.current();
        return new UUID(random.nextInt(),random.nextInt()).toString().replaceAll("-","");
    }
}