package com.deark.be.event.repository;

import com.deark.be.event.dto.response.StoreInEventResponse;
import java.util.List;

public interface EventStoreRepositoryCustom {
    List<StoreInEventResponse> findStoresInEventWithDesignImages(Long eventId, Long userId);
}
