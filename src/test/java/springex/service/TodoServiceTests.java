package springex.service;

import java.time.LocalDate;

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
}
