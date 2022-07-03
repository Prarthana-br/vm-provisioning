package com.ripple.vmprovisioning.mappers;

public interface VMMapper<S1, S2, D> {

    D map(S1 s1, S2 s2);
}
