package com.deark.be.order.service;

import com.deark.be.design.domain.Design;
import com.deark.be.design.service.DesignService;
import com.deark.be.order.domain.Message;
import com.deark.be.order.domain.QA;
import com.deark.be.order.domain.type.DesignType;
import com.deark.be.order.domain.type.RequestDetailType;
import com.deark.be.order.dto.request.SubmitOrderRequest;
import com.deark.be.order.dto.response.BusinessHoursResponse;
import com.deark.be.order.dto.response.PickUpDateResponse;
import com.deark.be.order.dto.response.PickUpDateResponseList;
import com.deark.be.order.repository.MessageRepository;
import com.deark.be.order.repository.QARepository;
import com.deark.be.store.domain.BusinessHours;
import com.deark.be.store.domain.Store;
import com.deark.be.store.dto.response.DesignCreamResponse;
import com.deark.be.store.dto.response.DesignCreamResponseList;
import com.deark.be.store.dto.response.DesignSizeResponse;
import com.deark.be.store.dto.response.DesignSizeResponseList;
import com.deark.be.store.service.BusinessHoursService;
import com.deark.be.store.service.StoreService;
import com.deark.be.user.domain.User;
import com.deark.be.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final MessageRepository messageRepository;
    private final QARepository qaRepository;

    private final UserService userService;
    private final StoreService storeService;
    private final DesignService designService;
    private final BusinessHoursService businessHoursService;

    @Transactional
    public Long submitOrder(SubmitOrderRequest request, Long userId) {
        User user = userService.findUser(userId);
        Store store = storeService.getStoreByIdOrThrow(request.storeId());

        request.validateDesignParams();
        request.validateRequestDetailParams();

        Design design = request.designType() == DesignType.STORE
                ? designService.getDesignByIdOrThrow(request.designId())
                : null;

        Design requestDetailDesign = request.requestDetailType() == RequestDetailType.EVENT
                ? designService.getDesignByIdOrThrow(request.requestDetailDesignId())
                : null;

        Message message=messageRepository.save(request.toEntity(user,store,design,requestDetailDesign));
        List<QA> qaList =request.answers().stream()
                .map(answer -> answer.toEntity(message))
                .toList();
        qaRepository.saveAll(qaList);

        return message.getId();
    }

    public PickUpDateResponseList getPickUpDate(Long storeId) {
        Store store = storeService.getStoreByIdOrThrow(storeId);

        List<BusinessHours> businessHours = businessHoursService.getBusinessHoursByStore(store);
        List<PickUpDateResponse> pickUpDates = businessHours.stream()
                .map(businessHour -> PickUpDateResponse.from(businessHour.getBusinessDay()))
                .toList();

        return PickUpDateResponseList.from(pickUpDates);
    }

    public BusinessHoursResponse getBusinessHours(Long storeId, LocalDate pickUpDate) {
        Store store = storeService.getStoreByIdOrThrow(storeId);
        BusinessHours businessHours = businessHoursService.getBusinessHoursByDate(store, pickUpDate);

        return BusinessHoursResponse.from(businessHours);
    }

    public DesignSizeResponseList getDesignSize(Long storeId) {
        Store store = storeService.getStoreByIdOrThrow(storeId);

        List<DesignSizeResponse> designSizeResponses = store.getSizeList().stream()
                .distinct()
                .map(DesignSizeResponse::from)
                .toList();

        return DesignSizeResponseList.from(designSizeResponses);
    }

    public DesignCreamResponseList getDesignCream(Long storeId) {
        Store store = storeService.getStoreByIdOrThrow(storeId);

        List<DesignCreamResponse> designSizeResponses = store.getCreamList().stream()
                .distinct()
                .map(DesignCreamResponse::from)
                .toList();

        return DesignCreamResponseList.from(designSizeResponses);
    }
}