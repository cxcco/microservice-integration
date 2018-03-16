/*
 * Copyright (c) 2018.
 * 项目名称：auth-gateway-backend
 * 文件名称：BaseEntity.java
 * Date：18-3-16 下午4:05
 * Author：boni
 */

package com.wisfarm.auth.entity;

import com.wisfarm.auth.utils.TimestampAdapter;
import lombok.Data;

import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Created by keets on 2016/12/5.
 */
@Data
public abstract class BaseEntity implements Serializable {

    private static final long serialVersionUID = 8388417013613884411L;

    @XmlJavaTypeAdapter(TimestampAdapter.class)
    private Timestamp createTime;

    @XmlJavaTypeAdapter(TimestampAdapter.class)
    private Timestamp updateTime;

    private int createBy;

    private int updateBy;
}
