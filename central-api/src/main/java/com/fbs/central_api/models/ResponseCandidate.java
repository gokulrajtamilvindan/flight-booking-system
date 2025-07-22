package com.fbs.central_api.models;

import lombok.*;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseCandidate {
    private ResponseContent content;
    private String finishReason;
    private double avgLogprobs;
}
