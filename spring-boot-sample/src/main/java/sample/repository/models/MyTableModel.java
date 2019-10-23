package sample.repository.models;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Builder
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Document(value = "MyTable")
public class MyTableModel {
    @Id
    @Setter(AccessLevel.PRIVATE)
    private String code;

    private String name;

    private String email;

    private String creationDate;

    private String address;
}
