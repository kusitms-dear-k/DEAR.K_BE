package com.deark.be.alarm.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Alarm", description = "알림 관련 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/alarm")
public class AlarmController {
}
