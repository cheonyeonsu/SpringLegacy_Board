package com.springex.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

import lombok.*;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {

    @Builder.Default        //필드에 기본값 설정
    @Min(value=1)           //최솟값
    @Positive               //양수여야한다.
    private int page = 1;   //현재 페이지 번호

    @Builder.Default        //필드에 기본값을 설정
    @Min(value=10)          //최솟값 10
    @Max(value=100)         //최댓값 100개
    @Positive
    private int size = 10;  //한페이지당 보여주는 데이터의 수

    public int getSkip() {
        //일반적으로 데이터베이스나 컬렉션에서 데이터를 가져올 때 0부터 시작하는 인덱스를 사용하는 경우가 많아서
        //페이지를 가져오려면 인덱스를 0번부터 시작해야한다.
        //ex.페이지 번호가 1이면 (1-1)*10은 0 -> 첫번째 페이지를 의미.
        //페이지 번호가 2이면 (2-1)*10 -> 10부터는 두번째 페이지의 데이터를 가져올 때 사용. 
        return (page -1) * 10;

    }
}