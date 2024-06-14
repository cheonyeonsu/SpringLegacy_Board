package com.springex.service;

import com.springex.domain.TodoVO;
import com.springex.dto.TodoDTO;
import com.springex.mapper.TodoMapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService{

    // 의존성 주입이 필요한 객체의 타입을 final로 고정하고
    // @RequiredArgsConstructor를 이용해서 생성자를 생성하는 방식을 사용.
    private final TodoMapper todoMapper;
    private final ModelMapper modelMapper;

    //ModelMappe를 이용해서 TodoDTO를 TodoVO로 변환하고
//    이것을 TodoMapper를 통해서 insert 처리.
    @Override
    public void register(TodoDTO todoDTO) {

        log.info(modelMapper);
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);

        log.info(todoVO);

        todoMapper.insert(todoVO);
    }
    @Override
    public List<TodoDTO> getAll() {
        // TodoVO의 리스트를 TodoDTO리스트로 변환하여 반환.
        List<TodoDTO> dtoList = todoMapper.selectAll().stream()
                .map(vo -> modelMapper.map(vo, TodoDTO.class))
                .collect(Collectors.toList());

        return dtoList;
    }
    @Override
    public TodoDTO getOne(Long tno) {
        TodoVO todoVO = todoMapper.selectOne(tno);

        TodoDTO todoDTO = modelMapper.map(todoVO, TodoDTO.class);

        return todoDTO;
    }

    @Override
    public void remove(Long tno){
        todoMapper.delete(tno);
    }

    @Override
    public void modify(TodoDTO todoDTO) {
        TodoVO todoVO = modelMapper.map(todoDTO, TodoVO.class);

        todoMapper.update(todoVO);
    }


}