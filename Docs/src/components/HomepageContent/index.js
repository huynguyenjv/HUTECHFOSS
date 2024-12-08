import styles from './styles.module.css';
import clsx from 'clsx';

export default function HomepageContent() {
  return (
    <main className={styles.content}>
        <section className={styles.description}>
          <div className={clsx("row", styles.descriptionRow)}>
            <div className={clsx("col col--6", styles.imgColumn)}>
              <img src="img/tinhnguyenvien.png" className={styles.demoImg} />
            </div>
            <div className="col col--6">
            <h3>Đăng ký cứu trợ - Dự án hệ thống cứu trợ V-Relief</h3> <p>Dự án V-Relief là một hệ thống cứu trợ thiết yếu dành cho các tình huống thiên tai. Với sứ mệnh kết nối và sẻ chia, hệ thống cho phép người dân vùng bị ảnh hưởng dễ dàng đăng ký nhận các nhu yếu phẩm từ sự chung tay hỗ trợ của cộng đồng trên khắp cả nước.Với V-Relief, chúng tôi hy vọng mang lại sự an tâm và kịp thời giúp đỡ những người đang đối mặt với khó khăn sau bão lũ. </p>
            </div>
          </div>
        </section>
        <section className={styles.description}>
          <div className={clsx("row", styles.descriptionRow)}>
            <div className="col col--6">
              <h3>Cứu Trợ — Dự án hệ thống cứu trợ V-Relief</h3>
              <p>Mọi người dân trên toàn đất nước Việt Nam có thể truy cập vào hệ thông để đăng kí cứu trợ, cung cấp nhu yếu phẩm cho các bà con ở nơi thiên tai bão lũ, nhằm giúp khắc phục hậu quả sau thiên tai.</p>
            </div>
            <div className={clsx("col col--6", styles.imgColumn)}>
              <img src="img/nhancuutro.png" className={styles.demoImg} />
            </div>
          </div>
        </section>
        <section className={styles.description}>
          <div className={clsx("row", styles.descriptionRow)}>
          <div className={clsx("col col--6", styles.imgColumn)}>
              <img src="img/mapweather2.png" className={styles.demoImg} />
            </div>
            <div className="col col--6">
              <h3>Theo dõi bão và nhận thông báo - V-Relief</h3>
              <p>Hệ thống có chức năng theo dõi các cơn báo và thời tiết . Người dân có thể nhận được thông tin khi bão sắp đổ bộ vào đất liền bằng cách thông qua email và số điện thoại của người dân đã cung cấp cho hệ thống của chúng tôi.
              </p>
            </div>
          </div>
        </section>
        <section className={styles.description}>
          <div className={clsx("row", styles.descriptionRow)}>
            <div className="col col--6">
              <h3>Trung tâm điều phối thông minh - V-Relief</h3>
              <p>Trung tâm điều phối sẽ dựa vào các thông tin đăng kí nhận cứu trợ và cứu trợ sau đó sẽ phân phối lương thực về đúng nơi đúng chỗ và đúng số lương cần thiết. Và để tránh các trường hợp nơi có và nơi thiếu </p>
            </div>
            <div className={clsx("col col--6", styles.imgColumn)}>
              <img src="img/cuuho.png" className={styles.demoImg} />
            </div>
          </div>
        </section>
      </main>
  );
}
