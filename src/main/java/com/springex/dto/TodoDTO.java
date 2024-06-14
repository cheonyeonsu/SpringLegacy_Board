package com.springex.dto;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.time.LocalDate;

@ToString
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TodoDTO {

    private Long tno;

    @NotEmpty //not null
    private String title;

    @Future //할 일이니까 오늘 이후
    private LocalDate dueDate;

    private boolean finished;

    @NotEmpty
    private String writer;
}
