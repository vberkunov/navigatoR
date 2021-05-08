package com.locationService.locationService.dto;

import com.locationService.locationService.entity.Tag;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ReaderManagerDTO {

    private long reader_id;
    private String width;
    private String height;
    private String epc;
    private String description;
}
