package sample.views;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.util.Date;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MyTableView {

    private long id;

    private String name;

    private String email;

    private Date creationDate;

    private String address;

}