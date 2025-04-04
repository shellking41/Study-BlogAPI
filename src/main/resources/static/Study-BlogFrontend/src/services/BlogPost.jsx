import React from 'react';
import Header from '../components/Header.jsx';
import style from './styles/BlogPostStyle.module.css';

function BlogPost({ authorName, updatedAt, follow, likes, img, content, comments }) {
  return (
    <>
      <div className={style.post}>
        <div className={style.header}>
          <Header tag={'h4'} className={style.usernameHeader} text={authorName} />
          <span>*</span>
          <p>{updatedAt}</p>
          <span>*</span>
          <button className={style.followButton}>{follow}</button>
        </div>
        <div className={style.content}>
          <p>{content}</p>
          <img className={style.contentImg} src={img} />
        </div>
        <div className={style.footer}>
          <Header tag={'h5'} text={`${likes} likes`} className={''} />
        </div>
        <div className={style.contentButtons}>
          <button className={style.likeButton}>Like</button>
          <button className={style.commentButton}>Comment</button>
        </div>
        <div className={style.comments}>
          {comments && comments.length > 0 && comments.map((comment) => {
            return (
              <div key={comment.id} className={style.commentWrapper}>
                <Header tag={'h5'} text={comment.commenter} className={style.commenterHeader} />
                <p>{comment.content}</p>
              </div>
            );
          })}
        </div>
      </div>
    </>
  );
}

export default BlogPost;
