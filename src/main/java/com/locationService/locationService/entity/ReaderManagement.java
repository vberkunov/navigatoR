package com.locationService.locationService.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "readerManagement")
@Entity
public class ReaderManagement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @OneToOne(targetEntity = RFIDReader.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "reader_id", referencedColumnName = "id")
    private RFIDReader reader;
    @OneToOne(targetEntity = Asset.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "asset_id", referencedColumnName = "id")
    private Asset asset;


}
