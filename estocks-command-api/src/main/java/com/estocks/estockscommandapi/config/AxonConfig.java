package com.estocks.estockscommandapi.config;

import org.axonframework.eventsourcing.EventSourcingRepository;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.estocks.estockscommandapi.model.CompanyAggregate;


@Configuration
public class AxonConfig {
	
	@Bean
    static EventSourcingRepository<CompanyAggregate> stockAggregateEventSourcingRepository(EventStore eventStore){
        EventSourcingRepository<CompanyAggregate> repository = EventSourcingRepository.builder(CompanyAggregate.class).eventStore(eventStore).build();
        return repository;
    }

}
