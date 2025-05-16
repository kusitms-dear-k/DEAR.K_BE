package com.deark.be.order.service;

import com.deark.be.design.domain.Design;
import com.deark.be.design.dto.response.StoreDesignSimpleResponse;
import com.deark.be.design.service.DesignService;
import com.deark.be.global.service.S3Service;
import com.deark.be.order.domain.Message;
import com.deark.be.order.domain.QA;
import com.deark.be.order.domain.type.DesignType;
import com.deark.be.order.domain.type.RequestDetailType;
import com.deark.be.order.dto.request.SubmitOrderRequest;
import com.deark.be.order.dto.response.*;
import com.deark.be.order.exception.OrderException;
import com.deark.be.order.exception.errorcode.OrderErrorCode;
import com.deark.be.order.repository.MessageRepository;
import com.deark.be.order.repository.QARepository;
import com.deark.be.store.domain.BusinessHours;
import com.deark.be.store.domain.Store;
import com.deark.be.store.dto.response.*;
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
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class OrderService {

    private final MessageRepository messageRepository;
    private final QARepository qaRepository;

    private final S3Service s3Service;
    private final UserService userService;
    private final StoreService storeService;
    private final DesignService designService;
    private final BusinessHoursService businessHoursService;

    @Transactional
    public Long submitOrder(SubmitOrderRequest request, Long userId, MultipartFile designImage, MultipartFile requestDetailImage) {
        User user = userService.findUser(userId);
        Store store = storeService.getStoreByIdOrThrow(request.storeId());

        String designUrl = (designImage != null) ? s3Service.uploadFile(designImage) : null;
        String requestDetailImageUrl = (requestDetailImage != null) ? s3Service.uploadFile(requestDetailImage) : null;

        validateDesignParams(request,designUrl);
        validateRequestDetailParams(request,requestDetailImageUrl);

        Design design = request.designType() == DesignType.STORE
                ? designService.getDesignByIdOrThrow(request.designId())
                : null;

        Design requestDetailDesign = request.requestDetailType() == RequestDetailType.EVENT
                ? designService.getDesignByIdOrThrow(request.requestDetailDesignId())
                : null;

        Message message = messageRepository.save(request.toEntity(user,store,design,requestDetailDesign,designUrl,requestDetailImageUrl));
        List<QA> qaList =request.answers().stream()
                .map(answer -> answer.toEntity(message))
                .toList();
        qaRepository.saveAll(qaList);

        return message.getId();
    }

    public void validateDesignParams(SubmitOrderRequest request, String designUrl) {
        if (request.designType() == DesignType.STORE) {
            if (request.designId() == null || designUrl != null) {
                throw new OrderException(OrderErrorCode.INVALID_STORE_DESIGN_CONFLICT);
            }
        } else if (request.designType() == DesignType.CUSTOM) {
            if (designUrl == null || designUrl.isBlank() || request.designId()  != null) {
                throw new OrderException(OrderErrorCode.INVALID_CUSTOM_DESIGN_CONFLICT);
            }
        }
    }

    public void validateRequestDetailParams(SubmitOrderRequest request, String requestDetailImageUrl) {
        if (request.requestDetailType() == RequestDetailType.EVENT) {
            if (request.requestDetailDesignId() == null || requestDetailImageUrl != null) {
                throw new OrderException(OrderErrorCode.INVALID_EVENT_REQUEST_DETAIL_CONFLICT);
            }
        } else if (request.requestDetailType() == RequestDetailType.CUSTOM) {
            if (requestDetailImageUrl == null || requestDetailImageUrl.isBlank() || request.requestDetailDesignId() != null) {
                throw new OrderException(OrderErrorCode.INVALID_CUSTOM_REQUEST_DETAIL_CONFLICT);
            }
        }
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

    public DesignSheetResponseList getDesignSheet(Long storeId) {
        Store store = storeService.getStoreByIdOrThrow(storeId);

        List<DesignSheetResponse> designSheetResponses = store.getSheetList().stream()
                .distinct()
                .map(DesignSheetResponse::from)
                .toList();

        return DesignSheetResponseList.from(designSheetResponses);
    }

    public List<StoreDesignSimpleResponse> getDesignListByStoreId(Long storeId) {
        return designService.getSimpleDesignListByStoreId(storeId);
    }
}