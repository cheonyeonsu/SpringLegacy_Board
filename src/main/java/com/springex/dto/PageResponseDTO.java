package com.springex.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
//<E> -> 제네릭타입
//       :클래스 내부에서 지정하는 것이 아닌 외부에서 사용자에 의해 지정되는 것
//제네릭 타입을 이용하는 이유 : 다른 종류의 객체를 이용해서 해당 클래스를 구성할 수 있도록.
///ex. 게시판의 회원정보 등에서도 페이징 처리가 필요할 수 있기 때문에 공통적인 처리를 위해서 제네릭으로 구성함
public class PageResponseDTO<E>{

    private int page;
    private int size;
    private int total;

    //시작 페이지 번호
    private int start;
    //끝 페이지 번호
    private int end;

    // 이전 페이지의 존재 여부
    private boolean prev;
    // 다음 페이지의 존재 여부
    private boolean next;

    private List<E> dtoList;

    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(PageRequestDTO pageRequestDTO,
                           List<E> dtoList, int total) {

        //어디서든 쓸 수 있어야하므로 this.
        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();

        this.total = total;
        this.dtoList = dtoList;
    }


}
