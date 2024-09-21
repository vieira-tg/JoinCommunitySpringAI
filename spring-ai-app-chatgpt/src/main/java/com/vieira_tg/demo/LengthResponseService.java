package com.vieira_tg.demo;


import org.springframework.context.annotation.Description;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@Description("Calcule a quantidade de caracter da resposta")
public class LengthResponseService implements Function<LengthResponseService.LengthRequest, LengthResponseService.LengthResponse> {
    @Override
    public LengthResponse apply(LengthRequest lengthRequest) {
        return new LengthResponse(lengthRequest.expression.length());
    }

    public record LengthRequest(String expression) {}
    public record LengthResponse(int length) {}

}
