package com.sg.silvergarden.vo.program;

import lombok.Data;

@Data
public class ProgramVO {
    private Integer PG_NO;
    private String PG_NAME;
    private String PG_START;
    private String PG_END;
    private String PG_DAYSOFWEEK;
    private String PG_CATEGORY;
    private String PG_TEACHER;
    private String PG_CONTENT;
}
