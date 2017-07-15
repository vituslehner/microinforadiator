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
 * Abstract service that can be used to cache a certain type of element, e.g. user data or events.
 *
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

    /**
     * Add a cacheable element to the cache.
     * <p>
     * Does not check for duplicates.
     *
     * @param cachable the cacheable to add
     * @return true if addition successful
     */
    protected boolean addCachable(C cachable) {
        logger.debug("Adding cachable: {}", cachable);
        return cachables.add(cachable);
    }

    /**
     * Return the first cacheable with the given identifier and filter lambda.
     *
     * @param identifier the identifier to look out for
     * @param filter the filter to apply
     * @return the first found element
     */
    public Optional<C> getCachable(Identifier identifier, Predicate<C> filter) {
        Preconditions.checkNotNull(identifier, "Cachable identifier must not be null for lookup.");

        return cachables.stream().filter(filter).findFirst();
    }

    /**
     * Return all cachables.
     *
     * @return the cacheables
     */
    protected List<C> getAll() {
        return cachables;
    }

}
