package com.locationService.locationService.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Table(name = "asset")
@Entity
public class Asset {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String name;
    private String description;
    @OneToOne(mappedBy = "asset")
    private ReaderManagement manager;
    @JoinColumn(name = "tag_id", referencedColumnName = "id")
    private Tag tag;
}
