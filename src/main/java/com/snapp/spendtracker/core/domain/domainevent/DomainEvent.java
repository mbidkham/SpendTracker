package com.snapp.spendtracker.core.domain.domainevent;

import an.awesome.pipelinr.Command;

import java.time.Instant;

public interface DomainEvent<A> extends Command<A> {
    String eventId();

    String aggregateId();

    Instant when();

    Object payload();
}
