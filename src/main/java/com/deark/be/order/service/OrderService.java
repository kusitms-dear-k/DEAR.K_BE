package com.deark.be.order.service;

import com.deark.be.design.domain.Design;
import com.deark.be.design.service.DesignService;
import com.deark.be.order.domain.Message;
import com.deark.be.order.domain.QA;
import com.deark.be.order.domain.type.DesignType;
import com.deark.be.order.domain.type.RequestDetailType;
import com.deark.be.order.dto.request.SubmitOrderRequest;
import com.deark.be.order.repository.MessageRepository;
import com.deark.be.order.repository.QARepository;
import com.deark.be.store.domain.Store;
import com.deark.be.store.service.StoreService;
import com.deark.be.user.domain.User;
import com.deark.be.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}