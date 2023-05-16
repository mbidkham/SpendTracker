package com.snapp.spendtracker.core.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.snapp.spendtracker.core.domain.domainevent.DomainEvent;
import lombok.Generated;

import java.util.LinkedList;
import java.util.List;

@Generated
public class DomainEntity {

    @JsonIgnore
    public List<DomainEvent<?>> events = new LinkedList<>();

}
