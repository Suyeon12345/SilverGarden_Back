package com.sg.silvergarden.controller.payment;

import com.google.gson.Gson;
import com.sg.silvergarden.service.payment.PayUrlService;
import com.sg.silvergarden.service.payment.PaymentService;
import com.sg.silvergarden.vo.payment.PayTokenResponse;
import com.sg.silvergarden.vo.payment.PaymentResponse;
import com.sg.silvergarden.vo.payment.RefundResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Log4j2
@RestController
@RequestMapping("/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PayUrlService payUrlService;

    @Autowired
    PaymentService paymentService = null;

    @GetMapping("paylist")
    public String payList(@RequestParam Map<String, Object> pmap){
        List<Map<String, Object>> list = null;
        list = paymentService.payList(pmap);
        Gson g = new Gson();
        String temp = g.toJson(list);

        return temp;
    }

    @GetMapping("payclientlist")
    public String payClientList(@RequestParam Map<String, Object> pmap){
        List<Map<String, Object>> list = null;
        list = paymentService.payClientList(pmap);
        Gson g = new Gson();
        String temp = g.toJson(list);

        return temp;
    }

    @PostMapping("/refund")
    public void payRefund(@RequestBody Map<String, Object> pmap) {

        String merchant_uid = (String) pmap.get("merchant_uid");
        String accessToken = payUrlService.getToken();

        //refund
        HttpHeaders refundHeader = new HttpHeaders();
        refundHeader.add("Content-type", "application/json");
        refundHeader.add("Authorization", accessToken);
        String refundBody = "{\"merchant_uid\": \""+ merchant_uid +"\"}";
        HttpEntity<String> refundRequest = new HttpEntity<>(refundBody, refundHeader);
        RestTemplate rt = new RestTemplate();
        ResponseEntity<String> response = rt.exchange("https://api.iamport.kr/payments/cancel", HttpMethod.POST, refundRequest, String.class);

        String responsebody = response.getBody();
        Gson g = new Gson();
        RefundResponse refundResponse = g.fromJson(responsebody, RefundResponse.class);
        int code = refundResponse.getCode();
        if(code == 0){
            paymentService.payRefund(pmap);
        }
    }



}
