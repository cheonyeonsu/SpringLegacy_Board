package com.springex.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;

import lombok.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.Arrays;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {

    @Builder.Default    // 필드에 기본값 설정
    @Min(value=1)       // 최솟값
    @Positive           // 양수여야 한다.
    private int page = 1;// 현재 페이지 번호

    @Builder.Default    // 필드에 기본값을 설정
    @Min(value=10)      // 최솟값 10
    @Max(value=100)        // 최댓값 100
    @Positive
    private int size = 10;  // 한페이지당 보여주는 데이터의 수

    private String link;

    // 검색종류
    private String[] types;
    // 제목(t), 작성자(w) 검색에 사용하는 문자열
    private String keyword;
    // 완료여부에 사용되는 boolean
    private boolean finished;
    // 시작날짜
    private LocalDate from;
    // 끝날짜
    private LocalDate to;

    public int getSkip() {
        // 일반적으로 데이터베이스나 컬렉션에서 데이터를 가져올때 0부터 시작하는 인덱스를 사용하는 경우가 많아서
        // 페이지를 가져오려면 인덱스 0번 부터 시작해야 한다.
        // ex)페이지 번호 1이면 (1-1)*10 은 0 첫번째 페이지의 데이터를 가져올때 사용.
        // 페이지번호가 2이면 (2-1)*10 10 부터는 두번째 페이지의 데이터를 가져올때 사용.
        return (page -1) * 10;
    }
    public String getLink() {
    //모든 검색,필터링 조건을 쿼리스트링으로 구성해야 하기 때문에
        StringBuilder builder = new StringBuilder();
        builder.append("page=" +this.page);
        builder.append("&size=" + this.size);

        if(finished) {
            builder.append("&finished=on");
        }

        if(types != null && types.length > 0) {
            for(int i = 0; i < types.length; i++) {
                builder.append("&types=" + types[i]);
            }
        }

        if(keyword != null) {
            try {
                builder.append("&keyword=" + URLEncoder.encode(keyword, "UTF-8"));
            }catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }

        if(from != null) {
            builder.append("&from=" + from.toString());
        }

        if(to != null) {
            builder.append("&to=" + to.toString());
        }
        return builder.toString();
    }
    public boolean checkType(String type) {
        if(types == null || types.length == 0 ) {
            return false;
        }
//        배열인 types에 대한 스트림을 생성하고
//        anyMatch: 매개변수의 값을 확인해서 충족하는 요소가 하나라도 있는지 확인
//                type::equals는 equals메서드를 type 객체에 대해 호출하는 메서드.
//        types 배열의 요소 중 하나와 type을 비교하여 일치하는 확인.
        // String[t,w]
        // t값이
        return Arrays.stream(types).anyMatch(type::equals);
    }
}