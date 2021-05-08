package com.locationService.locationService.entity;


import com.locationService.locationService.entity.addition.ReaderCondition;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "rfidreader")
@Entity
public class RFIDReader {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String ip;
    private int port;
    //private PhysicalReaderTypeEnum PhysicalReaderType
    private String login;
    private String password;
    private ReaderCondition condition;
    private boolean started;
    private String errorMessage;
    private Timestamp dateCreated;
    private Timestamp dateModified;
    @OneToOne(mappedBy = "reader")
    private ReaderManagement manager;

}
