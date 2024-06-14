package springex.service;

import java.time.LocalDate;

import com.springex.dto.PageRequestDTO;
import com.springex.dto.PageResponseDTO;
import com.springex.dto.TodoDTO;
import com.springex.service.TodoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import lombok.extern.log4j.Log4j2;

@Log4j2
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations="file:src/main/webapp/WEB-INF/root-context.xml")
public class TodoServiceTests {

    @Autowired
    private TodoService todoService;

    @Test
    public void testRegister() {

        TodoDTO todoDTO = TodoDTO.builder()
                .title("test--------")
                .dueDate(LocalDate.now())
                .writer("user11")
                .build();

        todoService.register(todoDTO);
    }

    @Test
    public void testPaging() {
        //1개의 페이지에 10개를 담아서 PageRequestDTO에 넘김
        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().page(1).size(10).build();

        PageResponseDTO<TodoDTO> responseDTO = todoService.getList(pageRequestDTO);

        log.info(responseDTO);

        responseDTO.getDtoList().stream().forEach(todoDTO -> log.info(todoDTO));
        //responseDTO에서 getDtoList를 쓸 수 있는 이유 : PageResponseDTO에 private List<E> dtoList; 있기 때문에.
    }

}
