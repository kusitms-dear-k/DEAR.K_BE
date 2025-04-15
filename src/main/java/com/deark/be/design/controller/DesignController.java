package com.deark.be.design.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Design", description = "케이크 디자인 관련 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/design")
public class DesignController {
}
