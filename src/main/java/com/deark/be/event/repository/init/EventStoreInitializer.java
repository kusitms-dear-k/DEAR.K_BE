package com.deark.be.event.repository.init;

import com.deark.be.event.domain.Event;
import com.deark.be.event.domain.EventStore;
import com.deark.be.event.repository.EventRepository;
import com.deark.be.event.repository.EventStoreRepository;
import com.deark.be.global.util.DummyDataInit;
import com.deark.be.store.domain.Store;
import com.deark.be.store.repository.StoreRepository;
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
public class EventStoreInitializer implements ApplicationRunner {

    private final EventRepository eventRepository;
    private final StoreRepository storeRepository;
    private final EventStoreRepository eventStoreRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (eventStoreRepository.count() > 0) {
            log.info("[EventStore] 더미 데이터 존재");
        } else {
            Event DUMMY_EVENT1 = eventRepository.findById(1L).orElseThrow();
            Event DUMMY_EVENT2 = eventRepository.findById(2L).orElseThrow();

            Store DUMMY_STORE1 = storeRepository.findById(1L).orElseThrow();
            Store DUMMY_STORE2 = storeRepository.findById(2L).orElseThrow();

            List<EventStore> eventStoreList = new ArrayList<>();

            EventStore DUMMY_EVENT_STORE1 = EventStore.builder()
                    .event(DUMMY_EVENT1)
                    .store(DUMMY_STORE1)
                    .memo("디어레터의 생일 이벤트")
                    .build();
            EventStore DUMMY_EVENT_STORE2 = EventStore.builder()
                    .event(DUMMY_EVENT1)
                    .store(DUMMY_STORE2)
                    .memo("블루베리의 결혼식 이벤트")
                    .build();
            EventStore DUMMY_EVENT_STORE3 = EventStore.builder()
                    .event(DUMMY_EVENT2)
                    .store(DUMMY_STORE1)
                    .memo("디어레터의 전역 축하 이벤트")
                    .build();
            EventStore DUMMY_EVENT_STORE4 = EventStore.builder()
                    .event(DUMMY_EVENT2)
                    .store(DUMMY_STORE2)
                    .memo("블루베리의 아이 생일 파티 이벤트")
                    .build();

            eventStoreList.add(DUMMY_EVENT_STORE1);
            eventStoreList.add(DUMMY_EVENT_STORE2);
            eventStoreList.add(DUMMY_EVENT_STORE3);
            eventStoreList.add(DUMMY_EVENT_STORE4);

            eventStoreRepository.saveAll(eventStoreList);
        }
    }
}
