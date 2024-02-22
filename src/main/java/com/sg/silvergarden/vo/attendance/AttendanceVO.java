package com.sg.silvergarden.vo.attendance;

import lombok.Data;

import java.util.Date;

@Data
public class AttendanceVO {
    private Date AT_DATE;
    private String E_NO;
    private Date AT_START;
    private Date AT_END;
    private String AT_STATUS;
    private Date REG_DATE;
    private Date MOD_DATE;
    private String REG_ID;
    private String MOD_ID;
}
