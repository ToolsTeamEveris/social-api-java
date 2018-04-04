/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.social.entity;
import lombok.Data;


import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author iguijarm
 */
@Entity
@Data
public class Message {
    
    @Id
    private String mFrom;
    private String mText;
}
