package com.raphael.itoosabi.data.models;

import com.raphael.itoosabi.audit.Auditable;
import jakarta.persistence.*;
import lombok.*;



@Entity
@Table(name = "comments")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Comment extends Auditable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

}
