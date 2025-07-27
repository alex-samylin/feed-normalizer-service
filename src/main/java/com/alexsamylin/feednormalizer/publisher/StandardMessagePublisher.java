package com.alexsamylin.feednormalizer.publisher;

import com.alexsamylin.feednormalizer.model.standard.StandardizedMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StandardMessagePublisher {

    public void publishMessage(StandardizedMessage msg) {
        log.info("Publishing standardized message: {}", msg);
    }
}
