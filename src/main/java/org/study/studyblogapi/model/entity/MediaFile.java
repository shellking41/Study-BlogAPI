package org.study.studyblogapi.model.entity;

import jakarta.persistence.*;
import lombok.*;
import org.study.studyblogapi.model.enums.UsageType;

@EqualsAndHashCode(callSuper = true)
@Builder
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="media_file")

public class MediaFile extends MediaFileBaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private UsageType usageType;

    private String path;

    @OneToOne(mappedBy = "userIcon")
    private User user;

    @ManyToOne
    @JoinColumn(name = "blog_post_id")
    private BlogPost blogPost;

}
