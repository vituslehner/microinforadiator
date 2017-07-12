/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.service;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sociotech.urbanlifeplus.microinforadiator.model.Cachable;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * @author vituslehner 04.07.17
 */
public abstract class AbstractCacheService<C extends Cachable, Identifier> {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final TimingService timingService;
    private final List<C> cachables = new ArrayList<>();

    @Autowired
    protected AbstractCacheService(TimingService timingService) {
        this.timingService = timingService;
    }

    protected boolean addCachable(C cachable) {
        logger.debug("Adding cachable: {}", cachable);
        return cachables.add(cachable);
    }

    public Optional<C> getCachable(Identifier identifier, Predicate<C> filter) {
        Preconditions.checkNotNull(identifier, "Cachable identifier must not be null for lookup.");

        return cachables.stream().filter(filter).findFirst();
    }

    protected List<C> getAll() {
        return cachables;
    }

}
