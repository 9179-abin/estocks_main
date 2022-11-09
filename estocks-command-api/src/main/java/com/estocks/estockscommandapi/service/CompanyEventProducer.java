package com.estocks.estockscommandapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.estocks.estockscommandapi.events.CompanyDeletedEvent;
import com.estocks.estockscommandapi.events.CompanyRegisteredEvent;
import com.estocks.estockscommandapi.events.StockPriceAddedEvent;

@Service
public class CompanyEventProducer {
	
	@Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
	
	public void produce(String topic, CompanyRegisteredEvent event) {
        this.kafkaTemplate.send(topic, event);
    }

    public void produceStockPriceUpdate(String topic, StockPriceAddedEvent event) {
        this.kafkaTemplate.send(topic, event);
    }
    
    public void produceDelete(String topic, CompanyDeletedEvent event) {
        this.kafkaTemplate.send(topic, event);
    }

}
