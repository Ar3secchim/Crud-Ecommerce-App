package com.crud.infra.queue;

import com.crud.infra.queue.DTO.StockReservationRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Queue;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ReservationItemStockProducer {
  private final RabbitTemplate rabbitTemplate;

  private final Queue queue;

  private final ObjectMapper objectMapper;

  public void enviar(StockReservationRequest StockReservation) throws JsonProcessingException {
    String message = objectMapper.writeValueAsString(StockReservation);
    rabbitTemplate.convertSendAndReceive(queue.getName(), message);
  }
}
