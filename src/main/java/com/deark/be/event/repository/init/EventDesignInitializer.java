package com.deark.be.event.repository.init;

import com.deark.be.design.domain.Design;
import com.deark.be.design.repository.DesignRepository;
import com.deark.be.event.domain.Event;
import com.deark.be.event.domain.EventDesign;
import com.deark.be.event.repository.EventDesignRepository;
import com.deark.be.event.repository.EventRepository;
import com.deark.be.global.util.DummyDataInit;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Order(4)
@DummyDataInit
public class EventDesignInitializer implements ApplicationRunner {

    private final EventDesignRepository eventDesignRepository;
    private final EventRepository eventRepository;
    private final DesignRepository designRepository;

    @Override
    public void run(ApplicationArguments args) {
        if (eventDesignRepository.count() > 0) {
            log.info("[EventDesign] 더미 데이터 존재");
        } else {
            List<EventDesign> eventDesignList = new ArrayList<>();
            List<Event> events = eventRepository.findAll();
            List<Design> designs = designRepository.findAll();

            for (Event event : events) {
                int designCount = (int) (Math.random() * 2) + 2;

                for (int i = 0; i < designCount && i < designs.size(); i++) {
                    Design design = designs.get(i);

                    EventDesign eventDesign = EventDesign.builder()
                            .event(event)
                            .design(design)
                            .build();

                    eventDesignList.add(eventDesign);
                }
            }

            eventDesignRepository.saveAll(eventDesignList);
        }
    }
} 