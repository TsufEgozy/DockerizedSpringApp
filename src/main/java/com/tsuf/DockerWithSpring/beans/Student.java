package com.tsuf.DockerWithSpring.beans;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

    @Entity
    @Data
    @Builder
    public class Student {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private String firstName;
        private String lastName;
        private String email;

    }
