package com.locationService.locationService.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "tag")
@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String epc;
    private double x;
    private double y;
    @OneToOne(mappedBy = "tag")
    private Asset asset;

}
