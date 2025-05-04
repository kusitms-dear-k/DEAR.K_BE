package com.deark.be.event.controller;

import static com.deark.be.global.dto.ResponseTemplate.EMPTY_RESPONSE;

import com.deark.be.event.dto.request.EventCreateRequest;
import com.deark.be.event.dto.request.EventUpdateRequest;
import com.deark.be.event.dto.request.UpdateDesignMappingRequest;
import com.deark.be.event.dto.request.UpdateStoreMappingRequest;
import com.deark.be.event.dto.response.DesignInEventResponse;
import com.deark.be.event.dto.response.EventResponse;
import com.deark.be.event.dto.response.EventWithCheckResponse;
import com.deark.be.event.dto.response.StoreInEventResponse;
import com.deark.be.event.service.EventDesignService;
import com.deark.be.event.service.EventService;
import com.deark.be.event.service.EventStoreService;
import com.deark.be.global.dto.ResponseTemplate;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Event", description = "이벤트 관련 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/event")
public class EventController {
    private final EventService eventService;
    private final EventDesignService eventDesignService;
    private final EventStoreService eventStoreService;

    @GetMapping("/my_events")
    @Operation(summary = "내 이벤트 전체목록 조회", description = "로그인한 사용자가 생성한 이벤트 전체목록을 조회합니다.")
    public ResponseEntity<ResponseTemplate<Object>> getMyEvents(
            @AuthenticationPrincipal Long userId
    ) {
        List<EventResponse> events = eventService.getMyEvents(userId);
        return ResponseEntity.ok(ResponseTemplate.from(events));
    }

    @GetMapping("/{eventId}")
    @Operation(summary = "이벤트 상세 조회", description = "이벤트 ID로 해당 이벤트의 상세 정보(제목, 날짜, 주소)를 조회합니다.")
    public ResponseEntity<ResponseTemplate<Object>> getEventDetail(
            @PathVariable Long eventId,
            @AuthenticationPrincipal Long userId
    ) {
        EventResponse response = eventService.getEventDetail(eventId, userId);
        return ResponseEntity.ok(ResponseTemplate.from(response));
    }

    @GetMapping("/my_events/with-check/design")
    @Operation(summary = "디자인 포함 여부와 함께 내 이벤트 목록 조회", description = "디자인이 포함되어 있는 이벤트를 표시합니다.")
    public ResponseEntity<ResponseTemplate<Object>> getMyEventsWithCheckForDesign(
            @AuthenticationPrincipal Long userId,
            @RequestParam Long designId
    ) {
        List<EventWithCheckResponse> response = eventService.getMyEventsWithCheckForDesign(userId, designId);
        return ResponseEntity.ok(ResponseTemplate.from(response));
    }

    @GetMapping("/my_events/with-check/store")
    @Operation(summary = "스토어 포함 여부와 함께 내 이벤트 목록 조회", description = "스토어가 포함되어 있는 이벤트를 표시합니다.")
    public ResponseEntity<ResponseTemplate<Object>> getMyEventsWithCheckForStore(
            @AuthenticationPrincipal Long userId,
            @RequestParam Long storeId
    ) {
        List<EventWithCheckResponse> response = eventService.getMyEventsWithCheckForStore(userId, storeId);
        return ResponseEntity.ok(ResponseTemplate.from(response));
    }

    @PostMapping("")
    @Operation(summary = "새 이벤트 생성", description = "새로운 이벤트를 생성합니다.")
    public ResponseEntity<ResponseTemplate<Object>> createEvent(
            @Valid @RequestBody EventCreateRequest request,
            @AuthenticationPrincipal Long userId
    ){
    Long eventId=eventService.createEvent(userId, request);
    return ResponseEntity
            .ok(ResponseTemplate.from(eventId));
    }

    @DeleteMapping("/{eventId}")
    @Operation(summary = "이벤트 삭제", description = "이벤트를 삭제합니다. 관련된 디자인 및 스토어 연결도 제거됩니다.")
    public ResponseEntity<ResponseTemplate<Object>> deleteEvent(
            @PathVariable Long eventId,
            @AuthenticationPrincipal Long userId
    ) {
        eventService.deleteEvent(eventId, userId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(EMPTY_RESPONSE);
    }

    @PutMapping("/{eventId}")
    @Operation(summary = "이벤트 수정", description = "이벤트 정보를 수정합니다.")
    public ResponseEntity<ResponseTemplate<Object>> updateEvent(
            @PathVariable Long eventId,
            @Valid @RequestBody EventUpdateRequest request,
            @AuthenticationPrincipal Long userId
    ) {
        eventService.updateEvent(eventId, userId, request);
        return ResponseEntity.status(HttpStatus.OK).body(EMPTY_RESPONSE);
    }

    @GetMapping("/{eventId}/designs")
    @Operation(summary = "이벤트 내 디자인 목록 조회", description = "특정 이벤트에 포함된 모든 디자인 목록을 조회합니다.")
    public ResponseEntity<ResponseTemplate<Object>> getDesignsInEvent(
            @PathVariable Long eventId,
            @AuthenticationPrincipal Long userId
    ) {
        List<DesignInEventResponse> result = eventService.getDesignsInEvent(eventId, userId);
        return ResponseEntity.ok(ResponseTemplate.from(result));
    }

    @GetMapping("/{eventId}/stores")
    @Operation(summary = "이벤트 내 스토어 목록 조회", description = "특정 이벤트에 포함된 모든 스토어 목록을 조회합니다.")
    public ResponseEntity<ResponseTemplate<Object>> getStoresInEvent(
            @PathVariable Long eventId,
            @AuthenticationPrincipal Long userId
    ) {
        List<StoreInEventResponse> result = eventService.getStoresInEvent(eventId, userId);
        return ResponseEntity.ok(ResponseTemplate.from(result));
    }

    @PutMapping("/design/mapping")
    @Operation(summary = "디자인-이벤트 일괄 매핑", description = "특정 디자인이 속한 이벤트들을 재설정합니다.")
    public ResponseEntity<ResponseTemplate<Object>> updateDesignMappings(
            @RequestBody UpdateDesignMappingRequest request,
            @AuthenticationPrincipal Long userId
    ) {
        eventDesignService.updateDesignEventMappings(request,userId);
        return ResponseEntity.status(HttpStatus.OK).body(EMPTY_RESPONSE);
    }

    @PutMapping("/store/mapping")
    @Operation(summary = "스토어-이벤트 일괄 매핑", description = "특정 스토어가 속한 이벤트들을 재설정합니다.")
    public ResponseEntity<ResponseTemplate<Object>> updateStoreMappings(
            @RequestBody UpdateStoreMappingRequest request,
            @AuthenticationPrincipal Long userId
    ) {
        eventStoreService.updateStoreEventMappings(request,userId);
        return ResponseEntity.status(HttpStatus.OK).body(EMPTY_RESPONSE);
    }

    @DeleteMapping("/{eventId}/designs/{designId}")
    @Operation(summary = "이벤트 내 디자인 제거", description = "이벤트에서 특정 디자인을 제거합니다.")
    public ResponseEntity<ResponseTemplate<Object>> removeDesignFromEvent(
            @PathVariable Long eventId,
            @PathVariable Long designId,
            @AuthenticationPrincipal Long userId
    ) {
        eventDesignService.removeDesignFromEvent(eventId, designId, userId);
        return ResponseEntity.status(HttpStatus.OK).body(EMPTY_RESPONSE);
    }

    @DeleteMapping("/{eventId}/stores/{storeId}")
    @Operation(summary = "이벤트 내 스토어 제거", description = "이벤트에서 특정 스토어를 제거합니다.")
    public ResponseEntity<ResponseTemplate<Object>> removeStoreFromEvent(
            @PathVariable Long eventId,
            @PathVariable Long storeId,
            @AuthenticationPrincipal Long userId
    ) {
        eventStoreService.removeStoreFromEvent(eventId, storeId, userId);
        return ResponseEntity.status(HttpStatus.OK).body(EMPTY_RESPONSE);
    }
}
