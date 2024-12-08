
import styles from './styles.module.css';

const TechnologiesAndSponsorsList = [
  {
    title: 'Công Nghệ Dự Kiến',
  },
  {
    src: require('@site/static/img/n8n.png').default,
    href: 'http://n8n.io/',
  },
  {
    src: require('@site/static/img/reactjs.png').default,
    href: 'https://react.dev/',
  },
  {
    src: require('@site/static/img/botpress.png').default,
    href: 'https://botpress.com/',
  },
  {
    src: require('@site/static/img/openAI.png').default,
    href: 'https://openai.com/',
  },
  {
    src: require('@site/static/img/windy.png').default,
    href: 'https://windy.com/',
  },
  {
    src: require('@site/static/img/openWeather.png').default,
    href: 'https://openweathermap.org/',
  }

];

const SupportList = [
  {
    title: 'Sponsors and Organization',
  },
  {
    src: require('@site/static/img/logo-hutech.png').default,
    href: 'https://www.hutech.edu.vn/',
  },
  {
    src: require('@site/static/img/vfossa.png').default,
    href: 'https://vfossa.vn/',
  },
  {
    src: require('@site/static/img/olp.png').default,
    href: 'https://olp.haui.edu.vn/',
  },
  {
    src: require('@site/static/img/HaUI.png').default,
    href: 'https://www.haui.edu.vn/',
  },
];

// Component hiển thị từng mục với hình ảnh
function ItemSup({ src, href }) {
  return (
    <a href={href} target="_blank" rel="noopener noreferrer" className={styles.iconLink}>
      <img src={src} alt="" className={styles.iconImageSup} />
    </a>
  );
}
function ItemTS({ src, href }) {
  return (
    <a href={href} target="_blank" rel="noopener noreferrer" className={styles.iconLink}>
      <img src={src} alt="" className={styles.iconImageTS} />
    </a>
  );
}

// Component hiển thị danh sách công nghệ và nhà tài trợ
function TechnologiesAndSponsors() {
  return (
    <div className={styles.section}>
      <h3 className={styles.sectionTitle}>{TechnologiesAndSponsorsList[0].title}</h3>
      <div className={styles.horizontalImages}> {/* Di chuyển lớp này lên cấp cao hơn */}
        {TechnologiesAndSponsorsList.slice(1).map((item, index) => (
          <ItemTS key={index} src={item.src} href={item.href} />
        ))}
      </div>
    </div>
  );
}


function Support() {
  return (
    <div className={styles.section}>
      <h3 className={styles.sectionTitle}>{SupportList[0].title}</h3>
      <div className={styles.supportGrid}>
        {SupportList.slice(1).map((item, index) => (
          <div key={index} className={styles.supportGridItem}>
            <ItemSup src={item.src} href={item.href} />
          </div>
        ))}
      </div>
    </div>
  );
}
// Component chính cho trang HomePageOverview
export default function HomePageOverview() {
  return (
    <>
      <section className={styles['section-top']}>
        <TechnologiesAndSponsors />
      </section>

      <hr className={styles['divider']} />

      <section className={styles['section-bottom']}>
        <Support />
      </section>
    </>
  );
}
