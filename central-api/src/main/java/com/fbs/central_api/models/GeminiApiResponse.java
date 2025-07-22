package com.fbs.central_api.models;

import lombok.*;

import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GeminiApiResponse {
    private List<ResponseCandidate> candidates;
    private String modelVersion;
    private String responseId;
}
