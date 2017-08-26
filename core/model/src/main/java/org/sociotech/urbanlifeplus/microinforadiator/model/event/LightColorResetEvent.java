/*
 * Copyright (c) 2017. Vitus Lehner. UrbanLife+. Universität der Bundeswehr München.
 */

package org.sociotech.urbanlifeplus.microinforadiator.model.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import org.sociotech.urbanlifeplus.microinforadiator.model.User;

/**
 * @author vituslehner 26.08.17
 */
public class LightColorResetEvent extends ReactorEvent {

    private final boolean doReset;

    @JsonCreator
    public LightColorResetEvent(@JsonProperty("user") User user,
                                @JsonProperty("mirId") String sourceMirId,
                                @JsonProperty("doReset") boolean doReset) {
        super(user, sourceMirId);
        this.doReset = doReset;
    }

    public boolean isDoReset() {
        return doReset;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LightColorResetEvent that = (LightColorResetEvent) o;
        return doReset == that.doReset;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(doReset);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("doReset", doReset)
                .add("user", getUser())
                .add("sourceMirId", getSourceMirId())
                .toString();
    }
}
