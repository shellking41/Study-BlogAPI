package org.study.studyblogapi.model.entity;

import jakarta.persistence.*;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="BlogPost")
public class BlogPost  extends  BaseEntity{

    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String content;
    private String category;

}
