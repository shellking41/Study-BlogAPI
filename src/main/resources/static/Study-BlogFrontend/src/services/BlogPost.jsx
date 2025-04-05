import React, { useState } from 'react';
import Header from '../components/Header.jsx';
import style from './styles/BlogPostStyle.module.css';
import { GoDotFill } from 'react-icons/go';
import { IoIosArrowDown } from 'react-icons/io';

function BlogPost({ authorName, updatedAt, follow, likes, img, content, comments, icon }) {

  const [showComments, setShowComments] = useState(false);

  const handleShowComments = (e) => {
    setShowComments(e.target.checked);
  };

  return (
    <>
      <div className={style.post}>
        <div className={style.headerWrapper}>
          <img className={style.userIcon} alt={'User Icon'} src={icon} />
          <div className={style.headerTextWrapper}>
            <Header tag={'h3'} className={style.usernameHeader} text={authorName} />
            <GoDotFill className={style.dot} />
            <p>{updatedAt}</p>
            <GoDotFill className={style.dot} />
            <button className={style.followButton}>{follow}</button>
          </div>
        </div>
        <div className={style.content}>
          <p>{content}</p>
          <img className={style.contentImg} src={img} alt={'Content Image'} />
        </div>
        <div className={style.footer}>
          <Header tag={'h5'} text={`${likes} likes`} className={''} />
          <div className={style.contentButtons}>
            <button className={style.likeButton}>Like</button>
            <button className={style.commentButton}>Comment</button>
          </div>
          <div className={style.collapserWrapper}>
            <input type={'checkbox'} id="checkbox" className={style.checkbox} onChange={(e) => handleShowComments(e)} />

            <label className={style.collapserText} htmlFor={'checkbox'}>
              Comments
              <IoIosArrowDown className={style.collapserArrow} />
            </label>

          </div>
        </div>

        <div className={`${style.comments} ${showComments ? style.hideComments : ''}`}>
          {comments && comments.length > 0 && comments.map((comment) => {
            return (
              <span key={comment.id} className={style.commentWrapper}>
                <Header tag={'h5'} text={comment.commenter} className={style.commenterHeader} />
                <p>{comment.content}</p>
              </span>
            );
          })}
        </div>

      </div>
    </>
  );
}

export default BlogPost;
