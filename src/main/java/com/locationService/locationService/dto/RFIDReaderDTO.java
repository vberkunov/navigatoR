package com.locationService.locationService.dto;

import lombok.*;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class RFIDReaderDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String ip;
    private int port;
    private String login;
    private String password;
}
