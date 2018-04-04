/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.social.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

/**
 *
 * @author iguijarm
 */
@Entity
@Data
public class OutputMessage {
    
    @Id
    private String mFrom;
    private String mText;
    private String mTime;

    public OutputMessage(final String from, final String text, final String time) {
        
    }
}
