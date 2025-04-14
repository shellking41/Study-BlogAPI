package org.study.studyblogapi.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.study.studyblogapi.exception.BlogPostNotFoundException;
import org.study.studyblogapi.mapper.BlogPostMapper;
import org.study.studyblogapi.model.dto.request.BlogPostRequest;
import org.study.studyblogapi.model.dto.response.BlogPostResponse;
import org.study.studyblogapi.model.entity.BlogPost;
import org.study.studyblogapi.model.entity.User;
import org.study.studyblogapi.repository.BlogPostRepository;
import org.study.studyblogapi.repository.UserRepository;
import org.study.studyblogapi.service.IBlogPostService;
import org.study.studyblogapi.service.IUserService;

@Service
@RequiredArgsConstructor
public class BlogPostService implements IBlogPostService {

    final BlogPostRepository blogPostRepository;
    final IUserService userService;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public BlogPostResponse createBlogPost(BlogPostRequest request) {
        User user=userService.getAuthenticatedUser();

       BlogPost blogPost= BlogPostMapper.toEntity(request,user);

       blogPostRepository.save(blogPost);

       return BlogPostMapper.toResponse(blogPost);
        }

    @Override
    @Transactional
    public boolean toggleLike(Long postId) {
        User user=userService.getAuthenticatedUser();
        BlogPost blogPost=blogPostRepository.findById(postId)
                .orElseThrow(()-> new BlogPostNotFoundException("Post Not Found"));
        boolean hasLiked= blogPostRepository.hasUserLikedPost(postId,user.getId());

        if(hasLiked){
           blogPost.getLikedByUsers().remove(user);
           blogPost.setLikeCount(blogPost.getLikeCount()-1);
        }else{
            blogPost.getLikedByUsers().add(user);
            blogPost.setLikeCount(blogPost.getLikeCount()+1);
        }

        blogPostRepository.save(blogPost);
        return !hasLiked;
    }

}
