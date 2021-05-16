package com.fwtai.repository;

import com.fwtai.entity.City;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class CityRepository{

    private ConcurrentMap<Long,City> repository = new ConcurrentHashMap<>();

    private static final AtomicLong idGenerator = new AtomicLong(0);

    public Long save(final City city) {
        final Long id = idGenerator.incrementAndGet();
        city.setId(id);
        repository.put(id,city);
        return id;
    }

    public Collection<City> findAll() {
        return repository.values();
    }

    public City findCityById(final Long id) {
        return repository.get(id);
    }

    public Long updateCity(final City city) {
        repository.put(city.getId(), city);
        return city.getId();
    }

    public Long deleteCity(final Long id) {
        repository.remove(id);
        return id;
    }
}