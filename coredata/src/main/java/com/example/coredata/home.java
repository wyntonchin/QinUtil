package com.example.coredata;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

@Entity
public class home {
    @Id
    long id;

    String address;
}
