package com.jean.blue.web.dto.posts;

import com.jean.blue.domain.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class PostsListRequestDto {
    private Long postsId;
    private String title;
    private String content;
    private String author;
    private LocalDateTime modifiedDate;

    @Builder
    public PostsListRequestDto(Posts entity){
        this.postsId = entity.getPostsId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
        this.modifiedDate = entity.getModifiedDate();
    }

}
