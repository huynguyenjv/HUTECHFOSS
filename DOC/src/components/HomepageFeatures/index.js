import clsx from 'clsx';
import Heading from '@theme/Heading';
import styles from './styles.module.css';

const FeatureList = [
  {
    title: 'Thân thiện và dễ sử dụng',
    Img: require('/static/img/easy.png').default, // Giữ lại SVG
    description: (
      <>
        Website được thiết kế đơn giản, dễ hiểu, giúp người dùng dễ dàng tìm kiếm thông tin cứu trợ và đăng ký nhận hỗ trợ một cách nhanh chóng.
      </>
    ),
  },
  {
    title: 'Nhanh chóng và hiệu quả',
    Img: require('/static/img/quickly.png').default,
    description: (
      <>
        Cung cấp thông tin về thiên tai và cứu trợ chính xác, nhanh chóng, giúp người dân và các trung tâm cứu trợ hành động kịp thời trong các tình huống khẩn cấp.
      </>
    ),
  },
  {
    title: 'Bảo mật mạnh mẽ',
    Img: require('/static/img/security.png').default, // Giữ lại SVG
    description: (
      <>
        Ưu tiên bảo vệ thông tin của người dùng với các biện pháp bảo mật tiên tiến, bao gồm mã hóa dữ liệu và bảo vệ quyền riêng tư của người dùng khi họ đăng ký nhận cứu trợ hoặc cung cấp thông tin.
      </>
    ),
  },
];

function Feature({Img, title, description}) {
  return (
    <div className={clsx('col col--4')}>
      <div className="text--center">
        {/* Sử dụng thẻ <img> để hiển thị hình ảnh PNG */}
        <img src={Img} className={styles.featureImg} alt={title} />
      </div>
      <div className="text--center padding-horiz--md">
        <Heading as="h3">{title}</Heading>
        <p>{description}</p>
      </div>
    </div>
  );
}

export default function HomepageFeatures() {
  return (
    <section className={styles.features}>
      <div className="container">
        <div className="row">
          {FeatureList.map((props, idx) => (
            <Feature key={idx} {...props} />
          ))}
        </div>
      </div>
    </section>
  );
}
