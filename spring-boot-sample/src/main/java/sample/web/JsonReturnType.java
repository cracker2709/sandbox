package sample.web;

import lombok.*;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class JsonReturnType {
    private String code;
    private String message;
}