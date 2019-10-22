package sample.repository.models;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Builder
@Getter
@EqualsAndHashCode
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
@Document(value = "myTable")
public class MyTableEntity {
    @Id
    @Indexed
    @Setter
    private Long id;

    @Setter
    private String name;

    @Setter
    private String email;

    @Setter
    private Date creationDate;

    @Setter
    private String address;
}
