package com.example.demo.domain.string.model.response;

import com.example.demo.domain.string.model.StringModel;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "redis string response")
public record StringResponse(

    @Schema(description = "set string response")
    List<StringModel> response
) {

}
