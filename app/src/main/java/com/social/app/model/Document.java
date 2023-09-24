package com.social.app.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Document")
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long documentId;
    private String documentName;
    private String description;
    private String url;
    private int cost;
    private boolean isApproved;
    private Date time;

    @ManyToOne
    @JoinColumn(name="groupId")
    private Groups group;

    @ManyToOne
    @JoinColumn(name="user_Id")
    private User user;

    @OneToMany(mappedBy = "document")
    private List<Bill> bills;

    @OneToMany(mappedBy = "document")
    private List<Rating> ratings;
}
