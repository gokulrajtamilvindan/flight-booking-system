package com.fbs.central_api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseCandidate {
    private ResponseContent content;
    private String finishReason;
    private double avgLogprobs;
}
