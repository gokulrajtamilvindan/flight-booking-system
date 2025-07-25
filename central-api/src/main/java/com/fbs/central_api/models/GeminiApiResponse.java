package com.fbs.central_api.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GeminiApiResponse {
    private List<ResponseCandidate> candidates;
    private String modelVersion;
    private String responseId;
}
