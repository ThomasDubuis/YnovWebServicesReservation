package com.tdubuis.reservationapp.service.movie;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

@Accessors(chain = true)
@NoArgsConstructor
@Data
public class MovieResponse {
    private String uid;
    private String name;
    private String description;
    private Integer rate;
    private Integer duration;
    //    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSX")
    private Date createdAt;
    //    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSSX")
    private Date updatedAt;
}
