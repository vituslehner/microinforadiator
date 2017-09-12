/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.model.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.sociotech.urbanlifeplus.microinforadiator.interfaces.light.LightSymbol;
import org.sociotech.urbanlifeplus.microinforadiator.model.User;

/**
 * @author vituslehner 26.08.17
 */
public class SymbolEvent extends ReactorEvent{

    private final LightSymbol lightSymbol;

    @JsonCreator
    public SymbolEvent(@JsonProperty("user") User user,
                       @JsonProperty("mirId") String sourceMirId,
                       @JsonProperty("lightSymbol") LightSymbol lightSymbol) {
        super(user, sourceMirId);
        this.lightSymbol = lightSymbol;
    }

    public LightSymbol getLightSymbol() {
        return lightSymbol;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SymbolEvent that = (SymbolEvent) o;
        return lightSymbol == that.lightSymbol;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(lightSymbol);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("user", getUser())
                .add("sourceMirId", getSourceMirId())
                .add("lightSymbol", lightSymbol)
                .toString();
    }
}
