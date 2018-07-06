package com.example.jpademo.jungwon.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@AllArgsConstructor
@NoArgsConstructor
@Entity
public class MeatCut {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "MEATCUT_COMPANY", joinColumns = @JoinColumn(name = "MEATCUT_ID"), inverseJoinColumns = @JoinColumn(name = "COMPANY_ID"))
    private List<Company> companies;
}
