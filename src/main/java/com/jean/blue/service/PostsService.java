package com.jean.blue.service;

import com.jean.blue.domain.Posts;
import com.jean.blue.repository.PostsRepository;
import com.jean.blue.web.dto.posts.PostsListRequestDto;
import com.jean.blue.web.dto.posts.PostsResponseDto;
import com.jean.blue.web.dto.posts.PostsSaveRequestDto;
import com.jean.blue.web.dto.posts.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getPostsId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당글이 없습니다. id = " + id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    @Transactional
    public Long delete(Long id) {
        Posts posts = postsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당글이 없습니다. id = " + id));
        postsRepository.delete(posts);
        return id;
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("해당 글이 없습니다. id=" + id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListRequestDto> findAllDesc(){
        return postsRepository.findAllDesc().stream()
                .map(PostsListRequestDto::new)
                .collect(Collectors.toList());
    }

}
