package com.dash.aws.lambda.domain;

import lombok.*;
import lombok.Data;

import javax.persistence.*;

@NoArgsConstructor
@Entity
@Data
@Table(name="myusers")
public class User {

    public void setId(String id) {
        this.id = id;
    }

    @Id
    private String id;

    @Column
    private String name;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

//    public Order(int id, String name) {
//        this.id = id;
//        this.name = name;
//    }


    public String getName() {
        return name;
    }
}
