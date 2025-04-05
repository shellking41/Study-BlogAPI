import React from 'react';
import Form from '../services/Form.jsx';
import Header from '../components/Header.jsx';
import NavButton from '../components/NavButton.jsx';
import BlogPost from '../services/BlogPost.jsx';
import style from './styles/HomeStyle.module.css';

function Home() {
  return (
    <div className={style.container}>
      <div className={style.profile}></div>
      <div className={style.posts}>
        <BlogPost authorName={'lajos'} follow={'followed'} updatedAt={'2025.01.03'}
                  img={'https://www.davidhechler.com/wp-content/uploads/2016/07/500x500-dummy-image.jpg'}
                  content={'adsasddsadasads das adsads das ad sadsdsaadsads'} likes={15}
                  comments={[{ id: 1, commenter: 'lajos', content: 'dwa d wadw d wa dwwad awd aw!!!!' }]}
                  icon={'https://static-00.iconduck.com/assets.00/user-icon-2046x2048-9pwm22pp.png'}
        />
        <BlogPost authorName={'lajos'} follow={'followed'} updatedAt={'2025.01.03'}
                  img={'https://www.davidhechler.com/wp-content/uploads/2016/07/500x500-dummy-image.jpg'}
                  content={'adsasddsadasads das adsads das ad sadsdsaadsads'} likes={15}
                  comments={[{ id: 1, commenter: 'lajos', content: 'dwa d wadw d wa dwwad awd aw!!!!' }]}
                  icon={'https://static-00.iconduck.com/assets.00/user-icon-2046x2048-9pwm22pp.png'}
        />

      </div>
      <div className={style.followWrapper}>
        <div className={style.following}></div>
        <div className={style.followers}></div>
      </div>
    </div>

  );
}

export default Home;
