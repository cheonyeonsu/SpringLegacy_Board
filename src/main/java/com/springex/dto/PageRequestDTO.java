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

    private String link;

    public int getSkip() {
        //일반적으로 데이터베이스나 컬렉션에서 데이터를 가져올 때 0부터 시작하는 인덱스를 사용하는 경우가 많아서
        //페이지를 가져오려면 인덱스를 0번부터 시작해야한다.
        //ex.페이지 번호가 1이면 (1-1)*10은 0 -> 첫번째 페이지를 의미.
        //페이지 번호가 2이면 (2-1)*10 -> 10부터는 두번째 페이지의 데이터를 가져올 때 사용. 
        return (page -1) * 10;
    }

    public String getLink() {
        if(link == null) {
            //StringBuilder : 문자열을 동적으로 생성하고 조작하기 위한 클래스.
            StringBuilder builder = new StringBuilder();
            builder.append("page=" +this.page);
            builder.append("&size=" + this.size);
            link = builder.toString();
            //StringBuilder의 append 메서드를 사용하여 문자열을 추가할 수가 있으며,
            // 마지막에 toString메서드를 호출하여 StringBulder객체를 일반적인 String객체로 변환.
            // 이렇게 하면 문자열의 조작이 빠르고 메모리를 효율적으로 처리할 수 있다.
            // 성능 문제를 일으킬 수 있으므로 문자열의 연결작업에 StringBulder를 사용하는 것이 좋은 습관이다.
        }
        return link;
    }

}