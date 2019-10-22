package com.example.demo.user

import javax.persistence.*

@Entity
@Table(name="yuni")
class User(
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY) var id:Long? = null,
        var userId: String,
        var password: String,
        var name: String
) {
    constructor(userId: String, password: String, name: String): this(null, userId, password, name)
}